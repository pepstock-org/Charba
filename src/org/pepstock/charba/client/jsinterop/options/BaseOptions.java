package org.pepstock.charba.client.jsinterop.options;

import java.util.List;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.enums.Event;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.jsinterop.callbacks.LegendCallback;
import org.pepstock.charba.client.jsinterop.commons.ArrayListHelper;
import org.pepstock.charba.client.jsinterop.commons.ArrayString;
import org.pepstock.charba.client.jsinterop.commons.Checker;
import org.pepstock.charba.client.jsinterop.commons.Enumer;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions;
import org.pepstock.charba.client.jsinterop.items.UndefinedValues;

import jsinterop.annotations.JsFunction;

public abstract class BaseOptions<A extends Animation, L extends Legend> extends BaseModel<BaseOptions<A,L>, IsDefaultOptions, NativeOptions>{
	
	/**
	 * Called to generate an HTML legend.
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	@JsFunction
	interface ProxyGenerateLegendCallback {
		/**
		 * Called to generate an HTML legend.
		 * 
		 * @return an HTML string which represents the legend.
		 */
		String call(Object context);
	}
	
	private final Hover hover;
	
	private final Layout layout;
	
	private final Elements elements;
	
	private final Title title;

	private final Tooltips tooltips;
	
	private final Plugins plugins;
	
	private Scale scale;
	
	private final Scales scales;
	
	private LegendCallback legendCallback = null;
	
	BaseOptions(IsDefaultOptions defaultValues) {
		this(defaultValues, null);
	}

	BaseOptions(IsDefaultOptions defaultValues, NativeOptions delegated) {
		// if delegated == null, is global or chart, noit config
		super(defaultValues, delegated == null ? new NativeOptions() : delegated);
		elements = new Elements(this, defaultValues, getDelegated().getElements());
		hover = new Hover(this, getDefaultValues().getHover(), getDelegated().getHover());
		layout = new Layout(this, defaultValues, getDelegated().getLayout());
		title = new Title(this, getDefaultValues().getTitle(), getDelegated().getTitle());
		tooltips = new Tooltips(this, getDefaultValues().getTooltips(), getDelegated().getTooltips());
		// FIXME check visibilita
		scale = new Scale(this, getDefaultValues().getScale(), getDelegated().getScale());
		scales = new Scales(this, getDefaultValues().getScale(), getDelegated().getScales());
		plugins = new Plugins(this, getDelegated().getPlugins());
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
	 * @return the animation
	 */
	public abstract A getAnimation();

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
	 * @return the legend
	 */
	public abstract L getLegend();

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
	
	/**
	 * @return the legendCallBack
	 */
	public LegendCallback getLegendCallback() {
		return legendCallback;
	}

	/**
	 * @param legendCallBack the legendCallBack to set
	 */
	public void setLegendCallback(LegendCallback legendCallBack) {
		this.legendCallback = legendCallBack;
	}
	
	public String getCharbaId() {
		return Checker.check(getDelegated().getCharbaId(), UndefinedValues.STRING);
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
			getDelegated().setEvents(new ArrayString());
		} else {
			getDelegated().setEvents(ArrayString.of(events));
		}
	}

	/**
	 * Returns the browser events that the chart should listen to for tooltips and hovering.
	 * 
	 * @return the browser events that the chart should listen to for tooltips and hovering.
	 */
	public List<Event> getEvents() {
		return ArrayListHelper.list(Event.class, getDelegated().getEvents());
	}

	/**
	 * Returns if should chart be animated or not.
	 * 
	 * @return if should chart be animated or not. Default value is <code>true</code>.
	 */
	// FIXME
