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
 * {@link ZoomPlugin#ID} plugin default options interface for ZOOM element.<br>
 * It contains all default values for ZOOM.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface IsDefaultZoom extends IsDefaultConfigurationItem {

	/**
	 * Returns the minimal pan distance required before actually applying pan.
	 * 
	 * @return the minimal pan distance required before actually applying pan
	 */
	@Override
	default double getThreshold() {
		return Zoom.DEFAULT_THRESHOLD;
	}

	/**
	 * Returns <code>true</code> to enable drag-to-zoom behavior.
	 * 
	 * @return <code>true</code> to enable drag-to-zoom behavior
	 */
	default boolean isDrag() {
		return Zoom.DEFAULT_DRAG;
	}

	/**
	 * Returns the customized drag-to-zoom effect.
	 * 
	 * @return the customized drag-to-zoom effect
	 */
	IsDefaultDrag getDrag();

	/**
	 * Returns the speed of element via mouse wheel (percentage of element on a wheel event).
	 * 
	 * @return the speed of element via mouse wheel
	 */
	default double getSpeed() {
		return Zoom.DEFAULT_SPEED;
	}

	/**
	 * Returns the modifier key to activate zooming by wheeling.
	 * 
	 * @return the modifier key to activate zooming by wheeling
	 */
	default ModifierKey getWheelModifierKey() {
		return null;
	}

}