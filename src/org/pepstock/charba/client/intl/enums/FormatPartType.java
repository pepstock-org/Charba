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
package org.pepstock.charba.client.intl.enums;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.intl.FormatPart;

/**
 * Enumerates the possible types of a {@link FormatPart} can have.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum FormatPartType implements Key
{
	/**
	 * The currency string, such as the symbols "$" and "€" or the name "Dollar", "Euro" depending on how currencyDisplay is specified.
	 */
	CURRENCY("currency"),
	/**
	 * The decimal separator string (".").
	 */
	DECIMAL("decimal"),
	/**
	 * The fraction number.
	 */
	FRACTION("fraction"),
	/**
	 * The group separator string (",").
	 */
	GROUP("group"),
	/**
	 * The Infinity string ("∞").
	 */
	INFINITY("infinity"),
	/**
	 * The integer number.
	 */
	INTEGER("integer"),
	/**
	 * Any literal strings or whitespace in the formatted number or the string used for separating date and time values, for example "/", ",", "o'clock", "de", etc.
	 */
	LITERAL("literal"),
	/**
	 * The minus sign string ("-").
	 */
	MINUS_SIGN("minusSign"),
	/**
	 * The {@link Double#NaN} string ("NaN").
	 */
	NAN("nan"),
	/**
	 * The plus sign string ("+").
	 */
	PLUS_SIGN("plusSign"),
	/**
	 * The percent sign string ("%").
	 */
	PERCENT_SIGN("percentSign"),
	/**
	 * The unit string.
	 */
	UNIT("unit"),
	/**
	 * The string used for the day, for example "17".
	 */
	DAY("day"),
	/**
	 * The string used for the day period, for example, "AM", "PM", "in the morning", or "noon".
	 */
	DAY_PERIOD("dayPeriod"),
	/**
	 * The string used for the era, for example "BC" or "AD".
	 */
	ERA("era"),
	/**
	 * The string used for the fractional seconds, for example "0" or "00" or "000".
	 */
	FRACTIONAL_SECOND("fractionalSecond"),
	/**
	 * The string used for the hour, for example "3" or "03".
	 */
	HOUR("hour"),
	/**
	 * The string used for the minute, for example "00".
	 */
	MINUTE("minute"),
	/**
	 * The string used for the month, for example "12".
	 */
	MONTH("month"),
	/**
	 * The string used for the related 4-digit Gregorian year, in the event that the calendar's representation would be a yearName instead of a year, for example "2019".
	 */
	RELATED_YEAR("relatedYear"),
	/**
	 * The string used for the second, for example "07" or "42".
	 */
	SECOND("second"),
	/**
	 * The string used for the name of the time zone, for example "UTC".
	 */
	TIME_ZONE_NAME("timeZoneName"),
	/**
	 * The string used for the weekday, for example "M", "Monday", or "Montag".
	 */
	WEEK_DAY("weekday"),
	/**
	 * The string used for the year, for example "2012" or "96".
	 */
	YEAR("year"),
	/**
	 * The string used for the yearName in relevant contexts, for example "geng-zi"
	 */
	YEAR_NAME("yearName"),
	/**
	 * Unknown token.<br>
	 * This is an add-on of <b>Charba</b> implementation.
	 */
	UNKNOWN("unknown");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private FormatPartType(String value) {
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