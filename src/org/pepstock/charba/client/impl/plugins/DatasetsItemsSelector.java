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
import org.pepstock.charba.client.configuration.BarOptions;
import org.pepstock.charba.client.configuration.LineOptions;
import org.pepstock.charba.client.data.BarDataset;
import org.pepstock.charba.client.data.Dataset;
import org.pepstock.charba.client.data.LineDataset;
import org.pepstock.charba.client.defaults.IsDefaultScaledOptions;
import org.pepstock.charba.client.dom.BaseNativeEvent;
import org.pepstock.charba.client.dom.DOMBuilder;
import org.pepstock.charba.client.dom.enums.CursorType;
import org.pepstock.charba.client.enums.Event;
import org.pepstock.charba.client.enums.IndexAxis;
import org.pepstock.charba.client.events.DatasetRangeClearSelectionEvent;
import org.pepstock.charba.client.events.HandlerRegistration;
import org.pepstock.charba.client.events.LegendClickEvent;
import org.pepstock.charba.client.impl.callbacks.AtLeastOneDatasetHandler;
import org.pepstock.charba.client.impl.plugins.DatasetsItemsSelectorOptionsFactory.DatasetsItemsSelectorDefaultsOptionsFactory;
import org.pepstock.charba.client.items.PluginEventArgument;
import org.pepstock.charba.client.plugins.AbstractPlugin;
import org.pepstock.charba.client.resources.ResourceName;
import org.pepstock.charba.client.utils.Utilities;

/**
 * Enables the datasets items selection directly in the the canvas.<br>
 * It works only for line and bar chart instances and if ZoomPlugin is disable.<br>
 * It will add mouser listeners to canvas.<br>
 * Tooltips will be disable to avoid events conflicts.<br>
 * Overrides also the events which can be caught (only click and touchstart).<br>
 * It activates also the legend click handler to avoid that all datasets will be hidden.
 * 
 * @author Andrea "Stock" Stocchero
 * @see AtLeastOneDatasetHandler
 */
public final class DatasetsItemsSelector extends AbstractPlugin {

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
	// It can not use zoom plugin id
	// to avoid to load zoom and hammer JS if not needed
	// it must always aligned with value in the zoom plugin model
	private static final String ZOOM_PLUIGIN_ID = ResourceName.ZOOM_PLUGIN.value();
	// map to maintain the selectors handler for every chart
	private final Map<String, SelectionHandler> pluginSelectionHandlers = new HashMap<>();
	// set to maintain the status if legend click handler, if already added or not
	private final Map<String, HandlerRegistration> pluginEventsRegistrationHandlers = new HashMap<>();
	// click legend handler to avoid to remove all datasets
	private final AtLeastOneDatasetHandler pluginLegendClickHandler = new AtLeastOneDatasetHandler();
	// maps with the enablement if the plugin by chart instances
	private final Map<String, Boolean> pluginChartEnablement = new HashMap<>();

	/**
	 * To avoid any instantiation
	 */
	private DatasetsItemsSelector() {
		super(ID);
	}

	/**
	 * Returns the singleton instance of plugin.
	 * 
	 * @return the singleton instance of plugin
	 */
	public static DatasetsItemsSelector get() {
		return INSTANCE;
	}

	/**
	 * Returns the padding height used by clear selection element if enabled.<br>
	 * This is very helpful when you have added padding for your purposes and you need to know the amount of space that the element allocated.
	 * 
	 * @param chart chart instance
	 * @return the padding height used by clear selection element or <b>{@link ClearSelection#DEFAULT_VALUE}</b> if disabled
	 */
	public double getPadding(IsChart chart) {
		// checks if chart is consistent and there is a handler
		if (IsChart.isValid(chart) && pluginSelectionHandlers.containsKey(chart.getId())) {
			// gets selection handler
			SelectionHandler handler = pluginSelectionHandlers.get(chart.getId());
			// option instance
			DatasetsItemsSelectorOptions pOptions = handler.getOptions();
			// gets clear selection configuration
			ClearSelection clearSelection = pOptions.getClearSelection();
			// checks if is enabled
			if (clearSelection.isDisplay()) {
				// returns the used padding
				return clearSelection.getLayoutPadding();
			}
		}
		// by defaults, returns 0
		return ClearSelection.DEFAULT_VALUE;
	}

	/**
	 * Clears the selection on the chart. With this method, it don't fire any clear event if not selected in the plugin options.
	 * 
	 * @param chart chart instance to clear the selection
	 */
	public void clearSelection(IsChart chart) {
		// flag with default to false
		boolean fireEvent = false;
		// checks chart is consistent and for handler
		if (IsChart.isValid(chart)) {
			// checks in the options if fire event has been set
			fireEvent = chart.isEventHandled(DatasetRangeClearSelectionEvent.TYPE);
		}
		// invoke reset using fire event of options or false by default
		clearSelection(chart, fireEvent);
	}

