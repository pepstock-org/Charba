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
package org.pepstock.charba.client.ext.datalabels;

import org.pepstock.charba.client.commons.Key;

/**
 * This DATALABELS plugin currently supports the below label events.<br>
 * Charba events that need to be enabled in order to get the associated label event working. Note that by default Charba enables
 * "mousemove", "mouseout", "click", "touchstart", "touchmove", "touchend", meaning that label events work out-of-the-box.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
enum Event implements Key
{
	/**
	 * The mouse is moved over a label. Charba {@link org.pepstock.charba.client.enums.Event#mousemove} must be enabled.
	 */
	enter,
	/**
	 * The mouse is moved out of a label. Charba {@link org.pepstock.charba.client.enums.Event#mousemove} must be enabled.
	 */
	leave,
	/**
	 * The mouse's primary button is pressed and released on a label. Charba
	 * {@link org.pepstock.charba.client.enums.Event#click} must be enabled.
	 */
	click;
}
