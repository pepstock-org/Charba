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
package org.pepstock.charba.client.callbacks;

import java.util.List;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.items.DatasetElement;
import org.pepstock.charba.client.items.DatasetItem;
import org.pepstock.charba.client.items.Undefined;

/**
 * The option context is used to give contextual information when resolving options where the data set locator (data and data set index) must be used.
 * 
 * @author Andrea "Stock" Stocchero
 */
public abstract class AbstractDatasetContext extends ChartContext {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		ACTIVE("active"),
		DATASET_INDEX("datasetIndex"),
		DATA_INDEX("dataIndex");

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

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped
	 */
	protected AbstractDatasetContext(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns true if element is active (hovered).
	 * 
	 * @return true if element is active (hovered)
	 */
	public final boolean isActive() {
		return getValue(Property.ACTIVE, false);
	}

	/**
	 * Returns the index of the current data set.
	 * 
	 * @return the index of the current data set.
	 */
	public final int getDatasetIndex() {
		return getValue(Property.DATASET_INDEX, Undefined.INTEGER);
	}

	/**
	 * Returns the index of the current data.
	 * 
	 * @return the index of the current data.
	 */
	public final int getDataIndex() {
		return getValue(Property.DATA_INDEX, Undefined.INTEGER);
	}

	/**
	 * Returns the {@link DatasetItem} related to the context, if there is, otherwise <code>null</code>.
	 * 
	 * @return the {@link DatasetItem} related to the context, if there is, otherwise <code>null</code>
	 */
	public final DatasetItem getDatasetItem() {
		// gets chart instance
		IsChart chart = getChart();
		// checks if context and chart re consistent
		if (isConsistent() && IsChart.isValid(chart)) {
			// gets data set item
			return chart.getDatasetItem(getDatasetIndex());
		}
		// if here, data set index is not consistent
		// then returns null
		return null;
	}

	/**
	 * Returns the {@link DatasetElement} related to the context, if there is, otherwise <code>null</code>.
	 * 
	 * @return the {@link DatasetElement} related to the context, if there is, otherwise <code>null</code>
	 */
	public final DatasetElement getDatasetElement() {
		// gets data set item
		DatasetItem datasetItem = getDatasetItem();
		// checks if data set item is consistent
		if (datasetItem != null) {
			// gets data index
			int dataIndex = getDataIndex();
			// gets the list of all data set elements
			List<DatasetElement> elements = datasetItem.getElements();
			// checks if the data index is consistent against the list
			if (!elements.isEmpty() && dataIndex >= 0 && dataIndex < elements.size()) {
				// gets data set element
				return elements.get(dataIndex);
			}
		}
		// if here, data set index or data index are not consistent
		// then returns null
		return null;
	}

}