//	public boolean isAnimationEnable() {
//		return has(Property.animation);
//	}

	/**
	 * Sets the resizing of the chart canvas when its container does.
	 * 
	 * @param responsive the resizing of the chart canvas when its container does.
	 */
	public void setResponsive(boolean responsive) {
		getDelegated().setResponsive(responsive);
	}

	/**
	 * Returns the resizing of the chart canvas when its container does.
	 * 
	 * @return the resizing of the chart canvas when its container does. Default is true.
	 */
	public boolean isResponsive() {
		return Checker.check(getDelegated().isResponsive(), getDefaultValues().isResponsive());
	}

	/**
	 * Sets the duration in milliseconds it takes to animate to new size after a resize event.
	 * 
	 * @param milliseconds the duration in milliseconds it takes to animate to new size after a resize event.
	 */
	public void setResponsiveAnimationDuration(int milliseconds) {
		getDelegated().setResponsiveAnimationDuration(milliseconds);
	}

	/**
	 * Returns the duration in milliseconds it takes to animate to new size after a resize event.
	 * 
	 * @return the duration in milliseconds it takes to animate to new size after a resize event. Default is 0.
	 */
	public int getResponsiveAnimationDuration() {
		return Checker.check(getDelegated().getResponsiveAnimationDuration(), getDefaultValues().getResponsiveAnimationDuration());
	}

	/**
	 * Sets the maintaining of the original canvas aspect ratio (width / height) when resizing.
	 * 
	 * @param maintainAspectRatio the maintaining of the original canvas aspect ratio (width / height) when resizing.
	 */
	public void setMaintainAspectRatio(boolean maintainAspectRatio) {
		getDelegated().setMaintainAspectRatio(maintainAspectRatio);
	}

	/**
	 * Returns the the maintaining of the original canvas aspect ratio (width / height) when resizing.
	 * 
	 * @return the maintaining of the original canvas aspect ratio (width / height) when resizing. Default is true.
	 */
	public boolean isMaintainAspectRatio() {
		return Checker.check(getDelegated().isMaintainAspectRatio(), getDefaultValues().isMaintainAspectRatio());
	}
	
	/**
	 * The chart's canvas will use a 1:1 pixel ratio, unless the physical display has a higher pixel ratio (e.g. Retina displays).
	 * Setting devicePixelRatio to a value other than 1 will force the canvas size to be scaled by that amount.
	 * 
	 * @param ratio the pixel ratio.
	 */
	public void setDevicePixelRatio(double ratio) {
		getDelegated().setDevicePixelRatio(ratio);
	}

	/**
	 * The chart's canvas will use a 1:1 pixel ratio, unless the physical display has a higher pixel ratio (e.g. Retina displays).
	 * Setting devicePixelRatio to a value other than 1 will force the canvas size to be scaled by that amount.
	 * Returns  the pixel ratio.
	 * 
	 * @return  the pixel ratio. Default is <code>window.devicePixelRatio</code>.
	 */
	public double getDevicePixelRatio() {
		return Checker.check(getDelegated().getDevicePixelRatio(), getDefaultValues().getDevicePixelRatio());
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
		getDelegated().setDefaultColor(defaultColor);
	}

	/**
	 * Returns the default color to use in the chart, on all objects, if not override by the specific configuration.
	 * @return color to use into chart. Default is "rgba(0,0,0,0.1)"
	 */
	public String getDefaultColorAsString() {
		return Checker.check(getDelegated().getDefaultColor(), getDefaultValues().getDefaultColor());
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
		getDelegated().setDefaultFontColor(defaultFontColor);
	}

	/**
	 * Returns the default font color to use in the chart, on all objects, if not override by the specific configuration.
	 * @return  font color to use into chart. Default is #666.
	 */
	public String getDefaultFontColorAsString() {
		return Checker.check(getDelegated().getDefaultFontColor(), getDefaultValues().getDefaultFontColor());
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
		getDelegated().setDefaultFontSize(fontSize);
	}

	/**
	 * Returns the font size to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @return Font size into chart. Default is 12.
	 */
	public int getDefaultFontSize() {
		return Checker.check(getDelegated().getDefaultFontSize(), getDefaultValues().getDefaultFontSize());
	}

	/**
	 * Sets the font style to use in the chart, on all objects, if not override by the specific configuration, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @param fontStyle Font style to use in the chart, on all objects, if not override by the specific configuration, follows CSS font-style options (i.e. normal, italic, oblique, initial,
	 *            inherit).
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public void setDefaultFontStyle(FontStyle fontStyle) {
		getDelegated().setDefaultFontStyle(fontStyle.name());
	}

	/**
	 * Returns the font style to use in the chart, on all objects, if not override by the specific configuration, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @return the font styleto use in the chart, on all objects, if not override by the specific configuration, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 *         Default is {@link org.pepstock.charba.client.enums.FontStyle#normal}.
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public FontStyle getDefaultFontStyle() {
		return Enumer.deserialize(getDelegated().getDefaultFontStyle(), getDefaultValues().getDefaultFontStyle(), FontStyle.class, FontStyle.normal);
	}

	/**
	 * Sets the font family to use in the chart, on all objects, if not override by the specific configuration, follows CSS font-family options.
	 * 
	 * @param fontFamily Font family to use in the chart, on all objects, if not override by the specific configuration, follows CSS font-family options.
	 */
	public void setDefaultFontFamily(String fontFamily) {
		getDelegated().setDefaultFontFamily(fontFamily);
	}

	/**
	 * Returns the font family to use in the chart, on all objects, if not override by the specific configuration, follows CSS font-family options.
	 * 
	 * @return Font family to use in the chart, on all objects, if not override by the specific configuration, follows CSS font-family options. Default is 'Helvetica Neue', 'Helvetica', 'Arial',
	 *         sans-serif
	 */
	public String getDefaultFontFamily() {
		return Checker.check(getDelegated().getDefaultFontFamily(), getDefaultValues().getDefaultFontFamily());
	}

	/**
	 * If false, the lines between points are not drawn.
	 * 
	 * @param showLine If false, the lines between points are not drawn.
	 */
	public void setShowLines(boolean showLine) {
		getDelegated().setShowLines(showLine);
	}

	/**
	 * If false, the lines between points are not drawn.
	 * 
	 * @return If false, the lines between points are not drawn. Default is true.
	 */
	public boolean isShowLines() {
		return Checker.check(getDelegated().isShowLines(), getDefaultValues().isShowLines());
	}
	
	/**
	 * If false, NaN data causes a break in the line.
	 * 
	 * @param spanGaps If false, NaN data causes a break in the line.
	 */
	public void setSpanGaps(boolean spanGaps) {
		getDelegated().setSpanGaps(spanGaps);
	}

	/**
	 * If false, NaN data causes a break in the line.
	 * 
	 * @return If false, NaN data causes a break in the line. Default is false.
	 */
	public boolean isSpanGaps() {
		return Checker.check(getDelegated().isSpanGaps(), getDefaultValues().isSpanGaps());
	}
	
	/**
	 * Sets the percentage of the chart that is cut out of the middle.
	 * 
	 * @param cutoutPercentage the percentage of the chart that is cut out of the middle.
	 */
	public void setCutoutPercentage(double cutoutPercentage) {
		getDelegated().setCutoutPercentage(cutoutPercentage);
	}

	/**
	 * Returns the the percentage of the chart that is cut out of the middle.
	 * 
	 * @return the percentage of the chart that is cut out of the middle. Default is 0.
	 */
	public double getCutoutPercentage() {
		return Checker.check(getDelegated().getCutoutPercentage(), getDefaultValues().getCutoutPercentage());
	}

	/**
	 * Sets the starting angle to draw arcs from.
	 * 
	 * @param rotation starting angle to draw arcs from.
	 */
	public void setRotation(double rotation) {
		getDelegated().setRotation(rotation);
	}

	/**
	 * Returns the starting angle to draw arcs from.
	 * 
	 * @return starting angle to draw arcs from. Default is <code>-0.5 * Math.PI</code>.
	 */
	public double getRotation() {
		return Checker.check(getDelegated().getRotation(), getDefaultValues().getRotation());
	}

	/**
	 * Sets the sweep to allow arcs to cover.
	 * 
	 * @param circumference the sweep to allow arcs to cover.
	 */
	public void setCircumference(double circumference) {
		getDelegated().setCircumference(circumference);
	}

	/**
	 * Returns the the sweep to allow arcs to cover.
	 * 
	 * @return the sweep to allow arcs to cover. Default is <code>2 * Math.PI</code>.
	 */
	public double getCircumference() {
		return Checker.check(getDelegated().getCircumference(), getDefaultValues().getCircumference());
	}

	/**
	 * Sets the starting angle to draw arcs for the first item in a dataset.
	 * 
	 * @param startAngle starting angle to draw arcs for the first item in a dataset.
	 */
	public void setStartAngle(double startAngle) {
		getDelegated().setStartAngle(startAngle);
	}

	/**
	 * Returns the starting angle to draw arcs for the first item in a dataset.
	 * 
	 * @return starting angle to draw arcs for the first item in a dataset. Default is <code>-0.5 * Math.PI</code>.
	 */
	public double getStartAngle() {
		return Checker.check(getDelegated().getStartAngle(), getDefaultValues().getStartAngle());
	}
	
	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.BaseModel#addToParent()
	 */
	@Override
	protected void addToParent() {
	}
}
