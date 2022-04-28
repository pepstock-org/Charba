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
 * Simple Linear Regression is a type of Regression algorithms that models the relationship between a dependent variable and a single independent variable.<br>
 * The relationship shown by a Simple Linear Regression model is linear or a sloped straight line, hence it is called Simple Linear Regression.<br>
 * The key point in Simple Linear Regression is that the dependent variable must be a continuous/real value.<br>
 * However, the independent variable can be measured on continuous or categorical values.<br>
 * The Simple Linear Regression model can be represented using the below equation:<br>
 * <br>
 * <b style="font: italic bold 24px courier;">y = a<sub>0</sub> + a<sub>1</sub>x</b><br>
 * <br>
 * where<br>
 * <ul>
 * <li><b style="font: italic bold 24px courier;">a<sub>0</sub></b> is the intercept of the Regression line (can be obtained putting x=0)
 * <li><b style="font: italic bold 24px courier;">a<sub>1</sub></b> is the slope of the regression line, which tells whether the line is increasing or decreasing
 * </ul>
 * <br>
 * It maps <a href="https://github.com/mljs/regression-simple-linear">mljs/regression-simple-linear</a>.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.ML_SIMPLE_LINEAR_REGRESSION, namespace = JsPackage.GLOBAL)
class NativeLinearRegression extends NativeBaseLinearRegression {

	/**
	 * Creates the simple linear regression object, using the passed data to calculate the formula.
	 * 
	 * @param x values bound to x
	 * @param y values bound to y
	 */
	NativeLinearRegression(ArrayDouble x, ArrayDouble y) {
		// nothing
	}

	/**
	 * Creates new regression by a descriptor.
	 * 
	 * @param descriptor regression description used to create new regression
	 * @return new regression instance
	 */
	@JsMethod
	static native NativeLinearRegression load(RegressionDescriptor descriptor);

}
