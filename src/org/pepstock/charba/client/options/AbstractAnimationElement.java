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
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultAnimationElement;

/**
 * It animates charts out of the box. A number of options are provided to configure how the animation looks and how long it takes.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class AbstractAnimationElement extends AbstractModel<Animation, IsDefaultAnimationElement> implements IsDefaultAnimationElement {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		DURATION("duration");

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
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param animation animation options of the chart.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	AbstractAnimationElement(Animation animation, Key childKey, IsDefaultAnimationElement defaultValues, NativeObject nativeObject) {
		super(animation, childKey, defaultValues, nativeObject);
	}

	/**
	 * Sets the number of milliseconds an animation takes.
	 * 
	 * @param milliseconds the number of milliseconds an animation takes.
	 */
	public void setDuration(int milliseconds) {
		setValue(Property.DURATION, milliseconds);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the number of milliseconds an animation takes.
	 * 
	 * @return the number of milliseconds an animation takes.
	 */
	public int getDuration() {
		return getValue(Property.DURATION, getDefaultValues().getDuration());
	}

}