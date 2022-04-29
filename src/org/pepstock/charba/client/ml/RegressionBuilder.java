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

import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.enums.RegressionType;

/**
 * Builds regressions instances.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class RegressionBuilder extends AbstractBuilder<RegressionBuilder> {

	private final RegressionDescriptor source;

	/**
	 * To avoid any instantiation
	 * 
	 * @param source descriptor source to use to create a regression or <code>null</code>
	 */
	private RegressionBuilder(RegressionDescriptor source) {
		this.source = source;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.ml.SamplesContainer#getBuilder()
	 */
	@Override
	RegressionBuilder getBuilder() {
		return this;
	}

	/**
	 * Creates new regression builder, without any sample.
	 * 
	 * @return new regression builder, without any sample
	 */
	public static RegressionBuilder create() {
		return new RegressionBuilder(null);
	}

	/**
	 * Creates new regression builder, using the passed data as samples.
	 * 
	 * @param x X values to use to create the regression
	 * @param y Y values to use to create the regression
	 * @return new regression builder
	 */
	public static RegressionBuilder create(List<Double> x, List<Double> y) {
		// creates builder
		RegressionBuilder builder = create();
		// stores samples
		builder.setSamples(x, y);
		// returns builder
		return builder;
	}

	/**
	 * Creates new regression builder, using the passed regression instance.
	 * 
	 * @param regression regression instance to clone
	 * @return new regression builder
	 */
	public static RegressionBuilder create(IsRegression regression) {
		return create(IsRegression.checkAndGetIfValid(regression).getDescriptor());
	}

	/**
	 * Creates new regression builder, using the passed regression descriptor instance to create new regression.
	 * 
	 * @param descriptor regression descriptor instance to create new regression
	 * @return new regression builder
	 */
	public static RegressionBuilder create(RegressionDescriptor descriptor) {
		return new RegressionBuilder(Checker.checkAndGetIfValid(descriptor, "Regression descriptor"));
	}

	/**
	 * Creates new regression instance, using the passed regression instance.
	 * 
	 * @param regression regression instance to clone
	 * @return new regression
	 */
	public static IsRegression build(IsRegression regression) {
		return build(IsRegression.checkAndGetIfValid(regression).getDescriptor());
	}

	/**
	 * Creates new regression, using the passed regression descriptor instance to create new regression.
	 * 
	 * @param descriptor regression descriptor instance to create new regression
	 * @return new regression
	 */
	public static IsRegression build(RegressionDescriptor descriptor) {
		return RegressionFactory.create(descriptor);
	}

	// --------------------------------
	// BUILD
	// --------------------------------

	/**
	 * Creates a simple linear regression.
	 * 
	 * @return simple linear regression instance
	 */
	public LinearRegression buildLinearRegression() {
		// checks if descriptor was passed
		if (buildByDescriptor(RegressionType.LINEAR)) {
			// created by descriptor
			return new LinearRegression(source);
		}
		// if here, created by samples
		return new LinearRegression(checkAndGetSamples(getX(), X_SAMPLES_TYPE), checkAndGetSamples(getY(), Y_SAMPLES_TYPE));
	}

	/**
	 * Creates a polynomial regression.<br>
	 * It uses the default degree, <b>{@value PolynomialRegression#DEFAULT_DEGREE}</b>.
	 * 
	 * @return polynomial regression instance
	 */
	public PolynomialRegression buildPolynomialRegression() {
		// checks if descriptor was passed
		if (buildByDescriptor(RegressionType.POLYNOMIAL)) {
			// created by descriptor
			return new PolynomialRegression(source);
		}
		// if here, created by samples
		return buildPolynomialRegression(PolynomialRegression.DEFAULT_DEGREE);
	}

	/**
	 * Creates a polynomial regression.
	 * 
	 * @param degree the maximum degree of the polynomial
	 * @return polynomial regression instance
	 */
	public PolynomialRegression buildPolynomialRegression(int degree) {
		// if the builder is created by a descriptor
		// you can not use this build method
		Checker.assertCheck(!buildByDescriptor(RegressionType.POLYNOMIAL), "Degree can not be changed in the regression descriptor. Use 'buildPolynomialRegression()' method instead.");
		// if here, created by samples
		// checks minimum degree
		Checker.checkIfGreaterThan(degree, PolynomialRegression.MINIMUM_DEGREE, "Degree of polynomial regression ");
		// creates regression
		return new PolynomialRegression(checkAndGetSamples(getX(), X_SAMPLES_TYPE), checkAndGetSamples(getY(), Y_SAMPLES_TYPE), degree);
	}

	/**
	 * Creates a power regression.
	 * 
	 * @return power regression instance
	 */
	public PowerRegression buildPowerRegression() {
		// checks if descriptor was passed
		if (buildByDescriptor(RegressionType.POWER)) {
			// created by descriptor
			return new PowerRegression(source);
		}
		// if here, created by samples
		return new PowerRegression(checkAndGetSamples(getX(), X_SAMPLES_TYPE), checkAndGetSamples(getY(), Y_SAMPLES_TYPE));
	}

	/**
	 * Creates a exponential regression.
	 * 
	 * @return exponential regression instance
	 */
	public ExponentialRegression buildExponentialRegression() {
		// checks if descriptor was passed
		if (buildByDescriptor(RegressionType.EXPONENTIAL)) {
			// created by descriptor
			return new ExponentialRegression(source);
		}
		// if here, created by samples
		return new ExponentialRegression(checkAndGetSamples(getX(), X_SAMPLES_TYPE), checkAndGetSamples(getY(), Y_SAMPLES_TYPE));
	}

	/**
	 * Creates a TheilSen regression.
	 * 
	 * @return TheilSen regression instance
	 */
	public TheilSenRegression buildTheilSenRegression() {
		// checks if descriptor was passed
		if (buildByDescriptor(RegressionType.THEIL_SEN)) {
			// created by descriptor
			return new TheilSenRegression(source);
		}
		// if here, created by samples
		return new TheilSenRegression(checkAndGetSamples(getX(), X_SAMPLES_TYPE), checkAndGetSamples(getY(), Y_SAMPLES_TYPE));
	}

	/**
	 * Creates a robust polynomial regression.<br>
	 * It uses the default degree, <b>{@value RobustPolynomialRegression#DEFAULT_DEGREE}</b>.
	 * 
	 * @return robust polynomial regression instance
	 */
	public RobustPolynomialRegression buildRobustPolynomialRegression() {
		// checks if descriptor was passed
		if (buildByDescriptor(RegressionType.ROBUST_POLYNOMIAL)) {
			// created by descriptor
			return new RobustPolynomialRegression(source);
		}
		// if here, created by samples
		return buildRobustPolynomialRegression(RobustPolynomialRegression.DEFAULT_DEGREE);
	}

	/**
	 * Creates a robust polynomial regression.
	 * 
	 * @param degree the maximum degree of the robust polynomial
	 * @return robust polynomial regression instance
	 */
	public RobustPolynomialRegression buildRobustPolynomialRegression(int degree) {
		// if the builder is created by a descriptor
		// you can not use this build method
		Checker.assertCheck(!buildByDescriptor(RegressionType.ROBUST_POLYNOMIAL), "Degree can not be changed in the regression descriptor. Use 'buildRobustPolynomialRegression()' method instead.");
		// if here, created by samples
		// checks minimum degree
		Checker.checkIfGreaterThan(degree, RobustPolynomialRegression.MINIMUM_DEGREE, "Degree of robust polynomial regression ");
		// creates regression
		return new RobustPolynomialRegression(checkAndGetSamples(getX(), X_SAMPLES_TYPE), checkAndGetSamples(getY(), Y_SAMPLES_TYPE), degree);
	}

	/**
	 * Returns <code>true</code> if the regression instance must be created by the passed descriptor.
	 * 
	 * @param type the type of target regression
	 * @return <code>true</code> if the regression instance must be created by the passed descriptor
	 */
	private boolean buildByDescriptor(RegressionType type) {
		return source != null && type.equals(source.getType());
	}
}