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
package org.pepstock.charba.client.configuration;

import org.pepstock.charba.client.enums.DecimationAlgorithm;
import org.pepstock.charba.client.enums.DefaultPluginId;

/**
 * The configuration for {@link DefaultPluginId#DECIMATION} plugin.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class Decimation extends ConfigurationOptionsContainer {

	/**
	 * Builds the object storing the root options element.
	 * 
	 * @param options root options element.
	 */
	Decimation(ConfigurationOptions options) {
		super(options);
	}
	
	/**
	 * Sets <code>true</code> if decimation are enabled.
	 * 
	 * @param enabled <code>true</code> if decimation are enabled.
	 */
	public void setEnabled(boolean enabled) {
		getConfiguration().getDecimation().setEnabled(enabled);
	}

	/**
	 * Returns <code>true</code> if decimation are enabled.
	 * 
	 * @return <code>true</code> if decimation are enabled.
	 */
	public boolean isEnabled() {
		return getConfiguration().getDecimation().isEnabled();
	}

	/**
	 * Sets the algorithm used by the plugin.
	 * 
	 * @param algorithm the algorithm used by the plugin
	 */
	public void setAlgorithm(DecimationAlgorithm algorithm) {
		getConfiguration().getDecimation().setAlgorithm(algorithm);
	}

	/**
	 * Returns the algorithm used by the plugin.
	 * 
	 * @return the algorithm used by the plugin
	 */
	public DecimationAlgorithm getAlgorithm() {
		return getConfiguration().getDecimation().getAlgorithm();
	}

	/**
	 * If the {@link DecimationAlgorithm#LTTB} algorithm is used, this is the number of samples in the output data set.<br>
	 * Defaults to the canvas width to pick 1 sample per pixel.
	 * 
	 * @param samples the number of samples in the output data set
	 */
	public void setSamples(double samples) {
		getConfiguration().getDecimation().setSamples(samples);
	}

	/**
	 * If the {@link DecimationAlgorithm#LTTB} algorithm is used, this is the number of samples in the output data set.<br>
	 * Defaults to the canvas width to pick 1 sample per pixel.
	 * 
	 * @return the number of samples in the output data set
	 */
	public double getSamples() {
		return getConfiguration().getDecimation().getSamples();
	}

}