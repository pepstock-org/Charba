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

import org.pepstock.charba.client.callbacks.CallbacksEnvelop;
import org.pepstock.charba.client.commons.IsEnvelop;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;

/**
 * It wraps the parsed data values for the given item point.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class DatasetPoint extends NativeObjectContainer {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		X("x"),
		Y("y");

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
	 * Creates the object with a native object passed as argument.
	 * 
	 * @param envelop envelop of native java script object which contains all properties.
	 */
	public DatasetPoint(CallbacksEnvelop<NativeObject> envelop) {
		super(IsEnvelop.checkAndGetIfValid(envelop).getContent());
	}

	/**
	 * Creates the object with a native object passed as argument.
	 * 
	 * @param nativeObject native object which maps a data point
	 */
	DatasetPoint(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns X value.
	 * 
	 * @return X value
	 */
	public double getX() {
		return getValue(Property.X, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns Y value.
	 * 
	 * @return Y value.
	 */
	public double getY() {
		return getValue(Property.Y, UndefinedValues.DOUBLE);
	}

}