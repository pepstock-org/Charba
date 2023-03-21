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
package org.pepstock.charba.client.adapters;

import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.items.ScaleItem;
import org.pepstock.charba.client.resources.ResourcesType;

/**
 * This is a singleton wrapper for Java native object which is wrapping a CHARBA java script object implementation to create a date adapter from CHART.JS. This wrapper is necessary
 * to ensure that script is injected with CHART.JS.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class JsDateAdapterHelper {
	// static instance for singleton
	private static final JsDateAdapterHelper INSTANCE = new JsDateAdapterHelper();

	/**
	 * To avoid any instantiation
	 */
	private JsDateAdapterHelper() {
		// to be sure that CHARBA java script object is injected
		// invoking the JsHelper
		// PAY ATTENTION: MUST be called before injecting CHART.JS
		JsHelper.get();
		// to be sure that CHART.JS java script object is injected
		// some methods are calling CHART.JS for this reason is mandatory
		// to include also chart.js
		// inject Chart.js and date library if not already loaded
		ResourcesType.getResources().inject();
	}

	/**
	 * Singleton object to get the helper instance
	 * 
	 * @return helper instance.
	 */
	static JsDateAdapterHelper get() {
		return INSTANCE;
	}

	/**
	 * Returns a date adapter instance, using the options passed as argument.
	 * 
	 * @param options date adapter options.
	 * @return a date adapter instance.
	 */
	NativeDateAdapter create(DateAdapterOptions options) {
		return NativeJsDateAdapterHelper.create(options.nativeObject());
	}

	/**
	 * Returns a date adapter instance, using the scale passed as argument.
	 * 
	 * @param scale date adapter of {@link ScaleItem}.
	 * @return a date adapter instance.
	 */
	NativeDateAdapter retrieve(NativeObject scale) {
		// checks if scale instance is consistent
		if (scale != null) {
			// retrieves adapter
			return NativeJsDateAdapterHelper.retrieve(scale);
		}
		// inconsistent scale
		// then returns null
		return null;
	}

	/**
	 * Returns a epoch time in millisecond by a year and a weeks.
	 *
	 * @param weekYear the year of the week
	 * @param weekNumber the week in the year
	 * @param options options to configure date adapter
	 * @return calculated epoch time in millisecond
	 */
	double getEpochByWeek(int weekYear, int weekNumber, DateAdapterOptions options) {
		return NativeJsDateAdapterHelper.getEpochByWeek(weekYear, weekNumber, options.nativeObject());
	}

	/**
	 * Returns a epoch time in millisecond by a year and a ordinal.
	 *
	 * @param year the year of the week
	 * @param ordinal the ordinal day in the year
	 * @param options options to configure date adapter
	 * @return calculated epoch time in millisecond
	 */
	double getEpochByOrdinal(int year, int ordinal, DateAdapterOptions options) {
		return NativeJsDateAdapterHelper.getEpochByOrdinal(year, ordinal, options.nativeObject());
	}
}