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
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;

/**
 * Configuration options of background color plugin.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class ChartBackgroundColorOptions extends NativeObjectContainer {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		backgroundColor
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
	 * Returns the background color.
	 * 
	 * @return the background color. Default is "white".
	 */
	public String getBackgroundColorAsString() {
		return getValue(Property.backgroundColor, ChartBackgroundColor.DEFAULT_BACKGROUND_COLOR);
	}

	/**
	 * Returns the background color.
	 * 
	 * @return the background color.
	 */
	public IsColor getBackgroundColor() {
		return ColorBuilder.parse(getBackgroundColorAsString());
	}

	/**
	 * Sets the background color.
	 * 
	 * @param color the background color.
	 */
	public void setBackgroundColor(String color) {
		setValue(Property.backgroundColor, color);
	}

	/**
	 * Sets the background color.
	 * 
	 * @param color the background color.
	 */
	public void setBackgroundColor(IsColor color) {
		setBackgroundColor(color.toRGBA());
	}
}
