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
package org.pepstock.charba.client.jsinterop.impl.plugins;

import java.util.List;

import org.pepstock.charba.client.ChartType;
import org.pepstock.charba.client.enums.Event;
import org.pepstock.charba.client.jsinterop.AbstractChart;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.jsinterop.items.DatasetItem;
import org.pepstock.charba.client.jsinterop.items.DatasetMetaItem;
import org.pepstock.charba.client.jsinterop.plugins.AbstractPlugin;
import org.pepstock.charba.client.jsinterop.plugins.InvalidPluginIdException;

import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Image;

/**
 * Enables the datasets items selection directly into the canvas.<br>
 * It works only for line and bar chart instances.<br>
 * It will add mouser listeners to canvas.<br>
 * Tooltips will be disable to avoid events conflicts.<br>
 * Overrides also the events which can be caught (only click and touchstart).
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 */
public final class DatasetsItemsSelector extends AbstractPlugin {
	
	// handler of selection on canvas
	private SelectionHandler handler = null;
	// event handler registration
	private HandlerRegistration mouseDown = null;
	// event handler registration
	private HandlerRegistration mouseUp = null;
	// event handler registration
	private HandlerRegistration mouseMove = null;
	// previous chart area
	private String previousChartAreaAsString = null;
	// previous datasets
	private List<String> previousDatasetsAsString = null;
	
