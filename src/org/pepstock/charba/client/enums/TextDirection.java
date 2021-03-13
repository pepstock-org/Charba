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
package org.pepstock.charba.client.enums;

import org.pepstock.charba.client.commons.Key;

/**
 * Enumerates the text direction that will force the text direction on the canvas for rendering the legend, regardless of the CSS specified on the canvas.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum TextDirection implements Key
{
	/**
	 * Left-to-right direction.
	 */
	LEFT_TO_RIGHT("ltr"),
	/**
	 * Right-to-left direction.
	 */
	RIGHT_TO_LEFT("rtl");

	// internal value to use in the CodeMirror configuration
	private final String value;

	/**
	 * Creates the enumeration by its value.
	 * 
	 * @param value string value to use in the CodeMirror configuration
	 */
	private TextDirection(String value) {
		this.value = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.Key#value()
	 */
	@Override
	public String value() {
		return value;
	}

}
