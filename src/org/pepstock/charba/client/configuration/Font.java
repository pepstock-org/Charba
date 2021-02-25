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

import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.enums.Weight;
import org.pepstock.charba.client.options.IsFont;

/**
 * Base object to map font configuration.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class Font implements IsFont {

	// font provider instance
	private final IsFontProvider provider;

	/**
	 * Builds the object by a font provider used to get the font element for storing properties.
	 * 
	 * @param provider font provider used to get the font element for storing properties.
	 */
	Font(IsFontProvider provider) {
		// checks if consistent
		if (provider == null) {
			// exception!
			throw new IllegalArgumentException("Font provider argument is null");
		}
		// stores provider
		this.provider = provider;
	}

	/**
	 * Sets the font size.
	 * 
	 * @param size the font size.
	 */
	@Override
	public void setSize(int size) {
		checkAndGet().setSize(size);
	}

	/**
	 * Returns the font size.
	 * 
	 * @return the font size.
	 */
	@Override
	public int getSize() {
		return checkAndGet().getSize();
	}

	/**
	 * Sets the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @param style Font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 */
	@Override
	public void setStyle(FontStyle style) {
		checkAndGet().setStyle(style);
	}

	/**
	 * Returns the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @return the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 */
	@Override
	public FontStyle getStyle() {
		return checkAndGet().getStyle();
	}

	/**
	 * Sets the font family, follows CSS font-family options.
	 * 
	 * @param family Font family, follows CSS font-family options.
	 */
	@Override
	public void setFamily(String family) {
		checkAndGet().setFamily(family);
	}

	/**
	 * Returns the font family, follows CSS font-family options.
	 * 
	 * @return Font family, follows CSS font-family options.
	 */
	@Override
	public String getFamily() {
		return checkAndGet().getFamily();
	}

	/**
	 * Sets the font weight, follows CSS font-style-weight options.
	 * 
	 * @param weight font weight, follows CSS font-style-weight options.
	 */
	@Override
	public void setWeight(Weight weight) {
		checkAndGet().setWeight(weight);
	}

	/**
	 * Returns the font weight, follows CSS font-style-weight options.
	 * 
	 * @return the font weight, follows CSS font-style-weight options.
	 */
	@Override
	public Weight getWeight() {
		return checkAndGet().getWeight();
	}

	/**
	 * Sets the line height.
	 * 
	 * @param lineHeight the line height.
	 */
	@Override
	public void setLineHeight(double lineHeight) {
		checkAndGet().setLineHeight(lineHeight);
	}

	/**
	 * Sets the line height.
	 * 
	 * @param lineHeight the line height.
	 */
	@Override
	public void setLineHeight(String lineHeight) {
		checkAndGet().setLineHeight(lineHeight);
	}

	/**
	 * Returns the height of an individual line of text.
	 * 
	 * @return the height of an individual line of text.
	 */
	@Override
	public double getLineHeight() {
		return checkAndGet().getLineHeight();
	}

	/**
	 * Returns the height of an individual line of text.
	 * 
	 * @return the height of an individual line of text.
	 */
	@Override
	public String getLineHeightAsString() {
		return checkAndGet().getLineHeightAsString();
	}

	/**
	 * Gets the {@link IsFont} instance from provider checking if is consistent.
	 * 
	 * @return the {@link IsFont} instance from provider
	 */
	private IsFont checkAndGet() {
		IsFont font = provider.getElement();
		// checks if consistent
		if (font == null) {
			// exception!
			throw new IllegalArgumentException("Font element by provider is null");
		}
		return font;
	}

	/**
	 * Interface to implement to provide the {@link IsFont} options to manage font properties.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	interface IsFontProvider {

		/**
		 * Provides the {@link IsFont} options to manage font properties.
		 * 
		 * @return {@link IsFont} options to manage font properties.
		 */
		IsFont getElement();

	}
}
