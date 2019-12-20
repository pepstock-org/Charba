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

import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.zoom.Drag.Property;

/**
 * {@link ZoomPlugin#ID} plugin default options for DRAG element, in order to style the drag area.<br>
 * It contains all default values for DRAG.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class DefaultsDrag extends NativeObjectContainer {

	/**
	 * Creates the object with an empty native object instance.
	 */
	DefaultsDrag() {
		super();
	}

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped
	 */
	DefaultsDrag(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the fill color during dragging.
	 * 
	 * @return the fill color during dragging
	 */
	String getBackgroundColorAsString() {
		// returns color as string
		return getValue(Property.BACKGROUND_COLOR, Drag.DEFAULT_BACKGROUND_COLOR);
	}

	/**
	 * Returns the color of the border during dragging.
	 * 
	 * @return the color of the border during dragging
	 */
	String getBorderColorAsString() {
		// returns color as string
		return getValue(Property.BORDER_COLOR, Drag.DEFAULT_BORDER_COLOR);
	}

	/**
	 * Returns the width of the border in pixels.
	 * 
	 * @return the width of the border in pixels.
	 */
	int getBorderWidth() {
		return getValue(Property.BORDER_WIDTH, Drag.DEFAULT_BORDER_WIDTH);
	}

	/**
	 * Returns the number of milliseconds an animation takes.
	 * 
	 * @return the number of milliseconds an animation takes.
	 */
	int getAnimationDuration() {
		return getValue(Property.ANIMATION_DURATION, Drag.DEFAULT_ANIMATION_DURATION);
	}
}