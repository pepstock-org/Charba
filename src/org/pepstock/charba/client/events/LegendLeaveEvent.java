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
import org.pepstock.charba.client.enums.LegendEventProperty;
import org.pepstock.charba.client.items.LegendItem;

import com.google.gwt.dom.client.NativeEvent;

/**
 * Event which is fired when the user leaves on the legend of the chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class LegendLeaveEvent extends AbstractChartEvent<LegendLeaveEventHandler> implements IsLegendEvent {

	/**
	 * Event type
	 */
	public static final Type<LegendLeaveEventHandler> TYPE = new Type<>();
	// legend item selected by hovering
	private final LegendItem item;

	/**
	 * Creates the event with legend item related to the leaving.
	 * 
	 * @param nativeEvent native event of this custom event
	 * @param functionContext function context provided by CHART.JS
	 * @param item legend item related to the leaving
	 */
	public LegendLeaveEvent(NativeEvent nativeEvent, Chart functionContext, LegendItem item) {
		super(nativeEvent, functionContext, LegendEventProperty.ON_LEAVE);
		// checks if argument is consistent
		if (item == null) {
			throw new IllegalArgumentException("Legend item is null");
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
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	@Override
	public Type<LegendLeaveEventHandler> getAssociatedType() {
		return TYPE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared.EventHandler)
	 */
	@Override
	protected void dispatch(LegendLeaveEventHandler handler) {
		handler.onLeave(this);
	}

}