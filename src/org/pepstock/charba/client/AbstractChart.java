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
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.configuration.ConfigurationOptions;
import org.pepstock.charba.client.controllers.ControllerType;
import org.pepstock.charba.client.data.BarDataset;
import org.pepstock.charba.client.data.Data;
import org.pepstock.charba.client.data.Dataset;
import org.pepstock.charba.client.data.HasDataPoints;
import org.pepstock.charba.client.data.LineDataset;
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
import org.pepstock.charba.client.enums.DataType;
import org.pepstock.charba.client.enums.InteractionAxis;
import org.pepstock.charba.client.enums.InteractionMode;
import org.pepstock.charba.client.events.AddHandlerEvent;
import org.pepstock.charba.client.events.ChartEventHandler;
import org.pepstock.charba.client.events.EventHandler;
import org.pepstock.charba.client.events.EventType;
import org.pepstock.charba.client.events.HandlerManager;
import org.pepstock.charba.client.events.HandlerRegistration;
import org.pepstock.charba.client.items.DatasetItem;
import org.pepstock.charba.client.items.DatasetReference;
import org.pepstock.charba.client.items.UndefinedValues;
import org.pepstock.charba.client.options.ExtendedOptions;
import org.pepstock.charba.client.options.IsTransitionKey;
import org.pepstock.charba.client.plugins.Plugins;
import org.pepstock.charba.client.resources.ResourcesType;
import org.pepstock.charba.client.utils.CTimer;
import org.pepstock.charba.client.utils.Utilities;

/**
 * Base class of all charts.<br>
 * It contains Chart.js initialization.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 */
public abstract class AbstractChart extends HandlerManager implements IsChart, MutationHandler {

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
	// creates a static reference for a interaction options for getElementsAtEventForMode method
	private static final InternalInterationModeObject INTERACTION_MODE = new InternalInterationModeObject(true);
	// reference to Chart.js chart instance
	private Chart chart = null;
	// chart ID using generate unique id
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
	// cursor defined when chart is created
	private final CursorType initialCursor;
	// timer instance
	private CTimer timer = null;

