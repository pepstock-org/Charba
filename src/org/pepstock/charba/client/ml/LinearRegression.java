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

import java.util.Date;
import java.util.List;

import org.pepstock.charba.client.commons.ArrayDouble;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ImmutableDate;
import org.pepstock.charba.client.data.DataPoint;
import org.pepstock.charba.client.data.FloatingData;
import org.pepstock.charba.client.data.TimeSeriesItem;
import org.pepstock.charba.client.items.Undefined;

/**
 * Represents the simple linear regression is a linear regression model with a single explanatory variable.<br>
 * That is, it concerns two-dimensional sample points with one independent variable and one dependent variable (conventionally, the x and y coordinates in a cartesian coordinate
 * system) and finds a linear function (a non-vertical straight line) that, as accurately as possible, predicts the dependent variable values as a function of the independent
 * variable.<br>
 * The adjective simple refers to the fact that the outcome variable is related to a single predictor.<br>
 * Formula:<br>
 * 
 * <pre>
 * <b>f(x) = slope * x + intercept</b>
 * </pre>
 * 
 * It maps <a href="https://github.com/mljs/regression-simple-linear">mljs/regression-simple-linear</a>.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class LinearRegression extends BaseRegression<NativeLinearRegression> {

	/**
	 * Creates the simple linear regression object, using the passed data to calculate the formula.
	 * 
	 * @param x values bound to x
	 * @param y values bound to y
	 */
	LinearRegression(List<Double> x, List<Double> y) {
		super(new NativeLinearRegression(ArrayDouble.fromOrEmpty(x), ArrayDouble.fromOrEmpty(y)), x, y);
	}

	/**
	 * Returns the slope coefficient.
	 * 
	 * @return the slope coefficient
	 */
	public double getSlope() {
		return getNativeBaseRegression().getSlope();
	}

	/**
	 * Returns the intercept coefficient.
	 * 
	 * @return the intercept coefficient
	 */
	public double getIntercept() {
		return getNativeBaseRegression().getIntercept();
	}

	/**
	 * Returns all calculated coefficients as a list.
	 * 
	 * @return all calculated coefficients as a list
	 */
	public List<Double> getCoefficients() {
		return ArrayListHelper.unmodifiableList(getNativeBaseRegression().getCoefficients());
	}

	/**
	 * Returns a calculated X value by the Y value.
	 * 
	 * @param y Y value to use to calculate the X value
	 * @return a calculated X value by the Y value
	 */
	public double computeX(double y) {
		// checks if argument is consistent
		if (Undefined.isNot(y) && isConsistent()) {
			// computes and returns the value
			return getNativeBaseRegression().computeX(y);
		}
		// if here, argument is not consistent
		// then returns undefined
		return Undefined.DOUBLE;
	}

	/**
	 * Returns a calculated X value by the Y value.
	 * 
	 * @param y Y value to use to calculate the X value
	 * @return a calculated X value by the Y value
	 */
	public double computeX(FloatingData y) {
		// checks if argument is consistent
		if (y != null) {
			// computes and returns the value
			return computeX(y.getValue());
		}
		// if here, argument is not consistent
		// then returns undefined
		return Undefined.DOUBLE;
	}

	/**
	 * Returns a calculated X value by the Y value.
	 * 
	 * @param dataPoint Y value to use to calculate the X value
	 * @return a calculated X value by the Y value
	 */
	public double computeX(DataPoint dataPoint) {
		// checks if argument is consistent
		if (dataPoint != null) {
			// gets y value
			double value = MLUtil.get().getYValueFromDataPoint(dataPoint);
			// checks if value is consistent
			if (Undefined.isNot(value)) {
				// computes and returns the value
				return computeX(value);
			}
		}
		// if here, argument is not consistent
		// then returns undefined
		return Undefined.DOUBLE;
	}

	/**
	 * Returns a calculated X value by the Y value.
	 * 
	 * @param item Y value to use to calculate the X value
	 * @return a calculated X value by the Y value
	 */
	public Date computeX(TimeSeriesItem item) {
		// checks if argument is consistent
		if (item != null) {
			// gets y value
			return computeXToDate(item.getValue());
		}
		// if here, argument is not consistent
		// then returns null
		return null;
	}

	/**
	 * Returns a calculated X value by the Y value.
	 * 
	 * @param y Y value to use to calculate the X value
	 * @return a calculated X value by the Y value
	 */
	public Date computeXToDate(double y) {
		// checks value
		double value = computeX(y);
		// checks if argument is consistent
		if (Undefined.isNot(value)) {
			// computes and returns the value
			return new ImmutableDate((long) value);
		}
		// if here, argument is not consistent
		// then returns null
		return null;
	}

	/**
	 * Returns a calculated X value by the Y value.
	 * 
	 * @param y Y value to use to calculate the X value
	 * @return a calculated X value by the Y value
	 */
	public Date computeXToDate(FloatingData y) {
		// checks if argument is consistent
		if (y != null) {
			// computes and returns the value
			return computeXToDate(y.getValue());
		}
		// if here, argument is not consistent
		// then returns null
		return null;
	}

	/**
	 * Returns a calculated X value by the Y value.
	 * 
	 * @param dataPoint Y value to use to calculate the X value
	 * @return a calculated X value by the Y value
	 */
	public Date computeXToDate(DataPoint dataPoint) {
		// checks if argument is consistent
		if (dataPoint != null) {
			// gets y value
			double value = MLUtil.get().getYValueFromDataPoint(dataPoint);
			// checks if value is consistent
			if (Undefined.isNot(value)) {
				// computes and returns the value
				return computeXToDate(value);
			}
		}
		// if here, argument is not consistent
		// then returns undefined
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.ml.BaseRegression#isConsistent()
	 */
	@Override
	public boolean isConsistent() {
		return Undefined.isNot(getSlope()) && Undefined.isNot(getIntercept());
	}

}