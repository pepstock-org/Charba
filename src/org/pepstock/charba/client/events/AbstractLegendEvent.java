/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.events;

import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Key;
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
	 * @param eventContext event context generated by CHART.js
	 * @param type type of event
	 * @param key options key where default function is stored
	 * @param item legend item related to the click
	 */
	AbstractLegendEvent(ChartEventContext eventContext, EventType type, Key key, LegendItem item) {
		super(eventContext, type, key);
		// checks if item is consistent
		// stores the item
		this.item = Checker.checkAndGetIfValid(item, "Legend item argument");
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