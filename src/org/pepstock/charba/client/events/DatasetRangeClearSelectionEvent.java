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

/**
 * Event which is fired when the user clears an area on the chart, by {@link DatasetsItemsSelector#ID} plugin.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class DatasetRangeClearSelectionEvent extends AbstractEvent {

	/**
	 * Event type
	 */
	public static final EventType TYPE = EventType.create(DatasetRangeClearSelectionEvent.class);

	/**
	 * Creates the event with native event.
	 * 
	 * @param nativeEvent native event of this custom event
	 */
	public DatasetRangeClearSelectionEvent(BaseNativeEvent nativeEvent) {
		super(nativeEvent, TYPE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.events.Event#dispatch(org.pepstock.charba.client.events.EventHandler)
	 */
	@Override
	protected void dispatch(EventHandler handler) {
		// checks if handler is a correct instance
		if (handler instanceof DatasetRangeClearSelectionEventHandler) {
			// casts handler
			DatasetRangeClearSelectionEventHandler myHandler = (DatasetRangeClearSelectionEventHandler) handler;
			// invokes
			myHandler.onClear(this);
		}
	}

}