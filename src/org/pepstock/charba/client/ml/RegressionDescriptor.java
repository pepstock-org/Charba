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
package org.pepstock.charba.client.ml;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeName;
import org.pepstock.charba.client.enums.RegressionType;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * It maps a regression and it can be used to clone or create new instance.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.OBJECT, namespace = JsPackage.GLOBAL)
public final class RegressionDescriptor {

	/**
	 * To avoid any instantiation
	 */
	private RegressionDescriptor() {
		// do nothing
	}

	/**
	 * Returns the name of regression.
	 * 
	 * @return the name of regression
	 */
	@JsProperty
	private native String getName();

	/**
	 * Returns the regression type of the descriptor.
	 * 
	 * @return the regression type of the descriptor
	 */
	@JsOverlay
	public RegressionType getType() {
		return Key.checkAndGetIfValid(Key.getKeyByValue(RegressionType.values(), getName()));
	}
}
