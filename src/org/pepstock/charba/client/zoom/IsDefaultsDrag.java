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
 * FIXME
 * {@link ZoomPlugin#ID} plugin default options for DRAG element, in order to style the drag area.<br>
 * It contains all default values for DRAG.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface IsDefaultsDrag {

	/**
	 * Returns the fill color during dragging.
	 * 
	 * @return the fill color during dragging
	 */
	default String getBackgroundColorAsString() {
		return Drag.DEFAULT_BACKGROUND_COLOR;
	}

	/**
	 * Returns the color of the border during dragging.
	 * 
	 * @return the color of the border during dragging
	 */
	default String getBorderColorAsString() {
		return Drag.DEFAULT_BORDER_COLOR;
	}

	/**
	 * Returns the width of the border in pixels.
	 * 
	 * @return the width of the border in pixels.
	 */
	default int getBorderWidth() {
		return Drag.DEFAULT_BORDER_WIDTH;
	}

	/**
	 * Returns the number of milliseconds an animation takes.
	 * 
	 * @return the number of milliseconds an animation takes.
	 */
	default int getAnimationDuration() {
		return Drag.DEFAULT_ANIMATION_DURATION;
	}
}