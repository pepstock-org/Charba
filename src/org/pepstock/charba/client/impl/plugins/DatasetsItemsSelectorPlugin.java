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
import java.util.Map;

import org.pepstock.charba.client.ChartType;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.ScaleType;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.configuration.BarOptions;
import org.pepstock.charba.client.configuration.LineOptions;
import org.pepstock.charba.client.data.BarDataset;
import org.pepstock.charba.client.data.Dataset;
import org.pepstock.charba.client.data.LineDataset;
import org.pepstock.charba.client.defaults.IsDefaultScaledOptions;
import org.pepstock.charba.client.dom.BaseEventTypes;
import org.pepstock.charba.client.dom.BaseNativeEvent;
import org.pepstock.charba.client.dom.enums.CursorType;
import org.pepstock.charba.client.enums.Event;
import org.pepstock.charba.client.enums.IndexAxis;
import org.pepstock.charba.client.events.HandlerRegistration;
import org.pepstock.charba.client.events.LegendClickEvent;
import org.pepstock.charba.client.impl.callbacks.AtLeastOneDatasetHandler;
import org.pepstock.charba.client.items.PluginEventArgument;
import org.pepstock.charba.client.plugins.AbstractPlugin;
import org.pepstock.charba.client.resources.ResourceName;

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
final class DatasetsItemsSelectorPlugin extends AbstractPlugin {

	// It can not use zoom plugin id
	// to avoid to load zoom and hammer JS if not needed
	// it must always aligned with value in the zoom plugin model
	private static final String ZOOM_PLUIGIN_ID = ResourceName.ZOOM_PLUGIN.value();
	// map to maintain the selectors handler for every chart
	private final Map<String, SelectionHandler> pluginSelectionHandlers = new HashMap<>();
	// set to maintain the status if legend click handler, if already added or not
	private final Map<String, HandlerRegistration> pluginEventsRegistrationHandlers = new HashMap<>();
	// click legend handler to avoid to remove all data sets
	private final AtLeastOneDatasetHandler pluginLegendClickHandler = new AtLeastOneDatasetHandler();
	// maps with the enablement if the plugin by chart instances
	private final Map<String, Boolean> pluginChartEnablement = new HashMap<>();

	/**
	 * To avoid any instantiation
	 */
	DatasetsItemsSelectorPlugin() {
		super(DatasetsItemsSelector.ID);
	}

