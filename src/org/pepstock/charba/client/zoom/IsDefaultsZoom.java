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

/**
 * {@link ZoomPlugin#ID} plugin default options for ZOOM element.<br>
 * It contains all default values for ZOOM.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface IsDefaultsZoom extends IsDefaultsConfigurationItem {

	/**
	 * Returns the minimal zoom level before actually applying zoom, on category scale.
	 * 
	 * @return the minimal zoom level before actually applying zoom, on category scale
	 */
	default double getSensitivity() {
		return Zoom.DEFAULT_SENSITIVITY;
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
	IsDefaultsDrag getDrag();

	/**
	 * Returns the speed of element via mouse wheel (percentage of element on a wheel event).
	 * 
	 * @return the speed of element via mouse wheel
	 */
	default double getSpeed() {
		return Zoom.DEFAULT_SPEED;
	}

}