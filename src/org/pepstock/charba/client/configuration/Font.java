/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.configuration;

import org.pepstock.charba.client.callbacks.FontCallback;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.enums.Weight;
import org.pepstock.charba.client.items.FontItem;
import org.pepstock.charba.client.options.IsFont;
import org.pepstock.charba.client.options.IsScriptableFontProvider;

/**
 * Base object to map font configuration.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class Font extends AbstractDynamicConfiguration<IsFont> implements IsFont {

	// instance of scriptable padding configuration container
	private final IsScriptableFontProvider<?> scriptableFontProvider;

	/**
	 * Builds the object by a font provider used to get the font element for storing properties.
	 * 
	 * @param provider font provider used to get the font element for storing properties.
	 */
	Font(IsProvider<IsFont> provider) {
		this(null, provider);
	}

	/**
	 * Builds the object by a font provider used to get the font element for storing properties.
	 * 
	 * @param scriptableFontProvider the provider of font callback
	 * @param provider font provider used to get the font element for storing properties.
	 */
	Font(IsScriptableFontProvider<?> scriptableFontProvider, IsProvider<IsFont> provider) {
		super(provider);
		// stores font container
		this.scriptableFontProvider = scriptableFontProvider;
	}

	/**
	 * Sets the font size.
	 * 
	 * @param size the font size.
	 */
	@Override
	public void setSize(int size) {
		// resets callback
		resetCallback();
		// stores value
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
		// resets callback
		resetCallback();
		// stores value
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
		// resets callback
		resetCallback();
		// stores value
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
		// resets callback
		resetCallback();
		// stores value
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
		// resets callback
		resetCallback();
		// stores value
		checkAndGet().setLineHeight(lineHeight);
	}

	/**
	 * Sets the line height.
	 * 
	 * @param lineHeight the line height.
	 */
	@Override
	public void setLineHeight(String lineHeight) {
		// resets callback
		resetCallback();
		// stores value
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
	 * Creates a font options instance using default or cloning current instance.
	 * 
	 * @return a font options instance using default or cloning current instance
	 */
	@Override
	public FontItem create() {
		return checkAndGet().create();
	}

	/**
	 * Invokes when any property of the font is being set, in order to reset the {@link FontCallback} if exists
	 */
	protected void resetCallback() {
		// checks if the font has been set previously as a callback
		if (scriptableFontProvider != null && scriptableFontProvider.getFontCallback() != null) {
			// if yes, resets it
			// resets by native callback to avoid conflicts on generics
			scriptableFontProvider.setFont((NativeCallback) null);
		}
	}

}