	/**
	 * Returns the selection handlers.
	 * 
	 * @return the selection handlers.
	 */
	Map<String, SelectionHandler> getHandlers() {
		return pluginSelectionHandlers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onConfigure(org.pepstock.charba.client. AbstractChart)
	 */
	@Override
	public void onConfigure(IsChart chart) {
		// gets options in order to store the enablement of the plugin
		storePluginEnablement(chart);
		// checks if chart is consistent and the plugin has been invoked for LINE or BAR charts
		if (mustBeActivated(chart)) {
			// overrides the tooltip configuration disabling it
			chart.getOptions().getTooltips().setEnabled(false);
			// resets the padding callback
			chart.getOptions().getLayout().setPadding((NativeCallback) null);
			// checks if handler on legend to avoid to remove all data sets has been already added
			// and if legend is display
			// checks if is chart is a abstract chart instance
			if (!pluginEventsRegistrationHandlers.containsKey(chart.getId()) && chart.getOptions().getLegend().isDisplay()) {
				// if not, adds handler
				HandlerRegistration registratrion = chart.addHandler(pluginLegendClickHandler, LegendClickEvent.TYPE);
				// stores flag in the map
				pluginEventsRegistrationHandlers.put(chart.getId(), registratrion);
			}
			// checks if chart has got already an handler
			if (pluginSelectionHandlers.containsKey(chart.getId())) {
				// gets selection handler
				SelectionHandler handler = pluginSelectionHandlers.get(chart.getId());
				// cleans up the handler
				handler.destroy();
				// removes previous handler
				pluginSelectionHandlers.remove(chart.getId());
			}
			// option instance
			DatasetsItemsSelectorOptions pOptions = getOptions(chart);
			// creates the handler of selection
			// by chart instance and the options stored in the options (if exists).
			SelectionHandler handler = new SelectionHandler(chart, pOptions);
			// removes the default mouse down listener
			chart.removeCanvasPreventDefault();
			// adds all mouse listeners to canvas
			handler.addListeners();
			// stores selection handler
			pluginSelectionHandlers.put(chart.getId(), handler);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onBeginDrawing(org.pepstock.charba.client.IsChart, boolean)
	 */
	@Override
	public void onBeginDrawing(IsChart chart, boolean overridePreviousUpdate) {
		// checks if chart is consistent and the plugin has been invoked for LINE or BAR charts
		if (mustBeActivated(chart)) {
			// adds checks if there is any data set selection handler in the option
			// if yes exception
			Checker.assertCheck(!chart.getOptions().hasDatasetSelectionHandlers(), "Unable to activate plugin because a dataset selection handler has been defined");
			if (pluginSelectionHandlers.containsKey(chart.getId())) {
				// sets cursor wait because the chart is drawing and not selectable
				chart.getCanvas().getStyle().setCursorType(CursorType.WAIT);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onAfterDraw(org.pepstock.charba.client.IsChart)
	 */
	@Override
	public void onAfterDraw(IsChart chart) {
		// checks if chart is consistent and the plugin has been invoked for LINE or BAR charts
		if (mustBeActivated(chart) && pluginSelectionHandlers.containsKey(chart.getId())) {
			// gets selection handler
			SelectionHandler handler = pluginSelectionHandlers.get(chart.getId());
			// calculates the coordinates of selection cleaner element
			handler.calculateSelectionCleanerPositions();
			// checks if the draw if at the end of animation
			// and if the selection is not already started
			// the selection is managed
			manageSelection(chart, handler);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onBeforeDestroy(org.pepstock.charba.client.IsChart)
	 */
	@Override
	public void onBeforeDestroy(IsChart chart) {
		// checks if chart is consistent and the plugin has been invoked for LINE or BAR charts
		if (mustBeActivated(chart)) {
			// resets configuration
			resetPluginConfiguration(chart);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onBeforeEvent(org.pepstock.charba.client.IsChart, org.pepstock.charba.client.items.PluginEventArgument)
	 */
	@Override
	public boolean onBeforeEvent(IsChart chart, PluginEventArgument argument) {
		// the mouse move is enabled
		// but not managed in order to work with other plugins
		if (BaseEventTypes.MOUSE_MOVE.equalsIgnoreCase(argument.getEventContext().getType())) {
			return true;
		}
		// checks if chart is consistent and the plugin has been invoked for LINE or BAR charts
		if (mustBeActivated(chart) && pluginSelectionHandlers.containsKey(chart.getId())) {
			// gets native event
			BaseNativeEvent event = argument.getEventContext().getNativeEvent();
			// gets selection handler
			SelectionHandler handler = pluginSelectionHandlers.get(chart.getId());
			// manages event
			// if returns false
			// does not continue
			// avoiding to propagate the event
			if (!manageClickEvent(chart, event, handler)) {
				return false;
			}
			// This control has been added because a click event is always fired
			// by canvas when mouse up (of selection handler) is performed
			// but to avoid to refresh the chart every time
			// selection handler sets a flag to check this condition
			if (handler.isPreventClickEvent()) {
				// resets flag
				handler.resetPreventClickEvent();
				// and forces the event will be discarded.
				return false;
			}
		}
		// if here, propagates the event to other listeners
		return true;
	}

	/**
	 * Returns the options of the plugin from chart.
	 * 
	 * @param chart chart instance to check
	 * @return the options of the plugin from chart.
	 */
	private DatasetsItemsSelectorOptions getOptions(IsChart chart) {
		// option instance
		DatasetsItemsSelectorOptions pOptions = null;
		// checks if chart is consistent
		if (IsChart.isConsistent(chart)) {
			// loads chart options for the chart
			IsDefaultScaledOptions options = chart.getWholeOptions();
			// creates the plugin options using the java script object
			// passing also the default color set at constructor.
			if (options.getPlugins().hasOptions(DatasetsItemsSelector.ID)) {
				pOptions = options.getPlugins().getOptions(DatasetsItemsSelector.ID, DatasetsItemsSelector.FACTORY);
			} else {
				pOptions = new DatasetsItemsSelectorOptions(DatasetsItemsSelectorDefaultOptions.INSTANCE);
			}
		} else {
			// if here the chart is not consistent
			// then returns default
			pOptions = new DatasetsItemsSelectorOptions(DatasetsItemsSelectorDefaultOptions.INSTANCE);
			// disabled it
			pOptions.setEnabled(false);
		}
		// returns options
		return pOptions;
	}

	/**
	 * Stores the "enabled" option in the cache in order to change the plugin bahaviors at runtime.
	 * 
	 * @param chart chart instance to check
	 */
	private void storePluginEnablement(IsChart chart) {
		// option instance
		DatasetsItemsSelectorOptions pOptions = getOptions(chart);
		// gets stored enablement
		Boolean isEnabled = pluginChartEnablement.get(chart.getId());
		// checks if there is already an enabled
		if (Boolean.TRUE.equals(isEnabled) && !pOptions.isEnabled()) {
			// selection cleaner
			if (pluginSelectionHandlers.containsKey(chart.getId())) {
				// gets selection handler
				SelectionHandler handler = pluginSelectionHandlers.get(chart.getId());
				// clear the selection
				handler.removeSelectionCleaner();
			}
			// here if the previous config enabled the plugin
			// and then now it must be disabled
			resetPluginConfiguration(chart);
		}
		// stores if the chart is enabled
		pluginChartEnablement.put(chart.getId(), pOptions.isEnabled());
	}

	/**
	 * Resets the current configuration when the chart is destroyed or when the plugin is disabled
	 * 
	 * @param chart chart instance to check
	 */
	private void resetPluginConfiguration(IsChart chart) {
		// checks if we have already an handler
		if (pluginSelectionHandlers.containsKey(chart.getId())) {
			// gets selection handler
			SelectionHandler handler = pluginSelectionHandlers.get(chart.getId());
			// at chart destroy phase, all handler will be removed form canvas
			// removes mouse handler if consistent
			// and cleans the handler
			handler.destroy();
			// removes handler from map
			pluginSelectionHandlers.remove(chart.getId());
		}
		// checks if we have already an legend handler
		if (pluginEventsRegistrationHandlers.containsKey(chart.getId())) {
			// cleans the legend click handler status
			HandlerRegistration registration = pluginEventsRegistrationHandlers.remove(chart.getId());
			// removes registration
			registration.removeHandler();
		}
	}

	/**
	 * Returns <code>true</code> if the chart is consistent and the scale type is {@link ScaleType#MULTI} and {@link IndexAxis#X}.<br>
	 * If ZoomPlugin is activated, this plugin will be disabled.
	 * 
	 * @param chart chart instance to check
	 * @return <code>true</code> if the chart is consistent and the scale type is {@link ScaleType#MULTI} and {@link IndexAxis#X}
	 */
	private boolean mustBeActivated(IsChart chart) {
		// checks consistent
		boolean mustBeActivated = IsChart.isConsistent(chart) && (ScaleType.MULTI.equals(chart.getBaseType().scaleType()));
		// if the first check is true...
		if (mustBeActivated) {
			// .. is adding an additional check on ZOOM plugin, if enabled
			mustBeActivated = !chart.getOptions().getPlugins().isEnabled(ZOOM_PLUIGIN_ID);
			// checks if it's an horizontal bar
			if (mustBeActivated) {
				// sets if must be activated checking if the options and datasets
				mustBeActivated = checkIndexAxis(chart);
			}
		}
		// checks if the plugin has been enabled
		if (mustBeActivated) {
			mustBeActivated = pluginChartEnablement.containsKey(chart.getId()) && pluginChartEnablement.get(chart.getId());
		}
		return mustBeActivated;
	}

	/**
	 * Returns <code>true</code> if the chart has got {@link IndexAxis#X} where applicable.
	 * 
	 * @param chart chart instance to check
	 * @return <code>true</code> if the chart has got {@link IndexAxis#X} where applicable
	 */
	private boolean checkIndexAxis(IsChart chart) {
		// checks is a line or bar chart
		// in order to check the index axis
		if (ChartType.BAR.equals(chart.getBaseType()) && chart.getOptions() instanceof BarOptions) {
			// casts to bar options
			BarOptions barOptions = (BarOptions) chart.getOptions();
			// checks index axis
			return IndexAxis.X.equals(barOptions.getIndexAxis());
		} else if (ChartType.LINE.equals(chart.getBaseType()) && chart.getOptions() instanceof LineOptions) {
			// casts to line options
			LineOptions lineOptions = (LineOptions) chart.getOptions();
			// checks index axis
			return IndexAxis.X.equals(lineOptions.getIndexAxis());
		}
		// if here, the chart is manageable
		// but the data sets must be check as well
		return checkDatasets(chart);
	}

	/**
	 * Returns <code>true</code> if all data sets of bar chart have got the {@link IndexAxis#X}.
	 * 
	 * @param chart chart instance
	 * @return <code>true</code> if all data sets of bar chart have got the {@link IndexAxis#X}
	 */
	private boolean checkDatasets(IsChart chart) {
		// scans all data sets to check if they are set as horizontal
		for (Dataset dataset : chart.getData().getDatasets()) {
			// checks if is a bar data set
			if (dataset instanceof BarDataset) {
				// casts to bar data set
				BarDataset barDataset = (BarDataset) dataset;
				// checks if is horizontal
				if (IndexAxis.Y.equals(barDataset.getIndexAxis())) {
					// the base axis of data set is horizontal
					// therefore returns false
					return false;
				}
			} else if (dataset instanceof LineDataset) {
				// checks if is a line data set
				// casts to line data set
				LineDataset lineDataset = (LineDataset) dataset;
				// checks if is vertical
				if (IndexAxis.Y.equals(lineDataset.getIndexAxis())) {
					// the base axis of data set is vertical
					// therefore returns false
					return false;
				}
			}
		}
		// every data set is correctly configured
		// in order to enable the plugin
		return true;
	}

	/**
	 * Manages the CLICK event on selection area.<br>
	 * Returns <code>true</code> if must continue the event management.
	 * 
	 * @param chart chart instance to manage
	 * @param event native event to manage
	 * @param handler selection handler related to chart instance
	 * @return <code>true</code> if must continue the event management
	 */
	private boolean manageClickEvent(IsChart chart, BaseNativeEvent event, SelectionHandler handler) {
		// checks if it is a click event
		// ONLY click are caught
		if (Event.CLICK.value().equalsIgnoreCase(event.getType())) {
			// option instance
			DatasetsItemsSelectorOptions pOptions = handler.getOptions();
			// get selection cleaner element
			SelectionCleaner selectionCleaner = pOptions.getSelectionCleaner();
			// checks if is enabled
			if (selectionCleaner.isDisplay()) {
				// calculates if the events coordinates are hover of selection cleaner element
				boolean isX = event.getLayerX() >= selectionCleaner.getX() && event.getLayerX() <= (selectionCleaner.getX() + selectionCleaner.getWidth());
				boolean isY = event.getLayerY() >= selectionCleaner.getY() && event.getLayerY() <= (selectionCleaner.getY() + selectionCleaner.getHeight());
				// checks if hover
				if (isX && isY) {
					// invokes the selection cleaner
					DatasetsItemsSelector.get().cleanSelection(chart);
					// and forces the event will be discarded.
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Invoked when the chart is already drawn and takes care about the selection logic.
	 * 
	 * @param chart chart instance
	 * @param handler selection handler instance
	 */
	private void manageSelection(IsChart chart, SelectionHandler handler) {
		// if the selections is already present
		// it refreshes all the calculation of existing selection
		if (handler.getStatus().equals(SelectionStatus.SELECTED)) {
			handler.refresh();
		} else if (handler.getStatus().equals(SelectionStatus.SELECTING)) {
			handler.draw();
			// returns in order to maintain the cursor crosshair
			return;
		}
		// the drawing of chart is completed and set the default cursor
		// removing the "wait" one.
		chart.getCanvas().getStyle().setCursorType(CursorType.DEFAULT);
	}
}