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

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.defaults.IsDefaultAnimationProperty;
import org.pepstock.charba.client.enums.AnimationType;

/**
 * Is the base animation options with common properties for animation properties (property and collections of properties).
 * 
 * @author Andrea "Stock" Stocchero
 * @param <T> type of key
 * @param <D> type of default values
 */
abstract class AbstractAnimationProperty<T extends Key, D extends IsDefaultAnimationProperty> extends AbstractAnimation<T, D> implements IsDefaultAnimationProperty, IsAnimationElement {

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
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
	AbstractAnimationProperty(AbstractNode parent, T childKey, D defaultValues, NativeObject nativeObject) {
		super(parent, childKey, defaultValues, nativeObject);
		// redefines hashcode in order do not have
		// the property $H for hashcode
		super.redefineHashcode();
	}

	/**
	 * Sets the type of <code>from</code> property and determines the interpolator used.
	 * 
	 * @param type the type of <code>from</code> property and determines the interpolator used.
	 */
	public void setType(AnimationType type) {
		setValue(Property.TYPE, type);
		// checks if the node is already added to parent
		checkAndAddToParent();
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
		setValue(Property.FROM, from);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Sets the start value for the animation as number.
	 * 
	 * @param from the start value for the animation as number.
	 */
	public void setFrom(double from) {
		setValue(Property.FROM, from);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Sets the start value for the animation as color string.
	 * 
	 * @param from the start value for the animation as color string.
	 */
	public void setFrom(String from) {
		setValue(Property.FROM, from);
		// checks if the node is already added to parent
		checkAndAddToParent();
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
		// gets type of property
		ObjectType propertyType = type(Property.FROM);
		// checks if the value is stored as the type
		if (ObjectType.NUMBER.equals(propertyType)) {
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
		// gets type of property
		ObjectType propertyType = type(Property.FROM);
		// checks if the value is stored as the type
		if (ObjectType.BOOLEAN.equals(propertyType)) {
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
		// gets type of property
		ObjectType propertyType = type(Property.FROM);
		// checks if the value is stored as the type
		if (ObjectType.STRING.equals(propertyType)) {
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
		setValue(Property.TO, to);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Sets the end value for the animation as number.
	 * 
	 * @param to the end value for the animation as number.
	 */
	public void setTo(double to) {
		setValue(Property.TO, to);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Sets the end value for the animation as color string.
	 * 
	 * @param to the end value for the animation as color string.
	 */
	public void setTo(String to) {
		setValue(Property.TO, to);
		// checks if the node is already added to parent
		checkAndAddToParent();
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
		// gets type of property
		ObjectType propertyType = type(Property.TO);
		// checks if the value is stored as the type
		if (ObjectType.NUMBER.equals(propertyType)) {
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
		// gets type of property
		ObjectType propertyType = type(Property.TO);
		// checks if the value is stored as the type
		if (ObjectType.BOOLEAN.equals(propertyType)) {
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
		// gets type of property
		ObjectType propertyType = type(Property.TO);
		// checks if the value is stored as the type
		if (ObjectType.STRING.equals(propertyType)) {
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
}