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

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.plugins.PluginsEnvelop;

/**
 * This is a wrapper of java script object which represents a dataset.<br>
 * This object is used in the plugins methods of CHART.JS.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class PluginDatasetArgument extends PluginUpdateArgument {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		INDEX("index"),
		META("meta");

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

	// dataset item instance
	private final DatasetItem datasetItem;

	/**
	 * Creates the item using an envelop of the native java script object which contains all properties.
	 * 
	 * @param envelop envelop of the native java script object which contains all properties.
	 */
	public PluginDatasetArgument(PluginsEnvelop<NativeObject> envelop) {
		super(envelop);
		// checks if data item is present
		// creating it or setting an empty object
		datasetItem = has(Property.META) ? new DatasetItem(getValue(Property.META)) : new DatasetItem();
	}

	/**
	 * Returns the index of the data inside the dataset.
	 * 
	 * @return the index of the data inside the dataset.
	 */
	public int getIndex() {
		return getValue(Property.INDEX, Undefined.INTEGER);
	}

	/**
	 * Returns the dataset item.
	 * 
	 * @return the dataset item.
	 */
	public DatasetItem getDatasetItem() {
		return datasetItem;
	}
}