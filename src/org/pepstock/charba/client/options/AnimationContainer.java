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

import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.IsEnvelop;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.data.DataEnvelop;
import org.pepstock.charba.client.defaults.IsDefaultAnimation;

/**
 * Manages the ANIMATION property of options in order to use the same logic among all options/configuration and datasets.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class AnimationContainer extends AbstractNode {

	// animation instance
	private final Animation animation;

	/**
	 * Name of properties of native object.
	 */
	protected enum Property implements Key
	{
		ANIMATION("animation");

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
	 * Creates an animation container with the envelop of the native object where ANIMATION property must be managed.
	 * 
	 * @param defaultValues default provider
	 * @param envelop envelop with a native object to map java script properties
	 */
	public AnimationContainer(IsDefaultAnimation defaultValues, DataEnvelop<NativeObject> envelop) {
		this(defaultValues, IsEnvelop.checkAndGetIfValid(envelop).getContent());
	}

	/**
	 * Creates an animation container with the native object where ANIMATION property must be managed.
	 * 
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	AnimationContainer(IsDefaultAnimation defaultValues, NativeObject nativeObject) {
		super(nativeObject);
		// checks default value instance
		if (defaultValues == null) {
			throw new IllegalArgumentException("Default value argument is null");
		}
		// gets all sub elements
		this.animation = new Animation(this, Property.ANIMATION, defaultValues, getValue(Property.ANIMATION));
	}

	/**
	 * Returns the animation element.
	 * 
	 * @return the animation
	 */
	public Animation getAnimation() {
		return animation;
	}

	/**
	 * Enables or disables the animation.
	 * 
	 * @param enabled if <code>true</code> the animation is enabled otherwise <code>false</code> to disable it.
	 */
	void setAnimationEnabled(boolean enabled) {
		// checks if disabling
		if (!enabled) {
			setValueAndAddToParent(Property.ANIMATION, false);
		} else {
			// if here is enabling
			setValueAndAddToParent(Property.ANIMATION, animation.nativeObject());
		}
	}

	/**
	 * Returns <code>true</code> if animation is enabled, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if animation is enabled, otherwise <code>false</code>
	 */
	boolean isAnimationEnabled() {
		return !ObjectType.BOOLEAN.equals(type(Property.ANIMATION));
	}

}
