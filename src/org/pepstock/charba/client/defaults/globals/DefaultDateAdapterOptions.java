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
package org.pepstock.charba.client.defaults.globals;

import org.pepstock.charba.client.defaults.IsDefaultDateAdapterOptions;
import org.pepstock.charba.client.intl.CLocale;
import org.pepstock.charba.client.intl.enums.Calendar;
import org.pepstock.charba.client.intl.enums.NumberingSystem;
import org.pepstock.charba.client.intl.enums.TimeZone;

/**
 * CHART.JS default values for DATE ADPATER options element.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultDateAdapterOptions implements IsDefaultDateAdapterOptions {

	/**
	 * Immutable instance with the date adapter options defaults.
	 */
	public static final IsDefaultDateAdapterOptions INSTANCE = new DefaultDateAdapterOptions();

	private static final CLocale DEFAULT_LOCALE = null;

	private static final TimeZone DEFAULT_ZONE = null;

	private static final Calendar DEFAULT_OUTPUT_CALENDAR = null;

	private static final NumberingSystem DEFAULT_NUMBERING_SYSTEM = null;

	/**
	 * To avoid any instantiation
	 */
	DefaultDateAdapterOptions() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultDateAdapterOptions#getLocale()
	 */
	@Override
	public CLocale getLocale() {
		return DEFAULT_LOCALE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultDateAdapterOptions#getZone()
	 */
	@Override
	public TimeZone getZone() {
		return DEFAULT_ZONE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultDateAdapterOptions#getOutputCalendar()
	 */
	@Override
	public Calendar getOutputCalendar() {
		return DEFAULT_OUTPUT_CALENDAR;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultDateAdapterOptions#getNumberingSystem()
	 */
	@Override
	public NumberingSystem getNumberingSystem() {
		return DEFAULT_NUMBERING_SYSTEM;
	}

}