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

import java.util.List;

import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.enums.RegressionType;

/**
 * Polynomial Regression is a regression algorithm that models the relationship between a dependent(y) and independent variable(x) as nth degree polynomial.<br>
 * The Polynomial Regression equation is given below:<br>
 * <br>
 * <b style="font: italic bold 24px courier;">y = b<sub>0</sub> + b<sub>1</sub>x<sub>1</sub> + b<sub>2</sub>x<sub>1</sub><sup style="font: italic bold 20px courier;">2</sup> +
 * b<sub>2</sub>x<sub>1</sub><sup style="font: italic bold 20px courier;">3</sup> +...... b<sub>n</sub>x<sub>1</sub><sup style="font: italic bold 20px courier;">n</sup></b><br>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class BasePolynomialRegression<T extends NativeBasePolynomialRegression> extends BaseRegression<T> {

	/**
	 * Creates the object storing the native regression
	 * 
	 * @param type regression type
	 * @param nativeRegression native regression instance
	 */
	BasePolynomialRegression(RegressionType type, T nativeRegression) {
		super(type, nativeRegression);
	}

	/**
	 * Returns the maximum degree of the polynomial.
	 * 
	 * @return the maximum degree of the polynomial
	 */
	public final int getDegree() {
		return getNativeBaseRegression().getDegree();
	}

	/**
	 * Returns the powers coefficient.
	 * 
	 * @return the powers coefficient
	 */
	public final List<Double> getPowers() {
		return ArrayListHelper.unmodifiableList(getNativeBaseRegression().getPowers());
	}

	/**
	 * Returns all calculated coefficients as an array, in increasing order of power (from 0 to degree).
	 * 
	 * @return all calculated coefficients as an array
	 */
	@Override
	public final List<Double> getCoefficients() {
		return ArrayListHelper.unmodifiableList(getNativeBaseRegression().getCoefficients());
	}

}
