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
import org.pepstock.charba.client.items.DatasetItem;
import org.pepstock.charba.client.items.DatasetMetaItem;
import org.pepstock.charba.client.plugins.AbstractPlugin;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.user.client.ui.Image;

/**
 * Enables the datasets items selection directly into the canvas.<br>
 * It works only for line and bar chart instances.<br>
 * It will add mouser listeners to canvas.<br>
 * Tooltips will be disable to avoid events conflicts.<br>
 * Overrides also the events which can be caught (only click and touchstart).
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DatasetsItemsSelector extends AbstractPlugin {

	/**
	 * Plugin ID {@value ID}
	 */
	public static final String ID = "datasetsitemsselector";
	/**
	 * The factory to read options for plugin
	 */
	public static final DatasetsItemsSelectorOptionsFactory FACTORY = new DatasetsItemsSelectorOptionsFactory();
	// maps to maintain the selectors handler for every chart
	private static final Map<String, SelectionHandler> HANDLERS = new HashMap<>();

	/**
	 * Reset the selection on the chart. With this method, it don't fire any reset event.
	 * 
	 * @param chart chart instance to reset the selection
	 */
	public void reset(AbstractChart<?, ?> chart) {
		reset(chart, false);
	}

	/**
	 * Reset the selection on the chart and set if an event should fire on reset action.
	 * 
	 * @param chart chart instance to reset the selection
	 * @param fireEvent if <code>true</code> an event is fired otherwise not.
	 */
	public void reset(AbstractChart<?, ?> chart, boolean fireEvent) {
		// flag to know if the chart must be updated
		boolean mustBeUpodated = false;
		// checks if we have already an handler
		if (HANDLERS.containsKey(chart.getId())) {
			// gets selection handler
			SelectionHandler handler = HANDLERS.get(chart.getId());
			// checks if the selection was done
			mustBeUpodated = !SelectionStatus.ready.equals(handler.getStatus());
		}
		// destroy the current configuration
		onDestroy(chart);
		// recreates the selections handler
		onAfterInit(chart);
		// checks if it must fire the event
		if (fireEvent) {
			// fires the reset event
			chart.fireEvent(new DatasetRangeSelectionEvent(Document.get().createChangeEvent()));
		}
		// updates the chart only if the selection was done
		if (mustBeUpodated) {
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
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.plugins.AbstractPlugin#onAfterInit(org.pepstock.charba.client. AbstractChart)
	 */
	@Override
	public void onAfterInit(AbstractChart<?, ?> chart) {
		// checks if the plugin has been invoked for LINE or BAR charts
		if (chart.getType().equals(ChartType.line) || chart.getType().equals(ChartType.bar)) {
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
							// stores the amount of items
							handler.setDatasetsItemsCount(itemsCount);
						}
					}
				}
				// gets the image from canvas
				String dataUrl = chart.getCanvas().toDataUrl();
				// checks if chart is changed
				if (handler.isChartChanged(dataUrl)) {
					// this is necessary to apply every time the handler
					// will draw directly into canvas
					Image img = new Image(dataUrl);
					// fix dimension
					img.setPixelSize(chart.getCanvas().getOffsetWidth(), chart.getCanvas().getOffsetHeight());
					// stores image
					handler.setSnapshot(ImageElement.as(img.getElement()));
				}
				// if the selections is already present
				// it refreshes all the calculation of existing selection
				if (handler.getStatus().equals(SelectionStatus.selected) && itemsCount > 0) {
					handler.refresh();
				}
				// if no dataset 
				if (itemsCount == 0) {
					// reset selection
					reset(chart);
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
				HANDLERS.remove(chart.getId());
			}
		}
	}
}
