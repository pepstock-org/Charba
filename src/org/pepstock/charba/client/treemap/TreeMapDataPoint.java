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
package org.pepstock.charba.client.treemap;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.items.Undefined;

/**
 * Used for treemap datasets. Each data point is related to a box drawn on treemap chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class TreeMapDataPoint extends NativeObjectContainer {

	/**
	 * Name of properties of native object.<br>
	 * No private because it is used by time series item
	 */
	private enum Property implements Key
	{
		X("x"),
		Y("y"),
		W("w"),
		H("h"),
		V("v"),
		S("s"),
		L("l"),
		G("g"),
		GS("gs");

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
	 * Creates the object with a native object passed as argument.
	 * 
	 * @param nativeObject native object which maps a data point
	 */
	TreeMapDataPoint(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns X value.
	 * 
	 * @return X value
	 */
	public double getX() {
		return getValue(Property.X, Undefined.DOUBLE);
	}

	/**
	 * Returns Y value.
	 * 
	 * @return Y value.
	 */
	public double getY() {
		return getValue(Property.Y, Undefined.DOUBLE);
	}
	
	/**
	 * Returns the width in pixel.
	 * 
	 * @return the width in pixel.
	 */
	public double getWidth() {
		return getValue(Property.W, Undefined.DOUBLE);
	}

	/**
	 * Returns the height in pixel.
	 * 
	 * @return the height in pixel.
	 */
	public double getHeight() {
		return getValue(Property.H, Undefined.DOUBLE);
	}

	/**
	 * Returns the value.
	 * 
	 * @return the value.
	 */
	public double getValue() {
		return getValue(Property.V, Undefined.DOUBLE);
	}

	/**
	 * Returns the sum.
	 * 
	 * @return the sum.
	 */
	public double getSum() {
		return getValue(Property.S, Undefined.DOUBLE);
	}

	/**
	 * Returns the depth, only available if grouping.
	 * 
	 * @return the depth, only available if grouping.
	 */
	public double getDepth() {
		return getValue(Property.L, Undefined.DOUBLE);
	}

	/**
	 * Returns the group name, only available if grouping.
	 * 
	 * @return the group name, only available if grouping.
	 */
	public String getGroup() {
		return getValue(Property.G, Undefined.STRING);
	}

	/**
	 * Returns the group sum, only available if grouping.
	 * 
	 * @return the group sum, only available if grouping.
	 */
	public double getGroupSum() {
		return getValue(Property.GS, Undefined.DOUBLE);
	}

}