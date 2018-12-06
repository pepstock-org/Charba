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

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.Easing;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.commons.NativeObjectContainer;

/**
 * The onProgress and onComplete event are useful for synchronizing an external draw to the chart animation.<br>
 * This is a wrapper of the CHART.JS item with all needed info.<br>
 * 
 * @author Andrea "Stock" Stocchero
 * @version 2.0
 */
public final class AnimationItem extends NativeObjectContainer {
	
	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		currentStep,
		numSteps,
		easing
	}
	
	/**
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	AnimationItem(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the current animation frame number.
	 * 
	 * @return the current animation frame number. Default is {@link org.pepstock.charba.client.jsinterop.items.UndefinedValues#DOUBLE}.
	 */
	public double getCurrentStep() {
		return getValue(Property.currentStep, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the total number of animation frames.
	 * 
	 * @return the total number of animation frames. Default is {@link org.pepstock.charba.client.jsinterop.items.UndefinedValues#DOUBLE}.
	 */
	public double getNumSteps() {
		return getValue(Property.numSteps, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the animation easing to use.
	 * 
	 * @return the animation easing to use. Default is {@link org.pepstock.charba.client.enums.Easing#easeInOutQuart}
	 */
	public Easing getEasing() {
		return getValue(Property.easing, Easing.class, Easing.easeOutQuart);
	}
}