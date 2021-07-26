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
package org.pepstock.charba.client.defaults.globals;

import org.pepstock.charba.client.defaults.IsDefaultDecimation;
import org.pepstock.charba.client.enums.DecimationAlgorithm;
import org.pepstock.charba.client.items.Undefined;

/**
 * CHART.JS default values for DECIMATION plugin element.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultDecimation implements IsDefaultDecimation {

	private static final boolean DEFAULT_ENABLED = false;

	private static final double DEFAULT_SAMPLES = Undefined.DOUBLE;

	private static final double DEFAULT_THRESHOLD = Undefined.DOUBLE;

	/**
	 * To avoid any instantiation
	 */
	DefaultDecimation() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultDecimation#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		return DEFAULT_ENABLED;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultDecimation#getAlgorithm()
	 */
	@Override
	public DecimationAlgorithm getAlgorithm() {
		return DecimationAlgorithm.MIN_MAX;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultDecimation#getSamples()
	 */
	@Override
	public double getSamples() {
		return DEFAULT_SAMPLES;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultDecimation#getThreshold()
	 */
	@Override
	public double getThreshold() {
		return DEFAULT_THRESHOLD;
	}

}
