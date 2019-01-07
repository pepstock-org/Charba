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

import org.pepstock.charba.client.callbacks.LegendCallback;
import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.data.Data;
import org.pepstock.charba.client.data.Dataset;
import org.pepstock.charba.client.events.ChartNativeEvent;
import org.pepstock.charba.client.items.ChartNode;
import org.pepstock.charba.client.items.DatasetItem;
import org.pepstock.charba.client.items.DatasetMetaItem;
import org.pepstock.charba.client.items.UndefinedValues;
import org.pepstock.charba.client.options.BaseOptions;
import org.pepstock.charba.client.plugins.Plugins;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * Base class of all charts.<br>
 * It contains Chart.js initialization.
 * 
 * @author Andrea "Stock" Stocchero
 *
 * @param <O> Options type for the specific chart
 * @param <D> Dataset type for the specific chart
 */
public abstract class AbstractChart<O extends BaseOptions, D extends Dataset> extends SimplePanel implements IsChart<O, D> {

	// message to show when the browser can't support canvas
	private static final String CANVAS_NOT_SUPPORTED_MESSAGE = "Ops... Canvas element is not supported...";
	// constant for WIDTH property
	private static final String WIDTH_PROPERTY = "width";
	// constant for HEIGHT property
	private static final String HEIGHT_PROPERTY = "height";
	// PCT standard for width
	private static final double DEFAULT_PCT_WIDTH = 90D;
	// reference to Chart.js chart instance
	private JavaScriptObject chart = null;

	// chart ID using GWT unique id
	private final String id = Document.get().createUniqueId();

	// canvas prevent default handler
	private final HandlerRegistration preventDisplayHandler;
	// canvas where Chart.js draws the chart
	final Canvas canvas;
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
	private final GlobalOptions options;

