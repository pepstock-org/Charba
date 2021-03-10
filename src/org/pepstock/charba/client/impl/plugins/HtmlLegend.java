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

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.colors.tiles.TilesFactory;
import org.pepstock.charba.client.configuration.Legend;
import org.pepstock.charba.client.defaults.IsDefaultScaledOptions;
import org.pepstock.charba.client.dom.BaseElement;
import org.pepstock.charba.client.dom.BaseHtmlElement;
import org.pepstock.charba.client.dom.BaseNode;
import org.pepstock.charba.client.dom.DOMBuilder;
import org.pepstock.charba.client.dom.NodeList;
import org.pepstock.charba.client.dom.elements.Canvas;
import org.pepstock.charba.client.dom.elements.Div;
import org.pepstock.charba.client.dom.elements.TableCell;
import org.pepstock.charba.client.dom.enums.Unit;
import org.pepstock.charba.client.dom.safehtml.SafeHtml;
import org.pepstock.charba.client.enums.DefaultPluginId;
import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.impl.plugins.HtmlLegendOptionsFactory.HtmlLegendBuilderDefaultsOptionsFactory;
import org.pepstock.charba.client.items.LegendLabelItem;
import org.pepstock.charba.client.plugins.AbstractPlugin;

/**
 * This plugin implements a HTML legend in order to give more flexibility to who needs to customize the legend.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class HtmlLegend extends AbstractPlugin {

	/**
	 * Plugin ID <b>{@value ID}</b>.
	 */
	public static final String ID = "charbahtmllegend";
	/**
	 * The factory to create options for plugin.
	 */
	public static final HtmlLegendOptionsFactory FACTORY = new HtmlLegendOptionsFactory();
	// factory instance to read the options from default global
	static final HtmlLegendBuilderDefaultsOptionsFactory DEFAULTS_FACTORY = new HtmlLegendBuilderDefaultsOptionsFactory();
	// singleton instance
	private static final HtmlLegend INSTANCE = new HtmlLegend();
	// suffix label for main HTML legend element id
	private static final String SUFFIX_LEGEND_ELEMENT_ID = "_legend";
	// static instance to generate legend into HTML
	private static final HtmlLegendGenerator GENERATOR = new HtmlLegendGenerator();
	// cache to store options in order do not load every time the options
	private final Map<String, HtmlLegendOptions> pluginOptions = new HashMap<>();
	// cache to store legend items managed by chart
	private final Map<String, List<LegendLabelItem>> pluginLegendLabelsItems = new HashMap<>();
	// cache to store the chart id in order to know when new legend must be created
	private final Set<String> pluginAddedLegendStatus = new HashSet<>();
	// cache to store DIV element which contains legend for each chart
	private final Map<String, Div> pluginDivElements = new HashMap<>();
	// cache to store the original value of legend in order to
	// manage the change of the legend display after chart creation
	private final Map<String, Boolean> pluginLegendDisplayStatus = new HashMap<>();
	// cache to store the callback proxies of legend
	private final Map<String, HtmlLegendCallbackProxy> pluginCallbackProxies = new HashMap<>();

	/**
	 * To avoid any instantiation
	 */
	private HtmlLegend() {
		super(ID);
	}

	/**
	 * Returns the singleton instance of plugin.
	 * 
	 * @return the singleton instance of plugin
	 */
	public static HtmlLegend get() {
		return INSTANCE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onConfigure(org.pepstock.charba.client. AbstractChart)
	 */
	@Override
	public void onConfigure(IsChart chart) {
		// checks if argument is consistent
		if (IsChart.isConsistent(chart)) {
			// adds into a map the callback proxy instance
			// to catch events form legend
			if (!pluginCallbackProxies.containsKey(chart.getId())) {
				pluginCallbackProxies.put(chart.getId(), new HtmlLegendCallbackProxy());
			}
			HtmlLegendOptions pOptions = null;
			// loads chart options for the chart
			IsDefaultScaledOptions options = chart.getWholeOptions();
			// if not, loads and cache
			// creates the plugin options using the java script object
			// passing also the default color set at constructor.
			if (options.getPlugins().hasOptions(ID)) {
				pOptions = options.getPlugins().getOptions(ID, FACTORY);
			} else {
				pOptions = new HtmlLegendOptions(HtmlLegendDefaultOptions.INSTANCE);
			}
			pluginOptions.put(chart.getId(), pOptions);
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
	 * @see org.pepstock.charba.client.Plugin#onBeginDrawing(org.pepstock.charba.client.IsChart, boolean)
	 */
	@Override
	public void onBeginDrawing(IsChart chart, boolean overridePreviousUpdate) {
		// checks if argument is consistent
		if (mustBeDisplay(chart)) {
			// checks if reloading during previous drawing
			// if the previous stored easing is greater than the current one her
			// means that the is chart is updating without waiting for ending
			// the previous update
			if (overridePreviousUpdate) {
				// removes the item
				// in order to after draw to create the legend
				pluginAddedLegendStatus.remove(chart.getId());
			}
			// gets the legend
			Legend legend = chart.getOptions().getLegend();
			// creates legend DIV element reference
			Div legendElement = null;
			// checks if element is alreayd created
			if (!pluginDivElements.containsKey(chart.getId())) {
				// if new
				// creates a DIV element
				legendElement = DOMBuilder.get().createDivElement();
				// sets the id by chart instance
				legendElement.setId(formatLegendElementId(chart));
				// stores into map
				pluginDivElements.put(chart.getId(), legendElement);
			} else {
				// if here, DIV element already exists then it retrieves it
				legendElement = pluginDivElements.get(chart.getId());
			}
			// checks if there is a parent
			if (legendElement.getParentNode() == null) {
				// if no parent, means new object to add to chart element
				addLegendElement(chart.getChartElement(), legendElement, legend.getPosition(), legend.getLabels().getPadding());
			} else {
				// removes the item
				// in order to after draw to create the legend
				pluginAddedLegendStatus.remove(chart.getId());
				// if here, the div has got the parent
				// then it checks if the position is the same when it has been created
				// otherwise it will move to the right position
				manageLegendElement(chart, legendElement, legend.getPosition());
			}
			// checks if is full width has been set
			if (legend.isFullSize()) {
				// sets 100% of width
				legendElement.getStyle().setWidth(Unit.PCT.format(100));
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onAfterDraw(org.pepstock.charba.client. AbstractChart)
	 */
	@Override
	public void onAfterDraw(IsChart chart) {
		// checks if argument is consistent
		// and checks if the legend must be created
		// the legend will be created if there is the legend element
		// and the chart is is NOT in the set
		if (mustBeDisplay(chart) && pluginDivElements.containsKey(chart.getId()) && !pluginAddedLegendStatus.contains(chart.getId())) {
			// gets div element
			Div legendElement = pluginDivElements.get(chart.getId());
			// invokes the legend generator to have the HTML of legend
			SafeHtml html = GENERATOR.generateLegend(chart);
			// removes all children of div element
			legendElement.removeAllChildren();
			// sets as inner HTML
			legendElement.setInnerHTML(html.asString());
			// removes all listeners
			removeListeners(chart, legendElement);
			// adds the event listeners to element
			addListeners(chart, legendElement);
			// adds into set
			// in order do not add the inner html every easing
			pluginAddedLegendStatus.add(chart.getId());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onEndDrawing(org.pepstock.charba.client.IsChart)
	 */
	@Override
	public void onEndDrawing(IsChart chart) {
		// checks if argument is consistent
		if (mustBeDisplay(chart)) {
			// removes chart for items
			// in order to add next cycle
			pluginAddedLegendStatus.remove(chart.getId());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onDestroy(org.pepstock.charba.client.IsChart)
	 */
	@Override
	public void onDestroy(IsChart chart) {
		// checks if argument is consistent
		if (IsChart.isValid(chart)) {
			// resets all status items
			resetStatus(chart);
			// removes status of legend display
			pluginLegendDisplayStatus.remove(chart.getId());
			// removes callback proxies
			pluginCallbackProxies.remove(chart.getId());
			// removes the chart from options
			pluginOptions.remove(chart.getId());
		}
	}

	/**
	 * Manages if the legend must be displayed or not based on choice of user and what can be changed into chart configuration after the chart initialization bu a
	 * <code>chart.reconfigure</code>.
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
		if (pluginLegendDisplayStatus.containsKey(chart.getId())) {
			// if here the plugin was already initialized
			// and then it stored the display value
			cachedValue = pluginLegendDisplayStatus.get(chart.getId());
			// because is not the first round
			// the legend value should be false because set by plugin
			// if is true, means the user changed it programmatically
			if (chart.getOptions().getLegend().isDisplay() && !cachedValue) {
				// stored the legend display value because is changed
				pluginLegendDisplayStatus.put(chart.getId(), chart.getOptions().getLegend().isDisplay());
			}
		} else {
			// stored the legend display value because is missing
			pluginLegendDisplayStatus.put(chart.getId(), chart.getOptions().getLegend().isDisplay());
		}
		boolean mustBeChecked = chart.getOptions().getLegend().isDisplay() || cachedValue;
		if (mustBeChecked && chart.getOptions().getPlugins().isEnabled(DefaultPluginId.LEGEND)) {
			// disable legend
			chart.getOptions().getLegend().setDisplay(false);
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
		if (IsChart.isValid(chart) && pluginOptions.containsKey(chart.getId())) {
			// gets stored option
			HtmlLegendOptions options = pluginOptions.get(chart.getId());
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
		if (pluginDivElements.containsKey(chart.getId())) {
			// removes from map
			Div legendElement = pluginDivElements.remove(chart.getId());
			// removes all listeners
			removeListeners(chart, legendElement);
			// removes all children
			legendElement.removeAllChildren();
			// removes from parent
			legendElement.removeFromParent();
		}
		// removes the chart status
		pluginAddedLegendStatus.remove(chart.getId());
		// removes the chart legend labels items
		pluginLegendLabelsItems.remove(chart.getId());
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
	private void addListeners(IsChart chart, Div legendElement) {
		// gets all nodes with TAG TD
		NodeList<BaseElement> tds = legendElement.getElementsByTagName(TableCell.TAG);
		// scans all nodes
		for (int i = 0; i < tds.length(); i++) {
			// gets element
			BaseElement td = tds.item(i);
			// checks if the element has got a correct ID
			// starting with chart id
			if (td.getId().startsWith(chart.getId()) && pluginCallbackProxies.containsKey(chart.getId())) {
				HtmlLegendCallbackProxy callbackProxy = pluginCallbackProxies.get(chart.getId());
				// adds to the element all event listeners
				callbackProxy.addListeners(td);
			}
		}
	}

	/**
	 * Removes the event listeners from all elements created by legend callback.
	 * 
	 * @param chart chart instance
	 * @param legendElement DIV legend element which contains the custom HTML legend.
	 */
	private void removeListeners(IsChart chart, Div legendElement) {
		// gets all nodes with TAG TD
		NodeList<BaseElement> tds = legendElement.getElementsByTagName(TableCell.TAG);
		// scans all nodes
		for (int i = 0; i < tds.length(); i++) {
			// gets element
			BaseElement td = tds.item(i);
			// checks if the element has got a correct ID
			// starting with chart id
			if (td.getId().startsWith(chart.getId()) && pluginCallbackProxies.containsKey(chart.getId())) {
				HtmlLegendCallbackProxy callbackProxy = pluginCallbackProxies.get(chart.getId());
				// removes to the element all event listeners
				callbackProxy.removeListeners(td);
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
	private void addLegendElement(Div chartElement, Div legendElement, Position position, int padding) {
		if (mustAddToBottom(position)) {
			// appends the legend element
			chartElement.appendChild(legendElement);
			// and sets the bottom padding
			legendElement.getStyle().setPaddingBottom(Unit.PX.format(padding));
		} else {
			// if not bottom, inserts the legend element as first
			chartElement.insertBefore(legendElement, chartElement.getFirstChild());
			// and sets the top padding
			legendElement.getStyle().setPaddingTop(Unit.PX.format(padding));
		}
	}

	/**
	 * Manages the HTML legend element to the right position into chart element, depending on {@link Position} set for legend.<br>
	 * This method is called when a legend element is already added and the position could be changed comparing when the element has been created.
	 * 
	 * @param chart chart instance
	 * @param legendElement legend HTML element
	 * @param position position set by legend configuration object
	 */
	private void manageLegendElement(IsChart chart, Div legendElement, Position position) {
		// gets chart element
		Div chartElement = chart.getChartElement();
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
			chartElement.insertBefore(legendElement, chartElement.getFirstChild());
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
	private boolean isAfterCanvas(IsChart chart, Div legendElement) {
		// gets chart element
		Div chartElement = chart.getChartElement();
		// retrieves canvas id of chart
		String canvasId = chart.getCanvas().getId();
		// scans all children of chart element
		NodeList<BaseNode> children = chartElement.getChildNodes();
		for (int i = 0; i < children.length(); i++) {
			// gets the node
			BaseNode childNode = children.item(i);
			// checks if is html element
			if (childNode instanceof BaseHtmlElement) {
				BaseHtmlElement childElement = (BaseHtmlElement) childNode;
				// checks if the legend element is equals to the scanned node
				// by its id
				if (childElement.getNodeName().equalsIgnoreCase(Div.TAG) && legendElement.getId().equalsIgnoreCase(childElement.getId())) {
					// if here, means that the legend element has been found before the canvas element
					// then returns false
					return false;
				} else if (childElement.getNodeName().equalsIgnoreCase(Canvas.TAG) && childElement.getId().equalsIgnoreCase(canvasId)) {
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
	 * Returns the map of cached plugin options.
	 * 
	 * @return the map of cached plugin options
	 */
	Map<String, HtmlLegendOptions> getPluginOptions() {
		return pluginOptions;
	}

	/**
	 * Returns the map of cached legend labels items.
	 * 
	 * @return the map of cached legend labels items
	 */
	Map<String, List<LegendLabelItem>> getPluginLegendLabelsItems() {
		return pluginLegendLabelsItems;
	}

	/**
	 * Returns the map of cached added legend labels status.
	 * 
	 * @return the map of cached added legend labels status
	 */
	Set<String> getPluginAddedLegendStatus() {
		return pluginAddedLegendStatus;
	}

	/**
	 * Returns the map of cached DIV elements of HTML legend.
	 * 
	 * @return the map of cached DIV elements of HTML legend
	 */
	Map<String, Div> getPluginDivElements() {
		return pluginDivElements;
	}

}