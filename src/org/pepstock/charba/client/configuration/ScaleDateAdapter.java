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
package org.pepstock.charba.client.configuration;

import org.pepstock.charba.client.adapters.DateAdapter;
import org.pepstock.charba.client.adapters.DateAdapterOptions;
import org.pepstock.charba.client.intl.CLocale;
import org.pepstock.charba.client.intl.enums.Calendar;
import org.pepstock.charba.client.intl.enums.NumberingSystem;
import org.pepstock.charba.client.intl.enums.TimeZone;

/**
 * This is abstract date adapter object element of the chart options.<br>
 * 
 * @author Andrea "Stock" Stocchero
 * @see DateAdapterOptions
 */
public final class ScaleDateAdapter extends AxisContainer {

	/**
	 * Builds the object storing the axis which this adapters belongs to.
	 * 
	 * @param axis axis which this adapters belongs to.
	 */
	ScaleDateAdapter(Axis axis) {
		super(axis);
	}

	/**
	 * Returns the {@link DateAdapterOptions} of the scale.
	 * 
	 * @return the {@link DateAdapterOptions} of the scale
	 */
	public DateAdapterOptions getDateAdapterOptions() {
		return getAxis().getScale().getAdapters().getDate().getDateAdapterOptions();
	}

	/**
	 * Creates a {@link DateAdapter} using a clone of this object as options for the date adapter instance.
	 * 
	 * @return a {@link DateAdapter} using a clone of this object as options for the date adapter instance
	 */
	public DateAdapter create() {
		return getAxis().getScale().getAdapters().getDate().create();
	}

	/**
	 * Sets the locale that LUXON must use by the date adapter.<br>
	 * See <a href="https://moment.github.io/luxon/#/intl">here</a> the details about locale in LUXON.
	 * 
	 * @param locale the time zone that LUXON must use by the date adapter
	 */
	public void setLocale(CLocale locale) {
		getAxis().getScale().getAdapters().getDate().setLocale(locale);
	}

	/**
	 * Returns the locale that LUXON must use by the date adapter.<br>
	 * See <a href="https://moment.github.io/luxon/#/intl">here</a> the details about locale in LUXON.
	 * 
	 * @return the locale that LUXON must use by the date adapter
	 */
	public CLocale getLocale() {
		return getAxis().getScale().getAdapters().getDate().getLocale();
	}

	/**
	 * Sets the zone that LUXON must use by the date adapter.<br>
	 * See <a href="https://moment.github.io/luxon/#/zones">here</a> the details about time zone in LUXON.
	 * 
	 * @param zone the zone that LUXON must use by the date adapter
	 */
	public void setZone(TimeZone zone) {
		getAxis().getScale().getAdapters().getDate().setZone(zone);
	}

	/**
	 * Returns the zone that LUXON must use by the date adapter.<br>
	 * See <a href="https://moment.github.io/luxon/#/zones">here</a> the details about time zone in LUXON.
	 * 
	 * @return the zone that LUXON must use by the date adapter
	 */
	public TimeZone getZone() {
		return getAxis().getScale().getAdapters().getDate().getZone();
	}

	/**
	 * Sets the name of calendaring systems that LUXON must use by the date adapter.<br>
	 * See <a href="https://moment.github.io/luxon/#/calendars">here</a> the list of implemented and available. output calendar systems.
	 * 
	 * @param outputCalendar the name of calendaring systems that LUXON must use by the date adapter
	 */
	public void setOutputCalendar(Calendar outputCalendar) {
		getAxis().getScale().getAdapters().getDate().setOutputCalendar(outputCalendar);
	}

	/**
	 * Returns the name of calendaring systems that LUXON must use by the date adapter.<br>
	 * See <a href="https://moment.github.io/luxon/#/calendars">here</a> the list of implemented and available. output calendar systems.
	 * 
	 * @return the name of calendaring systems that LUXON must use by the date adapter
	 */
	public Calendar getOutputCalendar() {
		return getAxis().getScale().getAdapters().getDate().getOutputCalendar();
	}

	/**
	 * Sets the name of numbering systems that LUXON must use by the date adapter.<br>
	 * See <a href="https://moment.github.io/luxon/#/intl">here</a> the details about numbering system in LUXON.
	 * 
	 * @param numberingSystem the name of numbering systems that LUXON must use by the date adapter
	 */
	public void setNumberingSystem(NumberingSystem numberingSystem) {
		getAxis().getScale().getAdapters().getDate().setNumberingSystem(numberingSystem);
	}

	/**
	 * Returns the name of numbering systems that LUXON must use by the date adapter.<br>
	 * See <a href="https://moment.github.io/luxon/#/intl">here</a> the details about numbering system in LUXON.
	 * 
	 * @return the name of numbering systems that LUXON must use by the date adapter
	 */
	public NumberingSystem getNumberingSystem() {
		return getAxis().getScale().getAdapters().getDate().getNumberingSystem();
	}

}