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

import org.pepstock.charba.client.Chart;
import org.pepstock.charba.client.dom.BaseNativeEvent;
import org.pepstock.charba.client.enums.ChartEventProperty;
import org.pepstock.charba.client.items.DatasetItem;

/**
 * Event which is fired when the user clicks on the chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class ChartClickEvent extends AbstractChartEvent implements IsChartEvent {

	/**
	 * Event type
	 */
	public static final EventType TYPE = EventType.create(ChartClickEvent.class);

	// a list of items with dataset metadata related to the click
	private final List<DatasetItem> items;

	/**
	 * Creates the event with a list of items with dataset metadata related to the click
	 * 
	 * @param nativeEvent native event of this custom event
	 * @param functionContext function context provided by CHART.JS
	 * @param items a list of items with dataset metadata related to the click
	 */
	public ChartClickEvent(BaseNativeEvent nativeEvent, Chart functionContext, List<DatasetItem> items) {
		super(nativeEvent, functionContext, ChartEventProperty.ON_CLICK);
		// checks if argument is consistent
		if (items == null) {
			throw new IllegalArgumentException("Dataset items list argument is null");
		}
		this.items = items;
	}

	/**
	 * Returns a list of items with dataset metadata related to the click
	 * 
	 * @return a list of items with dataset metadata related to the click
	 */
	public List<DatasetItem> getItems() {
		return items;
	}

	/*
	 * (non-Javadoc)
	 * 

	 */
	@Override
	public EventType getType() {
		return TYPE;
	}

	/*
	 * (non-Javadoc)
	 * 

	 */
	@Override
	protected void dispatch(EventHandler handler) {
		if (handler instanceof ChartClickEventHandler) {
			ChartClickEventHandler myHandler = (ChartClickEventHandler)handler;
			myHandler.onClick(this);
		}		
	}

}