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
package org.pepstock.charba.client.jsinterop.configuration;

import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.jsinterop.options.AbstractElement;
import org.pepstock.charba.client.jsinterop.options.ExtendedOptions;

/**
 * While chart types provide settings to configure the styling of each dataset, you sometimes want to style all datasets the same way.<br>
 * Options can be configured for four different types of elements: arc, lines, points, and rectangles.<br>
 * When set, these options apply to all objects of that type unless specifically overridden by the configuration attached to a dataset.
 * 
 * @author Andrea "Stock" Stocchero
 * @version 2.0
 */
abstract class AbstractConfigurationElement {
	
	// root options
	private final ExtendedOptions options;
	// options element
	private final AbstractElement<?, ?> configuration;

	/***
	 * Builds the object with options, root and element ones.
	 * @param options options instance
	 * @param configuration element instance
	 */
	AbstractConfigurationElement(ExtendedOptions options, AbstractElement<?, ?> configuration) {
		this.options = options;
		this.configuration = configuration;
	}

	/**
	 * Returns the root options.
	 * 
	 * @return the options
	 */
	final ExtendedOptions getOptions() {
		return options;
	}

	/**
	 * Sets the background color.
	 * 
	 * @param backgroundColor the background color.
	 */
	public void setBackgroundColor(IsColor backgroundColor) {
		configuration.setBackgroundColor(backgroundColor);
	}

	/**
	 * Sets the background color.
	 * 
	 * @param backgroundColor the background color.
	 */
	public void setBackgroundColor(String backgroundColor) {
		configuration.setBackgroundColor(backgroundColor);
	}

	/**
	 * Returns the background color.
	 * 
	 * @return the background color.
	 */
	public String getBackgroundColorAsString() {
		return configuration.getBackgroundColorAsString();
	}

	/**
	 * Returns the background color.
	 * 
	 * @return the background color.
	 */
	public IsColor getBackgroundColor() {
		return configuration.getBackgroundColor();
	}

	/**
	 * Sets the border width.
	 * 
	 * @param borderWidth the border width.
	 */
	public void setBorderWidth(int borderWidth) {
		configuration.setBorderWidth(borderWidth);
	}

	/**
	 * Returns the border width.
	 * 
	 * @return the border width.
	 */
	public int getBorderWidth() {
		return configuration.getBorderWidth();
	}

	/**
	 * Sets the border color.
	 * 
	 * @param borderColor the border color.
	 */
	public void setBorderColor(IsColor borderColor) {
		configuration.setBorderColor(borderColor);
	}

	/**
	 * Sets the border color.
	 * 
	 * @param borderColor the border color.
	 */
	public void setBorderColor(String borderColor) {
		configuration.setBorderColor(borderColor);
	}

	/**
	 * Returns the border color.
	 * 
	 * @return the border color.
	 */
	public String getBorderColorAsString() {
		return configuration.getBorderColorAsString();
	}

	/**
	 * Returns the border color.
	 * 
	 * @return the border color.
	 */
	public IsColor getBorderColor() {
		return configuration.getBorderColor();
	}
	
}