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

/**
 * Base scale for color and size axes, needed for GOE charts implementation.<br>
 * It contains all options needed to configure the scale with a legend.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface IsLegendAxis {

	/**
	 * Returns the legend axis mapper.
	 * 
	 * @return the legend axis mapper
	 */
	LegendAxisMapper getMapper();

	/**
	 * Returns the legend configuration.
	 * 
	 * @return the legend configuration
	 */
	default Legend getLegend() {
		// checks if mapper is consistent
		if (getMapper() != null) {
			// returns value
			return getMapper().getLegend();
		}
		// if here, mapper is not consistent
		// then returns null
		return null;
	}

}
