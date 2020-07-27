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
import java.util.List;
import java.util.Map;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.ScaleType;
import org.pepstock.charba.client.defaults.IsDefaultScaledOptions;
import org.pepstock.charba.client.dom.BaseNativeEvent;
import org.pepstock.charba.client.dom.enums.CursorType;
import org.pepstock.charba.client.enums.DefaultPluginId;
import org.pepstock.charba.client.impl.plugins.ChartPointerOptionsFactory.ChartPointerDefaultsOptionsFactory;
import org.pepstock.charba.client.impl.plugins.enums.PointerElement;
import org.pepstock.charba.client.items.LegendHitBoxItem;
import org.pepstock.charba.client.plugins.AbstractPlugin;

/**
 * This plugin is changing the cursor when mouse over on dataset, title on canvas if a dataset selection, title handler have been defined.
 * 
 * @author Andrea "Stock" Stocchero
 * @see CursorType
 */
public final class ChartPointer extends AbstractPlugin {

	/**
	 * Plugin ID <b>{@value ID}</b>.
	 */
	public static final String ID = "charbacursorpointer";
	/**
	 * The factory to create options for plugin.
	 */
	public static final ChartPointerOptionsFactory FACTORY = new ChartPointerOptionsFactory();
	// singleton instance
	private static final ChartPointer INSTANCE = new ChartPointer();
	// defaults global options factory
	static final ChartPointerDefaultsOptionsFactory DEFAULTS_FACTORY = new ChartPointerDefaultsOptionsFactory();
	// cache to store options in order do not load every time the options
	private final Map<String, ChartPointerOptions> pluginOptions = new HashMap<>();

	/**
	 * To avoid any instantiation
	 */
	private ChartPointer() {
		// do nothing
	}

