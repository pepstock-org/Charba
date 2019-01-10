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
package org.pepstock.charba.client.jsinterop;

import org.pepstock.charba.client.ChartType;
import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.jsinterop.configuration.PolarAreaOptions;
import org.pepstock.charba.client.jsinterop.data.PolarAreaDataset;

/**
 * POLAR AREA chart implementation.<br>
 * Polar area charts are similar to pie charts, but each segment has the same angle - the radius of the segment differs
 * depending on the value.<br>
 * This type of chart is often useful when we want to show a comparison data similar to a pie chart, but also show a scale of
 * values for context.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 */
public class PolarAreaChart extends AbstractChart<PolarAreaOptions, PolarAreaDataset> {

	private final PolarAreaOptions options;

	/**
	 * Builds the object.
	 */
	public PolarAreaChart() {
		options = new PolarAreaOptions(this, getChartOptions());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.IsChart#getType()
	 */
	@Override
	public Type getType() {
		return ChartType.polarArea;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.IsChart#getOptions()
	 */
	@Override
	public PolarAreaOptions getOptions() {
		return options;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.IsChart#newDataset()
	 */
	@Override
	public PolarAreaDataset newDataset() {
		return new PolarAreaDataset();
	}

}