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
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.items.DatasetItem;

import com.google.gwt.dom.client.NativeEvent;

/**
 * Event which is fired when the user hovers on the chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class ChartHoverEvent extends AbstractChartEvent<ChartHoverEventHandler> implements IsChartEvent {

	/**
	 * Event type
	 */
	public static final Type<ChartHoverEventHandler> TYPE = new Type<>();

	// a list of items with dataset metadata related to the hover
	private final List<DatasetItem> items;

	/**
	 * Creates the event with a list of items with dataset metadata related to the hover
	 * 
	 * @param nativeEvent native event of this custom event
	 * @param functionContext function context provided by CHART.JS
	 * @param key options key where default function is stored
	 * @param items a list of items with dataset metadata related to the hover
	 */
	public ChartHoverEvent(NativeEvent nativeEvent, Chart functionContext, Key key, List<DatasetItem> items) {
		super(nativeEvent, functionContext, key);
		// checks if argument is consistent
		if (items == null) {
			throw new IllegalArgumentException("Dataset items list is null");
		}
		this.items = items;
	}

	/**
	 * Returns a list of items with dataset metadata related to the hover
	 * 
	 * @return a list of items with dataset metadata related to the hover
	 */
	public List<DatasetItem> getItems() {
		return items;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	@Override
	public Type<ChartHoverEventHandler> getAssociatedType() {
		return TYPE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared.EventHandler)
	 */
	@Override
	protected void dispatch(ChartHoverEventHandler handler) {
		handler.onHover(this);
	}

}