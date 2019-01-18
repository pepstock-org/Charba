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
 * Both line and radar charts support a fill option on the dataset object which can be used to create area between two datasets
 * or a dataset and a boundary.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 */
public enum Fill implements Key
{

	/**
	 * Fill the area from the bottom X axis
	 */
	start,
	/**
	 * Fill the area from the top X axis
	 */
	end,
	/**
	 * Fill the area from 0 axis to top or bottom, depending on value.<br>
	 * Default.
	 */
	origin,
	/**
	 * Does not fill any area
	 */
	nofill;

}