	/**
	 * Clears the selection on the chart and set if an event should fire on clear action.
	 * 
	 * @param chart chart instance to clear the selection
	 * @param fireEvent if <code>true</code> an event is fired otherwise not.
	 */
	public void clearSelection(IsChart chart, boolean fireEvent) {
		// checks if chart is consistent
		if (!IsChart.isValid(chart)) {
			// if not exit
			return;
		}
		// flag to know if the chart must be updated
		boolean mustBeUpdated = false;
		// checks if we have already an handler
		if (pluginSelectionHandlers.containsKey(chart.getId())) {
			// gets selection handler
			SelectionHandler handler = pluginSelectionHandlers.get(chart.getId());
			// clear the selection
			handler.removeClearSelection();
			// checks if the selection was done
			mustBeUpdated = !SelectionStatus.READY.equals(handler.getStatus());
		}
		// destroy the current configuration
		onDestroy(chart);
		// recreates the selections handler
		onConfigure(chart);
		// checks if it must fire the event
		if (fireEvent) {
			// fires the reset event
			chart.fireEvent(new DatasetRangeClearSelectionEvent(DOMBuilder.get().createChangeEvent()));
		}
		// updates the chart only if the selection was done
		if (mustBeUpdated) {
			// refresh the chart
			chart.update();
		}
	}

	/**
	 * Sets a flag to skip to send event after refresh. This is helpful for drill down implementation.
	 * 
	 * @param chart chart instance to apply the fire events skipping.
	 */
	public void skipNextRefreshFireEvent(IsChart chart) {
		// checks is chart is consistent
		// checks if the plugin has been invoked for LINE or BAR charts
		// checks if we have already an handler
		if (mustBeActivated(chart) && pluginSelectionHandlers.containsKey(chart.getId())) {
			// gets selection handler
			SelectionHandler handler = pluginSelectionHandlers.get(chart.getId());
			// sets the flag to skip next event after refresh
			handler.setSkipNextFireEvent(true);
		}
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
			// overrides the events configuration setting only the following
			chart.getOptions().setEvents(Event.CLICK, Event.TOUCHSTART);
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
			// adds checks if there is any dataset selection handler in the option
			// if yes exception
			if (chart.getOptions().hasDatasetSelectionHandlers()) {
				// throw exception
				throw new IllegalArgumentException("Unable to activate plugin because a dataset selection handler has been defined");
			} else if (pluginSelectionHandlers.containsKey(chart.getId())) {
				// sets cursor wait because the chart is drawing and not selectable
				chart.getCanvas().getStyle().setCursorType(CursorType.WAIT);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onEndDrawing(org.pepstock.charba.client.IsChart)
	 */
	@Override
	public void onEndDrawing(IsChart chart) {
		// checks if chart is consistent and the plugin has been invoked for LINE or BAR charts
		if (mustBeActivated(chart) && pluginSelectionHandlers.containsKey(chart.getId())) {
			// gets selection handler
			SelectionHandler handler = pluginSelectionHandlers.get(chart.getId());
			// calculates the coordinates of clear selection element
			handler.calculateClearSelectionPositions();
			// checks if the draw if at the end of animation
			// and if the selection is not already started
			// the selection is managed
			manageSelection(chart, handler);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onDestroy(org.pepstock.charba.client.IsChart)
	 */
	@Override
	public void onDestroy(IsChart chart) {
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
			if (options.getPlugins().hasOptions(ID)) {
				pOptions = options.getPlugins().getOptions(ID, FACTORY);
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
			// clear selection
			if (pluginSelectionHandlers.containsKey(chart.getId())) {
				// gets selection handler
				SelectionHandler handler = pluginSelectionHandlers.get(chart.getId());
				// clear the selection
				handler.removeClearSelection();
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
			// get clear selection element
			ClearSelection clearSelection = pOptions.getClearSelection();
			// checks if is enabled
			if (clearSelection.isDisplay()) {
				// calculates if the events coordinates are hover of clear selection element
				boolean isX = event.getLayerX() >= clearSelection.getX() && event.getLayerX() <= (clearSelection.getX() + clearSelection.getWidth());
				boolean isY = event.getLayerY() >= clearSelection.getY() && event.getLayerY() <= (clearSelection.getY() + clearSelection.getHeight());
				// checks if hover
				if (isX && isY) {
					// invokes the clear selection
					clearSelection(chart);
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
		// gets the image from canvas
		String dataUrl = chart.getCanvas().toDataURL();
		// checks if chart is changed
		if (handler.isChartChanged(dataUrl)) {
			// this is necessary to apply every time the handler
			// will draw directly in the canvas
			// stores image setting size
			handler.setSnapshot(Utilities.toImageElement(dataUrl, chart.getCanvas().getOffsetWidth(), chart.getCanvas().getOffsetHeight()));
		}
		// if the selections is already present
		// it refreshes all the calculation of existing selection
		if (handler.getStatus().equals(SelectionStatus.SELECTED)) {
			handler.refresh();
		}
		// the drawing of chart is completed and set the default cursor
		// removing the "wait" one.
		chart.getCanvas().getStyle().setCursorType(CursorType.DEFAULT);
	}

}