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

/**
 * This is a wrapper of java script object which represents a dataset.<br>
 * This object is used in the plugins methods of CHART.JS.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DatasetPluginItem extends NativeObjectContainer {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		easing,
		index,
		meta
	}

	// meta data reference
	private final DatasetMetaItem meta;

	/**
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	public DatasetPluginItem(NativeObject nativeObject) {
		super(nativeObject);
		// checks if meta data are present
		// creating it or setting an empty object
		meta = has(Property.meta) ? new DatasetMetaItem(getValue(Property.meta)) : new DatasetMetaItem();
	}

	/**
	 * Returns the current animation frame number.
	 * 
	 * @return the current animation frame number. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	public double getEasing() {
		return getValue(Property.easing, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the index of the data inside the dataset.
	 * 
	 * @return the index of the data inside the dataset. Default is
	 *         {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	public int getIndex() {
		return getValue(Property.index, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the dataset meta data item.
	 * 
	 * @return the dataset meta data item.
	 */
	public DatasetMetaItem getMeta() {
		return meta;
	}
}