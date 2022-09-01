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

import org.pepstock.charba.client.ChartEnvelop;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.callbacks.AbstractDatasetContext;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Envelop;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;

/**
 * Calling some methods on your chart instance passing an argument of an event, will return the elements at the event position.<br>
 * The elements are mapped by this object.<br>
 * This is the CHART.JS item with all needed info about a selected data set, providing the right indexes to get the data set.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DatasetReference extends NativeObjectContainer {

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

	// dataset item element instance
	private final ChartElement element;

	/**
	 * Creates the item reference using a a scriptale context and a data set item.
	 * 
	 * @param context scriptable context instance
	 * @param element chart element to add to the object
	 */
	public DatasetReference(AbstractDatasetContext context, ChartElement element) {
		super(null);
		// checks if context is consistent
		Checker.checkIfValid(context, "Scriptable context argument");
		// checks if chart element is consistent
		Checker.checkIfValid(element, "Chart element argument");
		// stores data set and data indexes by content
		setValue(Property.DATASET_INDEX, context.getDatasetIndex());
		setValue(Property.INDEX, context.getDataIndex());
		// stores chart element
		setValue(Property.ELEMENT, element);
		// sets the chart element
		this.element = element;
	}

	/**
	 * Creates the item using a native java script object which contains all properties. FIXME
	 * 
	 * @param evenlop envelop from chart with native java script object which contains all properties
	 */
	public DatasetReference(IsChart chart, ChartEnvelop<NativeObject> envelop) {
		super(Envelop.checkAndGetIfValid(envelop).getContent());
		// checks if chart is valid
		IsChart.checkIfValid(chart);
		// checks if dataset index is consistent
		Checker.assertCheck(has(Property.values()), "Invalid object: it does not contain all properties of a dataset reference");
		// gets dataset index from native object
		int datasetIndex = JsHelper.get().getIntegerProperty(Property.DATASET_INDEX, getNativeObject());
		// gets dataset item form chart
		DatasetItem item = chart.getDatasetItem(datasetIndex);
		// gets the factory
		ElementFactory<?> factory = ElementFactories.get().getFactory(item);
		// sets the chart element
		element = factory.create(getValue(Property.ELEMENT));
	}

	/**
	 * Returns the data set item element.
	 *
	 * @return the data set element
	 */
	public final ChartElement getElement() {
		return element;
	}

	/**
	 * Returns the data set item index.
	 * 
	 * @return the data set item index.
	 */
	public final int getDatasetIndex() {
		return getValue(Property.DATASET_INDEX, Undefined.INTEGER);
	}

	/**
	 * Returns the data set item data index.
	 * 
	 * @return the data set item data index.
	 */
	public final int getIndex() {
		return getValue(Property.INDEX, Undefined.INTEGER);
	}

}