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
package org.pepstock.charba.client.impl.plugins;

import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.Pattern;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.impl.plugins.ChartBackgroundColorOptions.ColorType;
import org.pepstock.charba.client.plugins.AbstractPluginOptions;

/**
 * Default configuration options of {@link ChartBackgroundColor#ID} plugin.
 * 
 * @author Andrea "Stock" Stocchero
 */
final class ChartBackgroundColorDefaultsOptions extends AbstractPluginOptions {

	// defaults options instance
	static final ChartBackgroundColorDefaultsOptions DEFAULTS_INSTANCE = new ChartBackgroundColorDefaultsOptions();

	/**
	 * Builds the object with an empty java script object.
	 */
	private ChartBackgroundColorDefaultsOptions() {
		this(null);
	}

	/**
	 * Builds the object with a java script object stored into options.
	 * 
	 * @param nativeObject native object into options
	 */
	ChartBackgroundColorDefaultsOptions(NativeObject nativeObject) {
		super(ChartBackgroundColor.ID, nativeObject);
	}

	/**
	 * Returns the type of background color has been set.
	 * 
	 * @return the type of background color has been set
	 */
	ColorType getColorType() {
		return getValue(ChartBackgroundColorOptions.Property.COLOR_TYPE, ColorType.class, ColorType.COLOR);
	}

	/**
	 * Returns the background color as string.
	 * 
	 * @return the background color
	 */
	String getBackgroundColorAsString() {
		return getValue(ChartBackgroundColorOptions.Property.BACKGROUND_COLOR, ChartBackgroundColor.DEFAULT_BACKGROUND_COLOR);
	}

	/**
	 * Returns the background gradient. If it has been set a color or pattern, returns <code>null</code>.
	 * 
	 * @return the background gradient. If it has been set a color or pattern, returns <code>null</code>
	 */
	Gradient getBackgroundColorAsGradient() {
		// checks if gradient has been set
		if (ColorType.GRADIENT.equals(getColorType())) {
			return ChartBackgroundColor.GRADIENT_FACTORY.create(getValue(ChartBackgroundColorOptions.Property.BACKGROUND_COLOR));
		}
		// otherwise returns null
		return null;
	}

	/**
	 * Returns the background pattern. If it has been set a color or gradient, returns <code>null</code>.
	 * 
	 * @return the background pattern. If it has been set a color or pattern, returns <code>null</code>
	 */
	Pattern getBackgroundColorAsPattern() {
		// checks if pattern has been set
		if (ColorType.PATTERN.equals(getColorType())) {
			return ChartBackgroundColor.PATTERN_FACTORY.create(getValue(ChartBackgroundColorOptions.Property.BACKGROUND_COLOR));
		}
		// otherwise returns null
		return null;
	}

}
