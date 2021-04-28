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
package org.pepstock.charba.client.configuration;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.defaults.IsDefaultScaledOptions;

/**
 * Specific options for LINE chartfor time series. It contains all properties for this kind of chart.
 *
 * @author Andrea "Stock" Stocchero
 *
 */
public class TimeSeriesLineOptions extends TimeSeriesOptions implements HasLineOptions {
	
	// decimation instance
	private final Decimation decimation;
	// segment instance
	private final Segment segment;

	/**
	 * Builds the object storing the chart instance and default values.
	 * 
	 * @param chart chart instance
	 * @param defaultValues defaults options of line chart
	 */
	public TimeSeriesLineOptions(IsChart chart, IsDefaultScaledOptions defaultValues) {
		super(chart, defaultValues);
		// stores decimation and segment
		this.decimation = new Decimation(this);
		this.segment = new Segment(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.HasLineOptions#getOptions()
	 */
	@Override
	public TimeSeriesOptions getOptions() {
		return this;
	}
	
	/**
	 * Returns the decimation plugin element.
	 * 
	 * @return the decimation plugin element
	 */
	public Decimation getDecimation() {
		return decimation;
	}
	
	/**
	 * Returns the segment element.
	 * 
	 * @return the segment element
	 */
	public Segment getSegment() {
		return segment;
	}

}