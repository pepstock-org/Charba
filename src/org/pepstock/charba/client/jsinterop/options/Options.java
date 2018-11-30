package org.pepstock.charba.client.jsinterop.options;

import java.util.List;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.Event;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.jsinterop.commons.ArrayListHelper;
import org.pepstock.charba.client.jsinterop.commons.ArrayString;
import org.pepstock.charba.client.jsinterop.commons.Id;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions;

public class Options extends AbstractModel<Options, IsDefaultOptions> implements IsDefaultOptions{
	
	private final Animation animation;

	private final Legend legend;
	
	private final Hover hover;
	
	private final Layout layout;
	
	private final Elements elements;
	
	private final Title title;

	private final Tooltips tooltips;
	
	private final Plugins plugins;
	
	private Scale scale;
	
	private final Scales scales;

	/**
	 * Name of fields of JavaScript object.
	 */
	enum Property implements Key
	{
		// sub objects
		hover,
		elements,
		layout,
		animation,
		tooltips,
		legend,
		title,
		scale,
		scales,
		plugins,
		responsive,
		responsiveAnimationDuration,
		maintainAspectRatio,
		events,
		defaultColor,
		defaultFontColor,
		defaultFontFamily,
		defaultFontSize,
		defaultFontStyle,
		devicePixelRatio,
		showLines,
		spanGaps,
		cutoutPercentage,
		rotation,
		circumference,
		startAngle
	}
	
	protected Options(IsDefaultOptions defaultValues) {
		this(defaultValues, null);
	}

	protected Options(IsDefaultOptions defaultValues, NativeObject nativeObject) {
		// if delegated == null, is global or chart, noit config
		super(defaultValues, nativeObject);
		animation = new Animation(this, Property.animation, getDefaultValues().getAnimation(), getValue(Property.animation));
		legend = new Legend(this, Property.legend, getDefaultValues().getLegend(), getValue(Property.legend));
		elements = new Elements(this, Property.elements, defaultValues.getElements(), getValue(Property.elements));
		hover = new Hover(this, Property.hover, getDefaultValues().getHover(), getValue(Property.hover));
		layout = new Layout(this, Property.layout, defaultValues.getLayout(), getValue(Property.layout));
		title = new Title(this, Property.title, getDefaultValues().getTitle(), getValue(Property.title));
		tooltips = new Tooltips(this, Property.tooltips, getDefaultValues().getTooltips(), getValue(Property.tooltips));
		// FIXME check visibilita
		scale = new Scale(this, Property.scale, getDefaultValues().getScale(), getValue(Property.scale));
		scales = new Scales(this, Property.scales, getDefaultValues().getScale(), getValue(Property.scales));
		plugins = new Plugins(this, Property.plugins, getValue(Property.plugins));
	}
	
	/**
	 * @return the animation
	 */
	public Animation getAnimation() {
		return animation;
	}

	/**
	 * @return the legend
	 */
	public Legend getLegend() {
		return legend;
	}

	/**
	 * @return the scales
	 */
	public final Scales getScales() {
		return scales;
	}

	/**
	 * @return the scale
	 */
	public final Scale getScale() {
		return scale;
	}
	
	/**
	 * @param scale the scale to set
	 */
	public final void setScale(Scale scale) {
		this.scale = scale;
	}

	/**
	 * @return the hover
	 */
	public final Hover getHover() {
		return hover;
	}

	/**
	 * @return the layout
	 */
	public final Layout getLayout() {
		return layout;
	}

	/**
	 * @return the elements
	 */
	public final Elements getElements() {
		return elements;
	}

	/**
	 * @return the title
	 */
	public final Title getTitle() {
		return title;
	}

	/**
	 * @return the tooltips
	 */
	public final Tooltips getTooltips() {
		return tooltips;
	}

	/**
	 * @return the plugins
	 */
	public final Plugins getPlugins() {
		return plugins;
	}
	
