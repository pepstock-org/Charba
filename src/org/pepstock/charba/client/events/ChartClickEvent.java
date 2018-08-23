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

import java.util.Arrays;
import java.util.List;

import org.pepstock.charba.client.items.DatasetItem;
import org.pepstock.charba.client.items.DatasetMetaItem;

import com.google.gwt.dom.client.NativeEvent;

/**
 * Event which is fired when the user clicks on the chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ChartClickEvent extends AbstractEvent<ChartClickEventHandler> {

	/**
	 * Event type
	 */
	public static final Type<ChartClickEventHandler> TYPE = new Type<ChartClickEventHandler>();

	// a list of items with dataset metadata related to the click
	private final List<DatasetItem> items;

	/**
	 * Creates the event with dataset metadata item related to the click
	 * 
	 * @param nativeEvent native event of this custom event
	 * @param item dataset metadata item related to the click
	 * @see org.pepstock.charba.client.items.DatasetItem
	 */
	public ChartClickEvent(NativeEvent nativeEvent, DatasetItem item) {
		super(nativeEvent);
		this.items = Arrays.asList(item);
	}

	/**
	 * Creates the event with a list of items with dataset metadata related to the click
	 * 
	 * @param nativeEvent native event of this custom event
	 * @param items a list of items with dataset metadata related to the click
	 * @see org.pepstock.charba.client.items.DatasetItem
	 */
	public ChartClickEvent(NativeEvent nativeEvent, DatasetMetaItem items) {
		super(nativeEvent);
		this.items = items.getDatasets();
	}

	/**
	 * Returns a list of items with dataset metadata related to the click
	 * 
	 * @return a list of items with dataset metadata related to the click
	 * @see org.pepstock.charba.client.items.DatasetItem
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
	public Type<ChartClickEventHandler> getAssociatedType() {
		return TYPE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared.EventHandler)
	 */
	@Override
	protected void dispatch(ChartClickEventHandler handler) {
		handler.onClick(this);
	}

}