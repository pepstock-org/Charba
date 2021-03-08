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

import org.pepstock.charba.client.commons.IsEnvelop;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.configuration.ConfigurationEnvelop;
import org.pepstock.charba.client.data.DataEnvelop;
import org.pepstock.charba.client.datalabels.DataLabelsEnvelop;
import org.pepstock.charba.client.items.DatasetElement;
import org.pepstock.charba.client.items.DatasetPoint;
import org.pepstock.charba.client.items.UndefinedValues;

/**
 * The option context is used to give contextual information when resolving options.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class ScriptableContext extends AbstractScriptableContext {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		ACTIVE("active"),
		DATASET_INDEX("datasetIndex"),
		DATA_INDEX("dataIndex"),
		PARSED("parsed"),
		ELEMENT("element");

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

	// data point instance
	private DatasetPoint dataPoint = null;
	// element instance
	private DatasetElement element = null;

	/**
	 * Creates the object with public object instance to be wrapped, called by <code>configuration</code> package.
	 * 
	 * @param envelop envelop of public object instance to be wrapped.
	 */
	public ScriptableContext(ConfigurationEnvelop<NativeObject> envelop) {
		this(IsEnvelop.checkAndGetIfValid(envelop).getContent());
	}

	/**
	 * Creates the object with public object instance to be wrapped, called by <code>data</code> package.
	 * 
	 * @param envelop envelop of public object instance to be wrapped.
	 */
	public ScriptableContext(DataEnvelop<NativeObject> envelop) {
		this(IsEnvelop.checkAndGetIfValid(envelop).getContent());
	}

	/**
	 * Creates the object with public object instance to be wrapped, called by <code>datalabels</code> package.
	 * 
	 * @param envelop envelop of public object instance to be wrapped.
	 */
	public ScriptableContext(DataLabelsEnvelop<NativeObject> envelop) {
		this(IsEnvelop.checkAndGetIfValid(envelop).getContent());
	}

	/**
	 * Creates the object with public object instance to be wrapped.
	 * 
	 * @param nativeObject public object instance to be wrapped.
	 */
	private ScriptableContext(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns true if element is active (hovered).
	 * 
	 * @return true if element is active (hovered)
	 */
	public boolean isActive() {
		return getValue(Property.ACTIVE, false);
	}

	/**
	 * Returns the index of the current data set.
	 * 
	 * @return the index of the current data set.
	 */
	public int getDatasetIndex() {
		return getValue(Property.DATASET_INDEX, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the index of the current data.
	 * 
	 * @return the index of the current data.
	 */
	public int getDataIndex() {
		return getValue(Property.DATA_INDEX, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the parsed data values for the given dataIndex and datasetIndex.
	 * 
	 * @return the parsed data values for the given dataIndex and datasetIndex
	 */
	public DatasetPoint getParsed() {
		// checks is point object is already created
		if (dataPoint == null) {
			// stores point
			this.dataPoint = new DatasetPoint(new CallbacksEnvelop<>(getValue(Property.PARSED), true));
		}
		return dataPoint;
	}

	/**
	 * Returns the element (point, arc, bar, etc.) for this data.
	 * 
	 * @return the element (point, arc, bar, etc.) for this data
	 */
	public DatasetElement getElement() {
		// checks is element object is already created
		if (element == null) {
			// stores element
			this.element = new DatasetElement(new CallbacksEnvelop<>(getValue(Property.ELEMENT), true));
		}
		return element;
	}

}