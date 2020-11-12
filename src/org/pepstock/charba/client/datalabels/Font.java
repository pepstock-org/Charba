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
package org.pepstock.charba.client.datalabels;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.datalabels.callbacks.FontCallback;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.enums.Weight;

/**
 * Base object to map font options for {@link DataLabelsPlugin#ID} plugin configuration.<br>
 * It can be used also into callback for font generation at runtime.
 * 
 * @author Andrea "Stock" Stocchero
 * @see FontCallback
 */
public final class Font extends AbstractElement implements IsDefaultsFont {

	// defaults global options instance
	private IsDefaultsFont defaultsOptions;

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		SIZE("size"),
		STYLE("style"),
		FAMILY("family"),
		WEIGHT("weight"),
		LINE_HEIGHT("lineHeight");

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
	 * Creates new font element, using the default values options.
	 */
	public Font() {
		this(DefaultsOptions.DEFAULTS_INSTANCE.getFont(), null);
	}

	/**
	 * Creates new font element, using stored native object instance and the default values options.
	 * 
	 * @param nativeObject stored font values into native object to read.
	 * @param defaultsOptions default FONT options to returns the default when required.
	 */
	Font(IsDefaultsFont defaultsOptions, NativeObject nativeObject) {
		super(nativeObject);
		// checks if default value is consistent
		// stores default
		this.defaultsOptions = checkDefaultValuesArgument(defaultsOptions);
	}

	/**
	 * Sets the font size.
	 * 
	 * @param fontSize the font size.
	 */
	public void setSize(int fontSize) {
		setValue(Property.SIZE, fontSize);
	}

	/**
	 * Returns the font size.
	 * 
	 * @return the font size.
	 */
	@Override
	public int getSize() {
		return getValue(Property.SIZE, defaultsOptions.getSize());
	}

	/**
	 * Sets the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @param fontStyle Font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 */
	public void setStyle(FontStyle fontStyle) {
		setValue(Property.STYLE, fontStyle);
	}

	/**
	 * Returns the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @return the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 */
	@Override
	public FontStyle getStyle() {
		return getValue(Property.STYLE, FontStyle.values(), defaultsOptions.getStyle());
	}

	/**
	 * Sets the font family, follows CSS font-family options.
	 * 
	 * @param fontFamily Font family, follows CSS font-family options.
	 */
	public void setFamily(String fontFamily) {
		setValue(Property.FAMILY, fontFamily);
	}

	/**
	 * Returns the font family, follows CSS font-family options.
	 * 
	 * @return Font family, follows CSS font-family options.
	 */
	@Override
	public String getFamily() {
		return getValue(Property.FAMILY, defaultsOptions.getFamily());
	}

	/**
	 * Sets the font weight, follows CSS font-style-weight options.
	 * 
	 * @param weight font weight, follows CSS font-style-weight options.
	 */
	public void setWeight(Weight weight) {
		setValue(Property.WEIGHT, weight);
	}

	/**
	 * Returns the font weight, follows CSS font-style-weight options.
	 * 
	 * @return the font weight, follows CSS font-style-weight options.
	 */
	@Override
	public Weight getWeight() {
		return getValue(Property.WEIGHT, Weight.values(), defaultsOptions.getWeight());
	}

	/**
	 * Sets the line height.
	 * 
	 * @param lineHeight the line height.
	 */
	public void setLineHeight(double lineHeight) {
		setValue(Property.LINE_HEIGHT, lineHeight);
	}

	/**
	 * Sets the line height.
	 * 
	 * @param lineHeight the line height.
	 */
	public void setLineHeight(String lineHeight) {
		setValue(Property.LINE_HEIGHT, lineHeight);
	}

	/**
	 * Returns the height of an individual line of text.
	 * 
	 * @return the height of an individual line of text.
	 */
	@Override
	public double getLineHeight() {
		// creates default
		double defaultValue = defaultsOptions.getLineHeight();
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
	public String getLineHeightAsString() {
		// creates default
		String defaultValue = String.valueOf(defaultsOptions.getLineHeight());
		// checks type if string
		if (isType(Property.LINE_HEIGHT, ObjectType.STRING)) {
			// reads and returns as string
			return getValue(Property.LINE_HEIGHT, defaultValue);
		}
		// if here, is not a number
		// then returns the default
		return defaultValue;
	}

}
