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
package org.pepstock.charba.client.jsinterop.enums;

import org.pepstock.charba.client.jsinterop.commons.Key;

/**
 * The events option defines the browser events that the chart should listen to.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 */
public enum Event implements Key
{

	/**
	 * The user moves the mouse over an element
	 */
	mousemove,
	/**
	 * The user moves the mouse away from an element
	 */
	mouseout,
	/**
	 * The user clicks an element
	 */
	click,
	/**
	 * The user touches a point is placed on the touch surface.
	 */
	touchstart,
	/**
	 * The user touches a point is moved along the touch surface.
	 */
	touchmove,
	/**
	 * The user touches a point is removed from the touch surface.
	 */
	touchend;

}