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
package org.pepstock.charba.client.ext.datalabels;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.enums.FontStyle;

/**
 * Base object to map font options for configuration.
 * 
 * @author Andrea "Stock" Stocchero
 *
 * @param <P> parent node class
 * @param <D> defaults provider class
 */
public final class Font extends AbstractObjectCallback {

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
	
	public Font() {
		this(new DataLabelsDefaultsFont());
	}

	Font(DataLabelsDefaultsFont defaultsOptions) {
		this(null, defaultsOptions);
	}

	Font(NativeObject nativeObject, DataLabelsDefaultsFont defaultsOptions) {
		super(nativeObject);
		this.defaultsOptions = defaultsOptions;
	}

	/**
	 * Sets the font size.
	 * 
	 * @param fontSize the font size.
	 */
	public void setFontSize(int fontSize) {
		setValue(Property.size, defaultsOptions.getFontSize());
	}

	/**
	 * Returns the font size.
	 * 
	 * @return the font size.
	 */
	public int getFontSize() {
		return getValue(Property.size, defaultsOptions.getFontSize());
	}

	/**
	 * Sets the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @param fontStyle Font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 */
	public void setFontStyle(FontStyle fontStyle) {
		setValue(Property.style, fontStyle);
	}

	/**
	 * Returns the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @return the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 */
	public FontStyle getFontStyle() {
		return getValue(Property.style, FontStyle.class, defaultsOptions.getFontStyle());
	}

	/**
	 * Sets the font family, follows CSS font-family options.
	 * 
	 * @param fontFamily Font family, follows CSS font-family options.
	 */
	public void setFontFamily(String fontFamily) {
		setValue(Property.family, fontFamily);
	}

	/**
	 * Returns the font family, follows CSS font-family options.
	 * 
	 * @return Font family, follows CSS font-family options.
	 */
	public String getFontFamily() {
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
	 * Returns the line height.
	 * 
	 * @return the line height.
	 */
	public double getLineHeight() {
		return getValue(Property.lineHeight, defaultsOptions.getLineHeight());
	}

}
