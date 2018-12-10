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
import org.pepstock.charba.client.jsinterop.configuration.RadarOptions;
import org.pepstock.charba.client.jsinterop.data.RadarDataset;

/**
 * RADAR chart implementation.<br>
 * A radar chart is a way of showing multiple data points and the variation between them.<br>
 * They are often useful for comparing the points of two or more different data sets.
 * 
 * @author Andrea "Stock" Stocchero
 * @version 2.0
 */
public class RadarChart extends AbstractChart<RadarOptions, RadarDataset> {

	private final RadarOptions options;

	/**
	 * Builds the object.
	 */
	public RadarChart() {
		options = new RadarOptions(this, getChartOptions());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.IsChart#getType()
	 */
	@Override
	public Type getType() {
		return ChartType.radar;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.IsChart#getOptions()
	 */
	@Override
	public RadarOptions getOptions() {
		return options;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.IsChart#newDataset()
	 */
	@Override
	public RadarDataset newDataset() {
		return new RadarDataset();
	}

}