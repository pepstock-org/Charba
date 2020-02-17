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
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.configuration.ConfigurationOptions;
import org.pepstock.charba.client.controllers.ControllerType;
import org.pepstock.charba.client.data.Data;
import org.pepstock.charba.client.data.Dataset;
import org.pepstock.charba.client.defaults.IsDefaultScaledOptions;
import org.pepstock.charba.client.defaults.chart.DefaultChartOptions;
import org.pepstock.charba.client.dom.BaseEventTarget.EventListenerCallback;
import org.pepstock.charba.client.dom.BaseEventTypes;
import org.pepstock.charba.client.dom.BaseNativeEvent;
import org.pepstock.charba.client.dom.DOMBuilder;
import org.pepstock.charba.client.dom.elements.Canvas;
import org.pepstock.charba.client.dom.elements.Div;
import org.pepstock.charba.client.dom.elements.Heading;
import org.pepstock.charba.client.dom.enums.CursorType;
import org.pepstock.charba.client.dom.enums.Position;
import org.pepstock.charba.client.dom.enums.Unit;
import org.pepstock.charba.client.events.AddHandlerEvent;
import org.pepstock.charba.client.events.ChartEventHandler;
import org.pepstock.charba.client.events.EventHandler;
import org.pepstock.charba.client.events.EventType;
import org.pepstock.charba.client.events.HandlerManager;
import org.pepstock.charba.client.events.HandlerRegistration;
import org.pepstock.charba.client.items.DatasetItem;
import org.pepstock.charba.client.items.DatasetItem.DatasetItemFactory;
import org.pepstock.charba.client.items.DatasetMetaItem;
import org.pepstock.charba.client.items.UndefinedValues;
import org.pepstock.charba.client.options.ExtendedOptions;
import org.pepstock.charba.client.plugins.Plugins;
import org.pepstock.charba.client.resources.ResourcesType;
import org.pepstock.charba.client.utils.Utilities;

/**
 * Base class of all charts.<br>
 * It contains Chart.js initialization.
 * 
 * @author Andrea "Stock" Stocchero
 *
 * @param <D> Dataset type for the specific chart
 */
public abstract class AbstractChart<D extends Dataset> extends HandlerManager implements IsChart, MutationHandler {
	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the mouse down function on canvas
	private final CallbackProxy<EventListenerCallback> canvasCallbackProxy = JsHelper.get().newCallbackProxy();

	// message to show when the browser can't support canvas
	private static final String CANVAS_NOT_SUPPORTED_MESSAGE = "Ops... Canvas element is not supported...";
	// PCT standard for width
	private static final int DEFAULT_WIDTH = 90;
	// PCT standard for width
	private static final int DEFAULT_HEIGHT = 100;
	// suffix label for canvas element id
	private static final String SUFFIX_CANVAS_ELEMENT_ID = "_canvas";
	// reference to Chart.js chart instance
	private Chart chart = null;
	// chart ID using GWT unique id
	private final String id = DOMBuilder.get().createUniqueId();
	// stores the type of chart
	private final Type type;
	// list of all handle registration when
	// an handler (for events) has been added to chart
	// needed for clean up when chart will be destroy
	private final List<HandlerRegistration> handlerRegistrations = new ArrayList<>();
	// canvas where Chart.js draws the chart
	private final Div element;
	// canvas where Chart.js draws the chart
	private final Canvas canvas;
	// CHart configuration object
	private final Configuration configuration = new Configuration();
	// Data element of configuration
	private final Data data = new Data();
	// plugins of this chart
	private final Plugins plugins;
	// gets if Canvas is supported
	private final boolean isCanvasSupported = DOMBuilder.get().isCanvasSupported();
	// merged options of defaults
	private final ChartOptions options;
	// merged options as default options
	private final IsDefaultScaledOptions defaultChartOptions;
	// instance of dataset items factory.
	private final DatasetItemFactory datasetItemFactory = new DatasetItemFactory();
	// cursor defined when chart is created
	private final CursorType initialCursor;

