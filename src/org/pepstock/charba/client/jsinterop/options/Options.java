package org.pepstock.charba.client.jsinterop.options;

import java.util.List;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.Event;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.jsinterop.commons.ArrayListHelper;
import org.pepstock.charba.client.jsinterop.commons.ArrayObject;
import org.pepstock.charba.client.jsinterop.commons.ArrayString;
import org.pepstock.charba.client.jsinterop.commons.AssignHelper;
import org.pepstock.charba.client.jsinterop.commons.CallbackProxy;
import org.pepstock.charba.client.jsinterop.commons.JsFactory;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions;
import org.pepstock.charba.client.jsinterop.events.ChartNativeEvent;
import org.pepstock.charba.client.jsinterop.items.DatasetItem;
import org.pepstock.charba.client.jsinterop.items.SizeItem;

import jsinterop.annotations.JsFunction;

public class Options extends BaseModel<Options, IsDefaultOptions, NativeOptions>{
	
	@JsFunction
	public interface ChartClickCallback {
		void call(Object context, ChartNativeEvent event, ArrayObject<DatasetItem> items);
	}

	@JsFunction
	public interface ChartHoverCallback {
		void call(Object context, ChartNativeEvent event, ArrayObject<DatasetItem> items);
	}

	@JsFunction
	public interface ChartResizeCallback {
		void call(Object context, Object chart, SizeItem size);
	}
	
	private final Animation animation;
	
	private final Hover hover;
	
	private final Layout layout;
	
	private final Elements elements;
	
	private final Title title;

	private final Legend legend;
	
	private final Tooltips tooltips;
	
	private Scale scale;
	
	private final Scales scales;
	
	private final CallbackProxy<ChartResizeCallback> resizeCallbackProxy = JsFactory.newCallbackProxy();

	private final CallbackProxy<ChartClickCallback> clickCallbackProxy = JsFactory.newCallbackProxy();

	private final CallbackProxy<ChartHoverCallback> hoverCallbackProxy = JsFactory.newCallbackProxy();

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		onResize,
		onClick,
		onHover
	}

	public Options(IsDefaultOptions defaultValues) {
		this(defaultValues, null);
	}

	protected Options(IsDefaultOptions defaultValues, NativeOptions delegated) {
		// if delegated == null, is global or chart, noit config
		super(defaultValues, delegated == null ? new NativeOptions() : delegated, delegated == null);
		animation = new Animation(this, getDefaultValues().getAnimation(), getDelegated().getAnimation());
		elements = new Elements(this, defaultValues, getDelegated().getElements());
		hover = new Hover(this, getDefaultValues().getHover(), getDelegated().getHover());
		layout = new Layout(this, defaultValues, getDelegated().getLayout());
		title = new Title(this, getDefaultValues().getTitle(), getDelegated().getTitle());
		legend = new Legend(this, getDefaultValues().getLegend(),getDelegated().getLegend());
		tooltips = new Tooltips(this, getDefaultValues().getTooltips(), getDelegated().getTooltips());
		if (getDelegated().getScale() != null) {
			scale = new Scale(this, getDefaultValues().getScale(), getDelegated().getScale());
		}
		scales = new Scales(this, getDefaultValues().getScale(), getDelegated().getScales());
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
	public final Animation getAnimation() {
		return animation;
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
	 * @return the legend
	 */
	public final Legend getLegend() {
		return legend;
	}

	/**
	 * @return the tooltips
	 */
	public final Tooltips getTooltips() {
		return tooltips;
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
			getDelegated().setEvents(AssignHelper.serialize(events));
		}
	}

	/**
	 * Returns the browser events that the chart should listen to for tooltips and hovering.
	 * 
	 * @return the browser events that the chart should listen to for tooltips and hovering.
	 */
	public List<Event> getEvents() {
		return ArrayListHelper.build(Event.class, getDelegated().getEvents());
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
		return AssignHelper.check(getDelegated().isResponsive(), getDefaultValues().isResponsive());
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
		return AssignHelper.check(getDelegated().getResponsiveAnimationDuration(), getDefaultValues().getResponsiveAnimationDuration());
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
		return AssignHelper.check(getDelegated().isMaintainAspectRatio(), getDefaultValues().isMaintainAspectRatio());
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
		return AssignHelper.check(getDelegated().getDevicePixelRatio(), getDefaultValues().getDevicePixelRatio());
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
		return AssignHelper.check(getDelegated().getDefaultColor(), getDefaultValues().getDefaultColor());
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
		return AssignHelper.check(getDelegated().getDefaultFontColor(), getDefaultValues().getDefaultFontColor());
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
		return AssignHelper.check(getDelegated().getDefaultFontSize(), getDefaultValues().getDefaultFontSize());
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
		return AssignHelper.deserialize(AssignHelper.check(getDelegated().getDefaultFontStyle(), getDefaultValues().getDefaultFontStyle()), FontStyle.class, FontStyle.normal);
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
		return AssignHelper.check(getDelegated().getDefaultFontFamily(), getDefaultValues().getDefaultFontFamily());
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
		return AssignHelper.check(getDelegated().isShowLines(), getDefaultValues().isShowLines());
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
		return AssignHelper.check(getDelegated().isSpanGaps(), getDefaultValues().isSpanGaps());
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
		return AssignHelper.check(getDelegated().getCutoutPercentage(), getDefaultValues().getCutoutPercentage());
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
		return AssignHelper.check(getDelegated().getRotation(), getDefaultValues().getRotation());
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
		return AssignHelper.check(getDelegated().getCircumference(), getDefaultValues().getCircumference());
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
		return AssignHelper.check(getDelegated().getStartAngle(), getDefaultValues().getStartAngle());
	}

	public void setOnResize(ChartResizeCallback callback) {
		if (isEventEnabled()) {
			if (callback != null) {
				resizeCallbackProxy.setCallback(callback);
				getDelegated().setOnResize(resizeCallbackProxy.getProxy());	
			} else {
				remove(Property.onResize);
			}
		} else {
			throw new UnsupportedOperationException("This 'options' is not created to configure a chart and unables to set an event callback.");
		}
	}

	public void setOnClick(ChartClickCallback callback) {
		if (isEventEnabled()) {
			if (callback != null) {
				clickCallbackProxy.setCallback(callback);
				getDelegated().setOnClick(clickCallbackProxy.getProxy());	
			} else {
				remove(Property.onClick);
			}
		} else {
			throw new UnsupportedOperationException("This 'options' is not created to configure a chart and unables to set an event callback.");
		}
	}

	public void setOnHover(ChartHoverCallback callback) {
		if (isEventEnabled()) {
			if (callback != null) {
				hoverCallbackProxy.setCallback(callback);
				getDelegated().setOnHover(hoverCallbackProxy.getProxy());	
			} else {
				remove(Property.onHover);
			}
		} else {
			throw new UnsupportedOperationException("This 'options' is not created to configure a chart and unables to set an event callback.");
		}

	}
	
	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.BaseModel#addToParent()
	 */
	@Override
	protected void addToParent() {
	}
}
