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
import org.pepstock.charba.client.callbacks.TickCallback;
import org.pepstock.charba.client.configuration.Axis;
import org.pepstock.charba.client.data.Dataset;
import org.pepstock.charba.client.items.DatasetMetaItem;

import com.google.gwt.i18n.client.NumberFormat;

/**
 * Implementation of tick callback in order to avoid that when all datasets are hidden, the ticks will get a wrong double
 * precision.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class NoSelectedDatasetTicksCallback implements TickCallback {
	
	/**
	 * Default number format to apply to ticks, <b>{@value DEFAULT_FORMAT}</b>.
	 */
	public static final String DEFAULT_FORMAT = "0.0";

	// it formats the number of ticks 
	private final NumberFormat numberFormat;
	// instance of format as string
	private final String format;
	
	/**
	 * Creates the callback using the {@link NoSelectedDatasetTicksCallback#DEFAULT_FORMAT}. 
	 */
	public NoSelectedDatasetTicksCallback() {
		this(DEFAULT_FORMAT);
	}

	/**
	 * Creates the callback using the argument as number format to apply to ticks.
	 * 
	 * @param stringFormat number format to apply, if <code>null</code> it uses {@link NoSelectedDatasetTicksCallback#DEFAULT_FORMAT}
	 */
	public NoSelectedDatasetTicksCallback(String stringFormat) {
		// stores the format as string
		this.format = stringFormat != null ? stringFormat : DEFAULT_FORMAT;
		// creates the number fomramt
		this.numberFormat = NumberFormat.getFormat(format);
	}

	/**
	 * Returns the number format applied to ticks.
	 * 
	 * @return the number format applied to ticks.
	 */
	public String getFormat() {
		return format;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.callbacks.TickCallback#onCallback(org.pepstock.charba.client.configuration .Axis, double,
	 * int, java.util.List)
	 */
	@Override
	public String onCallback(Axis axis, double value, int index, List<Double> values) {
		// gets chart instance
		IsChart chart = axis.getChart();
		// flags to know if all datasets are hidden
		boolean allHidden = false;
		// gets datasets
		List<Dataset> dss = chart.getData().getDatasets();
		// scans them by for cycle to have the index for retrieving
		// the dataset metadata
		for (int i = 0; i < dss.size(); i++) {
			// gets metadata to know if is hidden
			DatasetMetaItem metadata = chart.getDatasetMeta(i);
			// checks if metadata is null.
			// it happens when the chart is drawing for the first time
			// but at the first time 1 dataset is always visible
			if (metadata != null) {
				// OR on dataset visibility
				allHidden = allHidden || metadata.isHidden();
			}
		}
		// if all datasets are hidden
		if (allHidden) {
			// uses the tick value (double) provided by CHART.js
			// applying the number format
			return numberFormat.format(value);
		}
		// otherwise will return the tick value as string
		return String.valueOf(value);
	}

}
