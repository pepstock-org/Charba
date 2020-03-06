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

/**
 * This is the wrapper of native java script object, passed by CHART.JS during the animation events, which contains the animation item.<br>
 * This is the CHART.JS item with all needed info.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class AnimationObject extends NativeObjectContainer {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		ANIMATION_OBJECT("animationObject");

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
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 */
	// FIXME
	public AnimationObject(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the animation item, by the native java script object.
	 * 
	 * @return the animation item, by the native java script object.
	 */
	public AnimationItem getAnimationItem() {
		// checks if animation object is present
		if (has(Property.ANIMATION_OBJECT)) {
			return new AnimationItem(getValue(Property.ANIMATION_OBJECT));
		} else {
			// if here, no animation obejct then it is already an item
			return new AnimationItem(getNativeObject());
		}
	}

}