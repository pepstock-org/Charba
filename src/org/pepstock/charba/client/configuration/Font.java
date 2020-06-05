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
package org.pepstock.charba.client.configuration;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.enums.Weight;
import org.pepstock.charba.client.options.IsFont;

/**
 * Base object to map font configuration.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class Font implements IsFont {

	// font options element
	private final IsFont font;

	/**
	 * Builds the object storing the font options.
	 * 
	 * @param font font options element
	 */
	Font(IsFont font) {
		// checks if font container is consistent
		if (font == null) {
			throw new IllegalArgumentException("Font argument is null");
		}
		// stores font container
		this.font = font;
	}

	/**
	 * Sets the font color.
	 * 
	 * @param color font color.
	 */
	public void setColor(IsColor color) {
		font.setColor(color);
	}

	/**
	 * Sets the font color.
	 * 
	 * @param color font color.
	 */
	public void setColor(String color) {
		font.setColor(color);
	}

	/**
	 * Returns the font color as string.
	 * 
	 * @return font color as string
	 */
	public String getColorAsString() {
		return font.getColorAsString();
	}

	/**
	 * Returns the font color.
	 * 
	 * @return font color
	 */
	public IsColor getColor() {
		return ColorBuilder.parse(getColorAsString());
	}

	/**
	 * Sets the font size.
	 * 
	 * @param size the font size.
	 */
	public void setSize(int size) {
		font.setSize(size);
	}

	/**
	 * Returns the font size.
	 * 
	 * @return the font size.
	 */
	public int getSize() {
		return font.getSize();
	}

	/**
	 * Sets the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @param style Font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 */
	public void setStyle(FontStyle style) {
		font.setStyle(style);
	}

	/**
	 * Returns the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @return the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 */
	public FontStyle getStyle() {
		return font.getStyle();
	}

	/**
	 * Sets the font family, follows CSS font-family options.
	 * 
	 * @param family Font family, follows CSS font-family options.
	 */
	public void setFamily(String family) {
		font.setFamily(family);
	}

	/**
	 * Returns the font family, follows CSS font-family options.
	 * 
	 * @return Font family, follows CSS font-family options.
	 */
	public String getFamily() {
		return font.getFamily();
	}

	/**
	 * Sets the font weight, follows CSS font-style-weight options.
	 * 
	 * @param weight font weight, follows CSS font-style-weight options.
	 */
	public void setWeight(Weight weight) {
		font.setWeight(weight);
	}

	/**
	 * Returns the font weight, follows CSS font-style-weight options.
	 * 
	 * @return the font weight, follows CSS font-style-weight options.
	 */
	public Weight getWeight() {
		return font.getWeight();
	}

	/**
	 * Sets the line height.
	 * 
	 * @param lineHeight the line height.
	 */
	public void setLineHeight(double lineHeight) {
		font.setLineHeight(lineHeight);
	}

	/**
	 * Sets the line height.
	 * 
	 * @param lineHeight the line height.
	 */
	public void setLineHeight(String lineHeight) {
		font.setLineHeight(lineHeight);
	}

	/**
	 * Returns the height of an individual line of text.
	 * 
	 * @return the height of an individual line of text.
	 */
	public double getLineHeight() {
		return font.getLineHeight();
	}

	/**
	 * Returns the height of an individual line of text.
	 * 
	 * @return the height of an individual line of text.
	 */
	public String getLineHeightAsString() {
		return font.getLineHeightAsString();
	}

	/**
	 * Sets the stroke width around the text.<br>
	 * Currently only supported by ticks.
	 * 
	 * @param lineWidth the stroke width around the text
	 */
	public void setLineWidth(int lineWidth) {
		font.setLineWidth(lineWidth);

	}

	/**
	 * Returns the stroke width around the text.<br>
	 * Currently only supported by ticks.
	 * 
	 * @return the stroke width around the text
	 */
	public int getLineWidth() {
		return font.getLineWidth();
	}

	/**
	 * Sets the color of the stroke around the text.<br>
	 * Currently only supported by ticks.
	 * 
	 * @param strokeStyle the color of the stroke around the text
	 */
	public void setStrokeStyle(IsColor strokeStyle) {
		font.setStrokeStyle(strokeStyle);
	}

	/**
	 * Sets the color of the stroke around the text.<br>
	 * Currently only supported by ticks.
	 * 
	 * @param strokeStyle the color of the stroke around the text
	 */
	public void setStrokeStyle(String strokeStyle) {
		font.setStrokeStyle(strokeStyle);
	}

	/**
	 * Returns the color of the stroke around the text.<br>
	 * Currently only supported by ticks.
	 * 
	 * @return the color of the stroke around the text
	 */
	public String getStrokeStyleAsString() {
		return font.getStrokeStyleAsString();
	}

	/**
	 * Returns the color of the stroke around the text.<br>
	 * Currently only supported by ticks.
	 * 
	 * @return the color of the stroke around the text
	 */
	public IsColor getStrokeStyle() {
		return ColorBuilder.parse(getStrokeStyleAsString());
	}

}
