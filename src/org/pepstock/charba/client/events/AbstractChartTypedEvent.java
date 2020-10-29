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

import java.util.List;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.items.DatasetReference;

/**
 * Abstract event which is fired when the user is acting on the chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
abstract class AbstractChartTypedEvent extends AbstractChartEvent implements IsChartEvent {

	// a list of items with dataset references related to the click
	private final List<DatasetReference> items;

	/**
	 * Creates the event with a list of dataset references related to the click.
	 * 
	 * @param eventContext event context generated by CHART.js
	 * @param type type of event
	 * @param key options key where default function is stored
	 * @param items a list of items with dataset references related to the click
	 */
	AbstractChartTypedEvent(ChartEventContext eventContext, EventType type, Key key, List<DatasetReference> items) {
		super(eventContext, type, key);
		// checks if argument is consistent
		if (items == null) {
			throw new IllegalArgumentException("Dataset references items list argument is null");
		}
		// stores argument
		this.items = items;
	}

	/**
	 * Returns a list of items with dataset references related to the click.
	 * 
	 * @return a list of items with dataset references related to the click
	 */
	@Override
	public final List<DatasetReference> getItems() {
		return items;
	}

}