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
 * Power regression is a non-linear regression technique that looks like this:<br>
 * <br>
 * <b style="font: italic bold 24px courier;">y = A * x<sup>B</sup></b><br>
 * <br>
 * It maps <a href="https://github.com/mljs/regression-power">mljs/regression-power</a>.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.ML_POWER_REGRESSION, namespace = JsPackage.GLOBAL)
final class NativePowerRegression extends NativeBaseRegression {

	/**
	 * Creates the power regression object, using the passed data to calculate the formula.
	 * 
	 * @param x values bound to x
	 * @param y values bound to y
	 */
	// Ignores SonarCloud issue, java:S1172 - Unused method parameters should be removed, because this is the way of JSINTEROP to invoke a JavaScript constructor.
	@SuppressWarnings("java:S1172")
	NativePowerRegression(ArrayDouble x, ArrayDouble y) {
		// nothing
	}

	/**
	 * Returns the A coefficient.
	 * 
	 * @return the A coefficient
	 */
	@JsProperty(name = "A")
	native double getA();

	/**
	 * Returns the B coefficient.
	 * 
	 * @return the B coefficient
	 */
	@JsProperty(name = "B")
	native double getB();

	/**
	 * Creates new regression by a descriptor.
	 * 
	 * @param descriptor regression description used to create new regression
	 * @return new regression instance
	 */
	@JsMethod
	static native NativePowerRegression load(RegressionDescriptor descriptor);

}
