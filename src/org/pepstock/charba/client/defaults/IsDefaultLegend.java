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

import java.util.List;

import org.pepstock.charba.client.enums.Event;

/**
 * Interface to define legends object defaults.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsDefaultLegend extends IsDefaultTextDirectionHandler, IsDefaultPluginElement {

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
	 * Returns the browser events that the legend should listen to.
	 * 
	 * @return the browser events that the legend should listen to.
	 */
	List<Event> getEvents();

	/**
	 * Returns <code>true</code> if marks that this box should take the full width/height of the canvas (moving other boxes).
	 * 
	 * @return <code>true</code> if marks that this box should take the full width/height of the canvas (moving other boxes)
	 */
	boolean isFullSize();

	/**
	 * Returns if the legend will show datasets in reverse order.
	 * 
	 * @return legend will show datasets in reverse order.
	 */
	boolean isReverse();

	/**
	 * Returns the maximum width of the legend, in pixels.
	 * 
	 * @return the maximum width of the legend, in pixels
	 */
	int getMaxWidth();

	/**
	 * Returns the maximum height of the legend, in pixels.
	 * 
	 * @return the maximum height of the legend, in pixels
	 */
	int getMaxHeight();
}