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
package org.pepstock.charba.client.jsinterop.options;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.jsinterop.commons.AssignHelper;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultFontItem;

/**
 * Base object to map font options of globals.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class FontItem<P extends BaseModel<?,?,?>, D extends IsDefaultFontItem, O extends NativeFontItem> extends BaseModel<P, D, O>{
	
	FontItem(P parent, D defaultValues, O delegated) {
		super(parent, defaultValues, delegated);
	}
	
	/**
	 * Sets the font size.
	 * 
	 * @param fontSize the font size.
	 */
	public void setFontSize(int fontSize) {
		getDelegated().setFontSize(fontSize);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the font size.
	 * 
	 * @return the font size. Default is {@link org.pepstock.charba.client.defaults.global.Options#getDefaultFontSize()}.
	 */
	public int getFontSize() {
		return AssignHelper.check(getDelegated().getFontSize(), getDefaultValues().getFontSize());
	}

	/**
	 * Sets the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @param fontStyle Font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public void setFontStyle(FontStyle fontStyle) {
		getDelegated().setFontStyle(fontStyle.name());
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @return the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit). Default is
	 *         {@link org.pepstock.charba.client.defaults.global.Options#getDefaultFontStyle()}.
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public FontStyle getFontStyle() {
		return AssignHelper.deserialize(AssignHelper.check(getDelegated().getFontStyle(), getDefaultValues().getFontStyle()), FontStyle.class, FontStyle.normal);
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
		getDelegated().setFontColor(fontColor);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the font color
	 * 
	 * @return Font color. Default is {@link org.pepstock.charba.client.defaults.global.Options#getDefaultFontColor()}.
	 */
	public String getFontColorAsString() {
		return AssignHelper.check(getDelegated().getFontColor(), getDefaultValues().getFontColor());
	}

	/**
	 * Returns the font color
	 * 
	 * @return Font color. Default is {@link org.pepstock.charba.client.defaults.global.Options#getDefaultFontColor()}.
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
		getDelegated().setFontFamily(fontFamily);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the font family, follows CSS font-family options.
	 * 
	 * @return Font family, follows CSS font-family options. Default is
	 *         {@link org.pepstock.charba.client.defaults.global.Options#getDefaultFontFamily()}.
	 */
	public String getFontFamily() {
		return AssignHelper.check(getDelegated().getFontFamily(), getDefaultValues().getFontFamily());
	}

}