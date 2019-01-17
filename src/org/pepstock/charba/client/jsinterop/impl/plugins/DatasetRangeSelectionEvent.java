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
package org.pepstock.charba.client.jsinterop.impl.plugins;

import org.pepstock.charba.client.jsinterop.events.AbstractEvent;

import com.google.gwt.dom.client.NativeEvent;

/**
 * Event which is fired when the user selects an area on the chart.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 * @see org.pepstock.charba.client.jsinterop.impl.plugins.DatasetsItemsSelector
 */
public final class DatasetRangeSelectionEvent extends AbstractEvent<DatasetRangeSelectionEventHandler> {

	/**
	 * Value of FROM and TO when the event is representing a reset of selection
	 */
	public static final int RESET_SELECTION = Integer.MIN_VALUE;

	/**
	 * Event type
	 */
	public static final Type<DatasetRangeSelectionEventHandler> TYPE = new Type<DatasetRangeSelectionEventHandler>();
	// starting index of selected dataset
	private final int from;
	// ending index of selected dataset
	private final int to;

	/**
	 * Creates the event when the reset of current selection has been requested.
	 * 
	 * @param nativeEvent native event of this custom event
	 */
	public DatasetRangeSelectionEvent(NativeEvent nativeEvent) {
		this(nativeEvent, RESET_SELECTION, RESET_SELECTION);
	}

	/**
	 * Creates the event with start and end index of selected datasets.
	 * 
	 * @param nativeEvent native event of this custom event
	 * @param from starting index of selected dataset
	 * @param to ending index of selected dataset
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