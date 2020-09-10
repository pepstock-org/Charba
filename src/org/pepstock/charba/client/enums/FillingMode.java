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
 * Represents how to fill the area under the line. This is an interfaces implemented on different way, as CHART.JS has implemented.<br>
 * Both line and radar charts support a fill option on the dataset object which can be used to create area between two datasets or a dataset.<br>
 * These are the different kinds of fill you can set:<br>
 * <ul>
 * <li>Predefined values, mapped by {@link Fill}.
 * <li>Absolute dataset index, as integer (1,2,3,...)
 * <li>Relative dataset index, as string ("-1", "-2", "+1", "+2",...)
 * <li>Predefined boolean, if <code>true</code> the fill is applied, othewise <code>false</code>.
 * </ul>
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum FillingMode implements Key
{
	/**
	 * Links datasets by absolute dataset index, as integer (1,2,3,...)
	 */
	ABSOLUTE_DATASET_INDEX("absoluteDatasetIndex"),
	/**
	 * Links datastes by relative dataset index, as string ("-1", "-2", "+1", "+2",...)
	 */
	RELATIVE_DATASET_INDEX("relativeDatasetIndex"),
	/**
	 * Predefined values by {@link Fill}.
	 */
	PREDEFINED("predefined"),
	/**
	 * It is never really used, because the value is boolean.
	 */
	PREDEFINED_BOOLEAN("predefinedBoolean");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use into native object.
	 * 
	 * @param value value of property name
	 */
	private FillingMode(String value) {
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
