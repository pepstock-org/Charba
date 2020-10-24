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
package org.pepstock.charba.client.adapters;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.intl.CLocale;
import org.pepstock.charba.client.intl.CLocaleBuilder;
import org.pepstock.charba.client.intl.enums.NumberingSystem;
import org.pepstock.charba.client.items.UndefinedValues;

/**
 * Simple class to map LUXON options to provide to date time adapter.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class LuxonOptions extends DateAdapterOptions {

	/**
	 * Date adapter options factory for LUXON option.<br>
	 * It should be use to get stored LUXON options from global, chart options and configurations.
	 */
	public static final LuxonOptionsFactory FACTORY = new LuxonOptionsFactory();

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		ZONE("zone"),
		LOCALE("locale"),
		SET_ZONE("setZone"),
		OUTPUT_CALENDAR("outputCalendar"),
		NUMBERING_SYSTEM("numberingSystem");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use into native object.
		 * 
		 * @param value value of property name
		 */
		private Property(String value) {
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

	/**
	 * Creates an empty LUXON adapter options.
	 */
	public LuxonOptions() {
	}

	/**
	 * Creates a LUXON adapter options using a native object.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 */
	LuxonOptions(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Sets the zone that LUXON must use by the date adapter.<br>
	 * See <a href="https://moment.github.io/luxon/docs/manual/zones.html">here</a> the details about time zone in LUXON.
	 * 
	 * @param zone the zone that LUXON must use by the date adapter
	 */
	public void setZone(String zone) {
		setValue(Property.ZONE, zone);
	}

	/**
	 * Returns the zone that LUXON must use by the date adapter.<br>
	 * See <a href="https://moment.github.io/luxon/docs/manual/zones.html">here</a> the details about time zone in LUXON.
	 * 
	 * @return the zone that LUXON must use by the date adapter
	 */
	public String getZone() {
		return getValue(Property.ZONE, UndefinedValues.STRING);
	}

	/**
	 * Sets the locale that LUXON must use by the date adapter.<br>
	 * See <a href="https://moment.github.io/luxon/docs/manual/intl.html">here</a> the details about locale in LUXON.
	 * 
	 * @param locale the time zone that LUXON must use by the date adapter
	 */
	public void setLocale(CLocale locale) {
		setValue(Property.LOCALE, locale == null ? null : locale.getIdentifier());
	}

	/**
	 * Returns the locale that LUXON must use by the date adapter.<br>
	 * See <a href="https://moment.github.io/luxon/docs/manual/intl.html">here</a> the details about locale in LUXON.
	 * 
	 * @return the locale that LUXON must use by the date adapter
	 */
	public CLocale getLocale() {
		// gets the value stored as string
		// FIXME serve il default
		String value = getValue(Property.LOCALE, UndefinedValues.STRING);
		// checks if consistent
		return CLocaleBuilder.build(value);
	}

	/**
	 * Sets the name of calendaring systems that LUXON must use by the date adapter.<br>
	 * See <a href="https://moment.github.io/luxon/docs/manual/calendars.html">here</a> the list of implemented and available. output calendar systems.
	 * 
	 * @param outputCalendar the name of calendaring systems that LUXON must use by the date adapter
	 */
	public void setOutputCalendar(String outputCalendar) {
		setValue(Property.OUTPUT_CALENDAR, outputCalendar);
	}

	/**
	 * Returns the name of calendaring systems that LUXON must use by the date adapter.<br>
	 * See <a href="https://moment.github.io/luxon/docs/manual/calendars.html">here</a> the list of implemented and available. output calendar systems.
	 * 
	 * @return the name of calendaring systems that LUXON must use by the date adapter
	 */
	public String getOutputCalendar() {
		return getValue(Property.OUTPUT_CALENDAR, UndefinedValues.STRING);
	}

	/**
	 * Sets the name of numbering systems that LUXON must use by the date adapter.<br>
	 * See <a href="https://moment.github.io/luxon/docs/manual/intl.html">here</a> the details about numbering system in LUXON.
	 * 
	 * @param numberingSystem the name of numbering systems that LUXON must use by the date adapter
	 */
	public void setNumberingSystem(NumberingSystem numberingSystem) {
		setValue(Property.NUMBERING_SYSTEM, numberingSystem);
	}

	/**
	 * Returns the name of numbering systems that LUXON must use by the date adapter.<br>
	 * See <a href="https://moment.github.io/luxon/docs/manual/intl.html">here</a> the details about numbering system in LUXON.
	 * 
	 * @return the name of numbering systems that LUXON must use by the date adapter
	 */
	public NumberingSystem getNumberingSystem() {
		// FIXME serve il default
		return getValue(Property.NUMBERING_SYSTEM, NumberingSystem.values(), NumberingSystem.LATN);
	}

	/**
	 * Date adapter options factory for LUXON option.<br>
	 * It should be use to get stored LUXON options from global, chart options and configurations.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	public static class LuxonOptionsFactory implements DateAdaptersOptionsFactory<LuxonOptions> {

		/**
		 * To avoid any instantiation
		 */
		private LuxonOptionsFactory() {
			// do nothing
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		public LuxonOptions create(NativeObject nativeObject) {
			return new LuxonOptions(nativeObject);
		}

	}
}
