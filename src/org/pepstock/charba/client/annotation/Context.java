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
package org.pepstock.charba.client.annotation;

import org.pepstock.charba.client.Chart;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.commons.NativeName;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * The callback or handler context, created and passed by {@link AnnotationPlugin#ID} which contains the link to the native
 * chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
@JsType(isNative = true, name = NativeName.OBJECT, namespace = JsPackage.GLOBAL)
final class Context {

	/**
	 * To avoid any instantiation
	 */
	private Context() {
	}

	/**
	 * Returns the <code>chart</code> property by native object.
	 * 
	 * @return the <code>chart</code> property by native object.
	 */
	@JsProperty(name = "chartInstance")
	native Chart getNativeChart();

	/**
	 * Returns the CHARBA chart instance.
	 * 
	 * @return the CHARBA chart instance
	 */
	@JsOverlay
	IsChart getChart() {
		// checks if native chart is present
		if (getNativeChart() != null) {
			// returns is chart instance
			return getNativeChart().getChart();
		}
		// if here, the native chart is not consistent
		return null;
	}
}