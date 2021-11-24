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
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.HasCallbackScope;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.defaults.IsDefaultTransitions;

/**
 * It animates charts out of the box.<br>
 * A number of options are provided to configure how the animation looks and how long it takes.<br>
 * This configuration item is configuring the common transitions properties, TRANSITIONS name space.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Transitions extends AbstractNode implements IsTransitions, HasCallbackScope {

	// defaults instance
	private final IsDefaultTransitions defaultValues;
	// scope of the callbacks
	private final String scope;

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param parent parent node of the chart options.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 * @param scope scope of the options
	 */
	Transitions(AbstractNode parent, Key childKey, IsDefaultTransitions defaultValues, NativeObject nativeObject, String scope) {
		super(parent, childKey, nativeObject);
		// stores defaults which has been already checked on super class
		this.defaultValues = defaultValues;
		// checks if scope is consistent
		this.scope = Checker.checkAndGetIfValid(scope, "Scope argument");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.HasCallbackScope#getScope()
	 */
	@Override
	public String getScope() {
		return scope;
	}

	/**
	 * Returns <code>true</code> if an animation transition instance is stored in the animation options.
	 * 
	 * @param transition transition instance used to check in the animation options
	 * @return <code>true</code> if an animation transition instance is stored in the animation options
	 */
	@Override
	public boolean has(TransitionKey transition) {
		// checks if transition is consistent
		if (TransitionKey.isValid(transition)) {
			// checks if is cached
			return isType(transition, ObjectType.OBJECT);
		}
		// if here, the transition is not valid
		// then returns false
		return false;
	}

	/**
	 * Returns an animation transition instance if stored in the animation options.
	 * 
	 * @param transition transition instance used to get for animation options
	 * @return an animation transition instance or <code>null</code> if does not exists
	 */
	@Override
	public AnimationTransition get(TransitionKey transition) {
		// checks if transition is consistent
		if (has(transition)) {
			// gets from the native object
			return new AnimationTransition(this, transition, defaultValues.get(transition), getValue(transition), getScope());
		}
		// if here, the transition is not valid
		// then returns null
		return null;
	}

	/**
	 * Sets an animation transition instance to store in the animation options.
	 * 
	 * @param transition transition instance used to get for animation options
	 * @param animationTransition an animation transition instance to set
	 */
	@Override
	public void set(TransitionKey transition, AnimationTransition animationTransition) {
		// checks if is consistent and if the has been previously added
		if (TransitionKey.isValid(transition)) {
			// stores in the object
			setValue(transition, animationTransition);
		}
	}

	/**
	 * Creates an animation transition instance and stores in the animation options.
	 * 
	 * @param transition transition key used to create the animation transitions
	 * @return a transition animation options
	 */
	@Override
	public AnimationTransition create(TransitionKey transition) {
		// gets from the native object
		AnimationTransition options = new AnimationTransition(this, transition, defaultValues.get(transition), null, getScope());
		// stores in the object
		setValue(transition, options);
		// returns the animation options
		return options;
	}

	/**
	 * Removes an animation transition previously added.
	 * 
	 * @param transition transition instance used to remove from animation options
	 */
	@Override
	public void remove(TransitionKey transition) {
		// checks if is consistent and if the has been previously added
		if (TransitionKey.isValid(transition)) {
			// removes from object
			remove(transition);
		}
	}
}