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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.defaults.IsDefaultAnimationMode;
import org.pepstock.charba.client.enums.AnimationType;
import org.pepstock.charba.client.enums.DefaultAnimationCollectionKey;

/**
 * Abstract options to define the animation for a specific update mode or for the main animation options.
 * 
 * @author Andrea "Stock" Stocchero
 * @param <T> type of key
 * @param <D> type of default values
 */
abstract class AbstractAnimationMode<T extends Key, D extends IsDefaultAnimationMode> extends AbstractAnimation<T, D> implements IsDefaultAnimationMode {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		CHARBA_CURRENT_NUMBERS_COLLECTION("_charbaCurrentNumbersCollection"),
		CHARBA_CURRENT_COLORS_COLLECTION("_charbaCurrentColorsCollection");

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

	// map to store the custom of animation collections
	// K = collection name, V = animation collection instance
	private final Map<String, AnimationCollection> animationCollections = new HashMap<>();
	// map to store the custom of animation properties
	// K = property name, V = animation property instance
	private final Map<String, AnimationProperty> animationProperties = new HashMap<>();
	// set to store the properties which have been disabled
	private final Set<String> animationDisabledProperties = new HashSet<>();
	// current numbers collection
	private Key currentNumbersCollection = null;
	// current colors collection
	private Key currentColorsCollection = null;

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param parent options parent node of the chart options.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	AbstractAnimationMode(AbstractNode parent, T childKey, D defaultValues, NativeObject nativeObject) {
		super(parent, childKey, defaultValues, nativeObject);
		// gets current collections if there are
		currentColorsCollection = Key.create(getValue(Property.CHARBA_CURRENT_COLORS_COLLECTION, DefaultAnimationCollectionKey.COLORS.value()));
		currentNumbersCollection = Key.create(getValue(Property.CHARBA_CURRENT_NUMBERS_COLLECTION, DefaultAnimationCollectionKey.NUMBERS.value()));
	}

	/**
	 * Sets an animation property instance to animation options.
	 * 
	 * @param animationElement animation property instance to add
	 */
	public void setProperty(AnimationProperty animationElement) {
		// adds and checks if added
		if (setSubElement(animationElement)) {
			// stores the object into the cache
			animationProperties.put(animationElement.getKey().value(), animationElement);
			// checks if the node is already added to parent
			checkAndAddToParent();
		}
	}

	/**
	 * Enables or disables an animation property instance into animation options.
	 * 
	 * @param property property instance used to check into animation options
	 * @param enabled if <code>true</code> it enables an animation property
	 */
	public void setPropertyEnabled(IsAnimationPropertyKey property, boolean enabled) {
		// checks if property is consistent
		if (IsAnimationPropertyKey.isValid(property)) {
			// checks if is enabling
			if (enabled) {
				// checks if cached
				if (animationProperties.containsKey(property.value())) {
					// stores the object
					setValue(property, animationProperties.get(property.value()).nativeObject());
				} else if (ObjectType.BOOLEAN.equals(type(property))) {
					// if here is not cached
					// then it must be removed in order to enable the default
					// the type of property should be boolean because
					// set to false previously
					removeIfExists(property);
				}
				// removes from disabled properties
				animationDisabledProperties.remove(property.value());
			} else {
				// sets the property to false
				setValue(property, false);
				// adds to the disabled properties
				animationDisabledProperties.add(property.value());
			}
		}
	}

	/**
	 * Returns <code>true</code> if the animation property is enabled, otherwise <code>false</code>.
	 * 
	 * @param property property instance used to check into animation options
	 * @return <code>true</code> if the animation property is enabled, otherwise <code>false</code>
	 */
	public boolean isPropertyEnabled(IsAnimationPropertyKey property) {
		// checks if property is consistent
		if (IsAnimationPropertyKey.isValid(property)) {
			// checks if a custom property, previously added) is enabled
			if (animationProperties.containsKey(property.value()) && ObjectType.OBJECT.equals(type(property))) {
				// returns that is enabled
				return true;
			}
			// if here, it could be the case
			// that a default property has been enabled or disabled
			// and the native object is not stored here but on defaults
			// then it checks only if is not in the disabled property
			return !animationDisabledProperties.contains(property.value());
		}
		// if here, the property is not valid
		// then returns false
		return false;
	}

	/**
	 * Returns <code>true</code> if an animation property instance is stored into the animation options.
	 * 
	 * @param property property instance used to check into animation options
	 * @return <code>true</code> if an animation property instance is stored into the animation options
	 */
	public boolean hasProperty(IsAnimationPropertyKey property) {
		// checks if property is consistent
		if (IsAnimationPropertyKey.isValid(property)) {
			// checks if is cached
			if (animationProperties.containsKey(property.value())) {
				// returns because it is in the cached
				return true;
			}
			// checks on the native object
			return ObjectType.OBJECT.equals(type(property));
		}
		// if here, the property is not valid
		// then returns false
		return false;
	}

