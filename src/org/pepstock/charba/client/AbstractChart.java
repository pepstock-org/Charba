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
package org.pepstock.charba.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.configuration.ConfigurationOptions;
import org.pepstock.charba.client.data.Data;
import org.pepstock.charba.client.data.Dataset;
import org.pepstock.charba.client.events.ChartNativeEvent;
import org.pepstock.charba.client.items.DatasetItem;
import org.pepstock.charba.client.items.DatasetItem.DatasetItemFactory;
import org.pepstock.charba.client.items.DatasetMetaItem;
import org.pepstock.charba.client.items.UndefinedValues;
import org.pepstock.charba.client.plugins.Plugins;
import org.pepstock.charba.client.resources.ResourcesType;
import org.pepstock.charba.client.utils.JSON;
import org.pepstock.charba.client.utils.Utilities;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * Base class of all charts.<br>
 * It contains Chart.js initialization.
 * 
 * @author Andrea "Stock" Stocchero
 *
 * @param <D> Dataset type for the specific chart
 */
public abstract class AbstractChart<D extends Dataset> extends SimplePanel implements IsChart {

	// message to show when the browser can't support canvas
	private static final String CANVAS_NOT_SUPPORTED_MESSAGE = "Ops... Canvas element is not supported...";
	// PCT standard for width
	private static final double DEFAULT_PCT_WIDTH = 90D;
	// reference to Chart.js chart instance
	private Chart chart = null;

	// chart ID using GWT unique id
	private final String id = Document.get().createUniqueId();

	// canvas prevent default handler
	private final HandlerRegistration preventDisplayHandler;
	// list of all handle registration when
	// an handler (for events) has been added to chart
	// needed for clean up when chart will be destroy
	private final List<HandlerRegistration> handlerRegistrations = new ArrayList<>();

	// canvas where Chart.js draws the chart
	private final Canvas canvas;
	// CHart configuration object
	private final Configuration configuration = new Configuration();
	// Data element of configuration
	private final Data data = new Data();
	// plugins of this chart
	private final Plugins plugins;
	// flag if must be draw on attach
	private boolean drawOnAttach = true;
	// flag if must be destroy on detach
	private boolean destroyOnDetach = true;
	// gets if Canvas is supported
	private final boolean isCanvasSupported = Canvas.isSupported();
	// merged options of defaults
	private final ChartOptions options;
	// instance of dataset items factory.
	private final DatasetItemFactory datasetItemFactory = new DatasetItemFactory();
	// cursor defined when chart is created
	private final Cursor initialCursor;

