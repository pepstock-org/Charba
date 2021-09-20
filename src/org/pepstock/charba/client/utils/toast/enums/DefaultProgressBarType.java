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

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.utils.toast.IsProgressBarType;

/**
 * Enumerates the list of progress bar type for toasting.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum DefaultProgressBarType implements IsProgressBarType
{
	/**
	 * Default toast type.
	 */
	DEFAULT("default", ColorBuilder.parse("#C0C0C0")),
	/**
	 * Success toast type, in green.
	 */
	SUCCESS("success", ColorBuilder.parse("#51C625")),
	/**
	 * Success toast type, in amber.
	 */
	WARNING("warning", ColorBuilder.parse("#DB9215")),
	/**
	 * Error toast type, in red.
	 */
	ERROR("error", ColorBuilder.parse("#DB2B1D")),
	/**
	 * Info toast type, in red.
	 */
	INFO("info", ColorBuilder.parse("#27ABDB")),
	/**
	 * Rainbow toast type, in red.
	 */
	RAINBOW("rainbow", null);

	// name value of property
	private final String value;
	// type color instance
	private final IsColor backgroundColor;

	/**
	 * Creates the progress bar type with its property name to use in the options.
	 * 
	 * @param value value to use inside the native object as name of property
	 * @param backgroundColor background color of toast progress bar
	 */
	private DefaultProgressBarType(String value, IsColor backgroundColor) {
		this.value = value;
		this.backgroundColor = backgroundColor;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.utils.toast.IsProgressBarType#getBackgroundColor()
	 */
	@Override
	public IsColor getBackgroundColor() {
		return backgroundColor;
	}

}