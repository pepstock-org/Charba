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
package org.pepstock.charba.client.impl.plugins;

import java.util.List;

import org.pepstock.charba.client.Chart;
import org.pepstock.charba.client.Charts;
import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.callbacks.CallbackFunctionContext;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.enums.Event;
import org.pepstock.charba.client.events.ChartNativeEvent;
import org.pepstock.charba.client.events.LegendClickEvent;
import org.pepstock.charba.client.events.LegendHoverEvent;
import org.pepstock.charba.client.events.LegendLeaveEvent;
import org.pepstock.charba.client.items.LegendItem;
import org.pepstock.charba.client.items.LegendLabelItem;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.dom.client.TableCellElement;

import jsinterop.annotations.JsFunction;

/**
 * Manages events from {@link HtmlLegend}, enabling and disabling event listeners on HTML elements created by plugin.<br>
 * This object is able to manage all events on CHART.JS legend also into HTML legend one.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class HtmlLegendCallbackProxy {

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
		 * @param context context value of <code>this</code> to the execution context of function.
		 * @param event native event
		 */
		void call(CallbackFunctionContext context, ChartNativeEvent event);
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

	/**
	 * Creates the object configuring the callbacks for the events.
	 */
	HtmlLegendCallbackProxy() {
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// fires the event
		clickCallbackProxy.setCallback((context, event) -> handleEvent(event));
		// fires the event
		hoverCallbackProxy.setCallback((context, event) -> handleEvent(event));
		// fires the event
		leaveCallbackProxy.setCallback((context, event) -> handleEvent(event));
	}

	/**
	 * Adds {@link Event#CLICK}, {@link Event#MOUSEMOVE} and {@link Event#MOUSEOUT} events to HTML legend element.
	 * 
	 * @param element HTML legend element on which to add events listeners
	 */
	void addListeners(Element element) {
		// adds to the element all event listeners
		JsHtmlLegendBuilderHelper.get().addEventListener(Event.CLICK, element, clickCallbackProxy.getProxy());
		JsHtmlLegendBuilderHelper.get().addEventListener(Event.MOUSEMOVE, element, hoverCallbackProxy.getProxy());
		JsHtmlLegendBuilderHelper.get().addEventListener(Event.MOUSEOUT, element, leaveCallbackProxy.getProxy());
	}

	/**
	 * Removes {@link Event#CLICK}, {@link Event#MOUSEMOVE} and {@link Event#MOUSEOUT} events from HTML legend element.
	 * 
	 * @param element HTML legend element on which to remove events listeners
	 */
	void removeListeners(Element element) {
		// removes to the element all event listeners
		JsHtmlLegendBuilderHelper.get().removeEventListener(Event.CLICK, element, clickCallbackProxy.getProxy());
		JsHtmlLegendBuilderHelper.get().removeEventListener(Event.MOUSEMOVE, element, hoverCallbackProxy.getProxy());
		JsHtmlLegendBuilderHelper.get().removeEventListener(Event.MOUSEOUT, element, leaveCallbackProxy.getProxy());
	}

	/**
	 * This method has been invoked by legend elements when an event fires by them.
	 * 
	 * @param event DOM native event generated by legend elements
	 */
	private void handleEvent(ChartNativeEvent event) {
		// gets event target
		EventTarget eventTarget = event.getEventTarget();
		// gets by element
		Element element = Element.as(eventTarget);
		// gets reference of table column
		Element legendColumnElement = null;
		// checks if the element is TD
		if (element.getNodeName().equalsIgnoreCase(TableCellElement.TAG_TD)) {
			// if TD, the element itself contains the correct ID.
			legendColumnElement = element;
		} else {
			// if not TD but the parent has got TD, the parent element itself contains the correct ID.
			legendColumnElement = checkParent(element);
		}
		// checks if legend column is consistent
		if (legendColumnElement != null) {
			// creates a legend id by element
			HtmlLegendId legendId = HtmlLegendId.get(legendColumnElement);
			// checks if id is consistent
			// and if there is a chart with that id
			if (legendId != null && Charts.hasNative(legendId.getChartId())) {
				// gets the chart id
				String chartId = legendId.getChartId();
				// retrieves the chart instance
				IsChart chart = Charts.get(chartId);
				// creates a reference with a list of legend labels
				List<LegendLabelItem> legendItems = HtmlLegend.get().getPluginLegendLabelsItems().get(chartId);
				// by HTML legend ID object, extract the legend item
				// to pass to event
				LegendItem selectedItem = legendId.lookForLegendItem(legendItems);
				// fires the event
				fireEvent(chart, selectedItem, event);
			}
		}
	}

	/**
	 * Scans recursively the element to get a parent DOM element which has got TD as tag name.
	 * 
	 * @param child child element to check
	 * @return the parent element with TD tag name or <code>null</code> if not found.
	 */
	private Element checkParent(Element child) {
		// checks if has got a parent
		if (child.hasParentElement()) {
			// checks if parent has got the TD element
			if (child.getParentElement().getNodeName().equalsIgnoreCase(TableCellElement.TAG_TD)) {
				// returns parent element
				return child.getParentElement();
			} else {
				// calls this method recursively
				// scanning the parent
				return checkParent(child.getParentElement());
			}
		}
		// if here if scanning the elements tree
		// the TD element is not found
		return null;
	}

	/**
	 * Fires the event on specific legend item, selected by native event.
	 * 
	 * @param chart chart instance
	 * @param selectedItem legend item instance, selected by native event
	 * @param event DOM native event, got from legend element
	 */
	private void fireEvent(IsChart chart, LegendItem selectedItem, ChartNativeEvent event) {
		// checks if legend item is consistent
		// and there is native chart stored into cache (must be)
		if (selectedItem != null && Charts.hasNative(chart)) {
			// checks if is a click event
			if (Event.CLICK.value().equalsIgnoreCase(event.getType())) {
				// invokes on click event
				onClick(chart, selectedItem, event);
			} else if (Event.MOUSEMOVE.value().equalsIgnoreCase(event.getType())) {
				// invokes on hover event
				onHover(chart, selectedItem, event);
			} else if (Event.MOUSEOUT.value().equalsIgnoreCase(event.getType())) {
				// invokes on leave event
				onLeave(chart, selectedItem, event);
			}
		}
	}

	/**
	 * Fires the CLICK event on specific legend item.
	 * 
	 * @param chart chart instance
	 * @param selectedItem legend item instance, selected by native event
	 * @param event DOM native event, got from legend element
	 */
	private void onClick(IsChart chart, LegendItem selectedItem, ChartNativeEvent event) {
		// retrieves native chart, needed to create the event
		Chart nativeChart = Charts.getNative(chart);
		// removes the chart id because
		// usually the click set hidden which needs an update of chart
		// and removing here the legend will be created to next update
		HtmlLegend.get().getPluginAddedLegendStatus().remove(chart.getId());
		// creates the event
		LegendClickEvent eventToFire = new LegendClickEvent(event, nativeChart, selectedItem);
		// checks if there is any event handler
		if (chart.getOptions().getLegend().hasClickHandlers()) {
			// if yes, fires the event by chart
			chart.fireEvent(eventToFire);
		} else {
			// if here, no handler then invokes the default
			Defaults.get().invokeLegendOnClick(eventToFire);
		}
	}

	/**
	 * Fires the HOVER event on specific legend item.
	 * 
	 * @param chart chart instance
	 * @param selectedItem legend item instance, selected by native event
	 * @param event DOM native event, got from legend element
	 */
	private void onHover(IsChart chart, LegendItem selectedItem, ChartNativeEvent event) {
		// retrieves native chart, needed to create the event
		Chart nativeChart = Charts.getNative(chart);
		// set cursor
		setCursorOnLegend(chart, true);
		// creates the event
		LegendHoverEvent eventToFire = new LegendHoverEvent(event, nativeChart, selectedItem);
		// checks if there is any event handler
		if (chart.getOptions().getLegend().hasHoverHandlers()) {
			// if yes, fires the event by chart
			chart.fireEvent(eventToFire);
		} else {
			// if here, no handler then invokes the default
			Defaults.get().invokeLegendOnHover(eventToFire);
		}
	}

	/**
	 * Fires the LEAVE event on specific legend item.
	 * 
	 * @param chart chart instance
	 * @param selectedItem legend item instance, selected by native event
	 * @param event DOM native event, got from legend element
	 */
	private void onLeave(IsChart chart, LegendItem selectedItem, ChartNativeEvent event) {
		// retrieves native chart, needed to create the event
		Chart nativeChart = Charts.getNative(chart);
		// set cursor
		setCursorOnLegend(chart, false);
		// creates the event
		LegendLeaveEvent eventToFire = new LegendLeaveEvent(event, nativeChart, selectedItem);
		// checks if there is any event handler
		if (chart.getOptions().getLegend().hasLeaveHandlers()) {
			// if yes, fires the event by chart
			chart.fireEvent(eventToFire);
		} else {
			// if here, no handler then invokes the default
			Defaults.get().invokeLegendOnLeave(eventToFire);
		}
	}

	/**
	 * Sets the cursor point when the cursor is mouse over the legend and the default one when is mouse out.
	 * 
	 * @param chart chart instance
	 * @param setPointer if <code>true</code>, sets the cursor pointer, otherwise the default one
	 */
	private void setCursorOnLegend(IsChart chart, boolean setPointer) {
		// checks if there is legend element
		if (HtmlLegend.get().getPluginDivElements().containsKey(chart.getId())) {
			// gets legend element
			DivElement legendElement = HtmlLegend.get().getPluginDivElements().get(chart.getId());
			// get options
			HtmlLegendOptions options = HtmlLegend.get().getPluginOptions().get(chart.getId());
			// sets cursor
			legendElement.getStyle().setCursor(setPointer ? options.getCursorPointer() : options.getCurrentCursor());
		}
	}

}
