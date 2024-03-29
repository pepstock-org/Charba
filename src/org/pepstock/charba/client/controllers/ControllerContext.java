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
package org.pepstock.charba.client.controllers;

import org.pepstock.charba.client.Chart;
import org.pepstock.charba.client.ChartNode;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.NativeName;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.items.Undefined;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * This object stores the <code>this</code> instance of java script because is necessary to invoke the default methods of controller when it's extending an existing chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
@JsType(isNative = true, name = NativeName.CHART_CONTROLLER_CONTEXT, namespace = JsPackage.GLOBAL)
public final class ControllerContext {

	/**
	 * To avoid any instantiation
	 */
	ControllerContext() {
		// do nothing
	}

	/**
	 * Returns the <code>chart</code> property by native object.
	 * 
	 * @return the <code>chart</code> property by native object.
	 */
	@JsProperty(name = "chart")
	native Chart getNativeChart();

	/**
	 * Returns the <code>index</code> property by native object.
	 * 
	 * @return the <code>index</code> property by native object.
	 */
	@JsProperty(name = "index")
	native int getNativeIndex();

	/**
	 * Returns the index of the data inside the dataset.
	 * 
	 * @return the index of the data inside the dataset.
	 */
	@JsOverlay
	public final int getIndex() {
		// checks if there is the property
		if (ObjectType.UNDEFINED.equals(JsHelper.get().typeOf(this, "index"))) {
			return Undefined.INTEGER;
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
		// gets native chart
		Chart nativeChart = getNativeChart();
		// gets chart
		IsChart chart = nativeChart.getChart();
		// creates and returns a char node
		return new ChartNode(chart.getId(), nativeChart);
	}

}