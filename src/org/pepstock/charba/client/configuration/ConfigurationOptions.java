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
package org.pepstock.charba.client.configuration;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.pepstock.charba.client.Chart;
import org.pepstock.charba.client.Configuration;
import org.pepstock.charba.client.ConfigurationElement;
import org.pepstock.charba.client.Envelop;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.ScaleType;
import org.pepstock.charba.client.callbacks.CallbackFunctionContext;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.ConfigurationLoader;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Merger;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultScaledOptions;
import org.pepstock.charba.client.dom.BaseEventTarget.EventListenerCallback;
import org.pepstock.charba.client.dom.BaseEventTypes;
import org.pepstock.charba.client.dom.BaseNativeEvent;
import org.pepstock.charba.client.enums.ChartEventProperty;
import org.pepstock.charba.client.enums.Event;
import org.pepstock.charba.client.events.AddHandlerEvent;
import org.pepstock.charba.client.events.AxisClickEvent;
import org.pepstock.charba.client.events.ChartClickEvent;
import org.pepstock.charba.client.events.ChartEventContext;
import org.pepstock.charba.client.events.ChartHoverEvent;
import org.pepstock.charba.client.events.ChartResizeEvent;
import org.pepstock.charba.client.events.DatasetSelectionEvent;
import org.pepstock.charba.client.events.EventType;
import org.pepstock.charba.client.events.RemoveHandlerEvent;
import org.pepstock.charba.client.events.TitleClickEvent;
import org.pepstock.charba.client.items.DatasetReferenceItem;
import org.pepstock.charba.client.items.DatasetReferenceItem.DatasetReferenceItemFactory;
import org.pepstock.charba.client.items.ScaleItem;
import org.pepstock.charba.client.items.ScalesNode;
import org.pepstock.charba.client.items.SizeItem;
import org.pepstock.charba.client.items.UndefinedValues;
import org.pepstock.charba.client.options.ExtendedOptions;

import jsinterop.annotations.JsFunction;

