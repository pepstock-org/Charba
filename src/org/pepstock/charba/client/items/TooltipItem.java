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

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.commons.ObjectType;

/**
 * Contains all info for every item of tooltip.<br>
 * Created and passed by CHART.JS.<br>
 * It uses in the tooltips callbacks.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class TooltipItem extends NativeObjectContainer {

	/**
	 * Public factory to create a tooltip item from a native object.
	 */
	public static final TooltipItemFactory FACTORY = new TooltipItemFactory();

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		CHART("chart"),
		LABEL("label"),
		DATA_POINT("dataPoint"),
		FORMATTED_VALUE("formattedValue"),
		DATASET_INDEX("datasetIndex"),
		DATA_INDEX("dataIndex"),
		ELEMENT("element");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use in the native object.
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
	private final DatasetPoint dataPoint;

	/**
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	TooltipItem(NativeObject nativeObject) {
		super(nativeObject);
		// stores the data point
		this.dataPoint = new DatasetPoint(getValue(Property.DATA_POINT));
	}

	/**
	 * Returns the CHARBA chart instance.
	 * 
	 * @return the CHARBA chart instance
	 */
	public final IsChart getChart() {
		// checks if chart is inside the context
		if (isType(Property.CHART, ObjectType.OBJECT)) {
			return getNativeChart(Property.CHART).getChart();
		}
		// if here the context is not consistent
		// returns null
		return null;
	}

	/**
	 * Returns the label for the tooltip.
	 * 
	 * @return the label for the tooltip.
	 */
	public final String getLabel() {
		return getValue(Property.LABEL, Undefined.STRING);
	}

	/**
	 * Returns the parsed data values for the given {@link TooltipItem#getDatasetIndex()} and {@link TooltipItem#getDataIndex()}.
	 * 
	 * @return the parsed data values for the given {@link TooltipItem#getDatasetIndex()} and {@link TooltipItem#getDataIndex()}
	 */
	public final DatasetPoint getDataPoint() {
		return dataPoint;
	}

	/**
	 * Returns the formatted value for the tooltip.
	 * 
	 * @return the formatted value for the tooltip.
	 */
	public final String getFormattedValue() {
		return getValue(Property.FORMATTED_VALUE, Undefined.STRING);
	}

	/**
	 * Returns the dataset index of the chart
	 * 
	 * @return the dataset index of the chart.
	 */
	public final int getDatasetIndex() {
		return getValue(Property.DATASET_INDEX, Undefined.INTEGER);
	}

	/**
	 * Returns the index of the data inside the dataset.
	 * 
	 * @return the index of the data inside the dataset.
	 */
	public final int getDataIndex() {
		return getValue(Property.DATA_INDEX, Undefined.INTEGER);
	}

	/**
	 * Returns the chart element (point, arc, bar, etc.) for this tooltip item.
	 * 
	 * @return the chart element (point, arc, bar, etc.) for this tooltip item.
	 */
	public final ChartElement getElement() {
		// gets native object
		NativeObject nativeObject = getValue(Property.ELEMENT);
		// gets chart
		IsChart chart = getChart();
		// check is consistent
		if (IsChart.isValid(chart)) {
			// gets dataset item
			DatasetItem item = chart.getDatasetItem(getDatasetIndex());
			// checks if the item is consistent
			if (item != null) {
				// gets the factory
				ChartElementFactory<?> factory = ChartElementFactories.get().getFactory(item);
				// creates and returns element
				return factory.create(nativeObject);
			}
		}
		// if here, it's not able to resolve the chart or dataset item
		// then returns the base element
		return new ChartElement(nativeObject);
	}

	/**
	 * Inner class to create tooltip item by a native object.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	public static class TooltipItemFactory implements NativeObjectContainerFactory<TooltipItem> {

		/**
		 * To avoid any instantiation
		 */
		private TooltipItemFactory() {
			// do nothing
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		public TooltipItem create(NativeObject nativeObject) {
			return new TooltipItem(nativeObject);
		}
	}
}