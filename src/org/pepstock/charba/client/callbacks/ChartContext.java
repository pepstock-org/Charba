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
import org.pepstock.charba.client.enums.ContextType;

/**
 * Abstract implementation to map the context used as argument on options, data sets, scales and plugins callbacks.<br>
 * All context implementations have got a property which is a reference to the chart instance.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class ChartContext extends NativeObjectContainer{

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		CHART("chart"),
		TYPE("type");

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
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 */
	protected ChartContext(NativeObject nativeObject) {
		super(nativeObject);
		// checks if object is consistent
		if (nativeObject == null) {
			throw new IllegalArgumentException("Native context argument is null");
		}
		// the chart must be there
		if (!has(Property.CHART)) {
			// if not, exception
			throw new IllegalArgumentException("Unable to retrieve the chart instance and the context does not seem to be consistent");
		}
	}

	/**
	 * Returns the CHARBA chart instance.
	 * 
	 * @return the CHARBA chart instance
	 */
	public final IsChart getChart() {
		return super.getNativeChart(Property.CHART).getChart();
	}

	/**
	 * Returns the type of the context.
	 * 
	 * @return the type of the context
	 */
	public final ContextType getType() {
		return getValue(Property.TYPE, ContextType.values(), ContextType.UNKNOWN);
	}
	
	/**
	 * Returns <code>true</code> if the context is consistent.<br>
	 * Custom context (the plugin ones) should extend it and check if the context is consistent before invoking the callback.
	 * 
	 * @return <code>true</code> if the context is consistent
	 */
	protected boolean isConsistent() {
		return true;
	}

}