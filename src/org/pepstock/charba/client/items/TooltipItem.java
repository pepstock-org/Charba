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

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;

/**
 * Contains all info for every item of tooltip.<br>
 * Created and passed by CHART.JS.<br>
 * It uses into the tooltips callbacks.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class TooltipItem extends NativeObjectContainer {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		LABEL("label"),
		DATA_POINT("dataPoint"),
		FORMATTED_VALUE("formattedValue"),
		DATASET_INDEX("datasetIndex"),
		DATA_INDEX("dataIndex");

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
	
	// reference to data point
	private final TooltipDataPoint dataPoint;

	/**
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	public TooltipItem(NativeObject nativeObject) {
		super(nativeObject);
		// stores teh data point
		this.dataPoint = new TooltipDataPoint(getValue(Property.DATA_POINT));
	}

	/**
	 * Returns the label for the tooltip.
	 * 
	 * @return the label for the tooltip. Default is {@link UndefinedValues#STRING}.
	 */
	public String getLabel() {
		return getValue(Property.LABEL, UndefinedValues.STRING);
	}
	
	/**
	 * Returns the parsed data values for the given {@link TooltipItem#getDatasetIndex()} and {@link TooltipItem#getDataIndex()}.
	 * 
	 * @return the parsed data values for the given {@link TooltipItem#getDatasetIndex()} and {@link TooltipItem#getDataIndex()} 
	 */
	public TooltipDataPoint getDataPoint() {
		return dataPoint;
	}


	/**
	 * Returns the formatted value for the tooltip.
	 * 
	 * @return the formatted value for the tooltip. Default is {@link UndefinedValues#STRING}.
	 */
	public String getFormattedValue() {
		return getValue(Property.FORMATTED_VALUE, UndefinedValues.STRING);
	}

	/**
	 * Returns the dataset index of the chart
	 * 
	 * @return the dataset index of the chart. Default is {@link UndefinedValues#INTEGER}.
	 */
	public int getDatasetIndex() {
		return getValue(Property.DATASET_INDEX, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the index of the data inside the dataset.
	 * 
	 * @return the index of the data inside the dataset. Default is {@link UndefinedValues#INTEGER}.
	 */
	public int getDataIndex() {
		return getValue(Property.DATA_INDEX, UndefinedValues.INTEGER);
	}

	/**
	 * Inner class to create tooltip item by a native object.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	public static final class TooltipItemFactory implements NativeObjectContainerFactory<TooltipItem> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons. NativeObject)
		 */
		@Override
		public TooltipItem create(NativeObject nativeObject) {
			return new TooltipItem(nativeObject);
		}
	}
}