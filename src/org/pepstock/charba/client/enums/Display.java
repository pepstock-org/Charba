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
 * The display option controls the visibility of axis.<br>
 * Controls the axis global visibility (visible when true, hidden when false). When display: 'auto', the axis is visible only if
 * at least one associated dataset is visible.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum AxisDisplay implements Key
{
	/**
	 * This is default and the axis is drawn.
	 */
	True,
	/**
	 * The axis is hidden.
	 */
	False,
	/**
	 * The axis is visible only if at least one associated dataset is visible.
	 */
	auto
}
