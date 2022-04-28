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

import org.pepstock.charba.client.data.DataPoint;
import org.pepstock.charba.client.data.TimeSeriesItem;
import org.pepstock.charba.client.enums.RegressionType;

/**
 * Maps all common methods of a regression.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsRegression {

	/**
	 * Checks if regression passed as argument is not <code>null</code> and if consistent as well.<br>
	 * If not, throw a {@link IllegalArgumentException}.
	 * 
	 * @param regression regression to be checked
	 */
	static void checkIfValid(IsRegression regression) {
		if (regression == null || !regression.isConsistent()) {
			throw new IllegalArgumentException("Regression is null or not consistent");
		}
	}

	/**
	 * Checks if regression passed as argument is not <code>null</code> and if consistent as well.<br>
	 * If not, throw a {@link IllegalArgumentException}, otherwise it returns the regression.
	 * 
	 * @param regression regression to be checked
	 * @param <T> type of regression
	 * @return the same regression passed as argument
	 */
	static <T extends IsRegression> T checkAndGetIfValid(T regression) {
		// checks if regression is consistent
		checkIfValid(regression);
		// if here, is consistent
		// then returns the argument
		return regression;
	}

	/**
	 * Returns the regression type.
	 * 
	 * @return the regression type
	 */
	RegressionType getType();

	/**
	 * Returns the regression descriptor.
	 * 
	 * @return the regression descriptor
	 */
	RegressionDescriptor getDescriptor();

	/**
	 * Returns <code>true</code> if the regression is consistent and usable.
	 * 
	 * @return <code>true</code> if the regression is consistent and usable
	 */
	boolean isConsistent();

	/**
	 * Returns the Y value, calculated by the regression formula at specific X value.
	 * 
	 * @param x value to use to get the predicted value
	 * @return the Y value, calculated by the regression formula at specific X value
	 */
	double predict(double x);

	/**
	 * Returns the Y value, calculated by the regression formula at specific X value.
	 * 
	 * @param x value to use to get the predicted value
	 * @return the Y value, calculated by the regression formula at specific X value
	 */
	double predict(Date x);

	/**
	 * Returns the Y value, calculated by the regression formula at specific X value.
	 * 
	 * @param dataPoint value to use to get the predicted value
	 * @return the Y value, calculated by the regression formula at specific X value
	 */
	double predict(DataPoint dataPoint);

	/**
	 * Returns the Y value, calculated by the regression formula at specific X value.
	 * 
	 * @param item value to use to get the predicted value
	 * @return the Y value, calculated by the regression formula at specific X value
	 */
	double predict(TimeSeriesItem item);

	/**
	 * Returns a list of Y values, calculated by the regression formula for specific X values.
	 * 
	 * @param xValues values to use to get the predicted values
	 * @return a list of Y values, calculated by the regression formula for specific X values
	 */
	List<Double> predict(double... xValues);

	/**
	 * Returns a list of Y values, calculated by the regression formula for specific X values.
	 * 
	 * @param xValues values to use to get the predicted values
	 * @return a list of Y values, calculated by the regression formula for specific X values
	 */
	List<Double> predict(List<Double> xValues);

	/**
	 * Returns a list of Y values, calculated by the regression formula for specific X values.
	 * 
	 * @param xValues values to use to get the predicted values
	 * @return a list of Y values, calculated by the regression formula for specific X values
	 */
	List<Double> predictByDates(Date... xValues);

	/**
	 * Returns a list of Y values, calculated by the regression formula for specific X values.
	 * 
	 * @param xValues values to use to get the predicted values
	 * @return a list of Y values, calculated by the regression formula for specific X values
	 */
	List<Double> predictByDates(List<Date> xValues);

	/**
	 * Returns a list of Y values, calculated by the regression formula for specific X values.
	 * 
	 * @param dataPoints values to use to get the predicted values
	 * @return a list of Y values, calculated by the regression formula for specific X values
	 */
	List<Double> predictByDataPoints(DataPoint... dataPoints);

	/**
	 * Returns a list of Y values, calculated by the regression formula for specific X values.
	 * 
	 * @param dataPoints values to use to get the predicted values
	 * @return a list of Y values, calculated by the regression formula for specific X values
	 */
	List<Double> predictByDataPoints(List<DataPoint> dataPoints);

	/**
	 * Returns a list of Y values, calculated by the regression formula for specific X values.
	 * 
	 * @param items values to use to get the predicted values
	 * @return a list of Y values, calculated by the regression formula for specific X values
	 */
	List<Double> predictByTimeSeriesItems(TimeSeriesItem... items);

	/**
	 * Returns a list of Y values, calculated by the regression formula for specific X values.
	 * 
	 * @param items values to use to get the predicted values
	 * @return a list of Y values, calculated by the regression formula for specific X values
	 */
	List<Double> predictByTimeSeriesItems(List<TimeSeriesItem> items);

	/**
	 * Evaluates the regression formula if the model fits enough.<br>
	 * X values are creating starting from 0 with increment of 1.
	 * 
	 * @param y Y values to use for evaluation
	 * @return the score of the regression
	 */
	RegressionScore score(List<Double> y);

	/**
	 * Evaluates the regression formula if the model fits enough.<br>
	 * X values are creating starting from passed argument with increment of 1.
	 * 
	 * @param y Y values to use for evaluation
	 * @param starting starting value to create X values
	 * @return the score of the regression
	 */
	RegressionScore score(List<Double> y, double starting);

	/**
	 * Evaluates the regression formula if the model fits enough.<br>
	 * X values are creating starting from passed argument with increment of passed argument.
	 * 
	 * @param y Y values to use for evaluation
	 * @param starting starting value to create X values
	 * @param increment increment to apply to the starting value
	 * @return the score of the regression
	 */
	RegressionScore score(List<Double> y, double starting, double increment);

	/**
	 * Evaluates the regression formula if the model fits enough.
	 * 
	 * @param x X values to use for evaluation
	 * @param y Y values to use for evaluation
	 * @return the score of the regression
	 */
	RegressionScore score(List<Double> x, List<Double> y);

	/**
	 * Evaluates the regression formula if the model fits enough.
	 * 
	 * @param x X values to use for evaluation
	 * @param y Y values to use for evaluation
	 * @return the score of the regression
	 */
	RegressionScore scoreByDate(List<Date> x, List<Double> y);

	/**
	 * Evaluates the regression formula if the model fits enough.
	 * 
	 * @param dataPoints X and Y values to use for evaluation
	 * @return the score of the regression
	 */
	RegressionScore scoreByDataPoints(List<DataPoint> dataPoints);

	/**
	 * Evaluates the regression formula if the model fits enough.
	 * 
	 * @param items X and Y values to use for evaluation
	 * @return the score of the regression
	 */
	RegressionScore scoreByTimeSeriesItems(List<TimeSeriesItem> items);

	/**
	 * Returns the formula of the regression.
	 * 
	 * @return the formula of the regression
	 */
	default String toFormula() {
		return toFormula(BaseRegression.DEFAULT_FORMULA_PRECISION);
	}

	/**
	 * Returns the formula of the regression, using the requested precision.
	 * 
	 * @param precision precision to apply to the numbers of the formula
	 * @return the formula of the regression
	 */
	String toFormula(int precision);

	/**
	 * Returns the formula of the regression.
	 * 
	 * @return the formula of the regression
	 */
	default String toLaTeX() {
		return toLaTeX(BaseRegression.DEFAULT_FORMULA_PRECISION);
	}

	/**
	 * Returns the formula of the regression, using the requested precision.
	 * 
	 * @param precision precision to apply to the numbers of the formula
	 * @return the formula of the regression
	 */
	String toLaTeX(int precision);

}
