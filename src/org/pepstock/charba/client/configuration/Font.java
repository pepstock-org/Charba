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
	private final IsFont innerFont;

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
		this.innerFont = font;
	}

	/**
	 * Sets the font color.
	 * 
	 * @param color font color.
	 */
	@Override
	public void setColor(IsColor color) {
		innerFont.setColor(color);
	}

	/**
	 * Sets the font color.
	 * 
	 * @param color font color.
	 */
	@Override
	public void setColor(String color) {
		innerFont.setColor(color);
	}

	/**
	 * Returns the font color as string.
	 * 
	 * @return font color as string
	 */
	@Override
	public String getColorAsString() {
		return innerFont.getColorAsString();
	}

	/**
	 * Returns the font color.
	 * 
	 * @return font color
	 */
	@Override
	public IsColor getColor() {
		return ColorBuilder.parse(getColorAsString());
	}

	/**
	 * Sets the font size.
	 * 
	 * @param size the font size.
	 */
	@Override
	public void setSize(int size) {
		innerFont.setSize(size);
	}

	/**
	 * Returns the font size.
	 * 
	 * @return the font size.
	 */
	@Override
	public int getSize() {
		return innerFont.getSize();
	}

	/**
	 * Sets the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @param style Font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 */
	@Override
	public void setStyle(FontStyle style) {
		innerFont.setStyle(style);
	}

	/**
	 * Returns the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @return the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 */
	@Override
	public FontStyle getStyle() {
		return innerFont.getStyle();
	}

	/**
	 * Sets the font family, follows CSS font-family options.
	 * 
	 * @param family Font family, follows CSS font-family options.
	 */
	@Override
	public void setFamily(String family) {
		innerFont.setFamily(family);
	}

	/**
	 * Returns the font family, follows CSS font-family options.
	 * 
	 * @return Font family, follows CSS font-family options.
	 */
	@Override
	public String getFamily() {
		return innerFont.getFamily();
	}

	/**
	 * Sets the font weight, follows CSS font-style-weight options.
	 * 
	 * @param weight font weight, follows CSS font-style-weight options.
	 */
	@Override
	public void setWeight(Weight weight) {
		innerFont.setWeight(weight);
	}

	/**
	 * Returns the font weight, follows CSS font-style-weight options.
	 * 
	 * @return the font weight, follows CSS font-style-weight options.
	 */
	@Override
	public Weight getWeight() {
		return innerFont.getWeight();
	}

	/**
	 * Sets the line height.
	 * 
	 * @param lineHeight the line height.
	 */
	@Override
	public void setLineHeight(double lineHeight) {
		innerFont.setLineHeight(lineHeight);
	}

	/**
	 * Sets the line height.
	 * 
	 * @param lineHeight the line height.
	 */
	@Override
	public void setLineHeight(String lineHeight) {
		innerFont.setLineHeight(lineHeight);
	}

	/**
	 * Returns the height of an individual line of text.
	 * 
	 * @return the height of an individual line of text.
	 */
	@Override
	public double getLineHeight() {
		return innerFont.getLineHeight();
	}

	/**
	 * Returns the height of an individual line of text.
	 * 
	 * @return the height of an individual line of text.
	 */
	@Override
	public String getLineHeightAsString() {
		return innerFont.getLineHeightAsString();
	}

	/**
	 * Sets the stroke width around the text.<br>
	 * Currently only supported by ticks.
	 * 
	 * @param lineWidth the stroke width around the text
	 */
	@Override
	public void setLineWidth(int lineWidth) {
		innerFont.setLineWidth(lineWidth);

	}

	/**
	 * Returns the stroke width around the text.<br>
	 * Currently only supported by ticks.
	 * 
	 * @return the stroke width around the text
	 */
	@Override
	public int getLineWidth() {
		return innerFont.getLineWidth();
	}

	/**
	 * Sets the color of the stroke around the text.<br>
	 * Currently only supported by ticks.
	 * 
	 * @param strokeStyle the color of the stroke around the text
	 */
	@Override
	public void setStrokeStyle(IsColor strokeStyle) {
		innerFont.setStrokeStyle(strokeStyle);
	}

	/**
	 * Sets the color of the stroke around the text.<br>
	 * Currently only supported by ticks.
	 * 
	 * @param strokeStyle the color of the stroke around the text
	 */
	@Override
	public void setStrokeStyle(String strokeStyle) {
		innerFont.setStrokeStyle(strokeStyle);
	}

	/**
	 * Returns the color of the stroke around the text.<br>
	 * Currently only supported by ticks.
	 * 
	 * @return the color of the stroke around the text
	 */
	@Override
	public String getStrokeStyleAsString() {
		return innerFont.getStrokeStyleAsString();
	}

	/**
	 * Returns the color of the stroke around the text.<br>
	 * Currently only supported by ticks.
	 * 
	 * @return the color of the stroke around the text
	 */
	@Override
	public IsColor getStrokeStyle() {
		return ColorBuilder.parse(getStrokeStyleAsString());
	}

}
