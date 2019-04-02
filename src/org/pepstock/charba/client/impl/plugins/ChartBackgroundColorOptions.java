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
	 * Name of properties of native object.
	 */
	enum ColorType implements Key
	{
		color,
		pattern,
		gradient
	}

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		backgroundColor,
		colorType
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
		if (!has(Property.backgroundColor)) {
			setBackgroundColor(ChartBackgroundColor.DEFAULT_BACKGROUND_COLOR);
		}
	}

	/**
	 * Returns the type of background color has been set.
	 * 
	 * @return the type of background color has been set. Default is {@link ColorType#color}.
	 */
	ColorType getColorType() {
		return getValue(Property.colorType, ColorType.class, ColorType.color);
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
		if (ColorType.color.equals(getColorType())) {
			return getValue(Property.backgroundColor, ChartBackgroundColor.DEFAULT_BACKGROUND_COLOR);
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
		if (ColorType.color.equals(getColorType())) {
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
		if (ColorType.gradient.equals(getColorType())) {
			return gradientFactory.create(getValue(Property.backgroundColor));
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
		if (ColorType.pattern.equals(getColorType())) {
			return patternFactory.create(getValue(Property.backgroundColor));
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
		setValue(Property.backgroundColor, color);
		// sets the color type
		setValue(Property.colorType, ColorType.color);
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
		setValue(Property.backgroundColor, gradient);
		// sets the color type
		setValue(Property.colorType, ColorType.gradient);
	}

	/**
	 * Sets the background pattern.
	 * 
	 * @param pattern the background pattern.
	 */
	public void setBackgroundColor(Pattern pattern) {
		setValue(Property.backgroundColor, pattern);
		// sets the color type
		setValue(Property.colorType, ColorType.pattern);
	}
}
