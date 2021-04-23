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

import org.pepstock.charba.client.callbacks.AbstractDatasetContext;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;

/**
 * Calling some methods on your chart instance passing an argument of an event, will return the elements at the event position.<br>
 * The elements are mapped by this object.<br>
 * This is the CHART.JS item with all needed info about a selected data set, providing the right indexes to get the data set.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DatasetReference extends NativeObjectContainer {

	/**
	 * Public factory to create a data set item from a native object.
	 */
	public static final DatasetReferenceItemFactory FACTORY = new DatasetReferenceItemFactory();

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
	private final DatasetElement element;

	/**
	 * Creates the item reference using a a scriptale context and a data set item.
	 * 
	 * @param context scriptable context instance
	 * @param item data set item to add to the object
	 */
	public DatasetReference(AbstractDatasetContext context, DatasetElement item) {
		super(null);
		// checks if context is consistent
		Checker.checkIfValid(context, "Scriptable context argument");
		// checks if data set item is consistent
		Checker.checkIfValid(item, "Dataset item argument");
		// stores data set and data index by content
		setValue(Property.DATASET_INDEX, context.getDatasetIndex());
		setValue(Property.INDEX, context.getDataIndex());
		// stores data set item
		setValue(Property.ELEMENT, item);
		// sets the data set item element
		element = item;
	}

	/**
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties
	 */
	DatasetReference(NativeObject nativeObject) {
		super(nativeObject);
		// sets the dataset item element
		element = new DatasetElement(getValue(Property.ELEMENT));
	}

	/**
	 * Returns the data set item element.
	 *
	 * @return the data set element
	 */
	public final DatasetElement getElement() {
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

	/**
	 * Inner class to create data set reference item by a native object.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	public static class DatasetReferenceItemFactory implements NativeObjectContainerFactory<DatasetReference> {

		/**
		 * To avoid any instantiation
		 */
		private DatasetReferenceItemFactory() {
			// do nothing
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons. NativeObject)
		 */
		@Override
		public DatasetReference create(NativeObject nativeObject) {
			return new DatasetReference(nativeObject);
		}
	}

}