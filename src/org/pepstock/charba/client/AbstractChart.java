/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.pepstock.charba.client.commons.AbstractPoint;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.ArrayUtil;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.IsPoint;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.configuration.Axis;
import org.pepstock.charba.client.configuration.ConfigurationOptions;
import org.pepstock.charba.client.controllers.ControllerType;
import org.pepstock.charba.client.data.Data;
import org.pepstock.charba.client.data.Dataset;
import org.pepstock.charba.client.defaults.IsDefaultScaledOptions;
import org.pepstock.charba.client.defaults.chart.DefaultChartOptions;
import org.pepstock.charba.client.dom.BaseEventTarget.EventListenerCallback;
import org.pepstock.charba.client.dom.DOMBuilder;
import org.pepstock.charba.client.dom.elements.Canvas;
import org.pepstock.charba.client.dom.elements.Div;
import org.pepstock.charba.client.dom.elements.Heading;
import org.pepstock.charba.client.dom.enums.CursorType;
import org.pepstock.charba.client.dom.enums.MouseEventType;
import org.pepstock.charba.client.dom.enums.Position;
import org.pepstock.charba.client.dom.enums.Unit;
import org.pepstock.charba.client.dom.events.NativeBaseEvent;
import org.pepstock.charba.client.enums.DefaultInteractionMode;
import org.pepstock.charba.client.enums.ImageMimeType;
import org.pepstock.charba.client.events.AddHandlerEvent;
import org.pepstock.charba.client.events.ChartEventHandler;
import org.pepstock.charba.client.events.EventHandler;
import org.pepstock.charba.client.events.EventType;
import org.pepstock.charba.client.events.HandlerManager;
import org.pepstock.charba.client.events.HandlerRegistration;
import org.pepstock.charba.client.items.ActiveDatasetElement;
import org.pepstock.charba.client.items.DatasetItem;
import org.pepstock.charba.client.items.DatasetReference;
import org.pepstock.charba.client.items.InteractionOptions;
import org.pepstock.charba.client.items.Undefined;
import org.pepstock.charba.client.options.ExtendedOptions;
import org.pepstock.charba.client.options.TransitionMode;
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
public abstract class AbstractChart extends HandlerManager implements IsChart, MutationHandler, HasAxes {

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the mouse down function on canvas
	private final CallbackProxy<EventListenerCallback> canvasCallbackProxy = JsHelper.get().newCallbackProxy();

