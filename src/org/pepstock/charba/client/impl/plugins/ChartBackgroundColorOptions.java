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

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.GradientBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.colors.Pattern;
import org.pepstock.charba.client.colors.PatternBuilder;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.enums.ColorType;
import org.pepstock.charba.client.plugins.AbstractPluginOptions;

/**
 * Configuration options of {@link ChartBackgroundColor#ID} plugin.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class ChartBackgroundColorOptions extends AbstractPluginOptions implements IsChartBackgroundColorDefaultOptions {

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		BACKGROUND_COLOR("backgroundColor"),
		COLOR_TYPE("colorType");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use in the native object.
		 * 
		 * @param value value of property name
		 */
		private Property(String value) {
			this.value = value;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.Key#value()
		 */
		@Override
		public String value() {
			return value;
		}
	}

	// defaults options instance
	private final IsChartBackgroundColorDefaultOptions defaultOptions;

	/**
	 * Builds the object with new java script object setting the default value of plugin.<br>
	 * The global plugin options is used, if exists, as defaults values.
	 */
	public ChartBackgroundColorOptions() {
		this(null, null);
	}

	/**
	 * Builds the object with a chart instance in order to get the right defaults.<br>
	 * If the plugin options have not been set by chart type, it will use the global.
	 * 
	 * @param chart chart instance related to the plugin options
	 */
	public ChartBackgroundColorOptions(IsChart chart) {
		this(IsChart.isConsistent(chart) ? chart.getDefaultChartOptions().getPlugins().getOptions(ChartBackgroundColor.ID, ChartBackgroundColor.DEFAULTS_FACTORY) : null);
	}

	/**
	 * Builds new object with default options.
	 * 
	 * @param defaultOptions defaults options to use to get values
	 */
	ChartBackgroundColorOptions(IsChartBackgroundColorDefaultOptions defaultOptions) {
		this(defaultOptions, null);
	}

	/**
	 * Builds the object with a java script object stored in the options.
	 * 
	 * @param defaultOptions plugin default options
	 * @param nativeObject native object in the options
	 */
	ChartBackgroundColorOptions(IsChartBackgroundColorDefaultOptions defaultOptions, NativeObject nativeObject) {
		super(ChartBackgroundColor.ID, nativeObject);
		// checks if there is any default options
		if (defaultOptions == null) {
			// loads global options
			this.defaultOptions = loadGlobalsPluginOptions(ChartBackgroundColor.DEFAULTS_FACTORY);
		} else {
			// stores default options
			this.defaultOptions = defaultOptions;
		}
	}

	/**
	 * Returns the type of background color has been set.
	 * 
	 * @return the type of background color has been set.
	 */
	@Override
	public ColorType getColorType() {
		return getValue(Property.COLOR_TYPE, ColorType.values(), defaultOptions.getColorType());
	}

	/**
	 * Returns the background color.<br>
	 * If it has been set a gradient or pattern, returns <code>null</code>, otherwise the default color, "white".
	 * 
	 * @return the background color.<br>
	 *         If it has been set a gradient or pattern, returns <code>null</code>, otherwise the default color, "white".
	 */
	@Override
	public String getBackgroundColorAsString() {
		// checks if color has been set
		if (ColorType.COLOR.equals(getColorType())) {
			return getValue(Property.BACKGROUND_COLOR, defaultOptions.getBackgroundColorAsString());
		}
		// otherwise returns defaults
		return defaultOptions.getBackgroundColorAsString();
	}

	/**
	 * Returns the background color.<br>
	 * If it has been set a gradient or pattern, returns <code>null</code>, otherwise the default color, "white".
	 * 
	 * @return the background color.<br>
	 *         If it has been set a gradient or pattern, returns <code>null</code>, otherwise the default color, "white".
	 */
	public IsColor getBackgroundColor() {
		// checks if color has been set
		if (ColorType.COLOR.equals(getColorType())) {
			return ColorBuilder.parse(getBackgroundColorAsString());
		}
		// otherwise returns defaults
		return ColorBuilder.parse(defaultOptions.getBackgroundColorAsString());
	}

	/**
	 * Returns the background gradient. <br>
	 * If it has been set a color or pattern, returns <code>null</code>.
	 * 
	 * @return the background gradient. <br>
	 *         If it has been set a color or pattern, returns <code>null</code>
	 */
	@Override
	public Gradient getBackgroundColorAsGradient() {
		// checks if gradient has been set
		if (ColorType.GRADIENT.equals(getColorType())) {
			// checks if the gradient has been set in the this object or in the defaults
			if (has(Property.BACKGROUND_COLOR)) {
				// if here, the gradient has been set in the this options
				return GradientBuilder.build(getValue(Property.BACKGROUND_COLOR));
			} else {
				// if here, the gradient has been set in the defaults options
				return defaultOptions.getBackgroundColorAsGradient();
			}
		}
		// otherwise returns null
		return null;
	}

	/**
	 * Returns the background pattern.<br>
	 * If it has been set a color or gradient, returns <code>null</code>.
	 * 
	 * @return the background pattern. <br>
	 *         If it has been set a color or pattern, returns <code>null</code>
	 */
	@Override
	public Pattern getBackgroundColorAsPattern() {
		// checks if pattern has been set
		if (ColorType.PATTERN.equals(getColorType())) {
			// checks if the pattern has been set in the this object or in the defaults
			if (has(Property.BACKGROUND_COLOR)) {
				// if here, the pattern has been set in the this options
				return PatternBuilder.build(getValue(Property.BACKGROUND_COLOR));
			} else {
				// if here, the pattern has been set in the defaults options
				return defaultOptions.getBackgroundColorAsPattern();
			}
		}
		// otherwise returns null
		return null;
	}

	/**
	 * Sets the background color.
	 * 
	 * @param color the background color.
	 */
	public void setBackgroundColor(String color) {
		setValue(Property.BACKGROUND_COLOR, color);
		// checks if color is consistent
		if (color != null) {
			// sets the color type
			setValue(Property.COLOR_TYPE, ColorType.COLOR);
		} else {
			// if here, the color is not consistent
			// removes type
			remove(Property.COLOR_TYPE);
		}
	}

	/**
	 * Sets the background color.
	 * 
	 * @param color the background color.
	 */
	public void setBackgroundColor(IsColor color) {
		setBackgroundColor(IsColor.checkAndGetValue(color));
	}

	/**
	 * Sets the background gradient.
	 * 
	 * @param gradient the background gradient.
	 */
	public void setBackgroundColor(Gradient gradient) {
		setValue(Property.BACKGROUND_COLOR, gradient);
		// checks if argument is consistent
		if (gradient != null) {
			// sets the color type
			setValue(Property.COLOR_TYPE, ColorType.GRADIENT);
		} else {
			// if here, the gradient is not consistent
			// removes type
			remove(Property.COLOR_TYPE);
		}
	}

	/**
	 * Sets the background pattern.
	 * 
	 * @param pattern the background pattern.
	 */
	public void setBackgroundColor(Pattern pattern) {
		setValue(Property.BACKGROUND_COLOR, pattern);
		// checks if argument is consistent
		if (pattern != null) {
			// sets the color type
			setValue(Property.COLOR_TYPE, ColorType.PATTERN);
		} else {
			// if here, the pattern is not consistent
			// removes type
			remove(Property.COLOR_TYPE);
		}
	}
}
