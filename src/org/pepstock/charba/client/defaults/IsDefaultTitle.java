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

import org.pepstock.charba.client.enums.Position;

/**
 * Interface to define title object defaults.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 */
public interface IsDefaultTitle extends IsDefaultFontItem {

	/**
	 * Returns if the title is shown.
	 * 
	 * @return if the title is shown.
	 */
	boolean isDisplay();

	/**
	 * Returns the position of title.
	 * 
	 * @return the position of title.
	 */
	Position getPosition();

	/**
	 * Returns the padding to apply around labels. Only top and bottom are implemented.
	 * 
	 * @return Padding to apply around labels. Only top and bottom are implemented.
	 */
	int getPadding();

	/**
	 * Returns if marks that this box should take the full width of the canvas (pushing down other boxes)
	 * 
	 * @return Marks that this box should take the full width of the canvas (pushing down other boxes).
	 */
	boolean isFullWidth();

	/**
	 * Returns the height of an individual line of text.
	 * 
	 * @return the height of an individual line of text.
	 */
	double getLineHeight();

}