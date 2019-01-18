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
 * Controls the ticks generation on cartesian time axis.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 */
public enum TickSource implements Key
{
	/**
	 * Generates "optimal" ticks based on scale size and time options.
	 */
	auto,
	/**
	 * Generates ticks from data (including labels from data <code>{t|x|y}</code> objects).
	 */
	data,
	/**
	 * Generates ticks from user given <code>data.labels</code> values ONLY.
	 */
	labels
}