	/**
	 * Initializes simple panel and canvas which are used by CHART.JS.<br>
	 * It sets also some default behaviors (width in percentage) for resizing
	 */
	public AbstractChart() {
		// creates DIV
		getElement().setId(id);
		// sets relative position
		getElement().getStyle().setPosition(Position.RELATIVE);
		// sets default width values
		getElement().getStyle().setWidth(DEFAULT_PCT_WIDTH, Unit.PCT);
		getElement().getStyle().setHeight(100, Unit.PCT);
		// checks if canvas is supported
		if (isCanvasSupported) {
			// creates a canvas
			canvas = Canvas.createIfSupported();
			// adds to panel
			add(canvas);
			// adds the listener to disable canvas selection
			// removes the default behavior
			preventDisplayHandler = canvas.addMouseDownHandler(MouseDownEvent::preventDefault);
		} else {
			// creates a header element
			HeadingElement h = Document.get().createHElement(3);
			// to show the error message
			// because canvas is not supported
			h.setInnerText(CANVAS_NOT_SUPPORTED_MESSAGE);
			getElement().appendChild(h);
			// resets canvas
			canvas = null;
			preventDisplayHandler = null;
		}
		// Inject Chart.js if not already loaded
		Injector.ensureInjected(ResourcesType.getClientBundle().chartJs());
		// creates plugins container
		plugins = new Plugins();
		// creates defaults options for this chart type
		options = createChartOptions();
		// stores initial cursor
		initialCursor = Utilities.getCursorOfChart(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.ui.Widget#createHandlerManager()
	 */
	@Override
	protected final HandlerManager createHandlerManager() {
		return new ChartHandlerManager(this);
	}

	/**
	 * Creates a new dataset related to chart type.
	 * 
	 * @return a new dataset related to chart type.
	 */
	public abstract D newDataset();

	/**
	 * Returns the ID of chart.<br>
	 * It could be considered as chart unique ID.
	 * 
	 * @return the ID of chart
	 */
	@Override
	public final String getId() {
		return id;
	}

	/**
	 * Returns the canvas element used to draw the chart.
	 * 
	 * @return the canvas
	 */
	@Override
	public final Canvas getCanvas() {
		// checks if canvas is initialized
		if (isCanvasSupported) {
			// returns it
			return canvas;
		} else {
			// otherwise throws an exxeption
			throw new NullPointerException(CANVAS_NOT_SUPPORTED_MESSAGE);
		}
	}

	/**
	 * Remove the registration of prevent default mouse listener from canvas.<br>
	 * This is necessary when you will add your mouse down listener.
	 */
	@Override
	public final void removeCanvasPreventDefault() {
		// checks if consistent
		if (preventDisplayHandler != null) {
			// cleans up the handler for mouse listener
			preventDisplayHandler.removeHandler();
		}
	}

	/**
	 * Returns the initial cursor of the chart.
	 * 
	 * @return the initial cursor of the chart.
	 */
	@Override
	public final Cursor getInitialCursor() {
		return initialCursor;
	}

	/**
	 * Returns <code>true</code> if CHART.JS chart has been initialized, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if CHART.JS chart has been initialized, otherwise <code>false</code>.
	 */
	@Override
	public boolean isInitialized() {
		return chart != null;
	}

	/**
	 * Returns the chart node with runtime data.
	 * 
	 * @return the chart node.
	 */
	@Override
	public final ChartNode getNode() {
		return new ChartNode(chart);
	}

	/**
	 * Returns the data object with all passed datasets.
	 * 
	 * @return the data configuration object
	 */
	@Override
	public final Data getData() {
		return data;
	}

	/**
	 * Returns the plugins element to manage inline plugins.
	 * 
	 * @return the plugins configuration object
	 */
	@Override
	public final Plugins getPlugins() {
		return plugins;
	}

	/**
	 * Creates the chart options based on type of chart.<br>
	 * It can be override when a controller is implemented.
	 * 
	 * @return the chart options based on type of chart.
	 */
	protected ChartOptions createChartOptions() {
		return Defaults.get().getChartOptions(getType());
	}

	/**
	 * Returns the default options created based on chart type.
	 * 
	 * @return the default options of the chart
	 */
	@Override
	public final ChartOptions getDefaultChartOptions() {
		return options;
	}

	/**
	 * Returns <code>true</code> if the chart is configured to be drawn on the attach of DIV element, otherwise
	 * <code>false</code>.
	 * 
	 * @return the drawOnAttach <code>true</code> if the chart is configured to be drawn on the attach of DIV element, otherwise
	 *         <code>false</code>. Default is <code>true</code>.
	 */
	@Override
	public final boolean isDrawOnAttach() {
		return drawOnAttach;
	}

	/**
	 * Sets <code>true</code> if the chart is configured to be draw on the attach of DIV element, otherwise <code>false</code>.
	 * 
	 * @param drawOnAttach the drawOnAttach to set
	 */
	@Override
	public final void setDrawOnAttach(boolean drawOnAttach) {
		this.drawOnAttach = drawOnAttach;
	}

	/**
	 * Returns <code>true</code> if the chart is configured to be destroyed on the attach of DIV element, otherwise
	 * <code>false</code>.
	 * 
	 * @return the destroyOnDetach <code>true</code> if the chart is configured to be destroyed on the attach of DIV element,
	 *         otherwise <code>false</code>. Default is <code>true</code>.
	 */
	@Override
	public final boolean isDestroyOnDetach() {
		return destroyOnDetach;
	}

	/**
	 * Sets <code>true</code> if the chart is configured to be destroyed on the attach of DIV element, otherwise
	 * <code>false</code>.
	 * 
	 * @param destroyOnDetach the destroyOnDetach to set
	 */
	@Override
	public final void setDestroyOnDetach(boolean destroyOnDetach) {
		this.destroyOnDetach = destroyOnDetach;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.ui.Widget#onAttach()
	 */
	@Override
	protected void onAttach() {
		// attaches the widget
		super.onAttach();
		// if is not to be drawn on attach, doesn't draw
		if (isDrawOnAttach()) {
			draw();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.ui.Widget#onDetach()
	 */
	@Override
	protected void onDetach() {
		// detaches the widget
		super.onDetach();
		// if is not to be destroyed on detach, doesn't destroy
		if (isDestroyOnDetach()) {
			// then destroy
			destroy();
		}
	}

	/**
	 * Called by handler manager to store every handler registration in order to remove them automatically when the chart will
	 * be destroy.
	 * 
	 * @param registration new handler registration created
	 */
	void addHandlerRegistration(HandlerRegistration registration) {
		handlerRegistrations.add(registration);
	}

	/**
	 * Use this to destroy any chart instances that are created. This will clean up any references stored to the chart object
	 * within Chart.js, along with any associated event listeners attached by Chart.js.
	 */
	@Override
	public final void destroy() {
		// notify before destroy
		Charts.fireBeforeDestory(this);
		// checks if chart is created
		if (chart != null) {
			// then destroy
			chart.destroy();
		}
		// remove handler of mouse event handler
		removeCanvasPreventDefault();
		// removes all handlers created to add
		// events handler to chart
		for (HandlerRegistration registration : handlerRegistrations) {
			registration.removeHandler();
		}
		// clears the cache of handler registrations.
		handlerRegistrations.clear();
		// removes chart instance from collection
		Charts.remove(getId());
	}

	/**
	 * Use this to stop any current animation loop. This will pause the chart during any current animation frame. Call
	 * <code>.render()</code> to re-animate.
	 */
	@Override
	public final void stop() {
		// checks if chart is created
		if (chart != null) {
			// then stop
			chart.stop();
		}
	}

	/**
	 * Will clear the chart canvas. Used extensively internally between animation frames.<br>
	 * Overrides the <code>clear</code> method of GWT <code>Panel</code>, changing completely the behavior of GWT
	 * <code>Panel</code> one.
	 */
	@Override
	public final void clear() {
		// checks if chart is created
		if (chart != null) {
			// then clear
			chart.clear();
		}
	}

	/**
	 * Reset the chart to it's state before the initial animation. A new animation can then be triggered using update.
	 */
	@Override
	public final void reset() {
		// checks if chart is created
		if (chart != null) {
			// then reset
			chart.reset();
		}
	}

	/**
	 * Returns a base 64 encoded string of the chart in it's current state.
	 * 
	 * @return base 64 image or {@link UndefinedValues#STRING} if chart is not initialized.
	 */
	@Override
	public final String toBase64Image() {
		// checks if chart is created
		if (chart != null) {
			// then get the image
			return chart.toBase64Image();
		}
		// default
		return UndefinedValues.STRING;
	}

	/**
	 * Returns an HTML string of a legend for that chart. The legend is generated from the legendCallback in the options.
	 * 
	 * @return the HTML legend or {@link UndefinedValues#STRING} if chart is not initialized.
	 */
	@Override
	public final String generateLegend() {
		// checks if chart is created
		if (chart != null) {
			// returns legend
			return chart.generateLegend();
		}
		// default
		return UndefinedValues.STRING;
	}

	/**
	 * Use this to manually resize the canvas element. This is run each time the canvas container is resized, but can be called
	 * this method manually if you change the size of the canvas nodes container element.
	 */
	@Override
	public final void resize() {
		// checks if chart is created
		if (chart != null) {
			// resize!
			chart.resize();
		}
	}

	/**
	 * Triggers an update of the chart. This can be safely called after updating the data object. This will update all scales,
	 * legends, and then re-render the chart.
	 */
	@Override
	public final void update() {
		update(null);
	}

	/**
	 * Triggers an update of the chart. This can be safely called after updating the data object. This will update all scales,
	 * legends, and then re-render the chart. A config object can be provided with additional configuration for the update
	 * process. This is useful when update is manually called inside an event handler and some different animation is desired.
	 * 
	 * @param config a config object can be provided with additional configuration for the update process
	 */
	@Override
	public final void update(UpdateConfiguration config) {
		// checks if chart is created
		if (chart != null) {
			// if config is not passed..
			if (config == null) {
				// .. calls the update
				chart.update();
			} else {
				// .. otherwise calls the update with config
				chart.update(config.getObject());
			}
		}
	}

	/**
	 * Triggers a redraw of all chart elements. Note, this does not update elements for new data. Use <code>.update()</code> in
	 * that case.
	 */
	@Override
	public final void render() {
		render(null);
	}

	/**
	 * Triggers a redraw of all chart elements. Note, this does not update elements for new data. Use <code>.update()</code> in
	 * that case. A config object can be provided with additional configuration for the render process. This is useful when
	 * update is manually called inside an event handler and some different animation is desired.
	 * 
	 * @param config a config object can be provided with additional configuration for the render process
	 */
	@Override
	public final void render(UpdateConfiguration config) {
		// checks if chart is created
		if (chart != null) {
			// if config is not passed..
			if (config == null) {
				// .. calls the render
				chart.render();
			} else {
				// .. otherwise calls the render with config
				chart.render(config.getObject());
			}
		}
	}

	/**
	 * Looks for the dataset that matches the current index and returns that metadata.
	 * 
	 * @param index dataset index
	 * @return dataset meta data item.
	 */
	@Override
	public final DatasetMetaItem getDatasetMeta(int index) {
		// checks consistency of chart and datasets
		if (chart != null && data.getDatasets() != null && !data.getDatasets().isEmpty() && index < data.getDatasets().size() && index >= 0) {
			// returns the array
			return new DatasetMetaItem(chart.getDatasetMeta(index));
		}
		// returns null
		return null;
	}

	/**
	 * Looks for the dataset that matches the event and returns that metadata.
	 * 
	 * @param event event of chart.
	 * @return dataset meta data item.
	 */
	@Override
	public DatasetMetaItem getDatasetAtEvent(ChartNativeEvent event) {
		// checks consistency of chart and event
		if (chart != null && event != null) {
			// gets dataset and
			// returns the array
			return new DatasetMetaItem(chart.getDatasetAtEvent(event));
		}
		// if here, chart or event not valid than returns null
		return null;
	}

	/**
	 * Looks for the dataset if it's visible or not, selected by index.
	 * 
	 * @param index dataset index
	 * @return <code>true</code> if dataset is visible otherwise <code>false</code>.
	 */
	@Override
	public boolean isDatasetVisible(int index) {
		// checks consistency of chart and datasets
		if (chart != null && data.getDatasets() != null && !data.getDatasets().isEmpty() && index < data.getDatasets().size() && index >= 0) {
			// gets if dataset is visible or not
			return chart.isDatasetVisible(index);
		}
		// returns false
		return false;
	}

	/**
	 * Returns the amount of datasets which are visible
	 * 
	 * @return the amount of datasets which are visible. If chart is not initialized, return {@link UndefinedValues#INTEGER}.
	 */
	@Override
	public int getVisibleDatasetCount() {
		// checks consistency of chart and datasets
		if (chart != null) {
			// gets if dataset is visible or not
			return chart.getVisibleDatasetCount();
		}
		// returns undefined
		return UndefinedValues.INTEGER;
	}

	/**
	 * Calling on your chart instance passing an argument of an event, will return the single element at the event position.<br>
	 * If there are multiple items within range, only the first is returned.
	 * 
	 * @param event event of chart.
	 * @return single element at the event position or null.
	 */
	@Override
	public final DatasetItem getElementAtEvent(ChartNativeEvent event) {
		// checks consistency of chart and event
		if (chart != null && event != null) {
			// gets element
			ArrayObject result = chart.getElementAtEvent(event);
			if (result != null && !result.isEmpty()) {
				return new DatasetItem(result.get(0));
			}
		}
		// if here, inconsistent result
		return null;
	}

	/**
	 * Looks for the element under the event point, then returns all elements at the same data index.<br>
	 * Calling it on your chart instance passing an argument of an event, will return the point elements that are at that the
	 * same position of that event.
	 * 
	 * @param event event of chart.
	 * @return all elements at the same data index or an empty list.
	 */
	@Override
	public final List<DatasetItem> getElementsAtEvent(ChartNativeEvent event) {
		// checks consistency of chart and event
		if (chart != null && event != null) {
			// gets elements
			ArrayObject array = chart.getElementsAtEvent(event);
			// returns the array
			return ArrayListHelper.unmodifiableList(array, datasetItemFactory);
		}
		// if here, chart and event npot consistent then returns an empty list
		return Collections.emptyList();
	}

	/**
	 * Draws the chart
	 */
	@Override
	public final void draw() {
		// checks if canvas is supported
		if (isCanvasSupported) {
			// calls plugins for onConfigure method
			Defaults.get().getPlugins().onChartConfigure(configuration, this);
			plugins.onChartConfigure(configuration, this);
			// gets options
			ConfigurationOptions internalOptions = getOptions();
			// gets data
			Data internalData = getData();
			// sets all items to configuration item
			configuration.setType(getType());
			configuration.setOptions(this, internalOptions);
			configuration.setData(this, internalData);
			// sets plugins
			configuration.setPlugins(this, plugins);
			// destroy chart if chart is already instantiated
			// checks if chart is created
			if (chart != null) {
				// then destroy
				chart.destroy();
			}
			// stores the chart instance into collection
			Charts.add(this);
			// draws chart with configuration
			chart = new Chart(canvas.getContext2d(), configuration);
			// notify after destroy
			Charts.fireAfterInit(this);
		}
	}

	/**
	 * Returns the string JSON representation of the object.
	 * 
	 * @return the string JSON representation of the object.
	 */
	@Override
	public final String toJSON() {
		return JSON.stringifyWithReplacer(configuration, 3);
	}

}