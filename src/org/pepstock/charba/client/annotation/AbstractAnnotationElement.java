/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package org.pepstock.charba.client.annotation;

import java.util.Date;
import java.util.Map;

import org.pepstock.charba.client.ChartNode;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.annotation.callbacks.ClickCallback;
import org.pepstock.charba.client.annotation.callbacks.EnterCallback;
import org.pepstock.charba.client.annotation.callbacks.LeaveCallback;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.CallbackProxy.Proxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.dom.BaseEventTarget.EventListenerCallback;
import org.pepstock.charba.client.dom.BaseEventTypes;
import org.pepstock.charba.client.dom.BaseNativeEvent;
import org.pepstock.charba.client.dom.elements.Canvas;
import org.pepstock.charba.client.dom.elements.Context2dItem;
import org.pepstock.charba.client.items.ChartAreaNode;
import org.pepstock.charba.client.items.ScaleItem;
import org.pepstock.charba.client.items.ScalesNode;

/**
 * Internal class in order to manage annotation element.<br>
 * This class has been by the plugin in order to configure and draw the annotation.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 * @param <T> type of annotation configuration element
 */
abstract class AbstractAnnotationElement<T extends AbstractAnnotation> {

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to manage the click event
	private final CallbackProxy<EventListenerCallback> clickCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to manage the mouse-move event
	private final CallbackProxy<EventListenerCallback> mouseMoveCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the mouse-leave function
	private final CallbackProxy<EventListenerCallback> mouseLeaveCallbackProxy = JsHelper.get().newCallbackProxy();

	private final T configuration;

	private final IsChart chart;

	private final ClickCallback clickCallback;

	private final EnterCallback enterCallback;

	private final LeaveCallback leaveCallback;

	private boolean isHovered = false;

	/**
	 * Creates an annotation element by an annotation configuration.
	 * 
	 * @param chart chart instance
	 * @param configuration annotation configuration element
	 */
	AbstractAnnotationElement(IsChart chart, T configuration) {
		this.chart = chart;
		this.configuration = configuration;
		// stores callbacks instances
		this.clickCallback = configuration.getClickCallback();
		this.enterCallback = configuration.getEnterCallback();
		this.leaveCallback = configuration.getLeaveCallback();
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// fires the event
		clickCallbackProxy.setCallback((context, event) -> onClick(event));
		// fires the event
		mouseMoveCallbackProxy.setCallback((context, event) -> onMouseMove(event));
		// fires the event
		mouseLeaveCallbackProxy.setCallback((context, event) -> onMouseLeave(event));
	}

	/**
	 * Returns the chart instance.
	 * 
	 * @return the chart instance
	 */
	final IsChart getChart() {
		return chart;
	}

	/**
	 * Returns the annotation element configuration.
	 * 
	 * @return the annotation element configuration
	 */
	final T getConfiguration() {
		return configuration;
	}

	/**
	 * Returns <code>true</code> if the annotation element is ready to be drawn, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if the annotation element is ready to be drawn, otherwise <code>false</code>
	 */
	abstract boolean isConsistent();

	/**
	 * Calculates the limits, size of annotation, managing listeners.<br>
	 * This is the common configuration for all annotation elements.
	 */
	final void configure() {
		// gets chart node
		ChartNode node = getChart().getNode();
		// gets chart area
		ChartAreaNode area = node.getChartArea();
		// checks if chart area is consistent
		if (!area.isConsistent()) {
			// if not, returns
			return;
		}
		// gets scales information
		// gets scale nodes
		ScalesNode scalesNode = node.getScales();
		// gets map with all scale items
		Map<String, ScaleItem> scalesMap = scalesNode.getItems();
		// adds listeners
		addListeners();
		// invokes the specific configuration for the element
		configureElement(area, scalesMap);
	}

	/**
	 * Configures the annotation element with specific configuration based on the different annotation type.
	 * 
	 * @param area chart area item
	 * @param scalesMap map with all defined scales of chart
	 */
	abstract void configureElement(ChartAreaNode area, Map<String, ScaleItem> scalesMap);

