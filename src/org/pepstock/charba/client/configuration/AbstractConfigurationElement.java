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
	 * Returns the element instance to be managed.
	 * 
	 * @return the element instance to be managed
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

	// ------------------------------------------------------
	// HOVER
	// ------------------------------------------------------

	/**
	 * Sets the background color when hovered.
	 * 
	 * @param backgroundColor the background color when hovered.
	 */
	public void setHoverBackgroundColor(IsColor backgroundColor) {
		getElement().setHoverBackgroundColor(backgroundColor);
	}

	/**
	 * Sets the background color when hovered.
	 * 
	 * @param backgroundColor the background color when hovered.
	 */
	public void setHoverBackgroundColor(String backgroundColor) {
		getElement().setHoverBackgroundColor(backgroundColor);
	}

	/**
	 * Returns the background color when hovered.
	 * 
	 * @return the background color when hovered.
	 */
	public String getHoverBackgroundColorAsString() {
		return getElement().getHoverBackgroundColorAsString();
	}

	/**
	 * Returns the background color when hovered.
	 * 
	 * @return the background color when hovered.
	 */
	public IsColor getHoverBackgroundColor() {
		return getElement().getHoverBackgroundColor();
	}

	/**
	 * Sets the border width when hovered.
	 * 
	 * @param borderWidth the border width when hovered.
	 */
	public void setHoverBorderWidth(int borderWidth) {
		getElement().setHoverBorderWidth(borderWidth);
	}

	/**
	 * Returns the border width when hovered.
	 * 
	 * @return the border width when hovered.
	 */
	public int getHoverBorderWidth() {
		return getElement().getHoverBorderWidth();
	}

	/**
	 * Sets the border color when hovered.
	 * 
	 * @param borderColor the border color when hovered.
	 */
	public void setHoverBorderColor(IsColor borderColor) {
		getElement().setHoverBorderColor(borderColor);
	}

	/**
	 * Sets the border color when hovered.
	 * 
	 * @param borderColor the border color when hovered.
	 */
	public void setHoverBorderColor(String borderColor) {
		getElement().setHoverBorderColor(borderColor);
	}

	/**
	 * Returns the border color when hovered.
	 * 
	 * @return the border color when hovered.
	 */
	public String getHoverBorderColorAsString() {
		return getElement().getHoverBorderColorAsString();
	}

	/**
	 * Returns the border color when hovered.
	 * 
	 * @return the border color when hovered.
	 */
	public IsColor getHoverBorderColor() {
		return getElement().getHoverBorderColor();
	}

}