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
package org.pepstock.charba.client.items;

import java.util.Date;

import org.pepstock.charba.client.commons.ImmutableDate;
import org.pepstock.charba.client.data.FloatingData;
import org.pepstock.charba.client.enums.ChartAxisType;
import org.pepstock.charba.client.enums.ScaleDataType;

/**
 * This object is wrapping the value retrieved by the scale.<br>
 * The value could be a double, string or a date.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class ScaleValueItem {

	// native object passed by plugin.
	// it could be a double, string or a floating bars.
	// this is the double representation
	private final double value;
	// this is the date representation
	private final ImmutableDate valueAsDate;
	// this is the string representation
	private final String valueAsString;
	// datta type instance
	private final ScaleDataType dataType;
	// this is the label representation on scale
	private final String label;

	/**
	 * Creates the object with the value retrieved from the scale, value as {@link String}.<br>
	 * Used for {@link ChartAxisType#CATEGORY} scale.
	 * 
	 * @param value value on the scale
	 * @param index value index on the scale
	 * @param label string representation of the value, showed on the scale
	 */
	ScaleValueItem(String value, double index, String label) {
		this.value = index;
		this.valueAsDate = null;
		this.valueAsString = value;
		this.dataType = ScaleDataType.STRING;
		this.label = label;
	}

	/**
	 * Creates the object with the value retrieved from the scale, value as {@link Date}.<br>
	 * Used for {@link ChartAxisType#TIME} and {@link ChartAxisType#TIMESERIES} scales.
	 * 
	 * @param value value on the scale
	 * @param label string representation of the value, showed on the scale
	 */
	ScaleValueItem(ImmutableDate value, String label) {
		// checks if not null
		this.value = value != null ? value.getTime() : Undefined.DOUBLE;
		this.valueAsDate = value;
		this.valueAsString = label;
		this.dataType = ScaleDataType.DATE;
		this.label = label;
	}

	/**
	 * Creates the object with the value retrieved from the scale, value as double.<br>
	 * Used for {@link ChartAxisType#LINEAR}, {@link ChartAxisType#LOGARITHMIC} and {@link ChartAxisType#RADIAL_LINEAR} scales.
	 * 
	 * @param value value on the scale
	 * @param label string representation of the value, showed on the scale
	 */
	ScaleValueItem(double value, String label) {
		this.value = value;
		this.valueAsDate = null;
		this.valueAsString = label;
		this.dataType = ScaleDataType.NUMBER;
		this.label = label;
	}

	/**
	 * Returns the data type of value.
	 * 
	 * @return the data type of value
	 */
	public ScaleDataType getDataType() {
		return dataType;
	}

	/**
	 * Returns the value for the scale if it is or {@link Undefined#DOUBLE}.
	 * 
	 * @return the value for the scale if it is or {@link Undefined#DOUBLE}
	 */
	public double getValue() {
		return value;
	}

	/**
	 * Returns the value for the scale as {@link FloatingData} if it is or <code>null</code>.
	 * 
	 * @return the value for the scale as {@link FloatingData} if it is or <code>null</code>
	 */
	public Date getValueAsDate() {
		return valueAsDate;
	}

	/**
	 * Returns the value for the scale as string if it is or {@link Undefined#STRING}.
	 * 
	 * @return the value for the scale as string if it is or {@link Undefined#STRING}.
	 */
	public String getValueAsString() {
		return valueAsString;
	}

	/**
	 * Returns the label of the value used on the scale.
	 * 
	 * @return the label of the value used on the scale
	 */
	public String getLabel() {
		return label;
	}

}