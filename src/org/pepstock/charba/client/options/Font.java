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
import org.pepstock.charba.client.defaults.IsDefaultFont;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.enums.Weight;

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
		COLOR("color"),
		FAMILY("family"),
		SIZE("size"),
		STYLE("style"),
		WEIGHT("weight"),
		LINE_HEIGHT("lineHeight"),
		LINE_WIDTH("lineWidth"),
		STROKE_STYLE("strokeStyle");

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
	 * @param parent parent of the font.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	Font(AbstractNode parent, Key childKey, IsDefaultFont defaultValues, NativeObject nativeObject) {
		super(parent, childKey, nativeObject);
		// checks if default value is consistent
		if (defaultValues == null) {
			// if not, exception
			throw new IllegalArgumentException("Default values argument is null");
		}
		// stores defaults values
		this.defaultValues = defaultValues;
	}

	/**
	 * Sets the font color.
	 * 
	 * @param color font color.
	 */
	@Override
	public void setColor(IsColor color) {
		setColor(IsColor.checkAndGetValue(color));
	}

	/**
	 * Sets the font color.
	 * 
	 * @param color font color.
	 */
	@Override
	public void setColor(String color) {
		setValue(Property.COLOR, color);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the font color as string.
	 * 
	 * @return font color as string
	 */
	@Override
	public String getColorAsString() {
		return getValue(Property.COLOR, defaultValues.getColorAsString());
	}

	/**
	 * Returns the font color.
	 * 
	 * @return font color
	 */
	@Override
	public IsColor getColor() {
		return ColorBuilder.parse(getColorAsString());
	}

	/**
	 * Sets the font size.
	 * 
	 * @param size the font size.
	 */
	@Override
	public void setSize(int size) {
		setValue(Property.SIZE, size);
		// checks if the node is already added to parent
		checkAndAddToParent();
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
		setValue(Property.STYLE, style);
		// checks if the node is already added to parent
		checkAndAddToParent();
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
		setValue(Property.FAMILY, family);
		// checks if the node is already added to parent
		checkAndAddToParent();
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
		setValue(Property.WEIGHT, weight);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the font weight, follows CSS font-style-weight options.
	 * 
	 * @return the font weight, follows CSS font-style-weight options.
	 */
	@Override
	public Weight getWeight() {
		return getValue(Property.WEIGHT, Weight.values(), defaultValues.getWeight());
	}

	/**
	 * Sets the line height.
	 * 
	 * @param lineHeight the line height.
	 */
	@Override
	public void setLineHeight(double lineHeight) {
		setValue(Property.LINE_HEIGHT, lineHeight);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Sets the line height.
	 * 
	 * @param lineHeight the line height.
	 */
	@Override
	public void setLineHeight(String lineHeight) {
		setValue(Property.LINE_HEIGHT, lineHeight);
		// checks if the node is already added to parent
		checkAndAddToParent();
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
		if (ObjectType.NUMBER.equals(type(Property.LINE_HEIGHT))) {
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
		if (ObjectType.STRING.equals(type(Property.LINE_HEIGHT))) {
			// reads and returns as string
			return getValue(Property.LINE_HEIGHT, defaultValue);
		}
		// if here, is not a number
		// then returns the default
		return defaultValue;
	}

	/**
	 * Sets the stroke width around the text.<br>
	 * Currently only supported by ticks.
	 * 
	 * @param lineWidth the stroke width around the text
	 */
	@Override
	public void setLineWidth(int lineWidth) {
		setValue(Property.LINE_WIDTH, lineWidth);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the stroke width around the text.<br>
	 * Currently only supported by ticks.
	 * 
	 * @return the stroke width around the text
	 */
	@Override
	public int getLineWidth() {
		return getValue(Property.LINE_WIDTH, defaultValues.getLineWidth());
	}

	/**
	 * Sets the color of the stroke around the text.<br>
	 * Currently only supported by ticks.
	 * 
	 * @param strokeStyle the color of the stroke around the text
	 */
	@Override
	public void setStrokeStyle(IsColor strokeStyle) {
		setColor(IsColor.checkAndGetValue(strokeStyle));
	}

	/**
	 * Sets the color of the stroke around the text.<br>
	 * Currently only supported by ticks.
	 * 
	 * @param strokeStyle the color of the stroke around the text
	 */
	@Override
	public void setStrokeStyle(String strokeStyle) {
		setValue(Property.STROKE_STYLE, strokeStyle);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the color of the stroke around the text.<br>
	 * Currently only supported by ticks.
	 * 
	 * @return the color of the stroke around the text
	 */
	@Override
	public String getStrokeStyleAsString() {
		return getValue(Property.STROKE_STYLE, defaultValues.getStrokeStyleAsString());
	}

	/**
	 * Returns the color of the stroke around the text.<br>
	 * Currently only supported by ticks.
	 * 
	 * @return the color of the stroke around the text
	 */
	@Override
	public IsColor getStrokeStyle() {
		return ColorBuilder.parse(getStrokeStyleAsString());
	}

}
