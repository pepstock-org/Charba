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
import org.pepstock.charba.client.defaults.IsDefaultFont;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.enums.Weight;

/**
 * Interface to map a font element.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsFont extends IsDefaultFont {

	/**
	 * Sets the font color.
	 * 
	 * @param color font color.
	 */
	default void setColor(IsColor color) {
		setColor(IsColor.checkAndGetValue(color));
	}

	/**
	 * Sets the font color.
	 * 
	 * @param color font color.
	 */
	void setColor(String color);

	/**
	 * Returns the font color.
	 * 
	 * @return font color
	 */
	default IsColor getColor() {
		return ColorBuilder.parse(getColorAsString());
	}

	/**
	 * Sets the font size.
	 * 
	 * @param size the font size.
	 */
	void setSize(int size);

	/**
	 * Sets the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @param style Font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 */
	void setStyle(FontStyle style);

	/**
	 * Sets the font family, follows CSS font-family options.
	 * 
	 * @param family Font family, follows CSS font-family options.
	 */
	void setFamily(String family);

	/**
	 * Sets the font weight, follows CSS font-style-weight options.
	 * 
	 * @param weight font weight, follows CSS font-style-weight options.
	 */
	void setWeight(Weight weight);

	/**
	 * Sets the line height.
	 * 
	 * @param lineHeight the line height.
	 */
	void setLineHeight(double lineHeight);

	/**
	 * Sets the line height.
	 * 
	 * @param lineHeight the line height.
	 */
	void setLineHeight(String lineHeight);

	/**
	 * Returns the height of an individual line of text.
	 * 
	 * @return the height of an individual line of text.
	 */
	String getLineHeightAsString();

	/**
	 * Sets the stroke width around the text.<br>
	 * Currently only supported by ticks.
	 * 
	 * @param lineWidth the stroke width around the text
	 */
	void setLineWidth(int lineWidth);

	/**
	 * Sets the color of the stroke around the text.<br>
	 * Currently only supported by ticks.
	 * 
	 * @param strokeStyle the color of the stroke around the text
	 */
	default void setStrokeStyle(IsColor strokeStyle) {
		setStrokeStyle(IsColor.checkAndGetValue(strokeStyle));
	}

	/**
	 * Sets the color of the stroke around the text.<br>
	 * Currently only supported by ticks.
	 * 
	 * @param strokeStyle the color of the stroke around the text
	 */
	void setStrokeStyle(String strokeStyle);

	/**
	 * Returns the color of the stroke around the text.<br>
	 * Currently only supported by ticks.
	 * 
	 * @return the color of the stroke around the text
	 */
	default IsColor getStrokeStyle() {
		return ColorBuilder.parse(getStrokeStyleAsString());
	}

}
