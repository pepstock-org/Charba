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
package org.pepstock.charba.client.ext.labels;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.StandardKey;
import org.pepstock.charba.client.items.UndefinedValues;

/**
 * This object is wrapping the native java script object provided by labels plugin when the RENDER function is called.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class RenderItem extends NativeObjectContainer {

	// standard key inside the _META object
	private static final Key key = new StandardKey(String.valueOf(0));

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		label,
		index,
		percentage,
		value,
		dataset,
		// used to get dataset index into internal structure
		_meta,
		controller
	}

	/**
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	RenderItem(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the dataset index of the data inside the dataset.
	 * 
	 * @return the dataset index of the data inside the dataset. Default is
	 *         {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	public final int getDatasetIndex() {
		// to get the dataset index it has to go in deep
		// in the object.
		// the path is dataset -> _meta -> "0" -> controller -> index
		DatasetItem item = new DatasetItem(getValue(Property.dataset));
		return item.getIndex();
	}

	/**
	 * Returns the index of the data inside the dataset.
	 * 
	 * @return the index of the data inside the dataset. Default is
	 *         {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	public final int getIndex() {
		return getValue(Property.index, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the label for the dataset.
	 * 
	 * @return the label for the dataset. Default is {@link org.pepstock.charba.client.items.UndefinedValues#STRING}.
	 */
	public final String getLabel() {
		return getValue(Property.label, UndefinedValues.STRING);
	}

	/**
	 * Returns the percentage for the dataset.
	 * 
	 * @return the percentage for the dataset. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	public final double getPercentage() {
		return getValue(Property.percentage, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the value for the dataset.
	 * 
	 * @return the value for the dataset. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	public final double getValue() {
		return getValue(Property.value, UndefinedValues.DOUBLE);
	}

	/**
	 * This object is wrapping the node DATASET.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	private static class DatasetItem extends NativeObjectContainer {

		/**
		 * Creates the item using a native java script object which contains all properties.
		 * 
		 * @param nativeObject native java script object which contains all properties.
		 */
		DatasetItem(NativeObject nativeObject) {
			super(nativeObject);
		}

		/**
		 * Returns the index of the data inside the dataset.
		 * 
		 * @return the index of the data inside the dataset. Default is
		 *         {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
		 */
		int getIndex() {
			MetaItem meta = new MetaItem(getValue(Property._meta));
			return meta.getIndex();
		}
	}

	/**
	 * This object is wrapping the node DATASET --> _META.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	private static class MetaItem extends NativeObjectContainer {

		/**
		 * Creates the item using a native java script object which contains all properties.
		 * 
		 * @param nativeObject native java script object which contains all properties.
		 */
		MetaItem(NativeObject nativeObject) {
			super(nativeObject);
		}

		/**
		 * Returns the index of the data inside the dataset.
		 * 
		 * @return the index of the data inside the dataset. Default is
		 *         {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
		 */
		int getIndex() {
			// checks is key exists
			if (has(key)) {
				// creates meta 0 object by "0" proiperty
				Meta0Item item = new Meta0Item(getValue(key));
				// returns get index
				return item.getIndex();
			}
			// if here, no object, returns default
			return UndefinedValues.INTEGER;
		}
	}

	/**
	 * This object is wrapping the node DATASET --> _META --> "0".
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	private static class Meta0Item extends NativeObjectContainer {

		/**
		 * Creates the item using a native java script object which contains all properties.
		 * 
		 * @param nativeObject native java script object which contains all properties.
		 */
		Meta0Item(NativeObject nativeObject) {
			super(nativeObject);
		}

		/**
		 * Returns the index of the data inside the dataset.
		 * 
		 * @return the index of the data inside the dataset. Default is
		 *         {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
		 */
		int getIndex() {
			// gets the controller object
			ControllerItem item = new ControllerItem(getValue(Property.controller));
			// returns index
			return item.getIndex();
		}

	}

	/**
	 * This object is wrapping the node DATASET --> _META --> "0" --> CONTROLLER.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	private static class ControllerItem extends NativeObjectContainer {

		/**
		 * Creates the item using a native java script object which contains all properties.
		 * 
		 * @param nativeObject native java script object which contains all properties.
		 */
		ControllerItem(NativeObject nativeObject) {
			super(nativeObject);
		}

		/**
		 * Returns the index of the data inside the dataset.
		 * 
		 * @return the index of the data inside the dataset. Default is
		 *         {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
		 */
		int getIndex() {
			// gets INDEX
			return getValue(Property.index, UndefinedValues.INTEGER);
		}

	}

}