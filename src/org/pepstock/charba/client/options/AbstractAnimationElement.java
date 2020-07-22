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
import org.pepstock.charba.client.defaults.IsDefaultAnimationElement;
import org.pepstock.charba.client.enums.AnimationType;

/**
 * FIXME It animates charts out of the box. A number of options are provided to configure how the animation looks and how long it takes.
 * 
 * @author Andrea "Stock" Stocchero
 * @param <T> type of key
 *
 */
abstract class AbstractAnimationElement<T extends Key> extends AbstractAnimation implements IsDefaultAnimationElement {

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		TYPE("type"),
		FROM("from");

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

	// default values
	private final IsDefaultAnimationElement defaultValues;
	// key value
	private final T key;

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param parent options parent node of the chart options.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	AbstractAnimationElement(AbstractNode parent, T childKey, IsDefaultAnimationElement defaultValues, NativeObject nativeObject) {
		super(parent, childKey, defaultValues, nativeObject);
		// stores the key
		this.key = childKey;
		// stores defaults which has been already checked on super class
		this.defaultValues = defaultValues;
	}

	/**
	 * Returns the key used for animation element.
	 * 
	 * @return the key used for animation element
	 */
	public final T getKey() {
		return key;
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
		return getValue(Property.TYPE, AnimationType.values(), defaultValues.getType());
	}

	/**
	 * Sets the start value for the animation as number.
	 * 
	 * @param from the start value for the animation as number.
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
			return getValue(Property.FROM, defaultValues.getFrom());
		}
		// if here, the type is not consistent
		// then returns the default value
		return defaultValues.getFrom();
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
			return getValue(Property.FROM, defaultValues.getFromAsBoolean());
		}
		// if here, the type is not consistent
		// then returns the default value
		return defaultValues.getFromAsBoolean();
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
			return getValue(Property.FROM, defaultValues.getFromAsString());
		}
		// if here, the type is not consistent
		// then returns the default value
		return defaultValues.getFromAsString();
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
	 * Returns the native object instance.
	 * 
	 * @return the native object instance.
	 */
	final NativeObject nativeObject() {
		return super.getNativeObject();
	}

}