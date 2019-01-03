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
package org.pepstock.charba.client.jsinterop.controllers;

import java.util.List;

import org.pepstock.charba.client.jsinterop.Chart;
import org.pepstock.charba.client.jsinterop.commons.ArrayInteger;
import org.pepstock.charba.client.jsinterop.commons.ArrayListHelper;
import org.pepstock.charba.client.jsinterop.commons.NativeName;
import org.pepstock.charba.client.jsinterop.items.UndefinedValues;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * This object stores the <code>this</code> instance of java script because is necessary to invoke the default methods of
 * controller when it's extending an existing chart.
 * 
 * @author Andrea "Stock" Stocchero
 * @version 2.0
 */
@JsType(isNative = true, name = NativeName.OBJECT, namespace = JsPackage.GLOBAL)
public final class Context {

	/**
	 * To avoid any instantiation
	 */
	Context() {
	}

	/**
	 * Returns the <code>chart</code> property by native object.
	 * 
	 * @return the <code>chart</code> property by native object.
	 */
	@JsProperty(name = "chart")
	static native Chart getNativeChart();

	/**
	 * Returns the <code>index</code> property by native object.
	 * 
	 * @return the <code>index</code> property by native object.
	 */
	@JsProperty(name = "index")
	static native int getNativeIndex();

	/**
	 * Returns the <code>_data</code> property by native object.
	 * 
	 * @return the <code>_data</code> property by native object.
	 */
	@JsProperty(name = "_data")
	static native ArrayInteger getNativeData();

	/**
	 * Returns the index of the data inside the dataset.
	 * 
	 * @return the index of the data inside the dataset.
	 */
	@JsOverlay
	public final int getIndex() {
		int value = getNativeIndex();
		return value == Double.NaN ? UndefinedValues.INTEGER : value;
	}

	/**
	 * Returns the CHARBA ID, set to the chart.
	 * 
	 * @return the CHARBA ID
	 */
	@JsOverlay
	public final String getCharbaId() {
		return getNativeChart().getCharbaId();
	}

	/**
	 * Returns an array of integer of data
	 * 
	 * @return an array of integer of data
	 */
	@JsOverlay
	public final List<Integer> getData() {
		ArrayInteger array = getNativeData();
		return ArrayListHelper.unmodifiableList(array);
	}
}