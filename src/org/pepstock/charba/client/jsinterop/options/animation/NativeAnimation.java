package org.pepstock.charba.client.jsinterop.options.animation;

import org.pepstock.charba.client.jsinterop.commons.NativeObject;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * It animates charts out of the box. A number of options are provided to configure how the animation looks and how long it
 * takes.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name="Object")
public final class NativeAnimation extends NativeObject {
	
	/**
	 * Sets the animation easing.
	 * 
	 * @param easing animation easing.
	 */
	@JsProperty
	public native void setEasing(String easing);

	/**
	 * Returns the animation easing.
	 * 
	 * @return animation easing. Default value is {@link org.pepstock.charba.client.enums.Easing#easeOutQuart}.
	 */
	@JsProperty
	public native String getEasing();

	/**
	 * Sets the number of milliseconds an animation takes.
	 * 
	 * @param milliseconds the number of milliseconds an animation takes.
	 */
	@JsProperty
	public native void setDuration(int milliseconds);

	/**
	 * Returns the number of milliseconds an animation takes.
	 * 
	 * @return the number of milliseconds an animation takes. 
	 */
	@JsProperty
	public native int getDuration();
	
	/**
	 * If true, the chart will animate in with a rotation animation.
	 * 
	 * @param animateRotate If true, the chart will animate in with a rotation animation.
	 */
	@JsProperty
	public native void setAnimateRotate(boolean animateRotate);

	/**
	 * If true, the chart will animate in with a rotation animation.
	 * 
	 * @return If true, the chart will animate in with a rotation animation. 
	 */
	@JsProperty
	public native boolean isAnimateRotate();
	/**
	 * If true, will animate scaling the chart from the center outwards.
	 * 
	 * @param animateScale If true, will animate scaling the chart from the center outwards.
	 */
	@JsProperty
	public native void setAnimateScale(boolean animateScale);

	/**
	 * If true, will animate scaling the chart from the center outwards.
	 * 
	 * @return If true, will animate scaling the chart from the center outwards.
	 */
	@JsProperty
	public native boolean isAnimateScale();
	
}
