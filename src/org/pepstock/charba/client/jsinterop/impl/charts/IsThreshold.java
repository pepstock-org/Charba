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
package org.pepstock.charba.client.jsinterop.impl.charts;

import org.pepstock.charba.client.colors.IsColor;

/**
 * Interface tomap a threshold. Needed for standard ones.
 * 
 * @author Andrea "Stock" Stocchero
 * @see GaugeThreshold
 */
public interface IsThreshold {
	
	/**
	 * Returns the name of threshold.
	 * @return the name of threshold.
	 */
	String getName();
	
	/**
	 * Returns the value of threshold.
	 * @return the value of threshold.
	 */
	double getValue();
	
	/**
	 * Returns the color of threshold.
	 * @return the color of threshold.
	 */
	IsColor getColor();

}
