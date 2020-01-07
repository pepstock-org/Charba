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

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.colors.Pattern;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.plugins.AbstractPluginOptions;

/**
 * Configuration options of {@link ChartBackgroundColor#ID} plugin.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class ChartBackgroundColorOptions extends AbstractPluginOptions {

	/**
	 * Value of the stored color type into native object.
	 */
	enum ColorType implements Key
	{
		COLOR("color"),
		PATTERN("pattern"),
		GRADIENT("gradient");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use into native object.
		 * 
		 * @param value value of property name
		 */
		private ColorType(String value) {
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
		 * Creates with the property value to use into native object.
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
	private final ChartBackgroundColorDefaultsOptions defaultsOptions;
	
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
	 * @param type chart type to use to get the default values by chart
	 */
	public ChartBackgroundColorOptions(Type type) {
		this(Type.isValid(type) ? Defaults.get().getChartOptions(type).getPlugins().getOptions(ChartBackgroundColor.ID, ChartBackgroundColor.DEFAULTS_FACTORY) : null);
	}

	/**
	 * Builds new object with default options.
	 * 
	 * @param defaultsOptions defaults options to use to get values
	 */
	ChartBackgroundColorOptions(ChartBackgroundColorDefaultsOptions defaultsOptions) {
		this(null, defaultsOptions);
	}

	/**
	 * Builds the object with a java script object stored into options.
	 * 
	 * @param nativeObject native object into options
	 */
	ChartBackgroundColorOptions(NativeObject nativeObject, ChartBackgroundColorDefaultsOptions defaultsOptions) {
		super(ChartBackgroundColor.ID, nativeObject);
		// checks if there is any default options
		if (defaultsOptions == null) {
			// loads global options
			this.defaultsOptions = loadGlobalsPluginOptions(ChartBackgroundColor.DEFAULTS_FACTORY);
		} else {
			// stores default options
			this.defaultsOptions = defaultsOptions;
		}
	}

	/**
	 * Returns the type of background color has been set.
	 * 
	 * @return the type of background color has been set. Default is {@link ColorType#COLOR}.
	 */
	ColorType getColorType() {
		return getValue(Property.COLOR_TYPE, ColorType.class, defaultsOptions.getColorType());
	}

	/**
	 * Returns the background color. If it has been set a gradient or pattern, returns <code>null</code>, otherwise the default
	 * color, "white".
	 * 
	 * @return the background color. If it has been set a gradient or pattern, returns <code>null</code>, otherwise the default
	 *         color, "white".
	 */
	public String getBackgroundColorAsString() {
		// checks if color has been set
		if (ColorType.COLOR.equals(getColorType())) {
			return getValue(Property.BACKGROUND_COLOR, defaultsOptions.getBackgroundColorAsString());
		}
		// otherwise returns null
		return null;
	}

	/**
	 * Returns the background color. If it has been set a gradient or pattern, returns <code>null</code>, otherwise the default
	 * color, "white".
	 * 
	 * @return the background color. If it has been set a gradient or pattern, returns <code>null</code>, otherwise the default
	 *         color, "white".
	 */
	public IsColor getBackgroundColor() {
		// checks if color has been set
		if (ColorType.COLOR.equals(getColorType())) {
			return ColorBuilder.parse(getBackgroundColorAsString());
		}
		// otherwise returns null
		return null;
	}

	/**
	 * Returns the background gradient. If it has been set a color or pattern, returns <code>null</code>.
	 * 
	 * @return the background gradient. If it has been set a color or pattern, returns <code>null</code>
	 */
	public Gradient getBackgroundColorAsGradient() {
		// checks if gradient has been set
		if (ColorType.GRADIENT.equals(getColorType())) {
			// checks if the gradient has been set into this object or into defaults
			if (has(Property.BACKGROUND_COLOR)) {
				// if here, the gradient has been set into this options
				return ChartBackgroundColor.GRADIENT_FACTORY.create(getValue(Property.BACKGROUND_COLOR));
			} else {
				// if here, the gradient has been set into defaults options
				return defaultsOptions.getBackgroundColorAsGradient();
			}
		}
		// otherwise returns null
		return null;
	}

	/**
	 * Returns the background pattern. If it has been set a color or gradient, returns <code>null</code>.
	 * 
	 * @return the background pattern. If it has been set a color or pattern, returns <code>null</code>
	 */
	public Pattern getBackgroundColorAsPattern() {
		// checks if pattern has been set
		if (ColorType.PATTERN.equals(getColorType())) {
			// checks if the pattern has been set into this object or into defaults
			if (has(Property.BACKGROUND_COLOR)) {
				// if here, the pattern has been set into this options
				return ChartBackgroundColor.PATTERN_FACTORY.create(getValue(Property.BACKGROUND_COLOR));
			} else {
				// if here, the pattern has been set into defaults options
				return defaultsOptions.getBackgroundColorAsPattern();
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
			//removes type
			remove(Property.COLOR_TYPE);
		}
	}

	/**
	 * Sets the background color.
	 * 
	 * @param color the background color.
	 */
	public void setBackgroundColor(IsColor color) {
		setBackgroundColor(checkValue(color));
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
			//removes type
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
			//removes type
			remove(Property.COLOR_TYPE);
		}
	}
}
