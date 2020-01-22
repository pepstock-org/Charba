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

import org.pepstock.charba.client.GlobalOptions;
import org.pepstock.charba.client.defaults.IsDefaultScale;
import org.pepstock.charba.client.defaults.IsDefaultScaledOptions;
import org.pepstock.charba.client.defaults.IsDefaultScales;
import org.pepstock.charba.client.defaults.globals.DefaultsBuilder;

/**
 * Defaults for options element, using the defualt global options. THIS IS THE ROOT OF ALL ELEMENTS DEFAULTS.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultGlobalOptions extends AbstractDefaultChartOptions implements IsDefaultScaledOptions {

	private final IsDefaultScaledOptions scalesOptions;

	/**
	 * Creates the object by global options and defaults scaled options
	 * 
	 * @param globalOptions global options instance used as default.
	 */
	public DefaultGlobalOptions(GlobalOptions globalOptions) {
		super(globalOptions);
		// uses the default scaled options
		this.scalesOptions = DefaultsBuilder.get().getScaledOptions();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#getScale()
	 */
	@Override
	public IsDefaultScale getScale() {
		return scalesOptions.getScale();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScaledOptions#getScales()
	 */
	@Override
	public IsDefaultScales getScales() {
		return scalesOptions.getScales();
	}

}
