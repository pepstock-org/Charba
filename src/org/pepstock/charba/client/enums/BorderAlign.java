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
 * Property to set the border alignment on chart datasets.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum BorderAlign implements Key
{

	/**
	 * When 'center' is set, the borders of arcs next to each other will overlap. This is the default.
	 */
	center,
	/**
	 * When 'inner' is set, it is guaranteed that all the borders are not overlap.
	 */
	inner;
}