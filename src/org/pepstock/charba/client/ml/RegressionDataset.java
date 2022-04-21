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

import org.pepstock.charba.client.ChartType;
import org.pepstock.charba.client.commons.ArrayUtil;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.data.DataPoint;
import org.pepstock.charba.client.data.LineDataset;

/**
 * This is a {@link LineDataset} which is created to add the regression line, calculate by a regression, to a chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class RegressionDataset extends LineDataset {

	private final IsRegression regression;

	private final boolean manageDates;

	private final boolean manageDataPoints;

	/**
	 * Creates a data set.<br>
	 * It uses the global options has default.
	 * 
	 * @param regression regression instance
	 * @param hidden if <code>true</code>, it will be initially hidden
	 * @param manageDates if <code>true</code>, it is managing {@link Date}s as X values
	 * @param manageDataPoints if <code>true</code>, it is managing {@link DataPoint}s as data
	 */
	RegressionDataset(IsRegression regression, boolean hidden, boolean manageDates, boolean manageDataPoints) {
		super(ChartType.LINE, null, hidden);
		// checks if regression is consistent
		this.regression = Checker.checkAndGetIfValid(regression, "Regression ");
		// checks if the regression is consistent
		Checker.assertCheck(this.regression.isConsistent(), "Regression is not consistent");
		// stores flags about dates and points
		this.manageDates = manageDates;
		this.manageDataPoints = manageDataPoints;
	}

	/**
	 * Returns the regression instance, applied to the dataset.
	 * 
	 * @return the regression instance, applied to the dataset
	 */
	public IsRegression getRegression() {
		return regression;
	}

	/**
	 * Adds Y values to the dataset, using the amount of X values.<br>
	 * X values are creating starting from 0 with increment of 1.
	 * 
	 * @param amount amount of X and Y values to add
	 */
	public void addX(int amount) {
		addX(amount, 0);
	}

	/**
	 * Adds Y values to the dataset, using the amount of X values.<br>
	 * X values are creating starting from passed starting point with increment of 1.
	 * 
	 * @param amount amount of X and Y values to add
	 * @param starting starting value to create X values
	 */
	public void addX(int amount, double starting) {
		addX(amount, starting, 1);
	}

	/**
	 * Adds Y values to the dataset, using the amount of X values.<br>
	 * X values are creating starting from passed argument with increment of passed argument.
	 * 
	 * @param amount amount of X and Y values to add
	 * @param starting starting value to create X values
	 * @param increment increment to apply to the starting value
	 */
	public void addX(int amount, double starting, double increment) {
		// checks if dataset is managed by datapoint
		if (!areDataPointsManaged()) {
			// creates x values
			List<Double> xValues = MLUtil.get().loadXValues(amount, starting, increment);
			// gets values
			List<Double> predict = regression.predict(xValues);
			// creates result
			List<Double> result = getData(true);
			// stores data
			result.addAll(predict);
		}
	}

	/**
	 * Adds Y values to the dataset, using the passed X values.
	 * 
	 * @param values X values to add to dataset
	 */
	public void addXValues(double... values) {
		// checks if dataset is not managing dates
		if (!areDatesManaged() && ArrayUtil.isNotEmpty(values)) {
			// gets values
			List<Double> predict = regression.predict(values);
			// creates result
			List<DataPoint> result = getDataPoints(true);
			// scans values
			for (int i = 0; i < predict.size(); i++) {
				result.add(new DataPoint(values[i], predict.get(i)));
			}
		}
	}

	/**
	 * Adds Y values to the dataset, using the passed X values.
	 * 
	 * @param values X values to add to dataset
	 */
	public void addXValues(List<Double> values) {
		addXValues(ArrayUtil.toDoubles(values));
	}

	/**
	 * Adds Y values to the dataset, using the passed X values.
	 * 
	 * @param values X values to add to dataset
	 */
	public void addXDates(Date... values) {
		// checks if dataset is managing dates
		if (areDatesManaged() && ArrayUtil.isNotEmpty(values)) {
			// gets values
			List<Double> predict = regression.predictByDates(values);
			// creates result
			List<DataPoint> result = getDataPoints(true);
			// scans values
			for (int i = 0; i < predict.size(); i++) {
				result.add(new DataPoint(values[i], predict.get(i)));
			}
		}
	}

	/**
	 * Adds Y values to the dataset, using the passed X values.
	 * 
	 * @param values X values to add to dataset
	 */
	public void addXDates(List<Date> values) {
		addXDates(ArrayUtil.toDates(values));
	}

	/**
	 * Returns <code>true</code> if it is managing {@link Date}s as X values.
	 * 
	 * @return <code>true</code> if it is managing {@link Date}s as X values
	 */
	private boolean areDatesManaged() {
		return manageDates;
	}

	/**
	 * Returns <code>true</code> if it is managing {@link Date}s as X values.
	 * 
	 * @return <code>true</code> if it is managing {@link Date}s as X values
	 */
	private boolean areDataPointsManaged() {
		return manageDataPoints;
	}

}