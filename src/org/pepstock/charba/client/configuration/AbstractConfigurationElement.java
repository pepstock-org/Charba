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

import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.defaults.IsDefaultOptionsElement;
import org.pepstock.charba.client.options.AbstractElement;

/**
 * While chart types provide settings to configure the styling of each dataset, you sometimes want to style all datasets the same way.<br>
 * Options can be configured for four different types of elements: arc, lines, points, and bars.<br>
 * When set, these options apply to all objects of that type unless specifically overridden by the configuration attached to a dataset.
 * 
 * @author Andrea "Stock" Stocchero
 */
abstract class AbstractConfigurationElement<D extends IsDefaultOptionsElement> extends ConfigurationOptionsContainer {

	/***
	 * Builds the object with options.
	 * 
	 * @param options options instance
	 */
	AbstractConfigurationElement(ConfigurationOptions options) {
		super(options);
	}

	/**
	 * FIXME
	 * 
	 * @return
	 */
	protected abstract AbstractElement<D> getElement();

	/**
	 * Sets the background color.
	 * 
	 * @param backgroundColor the background color.
	 */
	public void setBackgroundColor(IsColor backgroundColor) {
		getElement().setBackgroundColor(backgroundColor);
	}

	/**
	 * Sets the background color.
	 * 
	 * @param backgroundColor the background color.
	 */
	public void setBackgroundColor(String backgroundColor) {
		getElement().setBackgroundColor(backgroundColor);
	}

	/**
	 * Returns the background color.
	 * 
	 * @return the background color.
	 */
	public String getBackgroundColorAsString() {
		return getElement().getBackgroundColorAsString();
	}

	/**
	 * Returns the background color.
	 * 
	 * @return the background color.
	 */
	public IsColor getBackgroundColor() {
		return getElement().getBackgroundColor();
	}

	/**
	 * Sets the border width.
	 * 
	 * @param borderWidth the border width.
	 */
	public void setBorderWidth(int borderWidth) {
		getElement().setBorderWidth(borderWidth);
	}

	/**
	 * Returns the border width.
	 * 
	 * @return the border width.
	 */
	public int getBorderWidth() {
		return getElement().getBorderWidth();
	}

	/**
	 * Sets the border color.
	 * 
	 * @param borderColor the border color.
	 */
	public void setBorderColor(IsColor borderColor) {
		getElement().setBorderColor(borderColor);
	}

	/**
	 * Sets the border color.
	 * 
	 * @param borderColor the border color.
	 */
	public void setBorderColor(String borderColor) {
		getElement().setBorderColor(borderColor);
	}

	/**
	 * Returns the border color.
	 * 
	 * @return the border color.
	 */
	public String getBorderColorAsString() {
		return getElement().getBorderColorAsString();
	}

	/**
	 * Returns the border color.
	 * 
	 * @return the border color.
	 */
	public IsColor getBorderColor() {
		return getElement().getBorderColor();
	}

}