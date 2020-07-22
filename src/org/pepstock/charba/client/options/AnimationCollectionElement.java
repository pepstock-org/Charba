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

import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultAnimationElement;
import org.pepstock.charba.client.defaults.globals.DefaultsBuilder;
import org.pepstock.charba.client.enums.AnimationType;
import org.pepstock.charba.client.enums.DefaultAnimationProperty;

/**
 * FIXME It animates charts out of the box. A number of options are provided to configure how the animation looks and how long it takes.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class AnimationCollectionElement extends AbstractAnimationElement<IsAnimationCollection> {

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
	public AnimationCollectionElement(IsAnimationCollection collection) {
		this(collection, DefaultsBuilder.get().getOptions().getAnimation().getCollection(IsAnimationCollection.checkAndGetIfValid(collection)));
	}

	/**
	 * Creates an animation options to configure a specific collection.
	 * 
	 * @param collection collection instance used to get for animation options
	 * @param defaultValues default provider
	 */
	public AnimationCollectionElement(IsAnimationCollection collection, IsDefaultAnimationElement defaultValues) {
		this(null, collection, defaultValues, null);

	}

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param parent options parent node of the chart options.
	 * @param collection collection instance used to get for animation options
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	AnimationCollectionElement(AbstractNode parent, IsAnimationCollection collection, IsDefaultAnimationElement defaultValues, NativeObject nativeObject) {
		super(parent, collection, defaultValues, nativeObject);
		// checks collection is valid
		IsAnimationCollection.checkIfValid(collection);
		// stores the type
		super.setType(collection.type());
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
	 * FIXME
	 * @return
	 */
	public void setProperties(IsAnimationProperty... properties) {
		// checks if argument is consistent
		if (properties != null && properties.length > 0) {
			// loads the array from list
			ArrayString array = ArrayString.fromOrEmpty(properties);
			// stores the properties
			setArrayValue(Property.PROPERTIES, array);
		}
	}

	/**
	 * FIXME
	 * @return
	 */
	public void setProperties(List<IsAnimationProperty> properties) {
		// checks if argument is consistent
		if (properties != null && !properties.isEmpty()) {
			// loads the array from list
			ArrayString array = ArrayString.fromOrEmpty(properties.toArray(new IsAnimationProperty[0]));
			// stores the properties
			setArrayValue(Property.PROPERTIES, array);
		}
	}

	/**
	 * FIXME
	 * @return
	 */
	public List<IsAnimationProperty> getProperties() {
		// gets result list
		List<IsAnimationProperty> result = new LinkedList<>();
		// gets array
		ArrayString array = getArrayValue(Property.PROPERTIES);
		// checks if array is consistent
		if (array.length() > 0) {
			// scans the array and the load the properties
			for (int i = 0; i < array.length(); i++) {
				// stores the property name
				String property = array.get(i);
				// checks if default on
				if (DefaultAnimationProperty.is(property)) {
					// adds default property
					result.add(Key.getKeyByValue(DefaultAnimationProperty.values(), property));
				} else {
					// adds new property
					result.add(IsAnimationProperty.create(property, getType()));
				}
			}
		}
		return result;
	}

}