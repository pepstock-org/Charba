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
package org.pepstock.charba.client.jsinterop;

import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.defaults.globals.DefaultOptions;
import org.pepstock.charba.client.jsinterop.options.Options;

/**
 * This object is mapping the default options related to the chart type.
 * 
 * @author Andrea "Stock" Stocchero
 * @version 2.0
 */
public final class ChartOptions extends Options {

	/**
	 * Creates the object with a empty native object.
	 */
	ChartOptions() {
		this(null);
	}

	/**
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	ChartOptions(NativeObject nativeObject) {
		// the default of chart default ones are the CHART.JS one
		super(DefaultOptions.get(), nativeObject);
	}
}
