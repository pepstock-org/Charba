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

import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.data.DataPoint;
import org.pepstock.charba.client.data.TimeSeriesItem;

/**
 * Common utility which is managing the samples to use to create a regression.
 * 
 * @author Andrea "Stock" Stocchero
 * @param <T> type of regression builder
 */
abstract class SamplesContainer<T> {

	// constants for samples types
	static final String X_SAMPLES_TYPE = "X";
	static final String Y_SAMPLES_TYPE = "Y";

	// samples collections
	private List<Double> x = null;
	private List<Double> y = null;

	/**
	 * To avoid any instantiation
	 */
	SamplesContainer() {
		// to be sure that ML java script object is injected
		MLInjector.get();
	}

	/**
	 * Returns the builder instance.
	 * 
	 * @return the builder instance
	 */
	abstract T getBuilder();

	/**
	 * Returns X samples values.
	 * 
	 * @return X samples values.
	 */
	final List<Double> getX() {
		return x;
	}

	/**
	 * Returns Y samples values.
	 * 
	 * @return Y samples values.
	 */
	final List<Double> getY() {
		return y;
	}

	/**
	 * Checks if the passed list of double is consistent.
	 * 
	 * @param samples list of doubles to check
	 * @param type type of the samples, X or Y
	 * @return the passed list
	 */
	final List<Double> checkAndGetSamples(List<Double> samples, String type) {
		Checker.assertCheck(ArrayListHelper.isConsistent(samples), type + " samples are not consistent");
		return samples;
	}

	// --------------------------------
	// SAMPLES SETTINGS
	// --------------------------------

	/**
	 * Sets samples, using the passed Y values.<br>
	 * X values are creating starting from 0 with increment of 1.
	 * 
	 * @param y Y values to use to create the regression
	 * @return regression builder
	 */
	public final T setSamples(List<Double> y) {
		return setSamples(y, 0);
	}

	/**
	 * Sets samples, using the passed Y values.<br>
	 * X values are creating starting from passed argument with increment of 1.
	 * 
	 * @param y Y values to use to create the regression
	 * @param starting starting value to create X values
	 * @return regression builder instance
	 */
	public final T setSamples(List<Double> y, double starting) {
		return setSamples(y, starting, 1);
	}

	/**
	 * Sets samples, using the passed Y values.<br>
	 * X values are creating starting from passed argument with increment of passed argument.
	 * 
	 * @param y Y values to use to create the regression
	 * @param starting starting value to create X values
	 * @param increment increment to apply to the starting value
	 * @return regression builder instance
	 */
	public T setSamples(List<Double> y, double starting, double increment) {
		// checks list of y values
		checkAndGetSamples(y, Y_SAMPLES_TYPE);
		// stores samples
		return setSamples(MLUtil.get().loadXValues(y.size(), starting, increment), y);
	}

	/**
	 * Creates a simple linear regression, using the passed X and Y values.
	 * 
	 * @param x X values to use to create the regression
	 * @param y Y values to use to create the regression
	 * @return regression builder instance
	 */
	public T setSamplesByDates(List<Date> x, List<Double> y) {
		return setSamples(MLUtil.get().parseDates(x), y);
	}

	/**
	 * Creates a simple linear regression, using the passed {@link DataPoint}s.
	 * 
	 * @param dataPoints data points to use to create the regression
	 * @return regression builder instance
	 */
	public T setSamplesByDataPoints(List<DataPoint> dataPoints) {
		return setSamples(MLUtil.get().parseDataPoints(true, dataPoints), MLUtil.get().parseDataPoints(false, dataPoints));
	}

	/**
	 * Creates a simple linear regression, using the passed {@link TimeSeriesItem}s.
	 * 
	 * @param items time series items to use to create the regression
	 * @return regression builder instance
	 */
	public T setSamplesByTimeSeriesItems(List<TimeSeriesItem> items) {
		return setSamples(MLUtil.get().parseTimeSeriesItems(true, items), MLUtil.get().parseTimeSeriesItems(false, items));
	}

	/**
	 * Creates a simple linear regression, using the passed X and Y values.
	 * 
	 * @param x X values to use to create the regression
	 * @param y Y values to use to create the regression
	 * @return regression builder instance
	 */
	public T setSamples(List<Double> x, List<Double> y) {
		// stores samples
		this.x = x;
		this.y = y;
		// returns builder
		return getBuilder();
	}

}