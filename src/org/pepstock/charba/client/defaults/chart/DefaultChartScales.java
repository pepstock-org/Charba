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
package org.pepstock.charba.client.defaults.chart;

import org.pepstock.charba.client.defaults.IsDefaultScale;
import org.pepstock.charba.client.defaults.IsDefaultScales;
import org.pepstock.charba.client.defaults.globals.DefaultsBuilder;
import org.pepstock.charba.client.enums.DefaultScaleId;
import org.pepstock.charba.client.options.Scales;

/**
 * Defaults for scales/axes option element, based on chart type.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultChartScales implements IsDefaultScales {

	private final IsDefaultScale xAxis;

	private final IsDefaultScale yAxis;

	private final IsDefaultScale rAxis;

	/**
	 * Creates the object by scales option element instance.
	 * 
	 * @param scales scales option element instance.
	 */
	public DefaultChartScales(Scales scales) {
		// checks if argument is consistent
		if (scales == null) {
			// exception
			throw new IllegalArgumentException("Scales instance is not consistent");
		}
		// checks if there is any x axes
		if (!scales.getAxes().isEmpty()) {
			// looks for X default
			if (scales.hasAxis(DefaultScaleId.X)) {
				// gets the stored axis as X
				xAxis = new DefaultChartScale(scales.getAxis(DefaultScaleId.X));
			} else {
				// uses the default
				xAxis = DefaultsBuilder.get().getScaledOptions().getScales().getXAxis();
			}
			// looks for X default
			if (scales.hasAxis(DefaultScaleId.Y)) {
				// gets the stored axis as Y
				yAxis = new DefaultChartScale(scales.getAxis(DefaultScaleId.Y));
			} else {
				// uses the default
				yAxis = DefaultsBuilder.get().getScaledOptions().getScales().getYAxis();
			}
			// looks for X default
			if (scales.hasAxis(DefaultScaleId.R)) {
				// gets the stored axis as Y
				rAxis = new DefaultChartScale(scales.getAxis(DefaultScaleId.R));
			} else {
				// uses the default
				rAxis = DefaultsBuilder.get().getScaledOptions().getScales().getRAxis();
			}
		} else {
			// uses the defaults
			xAxis = DefaultsBuilder.get().getScaledOptions().getScales().getXAxis();
			yAxis = DefaultsBuilder.get().getScaledOptions().getScales().getYAxis();
			rAxis = DefaultsBuilder.get().getScaledOptions().getScales().getRAxis();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScales#getXAxis()
	 */
	@Override
	public IsDefaultScale getXAxis() {
		return xAxis;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScales#getYAxis()
	 */
	@Override
	public IsDefaultScale getYAxis() {
		return yAxis;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScales#getRAxis()
	 */
	@Override
	public IsDefaultScale getRAxis() {
		return rAxis;
	}

}
