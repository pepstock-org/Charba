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
package org.pepstock.charba.client.datalabels.enums;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.datalabels.callbacks.FormatterCallback;

/**
 * The textAlign option only applies to multi-line labels and specifies the text alignment being used when drawing the label text.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 * @see FormatterCallback
 *
 */
public enum TextAlign implements Key
{
	/**
	 * This is the default and the text is left-aligned.
	 */
	START("start"),
	/**
	 * The text is centered.
	 */
	CENTER("center"),
	/**
	 * The text is right-aligned.
	 */
	END("end"),
	/**
	 * Alias of 'start'
	 */
	LEFT("left"),
	/**
	 * alias of 'end'
	 */
	RIGHT("right");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private TextAlign(String value) {
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
