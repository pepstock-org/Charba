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
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Polynomial Regression is a regression algorithm that models the relationship between a dependent(y) and independent variable(x) as nth degree polynomial.<br>
 * The Polynomial Regression equation is given below:<br>
 * <br>
 * <b style="font: italic bold 24px courier;">y = b<sub>0</sub> + b<sub>1</sub>x<sub>1</sub> + b<sub>2</sub>x<sub>1</sub><sup style="font: italic bold 20px courier;">2</sup> +
 * b<sub>2</sub>x<sub>1</sub><sup style="font: italic bold 20px courier;">3</sup> +...... b<sub>n</sub>x<sub>1</sub><sup style="font: italic bold 20px courier;">n</sup></b><br>
 * <br>
 * It maps <a href="https://github.com/mljs/regression-polynomial">mljs/regression-polynomial</a>.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.ML_POLYNOMIAL_REGRESSION, namespace = JsPackage.GLOBAL)
final class NativePolynomialRegression extends NativeBaseRegression {

	/**
	 * Creates the polynomial regression object, using the passed data to calculate the formula.
	 * 
	 * @param x values bound to x
	 * @param y values bound to y
	 * @param degree the maximum degree of the polynomial
	 */
	NativePolynomialRegression(ArrayDouble x, ArrayDouble y, int degree) {
		// nothing
	}

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

	/**
	 * Creates new regression by a descriptor.
	 * 
	 * @param descriptor regression description used to create new regression
	 * @return new regression instance
	 */
	@JsMethod
	static native NativePolynomialRegression load(RegressionDescriptor descriptor);

}
