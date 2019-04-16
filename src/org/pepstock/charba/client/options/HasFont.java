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
import org.pepstock.charba.client.defaults.IsDefaultFontItem;
import org.pepstock.charba.client.enums.FontStyle;

/**
 * Interface to map font options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface HasFont extends IsDefaultFontItem {

	/**
	 * Returns a fonter instance to use into default methods of this interface.
	 * 
	 * @return a fonter instance
	 */
	Fonter getFonter();
	
	/**
	 * Sets the font size.
	 * 
	 * @param fontSize the font size.
	 */
	default void setFontSize(int fontSize) {
		getFonter().setFontSize(fontSize);
	}

	/**
	 * Returns the font size.
	 * 
	 * @return the font size.
	 */
	default int getFontSize() {
		return getFonter().getFontSize();
	}

	/**
	 * Sets the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @param fontStyle Font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 */
	default void setFontStyle(FontStyle fontStyle) {
		getFonter().setFontStyle(fontStyle);
	}

	/**
	 * Returns the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @return the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 */
	default FontStyle getFontStyle() {
		return getFonter().getFontStyle();
	}

	/**
	 * Sets the font color.
	 * 
	 * @param fontColor font color
	 */
	default void setFontColor(IsColor fontColor) {
		getFonter().setFontColor(fontColor);
	}

	/**
	 * Sets the font color as string.
	 * 
	 * @param fontColor font color as string.
	 */
	default void setFontColor(String fontColor) {
		getFonter().setFontColor(fontColor);
	}

	/**
	 * Returns the font color as string.
	 * 
	 * @return font color as string.
	 */
	default String getFontColorAsString() {
		return getFonter().getFontColorAsString();
	}

	/**
	 * Returns the font color.
	 * 
	 * @return font color.
	 */
	default IsColor getFontColor() {
		return ColorBuilder.parse(getFontColorAsString());
	}

	/**
	 * Sets the font family, follows CSS font-family options.
	 * 
	 * @param fontFamily Font family, follows CSS font-family options.
	 */
	default void setFontFamily(String fontFamily) {
		getFonter().setFontFamily(fontFamily);
	}

	/**
	 * Returns the font family, follows CSS font-family options.
	 * 
	 * @return Font family, follows CSS font-family options.
	 */
	default String getFontFamily() {
		return getFonter().getFontFamily();
	}

}