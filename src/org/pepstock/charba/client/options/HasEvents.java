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

import java.util.Set;

import org.pepstock.charba.client.defaults.IsDefaultEventHandler;
import org.pepstock.charba.client.defaults.globals.DefaultsBuilder;

/**
 * Defines a configuration element which is managing the EVENTS property.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface HasEvents extends IsDefaultEventHandler {

	/**
	 * Returns a event handler handler instance to use in the default methods of this interface.
	 * 
	 * @return a event handler handler instance
	 */
	EventsOptionHandler getEventsOptionHandler();

	/**
	 * Sets the browser events that the chart should listen to.
	 * 
	 * @param events the browser events that the chart should listen to.
	 */
	default void setEvents(IsEvent... events) {
		// checks if event handler handler is consistent
		if (getEventsOptionHandler() != null) {
			getEventsOptionHandler().setEvents(events);
		}
	}

	/**
	 * Sets the browser events that the legend should listen to.
	 * 
	 * @param events the browser events that the legend should listen to.
	 */
	default void setEvents(Set<IsEvent> events) {
		// checks if event handler handler is consistent
		if (getEventsOptionHandler() != null) {
			getEventsOptionHandler().setEvents(events);
		}
	}

	/**
	 * Returns the browser events that the chart should listen to.
	 * 
	 * @return the browser events that the chart should listen to.
	 */
	@Override
	default Set<IsEvent> getEvents() {
		// checks if event handler handler is consistent
		if (getEventsOptionHandler() != null) {
			return getEventsOptionHandler().getEvents();
		}
		return DefaultsBuilder.get().getOptions().getEvents();
	}

}
