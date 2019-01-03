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
package org.pepstock.charba.client.jsinterop.events;

import com.google.gwt.dom.client.NativeEvent;

/**
 * Event which is fired when the user clicks on the chart and selects a dataset.<br>
 * This event doesn't suppress the click event on the chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class DatasetRangeSelectionEvent extends AbstractEvent<DatasetRangeSelectionEventHandler> {

	/**
	 * Event type
	 */
	public static final Type<DatasetRangeSelectionEventHandler> TYPE = new Type<DatasetRangeSelectionEventHandler>();

	private final int from;
	
	private final int to;
	
	/**
	 * Creates the event with dataset metadata item related to the click
	 * 
	 * @param nativeEvent native event of this custom event
	 * @param item dataset metadata item related to the click org.pepstock.charba.client.items.DatasetMetaItem
	 */
	public DatasetRangeSelectionEvent(NativeEvent nativeEvent, int from, int to) {
		super(nativeEvent);
		this.from = from;
		this.to = to;
	}

	/**
	 * @return the from
	 */
	public int getFrom() {
		return from;
	}

	/**
	 * @return the to
	 */
	public int getTo() {
		return to;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	@Override
	public Type<DatasetRangeSelectionEventHandler> getAssociatedType() {
		return TYPE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared.EventHandler)
	 */
	@Override
	protected void dispatch(DatasetRangeSelectionEventHandler handler) {
		handler.onSelect(this);
	}

}