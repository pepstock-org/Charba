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

import java.util.Arrays;
import java.util.List;

import org.pepstock.charba.client.commons.ArrayDouble;
import org.pepstock.charba.client.enums.RegressionType;

/**
 * Exponential regression is a non-linear regression technique that looks like this:<br>
 * <br>
 * <b style="font: italic bold 24px courier;">y = A * <i>e</i><sup style="font: italic bold 20px courier;">B * x</sup></b><br>
 * <br>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ExponentialRegression extends BaseRegression<NativeExponentialRegression> {

	// coefficients
	private final List<Double> coefficients;

	/**
	 * Creates the exponential regression object, using the passed regression descriptor.
	 * 
	 * @param descriptor regression description used to create new regression
	 */
	ExponentialRegression(RegressionDescriptor descriptor) {
		super(RegressionType.EXPONENTIAL, NativeExponentialRegression.load(descriptor));
		// stores coefficients
		this.coefficients = Arrays.asList(getA(), getB());
	}

	/**
	 * Creates the exponential regression object, using the passed data to calculate the formula.
	 * 
	 * @param x values bound to x
	 * @param y values bound to y
	 */
	ExponentialRegression(List<Double> x, List<Double> y) {
		super(RegressionType.EXPONENTIAL, new NativeExponentialRegression(ArrayDouble.fromOrEmpty(x), ArrayDouble.fromOrEmpty(y)));
		// stores coefficients
		this.coefficients = Arrays.asList(getA(), getB());
	}

	/**
	 * Returns the A coefficient.
	 * 
	 * @return the A coefficient
	 */
	public double getA() {
		return getNativeBaseRegression().getA();
	}

	/**
	 * Returns the B coefficient.
	 * 
	 * @return the B coefficient
	 */
	public double getB() {
		return getNativeBaseRegression().getB();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.ml.IsRegression#getCoefficients()
	 */
	@Override
	public List<Double> getCoefficients() {
		return coefficients;
	}

}