	/**
	 * Initializes simple panel and canvas which are used by CHART.JS.<br>
	 * It sets also some default behaviors (width in percentage) for resizing
	 * 
	 * @param type the type of chart
	 */
	protected AbstractChart(Type type) {
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
		ResourcesType.getResources().inject();
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
	 * @see org.pepstock.charba.client.commons.events.HandlerManager#addHandler(org.pepstock.charba.client.commons.events.EventHandler,
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
		// stores the registration in the chart
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
	 * Returns the CHART.JS instance, check if the inner one is not consistent yet and then looking for the stored one in the {@link Charts}.
	 * 
	 * @return the CHART.JS instance, check if the inner one is not consistent yet
	 */
	private final Chart lookForConsistentInstance() {
		// gets chart instance getting the stored one in the map
		// if the current one is still initializing
		return isInitialized() ? chart : Charts.getNative(id);
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
			// otherwise throws an exception
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
		return new ChartNode(getId(), lookForConsistentInstance());
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
	private ChartOptions createChartOptions() {
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
		// check if chart has been initialized
		if (isInitialized()) {
			// returns the options
			return getNode().getOptions();
		} else if (getOptions() != null) {
			// if here the chart is not initialized
	 		// checks if options are consistent
			// creates an envelop for options
			ChartEnvelop<NativeObject> envelop = new ChartEnvelop<>();
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
	 * Returns a {@link CTimer} instance inside chart.
	 * 
	 * @return the timer instance of the chart
	 */
	@Override
	public final CTimer getTimer() {
		return timer;
	}

	/**
	 * Creates a {@link CTimer} instance inside chart.<br>
	 * It can be created only once during the life cycle of the chart.
	 * 
	 * @param task the task to be executed repeatedly
	 * @param interval the time, in milliseconds (thousands of a second), the timer should delay in between executions of the specified task.<br>
	 *            Must be greater than 0.
	 */
	@Override
	public void createAndSetTimer(ChartTimerTask task, int interval) {
		// checks if timer is already created
		if (timer != null) {
			// exception!
			throw new IllegalArgumentException("Timer instance is already created and can not be overrided");
		}
		// checks if task is consistent
		if (task == null) {
			// exception!
			throw new IllegalArgumentException("Task instance is null");
		}
		// creates and stores the timer
		this.timer = new CTimer(new ChartRunnableWrapper(this, task), interval);
	}

	/**
	 * Returns <code>true</code> if the chart is configured to be drawn on the attach of DIV element, otherwise <code>false</code>.
	 * 
	 * @return the drawOnAttach <code>true</code> if the chart is configured to be drawn on the attach of DIV element, otherwise <code>false</code>.
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
	 * @return the destroyOnDetach <code>true</code> if the chart is configured to be destroyed on the detach from DIV element, otherwise <code>false</code>.
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
	 * Use this to destroy any chart instances that are created.<br>
	 * This will clean up any references stored to the chart object within Chart.js, along with any associated event listeners attached by Chart.js.
	 */
	@Override
	public final void destroy() {
		// notify before destroy
		Charts.fireBeforeDestory(this);
		// cancel the timer if exist
		if (timer != null) {
			// stops timer
			timer.stop();
		}
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
		// reset char instance
		chart = null;
	}

	/**
	 * Use this to stop any current animation loop.<br>
	 * This will pause the chart during any current animation frame.<br>
	 * Call <code>.render()</code> to re-animate.
	 */
	@Override
	public final void stop() {
		// checks if chart is created
		if (isInitialized()) {
			// then stop
			chart.stop();
		}
	}

	/**
	 * Will clear the chart canvas.
	 */
	@Override
	public final void clear() {
		// checks if chart is created
		if (isInitialized()) {
			// then clear
			chart.clear();
		}
	}

	/**
	 * Reset the chart to it's state before the initial animation.<br>
	 * A new animation can then be triggered using update.
	 */
	@Override
	public final void reset() {
		// checks if chart is created
		if (isInitialized()) {
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
		if (isInitialized()) {
			// then get the image
			return chart.toBase64Image();
		}
		// default
		return UndefinedValues.STRING;
	}

	/**
	 * Use this to manually resize the canvas element.<br>
	 * This is run each time the canvas container is resized, but can be called this method manually if you change the size of the canvas nodes container element.
	 */
	@Override
	public final void resize() {
		// checks if chart is created
		if (isInitialized()) {
			// resize!
			chart.resize();
		}
	}

	/**
	 * Use this to manually resize the canvas element.<br>
	 * This is run each time the canvas container is resized, but can be called this method manually if you change the size of the canvas nodes container element.
	 * 
	 * @param width width size of resize
	 * @param height height size of resize
	 */
	@Override
	public final void resize(int width, int height) {
		// checks if chart is created
		if (isInitialized()) {
			// resize!
			chart.resize(width, height);
		}
	}

	/**
	 * Triggers an update of the chart.<br>
	 * This can be safely called after updating the data object.<br>
	 * This will update all scales, legends, and then re-render the chart.
	 */
	@Override
	public final void update() {
		update((IsTransitionKey) null);
	}

	/**
	 * Triggers an update of the chart.<br>
	 * This can be safely called after updating the data object.<br>
	 * This will update all scales, legends, and then re-render the chart.<br>
	 * A animation mode key can be provided for the update process using a specific animation configuration.<br>
	 * This is useful when update is manually called inside an event handler and some different animation is desired.
	 * 
	 * @param mode an animation mode can be provided to indicate what should be updated and what animation configuration should be used
	 */
	@Override
	public final void update(IsTransitionKey mode) {
		// checks if chart is created
		if (isInitialized()) {
			// if mode is valid.. added check to null to avoid issue from code analysis
			if (mode != null && IsTransitionKey.isValid(mode)) {
				// then calls the update with animation mode
				chart.update(mode.value());
			} else {
				// otherwise calls the update
				chart.update();
			}
		}
	}

	/**
	 * Triggers an update of the chart.<br>
	 * This can be safely called after updating the data object.<br>
	 * This will update all scales, legends, and then re-render the chart.<br>
	 * A configuration object can be provided with additional configuration for the update process.<br>
	 * This is useful when update is manually called inside an event handler and some different animation is desired.
	 * 
	 * @param configuration a configuration object can be provided with additional configuration for the update process
	 */
	@Override
	public final void update(UpdateConfiguration configuration) {
		// checks if chart is created
		if (isInitialized()) {
			// if configuration is not passed..
			if (configuration == null) {
				// then calls the update
				chart.update();
			} else {
				// otherwise calls the update with configuration
				// stores the animation mode to animation options
				getOptions().getTransitions().set(UpdateConfiguration.UPDATE, configuration.getTransition());
				// updates the chart
				update(UpdateConfiguration.UPDATE);
			}
		}
	}

	/**
	 * Triggers an update of the chart.<br>
	 * This can be safely called after updating the data object.<br>
	 * This will update the options, mutating the options property in place.
	 */
	@Override
	public final void reconfigure() {
		reconfigure((IsTransitionKey) null);
	}

	/**
	 * Triggers an update of the chart.<br>
	 * This can be safely called after updating the data object.<br>
	 * This will update the options, mutating the options property in place.<br>
	 * A animation mode key can be provided for the update process using a specific animation configuration.<br>
	 * This is useful when update is manually called inside an event handler and some different animation is desired.
	 * 
	 * @param mode an animation mode can be provided to indicate what should be updated and what animation configuration should be used
	 */
	@Override
	public final void reconfigure(IsTransitionKey mode) {
		// checks and performs pre-reconfiguration
		if (reconfigureOptions()) {
			// if here, pre-reconfiguration has been done
			// update chart
			update(mode);
			// replaces the native object in the configuration
			updateForReconfiguring();
		}
	}

	/**
	 * Triggers an update of the chart.<br>
	 * This can be safely called after updating the data object.<br>
	 * This will update the options, mutating the options property in place.<br>
	 * A configuration object can be provided with additional configuration for the update process.<br>
	 * This is useful when update is manually called inside an event handler and some different animation is desired.
	 * 
	 * @param configuration a configuration object can be provided with additional configuration for the update process
	 */
	@Override
	public final void reconfigure(UpdateConfiguration configuration) {
		// checks and performs pre-reconfiguration
		if (reconfigureOptions()) {
			// if here, pre-reconfiguration has been done
			// update chart
			update(configuration);
			// replaces the native object in the configuration
			updateForReconfiguring();
		}
	}

	/**
	 * Prepares the chart options with the configuration ones before updating the chart, in order that new or updated options will be used by chart.
	 * 
	 * @return <code>true</code> if the chart is ready to be configured, otherwise <code>false</code>
	 */
	private boolean reconfigureOptions() {
		// checks if chart is created and consistent
		if (isInitialized() && IsChart.isConsistent(this)) {
			// invokes the apply configuration
			applyConfiguration();
			// fires that chart is configuring
			Charts.fireBeforeConfigure(this);
			// updates option passed by configuration element
			Configuration tempConfiguration = new Configuration();
			// gets options
			ConfigurationOptions internalOptions = getOptions();
			// sets options by temporary configuration
			tempConfiguration.setOptions(this, internalOptions);
			// calls plugins for onConfigure method
			Defaults.get().getPlugins().onChartConfigure(tempConfiguration, this);
			plugins.onChartConfigure(tempConfiguration, this);
			// fires that chart has been configured
			Charts.fireAfterConfigure(this);
			return true;
		}
		// if here the chart is not consistent
		return false;
	}

	/**
	 * Replaces the native object in the configuration because the chart has been reconfigured.
	 */
	private void updateForReconfiguring() {
		getOptions().setChartOptions(new ChartEnvelop<>(chart.getOptions()));
	}

	/**
	 * Triggers a redraw of all chart elements.<br>
	 * Note, this does not update elements for new data.<br>
	 * Use <code>.update()</code> in that case.
	 */
	@Override
	public final void render() {
		// checks if chart is created
		if (isInitialized()) {
			// calls the render
			chart.render();
		}
	}

	/**
	 * Looks for the data set that matches the current index.
	 * 
	 * @param index data set index
	 * @return data set item or <code>null</code> if the index is out of range of data sets count.
	 */
	@Override
	public final DatasetItem getDatasetItem(int index) {
		// get consistent chart instance
		Chart instance = lookForConsistentInstance();
		// checks consistency of chart and data sets
		if (instance != null && isValidDatasetIndex(index)) {
			// returns the array
			return new DatasetItem(new ChartEnvelop<>(instance.getDatasetMeta(index), true));
		}
		// returns null
		return null;
	}

	/**
	 * Looks for the data sets that matches the event and returns the data set items references as a list.
	 * 
	 * @param event event of chart.
	 * @return data set items references list or or an empty list.
	 */
	@Override
	public final List<DatasetReference> getDatasetAtEvent(BaseNativeEvent event) {
		// get consistent chart instance
		Chart instance = lookForConsistentInstance();
		// checks consistency of chart and event
		if (instance != null && event != null) {
			// gets data sets
			ArrayObject array = instance.getElementsAtEventForMode(event, InteractionMode.DATASET.value(), INTERACTION_MODE.nativeObject(), false);
			// returns the array
			return ArrayListHelper.unmodifiableList(array, DatasetReference.FACTORY);
		}
		// if here, chart and event not consistent then returns an empty list
		return Collections.emptyList();
	}

	/**
	 * Looks for the data set if it's visible or not, selected by index.
	 * 
	 * @param index data set index
	 * @return <code>true</code> if data set is visible otherwise <code>false</code>.
	 */
	@Override
	public final boolean isDatasetVisible(int index) {
		// get consistent chart instance
		Chart instance = lookForConsistentInstance();
		// checks consistency of chart and data sets
		if (instance != null && isValidDatasetIndex(index)) {
			// gets if data set is visible or not
			return instance.isDatasetVisible(index);
		}
		// returns false
		return false;
	}

	/**
	 * Returns the amount of data sets which are visible
	 * 
	 * @return the amount of data sets which are visible.<br>
	 *         If chart is not initialized, return {@link UndefinedValues#INTEGER}.
	 */
	@Override
	public final int getVisibleDatasetCount() {
		// get consistent chart instance
		Chart instance = lookForConsistentInstance();
		// checks consistency of chart and data sets
		if (instance != null) {
			// gets if data set is visible or not
			return instance.getVisibleDatasetCount();
		}
		// returns undefined
		return UndefinedValues.INTEGER;
	}

	/**
	 * Sets the visibility for a given data set.<br>
	 * This can be used to build a chart legend in HTML.<br>
	 * During click on one of the HTML items, you can call it to change the appropriate data set.
	 * 
	 * @param datasetIndex data set index
	 * @param visibility if <code>true</code> enables the visibility otherwise <code>false</code>
	 */
	@Override
	public final void setDatasetVisibility(int datasetIndex, boolean visibility) {
		// get consistent chart instance
		Chart instance = lookForConsistentInstance();
		// checks consistency of chart and data sets
		if (instance != null && isValidDatasetIndex(datasetIndex)) {
			// sets data set visibility
			instance.setDatasetVisibility(datasetIndex, visibility);
		}
	}

	/**
	 * Toggles the visibility of an item in all data sets.<br>
	 * A data set needs to explicitly support this feature for it to have an effect.<br>
	 * From internal chart types, doughnut / pie and polar area use this.
	 * 
	 * @param index data index
	 */
	@Override
	public final void toggleDataVisibility(int index) {
		// get consistent chart instance
		Chart instance = lookForConsistentInstance();
		// checks consistency of chart and data sets for the first data set
		if (instance != null && isValidDataIndex(0, index)) {
			// toggles data index
			instance.toggleDataVisibility(index);
		}
	}

	/**
	 * Returns the stored visibility state of an data index for all data sets.
	 * 
	 * @param index data index
	 * @return <code>true</code> if the data item is visible
	 */
	@Override
	public final boolean isDataVisible(int index) {
		// get consistent chart instance
		Chart instance = lookForConsistentInstance();
		// checks consistency of chart and datasets for the first dataset
		if (instance != null && isValidDataIndex(0, index)) {
			// returns data index visibility
			return instance.getDataVisibility(index);
		}
		// if here, the chart instance or data index is not consistent
		return false;
	}

	/**
	 * Sets the visibility for the given data set to false.<br>
	 * Updates the chart and animates the data set with 'hide' mode.<br>
	 * This animation can be configured under the hide key in animation options.
	 * 
	 * @param datasetIndex data set index
	 */
	@Override
	public final void hide(int datasetIndex) {
		// get consistent chart instance
		Chart instance = lookForConsistentInstance();
		// checks consistency of chart and datasets
		if (instance != null && isValidDatasetIndex(datasetIndex)) {
			// hides dataset
			instance.hide(datasetIndex);
		}
	}

	/**
	 * Sets the visibility for the given dataset to true.<br>
	 * Updates the chart and animates the dataset with 'show' mode.<br>
	 * This animation can be configured under the show key in animation options.
	 * 
	 * @param datasetIndex dataset index
	 */
	@Override
	public final void show(int datasetIndex) {
		// get consistent chart instance
		Chart instance = lookForConsistentInstance();
		// checks consistency of chart and datasets
		if (instance != null && isValidDatasetIndex(datasetIndex)) {
			// hides dataset
			instance.show(datasetIndex);
		}
	}

	/**
	 * Calling on your chart instance passing an argument of an event, will return the single element at the event position.<br>
	 * If there are multiple items within range, only the first is returned.
	 * 
	 * @param event event of chart.
	 * @return single element at the event position or <code>null</code> if event is not consistent
	 */
	@Override
	public final DatasetReference getElementAtEvent(BaseNativeEvent event) {
		// get consistent chart instance
		Chart instance = lookForConsistentInstance();
		// checks consistency of chart and event
		if (instance != null && event != null) {
			// gets element
			ArrayObject result = instance.getElementsAtEventForMode(event, InteractionMode.NEAREST.value(), INTERACTION_MODE.nativeObject(), false);
			if (result != null && !result.isEmpty()) {
				return DatasetReference.FACTORY.create(result.get(0));
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
	public final List<DatasetReference> getElementsAtEvent(BaseNativeEvent event) {
		// get consistent chart instance
		Chart instance = lookForConsistentInstance();
		// checks consistency of chart and event
		if (instance != null && event != null) {
			// gets elements
			ArrayObject array = instance.getElementsAtEventForMode(event, InteractionMode.INDEX.value(), INTERACTION_MODE.nativeObject(), false);
			// returns the array
			return ArrayListHelper.unmodifiableList(array, DatasetReference.FACTORY);
		}
		// if here, chart and event not consistent then returns an empty list
		return Collections.emptyList();
	}

	/**
	 * Draws the chart.<br>
	 * It can be invoked once during the life cycle of the chart.
	 */
	@Override
	public final void draw() {
		// checks if canvas is supported and the chart is consistent
		if (isCanvasSupported && IsChart.isConsistent(this)) {
			// invokes the apply configuration
			applyConfiguration();
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
			if (isInitialized()) {
				// then destroy
				chart.destroy();
			}
			// stores the chart instance in the collection
			Charts.add(this);
			// draws chart with configuration
			chart = new Chart(canvas.getContext2d(), configuration.nativeObject());
			// replaces the native object in the configuration
			updateForReconfiguring();
			// notify after init
			Charts.fireAfterInit(this);
			// cancel the timer if exist and it is
			// still in initialized status
			if (timer != null && CTimer.Status.INITIALIZED.equals(timer.getStatus())) {
				// stops timer
				timer.start();
			}
		}
	}

	/**
	 * Called before any drawing or reconfiguration in order that the chart which implements it can override options
	 */
	protected void applyConfiguration() {
		// do nothing
	}

	/**
	 * Returns <code>true</code> if the dataset index, passed as argument, is a valid index.
	 * 
	 * @param datasetIndex dataset index to check
	 * @return <code>true</code> if the dataset index, passed as argument, is a valid index
	 */
	private boolean isValidDatasetIndex(int datasetIndex) {
		return !data.getDatasets().isEmpty() && datasetIndex < data.getDatasets().size() && datasetIndex >= 0;
	}

	/**
	 * Returns <code>true</code> if the dataset index and data index, passed as arguments, are valid indexes.
	 * 
	 * @param datasetIndex dataset index to check
	 * @param index data index
	 * @return <code>true</code> if the dataset index and data index, passed as arguments, are valid indexes.
	 */
	private boolean isValidDataIndex(int datasetIndex, int index) {
		// checks if dataset index is valid
		if (isValidDatasetIndex(datasetIndex)) {
			// gets dataset instance
			Dataset dataset = data.getDatasets().get(datasetIndex);
			// get data type
			DataType dataType = dataset.getDataType();
			// checks all types of data
			if (DataType.NUMBERS.equals(dataType)) {
				// checks and returns if valid
				return isInRange(dataset.getData(), index);
			} else if (DataType.POINTS.equals(dataType) && dataset instanceof HasDataPoints) {
				// casts to interface
				HasDataPoints dataPoints = (HasDataPoints) dataset;
				// checks and returns if valid
				return isInRange(dataPoints.getDataPoints(), index);
			} else if (DataType.ARRAYS.equals(dataType) && dataset instanceof BarDataset) {
				// casts to bar dataset
				BarDataset barDataset = (BarDataset) dataset;
				// checks and returns if valid
				return isInRange(barDataset.getFloatingData(), index);
			} else if (DataType.STRINGS.equals(dataType) && dataset instanceof LineDataset) {
				// casts to line dataset
				LineDataset lineDataset = (LineDataset) dataset;
				// checks and returns if valid
				return isInRange(lineDataset.getDataString(), index);
			}
		}
		// if here, the dataset index is not valid
		return false;
	}

	/**
	 * Returns <code>true</code> if the index, passed as argument, is in range of the list.
	 * 
	 * @param data list of element to use for checking
	 * @param index index to check against the list
	 * @return <code>true</code> if the index, passed as argument, is in range of the list
	 */
	private boolean isInRange(List<?> data, int index) {
		// checks and returns if valid
		return data != null && !data.isEmpty() && index < data.size() && index >= 0;
	}

	/**
	 * Internal native object container which is mapping the interaction options needed to invoke
	 * {@link Chart#getElementsAtEventForMode(BaseNativeEvent, String, NativeObject, boolean)}.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	static final class InternalInterationModeObject extends NativeObjectContainer {

		/**
		 * Name of properties of native object.
		 */
		private enum Property implements Key
		{
			AXIS("axis"),
			INTERSECT("intersect");

			// name value of property
			private final String value;

			/**
			 * Creates with the property value to use in the native object.
			 * 
			 * @param value value of property name
			 */
			private Property(String value) {
				this.value = value;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.commons.Key#value()
			 */
			@Override
			public String value() {
				return value;
			}

		}

		/**
		 * Creates the object with a native object instance, setting the intersect passed as argument.
		 * 
		 * @param intersect if <code>true</code>, the hover mode only applies when the mouse position intersects an item on the chart
		 */
		private InternalInterationModeObject(boolean intersect) {
			super();
			// sets the intersect
			setValue(Property.INTERSECT, intersect);
		}

		/**
		 * Sets to 'x', 'y', or 'xy' to define which directions are used in calculating distances.<br>
		 * Defaults to 'x' for index mode and 'xy' in dataset and nearest modes.
		 * 
		 * @param axis define which directions are used in calculating distances.
		 */
		void setAxis(InteractionAxis axis) {
			setValue(Property.AXIS, axis);
		}

		/**
		 * Returns to 'x', 'y', or 'xy' to define which directions are used in calculating distances.
		 * 
		 * @return define which directions are used in calculating distances.
		 */
		InteractionAxis getAxis() {
			return getValue(Property.AXIS, InteractionAxis.values(), InteractionAxis.X);
		}

		/**
		 * if <code>true</code>, the hover mode only applies when the mouse position intersects an item on the chart.
		 * 
		 * @param intersect if <code>true</code>, the hover mode only applies when the mouse position intersects an item on the chart.
		 */
		final void setIntersect(boolean intersect) {
			setValue(Property.INTERSECT, intersect);
		}

		/**
		 * if <code>true</code>, the hover mode only applies when the mouse position intersects an item on the chart.
		 * 
		 * @return if <code>true</code>, the hover mode only applies when the mouse position intersects an item on the chart.
		 */
		final boolean isIntersect() {
			return getValue(Property.INTERSECT, true);
		}

		/**
		 * Returns the native object instance.
		 * 
		 * @return the native object instance.
		 */
		private NativeObject nativeObject() {
			return super.getNativeObject();
		}

	}

	/**
	 * Wrapper of a {@link ChartTimerTask} in order to to be added to a {@link CTimer}.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static final class ChartRunnableWrapper implements Runnable {

		private final IsChart chart;

		private final ChartTimerTask task;

		/**
		 * Creates the runnable wrapping a custom task.
		 * 
		 * @param chart chart instance
		 * @param task task to be wrapper
		 */
		private ChartRunnableWrapper(IsChart chart, ChartTimerTask task) {
			this.chart = chart;
			this.task = task;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			// executes the task
			task.execute(chart);
		}

	}

}