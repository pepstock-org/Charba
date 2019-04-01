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

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.ChartType;
import org.pepstock.charba.client.enums.Event;
import org.pepstock.charba.client.events.ChartNativeEvent;
import org.pepstock.charba.client.events.LegendClickEvent;
import org.pepstock.charba.client.impl.callbacks.AtLeastOneDatasetHandler;
import org.pepstock.charba.client.items.DatasetItem;
import org.pepstock.charba.client.items.DatasetMetaItem;
import org.pepstock.charba.client.plugins.AbstractPlugin;
import org.pepstock.charba.client.utils.Utilities;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.event.shared.HandlerRegistration;

/**
 * Enables the datasets items selection directly into the canvas.<br>
 * It works only for line and bar chart instances.<br>
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
	 * Plugin ID {@value ID}
	 */
	public static final String ID = "datasetsitemsselector";
	/**
	 * The factory to read options for plugin
	 */
	public static final DatasetsItemsSelectorOptionsFactory FACTORY = new DatasetsItemsSelectorOptionsFactory(ID);
	// map to maintain the selectors handler for every chart
	private static final Map<String, SelectionHandler> HANDLERS = new HashMap<>();
	// set to maintain the status if legend click handler, if already added or not
	private static final Map<String, HandlerRegistration> LEGEND_HANDLERS_STATUS = new HashMap<>();
	// click lgend handler to avoid to remove all datasets
	private final AtLeastOneDatasetHandler legendClickHandler = new AtLeastOneDatasetHandler();

	/**
	 * Returns the padding height used by clear selection element if enabled.<br>
	 * This is very helpful when you have added padding for your purposes and you need to know the amount of space that the
	 * element allocated.
	 * 
	 * @param chart chart instance
	 * @return the padding height used by clear selection element or <code>0</code> if disabled
	 */
	public double getPadding(AbstractChart<?, ?> chart) {
		// checks if there is a handler
		if (HANDLERS.containsKey(chart.getId())) {
			// gets selection handler
			SelectionHandler handler = HANDLERS.get(chart.getId());
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
	 * Clears the selection on the chart. With this method, it don't fire any clear event if not selected into plugin options.
	 * 
	 * @param chart chart instance to clear the selection
	 * @see DatasetsItemsSelectorOptions#isFireEventOnClearSelection()
	 * @see DatasetsItemsSelectorOptions#setFireEventOnClearSelection(boolean)
	 */
	public void clearSelection(AbstractChart<?, ?> chart) {
		// flag with default to false
		boolean fireEvent = false;
		// checks for handler
		if (HANDLERS.containsKey(chart.getId())) {
			// gets selection handler
			SelectionHandler handler = HANDLERS.get(chart.getId());
			// checks into options if fire event has been set
			fireEvent = handler.getOptions().isFireEventOnClearSelection();
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
	public void clearSelection(AbstractChart<?, ?> chart, boolean fireEvent) {
		// flag to know if the chart must be updated
		boolean mustBeUpdated = false;
		// checks if we have already an handler
		if (HANDLERS.containsKey(chart.getId())) {
			// gets selection handler
			SelectionHandler handler = HANDLERS.get(chart.getId());
			// clear the selection
			handler.removeClearSelection();
			// checks if the selection was done
			mustBeUpdated = !SelectionStatus.ready.equals(handler.getStatus());
		}
		// destroy the current configuration
		onDestroy(chart);
		// recreates the selections handler
		onConfigure(chart);
		// checks if it must fire the event
		if (fireEvent) {
			// fires the reset event
			chart.fireEvent(new DatasetRangeSelectionEvent(Document.get().createChangeEvent()));
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
	public void skipNextRefreshFireEvent(AbstractChart<?, ?> chart) {
		// checks if the plugin has been invoked for LINE or BAR charts
		if (chart.getType().equals(ChartType.line) || chart.getType().equals(ChartType.bar)) {
			// checks if we have already an handler
			if (HANDLERS.containsKey(chart.getId())) {
				// gets selection handler
				SelectionHandler handler = HANDLERS.get(chart.getId());
				// sets the flag to skip next event after refresh
				handler.setSkipNextFireEvent(true);
			}
		}
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
	public void onConfigure(AbstractChart<?, ?> chart) {
		// checks if the plugin has been invoked for LINE or BAR charts
		if (chart.getType().equals(ChartType.line) || chart.getType().equals(ChartType.bar)) {
			// overrides the tooltip configuration disabling it
			chart.getOptions().getTooltips().setEnabled(false);
			// overrides the events configuration setting only the following
			chart.getOptions().setEvents(Event.click, Event.touchstart);
			// checks if handler on legend to avoid to remove all datasets has been already added
			// and if legend is display
			if (!LEGEND_HANDLERS_STATUS.containsKey(chart.getId()) && chart.getOptions().getLegend().isDisplay()) {
				// if not, adds handler
				HandlerRegistration registratrion = chart.addHandler(legendClickHandler, LegendClickEvent.TYPE);
				// stores flag into map
				LEGEND_HANDLERS_STATUS.put(chart.getId(), registratrion);
			}
			// option instance
			DatasetsItemsSelectorOptions pOptions = null;
			// creates the plugin options using the java script object
			// passing also the default color set at constructor.
			if (chart.getOptions().getPlugins().hasOptions(ID)) {
				pOptions = chart.getOptions().getPlugins().getOptions(ID, FACTORY);
			} else {
				pOptions = new DatasetsItemsSelectorOptions();
			}
			// checks if chart has got already an handler
			if (HANDLERS.containsKey(chart.getId())) {
				// removes previous handler
				HANDLERS.remove(chart.getId());
			}
			// creates the handler of selection
			// by chart instance and the options stored into options (if exists).
			SelectionHandler handler = new SelectionHandler(chart, pOptions);
			// removes the default mouse down listener
			chart.removeCanvasPreventDefault();
			// adds all mouse listeners to canvas
			handler.setMouseDown(chart.getCanvas().addMouseDownHandler(handler));
			handler.setMouseUp(chart.getCanvas().addMouseUpHandler(handler));
			handler.setMouseMove(chart.getCanvas().addMouseMoveHandler(handler));
			// stores selection handler
			HANDLERS.put(chart.getId(), handler);
		}
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.plugins.AbstractPlugin#onBeforeUpdate(org.pepstock.charba.client.AbstractChart)
	 */
	@Override
	public boolean onBeforeUpdate(AbstractChart<?, ?> chart) {
		// checks if the plugin has been invoked for LINE or BAR charts
		if (chart.getType().equals(ChartType.line) || chart.getType().equals(ChartType.bar)) {
			// add checks if there is any dataset selection handler into option
			// if yes exception
			if (chart.getOptions().hasDatasetSelectionHandlers()) {
				// throw exception
				throw new IllegalArgumentException("Unable to activate plugin because a dataset selection handler has been defined.");
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
	public void onAfterDraw(AbstractChart<?, ?> chart, double easing) {
		// checks if the plugin has been invoked for LINE or BAR charts
		if (chart.getType().equals(ChartType.line) || chart.getType().equals(ChartType.bar)) {
			// sets cursor wait because the chart is drawing and not selectable
			chart.getCanvas().getElement().getStyle().setCursor(Cursor.WAIT);
			// gets selection handler
			SelectionHandler handler = HANDLERS.get(chart.getId());
			// calculates the coordinates of clear selection element
			handler.calculateClearSelectionPositions();
			// checks if the draw if at the end of animation
			// and if the selection is not already started
			if (easing == 1D) {
				// gets the width of canvas
				// here is already attached to the parent
				int limitRight = chart.getCanvas().getOffsetWidth();
				// datasets items count
				int itemsCount = 0;
				// gets the amount of datasets
				int datasetsCount = chart.getData().getDatasets().size();
				// scans all datasets
				for (int i = 0; i < datasetsCount; i++) {
					// checks if dataset is visible and
					// it didn't start count the dataset items (first cycle)
					if (chart.isDatasetVisible(i) && itemsCount == 0) {
						// gets dataset meta data
						DatasetMetaItem items = chart.getDatasetMeta(i);
						if (chart.getType().equals(items.getType())) {
							// scans all datasets items
							for (DatasetItem item : items.getDatasets()) {
								// if the chart is line
								// and X coordinate is less the width of canvas (item is inside of canvas)
								if (chart.getType().equals(ChartType.line) && item.getView().getX() <= limitRight) {
									itemsCount++;
								}
								// if the chart is line
								// and X coordinate is less the width of canvas
								// and the width of bar is less of width of canvas (item is inside of canvas)
								if (chart.getType().equals(ChartType.bar) && (item.getView().getX() <= limitRight || (item.getView().getX() + item.getView().getWidth()) <= limitRight)) {
									itemsCount++;
								}
							}
						}
					}
				}
				// stores the amount of items
				handler.setDatasetsItemsCount(itemsCount);
				// checks if there is the amount of datasets for selection
				if (handler.hasMinimumDatasetsItems()) {
					// gets the image from canvas
					String dataUrl = chart.getCanvas().toDataUrl();
					// checks if chart is changed
					if (handler.isChartChanged(dataUrl)) {
						// this is necessary to apply every time the handler
						// will draw directly into canvas
						// stores image setting size
						handler.setSnapshot(Utilities.toImageElement(dataUrl, chart.getCanvas().getOffsetWidth(), chart.getCanvas().getOffsetHeight()));
					}
					// if the selections is already present
					// it refreshes all the calculation of existing selection
					if (handler.getStatus().equals(SelectionStatus.selected)) {
						handler.refresh();
					}
				} else {
					// clears selection
					clearSelection(chart);
				}
				// the drawing of chart is completed and set the default cursor
				// removing the "wait" one.
				chart.getCanvas().getElement().getStyle().setCursor(Cursor.DEFAULT);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.plugins.AbstractPlugin#onDestroy(org.pepstock.charba.client.AbstractChart)
	 */
	@Override
	public void onDestroy(AbstractChart<?, ?> chart) {
		// checks if the plugin has been invoked for LINE or BAR charts
		if (chart.getType().equals(ChartType.line) || chart.getType().equals(ChartType.bar)) {
			// checks if we have already an handler
			if (HANDLERS.containsKey(chart.getId())) {
				// gets selection handler
				SelectionHandler handler = HANDLERS.get(chart.getId());
				// at chart destroy phase, all handler will be removed form canvas
				// removes mouse handler if consistent
				if (handler.getMouseDown() != null) {
					handler.getMouseDown().removeHandler();
				}
				// removes mouse handler if consistent
				if (handler.getMouseUp() != null) {
					handler.getMouseUp().removeHandler();
				}
				// removes mouse handler if consistent
				if (handler.getMouseMove() != null) {
					handler.getMouseMove().removeHandler();
				}
				// removes handler from map
				HANDLERS.remove(chart.getId());
			}
			// checks if we have already an legend handler
			if (LEGEND_HANDLERS_STATUS.containsKey(chart.getId())) {
				// cleans the legend click handler status
				HandlerRegistration registration = LEGEND_HANDLERS_STATUS.remove(chart.getId());
				// removes registration
				registration.removeHandler();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.plugins.AbstractPlugin#onBeforeEvent(org.pepstock.charba.client.AbstractChart,
	 * org.pepstock.charba.client.events.ChartNativeEvent)
	 */
	@Override
	public boolean onBeforeEvent(AbstractChart<?, ?> chart, ChartNativeEvent event) {
		// gets selection handler
		SelectionHandler handler = HANDLERS.get(chart.getId());
		// checks if it is a click event
		// ONLY click are caught
		if (Event.click.name().equalsIgnoreCase(event.getType())) {
			// option instance
			DatasetsItemsSelectorOptions pOptions = handler.getOptions();
			// get clear selection element
			ClearSelection clearSelection = pOptions.getClearSelection();
			// checks if is enabled
			if (clearSelection.isDisplay()) {
				// calculates if the events cooradintes are hover of clear selection element
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
		// This control has been added because a click event is always fired
		// by canvas when mouse up (of selection handler) is perfromed
		// but to avoid to refresh the chart every time
		// selection handler sets a flag to check this condition
		if (handler.isPreventClickEvent()) {
			// resets flag
			handler.resetPreventClickEvent();
			// and forces the event will be discarded.
			return false;
		}
		// if here, propagates the event to other listeners
		return true;
	}

}