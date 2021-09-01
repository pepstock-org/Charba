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
package org.pepstock.charba.client.sankey;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.configuration.ScalesOptions;
import org.pepstock.charba.client.defaults.IsDefaultScaledOptions;

/**
 * Specific options for sankey chart.<br>
 * Disables legend at the beginning but you can enable when needed.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class SankeyOptions extends ScalesOptions {

	/**
	 * Builds the object storing the chart instance and defaults.
	 * 
	 * @param chart chart instance
	 * @param defaultValues defaults of chart
	 */
	SankeyOptions(IsChart chart, IsDefaultScaledOptions defaultValues) {
		super(chart, defaultValues);
		// disables the legend at the beginning
		getLegend().setDisplay(false);
	}

}