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
 * The weight sets how thick or thin characters in text should be displayed.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum Weight implements Key
{
	/**
	 * Defines normal characters. This is default.
	 */
	normal,
	/**
	 * Defines thick characters.
	 */
	bold,
	/**
	 * Defines thicker characters.
	 */
	bolder,
	/**
	 * Defines lighter characters.
	 */
	lighter,
	/**
	 * Sets this property to its default value.
	 */
	initial,
	/**
	 * Inherits this property from its parent element.
	 */
	inherit
}