	/**
	 * Draws the annotation element on chart.<br>
	 * This is the common drawing for all annotation elements.
	 */
	final void draw() {
		// gets chart node
		ChartNode node = getChart().getNode();
		// gets chart area
		ChartAreaNode area = node.getChartArea();
		// checks if chart area is consistent
		if (!area.isConsistent() || !isConsistent()) {
			// if not, returns
			return;
		}
		// gets canvas context
		Context2dItem ctx = getChart().getCanvas().getContext2d();
		// saves the current context
		// clips the whole chart area
		ctx.save();
		ctx.beginPath();
		ctx.rect(area.getLeft(), area.getTop(), area.getWidth(), area.getHeight());
		ctx.clip();
		// invokes the specific drawing for the element
		drawElement(area, ctx);

		ctx.restore();
	}

	/**
	 * Draws the annotation element with specific drawing based on the different annotation type.
	 * 
	 * @param area chart area item
	 * @param ctx canvas context instance in order to be able to draw the element
	 */
	abstract void drawElement(ChartAreaNode area, Context2dItem ctx);

	/**
	 * Destroys the internal references to objects in order to clean up.
	 */
	final void destroy() {
		// removes all canvas listeners used for callbacks
		removeListeners();
		// invokes the specific destroying for the element
		destroyElement();
	}

	/**
	 * Destroys the annotation element references based on the different annotation type.
	 */
	void destroyElement() {
		// can be override to implement a specific clean up
	}

	/**
	 * Returns <code>true</code> if the event location is overriding the annotation element, otherwise <code>false</code>.<br>
	 * Every annotation element has got own size.
	 * 
	 * @param event event instance
	 * @return <code>true</code> if the event location is overriding the annotation element, otherwise <code>false</code>
	 */
	abstract boolean isInside(BaseNativeEvent event);

	/**
	 * Returns the position on the scale for a {@link String} value.<br>
	 * In case the value is not present on scale, the default position, passed as argument, will be returned.
	 * 
	 * @param value {@link String} value to retrieve from scale
	 * @param scale chart scale instance to use to get the position of value
	 * @param defaultPosition the default position if the value is not present on the scale.
	 * @return the position on the scale for a {@link String} value.
	 */
	final double getValuePositionFromString(String value, ScaleItem scale, double defaultPosition) {
		// checks if value is consistent
		if (value != null) {
			// gets position from value
			double position = scale.getPixelForStringValue(value);
			// checks if position is consistent
			if (!Double.isNaN(position)) {
				// checks to min/max
				return position;
			}
		}
		// if here, the value is not consistent
		// then returns the default position
		return defaultPosition;
	}

	/**
	 * Returns the position on the scale for a {@link Date} value.<br>
	 * In case the value is not present on scale, the default position, passed as argument, will be returned.
	 * 
	 * @param value {@link Date} value to retrieve from scale
	 * @param scale chart scale instance to use to get the position of value
	 * @param defaultPosition the default position if the value is not present on the scale.
	 * @return the position on the scale for a {@link Date} value.
	 */
	final double getValuePositionFromDate(Date value, ScaleItem scale, double defaultPosition) {
		// checks if value is consistent
		if (value != null) {
			// gets position from value
			double position = scale.getPixelForDateValue(value);
			// checks if position is consistent
			if (!Double.isNaN(position)) {
				// checks to min/max
				return position;
			}
		}
		// if here, the value is not consistent
		// then returns the default position
		return defaultPosition;
	}

	/**
	 * Returns the position on the scale for a double value.<br>
	 * In case the value is not present on scale, the default position, passed as argument, will be returned.
	 * 
	 * @param value double value to retrieve from scale
	 * @param scale chart scale instance to use to get the position of value
	 * @param defaultPosition the default position if the value is not present on the scale.
	 * @return the position on the scale for a double value.
	 */
	final double getValuePosition(double value, ScaleItem scale, double defaultPosition) {
		// checks if value is consistent
		if (!Double.isNaN(value)) {
			// gets position from value
			double position = scale.getPixelForValue(value);
			// checks if position is consistent
			if (!Double.isNaN(position)) {
				// checks to min/max
				return position;
			}
		}
		// if here, the value is not consistent
		// then returns the default position
		return defaultPosition;
	}

