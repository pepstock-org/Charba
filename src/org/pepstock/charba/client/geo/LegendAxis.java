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
package org.pepstock.charba.client.geo;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.configuration.AxisType;
import org.pepstock.charba.client.configuration.CartesianLinearAxis;
import org.pepstock.charba.client.enums.AxisKind;
import org.pepstock.charba.client.options.ScaleId;

/**
 * Base scale for color and size axes, needed for GOE charts implementation.<br>
 * It contains all options needed to configure the scale with a legend.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class LegendAxis extends CartesianLinearAxis {

	/**
	 * Default display options, <b>{@value DEFAULT_DISPLAY}</b>.
	 */
	public static final boolean DEFAULT_DISPLAY = true;

	// internal legend instance
	private Legend legend;

	/**
	 * Builds the object storing the chart instance and axis type.
	 * 
	 * @param chart chart instance
	 * @param id axis id
	 * @param type axis type
	 */
	LegendAxis(IsChart chart, ScaleId id, AxisType type) {
		super(chart, id, type, AxisKind.X);
	}

	/**
	 * Reloads the extended scale
	 */
	final void resetLegend() {
		// resets legend
		this.legend = null;
	}
	
	/**
	 * Returns the legend axis mapper.
	 * 
	 * @return the legend axis mapper
	 */
	abstract LegendAxisMapper getMapper();

	/**
	 * Returns the legend configuration.
	 * 
	 * @return the legend configuration
	 */
	public final Legend getLegend() {
		// checks if legend is initialize
		if (legend == null) {
			// gets and stores the legend
			this.legend = getMapper().getLegend();
		}
		return legend;
	}

}
