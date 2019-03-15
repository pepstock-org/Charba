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
import org.pepstock.charba.client.datalabels.enums.Weight;
import org.pepstock.charba.client.enums.FontStyle;

/**
 * Base object to map font options for DATALABELS plugin configuration.<br>
 * It can be used also into callback for font generation at runtime.
 * 
 * @author Andrea "Stock" Stocchero
 * @see FontCallback
 */
public final class Font extends AbstractElement {

	// defaults global options instance
	private DataLabelsDefaultsFont defaultsOptions;

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		size,
		style,
		family,
		weight,
		lineHeight
	}

	/**
	 * Creates new font element.
	 */
	public Font() {
		// uses the default values for font
		this(new DataLabelsDefaultsFont());
	}

	/**
	 * Creates new font element, using the default values options.
	 * 
	 * @param defaultsOptions default FONT options to returns the default when required.
	 */
	Font(DataLabelsDefaultsFont defaultsOptions) {
		this(null, defaultsOptions);
	}

	/**
	 * Creates new font element, using stored native object instance and the default values options.
	 * 
	 * @param nativeObject stored font values into native object to read.
	 * @param defaultsOptions default FONT options to returns the default when required.
	 */
	Font(NativeObject nativeObject, DataLabelsDefaultsFont defaultsOptions) {
		super(nativeObject);
		this.defaultsOptions = defaultsOptions;
	}

	/**
	 * Sets the font size.
	 * 
	 * @param fontSize the font size.
	 */
	public void setSize(int fontSize) {
		setValue(Property.size, fontSize);
	}

	/**
	 * Returns the font size.
	 * 
	 * @return the font size.
	 */
	public int getSize() {
		return getValue(Property.size, defaultsOptions.getFontSize());
	}

	/**
	 * Sets the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @param fontStyle Font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 */
	public void setStyle(FontStyle fontStyle) {
		setValue(Property.style, fontStyle);
	}

	/**
	 * Returns the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @return the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 */
	public FontStyle getStyle() {
		return getValue(Property.style, FontStyle.class, defaultsOptions.getFontStyle());
	}

	/**
	 * Sets the font family, follows CSS font-family options.
	 * 
	 * @param fontFamily Font family, follows CSS font-family options.
	 */
	public void setFamily(String fontFamily) {
		setValue(Property.family, fontFamily);
	}

	/**
	 * Returns the font family, follows CSS font-family options.
	 * 
	 * @return Font family, follows CSS font-family options.
	 */
	public String getFamily() {
		return getValue(Property.family, defaultsOptions.getFontFamily());
	}

	/**
	 * Sets the font weight, follows CSS font-style-weight options.
	 * 
	 * @param weight font weight, follows CSS font-style-weight options.
	 */
	public void setWeight(Weight weight) {
		setValue(Property.weight, weight);
	}

	/**
	 * Returns the font weight, follows CSS font-style-weight options.
	 * 
	 * @return the font weight, follows CSS font-style-weight options.
	 */
	public Weight getWeight() {
		return getValue(Property.weight, Weight.class, defaultsOptions.getWeight());
	}

	/**
	 * Sets the line height.
	 * 
	 * @param lineHeight the line height.
	 */
	public void setLineHeight(double lineHeight) {
		setValue(Property.lineHeight, lineHeight);
	}

	/**
	 * Sets the line height.
	 * 
	 * @param lineHeight the line height.
	 */
	public void setLineHeight(String lineHeight) {
		setValue(Property.lineHeight, lineHeight);
	}
	
	/**
	 * Returns the height of an individual line of text.
	 * 
	 * @return the height of an individual line of text.
	 */
	public double getLineHeight() {
		// creates default
		double defaultValue = defaultsOptions.getLineHeight();
		// checks type if number
		if (ObjectType.Number.equals(type(Property.lineHeight))) {
			// reads and returns as double
			return getValue(Property.lineHeight, defaultValue);
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
		if (ObjectType.String.equals(type(Property.lineHeight))) {
			// reads and returns as string
			return getValue(Property.lineHeight, defaultValue);
		}
		// if here, is not a number
		// then returns the default
		return defaultValue;
	}

}
