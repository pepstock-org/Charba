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

import org.pepstock.charba.client.ScaleType;

/**
 * Interface to define options defaults. THIS IS THE ROOT OF ALL INTERFACE DEFAULTS.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsDefaultScaledOptions extends IsDefaultOptions {
	
	/**
	 * Returns the type of scale.
	 * 
	 * @return the type of scale
	 */
	ScaleType getScaleType();

	/**
	 * Returns the scale/axis defaults.
	 * 
	 * @return the scale/axis defaults.
	 */
	IsDefaultScales getScales();

	/**
	 * Returns the scale/axis defaults.
	 * 
	 * @return the scale/axis defaults.
	 */
	IsDefaultScale getScale();

}