	/**
	 * Initializes simple panel and canvas which are used by CHART.JS.<br>
	 * It sets also some default behaviors (width in percentage) for resizing
	 * 
	 * @param type the type of chart
	 */
	public AbstractChart(Type type) {
		// checks if type is consistent
		Type.checkIfValid(type);
		// stores type
		this.type = type;
		// creates the div element
		element = DOMBuilder.get().createDivElement();
		// creates DIV
		element.setId(id);
		// sets relative position
		element.getStyle().setPosition(Position.RELATIVE);
		// sets default width values
		element.getStyle().setWidth(Unit.PCT.format(DEFAULT_WIDTH));
		element.getStyle().setHeight(Unit.PCT.format(DEFAULT_HEIGHT));
		// checks if canvas is supported
		if (isCanvasSupported) {
			// creates a canvas
			canvas = DOMBuilder.get().createCanvasElement();
			// set id to canvas
			canvas.setId(id + SUFFIX_CANVAS_ELEMENT_ID);
			// adds to panel
			element.appendChild(canvas);
			// -------------------------------
			// -- SET CALLBACKS to PROXIES ---
			// -------------------------------
			// fires the event
			canvasCallbackProxy.setCallback((context, event) -> event.preventDefault());
			// adds the listener to disable canvas selection
			// removes the default behavior
			canvas.addEventListener(BaseEventTypes.MOUSE_DOWN, canvasCallbackProxy.getProxy());
		} else {
			// creates a header element
			Heading h = DOMBuilder.get().createHeadingElement();
			// to show the error message
			// because canvas is not supported
			h.setTextContent(CANVAS_NOT_SUPPORTED_MESSAGE);
			element.appendChild(h);
			// resets canvas
			canvas = null;
		}
		// inject Chart.js and date library if not already loaded
		ResourcesType.getClientBundle().inject();
		// creates plugins container
		plugins = new Plugins();
		// creates defaults options for this chart type
		// chart type is NOT checked here but when new chart options
		// object is created
		options = createChartOptions();
		// returns a default option with all configuration
		// it uses the default builder and the default scaled options
		// because chart options is already a merge between global and chart global
		defaultChartOptions = new DefaultChartOptions(options);
		// stores initial cursor
		initialCursor = Utilities.getCursorOfChart(this);
		// adds chart observer to get on attach and detach
		ChartObserver.get().addHandler(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.events.HandlerManager#getSource()
	 */
	@Override
	protected final IsChart getSource() {
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.events.HandlerManager#addHandler(org.pepstock.charba.client.commons.events. EventHandler,
	 * org.pepstock.charba.client.commons.events.EventType)
	 */
	@Override
	public final HandlerRegistration addHandler(EventHandler handler, EventType type) {
		// adds handler
		HandlerRegistration registration = super.addHandler(handler, type);
		// if the handler is a chart event handler one
		if (handler instanceof ChartEventHandler) {
			// sends the event
			fireEvent(new AddHandlerEvent(type));
		}
		// stores the registration into chart
		// for cleaning up when chart will be destroy
		handlerRegistrations.add(registration);
		// returns registration
		return registration;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#getElement()
	 */
	@Override
	public final Div getChartElement() {
		return element;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#getType()
	 */
	@Override
	public final Type getType() {
		return type;
	}

	/**
	 * Creates a new dataset related to chart type.
	 * 
	 * @return a new dataset related to chart type.
	 */
	public abstract D newDataset();

	/**
	 * Returns the native object related to CHART.JS implementation.
	 *
	 * @return the native object related to CHART.JS implementation
	 */
	final Chart getNativeObject() {
		return lookForConsistentInstance();
	}

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#getBaseType()
	 */
	@Override
	public final Type getBaseType() {
		// gets the type
		Type currentType = getType();
		// checks if a controller type
		if (currentType instanceof ControllerType) {
			// casts to a controller type
			ControllerType controllerType = (ControllerType) currentType;
			// returns the chart type of the contrller is exist
			// or null
			return controllerType.getChartType();
		}
		// if here is a chart type
		// then returns the current type
		return currentType;
	}

	/**
	 * Returns the CHART.JS instance, check if the inner one is not consistent yet and then looking for the stored one into {@link Charts}.
	 * 
	 * @return the CHART.JS instance, check if the inner one is not consistent yet
	 */
	private final Chart lookForConsistentInstance() {
		// gets chart instance getting the stored one into map
		// if the current one is still initializing
		return chart != null ? chart : Charts.getNative(id);
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
			throw new UnsupportedOperationException(CANVAS_NOT_SUPPORTED_MESSAGE);
		}
	}

	/**
	 * Remove the registration of prevent default mouse listener from canvas.<br>
	 * This is necessary when you will add your mouse down listener.
	 */
	@Override
	public final void removeCanvasPreventDefault() {
		// cleans up the handler for mouse listener
		canvas.removeEventListener(BaseEventTypes.MOUSE_DOWN, canvasCallbackProxy.getProxy());
	}

	/**
	 * Returns the initial cursor of the chart.
	 * 
	 * @return the initial cursor of the chart.
	 */
	@Override
	public final CursorType getInitialCursor() {
		return initialCursor;
	}

	/**
	 * Returns <code>true</code> if CHART.JS chart has been initialized, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if CHART.JS chart has been initialized, otherwise <code>false</code>.
	 */
	@Override
	public final boolean isInitialized() {
		return chart != null;
	}

	/**
	 * Returns the chart node with runtime data.
	 * 
	 * @return the chart node.
	 */
	@Override
	public final ChartNode getNode() {
		return new ChartNode(Charts.getNative(id));
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
	 * Returns the default chart options
	 * 
	 * @return the default chart options
	 */
	final ChartOptions getChartOptions() {
		return options;
	}

	/**
	 * Returns the default options created based on chart type.
	 * 
	 * @return the default options of the chart
	 */
	@Override
	public final IsDefaultScaledOptions getDefaultChartOptions() {
		return defaultChartOptions;
	}

	/**
	 * Returns the default options by a chart instance, merging global, chart type global and chart options.<br>
	 * If the options is not consistent, it will returns the {@link AbstractChart#getDefaultChartOptions()}.
	 * 
	 * @return the default options by a chart instance, merging global, chart type global and chart options
	 */
	@Override
	public final IsDefaultScaledOptions getWholeOptions() {
		// checks if options are consistent
		if (getOptions() != null) {
			// creates an envelop for options
			Envelop<NativeObject> envelop = new Envelop<>();
			// load the envelop
			getOptions().loadOptions(envelop);
			// creates a chart options with complete configuration of chart
			ExtendedOptions chartOptions = new ExtendedOptions(this, defaultChartOptions, envelop);
			// returns a default option with all configuration
			return new DefaultChartOptions(chartOptions);
		}
		// if here, the options are not consistent then it will returns the default chartoptions
		return getDefaultChartOptions();
	}

	/**
	 * Returns <code>true</code> if the chart is configured to be drawn on the attach of DIV element, otherwise <code>false</code>.
	 * 
	 * @return the drawOnAttach <code>true</code> if the chart is configured to be drawn on the attach of DIV element, otherwise <code>false</code>. Default is <code>true</code>.
	 */
	@Override
	public final boolean isDrawOnAttach() {
		// checks if options are consistent
		IsChart.checkIfConsistent(this);
		return getOptions().isDrawOnAttach();
	}

	/**
	 * Sets <code>true</code> if the chart is configured to be draw on the attach of DIV element, otherwise <code>false</code>.
	 * 
	 * @param drawOnAttach the drawOnAttach to set
	 */
	@Override
	public final void setDrawOnAttach(boolean drawOnAttach) {
		// checks if options are consistent
		IsChart.checkIfConsistent(this);
		getOptions().setDrawOnAttach(drawOnAttach);
	}

	/**
	 * Returns <code>true</code> if the chart is configured to be destroyed on the detach from DIV element, otherwise <code>false</code>.
	 * 
	 * @return the destroyOnDetach <code>true</code> if the chart is configured to be destroyed on the detach from DIV element, otherwise <code>false</code>. Default is
	 *         <code>true</code>.
	 */
	@Override
	public final boolean isDestroyOnDetach() {
		// checks if options are consistent
		IsChart.checkIfConsistent(this);
		return getOptions().isDestroyOnDetach();
	}

	/**
	 * Sets <code>true</code> if the chart is configured to be destroyed on the detach from DIV element, otherwise <code>false</code>.
	 * 
	 * @param destroyOnDetach the destroyOnDetach to set
	 */
	@Override
	public final void setDestroyOnDetach(boolean destroyOnDetach) {
		// checks if options are consistent
		IsChart.checkIfConsistent(this);
		getOptions().setDestroyOnDetach(destroyOnDetach);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.MutationHandler#onAttach(org.pepstock.charba.client.MutationItem)
	 */
	@Override
	public final void onAttach(MutationItem item) {
		// if item is consistent and
		// if is not to be drawn on attach, doesn't draw
		if (item != null && isDrawOnAttach()) {
			draw();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.MutationHandler#onDetach(org.pepstock.charba.client.MutationItem)
	 */
	@Override
	public final void onDetach(MutationItem item) {
		// if item is consistent and
		// if is not to be destroyed on detach, doesn't destroy
		if (item != null && isDestroyOnDetach()) {
			// then destroy
			destroy();
		}
	}

	/**
	 * Use this to destroy any chart instances that are created. This will clean up any references stored to the chart object within Chart.js, along with any associated event
	 * listeners attached by Chart.js.
	 */
	@Override
	public final void destroy() {
		// notify before destroy
		Charts.fireBeforeDestory(this);
		// get consistent chart instance
		Chart instance = lookForConsistentInstance();
		// checks if chart is created
		if (instance != null) {
			// then destroy
			instance.destroy();
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
		// remove chart observer to get on attach and detach
		ChartObserver.get().removeHandler(this);
	}

	/**
	 * Use this to stop any current animation loop.<br>
	 * This will pause the chart during any current animation frame.<br>
	 * Call <code>.render()</code> to re-animate.
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
	 * Will clear the chart canvas.<br>
	 * Used extensively internally between animation frames.<br>
	 * Overrides the <code>clear</code> method of GWT <code>Panel</code>, changing completely the behavior of GWT <code>Panel</code> one.
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
		// get consistent chart instance
		Chart instance = lookForConsistentInstance();
		// checks if chart is created
		if (instance != null) {
			// returns custom HTML legend
			return instance.generateLegend();
		}
		// default
		return UndefinedValues.STRING;
	}

	/**
	 * Use this to manually resize the canvas element. This is run each time the canvas container is resized, but can be called this method manually if you change the size of the
	 * canvas nodes container element.
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
	 * Triggers an update of the chart. This can be safely called after updating the data object. This will update all scales, legends, and then re-render the chart.
	 */
	@Override
	public final void update() {
		update(null);
	}

	/**
	 * Triggers an update of the chart. This can be safely called after updating the data object. This will update all scales, legends, and then re-render the chart. A
	 * configuration object can be provided with additional configuration for the update process. This is useful when update is manually called inside an event handler and some
	 * different animation is desired.
	 * 
	 * @param configuration a configuration object can be provided with additional configuration for the update process
	 */
	@Override
	public final void update(UpdateConfiguration configuration) {
		// checks if chart is created
		if (chart != null) {
			// if config is not passed..
			if (configuration == null) {
				// .. calls the update
				chart.update();
			} else {
				// .. otherwise calls the update with config
				chart.update(configuration.nativeObject());
			}
		}
	}

	/**
	 * Triggers an update of the chart. This can be safely called after updating the data object. This will update the options, mutating the options property in place.
	 */
	@Override
	public final void reconfigure() {
		reconfigure(null);
	}

	/**
	 * Triggers an update of the chart. This can be safely called after updating the data object. This will update the options, mutating the options property in place. A
	 * configuration object can be provided with additional configuration for the update process. This is useful when update is manually called inside an event handler and some
	 * different animation is desired.
	 * 
	 * @param configuration a configuration object can be provided with additional configuration for the update process
	 */
	@Override
	public final void reconfigure(UpdateConfiguration configuration) {
		// checks if chart is created and consistent
		if (chart != null && IsChart.isConsistent(this)) {
			// fires that chart is configuring
			Charts.fireBeforeConfigure(this);
			// updates option passed by configuration element
			Configuration tempConfiguration = new Configuration();
			// gets options
			ConfigurationOptions internalOptions = getOptions();
			// sets options by temporary configuration
			tempConfiguration.setOptions(this, internalOptions);
			// replace the options
			chart.setOptions(tempConfiguration.getOptions());
			// calls plugins for onConfigure method
			Defaults.get().getPlugins().onChartConfigure(tempConfiguration, this);
			plugins.onChartConfigure(tempConfiguration, this);
			// fires that chart has been configured
			Charts.fireAfterConfigure(this);
			// update chart
			update(configuration);
		}
	}

	/**
	 * Triggers a redraw of all chart elements. Note, this does not update elements for new data. Use <code>.update()</code> in that case.
	 */
	@Override
	public final void render() {
		render(null);
	}

	/**
	 * Triggers a redraw of all chart elements. Note, this does not update elements for new data. Use <code>.update()</code> in that case. A configuration object can be provided
	 * with additional configuration for the render process. This is useful when update is manually called inside an event handler and some different animation is desired.
	 * 
	 * @param configuration a configuration object can be provided with additional configuration for the render process
	 */
	@Override
	public final void render(UpdateConfiguration configuration) {
		// checks if chart is created
		if (chart != null) {
			// if config is not passed..
			if (configuration == null) {
				// .. calls the render
				chart.render();
			} else {
				// .. otherwise calls the render with config
				chart.render(configuration.nativeObject());
			}
		}
	}

	/**
	 * Looks for the dataset that matches the current index and returns that metadata.
	 * 
	 * @param index dataset index
	 * @return dataset meta data item or <code>null</code> if the index is out of range of datasets count.
	 */
	@Override
	public final DatasetMetaItem getDatasetMeta(int index) {
		// get consistent chart instance
		Chart instance = lookForConsistentInstance();
		// checks consistency of chart and datasets
		if (instance != null && data.getDatasets() != null && !data.getDatasets().isEmpty() && index < data.getDatasets().size() && index >= 0) {
			// returns the array
			return new DatasetMetaItem(instance.getDatasetMeta(index));
		}
		// returns null
		return null;
	}

	/**
	 * Looks for the dataset that matches the event and returns that metadata.
	 * 
	 * @param event event of chart.
	 * @return dataset meta data item or <code>null</code> if event is not consistent
	 */
	@Override
	public final DatasetMetaItem getDatasetAtEvent(BaseNativeEvent event) {
		// get consistent chart instance
		Chart instance = lookForConsistentInstance();
		// checks consistency of chart and event
		if (instance != null && event != null) {
			// gets dataset and
			// returns the array
			return new DatasetMetaItem(instance.getDatasetAtEvent(event));
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
	public final boolean isDatasetVisible(int index) {
		// get consistent chart instance
		Chart instance = lookForConsistentInstance();
		// checks consistency of chart and datasets
		if (instance != null && data.getDatasets() != null && !data.getDatasets().isEmpty() && index < data.getDatasets().size() && index >= 0) {
			// gets if dataset is visible or not
			return instance.isDatasetVisible(index);
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
	public final int getVisibleDatasetCount() {
		// get consistent chart instance
		Chart instance = lookForConsistentInstance();
		// checks consistency of chart and datasets
		if (instance != null) {
			// gets if dataset is visible or not
			return instance.getVisibleDatasetCount();
		}
		// returns undefined
		return UndefinedValues.INTEGER;
	}

	/**
	 * Calling on your chart instance passing an argument of an event, will return the single element at the event position.<br>
	 * If there are multiple items within range, only the first is returned.
	 * 
	 * @param event event of chart.
	 * @return single element at the event position or <code>null</code> if event is not consistent
	 */
	@Override
	public final DatasetItem getElementAtEvent(BaseNativeEvent event) {
		// get consistent chart instance
		Chart instance = lookForConsistentInstance();
		// checks consistency of chart and event
		if (instance != null && event != null) {
			// gets element
			ArrayObject result = instance.getElementAtEvent(event);
			if (result != null && !result.isEmpty()) {
				return new DatasetItem(result.get(0));
			}
		}
		// if here, inconsistent result
		return null;
	}

	/**
	 * Looks for the element under the event point, then returns all elements at the same data index.<br>
	 * Calling it on your chart instance passing an argument of an event, will return the point elements that are at that the same position of that event.
	 * 
	 * @param event event of chart.
	 * @return all elements at the same data index or an empty list.
	 */
	@Override
	public final List<DatasetItem> getElementsAtEvent(BaseNativeEvent event) {
		// get consistent chart instance
		Chart instance = lookForConsistentInstance();
		// checks consistency of chart and event
		if (instance != null && event != null) {
			// gets elements
			ArrayObject array = instance.getElementsAtEvent(event);
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
		// checks if canvas is supported and the chart is consistent
		if (isCanvasSupported && IsChart.isConsistent(this)) {
			// fires that chart is configuring
			Charts.fireBeforeConfigure(this);
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
			// PAY ATTENTION that the data configuration
			// must be executed after PLUGINS on configure
			// in order to be able to disable the canvas object handler plugin
			// if required
			configuration.setData(this, internalData);
			// sets plugins
			configuration.setPlugins(this, plugins);
			// fires that chart has been configured
			Charts.fireAfterConfigure(this);
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
			// notify after init
			Charts.fireAfterInit(this);
		}
	}

}