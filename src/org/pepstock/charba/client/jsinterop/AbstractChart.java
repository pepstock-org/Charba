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
package org.pepstock.charba.client.jsinterop;

import java.util.List;

import org.pepstock.charba.client.Injector;
import org.pepstock.charba.client.jsinterop.commons.ArrayListHelper;
import org.pepstock.charba.client.jsinterop.commons.ArrayObject;
import org.pepstock.charba.client.jsinterop.configuration.ConfigurationOptions;
import org.pepstock.charba.client.jsinterop.data.Data;
import org.pepstock.charba.client.jsinterop.data.Dataset;
import org.pepstock.charba.client.jsinterop.events.ChartNativeEvent;
import org.pepstock.charba.client.jsinterop.items.DatasetItem;
import org.pepstock.charba.client.jsinterop.items.DatasetItem.DatasetItemFactory;
import org.pepstock.charba.client.jsinterop.items.DatasetMetaItem;
import org.pepstock.charba.client.jsinterop.plugins.Plugins;
import org.pepstock.charba.client.jsinterop.utils.JSON;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.dom.client.CanvasElement;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.Widget;

/**
 * Base class of all charts.<br>
 * It contains Chart.js initialization.
 * 
 * @author Andrea "Stock" Stocchero
 *
 * @param <O> Options type for the specific chart
 * @param <D> Dataset type for the specific chart
 */
public abstract class AbstractChart<O extends ConfigurationOptions, D extends Dataset> extends Widget implements IsChart<O, D> {
	
	// message to show when the browser can't support canvas
	private static final String CANVAS_NOT_SUPPORTED_MESSAGE = "Ops... Canvas element is not supported...";
	// constant for WIDTH property
	private static final String WIDTH_PROPERTY = "width";
	// constant for HEIGHT property
	private static final String HEIGHT_PROPERTY = "height";
	// PCT standard for width
	private static final double DEFAULT_PCT_WIDTH = 90D;
	// reference to Chart.js chart instance
	private Chart chart = null;
	
	private ChartNode node = null;
	
	// chart ID using GWT unique id
	private final String id = Document.get().createUniqueId();
	
	// chart container
	final DivElement div;

	// canvas where Chart.js draws the chart
	final CanvasElement canvas;

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
	
	private final DatasetItemFactory datasetItemFactory = new DatasetItemFactory();

	/**
	 * Initializes HTMl elements (DIV and Canvas).<br>
	 * It sets also some default behaviors (width in percentage) for resizing
	 */
	public AbstractChart() {
		// creates DIV
		div = Document.get().createDivElement();
		div.setId(id);
		// sets relative position
		div.getStyle().setPosition(Position.RELATIVE);
		// sets default width values
		div.getStyle().setWidth(DEFAULT_PCT_WIDTH, Unit.PCT);
		div.getStyle().setHeight(100, Unit.PCT);
		// checks if canvas is supported
		if (isCanvasSupported) {
			// creates a canvas and add to DIV
			canvas = Document.get().createCanvasElement();
			div.appendChild(canvas);
		} else {
			// creates a header element
			HeadingElement h = Document.get().createHElement(3);
			// to show the error message
			// because canvas is not supported
			h.setInnerText(CANVAS_NOT_SUPPORTED_MESSAGE);
			div.appendChild(h);
			// resets canvas
			canvas = null;
		}
		// sets DIV as element of the widget
		setElement(div);
		// injects Chart.js java script source
		Injector.ensureInjected();
		// creates plugins container
		//FIXME
		plugins = new Plugins(this);
		// creates global options
		options = Defaults.get().options(getType());
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
	public final String getId(){
		return id;
	}
	
	/**
	 * @return the canvas
	 */
	public final CanvasElement getCanvas() {
		return canvas;
	}

	/**
	 * @return the chart container HTML element
	 */
	public final DivElement getContainer() {
		return div;
	}
	
	/**
	 * Returns the chart node with runtime data.
	 * @return the chart node.
	 */
	public final ChartNode getNode(){
		return node;
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
	public final ChartOptions getChartOptions() {
		return options;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.ui.UIObject#setHeight(java.lang.String)
	 */
	@Override
	public void setHeight(String height) {
		div.getStyle().setProperty(HEIGHT_PROPERTY, height);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.ui.UIObject#setWidth(java.lang.String)
	 */
	@Override
	public void setWidth(String width) {
		div.getStyle().setProperty(WIDTH_PROPERTY, width);
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
			chart.destroy();
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
			chart.stop();
		}
	}

	/**
	 * Will clear the chart canvas. Used extensively internally between animation frames.
	 */
	public void clear() {
		if (chart != null) {
			chart.clear();
		}
	}

	/**
	 * Reset the chart to it's state before the initial animation. A new animation can then be triggered using update.
	 */
	public void reset() {
		if (chart != null) {
			chart.reset();
		}
	}

	/**
	 * his returns a base 64 encoded string of the chart in it's current state.
	 * 
	 * @return base 64 image or null if chart is not initialized
	 */
	public String toBase64Image() {
		if (chart != null) {
			return chart.toBase64Image();
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
			return chart.generateLegend();
		}
		return null;
	}

	/**
	 * Use this to manually resize the canvas element. This is run each time the canvas container is resized, but can be called
	 * this method manually if you change the size of the canvas nodes container element.
	 */
	public void resize() {
		if (chart != null) {
			chart.resize();
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
			if (config == null) {
				chart.update();
			} else {
				chart.update(config);
			}
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
			if (config == null) {
				chart.render();
			} else {
				chart.render(config);
			}
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
			// returns the array
			return new DatasetMetaItem(chart.getDatasetMeta(index));
		}
		// returns null
		return null;
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
			ArrayObject result = chart.getElementAtEvent(event);
			if (result != null && result.length() > 0) {
				return new DatasetItem(result.get(0));
			}
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
	public List<DatasetItem> getElementsAtEvent(ChartNativeEvent event) {
		// checks consistency of chart and event
		if (chart != null && event != null) {
			// gets elements
			ArrayObject array = chart.getElementsAtEvent(event);
			// returns the array
			return ArrayListHelper.unmodifiableList(array, datasetItemFactory);
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
			// gets options
			ConfigurationOptions options = getOptions();
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
			chart = new Chart(canvas.getContext2d(), configuration);
			// stores the chart to node wrapper
			node = new ChartNode(chart);
		}
	}

	/**
	 * Returns the chart configuration in JSON format.
	 * 
	 * @return the chart configuration in JSON format.
	 */
	public String toJSONString() {
		// returns on JSON format
		return JSON.stringify(configuration);
	}

 }