	/**
	 * Initializes HTMl elements (DIV and Canvas).<br>
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
			// creates a canvas and add to DIV
			// canvas = Document.get().createCanvasElement();
			canvas = Canvas.createIfSupported();
			add(canvas);
			// adds the listener to disable canvas selection
			preventDisplayHandler = canvas.addMouseDownHandler(new MouseDownHandler() {

				/*
				 * (non-Javadoc)
				 * 
				 * @see
				 * com.google.gwt.event.dom.client.MouseDownHandler#onMouseDown(com.google.gwt.event.dom.client.MouseDownEvent)
				 */
				@Override
				public void onMouseDown(MouseDownEvent event) {
					// removes the default behavior
					event.preventDefault();
				}

			});
			// div.appendChild(canvas);
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
		// injects Chart.js java script source
		Injector.ensureInjected();
		// creates plugins container
		plugins = new Plugins(this);
		// creates global options
		options = new GlobalOptions(createGlobalOptions(getType().name()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.ui.Widget#createHandlerManager()
	 */
	@Override
	protected HandlerManager createHandlerManager() {
		return new ChartHandlerManager(this);
	}

	/**
	 * Returns the ID of chart.<br>
	 * It could be considered as chart unique ID.
	 * 
	 * @return the ID of chart
	 */
	public final String getId() {
		return id;
	}

	/**
	 * Returns the canvas element
	 * 
	 * @return the canvas
	 */
	public final Canvas getCanvas() {
		return canvas;
	}

	/**
	 * Remove the registration of prevent default mouse listener from canvas.<br>
	 * This is necessary when you will add your mouse down listeber.
	 */
	public void removeCanvasPreventDefault() {
		// checks if consistent
		if (preventDisplayHandler != null) {
			// cleans up the handler for mouse listener
			preventDisplayHandler.removeHandler();
		}
	}

	/**
	 * Returns <code>true</code> if CHART.JS chart has been initialized, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if CHART.JS chart has been initialized, otherwise <code>false</code>.
	 */
	public boolean isInitialized() {
		return chart != null;
	}

	/**
	 * Returns the chart node with runtime data.
	 * 
	 * @return the chart node.
	 */
	public final ChartNode getChartNode() {
		return new ChartNode((GenericJavaScriptObject) chart);
	}

	/**
	 * @return the data configuration object
	 */
	public final Data getData() {
		return data;
	}

	/**
	 * @return the plugins configuration object
	 */
	public final Plugins getPlugins() {
		return plugins;
	}

	/**
	 * @return the options
	 */
	public GlobalOptions getGlobal() {
		return options;
	}

	/**
	 * @return the drawOnAttach
	 */
	public boolean isDrawOnAttach() {
		return drawOnAttach;
	}

	/**
	 * @param drawOnAttach the drawOnAttach to set
	 */
	public void setDrawOnAttach(boolean drawOnAttach) {
		this.drawOnAttach = drawOnAttach;
	}

	/**
	 * @return the destroyOnDetach
	 */
	public boolean isDestroyOnDetach() {
		return destroyOnDetach;
	}

	/**
	 * @param destroyOnDetach the destroyOnDetach to set
	 */
	public void setDestroyOnDetach(boolean destroyOnDetach) {
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
			destroy();
		}
	}

	/**
	 * Use this to destroy any chart instances that are created. This will clean up any references stored to the chart object
	 * within Chart.js, along with any associated event listeners attached by Chart.js.
	 */
	public void destroy() {
		if (chart != null) {
			// checks if handler is consistent
			removeCanvasPreventDefault();
			destroyChart();
			// removes chart instance from collection
			Charts.remove(getId());
		}
	}

	/**
	 * Use this to stop any current animation loop. This will pause the chart during any current animation frame. Call
	 * <code>.render()</code> to re-animate.
	 * 
	 * @see AbstractChart#render()
	 */
	public void stop() {
		if (chart != null) {
			stopChart();
		}
	}

	/**
	 * Will clear the chart canvas. Used extensively internally between animation frames.
	 */
	public void clear() {
		if (chart != null) {
			clearChart();
		}
	}

	/**
	 * Reset the chart to it's state before the initial animation. A new animation can then be triggered using update.
	 */
	public void reset() {
		if (chart != null) {
			resetChart();
		}
	}

	/**
	 * his returns a base 64 encoded string of the chart in it's current state.
	 * 
	 * @return base 64 image or null if chart is not initialized
	 */
	public String toBase64Image() {
		if (chart != null) {
			return toBase64ImageChart();
		}
		return null;
	}

	/**
	 * Returns an HTML string of a legend for that chart. The legend is generated from the legendCallback in the options.
	 * 
	 * @return the HTML legend or null if chart is not initialized
	 * @see LegendCallback
	 */
	public String generateLegend() {
		if (chart != null) {
			return generateLegendChart();
		}
		return null;
	}

	/**
	 * Use this to manually resize the canvas element. This is run each time the canvas container is resized, but can be called
	 * this method manually if you change the size of the canvas nodes container element.
	 */
	public void resize() {
		if (chart != null) {
			resizeChart();
		}
	}

	/**
	 * Triggers an update of the chart. This can be safely called after updating the data object. This will update all scales,
	 * legends, and then re-render the chart.
	 */
	public void update() {
		update(null);
	}

	/**
	 * Triggers an update of the chart. This can be safely called after updating the data object. This will update all scales,
	 * legends, and then re-render the chart. A config object can be provided with additional configuration for the update
	 * process. This is useful when update is manually called inside an event handler and some different animation is desired.
	 * 
	 * @param config A config object can be provided with additional configuration for the update process
	 * @see UpdateConfiguration
	 */
	public void update(UpdateConfiguration config) {
		if (chart != null) {
			updateChart(config == null ? null : config.getObject());
		}
	}

	/**
	 * Triggers a redraw of all chart elements. Note, this does not update elements for new data. Use <code>.update()</code> in
	 * that case.
	 * 
	 * @see AbstractChart#update()
	 */
	public void render() {
		render(null);
	}

	/**
	 * Triggers a redraw of all chart elements. Note, this does not update elements for new data. Use <code>.update()</code> in
	 * that case. A config object can be provided with additional configuration for the render process. This is useful when
	 * update is manually called inside an event handler and some different animation is desired.
	 * 
	 * @param config A config object can be provided with additional configuration for the render process
	 * @see AbstractChart#update()
	 * @see UpdateConfiguration
	 */
	public void render(UpdateConfiguration config) {
		if (chart != null) {
			renderChart(config == null ? null : config.getObject());
		}
	}

	/**
	 * Looks for the dataset that matches the current index and returns that metadata.
	 * 
	 * @param index dataset index
	 * @return dataset meta data item.
	 */
	public DatasetMetaItem getDatasetMeta(int index) {
		// checks consistency of chart and datasets
		if (chart != null && data.getDatasets() != null && !data.getDatasets().isEmpty() && index < data.getDatasets().size()) {
			// gets all meta data items
			DatasetMetaItem array = new DatasetMetaItem(getChartDatasetMeta(index));
			// returns the array
			return array;
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
	public DatasetMetaItem getDatasetAtEvent(ChartNativeEvent event) {
		// checks consistency of chart and event
		if (chart != null && event != null) {
			// gets dataset
			DatasetMetaItem array = new DatasetMetaItem(getChartDatasetAtEvent(event));
			// returns the array
			return array;
		}
		// returns null;
		return null;
	}

	/**
	 * Looks for the dataset if it's visible or not, selected by index.
	 * 
	 * @param index dataset index
	 * @return <code>true</code> if dataset is visible otherwise <code>false</code>. 
	 */
	public boolean isDatasetVisible(int index) {
		// checks consistency of chart and datasets
		if (chart != null && data.getDatasets() != null && !data.getDatasets().isEmpty() && index < data.getDatasets().size()) {
			// gets if dataset is visible or not
			return isChartDatasetVisible(index);
		}
		// returns false
		return false;
	}

	/**
	 * Returns the amount of datasets which are visible
	 * 
	 * @return the amount of datasets which are visible. If chart is not initialized, return
	 *         {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}. 
	 */
	public int getVisibleDatasetCount() {
		// checks consistency of chart and datasets
		if (chart != null) {
			// gets if dataset is visible or not
			return getChartVisibleDatasetCount();
		}
		// returns false
		return UndefinedValues.INTEGER;
	}

	/**
	 * Calling on your chart instance passing an argument of an event, will return the single element at the event position.<br>
	 * If there are multiple items within range, only the first is returned.<br>
	 * 
	 * @param event event of chart.
	 * @return single element at the event position or null.
	 */
	public DatasetItem getElementAtEvent(ChartNativeEvent event) {
		// checks consistency of chart and event
		if (chart != null && event != null) {
			// gets element
			return new DatasetItem(getChartElementAtEvent(event));
		}
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
	public DatasetMetaItem getElementsAtEvent(ChartNativeEvent event) {
		// checks consistency of chart and event
		if (chart != null && event != null) {
			// gets elements
			DatasetMetaItem array = new DatasetMetaItem(getChartElementsAtEvent(event));
			// returns the array
			return array;
		}
		// returns null;
		return null;
	}

	/**
	 * 
	 */
	public void draw() {
		// checks if canvas is supported
		if (isCanvasSupported) {
			// calls plugins for onConfigure method
			Defaults.getPlugins().onChartConfigure(configuration, this);
			plugins.onChartConfigure(configuration, this);
			// gets options
			BaseOptions options = getOptions();
			// gets data
			Data data = getData();
			// sets all items to configuration item
			configuration.setType(getType());
			configuration.setOptions(options);
			configuration.setData(data);
			configuration.setPlugins(plugins);
			// destroy chart if chart is already instantiated
			destroy();
			// stores teh chart instance into collection
			Charts.add(this);
			// draws chart with configuration
			drawChart(configuration.getObject(), canvas.getContext2d());
		}
	}

	/**
	 * Returns the chart configuration in JSON format.
	 * 
	 * @return the chart configuration in JSON format.
	 */
	public String toJSONString() {
		// gets options
		BaseOptions options = getOptions();
		// gets data
		Data data = getData();
		// sets all items to configuration item
		configuration.setType(getType());
		configuration.setOptions(options);
		configuration.setData(data);
		configuration.setPlugins(plugins);
		// returns on JSON format
		return configuration.getObject().toJSON();
	}

	/**
	 * Draws the chart.
	 * 
	 * @param config configuration java script object.
	 */
	private native int drawChart(JavaScriptObject config, Context2d context)/*-{
																			var chart = this.@org.pepstock.charba.client.AbstractChart::chart;
																			chart = new $wnd.Chart(context, config);
																			this.@org.pepstock.charba.client.AbstractChart::chart = chart;
																			return chart.id;
																			}-*/;

	/**
	 * Resizes the chart.
	 */
	private native void resizeChart()/*-{
										var chart = this.@org.pepstock.charba.client.AbstractChart::chart;
										chart.resize();
										}-*/;

	/**
	 * Updates the chart
	 * 
	 * @param config update configuration java script object.
	 */
	private native void updateChart(JavaScriptObject config)/*-{
															var chart = this.@org.pepstock.charba.client.AbstractChart::chart;
															if (config == null){
															chart.update();
															} else {
															chart.update(config);
															}
															}-*/;

	/**
	 * Renders the chart.
	 * 
	 * @param config update configuration java script object.
	 */
	private native void renderChart(JavaScriptObject config)/*-{
															var chart = this.@org.pepstock.charba.client.AbstractChart::chart;
															if (config == null){
															chart.render();
															} else {
															chart.render(config);
															}
															}-*/;

	/**
	 * Destroys the chart.
	 */
	private native void destroyChart()/*-{
										var chart = this.@org.pepstock.charba.client.AbstractChart::chart;
										chart.destroy();
										}-*/;

	/**
	 * Stops the chart.
	 */
	private native void stopChart()/*-{
									var chart = this.@org.pepstock.charba.client.AbstractChart::chart;
									chart.stop();
									}-*/;

	/**
	 * Resets the chart.
	 */
	private native void resetChart()/*-{
									var chart = this.@org.pepstock.charba.client.AbstractChart::chart;
									chart.reset();
									}-*/;

	/**
	 * Clears the chart,
	 */
	private native void clearChart()/*-{
									var chart = this.@org.pepstock.charba.client.AbstractChart::chart;
									chart.clear();
									}-*/;

	/**
	 * Generates the legend of chart.
	 * 
	 * @return the legend of chart.
	 */
	private native String generateLegendChart()/*-{
												var chart = this.@org.pepstock.charba.client.AbstractChart::chart;
												return chart.generateLegend();
												}-*/;

	/**
	 * Gets the image (base 64) of the chart.
	 * 
	 * @return the image (base 64) of the chart.
	 */
	private native String toBase64ImageChart()/*-{
												var chart = this.@org.pepstock.charba.client.AbstractChart::chart;
												if (chart != null){
												return chart.toBase64Image();
												}
												return null;
												}-*/;

	/**
	 * Gets the element of the chart, selected by event.
	 * 
	 * @param event event of get the element.
	 * @return dataset meta data item.
	 */
	private native GenericJavaScriptObject getChartElementAtEvent(JavaScriptObject event)/*-{
																							var chart = this.@org.pepstock.charba.client.AbstractChart::chart;
																							var items = chart.getElementAtEvent(event);
																							if (items.length == 1){
																							return items[0];
																							}
																							return null;
																							}-*/;

	/**
	 * Gets the elements of the chart, selected by event.
	 * 
	 * @param event event of get the elements.
	 * @return dataset meta data items.
	 */
	private native GenericJavaScriptObject getChartElementsAtEvent(JavaScriptObject event)/*-{
																							var chart = this.@org.pepstock.charba.client.AbstractChart::chart;
																							return chart.getElementsAtEvent(event);
																							}-*/;

	/**
	 * Gets the dataset of the chart, selected by event.
	 * 
	 * @param event event of get the dataset.
	 * @return dataset meta data items.
	 */
	private native GenericJavaScriptObject getChartDatasetAtEvent(JavaScriptObject event)/*-{
																							var chart = this.@org.pepstock.charba.client.AbstractChart::chart;
																							return chart.getDatasetAtEvent(event);
																							}-*/;

	/**
	 * Gets the dataset meta data array of the chart, selected by index.
	 * 
	 * @param datasetIndex dataset index
	 * @return dataset meta data items.
	 */
	private native GenericJavaScriptObject getChartDatasetMeta(int datasetIndex)/*-{
																				var chart = this.@org.pepstock.charba.client.AbstractChart::chart;
																				return chart.getDatasetMeta(datasetIndex);
																				}-*/;

	/**
	 * Gets if the dataset is visible or not, selected by index.
	 * 
	 * @param datasetIndex dataset index
	 * @return <code>true</code> if dataset is visible otherwise <code>false</code>.
	 */
	private native boolean isChartDatasetVisible(int datasetIndex)/*-{
																	var chart = this.@org.pepstock.charba.client.AbstractChart::chart;
																	return chart.isDatasetVisible(datasetIndex);
																	}-*/;

	/**
	 * Gets the amount of datasets which are visible
	 * 
	 * @return the amount of datasets which are visible
	 */
	private native int getChartVisibleDatasetCount()/*-{
													var chart = this.@org.pepstock.charba.client.AbstractChart::chart;
													return chart.getVisibleDatasetCount();
													}-*/;

	/**
	 * Builds the configuration using the defaults global both for chart and for the specific chart.
	 * 
	 * @param chartType type of chart
	 * @return java script object with merged configuration.
	 */
	private native GenericJavaScriptObject createGlobalOptions(String chartType) /*-{
																					var chartOptions = new Object();
																					if ($wnd.Chart.defaults.hasOwnProperty(chartType)){
																					chartOptions = $wnd.Chart.helpers.clone($wnd.Chart.defaults[chartType]);
																					}
																					var scaleOptions = $wnd.Chart.helpers.clone($wnd.Chart.defaults.scale);
																					var globalOptions = $wnd.Chart.helpers.clone($wnd.Chart.defaults.global);
																					
																					if (chartOptions.hasOwnProperty("scale")){
																					var scaleChartOptions = $wnd.Chart.helpers.mergeIf(chartOptions.scale, scaleOptions);
																					} else if (chartOptions.hasOwnProperty("scales")){
																					var xAxes = chartOptions.scales.xAxes;
																					for (var i=0; i<xAxes.length; i++){
																					$wnd.Chart.helpers.mergeIf(xAxes[i], scaleOptions, xAxes[i]);
																					}
																					var yAxes = chartOptions.scales.yAxes;
																					for (var i=0; i<yAxes.length; i++){
																					$wnd.Chart.helpers.mergeIf(yAxes[i], scaleOptions);
																					}
																					}
																					$wnd.Chart.helpers.mergeIf(chartOptions, globalOptions);
																					return chartOptions;
																					}-*/;

}