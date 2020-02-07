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
import org.pepstock.charba.client.dom.BaseNativeEvent;
import org.pepstock.charba.client.enums.LegendEventProperty;
import org.pepstock.charba.client.items.LegendItem;

/**
 * Event which is fired when the user leaves on the legend of the chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class LegendLeaveEvent extends AbstractChartEvent implements IsLegendEvent {

	/**
	 * Event type
	 */
	public static final EventType TYPE = EventType.create(LegendLeaveEvent.class);
	// legend item selected by hovering
	private final LegendItem item;

	/**
	 * Creates the event with legend item related to the leaving.
	 * 
	 * @param nativeEvent native event of this custom event
	 * @param functionContext function context provided by CHART.JS
	 * @param item legend item related to the leaving
	 */
	public LegendLeaveEvent(BaseNativeEvent nativeEvent, Chart functionContext, LegendItem item) {
		super(nativeEvent, functionContext, LegendEventProperty.ON_LEAVE);
		// checks if argument is consistent
		if (item == null) {
			throw new IllegalArgumentException("Legend item argument is null");
		}
		this.item = item;
	}

	/**
	 * Returns the legend item related to the leaving.
	 * 
	 * @return the legend item related to the leaving
	 */
	public LegendItem getItem() {
		return item;
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
		if (handler instanceof LegendLeaveEventHandler) {
			LegendLeaveEventHandler myHandler = (LegendLeaveEventHandler)handler;
			myHandler.onLeave(this);
		}
	}
	
}