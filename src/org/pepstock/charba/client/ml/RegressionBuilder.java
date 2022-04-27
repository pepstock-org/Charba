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

/**
 * Builds regressions instances.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class RegressionBuilder extends SamplesContainer<RegressionBuilder> {

	/**
	 * To avoid any instantiation
	 */
	private RegressionBuilder() {
		super();
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
		return new RegressionBuilder();
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

	// --------------------------------
	// BUILD
	// --------------------------------

	/**
	 * Creates a simple linear regression.
	 * 
	 * @return polynomial regression instance
	 */
	public LinearRegression buildLinearRegression() {
		// gets samples
		List<Double> x = getX();
		List<Double> y = getY();
		// checks samples consistency
		checkSamples(x, "X");
		checkSamples(y, "Y");
		// creates regression
		return new LinearRegression(x, y);
	}

	/**
	 * Creates a polynomial regression.<br>
	 * It uses the default degree, <b>{@value PolynomialRegression#DEFAULT_DEGREE}</b>.
	 * 
	 * @return polynomial regression instance
	 */
	public PolynomialRegression buildPolynomialRegression() {
		return buildPolynomialRegression(PolynomialRegression.DEFAULT_DEGREE);
	}

	/**
	 * Creates a polynomial regression.
	 * 
	 * @param degree the maximum degree of the polynomial
	 * @return polynomial regression instance
	 */
	public PolynomialRegression buildPolynomialRegression(int degree) {
		// gets samples
		List<Double> x = getX();
		List<Double> y = getY();
		// checks samples consistency
		checkSamples(x, "X");
		checkSamples(y, "Y");
		// checks minimum degree
		Checker.checkIfGreaterThan(degree, PolynomialRegression.MINIMUM_DEGREE, "Degree of polynomial regression ");
		// creates regression
		return new PolynomialRegression(x, y, degree);
	}

}