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
 * Robust regression is a form of regression analysis designed to overcome some limitations of traditional parametric and non-parametric methods.<br>
 * Regression analysis seeks to find the relationship between one or more independent variables and a dependent variable. Certain widely used methods of regression, such as
 * ordinary least squares, have favourable properties if their underlying assumptions are true, but can give misleading results if those assumptions are not true; thus ordinary
 * least squares is said to be not robust to violations of its assumptions.<br>
 * Robust regression methods are designed to be not overly affected by violations of assumptions by the underlying data-generating process.<br>
 * <br>
 * In particular, least squares estimates for regression models are highly sensitive to outliers.<br>
 * While there is no precise definition of an outlier, outliers are observations that do not follow the pattern of the other observations.<br>
 * This is not normally a problem if the outlier is simply an extreme observation drawn from the tail of a normal distribution, but if the outlier results from non-normal
 * measurement error or some other violation of standard ordinary least squares assumptions, then it compromises the validity of the regression results if a non-robust regression
 * technique is used.<br>
 * The Polynomial Regression equation is given below:<br>
 * <br>
 * <b style="font: italic bold 24px courier;">y = b<sub>0</sub> + b<sub>1</sub>x<sub>1</sub> + b<sub>2</sub>x<sub>1</sub><sup style="font: italic bold 20px courier;">2</sup> +
 * b<sub>2</sub>x<sub>1</sub><sup style="font: italic bold 20px courier;">3</sup> +...... b<sub>n</sub>x<sub>1</sub><sup style="font: italic bold 20px courier;">n</sup></b><br>
 * <br>
 * It maps <a href="https://github.com/mljs/regression-robust-polynomial">mljs/regression-robust-polynomial</a>.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.ML_ROBUST_POLYNOMIAL_REGRESSION, namespace = JsPackage.GLOBAL)
final class NativeRobustPolynomialRegression extends NativeBasePolynomialRegression {

	/**
	 * Creates the robust polynomial regression object, using the passed data to calculate the formula.
	 * 
	 * @param x values bound to x
	 * @param y values bound to y
	 * @param degree the maximum degree of the polynomial
	 */
	// Ignores SonarCloud issue, java:S1172 - Unused method parameters should be removed, because this is the way of JSINTEROP to invoke a JavaScript constructor.
	@SuppressWarnings("java:S1172")
	NativeRobustPolynomialRegression(ArrayDouble x, ArrayDouble y, int degree) {
		// nothing
	}

	/**
	 * Creates new regression by a descriptor.
	 * 
	 * @param descriptor regression description used to create new regression
	 * @return new regression instance
	 */
	@JsMethod
	static native NativeRobustPolynomialRegression load(RegressionDescriptor descriptor);

}
