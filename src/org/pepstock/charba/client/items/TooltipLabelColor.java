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

import org.pepstock.charba.client.commons.Key;

/**
 * This object contains the color info when a label into tooltip.<br>
 * It must be used into label tooltip callback.
 * 
 * @author Andrea "Stock" Stocchero
 * @see org.pepstock.charba.client.callbacks.TooltipLabelCallback
 */
public final class TooltipLabelColor extends BaseItem {

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		backgroundColor,
		borderColor
	}

	/**
	 * Needed for GWt injection
	 */
	protected TooltipLabelColor() {
		// do nothing
	}

	/**
	 * Returns the background color of the label.
	 * 
	 * @return the background color of the label.
	 */
	public final String getBackgroundColor() {
		return getString(Property.backgroundColor.name());
	}

	/**
	 * Returns the border color of the label.
	 * 
	 * @return the border color of the label.
	 */
	public final String getBorderColor() {
		return getString(Property.borderColor.name());
	}
}