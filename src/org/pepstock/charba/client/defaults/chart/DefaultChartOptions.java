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

import org.pepstock.charba.client.ChartOptions;
import org.pepstock.charba.client.ScaleType;
import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.defaults.IsDefaultScale;
import org.pepstock.charba.client.defaults.IsDefaultScaledOptions;
import org.pepstock.charba.client.defaults.IsDefaultScales;
import org.pepstock.charba.client.defaults.globals.DefaultsBuilder;
import org.pepstock.charba.client.options.ExtendedOptions;
import org.pepstock.charba.client.options.Scale;
import org.pepstock.charba.client.options.Scales;

/**
 * Defaults for options element, based on chart type. THIS IS THE ROOT OF ALL ELEMENTS DEFAULTS.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultChartOptions extends AbstractDefaultChartOptions implements IsDefaultScaledOptions {

	private final IsDefaultScale scale;

	private final IsDefaultScales scales;

	/**
	 * FIXME check chartOptions
	 * Creates the object by options element instance.
	 * 
	 * @param chartOptions chart options instance.
	 */
	public DefaultChartOptions(ExtendedOptions chartOptions) {
		this(chartOptions, chartOptions.getChart().getType(), chartOptions.getScale(), chartOptions.getScales());
	}

	/**
	 * Creates the object by options element instance.
	 * 
	 * @param chartOptions chart options instance.
	 */
	public DefaultChartOptions(ChartOptions chartOptions) {
		this(chartOptions, chartOptions.getType(), chartOptions.getScale(), chartOptions.getScales());
	}

	/**
	 * FIXME
	 * Creates the object by options element instance.
	 * 
	 * @param chartOptions chart options instance.
	 */
	private DefaultChartOptions(IsDefaultScaledOptions chartOptions, Type type, Scale defaultScale, Scales defaultScales) {
		super(chartOptions);
		// checks type
		Type.checkIfValid(type);
		// checks if the chart options is related to axes
		// checks if single scale
		if (ScaleType.SINGLE.equals(type.scaleType())) {
			// gets scale
			this.scale = new DefaultChartScale(defaultScale);
		} else {
			// uses the default scale
			scale = DefaultsBuilder.get().getScaledOptions().getScale();
		}
		// checks if multi scale
		if (ScaleType.MULTI.equals(type.scaleType())) {
			// gets scale
			scales = new DefaultChartScales(defaultScales);
		} else {
			// uses the default scales
			scales = DefaultsBuilder.get().getScaledOptions().getScales();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#getScale()
	 */
	@Override
	public IsDefaultScale getScale() {
		return scale;
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
