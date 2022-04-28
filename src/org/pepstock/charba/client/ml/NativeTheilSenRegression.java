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
 * In TheilSen Regression, the estimation of the model is done by calculating the slopes and intercepts of a subpopulation of all possible combinations of p subsample points.<br>
 * If an intercept is fitted, p must be greater than or equal to n_features + 1.<br>
 * The final slope and intercept is then defined as the spatial median of these slopes and intercepts.<br>
 * The TheilSen Regression model can be represented using the below equation:<br>
 * <br>
 * <b style="font: italic bold 24px courier;">y = a<sub>0</sub> + a<sub>1</sub>x</b><br>
 * <br>
 * where<br>
 * <ul>
 * <li><b style="font: italic bold 24px courier;">a<sub>0</sub></b> is the intercept of the Regression line (can be obtained putting x=0)
 * <li><b style="font: italic bold 24px courier;">a<sub>1</sub></b> is the slope of the regression line, which tells whether the line is increasing or decreasing
 * </ul>
 * <br>
 * It maps <a href="https://github.com/mljs/regression-theil-sen">mljs/regression-theil-sen</a>.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.ML_THEIL_SEN_REGRESSION, namespace = JsPackage.GLOBAL)
final class NativeTheilSenRegression extends NativeBaseLinearRegression {

	/**
	 * Creates the TheilSen regression object, using the passed data to calculate the formula.
	 * 
	 * @param x values bound to x
	 * @param y values bound to y
	 */
	NativeTheilSenRegression(ArrayDouble x, ArrayDouble y) {
		// nothing
	}

	/**
	 * Creates new regression by a descriptor.
	 * 
	 * @param descriptor regression description used to create new regression
	 * @return new regression instance
	 */
	@JsMethod
	static native NativeTheilSenRegression load(RegressionDescriptor descriptor);
}
