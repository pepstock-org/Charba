package org.pepstock.charba.client.jsinterop.options;

import org.pepstock.charba.client.jsinterop.commons.ArrayString;
import org.pepstock.charba.client.jsinterop.commons.CallbackProxy;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public final class NativeOptions extends NativeObject{

	@JsProperty
	native NativeScale getScale();

	@JsProperty
	native void setScale(NativeScale scale);

	@JsProperty
	native NativeAnimation getAnimation();

	@JsProperty
	native void setAnimation(NativeAnimation animation);

	@JsProperty
	native NativeHover getHover();

	@JsProperty
	native void setHover(NativeHover hover);

	@JsProperty
	native NativeLayout getLayout();

	@JsProperty
	native void setLayout(NativeLayout layout);

	@JsProperty
	native NativeElements getElements();

	@JsProperty
	native void setElements(NativeElements elements);

	@JsProperty
	native NativeTitle getTitle();

	@JsProperty
	native void setTitle(NativeTitle title);

	@JsProperty
	native NativeLegend getLegend();

	@JsProperty
	native void setLegend(NativeLegend legend);

	@JsProperty
	native NativeTooltips getTooltips();

	@JsProperty
	native void setTooltips(NativeTooltips tooltips);

	@JsProperty
	native void setResponsive(boolean responsive);

	@JsProperty
	native boolean isResponsive();
	
	@JsProperty
	native void setEvents(ArrayString events);

	@JsProperty
	native ArrayString getEvents();

	/**
	 * Sets the duration in milliseconds it takes to animate to new size after a resize event.
	 * 
	 * @param milliseconds the duration in milliseconds it takes to animate to new size after a resize event.
	 */
	@JsProperty
	native void setResponsiveAnimationDuration(int milliseconds);

	/**
	 * Returns the duration in milliseconds it takes to animate to new size after a resize event.
	 * 
	 * @return the duration in milliseconds it takes to animate to new size after a resize event. Default is 0.
	 */
	@JsProperty
	native int getResponsiveAnimationDuration();

	/**
	 * Sets the maintaining of the original canvas aspect ratio (width / height) when resizing.
	 * 
	 * @param maintainAspectRatio the maintaining of the original canvas aspect ratio (width / height) when resizing.
	 */
	@JsProperty
	native void setMaintainAspectRatio(boolean maintainAspectRatio);

	/**
	 * Returns the the maintaining of the original canvas aspect ratio (width / height) when resizing.
	 * 
	 * @return the maintaining of the original canvas aspect ratio (width / height) when resizing. Default is true.
	 */
	@JsProperty
	native boolean isMaintainAspectRatio();
	
	/**
	 * The chart's canvas will use a 1:1 pixel ratio, unless the physical display has a higher pixel ratio (e.g. Retina displays).
	 * Setting devicePixelRatio to a value other than 1 will force the canvas size to be scaled by that amount.
	 * 
	 * @param ratio the pixel ratio.
	 */
	@JsProperty
	native void setDevicePixelRatio(double ratio);

	/**
	 * The chart's canvas will use a 1:1 pixel ratio, unless the physical display has a higher pixel ratio (e.g. Retina displays).
	 * Setting devicePixelRatio to a value other than 1 will force the canvas size to be scaled by that amount.
	 * Returns  the pixel ratio.
	 * 
	 * @return  the pixel ratio. Default is <code>window.devicePixelRatio</code>.
	 */
	@JsProperty
	native double getDevicePixelRatio();

	/**
	 * Sets the default color to use in the chart, on all objects, if not override by the specific configuration.
	 * @param defaultColor color to use into chart. 
	 */
	@JsProperty
	native void setDefaultColor(String defaultColor);

	/**
	 * Returns the default color to use in the chart, on all objects, if not override by the specific configuration.
	 * @return color to use into chart. Default is "rgba(0,0,0,0.1)"
	 */
	@JsProperty
	native String getDefaultColor();

	/**
	 * Sets the default font color to use in the chart, on all objects, if not override by the specific configuration.
	 * @param defaultFontColor font color to use into chart. 
	 */
	@JsProperty
	native void setDefaultFontColor(String defaultFontColor);

	/**
	 * Returns the default font color to use in the chart, on all objects, if not override by the specific configuration.
	 * @return  font color to use into chart. Default is #666.
	 */
	@JsProperty
	native String getDefaultFontColor();


	/**
	 * Sets the font size to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @param fontSize Font size into chart.
	 */
	@JsProperty
	native void setDefaultFontSize(int fontSize);

	/**
	 * Returns the font size to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @return Font size into chart. Default is 12.
	 */
	@JsProperty
	native int getDefaultFontSize();

	@JsProperty
	native void setDefaultFontStyle(String defaultFontStyle);

	@JsProperty
	native String getDefaultFontStyle();

	/**
	 * Sets the font family to use in the chart, on all objects, if not override by the specific configuration, follows CSS font-family options.
	 * 
	 * @param fontFamily Font family to use in the chart, on all objects, if not override by the specific configuration, follows CSS font-family options.
	 */
	@JsProperty
	native void setDefaultFontFamily(String fontFamily);

	/**
	 * Returns the font family to use in the chart, on all objects, if not override by the specific configuration, follows CSS font-family options.
	 * 
	 * @return Font family to use in the chart, on all objects, if not override by the specific configuration, follows CSS font-family options. Default is 'Helvetica Neue', 'Helvetica', 'Arial',
	 *         sans-serif
	 */
	@JsProperty
	native String getDefaultFontFamily();

	/**
	 * If false, the lines between points are not drawn.
	 * 
	 * @param showLine If false, the lines between points are not drawn.
	 */
	@JsProperty
	native void setShowLines(boolean showLine);
	/**
	 * If false, the lines between points are not drawn.
	 * 
	 * @return If false, the lines between points are not drawn. Default is true.
	 */
	@JsProperty
	native boolean isShowLines();
	
	/**
	 * If false, NaN data causes a break in the line.
	 * 
	 * @param spanGaps If false, NaN data causes a break in the line.
	 */
	@JsProperty
	native void setSpanGaps(boolean spanGaps);

	/**
	 * If false, NaN data causes a break in the line.
	 * 
	 * @return If false, NaN data causes a break in the line. Default is false.
	 */
	@JsProperty
	native boolean isSpanGaps();
	
	/**
	 * Sets the percentage of the chart that is cut out of the middle.
	 * 
	 * @param cutoutPercentage the percentage of the chart that is cut out of the middle.
	 */
	@JsProperty
	native void setCutoutPercentage(double cutoutPercentage);

	/**
	 * Returns the the percentage of the chart that is cut out of the middle.
	 * 
	 * @return the percentage of the chart that is cut out of the middle. Default is 0.
	 */
	@JsProperty
	native double getCutoutPercentage();
	
	/**
	 * Sets the starting angle to draw arcs from.
	 * 
	 * @param rotation starting angle to draw arcs from.
	 */
	@JsProperty
	native void setRotation(double rotation);

	/**
	 * Returns the starting angle to draw arcs from.
	 * 
	 * @return starting angle to draw arcs from. Default is <code>-0.5 * Math.PI</code>.
	 */
	@JsProperty
	native double getRotation();
	

	/**
	 * Sets the sweep to allow arcs to cover.
	 * 
	 * @param circumference the sweep to allow arcs to cover.
	 */
	@JsProperty
	native void setCircumference(double circumference);

	/**
	 * Returns the the sweep to allow arcs to cover.
	 * 
	 * @return the sweep to allow arcs to cover. Default is <code>2 * Math.PI</code>.
	 */
	@JsProperty
	native double getCircumference();
	
	/**
	 * Sets the starting angle to draw arcs for the first item in a dataset.
	 * 
	 * @param startAngle starting angle to draw arcs for the first item in a dataset.
	 */
	@JsProperty
	native void setStartAngle(double startAngle);

	/**
	 * Returns the starting angle to draw arcs for the first item in a dataset.
	 * 
	 * @return starting angle to draw arcs for the first item in a dataset. Default is <code>-0.5 * Math.PI</code>.
	 */
	@JsProperty
	native double getStartAngle();
	
	@JsProperty
	native void setOnResize(CallbackProxy.Proxy proxy);

	@JsProperty
	native void setOnClick(CallbackProxy.Proxy proxy);

	@JsProperty
	native void setOnHover(CallbackProxy.Proxy proxy);

}
