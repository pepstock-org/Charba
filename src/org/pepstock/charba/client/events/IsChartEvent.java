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

import org.pepstock.charba.client.items.DatasetReferenceItem;

/**
 * Interface which maps a chart event, an event generated from CHART.JS.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface IsChartEvent extends IsCommonChartEvent {

	/**
	 * Returns a list of items with dataset references related to the event.
	 * 
	 * @return a list of items with dataset references related to the event
	 */
	List<DatasetReferenceItem> getItems();

}
