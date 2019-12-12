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

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.pepstock.charba.client.Chart;
import org.pepstock.charba.client.Charts;
import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.callbacks.CallbackFunctionContext;
import org.pepstock.charba.client.colors.tiles.TilesFactory;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.configuration.Legend;
import org.pepstock.charba.client.enums.DefaultPlugin;
import org.pepstock.charba.client.enums.Event;
import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.events.ChartNativeEvent;
import org.pepstock.charba.client.events.LegendClickEvent;
import org.pepstock.charba.client.events.LegendHoverEvent;
import org.pepstock.charba.client.events.LegendLeaveEvent;
import org.pepstock.charba.client.items.LegendItem;
import org.pepstock.charba.client.items.LegendLabelItem;
import org.pepstock.charba.client.plugins.AbstractPlugin;

import com.google.gwt.dom.client.CanvasElement;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.dom.client.TableCellElement;
import com.google.gwt.safehtml.shared.SafeHtml;

import jsinterop.annotations.JsFunction;

/**
 * This plugin implements a HTML legend in order to give more flexibility to who needs to customize the legend.<br>
 * It uses the {@link HtmlLegendLabelsCallback} to generated HTML legend.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class HtmlLegend extends AbstractPlugin {

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
	 * Plugin ID <b>{@value ID}</b>.
	 */
	public static final String ID = "charbahtmllegend";
	/**
	 * The factory to create options for plugin.
	 */
	public static final HtmlLegendOptionsFactory FACTORY = new HtmlLegendOptionsFactory(ID);
	// suffix label for main HTML legend element id
	private static final String SUFFIX_LEGEND_ELEMENT_ID = "_legend";
	// cache to store options in order do not load every time the options
	static final Map<String, HtmlLegendOptions> OPTIONS = new HashMap<>();
	// cache to store legend items managed by chart
	static final Map<String, List<LegendLabelItem>> LEGEND_LABELS = new HashMap<>();
	// cache to store DIV element which contains legend for each chart
	private static final Map<String, DivElement> DIV_ELEMENTS = new HashMap<>();
	// cache to store easing during drawing for each chart
	// this cache is needed in order to recreate the legend when a chart update
	// is invoked during a previous update
	private static final Map<String, Double> EASINGS = new HashMap<>();
	// cache to store the original value of legend in order to
	// manage the change of the legend display after chart creation
	private static final Map<String, Boolean> LEGEND_DISPLAY = new HashMap<>();
	// cache to store the chart id in order to know when new legend must be created
	private static final Set<String> ADDED_LEGEND = new HashSet<>();
	// static callback to generate legend into HTML
	private static final HtmlLegendLabelsCallback CALLBACK = new HtmlLegendLabelsCallback();

	/**
	 * Creates a plugin instance.
	 */
	public HtmlLegend() {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#getId()
	 */
	@Override
	public String getId() {
		return ID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.plugins.AbstractPlugin#onConfigure(org.pepstock.charba.client. AbstractChart)
	 */
	@Override
	public void onConfigure(IsChart chart) {
		// checks if argument is consistent
		if (IsChart.isConsistent(chart)) {
			HtmlLegendOptions pOptions = null;
			// if not, loads and cache
			// creates the plugin options using the java script object
			// passing also the default color set at constructor.
			if (chart.getOptions().getPlugins().hasOptions(ID)) {
				pOptions = chart.getOptions().getPlugins().getOptions(ID, FACTORY);
			} else {
				pOptions = new HtmlLegendOptions();
			}
			OPTIONS.put(chart.getId(), pOptions);
			pOptions.setCurrentCursor(chart.getInitialCursor());
			// checks if the plugin is configured to show legend
			if (pOptions.isDisplay()) {
				// if the legend is set do not display
				// or the OOTB legend plugin has been disable
				// it respects it then ignore it and the plugin in
				// will be disable
				manageLegendDisplay(chart, pOptions);
			} else {
				// resets status of plugin
				// because display is false
				resetStatus(chart);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.plugins.AbstractPlugin#onBeforeDraw(org.pepstock.charba.client.IsChart, double)
	 */
	@Override
	public boolean onBeforeDraw(IsChart chart, double easing) {
		// checks if argument is consistent
		if (mustBeDisplay(chart)) {
			// gets the legend
			Legend legend = chart.getOptions().getLegend();
			// creates legend DIV element reference
			DivElement legendElement = null;
			// checks if element is alreayd created
			if (!DIV_ELEMENTS.containsKey(chart.getId())) {
				// if new
				// creates a DIV element
				legendElement = Document.get().createDivElement();
				// sets the id by chart instance
				legendElement.setId(formatLegendElementId(chart));
				// stores into map
				DIV_ELEMENTS.put(chart.getId(), legendElement);
			} else {
				// if here, DIV element already exists then it retrieves it
				legendElement = DIV_ELEMENTS.get(chart.getId());
			}
			// checks if there is a parent
			if (!legendElement.hasParentElement()) {
				// if no parent, means new object to add to chart element
				addLegendElement(chart.getElement(), legendElement, legend.getPosition(), legend.getLabels().getPadding());
			} else {
				// removes the item
				// in order to after draw to create the legend
				ADDED_LEGEND.remove(chart.getId());
				// if here, the div has got the parent
				// then it checks if the position is the same when it has been created
				// otherwise it will move to the right position
				manageLegendElement(chart, legendElement, legend.getPosition());
			}
			// checks if is full width has been set
			if (legend.isFullWidth()) {
				legendElement.getStyle().setWidth(100, Unit.PCT);
			}
			// checks if reloading during previous drawing
			// if the previous stored easing is greater than the current one her
			// means that the is chart is updating without waiting for ending
			// the previous update
			if (EASINGS.put(chart.getId(), easing) > easing) {
				// removes the item
				// in order to after draw to create the legend
				ADDED_LEGEND.remove(chart.getId());
			}
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.plugins.AbstractPlugin#onAfterDraw(org.pepstock.charba.client. AbstractChart, double)
	 */
	@Override
	public void onAfterDraw(IsChart chart, double easing) {
		// checks if argument is consistent
		if (mustBeDisplay(chart)) {
			// checks if the legend must be created
			// the legend will be created if there is the legend element
			// and the chart is is NOT in the set
			if (DIV_ELEMENTS.containsKey(chart.getId()) && !ADDED_LEGEND.contains(chart.getId())) {
				// gets div element
				DivElement legendElement = DIV_ELEMENTS.get(chart.getId());
				// removes all listeners
				removeListeners(chart, legendElement);
				// removes all children of div element
				legendElement.removeAllChildren();
				// invokes the legend callback to have the HTML of legend
				SafeHtml html = CALLBACK.generateLegend(chart);
				// sets as inner HTML
				legendElement.setInnerHTML(html.asString());
				// adds the event listeners to element
				addListeners(chart, legendElement);
				// adds into set
				// in order do not add the inner html every easing
				ADDED_LEGEND.add(chart.getId());
			}
			// if end of drawing,
			// removes charts from set
			if (easing == 1D) {
				// removes chart for items
				// in order to add next cycle
				ADDED_LEGEND.remove(chart.getId());
				// sets easing to zero
				EASINGS.put(chart.getId(), 0D);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.plugins.AbstractPlugin#onDestroy(org.pepstock.charba.client.IsChart)
	 */
	@Override
	public void onDestroy(IsChart chart) {
		// checks if argument is consistent
		if (IsChart.isValid(chart)) {
			// resets all status items
			resetStatus(chart);
			// removes status of legend display
			LEGEND_DISPLAY.remove(chart.getId());
			// removes the chart from options
			HtmlLegendOptions oldOptions = OPTIONS.remove(chart.getId());
			// scans all options to see if the options is used in another chart
			for (HtmlLegendOptions options : OPTIONS.values()) {
				// checks if the option si s equals to old one
				if (options.getCharbaId() == oldOptions.getCharbaId()) {
					return;
				}
			}
			// if here, the old options is no longer used
			// then it removes the legend callback from cache
			FACTORY.store(oldOptions.getCharbaId(), null);
		}
	}

	/**
	 * Manages if the legend must be displayed or not based on choice of user and what can be changed into chart configuration
	 * after the chart initialization bu a <code>chart.reconfigure</code>.
	 * 
	 * @param chart chart instance to manage
	 * @param pOptions plugin option for the chart
	 */
	private void manageLegendDisplay(IsChart chart, HtmlLegendOptions pOptions) {
		// if the legend is set do not display
		// or the OOTB legend plugin has been disable
		// it respects it then ignore it and the plugin in
		// will be disable
		boolean cachedValue = false;
		// check if the display must be stored because was changed by user
		if (LEGEND_DISPLAY.containsKey(chart.getId())) {
			// if here the plugin was already initialized
			// and then it stored the display value
			cachedValue = LEGEND_DISPLAY.get(chart.getId());
			// because is not the first round
			// the legend value should be false because set by plugin
			// if is true, means the user changed it programmatically
			if (chart.getOptions().getLegend().isDisplay() && !cachedValue) {
				// stored the legend display value because is changed
				LEGEND_DISPLAY.put(chart.getId(), chart.getOptions().getLegend().isDisplay());
			}
		} else {
			// stored the legend display value because is missing
			LEGEND_DISPLAY.put(chart.getId(), chart.getOptions().getLegend().isDisplay());
		}
		boolean mustBeChecked = chart.getOptions().getLegend().isDisplay() || cachedValue;
		if (mustBeChecked && !chart.getOptions().getPlugins().isForcedlyDisabled(DefaultPlugin.LEGEND)) {
			// disable legend
			chart.getOptions().getLegend().setDisplay(false);
			// sets legend callback
			// overriding whatever other callback has been set
			chart.getOptions().setLegendCallback(CALLBACK);
			// sets easing to zero
			EASINGS.put(chart.getId(), 0D);
			// creates options instance
		} else {
			// resets all status items if there are
			// this is in case of update options
			resetStatus(chart);
			// disables display of plugin
			pOptions.setDisplay(false);
		}
	}

	/**
	 * Returns <code>true</code> if the plugin must be manage the legend, creating it.
	 * 
	 * @param chart chart instance.
	 * @return <code>true</code> if the plugin must be manage the legend, creating it.
	 */
	private boolean mustBeDisplay(IsChart chart) {
		// checks if argument is consistent
		if (IsChart.isValid(chart) && OPTIONS.containsKey(chart.getId())) {
			// gets stored option
			HtmlLegendOptions options = OPTIONS.get(chart.getId());
			// returns if must be display
			return options.isDisplay();
		}
		// if here, chart is not consistent or no option
		// then returns false
		return false;
	}

	/**
	 * Removes all status items from all maps.
	 * 
	 * @param chart chart instance
	 */
	private void resetStatus(IsChart chart) {
		// checks if there is div element for legend
		if (DIV_ELEMENTS.containsKey(chart.getId())) {
			// removes from map
			DivElement legendElement = DIV_ELEMENTS.remove(chart.getId());
			// removes all listeners
			removeListeners(chart, legendElement);
			// removes all children
			legendElement.removeAllChildren();
			// removes from parent
			legendElement.removeFromParent();
		}
		// removes the chart status
		ADDED_LEGEND.remove(chart.getId());
		// removes the chart legend labels items
		LEGEND_LABELS.remove(chart.getId());
		// removes the chart from easing status
		EASINGS.remove(chart.getId());
		// removes cached point style from tile factory
		// if there are
		HtmlLegendItem htmlLegendItem = new HtmlLegendItem(chart);
		TilesFactory.clearHtmlLegendItems(htmlLegendItem);
	}

	/**
	 * Creates a HTML element id for chart legend.<br>
	 * The format is:<br>
	 * <code>[chartId]-legend</code> <br>
	 * 
	 * @param chart chart instance
	 * @return a string representation of HTML element id.
	 */
	private String formatLegendElementId(IsChart chart) {
		return chart.getId() + SUFFIX_LEGEND_ELEMENT_ID;
	}

	/**
	 * Adds the event listeners to all elements created by legend callback.
	 * 
	 * @param chart chart instance
	 * @param legendElement DIV legend element which contains the custom HTML legend.
	 */
	private void addListeners(IsChart chart, DivElement legendElement) {
		// gets all nodes with TAG TD
		NodeList<Element> tds = legendElement.getElementsByTagName(TableCellElement.TAG_TD);
		// scans all nodes
		for (int i = 0; i < tds.getLength(); i++) {
			// gets node
			Node node = tds.getItem(i);
			// checks if its an element
			if (node instanceof Element) {
				// casts to element
				Element td = (Element) node;
				// checks if the element has got a correct ID
				// starting with chart id
				if (td.getId().startsWith(chart.getId())) {
					// adds to the element all event listeners
					JsHtmlLegendBuilderHelper.get().addEventListener(Event.CLICK, td, clickCallbackProxy.getProxy());
					JsHtmlLegendBuilderHelper.get().addEventListener(Event.MOUSEMOVE, td, hoverCallbackProxy.getProxy());
					JsHtmlLegendBuilderHelper.get().addEventListener(Event.MOUSEOUT, td, leaveCallbackProxy.getProxy());
				}
			}
		}
	}

	/**
	 * Removes the event listeners from all elements created by legend callback.
	 * 
	 * @param chart chart instance
	 * @param legendElement DIV legend element which contains the custom HTML legend.
	 */
	private void removeListeners(IsChart chart, DivElement legendElement) {
		// gets all nodes with TAG TD
		NodeList<Element> tds = legendElement.getElementsByTagName(TableCellElement.TAG_TD);
		// scans all nodes
		for (int i = 0; i < tds.getLength(); i++) {
			// gets node
			Node node = tds.getItem(i);
			// checks if its an element
			if (node instanceof Element) {
				// casts to element
				Element td = (Element) node;
				// checks if the element has got a correct ID
				// starting with chart id
				if (td.getId().startsWith(chart.getId())) {
					// removes to the element all event listeners
					JsHtmlLegendBuilderHelper.get().removeEventListener(Event.CLICK, td, clickCallbackProxy.getProxy());
					JsHtmlLegendBuilderHelper.get().removeEventListener(Event.MOUSEMOVE, td, hoverCallbackProxy.getProxy());
					JsHtmlLegendBuilderHelper.get().removeEventListener(Event.MOUSEOUT, td, leaveCallbackProxy.getProxy());
				}
			}
		}
	}

	/**
	 * Adds the HTML legend element to the right position into chart element, depending on {@link Position} set for legend.
	 * 
	 * @param chartElement chart HTML element
	 * @param legendElement legend HTML element
	 * @param position position set by legend configuration object
	 * @param padding padding set by legend configuration object
	 */
	private void addLegendElement(Element chartElement, DivElement legendElement, Position position, int padding) {
		if (mustAddToBottom(position)) {
			// appends the legend element
			chartElement.appendChild(legendElement);
			// and sets the bottom padding
			legendElement.getStyle().setPaddingBottom(padding, Unit.PX);
		} else {
			// if not bottom, inserts the legend element as first
			chartElement.insertFirst(legendElement);
			// and sets the top padding
			legendElement.getStyle().setPaddingTop(padding, Unit.PX);
		}
	}

	/**
	 * Manages the HTML legend element to the right position into chart element, depending on {@link Position} set for
	 * legend.<br>
	 * This method is called when a legend element is already added and the position could be changed comparing when the element
	 * has been created.
	 * 
	 * @param chart chart instance
	 * @param legendElement legend HTML element
	 * @param position position set by legend configuration object
	 */
	private void manageLegendElement(IsChart chart, DivElement legendElement, Position position) {
		// gets chart element
		Element chartElement = chart.getElement();
		// gets if the legend element has been defined after the canvas
		boolean isAfterCanvas = isAfterCanvas(chart, legendElement);
		// gets if the legend must be added to bottom
		boolean mustBeAddedToBottom = mustAddToBottom(position);
		if (mustBeAddedToBottom && !isAfterCanvas) {
			// removes the legend element from parent
			legendElement.removeFromParent();
			// and appends at the end
			chartElement.appendChild(legendElement);
		} else if (!mustBeAddedToBottom && isAfterCanvas) {
			// if here the position is not bottom but
			// legend element is after the canvas
			// then it removes from parent
			legendElement.removeFromParent();
			// and inserts it at the beginning
			chartElement.insertFirst(legendElement);
		}
	}

	/**
	 * Checks if the legend must be added into chart element on top or bottom.
	 * 
	 * @param position position set by legend configuration object
	 * @return <code>true</code> if the legend must be added to bottom
	 */
	private boolean mustAddToBottom(Position position) {
		// if position is bottom or right
		// legend is to bottom
		return Position.RIGHT.equals(position) || Position.BOTTOM.equals(position);
	}

	/**
	 * Returns <code>true</code> if the legend element has been added to chart element after the canvas one.
	 * 
	 * @param chart chart instance
	 * @param legendElement legend HTML element
	 * @return <code>true</code> if the legend element has been added to chart element after the canvas one
	 */
	private boolean isAfterCanvas(IsChart chart, DivElement legendElement) {
		// gets chart element
		Element chartElement = chart.getElement();
		// retrieves canvas id of chart
		String canvasId = chart.getCanvas().getCanvasElement().getId();
		// scans all children of chart element
		for (int i = 0; i < chartElement.getChildCount(); i++) {
			// gets the node
			Node child = chartElement.getChild(i);
			// checks if the node is an element
			if (child instanceof Element) {
				// casts to element
				Element childElement = (Element) child;
				// checks if the legend element is queals to the scanned node
				// by its id
				if (childElement.getNodeName().equalsIgnoreCase(DivElement.TAG) && legendElement.getId().equalsIgnoreCase(childElement.getId())) {
					// if here, means that the legend element has been found before the canvas element
					// then returns false
					return false;
				} else if (childElement.getNodeName().equalsIgnoreCase(CanvasElement.TAG) && childElement.getId().equalsIgnoreCase(canvasId)) {
					// if here, means that the canvas element has been found before the legend element
					// then returns true
					return true;
				}
			}
		}
		// if here, nothing was found then return false
		// it should not happen
		return false;
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
				List<LegendLabelItem> legendItems = LEGEND_LABELS.get(chartId);
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
		ADDED_LEGEND.remove(chart.getId());
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
		if (DIV_ELEMENTS.containsKey(chart.getId())) {
			// gets legend element
			DivElement legendElement = DIV_ELEMENTS.get(chart.getId());
			// get options
			HtmlLegendOptions options = OPTIONS.get(chart.getId());
			// sets cursor
			legendElement.getStyle().setCursor(setPointer ? options.getCursorPointer() : options.getCurrentCursor());
		}
	}

}