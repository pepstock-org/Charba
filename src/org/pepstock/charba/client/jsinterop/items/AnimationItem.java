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
package org.pepstock.charba.client.jsinterop.items;

import org.pepstock.charba.client.enums.Easing;
import org.pepstock.charba.client.jsinterop.commons.AssignHelper;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * The onProgress and onComplete event are useful for synchronizing an external draw to the chart animation.<br>
 * This is the CHART.JS item with all needed info.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name="Object")
public final class AnimationItem extends NativeObject {
	
	/**
	 * Returns the current Animation frame number.
	 * 
	 * @return the current Animation frame number. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	@JsProperty (name = "currentStep")
	native double getNativeCurrentStep();

	/**
	 * Returns the total number of animation frames.
	 * 
	 * @return the total number of animation frames. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	@JsProperty (name = "numSteps")
	native double getNativeNumSteps();
	
	/**
	 * Returns the animation easing to use.
	 * 
	 * @return the animation easing to use.
	 * @see org.pepstock.charba.client.enums.Easing
	 */
	@JsProperty (name = "easing")
	native String getNativeEasing();

	/**
	 * Returns the current Animation frame number.
	 * 
	 * @return the current Animation frame number. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	@JsOverlay
	public final double getCurrentStep() {
		return AssignHelper.check(getNativeCurrentStep(), UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the total number of animation frames.
	 * 
	 * @return the total number of animation frames. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	@JsOverlay
	public final double getNumSteps() {
		return AssignHelper.check(getNativeNumSteps(), UndefinedValues.DOUBLE);
	}
	
	/**
	 * Returns the animation easing to use.
	 * 
	 * @return the animation easing to use.
	 * @see org.pepstock.charba.client.enums.Easing
	 */
	@JsOverlay
	public final Easing getEasing() {
		return AssignHelper.deserialize(AssignHelper.check(getNativeEasing(), Easing.easeOutQuart.name()), Easing.class, Easing.easeOutQuart);
	}

}