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
 * Controls the scale boundary strategy (bypassed by minimum/maximum time options).
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum ScaleBounds implements Key
{
	/**
	 * Make sure data are fully visible, labels outside are removed.
	 */
	data,
	/**
	 * Make sure ticks are fully visible, data outside are truncated.
	 */
	ticks
}
