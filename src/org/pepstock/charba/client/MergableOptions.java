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
package org.pepstock.charba.client;

import org.pepstock.charba.client.commons.NativeObject;

/**
 * This object is a container of chart options, stored as native object container.<br>
 * It can be instantiated ONLY by the merger.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class MergableOptions {

	// native instance of chart options
	private NativeObject nativeOptions = null;

	/**
	 * To avoid any instantiation
	 */
	MergableOptions() {
		// do nothing
	}

	/**
	 * Returns the native options of chart.
	 * 
	 * @return the native options of chart
	 */
	NativeObject getNativeOptions() {
		return nativeOptions;
	}

	/**
	 * Stores the native options into this container.
	 * 
	 * @param nativeOptions the native options instance to store
	 */
	public void setNativeOptions(NativeObject nativeOptions) {
		this.nativeOptions = nativeOptions;
	}

}
