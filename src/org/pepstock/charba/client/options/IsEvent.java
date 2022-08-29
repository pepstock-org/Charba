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
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.dom.events.NativeBaseEvent;
import org.pepstock.charba.client.events.HasNativeEvent;

/**
 * RepresentstThe events option defines the browser events that the chart, legend, tooltip or plugins should listen to.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsEvent extends Key {

	/**
	 * Returns a event instance by its string value.
	 * 
	 * @param event string value to use
	 * @return new event instance
	 */
	static IsEvent create(String event) {
		// checks if event as argument is a default one
		IsEvent eventInstance = DefinedEvents.get().lookup(event);
		// checks if event already exists
		if (eventInstance != null) {
			// returns the found event
			return eventInstance;
		}
		// if here, is not a defined one
		// then creates new event
		return new StandardEvent(event);
	}

	/**
	 * Checks if the event type is equals to the event type passed as string.
	 * 
	 * @param eventType event type to be checked as string.
	 * @return <code>true</code> if the event type is equals to the event type passed as string
	 */
	default boolean is(String eventType) {
		return Key.isValid(this) && value().equalsIgnoreCase(eventType);
	}

	/**
	 * Checks if the event type is equals to the event type passed in the event.
	 * 
	 * @param event event to be checked, by its type.
	 * @return <code>true</code> if the event type is equals to the event type passed as event
	 */
	default boolean is(NativeBaseEvent event) {
		return event != null && is(event.getType());
	}

	/**
	 * Checks if the event type is equals to the event type passed in the event container.
	 * 
	 * @param container event container to be checked, by its event type.
	 * @return <code>true</code> if the event type is equals to the event type passed as event container
	 */
	default boolean is(HasNativeEvent container) {
		return container != null && is(container.getNativeEvent());
	}

}