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

import org.pepstock.charba.client.enums.BorderAlign;

/**
 * Interface to define arc object defaults.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsDefaultArc extends IsDefaultOptionsElement {

	/**
	 * Returns the property to set the border alignment on chart datasets.
	 * 
	 * @return the property to set the border alignment on chart datasets.
	 */
	BorderAlign getBorderAlign();

	/**
	 * Returns the relative thickness of the dataset.<br>
	 * Providing a value for weight will cause the pie or doughnut dataset to be drawn with a thickness relative to the sum of all the dataset weight values.
	 * 
	 * @return the relative thickness of the dataset
	 */
	double getWeight();

	/**
	 * Returns the arc angle to cover.
	 * 
	 * @return the arc angle to cover
	 */
	double getAngle();

	/**
	 * Returns the arc offset (in pixels).
	 * 
	 * @return the arc offset
	 */
	int getOffset();
	
	/**
	 * Returns the arc border radius (in pixels).
	 * 
	 * @return the arc border radius (in pixels).
	 */
	int getBorderRadius();
	
	/**
	 * Returns the arc offset (in pixels) when hovered.
	 * 
	 * @return the arc offset when hovered
	 */
	int getHoverOffset();
	
}
