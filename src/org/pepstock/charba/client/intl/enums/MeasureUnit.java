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

/**
 * Enumerated the untis of measure to configure the number formatting.<br>
 * Possible values are core unit identifiers, defined here.<br>
 * Pairs of simple units can be concatenated with "-per-" to make a compound unit.<br>
 * There is no default value; if the style is "unit", the unit property must be provided.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum MeasureUnit implements Key
{
	/**
	 * Core unit identifier for <b>acre</b>.
	 */
	ACRE("acre"),

	/**
	 * Core unit identifier for <b>bit</b>.
	 */
	BIT("bit"),

	/**
	 * Core unit identifier for <b>byte</b>.
	 */
	BYTE("byte"),

	/**
	 * Core unit identifier for <b>celsius</b>.
	 */
	CELSIUS("celsius"),

	/**
	 * Core unit identifier for <b>centimeter</b>.
	 */
	CENTIMETER("centimeter"),

	/**
	 * Core unit identifier for <b>day</b>.
	 */
	DAY("day"),

	/**
	 * Core unit identifier for <b>degree</b>.
	 */
	DEGREE("degree"),

	/**
	 * Core unit identifier for <b>fahrenheit</b>.
	 */
	FAHRENHEIT("fahrenheit"),

	/**
	 * Core unit identifier for <b>fluid-ounce</b>.
	 */
	FLUID_OUNCE("fluid-ounce"),

	/**
	 * Core unit identifier for <b>foot</b>.
	 */
	FOOT("foot"),

	/**
	 * Core unit identifier for <b>gallon</b>.
	 */
	GALLON("gallon"),

	/**
	 * Core unit identifier for <b>gigabit</b>.
	 */
	GIGABIT("gigabit"),

	/**
	 * Core unit identifier for <b>gigabyte</b>.
	 */
	GIGABYTE("gigabyte"),

	/**
	 * Core unit identifier for <b>gram</b>.
	 */
	GRAM("gram"),

	/**
	 * Core unit identifier for <b>hectare</b>.
	 */
	HECTARE("hectare"),

	/**
	 * Core unit identifier for <b>hour</b>.
	 */
	HOUR("hour"),

	/**
	 * Core unit identifier for <b>inch</b>.
	 */
	INCH("inch"),

	/**
	 * Core unit identifier for <b>kilobit</b>.
	 */
	KILOBIT("kilobit"),

	/**
	 * Core unit identifier for <b>kilobyte</b>.
	 */
	KILOBYTE("kilobyte"),

	/**
	 * Core unit identifier for <b>kilogram</b>.
	 */
	KILOGRAM("kilogram"),

	/**
	 * Core unit identifier for <b>kilometer</b>.
	 */
	KILOMETER("kilometer"),

	/**
	 * Core unit identifier for <b>liter</b>.
	 */
	LITER("liter"),

	/**
	 * Core unit identifier for <b>megabit</b>.
	 */
	MEGABIT("megabit"),

	/**
	 * Core unit identifier for <b>megabyte</b>.
	 */
	MEGABYTE("megabyte"),

	/**
	 * Core unit identifier for <b>meter</b>.
	 */
	METER("meter"),

	/**
	 * Core unit identifier for <b>mile</b>.
	 */
	MILE("mile"),

	/**
	 * Core unit identifier for <b>mile-scandinavian</b>.
	 */
	MILE_SCANDINAVIAN("mile-scandinavian"),

	/**
	 * Core unit identifier for <b>milliliter</b>.
	 */
	MILLILITER("milliliter"),

	/**
	 * Core unit identifier for <b>millimeter</b>.
	 */
	MILLIMETER("millimeter"),

	/**
	 * Core unit identifier for <b>millisecond</b>.
	 */
	MILLISECOND("millisecond"),

	/**
	 * Core unit identifier for <b>minute</b>.
	 */
	MINUTE("minute"),

	/**
	 * Core unit identifier for <b>month</b>.
	 */
	MONTH("month"),

	/**
	 * Core unit identifier for <b>ounce</b>.
	 */
	OUNCE("ounce"),

	/**
	 * Core unit identifier for <b>percent</b>.
	 */
	PERCENT("percent"),

	/**
	 * Core unit identifier for <b>petabyte</b>.
	 */
	PETABYTE("petabyte"),

	/**
	 * Core unit identifier for <b>pound</b>.
	 */
	POUND("pound"),

	/**
	 * Core unit identifier for <b>second</b>.
	 */
	SECOND("second"),

	/**
	 * Core unit identifier for <b>stone</b>.
	 */
	STONE("stone"),

	/**
	 * Core unit identifier for <b>terabit</b>.
	 */
	TERABIT("terabit"),

	/**
	 * Core unit identifier for <b>terabyte</b>.
	 */
	TERABYTE("terabyte"),

	/**
	 * Core unit identifier for <b>week</b>.
	 */
	WEEK("week"),

	/**
	 * Core unit identifier for <b>yard</b>.
	 */
	YARD("yard"),

	/**
	 * Core unit identifier for <b>year</b>.
	 */
	YEAR("year");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private MeasureUnit(String value) {
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