	/**
	 * Returns an animation property instance if stored into the animation options.
	 * 
	 * @param property property instance used to get for animation options
	 * @return an animation property instance or <code>null</code> if does not exists
	 */
	@Override
	public AnimationProperty getProperty(IsAnimationPropertyKey property) {
		// checks if property is consistent
		if (IsAnimationPropertyKey.isValid(property)) {
			// checks if is cached
			if (animationProperties.containsKey(property.value())) {
				// returns because it is in the cached
				return animationProperties.get(property.value());
			}
			// gets from the native object
			return new AnimationProperty(this, property, getDefaultValues().getProperty(property), getValue(property));
		}
		// if here, the property is not valid
		// then returns null
		return null;
	}

	/**
	 * Removes an animation property previously added.
	 * 
	 * @param property property instance used to remove from animation options
	 */
	public void removeProperty(IsAnimationPropertyKey property) {
		// checks if property is consistent
		if (IsAnimationPropertyKey.isValid(property)) {
			// remove from object
			remove(property);
			// removes from cache
			animationProperties.remove(property.value());
		}
	}

	/**
	 * Sets an animation collection instance to animation options.
	 * 
	 * @param animationElement animation collection instance to add
	 */
	public void setCollection(AnimationCollection animationElement) {
		// checks if the sub element has been added
		if (setSubElement(animationElement)) {
			// manages the disabling of current collection
			manageCollection(animationElement);
			// stores the collection into cached
			animationCollections.put(animationElement.getKey().value(), animationElement);
			// checks if the node is already added to parent
			checkAndAddToParent();
		}
	}

	/**
	 * Manages the animation collection in order to set the current collection depending on collection type.
	 * 
	 * @param animationElement animation collection instance to check
	 */
	private void manageCollection(AnimationCollection animationElement) {
		// checks if equals to the current
		if (animationElement != null && IsAnimationCollectionKey.isValid(animationElement.getKey())) {
			// manages
			manageCollection(animationElement.getKey());
		}
	}

	/**
	 * Manages the animation collection in order to set the current collection depending on collection type.
	 * 
	 * @param collection collection instance used to check into animation options
	 */
	private void manageCollection(IsAnimationCollectionKey collection) {
		// gets the current collection set previously
		Key current = getCurrentCollectionKey(collection);
		// checks if equals to the current
		if (!Key.equals(collection, current)) {
			// disable the current one
			setValue(current, false);
			// stores the current
			setCurrentCollectionKey(collection);
		}
	}

	/**
	 * Enables or disables an animation collection instance into animation options.
	 * 
	 * @param collection collection instance used to check into animation options
	 * @param enabled if <code>true</code> it enables an animation collection
	 */
	public void setCollectionEnabled(IsAnimationCollectionKey collection, boolean enabled) {
		// checks if collection is consistent
		if (IsAnimationCollectionKey.isValid(collection)) {
			// checks if is enabling
			if (enabled) {
				// checks if cached
				if (animationCollections.containsKey(collection.value())) {
					// stores the object
					setValue(collection, animationCollections.get(collection.value()).nativeObject());
				} else if (ObjectType.BOOLEAN.equals(type(collection))) {
					// if here is not cached
					// then it must be removed in order to enable the default
					// the type of collection should be boolean because
					// set to false previously
					removeIfExists(collection);
				}
				// gets the current collection
				manageCollection(collection);
			} else {
				// sets the collection to false
				setValue(collection, false);
				// gets the current collection
				Key current = getCurrentCollectionKey(collection);
				// checks if equals to current defaults
				if (Key.equals(collection, current)) {
					// sets the default collection
					// because if ere it means that teh current
					// collection has been disabled
					IsAnimationCollectionKey defaultToSet = AnimationType.COLOR.equals(collection.type()) ? DefaultAnimationCollectionKey.COLORS : DefaultAnimationCollectionKey.NUMBERS;
					// resets the default
					setCurrentCollectionKey(defaultToSet);
					// checks if it has been changed from default
					if (animationCollections.containsKey(defaultToSet.value())) {
						// stores the changed object of defaults
						setValue(defaultToSet, animationCollections.get(defaultToSet.value()).nativeObject());
					} else {
						// removes the key
						// because the default is set
						// on default object
						removeIfExists(defaultToSet);
					}
				}
			}
		}
	}

	/**
	 * Returns <code>true</code> if the animation collection is enabled, otherwise <code>false</code>.
	 * 
	 * @param collection collection instance used to check into animation options
	 * @return <code>true</code> if the animation collection is enabled, otherwise <code>false</code>
	 */
	public boolean isCollectionEnabled(IsAnimationCollectionKey collection) {
		// checks if collection is consistent
		return IsAnimationCollectionKey.isValid(collection) && Key.equals(collection, getCurrentCollectionKey(collection));
	}

