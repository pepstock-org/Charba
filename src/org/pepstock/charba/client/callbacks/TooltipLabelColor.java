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
package org.pepstock.charba.client.callbacks;

import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.JavaScriptObjectContainer;
import org.pepstock.charba.client.commons.Key;

/**
 * Item which contains the colors to apply to tooltip labels.
 * 
 * @author Andrea "Stock" Stocchero
 * @see org.pepstock.charba.client.callbacks.TooltipLabelColor
 */
public final class TooltipLabelColor extends JavaScriptObjectContainer {

	// default background color
	private static final String DEFAULT_BACKGROUND_COLOR = "rgba(0,0,0,0.8)";
	// default border color
	private static final String DEFAULT_BORDER_COLOR = "rgba(0,0,0,0)";

	/**
	 * List of fields name of JavaScript object
	 */
	private enum Property implements Key
	{
		backgroundColor,
		borderColor
	}

	/**
	 * Needed for GWt injection.
	 */
	public TooltipLabelColor() {
		// do nothing
	}

	/**
	 * Returns the background color
	 * 
	 * @return the background color
	 */
	public final String getBackgroundColor() {
		return getValue(Property.backgroundColor, DEFAULT_BACKGROUND_COLOR);
	}

	/**
	 * Sets background color
	 * 
	 * @param backgroundColor background color
	 */
	public void setBackgroundColor(String backgroundColor) {
		setValue(Property.backgroundColor, backgroundColor);
	}

	/**
	 * Returns the border color
	 * 
	 * @return the border color
	 */
	public final String getBorderColor() {
		return getValue(Property.borderColor, DEFAULT_BORDER_COLOR);
	}

	/**
	 * Sets border color
	 * 
	 * @param boderColor border color
	 */
	public void setBorderColor(String borderColor) {
		setValue(Property.borderColor, borderColor);
	}

	/**
	 * Returns the JavaScript object with properties
	 * 
	 * @return the JavaScript object with properties
	 * @see org.pepstock.charba.client.commons.GenericJavaScriptObject
	 */
	public final GenericJavaScriptObject getObject() {
		return getJavaScriptObject();
	}

	/**
	 * String representation of this object
	 */
	public String toString() {
		return "TooltipLabelColor [getBackgroundColor()=" + getBackgroundColor() + ", getBorderColor()=" + getBorderColor() + "]";
	}
}