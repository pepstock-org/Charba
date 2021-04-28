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
import org.pepstock.charba.client.enums.IndexAxis;

/**
 * Specific options for LINE chart. It contains all properties for this kind of chart.
 *
 * @author Andrea "Stock" Stocchero
 *
 */
public class LineOptions extends ScalesOptions implements HasLineOptions {

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
	public LineOptions(IsChart chart, IsDefaultScaledOptions defaultValues) {
		super(chart, defaultValues);
		// stores decimation and segment
		this.decimation = new Decimation(this);
		this.segment = new Segment(this);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.HasLineOptions#getOptions()
	 */
	@Override
	public final ScalesOptions getOptions() {
		return this;
	}

	/**
	 * Sets the base axis for the dataset.<br>
	 * Use {@link IndexAxis#Y} for vertical line.
	 * 
	 * @param indexAxis the base axis for the dataset
	 */
	public void setIndexAxis(IndexAxis indexAxis) {
		getConfiguration().setIndexAxis(indexAxis);
	}

	/**
	 * Returns the base axis for the dataset.
	 * 
	 * @return the base axis for the dataset
	 */
	public IndexAxis getIndexAxis() {
		return getConfiguration().getIndexAxis();
	}

}