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
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.defaults.IsDefaultBoxHandler;

/**
 * Interface to map the text direction options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface HasBox extends IsDefaultBoxHandler {

	/**
	 * Returns a box handler instance to use into default methods of this interface.
	 * 
	 * @return a box handler instance
	 */
	BoxHandler getBoxHandler();

	/**
	 * Sets the width of colored box.
	 * 
	 * @param boxWidth width of colored box.
	 */
	default void setBoxWidth(int boxWidth) {
		// checks if box handler is consistent
		if (getBoxHandler() != null) {
			getBoxHandler().setBoxWidth(boxWidth);
		}
	}

	/**
	 * Returns the width of colored box.
	 * 
	 * @return width of colored box.
	 */
	@Override
	default int getBoxWidth() {
		// checks if box handler is consistent
		if (getBoxHandler() != null) {
			return getBoxHandler().getBoxWidth();
		}
		// if here, box handler is not consistent
		// uses the default of tooltips
		return Defaults.get().getGlobal().getTooltips().getBoxWidth();
	}

	/**
	 * Sets the height of colored box.
	 * 
	 * @param boxHeight width of colored box.
	 */
	default void setBoxHeight(int boxHeight) {
		// checks if box handler is consistent
		if (getBoxHandler() != null) {
			getBoxHandler().setBoxHeight(boxHeight);
		}
	}

	/**
	 * Returns the height of colored box.
	 * 
	 * @return height of colored box.
	 */
	@Override
	default int getBoxHeight() {
		// checks if box handler is consistent
		if (getBoxHandler() != null) {
			return getBoxHandler().getBoxHeight();
		}
		// if here, box handler is not consistent
		// uses the default of tooltips
		return Defaults.get().getGlobal().getTooltips().getBoxHeight();
	}
}