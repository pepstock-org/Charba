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

import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.items.DatasetElement;

/**
 * Element used by controller by <code>removeHoverStyle</code> and <code>setHoverStyle</code> methods.<br>
 * It contains information about the dataset element.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ControllerDatasetElement extends DatasetElement {

	/**
	 * Wraps the CHART.JS java script object.
	 * 
	 * @param nativeObject CHART.JS java script object
	 */
	ControllerDatasetElement(NativeObject nativeObject) {
		super(new ControllersEnvelop<>(nativeObject, true));
	}

	/**
	 * Returns the native object of this element.
	 * 
	 * @return the native object.
	 */
	NativeObject nativeObject() {
		return super.getNativeObject();
	}

}
