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
package org.pepstock.charba.client.items;

import java.util.Date;

import org.pepstock.charba.client.data.FloatingData;
import org.pepstock.charba.client.enums.AxisType;
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
	private final Date valueAsDate;
	// this is the string representation
	private final String valueAsString;
	// datta type instance
	private final ScaleDataType dataType;
	// this is the label representation on scale
	private final String label;

	/**
	 * Creates the object with the value retrieved from the scale, value as {@link String}.<br>
	 * Used for {@link AxisType#CATEGORY} scale.
	 * 
	 * @param value value on the scale
	 * @param label string representation of the value, showed on the scale
	 */
	ScaleValueItem(String value, String label) {
		this.value = UndefinedValues.DOUBLE;
		this.valueAsDate = null;
		this.valueAsString = value;
		this.dataType = ScaleDataType.STRING;
		this.label = label;
	}

	/**
	 * Creates the object with the value retrieved from the scale, value as {@link Date}.<br>
	 * Used for {@link AxisType#TIME} and {@link AxisType#TIMESERIES} scales.
	 * 
	 * @param value value on the scale
	 * @param label string representation of the value, showed on the scale
	 */
	ScaleValueItem(Date value, String label) {
		// checks if not null
		this.value = value != null ? value.getTime() : UndefinedValues.DOUBLE;
		this.valueAsDate = value;
		this.valueAsString = label;
		this.dataType = ScaleDataType.DATE;
		this.label = label;
	}

	/**
	 * Creates the object with the value retrieved from the scale, value as double.<br>
	 * Used for {@link AxisType#LINEAR}, {@link AxisType#LOGARITHMIC} and {@link AxisType#RADIAL_LINEAR} scales.
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
	 * Returns the value for the scale if it is or {@link UndefinedValues#DOUBLE}.
	 * 
	 * @return the value for the scale if it is or {@link UndefinedValues#DOUBLE}
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
	 * Returns the value for the scale as string if it is or {@link UndefinedValues#STRING}.
	 * 
	 * @return the value for the scale as string if it is or {@link UndefinedValues#STRING}.
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

	// FIXME
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ScaleValueItem [value=" + value + ", valueAsDate=" + valueAsDate + ", valueAsString=" + valueAsString + ", dataType=" + dataType + ", label=" + label + "]";
	}
	
	

}