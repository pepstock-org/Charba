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

import org.pepstock.charba.client.commons.ArrayDouble;
import org.pepstock.charba.client.commons.Constants;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.data.BarDataset;
import org.pepstock.charba.client.data.DataPoint;
import org.pepstock.charba.client.data.FloatingData;
import org.pepstock.charba.client.enums.DataType;
import org.pepstock.charba.client.utils.JSON;

/**
 * This object is wrapping the value of data set which can be used into the plugins (extensions) callbacks.<br>
 * The value could be a double, string or a {@link FloatingData} if the data set is a BAR data set and uses floating bars data.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DataItem {

	// native object passed by plugin.
	// it could be a double, string or a floating bars.
	// this is the double representation
	private final Double value;
	// this is the floating data representation
	private final FloatingData valueAsFloatingData;
	// this is the string representation
	private final String valueAsString;
	// this is the string representation
	private final DataPoint valueAsPoint;
	// data type instance
	private final DataType dataType;

	/**
	 * Creates the object with object which represents the value.
	 * 
	 * @param object object which represents the value.
	 */
	public DataItem(Object object) {
		// checks if the value is a double
		if (object instanceof Double) {
			// sets double and null to floating data and string value
			this.value = (Double) object;
			this.valueAsFloatingData = null;
			this.valueAsString = String.valueOf(value);
			this.valueAsPoint = null;
			this.dataType = DataType.NUMBERS;
		} else if (object instanceof ArrayDouble) {
			// sets floating data, getting the array double and set nan to value
			this.value = UndefinedValues.DOUBLE;
			this.valueAsFloatingData = BarDataset.FLOATING_BAR_DATA_FACTORY.create((ArrayDouble) object);
			this.valueAsString = valueAsFloatingData.toString();
			this.valueAsPoint = null;
			this.dataType = DataType.ARRAYS;
		} else if (object instanceof String) {
			// uses the to string method of object and nan and null for other values
			this.value = UndefinedValues.DOUBLE;
			this.valueAsFloatingData = null;
			this.valueAsString = object.toString();
			this.valueAsPoint = null;
			this.dataType = DataType.STRINGS;
		} else if (object instanceof NativeObject) {
			// uses the to string method of object and nan and null for other values
			this.value = UndefinedValues.DOUBLE;
			this.valueAsFloatingData = null;
			this.valueAsString = JSON.stringify(object, 3);
			this.valueAsPoint = new DataPoint(new ItemsEnvelop<>((NativeObject)object));
			this.dataType = DataType.POINTS;
		} else {
			// if here is not a recognized object
			// for data, then set nan and null.
			this.value = UndefinedValues.DOUBLE;
			this.valueAsFloatingData = null;
			this.valueAsString = Constants.NULL_STRING;
			this.valueAsPoint = null;
			this.dataType = DataType.UNKNOWN;
		}
	}

	/**
	 * Returns the data type of value.
	 * 
	 * @return the data type of value
	 */
	public DataType getDataType() {
		return dataType;
	}

	/**
	 * Returns the value for the data set if it is or {@link UndefinedValues#DOUBLE}.
	 * 
	 * @return the value for the data set if it is or {@link UndefinedValues#DOUBLE}
	 */
	public double getValue() {
		return value;
	}

	/**
	 * Returns the value for the data set as {@link FloatingData} if it is or <code>null</code>.
	 * 
	 * @return the value for the data set as {@link FloatingData} if it is or <code>null</code>
	 */
	public FloatingData getValueAsFloatingData() {
		return valueAsFloatingData;
	}

	/**
	 * Returns the value for the data set as string if it is or {@link UndefinedValues#STRING}.
	 * 
	 * @return the value for the data set as string if it is or {@link UndefinedValues#STRING}.
	 */
	public String getValueAsString() {
		return valueAsString;
	}

	/**
	 * Returns the value for the data set as {@link DataPoint} if it is or <code>null</code>.
	 * 
	 * @return the value for the data set as {@link DataPoint} if it is or <code>null</code>.
	 */
	public DataPoint getValueAsDataPoint() {
		return valueAsPoint;
	}

}