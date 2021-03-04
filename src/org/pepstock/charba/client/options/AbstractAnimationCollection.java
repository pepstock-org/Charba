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

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.defaults.IsDefaultAnimationCollection;
import org.pepstock.charba.client.enums.AnimationType;
import org.pepstock.charba.client.enums.DefaultAnimationPropertyKey;

/**
 * Is the base animation options with common element properties for animation properties, ANIMATIONS name space.
 * 
 * @author Andrea "Stock" Stocchero
 * @param <T> type of key
 * @param <D> type of default values
 */
abstract class AbstractAnimationCollection<T extends Key, D extends IsDefaultAnimationCollection> extends AbstractAnimation<T, D> implements IsDefaultAnimationCollection {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		PROPERTIES("properties"),
		TYPE("type"),
		FROM("from"),
		TO("to");

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
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param parent parent of the animation options.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	AbstractAnimationCollection(AbstractNode parent, T childKey, D defaultValues, NativeObject nativeObject) {
		super(parent, childKey, defaultValues, nativeObject);
	}

	/**
	 * Sets the type of <code>from</code> property and determines the interpolator used.
	 * 
	 * @param type the type of <code>from</code> property and determines the interpolator used.
	 */
	public void setType(AnimationType type) {
		setValueAndAddToParent(Property.TYPE, type);
	}

	/**
	 * Returns the type of <code>from</code> property and determines the interpolator used.
	 * 
	 * @return the type of <code>from</code> property and determines the interpolator used.
	 */
	@Override
	public AnimationType getType() {
		return getValue(Property.TYPE, AnimationType.values(), getDefaultValues().getType());
	}

	/**
	 * Sets the end value for the animation as number.
	 * 
	 * @param from the start end for the animation as number.
	 */
	public void setFrom(boolean from) {
		setValueAndAddToParent(Property.FROM, from);
	}

	/**
	 * Sets the start value for the animation as number.
	 * 
	 * @param from the start value for the animation as number.
	 */
	public void setFrom(double from) {
		setValueAndAddToParent(Property.FROM, from);
	}

	/**
	 * Sets the start value for the animation as color string.
	 * 
	 * @param from the start value for the animation as color string.
	 */
	public void setFrom(String from) {
		setValueAndAddToParent(Property.FROM, from);
	}

	/**
	 * Sets the start value for the animation as color.
	 * 
	 * @param from the start value for the animation as color.
	 */
	public void setFrom(IsColor from) {
		setFrom(IsColor.checkAndGetValue(from));
	}

	/**
	 * Returns the start value for the animation as number.
	 * 
	 * @return the start value for the animation as number.
	 */
	@Override
	public double getFrom() {
		// checks if the value is stored as the type
		if (isType(Property.FROM, ObjectType.NUMBER)) {
			return getValue(Property.FROM, getDefaultValues().getFrom());
		}
		// if here, the type is not consistent
		// then returns the default value
		return getDefaultValues().getFrom();
	}

	/**
	 * Returns the start value for the animation as number.
	 * 
	 * @return the start value for the animation as number.
	 */
	@Override
	public boolean getFromAsBoolean() {
		// checks if the value is stored as the type
		if (isType(Property.FROM, ObjectType.BOOLEAN)) {
			return getValue(Property.FROM, getDefaultValues().getFromAsBoolean());
		}
		// if here, the type is not consistent
		// then returns the default value
		return getDefaultValues().getFromAsBoolean();
	}

	/**
	 * Returns the start value for the animation as color string.
	 * 
	 * @return the start value for the animation as color string.
	 */
	@Override
	public String getFromAsString() {
		// checks if the value is stored as the type
		if (isType(Property.FROM, ObjectType.STRING)) {
			return getValue(Property.FROM, getDefaultValues().getFromAsString());
		}
		// if here, the type is not consistent
		// then returns the default value
		return getDefaultValues().getFromAsString();
	}

	/**
	 * Returns the start value for the animation as color.
	 * 
	 * @return the start value for the animation as color.
	 */
	public IsColor getFromAsColor() {
		// gets value as string
		String fromAsString = getFromAsString();
		// checks if consistent
		if (fromAsString != null) {
			// creates and returns the color
			return ColorBuilder.parse(fromAsString);
		}
		// if here
		// no from as string
		// then returns null
		return null;
	}

	/**
	 * Sets the end value for the animation as number.
	 * 
	 * @param to the end value for the animation as number.
	 */
	public void setTo(boolean to) {
		setValueAndAddToParent(Property.TO, to);
	}

	/**
	 * Sets the end value for the animation as number.
	 * 
	 * @param to the end value for the animation as number.
	 */
	public void setTo(double to) {
		setValueAndAddToParent(Property.TO, to);
	}

	/**
	 * Sets the end value for the animation as color string.
	 * 
	 * @param to the end value for the animation as color string.
	 */
	public void setTo(String to) {
		setValueAndAddToParent(Property.TO, to);
	}

	/**
	 * Sets the end value for the animation as color.
	 * 
	 * @param to the end value for the animation as color.
	 */
	public void setTo(IsColor to) {
		setTo(IsColor.checkAndGetValue(to));
	}

	/**
	 * Returns the end value for the animation as number.
	 * 
	 * @return the end value for the animation as number.
	 */
	@Override
	public double getTo() {
		// checks if the value is stored as the type
		if (isType(Property.TO, ObjectType.NUMBER)) {
			return getValue(Property.TO, getDefaultValues().getTo());
		}
		// if here, the type is not consistent
		// then returns the default value
		return getDefaultValues().getTo();
	}

	/**
	 * Returns the end value for the animation as number.
	 * 
	 * @return the end value for the animation as number.
	 */
	@Override
	public boolean getToAsBoolean() {
		// checks if the value is stored as the type
		if (isType(Property.TO, ObjectType.BOOLEAN)) {
			return getValue(Property.TO, getDefaultValues().getToAsBoolean());
		}
		// if here, the type is not consistent
		// then returns the default value
		return getDefaultValues().getToAsBoolean();
	}

	/**
	 * Returns the end value for the animation as color string.
	 * 
	 * @return the end value for the animation as color string.
	 */
	@Override
	public String getToAsString() {
		// checks if the value is stored as the type
		if (isType(Property.TO, ObjectType.STRING)) {
			return getValue(Property.TO, getDefaultValues().getToAsString());
		}
		// if here, the type is not consistent
		// then returns the default value
		return getDefaultValues().getToAsString();
	}

	/**
	 * Returns the end value for the animation as color.
	 * 
	 * @return the end value for the animation as color.
	 */
	public IsColor getToAsColor() {
		// gets value as string
		String fromAsString = getToAsString();
		// checks if consistent
		if (fromAsString != null) {
			// creates and returns the color
			return ColorBuilder.parse(fromAsString);
		}
		// if here
		// no to as string
		// then returns null
		return null;
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
			setArrayValueAndAddToParent(Property.PROPERTIES, array);
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
			setArrayValueAndAddToParent(Property.PROPERTIES, array);
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