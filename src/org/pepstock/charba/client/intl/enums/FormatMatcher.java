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
package org.pepstock.charba.client.intl.enums;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.intl.DateTimeFormatOptions;

/**
 * The list of formats matching algorithm to use.<br>
 * The following properties describe the date-time components to use in formatted output, and their desired representations.<br>
 * Implementations are required to support at least the following subsets:<br>
 * <ul>
 * <li>weekday, year, month, day, hour, minute, second
 * <li>weekday, year, month, day
 * <li>year, month, day
 * <li>year, month
 * <li>month, day
 * <li>hour, minute, second
 * <li>hour, minute
 * </ul>
 * Implementations may support other subsets, and requests will be negotiated against all available subset-representation combinations to find the best match.<br>
 * Two algorithms are available for this negotiation and selected by the {@link DateTimeFormatOptions#setFormatMatcher(org.pepstock.charba.client.intl.enums.FormatMatcher)}
 * property.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum FormatMatcher implements Key
{
	/**
	 * Basic algorithm to get the format. See <a href="http://www.ecma-international.org/ecma-402/1.0/#BasicFormatMatcher">Ecma specification</a> for more details.
	 */
	BASIC("basic"),
	/**
	 * Lets the runtime provide a locale that's at least, but possibly more, suited for the request than the result of the basic algorithm.
	 */
	BEST_FIT("best fit");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private FormatMatcher(String value) {
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
