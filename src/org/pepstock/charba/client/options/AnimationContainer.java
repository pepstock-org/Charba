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
import org.pepstock.charba.client.defaults.IsDefaultAnimationContainer;

/**
 * Manages the ANIMATION, ANIMATIONS and TRANSITIONS properties of options in order to use the same logic among all options/configuration and datasets.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class AnimationContainer extends AnimationTransition {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		TRANSITIONS("transitions");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use in the native object.
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

	// default transitions values
	private final Transitions transitions;

	/**
	 * Creates an animation container with the envelop of the native object where animations properties must be managed.
	 * 
	 * @param defaultValues default provider
	 * @param envelop envelop with a native object to map java script properties
	 * @param scope scope of the options
	 */
	public AnimationContainer(IsDefaultAnimationContainer defaultValues, DataEnvelop<NativeObject> envelop, String scope) {
		this(null, null, defaultValues, IsEnvelop.checkAndGetIfValid(envelop).getContent(), scope);
	}

	/**
	 * Creates an animation container with the native object where animations properties must be managed.
	 * 
	 * @param parent parent node to use to add this element where changed
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 * @param scope scope of the options
	 */
	AnimationContainer(AbstractNode parent, IsDefaultAnimationContainer defaultValues, NativeObject nativeObject, String scope) {
		this(parent, null, defaultValues, nativeObject, scope);
	}

	/**
	 * Creates an animation container with the native object where animations properties must be managed.
	 * 
	 * @param parent parent node to use to add this element where changed
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 * @param scope scope of the options
	 */
	AnimationContainer(AbstractNode parent, Key childKey, IsDefaultAnimationContainer defaultValues, NativeObject nativeObject, String scope) {
		super(parent, childKey, defaultValues, nativeObject, scope);
		// gets all sub elements
		this.transitions = new Transitions(this, Property.TRANSITIONS, defaultValues.getTransitions(), getValue(Property.TRANSITIONS), scope);
	}

	/**
	 * Returns the animation transition element.
	 * 
	 * @return the animation transition
	 */
	Transitions getTransitions() {
		return transitions;
	}

	/**
	 * Enables or disables the animation.
	 * 
	 * @param enabled if <code>true</code> the animation is enabled otherwise <code>false</code> to disable it.
	 */
	void setAnimationEnabled(boolean enabled) {
		// checks if disabling
		if (!enabled) {
			setValueAndAddToParent(AnimationTransition.Property.ANIMATION, false);
		} else {
			// if here is enabling
			setValueAndAddToParent(AnimationTransition.Property.ANIMATION, getAnimation().nativeObject());
		}
	}

	/**
	 * Returns <code>true</code> if animation is enabled, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if animation is enabled, otherwise <code>false</code>
	 */
	boolean isAnimationEnabled() {
		return !isType(AnimationTransition.Property.ANIMATION, ObjectType.BOOLEAN);
	}

}
