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
package org.pepstock.charba.client.enums;

import org.pepstock.charba.client.commons.Key;

/**
 * Enumerates the possible algorithm that {@link DefaultPluginId#DECIMATION} plugin can use for its work.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum DecimationAlgorithm implements Key
{
	/**
	 * <b>Min-max</b> decimation will preserve peaks in your data but could require up to 4 points for each pixel.<br>
	 * This type of decimation would work well for a very noisy signal where you need to see data peaks.
	 */
	MIN_MAX("min-max"),
	/**
	 * <b>LTTB</b> decimation reduces the number of data points significantly.<br>
	 * This is most useful for showing trends in data using only a few data points.
	 */
	LTTB("lttb");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private DecimationAlgorithm(String value) {
		this.value = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.Key#value()
	 */
	@Override
	public String value() {
		return value;
	}
}