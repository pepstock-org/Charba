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
package org.pepstock.charba.client.jsinterop.configuration;

import java.util.List;

import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.jsinterop.options.Options;

/**
 * Configures the chart title which defines text to draw at the top of the chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Title {
	
	private final Options options;

	/**
	 * Builds the object storing the chart instance.
	 * 
	 * @param chart chart instance
	 */
	Title(Options options) {
		this.options = options;
	}

	/**
	 * Sets the font size for title.
	 * 
	 * @param fontSize Font size for title.
	 */
	public void setFontSize(int fontSize) {
		options.getTitle().setFontSize(fontSize);
	}

	/**
	 * Returns the font size for title.
	 * 
	 * @return Font size for title. For default value, see {@link org.pepstock.charba.client.GlobalOptions#getTitle()}.
	 */
	public int getFontSize() {
		return options.getTitle().getFontSize();
	}

	/**
	 * Sets the font style for title, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @param fontStyle Font style for title, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public void setFontStyle(FontStyle fontStyle) {
		options.getTitle().setFontStyle(fontStyle);
	}

	/**
	 * Returns the font style for title, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @return the font style for title, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit). For
	 *         default value, see {@link org.pepstock.charba.client.GlobalOptions#getTitle()}.
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public FontStyle getFontStyle() {
		return options.getTitle().getFontStyle();
	}

	/**
	 * Sets the font color for title
	 * 
	 * @param fontColor Font color for title
	 */
	public void setFontColor(IsColor fontColor) {
		options.getTitle().setFontColor(fontColor);
	}

	/**
	 * Sets the font color for title
	 * 
	 * @param fontColor Font color for title
	 */
	public void setFontColor(String fontColor) {
		options.getTitle().setFontColor(fontColor);
	}

	/**
	 * Returns the font color for title
	 * 
	 * @return Font color for title. For default value, see {@link org.pepstock.charba.client.GlobalOptions#getTitle()}.
	 */
	public String getFontColorAsString() {
		return options.getTitle().getFontColorAsString();
	}

	/**
	 * Returns the font color for title
	 * 
	 * @return Font color for title. For default value, see {@link org.pepstock.charba.client.GlobalOptions#getTitle()}.
	 */
	public IsColor getFontColor() {
		return options.getTitle().getFontColor();
	}

	/**
	 * Sets the font family for title, follows CSS font-family options.
	 * 
	 * @param fontFamily Font family for title, follows CSS font-family options.
	 */
	public void setFontFamily(String fontFamily) {
		options.getTitle().setFontFamily(fontFamily);
	}

	/**
	 * Returns the font family for title, follows CSS font-family options.
	 * 
	 * @return Font family for title, follows CSS font-family options. For default value, see
	 *         {@link org.pepstock.charba.client.GlobalOptions#getTitle()}.
	 */
	public String getFontFamily() {
		return options.getTitle().getFontFamily();
	}

	/**
	 * Sets if the title is shown.
	 * 
	 * @param display if the title is shown.
	 */
	public void setDisplay(boolean display) {
		options.getTitle().setDisplay(display);
	}

	/**
	 * Returns if the title is shown.
	 * 
	 * @return if the title is shown. For default value, see {@link org.pepstock.charba.client.GlobalOptions#getTitle()}.
	 */
	public boolean isDisplay() {
		return options.getTitle().isDisplay();
	}

	/**
	 * Sets the title text to display. If specified as an array, text is rendered on multiple lines.
	 * 
	 * @param text the title text to display. If specified as an array, text is rendered on multiple lines.
	 */
	public void setText(String... text) {
		options.getTitle().setText(text);
	}

	/**
	 * Returns the title text to display, as a list of strings.
	 * 
	 * @return a list of strings
	 */
	public List<String> getText() {
		return options.getTitle().getText();
	}

	/**
	 * Sets the position of title.
	 * 
	 * @param position the position of title.
	 * @see org.pepstock.charba.client.enums.Position
	 */
	public void setPosition(Position position) {
		options.getTitle().setPosition(position);
	}

	/**
	 * Returns the position of title.
	 * 
	 * @return the position of title. For default value, see {@link org.pepstock.charba.client.GlobalOptions#getTitle()}.
	 * @see org.pepstock.charba.client.enums.Position
	 */
	public Position getPosition() {
		return options.getTitle().getPosition();
	}

	/**
	 * Sets the padding to apply around title. Only top and bottom are implemented.
	 * 
	 * @param padding Padding to apply around title. Only top and bottom are implemented.
	 */
	public void setPadding(int padding) {
		options.getTitle().setPadding(padding);
	}

	/**
	 * Returns the padding to apply around title. Only top and bottom are implemented.
	 * 
	 * @return Padding to apply around title. Only top and bottom are implemented. For default value, see
	 *         {@link org.pepstock.charba.client.GlobalOptions#getTitle()}.
	 */
	public int getPadding() {
		return options.getTitle().getPadding();
	}

	/**
	 * Marks that this box should take the full width of the canvas (pushing down other boxes).
	 * 
	 * @param fullWidth Marks that this box should take the full width of the canvas (pushing down other boxes)
	 */
	public void setFullWidth(boolean fullWidth) {
		options.getTitle().setFullWidth(fullWidth);
	}

	/**
	 * Returns if marks that this box should take the full width of the canvas (pushing down other boxes)
	 * 
	 * @return Marks that this box should take the full width of the canvas (pushing down other boxes). For default see
	 *         {@link org.pepstock.charba.client.GlobalOptions#getTitle()}.
	 */
	public boolean isFullWidth() {
		return options.getTitle().isFullWidth();
	}

	/**
	 * Sets the height of an individual line of text.
	 * 
	 * @param lineHeight height of an individual line of text.
	 */
	public void setLineHeight(double lineHeight) {
		options.getTitle().setLineHeight(lineHeight);
	}

	/**
	 * Returns the height of an individual line of text.
	 * 
	 * @return height of an individual line of text. For default see
	 *         {@link org.pepstock.charba.client.GlobalOptions#getTitle()}.
	 */
	public double getLineHeight() {
		return options.getTitle().getLineHeight();
	}
}