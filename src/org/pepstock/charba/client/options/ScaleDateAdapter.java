/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.adapters.DateAdapter;
import org.pepstock.charba.client.adapters.DateAdapterOptions;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultDateAdapterOptions;
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
public final class ScaleDateAdapter extends AbstractModel<Adapters, IsDefaultDateAdapterOptions> implements IsDefaultDateAdapterOptions {

	// date adapter options instance
	private final DateAdapterOptions dateAdapterOptions;

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param model options model of the chart.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	ScaleDateAdapter(Adapters model, Key childKey, IsDefaultDateAdapterOptions defaultValues, NativeObject nativeObject) {
		super(model, childKey, defaultValues, nativeObject);
		// creates the number format option
		this.dateAdapterOptions = DateAdapterOptions.FACTORY.create(getNativeObject(), getDefaultValues());
	}

	/**
	 * Returns the {@link DateAdapterOptions} of the scale.
	 * 
	 * @return the {@link DateAdapterOptions} of the scale
	 */
	public DateAdapterOptions getDateAdapterOptions() {
		return dateAdapterOptions;
	}

	/**
	 * Creates a {@link DateAdapter} using a clone of this object as options for the date adapter instance.
	 * 
	 * @return a {@link DateAdapter} using a clone of this object as options for the date adapter instance
	 */
	public DateAdapter create() {
		return dateAdapterOptions.create();
	}

	/**
	 * Sets the locale that LUXON must use by the date adapter.<br>
	 * See <a href="https://moment.github.io/luxon/#/intl">here</a> the details about locale in LUXON.
	 * 
	 * @param locale the time zone that LUXON must use by the date adapter
	 */
	public void setLocale(CLocale locale) {
		dateAdapterOptions.setLocale(locale);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the locale that LUXON must use by the date adapter.<br>
	 * See <a href="https://moment.github.io/luxon/#/intl">here</a> the details about locale in LUXON.
	 * 
	 * @return the locale that LUXON must use by the date adapter
	 */
	@Override
	public CLocale getLocale() {
		return dateAdapterOptions.getLocale();
	}

	/**
	 * Sets the zone that LUXON must use by the date adapter.<br>
	 * See <a href="https://moment.github.io/luxon/#/zones">here</a> the details about time zone in LUXON.
	 * 
	 * @param zone the zone that LUXON must use by the date adapter
	 */
	public void setZone(TimeZone zone) {
		dateAdapterOptions.setZone(zone);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the zone that LUXON must use by the date adapter.<br>
	 * See <a href="https://moment.github.io/luxon/#/zones">here</a> the details about time zone in LUXON.
	 * 
	 * @return the zone that LUXON must use by the date adapter
	 */
	@Override
	public TimeZone getZone() {
		return dateAdapterOptions.getZone();
	}

	/**
	 * Sets the name of calendaring systems that LUXON must use by the date adapter.<br>
	 * See <a href="https://moment.github.io/luxon/#/calendars">here</a> the list of implemented and available. output calendar systems.
	 * 
	 * @param outputCalendar the name of calendaring systems that LUXON must use by the date adapter
	 */
	public void setOutputCalendar(Calendar outputCalendar) {
		dateAdapterOptions.setOutputCalendar(outputCalendar);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the name of calendaring systems that LUXON must use by the date adapter.<br>
	 * See <a href="https://moment.github.io/luxon/#/calendars">here</a> the list of implemented and available. output calendar systems.
	 * 
	 * @return the name of calendaring systems that LUXON must use by the date adapter
	 */
	@Override
	public Calendar getOutputCalendar() {
		return dateAdapterOptions.getOutputCalendar();
	}

	/**
	 * Sets the name of numbering systems that LUXON must use by the date adapter.<br>
	 * See <a href="https://moment.github.io/luxon/#/intl">here</a> the details about numbering system in LUXON.
	 * 
	 * @param numberingSystem the name of numbering systems that LUXON must use by the date adapter
	 */
	public void setNumberingSystem(NumberingSystem numberingSystem) {
		dateAdapterOptions.setNumberingSystem(numberingSystem);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the name of numbering systems that LUXON must use by the date adapter.<br>
	 * See <a href="https://moment.github.io/luxon/#/intl">here</a> the details about numbering system in LUXON.
	 * 
	 * @return the name of numbering systems that LUXON must use by the date adapter
	 */
	@Override
	public NumberingSystem getNumberingSystem() {
		return dateAdapterOptions.getNumberingSystem();
	}

}