	public String getCharbaId() {
		return Id.get(this);
	}

	/**
	 * Sets the browser events that the chart should listen to for tooltips and hovering.
	 * 
	 * @param events the browser events that the chart should listen to for tooltips and hovering.
	 */
	public void setEvents(Event... events) {
		// checks the events passed
		// if empty
		if (events == null || events.length == 0) {
			// remove java script property
			remove(Property.events);
		} else {
			setArrayValue(Property.events, ArrayString.of(events));
		}
	}

	/**
	 * Returns the browser events that the chart should listen to for tooltips and hovering.
	 * 
	 * @return the browser events that the chart should listen to for tooltips and hovering.
	 */
	public List<Event> getEvents() {
		return ArrayListHelper.list(Event.class, getArrayValue(Property.events));
	}

	/**
	 * Returns if should chart be animated or not.
	 * 
	 * @return if should chart be animated or not. Default value is <code>true</code>.
	 */
	public boolean isAnimationEnable() {
		return has(Property.animation);
	}

	/**
	 * Sets the resizing of the chart canvas when its container does.
	 * 
	 * @param responsive the resizing of the chart canvas when its container does.
	 */
	public void setResponsive(boolean responsive) {
		setValue(Property.responsive, responsive);
	}

	/**
	 * Returns the resizing of the chart canvas when its container does.
	 * 
	 * @return the resizing of the chart canvas when its container does. Default is true.
	 */
	public boolean isResponsive() {
		return getValue(Property.responsive, getDefaultValues().isResponsive());
	}

	/**
	 * Sets the duration in milliseconds it takes to animate to new size after a resize event.
	 * 
	 * @param milliseconds the duration in milliseconds it takes to animate to new size after a resize event.
	 */
	public void setResponsiveAnimationDuration(int milliseconds) {
		setValue(Property.responsiveAnimationDuration, milliseconds);
	}

	/**
	 * Returns the duration in milliseconds it takes to animate to new size after a resize event.
	 * 
	 * @return the duration in milliseconds it takes to animate to new size after a resize event. Default is 0.
	 */
	public int getResponsiveAnimationDuration() {
		return getValue(Property.responsiveAnimationDuration, getDefaultValues().getResponsiveAnimationDuration());
	}

	/**
	 * Sets the maintaining of the original canvas aspect ratio (width / height) when resizing.
	 * 
	 * @param maintainAspectRatio the maintaining of the original canvas aspect ratio (width / height) when resizing.
	 */
	public void setMaintainAspectRatio(boolean maintainAspectRatio) {
		setValue(Property.maintainAspectRatio, maintainAspectRatio);
	}

	/**
	 * Returns the the maintaining of the original canvas aspect ratio (width / height) when resizing.
	 * 
	 * @return the maintaining of the original canvas aspect ratio (width / height) when resizing. Default is true.
	 */
	public boolean isMaintainAspectRatio() {
		return getValue(Property.maintainAspectRatio, getDefaultValues().isMaintainAspectRatio());
	}
	
	/**
	 * The chart's canvas will use a 1:1 pixel ratio, unless the physical display has a higher pixel ratio (e.g. Retina displays).
	 * Setting devicePixelRatio to a value other than 1 will force the canvas size to be scaled by that amount.
	 * 
	 * @param ratio the pixel ratio.
	 */
	public void setDevicePixelRatio(double ratio) {
		setValue(Property.devicePixelRatio, ratio);
	}

	/**
	 * The chart's canvas will use a 1:1 pixel ratio, unless the physical display has a higher pixel ratio (e.g. Retina displays).
	 * Setting devicePixelRatio to a value other than 1 will force the canvas size to be scaled by that amount.
	 * Returns  the pixel ratio.
	 * 
	 * @return  the pixel ratio. Default is <code>window.devicePixelRatio</code>.
	 */
	public double getDevicePixelRatio() {
		return getValue(Property.devicePixelRatio, getDefaultValues().getDevicePixelRatio());
	}

