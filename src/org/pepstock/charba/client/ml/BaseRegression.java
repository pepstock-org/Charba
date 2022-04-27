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

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.pepstock.charba.client.commons.ArrayDouble;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.data.DataPoint;
import org.pepstock.charba.client.data.TimeSeriesItem;
import org.pepstock.charba.client.items.Undefined;

/**
 * Base regression class with common methods for all regressions.<br>
 * It wraps <a href="https://github.com/mljs/regression-base">mljs/regression-base</a>.
 * 
 * @author Andrea "Stock" Stocchero
 * @param <T> native type of regression
 */
abstract class BaseRegression<T extends NativeBaseRegression> implements IsRegression {

	/**
	 * Default precision to get the formula, <b>{@value DEFAULT_FORMULA_PRECISION}</b>.
	 */
	public static final int DEFAULT_FORMULA_PRECISION = 2;

	// native regression instance
	private final T nativeRegression;

	/**
	 * Creates the object storing the native regression
	 * 
	 * @param nativeRegression native regression instance
	 */
	BaseRegression(T nativeRegression) {
		this.nativeRegression = nativeRegression;
	}

	/**
	 * Returns the native regression instance, loaded by the specific regression class.
	 * 
	 * @return the native regression instance, loaded by the specific regression class
	 */
	final T getNativeBaseRegression() {
		return nativeRegression;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.ml.IsRegression#predict(double)
	 */
	@Override
	public final double predict(double x) {
		// checks if argument is consistent
		if (Undefined.isNot(x) && isConsistent()) {
			return getNativeBaseRegression().predict(x);
		}
		// if here, argument not consistent
		// then returns undefined
		return Undefined.DOUBLE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.ml.IsRegression#predict(java.util.Date)
	 */
	@Override
	public final double predict(Date x) {
		// checks if argument is consistent
		if (x != null) {
			return predict(x.getTime());
		}
		// if here, argument not consistent
		// then returns undefined
		return Undefined.DOUBLE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.ml.IsRegression#predict(org.pepstock.charba.client.data.DataPoint)
	 */
	@Override
	public final double predict(DataPoint dataPoint) {
		return predict(MLUtil.get().getXValueFromDataPoint(dataPoint));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.ml.IsRegression#predict(org.pepstock.charba.client.data.TimeSeriesItem)
	 */
	@Override
	public final double predict(TimeSeriesItem item) {
		return predict(item.getTime());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.ml.IsRegression#predict(double[])
	 */
	@Override
	public final List<Double> predict(double... xValues) {
		return predict(ArrayDouble.fromOrEmpty(xValues));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.ml.IsRegression#predict(java.util.List)
	 */
	@Override
	public final List<Double> predict(List<Double> xValues) {
		return predict(ArrayDouble.fromOrEmpty(xValues));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.ml.IsRegression#predictByDates(java.util.Date[])
	 */
	@Override
	public final List<Double> predictByDates(Date... xValues) {
		return predict(ArrayDouble.fromOrEmpty(MLUtil.get().parseDates(xValues)));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.ml.IsRegression#predictByDates(java.util.List)
	 */
	@Override
	public final List<Double> predictByDates(List<Date> xValues) {
		return predict(ArrayDouble.fromOrEmpty(MLUtil.get().parseDates(xValues)));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.ml.IsRegression#predictByDataPoints(org.pepstock.charba.client.data.DataPoint[])
	 */
	@Override
	public final List<Double> predictByDataPoints(DataPoint... dataPoints) {
		return predict(ArrayDouble.fromOrEmpty(MLUtil.get().parseDataPoints(true, dataPoints)));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.ml.IsRegression#predictByDataPoints(java.util.List)
	 */
	@Override
	public final List<Double> predictByDataPoints(List<DataPoint> dataPoints) {
		return predict(ArrayDouble.fromOrEmpty(MLUtil.get().parseDataPoints(true, dataPoints)));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.ml.IsRegression#predictByTimeSeriesItems(org.pepstock.charba.client.data.TimeSeriesItem[])
	 */
	@Override
	public final List<Double> predictByTimeSeriesItems(TimeSeriesItem... items) {
		return predict(ArrayDouble.fromOrEmpty(MLUtil.get().parseTimeSeriesItems(true, items)));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.ml.IsRegression#predictByTimeSeriesItems(java.util.List)
	 */
	@Override
	public final List<Double> predictByTimeSeriesItems(List<TimeSeriesItem> items) {
		return predict(ArrayDouble.fromOrEmpty(MLUtil.get().parseTimeSeriesItems(true, items)));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.ml.IsRegression#score(java.util.List)
	 */
	@Override
	public final RegressionScore score(List<Double> y) {
		return score(y, 0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.ml.IsRegression#score(java.util.List, double)
	 */
	@Override
	public final RegressionScore score(List<Double> y, double starting) {
		return score(y, starting, 1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.ml.IsRegression#score(java.util.List, double, double)
	 */
	@Override
	public final RegressionScore score(List<Double> y, double starting, double increment) {
		// checks of consistent
		if (isConsistent() && y != null) {
			return getNativeBaseRegression().score(ArrayDouble.fromOrEmpty(MLUtil.get().loadXValues(y.size(), starting, increment)), ArrayDouble.fromOrEmpty(y));
		}
		// if here, the regression is not consistent
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.ml.IsRegression#score(java.util.List, java.util.List)
	 */
	@Override
	public final RegressionScore score(List<Double> x, List<Double> y) {
		// checks of consistent
		if (isConsistent()) {
			return getNativeBaseRegression().score(ArrayDouble.fromOrEmpty(x), ArrayDouble.fromOrEmpty(y));
		}
		// if here, the regression is not consistent
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.ml.IsRegression#scoreByDate(java.util.List, java.util.List)
	 */
	@Override
	public final RegressionScore scoreByDate(List<Date> x, List<Double> y) {
		return score(MLUtil.get().parseDates(x), y);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.ml.IsRegression#scoreByDataPoints(java.util.List)
	 */
	@Override
	public final RegressionScore scoreByDataPoints(List<DataPoint> dataPoints) {
		// checks if argument is consistent
		if (isConsistent()) {
			// transforms argument in a array
			ArrayDouble arrayX = ArrayDouble.fromOrEmpty(MLUtil.get().parseDataPoints(true, dataPoints));
			ArrayDouble arrayY = ArrayDouble.fromOrEmpty(MLUtil.get().parseDataPoints(false, dataPoints));
			// and returns the prediction
			return getNativeBaseRegression().score(arrayX, arrayY);
		}
		// if here, argument not consistent
		// then returns null
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.ml.IsRegression#scoreByTimeSeriesItems(java.util.List)
	 */
	@Override
	public final RegressionScore scoreByTimeSeriesItems(List<TimeSeriesItem> items) {
		// checks if argument is consistent
		if (isConsistent()) {
			// transforms argument in a array
			ArrayDouble arrayX = ArrayDouble.fromOrEmpty(MLUtil.get().parseTimeSeriesItems(true, items));
			ArrayDouble arrayY = ArrayDouble.fromOrEmpty(MLUtil.get().parseTimeSeriesItems(false, items));
			// and returns the prediction
			return getNativeBaseRegression().score(arrayX, arrayY);
		}
		// if here, argument not consistent
		// then returns null
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.ml.IsRegression#toFormula(int)
	 */
	@Override
	public final String toFormula(int precision) {
		return NativeJsMLHelper.toFormula(getNativeBaseRegression(), Checker.greaterThanOrDefault(precision, 0, DEFAULT_FORMULA_PRECISION));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.ml.IsRegression#toLaTeX(int)
	 */
	@Override
	public final String toLaTeX(int precision) {
		return getNativeBaseRegression().toLaTeX(Checker.greaterThanOrDefault(precision, 0, DEFAULT_FORMULA_PRECISION));
	}

	// -----------------------
	// INTERNALS
	// -----------------------

	/**
	 * Returns the Y value, calculated by the regression formula at specific X value.
	 * 
	 * @param x value to use to get the predicted value
	 * @return the Y value, calculated by the regression formula at specific X value
	 */
	private List<Double> predict(ArrayDouble x) {
		// checks if regression is consistent
		if (isConsistent()) {
			// gets result as array
			ArrayDouble result = NativeJsMLHelper.predict(getNativeBaseRegression(), x);
			// transforms and returns in a list
			return ArrayListHelper.unmodifiableList(result);
		}
		// if here, regression is not consistent
		// then returns an empty list
		return Collections.emptyList();
	}

}
