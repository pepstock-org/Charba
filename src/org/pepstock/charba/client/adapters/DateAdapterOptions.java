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
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.defaults.IsDefaultDateAdapterOptions;
import org.pepstock.charba.client.defaults.globals.DefaultDateAdapterOptions;
import org.pepstock.charba.client.intl.CLocale;
import org.pepstock.charba.client.intl.CLocaleBuilder;
import org.pepstock.charba.client.intl.enums.Calendar;
import org.pepstock.charba.client.intl.enums.NumberingSystem;
import org.pepstock.charba.client.intl.enums.TimeZone;
import org.pepstock.charba.client.items.UndefinedValues;

/**
 * Simple class to map LUXON options to provide to date time adapter.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DateAdapterOptions extends NativeObjectContainer {

	/**
	 * Date adapter options factory for LUXON option.<br>
	 * It should be use to get stored LUXON options from global, chart options and configurations.
	 */
	public static final DateAdaptersOptionsFactory FACTORY = new DateAdaptersOptionsFactory();

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
	
	// default values instance
	private final IsDefaultDateAdapterOptions defaultValues;

	/**
	 * Creates the object with an empty native object instance.
	 */
	public DateAdapterOptions() {
		this(null, DefaultDateAdapterOptions.INSTANCE);
	}

	/**
	 * Creates the options by a native object and a default values instance.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 * @param defaultValues default values for the options
	 */
	DateAdapterOptions(NativeObject nativeObject, IsDefaultDateAdapterOptions defaultValues) {
		super(nativeObject);
		// checks if default value is consistent
		checkDefaultValuesArgument(defaultValues);
		// stores default
		this.defaultValues = defaultValues;
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
		// gets the default value
		CLocale defaultLocale = defaultValues.getLocale();
		// gets the value stored as string
		String value = getValue(Property.LOCALE, defaultLocale == null ? UndefinedValues.STRING : defaultLocale.getIdentifier());
		// checks if value is consistent
		// if default is null or 
		// the identifier is not equals to value
		if (value != null && (defaultLocale == null || !defaultLocale.getIdentifier().equalsIgnoreCase(value))) {
			// creates the locale
			return CLocaleBuilder.build(value);

		}
		// if here, returns the default value
		// which can be null as well
		return defaultLocale;
	}

	/**
	 * Returns <code>true</code> if the options are containing the locale property.
	 * 
	 * @return <code>true</code> if the options are containing the locale property
	 */
	boolean hasLocale() {
		return isType(Property.LOCALE, ObjectType.STRING);
	}

	/**
	 * Sets the zone that LUXON must use by the date adapter.<br>
	 * See <a href="https://moment.github.io/luxon/docs/manual/zones.html">here</a> the details about time zone in LUXON.
	 * 
	 * @param zone the zone that LUXON must use by the date adapter
	 */
	public void setZone(TimeZone zone) {
		setValue(Property.ZONE, zone);
	}

	/**
	 * Returns the zone that LUXON must use by the date adapter.<br>
	 * See <a href="https://moment.github.io/luxon/docs/manual/zones.html">here</a> the details about time zone in LUXON.
	 * 
	 * @return the zone that LUXON must use by the date adapter
	 */
	public TimeZone getZone() {
		return getValue(Property.ZONE, TimeZone.values(), defaultValues.getZone());
	}

	/**
	 * Sets the name of calendaring systems that LUXON must use by the date adapter.<br>
	 * See <a href="https://moment.github.io/luxon/docs/manual/calendars.html">here</a> the list of implemented and available. output calendar systems.
	 * 
	 * @param outputCalendar the name of calendaring systems that LUXON must use by the date adapter
	 */
	public void setOutputCalendar(Calendar outputCalendar) {
		setValue(Property.OUTPUT_CALENDAR, outputCalendar);
	}

	/**
	 * Returns the name of calendaring systems that LUXON must use by the date adapter.<br>
	 * See <a href="https://moment.github.io/luxon/docs/manual/calendars.html">here</a> the list of implemented and available. output calendar systems.
	 * 
	 * @return the name of calendaring systems that LUXON must use by the date adapter
	 */
	public Calendar getOutputCalendar() {
		return getValue(Property.OUTPUT_CALENDAR, Calendar.values(), defaultValues.getOutputCalendar());
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
		return getValue(Property.NUMBERING_SYSTEM, NumberingSystem.values(), defaultValues.getNumberingSystem());
	}

	/**
	 * Date adapter options factory for LUXON option.<br>
	 * It should be use to get stored LUXON options from global, chart options and configurations.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	public static class DateAdaptersOptionsFactory implements NativeObjectContainerFactory<DateAdapterOptions> {

		/**
		 * To avoid any instantiation
		 */
		private DateAdaptersOptionsFactory() {
			// do nothing
		}
		
		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		public DateAdapterOptions create(NativeObject nativeObject) {
			return create(nativeObject, null);
		}

		/**
		 * Creates a date adapter options by a native object and a default values instance.
		 * 
		 * @param nativeObject native object instance to be wrapped.
		 * @param defaultValues default values for the options
		 * @return a date adapter options instance
		 */
		public DateAdapterOptions create(NativeObject nativeObject, IsDefaultDateAdapterOptions defaultValues) {
			return new DateAdapterOptions(nativeObject, defaultValues == null ? DefaultDateAdapterOptions.INSTANCE : defaultValues);
		}

	}

	/**
	 * Returns the native object instance.
	 * 
	 * @return the native object instance.
	 */
	NativeObject nativeObject() {
		return super.getNativeObject();
	}

}
