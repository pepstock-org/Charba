/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.ml;

import org.pepstock.charba.client.commons.ArrayDouble;
import org.pepstock.charba.client.commons.NativeName;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * Native base regression class with common methods for all regressions.<br>
 * It maps <a href="https://github.com/mljs/regression-base">mljs/regression-base</a>.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.ML_BASE_REGRESSION, namespace = JsPackage.GLOBAL)
class NativeBaseRegression {

	/**
	 * Returns the Y value, calculated by the regression formula at specific X value.
	 * 
	 * @param x value to use to get the predicted value
	 * @return the Y value, calculated by the regression formula at specific X value
	 */
	@JsMethod
	native double predict(double x);

	/**
	 * Evaluates the regression formula if the model fits enough.
	 * 
	 * @param x X values to use for evaluation
	 * @param y Y values to use for evaluation
	 * @return the score of the regression
	 */
	@JsMethod
	native RegressionScore score(ArrayDouble x, ArrayDouble y);

	/**
	 * Returns the formula of the regression, using the requested precision.
	 * 
	 * @param precision precision to apply to the numbers of the formula
	 * @return the formula of the regression
	 */
	@JsMethod
	native String toLaTeX(int precision);

	/**
	 * Returns the regression descriptor.
	 * 
	 * @return the regression descriptor
	 */
	native RegressionDescriptor toJSON();

}