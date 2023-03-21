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
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Base class for Linear Regression which is a type of Regression algorithms that models the relationship between a dependent variable and a single independent variable.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.OBJECT, namespace = JsPackage.GLOBAL)
class NativeBaseLinearRegression extends NativeBaseRegression {

	/**
	 * Returns the slope coefficient.
	 * 
	 * @return the slope coefficient
	 */
	@JsProperty
	native double getSlope();

	/**
	 * Returns the intercept coefficient.
	 * 
	 * @return the intercept coefficient
	 */
	@JsProperty
	native double getIntercept();

	/**
	 * Returns all calculated coefficients as an array.
	 * 
	 * @return all calculated coefficients as an array
	 */
	@JsProperty
	native ArrayDouble getCoefficients();

	/**
	 * Returns a calculated X value by the Y value.
	 * 
	 * @param y Y value to use to calculate the X value
	 * @return a calculated X value by the Y value
	 */
	@JsMethod
	native double computeX(double y);

}