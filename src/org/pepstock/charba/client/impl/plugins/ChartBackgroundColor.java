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

import org.pepstock.charba.client.Plugin;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.HtmlColor;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.colors.Pattern;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.impl.plugins.ChartBackgroundColorOptionsFactory.ChartBackgroundColorDefaultsOptionsFactory;

/**
 * A plugin implementation to set the background color, gradient or pattern of chart.<br>
 * If added to defaults, without any configuration, the chart will have a {@link ChartBackgroundColor#DEFAULT_BACKGROUND_COLOR} background color.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ChartBackgroundColor extends CharbaPluginContainer {

	/**
	 * Plugin ID <b>{@value ID}</b>.
	 */
	public static final String ID = "charbabackgroundcolor";
	/**
	 * The factory to create options
	 */
	public static final ChartBackgroundColorOptionsFactory FACTORY = new ChartBackgroundColorOptionsFactory();
	/**
	 * Default background color, {@link HtmlColor#WHITE}.
	 */
	public static final String DEFAULT_BACKGROUND_COLOR = HtmlColor.WHITE.toRGBA();
	// defaults options factory instance
	static final ChartBackgroundColorDefaultsOptionsFactory DEFAULTS_FACTORY = new ChartBackgroundColorDefaultsOptionsFactory();
	// instance of the plugin
	private final ChartBackgroundColorPlugin pluginInstance;
	// color instance
	private final String color;
	// gradient instance
	private final Gradient gradient;
	// pattern instance
	private final Pattern pattern;

	/**
	 * Default constructor with {@link ChartBackgroundColor#DEFAULT_BACKGROUND_COLOR} background color.
	 */
	public ChartBackgroundColor() {
		this(DEFAULT_BACKGROUND_COLOR);
	}

	/**
	 * Builds the object with the default background color for all charts.
	 * 
	 * @param color background default color for all charts.
	 */
	public ChartBackgroundColor(IsColor color) {
		this(IsColor.isConsistent(color) ? color.toRGBA() : DEFAULT_BACKGROUND_COLOR);
	}

	/**
	 * Builds the object with the default background color for all charts.
	 * 
	 * @param color background default color for all charts.
	 */
	public ChartBackgroundColor(String color) {
		// sets default color if null
		this.color = (color != null) ? color : DEFAULT_BACKGROUND_COLOR;
		// sets null other kind of background
		this.pattern = null;
		this.gradient = null;
		// creates pluign instance
		this.pluginInstance = new ChartBackgroundColorPlugin(this);
	}

	/**
	 * Builds the object with the default gradient for all charts.
	 * 
	 * @param gradient background default gradient for all charts.
	 */
	public ChartBackgroundColor(Gradient gradient) {
		// checks if gradient is consistent
		// sets gradient
		this.gradient = Checker.checkAndGetIfValid(gradient, "Gradient argument");
		// sets null other kind of background
		this.color = null;
		this.pattern = null;
		// creates pluign instance
		this.pluginInstance = new ChartBackgroundColorPlugin(this);
	}

	/**
	 * Builds the object with the default pattern for all charts.
	 * 
	 * @param pattern background default pattern for all charts.
	 */
	public ChartBackgroundColor(Pattern pattern) {
		// checks if pattern is consistent
		// sets gradient
		this.pattern = Checker.checkAndGetIfValid(pattern, "Pattern argument");
		// sets null other kind of background
		this.color = null;
		this.gradient = null;
		// creates pluign instance
		this.pluginInstance = new ChartBackgroundColorPlugin(this);
	}

	/**
	 * Returns the color as string if it has been set, otherwise <code>null</code>.
	 * 
	 * @return the color as string if it has been set, otherwise <code>null</code>.
	 */
	public String getColorAsString() {
		return color;
	}

	/**
	 * Returns the color if it has been set, otherwise <code>null</code>.
	 * 
	 * @return the color if it has been set, otherwise <code>null</code>
	 */
	public IsColor getColor() {
		// checks if color has been set
		if (getColorAsString() != null) {
			// returns color
			return ColorBuilder.parse(getColorAsString());
		}
		// otherwise null
		return null;
	}

	/**
	 * Returns the gradient if it has been set, otherwise <code>null</code>.
	 * 
	 * @return the gradient if it has been set, otherwise <code>null</code>
	 */
	public Gradient getGradient() {
		return gradient;
	}

	/**
	 * Returns the pattern if it has been set, otherwise <code>null</code>.
	 * 
	 * @return the pattern if it has been set, otherwise <code>null</code>
	 */
	public Pattern getPattern() {
		return pattern;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.impl.plugins.CharbaPluginContainer#getPluginInstance()
	 */
	@Override
	Plugin getPluginInstance() {
		return pluginInstance;
	}

}
