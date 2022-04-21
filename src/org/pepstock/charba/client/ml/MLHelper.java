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
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.Injector;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.ImmutableDate;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.data.DataPoint;
import org.pepstock.charba.client.data.Dataset;
import org.pepstock.charba.client.data.HasDataPoints;
import org.pepstock.charba.client.data.HasTimeSeriesItems;
import org.pepstock.charba.client.data.TimeSeriesItem;
import org.pepstock.charba.client.enums.DataType;

/**
 * Utility entry point to create ML objects.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class MLHelper {

	// static instance for singleton
	private static final MLHelper INSTANCE = new MLHelper();
	// sorter of data point in ascendent mode
	private static final Comparator<DataPoint> ASCENDENT = MLUtil.get()::sort;
	// injectable JS resource for ML
	private final MlPluginResource jsResource = new MlPluginResource();

	/**
	 * To avoid any instantiation
	 */
	private MLHelper() {
		// to be sure that CHARBA java script object is injected
		JsHelper.get();
		// injects JS of ML
		Injector.ensureInjected(jsResource);
	}

	/**
	 * Singleton object to get the ML helper instance
	 * 
	 * @return ML helper instance.
	 */
	public static MLHelper get() {
		return INSTANCE;
	}

	// --------------------------------
	// LINEAR
	// --------------------------------

	/**
	 * Creates a simple linear regression, using the passed Y values.<br>
	 * X values are creating starting from 0 with increment of 1.
	 * 
	 * @param y Y values to use to create the regression
	 * @return simple linear regression instance
	 */
	public LinearRegression createLinearRegression(List<Double> y) {
		return createLinearRegression(y, 0);
	}

	/**
	 * Creates a simple linear regression, using the passed Y values.<br>
	 * X values are creating starting from passed argument with increment of 1.
	 * 
	 * @param y Y values to use to create the regression
	 * @param starting starting value to create X values
	 * @return simple linear regression instance
	 */
	public LinearRegression createLinearRegression(List<Double> y, double starting) {
		return createLinearRegression(y, starting, 1);
	}

	/**
	 * Creates a simple linear regression, using the passed Y values.<br>
	 * X values are creating starting from passed argument with increment of passed argument.
	 * 
	 * @param y Y values to use to create the regression
	 * @param starting starting value to create X values
	 * @param increment increment to apply to the starting value
	 * @return simple linear regression instance
	 */
	public LinearRegression createLinearRegression(List<Double> y, double starting, double increment) {
		// checks list of y values
		checkYValues(y);
		// creates regression
		return createLinearRegression(MLUtil.get().loadXValues(y.size(), starting, increment), y);
	}

	/**
	 * Creates a simple linear regression, using the passed X and Y values.
	 * 
	 * @param x X values to use to create the regression
	 * @param y Y values to use to create the regression
	 * @return simple linear regression instance
	 */
	public LinearRegression createLinearRegression(List<Double> x, List<Double> y) {
		return new LinearRegression(x, y);
	}

	/**
	 * Creates a simple linear regression, using the passed {@link DataPoint}s.
	 * 
	 * @param dataPoints data points to use to create the regression
	 * @return simple linear regression instance
	 */
	public LinearRegression createLinearRegressionByDataPoints(List<DataPoint> dataPoints) {
		return new LinearRegression(MLUtil.get().parseDataPoints(true, dataPoints), MLUtil.get().parseDataPoints(false, dataPoints));
	}

	/**
	 * Creates a simple linear regression, using the passed {@link TimeSeriesItem}s.
	 * 
	 * @param items tiem series items to use to create the regression
	 * @return simple linear regression instance
	 */
	public LinearRegression createLinearRegressionByTimeSeriesItems(List<TimeSeriesItem> items) {
		return new LinearRegression(MLUtil.get().parseTimeSeriesItems(true, items), MLUtil.get().parseTimeSeriesItems(false, items));
	}

	/**
	 * Creates a simple linear regression, using the passed X and Y values.
	 * 
	 * @param x X values to use to create the regression
	 * @param y Y values to use to create the regression
	 * @return simple linear regression instance
	 */
	public LinearRegression createLinearRegressionByDates(List<Date> x, List<Double> y) {
		return new LinearRegression(MLUtil.get().parseDates(x), y);
	}

	// --------------------------------
	// LINEAR DATASET
	// --------------------------------

	/**
	 * Creates a regression dataset, to add to a chart, using the passed Y values.<br>
	 * X values are creating starting from 0 with increment of 1.
	 * 
	 * @param y Y values to use to create the regression
	 * @return regression dataset to add to a chart
	 */
	public RegressionDataset createLinearRegressionDataset(List<Double> y) {
		return createLinearRegressionDataset(y, true);
	}

	/**
	 * Creates a regression dataset, to add to a chart, using the passed Y values.<br>
	 * X values are creating starting from 0 with increment of 1.
	 * 
	 * @param y Y values to use to create the regression
	 * @param loadData if <code>true</code>, the dataset will be loaded with calculated values
	 * @return regression dataset to add to a chart
	 */
	public RegressionDataset createLinearRegressionDataset(List<Double> y, boolean loadData) {
		return createLinearRegressionDataset(y, loadData, false);
	}

	/**
	 * Creates a regression dataset, to add to a chart, using the passed Y values.<br>
	 * X values are creating starting from 0 with increment of 1.
	 * 
	 * @param y Y values to use to create the regression
	 * @param loadData if <code>true</code>, the dataset will be loaded with calculated values
	 * @param hidden if <code>true</code>, it will be initially hidden
	 * @return regression dataset to add to a chart
	 */
	public RegressionDataset createLinearRegressionDataset(List<Double> y, boolean loadData, boolean hidden) {
		return createLinearRegressionDataset(y, 0, loadData, hidden);
	}

	/**
	 * Creates a regression dataset, to add to a chart, using the passed Y values.<br>
	 * X values are creating starting from passed starting point with increment of 1.
	 * 
	 * @param y Y values to use to create the regression
	 * @param starting starting value to create X values
	 * @return regression dataset to add to a chart
	 */
	public RegressionDataset createLinearRegressionDataset(List<Double> y, double starting) {
		return createLinearRegressionDataset(y, starting, true);
	}

	/**
	 * Creates a regression dataset, to add to a chart, using the passed Y values.<br>
	 * X values are creating starting from passed starting point with increment of 1.
	 * 
	 * @param y Y values to use to create the regression
	 * @param starting starting value to create X values
	 * @param loadData if <code>true</code>, the dataset will be loaded with calculated values
	 * @return regression dataset to add to a chart
	 */
	public RegressionDataset createLinearRegressionDataset(List<Double> y, double starting, boolean loadData) {
		return createLinearRegressionDataset(y, starting, loadData, false);
	}

	/**
	 * Creates a regression dataset, to add to a chart, using the passed Y values.<br>
	 * X values are creating starting from passed starting point with increment of 1.
	 * 
	 * @param y Y values to use to create the regression
	 * @param starting starting value to create X values
	 * @param loadData if <code>true</code>, the dataset will be loaded with calculated values
	 * @param hidden if <code>true</code>, it will be initially hidden
	 * @return regression dataset to add to a chart
	 */
	public RegressionDataset createLinearRegressionDataset(List<Double> y, double starting, boolean loadData, boolean hidden) {
		return createLinearRegressionDataset(y, starting, 1, loadData, hidden);
	}

	/**
	 * Creates a regression dataset, to add to a chart, using the passed Y values.<br>
	 * X values are creating starting from passed argument with increment of passed argument.
	 * 
	 * @param y Y values to use to create the regression
	 * @param starting starting value to create X values
	 * @param increment increment to apply to the starting value
	 * @return regression dataset to add to a chart
	 */
	public RegressionDataset createLinearRegressionDataset(List<Double> y, double starting, double increment) {
		return createLinearRegressionDataset(y, starting, increment, true);
	}

	/**
	 * Creates a regression dataset, to add to a chart, using the passed Y values.<br>
	 * X values are creating starting from passed argument with increment of passed argument.
	 * 
	 * @param y Y values to use to create the regression
	 * @param starting starting value to create X values
	 * @param increment increment to apply to the starting value
	 * @param loadData if <code>true</code>, the dataset will be loaded with calculated values
	 * @return regression dataset to add to a chart
	 */
	public RegressionDataset createLinearRegressionDataset(List<Double> y, double starting, double increment, boolean loadData) {
		return createLinearRegressionDataset(y, starting, increment, loadData, false);
	}

	/**
	 * Creates a regression dataset, to add to a chart, using the passed Y values.<br>
	 * X values are creating starting from passed argument with increment of passed argument.
	 * 
	 * @param y Y values to use to create the regression
	 * @param starting starting value to create X values
	 * @param increment increment to apply to the starting value
	 * @param loadData if <code>true</code>, the dataset will be loaded with calculated values
	 * @param hidden if <code>true</code>, it will be initially hidden
	 * @return regression dataset to add to a chart
	 */
	public RegressionDataset createLinearRegressionDataset(List<Double> y, double starting, double increment, boolean loadData, boolean hidden) {
		// checks list of y values
		checkYValues(y);
		// creates regression
		return createLinearRegressionDataset(MLUtil.get().loadXValues(y.size(), starting, increment), y, loadData, hidden);
	}

	/**
	 * Creates a regression dataset, to add to a chart, using the passed X and Y values.
	 * 
	 * @param x X values to use to create the regression
	 * @param y Y values to use to create the regression
	 * @return regression dataset to add to a chart
	 */
	public RegressionDataset createLinearRegressionDataset(List<Double> x, List<Double> y) {
		return createLinearRegressionDataset(x, y, true);
	}

	/**
	 * Creates a regression dataset, to add to a chart, using the passed X and Y values.
	 * 
	 * @param x X values to use to create the regression
	 * @param y Y values to use to create the regression
	 * @param loadData if <code>true</code>, the dataset will be loaded with calculated values
	 * @return regression dataset to add to a chart
	 */
	public RegressionDataset createLinearRegressionDataset(List<Double> x, List<Double> y, boolean loadData) {
		return createLinearRegressionDataset(x, y, loadData, false);
	}

	/**
	 * Creates a regression dataset, to add to a chart, using the passed X and Y values.
	 * 
	 * @param x X values to use to create the regression
	 * @param y Y values to use to create the regression
	 * @param loadData if <code>true</code>, the dataset will be loaded with calculated values
	 * @param hidden if <code>true</code>, it will be initially hidden
	 * @return regression dataset to add to a chart
	 */
	public RegressionDataset createLinearRegressionDataset(List<Double> x, List<Double> y, boolean loadData, boolean hidden) {
		return createLinearRegressionDataset(x, y, loadData, hidden, false, false);
	}

	/**
	 * Creates a regression dataset, to add to a chart, using the passed dataset, {@link DataPoint} based, data as source.
	 * 
	 * @param dataset dataset instance to use getting data as source
	 * @return regression dataset to add to a chart
	 */
	public RegressionDataset createLinearRegressionDatasetByDataset(HasDataPoints dataset) {
		return createLinearRegressionDatasetByDataset(dataset, true);
	}

	/**
	 * Creates a regression dataset, to add to a chart, using the passed dataset, {@link DataPoint} based, data as source.
	 * 
	 * @param dataset dataset instance to use getting data as source
	 * @param loadData if <code>true</code>, the dataset will be loaded with calculated values
	 * @return regression dataset to add to a chart
	 */
	public RegressionDataset createLinearRegressionDatasetByDataset(HasDataPoints dataset, boolean loadData) {
		return createLinearRegressionDatasetByDataset(dataset, loadData, false);
	}

	/**
	 * Creates a regression dataset, to add to a chart, using the passed dataset, {@link DataPoint} based, data as source.
	 * 
	 * @param dataset dataset instance to use getting data as source
	 * @param loadData if <code>true</code>, the dataset will be loaded with calculated values
	 * @param hidden if <code>true</code>, it will be initially hidden
	 * @return regression dataset to add to a chart
	 */
	public RegressionDataset createLinearRegressionDatasetByDataset(HasDataPoints dataset, boolean loadData, boolean hidden) {
		// checks dataset argument is consistent
		checkDataset(dataset);
		// gets data points
		List<DataPoint> dataPoints = dataset.getDataPoints();
		return createLinearRegressionDatasetByDataPoints(dataPoints, loadData, hidden);
	}

	/**
	 * Creates a regression dataset, to add to a chart, using the passed {@link DataPoint}s.
	 * 
	 * @param dataPoints data points list to use for creating the regression
	 * @return regression dataset to add to a chart
	 */
	public RegressionDataset createLinearRegressionDatasetByDataPoints(List<DataPoint> dataPoints) {
		return createLinearRegressionDatasetByDataPoints(dataPoints, true);
	}

	/**
	 * Creates a regression dataset, to add to a chart, using the passed {@link DataPoint}s.
	 * 
	 * @param dataPoints data points list to use for creating the regression
	 * @param loadData if <code>true</code>, the dataset will be loaded with calculated values
	 * @return regression dataset to add to a chart
	 */
	public RegressionDataset createLinearRegressionDatasetByDataPoints(List<DataPoint> dataPoints, boolean loadData) {
		return createLinearRegressionDatasetByDataPoints(dataPoints, loadData, false);
	}

	/**
	 * Creates a regression dataset, to add to a chart, using the passed {@link DataPoint}s.
	 * 
	 * @param dataPoints data points list to use for creating the regression
	 * @param loadData if <code>true</code>, the dataset will be loaded with calculated values
	 * @param hidden if <code>true</code>, it will be initially hidden
	 * @return regression dataset to add to a chart
	 */
	public RegressionDataset createLinearRegressionDatasetByDataPoints(List<DataPoint> dataPoints, boolean loadData, boolean hidden) {
		boolean hasDates = MLUtil.get().hasXAsDate(dataPoints);
		return createLinearRegressionDataset(MLUtil.get().parseDataPoints(true, dataPoints), MLUtil.get().parseDataPoints(false, dataPoints), loadData, hidden, hasDates, true);
	}

	/**
	 * Creates a regression dataset, to add to a chart, using the passed time series dataset, {@link TimeSeriesItem} based, data as source.
	 * 
	 * @param dataset dataset instance to use getting data as source
	 * @return regression dataset to add to a chart
	 */
	public RegressionDataset createLinearRegressionDatasetByDataset(HasTimeSeriesItems dataset) {
		return createLinearRegressionDatasetByDataset(dataset, true);
	}

	/**
	 * Creates a regression dataset, to add to a chart, using the passed time series dataset, {@link TimeSeriesItem} based, data as source.
	 * 
	 * @param dataset dataset instance to use getting data as source
	 * @param loadData if <code>true</code>, the dataset will be loaded with calculated values
	 * @return regression dataset to add to a chart
	 */
	public RegressionDataset createLinearRegressionDatasetByDataset(HasTimeSeriesItems dataset, boolean loadData) {
		return createLinearRegressionDatasetByDataset(dataset, loadData, false);
	}

	/**
	 * Creates a regression dataset, to add to a chart, using the passed time series dataset, {@link TimeSeriesItem} based, data as source.
	 * 
	 * @param dataset dataset instance to use getting data as source
	 * @param loadData if <code>true</code>, the dataset will be loaded with calculated values
	 * @param hidden if <code>true</code>, it will be initially hidden
	 * @return regression dataset to add to a chart
	 */
	public RegressionDataset createLinearRegressionDatasetByDataset(HasTimeSeriesItems dataset, boolean loadData, boolean hidden) {
		// checks dataset argument is consistent
		checkDataset(dataset);
		// gets data points
		List<TimeSeriesItem> items = dataset.getTimeSeriesData();
		return createLinearRegressionDatasetByTimeSeriesItems(items, loadData, hidden);
	}

	/**
	 * Creates a regression dataset, to add to a chart, using the passed {@link TimeSeriesItem}s.
	 * 
	 * @param items time series items list to use for creating the regression
	 * @return regression dataset to add to a chart
	 */
	public RegressionDataset createLinearRegressionDatasetByTimeSeriesItems(List<TimeSeriesItem> items) {
		return createLinearRegressionDatasetByTimeSeriesItems(items, true);
	}

	/**
	 * Creates a regression dataset, to add to a chart, using the passed {@link TimeSeriesItem}s.
	 * 
	 * @param items time series items list to use for creating the regression
	 * @param loadData if <code>true</code>, the dataset will be loaded with calculated values
	 * @return regression dataset to add to a chart
	 */
	public RegressionDataset createLinearRegressionDatasetByTimeSeriesItems(List<TimeSeriesItem> items, boolean loadData) {
		return createLinearRegressionDatasetByTimeSeriesItems(items, loadData, false);
	}

	/**
	 * Creates a regression dataset, to add to a chart, using the passed {@link TimeSeriesItem}s.
	 * 
	 * @param items time series items list to use for creating the regression
	 * @param loadData if <code>true</code>, the dataset will be loaded with calculated values
	 * @param hidden if <code>true</code>, it will be initially hidden
	 * @return regression dataset to add to a chart
	 */
	public RegressionDataset createLinearRegressionDatasetByTimeSeriesItems(List<TimeSeriesItem> items, boolean loadData, boolean hidden) {
		return createLinearRegressionDataset(MLUtil.get().parseTimeSeriesItems(true, items), MLUtil.get().parseTimeSeriesItems(false, items), loadData, hidden, true, true);
	}

	/**
	 * Creates a regression dataset, to add to a chart, using the passed X and Y values.
	 * 
	 * @param x X values to use to create the regression
	 * @param y Y values to use to create the regression
	 * @return regression dataset to add to a chart
	 */
	public RegressionDataset createLinearRegressionDatasetByDates(List<Date> x, List<Double> y) {
		return createLinearRegressionDatasetByDates(x, y, true);
	}

	/**
	 * Creates a regression dataset, to add to a chart, using the passed X and Y values.
	 * 
	 * @param x X values to use to create the regression
	 * @param y Y values to use to create the regression
	 * @param loadData if <code>true</code>, the dataset will be loaded with calculated values
	 * @return regression dataset to add to a chart
	 */
	public RegressionDataset createLinearRegressionDatasetByDates(List<Date> x, List<Double> y, boolean loadData) {
		return createLinearRegressionDatasetByDates(x, y, loadData, false);
	}

	/**
	 * Creates a regression dataset, to add to a chart, using the passed X and Y values.
	 * 
	 * @param x X values to use to create the regression
	 * @param y Y values to use to create the regression
	 * @param loadData if <code>true</code>, the dataset will be loaded with calculated values
	 * @param hidden if <code>true</code>, it will be initially hidden
	 * @return regression dataset to add to a chart
	 */
	public RegressionDataset createLinearRegressionDatasetByDates(List<Date> x, List<Double> y, boolean loadData, boolean hidden) {
		return createLinearRegressionDataset(MLUtil.get().parseDates(x), y, loadData, hidden, true, true);
	}

	/**
	 * Creates a regression dataset, to add to a chart, using the passed X and Y values.
	 * 
	 * @param x X values to use to create the regression
	 * @param y Y values to use to create the regression
	 * @param loadData if <code>true</code>, the dataset will be loaded with calculated values
	 * @param hidden if <code>true</code>, it will be initially hidden
	 * @param asDate if <code>true</code>, the dataset will manage {@link Date}s as X values
	 * @param asDataPoints if <code>true</code>, the dataset will manage {@link DataPoint}s as data
	 * @return regression dataset to add to a chart
	 */
	private RegressionDataset createLinearRegressionDataset(List<Double> x, List<Double> y, boolean loadData, boolean hidden, boolean asDate, boolean asDataPoints) {
		// creates regression
		LinearRegression regression = createLinearRegression(x, y);
		// creates dataset
		RegressionDataset dataset = new RegressionDataset(regression, hidden, asDate, asDataPoints);
		// defaults options
		dataset.setFill(false);
		dataset.setPointRadius(0);
		dataset.setPointHoverRadius(0);
		dataset.setPointHitRadius(0);
		dataset.setTension(0);
		// checks if the regression is valid
		if (loadData) {
			// creates y values
			// by passed x values
			List<Double> values = regression.predict(x);
			// checks if data points
			if (asDataPoints) {
				// loads data points
				loadDataPoints(dataset, x, values, asDate);
			} else {
				// sets data as list of doubles
				dataset.setData(values);
			}
		}
		// returns dataset instance
		return dataset;
	}

	// ------------------------
	// INTERNALS
	// ------------------------

	/**
	 * Checks if the passed list of double is consistent.
	 * 
	 * @param y list of doubles to check
	 */
	private void checkYValues(List<Double> y) {
		Checker.assertCheck(ArrayListHelper.isConsistent(y), "List of Y values is not consistent");
	}

	/**
	 * Checks if the dataset, passed as argument, is consistent.
	 * 
	 * @param <T> type of the dataset
	 * @param dataset dataset instance to check
	 */
	private <T extends HasDataPoints> void checkDataset(T dataset) {
		// checks dataset argument is consistent
		Checker.checkIfValid(dataset, "Dataset argument ");
		// gets wrapped dataset
		Dataset wrapped = dataset.getDataset();
		// checks dataset instance is consistent
		Checker.checkIfValid(wrapped, "Dataset instance ");
		// checks data type is consistent
		Checker.assertCheck(DataType.POINTS.equals(wrapped.getDataType()), "Data type is not " + DataType.POINTS.value() + " but " + wrapped.getDataType().value());
		// gets data points
		List<DataPoint> dataPoints = dataset.getDataPoints();
		// checks data points list is consistent
		Checker.assertCheck(!dataPoints.isEmpty(), "Data points list is empty");
	}

	/**
	 * Loads the data in the dataset as {@link DataPoint}s.
	 * 
	 * @param dataset dataset instance where adding data
	 * @param x X values to add, values used to create the regression
	 * @param y Y values to add, values used to create the regression
	 * @param asDate if <code>true</code>, the dataset will manage {@link Date}s as X values
	 */
	private void loadDataPoints(RegressionDataset dataset, List<Double> x, List<Double> y, boolean asDate) {
		// gets the data points list
		List<DataPoint> points = new LinkedList<>();
		// scans all values
		for (int i = 0; i < x.size(); i++) {
			// checks if store as date
			if (asDate) {
				// creates date
				Date date = new ImmutableDate(x.get(i).longValue());
				// creates data point and add to list
				points.add(new DataPoint(date, y.get(i)));
			} else {
				// creates data point and add to list
				points.add(new DataPoint(x.get(i), y.get(i)));
			}
		}
		// sorts data
		Collections.sort(points, ASCENDENT);
		// stores data points
		dataset.setDataPoints(points);
	}

}