	/**
	 * Returns the singleton instance of plugin.
	 * 
	 * @return the singleton instance of plugin
	 */
	public static ChartPointer get() {
		return INSTANCE;
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
	 * @see org.pepstock.charba.client.plugins.AbstractPlugin#onBeforeUpdate(org.pepstock.charba.client.IsChart)
	 */
	@Override
	public boolean onBeforeUpdate(IsChart chart) {
		// checks if chart is consistent
		if (IsChart.isConsistent(chart)) {
			// creates options instance
			ChartPointerOptions pOptions = null;
			// loads chart options for the chart
			IsDefaultScaledOptions options = chart.getWholeOptions();
			// creates the plugin options checking if exists ot not
			if (options.getPlugins().hasOptions(ID)) {
				pOptions = options.getPlugins().getOptions(ID, FACTORY);
			} else {
				pOptions = new ChartPointerOptions(ChartPointerDefaultsOptions.DEFAULTS_INSTANCE);
			}
			// stores option on the cache
			pluginOptions.put(chart.getId(), pOptions);
			// sets the initial cursor of chart
			pOptions.setCurrentCursor(chart.getInitialCursor());
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.plugins.AbstractPlugin#onAfterEvent(org.pepstock.charba.client.IsChart, org.pepstock.charba.client.dom.BaseNativeEvent)
	 */
	@Override
	public void onAfterEvent(IsChart chart, BaseNativeEvent event) {
		// checks if chart is consistent and options of plugin has been stored
		if (IsChart.isConsistent(chart) && pluginOptions.containsKey(chart.getId())) {
			// gets options instance
			ChartPointerOptions pOptions = pluginOptions.get(chart.getId());
			// gets the scope
			List<PointerElement> scope = pOptions.getElements();
			// DATASET SELECTION
			if (hasDatasetSelection(chart, event, scope)) {
				chart.getChartElement().getStyle().setCursorType(pOptions.getCursorPointer());
			} else if (hasTitleSelection(chart, event, scope)) {
				// TITLE SELECTION
				chart.getChartElement().getStyle().setCursorType(pOptions.getCursorPointer());
			} else if (hasScaleSelection(chart, event, scope)) {
				// AXIS SELECTION
				chart.getChartElement().getStyle().setCursorType(pOptions.getCursorPointer());
			} else if (hasLegendSelection(chart, event, scope)) {
				// LEGEND SELECTION
				chart.getChartElement().getStyle().setCursorType(pOptions.getCursorPointer());
			} else {
				// if null, sets the default cursor
				chart.getChartElement().getStyle().setCursorType(chart.getInitialCursor());
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
		// checks if chart is valid
		if (IsChart.isValid(chart)) {
			// removes the stored options for chart
			pluginOptions.remove(chart.getId());
		}
	}

	/**
	 * Returns <code>true</code> if the cursor is over to a dataset, otherwise <code>false</code>.
	 * 
	 * @param chart chart instance
	 * @param event event form CHART.js
	 * @param scope the scope with all activated scope of the plugin
	 * @return <code>true</code> if the cursor is over to a dataset, otherwise <code>false</code>
	 */
	private boolean hasDatasetSelection(IsChart chart, BaseNativeEvent event, List<PointerElement> scope) {
		// checks if the datasets is in scope
		if (chart.getOptions().hasDatasetSelectionHandlers() && isElementInScope(scope, PointerElement.DATASET)) {
			// if yes, asks the dataset item by event
			// if consistent, is over a dataset
			return chart.getElementAtEvent(event) != null;
		}
		return false;
	}

	/**
	 * Returns <code>true</code> if the cursor is over to the title, otherwise <code>false</code>.
	 * 
	 * @param chart chart instance
	 * @param event event form CHART.js
	 * @param scope the scope with all activated scope of the plugin
	 * @return <code>true</code> if the cursor is over to the title, otherwise <code>false</code>
	 */
	private boolean hasTitleSelection(IsChart chart, BaseNativeEvent event, List<PointerElement> scope) {
		// checks if title display is activated or title plugin is activated
		boolean isTitleEnabled = chart.getOptions().getTitle().isDisplay();
		// checks if there is any title click handler and title is in scope
		// and the cursor is over the title element
		return isTitleEnabled && chart.getOptions().hasTitleClickHandlers() && isElementInScope(scope, PointerElement.TITLE) && chart.getNode().getTitle().isInside(event);
	}

	/**
	 * Returns <code>true</code> if the cursor is over to a scale, otherwise <code>false</code>.
	 * 
	 * @param chart chart instance
	 * @param event event form CHART.js
	 * @param scope the scope with all activated scope of the plugin
	 * @return <code>true</code> if the cursor is over to a scale, otherwise <code>false</code>
	 */
	private boolean hasScaleSelection(IsChart chart, BaseNativeEvent event, List<PointerElement> scope) {
		// checks if there is any axis click handler and axis is in scope
		// and the cursor is over the axis element
		return chart.getOptions().hasAxisClickHandlers() && isElementInScope(scope, PointerElement.AXES) && !ScaleType.NONE.equals(chart.getType().scaleType()) && chart.getNode().getScales().isInside(event);
	}

	/**
	 * Returns <code>true</code> if the cursor is over to the legend, otherwise <code>false</code>.
	 * 
	 * @param chart chart instance
	 * @param event event form CHART.js
	 * @param scope the scope with all activated scope of the plugin
	 * @return <code>true</code> if the cursor is over to the legend, otherwise <code>false</code>
	 */
	private boolean hasLegendSelection(IsChart chart, BaseNativeEvent event, List<PointerElement> scope) {
		// checks if legend display is activated or legend plugin is activated
		boolean isLegendEnabled = chart.getOptions().getLegend().isDisplay() && !chart.getOptions().getPlugins().isForcedlyDisabled(DefaultPluginId.LEGEND);
		// checks if legend is in scope
		// and the cursor is over the legend element
		if (isLegendEnabled && isElementInScope(scope, PointerElement.LEGEND) && chart.getNode().getLegend().isInside(event)) {
			// LEGEND SELECTION
			// checks if cursor is over the hit box
			List<LegendHitBoxItem> legendItems = chart.getNode().getLegend().getHitBoxes();
			// scans all legend item box
			for (LegendHitBoxItem legendItem : legendItems) {
				// if cursor inside the legend item
				if (legendItem.isInside(event)) {
					// confirms is on legend
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Returns <code>true</code> if the element is configured in order to apply cursor when mouse is over.
	 * 
	 * @param scope list of pointer elements configured as in scope
	 * @param element the element to be checked
	 * @return <code>true</code> if the element is configured in order to apply cursor when mouse is over
	 */
	private boolean isElementInScope(List<PointerElement> scope, PointerElement element) {
		// scans all elements in scope
		for (PointerElement scopeElement : scope) {
			// if equals, return true
			if (element.equals(scopeElement)) {
				return true;
			}
		}
		// if here, the element is not in scope
		return false;
	}

}
