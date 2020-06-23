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

import org.pepstock.charba.client.enums.ElementAlign;
import org.pepstock.charba.client.enums.Position;

/**
 * Interface to define legends object defaults.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsDefaultLegend extends IsDefaultTextDirectioner {

	/**
	 * Returns the labels object defaults.
	 * 
	 * @return the labels object instance
	 */
	IsDefaultLegendLabels getLabels();

	/**
	 * Returns the title object defaults.
	 * 
	 * @return the title object instance
	 */
	IsDefaultLegendTitle getTitle();

	/**
	 * Returns <code>true</code> if the legend is shown.
	 * 
	 * @return <code>true</code> if the legend is shown.
	 */
	boolean isDisplay();

	/**
	 * Returns <code>true</code> if marks that this box should take the full width of the canvas (pushing down other boxes)
	 * 
	 * @return <code>true</code> if marks that this box should take the full width of the canvas (pushing down other boxes).
	 */
	boolean isFullWidth();

	/**
	 * Returns if the legend will show datasets in reverse order.
	 * 
	 * @return legend will show datasets in reverse order.
	 */
	boolean isReverse();

	/**
	 * Returns the position of the legend.
	 * 
	 * @return position of the legend.
	 */
	Position getPosition();

	/**
	 * Returns the alignment of the legend.
	 * 
	 * @return alignment of the legend.
	 */
	ElementAlign getAlign();

}