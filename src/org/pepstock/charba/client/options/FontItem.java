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
import org.pepstock.charba.client.defaults.IsDefaultFontItem;
import org.pepstock.charba.client.enums.FontStyle;

/**
 * Base object to map font options for configuration.
 * 
 * @author Andrea "Stock" Stocchero
 *
 * @param <P> parent node class
 * @param <D> defaults provider class
 */
public abstract class FontItem<P extends AbstractModel<?, ?>, D extends IsDefaultFontItem> extends AbstractModel<P, D> implements IsDefaultFontItem {

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
	 * Creates the object with the parent, the key of this element, default values and native object to map java script
	 * properties.
	 * 
	 * @param parent parent node to use to add this element where changed
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	FontItem(P parent, Key childKey, D defaultValues, NativeObject nativeObject) {
		super(parent, childKey, defaultValues, nativeObject);
	}

	/**
	 * Sets the font size.
	 * 
	 * @param fontSize the font size.
	 */
	public void setFontSize(int fontSize) {
		setValue(Property.FONT_SIZE, fontSize);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the font size.
	 * 
	 * @return the font size.
	 */
	public int getFontSize() {
		return getValue(Property.FONT_SIZE, getDefaultValues().getFontSize());
	}

	/**
	 * Sets the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @param fontStyle Font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 */
	public void setFontStyle(FontStyle fontStyle) {
		setValue(Property.FONT_STYLE, fontStyle);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @return the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 */
	public FontStyle getFontStyle() {
		return getValue(Property.FONT_STYLE, FontStyle.class, getDefaultValues().getFontStyle());
	}

	/**
	 * Sets the font color
	 * 
	 * @param fontColor Font color
	 */
	public void setFontColor(IsColor fontColor) {
		setFontColor(fontColor.toRGBA());
	}

	/**
	 * Sets the font color
	 * 
	 * @param fontColor Font color
	 */
	public void setFontColor(String fontColor) {
		setValue(Property.FONT_COLOR, fontColor);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the font color
	 * 
	 * @return Font color.
	 */
	public String getFontColorAsString() {
		return getValue(Property.FONT_COLOR, getDefaultValues().getFontColorAsString());
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
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the font family, follows CSS font-family options.
	 * 
	 * @return Font family, follows CSS font-family options.
	 */
	public String getFontFamily() {
		return getValue(Property.FONT_FAMILY, getDefaultValues().getFontFamily());
	}

}