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
package org.pepstock.charba.client.impl.charts;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.defaults.IsDefaultOptions;

/**
 * The Gauge chart allows a number of properties to be specified for each dataset. These are used to set display properties for
 * a specific dataset.<br>
 * Is equals of Meter dataset.<br>
 * The minimum value of data is 0 (see {@link MeterDataset#MINIMUM_VALUE}).<br>
 * To set the data, is mandatory to use {@link MeterDataset#setValue(double)}) method instead of
 * {@link org.pepstock.charba.client.data.Dataset#setData(double...)}) one.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class GaugeDataset extends MeterDataset {

	// list if thresholds
	private final List<Threshold> thresholds = new LinkedList<>();
	// current status related to threshold
	private IsThreshold current = GaugeThreshold.NORMAL.getThreshold();
	// flag to understand if the absolute or percentage value must be used
	// to compare thresholds
	private boolean percentageThreshold = true;

	/**
	 * Comparator to sort the thresholds using the thresholds value, always ASCENDING
	 */
	private static final Comparator<Threshold> COMPARATOR = (Threshold o1, Threshold o2) -> (int) (o1.getValue() - o2.getValue());

	/**
	 * Creates a dataset for gauge with maximum value of data. It uses the global options has default.
	 * 
	 * @param max maximum value of data.
	 */
	public GaugeDataset(double max) {
		this(max, Defaults.get().getGlobal());
	}

	/**
	 * Creates a dataset setting the maximum value of dataset and defaults value.
	 * 
	 * @param max maximum value of dataset.
	 * @param defaultValues default options
	 */
	public GaugeDataset(double max, IsDefaultOptions defaultValues) {
		super(max, defaultValues);
		// loads all gauge thresholds by default
		for (GaugeThreshold t : GaugeThreshold.values()) {
			thresholds.add(t.getThreshold());
		}
		// sets current color
		super.setColor(current.getColor());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.impl.charts.MeterDataset#setValueColor(org.pepstock.charba.client.colors.IsColor)
	 */
	@Override
	public void setColor(IsColor valueColor) {
		// value color must be override because
		// depends on threshold
		// checking if consistent
		super.setColor(current.getColor() != null ? current.getColor().toRGBA() : GaugeThreshold.NORMAL.getColor().toRGBA());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.impl.charts.MeterDataset#setValueColor(java.lang.String)
	 */
	@Override
	public void setColor(String valueColor) {
		// value color must be override because
		// depends on threshold
		// checking if consistent
		super.setColor(current.getColor() != null ? current.getColor().toRGBA() : GaugeThreshold.NORMAL.getColor().toRGBA());
	}

	/**
	 * Returns the current threshold.
	 * 
	 * @return the current
	 */
	public IsThreshold getCurrent() {
		return current;
	}

	/**
	 * Returns <code>true</code> if percentage threshold is used, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if percentage threshold is used, otherwise <code>false</code>
	 */
	public boolean isPercentageThreshold() {
		return percentageThreshold;
	}

	/**
	 * Sets <code>true</code> if percentage threshold is used, otherwise <code>false</code>.
	 * 
	 * @param percentageThreshold <code>true</code> if percentage threshold is used, otherwise <code>false</code>
	 */
	public void setPercentageThreshold(boolean percentageThreshold) {
		// sets to use the value of thresholds as percentage of value
		this.percentageThreshold = percentageThreshold;
		// checks the threshold
		current = checkLevel();
		// sets color
		setColor(current.getColor());
	}

	/**
	 * Sets all thresholds to use for this gauge.
	 * 
	 * @param thres thresholds array.
	 */
	public void setThresholds(Threshold... thres) {
		// clears existing thresholds
		thresholds.clear();
		// adds all new ones
		thresholds.addAll(Arrays.asList(thres));
		// checks the threshold
		current = checkLevel();
		// sets color
		setColor(current.getColor());
	}

	/**
	 * Gets all thresholds.
	 * 
	 * @return all thresholds
	 */
	public List<Threshold> getThresholds() {
		return thresholds;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.MeterDataset#setValue(double)
	 */
	@Override
	public void setValue(double value) {
		// sets value
		super.setValue(value);
		// checks the threshold
		current = checkLevel();
		// sets color
		setColor(current.getColor());
	}

	/**
	 * Checks the threshold status for this dataset, comparing the value with thresholds.
	 * 
	 * @return the current threshold of this dataset.
	 */
	private Threshold checkLevel() {
		// checks if the thresholds are set
		if (!thresholds.isEmpty()) {
			// sorts the thresholds by value
			Collections.sort(thresholds, COMPARATOR);
			// if the percentage value must be used to compare the threshold
			// or to use the absolute value
			final double valueToCheck = isPercentageThreshold() ? getValue() / getMax() * 100 : getValue();
			double lowLimit = 0;
			// scans all thresholds
			for (Threshold t : thresholds) {
				// checks if value is in the threshold
				if (t.isInRange(valueToCheck, lowLimit)) {
					// returns threshold
					return t;
				}
				// stores the previous threshold value as low limit
				// for next check
				lowLimit = t.getValue();
			}
			// if here, doesn't match and then the last threshold is returned
			return ((LinkedList<Threshold>) thresholds).getLast();
		}
		// default threshold is returned
		return GaugeThreshold.NORMAL.getThreshold();
	}

}