	/**
	 * Plugin ID 
	 */
	public static final String ID = "datasetsitemsselector";
	// factory to read options
	private final OptionsFactory factory = new OptionsFactory();
	
	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.Plugin#getId()
	 */
	@Override
	public String getId() {
		return ID;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.plugins.AbstractPlugin#onConfigure(org.pepstock.charba.client.AbstractChart)
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

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.plugins.AbstractPlugin#onAfterInit(org.pepstock.charba.client.AbstractChart, com.google.gwt.core.client.JavaScriptObject)
	 */
	@Override
	public void onAfterInit(AbstractChart<?, ?> chart) {
		// checks if the plugin has been invoked for LINE or BAR charts
		if (chart.getType().equals(ChartType.line) || chart.getType().equals(ChartType.bar)) {
			DatasetsItemsSelectorOptions pOptions = null;
			try {
				// creates the plugin options using the java script object
				// passing also the default color set at constructor.
				if (chart.getOptions().getPlugins().hasOptions(ID)) {
					pOptions = chart.getOptions().getPlugins().getOptions(ID, factory);
				} else {
					pOptions = new DatasetsItemsSelectorOptions();
				}
			} catch (InvalidPluginIdException e) {
				// ignore message
				// and use the default
				pOptions = new DatasetsItemsSelectorOptions();
			}

			// creates the handler of selection
			// by chart instance and the options stored into options (if exists).
			handler = new SelectionHandler(chart, pOptions);
			// removes the default mouse down listener
			chart.removeCanvasPreventDefault();
			// adds all mouse listeners to canvas
			mouseDown = chart.getCanvas().addMouseDownHandler(handler);
			mouseUp = chart.getCanvas().addMouseUpHandler(handler);		
			mouseMove = chart.getCanvas().addMouseMoveHandler(handler);
		}
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.plugins.AbstractPlugin#onAfterDraw(org.pepstock.charba.client.AbstractChart, double, com.google.gwt.core.client.JavaScriptObject)
	 */
	@Override
	public void onAfterDraw(AbstractChart<?, ?> chart, double easing) {
		// checks if the plugin has been invoked for LINE or BAR charts
		if (chart.getType().equals(ChartType.line) || chart.getType().equals(ChartType.bar)) {
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
				for (int i=0 ; i < datasetsCount ; i++ ) {
					// checks if dataset is visible and 
					// it didn't start count the dataset items (first cycle)
					if (chart.isDatasetVisible(i) && itemsCount == 0) {
						// gets dataset meta data 
						DatasetMetaItem items = chart.getDatasetMeta(i);
						if (chart.getType().equals(items.getType())){
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
				// checks if chart is changed
				if (isChartChanged(chart)) {
					// gets the image from canvas
					// this is necessary to apply every time the handler
					// will draw directly into canvas
					Image img = new Image(chart.getCanvas().toDataUrl());
					handler.setSnapshot(ImageElement.as(img.getElement()));
				}
				// if the selections is already present
				// it refreshes all the calculation of existing selection
				if (handler.getStatus().equals(SelectionStatus.selected) && itemsCount > 0) {
					handler.refresh();
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.plugins.AbstractPlugin#onDestroy(org.pepstock.charba.client.AbstractChart, com.google.gwt.core.client.JavaScriptObject)
	 */
	@Override
	public void onDestroy(AbstractChart<?, ?> chart) {
		// checks if the plugin has been invoked for LINE or BAR charts
		if (chart.getType().equals(ChartType.line) || chart.getType().equals(ChartType.bar)) {
			// at chart destroy phase, all handler will be removed form canvas
			// removes mouse handler if consistent
			if (mouseDown != null) { 
				mouseDown.removeHandler();
			}
			// removes mouse handler if consistent
			if (mouseUp != null) { 
				mouseUp.removeHandler();
			}
			// removes mouse handler if consistent
			if (mouseMove != null) { 
				mouseMove.removeHandler();
			}
		}
	}
	
	/**
	 * Checks if the chart is changed.<br>
	 * It checks:<br>
	 * <ul>
	 * <li> the dimension of chart
	 * <li> the number of datasets
	 * <li> the data of each dataset
	 * </ul>
	 * 
	 * @param chart chart instance
	 * @return <code>true</code> if chart is changed, otherwise <code>false</code>.
	 */
	private boolean isChartChanged(AbstractChart<?, ?> chart) {
		// gets the chart area in json format
		String chartAreaAsString = chart.getNode().getChartArea().toString();
		// gets the list of datasets data as list of JSON string
		List<String> datasetsAsString = chart.getData().getDatasetsAsStrings();
		// if the fields are null, this is the first call and draw of chart
		// because chart is changed
		if (previousDatasetsAsString == null && previousChartAreaAsString == null) {
			// saves the current datasets and dimensions of chart
			previousDatasetsAsString = datasetsAsString;
			previousChartAreaAsString = chartAreaAsString;
			return true;
		}
		// checks if dimension of chart is changed
		if (!chartAreaAsString.equalsIgnoreCase(previousChartAreaAsString)) {
			// saves the current datasets and dimensions of chart
			previousDatasetsAsString = datasetsAsString;
			previousChartAreaAsString = chartAreaAsString;
			return true;
		}
		// checks if teh amount of datasets remained the same
		if (previousDatasetsAsString.size() != datasetsAsString.size()) {
			// saves the current datasets and dimensions of chart
			previousDatasetsAsString = datasetsAsString;
			previousChartAreaAsString = chartAreaAsString;
			return true;
		}
		// checks if all data of all datasets remained the same
		for (int i=0; i<previousDatasetsAsString.size(); i++) {
			// gets the datasets data as string
			String datasetAsString = previousDatasetsAsString.get(i);
			// checks if changed
			if (!datasetAsString.equalsIgnoreCase(datasetsAsString.get(i))) {
				// saves the current datasets and dimensions of chart
				previousDatasetsAsString = datasetsAsString;
				previousChartAreaAsString = chartAreaAsString;
				return true;
			}
		}
		// if here the chart is NOT changed
		return false;
	}

	/**
	 * Factory to get the options from chart ones.
	 * 
	 * @author Andrea "Stock" Stocchero
	 * @since 2.0
	 */
	private static class OptionsFactory implements NativeObjectContainerFactory<DatasetsItemsSelectorOptions>{

		/* (non-Javadoc)
		 * @see org.pepstock.charba.client.jsinterop.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.jsinterop.commons.NativeObject)
		 */
		@Override
		public DatasetsItemsSelectorOptions create(NativeObject nativeObject) {
			return new DatasetsItemsSelectorOptions(nativeObject);
		}
		
	}
	
}
