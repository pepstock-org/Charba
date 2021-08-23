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
package org.pepstock.charba.client.defaults;

import org.pepstock.charba.client.intl.CLocale;
import org.pepstock.charba.client.intl.enums.Calendar;
import org.pepstock.charba.client.intl.enums.NumberingSystem;
import org.pepstock.charba.client.intl.enums.TimeZone;

/**
 * Interface to define date adapter options defaults.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsDefaultDateAdapterOptions {

	/**
	 * Returns the locale that LUXON must use by the date adapter.<br>
	 * See <a href="https://moment.github.io/luxon/#/intl">here</a> the details about locale in LUXON.
	 * 
	 * @return the locale that LUXON must use by the date adapter
	 */
	CLocale getLocale();

	/**
	 * Returns the zone that LUXON must use by the date adapter.<br>
	 * See <a href="https://moment.github.io/luxon/#/zones">here</a> the details about time zone in LUXON.
	 * 
	 * @return the zone that LUXON must use by the date adapter
	 */
	TimeZone getZone();

	/**
	 * Returns the name of calendaring systems that LUXON must use by the date adapter.<br>
	 * See <a href="https://moment.github.io/luxon/#/calendars">here</a> the list of implemented and available. output calendar systems.
	 * 
	 * @return the name of calendaring systems that LUXON must use by the date adapter
	 */
	Calendar getOutputCalendar();

	/**
	 * Returns the name of numbering systems that LUXON must use by the date adapter.<br>
	 * See <a href="https://moment.github.io/luxon/#/intl">here</a> the details about numbering system in LUXON.
	 * 
	 * @return the name of numbering systems that LUXON must use by the date adapter
	 */
	NumberingSystem getNumberingSystem();
}