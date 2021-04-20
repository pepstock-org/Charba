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
package org.pepstock.charba.client.impl.charts;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.defaults.IsDefaultFont;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.enums.Weight;
import org.pepstock.charba.client.items.UndefinedValues;
import org.pepstock.charba.client.options.IsFont;

/**
 * Object to map font configuration for {@link MeterOptions} and {@link GaugeOptions}.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class Font implements IsFont {

	private final IsDefaultFont defaultValues;

	private int size;

	private FontStyle style;

	private String family;

	private Weight weight;

	private double lineHeight;

	private String lineHeightAsString = null;

	/**
	 * Creates an empty font to use for meter or gauge chart configuration.
	 * 
	 * @param defaultValues default provider
	 */
	Font(IsDefaultFont defaultValues) {
		// checks if consistent
		if (defaultValues == null) {
			// exception!!
			throw new IllegalArgumentException("Default values argument is null");
		}
		// stores defaults
		this.defaultValues = defaultValues;
		// stores values
		// loading from defaults
		setSize(UndefinedValues.INTEGER);
		setStyle(this.defaultValues.getStyle());
		setFamily(this.defaultValues.getFamily());
		setWeight(this.defaultValues.getWeight());
		setLineHeight(this.defaultValues.getLineHeight());
		setLineHeight(this.defaultValues.getLineHeightAsString());
	}

	/**
	 * Sets the font size.
	 * 
	 * @param size the font size.
	 */
	@Override
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * Returns the font size.
	 * 
	 * @return the font size.
	 */
	@Override
	public int getSize() {
		return size;
	}

	/**
	 * Sets the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @param style Font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 */
	@Override
	public void setStyle(FontStyle style) {
		// checks if consistent
		if (Key.isValid(style)) {
			this.style = style;
		}
	}

	/**
	 * Returns the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @return the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 */
	@Override
	public FontStyle getStyle() {
		return style;
	}

	/**
	 * Sets the font family, follows CSS font-family options.
	 * 
	 * @param family Font family, follows CSS font-family options.
	 */
	@Override
	public void setFamily(String family) {
		// checks if consistent
		if (family != null) {
			this.family = family;
		}
	}

	/**
	 * Returns the font family, follows CSS font-family options.
	 * 
	 * @return Font family, follows CSS font-family options.
	 */
	@Override
	public String getFamily() {
		return family;
	}

	/**
	 * Sets the font weight, follows CSS font-style-weight options.
	 * 
	 * @param weight font weight, follows CSS font-style-weight options.
	 */
	@Override
	public void setWeight(Weight weight) {
		// checks if consistent
		if (Key.isValid(weight)) {
			this.weight = weight;
		}
	}

	/**
	 * Returns the font weight, follows CSS font-style-weight options.
	 * 
	 * @return the font weight, follows CSS font-style-weight options.
	 */
	@Override
	public Weight getWeight() {
		return weight;
	}

	/**
	 * Sets the line height.
	 * 
	 * @param lineHeight the line height.
	 */
	@Override
	public void setLineHeight(double lineHeight) {
		this.lineHeight = lineHeight;
		// checks if set to NaN
		if (Double.isNaN(lineHeight)) {
			// restores the default
			setLineHeight(this.defaultValues.getLineHeightAsString());
		}
	}

	/**
	 * Sets the line height.
	 * 
	 * @param lineHeight the line height.
	 */
	@Override
	public void setLineHeight(String lineHeight) {
		this.lineHeightAsString = lineHeight;
		// checks if set to null
		if (lineHeight == null) {
			// restores the default
			setLineHeight(this.defaultValues.getLineHeight());
		}
	}

	/**
	 * Returns the height of an individual line of text.
	 * 
	 * @return the height of an individual line of text.
	 */
	@Override
	public double getLineHeight() {
		return lineHeight;
	}

	/**
	 * Returns the height of an individual line of text.
	 * 
	 * @return the height of an individual line of text.
	 */
	@Override
	public String getLineHeightAsString() {
		return lineHeightAsString;
	}

}
