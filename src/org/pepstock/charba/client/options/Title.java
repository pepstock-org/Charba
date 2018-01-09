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

import java.util.List;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ChartContainer;
import org.pepstock.charba.client.commons.JsStringArrayList;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.enums.Position;

/**
 * Configures the chart title which defines text to draw at the top of the chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Title extends ChartContainer {

	// flag to check if a multiple line title has been set
	private boolean isTextArray = false;

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		fontSize,
		fontStyle,
		fontColor,
		fontFamily,
		display,
		padding,
		position,
		lineHeight,
		fullWidth,
		text
	}

	/**
	 * Builds the object storing the chart instance.
	 * 
	 * @param chart chart instance
	 */
	Title(AbstractChart<?, ?> chart) {
		super(chart);
	}

	/**
	 * Sets the font size for title.
	 * 
	 * @param fontSize Font size for title.
	 */
	public void setFontSize(int fontSize) {
		setValue(Property.fontSize, fontSize);
	}

	/**
	 * Returns the font size for title.
	 * 
	 * @return Font size for title. Default is {@link org.pepstock.charba.client.defaults.global.Title#getFontSize()}.
	 */
	public int getFontSize() {
		return getValue(Property.fontSize, Defaults.getGlobal().getTitle().getFontSize());
	}

	/**
	 * Sets the font style for title, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @param fontStyle Font style for title, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public void setFontStyle(FontStyle fontStyle) {
		setValue(Property.fontStyle, fontStyle);
	}

	/**
	 * Returns the font style for title, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @return the font style for title, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit). {@link org.pepstock.charba.client.defaults.global.Title#getFontStyle()}.
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public FontStyle getFontStyle() {
		return getValue(Property.fontStyle, FontStyle.class, Defaults.getGlobal().getTitle().getFontStyle());
	}

	/**
	 * Sets the font color for title
	 * 
	 * @param fontColor Font color for title
	 */
	public void setFontColor(String fontColor) {
		setValue(Property.fontColor, fontColor);
	}

	/**
	 * Returns the font color for title
	 * 
	 * @return Font color for title. Default is {@link org.pepstock.charba.client.defaults.global.Title#getFontColor()}.
	 */
	public String getFontColor() {
		return getValue(Property.fontColor, Defaults.getGlobal().getTitle().getFontColor());
	}

	/**
	 * Sets the font family for title, follows CSS font-family options.
	 * 
	 * @param fontFamily Font family for title, follows CSS font-family options.
	 */
	public void setFontFamily(String fontFamily) {
		setValue(Property.fontFamily, fontFamily);
	}

	/**
	 * Returns the font family for title, follows CSS font-family options.
	 * 
	 * @return Font family for title, follows CSS font-family options. Default is {@link org.pepstock.charba.client.defaults.global.Title#getFontFamily()}.
	 */
	public String getFontFamily() {
		return getValue(Property.fontFamily, Defaults.getGlobal().getTitle().getFontFamily());
	}

	/**
	 * Sets if the title is shown.
	 * 
	 * @param display if the title is shown.
	 */
	public void setDisplay(boolean display) {
		setValue(Property.display, display);
	}

	/**
	 * Returns if the title is shown.
	 * 
	 * @return if the title is shown. Default is {@link org.pepstock.charba.client.defaults.global.Title#isDisplay()}.
	 */
	public boolean isDisplay() {
		return getValue(Property.display, Defaults.getGlobal().getTitle().isDisplay());
	}

	/**
	 * Sets the title text to display. If specified as an array, text is rendered on multiple lines.
	 * 
	 * @param text the title text to display. If specified as an array, text is rendered on multiple lines.
	 */
	public void setText(String... text) {
		setText(ArrayListHelper.build(text));
	}

	/**
	 * Sets the title text to display as an array.
	 * 
	 * @param text the title text to display as an array.
	 */
	private void setText(JsStringArrayList text) {
		isTextArray = checkAndSetStringValues(Property.text, text);
	}

	/**
	 * Returns the title text to display, as a list of strings.
	 * 
	 * @return a list of strings
	 */
	public List<String> getText() {
		return checkAndGetStringValues(Property.text, isTextArray);
	}

	/**
	 * Sets the position of title.
	 * 
	 * @param position the position of title.
	 * @see org.pepstock.charba.client.enums.Position
	 */
	public void setPosition(Position position) {
		setValue(Property.position, position);
	}

	/**
	 * Returns the position of title.
	 * 
	 * @return the position of title. Default is {@link org.pepstock.charba.client.defaults.global.Title#getPosition()}.
	 * @see org.pepstock.charba.client.enums.Position
	 */
	public Position getPosition() {
		return getValue(Property.position, Position.class, Defaults.getGlobal().getTitle().getPosition());
	}
	
	/**
	 * Sets the padding to apply around title. Only top and bottom are implemented.
	 * 
	 * @param padding Padding to apply around title. Only top and bottom are implemented.
	 */
	public void setPadding(int padding) {
		setValue(Property.padding, padding);
	}

	/**
	 * Returns the padding to apply around title. Only top and bottom are implemented.
	 * 
	 * @return Padding to apply around title. Only top and bottom are implemented. Default is {@link org.pepstock.charba.client.defaults.global.Title#getPadding()}.
	 */
	public int getPadding() {
		return getValue(Property.padding, Defaults.getGlobal().getTitle().getPadding());
	}
	
	/**
	 */
	public void setFullWidth(boolean fullWidth) {
		setValue(Property.fullWidth, fullWidth);
	}

	/**
	 */
	public boolean isFullWidth() {
		return getValue(Property.fullWidth, Defaults.getGlobal().getTitle().isFullWidth());
	}

	/**
	 */
	public void setLineHeight(boolean lineHeight) {
		setValue(Property.lineHeight, lineHeight);
	}

	/**
	 */
	public double getLineHeight() {
		return getValue(Property.lineHeight, Defaults.getGlobal().getTitle().getLineHeight());
	}


}