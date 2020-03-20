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
package org.pepstock.charba.client.labels;

import org.pepstock.charba.client.Chart;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.items.UndefinedValues;

/**
 * This object is wrapping the native java script object provided by {@link LabelsPlugin#ID} plugin when the RENDER function is called.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class RenderItem extends NativeObjectContainer {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		CHART("chart"),
		DATASET_INDEX("datasetIndex"),
		INDEX("index"),
		LABEL("label"),
		VALUE("value"),
		PERCENTAGE("percentage");

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
	 * Returns the CHARBA chart instance.
	 * 
	 * @return the CHARBA chart instance
	 */
	IsChart getChart() {
		// gets native chart
		Chart nativeChart = getNativeChart(Property.CHART);
		// checks if native chart is present
		if (nativeChart != null) {
			// returns is chart instance
			return nativeChart.getChart();
		}
		// if here, the native chart is not consistent
		return null;
	}

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 */
	RenderItem(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the index of the data inside the dataset.
	 * 
	 * @return the index of the data inside the dataset. Default is {@link UndefinedValues#INTEGER}.
	 */

	public final int getIndex() {
		return getValue(Property.INDEX, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the dataset index of the data inside the dataset.
	 * 
	 * @return the dataset index of the data inside the dataset. Default is {@link UndefinedValues#INTEGER}.
	 */
	public final int getDatasetIndex() {
		return getValue(Property.DATASET_INDEX, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the label for the dataset.
	 * 
	 * @return the label for the dataset. Default is {@link UndefinedValues#STRING}.
	 */
	public final String getLabel() {
		return getValue(Property.LABEL, UndefinedValues.STRING);
	}

	/**
	 * Returns the percentage for the dataset.
	 * 
	 * @return the percentage for the dataset. Default is {@link UndefinedValues#DOUBLE}.
	 */
	public final double getPercentage() {
		return getValue(Property.PERCENTAGE, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the value for the dataset.
	 * 
	 * @return the value for the dataset. Default is {@link UndefinedValues#DOUBLE}.
	 */
	public final double getValue() {
		return getValue(Property.VALUE, UndefinedValues.DOUBLE);
	}
}