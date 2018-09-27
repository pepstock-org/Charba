/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package org.pepstock.charba.client.jsinterop;

import org.pepstock.charba.client.enums.Easing;
import org.pepstock.charba.client.jsinterop.commons.Checker;
import org.pepstock.charba.client.jsinterop.commons.Enumer;
import org.pepstock.charba.client.jsinterop.defaults.DefaultOptions;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Object can be provided with additional configuration for the update/render process.<br>
 * This is useful when update is manually called inside an event handler and some different animation is desired.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType
public final class UpdateConfiguration {

	@JsProperty(name = "easing")
	native void setNativeEasing(String easing);

	@JsProperty(name = "easing")
	native String getNativeEasing();

	@JsProperty(name = "duration")
	native void setNativeDuration(int duration);

	@JsProperty(name = "duration")
	native int getNativeDuration();

	@JsProperty(name = "lazy")
	native void setNativeLazy(boolean lazy);

	@JsProperty(name = "lazy")
	native boolean isNativeLazy();

	/**
	 * Sets the animation easing function.
	 * 
	 * @param easing animation easing function.
	 * @see org.pepstock.charba.client.enums.Easing
	 */
	@JsMethod
	public void setEasing(Easing easing) {
		setNativeEasing(easing.name());
	}

	/**
	 * Returns the animation easing function.
	 * 
	 * @return the animation easing function. Default is {@link org.pepstock.charba.client.enums.Easing#easeOutQuart}.
	 * @see org.pepstock.charba.client.enums.Easing
	 */
	@JsMethod
	public Easing getEasing() {
		return Enumer.deserialize(getNativeEasing(), DefaultOptions.get().getAnimation().getEasing(), Easing.class, Easing.easeOutQuart);
	}

	/**
	 * Sets the time for the animation of the redraw in milliseconds.
	 * 
	 * @param milliseconds time for the animation of the redraw in milliseconds.
	 */
	@JsMethod
	public void setDuration(int milliseconds) {
		setNativeDuration(milliseconds);
	}

	/**
	 * Returns the time for the animation of the redraw in milliseconds.
	 * 
	 * @return time for the animation of the redraw in milliseconds. Default is 1000.
	 */
	@JsMethod
	public int getDuration() {
		return Checker.check(getNativeDuration(), DefaultOptions.get().getAnimation().getDuration());
	}

	/**
	 * If true, the animation can be interrupted by other animations.
	 * 
	 * @param intersect if true, the animation can be interrupted by other animations.
	 */
	@JsMethod
	public void setLazy(boolean lazy) {
		setNativeLazy(lazy);
	}

	/**
	 * If true, the animation can be interrupted by other animations
	 * 
	 * @return if true, the animation can be interrupted by other animations. Default is false.
	 */
	@JsMethod
	public boolean isLazy() {
		return Checker.check(isNativeLazy(), false);
	}

}