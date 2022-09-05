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
package org.pepstock.charba.client.defaults;

import org.pepstock.charba.client.enums.BorderSkipped;

/**
 * Interface to define bar object defaults.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsDefaultBar extends IsDefaultOptionsElement, IsDefaultPointStyleHandler {

	/**
	 * Returns the edge to skip drawing the border for.
	 * 
	 * @return the edge to skip drawing the border for.
	 */
	BorderSkipped getBorderSkipped();

	/**
	 * Returns the bar border radius (in pixels).
	 * 
	 * @return the bar border radius (in pixels).
	 */
	int getBorderRadius();

	/**
	 * Returns the bar border radius (in pixels) when hovered.
	 * 
	 * @return the bar border radius (in pixels) when hovered.
	 */
	int getHoverBorderRadius();

	/**
	 * If <code>true</code>, it only shows the borderRadius of a bar when the bar is at the end of the stack.
	 * 
	 * @return if <code>true</code>, it only shows the borderRadius of a bar when the bar is at the end of the stack
	 */
	boolean isEnableBorderRadius();

	/**
	 * Returns <code>true</code> if the amount of pixels to inflate the bar rectangles, when drawing, is automatically calculated.
	 * 
	 * @return <code>true</code> if the amount of pixels to inflate the bar rectangles, when drawing, is automatically calculated
	 */
	boolean isAutoInflateAmount();

	/**
	 * Returns the amount of pixels to inflate the bar rectangles, when drawing.
	 * 
	 * @return the amount of pixels to inflate the bar rectangles, when drawing
	 */
	int getInflateAmount();

	/**
	 * Returns the base value for the bar in data units along the value axis.<br>
	 * If not set, defaults to the value axis base value.
	 * 
	 * @return base value for the bar in data units along the value axis.<br>
	 *         If not set, defaults to the value axis base value
	 */
	double getBase();

}