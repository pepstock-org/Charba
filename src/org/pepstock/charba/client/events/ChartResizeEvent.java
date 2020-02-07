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
import org.pepstock.charba.client.enums.ChartEventProperty;
import org.pepstock.charba.client.items.SizeItem;

/**
 * Event which is fired when the chart has been resized.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class ChartResizeEvent extends AbstractChartEvent {

	/**
	 * Event type
	 */
	public static final EventType TYPE = EventType.create(ChartResizeEvent.class);
	// item which contains the new size of the chart
	private final SizeItem size;

	/**
	 * Creates the event with a item with new size of the chart
	 * 
	 * @param nativeEvent native event of this custom event
	 * @param functionContext function context provided by CHART.JS
	 * @param size item with the new size of the chart
	 */
	public ChartResizeEvent(BaseNativeEvent nativeEvent, Chart functionContext, SizeItem size) {
		super(nativeEvent, functionContext, ChartEventProperty.ON_RESIZE);
		// checks if argument is consistent
		if (size == null) {
			throw new IllegalArgumentException("Size argument is null");
		}
		this.size = size;
	}

	/**
	 * Returns item with the new size of the chart
	 * 
	 * @return the item with the new size of the chart
	 */
	public SizeItem getSize() {
		return size;
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
		if (handler instanceof ChartResizeEventHandler) {
			ChartResizeEventHandler myHandler = (ChartResizeEventHandler)handler;
			myHandler.onResize(this);
		}
	}
	
}