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
package org.pepstock.charba.client.defaults;

import org.pepstock.charba.client.enums.AxisKind;
import org.pepstock.charba.client.options.IsScaleId;

/**
 * Interface to define scales/axes object defaults.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsDefaultScales {

	/**
	 * Returns the default configuration for x axis.
	 * 
	 * @param scaleId the scale id to use to get the default
	 * @param kind axis kind to use if the scale id is not recognizable
	 * @return the default configuration for x axis.
	 */
	IsDefaultScale getAxis(IsScaleId scaleId, AxisKind kind);

}