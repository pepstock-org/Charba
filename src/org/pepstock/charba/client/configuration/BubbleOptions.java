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

import org.pepstock.charba.client.ChartOptions;
import org.pepstock.charba.client.IsChart;

/**
 * Specific options for BUBBLE chart. It contains all properties for this kind of chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class BubbleOptions extends AbstractLineOptions {

	/**
	 * Builds the object storing the chart instance.
	 * 
	 * @param chart chart instance
	 * @param defaultValues defaults options of bubble chart
	 */
	public BubbleOptions(IsChart chart, ChartOptions defaultValues) {
		super(chart, defaultValues);
	}

}