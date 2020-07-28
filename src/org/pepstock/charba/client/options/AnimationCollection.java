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

import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultAnimationCollection;
import org.pepstock.charba.client.defaults.globals.DefaultsBuilder;
import org.pepstock.charba.client.enums.AnimationType;
import org.pepstock.charba.client.enums.DefaultAnimationPropertyKey;

/**
 * Defines the animation options for a collections for multiple properties, identified by properties list.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class AnimationCollection extends AbstractAnimationProperty<IsAnimationCollectionKey, IsDefaultAnimationCollection> implements IsDefaultAnimationCollection {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		PROPERTIES("properties");

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
	 * Creates an animation options to configure a specific collection.
	 * 
	 * @param collection collection instance used to get for animation options
	 */
	public AnimationCollection(IsAnimationCollectionKey collection) {
		this(collection, DefaultsBuilder.get().getOptions().getAnimation().getCollection(IsAnimationCollectionKey.checkAndGetIfValid(collection)));
	}

	/**
	 * Creates an animation options to configure a specific collection.
	 * 
	 * @param collection collection instance used to get for animation options
	 * @param defaultValues default provider
	 */
	public AnimationCollection(IsAnimationCollectionKey collection, IsDefaultAnimationCollection defaultValues) {
		this(null, collection, defaultValues, null);
		// stores the type
		super.setType(collection.type());
		// stores the properties
		setProperties(collection.properties());
	}

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param parent options parent node of the chart options.
	 * @param collection collection instance used to get for animation options
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	AnimationCollection(AbstractNode parent, IsAnimationCollectionKey collection, IsDefaultAnimationCollection defaultValues, NativeObject nativeObject) {
		super(parent, collection, defaultValues, nativeObject);
		// checks collection is valid
		IsAnimationCollectionKey.checkIfValid(collection);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.AbstractAnimationElement#setType(org.pepstock.charba.client.enums.AnimationType)
	 */
	@Override
	public void setType(AnimationType type) {
		// you can not override the property type
		// because included into collection object
	}

	/**
	 * Sets the properties to be defined into the animation collection.
	 * 
	 * @param properties the properties to be defined into the animation collection
	 */
	public void setProperties(IsAnimationPropertyKey... properties) {
		// checks if argument is consistent
		if (properties != null && properties.length > 0) {
			// loads the array from list
			ArrayString array = ArrayString.fromOrEmpty(properties);
			// stores the properties
			setArrayValue(Property.PROPERTIES, array);
		}
	}

	/**
	 * Sets the properties to be defined into the animation collection.
	 * 
	 * @param properties the properties to be defined into the animation collection
	 */
	public void setProperties(List<IsAnimationPropertyKey> properties) {
		// checks if argument is consistent
		if (properties != null && !properties.isEmpty()) {
			// loads the array from list
			ArrayString array = ArrayString.fromOrEmpty(properties.toArray(new IsAnimationPropertyKey[0]));
			// stores the properties
			setArrayValue(Property.PROPERTIES, array);
		}
	}

	/**
	 * Returns the properties defined into the animation collection.
	 * 
	 * @return the properties defined into the animation collection
	 */
	@Override
	public List<IsAnimationPropertyKey> getProperties() {
		// gets result list
		List<IsAnimationPropertyKey> result = new LinkedList<>();
		// gets array
		ArrayString array = getArrayValue(Property.PROPERTIES);
		// checks if array is consistent
		if (array.length() > 0) {
			// scans the array and the load the properties
			for (int i = 0; i < array.length(); i++) {
				// stores the property name
				String property = array.get(i);
				// checks if default on
				if (DefaultAnimationPropertyKey.is(property)) {
					// adds default property
					result.add(Key.getKeyByValue(DefaultAnimationPropertyKey.values(), property));
				} else {
					// adds new property
					result.add(IsAnimationPropertyKey.create(property, getType()));
				}
			}
		}
		return result;
	}

}