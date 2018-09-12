package org.pepstock.charba.client.jsinterop.options;

import java.util.List;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.enums.Event;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.jsinterop.commons.ArrayListHelper;
import org.pepstock.charba.client.jsinterop.commons.ArrayString;
import org.pepstock.charba.client.jsinterop.commons.AssignHelper;
import org.pepstock.charba.client.jsinterop.commons.IsDelegated;
import org.pepstock.charba.client.jsinterop.options.animation.Animation;
import org.pepstock.charba.client.jsinterop.options.animation.NativeAnimation;
import org.pepstock.charba.client.jsinterop.options.elements.Elements;
import org.pepstock.charba.client.jsinterop.options.elements.NativeElements;
import org.pepstock.charba.client.jsinterop.options.hover.Hover;
import org.pepstock.charba.client.jsinterop.options.hover.NativeHover;
import org.pepstock.charba.client.jsinterop.options.layout.Layout;
import org.pepstock.charba.client.jsinterop.options.layout.NativeLayout;
import org.pepstock.charba.client.jsinterop.options.title.NativeTitle;
import org.pepstock.charba.client.jsinterop.options.title.Title;

public class Options implements IsDelegated<NativeOptions>{
	
	private final NativeOptions delegated;
	
	private final IsDefaultOptions defaultValues;
	
	private Animation animation;
	
	private Hover hover;
	
	private Layout layout;
	
	private Elements elements;
	
	private Title title;
	
	public Options(IsDefaultOptions defaultValues) {
		this(new NativeOptions(), defaultValues);
	}

	public Options(NativeOptions delegated, IsDefaultOptions defaultValues) {
		this.delegated = delegated;
		this.defaultValues = defaultValues;
		NativeAnimation animationObject = delegated.getAnimation();
		if (animationObject != null) {
			animation = new Animation(animationObject, defaultValues.getAnimation());
		} else {
			setAnimation(new Animation(defaultValues.getAnimation()));
		}
		NativeElements elementsObject = this.delegated.getElements();
		if (elementsObject != null) {
			elements = new Elements(elementsObject, defaultValues);
		} else {
			setElements(new Elements(defaultValues));
		}
		NativeHover hoverObject = this.delegated.getHover();
		if (hoverObject != null) {
			hover = new Hover(hoverObject, defaultValues.getHover());
		} else {
			setHover(new Hover(defaultValues.getHover()));
		}
		NativeLayout layoutObject = this.delegated.getLayout();
		if (layoutObject != null) {
			layout = new Layout(layoutObject, defaultValues);
		} else {
			setLayout(new Layout(defaultValues));
		}
		NativeTitle titleObject = this.delegated.getTitle();
		if (titleObject != null) {
			title = new Title(titleObject, defaultValues.getTitle());
		} else {
			setTitle(new Title(defaultValues.getTitle()));
		}
	}
	
	public Animation getAnimation() {
		return animation;
	}
	
	public void setAnimation(Animation animation) {
		this.animation = animation;
		delegated.setAnimation(animation.getDelegated());
	}

	public Elements getElements() {
		return elements;
	}

	public void setElements(Elements elements) {
		this.elements = elements;
		delegated.setElements(elements.getDelegated());
	}

	public Hover getHover() {
		return hover;
	}

	public void setHover(Hover hover) {
		this.hover = hover;
		delegated.setHover(hover.getDelegated());
	}

	public Layout getLayout() {
		return layout;
	}

