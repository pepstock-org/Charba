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
package org.pepstock.charba.client.zoom;

import org.pepstock.charba.client.enums.ModifierKey;

/**
 * {@link ZoomPlugin#ID} plugin default options interface for PAN element.<br>
 * It contains all default values for PAN.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface IsDefaultPan extends IsDefaultConfigurationItem {

	/**
	 * Returns the minimal pan distance required before actually applying pan.
	 * 
	 * @return the minimal pan distance required before actually applying pan
	 */
	default double getThreshold() {
		return Pan.DEFAULT_THRESHOLD;
	}

	/**
	 * Returns the threshold factor before applying pan, on category scale.
	 * 
	 * @return the threshold factor before applying pan, on category scale
	 */
	default double getSpeed() {
		return Pan.DEFAULT_SPEED;
	}

	/**
	 * Returns the modifier key to activate panning.
	 * 
	 * @return the modifier key to activate panning
	 */
	default ModifierKey getModifierKey() {
		return null;
	}

}