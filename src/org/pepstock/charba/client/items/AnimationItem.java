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

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.enums.Easing;

/**
 * The onProgress and onComplete event are useful for synchronizing an external draw to the chart animation.<br>
 * This is a wrapper of the CHART.JS item with all needed info.<br>
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class AnimationItem extends NativeObjectContainer {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		CURRENT_STEP("currentStep"),
		NUM_STEPS("numSteps"),
		EASING("easing");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use into native object.
		 * 
		 * @param value value of property name
		 */
		private Property(String value) {
			this.value = value;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.Key#value()
		 */
		@Override
		public String value() {
			return value;
		}

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
	 * @return the current animation frame number. Default is {@link UndefinedValues#DOUBLE}.
	 */
	public double getCurrentStep() {
		return getValue(Property.CURRENT_STEP, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the total number of animation frames.
	 * 
	 * @return the total number of animation frames. Default is {@link UndefinedValues#DOUBLE}.
	 */
	public double getNumSteps() {
		return getValue(Property.NUM_STEPS, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the animation easing to use.
	 * 
	 * @return the animation easing to use. Default is {@link org.pepstock.charba.client.enums.Easing#EASE_OUT_QUART}
	 */
	public Easing getEasing() {
		return getValue(Property.EASING, Easing.values(), Easing.EASE_OUT_QUART);
	}
}