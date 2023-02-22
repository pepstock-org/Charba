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

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;

/**
 * Object used by CHART.JS to manages the user interaction on chart instances, for events, hovering and tooltips.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class InteractionItem extends NativeObjectContainer {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		DATASET_INDEX("datasetIndex"),
		INDEX("index"),
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

	// chart instance
	private final IsChart chart;

	/**
	 * Creates an item with chart and a specific dataset element, setting dataset and data indexes.
	 * 
	 * @param chart chart instance
	 * @param element element of the dataset
	 * @param datasetIndex dataset index in the chart
	 * @param index data index in the dataset data
	 */
	public InteractionItem(IsChart chart, ChartElement element, int datasetIndex, int index) {
		this(chart, (NativeObject) null);
		// checks arguments
		Checker.assertCheck(element != null, "Element argument is not consistent");
		// stores element
		setValue(Property.ELEMENT, element);
		// stores indexes
		setValue(Property.DATASET_INDEX, datasetIndex);
		setValue(Property.INDEX, index);
	}

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param chart chart instance
	 * @param nativeObject native object instance to be wrapped.
	 */
	InteractionItem(IsChart chart, NativeObject nativeObject) {
		super(nativeObject);
		// checks if chart is correct
		this.chart = IsChart.checkAndGetIfValid(chart);
	}

	/**
	 * Returns the CHARBA chart instance.
	 * 
	 * @return the CHARBA chart instance
	 */
	public final IsChart getChart() {
		return chart;
	}

	/**
	 * Returns the index of the data inside the dataset.
	 * 
	 * @return the index of the data inside the dataset.
	 */
	public final int getIndex() {
		return getValue(Property.INDEX, Undefined.INTEGER);
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
		if (chart != null && IsChart.isValid(chart)) {
			// gets dataset item
			DatasetItem item = chart.getDatasetItem(getDatasetIndex());
			// checks if the item is consistent
			if (item != null) {
				// gets the factory
				ChartElementFactory factory = ChartElementFactories.get().getFactory(item);
				// creates and returns element
				return factory.create(nativeObject);
			}
		}
		// if here, it's not able to resolve the chart or dataset item
		// then returns the base element
		return new ChartElement(ChartElement.UNDEFINED_TYPE, nativeObject);
	}

	/**
	 * Returns the native object instance.
	 * 
	 * @return the native object instance.
	 */
	public NativeObject nativeObject() {
		return super.getNativeObject();
	}

	/**
	 * Returns an interaction items factory for a specific chart.
	 * 
	 * @param chart chart instance
	 * @return an interaction items factory for a specific chart
	 */
	public static NativeObjectContainerFactory<InteractionItem> createFactory(IsChart chart) {
		return new InteractionItemFactory(IsChart.checkAndGetIfValid(chart));
	}

	/**
	 * Interaction item factory used to create items.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class InteractionItemFactory implements NativeObjectContainerFactory<InteractionItem> {

		private final IsChart chart;

		/**
		 * Creates a item factory with chart instance
		 * 
		 * @param chart chart instance
		 */
		private InteractionItemFactory(IsChart chart) {
			this.chart = chart;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		public InteractionItem create(NativeObject nativeObject) {
			return new InteractionItem(chart, nativeObject);
		}

	}

}