/**
 * Base object which maps chart configuration.<br>
 * Charba stores the unique chart ID into CHART.JS chart options using <code>charbaId</code> property key.<br>
 * Important topics to take care:<br>
 * <b> Responsive </b><br>
 * When it comes to change the chart size based on the window size, a major limitation is that the canvas render size (canvas.width and .height) can not be expressed with relative
 * values, contrary to the display size (canvas.style.width and .height). Furthermore, these sizes are independent from each other and thus the canvas render size does not adjust
 * automatically based on the display size, making the rendering inaccurate.<br>
 * It provides a few options to enable responsiveness and control the resize behavior of charts by detecting when the canvas display size changes and update the render size
 * accordingly.<br>
 * <b> Legend </b><br>
 * Sometimes you need a very complex legend. In these cases, it makes sense to generate an HTML legend. Charts provide a generateLegend() method on their prototype that returns an
 * HTML string for the legend. To configure how this legend is generated, you can set the legendCallback.<br>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class ConfigurationOptions extends ConfigurationContainer<ExtendedOptions> implements ConfigurationElement, IsEventProvider {

	// list of click event handler types
	private static final List<EventType> CHART_CLICK_TYPES = Collections.unmodifiableList(Arrays.asList(DatasetSelectionEvent.TYPE, ChartClickEvent.TYPE, TitleClickEvent.TYPE, AxisClickEvent.TYPE));

	// ---------------------------
	// -- JAVASCRIPT FUNCTIONS ---
	// ---------------------------

	/**
	 * Java script FUNCTION callback when a event on chart is triggered.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyChartEventCallback {

		/**
		 * Method of function to be called when a event on chart is triggered.
		 * 
		 * @param context value of <code>this</code> to the execution context of function.
		 * @param event CHART.JS event which wraps the native event
		 * @param items array of chart elements affected by the event
		 * @param chart chart instance
		 */
		void call(CallbackFunctionContext context, NativeObject event, ArrayObject items, Chart chart);
	}

	/**
	 * Java script FUNCTION callback when a resize event on chart is triggered.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyChartResizeCallback {

		/**
		 * Method of function to be called when a resize event on chart is triggered.
		 * 
		 * @param context value of <code>this</code> to the execution context of function.
		 * @param chart java script chart instance
		 * @param size new size of chart
		 */
		void call(CallbackFunctionContext context, Chart chart, NativeObject size);
	}

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------

	// callback proxy to invoke the resize function
	private final CallbackProxy<ProxyChartResizeCallback> resizeCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the click function
	private final CallbackProxy<ProxyChartEventCallback> clickCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the hover function
	private final CallbackProxy<ProxyChartEventCallback> hoverCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the click function for title element
	private final CallbackProxy<EventListenerCallback> titleClickCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the hover function for axis element
	private final CallbackProxy<EventListenerCallback> axisClickCallbackProxy = JsHelper.get().newCallbackProxy();

	private final Animation animation;

	private final Legend legend;

	private final Title title;

	private final Tooltips tooltips;

	private final Hover hover;

	private final Layout layout;

	private final Elements elements;

	private final Plugins plugins;

	// amount of dataset selection event handlers
	private int onDatasetSelectionHandlers = 0;
	// amount of click event handlers
	private int onClickHandlers = 0;
	// amount of hover event handlers
	private int onHoverHandlers = 0;
	// amount of resize event handlers
	private int onResizeHandlers = 0;
	// amount of resize event handlers
	private int onTitleClickHandlers = 0;
	// amount of resize event handlers
	private int onAxisClickHandlers = 0;

	// factory to transform a native object into a dataset reference item
	private final DatasetReferenceItemFactory datasetItemFactory = new DatasetReferenceItemFactory();

	/**
	 * Builds the object storing the chart instance and defaults options.
	 * 
	 * @param chart chart instance
	 * @param defaultValues defaults options
	 */
	ConfigurationOptions(IsChart chart, IsDefaultScaledOptions defaultValues) {
		// uses the extended option internally (no override)
		super(chart, new ExtendedOptions(chart, defaultValues));
		// registers as event handler
		IsEventProvider.register(chart, this);
		// creates all sub elements
		animation = new Animation(chart, getConfiguration());
		elements = new Elements(getConfiguration());
		legend = new Legend(chart, getConfiguration());
		title = new Title(getConfiguration());
		layout = new Layout(getConfiguration());
		hover = new Hover(getConfiguration());
		plugins = new Plugins(getConfiguration());
		tooltips = new Tooltips(chart, getConfiguration());
		// sets charba ID
		getConfiguration().setCharbaId(chart.getId());

		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		clickCallbackProxy.setCallback((context, event, items, nativeChart) -> {
			// creates a event context
			ChartEventContext eventContext = new ChartEventContext(event);
			// gets the native event
			BaseNativeEvent nativeEvent = eventContext.getNativeEvent();
			// handle click event for dataset
			handleDatasetSelection(nativeEvent);
			// fires the click event on the chart
			getChart().fireEvent(new ChartClickEvent(eventContext, ArrayListHelper.unmodifiableList(items, datasetItemFactory)));
		});
		// fires the hover hover on the chart
		hoverCallbackProxy.setCallback((context, event, items, nativeChart) -> {
			// creates a event context
			ChartEventContext eventContext = new ChartEventContext(event);
			// fires the hover event on the chart
			getChart().fireEvent(new ChartHoverEvent(eventContext, ArrayListHelper.unmodifiableList(items, datasetItemFactory)));
		});
		resizeCallbackProxy.setCallback((context, nativeChart, size) -> {
			// creates a event context
			ChartEventContext eventContext = new ChartEventContext(nativeChart);
			// fires the resize event on chart
			getChart().fireEvent(new ChartResizeEvent(eventContext, new SizeItem(size)));
		});
		// ---------------------------------------------------
		// -- SET CALLBACKS for title and axis click event ---
		// ---------------------------------------------------
		// fires the event
		titleClickCallbackProxy.setCallback((context, event) -> handleTitleSelection(event));
		// fires the event
		axisClickCallbackProxy.setCallback((context, event) -> handleScaleSelection(event));
	}

	/**
	 * Merges chart default options (by chart.defaults[type]), default scale options (by chart.defaults.scale) and global options (by chart.defaults.global) and chart options.<br>
	 * The chain of priority is:<br>
	 * <ul>
	 * <li>chart options
	 * <li>chart default options (by chart.defaults[type])
	 * <li>default scale options (by chart.defaults.scale)
	 * <li>global options (by chart.defaults.global)
	 * </ul>
	 * 
	 * @param envelop the envelop for options as native options
	 */
	public final void loadOptions(Envelop<NativeObject> envelop) {
		Merger.get().load(getChart(), getConfiguration(), envelop);
	}

	/**
	 * Returns the animation element.
	 * 
	 * @return the animation
	 */
	public Animation getAnimation() {
		return animation;
	}

	/**
	 * Returns the hover element.
	 * 
	 * @return the hover
	 */
	public Hover getHover() {
		return hover;
	}

	/**
	 * Returns the layout element.
	 * 
	 * @return the layout
	 */
	public Layout getLayout() {
		return layout;
	}

	/**
	 * Returns the legend element.
	 * 
	 * @return the legend
	 */
	public Legend getLegend() {
		return legend;
	}

	/**
	 * Returns the title element.
	 * 
	 * @return the title
	 */
	public Title getTitle() {
		return title;
	}

	/**
	 * Returns the tooltips element.
	 * 
	 * @return the tooltips
	 */
	public Tooltips getTooltips() {
		return tooltips;
	}

	/**
	 * Returns the elements element.
	 * 
	 * @return the elements
	 */
	public Elements getElements() {
		return elements;
	}

	/**
	 * Returns the plugins element.
	 * 
	 * @return the plugins
	 */
	public Plugins getPlugins() {
		return plugins;
	}

	/**
	 * Returns the axis by the unique CHARBA id of scale or <code>null</code> if not axis.
	 * 
	 * @param id the unique CHARBA id of scale
	 * @return the axis or <code>null</code> if not axis.
	 */
	abstract Axis getAxisById(int id);

	/**
	 * Sets the browser events that the chart should listen to for tooltips and hovering.
	 * 
	 * @param events the browser events that the chart should listen to for tooltips and hovering.
	 */
	public void setEvents(Event... events) {
		getConfiguration().setEvents(events);
	}

	/**
	 * Returns the browser events that the chart should listen to for tooltips and hovering.
	 * 
	 * @return the browser events that the chart should listen to for tooltips and hovering.
	 */
	public List<Event> getEvents() {
		return getConfiguration().getEvents();
	}

	/**
	 * Sets the resizing of the chart canvas when its container does.
	 * 
	 * @param responsive the resizing of the chart canvas when its container does.
	 */
	public void setResponsive(boolean responsive) {
		getConfiguration().setResponsive(responsive);
	}

	/**
	 * Returns the resizing of the chart canvas when its container does.
	 * 
	 * @return the resizing of the chart canvas when its container does.
	 */
	public boolean isResponsive() {
		return getConfiguration().isResponsive();
	}

	/**
	 * Sets the maintaining of the original canvas aspect ratio (width / height) when resizing.
	 * 
	 * @param maintainAspectRatio the maintaining of the original canvas aspect ratio (width / height) when resizing.
	 */
	public void setMaintainAspectRatio(boolean maintainAspectRatio) {
		getConfiguration().setMaintainAspectRatio(maintainAspectRatio);
	}

	/**
	 * Returns the the maintaining of the original canvas aspect ratio (width / height) when resizing.
	 * 
	 * @return the maintaining of the original canvas aspect ratio (width / height) when resizing.
	 */
	public boolean isMaintainAspectRatio() {
		return getConfiguration().isMaintainAspectRatio();
	}

	/**
	 * Canvas aspect ratio (i.e. width / height, a value of 1 representing a square canvas).<br>
	 * Note that this option is ignored if the height is explicitly defined either as attribute or via the style.
	 * 
	 * @param ratio the aspect ratio.
	 */
	public void setAspectRatio(double ratio) {
		getConfiguration().setAspectRatio(ratio);
	}

	/**
	 * Canvas aspect ratio (i.e. width / height, a value of 1 representing a square canvas).<br>
	 * Note that this option is ignored if the height is explicitly defined either as attribute or via the style.
	 * 
	 * @return the aspect ratio.
	 */
	public double getAspectRatio() {
		return getConfiguration().getAspectRatio();
	}

	/**
	 * The chart's canvas will use a 1:1 pixel ratio, unless the physical display has a higher pixel ratio (e.g. Retina displays). Setting devicePixelRatio to a value other than 1
	 * will force the canvas size to be scaled by that amount.
	 * 
	 * @param ratio the pixel ratio.
	 */
	public void setDevicePixelRatio(double ratio) {
		getConfiguration().setDevicePixelRatio(ratio);
	}

	/**
	 * The chart's canvas will use a 1:1 pixel ratio, unless the physical display has a higher pixel ratio (e.g. Retina displays). Setting devicePixelRatio to a value other than 1
	 * will force the canvas size to be scaled by that amount. Returns the pixel ratio.
	 * 
	 * @return the pixel ratio..
	 */
	public double getDevicePixelRatio() {
		return getConfiguration().getDevicePixelRatio();
	}

	/**
	 * Returns <code>true</code> if the chart is configured to be drawn on the attach of DIV element, otherwise <code>false</code>.
	 * 
	 * @return the drawOnAttach <code>true</code> if the chart is configured to be drawn on the attach of DIV element, otherwise <code>false</code>. Default is <code>true</code>.
	 */
	public boolean isDrawOnAttach() {
		return getConfiguration().isDrawOnAttach();
	}

	/**
	 * Sets <code>true</code> if the chart is configured to be draw on the attach of DIV element, otherwise <code>false</code>.
	 * 
	 * @param drawOnAttach the drawOnAttach to set
	 */
	public void setDrawOnAttach(boolean drawOnAttach) {
		getConfiguration().setDrawOnAttach(drawOnAttach);
	}

	/**
	 * Returns <code>true</code> if the chart is configured to be destroyed on the detach from DIV element, otherwise <code>false</code>.
	 * 
	 * @return the destroyOnDetach <code>true</code> if the chart is configured to be destroyed on the detach from DIV element, otherwise <code>false</code>. Default is
	 *         <code>true</code>.
	 */
	public boolean isDestroyOnDetach() {
		return getConfiguration().isDestroyOnDetach();
	}

	/**
	 * Sets <code>true</code> if the chart is configured to be destroyed on the detach from DIV element, otherwise <code>false</code>.
	 * 
	 * @param destroyOnDetach the destroyOnDetach to set
	 */
	public void setDestroyOnDetach(boolean destroyOnDetach) {
		getConfiguration().setDestroyOnDetach(destroyOnDetach);
	}

	/**
	 * Returns <code>true</code> if there is any dataset selection handler, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if there is any dataset selection handler, otherwise <code>false</code>.
	 */
	public final boolean hasDatasetSelectionHandlers() {
		return onDatasetSelectionHandlers > 0;
	}

	/**
	 * Returns <code>true</code> if there is any title click handler, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if there is any title click handler, otherwise <code>false</code>.
	 */
	public final boolean hasTitleClickHandlers() {
		return onTitleClickHandlers > 0;
	}

	/**
	 * Returns <code>true</code> if there is any axis click handler, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if there is any axis click handler, otherwise <code>false</code>.
	 */
	public final boolean hasAxisClickHandlers() {
		return onAxisClickHandlers > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.events.AddHandlerEventHandler#onAdd(org.pepstock.charba.client.events.AddHandlerEvent)
	 */
	@Override
	public final void onAdd(AddHandlerEvent event) {
		// checks if type of added event handler is dataset selection or click
		if (event.isRecognize(CHART_CLICK_TYPES)) {
			// if there is not any click event handler
			if (onClickHandlers == 0) {
				// sets the callback proxy in order to call the user event interface
				getConfiguration().setEvent(ChartEventProperty.ON_CLICK, clickCallbackProxy.getProxy());
			}
			// increments amount of handlers
			onClickHandlers++;
			// check if a dataset selection handler has been added
			if (event.isRecognize(DatasetSelectionEvent.TYPE)) {
				// increments handlers of dataset selection
				onDatasetSelectionHandlers++;
			}
			// check if a title click handler has been added
			if (event.isRecognize(TitleClickEvent.TYPE)) {
				// checks if is the first one in order to add the listeners to the canvas
				if (onTitleClickHandlers == 0) {
					// adds listener
					getChart().getCanvas().addEventListener(BaseEventTypes.CLICK, titleClickCallbackProxy.getProxy());
				}
				// increments handlers of title click
				onTitleClickHandlers++;
			}
			// check if a axis click handler has been added
			if (event.isRecognize(AxisClickEvent.TYPE)) {
				// checks if is the first one in order to add the listeners to the canvas
				if (onAxisClickHandlers == 0) {
					// adds listener
					getChart().getCanvas().addEventListener(BaseEventTypes.CLICK, axisClickCallbackProxy.getProxy());
				}
				// increments handlers of axis click
				onAxisClickHandlers++;
			}
		} else if (event.isRecognize(ChartHoverEvent.TYPE)) {
			// if there is not any hover event handler
			if (onHoverHandlers == 0) {
				// sets the callback proxy in order to call the user event interface
				getConfiguration().setEvent(ChartEventProperty.ON_HOVER, hoverCallbackProxy.getProxy());
			}
			// increments amount of handlers
			onHoverHandlers++;
		} else if (event.isRecognize(ChartResizeEvent.TYPE)) {
			// if there is not any resize event handler
			if (onResizeHandlers == 0) {
				// sets the callback proxy in order to call the user event interface
				getConfiguration().setEvent(ChartEventProperty.ON_RESIZE, resizeCallbackProxy.getProxy());
			}
			// increments amount of handlers
			onResizeHandlers++;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.events.RemoveHandlerEventHandler#onRemove(org.pepstock.charba.client.events. RemoveHandlerEvent)
	 */
	@Override
	public final void onRemove(RemoveHandlerEvent event) {
		// checks if type of removed event handler is dataset selection or click
		if (event.isRecognize(CHART_CLICK_TYPES)) {
			// decrements the amount of handlers
			onClickHandlers--;
			// if there is not any handler
			if (onClickHandlers == 0) {
				// removes the java script object
				getConfiguration().setEvent(ChartEventProperty.ON_CLICK, null);
			}
			// check if a dataset selection handler has been removed
			if (event.isRecognize(DatasetSelectionEvent.TYPE)) {
				// decrements handlers of dataset selection
				onDatasetSelectionHandlers--;
			}
			// check if a title click handler has been removed
			if (event.isRecognize(TitleClickEvent.TYPE)) {
				// decrements handlers of title click
				onTitleClickHandlers--;
				// checks if no handlers are defined
				if (onTitleClickHandlers == 0) {
					// removes listener
					getChart().getCanvas().removeEventListener(BaseEventTypes.CLICK, titleClickCallbackProxy.getProxy());
				}
			}
			// check if a axis click handler has been removed
			if (event.isRecognize(AxisClickEvent.TYPE)) {
				// decrements handlers of axis click
				onAxisClickHandlers--;
				// checks if no handlers are defined
				if (onAxisClickHandlers == 0) {
					// removes listener
					getChart().getCanvas().removeEventListener(BaseEventTypes.CLICK, axisClickCallbackProxy.getProxy());
				}
			}
		} else if (event.isRecognize(ChartHoverEvent.TYPE)) {
			// decrements the amount of handlers
			onHoverHandlers--;
			// if there is not any handler
			if (onHoverHandlers == 0) {
				// removes the java script object
				getConfiguration().setEvent(ChartEventProperty.ON_HOVER, null);
			}
		} else if (event.isRecognize(ChartResizeEvent.TYPE)) {
			// decrements the amount of handlers
			onResizeHandlers--;
			// if there is not any handler
			if (onResizeHandlers == 0) {
				// removes the java script object
				getConfiguration().setEvent(ChartEventProperty.ON_RESIZE, null);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.ConfigurationElement#load(org.pepstock.charba.client.IsChart, org.pepstock.charba.client.Configuration)
	 */
	@Override
	public void load(IsChart chart, Configuration configuration) {
		// loads the native object into configuration to pass to chart
		ConfigurationLoader.loadOptions(configuration, getConfiguration());
	}

	/**
	 * Check if the click event on chart and manage it fire a CHARBA dataset selection event.
	 * 
	 * @param event event generated on chart
	 */
	private void handleDatasetSelection(BaseNativeEvent event) {
		// gets the dataset items by event
		DatasetReferenceItem item = getChart().getElementAtEvent(event);
		// if the item is consistent and there is any handler
		if (item != null && hasDatasetSelectionHandlers()) {
			// fires the event for dataset selection
			getChart().fireEvent(new DatasetSelectionEvent(event, item));
		}
	}

	/**
	 * Check if the click event on chart and manage it fire a CHARBA title selection event.
	 * 
	 * @param event event generated on chart
	 */
	private void handleTitleSelection(BaseNativeEvent event) {
		// checks if title has been selected and there is any handler
		if (hasTitleClickHandlers() && getChart().getNode().getTitle().isInside(event)) {
			// fires the click event on the chart title
			getChart().fireEvent(new TitleClickEvent(event, getChart().getNode().getOptions().getTitle()));
		}
	}

	/**
	 * Check if the click event on chart and manage it fire a CHARBA scale selection event.
	 * 
	 * @param event event generated on chart
	 */
	private void handleScaleSelection(BaseNativeEvent event) {
		// checks if there is any handler, the event is not in chart area because if there is managed as chart click and the chart has got scales
		if (hasAxisClickHandlers() && !getChart().getNode().getChartArea().isInside(event) && !ScaleType.NONE.equals(getChart().getType().scaleType())) {
			// gets the scales
			ScalesNode scales = getChart().getNode().getScales();
			// checks if event is inside a scale box
			if (scales.isInside(event)) {
				// gets scale item
				ScaleItem scaleItem = scales.getScaleIsInside(event);
				// creates axis reference
				Axis axis = null;
				// gets charba id of scale
				final int charbaIdOfScale = scaleItem.getCharbaId();
				// checks if undefined
				// means no axis configured into chart
				if (charbaIdOfScale != UndefinedValues.INTEGER) {
					// gets the axis by charba id
					axis = getAxisById(charbaIdOfScale);
				}
				// fires the click event on the chart scale
				getChart().fireEvent(new AxisClickEvent(event, scaleItem, axis));
			}
		}
	}

}