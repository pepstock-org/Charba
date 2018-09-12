package org.pepstock.charba.client.jsinterop.options;

import org.pepstock.charba.client.jsinterop.commons.ArrayString;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.options.animation.NativeAnimation;
import org.pepstock.charba.client.jsinterop.options.elements.NativeElements;
import org.pepstock.charba.client.jsinterop.options.hover.NativeHover;
import org.pepstock.charba.client.jsinterop.options.layout.NativeLayout;
import org.pepstock.charba.client.jsinterop.options.title.NativeTitle;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public final class NativeOptions extends NativeObject{
	
	@JsProperty
	public native NativeAnimation getAnimation();

	@JsProperty
	public native void setAnimation(NativeAnimation animation);

	@JsProperty
	public native NativeHover getHover();

	@JsProperty
	public native void setHover(NativeHover hover);

	@JsProperty
	public native NativeLayout getLayout();

	@JsProperty
	public native void setLayout(NativeLayout layout);

	@JsProperty
	public native NativeElements getElements();

	@JsProperty
	public native void setElements(NativeElements elements);

	@JsProperty
	public native NativeTitle getTitle();

	@JsProperty
	public native void setTitle(NativeTitle title);
	
	@JsProperty
	public native void setResponsive(boolean responsive);

	@JsProperty
	public native boolean isResponsive();
	
	@JsProperty
	public native void setEvents(ArrayString events);

	@JsProperty
	public native ArrayString getEvents();

	/**
	 * Sets the duration in milliseconds it takes to animate to new size after a resize event.
	 * 
	 * @param milliseconds the duration in milliseconds it takes to animate to new size after a resize event.
	 */
	@JsProperty
	public native void setResponsiveAnimationDuration(int milliseconds);

	/**
	 * Returns the duration in milliseconds it takes to animate to new size after a resize event.
	 * 
	 * @return the duration in milliseconds it takes to animate to new size after a resize event. Default is 0.
	 */
	@JsProperty
	public native int getResponsiveAnimationDuration();

	/**
	 * Sets the maintaining of the original canvas aspect ratio (width / height) when resizing.
	 * 
	 * @param maintainAspectRatio the maintaining of the original canvas aspect ratio (width / height) when resizing.
	 */
	@JsProperty
	public native void setMaintainAspectRatio(boolean maintainAspectRatio);

	/**
	 * Returns the the maintaining of the original canvas aspect ratio (width / height) when resizing.
	 * 
	 * @return the maintaining of the original canvas aspect ratio (width / height) when resizing. Default is true.
	 */
	@JsProperty
	public native boolean isMaintainAspectRatio();
	
	/**
	 * The chart's canvas will use a 1:1 pixel ratio, unless the physical display has a higher pixel ratio (e.g. Retina displays).
	 * Setting devicePixelRatio to a value other than 1 will force the canvas size to be scaled by that amount.
	 * 
	 * @param ratio the pixel ratio.
	 */
	@JsProperty
	public native void setDevicePixelRatio(double ratio);

	/**
	 * The chart's canvas will use a 1:1 pixel ratio, unless the physical display has a higher pixel ratio (e.g. Retina displays).
	 * Setting devicePixelRatio to a value other than 1 will force the canvas size to be scaled by that amount.
	 * Returns  the pixel ratio.
	 * 
	 * @return  the pixel ratio. Default is <code>window.devicePixelRatio</code>.
	 */
	@JsProperty
	public native double getDevicePixelRatio();

	/**
	 * Sets the default color to use in the chart, on all objects, if not override by the specific configuration.
	 * @param defaultColor color to use into chart. 
	 */
	@JsProperty
	public native void setDefaultColor(String defaultColor);

	/**
	 * Returns the default color to use in the chart, on all objects, if not override by the specific configuration.
	 * @return color to use into chart. Default is "rgba(0,0,0,0.1)"
	 */
	@JsProperty
	public native String getDefaultColor();

	/**
	 * Sets the default font color to use in the chart, on all objects, if not override by the specific configuration.
	 * @param defaultFontColor font color to use into chart. 
	 */
	@JsProperty
	public native void setDefaultFontColor(String defaultFontColor);

	/**
	 * Returns the default font color to use in the chart, on all objects, if not override by the specific configuration.
	 * @return  font color to use into chart. Default is #666.
	 */
	@JsProperty
	public native String getDefaultFontColor();


	/**
	 * Sets the font size to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @param fontSize Font size into chart.
	 */
	@JsProperty
	public native void setDefaultFontSize(int fontSize);

	/**
	 * Returns the font size to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @return Font size into chart. Default is 12.
	 */
	@JsProperty
	public native int getDefaultFontSize();

	@JsProperty
	public native void setDefaultFontStyle(String defaultFontStyle);

	@JsProperty
	public native String getDefaultFontStyle();

	/**
	 * Sets the font family to use in the chart, on all objects, if not override by the specific configuration, follows CSS font-family options.
	 * 
	 * @param fontFamily Font family to use in the chart, on all objects, if not override by the specific configuration, follows CSS font-family options.
	 */
	@JsProperty
	public native void setDefaultFontFamily(String fontFamily);

	/**
	 * Returns the font family to use in the chart, on all objects, if not override by the specific configuration, follows CSS font-family options.
	 * 
	 * @return Font family to use in the chart, on all objects, if not override by the specific configuration, follows CSS font-family options. Default is 'Helvetica Neue', 'Helvetica', 'Arial',
	 *         sans-serif
	 */
	@JsProperty
	public native String getDefaultFontFamily();

	/**
	 * If false, the lines between points are not drawn.
	 * 
	 * @param showLine If false, the lines between points are not drawn.
	 */
	@JsProperty
	public native void setShowLines(boolean showLine);
	/**
	 * If false, the lines between points are not drawn.
	 * 
	 * @return If false, the lines between points are not drawn. Default is true.
	 */
	@JsProperty
	public native boolean isShowLines();
	
	/**
	 * If false, NaN data causes a break in the line.
	 * 
	 * @param spanGaps If false, NaN data causes a break in the line.
	 */
	@JsProperty
	public native void setSpanGaps(boolean spanGaps);

	/**
	 * If false, NaN data causes a break in the line.
	 * 
	 * @return If false, NaN data causes a break in the line. Default is false.
	 */
	@JsProperty
	public native boolean isSpanGaps();
	
	/**
	 * Sets the percentage of the chart that is cut out of the middle.
	 * 
	 * @param cutoutPercentage the percentage of the chart that is cut out of the middle.
	 */
	@JsProperty
	public native void setCutoutPercentage(double cutoutPercentage);

	/**
	 * Returns the the percentage of the chart that is cut out of the middle.
	 * 
	 * @return the percentage of the chart that is cut out of the middle. Default is 0.
	 */
	@JsProperty
	public native double getCutoutPercentage();
	
	/**
	 * Sets the starting angle to draw arcs from.
	 * 
	 * @param rotation starting angle to draw arcs from.
	 */
	@JsProperty
	public native void setRotation(double rotation);

	/**
	 * Returns the starting angle to draw arcs from.
	 * 
	 * @return starting angle to draw arcs from. Default is <code>-0.5 * Math.PI</code>.
	 */
	@JsProperty
	public native double getRotation();
	

	/**
	 * Sets the sweep to allow arcs to cover.
	 * 
	 * @param circumference the sweep to allow arcs to cover.
	 */
	@JsProperty
	public native void setCircumference(double circumference);

	/**
	 * Returns the the sweep to allow arcs to cover.
	 * 
	 * @return the sweep to allow arcs to cover. Default is <code>2 * Math.PI</code>.
	 */
	@JsProperty
	public native double getCircumference();
	
	/**
	 * Sets the starting angle to draw arcs for the first item in a dataset.
	 * 
	 * @param startAngle starting angle to draw arcs for the first item in a dataset.
	 */
	@JsProperty
	public native void setStartAngle(double startAngle);

	/**
	 * Returns the starting angle to draw arcs for the first item in a dataset.
	 * 
	 * @return starting angle to draw arcs for the first item in a dataset. Default is <code>-0.5 * Math.PI</code>.
	 */
	@JsProperty
	public native double getStartAngle();

}
