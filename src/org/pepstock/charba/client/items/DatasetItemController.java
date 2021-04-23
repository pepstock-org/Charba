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
 * This item provides all information about the dataset controller which has been displayed.<br>
 * This object has been created and passed to event handler or callbacks to apply own logic.<br>
 * This is a wrapper of the CHART.JS item with all needed info.<br>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class DatasetItemController extends NativeObjectContainer {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		INDEX("index"),
		INNER_RADIUS("innerRadius"),
		OUTER_RADIUS("outerRadius"),
		OFFSET_X("offsetX"),
		OFFSET_Y("offsetY");

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
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	DatasetItemController(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the dataset index.
	 * 
	 * @return the dataset index.
	 */
	public int getIndex() {
		return getValue(Property.INDEX, Undefined.INTEGER);
	}

	/**
	 * Returns the outer radius of dataset item in pixel.
	 * 
	 * @return the outer radius of dataset item in pixel.
	 */
	public double getOuterRadius() {
		return getValue(Property.OUTER_RADIUS, Undefined.DOUBLE);
	}

	/**
	 * Returns the inner radius of dataset item in pixel.
	 * 
	 * @return the inner radius of dataset item in pixel.
	 */
	public double getInnerRadius() {
		return getValue(Property.INNER_RADIUS, Undefined.DOUBLE);
	}

	/**
	 * Returns the offset X.
	 * 
	 * @return the offset X.
	 */
	public double getOffsetX() {
		return getValue(Property.OFFSET_X, Undefined.DOUBLE);
	}

	/**
	 * Returns the offset Y.
	 * 
	 * @return the offset Y.
	 */
	public double getOffsetY() {
		return getValue(Property.OFFSET_Y, Undefined.DOUBLE);
	}

	/**
	 * Returns a set of predefined style properties that should be used to represent the dataset or the data if the index is specified.
	 * 
	 * @param dataIndex index of data
	 * @return a set of predefined style properties that should be used to represent the dataset or the data if the index is specified
	 */
	public DatasetElementOptions getStyle(int dataIndex) {
		return new DatasetElementOptions(JsItemsHelper.get().getDatasetControllerStyle(getNativeObject(), dataIndex));
	}

}