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
package org.pepstock.charba.client.annotation;

import java.util.Collections;
import java.util.List;

import org.pepstock.charba.client.annotation.enums.DrawTime;
import org.pepstock.charba.client.annotation.enums.Event;

/**
 * This is the {@link AnnotationPlugin#ID} plugin DEFAULTS options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface IsDefaultOptions {

	/**
	 * Returns the draw time which defines when the annotations are drawn.
	 * 
	 * @return the draw time which defines when the annotations are drawn
	 */
	default DrawTime getDrawTime() {
		return AnnotationOptions.DEFAULT_DRAW_TIME;
	}

	/**
	 * Returns the double-click speed in milliseconds used to distinguish single-clicks from double-clicks whenever you need to
	 * capture both.<br>
	 * When listening for both {@link Event#CLICK} and {@link Event#DOUBLE_CLICK}, click events will be delayed by this amount.
	 * 
	 * @return the double-click speed in milliseconds
	 */
	default int getDoubleClickSpeed() {
		return AnnotationOptions.DEFAULT_DOUBLE_CLICK_SPEED;
	}

	/**
	 * Returns the browser events to enable on each annotation.
	 * 
	 * @return the browser events to enable on each annotation
	 */
	default List<Event> getEvents() {
		return Collections.emptyList();
	}

}
