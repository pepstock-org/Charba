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
 * Property to set the postion's edge of an element to a unit above/below its normal position.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 */
public enum Position implements Key
{

	/**
	 * The top property sets the top edge of an element to a unit above/below its normal position.
	 */
	top,
	/**
	 * the left property sets the left edge of an element to a unit to the left/right to its normal position.
	 */
	left,
	/**
	 * the bottom property sets the bottom edge of an element to a unit above/below its normal position.
	 */
	bottom,
	/**
	 * the right property sets the right edge of an element to a unit to the left/right to its normal position.
	 */
	right;
}