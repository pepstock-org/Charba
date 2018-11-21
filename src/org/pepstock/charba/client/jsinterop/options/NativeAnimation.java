package org.pepstock.charba.client.jsinterop.options;

import org.pepstock.charba.client.jsinterop.commons.CallbackProxy;
import org.pepstock.charba.client.jsinterop.commons.NativeName;
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
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name=NativeName.OBJECT)
final class NativeAnimation extends NativeObject {

	/**
	 * Sets the animation easing.
	 * 
	 * @param easing animation easing.
	 */
	@JsProperty
	native void setEasing(String easing);

	/**
	 * Returns the animation easing.
	 * 
	 * @return animation easing. Default value is {@link org.pepstock.charba.client.enums.Easing#easeOutQuart}.
	 */
	@JsProperty
	native String getEasing();

	/**
	 * Sets the number of milliseconds an animation takes.
	 * 
	 * @param milliseconds the number of milliseconds an animation takes.
	 */
	@JsProperty
	native void setDuration(int milliseconds);

	/**
	 * Returns the number of milliseconds an animation takes.
	 * 
	 * @return the number of milliseconds an animation takes. 
	 */
	@JsProperty
	native int getDuration();
	
	/**
	 * If true, the chart will animate in with a rotation animation.
	 * 
	 * @param animateRotate If true, the chart will animate in with a rotation animation.
	 */
	@JsProperty
	native void setAnimateRotate(boolean animateRotate);

	/**
	 * If true, the chart will animate in with a rotation animation.
	 * 
	 * @return If true, the chart will animate in with a rotation animation. 
	 */
	@JsProperty
	native boolean isAnimateRotate();
	/**
	 * If true, will animate scaling the chart from the center outwards.
	 * 
	 * @param animateScale If true, will animate scaling the chart from the center outwards.
	 */
	@JsProperty
	native void setAnimateScale(boolean animateScale);

	/**
	 * If true, will animate scaling the chart from the center outwards.
	 * 
	 * @return If true, will animate scaling the chart from the center outwards.
	 */
	@JsProperty
	native boolean isAnimateScale();
	
	@JsProperty
	native void setOnComplete(CallbackProxy.Proxy proxy);

	@JsProperty
	native void setOnProgress(CallbackProxy.Proxy proxy);


}
