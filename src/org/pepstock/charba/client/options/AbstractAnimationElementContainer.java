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
import java.util.Map;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.defaults.IsDefaultAnimationElementContainer;
import org.pepstock.charba.client.enums.DefaultAnimationCollection;

/**
 * FIXME It animates charts out of the box. A number of options are provided to configure how the animation looks and how long it takes.
 * 
 * @author Andrea "Stock" Stocchero
 * @param <T> type of key
 *
 */
abstract class AbstractAnimationElementContainer<T extends Key, D extends IsDefaultAnimationElementContainer> extends AbstractAnimation<D> implements IsDefaultAnimationElementContainer, IsAnimationSubElement{

	// key value
	private final T key;
	// map to store the starting values of animation collection
	// K = collection name, V = native object related to the collection name
	private final Map<String, NativeObject> defaultAnimationCollection = new HashMap<>();
	// map to store the  custom of animation collection
	// K = collection name, V = custom collection name
	private final Map<String, IsAnimationCollection> animationCollections = new HashMap<>();

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param parent options parent node of the chart options.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	AbstractAnimationElementContainer(AbstractNode parent, T childKey, D defaultValues, NativeObject nativeObject) {
		super(parent, childKey, defaultValues, nativeObject);
		// stores the
		this.key = Key.checkAndGetIfValid(childKey);
		// saves the current version of collection defaults
		// in order to recover them if needed
		for (DefaultAnimationCollection collection : DefaultAnimationCollection.values()) {
			// checks, get and stores the current value
			checkGetAndSave(collection, defaultAnimationCollection);
		}
	}

	/**
	 * Returns the key used for animation element.
	 * 
	 * @return the key used for animation element
	 */
	@Override
	public final T getKey() {
		return key;
	}
	
	/**
	 * Sets the animation options set for a specific property.
	 * 
	 * @param animationElement the animation options set for a specific property
	 */
	public void setProperty(AnimationPropertyElement animationElement) {
		setSubElement(animationElement);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the animation options set for a specific property.
	 * 
	 * @param property property instance used to get for animation options
	 * @return the animation options set for a specific property.
	 */
	@Override
	public AnimationPropertyElement getProperty(IsAnimationProperty property) {
		// checks if property is consistent
		if (IsAnimationProperty.isValid(property)) {
			return new AnimationPropertyElement(this, property, getDefaultValues().getProperty(property), getValue(property));
		}
		// if here, the property is not valid
		// then returns null
		return null;
	}

	/**
	 * Sets the animation options set for a specific collection.
	 * 
	 * @param animationElement the animation options set for a specific collection
	 */
	public void setCollection(AnimationCollectionElement animationElement) {
		// to enable a collection property
		// it should disable the defaults
		// then if checks if exists
		if (animationElement != null && IsAnimationCollection.isValid(animationElement.getKey()) && !DefaultAnimationCollection.is(animationElement.getKey())) {
			// gets the default collection by type of passed collection
			for (DefaultAnimationCollection collection : DefaultAnimationCollection.values()) {
				// checks if is a numbers of colors
				if (collection.type().equals(animationElement.getKey().type())) {
					// disable the defaults setting false
					setValue(collection, false);
					// manages if the previous must be 
					IsAnimationCollection previous  = animationCollections.put(collection.value(), animationElement.getKey());
					// checks if is not equals to the arguments one
					// removes because only 1 collection must be set
					if (previous != null && !Key.equals(animationElement.getKey(), previous)) {
						// removes previous collection
						remove(previous);
					}
				}
			}
		}
		// checks if 
		setSubElement(animationElement);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the animation options set for a specific collection.
	 * 
	 * @param collection collection instance used to get for animation options
	 * @return the animation options set for a specific collection.
	 */
	@Override
	public AnimationCollectionElement getCollection(IsAnimationCollection collection) {
		// checks if collection is consistent
		if (IsAnimationCollection.isValid(collection)) {
			return new AnimationCollectionElement(this, collection, getDefaultValues().getCollection(collection), getValue(collection));
		}
		// if here, the collection is not valid
		// then returns null
		return null;
	}
	
	/**
	 * Checks if a key is present into native object.<br>
	 * If <code>true</code>, gets the native object and store into the cache
	 * 
	 * @param property property to check
	 * @param cache map instance where store the ojects
	 */
	private void checkGetAndSave(Key property, Map<String, NativeObject> cache) {
		// checks if key and argument are consistent
		// and checks not already stored
		// and the property is present into object
		// and the property type is a object
		if (Key.isValid(property) && cache != null && !cache.containsKey(property.value()) && has(property) && ObjectType.OBJECT.equals(type(property))) {
			// gets the native object
			// and stores into map
			cache.put(property.value(), getValue(property));
		}
	}
	
	/**
	 * Stores by a unique method the sub elements of the animation option.
	 * 
	 * @param animationElement the animation element to store
	 */
	final void setSubElement(AbstractAnimation<?> animationElement) {
		// checks if element is a sub element
		if (animationElement instanceof IsAnimationSubElement) {
			// cats to sub element
			IsAnimationSubElement subElement = (IsAnimationSubElement) animationElement;
			// stores the element
			setValue(subElement.getKey(), animationElement.nativeObject());
		}
	}

}