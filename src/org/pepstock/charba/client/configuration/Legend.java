/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.configuration;

import java.util.Set;

import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.enums.ElementAlign;
import org.pepstock.charba.client.enums.LegendEventProperty;
import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.enums.TextDirection;
import org.pepstock.charba.client.events.AddHandlerEvent;
import org.pepstock.charba.client.events.ChartEventContext;
import org.pepstock.charba.client.events.LegendClickEvent;
import org.pepstock.charba.client.events.LegendHoverEvent;
import org.pepstock.charba.client.events.LegendLeaveEvent;
import org.pepstock.charba.client.events.RemoveHandlerEvent;
import org.pepstock.charba.client.items.LegendItem;
import org.pepstock.charba.client.options.IsEvent;

import jsinterop.annotations.JsFunction;

/**
 * The chart legend displays data about the datasets that area appearing on the chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class Legend extends ConfigurationOptionsContainer implements IsEventProvider {

	// ---------------------------
	// -- JAVASCRIPT FUNCTIONS ---
	// ---------------------------

	/**
	 * Java script FUNCTION callback called when a event on the legend is raised.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyLegendEventCallback {

		/**
		 * Method of function to be called when a event on the legend is raised.
		 * 
		 * @param event native event
		 * @param item legend item affected by event
		 * @param legend legend instance affected by event
		 */
		void call(NativeObject event, NativeObject item, NativeObject legend);
	}

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the click function
	private final CallbackProxy<ProxyLegendEventCallback> clickCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the hover function
	private final CallbackProxy<ProxyLegendEventCallback> hoverCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the leave function
	private final CallbackProxy<ProxyLegendEventCallback> leaveCallbackProxy = JsHelper.get().newCallbackProxy();
	// sub elements of legend
	private final LegendLabels labels;
	private final LegendTitle title;
	// amount of click handlers
	private int onClickHandlers = 0;
	// amount of hover handlers
	private int onHoverHandlers = 0;
	// amount of leave handlers
	private int onLeaveHandlers = 0;

	/**
	 * Builds the object storing the chart instance and the root options element.
	 * 
	 * @param options root options element.
	 */
	Legend(ConfigurationOptions options) {
		super(options);
		// registers as event handler
		IsEventProvider.register(getChart(), this);
		// creates sub elements
		this.labels = new LegendLabels(getOptions());
		this.title = new LegendTitle(getOptions());
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.clickCallbackProxy.setCallback((event, item, legend) -> getChart().fireEvent(new LegendClickEvent(new ChartEventContext(new ConfigurationEnvelop<>(event)), LegendItem.FACTORY.create(item))));
		// sets function to proxy callback in order to invoke the java interface
		this.hoverCallbackProxy.setCallback((event, item, legend) -> getChart().fireEvent(new LegendHoverEvent(new ChartEventContext(new ConfigurationEnvelop<>(event)), LegendItem.FACTORY.create(item))));
		// sets function to proxy callback in order to invoke the java interface
		this.leaveCallbackProxy.setCallback((event, item, legend) -> getChart().fireEvent(new LegendLeaveEvent(new ChartEventContext(new ConfigurationEnvelop<>(event)), LegendItem.FACTORY.create(item))));
	}

	/**
	 * Returns the legend labels element.
	 * 
	 * @return the labels
	 */
	public LegendLabels getLabels() {
		return labels;
	}

	/**
	 * Returns the legend title element.
	 * 
	 * @return the title
	 */
	public LegendTitle getTitle() {
		return title;
	}

	/**
	 * Sets the browser events that the legend should listen to.
	 * 
	 * @param events the browser events that the legend should listen to.
	 */
	public void setEvents(IsEvent... events) {
		getConfiguration().getLegend().setEvents(events);
	}

	/**
	 * Sets the browser events that the legend should listen to.
	 * 
	 * @param events the browser events that the legend should listen to.
	 */
	public void setEvents(Set<IsEvent> events) {
		getConfiguration().getLegend().setEvents(events);
	}

	/**
	 * Returns the browser events that the legend should listen to.
	 * 
	 * @return the browser events that the legend should listen to.
	 */
	public Set<IsEvent> getEvents() {
		return getConfiguration().getLegend().getEvents();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.events.AddHandlerEventHandler#onAdd(org.pepstock.charba.client.events.AddHandlerEvent)
	 */
	@Override
	public final void onAdd(AddHandlerEvent event) {
		// checks if type of added event handler is data set selection or click
		if (event.isRecognize(LegendClickEvent.TYPE)) {
			// if java script property is missing
			if (onClickHandlers == 0) {
				// adds the java script function to catch the event
				getConfiguration().setEvent(getConfiguration().getLegend(), LegendEventProperty.ON_CLICK, new ConfigurationEnvelop<>(clickCallbackProxy.getProxy()));
			}
			// increments amount of handlers
			onClickHandlers++;
		} else if (event.isRecognize(LegendHoverEvent.TYPE)) {
			// if java script property is missing
			if (onHoverHandlers == 0) {
				// adds the java script function to catch the event
				getConfiguration().setEvent(getConfiguration().getLegend(), LegendEventProperty.ON_HOVER, new ConfigurationEnvelop<>(hoverCallbackProxy.getProxy()));
			}
			// increments amount of handlers
			onHoverHandlers++;
		} else if (event.isRecognize(LegendLeaveEvent.TYPE)) {
			// if java script property is missing
			if (onLeaveHandlers == 0) {
				// adds the java script function to catch the event
				getConfiguration().setEvent(getConfiguration().getLegend(), LegendEventProperty.ON_LEAVE, new ConfigurationEnvelop<>(leaveCallbackProxy.getProxy()));
			}
			// increments amount of handlers
			onLeaveHandlers++;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.events.RemoveHandlerEventHandler#onRemove(org.pepstock.charba.client.events. RemoveHandlerEvent)
	 */
	@Override
	public final void onRemove(RemoveHandlerEvent event) {
		// checks if type of removed event handler is data set selection or click
		if (event.isRecognize(LegendClickEvent.TYPE)) {
			// decrements the amount of handlers
			onClickHandlers--;
			// if there is not any handler
			if (onClickHandlers == 0) {
				// removes the java script object
				getConfiguration().setEvent(getConfiguration().getLegend(), LegendEventProperty.ON_CLICK, ConfigurationOptions.RESET_CALLBACK_ENVELOP);
			}
		} else if (event.isRecognize(LegendHoverEvent.TYPE)) {
			// decrements the amount of handlers
			onHoverHandlers--;
			// if there is not any handler
			if (onHoverHandlers == 0) {
				// removes the java script object
				getConfiguration().setEvent(getConfiguration().getLegend(), LegendEventProperty.ON_HOVER, ConfigurationOptions.RESET_CALLBACK_ENVELOP);
			}
		} else if (event.isRecognize(LegendLeaveEvent.TYPE)) {
			// decrements the amount of handlers
			onLeaveHandlers--;
			// if there is not any handler
			if (onLeaveHandlers == 0) {
				// removes the java script object
				getConfiguration().setEvent(getConfiguration().getLegend(), LegendEventProperty.ON_LEAVE, ConfigurationOptions.RESET_CALLBACK_ENVELOP);
			}
		}
	}

	/**
	 * Returns <code>true</code> if there is any legend click handler, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if there is any legend click handler, otherwise <code>false</code>.
	 */
	public final boolean hasClickHandlers() {
		return onClickHandlers > 0;
	}

	/**
	 * Returns <code>true</code> if there is any legend hover handler, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if there is any legend hover handler, otherwise <code>false</code>.
	 */
	public final boolean hasHoverHandlers() {
		return onHoverHandlers > 0;
	}

	/**
	 * Returns <code>true</code> if there is any legend leave handler, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if there is any legend leave handler, otherwise <code>false</code>.
	 */
	public final boolean hasLeaveHandlers() {
		return onLeaveHandlers > 0;
	}

	/**
	 * Sets if the legend is shown.
	 * 
	 * @param display if the legend is shown.
	 */
	public void setDisplay(boolean display) {
		getConfiguration().getLegend().setDisplay(display);
	}

	/**
	 * Returns if the legend is shown.
	 * 
	 * @return if the legend is shown.
	 */
	public boolean isDisplay() {
		return getConfiguration().getLegend().isDisplay();
	}

	/**
	 * Marks that this box should take the full width/height of the canvas (moving other boxes).
	 * 
	 * @param fullSize Marks that this box should take the full width/height of the canvas (moving other boxes)
	 */
	public void setFullSize(boolean fullSize) {
		getConfiguration().getLegend().setFullSize(fullSize);
	}

	/**
	 * Returns if marks that this box should take the full width/height of the canvas (moving other boxes).
	 * 
	 * @return Marks that this box should take the full width/height of the canvas (moving other boxes)
	 */
	public boolean isFullSize() {
		return getConfiguration().getLegend().isFullSize();
	}

	/**
	 * Sets the legend will show datasets in reverse order.
	 * 
	 * @param reverse legend will show datasets in reverse order.
	 */
	public void setReverse(boolean reverse) {
		getConfiguration().getLegend().setReverse(reverse);
	}

	/**
	 * Returns if the legend will show datasets in reverse order.
	 * 
	 * @return Legend will show datasets in reverse order.
	 */
	public boolean isReverse() {
		return getConfiguration().getLegend().isReverse();
	}

	/**
	 * Sets the position of the legend.
	 * 
	 * @param position Position of the legend.
	 */
	public void setPosition(Position position) {
		getConfiguration().getLegend().setPosition(position);
	}

	/**
	 * Returns the position of the legend.
	 * 
	 * @return Position of the legend.
	 */
	public Position getPosition() {
		return getConfiguration().getLegend().getPosition();
	}

	/**
	 * Sets the alignment of the legend.
	 * 
	 * @param alignment alignment of the legend.
	 */
	public void setAlign(ElementAlign alignment) {
		getConfiguration().getLegend().setAlign(alignment);
	}

	/**
	 * Returns the alignment of the legend.
	 * 
	 * @return alignment of the legend.
	 */
	public ElementAlign getAlign() {
		return getConfiguration().getLegend().getAlign();
	}

	/**
	 * Sets <code>true</code> for rendering the legends from right to left.
	 * 
	 * @param rtl <code>true</code> for rendering the legends from right to left
	 */
	public void setRtl(boolean rtl) {
		getConfiguration().getLegend().setRtl(rtl);
	}

	/**
	 * Returns <code>true</code> for rendering the legends from right to left.
	 * 
	 * @return <code>true</code> for rendering the legends from right to left.
	 */
	public boolean isRtl() {
		return getConfiguration().getLegend().isRtl();
	}

	/**
	 * Sets the text direction of the legend that will force the text direction on the canvas for rendering the legend, regardless of the CSS specified on the canvas.
	 * 
	 * @param textDirection the text direction of the legend.
	 */
	public void setTextDirection(TextDirection textDirection) {
		getConfiguration().getLegend().setTextDirection(textDirection);
	}

	/**
	 * Returns the text direction of the legend that will force the text direction on the canvas for rendering the legend, regardless of the CSS specified on the canvas.
	 * 
	 * @return the text direction of the legend.
	 */
	public TextDirection getTextDirection() {
		return getConfiguration().getLegend().getTextDirection();
	}

	/**
	 * Sets the maximum width of the legend, in pixels.
	 * 
	 * @param maxWidth the maximum width of the legend, in pixels
	 */
	public void setMaxWidth(int maxWidth) {
		getConfiguration().getLegend().setMaxWidth(maxWidth);
	}

	/**
	 * Returns the maximum width of the legend, in pixels.
	 * 
	 * @return the maximum width of the legend, in pixels
	 */
	public int getMaxWidth() {
		return getConfiguration().getLegend().getMaxWidth();
	}

	/**
	 * Sets the maximum height of the legend, in pixels.
	 * 
	 * @param maxHeight the maximum height of the legend, in pixels
	 */
	public void setMaxHeight(int maxHeight) {
		getConfiguration().getLegend().setMaxHeight(maxHeight);
	}

	/**
	 * Returns the maximum width of the legend, in pixels.
	 * 
	 * @return the maximum width of the legend, in pixels
	 */
	public int getMaxHeight() {
		return getConfiguration().getLegend().getMaxWidth();
	}
}