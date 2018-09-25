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
package org.pepstock.charba.client.jsinterop.configuration.elements;

import org.pepstock.charba.client.jsinterop.options.EventableOptions;

/**
 * Arcs are used in the polar area, doughnut and pie charts.
 * 
 * @author Andrea "Stock" Stocchero
 * @see org.pepstock.charba.client.PolarAreaChart
 * @see org.pepstock.charba.client.DoughnutChart
 * @see org.pepstock.charba.client.PieChart
 */
public final class Arc extends BaseElement {

	/**
	 * Builds the object setting the java script padding object.
	 * 
	 * @param chart chart instance
	 */
	public Arc(EventableOptions options) {
		super(options, options.getElements().getArc());
	}

}