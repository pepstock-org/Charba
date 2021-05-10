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
package org.pepstock.charba.client.zoom;

import org.pepstock.charba.client.commons.AbstractPoint;
import org.pepstock.charba.client.commons.NativeObject;

/**
 * This object is wrapping the native java script object to set the position to pan the chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class Amount extends AbstractPoint {

	/**
	 * Creates an empty object
	 */
	public Amount() {
		super();
	}

	/**
	 * Creates an object with pan amount for x scale.
	 * 
	 * @param x the X amount of pan
	 */
	public Amount(double x) {
		super(x);
	}

	/**
	 * Creates an object with pan amount for x and y scales.
	 * 
	 * @param x the X amount of pan
	 * @param y the Y amount of pan
	 */
	public Amount(double x, double y) {
		super(x, y);
	}

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 */
	Amount(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the native object instance.
	 * 
	 * @return the native object instance.
	 */
	NativeObject nativeObject() {
		return getNativeObject();
	}

}