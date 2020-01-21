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
 * Contains the default date adapter IDs that can be set in CHART.JS, provided out of the box.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum DefaultDateAdapter implements Key
{
	/**
	 * Date adapter ID for <a href="https://momentjs.com/">MOMENT.js</a>.
	 */
	MOMENT("moment"),
	/**
	 * Date adapter ID for <a href="https://moment.github.io/luxon/">Luxon</a>.
	 */
	LUXON("luxon"),
	/**
	 * Date adapter ID for <a href="https://date-fns.org/">Date-fns</a>.
	 */
	DATE_FNS("date-fns");

	// name value of property
	private final String value;

	/**
	 * Creates with the id value of date adapter.
	 * 
	 * @param value the id value of date adapter
	 */
	private DefaultDateAdapter(String value) {
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
