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
package org.pepstock.charba.client.impl.callbacks;

import java.util.List;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.callbacks.TickCallback;
import org.pepstock.charba.client.configuration.Axis;
import org.pepstock.charba.client.data.Dataset;
import org.pepstock.charba.client.items.DatasetItem;
import org.pepstock.charba.client.utils.Utilities;

/**
 * Implementation of tick callback in order to avoid that when all datasets are hidden, the ticks will get a wrong double precision.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class NoSelectedDatasetTicksCallback implements TickCallback {

	/**
	 * Default precision, <b>{@value DEFAULT_PRECISION}</b>.
	 */
	public static final int DEFAULT_PRECISION = 1;

	// it sets the precision of ticks
	private final int precision;

	/**
	 * Creates the callback using the {@link NoSelectedDatasetTicksCallback#DEFAULT_PRECISION}.
	 */
	public NoSelectedDatasetTicksCallback() {
		this(DEFAULT_PRECISION);
	}

	/**
	 * Creates the callback using the argument as amount of decimals places to apply to ticks.
	 * 
	 * @param precision amount of decimals places
	 */
	public NoSelectedDatasetTicksCallback(int precision) {
		// stores the precision
		this.precision = precision;
	}

	/**
	 * Returns the precision applied to ticks.
	 * 
	 * @return the precision applied to ticks.
	 */
	public int getPrecision() {
		return precision;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.callbacks.TickCallback#onCallback(org.pepstock.charba.client.configuration .Axis, double, int, java.util.List)
	 */
	@Override
	public String onCallback(Axis axis, double value, int index, List<Double> values) {
		// checks consistent of axis and chart instances
		if (axis != null && IsChart.isConsistent(axis.getChart())) {
			// gets chart instance
			IsChart chart = axis.getChart();
			// flags to know if all datasets are hidden
			boolean allHidden = false;
			// gets datasets
			List<Dataset> dss = chart.getData().getDatasets();
			// scans them to have the index for retrieving
			// the visibility
			for (int i = 0; i < dss.size(); i++) {
				// gets dataset item to know if is hidden
				DatasetItem datasetItem = chart.getDatasetItem(i);
				// checks if item is null.
				// it happens when the chart is drawing for the first time
				// but at the first time 1 dataset is always visible
				if (datasetItem != null) {
					// OR on dataset visibility
					allHidden = allHidden || datasetItem.isHidden();
				}
			}
			// if all datasets are hidden
			if (allHidden) {
				// uses the tick value (double) provided by CHART.js
				// applying the precision
				return Utilities.applyPrecision(value, precision);
			}
		}
		// otherwise will return the tick value as string
		return String.valueOf(value);
	}

}