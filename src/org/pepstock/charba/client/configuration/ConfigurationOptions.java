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
package org.pepstock.charba.client.configuration;

import java.util.List;
import java.util.Set;

import org.pepstock.charba.client.Chart;
import org.pepstock.charba.client.ChartEnvelop;
import org.pepstock.charba.client.Configuration;
import org.pepstock.charba.client.ConfigurationElement;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.callbacks.ChartContext;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.ConfigurationLoader;
import org.pepstock.charba.client.commons.Envelop;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.Merger;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultScaledOptions;
import org.pepstock.charba.client.dom.events.NativeBaseEvent;
import org.pepstock.charba.client.enums.ChartEventProperty;
import org.pepstock.charba.client.events.AddHandlerEvent;
import org.pepstock.charba.client.events.ChartClickEvent;
import org.pepstock.charba.client.events.ChartEventContext;
import org.pepstock.charba.client.events.ChartHoverEvent;
import org.pepstock.charba.client.events.ChartResizeEvent;
import org.pepstock.charba.client.events.DatasetSelectionEvent;
import org.pepstock.charba.client.events.RemoveHandlerEvent;
import org.pepstock.charba.client.intl.CLocale;
import org.pepstock.charba.client.items.DatasetReference;
import org.pepstock.charba.client.items.SizeItem;
import org.pepstock.charba.client.options.ExtendedOptions;
import org.pepstock.charba.client.options.ExtendedScale;
import org.pepstock.charba.client.options.IsEvent;

import jsinterop.annotations.JsFunction;

