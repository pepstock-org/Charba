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
package org.pepstock.charba.client.events;

import org.pepstock.charba.client.Chart;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.dom.BaseNativeEvent;
import org.pepstock.charba.client.items.LegendItem;

/**
 * Abstract event which is fired when the user is acting on the legend of the chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
abstract class AbstractLegendEvent extends AbstractChartEvent implements IsLegendEvent {

	// legend item selected by clicking
	private final LegendItem item;

	/**
	 * Creates the event with legend item related to the click
	 * 
	 * @param nativeEvent native event of this custom event
	 * @param type type of event
	 * @param functionContext function context provided by CHART.JS
	 * @param key options key where default function is stored
	 * @param item legend item related to the click
	 */
	AbstractLegendEvent(BaseNativeEvent nativeEvent, EventType type, Chart functionContext, Key key, LegendItem item) {
		super(nativeEvent, type, functionContext, key);
		// checks if item is consistent
		if (item == null) {
			throw new IllegalArgumentException("Legend item argument is null");
		}
		// stores the item
		this.item = item;
	}

	/**
	 * Returns the legend item related to the click
	 * 
	 * @return the legend item related to the click
	 */
	@Override
	public final LegendItem getItem() {
		return item;
	}

}