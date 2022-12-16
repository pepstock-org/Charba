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
package org.pepstock.charba.client;

import org.pepstock.charba.client.commons.NativeName;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.dom.events.NativeBaseEvent;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * This is the java script native object which is the helpers utility of CHART.JS.<br>
 * It maps the java script object <code>chart.helpers</code>.
 * 
 * @author Andrea "Stock" Stocchero
 */
@JsType(isNative = true, name = NativeName.CHART_HELPERS, namespace = JsPackage.GLOBAL)
final class NativeHelpers {

	/**
	 * Recursively deep copies source properties in the target only if not defined in target.<br>
	 * IMPORTANT: target is not cloned and will be updated with source properties.
	 * 
	 * @param target the target object in which all sources are merged into.
	 * @param source object to merge in the target.
	 * @return the target object
	 */
	@JsMethod
	native NativeObject mergeIf(NativeObject target, NativeObject source);

	/**
	 * Returns a deep copy of source without keeping references on objects and arrays.
	 * 
	 * @param source the value to clone.
	 * @return a clone of source object
	 */
	@JsMethod
	native NativeObject clone(NativeObject source);

	/**
	 * A common occurrence is taking an event, such as a click, and finding the data coordinates on the chart where the event occurred. It provides the relative point on canvas.
	 * 
	 * @param event native event to be used for getting the point.
	 * @param chart chart instance
	 * @return a point object
	 */
	@JsMethod
	native NativeObject getRelativePosition(NativeBaseEvent event, Chart chart);

	/**
	 * Parses font options and returns the font object.
	 * 
	 * @param font a object that contains font options to be parsed.
	 * @return a font object
	 */
	@JsMethod
	native NativeObject toFont(NativeObject font);

	/**
	 * Format a number using a localized number formatter.
	 * 
	 * @param number the number to format
	 * @param locale the locale to pass to the <code>Intl.NumberFormat</code> constructor
	 * @param options <code>Intl</code> number format options
	 * @return a number formatted string
	 */
	@JsMethod
	native String formatNumber(double number, String locale, NativeObject options);

}