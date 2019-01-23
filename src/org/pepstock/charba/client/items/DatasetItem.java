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
 * Calling some methods on your chart instance passing an argument of an event, will return the elements at the event
 * position.<br>
 * The elements are mapped by this object.<br>
 * This is the CHART.JS item with all needed info about a selected dataset. This object has been created and passed to event
 * handler or callbacks to apply own logic.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class DatasetItem extends NativeObjectContainer {

	private final DatasetViewItem view;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		_datasetIndex,
		_index,
		_view,
		hidden
	}

	/**
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	public DatasetItem(NativeObject nativeObject) {
		super(nativeObject);
		// initializes the sub objects
		view = new DatasetViewItem(getValue(Property._view));
	}

	/**
	 * Returns the dataset index of the chart
	 * 
	 * @return the dataset index of the chart. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	public final int getDatasetIndex() {
		return getValue(Property._datasetIndex, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the index of the data inside the dataset.
	 * 
	 * @return the index of the data inside the dataset. Default is
	 *         {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	public final int getIndex() {
		return getValue(Property._index, UndefinedValues.INTEGER);
	}

	/**
	 * Returns if the dataset is hidden.
	 * 
	 * @return <code>true</code> if the dataset is hidden, otherwise <code>false</code>. Default is
	 *         {@link org.pepstock.charba.client.items.UndefinedValues#BOOLEAN}.
	 */
	public final boolean isHidden() {
		return getValue(Property.hidden, UndefinedValues.BOOLEAN);
	}

	/**
	 * Sets if the dataset must be hidden.
	 * 
	 * @param hidden <code>true</code> if the dataset must be hidden, otherwise <code>false</code>.
	 */
	public final void setHidden(boolean hidden) {
		setValue(Property.hidden, hidden);
	}

	/**
	 * Returns all view information about the dataset.
	 * 
	 * @return all view information about the dataset.
	 */
	public final DatasetViewItem getView() {
		return view;
	}

	/**
	 * Inner class to create dataset item by a native object.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	public static final class DatasetItemFactory implements NativeObjectContainerFactory<DatasetItem> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.jsinterop
		 * .commons.NativeObject)
		 */
		@Override
		public DatasetItem create(NativeObject nativeObject) {
			return new DatasetItem(nativeObject);
		}
	}

}