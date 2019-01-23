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
import org.pepstock.charba.client.options.Scales;

/**
 * Defaults for scales/axes option element, based on chart type.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultChartScales implements IsDefaultScales {

	private final Scales scales;

	private final IsDefaultScale xAxis;

	private final IsDefaultScale yAxis;

	/**
	 * Creates the object by scales option element instance.
	 * 
	 * @param scales scales option element instance.
	 */
	public DefaultChartScales(Scales scales) {
		this.scales = scales;
		// checks if there is any x axes
		if (scales.getXAxes() != null && !scales.getXAxes().isEmpty()) {
			// uses the first one as default
			xAxis = scales.getXAxes().get(0);
		} else {
			// uses the default
			xAxis = DefaultsBuilder.get().getScaledOptions().getScale();
		}
		// checks if there is any y axes
		if (scales.getYAxes() != null && !scales.getYAxes().isEmpty()) {
			// uses the first one as default
			yAxis = scales.getYAxes().get(0);
		} else {
			// uses the default
			yAxis = DefaultsBuilder.get().getScaledOptions().getScale();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScales#isDisplay()
	 */
	@Override
	public boolean isDisplay() {
		return scales.isDisplay();
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

}
