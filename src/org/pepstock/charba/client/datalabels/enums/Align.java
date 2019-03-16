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
package org.pepstock.charba.client.datalabels.enums;

import org.pepstock.charba.client.commons.Key;

/**
 * The align option defines the position of the label relative to the anchor point position and orientation.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum Align implements Key
{

	/**
	 * is the default: the label is centered on the anchor point.
	 */
	center,
	/**
	 * The label is positioned before the anchor point, following the same direction
	 */
	start,
	/**
	 * The label is positioned after the anchor point, following the same direction
	 */
	end,
	/**
	 * The label is positioned to the right of the anchor point (0 degrees)
	 */
	right,
	/**
	 * The label is positioned to the bottom of the anchor point (90 degrees)
	 */
	bottom,
	/**
	 * The label is positioned to the left of the anchor point (180 degrees)
	 */
	left,
	/**
	 * The label is positioned to the top of the anchor point (270 degrees)
	 */
	top;

}