	// message to show when the browser can't support canvas
	private static final String CANVAS_NOT_SUPPORTED_MESSAGE = "Ops... Canvas element is not supported...";
	// default interaction instance for dataset
	private static final InteractionOptions DEFAULT_INTERACTION = new InteractionOptions(DefaultInteractionMode.DATASET, true);
	// PCT standard for width
	private static final int DEFAULT_WIDTH = 90;
	// PCT standard for width
	private static final int DEFAULT_HEIGHT = 100;
	// suffix label for canvas element id
	private static final String SUFFIX_CANVAS_ELEMENT_ID = "_canvas";
	// dataset item factory
	private static final DatasetItemFactory DATASET_ITEM_FACTORY = new DatasetItemFactory();
	// reference to Chart.js chart instance
	private Chart chart = null;
	// chart ID using generate unique id
	private final String id = DOMBuilder.get().createUniqueChartId();
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
	private final Data data;
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
	// draw count
	private final AtomicInteger drawCount = new AtomicInteger(0);
	// factory to create a data set reference from a native object.
	public final DatasetReferenceFactory datasetReferenceFactory;
	// timer instance
	private CTimer timer = null;
	// status if attached
	private boolean attached = false;

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
		// creates data
		this.data = new Data(new ChartEnvelop<>(this));
		// creates the DIV element
		this.element = DOMBuilder.get().createDivElement();
		// creates DIV
		this.element.setId(id);
		// sets relative position
		this.element.getStyle().setPosition(Position.RELATIVE);
		// sets default width values
		this.element.getStyle().setWidth(Unit.PCT.format(DEFAULT_WIDTH));
		this.element.getStyle().setHeight(Unit.PCT.format(DEFAULT_HEIGHT));
		// checks if canvas is supported
		if (this.isCanvasSupported) {
			// creates a canvas
			this.canvas = DOMBuilder.get().createCanvasElement();
			// set id to canvas
			this.canvas.setId(id + SUFFIX_CANVAS_ELEMENT_ID);
			// adds to panel
			this.element.appendChild(canvas);
			// -------------------------------
			// -- SET CALLBACKS to PROXIES ---
			// -------------------------------
			// sets function to proxy callback in order to invoke the java interface
			this.canvasCallbackProxy.setCallback(NativeBaseEvent::preventDefault);
			// adds the listener to disable canvas selection
			// removes the default behavior
			this.canvas.addEventListener(MouseEventType.MOUSE_DOWN, canvasCallbackProxy.getProxy());
		} else {
			// creates a header element
			Heading h = DOMBuilder.get().createHeadingElement();
			// to show the error message
			// because canvas is not supported
			h.setTextContent(CANVAS_NOT_SUPPORTED_MESSAGE);
			this.element.appendChild(h);
			// resets canvas
			this.canvas = null;
		}
		// inject Chart.js and date library if not already loaded
		ResourcesType.getResources().inject();
		// creates plugins container
		this.plugins = new Plugins();
		// creates defaults options for this chart type
		// chart type is NOT checked here but when new chart options
		// object is created
		this.options = createChartOptions();
		// returns a default option with all configuration
		// it uses the default builder and the default scaled options
		// because chart options is already a merge between global and chart global
		this.defaultChartOptions = new DefaultChartOptions(options);
		// stores initial cursor
		this.initialCursor = Utilities.getCursorOfChart(this);
		// adds chart observer to get on attach and detach
		ChartObserver.get().addHandler(this);
		// creaets reference factory
		this.datasetReferenceFactory = new DatasetReferenceFactory(this);
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

	/**
	 * Returns the draw count of the chart.
	 * 
	 * @return the draw count of the chart
	 */
	@Override
	public final int getDrawCount() {
		return drawCount.get();
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
		canvas.removeEventListener(MouseEventType.MOUSE_DOWN, canvasCallbackProxy.getProxy());
	}

