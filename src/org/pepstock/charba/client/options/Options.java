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
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.HasCallbackScope;
import org.pepstock.charba.client.commons.Id;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.defaults.IsDefaultOptions;
import org.pepstock.charba.client.enums.DefaultPluginId;
import org.pepstock.charba.client.enums.IndexAxis;
import org.pepstock.charba.client.intl.CLocale;
import org.pepstock.charba.client.intl.CLocaleBuilder;
import org.pepstock.charba.client.items.Undefined;

/**
 * Base object which maps chart options.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class Options extends AbstractModel<Options, IsDefaultOptions> implements IsDefaultOptions, HasSpanGaps, HasAnimationOptions, HasCallbackScope, HasEvents {

	/**
	 * Name of properties of native object.<br>
	 * Properties common with which extends this class.
	 */
	protected enum CommonProperty implements Key
	{
		/**
		 * Property key to manage locale option.
		 */
		LOCALE("locale");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use in the native object.
		 * 
		 * @param value value of property name
		 */
		private CommonProperty(String value) {
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
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		// object properties
		FONT("font"),
		INTERACTION("interaction"),
		HOVER("hover"),
		ELEMENTS("elements"),
		PLUGINS("plugins"),
		LAYOUT("layout"),
		DATASETS("datasets"),
		// global options
		COLOR("color"),
		BACKGROUND_COLOR("backgroundColor"),
		BORDER_COLOR("borderColor"),
		// simple properties
		RESPONSIVE("responsive"),
		MAINTAIN_ASPECT_RATIO("maintainAspectRatio"),
		RESIZE_DELAY("resizeDelay"),
		ASPECT_RATIO("aspectRatio"),
		DEVICE_PIXEL_RATIO("devicePixelRatio"),
		// specific for chart type
		SHOW_LINE("showLine"),
		SKIP_NULL("skipNull"),
		CUTOUT("cutout"),
		RADIUS("radius"),
		ROTATION("rotation"),
		CIRCUMFERENCE("circumference"),
		INDEX_AXIS("indexAxis"),
		// internal key to store draw and destroy chart options
		CHARBA_DRAW_ON_ATTACH("charbaDrawOnAttach"),
		CHARBA_DESTROY_ON_DETACH("charbaDestroyOnDetach");

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

	// all sub elements
	private final Legend legend;

	private final Hover hover;

	private final Interaction interaction;

	private final Layout layout;

	private final Elements elements;

	private final Title title;

	private final Subtitle subtitle;

	private final Tooltips tooltips;

	private final Decimation decimation;

	private final Filler filler;

	private final AutoColors autoColors;

	private final Plugins plugins;

	private final Font font;

	private final Datasets datasets;

	private final String scope;

	// span gap handler instance
	private final SpanGapHandler spanGapHandler;
	// animation container
	private final AnimationContainer animationContainer;
	// events handler
	private final EventsOptionHandler eventsHandler;

	/**
	 * Creates the object only with default provider. This is used as the root element.<br>
	 * New native java script object is created and it's empty.
	 * 
	 * @param scope scope of the options
	 * @param defaultValues default provider instance.
	 */
	protected Options(String scope, IsDefaultOptions defaultValues) {
		this(scope, defaultValues, null);
	}

	/**
	 * Creates the object only with default provider and native object. This is used as the root element.
	 * 
	 * @param scope scope of the options
	 * @param defaultValues default provider instance.
	 * @param nativeObject native object to store properties.
	 */
	protected Options(String scope, IsDefaultOptions defaultValues, NativeObject nativeObject) {
		super(defaultValues, nativeObject);
		// checks if scope is consistent
		this.scope = Checker.checkAndGetIfValid(scope, "Scope argument");
		// gets all sub elements
		this.elements = new Elements(this, Property.ELEMENTS, getDefaultValues().getElements(), getValue(Property.ELEMENTS));
		this.hover = new Hover(this, Property.HOVER, getDefaultValues().getHover(), getValue(Property.HOVER));
		this.interaction = new Interaction(this, Property.INTERACTION, getDefaultValues().getInteraction(), getValue(Property.INTERACTION));
		this.layout = new Layout(this, Property.LAYOUT, getDefaultValues().getLayout(), getValue(Property.LAYOUT));
		this.plugins = new Plugins(this, Property.PLUGINS, getDefaultValues().getPlugins(), getValue(Property.PLUGINS));
		this.font = new Font(this, Property.FONT, getDefaultValues().getFont(), getValue(Property.FONT));
		this.datasets = new Datasets(this, Property.DATASETS, getDefaultValues().getDatasets(), getValue(Property.DATASETS));
		// sets span gap handler
		this.spanGapHandler = new SpanGapHandler(this, getDefaultValues(), getNativeObject());
		// sets animation container
		this.animationContainer = new AnimationContainer(this, getDefaultValues(), getNativeObject(), this.scope);
		// creates events handler
		this.eventsHandler = new EventsOptionHandler(this, getDefaultValues(), getNativeObject());
		// the defaults of plugins provided by CHART.JS (legend, title and tooltip)
		// set own default options in the defaults.plugin and not longer to the default node.
		// then it reads the default plugins and copies (reference of object)
		// to the options nodes
		// --------------------
		// loads default plugins
		this.legend = new Legend(plugins, DefaultPluginId.LEGEND, getDefaultValues().getLegend(), plugins.getDefaultPluginOptions(DefaultPluginId.LEGEND));
		this.title = new Title(plugins, DefaultPluginId.TITLE, getDefaultValues().getTitle(), plugins.getDefaultPluginOptions(DefaultPluginId.TITLE));
		this.subtitle = new Subtitle(plugins, DefaultPluginId.SUBTITLE, getDefaultValues().getSubtitle(), plugins.getDefaultPluginOptions(DefaultPluginId.SUBTITLE));
		this.tooltips = new Tooltips(plugins, DefaultPluginId.TOOLTIP, getDefaultValues().getTooltips(), plugins.getDefaultPluginOptions(DefaultPluginId.TOOLTIP));
		this.decimation = new Decimation(plugins, DefaultPluginId.DECIMATION, getDefaultValues().getDecimation(), plugins.getDefaultPluginOptions(DefaultPluginId.DECIMATION));
		this.filler = new Filler(plugins, DefaultPluginId.FILLER, getDefaultValues().getFiller(), plugins.getDefaultPluginOptions(DefaultPluginId.FILLER));
		this.autoColors = new AutoColors(plugins, DefaultPluginId.COLORS, getDefaultValues(), plugins.getDefaultPluginOptions(DefaultPluginId.COLORS));
	}

	/**
	 * Returns the font element.
	 * 
	 * @return the font
	 */
	@Override
	public final Font getFont() {
		return font;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.HasSpanGaps#getSpanGapHandler()
	 */
	@Override
	public final SpanGapHandler getSpanGapHandler() {
		return spanGapHandler;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.HasEvents#getEventsOptionHandler()
	 */
	@Override
	public final EventsOptionHandler getEventsOptionHandler() {
		return eventsHandler;
	}

	/**
	 * Returns the scope of the options, which is the options are used for defaults, chart defaults or chart.
	 * 
	 * @return the scope of the options
	 */
	@Override
	public final String getScope() {
		return scope;
	}

	/**
	 * Returns the animation element.
	 * 
	 * @return the animation
	 */
	@Override
	public Animation getAnimation() {
		return animationContainer.getAnimation();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.HasAnimation#getAnimationContainer()
	 */
	@Override
	public final AnimationContainer getAnimationContainer() {
		return animationContainer;
	}

	/**
	 * Returns the legend element.
	 * 
	 * @return the legend
	 */
	@Override
	public Legend getLegend() {
		return legend;
	}

	/**
	 * Returns the hover element.
	 * 
	 * @return the hover
	 */
	@Override
	public final Hover getHover() {
		return hover;
	}

	/**
	 * Returns the interaction element.
	 * 
	 * @return the interaction
	 */
	@Override
	public final Interaction getInteraction() {
		return interaction;
	}

	/**
	 * Returns the layout element.
	 * 
	 * @return the layout
	 */
	@Override
	public final Layout getLayout() {
		return layout;
	}

	/**
	 * Returns the decimation plugin element.
	 * 
	 * @return the decimation plugin element
	 */
	@Override
	public final Decimation getDecimation() {
		return decimation;
	}

	/**
	 * Returns the filler plugin element.
	 * 
	 * @return the filler plugin element
	 */
	@Override
	public final Filler getFiller() {
		return filler;
	}

	/**
	 * Returns the elements element.
	 * 
	 * @return the elements
	 */
	@Override
	public final Elements getElements() {
		return elements;
	}

	/**
	 * Returns the title element.
	 * 
	 * @return the title
	 */
	@Override
	public final Title getTitle() {
		return title;
	}

	/**
	 * Returns the title element.
	 * 
	 * @return the title
	 */
	@Override
	public final Subtitle getSubtitle() {
		return subtitle;
	}

	/**
	 * Returns the tooltips element.
	 * 
	 * @return the tooltips
	 */
	@Override
	public final Tooltips getTooltips() {
		return tooltips;
	}

	/**
	 * Returns the plugins element.
	 * 
	 * @return the plugins
	 */
	@Override
	public Plugins getPlugins() {
		return plugins;
	}

	/**
	 * Returns the data sets element.
	 * 
	 * @return the data sets element
	 */
	@Override
	public Datasets getDatasets() {
		return datasets;
	}

	/**
	 * Returns the CHARBA id when the options are related to a chart instance.
	 * 
	 * @return the CHARBA id when the options are related to a chart instance otherwise {@link Undefined#STRING}.
	 */
	public String getCharbaId() {
		return Id.get(this);
	}

	/**
	 * Sets the locale instance for internationalization.
	 * 
	 * @param locale the locale instance
	 */
	public void setLocale(CLocale locale) {
		// check if locale is consistent
		if (locale != null) {
			setValue(CommonProperty.LOCALE, locale.getIdentifier());
		} else {
			// if null, it use the default locale
			// sets locale defaults
			setValue(CommonProperty.LOCALE, CLocale.getDefault().getIdentifier());
		}
	}

	/**
	 * Returns the locale instance for internationalization.
	 * 
	 * @return the locale instance
	 */
	@Override
	public CLocale getLocale() {
		// gets value as string
		String localeIdentifier = getValue(CommonProperty.LOCALE, Undefined.STRING);
		// checks if consistent
		if (localeIdentifier != null) {
			return CLocaleBuilder.build(localeIdentifier);
		}
		// if here the value is not consistent
		// returns default
		return getDefaultValues().getLocale();
	}

	/**
	 * Sets the resizing of the chart canvas when its container does.
	 * 
	 * @param responsive the resizing of the chart canvas when its container does.
	 */
	public void setResponsive(boolean responsive) {
		setValue(Property.RESPONSIVE, responsive);
	}

	/**
	 * Returns the resizing of the chart canvas when its container does.
	 * 
	 * @return the resizing of the chart canvas when its container does.
	 */
	@Override
	public boolean isResponsive() {
		return getValue(Property.RESPONSIVE, getDefaultValues().isResponsive());
	}

	/**
	 * Sets the maintaining of the original canvas aspect ratio (width / height) when resizing.
	 * 
	 * @param maintainAspectRatio the maintaining of the original canvas aspect ratio (width / height) when resizing.
	 */
	public void setMaintainAspectRatio(boolean maintainAspectRatio) {
		setValue(Property.MAINTAIN_ASPECT_RATIO, maintainAspectRatio);
	}

	/**
	 * Returns the maintaining of the original canvas aspect ratio (width / height) when resizing.
	 * 
	 * @return the maintaining of the original canvas aspect ratio (width / height) when resizing.
	 */
	@Override
	public boolean isMaintainAspectRatio() {
		return getValue(Property.MAINTAIN_ASPECT_RATIO, getDefaultValues().isMaintainAspectRatio());
	}

	/**
	 * Canvas aspect ratio (i.e. width / height, a value of 1 representing a square canvas).<br>
	 * Note that this option is ignored if the height is explicitly defined either as attribute or via the style.
	 * 
	 * @param ratio the aspect ratio.
	 */
	public void setAspectRatio(double ratio) {
		setValue(Property.ASPECT_RATIO, Checker.positiveOrDefault(ratio, 1));
	}

	/**
	 * Canvas aspect ratio (i.e. width / height, a value of 1 representing a square canvas).<br>
	 * Note that this option is ignored if the height is explicitly defined either as attribute or via the style.
	 * 
	 * @return the aspect ratio.
	 */
	@Override
	public double getAspectRatio() {
		return getValue(Property.ASPECT_RATIO, getDefaultValues().getAspectRatio());
	}

	/**
	 * Sets the delay the resize update by give amount of milliseconds.<br>
	 * This can ease the resize process by debouncing update of the elements.
	 * 
	 * @param delay the delay the resize update by give amount of milliseconds
	 */
	public void setResizeDelay(int delay) {
		setValue(Property.RESIZE_DELAY, Checker.positiveOrZero(0));
	}

	/**
	 * Returns the delay the resize update by give amount of milliseconds.<br>
	 * This can ease the resize process by debouncing update of the elements.
	 * 
	 * @return the delay the resize update by give amount of milliseconds
	 */
	@Override
	public double getResizeDelay() {
		return getValue(Property.RESIZE_DELAY, getDefaultValues().getResizeDelay());
	}

	/**
	 * The chart's canvas will use a 1:1 pixel ratio, unless the physical display has a higher pixel ratio (e.g. Retina displays). Setting devicePixelRatio to a value other than 1
	 * will force the canvas size to be scaled by that amount.
	 * 
	 * @param ratio the pixel ratio.
	 */
	public void setDevicePixelRatio(double ratio) {
		setValue(Property.DEVICE_PIXEL_RATIO, Checker.positiveOrDefault(ratio, 1));
	}

	/**
	 * The chart's canvas will use a 1:1 pixel ratio, unless the physical display has a higher pixel ratio (e.g. Retina displays). Setting devicePixelRatio to a value other than 1
	 * will force the canvas size to be scaled by that amount. Returns the pixel ratio.
	 * 
	 * @return the pixel ratio.
	 */
	@Override
	public double getDevicePixelRatio() {
		return getValue(Property.DEVICE_PIXEL_RATIO, getDefaultValues().getDevicePixelRatio());
	}

	/**
	 * Returns <code>true</code> if the chart is configured to be drawn on the attach of DIV element, otherwise <code>false</code>.
	 * 
	 * @return the drawOnAttach <code>true</code> if the chart is configured to be drawn on the attach of DIV element, otherwise <code>false</code>.
	 */
	@Override
	public boolean isDrawOnAttach() {
		return getValue(Property.CHARBA_DRAW_ON_ATTACH, getDefaultValues().isDrawOnAttach());
	}

	/**
	 * Sets <code>true</code> if the chart is configured to be draw on the attach of DIV element, otherwise <code>false</code>.
	 * 
	 * @param drawOnAttach the drawOnAttach to set
	 */
	public void setDrawOnAttach(boolean drawOnAttach) {
		setValue(Property.CHARBA_DRAW_ON_ATTACH, drawOnAttach);
	}

	/**
	 * Returns <code>true</code> if the chart is configured to be destroyed on the detach from DIV element, otherwise <code>false</code>.
	 * 
	 * @return the destroyOnDetach <code>true</code> if the chart is configured to be destroyed on the attach of DIV element, otherwise <code>false</code>.
	 */
	@Override
	public boolean isDestroyOnDetach() {
		return getValue(Property.CHARBA_DESTROY_ON_DETACH, getDefaultValues().isDestroyOnDetach());
	}

	/**
	 * Sets <code>true</code> if the chart is configured to be destroyed on the detach from DIV element, otherwise <code>false</code>.
	 * 
	 * @param destroyOnDetach the destroyOnDetach to set
	 */
	public void setDestroyOnDetach(boolean destroyOnDetach) {
		setValue(Property.CHARBA_DESTROY_ON_DETACH, destroyOnDetach);
	}

	/**
	 * Sets the default color to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @param color color to use in the chart.
	 */
	public void setColor(IsColor color) {
		setColor(IsColor.checkAndGetValue(color));
	}

	/**
	 * Sets the default color to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @param color color to use in the chart.
	 */
	public void setColor(String color) {
		setValue(Property.COLOR, color);
	}

	/**
	 * Returns the default color to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @return color to use in the chart.
	 */
	@Override
	public String getColorAsString() {
		return getValue(Property.COLOR, getDefaultValues().getColorAsString());
	}

	/**
	 * Returns the default color to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @return color to use in the chart.
	 */
	public IsColor getColor() {
		return ColorBuilder.parse(getColorAsString());
	}

	/**
	 * Sets the default background color to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @param backgroundColor background color to use in the chart.
	 */
	public void setBackgroundColor(IsColor backgroundColor) {
		setBackgroundColor(IsColor.checkAndGetValue(backgroundColor));
	}

	/**
	 * Sets the default background color to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @param backgroundColor background color to use in the chart.
	 */
	public void setBackgroundColor(String backgroundColor) {
		setValue(Property.BACKGROUND_COLOR, backgroundColor);
	}

	/**
	 * Returns the default background color to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @return background color to use in the chart.
	 */
	@Override
	public String getBackgroundColorAsString() {
		return getValue(Property.BACKGROUND_COLOR, getDefaultValues().getBackgroundColorAsString());
	}

	/**
	 * Returns the default background color to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @return background color to use in the chart.
	 */
	public IsColor getBackgroundColor() {
		return ColorBuilder.parse(getBackgroundColorAsString());
	}

	/**
	 * Sets the default border color to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @param borderColor border color to use in the chart.
	 */
	public void setBorderColor(IsColor borderColor) {
		setBorderColor(IsColor.checkAndGetValue(borderColor));
	}

	/**
	 * Sets the default border color to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @param borderColor border color to use in the chart.
	 */
	public void setBorderColor(String borderColor) {
		setValue(Property.BORDER_COLOR, borderColor);
	}

	/**
	 * Returns the default border color to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @return border color to use in the chart.
	 */
	@Override
	public String getBorderColorAsString() {
		return getValue(Property.BORDER_COLOR, getDefaultValues().getBorderColorAsString());
	}

	/**
	 * Returns the default border color to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @return border color to use in the chart.
	 */
	public IsColor getBorderColor() {
		return ColorBuilder.parse(getBorderColorAsString());
	}

	/**
	 * Sets <code>true</code> when the auto colors plugin has been enabled and CHART.JS apply the default colors to datasets.
	 * 
	 * @param enabled <code>true</code> when the auto colors plugin has been enabled and CHART.JS apply the default colors to datasets
	 */
	public void setAutoColors(boolean enabled) {
		autoColors.setEnabled(enabled);
	}

	/**
	 * Returns <code>true</code> when the auto colors plugin has been enabled and CHART.JS apply the default colors to datasets.
	 * 
	 * @return <code>true</code> when the auto colors plugin has been enabled and CHART.JS apply the default colors to datasets
	 */
	@Override
	public boolean isAutoColors() {
		return autoColors.isEnabled();
	}

	/**
	 * By default the colors plugin only works when you initialize the chart without any colors for the border or background specified.<br>
	 * If you want to force the colors plugin to always color your datasets, for example when using dynamic datasets at runtime you will need to set the <code>forceOverride</code>
	 * option to <code>true</code>.
	 * 
	 * @param forceOverride <code>true</code> if auto color plugin forces setting palette.
	 */
	public void setAutoColorsForceOverride(boolean forceOverride) {
		autoColors.setForceOverride(forceOverride);
	}

	/**
	 * By default the colors plugin only works when you initialize the chart without any colors for the border or background specified.<br>
	 * If you want to force the colors plugin to always color your datasets, for example when using dynamic datasets at runtime you will need to set the <code>forceOverride</code>
	 * option to <code>true</code>.
	 * 
	 * @return <code>true</code> if auto color plugin forces setting palette.
	 */
	@Override
	public boolean isAutoColorsForceOverride() {
		return autoColors.isForceOverride();
	}

	/**
	 * If <code>false</code>, the lines between points are not drawn.
	 * 
	 * @param showLine if <code>false</code>, the lines between points are not drawn.
	 */
	public void setShowLine(boolean showLine) {
		setValue(Property.SHOW_LINE, showLine);
	}

	/**
	 * If <code>false</code>, the lines between points are not drawn.
	 * 
	 * @return if <code>false</code>, the lines between points are not drawn.
	 */
	@Override
	public boolean isShowLine() {
		return getValue(Property.SHOW_LINE, getDefaultValues().isShowLine());
	}

	/**
	 * If <code>true</code>, null or undefined values will not be drawn.
	 * 
	 * @param skipNull if <code>true</code>, null or undefined values will not be drawn
	 */
	public void setSkipNull(boolean skipNull) {
		setValue(Property.SKIP_NULL, skipNull);
	}

	/**
	 * If <code>true</code>, null or undefined values will not be drawn.
	 * 
	 * @return If <code>true</code>, null or undefined values will not be drawn
	 */
	@Override
	public boolean isSkipNull() {
		return getValue(Property.SKIP_NULL, getDefaultValues().isSkipNull());
	}

	/**
	 * Sets the portion of the chart that is cut out of the middle.<br>
	 * As {@link Double}, it is considered to be pixels.
	 * 
	 * @param cutout the portion of the chart that is cut out of the middle.<br>
	 *            As {@link Double}, it is considered to be pixels.
	 */
	public void setCutout(double cutout) {
		setValue(Property.CUTOUT, cutout);
	}

	/**
	 * Returns the portion of the chart that is cut out of the middle.<br>
	 * As {@link Double}, it is considered to be pixels.
	 * 
	 * @return the portion of the chart that is cut out of the middle.<br>
	 *         As {@link Double}, it is considered to be pixels.
	 */
	@Override
	public double getCutout() {
		// checks if is in pixel (number)
		if (isType(Property.CUTOUT, ObjectType.NUMBER)) {
			return getValue(Property.CUTOUT, getDefaultValues().getCutout());
		}
		// if here, the value is a string or not exist
		// returns the defaults
		return getDefaultValues().getCutout();
	}

	/**
	 * Sets the portion of the chart that is cut out of the middle.<br>
	 * As {@link String} and ending with '%', percentage of the chart radius.
	 * 
	 * @param cutout the portion of the chart that is cut out of the middle.<br>
	 *            As {@link String} and ending with '%', percentage of the chart radius.
	 */
	public void setCutoutPercentage(String cutout) {
		setValue(Property.CUTOUT, cutout);
	}

	/**
	 * Returns the portion of the chart that is cut out of the middle.<br>
	 * As {@link String} and ending with '%', percentage of the chart radius.
	 * 
	 * @return the portion of the chart that is cut out of the middle.<br>
	 *         As {@link String} and ending with '%', percentage of the chart radius.
	 */
	@Override
	public String getCutoutPercentage() {
		// checks if is in percentage (string)
		if (isType(Property.CUTOUT, ObjectType.STRING)) {
			return getValue(Property.CUTOUT, getDefaultValues().getCutoutPercentage());
		}
		// if here, the value is a number or not exist
		// returns the defaults
		return getDefaultValues().getCutoutPercentage();
	}

	/**
	 * Sets the outer radius of the chart.<br>
	 * As {@link Double}, it is considered to be pixels.
	 * 
	 * @param radius the outer radius of the chart.<br>
	 *            As {@link Double}, it is considered to be pixels.
	 */
	public void setRadius(double radius) {
		setValue(Property.RADIUS, Checker.positiveOrZero(radius));
	}

	/**
	 * Returns the outer radius of the chart.<br>
	 * As {@link Double}, it is considered to be pixels.
	 * 
	 * @return the outer radius of the chart.<br>
	 *         As {@link Double}, it is considered to be pixels.
	 */
	@Override
	public double getRadius() {
		// checks if is in pixel (number)
		if (isType(Property.RADIUS, ObjectType.NUMBER)) {
			return getValue(Property.RADIUS, getDefaultValues().getRadius());
		}
		// if here, the value is a string or not exist
		// returns the defaults
		return getDefaultValues().getRadius();
	}

	/**
	 * Sets the outer radius of the chart.<br>
	 * As {@link String} and ending with '%', percentage of the maximum radius.
	 * 
	 * @param radius the outer radius of the chart.<br>
	 *            As {@link String} and ending with '%', percentage of the maximum radius.
	 */
	public void setRadiusPercentage(String radius) {
		setValue(Property.RADIUS, radius);
	}

	/**
	 * Returns the outer radius of the chart.<br>
	 * As {@link String} and ending with '%', percentage of the maximum radius.
	 * 
	 * @return the outer radius of the chart.<br>
	 *         As {@link String} and ending with '%', percentage of the maximum radius.
	 */
	@Override
	public String getRadiusPercentage() {
		// checks if is in percentage (string)
		if (isType(Property.RADIUS, ObjectType.STRING)) {
			return getValue(Property.RADIUS, getDefaultValues().getRadiusPercentage());
		}
		// if here, the value is a number or not exist
		// returns the defaults
		return getDefaultValues().getRadiusPercentage();
	}

	/**
	 * Sets the starting angle to draw arcs from.
	 * 
	 * @param rotation starting angle to draw arcs from.
	 */
	public void setRotation(double rotation) {
		setValue(Property.ROTATION, rotation);
	}

	/**
	 * Returns the starting angle to draw arcs from.
	 * 
	 * @return starting angle to draw arcs from.
	 */
	@Override
	public double getRotation() {
		return getValue(Property.ROTATION, getDefaultValues().getRotation());
	}

	/**
	 * Sets the sweep to allow arcs to cover.
	 * 
	 * @param circumference the sweep to allow arcs to cover.
	 */
	public void setCircumference(double circumference) {
		setValue(Property.CIRCUMFERENCE, circumference);
	}

	/**
	 * Returns the sweep to allow arcs to cover.
	 * 
	 * @return the sweep to allow arcs to cover.
	 */
	@Override
	public double getCircumference() {
		return getValue(Property.CIRCUMFERENCE, getDefaultValues().getCircumference());
	}

	/**
	 * Sets the base axis for the dataset. Use {@link IndexAxis#Y} for horizontal bar.
	 * 
	 * @param indexAxis the base axis for the dataset
	 */
	public void setIndexAxis(IndexAxis indexAxis) {
		setValue(Property.INDEX_AXIS, indexAxis);
	}

	/**
	 * Returns the base axis for the dataset, only for bar options.
	 * 
	 * @return the base axis for the dataset, only for bar options
	 */
	@Override
	public IndexAxis getIndexAxis() {
		return getValue(Property.INDEX_AXIS, IndexAxis.values(), getDefaultValues().getIndexAxis());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#isSpanGaps()
	 */
	@Override
	public boolean isSpanGaps() {
		return spanGapHandler.isSpanGaps();
	}

}