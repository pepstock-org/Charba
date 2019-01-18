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
 * Interface to define legends object defaults.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 */
public interface IsDefaultLegend {

	/**
	 * Returns labels object defaults.
	 * 
	 * @return the labels
	 */
	IsDefaultLegendLabels getLabels();

	/**
	 * Returns if the legend is shown.
	 * 
	 * @return if the legend is shown.
	 */
	boolean isDisplay();

	/**
	 * Returns if marks that this box should take the full width of the canvas (pushing down other boxes)
	 * 
	 * @return Marks that this box should take the full width of the canvas (pushing down other boxes).
	 */
	boolean isFullWidth();

	/**
	 * Returns if the legend will show datasets in reverse order.
	 * 
	 * @return Legend will show datasets in reverse order.
	 */
	boolean isReverse();

	/**
	 * Returns the position of the legend.
	 * 
	 * @return Position of the legend.
	 */
	Position getPosition();
}