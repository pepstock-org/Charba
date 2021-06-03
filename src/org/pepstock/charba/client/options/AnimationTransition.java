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

import org.pepstock.charba.client.ChartEnvelop;
import org.pepstock.charba.client.UpdateConfiguration;
import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.HasCallbackScope;
import org.pepstock.charba.client.commons.Envelop;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultAnimationTransition;

/**
 * Abstract options to define the animation for a specific update mode (transition).
 * 
 * @author Andrea "Stock" Stocchero
 */
public class AnimationTransition extends AbstractNode implements IsDefaultAnimationTransition, HasCallbackScope {

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		ANIMATION("animation"),
		ANIMATIONS("animations");

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

	// scope of the callbacks
	private final String scope;
	// animation instance
	private final Animation animation;
	// animations instance
	private final Animations animations;

	/**
	 * Creates an animation transitions container for the {@link UpdateConfiguration}.
	 * 
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param envelop envelop which contains the native object to map java script properties
	 * @param scope scope of the options
	 */
	public AnimationTransition(Key childKey, IsDefaultAnimationTransition defaultValues, ChartEnvelop<NativeObject> envelop, String scope) {
		this(null, null, defaultValues, Envelop.checkAndGetIfValid(envelop).getContent(), scope);
	}

	/**
	 * Creates an animation transitions container with the native object where ANIMATION property must be managed.
	 * 
	 * @param parent parent node to use to add this element where changed
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 * @param scope scope of the options
	 */
	AnimationTransition(AbstractNode parent, Key childKey, IsDefaultAnimationTransition defaultValues, NativeObject nativeObject, String scope) {
		super(parent, childKey, nativeObject);
		// checks default value instance
		checkDefaultValuesArgument(defaultValues);
		// checks if scope is consistent
		this.scope = Checker.checkAndGetIfValid(scope, "Scope argument");
		// gets all sub elements
		this.animation = new Animation(this, Property.ANIMATION, defaultValues.getAnimation(), getValue(Property.ANIMATION));
		this.animations = new Animations(this, Property.ANIMATIONS, defaultValues.getAnimations(), getValue(Property.ANIMATIONS));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.HasCallbackScope#getScope()
	 */
	@Override
	public final String getScope() {
		return scope;
	}

	/**
	 * Returns the animation element.
	 * 
	 * @return the animation
	 */
	@Override
	public final Animation getAnimation() {
		return animation;
	}

	/**
	 * Returns the animations collection element.
	 * 
	 * @return the animations collection
	 */
	@Override
	public final Animations getAnimations() {
		return animations;
	}

	/**
	 * Returns the native object instance.
	 * 
	 * @return the native object instance.
	 */
	final NativeObject nativeObject() {
		return super.getNativeObject();
	}
}