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

import org.pepstock.charba.client.dom.BaseNativeEvent;
import org.pepstock.charba.client.impl.plugins.DatasetsItemsSelector;
import org.pepstock.charba.client.items.ScaleValueItem;

/**
 * Event which is fired when the user selects an area on the chart, by {@link DatasetsItemsSelector#ID} plugin.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class DatasetRangeSelectionEvent extends AbstractEvent {

	/**
	 * Event type
	 */
	public static final EventType TYPE = EventType.create(DatasetRangeSelectionEvent.class);
	// starting value of selected scale
	private final ScaleValueItem from;
	// ending value of selected scale
	private final ScaleValueItem to;

	/**
	 * Creates the event with start and end values of selected scale
	 * 
	 * @param nativeEvent native event of this custom event
	 * @param from starting value of selected scale
	 * @param to ending value of selected scale
	 */
	public DatasetRangeSelectionEvent(BaseNativeEvent nativeEvent, ScaleValueItem from, ScaleValueItem to) {
		super(nativeEvent, TYPE);
		// checks if arguments are consistent
		if (from == null) {
			throw new IllegalArgumentException("From scale value argument is null");
		}
		if (to == null) {
			throw new IllegalArgumentException("To scale value argument is null");
		}
		// stores arguments
		this.from = from;
		this.to = to;
	}

	/**
	 * Returns the starting value of selected scale.
	 * 
	 * @return the starting value of selected scale
	 */
	public ScaleValueItem getFrom() {
		return from;
	}

	/**
	 * Returns the ending value of selected scale.
	 * 
	 * @return the ending value of selected scale
	 */
	public ScaleValueItem getTo() {
		return to;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.events.Event#dispatch(org.pepstock.charba.client.events.EventHandler)
	 */
	@Override
	protected void dispatch(EventHandler handler) {
		// checks if handler is a correct instance
		if (handler instanceof DatasetRangeSelectionEventHandler) {
			// casts handler
			DatasetRangeSelectionEventHandler myHandler = (DatasetRangeSelectionEventHandler) handler;
			// invokes
			myHandler.onSelect(this);
		}
	}

}