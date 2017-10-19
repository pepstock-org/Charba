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
package org.pepstock.charba.client.options.elements;

import org.pepstock.charba.client.commons.JavaScriptObjectContainer;
import org.pepstock.charba.client.commons.Key;

/**
 * 
 */
public class Arc extends JavaScriptObjectContainer{

	private static final String DEFAULT_BACKGROUND_COLOR = "rgba(0,0,0,0.1)";

	private static final int DEFAULT_BORDER_WIDTH = 2;

	private static final String DEFAULT_BORDER_COLOR = "#fff";

	enum Property implements Key {
		backgroundColor,
		borderWidth,
		borderColor,
		borderSkipped
	}
	
	public void setBackgroundColor(String backgroundColor) {
		setValue(Property.backgroundColor, backgroundColor);
	}

	public String getBackgroundColor() {
		return getValue(Property.backgroundColor, DEFAULT_BACKGROUND_COLOR);
	}

	public void setBorderWidth(int borderWidth) {
		setValue(Property.borderWidth, borderWidth);
	}

	public int getBorderWidth() {
		return getValue(Property.borderWidth, DEFAULT_BORDER_WIDTH);
	}

	public void setBorderColor(String borderColor) {
		setValue(Property.borderColor, borderColor);
	}

	public String getBorderColor() {
		return getValue(Property.borderColor, DEFAULT_BORDER_COLOR);
	}

}