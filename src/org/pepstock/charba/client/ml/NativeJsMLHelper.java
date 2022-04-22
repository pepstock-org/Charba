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

import org.pepstock.charba.client.commons.ArrayDouble;
import org.pepstock.charba.client.commons.NativeName;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * Internal utility for to use ML. Some methods of the classes are NOT mappable by externs files.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.JS_ML_HELPER, namespace = JsPackage.GLOBAL)
final class NativeJsMLHelper {

	/**
	 * To avoid any instantiation
	 */
	private NativeJsMLHelper() {
		// do nothing
	}

	/**
	 * Returns an array of Y values, calculated by the regression formula for specific X values.
	 * 
	 * @param regression regression native instance
	 * @param x values to use to get the predicted values
	 * @return an array of Y values, calculated by the regression formula for specific X values
	 */
	@JsMethod
	static native ArrayDouble predict(NativeBaseRegression regression, ArrayDouble x);

	/**
	 * Returns the formula of the regression, using the requested precision.
	 * 
	 * @param regression regression native instance
	 * @param precision precision to apply to the numbers of the formula
	 * @return the formula of the regression
	 */
	@JsMethod
	static native String toFormula(NativeBaseRegression regression, int precision);
}