	/**
	 * Returns <code>true</code> whether this chart is currently attached to the browser's document.
	 * 
	 * @return <code>true</code> whether this chart is currently attached to the browser's document
	 */
	@Override
	public final boolean isChartAttached() {
		return attached;
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
			return new DefaultChartOptions(new ChartEnvelop<>(chartOptions));
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
		Checker.assertCheck(timer == null, "Timer instance is already created and can not be overrided");
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
		if (item != null) {
			// stores is attached
			attached = true;
			// if is not to be drawn on attach, doesn't draw
			if (isDrawOnAttach()) {
				draw();
			}
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
		if (item != null) {
			// stores is not attached
			attached = false;
			// if is not to be destroyed on detach, doesn't destroy
			if (isDestroyOnDetach()) {
				// then destroy
				destroy();
			}
		}
	}

	/**
	 * Sets the active (hovered) elements for the chart.
	 * 
	 * @param elements list of active elements
	 */
	@Override
	public final void setActiveElements(List<ActiveDatasetElement> elements) {
		// checks if chart is created
		if (isInitialized()) {
			ArrayObject array = ArrayObject.fromOrEmpty(elements);
			// stores elements
			chart.setActiveElements(array);
		}
	}

	/**
	 * Sets the active (hovered) elements for the chart.
	 * 
	 * @param elements array of active elements
	 */
	@Override
	public final void setActiveElements(ActiveDatasetElement... elements) {
		// checks if chart is created
		if (isInitialized()) {
			ArrayObject array = ArrayObject.fromOrEmpty(elements);
			// stores elements
			chart.setActiveElements(array);
		}
	}

	/**
	 * Resets the active (hovered) elements for the chart.
	 */
	@Override
	public final void resetActiveElements() {
		chart.setActiveElements(ArrayObject.fromOrEmpty((NativeObjectContainer) null));
	}

	/**
	 * Returns the active (hovered) elements for the chart.
	 * 
	 * @return the array of active elements
	 */
	@Override
	public final List<ActiveDatasetElement> getActiveElements() {
		// checks if chart is created
		if (isInitialized()) {
			ArrayObject array = chart.getActiveElements();
			// returns the array
			return ArrayListHelper.list(array, ActiveDatasetElement.FACTORY);
		}
		// if here the chart is not initialized
		// then returns an empty list
		return Collections.emptyList();
	}

	/**
	 * Sets the active tooltip elements for the chart.
	 * 
	 * @param elements list of active tooltip elements
	 */
	@Override
	public final void setTooltipActiveElements(List<ActiveDatasetElement> elements) {
		setTooltipActiveElements(null, elements);
	}

	/**
	 * Sets the active tooltip elements for the chart.
	 * 
	 * @param point synthetic event position used in positioning
	 * @param elements list of active tooltip elements
	 */
	@Override
	public final void setTooltipActiveElements(IsPoint point, List<ActiveDatasetElement> elements) {
		ArrayObject array = ArrayObject.fromOrEmpty(elements);
		// stores elements
		setTooltipActiveElements(point, array);
	}

	/**
	 * Sets the active tooltip elements for the chart.
	 * 
	 * @param elements array of active tooltip elements
	 */
	@Override
	public final void setTooltipActiveElements(ActiveDatasetElement... elements) {
		setTooltipActiveElements(null, elements);
	}

	/**
	 * Sets the active tooltip elements for the chart.
	 * 
	 * @param point synthetic event position used in positioning
	 * @param elements array of active tooltip elements
	 */
	@Override
	public final void setTooltipActiveElements(IsPoint point, ActiveDatasetElement... elements) {
		ArrayObject array = ArrayObject.fromOrEmpty(elements);
		// stores elements
		setTooltipActiveElements(point, array);
	}

	/**
	 * Sets the active tooltip elements for the chart.
	 * 
	 * @param point synthetic event position used in positioning
	 * @param elements array of active tooltip elements
	 */
	private void setTooltipActiveElements(IsPoint point, ArrayObject elements) {
		// checks if chart is created
		if (isInitialized()) {
			NativeObject nativePoint = null;
			// checks if point is consistent
			if (point != null && point.isConsistent()) {
				// gets inner point
				ActiveElementsPoint innerPoint = new ActiveElementsPoint(point.getX(), point.getY());
				// stores native point
				nativePoint = innerPoint.nativeObject();
			}
			// stores elements
			JsChartHelper.get().setTooltipActiveElements(chart, elements, nativePoint);
		}
	}

	/**
	 * Resets the active tooltip elements for the chart.
	 */
	@Override
	public final void resetTooltipActiveElements() {
		JsChartHelper.get().setTooltipActiveElements(chart, ArrayObject.fromOrEmpty((NativeObjectContainer) null), null);
	}

	/**
	 * Returns the active tooltip elements for the chart.
	 * 
	 * @return the array of active tooltip elements
	 */
	@Override
	public final List<ActiveDatasetElement> getTooltipActiveElements() {
		// checks if chart is created
		if (isInitialized()) {
			ArrayObject array = JsChartHelper.get().getTooltipActiveElements(chart);
			// returns the array
			return ArrayListHelper.list(array, ActiveDatasetElement.FACTORY);
		}
		// if here the chart is not initialized
		// then returns an empty list
		return Collections.emptyList();
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
	 * @param type indicating the image format
	 * @param encoderOptions between 0 and 1 indicating the image quality to use for image formats that use lossy compression.<br>
	 *            If this argument is anything else, the default value for image quality is used. The default value is 0.92.
	 * @return base 64 image or {@link Undefined#STRING} if chart is not initialized.
	 */
	@Override
	public final String toBase64Image(ImageMimeType type, double encoderOptions) {
		// checks if chart is created
		if (isInitialized()) {
			// checks if the type is consistent
			ImageMimeType checkedImageMimeType = Key.isValid(type) ? type : ImageMimeType.PNG;
			// gets the data from canvas
			// then get the image
			return chart.toBase64Image(checkedImageMimeType.value(), Checker.betweenOrDefault(encoderOptions, 0, 1, Canvas.DEFAULT_ENCODER_OPTIONS));
		}
		// default
		return Undefined.STRING;
	}

	/**
	 * Use this to manually resize the canvas element.<br>
	 * This is run each time the canvas container is resized, but can be called this method manually if you change the size of the canvas nodes container element.
	 */
	@Override
	public final void resize() {
		// checks if chart is created
		if (isInitialized()) {
			// increments draw count
			drawCount.incrementAndGet();
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
			// increments draw count
			drawCount.incrementAndGet();
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
		update((TransitionMode) null);
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
	public final void update(TransitionMode mode) {
		// checks if chart is created
		if (isInitialized()) {
			// invokes the apply configuration
			applyConfiguration();
			// increments draw count
			drawCount.incrementAndGet();
			// if mode is valid.. added check to null to avoid issue from code analysis
			if (mode != null && TransitionMode.isValid(mode)) {
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
				// invokes the apply configuration
				applyConfiguration();
				// increments draw count
				drawCount.incrementAndGet();
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
		reconfigure((TransitionMode) null);
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
	public final void reconfigure(TransitionMode mode) {
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
			// increments draw count
			drawCount.incrementAndGet();
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
			return DATASET_ITEM_FACTORY.create(instance.getDatasetMeta(index));
		}
		// returns null
		return null;
	}

	/**
	 * Returns an array of all the dataset items in the order that they are drawn on the canvas that are not hidden.
	 * 
	 * @return an array of all the dataset items in the order that they are drawn on the canvas that are not hidden.
	 */
	@Override
	public List<DatasetItem> getSortedVisibleDatasetItems() {
		// get consistent chart instance
		Chart instance = lookForConsistentInstance();
		// checks consistency of chart and data sets
		if (instance != null) {
			// returns the array
			return ArrayListHelper.unmodifiableList(chart.getSortedVisibleDatasetMetas(), DATASET_ITEM_FACTORY);
		}
		// returns empty list
		return Collections.emptyList();
	}

	/**
	 * Looks for the data sets that matches the event and returns the data set items references as a list.
	 * 
	 * @param event event of chart.
	 * @return data set items references list or or an empty list.
	 */
	@Override
	public final List<DatasetReference> getDatasetAtEvent(NativeBaseEvent event) {
		return getElementsAtEvent(event, DEFAULT_INTERACTION);
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
	 *         If chart is not initialized, return {@link Undefined#INTEGER}.
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
		return Undefined.INTEGER;
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
			// increments draw count
			drawCount.incrementAndGet();
			// hides dataset
			instance.hide(datasetIndex);
		}
	}

	/**
	 * Sets the hidden flag of that element index to <code>true</code> and updates the chart.
	 * 
	 * @param datasetIndex dataset index
	 * @param dataIndex data index
	 */
	@Override
	public final void hide(int datasetIndex, int dataIndex) {
		// get consistent chart instance
		Chart instance = lookForConsistentInstance();
		// checks consistency of chart and indexes
		if (instance != null && isValidDatasetIndex(datasetIndex) && dataIndex >= 0) {
			// increments draw count
			drawCount.incrementAndGet();
			// hides data at data index
			instance.hide(datasetIndex, dataIndex);
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
			// increments draw count
			drawCount.incrementAndGet();
			// hides dataset
			instance.show(datasetIndex);
		}
	}

	/**
	 * Sets the hidden flag of that element index to <code>false</code> and updates the chart.
	 * 
	 * @param datasetIndex dataset index
	 * @param dataIndex data index
	 */
	@Override
	public final void show(int datasetIndex, int dataIndex) {
		// get consistent chart instance
		Chart instance = lookForConsistentInstance();
		// checks consistency of chart and indexes
		if (instance != null && isValidDatasetIndex(datasetIndex) && dataIndex >= 0) {
			// increments draw count
			drawCount.incrementAndGet();
			// shows data at data index
			instance.show(datasetIndex, dataIndex);
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
	public final DatasetReference getElementAtEvent(NativeBaseEvent event) {
		return getElementAtEvent(event, null);
	}

	/**
	 * Calling on your chart instance passing an argument of an event, will return the single element at the event position.<br>
	 * If there are multiple items within range, only the first is returned.
	 * 
	 * @param event event of chart.
	 * @param interaction how the elements will be checked.
	 * @return single element at the event position or <code>null</code> if event is not consistent
	 */
	@Override
	public final DatasetReference getElementAtEvent(NativeBaseEvent event, InteractionOptions interaction) {
		// get consistent chart instance
		Chart instance = lookForConsistentInstance();
		// checks if interaction is consistent
		InteractionOptions item = interaction != null ? interaction : getOptions().getInteraction().create();
		// checks consistency of chart and event
		if (instance != null && event != null) {
			// gets element
			ArrayObject result = instance.getElementsAtEventForMode(event, item.getMode().value(), item.nativeObject(), false);
			if (result != null && !result.isEmpty()) {
				return getDatasetReferenceFactory().create(result.get(0));
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
	public final List<DatasetReference> getElementsAtEvent(NativeBaseEvent event) {
		return getElementsAtEvent(event, DEFAULT_INTERACTION);
	}

	/**
	 * Looks for the element under the event point, then returns all elements at the same data index.<br>
	 * Calling it on your chart instance passing an argument of an event, will return the point elements that are at that the same position of that event.
	 * 
	 * @param event event of chart.
	 * @param interaction how the elements will be checked.
	 * @return all elements at the same data index or an empty list.
	 */
	@Override
	public final List<DatasetReference> getElementsAtEvent(NativeBaseEvent event, InteractionOptions interaction) {
		// get consistent chart instance
		Chart instance = lookForConsistentInstance();
		// checks if interaction is consistent
		InteractionOptions item = interaction != null ? interaction : getOptions().getInteraction().create();
		// checks consistency of chart and event
		if (instance != null && event != null) {
			// gets elements
			ArrayObject array = instance.getElementsAtEventForMode(event, item.getMode().value(), item.nativeObject(), false);
			// returns the array
			return ArrayListHelper.unmodifiableList(array, getDatasetReferenceFactory());
		}
		// if here, chart and event not consistent then returns an empty list
		return Collections.emptyList();
	}

	/**
	 * Returns the factory to create {@link DatasetReference} when required, based on the data element type of the chart.
	 * 
	 * @return the factory to create {@link DatasetReference} when required, based on the data element type of the chart
	 */
	@Override
	public final NativeObjectContainerFactory<DatasetReference> getDatasetReferenceFactory() {
		return datasetReferenceFactory;
	}

	/**
	 * Check if a plugin with the specific ID is registered and enabled.
	 * 
	 * @param pluginId the ID of the plugin of which to check if it is enabled
	 * @return boolean returns true if plugin is registered and enabled
	 */
	@Override
	public final boolean isPluginEnabled(String pluginId) {
		// get consistent chart instance
		Chart instance = lookForConsistentInstance();
		// checks consistency of chart and event
		if (instance != null) {
			// gets plugin status
			return chart.isPluginEnabled(pluginId);
		}
		// if here, chart not consistent then returns false
		return false;
	}

	/**
	 * Draws the chart.<br>
	 * It can be invoked once during the life cycle of the chart.
	 */
	@Override
	public final void draw() {
		// checks if canvas is supported and the element is attached
		if (isCanvasSupported && isChartAttached()) {
			// checks if consistent
			if (!isInitialized() && IsChart.isConsistent(this)) {
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
				// increments draw count
				drawCount.incrementAndGet();
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
			} else if (isInitialized()) {
				// increments draw count
				drawCount.incrementAndGet();
				// if here, the chart is already initialized
				// and then draw it again.
				chart.draw();
			}
		}
	}

	/**
	 * Called before any drawing or reconfiguration in order that the chart which implements it can override options
	 */
	protected void applyConfiguration() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#checkDatasets(org.pepstock.charba.client.data.Dataset[])
	 */
	@Override
	public final void checkDatasets(Dataset... datasets) {
		// checks if argument is consistent
		if (ArrayUtil.isNotEmpty(datasets)) {
			// checks the amount of datasets
			Checker.checkIfBetween(datasets.length, 0, getMaximumDatasetsCount(), "Datasets size");
			// scans datasets
			for (Dataset dataset : datasets) {
				// checks the type of dataset
				Checker.assertCheck(checkDataset(dataset), "Dataset '" + dataset.getType().value() + "' is not manageable by a '" + getType().value() + "' chart");
			}
		}
	}

	/**
	 * Returns <code>true</code> if the dataset can be managed by a this chart type.
	 * 
	 * @param dataset dataset to check
	 * @return <code>true</code> if the dataset can be managed by a this chart type
	 */
	protected abstract boolean checkDataset(Dataset dataset);

	/**
	 * Returns the maximum amount of datasets that the chart can manage.
	 * 
	 * @return the maximum amount of datasets that the chart can manage.
	 */
	protected int getMaximumDatasetsCount() {
		return Integer.MAX_VALUE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#checkAxes(org.pepstock.charba.client.configuration.Axis[])
	 */
	@Override
	public void checkAxes(Axis... axes) {
		// checks if argument is consistent
		if (ArrayUtil.isNotEmpty(axes)) {
			// checks the amount of axes
			Checker.checkIfBetween(axes.length, 0, getMaximumAxesCount(), "Axes size");
			// scans axes
			for (Axis axis : axes) {
				// checks the type of axis
				Checker.assertCheck(checkAxis(axis), "Axis '" + axis.getType().value() + "' is not manageable by a '" + getType().value() + "' chart");
			}
		}
	}

	/**
	 * Returns the maximum amount of axes that the chart can manage.
	 * 
	 * @return the maximum amount of axes that the chart can manage.
	 */
	protected int getMaximumAxesCount() {
		return Integer.MAX_VALUE;
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
			// gets data count
			int dataCount = dataset.getDataCount();
			// checks and returns in the range
			return dataCount > 0 && index >= 0 && index < dataCount;
		}
		// if here, the dataset index is not valid
		return false;
	}

	/**
	 * Maps a point needed when {@link AbstractChart#setTooltipActiveElements(IsPoint, ArrayObject)} is invoked.
	 * 
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class ActiveElementsPoint extends AbstractPoint {

		/**
		 * Creates an object with X and Y coordinates of the point
		 * 
		 * @param x the X coordinate of the point
		 * @param y the Y coordinate of the point
		 */
		private ActiveElementsPoint(double x, double y) {
			super(x, y);
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

	/**
	 * Inner class to create data set reference item by a native object.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	private static class DatasetReferenceFactory implements NativeObjectContainerFactory<DatasetReference> {

		private final IsChart chart;

		/**
		 * To avoid any instantiation
		 * 
		 * @param chart chart instance
		 */
		private DatasetReferenceFactory(IsChart chart) {
			this.chart = chart;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		public DatasetReference create(NativeObject nativeObject) {
			// creates envelop
			ChartEnvelop<NativeObject> envelop = new ChartEnvelop<>(nativeObject, false);
			return new DatasetReference(chart, envelop);
		}
	}

	/**
	 * Inner class to create data set item by a native object.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	private static class DatasetItemFactory implements NativeObjectContainerFactory<DatasetItem> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		public DatasetItem create(NativeObject nativeObject) {
			// creates envelop
			ChartEnvelop<NativeObject> envelop = new ChartEnvelop<>(nativeObject, true);
			// creates and returns the item
			return new DatasetItem(envelop);
		}
	}
}