	/**
	 * Returns <code>true</code> if an animation collection instance is stored into the animation options.
	 * 
	 * @param collection collection instance used to check into animation options
	 * @return <code>true</code> if an animation collection instance is stored into the animation options
	 */
	public boolean hasCollection(IsAnimationCollectionKey collection) {
		// checks if collection is consistent
		if (IsAnimationCollectionKey.isValid(collection)) {
			// checks if is cached
			if (animationCollections.containsKey(collection.value())) {
				// returns because it is in the cached
				return true;
			}
			return ObjectType.OBJECT.equals(type(collection));
		}
		// if here, the collection is not valid
		// then returns false
		return false;

	}

	/**
	 * Returns an animation collection instance if stored into the animation options.
	 * 
	 * @param collection collection instance used to get for animation options
	 * @return an animation collection instance or <code>null</code> if does not exists
	 */
	@Override
	public AnimationCollection getCollection(IsAnimationCollectionKey collection) {
		// checks if collection is consistent
		if (IsAnimationCollectionKey.isValid(collection)) {
			// checks if is cached
			if (animationCollections.containsKey(collection.value())) {
				// returns because it is in the cached
				return animationCollections.get(collection.value());
			}
			// gets from the native object
			return new AnimationCollection(this, collection, getDefaultValues().getCollection(collection), getValue(collection));
		}
		// if here, the collection is not valid
		// then returns null
		return null;
	}

	/**
	 * Removes an animation collection previously added.
	 * 
	 * @param collection collection instance used to remove from animation options
	 */
	public void removeCollection(IsAnimationCollectionKey collection) {
		// checks if collection is consistent
		if (IsAnimationCollectionKey.isValid(collection)) {
			// gets the current collection
			Key current = getCurrentCollectionKey(collection);
			// checks if equals to current defaults
			if (!Key.equals(collection, current)) {
				// resets the default
				setCurrentCollectionKey(AnimationType.COLOR.equals(collection.type()) ? DefaultAnimationCollectionKey.COLORS : DefaultAnimationCollectionKey.NUMBERS);
			}
			// remove from object
			remove(collection);
			// removes from cache
			animationCollections.remove(collection.value());
		}
	}

	/**
	 * Returns the current animation collection, collection depending on collection type.
	 * 
	 * @param collection collection instance used to get for animation options
	 * @return the current animation collection
	 */
	private Key getCurrentCollectionKey(IsAnimationCollectionKey collection) {
		// checks if is a numbers or colors
		if (DefaultAnimationCollectionKey.COLORS.type().equals(collection.type())) {
			return currentColorsCollection;
		} else if (DefaultAnimationCollectionKey.NUMBERS.type().equals(collection.type())) {
			return currentNumbersCollection;
		}
		// if here, there is a boolean animation type
		// not supported yet
		throw new IllegalArgumentException("Unable to manage '" + collection.type() + "' animation type for collection '" + collection.value() + "'");
	}

	/**
	 * Sets the current animation collection, collection depending on collection type.
	 * 
	 * @param collection collection instance to set as current collection
	 */
	private void setCurrentCollectionKey(IsAnimationCollectionKey collection) {
		// sets the property to use to store current
		Key currentProperty = null;
		// checks if is a numbers or colors
		if (DefaultAnimationCollectionKey.COLORS.type().equals(collection.type())) {
			currentColorsCollection = collection;
			currentProperty = Property.CHARBA_CURRENT_COLORS_COLLECTION;
		} else if (DefaultAnimationCollectionKey.NUMBERS.type().equals(collection.type())) {
			currentColorsCollection = collection;
			currentProperty = Property.CHARBA_CURRENT_NUMBERS_COLLECTION;
		}
		// checks if current property is consistent
		if (Key.isValid(currentProperty)) {
			// stores values
			setValue(currentProperty, collection.value());
		} else {
			// if here, there is a boolean animation type
			// not supported yet
			throw new IllegalArgumentException("Unable to manage '" + collection.type() + "' animation type for collection '" + collection.value() + "'");
		}
	}

	/**
	 * Stores by a unique method the sub elements of the animation option.
	 * 
	 * @param animationElement the animation element to store
	 * @return <code>true</code> if the object has been added
	 */
	final boolean setSubElement(AbstractAnimation<?, ?> animationElement) {
		// checks if element is a sub element
		if (animationElement instanceof IsAnimationElement) {
			// cats to sub element
			IsAnimationElement subElement = (IsAnimationElement) animationElement;
			// stores the element
			setValue(subElement.getKey(), animationElement.nativeObject());
			// checks and returns if there is the value
			return has(subElement.getKey());
		}
		// if here, argument not consistent
		// then returns false;
		return false;
	}

}