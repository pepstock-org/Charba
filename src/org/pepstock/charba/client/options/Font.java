/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.defaults.IsDefaultFont;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.enums.Weight;
import org.pepstock.charba.client.items.FontItem;
import org.pepstock.charba.client.items.Undefined;

/**
 * Base object to map font options for configuration.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class Font extends AbstractNode implements IsFont {

	// default font values
	private final IsDefaultFont defaultValues;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		FAMILY("family"),
		SIZE("size"),
		STYLE("style"),
		WEIGHT("weight"),
		LINE_HEIGHT("lineHeight");

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

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param parent parent of the font.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	Font(AbstractNode parent, Key childKey, IsDefaultFont defaultValues, NativeObject nativeObject) {
		super(parent, childKey, nativeObject);
		// checks if default value is consistent
		// stores defaults values
		this.defaultValues = checkDefaultValuesArgument(defaultValues);
	}

	/**
	 * Sets the font size.
	 * 
	 * @param size the font size.
	 */
	@Override
	public void setSize(int size) {
		setValueAndAddToParent(Property.SIZE, Checker.positiveOrZero(size));
	}

	/**
	 * Returns the font size.
	 * 
	 * @return the font size.
	 */
	@Override
	public int getSize() {
		return getValue(Property.SIZE, defaultValues.getSize());
	}

	/**
	 * Sets the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @param style Font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 */
	@Override
	public void setStyle(FontStyle style) {
		setValueAndAddToParent(Property.STYLE, style);
	}

	/**
	 * Returns the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @return the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 */
	@Override
	public FontStyle getStyle() {
		return getValue(Property.STYLE, FontStyle.values(), defaultValues.getStyle());
	}

	/**
	 * Sets the font family, follows CSS font-family options.
	 * 
	 * @param family Font family, follows CSS font-family options.
	 */
	@Override
	public void setFamily(String family) {
		setValueAndAddToParent(Property.FAMILY, family);
	}

	/**
	 * Returns the font family, follows CSS font-family options.
	 * 
	 * @return Font family, follows CSS font-family options.
	 */
	@Override
	public String getFamily() {
		return getValue(Property.FAMILY, defaultValues.getFamily());
	}

	/**
	 * Sets the font weight, follows CSS font-style-weight options.
	 * 
	 * @param weight font weight, follows CSS font-style-weight options.
	 */
	@Override
	public void setWeight(Weight weight) {
		// checks if consistent
		if (Key.isValid(weight)) {
			// checks if a number must be stored
			if (weight.isValueAsInt()) {
				setValueAndAddToParent(Property.WEIGHT, weight.getValueAsInt());
			} else {
				setValueAndAddToParent(Property.WEIGHT, weight);
			}
		} else {
			// if here the argument is not consistent
			remove(Property.WEIGHT);
		}
	}

	/**
	 * Returns the font weight, follows CSS font-style-weight options.
	 * 
	 * @return the font weight, follows CSS font-style-weight options.
	 */
	@Override
	public Weight getWeight() {
		// checks if the value stored is a number
		if (isType(Property.WEIGHT, ObjectType.NUMBER)) {
			// extracts by number
			return Weight.getByIntValue(getValue(Property.WEIGHT, Undefined.INTEGER), defaultValues.getWeight());
		}
		// if here, weight is defined as string or
		// undefined
		return getValue(Property.WEIGHT, Weight.values(), defaultValues.getWeight());
	}

	/**
	 * Sets the line height.
	 * 
	 * @param lineHeight the line height.
	 */
	@Override
	public void setLineHeight(double lineHeight) {
		setValueAndAddToParent(Property.LINE_HEIGHT, Checker.positiveOrZero(lineHeight));
	}

	/**
	 * Sets the line height.
	 * 
	 * @param lineHeight the line height.
	 */
	@Override
	public void setLineHeight(String lineHeight) {
		setValueAndAddToParent(Property.LINE_HEIGHT, lineHeight);
	}

	/**
	 * Returns the height of an individual line of text.
	 * 
	 * @return the height of an individual line of text.
	 */
	@Override
	public double getLineHeight() {
		// creates default
		double defaultValue = defaultValues.getLineHeight();
		// checks type if number
		if (isType(Property.LINE_HEIGHT, ObjectType.NUMBER)) {
			// reads and returns as double
			return getValue(Property.LINE_HEIGHT, defaultValue);
		}
		// if here, is not a number
		// then returns the default
		return defaultValue;
	}

	/**
	 * Returns the height of an individual line of text.
	 * 
	 * @return the height of an individual line of text.
	 */
	@Override
	public String getLineHeightAsString() {
		// creates default
		String defaultValue = String.valueOf(defaultValues.getLineHeight());
		// checks type if string
		if (isType(Property.LINE_HEIGHT, ObjectType.STRING)) {
			// reads and returns as string
			return getValue(Property.LINE_HEIGHT, defaultValue);
		}
		// if here, is not a number
		// then returns the default
		return defaultValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultFont#create()
	 */
	@Override
	public FontItem create() {
		return IsFont.super.create(this.defaultValues);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultFont#create(org.pepstock.charba.client.defaults.IsDefaultFont)
	 */
	@Override
	public FontItem create(IsDefaultFont defaultValues) {
		return create();
	}

	/**
	 * Returns the native object instance.
	 * 
	 * @return the native object instance.
	 */
	NativeObject nativeObject() {
		return getNativeObject();
	}

}