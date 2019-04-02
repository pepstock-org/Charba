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
package org.pepstock.charba.client.impl.plugins.enums;

import org.pepstock.charba.client.commons.Key;

/**
 * Enumerates the possible values to address coloring of BAR or BUBBLE datasets by a color scheme.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum SchemeScope implements Key
{
	/**
	 * Apply the scheme colors at dataset level (1 color for 1 dataset).
	 */
	dataset,
	/**
	 * Apply the scheme colors at data lavel (1 color for each data inside a dataset dataset).
	 */
	data
}
