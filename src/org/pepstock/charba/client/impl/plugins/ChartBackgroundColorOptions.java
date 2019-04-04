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

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.colors.Pattern;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;

/**
 * Configuration options of {@link ChartBackgroundColor#ID} plugin.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class ChartBackgroundColorOptions extends NativeObjectContainer {

	// pattern factory
	private final Pattern.PatternFactory patternFactory = new Pattern.PatternFactory();
	// gradient factory
	private final Gradient.GradientFactory gradientFactory = new Gradient.GradientFactory();

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
	private enum Property implements Key
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

	/**
	 * Builds the object with new java script object setting the default value of plugin.
	 */
	public ChartBackgroundColorOptions() {
		this(null);
	}

	/**
	 * Builds the object with a java script object stored into options.
	 * 
	 * @param nativeObject native object into options
	 */
	ChartBackgroundColorOptions(NativeObject nativeObject) {
		super(nativeObject);
		// checks if background color exists
		// it could happens only when the object has been created
		// by a native object
		if (!has(Property.BACKGROUND_COLOR)) {
			setBackgroundColor(ChartBackgroundColor.DEFAULT_BACKGROUND_COLOR);
		}
	}

	/**
	 * Returns the type of background color has been set.
	 * 
	 * @return the type of background color has been set. Default is {@link ColorType#COLOR}.
	 */
	ColorType getColorType() {
		return getValue(Property.COLOR_TYPE, ColorType.class, ColorType.COLOR);
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
			return getValue(Property.BACKGROUND_COLOR, ChartBackgroundColor.DEFAULT_BACKGROUND_COLOR);
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
			return gradientFactory.create(getValue(Property.BACKGROUND_COLOR));
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
			return patternFactory.create(getValue(Property.BACKGROUND_COLOR));
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
		// sets the color type
		setValue(Property.COLOR_TYPE, ColorType.COLOR);
	}

	/**
	 * Sets the background color.
	 * 
	 * @param color the background color.
	 */
	public void setBackgroundColor(IsColor color) {
		setBackgroundColor(color.toRGBA());
	}

	/**
	 * Sets the background gradient.
	 * 
	 * @param gradient the background gradient.
	 */
	public void setBackgroundColor(Gradient gradient) {
		setValue(Property.BACKGROUND_COLOR, gradient);
		// sets the color type
		setValue(Property.COLOR_TYPE, ColorType.GRADIENT);
	}

	/**
	 * Sets the background pattern.
	 * 
	 * @param pattern the background pattern.
	 */
	public void setBackgroundColor(Pattern pattern) {
		setValue(Property.BACKGROUND_COLOR, pattern);
		// sets the color type
		setValue(Property.COLOR_TYPE, ColorType.PATTERN);
	}
}
