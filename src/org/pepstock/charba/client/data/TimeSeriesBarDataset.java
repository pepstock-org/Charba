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
package org.pepstock.charba.client.data;

import java.util.List;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.defaults.IsDefaultOptions;
import org.pepstock.charba.client.items.UndefinedValues;

/**
 * The time series bar chart allows a number of properties to be specified for each dataset. These are used to set display properties for a
 * specific dataset.<br>
 * In time series charts, the data (as double) is not allowed. You must use {@link DataPoint} or {@link IsTimeSeriesItem}. 
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class TimeSeriesBarDataset extends BarDataset implements HasTimeSeriesItems {

	// exception string message for setting ore getting data
	private static final String INVALID_DATA_CALL = "setData and getData methods are not invokable by a time series chart.";
	
	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		STACK("stack");

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

	/**
	 * Creates a dataset.<br>
	 * It uses the global options has default.
	 */
	public TimeSeriesBarDataset() {
		this(null);
	}

	/**
	 * Creates the dataset using a default.
	 * 
	 * @param defaultValues default options
	 */
	public TimeSeriesBarDataset(IsDefaultOptions defaultValues) {
		super(defaultValues);
	}
	
	/**
	 * Sets the name of stack group.
	 * 
	 * @param stackGroup name of stack group.
	 */
	public void setStackGroup(String stackGroup) {
		setValue(Property.STACK, stackGroup);
	}

	/**
	 * Returns the name of stack group.
	 * 
	 * @return the name of stack group.
	 */
	public String getStackGroup() {
		return getValue(Property.STACK, UndefinedValues.STRING);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#setData(double[])
	 */
	@Override
	public final void setData(double... values) {
		throw new UnsupportedOperationException(INVALID_DATA_CALL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#setData(java.util.List)
	 */
	@Override
	public final void setData(List<Double> values) {
		throw new UnsupportedOperationException(INVALID_DATA_CALL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#getData()
	 */
	@Override
	public final List<Double> getData() {
		throw new UnsupportedOperationException(INVALID_DATA_CALL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#getData(boolean)
	 */
	@Override
	public final List<Double> getData(boolean binding) {
		throw new UnsupportedOperationException(INVALID_DATA_CALL);
	}

}
