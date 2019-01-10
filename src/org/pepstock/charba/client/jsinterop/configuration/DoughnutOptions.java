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
package org.pepstock.charba.client.jsinterop.configuration;

import org.pepstock.charba.client.jsinterop.AbstractChart;
import org.pepstock.charba.client.jsinterop.ChartOptions;

/**
 * Specific options for DOUGHNUT chart. 
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 *
 */
public class DoughnutOptions extends AbstractPieOptions {

	/**
	 * Builds the object storing the chart instance and default options.
	 * 
	 * @param chart chart instance
	 * @param defaultValues default options
	 */
	public DoughnutOptions(AbstractChart<?, ?> chart, ChartOptions defaultValues) {
		super(chart, defaultValues);
	}

}