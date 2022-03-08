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

import java.util.Date;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.Plugin;
import org.pepstock.charba.client.configuration.CartesianCategoryAxis;
import org.pepstock.charba.client.configuration.CartesianLinearAxis;
import org.pepstock.charba.client.configuration.CartesianLogarithmicAxis;
import org.pepstock.charba.client.configuration.CartesianTimeAxis;
import org.pepstock.charba.client.configuration.CartesianTimeSeriesAxis;
import org.pepstock.charba.client.dom.DOMBuilder;
import org.pepstock.charba.client.events.DatasetRangeCleanSelectionEvent;
import org.pepstock.charba.client.impl.callbacks.AtLeastOneDatasetHandler;
import org.pepstock.charba.client.impl.plugins.DatasetsItemsSelectorOptionsFactory.DatasetsItemsSelectorDefaultsOptionsFactory;
import org.pepstock.charba.client.items.ScaleItem;

/**
 * Enables the datasets items selection directly in the canvas.<br>
 * It works only for line and bar chart instances and if ZoomPlugin is disable.<br>
 * It will add mouser listeners to canvas.<br>
 * Tooltips will be disable to avoid events conflicts.<br>
 * Overrides also the events which can be caught (only click and touchstart).<br>
 * It activates also the legend click handler to avoid that all datasets will be hidden.
 * 
 * @author Andrea "Stock" Stocchero
 * @see AtLeastOneDatasetHandler
 */
public final class DatasetsItemsSelector extends CharbaPluginContainer {

	/**
	 * Plugin ID <b>{@value ID}</b>.
	 */
	public static final String ID = "charbadatasetsitemsselector";
	/**
	 * The factory to read options for plugin
	 */
	public static final DatasetsItemsSelectorOptionsFactory FACTORY = new DatasetsItemsSelectorOptionsFactory();
	// factory instance to read the options from default global
	static final DatasetsItemsSelectorDefaultsOptionsFactory DEFAULTS_FACTORY = new DatasetsItemsSelectorDefaultsOptionsFactory();
	// singleton instance
	private static final DatasetsItemsSelector INSTANCE = new DatasetsItemsSelector();
	// instance of the plugin
	private final DatasetsItemsSelectorPlugin pluginInstance = new DatasetsItemsSelectorPlugin();

	/**
	 * To avoid any instantiation
	 */
	private DatasetsItemsSelector() {
		// do nothing
	}

