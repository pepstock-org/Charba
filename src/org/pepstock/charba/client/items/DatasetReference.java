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

import org.pepstock.charba.client.callbacks.ScriptableContext;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;

/**
 * Calling some methods on your chart instance passing an argument of an event, will return the elements at the event position.<br>
 * The elements are mapped by this object.<br>
 * This is the CHART.JS item with all needed info about a selected dataset, provideing the right indexes to get the dataset.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DatasetReference extends NativeObjectContainer {
	
	/**
	 * Public factory to create a dataset item from a native object.
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

	// dataset item element instance
	private final DatasetElement element;

	/**
	 * Creates the item reference using a a scriptale context and a dataset item.
	 * 
	 * @param context scriptable context instance
	 * @param item dataset item to add to the object
	 */
	public DatasetReference(ScriptableContext context, DatasetElement item) {
		super(null);
		// checks if context is consistent
		if (context == null) {
			// exception
			throw new IllegalArgumentException("The scriptable context argument is null");
		}
		// checks if dataset item is consistent
		if (item == null) {
			// exception
			throw new IllegalArgumentException("The dataset item argument is null");
		}
		// stores dataset and data index by content
		setValue(Property.DATASET_INDEX, context.getDatasetIndex());
		setValue(Property.INDEX, context.getDataIndex());
		// stores dataset item
		setValue(Property.ELEMENT, item);
		// sets the dataset item element
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
	 * Returns the dataset item element.
	 *
	 * @return the dataset element
	 */
	public final DatasetElement getElement() {
		return element;
	}

	/**
	 * Returns the dataset item index.
	 * 
	 * @return the dataset item index.
	 */
	public final int getDatasetIndex() {
		return getValue(Property.DATASET_INDEX, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the dataset item data index.
	 * 
	 * @return the dataset item data index.
	 */
	public final int getIndex() {
		return getValue(Property.INDEX, UndefinedValues.INTEGER);
	}

	/**
	 * Inner class to create dataset reference item by a native object.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	public static class DatasetReferenceItemFactory implements NativeObjectContainerFactory<DatasetReference> {
		
		/**
		 * To avoid any instatiation
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