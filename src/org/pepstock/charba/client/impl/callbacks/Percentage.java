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
package org.pepstock.charba.client.impl.callbacks;

import java.util.List;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.callbacks.ScriptableContext;
import org.pepstock.charba.client.data.BarDataset;
import org.pepstock.charba.client.data.DataPoint;
import org.pepstock.charba.client.data.Dataset;
import org.pepstock.charba.client.data.FloatingData;
import org.pepstock.charba.client.data.HasDataPoints;
import org.pepstock.charba.client.enums.DataType;

/**
 * Utility to calculate the percentage of the value based on the datasets of chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Percentage {

	/**
	 * To avoid any instantiation
	 */
	private Percentage() {
		// do nothing
	}

	/**
	 * Computes the percentage of the value based on the data of datasets. If the dataset is composed by {@link DataPoint}, it uses the {@link DataPoint#getY()} value to compute
	 * the percentage. The stack is ignored.
	 * 
	 * @param chart chart instance
	 * @param value current value to be evaluated
	 * @param context data labels plugin context
	 * @return the percentage value, a double between 0 and 1. If the datasets does not contains any data, it will return {@link Double#NaN}.
	 */
	public static double compute(IsChart chart, double value, ScriptableContext context) {
		return compute(chart, value, context, false);
	}

	/**
	 * Computes the percentage of the value based on the data of datasets. If the dataset is composed by {@link DataPoint}, it uses the {@link DataPoint#getY()} value to compute
	 * the percentage.
	 * 
	 * @param chart chart instance
	 * @param value current value to be evaluated
	 * @param context data labels plugin context
	 * @param stacked if <code>true</code>, it calculates the total at the same data index (cross datasets), otherwise if <code>false</code> it calculates the total at the same
	 *            dataset index (single dataset)
	 * @return the percentage value, a double between 0 and 1. If the datasets does not contains any data, it will return {@link Double#NaN}.
	 */
	public static double compute(IsChart chart, double value, ScriptableContext context, boolean stacked) {
		// checks if arguments are consistent
		if (!IsChart.isConsistent(chart) || context == null) {
			// if not consistent
			return Double.NaN;
		}
		// creates the total reference
		double total;
		// checks if stacked
		if (stacked) {
			// get the total for stacked datasets
			total = getTotalForStacked(chart, context);
		} else {
			// get the total for no stacked datasets
			total = getTotal(chart, context);
		}
		// if total is zero
		// means that no datasets are available data
		if (total == 0) {
			// returns NaN
			return Double.NaN;
		}
		// calculates the percentage
		return Math.abs(value) / total;
	}

	/**
	 * Calculates the total for data values for stacked dataset.
	 * 
	 * @param chart chart instance
	 * @param context data labels plugin context
	 * @return the total for data values for stacked dataset.
	 */
	private static double getTotalForStacked(IsChart chart, ScriptableContext context) {
		// creates the total reference
		double total = 0D;
		// scans all datasets
		for (Dataset ds : chart.getData().getDatasets()) {
			// if dataset contains data points
			if (DataType.POINTS.equals(ds.getDataType()) && ds instanceof HasDataPoints) {
				// then dataset is data point container
				// and then cast it
				HasDataPoints hasDataPoints = (HasDataPoints) ds;
				// gets the data points at data index
				DataPoint point = hasDataPoints.getDataPoints().get(context.getDataIndex());
				// adds the Y value to the total
				total = total + Math.abs(point.getY());
			} else if (DataType.ARRAYS.equals(ds.getDataType()) && ds instanceof BarDataset) {
				// then dataset is floating bar chart
				// and then cast it
				BarDataset barDataset = (BarDataset) ds;
				// gets the floating data
				FloatingData data = barDataset.getFloatingData().get(context.getDataIndex());
				// adds the absolute differences between start and end
				total = total + data.getAbsValue();
			} else if (DataType.NUMBERS.equals(ds.getDataType())) {
				// if here, the dataset has got data as doubles
				// then it get the double at data index
				double data = ds.getData().get(context.getDataIndex());
				// adds it to total
				total = total + Math.abs(data);
			}
		}
		return total;
	}

	/**
	 * Calculates the total for data values for NO stacked dataset.
	 * 
	 * @param chart chart instance
	 * @param context data labels plugin context
	 * @return the total for data values for NO stacked dataset.
	 */
	private static double getTotal(IsChart chart, ScriptableContext context) {
		// creates the total reference
		double total = 0D;
		// if here, the argument of stack is false
		// then it calculates the values inside the dataset
		Dataset ds = chart.getData().getDatasets().get(context.getDatasetIndex());
		// if dataset contains data points
		if (DataType.POINTS.equals(ds.getDataType()) && ds instanceof HasDataPoints) {
			// then dataset is data points container
			// and then cast it
			HasDataPoints hasDataPoints = (HasDataPoints) ds;
			// gets the data points
			List<DataPoint> points = hasDataPoints.getDataPoints();
			// scans data points
			for (DataPoint dataPoint : points) {
				// adds the Y value to the total
				total = total + Math.abs(dataPoint.getY());
			}
		} else if (DataType.ARRAYS.equals(ds.getDataType()) && ds instanceof BarDataset) {
			// then dataset is floating bar chart
			// and then cast it
			BarDataset barDataset = (BarDataset) ds;
			// gets the floating data
			List<FloatingData> data = barDataset.getFloatingData();
			// scans data
			for (FloatingData dataFloat : data) {
				// adds the absolute differences between start and end
				total = total + dataFloat.getAbsValue();
			}
		} else if (DataType.NUMBERS.equals(ds.getDataType())) {
			// if here, the dataset has got data as doubles
			// then it get the doubles
			List<Double> data = ds.getData();
			// scans doubles
			for (Double dataValue : data) {
				// adds it to total
				total = total + Math.abs(dataValue);
			}
		}
		return total;
	}
}
