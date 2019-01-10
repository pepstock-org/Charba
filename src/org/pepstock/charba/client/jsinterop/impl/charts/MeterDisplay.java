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
package org.pepstock.charba.client.jsinterop.impl.charts;

import org.pepstock.charba.client.commons.Key;

/**
 * Determines which information must be displayed into meter or gauge chart.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 */
public enum MeterDisplay implements Key
{
	/**
	 * Shows only the value
	 */
	value,
	/**
	 * Shows the percentage
	 */
	percentage,
	/**
	 * Shows value and label
	 */
	valueAndLabel,
	/**
	 * Shows percentage and label
	 */
	percentageAndLabel

}
