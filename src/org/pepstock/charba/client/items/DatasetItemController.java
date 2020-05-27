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
 * FIXME This item provides all information about the view where a dataset has been displayed.<br>
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
		// all charts
		INDEX("index"),
		INNER_RADIUS("innerRadius"),
		OUTER_RADIUS("outerRadius"),
		OFFSET_X("offsetX"),
		OFFSET_Y("offsetY");

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
	 * @return the dataset index. Default is {@link UndefinedValues#INTEGER}.
	 */
	public int getIndex() {
		return getValue(Property.INDEX, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the outer radius of dataset item in pixel.
	 * 
	 * @return the outer radius of dataset item in pixel. Default is {@link UndefinedValues#DOUBLE}.
	 */
	public double getOuterRadius() {
		return getValue(Property.OUTER_RADIUS, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the inner radius of dataset item in pixel.
	 * 
	 * @return the inner radius of dataset item in pixel. Default is {@link UndefinedValues#DOUBLE}.
	 */
	public double getInnerRadius() {
		return getValue(Property.INNER_RADIUS, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the offset X.
	 * 
	 * @return the offset X. Default is {@link UndefinedValues#DOUBLE}.
	 */
	public double getOffsetX() {
		return getValue(Property.OFFSET_X, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the offset Y.
	 * 
	 * @return the offset Y. Default is {@link UndefinedValues#DOUBLE}.
	 */
	public double getOffsetY() {
		return getValue(Property.OFFSET_Y, UndefinedValues.DOUBLE);
	}
}