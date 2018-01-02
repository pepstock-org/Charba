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
package org.pepstock.charba.client.options.scales;

import org.pepstock.charba.client.commons.JavaScriptObjectContainer;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.FontStyle;

/**
 * When creating a chart, you want to tell the viewer what data they are
 * viewing. To do this, you need to label the axis.<br>
 * The scale label configuration defines options for the scale title. Note that
 * this only applies to cartesian axes.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class CartesianScaleLabel extends JavaScriptObjectContainer {

	private static final int DEFAULT_FONT_SIZE = 12;

	private static final String DEFAULT_FONT_COLOR = "#666";

	private static final String DEFAULT_FONT_FAMILY = "'Helvetica Neue', 'Helvetica', 'Arial', sans-serif";

	private static final boolean DEFAULT_DISPLAY = false;

	private static final String DEFAULT_LABEL_STRING = "";

	private static final String DEFAULT_LINE_HEIGHT = "1.2";

	private static final int DEFAULT_PADDING = 4;

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		display,
		labelString,
		lineHeight,
		padding,
		fontSize,
		fontStyle,
		fontColor,
		fontFamily,
	}

	/**
	 * Empty constructor to reduce visibility
	 */
	CartesianScaleLabel() {
	}

	/**
	 * f true, display the axis title.
	 * 
	 * @param display
	 *            f true, display the axis title.
	 */
	public void setDisplay(boolean display) {
		setValue(Property.display, display);
	}

	/**
	 * f true, display the axis title.
	 * 
	 * @return f true, display the axis title. Default is false
	 */
	public boolean isDisplay() {
		return getValue(Property.display, DEFAULT_DISPLAY);
	}

	/**
	 * Sets the text for the title.
	 * 
	 * @param labelString
	 *            The text for the title.
	 */
	public void setLabelString(String labelString) {
		setValue(Property.labelString, labelString);
	}

	/**
	 * Returns the text for the title.
	 * 
	 * @return The text for the title.
	 */
	public String getLabelString() {
		return getValue(Property.labelString, DEFAULT_LABEL_STRING);
	}

	/**
	 * Sets the height of an individual line of text.
	 * 
	 * @param lineHeight
	 *            Height of an individual line of text.
	 */
	public void setLineHeight(String lineHeight) {
		setValue(Property.lineHeight, lineHeight);
	}

	/**
	 * Returns the height of an individual line of text.
	 * 
	 * @return the height of an individual line of text. Default is 1.2
	 */
	public String getLineHeight() {
		return getValue(Property.lineHeight, DEFAULT_LINE_HEIGHT);
	}

	/**
	 * Sets the padding to apply around scale labels. Only top and bottom are
	 * implemented.
	 * 
	 * @param padding
	 *            Padding to apply around scale labels. Only top and bottom are
	 *            implemented.
	 */
	public void setPadding(int padding) {
		setValue(Property.padding, padding);
	}

	/**
	 * Returns the padding to apply around scale labels. Only top and bottom are
	 * implemented.
	 * 
	 * @return Padding to apply around scale labels. Only top and bottom are
	 *         implemented. Default is 4.
	 */
	public int getPadding() {
		return getValue(Property.padding, DEFAULT_PADDING);
	}

	/**
	 * Sets the font size for scale title.
	 * 
	 * @param fontSize
	 *            Font size for scale title.
	 */
	public void setFontSize(int fontSize) {
		setValue(Property.fontSize, fontSize);
	}

	/**
	 * Returns the font size for scale title.
	 * 
	 * @return Font size for scale title.. Default is 12.
	 */
	public int getFontSize() {
		return getValue(Property.fontSize, DEFAULT_FONT_SIZE);
	}

	/**
	 * Sets the font style for the scale title, follows CSS font-style options
	 * (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @param fontStyle
	 *            Font style for the scale title, follows CSS font-style options
	 *            (i.e. normal, italic, oblique, initial, inherit).
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public void setFontStyle(FontStyle fontStyle) {
		setValue(Property.fontStyle, fontStyle);
	}

	/**
	 * Returns the font style for the scale title, follows CSS font-style
	 * options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @return the font style for the scale title, follows CSS font-style
	 *         options (i.e. normal, italic, oblique, initial, inherit). Default
	 *         is normal
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public FontStyle getFontStyle() {
		return getValue(Property.fontStyle, FontStyle.class, FontStyle.normal);
	}

	/**
	 * Sets the font color for scale title
	 * 
	 * @param fontColor
	 *            Font color for scale title
	 */
	public void setFontColor(String fontColor) {
		setValue(Property.fontColor, fontColor);
	}

	/**
	 * Returns the font color for scale title
	 * 
	 * @return Font color for scale title. Default is '#666'
	 */
	public String getFontColor() {
		return getValue(Property.fontColor, DEFAULT_FONT_COLOR);
	}

	/**
	 * Sets the font family for the scale title, follows CSS font-family
	 * options.
	 * 
	 * @param fontFamily
	 *            Font family for the scale title, follows CSS font-family
	 *            options.
	 */
	public void setFontFamily(String fontFamily) {
		setValue(Property.fontFamily, fontFamily);
	}

	/**
	 * Returns the font family for the scale title, follows CSS font-family
	 * options.
	 * 
	 * @return Font family for the scale title, follows CSS font-family options.
	 *         Default is 'Helvetica Neue', 'Helvetica', 'Arial', sans-serif
	 */
	public String getFontFamily() {
		return getValue(Property.fontFamily, DEFAULT_FONT_FAMILY);
	}

}