	/**
	 * Returns the singleton instance of plugin.
	 * 
	 * @return the singleton instance of plugin
	 */
	public static DatasetsItemsSelector get() {
		return INSTANCE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.impl.plugins.CharbaPluginContainer#getPluginInstance()
	 */
	@Override
	Plugin getPluginInstance() {
		return pluginInstance;
	}

	/**
	 * Returns the padding height used by selection cleaner element if enabled.<br>
	 * This is very helpful when you have added padding for your purposes and you need to know the amount of space that the element allocated.
	 * 
	 * @param chart chart instance
	 * @return the padding height used by selection cleaner element or <b>{@link SelectionCleaner#DEFAULT_VALUE}</b> if disabled
	 */
	public double getPadding(IsChart chart) {
		// checks if chart is consistent and there is a handler
		if (IsChart.isValid(chart) && pluginInstance.getHandlers().containsKey(chart.getId())) {
			// gets selection handler
			SelectionHandler handler = pluginInstance.getHandlers().get(chart.getId());
			// option instance
			DatasetsItemsSelectorOptions pOptions = handler.getOptions();
			// gets selection cleaner configuration
			SelectionCleaner selectionCleaner = pOptions.getSelectionCleaner();
			// checks if is enabled
			if (selectionCleaner.isDisplay()) {
				// returns the used padding
				return selectionCleaner.getLayoutPadding();
			}
		}
		// by defaults, returns 0
		return SelectionCleaner.DEFAULT_VALUE;
	}

	/**
	 * Clears the selection on the chart. With this method, it don't fire any clear event if not selected in the plugin options.
	 * 
	 * @param chart chart instance to clear the selection
	 */
	public void cleanSelection(IsChart chart) {
		// flag with default to false
		boolean fireEvent = false;
		// checks chart is consistent and for handler
		if (IsChart.isValid(chart)) {
			// checks in the options if fire event has been set
			fireEvent = chart.isEventHandled(DatasetRangeCleanSelectionEvent.TYPE);
		}
		// invoke reset using fire event of options or false by default
		cleanSelection(chart, fireEvent);
	}

	/**
	 * Clears the selection on the chart and set if an event should fire on clear action.
	 * 
	 * @param chart chart instance to clear the selection
	 * @param fireEvent if <code>true</code> an event is fired otherwise not.
	 */
	public void cleanSelection(IsChart chart, boolean fireEvent) {
		// checks if chart is consistent
		if (!IsChart.isValid(chart)) {
			// if not exit
			return;
		}
		// flag to know if the chart must be updated
		boolean mustBeUpdated = false;
		// checks if we have already an handler
		if (pluginInstance.getHandlers().containsKey(chart.getId())) {
			// gets selection handler
			SelectionHandler handler = pluginInstance.getHandlers().get(chart.getId());
			// clear the selection
			handler.removeSelectionCleaner();
			// checks if the selection was done
			mustBeUpdated = !SelectionStatus.READY.equals(handler.getStatus());
		}
		// destroy the current configuration
		pluginInstance.onBeforeDestroy(chart);
		// recreates the selections handler
		pluginInstance.onConfigure(chart);
		// checks if it must fire the event
		if (fireEvent) {
			// fires the reset event
			chart.fireEvent(new DatasetRangeCleanSelectionEvent(DOMBuilder.get().createChangeEvent()));
		}
		// updates the chart only if the selection was done
		if (mustBeUpdated) {
			// refresh the chart
			chart.update();
		}
	}

	/**
	 * Selects an area, invoked programmatically.<br>
	 * Values on axis are retrieved as string ({@link CartesianCategoryAxis}).
	 * 
	 * @param chart chart instance to use for selection
	 * @param from starting axis value
	 * @param to ending axis value
	 */
	public void setSelection(IsChart chart, String from, String to) {
		// checks if arguments are consistent
		if (IsChart.isConsistent(chart) && pluginInstance.getHandlers().containsKey(chart.getId())) {
			// gets selection handler
			SelectionHandler handler = pluginInstance.getHandlers().get(chart.getId());
			// gets scale
			ScaleItem scaleItem = handler.getScale();
			// sets selection
			handler.setSelection(scaleItem.getPixelForStringValue(from), scaleItem.getPixelForStringValue(to));
		}
	}

	/**
	 * Selects an area, invoked programmatically.<br>
	 * Values on axis are retrieved as number ({@link CartesianLinearAxis} or {@link CartesianLogarithmicAxis}).
	 * 
	 * @param chart chart instance to use for selection
	 * @param from starting axis value
	 * @param to ending axis value
	 */
	public void setSelection(IsChart chart, double from, double to) {
		// checks if arguments are consistent
		if (IsChart.isConsistent(chart) && pluginInstance.getHandlers().containsKey(chart.getId())) {
			// gets selection handler
			SelectionHandler handler = pluginInstance.getHandlers().get(chart.getId());
			// gets scale
			ScaleItem scaleItem = handler.getScale();
			// sets selection
			handler.setSelection(scaleItem.getPixelForValue(from), scaleItem.getPixelForValue(to));
		}
	}

	/**
	 * Selects an area, invoked programmatically.<br>
	 * Values on axis are retrieved as date ({@link CartesianTimeAxis} or {@link CartesianTimeSeriesAxis}).
	 * 
	 * @param chart chart instance to use for selection
	 * @param from starting axis value
	 * @param to ending axis value
	 */
	public void setSelection(IsChart chart, Date from, Date to) {
		// checks if arguments are consistent
		if (IsChart.isConsistent(chart) && pluginInstance.getHandlers().containsKey(chart.getId())) {
			// gets selection handler
			SelectionHandler handler = pluginInstance.getHandlers().get(chart.getId());
			// gets scale
			ScaleItem scaleItem = handler.getScale();
			// sets selection
			handler.setSelection(scaleItem.getPixelForDateValue(from), scaleItem.getPixelForDateValue(to));
		}
	}
}