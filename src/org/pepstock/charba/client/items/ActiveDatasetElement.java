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

import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;

/**
 * Calling some methods on your chart instance passing an argument of an event, will return the elements at the event position.<br>
 * The elements are mapped by this object.<br>
 * This is the CHART.JS item with all needed info about a selected data set.<br>
 * This object has been created and passed to event handler or callbacks to apply own logic.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class ActiveDatasetElement extends NativeObjectContainer {

	/**
	 * Factory instance to create active data set elements.
	 */
	public static final ActiveDatasetElementFactory FACTORY = new ActiveDatasetElementFactory();

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		DATASET_INDEX("datasetIndex"),
		INDEX("index");

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
	 * Creates a data set element, setting data set and data element indexes.
	 * 
	 * @param datasetIndex the index of the data set.
	 * @param dataIndex the index of the data element
	 */
	public ActiveDatasetElement(int datasetIndex, int dataIndex) {
		super();
		// stores data
		setDatasetIndex(datasetIndex);
		setIndex(dataIndex);
	}

	/**
	 * Creates an empty data set element.
	 */
	public ActiveDatasetElement() {
		super();
	}

	/**
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	ActiveDatasetElement(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Sets the index of the data set.
	 * 
	 * @param datasetIndex the index of the data set.
	 */
	public void setDatasetIndex(int datasetIndex) {
		setValue(Property.DATASET_INDEX, Checker.greaterThanOrZero(datasetIndex, 0));
	}

	/**
	 * Returns the index of the data set.
	 * 
	 * @return the index of the data set.
	 */
	public int getDatasetIndex() {
		return getValue(Property.DATASET_INDEX, Undefined.INTEGER);
	}

	/**
	 * Sets the index of the data element.
	 * 
	 * @param dataIndex the index of the data element
	 */
	public void setIndex(int dataIndex) {
		setValue(Property.INDEX, Checker.greaterThanOrZero(dataIndex, 0));
	}

	/**
	 * Returns the index of the data element.
	 * 
	 * @return the index of the data element
	 */
	public int getIndex() {
		return getValue(Property.INDEX, Undefined.INTEGER);
	}

	/**
	 * Inner class to create data set element by a native object.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	private static class ActiveDatasetElementFactory implements NativeObjectContainerFactory<ActiveDatasetElement> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		public ActiveDatasetElement create(NativeObject nativeObject) {
			return new ActiveDatasetElement(nativeObject);
		}
	}

}