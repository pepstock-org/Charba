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
package org.pepstock.charba.client.callbacks;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.ObjectType;

/**
 * Abstract implementation to map the context used as argument on options, datasets and scales callback.<br>
 * All context implementation have go a property which is a reference to the chart instance.
 * 
 * @author Andrea "Stock" Stocchero
 */
abstract class AbstractScriptableContext extends NativeObjectContainer {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		CHART("chart");

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
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 */
	AbstractScriptableContext(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the CHARBA chart instance.
	 * 
	 * @return the CHARBA chart instance
	 */
	public final IsChart getChart() {
		// checks if chart is inside the context
		if (ObjectType.OBJECT.equals(type(Property.CHART))) {
			return getNativeChart(Property.CHART).getChart();
		}
		// if here the context is not consistent
		// returns null
		return null;
	}

}