/**
 * Base object which maps chart configuration.<br>
 * CHARBA stores the unique chart ID in the CHART.JS chart options using <code>charbaId</code> property key.<br>
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
public abstract class ConfigurationOptions extends ConfigurationContainer<ExtendedOptions> implements ConfigurationElement, HasAnimation, IsEventProvider {

	// instance to reset the callback
	static final ConfigurationEnvelop<CallbackProxy.Proxy> RESET_CALLBACK_ENVELOP = new ConfigurationEnvelop<>(null, true);

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
		 * @param event CHART.JS event which wraps the native event
		 * @param items array of chart elements affected by the event
		 * @param chart chart instance
		 */
		void call(NativeObject event, ArrayObject items, Chart chart);
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
		 * @param chart java script chart instance
		 * @param size new size of chart
		 */
		void call(Chart chart, NativeObject size);
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

	private final IsDefaultScaledOptions defaultValues;

	private final AnimationContainer animationContainer;

	private final Legend legend;

	private final Title title;

	private final Subtitle subtitle;

	private final Tooltips tooltips;

	private final Filler filler;

	private final Hover hover;

	private final Interaction interaction;

	private final Layout layout;

	private final Elements elements;

	private final Plugins plugins;

	private final Datasets datasets;

	private final Font font;

	// event axes handler
	private final EventAxesHandler eventAxesHandler;
	// event title handler
	private final EventTitleHandler eventTitleHandler;
	// event subtitle handler
	private final EventSubtitleHandler eventSubtitleHandler;
	// amount of data set selection event handlers
	private int onDatasetSelectionHandlers = 0;
	// amount of click event handlers
	private int onClickHandlers = 0;
	// amount of hover event handlers
	private int onHoverHandlers = 0;
	// amount of resize event handlers
	private int onResizeHandlers = 0;

	/**
	 * Builds the object storing the chart instance and defaults options.
	 * 
	 * @param chart chart instance
	 * @param defaultValues defaults options
	 */
	protected ConfigurationOptions(IsChart chart, IsDefaultScaledOptions defaultValues) {
		// uses the extended option internally (no override)
		super(chart, new ExtendedOptions(chart, defaultValues, new ConfigurationEnvelop<>(null, true)));
		// stores default
		this.defaultValues = defaultValues;
		// registers as event handler
		IsEventProvider.register(chart, this);
		// creates elements handler
		this.eventAxesHandler = new EventAxesHandler(this);
		this.eventTitleHandler = new EventTitleHandler(this);
		this.eventSubtitleHandler = new EventSubtitleHandler(this);
		// creates the animation configuration manager
		this.animationContainer = new AnimationContainer(getChart(), this::getConfiguration);
		// creates all sub elements
		this.elements = new Elements(this);
		this.datasets = new Datasets(this);
		this.legend = new Legend(this);
		this.title = new Title(this);
		this.subtitle = new Subtitle(this);
		this.layout = new Layout(this);
		this.hover = new Hover(this);
		this.interaction = new Interaction(this);
		this.plugins = new Plugins(this);
		this.tooltips = new Tooltips(this);
		this.filler = new Filler(this);
		// gets font
		this.font = new Font(() -> getConfiguration().getFont());
		// sets charba ID
		getConfiguration().setCharbaId(chart.getId());
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		this.clickCallbackProxy.setCallback((event, items, nativeChart) -> {
			// creates a event context
			ChartEventContext eventContext = new ChartEventContext(new ConfigurationEnvelop<>(event));
			// gets the native event
			NativeBaseEvent nativeEvent = eventContext.getNativeEvent();
			// gets list of elements
			List<DatasetReference> references = ArrayListHelper.unmodifiableList(items, getChart().getDatasetReferenceFactory());
			// handle click event for dataset
			handleDatasetSelection(nativeEvent, references);
			// fires the click event on the chart
			getChart().fireEvent(new ChartClickEvent(eventContext, references));
		});
		// fires the hover hover on the chart
		this.hoverCallbackProxy.setCallback((event, items, nativeChart) -> {
			// creates a event context
			ChartEventContext eventContext = new ChartEventContext(new ConfigurationEnvelop<>(event));
			// fires the hover event on the chart
			getChart().fireEvent(new ChartHoverEvent(eventContext, ArrayListHelper.unmodifiableList(items, getChart().getDatasetReferenceFactory())));
		});
		this.resizeCallbackProxy.setCallback((nativeChart, size) -> {
			// creates a event context
			ChartEventContext eventContext = new ChartEventContext(getChart(), nativeChart);
			// fires the resize event on chart
			getChart().fireEvent(new ChartResizeEvent(eventContext, new SizeItem(new ConfigurationEnvelop<>(size, true))));
		});
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
	public final void loadOptions(ChartEnvelop<NativeObject> envelop) {
		Merger.get().load(getChart(), getConfiguration(), envelop);
	}

	/**
	 * Sets the chart options as underlying configuration options, after the first draw of the chart.<br>
	 * Leveraging on proxy, this is mandatory and enables the possibility to use only the configuration options to update the chart options at runtime.
	 * 
	 * @param envelop envelop for chart options as native options
	 */
	public final void setChartOptions(ChartEnvelop<NativeObject> envelop) {
		// invokes the notification method
		beforeConfigurationUpdate();
		// sets new configuration
		setConfiguration(new ExtendedOptions(getChart(), defaultValues, new ConfigurationEnvelop<>(Envelop.checkAndGetIfValid(envelop).getContent())));
		// checks if this configuration is prepared to have scales
		if (this instanceof ScalesOptions) {
			// casts to scales options
			ScalesOptions options = (ScalesOptions) this;
			// scans axes to set new configuration
			for (Axis axis : options.getScales().getAxes()) {
				// invokes the notification method
				beforeAxisConfigurationUpdate(axis);
				// stores new base object, the object of chart options
				axis.setConfiguration(new ExtendedScale(new ConfigurationEnvelop<>(getConfiguration().getScales().getAxis(axis.getId())), axis.getDefaultValues()));
				// invokes the notification method
				afterAxisConfigurationUpdate(axis);
			}
		}
		// invokes the notification method
		afterConfigurationUpdate();
	}

	/**
	 * Invoked before the chart options are going to be updated.
	 */
	protected void beforeConfigurationUpdate() {
		// do nothing
	}

	/**
	 * Invoked after the chart options has been updated.
	 */
	protected void afterConfigurationUpdate() {
		// do nothing
	}

	/**
	 * Invoked before the axis options are going to be updated.
	 * 
	 * @param axis axis instance which is going to be updated
	 */
	protected void beforeAxisConfigurationUpdate(Axis axis) {
		// do nothing
	}

	/**
	 * Invoked after the axis options has been updated.
	 * 
	 * @param axis axis instance which has been updated
	 */
	protected void afterAxisConfigurationUpdate(Axis axis) {
		// do nothing
	}

	/**
	 * Returns the defaults configuration.
	 * 
	 * @return the defaults configuration
	 */
	final IsDefaultScaledOptions getDefaultValues() {
		return defaultValues;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.HasAnimation#getAnimationContainer()
	 */
	@Override
	public final AnimationContainer getAnimationContainer() {
		return animationContainer;
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
	 * Returns the interaction element.
	 * 
	 * @return the interaction
	 */
	public Interaction getInteraction() {
		return interaction;
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
	 * Returns the subtitle element.
	 * 
	 * @return the subtitle
	 */
	public Subtitle getSubtitle() {
		return subtitle;
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
	 * Returns the data sets element.
	 * 
	 * @return the data sets
	 */
	public Datasets getDatasets() {
		return datasets;
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
	 * Returns the filler plugin configuration.
	 * 
	 * @return the filler plugin configuration
	 */
	public Filler getFiller() {
		return filler;
	}

	/**
	 * Returns the font element.
	 * 
	 * @return the font element
	 */
	public Font getFont() {
		return font;
	}

	/**
	 * Returns the axis by the unique CHARBA id of scale or <code>null</code> if not axis.
	 * 
	 * @param id the unique CHARBA id of scale
	 * @return the axis or <code>null</code> if not axis.
	 */
	protected Axis getAxisById(int id) {
		return null;
	}

	/**
	 * Sets the browser events that the chart should listen to.
	 * 
	 * @param events the browser events that the chart should listen to.
	 */
	public void setEvents(IsEvent... events) {
		getConfiguration().setEvents(events);
	}

	/**
	 * Sets the browser events that the chart should listen to.
	 * 
	 * @param events the browser events that the chart should listen to.
	 */
	public void setEvents(Set<IsEvent> events) {
		getConfiguration().setEvents(events);
	}

	/**
	 * Returns the browser events that the chart should listen to.
	 * 
	 * @return the browser events that the chart should listen to.
	 */
	public Set<IsEvent> getEvents() {
		return getConfiguration().getEvents();
	}

	/**
	 * Sets the locale instance for internationalization.
	 * 
	 * @param locale the locale instance
	 */
	public void setLocale(CLocale locale) {
		getConfiguration().setLocale(locale);
	}

	/**
	 * Returns the locale instance for internationalization.
	 * 
	 * @return the locale instance
	 */
	public CLocale getLocale() {
		return getConfiguration().getLocale();
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
	 * Returns the maintaining of the original canvas aspect ratio (width / height) when resizing.
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
	 * Sets the delay the resize update by give amount of milliseconds.<br>
	 * This can ease the resize process by debouncing update of the elements.
	 * 
	 * @param delay the delay the resize update by give amount of milliseconds
	 */
	public void setResizeDelay(int delay) {
		getConfiguration().setResizeDelay(delay);
	}

	/**
	 * Returns the delay the resize update by give amount of milliseconds.<br>
	 * This can ease the resize process by debouncing update of the elements.
	 * 
	 * @return the delay the resize update by give amount of milliseconds
	 */
	public double getResizeDelay() {
		return getConfiguration().getResizeDelay();
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
	 * @return the drawOnAttach <code>true</code> if the chart is configured to be drawn on the attach of DIV element, otherwise <code>false</code>.
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
	 * @return the destroyOnDetach <code>true</code> if the chart is configured to be destroyed on the detach from DIV element, otherwise <code>false</code>.
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
	 * Sets the default color to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @param color color to use in the chart.
	 */
	public void setColor(IsColor color) {
		getConfiguration().setColor(color);
	}

	/**
	 * Sets the default color to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @param color color to use in the chart.
	 */
	public void setColor(String color) {
		getConfiguration().setColor(color);
	}

	/**
	 * Returns the default color to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @return color to use in the chart.
	 */
	public String getColorAsString() {
		return getConfiguration().getColorAsString();
	}

	/**
	 * Returns the default color to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @return color to use in the chart.
	 */
	public IsColor getColor() {
		return getConfiguration().getColor();
	}

	/**
	 * Sets the default background color to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @param backgroundColor background color to use in the chart.
	 */
	public void setBackgroundColor(IsColor backgroundColor) {
		getConfiguration().setBackgroundColor(backgroundColor);
	}

	/**
	 * Sets the default background color to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @param backgroundColor background color to use in the chart.
	 */
	public void setBackgroundColor(String backgroundColor) {
		getConfiguration().setBackgroundColor(backgroundColor);
	}

	/**
	 * Returns the default background color to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @return background color to use in the chart.
	 */
	public String getBackgroundColorAsString() {
		return getConfiguration().getBackgroundColorAsString();
	}

	/**
	 * Returns the default background color to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @return background color to use in the chart.
	 */
	public IsColor getBackgroundColor() {
		return getConfiguration().getBackgroundColor();
	}

	/**
	 * Sets the default border color to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @param borderColor border color to use in the chart.
	 */
	public void setBorderColor(IsColor borderColor) {
		getConfiguration().setBorderColor(borderColor);
	}

	/**
	 * Sets the default border color to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @param borderColor border color to use in the chart.
	 */
	public void setBorderColor(String borderColor) {
		getConfiguration().setBorderColor(borderColor);
	}

	/**
	 * Returns the default border color to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @return border color to use in the chart.
	 */
	public String getBorderColorAsString() {
		return getConfiguration().getBorderColorAsString();
	}

	/**
	 * Sets <code>true</code> when the auto colors plugin has been enabled and CHART.JS apply the default colors to datasets.
	 * 
	 * @param enabled <code>true</code> when the auto colors plugin has been enabled and CHART.JS apply the default colors to datasets
	 */
	public void setAutoColors(boolean enabled) {
		getConfiguration().setAutoColors(enabled);
	}

	/**
	 * Returns <code>true</code> when the auto colors plugin has been enabled and CHART.JS apply the default colors to datasets.
	 * 
	 * @return <code>true</code> when the auto colors plugin has been enabled and CHART.JS apply the default colors to datasets
	 */
	public boolean isAutoColors() {
		return getConfiguration().isAutoColors();
	}

	/**
	 * By default the colors plugin only works when you initialize the chart without any colors for the border or background specified.<br>
	 * If you want to force the colors plugin to always color your datasets, for example when using dynamic datasets at runtime you will need to set the <code>forceOverride</code>
	 * option to <code>true</code>.
	 * 
	 * @param forceOverride <code>true</code> if auto color plugin forces setting palette.
	 */
	public void setAutoColorsForceOverride(boolean forceOverride) {
		getConfiguration().setAutoColorsForceOverride(forceOverride);
	}

	/**
	 * By default the colors plugin only works when you initialize the chart without any colors for the border or background specified.<br>
	 * If you want to force the colors plugin to always color your datasets, for example when using dynamic datasets at runtime you will need to set the <code>forceOverride</code>
	 * option to <code>true</code>.
	 * 
	 * @return <code>true</code> if auto color plugin forces setting palette.
	 */
	public boolean isAutoColorsForceOverride() {
		return getConfiguration().isAutoColorsForceOverride();
	}

	/**
	 * Returns the default border color to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @return border color to use in the chart.
	 */
	public IsColor getBorderColor() {
		return getConfiguration().getBorderColor();
	}

	/**
	 * Returns <code>true</code> if there is any data set selection handler, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if there is any data set selection handler, otherwise <code>false</code>.
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
		return eventTitleHandler.hasTitleClickHandlers();
	}

	/**
	 * Returns <code>true</code> if there is any title enter handler, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if there is any title enter handler, otherwise <code>false</code>.
	 */
	public final boolean hasTitleEnterHandlers() {
		return eventTitleHandler.hasTitleEnterHandlers();
	}

	/**
	 * Returns <code>true</code> if there is any title leave handler, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if there is any title leave handler, otherwise <code>false</code>.
	 */
	public final boolean hasTitleLeaveHandlers() {
		return eventTitleHandler.hasTitleLeaveHandlers();
	}

	/**
	 * Returns <code>true</code> if there is any subtitle click handler, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if there is any subtitle click handler, otherwise <code>false</code>.
	 */
	public final boolean hasSubtitleClickHandlers() {
		return eventSubtitleHandler.hasSubtitleClickHandlers();
	}

	/**
	 * Returns <code>true</code> if there is any subtitle enter handler, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if there is any subtitle enter handler, otherwise <code>false</code>.
	 */
	public final boolean hasSubtitleEnterHandlers() {
		return eventSubtitleHandler.hasSubtitleEnterHandlers();
	}

	/**
	 * Returns <code>true</code> if there is any subtitle leave handler, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if there is any subtitle leave handler, otherwise <code>false</code>.
	 */
	public final boolean hasSubtitleLeaveHandlers() {
		return eventSubtitleHandler.hasSubtitleLeaveHandlers();
	}

	/**
	 * Returns <code>true</code> if there is any axis click handler, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if there is any axis click handler, otherwise <code>false</code>.
	 */
	public final boolean hasAxisClickHandlers() {
		return eventAxesHandler.hasAxisClickHandlers();
	}

	/**
	 * Returns <code>true</code> if there is any axis hover handler, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if there is any axis hover handler, otherwise <code>false</code>.
	 */
	public final boolean hasAxisHoverHandlers() {
		return eventAxesHandler.hasAxisHoverHandlers();
	}

	/**
	 * Returns <code>true</code> if there is any axis enter handler, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if there is any axis enter handler, otherwise <code>false</code>.
	 */
	public final boolean hasAxisEnterHandlers() {
		return eventAxesHandler.hasAxisEnterHandlers();
	}

	/**
	 * Returns <code>true</code> if there is any axis leave handler, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if there is any axis leave handler, otherwise <code>false</code>.
	 */
	public final boolean hasAxisLeaveHandlers() {
		return eventAxesHandler.hasAxisLeaveHandlers();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.events.AddHandlerEventHandler#onAdd(org.pepstock.charba.client.events.AddHandlerEvent)
	 */
	@Override
	public final void onAdd(AddHandlerEvent event) {
		// checks if type of added event handler is data set selection or click
		if (event.isRecognize(ChartClickEvent.TYPE) || event.isRecognize(DatasetSelectionEvent.TYPE)) {
			// if there is not any click event handler
			if (onClickHandlers == 0) {
				// sets the callback proxy in order to call the user event interface
				getConfiguration().setEvent(getConfiguration(), ChartEventProperty.ON_CLICK, new ConfigurationEnvelop<>(clickCallbackProxy.getProxy()));
			}
			// increments amount of handlers
			onClickHandlers++;
			// check if a data set selection handler has been added
			if (event.isRecognize(DatasetSelectionEvent.TYPE)) {
				// increments handlers of data set selection
				onDatasetSelectionHandlers++;
			}
		} else if (event.isRecognize(ChartHoverEvent.TYPE)) {
			// if there is not any hover event handler
			if (onHoverHandlers == 0) {
				// sets the callback proxy in order to call the user event interface
				getConfiguration().setEvent(getConfiguration(), ChartEventProperty.ON_HOVER, new ConfigurationEnvelop<>(hoverCallbackProxy.getProxy()));
			}
			// increments amount of handlers
			onHoverHandlers++;
		} else if (event.isRecognize(ChartResizeEvent.TYPE)) {
			// if there is not any resize event handler
			if (onResizeHandlers == 0) {
				// sets the callback proxy in order to call the user event interface
				getConfiguration().setEvent(getConfiguration(), ChartEventProperty.ON_RESIZE, new ConfigurationEnvelop<>(resizeCallbackProxy.getProxy()));
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
		// checks if type of removed event handler is data set selection or click
		if (event.isRecognize(ChartClickEvent.TYPE) || event.isRecognize(DatasetSelectionEvent.TYPE)) {
			// decrements the amount of handlers
			onClickHandlers--;
			// if there is not any handler
			if (onClickHandlers == 0) {
				// removes the java script object
				getConfiguration().setEvent(getConfiguration(), ChartEventProperty.ON_CLICK, RESET_CALLBACK_ENVELOP);
			}
			// check if a data set selection handler has been removed
			if (event.isRecognize(DatasetSelectionEvent.TYPE)) {
				// decrements handlers of data set selection
				onDatasetSelectionHandlers--;
			}
		} else if (event.isRecognize(ChartHoverEvent.TYPE)) {
			// decrements the amount of handlers
			onHoverHandlers--;
			// if there is not any handler
			if (onHoverHandlers == 0) {
				// removes the java script object
				getConfiguration().setEvent(getConfiguration(), ChartEventProperty.ON_HOVER, RESET_CALLBACK_ENVELOP);
			}
		} else if (event.isRecognize(ChartResizeEvent.TYPE)) {
			// decrements the amount of handlers
			onResizeHandlers--;
			// if there is not any handler
			if (onResizeHandlers == 0) {
				// removes the java script object
				getConfiguration().setEvent(getConfiguration(), ChartEventProperty.ON_RESIZE, RESET_CALLBACK_ENVELOP);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.ConfigurationElement#load(org.pepstock.charba.client.IsChart, org.pepstock.charba.client.Configuration)
	 */
	@Override
	public final void load(IsChart chart, Configuration configuration) {
		// loads the native object in the configuration to pass to chart
		ConfigurationLoader.loadOptions(configuration, getConfiguration());
	}

	/**
	 * Sets the callbacks that every element of options can activate.
	 * 
	 * @param node element node instance
	 * @param property property name
	 * @param callBack the callback instance
	 * @param callbackProxy the callback proxy instance
	 */
	final void setCallback(AbstractNode node, Key property, Object callBack, CallbackProxy<?> callbackProxy) {
		// checks if consistent
		if (callBack != null) {
			// adds the callback proxy function to java script object
			getConfiguration().setCallback(new ConfigurationEnvelop<>(node), property, callbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			getConfiguration().setCallback(new ConfigurationEnvelop<>(node), property, (CallbackProxy.Proxy) null);
		}
	}

	/**
	 * Sets the callbacks that every element of options can activate.
	 * 
	 * @param node element node instance
	 * @param property property name
	 * @param callBack the callback instance
	 */
	final void setCallback(AbstractNode node, Key property, NativeCallback callBack) {
		// checks if consistent
		if (callBack != null) {
			// adds the callback proxy function to java script object
			getConfiguration().setCallback(new ConfigurationEnvelop<>(node), property, callBack);
		} else {
			// otherwise sets null which removes the properties from java script object
			getConfiguration().setCallback(new ConfigurationEnvelop<>(node), property, (NativeCallback) null);
		}
	}

	/**
	 * Creates a chart context for callback on configuration.
	 * 
	 * @param context native context, passed by CHART.JS
	 * @return a chart context for callback on configuration
	 */
	final ChartContext createContext(NativeObject context) {
		return new ChartContext(context);
	}

	/**
	 * Check if the click event on chart and manage it fire a CHARBA data set selection event.
	 * 
	 * @param event event generated on chart
	 * @param references a list of items with dataset references related to the click
	 */
	private void handleDatasetSelection(NativeBaseEvent event, List<DatasetReference> references) {
		// checks if there is any handler
		if (hasDatasetSelectionHandlers()) {
			// gets the data set items by event
			DatasetReference item = getChart().getElementAtEvent(event);
			// if the item is consistent
			if (item != null) {
				// fires the event for data set selection
				getChart().fireEvent(new DatasetSelectionEvent(event, item));
			} else if (!references.isEmpty()) {
				// if here, the element is not retrieved by interaction
				// and then it uses the elements passed by CHART.JS
				// fires the event for the first elements
				getChart().fireEvent(new DatasetSelectionEvent(event, references.get(0)));
			}

		}
	}

}