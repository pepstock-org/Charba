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

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.commons.ChartContainer;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.FontStyle;

/**
 * Abstract and common representation of a label inside the chart options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class AbstractLabel extends ChartContainer {

	private static final int DEFAULT_FONT_SIZE = 12;

	private static final String DEFAULT_FONT_COLOR = "#666";

	private static final String DEFAULT_FONT_FAMILY = "'Helvetica Neue', 'Helvetica', 'Arial', sans-serif";

	private static final int DEFAULT_PADDING = 10;

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		fontSize,
		fontStyle,
		fontColor,
		fontFamily,
		padding
	}

	/**
	 * Builds the object storing the chart instance.
	 * 
	 * @param chart chart instance
	 */
	protected AbstractLabel(AbstractChart<?, ?> chart) {
		super(chart);
	}

	/**
	 * Sets the font size for label.
	 * 
	 * @param fontSize Font size for label.
	 */
	public void setFontSize(int fontSize) {
		setValue(Property.fontSize, fontSize);
	}

	/**
	 * Returns the font size for label.
	 * 
	 * @return Font size for label.. Default is 12.
	 */
	public int getFontSize() {
		return getValue(Property.fontSize, DEFAULT_FONT_SIZE);
	}

	/**
	 * Sets the font style for the label, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @param fontStyle Font style for the label, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public void setFontStyle(FontStyle fontStyle) {
		setValue(Property.fontStyle, fontStyle);
	}

	/**
	 * Returns the font style for the label, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @return the font style for the label, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit). Default is normal
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public FontStyle getFontStyle() {
		return getValue(Property.fontStyle, FontStyle.class, FontStyle.normal);
	}

	/**
	 * Sets the font color for label
	 * 
	 * @param fontColor Font color for label
	 */
	public void setFontColor(String fontColor) {
		setValue(Property.fontColor, fontColor);
	}

	/**
	 * Returns the font color for label
	 * 
	 * @return Font color for label. Default is '#666'
	 */
	public String getFontColor() {
		return getValue(Property.fontColor, DEFAULT_FONT_COLOR);
	}

	/**
	 * Sets the font family for the label, follows CSS font-family options.
	 * 
	 * @param fontFamily Font family for the label, follows CSS font-family options.
	 */
	public void setFontFamily(String fontFamily) {
		setValue(Property.fontFamily, fontFamily);
	}

	/**
	 * Returns the font family for the label, follows CSS font-family options.
	 * 
	 * @return Font family for the label, follows CSS font-family options. Default is 'Helvetica Neue', 'Helvetica', 'Arial', sans-serif
	 */
	public String getFontFamily() {
		return getValue(Property.fontFamily, DEFAULT_FONT_FAMILY);
	}

	/**
	 * Sets the padding to apply around labels. Only top and bottom are implemented.
	 * 
	 * @param padding Padding to apply around labels. Only top and bottom are implemented.
	 */
	public void setPadding(int padding) {
		setValue(Property.padding, padding);
	}

	/**
	 * Returns the padding to apply around labels. Only top and bottom are implemented.
	 * 
	 * @return Padding to apply around labels. Only top and bottom are implemented. Default is 10.
	 */
	public int getPadding() {
		return getValue(Property.padding, DEFAULT_PADDING);
	}

}