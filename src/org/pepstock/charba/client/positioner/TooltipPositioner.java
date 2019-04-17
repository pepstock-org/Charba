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
package org.pepstock.charba.client.positioner;

import java.util.List;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.items.DatasetItem;
import org.pepstock.charba.client.options.Tooltips;

/**
 * Interface to be implemented to create a custom tooltip positionier in order address where the tooltip must be showed on
 * canvas.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface TooltipPositioner {

	/**
	 * Returns <code>true</code> if tooltip positioner passed as argument is not <code>null</code> and its name is not
	 * <code>null</code> as well.
	 * 
	 * @param tooltipPositioner tooltip positioner to be checked
	 * @return <code>true</code> if tooltip positioner passed as argument is not <code>null</code> and its name is not
	 *         <code>null</code>
	 */
	static boolean isValid(TooltipPositioner tooltipPositioner) {
		return tooltipPositioner != null && tooltipPositioner.getName() != null;
	}

	/**
	 * Returns the name of tooltip position which must be used in chart options.
	 * 
	 * @return the name of tooltip position.
	 * @see Tooltips#setPosition(org.pepstock.charba.client.enums.IsTooltipPosition)
	 */
	CustomTooltipPosition getName();

	/**
	 * Applies own logic to returns the point where the tooltip must be showed.
	 * 
	 * @param chart chart instance
	 * @param items list of dataset items
	 * @param eventPoint the point of event when the method has been invoked
	 * @return the point where the tooltip must be showed. If <code>null</code>, the default tooltip positioner will be used to
	 *         provide a consistent point where tooltip will be showed
	 */
	Point computePosition(IsChart chart, List<DatasetItem> items, Point eventPoint);

}
