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

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Base class for Polynomial Regression which is a regression algorithm that models the relationship between a dependent(y) and independent variable(x) as nth degree polynomial.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.OBJECT, namespace = JsPackage.GLOBAL)
class NativeBasePolynomialRegression extends NativeBaseRegression {

	/**
	 * Returns the maximum degree of the polynomial.
	 * 
	 * @return the maximum degree of the polynomial
	 */
	@JsProperty
	native int getDegree();

	/**
	 * Returns the powers coefficient.
	 * 
	 * @return the powers coefficient
	 */
	@JsProperty
	native ArrayDouble getPowers();

	/**
	 * Returns all calculated coefficients as an array, in increasing order of power (from 0 to degree).
	 * 
	 * @return all calculated coefficients as an array
	 */
	@JsProperty
	native ArrayDouble getCoefficients();

}
