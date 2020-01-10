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
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.defaults.IsDefaultFontItem;
import org.pepstock.charba.client.enums.FontStyle;

/**
 * Base object to map font options for configuration.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Fonter extends NativeObjectContainer {

	// default font values
	private final IsDefaultFontItem defaultValues;
	// model which contains the fonter
	private final AbstractModel<?, ?> model;

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		FONT_SIZE("fontSize"),
		FONT_STYLE("fontStyle"),
		FONT_COLOR("fontColor"),
		FONT_FAMILY("fontFamily");

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
	 * Creates a fonter with the native object where FONTs properties must be managed and the default value to use when the
	 * property does not exist.
	 * 
	 * @param nativeObject native object where FONTs properties must be managed
	 * @param model model which contains the fonter.
	 * @param defaultValues default value of FONTs to use when the properties do not exist
	 */
	public Fonter(NativeObject nativeObject, AbstractModel<?, ?> model, IsDefaultFontItem defaultValues) {
		super(nativeObject);
		// checks if model is consistent
		if (model == null) {
			// if not, exception
			throw new IllegalArgumentException("Options model argument is null");
		}
		// checks if default value is consistent
		if (defaultValues == null) {
			// if not, exception
			throw new IllegalArgumentException("Default values argument is null");
		}
		this.model = model;
		this.defaultValues = defaultValues;
	}

	/**
	 * Sets the font size.
	 * 
	 * @param fontSize the font size.
	 */
	public void setFontSize(int fontSize) {
		setValue(Property.FONT_SIZE, fontSize);
		// checks if all parents are attached
		model.checkAndAddToParent();
	}

	/**
	 * Returns the font size.
	 * 
	 * @return the font size.
	 */
	public int getFontSize() {
		return getValue(Property.FONT_SIZE, defaultValues.getFontSize());
	}

	/**
	 * Sets the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @param fontStyle Font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 */
	public void setFontStyle(FontStyle fontStyle) {
		setValue(Property.FONT_STYLE, fontStyle);
		// checks if all parents are attached
		model.checkAndAddToParent();
	}

	/**
	 * Returns the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @return the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 */
	public FontStyle getFontStyle() {
		return getValue(Property.FONT_STYLE, FontStyle.class, defaultValues.getFontStyle());
	}

	/**
	 * Sets the font color
	 * 
	 * @param fontColor Font color
	 */
	public void setFontColor(IsColor fontColor) {
		setFontColor(checkValue(fontColor));
	}

	/**
	 * Sets the font color
	 * 
	 * @param fontColor Font color
	 */
	public void setFontColor(String fontColor) {
		setValue(Property.FONT_COLOR, fontColor);
		// checks if all parents are attached
		model.checkAndAddToParent();
	}

	/**
	 * Returns the font color
	 * 
	 * @return Font color.
	 */
	public String getFontColorAsString() {
		return getValue(Property.FONT_COLOR, defaultValues.getFontColorAsString());
	}

	/**
	 * Returns the font color
	 * 
	 * @return Font color.
	 */
	public IsColor getFontColor() {
		return ColorBuilder.parse(getFontColorAsString());
	}

	/**
	 * Sets the font family, follows CSS font-family options.
	 * 
	 * @param fontFamily Font family, follows CSS font-family options.
	 */
	public void setFontFamily(String fontFamily) {
		setValue(Property.FONT_FAMILY, fontFamily);
		// checks if all parents are attached
		model.checkAndAddToParent();
	}

	/**
	 * Returns the font family, follows CSS font-family options.
	 * 
	 * @return Font family, follows CSS font-family options.
	 */
	public String getFontFamily() {
		return getValue(Property.FONT_FAMILY, defaultValues.getFontFamily());
	}

}