	/**
	 * Adds {@link BaseEventTypes#CLICK}, {@link BaseEventTypes#MOUSE_MOVE} and {@link BaseEventTypes#MOUSE_LEAVE} events to chart canvas element.
	 */
	private void addListeners() {
		// adds to the element all event listeners
		checkAndAddListener(clickCallback, BaseEventTypes.CLICK, clickCallbackProxy.getProxy());
		checkAndAddListener(enterCallback, BaseEventTypes.MOUSE_MOVE, mouseMoveCallbackProxy.getProxy());
		checkAndAddListener(leaveCallback, BaseEventTypes.MOUSE_LEAVE, mouseLeaveCallbackProxy.getProxy());
	}

	/**
	 * Adds a event listener to the canvas based on the consistency of the related callback.
	 * 
	 * @param callback callback instance to check
	 * @param eventType type of the event in order to add the right listener
	 * @param proxy callback proxy instance which will be invoked to call the callback
	 */
	private void checkAndAddListener(Object callback, String eventType, Proxy proxy) {
		// checks if callback has been set
		if (callback != null) {
			// gets canvas
			Canvas canvas = getChart().getCanvas();
			// adds event listener
			canvas.addEventListener(eventType, proxy);
		}
	}

	/**
	 * Removes {@link BaseEventTypes#CLICK}, {@link BaseEventTypes#MOUSE_MOVE} and {@link BaseEventTypes#MOUSE_LEAVE} events from chart canvas element.
	 */
	private void removeListeners() {
		// gets canvas
		Canvas canvas = getChart().getCanvas();
		// removes to the element all event listeners
		// even if not previously added
		canvas.removeEventListener(BaseEventTypes.CLICK, clickCallbackProxy.getProxy());
		canvas.removeEventListener(BaseEventTypes.MOUSE_MOVE, mouseMoveCallbackProxy.getProxy());
		canvas.removeEventListener(BaseEventTypes.MOUSE_LEAVE, mouseLeaveCallbackProxy.getProxy());
	}

	/**
	 * This method has been invoked by canvas event listener when an {@link BaseEventTypes#CLICK} event is fired.
	 * 
	 * @param event DOM native event generated by canvas element
	 */
	private void onClick(BaseNativeEvent event) {
		// checks if is the callback is consistent and
		// if the event is on the annotation element
		if (clickCallback != null && isInside(event)) {
			// invokes on click event
			clickCallback.onClick(getChart(), event, getConfiguration());
		}
	}

	/**
	 * This method has been invoked by canvas event listener when an {@link BaseEventTypes#MOUSE_MOVE} event is fired.
	 * 
	 * @param event DOM native event generated by canvas element
	 */
	private void onMouseMove(BaseNativeEvent event) {
		// checks if element is already hovered and
		// if is the callback is consistent and
		// if the event is not on the annotation element
		if (isHovered && leaveCallback != null && !isInside(event)) {
			// is leaving, then sets the flag
			isHovered = false;
			// invokes on leave callback
			leaveCallback.onLeave(getChart(), event, getConfiguration());
		} else if (!isHovered && enterCallback != null && isInside(event)) {
			// checks if element is not hovered and
			// if is the callback is consistent and
			// if the event is on the annotation element
			// is entering, then sets the flag
			isHovered = true;
			// invokes on enter callback
			enterCallback.onEnter(getChart(), event, getConfiguration());
		}
	}

	/**
	 * This method has been invoked by canvas event listener when an {@link BaseEventTypes#MOUSE_LEAVE} event is fired.<br>
	 * The event is fired when the mouse is leaving the canvas and NOT the annotation element.<br>
	 * It is anyway managed because we must be sure to clean up the hovering status
	 * 
	 * @param event DOM native event generated by canvas element
	 */
	private void onMouseLeave(BaseNativeEvent event) {
		// checks if element is already hovered and
		// if is the callback is consistent
		if (isHovered && leaveCallback != null) {
			// if here the mouse is leaving the canvas
			// is leaving, then sets the flag
			isHovered = false;
			// invokes on leave callback
			leaveCallback.onLeave(getChart(), event, getConfiguration());
		}
	}

}