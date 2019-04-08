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
package org.pepstock.charba.client.controllers;

import java.util.List;

import org.pepstock.charba.client.Chart;
import org.pepstock.charba.client.ChartNode;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.NativeName;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.items.UndefinedValues;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * This object stores the <code>this</code> instance of java script because is necessary to invoke the default methods of
 * controller when it's extending an existing chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
@JsType(isNative = true, name = NativeName.OBJECT, namespace = JsPackage.GLOBAL)
public final class ControllerContext {

	/**
	 * To avoid any instantiation
	 */
	ControllerContext() {
	}

	/**
	 * Returns the <code>chart</code> property by native object.
	 * 
	 * @return the <code>chart</code> property by native object.
	 */
	@JsProperty(name = "chart")
	native Chart getNativeChart();

	/**
	 * Sets the <code>chart</code> property by native object.
	 * 
	 * @param chart the <code>chart</code> property by native object.
	 */
	@JsProperty(name = "chart")
	native void setNativeChart(Chart chart);

	/**
	 * Returns the <code>index</code> property by native object.
	 * 
	 * @return the <code>index</code> property by native object.
	 */
	@JsProperty(name = "index")
	native int getNativeIndex();

	/**
	 * Returns the <code>_data</code> property by native object.
	 * 
	 * @return the <code>_data</code> property by native object.
	 */
	@JsProperty(name = "_data")
	native ArrayInteger getNativeData();

	/**
	 * Returns the index of the data inside the dataset.
	 * 
	 * @return the index of the data inside the dataset.
	 */
	@JsOverlay
	public final int getIndex() {
		// checks if there is the property
		if (ObjectType.UNDEFINED.equals(JsHelper.get().typeOf(this, "index"))) {
			return UndefinedValues.INTEGER;
		}
		return getNativeIndex();
	}

	/**
	 * Returns the CHARBA chart instance.
	 * 
	 * @return the CHARBA chart instance
	 */
	@JsOverlay
	public final IsChart getChart() {
		return getNativeChart().getChart();
	}

	/**
	 * Returns the chart node with runtime data.
	 * 
	 * @return the chart node.
	 */
	@JsOverlay
	public final ChartNode getNode() {
		return new ChartNode(getNativeChart());
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