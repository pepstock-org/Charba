/**
<    Copyright 2017 Andrea "Stock" Stocchero

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

import org.pepstock.charba.client.ChartOptions;
import org.pepstock.charba.client.ScaleType;
import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.defaults.IsDefaultScaledOptions;
import org.pepstock.charba.client.defaults.IsDefaultScales;
import org.pepstock.charba.client.defaults.globals.DefaultsBuilder;
import org.pepstock.charba.client.options.ExtendedOptions;
import org.pepstock.charba.client.options.ScaledOptions;

/**
 * Defaults for options element, based on chart type. THIS IS THE ROOT OF ALL ELEMENTS DEFAULTS, for chart defaults.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultChartOptions extends AbstractDefaultChartOptions implements IsDefaultScaledOptions {

	private final IsDefaultScales scales;

	/**
	 * Creates the default by an extended options instance, which represents the whole options of a chart.
	 * 
	 * @param chartOptions whole chart options instance.
	 */
	public DefaultChartOptions(ExtendedOptions chartOptions) {
		this(chartOptions, chartOptions.getChart().getType());
	}

	/**
	 * Creates the default by a chart options instance, which represents the global options of a chart.
	 * 
	 * @param chartOptions global chart options instance.
	 */
	public DefaultChartOptions(ChartOptions chartOptions) {
		this(chartOptions, chartOptions.getType());
	}

	/**
	 * Creates the default by a scaled options element instance.
	 * 
	 * @param chartOptions chart options instance based on scaled one
	 * @param type chart type of this default
	 */
	private DefaultChartOptions(ScaledOptions chartOptions, Type type) {
		super(chartOptions);
		// checks type
		Type.checkIfValid(type);
		// checks if the chart has got scales
		if (ScaleType.NONE.equals(type.scaleType())) {
			// gets default scales
			scales = DefaultsBuilder.get().getScaledOptions().getScales();
		} else {
			// gets scale
			scales = new DefaultChartScales(chartOptions.getScales());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScaledOptions#getScales()
	 */
	@Override
	public IsDefaultScales getScales() {
		return scales;
	}

}
