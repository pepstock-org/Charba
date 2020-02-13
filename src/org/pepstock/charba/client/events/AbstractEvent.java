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

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.dom.BaseNativeEvent;

/**
 * Abstract event for all events which must contain a native event.<br>
 * This event contains the chart instance as source.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class AbstractEvent extends Event {

	// event type instance
	private final EventType type;
	// native event
	private final BaseNativeEvent nativeEvent;

	/**
	 * Creates an event using a native event
	 * 
	 * @param nativeEvent native event of this custom event
	 * @param type type of event
	 */
	protected AbstractEvent(BaseNativeEvent nativeEvent, EventType type) {
		super();
		// checks if native event is consistent
		if (nativeEvent == null) {
			throw new IllegalArgumentException("Native event argument is null");
		}
		// checks if event type is consistent
		if (type == null) {
			throw new IllegalArgumentException("Event type argument is null");
		}
		// stores arguments
		this.nativeEvent = nativeEvent;
		this.type = type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.events.Event#getType()
	 */
	@Override
	public final EventType getType() {
		return type;
	}

	/**
	 * Returns the native event instance.
	 * 
	 * @return the nativeEvent
	 * 
	 */
	public final BaseNativeEvent getNativeEvent() {
		return nativeEvent;
	}

	/**
	 * Returns the chart instance, stored in the event as source.
	 * 
	 * @return the chart instance
	 */
	public IsChart getChart() {
		return (IsChart) getSource();
	}

}