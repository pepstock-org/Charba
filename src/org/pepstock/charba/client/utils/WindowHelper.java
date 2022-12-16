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
package org.pepstock.charba.client.utils;

import org.pepstock.charba.client.Chart;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.commons.ArrayChart;

import jsinterop.annotations.JsFunction;

/**
 * This is a singleton with some utilities to act on <code>window</code> java script object.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class WindowHelper {

	// ---------------------------
	// -- JAVASCRIPT FUNCTIONS ---
	// ---------------------------

	/**
	 * Java script FUNCTION callback to set to <code>onbeforeprint</code> Window object property.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface OnBeforePrintCallback {

		/**
		 * Method of function to be called on before print.
		 * 
		 * @param event event object
		 */
		void call(Object event);
	}

	// static instance for singleton
	private static final WindowHelper INSTANCE = new WindowHelper();

	private boolean enableResizeOnBeforePrint = false;

	/**
	 * To avoid any instantiation
	 */
	private WindowHelper() {
	}

	/**
	 * Singleton object to get the helper instance
	 * 
	 * @return helper instance.
	 */
	static WindowHelper get() {
		return INSTANCE;
	}

	/**
	 * CSS media queries allow changing styles when printing a page. The CSS applied from these media queries may cause charts to need to resize. However, the resize won't happen
	 * automatically. To support resizing charts when printing, one needs to hook the <code>onbeforeprint</code> event and manually trigger resizing of each chart.
	 * 
	 */
	void enableResizeOnBeforePrint() {
		// checks if already set
		if (!enableResizeOnBeforePrint) {
			// if not, set the resizing function
			Window.onBeforePrint(event -> onResize());
			// sets the flag
			enableResizeOnBeforePrint = true;
		}
	}

	/**
	 * Resizes all active chart instances before printing.s
	 */
	private void onResize() {
		// gets current instances
		ArrayChart charts = Chart.getInstances();
		// checks if array is consistent
		if (charts != null) {
			// scans charts
			for (int i = 0; i < charts.length(); i++) {
				// gets CHARBA chart
				IsChart charbaChart = charts.get(i).getChart();
				// checks if consistent
				if (IsChart.isValid(charbaChart)) {
					// invokes resize
					charbaChart.resize();
				}
			}
		}
	}

}