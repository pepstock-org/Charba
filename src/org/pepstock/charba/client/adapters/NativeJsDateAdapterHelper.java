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

import org.pepstock.charba.client.commons.NativeName;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.items.ScaleItem;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * This is a singleton wrapper for Java native object which is wrapping a CHARBA java script object implementation to create a date adapter from CHART.JS. This wrapper is necessary
 * to ensure that script is injected with CHART.JS.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.JS_DATE_ADAPTER_HELPER, namespace = JsPackage.GLOBAL)
final class NativeJsDateAdapterHelper {

	/**
	 * To avoid any instantiation
	 */
	private NativeJsDateAdapterHelper() {
		// do nothing
	}

	/**
	 * Returns a date adapter instance, using the options passed as argument.
	 * 
	 * @param options date adapter options.
	 * @return a date adapter instance.
	 */
	static native NativeDateAdapter create(NativeObject options);

	/**
	 * Returns a date adapter instance, using the {@link ScaleItem} passed as argument.
	 * 
	 * @param scale scale item, {@link ScaleItem}.
	 * @return a date adapter instance.
	 */
	static native NativeDateAdapter retrieve(NativeObject scale);

	/**
	 * Returns a epoch time in millisecond by a year and a weeks.
	 *
	 * @param weekYear the year of the week
	 * @param weekNumber the week in the year
	 * @param options options to configure date adapter
	 * @return calculated epoch time in millisecond
	 */
	static native double getEpochByWeek(int weekYear, int weekNumber, NativeObject options);

	/**
	 * Returns a epoch time in millisecond by a year and a ordinal.
	 *
	 * @param year the year of the week
	 * @param ordinal the ordinal day in the year
	 * @param options options to configure date adapter
	 * @return calculated epoch time in millisecond
	 */
	static native double getEpochByOrdinal(int year, int ordinal, NativeObject options);

}