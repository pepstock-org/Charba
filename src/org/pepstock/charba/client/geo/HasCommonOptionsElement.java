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
package org.pepstock.charba.client.geo;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.items.Undefined;

/**
 * Maps all options which are common among GEO options elements.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface HasCommonOptionsElement {

	/**
	 * Returns the element options handler.
	 * 
	 * @return the element options handler
	 */
	CommonOptionsElementHandler getCommonElementHandler();

	/**
	 * Sets the outline background color.
	 * 
	 * @param backgroundColor the outline background color.
	 */
	default void setOutlineBackgroundColor(IsColor backgroundColor) {
		setOutlineBackgroundColor(IsColor.checkAndGetValue(backgroundColor));
	}

	/**
	 * Sets the outline background color.
	 * 
	 * @param backgroundColor the outline background color.
	 */
	default void setOutlineBackgroundColor(String backgroundColor) {
		// checks if handler is consistent
		if (getCommonElementHandler() != null) {
			// stores value
			getCommonElementHandler().setOutlineBackgroundColor(backgroundColor);
		}
	}

	/**
	 * Returns the outline background color.
	 * 
	 * @return the outline background color.
	 */
	default String getOutlineBackgroundColorAsString() {
		// checks if handler is consistent
		if (getCommonElementHandler() != null) {
			// returns value
			return getCommonElementHandler().getOutlineBackgroundColorAsString();
		}
		// if here, handler is not consistent
		// then returns default
		return Undefined.STRING;
	}

	/**
	 * Returns the outline background color.
	 * 
	 * @return the outline background color.
	 */
	default IsColor getOutlineBackgroundColor() {
		// gets color
		String color = getOutlineBackgroundColorAsString();
		// if color is consistent, parses to a color object
		return color != null ? ColorBuilder.parse(color) : null;
	}

	/**
	 * Sets the outline border width.
	 * 
	 * @param borderWidth the outline border width.
	 */
	default void setOutlineBorderWidth(int borderWidth) {
		// checks if handler is consistent
		if (getCommonElementHandler() != null) {
			// stores value
			getCommonElementHandler().setOutlineBorderWidth(borderWidth);
		}
	}

	/**
	 * Returns the outline border width.
	 * 
	 * @return the outline border width.
	 */
	default int getOutlineBorderWidth() {
		// checks if handler is consistent
		if (getCommonElementHandler() != null) {
			// returns value
			return getCommonElementHandler().getOutlineBorderWidth();
		}
		// if here, handler is not consistent
		// then returns default
		return CommonOptionsElementHandler.DEFAULT_OUTLINE_BORDER_WIDTH;
	}

	/**
	 * Sets the outline border color.
	 * 
	 * @param borderColor the outline border color.
	 */
	default void setOutlineBorderColor(IsColor borderColor) {
		setOutlineBorderColor(IsColor.checkAndGetValue(borderColor));
	}

	/**
	 * Sets the outline border color.
	 * 
	 * @param borderColor the outline border color.
	 */
	default void setOutlineBorderColor(String borderColor) {
		// checks if handler is consistent
		if (getCommonElementHandler() != null) {
			// stores value
			getCommonElementHandler().setOutlineBorderColor(borderColor);
		}
	}

	/**
	 * Returns the outline border color.
	 * 
	 * @return the outline border color.
	 */
	default String getOutlineBorderColorAsString() {
		// checks if handler is consistent
		if (getCommonElementHandler() != null) {
			// returns value
			return getCommonElementHandler().getOutlineBorderColorAsString();
		}
		// if here, handler is not consistent
		// then returns default
		return Defaults.get().getGlobal().getBorderColorAsString();
	}

	/**
	 * Returns the outline border color.
	 * 
	 * @return the outline border color.
	 */
	default IsColor getOutlineBorderColor() {
		return ColorBuilder.parse(getOutlineBorderColorAsString());
	}

	// ------------------------------------------------------
	// GRATICULE
	// ------------------------------------------------------

	/**
	 * Sets the graticule border width.
	 * 
	 * @param borderWidth the graticule border width.
	 */
	default void setGraticuleBorderWidth(int borderWidth) {
		// checks if handler is consistent
		if (getCommonElementHandler() != null) {
			// stores value
			getCommonElementHandler().setGraticuleBorderWidth(borderWidth);
		}
	}

	/**
	 * Returns the graticule border width.
	 * 
	 * @return the graticule border width.
	 */
	default int getGraticuleBorderWidth() {
		// checks if handler is consistent
		if (getCommonElementHandler() != null) {
			// returns value
			return getCommonElementHandler().getGraticuleBorderWidth();
		}
		// if here, handler is not consistent
		// then returns default
		return CommonOptionsElementHandler.DEFAULT_GRATICULE_BORDER_WIDTH;
	}

	/**
	 * Sets the graticule border color.
	 * 
	 * @param borderColor the graticule border color.
	 */
	default void setGraticuleBorderColor(IsColor borderColor) {
		setGraticuleBorderColor(IsColor.checkAndGetValue(borderColor));
	}

	/**
	 * Sets the graticule border color.
	 * 
	 * @param borderColor the graticule border color.
	 */
	default void setGraticuleBorderColor(String borderColor) {
		// checks if handler is consistent
		if (getCommonElementHandler() != null) {
			// stores value
			getCommonElementHandler().setGraticuleBorderColor(borderColor);
		}
	}

	/**
	 * Returns the graticule border color.
	 * 
	 * @return the graticule border color.
	 */
	default String getGraticuleBorderColorAsString() {
		// checks if handler is consistent
		if (getCommonElementHandler() != null) {
			// returns value
			return getCommonElementHandler().getGraticuleBorderColorAsString();
		}
		// if here, handler is not consistent
		// then returns default
		return CommonOptionsElementHandler.DEFAULT_GRATICULE_BORDER_COLOR;
	}

	/**
	 * Returns the graticule border color.
	 * 
	 * @return the graticule border color.
	 */
	default IsColor getGraticuleBorderColor() {
		return ColorBuilder.parse(getGraticuleBorderColorAsString());
	}
}
