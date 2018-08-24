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
package org.pepstock.charba.client.items;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.JavaScriptObjectContainer;
import org.pepstock.charba.client.commons.Key;

/**
 * This object contains the color info when a label into tooltip.<br>
 * It must be used into label tooltip callback.
 * 
 * @author Andrea "Stock" Stocchero
 * @see org.pepstock.charba.client.callbacks.TooltipLabelCallback
 */
public final class TooltipLabelColor extends JavaScriptObjectContainer {

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		backgroundColor,
		borderColor
	}

	/**
	 * Wraps the CHART.JS java script object.
	 * 
	 * @param javaScriptObject CHART.JS java script object
	 */
	TooltipLabelColor(GenericJavaScriptObject javaScriptObject) {
		super(javaScriptObject);
	}

	/**
	 * Returns the background color of the label.
	 * 
	 * @return the background color of the label.
	 */
	public String getBackgroundColorAsString() {
		return getValue(Property.backgroundColor, Defaults.getGlobal().getTooltips().getBackgroundColorAsString());
	}

	/**
	 * Returns the background color of the label.
	 * 
	 * @return the background color of the label.
	 */
	public IsColor getBackgroundColor() {
		return ColorBuilder.parse(getBackgroundColorAsString());
	}

	/**
	 * Returns the border color of the label.
	 * 
	 * @return the border color of the label.
	 */
	public String getBorderColorAsString() {
		return getValue(Property.borderColor,  Defaults.getGlobal().getTooltips().getBorderColorAsString());
	}
	
	/**
	 * Returns the border color of the label.
	 * 
	 * @return the border color of the label.
	 */
	public IsColor getBorderColor() {
		return ColorBuilder.parse(getBorderColorAsString());
	}

}