	/**
	 * Sets the default color to use in the chart, on all objects, if not override by the specific configuration.
	 * @param defaultColor color to use into chart. 
	 */
	public void setDefaultColor(IsColor defaultColor) {
		setDefaultColor(defaultColor.toRGBA());
	}

	/**
	 * Sets the default color to use in the chart, on all objects, if not override by the specific configuration.
	 * @param defaultColor color to use into chart. 
	 */
	public void setDefaultColor(String defaultColor) {
		setValue(Property.defaultColor, defaultColor);
	}

	/**
	 * Returns the default color to use in the chart, on all objects, if not override by the specific configuration.
	 * @return color to use into chart. Default is "rgba(0,0,0,0.1)"
	 */
	public String getDefaultColorAsString() {
		return getValue(Property.defaultColor, getDefaultValues().getDefaultColorAsString());
	}

	/**
	 * Returns the default color to use in the chart, on all objects, if not override by the specific configuration.
	 * @return color to use into chart. Default is "rgba(0,0,0,0.1)"
	 */
	public IsColor getDefaultColor() {
		return ColorBuilder.parse(getDefaultColorAsString());
	}

	/**
	 * Sets the default font color to use in the chart, on all objects, if not override by the specific configuration.
	 * @param defaultFontColor font color to use into chart. 
	 */
	public void setDefaultFontColor(IsColor defaultFontColor) {
		setDefaultFontColor(defaultFontColor.toRGBA());
	}

	/**
	 * Sets the default font color to use in the chart, on all objects, if not override by the specific configuration.
	 * @param defaultFontColor font color to use into chart. 
	 */
	public void setDefaultFontColor(String defaultFontColor) {
		setValue(Property.defaultFontColor, defaultFontColor);
	}

	/**
	 * Returns the default font color to use in the chart, on all objects, if not override by the specific configuration.
	 * @return  font color to use into chart. Default is #666.
	 */
	public String getDefaultFontColorAsString() {
		return getValue(Property.defaultFontColor, getDefaultValues().getDefaultFontColorAsString());
	}

	/**
	 * Returns the default font color to use in the chart, on all objects, if not override by the specific configuration.
	 * @return  font color to use into chart. Default is #666.
	 */
	public IsColor getDefaultFontColor() {
		return ColorBuilder.parse(getDefaultFontColorAsString());
	}

	/**
	 * Sets the font size to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @param fontSize Font size into chart.
	 */
	public void setDefaultFontSize(int fontSize) {
		setValue(Property.defaultFontSize, fontSize);
	}

	/**
	 * Returns the font size to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @return Font size into chart. Default is 12.
	 */
	public int getDefaultFontSize() {
		return getValue(Property.defaultFontSize, getDefaultValues().getDefaultFontSize());
	}

	/**
	 * Sets the font style to use in the chart, on all objects, if not override by the specific configuration, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @param fontStyle Font style to use in the chart, on all objects, if not override by the specific configuration, follows CSS font-style options (i.e. normal, italic, oblique, initial,
	 *            inherit).
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public void setDefaultFontStyle(FontStyle fontStyle) {
		setValue(Property.defaultFontStyle, fontStyle.name());
	}

	/**
	 * Returns the font style to use in the chart, on all objects, if not override by the specific configuration, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @return the font styleto use in the chart, on all objects, if not override by the specific configuration, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 *         Default is {@link org.pepstock.charba.client.enums.FontStyle#normal}.
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public FontStyle getDefaultFontStyle() {
		return getValue(Property.defaultFontStyle, FontStyle.class, getDefaultValues().getDefaultFontStyle());
	}

	/**
	 * Sets the font family to use in the chart, on all objects, if not override by the specific configuration, follows CSS font-family options.
	 * 
	 * @param fontFamily Font family to use in the chart, on all objects, if not override by the specific configuration, follows CSS font-family options.
	 */
	public void setDefaultFontFamily(String fontFamily) {
		setValue(Property.defaultFontFamily, fontFamily);
	}

