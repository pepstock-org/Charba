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

import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.enums.RegressionType;

/**
 * Enumerates the available regression types.
 * 
 * @author Andrea "Stock" Stocchero
 */
enum RegressionFactory
{
	/**
	 * Identify a simple linear regression, {@link LinearRegression}.
	 */
	LINEAR(RegressionType.LINEAR, descriptor -> new LinearRegression(descriptor)),
	/**
	 * Identify a polynomial regression, {@link PolynomialRegression}.
	 */
	POLYNOMIAL(RegressionType.POLYNOMIAL, descriptor -> new PolynomialRegression(descriptor)),
	/**
	 * Identify a power regression, {@link PowerRegression}.
	 */
	POWER(RegressionType.POWER, descriptor -> new PowerRegression(descriptor)),
	/**
	 * Identify an exponential regression, {@link ExponentialRegression}.
	 */
	EXPONENTIAL(RegressionType.EXPONENTIAL, descriptor -> new ExponentialRegression(descriptor)),
	/**
	 * Identify an TheilSen regression, {@link TheilSenRegression}.
	 */
	THEIL_SEN(RegressionType.THEIL_SEN, descriptor -> new TheilSenRegression(descriptor));

	// regression type
	private final RegressionType type;
	// regression factory
	private final RegressionFactoryImpl factory;

	/**
	 * Creates with the regression type and factory.
	 * 
	 * @param type regression type
	 * @param factory the regression factory
	 */
	private RegressionFactory(RegressionType type, RegressionFactoryImpl factory) {
		this.type = type;
		this.factory = factory;
	}

	/**
	 * Returns the type related to the factory.
	 * 
	 * @return the type related to the factory
	 */
	RegressionType getType() {
		return type;
	}

	/**
	 * Returns the regression factory.
	 * 
	 * @return the regression factory
	 */
	RegressionFactoryImpl getFactory() {
		return factory;
	}

	/**
	 * Creates a regression instance by another regression and its descriptor.
	 * 
	 * @param regression regression instance to clone
	 * @return new regression instance
	 */
	static IsRegression create(IsRegression regression) {
		return create(IsRegression.checkAndGetIfValid(regression).getDescriptor());
	}

	/**
	 * Creates a regression instance by a descriptor.
	 * 
	 * @param descriptor descriptor of a regression
	 * @return new regression instance
	 */
	static IsRegression create(RegressionDescriptor descriptor) {
		// checks if descriptor is consistent
		Checker.checkIfValid(descriptor, "Descriptor");
		// gets descriptor type
		RegressionType type = descriptor.getType();
		// scans the regression factories
		for (RegressionFactory factory : RegressionFactory.values()) {
			// checks if types are equals
			if (factory.type.equals(type)) {
				// creates and returns the regression
				return factory.getFactory().create(descriptor);
			}
		}
		// if here, the type is not consistent
		// then throw an exception
		throw new IllegalArgumentException("Regression type '" + type.value() + "' not found");
	}

	/**
	 * Interface factory to create a regression instance by a {@link RegressionDescriptor}.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	interface RegressionFactoryImpl {

		/**
		 * Creates new regression object by a descriptor.
		 * 
		 * @param descriptor descriptor to use to create a regression
		 * @return new regression object by a descriptor
		 */
		IsRegression create(RegressionDescriptor descriptor);

	}
}
