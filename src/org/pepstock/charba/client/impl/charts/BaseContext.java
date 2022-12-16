/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.impl.charts;

import org.pepstock.charba.client.Charts;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.callbacks.ChartContext;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.enums.ContextType;

/**
 * Utility object which is creating a native object, setting chart and type, in order to be able to create a {@link ChartContext} without CHART.JS, for callbacks of meter and gauge
 * charts.
 * 
 * @author Andrea "Stock" Stocchero
 */
final class BaseContext extends NativeObjectContainer {

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
	 * @param chart chart instance
	 */
	BaseContext(IsChart chart) {
		super();
		// checks if chart instance is loaded
		Checker.assertCheck(Charts.hasNative(chart), "Chart argument does not have any related native chart instance");
		// stores the chart and type
		setValue(Property.CHART, Charts.getNative(chart));
		setValue(Property.TYPE, ContextType.METER);
	}

	/**
	 * Returns the native object instance.
	 * 
	 * @return the native object instance.
	 */
	NativeObject nativeObject() {
		return getNativeObject();
	}

}