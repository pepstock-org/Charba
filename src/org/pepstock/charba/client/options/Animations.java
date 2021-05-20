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
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.defaults.IsDefaultAnimationCollection;
import org.pepstock.charba.client.defaults.IsDefaultAnimations;
import org.pepstock.charba.client.defaults.globals.DefaultAnimationCollection;

/**
 * It animates charts out of the box.<br>
 * A number of options are provided to configure how the animation looks and how long it takes.<br>
 * This configuration item is configuring the common animations properties, ANIMATIONS name space.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Animations extends AbstractNode implements IsAnimations {

	// defaults instance
	private final IsDefaultAnimations defaultValues;

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param parent parent node of the chart options.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	Animations(AbstractNode parent, Key childKey, IsDefaultAnimations defaultValues, NativeObject nativeObject) {
		super(parent, childKey, nativeObject);
		// stores defaults which has been already checked on super class
		this.defaultValues = defaultValues;
	}

	/**
	 * Enables or disables an animation collection instance in the animation options.
	 * 
	 * @param collection collection instance used to check in the animation options
	 * @param enabled if <code>true</code> it enables an animation collection
	 */
	@Override
	public void setEnabled(IsAnimationCollectionKey collection, boolean enabled) {
		// checks if collection is consistent
		if (IsAnimationCollectionKey.isValid(collection)) {
			// checks if is enabling
			if (enabled) {
				// checks if cached
				if (isType(collection, ObjectType.BOOLEAN)) {
					// it must be removed in order to enable the default
					// the type of collection should be boolean because
					// set to false previously
					remove(collection);
				}
			} else {
				// sets the collection to false
				setValueAndAddToParent(collection, false);
			}
		}
	}

	/**
	 * Returns <code>true</code> if the animation collection is enabled, otherwise <code>false</code>.
	 * 
	 * @param collection collection instance used to check in the animation options
	 * @return <code>true</code> if the animation collection is enabled, otherwise <code>false</code>
	 */
	@Override
	public boolean isEnabled(IsAnimationCollectionKey collection) {
		// checks if collection is consistent
		return IsAnimationCollectionKey.isValid(collection) && !isType(collection, ObjectType.BOOLEAN);
	}

	/**
	 * Returns <code>true</code> if an animation collection instance is stored in the the animation options.
	 * 
	 * @param collection collection instance used to check in the animation options
	 * @return <code>true</code> if an animation collection instance is stored in the the animation options
	 */
	@Override
	public boolean has(IsAnimationCollectionKey collection) {
		// checks if collection is consistent
		if (IsAnimationCollectionKey.isValid(collection)) {
			// checks if is cached
			return isType(collection, ObjectType.OBJECT);
		}
		// if here, the collection is not valid
		// then returns false
		return false;
	}

	/**
	 * Returns an animation collection instance if stored in the the animation options.
	 * 
	 * @param collection collection instance used to get for animation options
	 * @return an animation collection instance or <code>null</code> if does not exists
	 */
	@Override
	public AnimationCollection get(IsAnimationCollectionKey collection) {
		// checks if collection is consistent
		if (has(collection)) {
			// gets defaults
			IsDefaultAnimationCollection defaultCollection = defaultValues.get(collection);
			// creates the collection
			return new AnimationCollection(this, collection, defaultCollection == null ? new DefaultAnimationCollection(collection) : defaultCollection, getValue(collection));
		}
		// if here, the collection is not valid
		// then returns null
		return null;
	}

	/**
	 * Sets an animation collection instance to store in the animation options.
	 * 
	 * @param collection collection instance used to get for animation options
	 * @param animationCollection an animation collection instance to set
	 */
	@Override
	public void set(IsAnimationCollectionKey collection, AnimationCollection animationCollection) {
		// checks if is consistent
		if (IsAnimationCollectionKey.isValid(collection)) {
			// stores in the object
			setValueAndAddToParent(collection, animationCollection);
		}
	}

	/**
	 * Creates an animation collection instance and stores in the the animation options.
	 * 
	 * @param collection collection key used to create the animation collections
	 * @return a collection animation options
	 */
	@Override
	public AnimationCollection create(IsAnimationCollectionKey collection) {
		// gets defaults
		IsDefaultAnimationCollection defaultCollection = defaultValues.get(collection);
		// creates the collection
		AnimationCollection options = new AnimationCollection(this, collection, defaultCollection == null ? new DefaultAnimationCollection(collection) : defaultCollection, null);
		// stores in the object
		setValueAndAddToParent(collection, options);
		// returns the animation options
		return options;
	}

	/**
	 * Removes an animation collection previously added.
	 * 
	 * @param collection collection instance used to remove from animation options
	 */
	@Override
	public void remove(IsAnimationCollectionKey collection) {
		// checks if collection is consistent and if the collection has been previously added
		if (IsAnimationCollectionKey.isValid(collection)) {
			// remove from object
			remove(collection);
		}
	}

}