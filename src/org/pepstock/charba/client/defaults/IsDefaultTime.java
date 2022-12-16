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
package org.pepstock.charba.client.defaults;

import org.pepstock.charba.client.enums.IsoWeekDay;
import org.pepstock.charba.client.enums.TimeUnit;

/**
 * Interface to define time object defaults.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsDefaultTime {

	/**
	 * Returns the ISO day of the week with 0 being Sunday and 6 being Saturday.
	 * 
	 * @return ISO day of the week with 0 being Sunday and 6 being Saturday
	 */
	IsoWeekDay getIsoWeekday();

	/**
	 * If defined, dates will be rounded to the start of this unit.
	 * 
	 * @return If defined, dates will be rounded to the start of this unit.
	 */
	TimeUnit getRound();

	/**
	 * The date format string to use for the tooltip.
	 * 
	 * @return date format string to use for the tooltip.
	 */
	String getTooltipFormat();

	/**
	 * If defined, will force the unit to be a certain type.
	 * 
	 * @return If defined, will force the unit to be a certain type.
	 */
	TimeUnit getUnit();

	/**
	 * The minimum display format to be used for a time unit.
	 * 
	 * @return The minimum display format to be used for a time unit.
	 */
	TimeUnit getMinUnit();

	/**
	 * Defined as a string, it is interpreted as a custom format to be used by LUXON to parse the date.
	 * 
	 * @return Defined as a string, it is interpreted as a custom format to be used by LUXON to parse the date.
	 */
	String getParser();
}