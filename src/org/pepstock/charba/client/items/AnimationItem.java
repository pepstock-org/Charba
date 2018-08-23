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
package org.pepstock.charba.client.items;

import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.JavaScriptObjectContainer;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.Easing;

/**
 * The onProgress and onComplete event are useful for synchronizing an external draw to the chart animation.<br>
 * This is the CHART.JS item with all needed info.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class AnimationItem extends JavaScriptObjectContainer {
	
	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		currentStep,
		numSteps,
		easing
	}

	public AnimationItem(GenericJavaScriptObject javaScriptObject) {
		super(javaScriptObject);
	}

	/**
	 * Returns the current Animation frame number.
	 * 
	 * @return the current Animation frame number.
	 */
	public double getCurrentStep() {
		return getValue(Property.currentStep, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the total number of animation frames.
	 * 
	 * @return the total number of animation frames.
	 */
	public double getNumSteps() {
		return getValue(Property.numSteps, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the animation easing to use.
	 * 
	 * @return the animation easing to use.
	 * @see org.pepstock.charba.client.enums.Easing
	 */
	public Easing getEasing() {
		return getValue(Property.easing, Easing.class, Easing.easeOutQuart);
	}

}