	public void setLayout(Layout layout) {
		this.layout = layout;
		delegated.setLayout(layout.getDelegated());
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
		delegated.setTitle(title.getDelegated());
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
			delegated.setEvents(new ArrayString());
		} else {
			delegated.setEvents(AssignHelper.serialize(events));
		}
	}

	/**
	 * Returns the browser events that the chart should listen to for tooltips and hovering.
	 * 
	 * @return the browser events that the chart should listen to for tooltips and hovering.
	 */
	public List<Event> getEvents() {
		return ArrayListHelper.build(Event.class, AssignHelper.check(delegated.getEvents(), defaultValues.getEvents()));
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
		delegated.setResponsive(responsive);
	}

	/**
	 * Returns the resizing of the chart canvas when its container does.
	 * 
	 * @return the resizing of the chart canvas when its container does. Default is true.
	 */
	public boolean isResponsive() {
		return AssignHelper.check(delegated.isResponsive(), defaultValues.isResponsive());
	}

	/**
	 * Sets the duration in milliseconds it takes to animate to new size after a resize event.
	 * 
	 * @param milliseconds the duration in milliseconds it takes to animate to new size after a resize event.
	 */
	public void setResponsiveAnimationDuration(int milliseconds) {
		delegated.setResponsiveAnimationDuration(milliseconds);
	}

	/**
	 * Returns the duration in milliseconds it takes to animate to new size after a resize event.
	 * 
	 * @return the duration in milliseconds it takes to animate to new size after a resize event. Default is 0.
	 */
	public int getResponsiveAnimationDuration() {
		return AssignHelper.check(delegated.getResponsiveAnimationDuration(), defaultValues.getResponsiveAnimationDuration());
	}

	/**
	 * Sets the maintaining of the original canvas aspect ratio (width / height) when resizing.
	 * 
	 * @param maintainAspectRatio the maintaining of the original canvas aspect ratio (width / height) when resizing.
	 */
	public void setMaintainAspectRatio(boolean maintainAspectRatio) {
		delegated.setMaintainAspectRatio(maintainAspectRatio);
	}

	/**
	 * Returns the the maintaining of the original canvas aspect ratio (width / height) when resizing.
	 * 
	 * @return the maintaining of the original canvas aspect ratio (width / height) when resizing. Default is true.
	 */
	public boolean isMaintainAspectRatio() {
		return AssignHelper.check(delegated.isMaintainAspectRatio(), defaultValues.isMaintainAspectRatio());
	}
	
	/**
	 * The chart's canvas will use a 1:1 pixel ratio, unless the physical display has a higher pixel ratio (e.g. Retina displays).
	 * Setting devicePixelRatio to a value other than 1 will force the canvas size to be scaled by that amount.
	 * 
	 * @param ratio the pixel ratio.
	 */
	public void setDevicePixelRatio(double ratio) {
		delegated.setDevicePixelRatio(ratio);
	}

	/**
	 * The chart's canvas will use a 1:1 pixel ratio, unless the physical display has a higher pixel ratio (e.g. Retina displays).
	 * Setting devicePixelRatio to a value other than 1 will force the canvas size to be scaled by that amount.
	 * Returns  the pixel ratio.
	 * 
	 * @return  the pixel ratio. Default is <code>window.devicePixelRatio</code>.
	 */
	public double getDevicePixelRatio() {
		return AssignHelper.check(delegated.getDevicePixelRatio(), defaultValues.getDevicePixelRatio());
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
		delegated.setDefaultColor(defaultColor);
	}

	/**
	 * Returns the default color to use in the chart, on all objects, if not override by the specific configuration.
	 * @return color to use into chart. Default is "rgba(0,0,0,0.1)"
	 */
	public String getDefaultColorAsString() {
		return AssignHelper.check(delegated.getDefaultColor(), defaultValues.getDefaultColor());
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
		delegated.setDefaultFontColor(defaultFontColor);
	}

	/**
	 * Returns the default font color to use in the chart, on all objects, if not override by the specific configuration.
	 * @return  font color to use into chart. Default is #666.
	 */
	public String getDefaultFontColorAsString() {
		return AssignHelper.check(delegated.getDefaultFontColor(), defaultValues.getDefaultFontColor());
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
		delegated.setDefaultFontSize(fontSize);
	}

	/**
	 * Returns the font size to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @return Font size into chart. Default is 12.
	 */
	public int getDefaultFontSize() {
		return AssignHelper.check(delegated.getDefaultFontSize(), defaultValues.getDefaultFontSize());
	}

	/**
	 * Sets the font style to use in the chart, on all objects, if not override by the specific configuration, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @param fontStyle Font style to use in the chart, on all objects, if not override by the specific configuration, follows CSS font-style options (i.e. normal, italic, oblique, initial,
	 *            inherit).
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public void setDefaultFontStyle(FontStyle fontStyle) {
		delegated.setDefaultFontStyle(fontStyle.name());
	}

	/**
	 * Returns the font style to use in the chart, on all objects, if not override by the specific configuration, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @return the font styleto use in the chart, on all objects, if not override by the specific configuration, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 *         Default is {@link org.pepstock.charba.client.enums.FontStyle#normal}.
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public FontStyle getDefaultFontStyle() {
		return AssignHelper.deserialize(AssignHelper.check(delegated.getDefaultFontStyle(), defaultValues.getDefaultFontStyle()), FontStyle.class, FontStyle.normal);
	}

	/**
	 * Sets the font family to use in the chart, on all objects, if not override by the specific configuration, follows CSS font-family options.
	 * 
	 * @param fontFamily Font family to use in the chart, on all objects, if not override by the specific configuration, follows CSS font-family options.
	 */
	public void setDefaultFontFamily(String fontFamily) {
		delegated.setDefaultFontFamily(fontFamily);
	}

	/**
	 * Returns the font family to use in the chart, on all objects, if not override by the specific configuration, follows CSS font-family options.
	 * 
	 * @return Font family to use in the chart, on all objects, if not override by the specific configuration, follows CSS font-family options. Default is 'Helvetica Neue', 'Helvetica', 'Arial',
	 *         sans-serif
	 */
	public String getDefaultFontFamily() {
		return AssignHelper.check(delegated.getDefaultFontFamily(), defaultValues.getDefaultFontFamily());
	}

	/**
	 * If false, the lines between points are not drawn.
	 * 
	 * @param showLine If false, the lines between points are not drawn.
	 */
	public void setShowLines(boolean showLine) {
		delegated.setShowLines(showLine);
	}

	/**
	 * If false, the lines between points are not drawn.
	 * 
	 * @return If false, the lines between points are not drawn. Default is true.
	 */
	public boolean isShowLines() {
		return AssignHelper.check(delegated.isShowLines(), defaultValues.isShowLines());
	}
	
	/**
	 * If false, NaN data causes a break in the line.
	 * 
	 * @param spanGaps If false, NaN data causes a break in the line.
	 */
	public void setSpanGaps(boolean spanGaps) {
		delegated.setSpanGaps(spanGaps);
	}

	/**
	 * If false, NaN data causes a break in the line.
	 * 
	 * @return If false, NaN data causes a break in the line. Default is false.
	 */
	public boolean isSpanGaps() {
		return AssignHelper.check(delegated.isSpanGaps(), defaultValues.isSpanGaps());
	}
	
	/**
	 * Sets the percentage of the chart that is cut out of the middle.
	 * 
	 * @param cutoutPercentage the percentage of the chart that is cut out of the middle.
	 */
	public void setCutoutPercentage(double cutoutPercentage) {
		delegated.setCutoutPercentage(cutoutPercentage);
	}

	/**
	 * Returns the the percentage of the chart that is cut out of the middle.
	 * 
	 * @return the percentage of the chart that is cut out of the middle. Default is 0.
	 */
	public double getCutoutPercentage() {
		return AssignHelper.check(delegated.getCutoutPercentage(), defaultValues.getCutoutPercentage());
	}

	/**
	 * Sets the starting angle to draw arcs from.
	 * 
	 * @param rotation starting angle to draw arcs from.
	 */
	public void setRotation(double rotation) {
		delegated.setRotation(rotation);
	}

	/**
	 * Returns the starting angle to draw arcs from.
	 * 
	 * @return starting angle to draw arcs from. Default is <code>-0.5 * Math.PI</code>.
	 */
	public double getRotation() {
		return AssignHelper.check(delegated.getRotation(), defaultValues.getRotation());
	}

	/**
	 * Sets the sweep to allow arcs to cover.
	 * 
	 * @param circumference the sweep to allow arcs to cover.
	 */
	public void setCircumference(double circumference) {
		delegated.setCircumference(circumference);
	}

	/**
	 * Returns the the sweep to allow arcs to cover.
	 * 
	 * @return the sweep to allow arcs to cover. Default is <code>2 * Math.PI</code>.
	 */
	public double getCircumference() {
		return AssignHelper.check(delegated.getCircumference(), defaultValues.getCircumference());
	}

	/**
	 * Sets the starting angle to draw arcs for the first item in a dataset.
	 * 
	 * @param startAngle starting angle to draw arcs for the first item in a dataset.
	 */
	public void setStartAngle(double startAngle) {
		delegated.setStartAngle(startAngle);
	}

	/**
	 * Returns the starting angle to draw arcs for the first item in a dataset.
	 * 
	 * @return starting angle to draw arcs for the first item in a dataset. Default is <code>-0.5 * Math.PI</code>.
	 */
	public double getStartAngle() {
		return AssignHelper.check(delegated.getStartAngle(), defaultValues.getStartAngle());
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.commons.IsDelegated#getDelegated()
	 */
	@Override
	public NativeOptions getDelegated() {
		return delegated;
	}
	
}
