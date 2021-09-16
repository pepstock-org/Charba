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
package org.pepstock.charba.client.utils.toast.enums;

import org.pepstock.charba.client.commons.Key;

/**
 * Enumerates the list of progress bar type for toasting.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum ProgressBarType implements Key
{
	/**
	 * Default toast type.
	 */
	DEFAULT("default"),
	/**
	 * Success toast type, in green.
	 */
	SUCCESS("success"),
	/**
	 * Success toast type, in amber.
	 */
	WARNING("warning"),
	/**
	 * Error toast type, in red.
	 */
	ERROR("error"),
	/**
	 * Info toast type, in red.
	 */
	INFO("info"),
	/**
	 * Rainbow toast type, in red.
	 */
	RAINBOW("rainbow");

	// name value of property
	private final String value;

	/**
	 * Creates the progress bar type with its property name to use in the options.
	 * 
	 * @param value value to use inside the native object as name of property
	 */
	private ProgressBarType(String value) {
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