	/**
	 * Returns the font family to use in the chart, on all objects, if not override by the specific configuration, follows CSS font-family options.
	 * 
	 * @return Font family to use in the chart, on all objects, if not override by the specific configuration, follows CSS font-family options. Default is 'Helvetica Neue', 'Helvetica', 'Arial',
	 *         sans-serif
	 */
	public String getDefaultFontFamily() {
		return getValue(Property.defaultFontFamily, getDefaultValues().getDefaultFontFamily());
	}

	/**
	 * If false, the lines between points are not drawn.
	 * 
	 * @param showLine If false, the lines between points are not drawn.
	 */
	public void setShowLines(boolean showLine) {
		setValue(Property.showLines, showLine);
	}

	/**
	 * If false, the lines between points are not drawn.
	 * 
	 * @return If false, the lines between points are not drawn. Default is true.
	 */
	public boolean isShowLines() {
		return getValue(Property.showLines, getDefaultValues().isShowLines());
	}
	
	/**
	 * If false, NaN data causes a break in the line.
	 * 
	 * @param spanGaps If false, NaN data causes a break in the line.
	 */
	public void setSpanGaps(boolean spanGaps) {
		setValue(Property.spanGaps, spanGaps);
	}

	/**
	 * If false, NaN data causes a break in the line.
	 * 
	 * @return If false, NaN data causes a break in the line. Default is false.
	 */
	public boolean isSpanGaps() {
		return getValue(Property.spanGaps, getDefaultValues().isSpanGaps());
	}
	
	/**
	 * Sets the percentage of the chart that is cut out of the middle.
	 * 
	 * @param cutoutPercentage the percentage of the chart that is cut out of the middle.
	 */
	public void setCutoutPercentage(double cutoutPercentage) {
		setValue(Property.cutoutPercentage, cutoutPercentage);
	}

	/**
	 * Returns the the percentage of the chart that is cut out of the middle.
	 * 
	 * @return the percentage of the chart that is cut out of the middle. Default is 0.
	 */
	public double getCutoutPercentage() {
		return getValue(Property.cutoutPercentage, getDefaultValues().getCutoutPercentage());
	}

	/**
	 * Sets the starting angle to draw arcs from.
	 * 
	 * @param rotation starting angle to draw arcs from.
	 */
	public void setRotation(double rotation) {
		setValue(Property.rotation, rotation);
	}

	/**
	 * Returns the starting angle to draw arcs from.
	 * 
	 * @return starting angle to draw arcs from. Default is <code>-0.5 * Math.PI</code>.
	 */
	public double getRotation() {
		return getValue(Property.rotation, getDefaultValues().getRotation());
	}

	/**
	 * Sets the sweep to allow arcs to cover.
	 * 
	 * @param circumference the sweep to allow arcs to cover.
	 */
	public void setCircumference(double circumference) {
		setValue(Property.circumference, circumference);
	}

	/**
	 * Returns the the sweep to allow arcs to cover.
	 * 
	 * @return the sweep to allow arcs to cover. Default is <code>2 * Math.PI</code>.
	 */
	public double getCircumference() {
		return getValue(Property.circumference, getDefaultValues().getCircumference());
	}

	/**
	 * Sets the starting angle to draw arcs for the first item in a dataset.
	 * 
	 * @param startAngle starting angle to draw arcs for the first item in a dataset.
	 */
	public void setStartAngle(double startAngle) {
		setValue(Property.startAngle, startAngle);
	}

	/**
	 * Returns the starting angle to draw arcs for the first item in a dataset.
	 * 
	 * @return starting angle to draw arcs for the first item in a dataset. Default is <code>-0.5 * Math.PI</code>.
	 */
	public double getStartAngle() {
		return getValue(Property.startAngle, getDefaultValues().getStartAngle());
	}
}
