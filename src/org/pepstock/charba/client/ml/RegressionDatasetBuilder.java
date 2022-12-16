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

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.ImmutableDate;
import org.pepstock.charba.client.data.DataPoint;
import org.pepstock.charba.client.data.Dataset;
import org.pepstock.charba.client.data.HasDataPoints;
import org.pepstock.charba.client.data.HasTimeSeriesItems;
import org.pepstock.charba.client.data.TimeSeriesItem;
import org.pepstock.charba.client.enums.DataType;

/**
 * Builds regressions datasets instances.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class RegressionDatasetBuilder extends AbstractBuilder<RegressionDatasetBuilder> {

	// sorter of data point in ascendent mode
	private static final Comparator<DataPoint> ASCENDENT = MLUtil.get()::sort;
	// flags references
	private boolean loadData = true;
	private boolean hidden = false;
	// internal flagsto set hte right type of dataset data
	private boolean asDates = false;
	private boolean asDataPoints = false;

	/**
	 * To avoid any instantiation
	 */
	private RegressionDatasetBuilder() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.ml.SamplesContainer#getBuilder()
	 */
	@Override
	RegressionDatasetBuilder getBuilder() {
		return this;
	}

	/**
	 * Creates new regression dataset builder, without any sample.
	 * 
	 * @return new regression dataset builder, without any sample
	 */
	public static RegressionDatasetBuilder create() {
		return new RegressionDatasetBuilder();
	}

	/**
	 * Creates new regression dataset builder, using the passed data as samples.
	 * 
	 * @param x X values to use to create the regression
	 * @param y Y values to use to create the regression
	 * @return new regression dataset builder
	 */
	public static RegressionDatasetBuilder create(List<Double> x, List<Double> y) {
		// creates builder
		RegressionDatasetBuilder builder = create();
		// stores samples
		builder.setSamples(x, y);
		// returns builder
		return builder;
	}

	/**
	 * Creates new regression dataset cloning the passed dataset.<br>
	 * The options and the data are NOT cloned.
	 * 
	 * @param dataset dataset to clone.
	 * @return new regression dataset instance
	 */
	public static RegressionDataset build(RegressionDataset dataset) {
		// checks dataset
		RegressionDataset checked = Checker.checkAndGetIfValid(dataset, "Dataset to clone");
		// gets regression descritor
		IsRegression regression = RegressionFactory.create(checked.getRegression());
		// creates new dataset
		RegressionDataset newDataset = new RegressionDataset(regression, false, checked.areDatesManaged(), checked.areDataPointsManaged());
		// defaults options
		setDefaultOptions(dataset);
		// returns cloned dataset
		return newDataset;
	}

	// ----------------------
	// OPTIONS SETTING
	// ----------------------

	/**
	 * Sets <code>true</code> if the samples will be loaded as dataset data.
	 * 
	 * @param loadData <code>true</code> if the samples will be loaded as dataset data
	 * @return new regression dataset builder
	 */
	public RegressionDatasetBuilder setLoadData(boolean loadData) {
		this.loadData = loadData;
		return this;
	}

	/**
	 * Sets <code>true</code> if the dataset will be created in hidden status.
	 * 
	 * @param hidden <code>true</code> if the dataset will be created in hidden status
	 * @return new regression dataset builder
	 */
	public RegressionDatasetBuilder setHidden(boolean hidden) {
		this.hidden = hidden;
		return this;
	}

	// --------------------------------
	// SAMPLES SETTINGS
	// --------------------------------

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.ml.SamplesContainer#setSamples(java.util.List, double, double)
	 */
	@Override
	public RegressionDatasetBuilder setSamples(List<Double> y, double starting, double increment) {
		asDates = false;
		asDataPoints = false;
		return super.setSamples(y, starting, increment);
	}

	/**
	 * Sets the samples by the passed dataset, {@link DataPoint} based, data as source.
	 * 
	 * @param dataset dataset instance to use getting data as source
	 * @return new regression dataset builder
	 */
	public RegressionDatasetBuilder setSamples(HasDataPoints dataset) {
		// checks dataset argument is consistent
		checkDataset(dataset);
		// gets data points
		List<DataPoint> dataPoints = dataset.getDataPoints();
		return setSamplesByDataPoints(dataPoints);
	}

	/**
	 * Sets the samples by the passed {@link DataPoint}s.
	 * 
	 * @param dataPoints data points list to use for creating the regression
	 * @return new regression dataset builder
	 */
	@Override
	public RegressionDatasetBuilder setSamplesByDataPoints(List<DataPoint> dataPoints) {
		asDates = MLUtil.get().hasXAsDate(dataPoints);
		asDataPoints = true;
		return super.setSamplesByDataPoints(dataPoints);
	}

	/**
	 * Sets the samples by the passed time series dataset, {@link TimeSeriesItem} based, data as source.
	 * 
	 * @param dataset dataset instance to use getting data as source
	 * @return new regression dataset builder
	 */
	public RegressionDatasetBuilder setSamples(HasTimeSeriesItems dataset) {
		// checks dataset argument is consistent
		checkDataset(dataset);
		// gets data points
		List<TimeSeriesItem> items = dataset.getTimeSeriesData();
		return setSamplesByTimeSeriesItems(items);
	}

	/**
	 * Sets the samples by the passed {@link TimeSeriesItem}s.
	 * 
	 * @param items time series items list to use for creating the regression
	 * @return new regression dataset builder
	 */
	@Override
	public RegressionDatasetBuilder setSamplesByTimeSeriesItems(List<TimeSeriesItem> items) {
		asDates = true;
		asDataPoints = true;
		return setSamplesInternally(MLUtil.get().parseTimeSeriesItems(true, items), MLUtil.get().parseTimeSeriesItems(false, items));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.ml.SamplesContainer#setSamples(java.util.List, java.util.List)
	 */
	@Override
	public RegressionDatasetBuilder setSamples(List<Double> x, List<Double> y) {
		asDates = false;
		asDataPoints = false;
		return super.setSamples(x, y);
	}

	/**
	 * Sets the samples by the passed X and Y values.
	 * 
	 * @param x X values to use to create the regression
	 * @param y Y values to use to create the regression
	 * @return new regression dataset builder
	 */
	@Override
	public RegressionDatasetBuilder setSamplesByDates(List<Date> x, List<Double> y) {
		asDates = true;
		asDataPoints = true;
		return super.setSamplesByDates(x, y);
	}

	// --------------------------------
	// BUILD
	// --------------------------------

	/**
	 * Creates a regression dataset, to add to a chart.
	 * 
	 * @return regression dataset to add to a chart
	 */
	public RegressionDataset buildLinearRegression() {
		// gets samples
		List<Double> x = getX();
		List<Double> y = getY();
		// creates regression
		LinearRegression regression = RegressionBuilder.create(x, y).buildLinearRegression();
		// returns dataset instance
		return buildDataset(regression, x);
	}

	/**
	 * Creates a regression dataset, to add to a chart.<br>
	 * It uses the default degree, <b>{@value PolynomialRegression#DEFAULT_DEGREE}</b>.
	 * 
	 * @return regression dataset to add to a chart
	 */
	public RegressionDataset buildPolynomialRegression() {
		return buildPolynomialRegression(PolynomialRegression.DEFAULT_DEGREE);
	}

	/**
	 * Creates a regression dataset, to add to a chart, using the passed maximum degree.
	 * 
	 * @param degree the maximum degree of the polynomial
	 * @return regression dataset to add to a chart
	 */
	public RegressionDataset buildPolynomialRegression(int degree) {
		// gets samples
		List<Double> x = getX();
		List<Double> y = getY();
		// creates regression
		PolynomialRegression regression = RegressionBuilder.create(x, y).buildPolynomialRegression(degree);
		// returns dataset instance
		return buildDataset(regression, x);
	}

	/**
	 * Creates a regression dataset, to add to a chart.
	 * 
	 * @return regression dataset to add to a chart
	 */
	public RegressionDataset buildPowerRegression() {
		// gets samples
		List<Double> x = getX();
		List<Double> y = getY();
		// creates regression
		PowerRegression regression = RegressionBuilder.create(x, y).buildPowerRegression();
		// returns dataset instance
		return buildDataset(regression, x);
	}

	/**
	 * Creates a regression dataset, to add to a chart.
	 * 
	 * @return regression dataset to add to a chart
	 */
	public RegressionDataset buildExponentialRegression() {
		// gets samples
		List<Double> x = getX();
		List<Double> y = getY();
		// creates regression
		ExponentialRegression regression = RegressionBuilder.create(x, y).buildExponentialRegression();
		// returns dataset instance
		return buildDataset(regression, x);
	}

	/**
	 * Creates a regression dataset, to add to a chart.
	 * 
	 * @return regression dataset to add to a chart
	 */
	public RegressionDataset buildTheilSenRegression() {
		// gets samples
		List<Double> x = getX();
		List<Double> y = getY();
		// creates regression
		TheilSenRegression regression = RegressionBuilder.create(x, y).buildTheilSenRegression();
		// returns dataset instance
		return buildDataset(regression, x);
	}

	/**
	 * Creates a regression dataset, to add to a chart.<br>
	 * It uses the default degree, <b>{@value RobustPolynomialRegression#DEFAULT_DEGREE}</b>.
	 * 
	 * @return regression dataset to add to a chart
	 */
	public RegressionDataset buildRobustPolynomialRegression() {
		return buildPolynomialRegression(RobustPolynomialRegression.DEFAULT_DEGREE);
	}

	/**
	 * Creates a regression dataset, to add to a chart, using the passed maximum degree.
	 * 
	 * @param degree the maximum degree of the robust polynomial
	 * @return regression dataset to add to a chart
	 */
	public RegressionDataset buildRobustPolynomialRegression(int degree) {
		// gets samples
		List<Double> x = getX();
		List<Double> y = getY();
		// creates regression
		RobustPolynomialRegression regression = RegressionBuilder.create(x, y).buildRobustPolynomialRegression(degree);
		// returns dataset instance
		return buildDataset(regression, x);
	}

	// ------------------------
	// INTERNALS
	// ------------------------

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

	/**
	 * Creates a regression dataset, to add to a chart, using the passed X and Y values.
	 * 
	 * @param regression regression instance
	 * @param x X values to use to create the regression
	 * @return regression dataset to add to a chart
	 */
	private RegressionDataset buildDataset(IsRegression regression, List<Double> x) {
		// creates dataset
		RegressionDataset dataset = new RegressionDataset(regression, hidden, asDates, asDataPoints);
		// defaults options
		setDefaultOptions(dataset);
		// checks if the regression is valid
		if (loadData) {
			// creates y values
			// by passed x values
			List<Double> values = regression.predict(x);
			// checks if data points
			if (asDataPoints) {
				// loads data points
				loadDataPoints(dataset, x, values, asDates);
			} else {
				// sets data as list of doubles
				dataset.setData(values);
			}
		}
		// returns dataset instance
		return dataset;
	}

	/**
	 * Sets the default options for a regression dataset
	 * 
	 * @param dataset dataset to change
	 */
	private static void setDefaultOptions(RegressionDataset dataset) {
		// defaults options
		dataset.setFill(false);
		dataset.setPointRadius(0);
		dataset.setPointHoverRadius(0);
		dataset.setPointHitRadius(0);
		dataset.setTension(0);
	}
}