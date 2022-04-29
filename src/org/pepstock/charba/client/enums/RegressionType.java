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
package org.pepstock.charba.client.enums;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.ml.ExponentialRegression;
import org.pepstock.charba.client.ml.LinearRegression;
import org.pepstock.charba.client.ml.PolynomialRegression;
import org.pepstock.charba.client.ml.PowerRegression;
import org.pepstock.charba.client.ml.RobustPolynomialRegression;
import org.pepstock.charba.client.ml.TheilSenRegression;

/**
 * Enumerates the available regression types.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum RegressionType implements Key
{
	/**
	 * Identify a simple linear regression, {@link LinearRegression}.
	 */
	LINEAR("simpleLinearRegression"),
	/**
	 * Identify a polynomial regression, {@link PolynomialRegression}.
	 */
	POLYNOMIAL("polynomialRegression"),
	/**
	 * Identify a power regression, {@link PowerRegression}.
	 */
	POWER("powerRegression"),
	/**
	 * Identify an exponential regression, {@link ExponentialRegression}.
	 */
	EXPONENTIAL("exponentialRegression"),
	/**
	 * Identify an TheilSen regression, {@link TheilSenRegression}.
	 */
	THEIL_SEN("TheilSenRegression"),
	/**
	 * Identify a robust polynomial regression, {@link RobustPolynomialRegression}.
	 */
	ROBUST_POLYNOMIAL("robustPolynomialRegression");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private RegressionType(String value) {
		this.value = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.Key#value()
	 */
	@Override
	public String value() {
		return value;
	}
}
