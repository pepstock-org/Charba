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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.pepstock.charba.client.dom.enums.KeyboardEventType;
import org.pepstock.charba.client.dom.enums.MouseEventType;
import org.pepstock.charba.client.dom.enums.PointerEventType;
import org.pepstock.charba.client.dom.enums.TouchEventType;

/**
 * Utility to store in a map all the DOM events, defined in Charba.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class DefinedEvents {

	// singleton instance
	private static final DefinedEvents INSTANCE = new DefinedEvents();
	// map the defined instance
	// K = event type, V = isEvent instance
	private final Map<String, IsEvent> instances;

	/**
	 * To avoid any instantiation
	 */
	private DefinedEvents() {
		// creates map
		Map<String, IsEvent> map = new HashMap<>();
		// loads all defined events
		load(map, MouseEventType.values());
		load(map, TouchEventType.values());
		load(map, PointerEventType.values());
		load(map, KeyboardEventType.values());
		// stores as immutable
		this.instances = Collections.unmodifiableMap(map);
	}

	/**
	 * Loads the values of a event type in the map.
	 * 
	 * @param map map instance to be loaded
	 * @param events list of event type to load
	 */
	private void load(Map<String, IsEvent> map, IsEvent[] events) {
		// scans all events
		for (IsEvent event : events) {
			// puts in the map
			map.put(event.value(), event);
		}
	}

	/**
	 * Returns the singleton instance of the utility.
	 * 
	 * @return the singleton instance of the utility
	 */
	static DefinedEvents get() {
		return INSTANCE;
	}

	/**
	 * Searches in the map the event for its value.
	 * 
	 * @param type event type to search
	 * @return the {@link IsEvent} instance or <code>null</code> if not found.
	 */
	IsEvent lookup(String type) {
		// checks if type is consistent
		if (type != null) {
			// searches in the map the passed type
			return instances.get(type);
		}
		// if here, the argument is not consistent
		return null;
	}
}
