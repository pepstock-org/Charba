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

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.pepstock.charba.client.commons.Key;

/**
 * Internal implementation of event bus, which will perform firing of event to the handlers.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 */
final class EventBus {

	// amount of event queue
	private int eventsFiringQueue = 0;
	// contains all deferred actions
	private final List<Runnable> deferredHandlerActions = new LinkedList<>();
	// maps the event type and the event handlers, registered for that type
	// K = event type, V = list of event handlers
	private final Map<EventType, List<EventHandler>> handlers = new HashMap<>();

	/**
	 * Adds an handler to receive events of the type passed as argument.
	 * 
	 * @param handler handler instance
	 * @param type type of event which the handler will consume
	 */
	void addHandler(EventHandler handler, EventType type) {
		// checks if event type is consistent
		Key.checkIfValid(type);
		// checks if handler is consistent
		if (handler == null) {
			// if not, exception
			throw new IllegalArgumentException("Evenmt handler is null");
		}
		// checks if some events are firing
		if (eventsFiringQueue > 0) {
			// if yes, stores the add handler into actions list
			deferredHandlerActions.add(() -> doAddNow(handler, type));
		} else {
			// if here, no event firing that add handler
			doAddNow(handler, type);
		}
	}

	/**
	 * Removes an handler from the specified event type.
	 * 
	 * @param handler handler instance
	 * @param type type of event which the handler will consume
	 */
	void removeHandler(EventHandler handler, EventType type) {
		// checks if event type is consistent
		Key.checkIfValid(type);
		// checks if handler is consistent
		if (handler == null) {
			// if not, exception
			throw new IllegalArgumentException("Evenmt handler is null");
		}
		// checks if some events are firing
		if (eventsFiringQueue > 0) {
			// if yes, stores the add handler into actions list
			deferredHandlerActions.add(() -> doRemoveNow(handler, type));
		} else {
			// if here, no event firing that add handler
			doRemoveNow(handler, type);
		}
	}

	/**
	 * Fires the event.
	 *
	 * @param event the event to fire
	 */
	void fireEvent(Event event) {
		doFire(event);
	}

	/**
	 * Returns the amount of handlers for a specific event type.
	 * 
	 * @param type event type to use to get the amount of handlers
	 * @return the amount of handlers for a specific event type
	 */
	int getHandlerCount(EventType type) {
		// checks if event type is consistent and the type has
		// got any handlers
		if (isEventHandled(type)) {
			// returns the size of the handlers list
			return handlers.get(type).size();
		}
		// if here, argument is not consistent
		// or event type has not got any handler
		return 0;
	}

	/**
	 * Returns <code>true</code> if there is any event handler for event type passed as argument.
	 * 
	 * @param type event type to check
	 * @return <code>true</code> if there is any event handler for event type passed as argument
	 */
	boolean isEventHandled(EventType type) {
		return Key.isValid(type) && handlers.containsKey(type);
	}

	/**
	 * Adds new event handler for the passed event type.
	 * 
	 * @param handler handler instance to add
	 * @param type the vent type related to the handler
	 */
	private void doAddNow(EventHandler handler, EventType type) {
		// checks if there is not already any handler for that type
		if (!handlers.containsKey(type)) {
			// creates new list
			List<EventHandler> newList = new LinkedList<>();
			// stores the list for event type
			handlers.put(type, newList);
		}
		// gets the stored list of handlers
		List<EventHandler> list = handlers.get(type);
		// stores the handler
		list.add(handler);
	}

	/**
	 * Removes an event handler for the passed event type.
	 * 
	 * @param handler handler instance to remove
	 * @param type the vent type related to the handler
	 */
	private void doRemoveNow(EventHandler handler, EventType type) {
		// checks if there is already any handler for that type
		if (handlers.containsKey(type)) {
			// gets the stored list of handler
			List<EventHandler> list = handlers.get(type);
			// removes and checks if the list is empty after removing
			if (list.remove(handler) && list.isEmpty()) {
				// if here, no handler for event type
				// therefore removes the list from map
				handlers.remove(type);
			}
		}
	}

	/**
	 * Fires the passed vent to all handlers registered for event type.
	 * 
	 * @param event event instance to fire
	 */
	private void doFire(Event event) {
		// checks if event and its type are consistent
		// if not, skips any firing
		if (event != null && Key.isValid(event.getType())) {
			try {
				// gets event type
				EventType type = event.getType();
				// increments counter of event firing
				eventsFiringQueue++;
				// checks if there is any handler for that event
				if (handlers.containsKey(type)) {
					// gets the list of stored handlers for that type
					List<EventHandler> storedHandlers = handlers.get(type);
					// scans all handlers
					for (EventHandler handler : storedHandlers) {
						// and dispatches event on handler
						event.dispatch(handler);
					}
				}
			} finally {
				// decrements counter of event firing
				eventsFiringQueue--;
				// checks if some events are firing
				if (eventsFiringQueue == 0) {
					// performs all deferred actions
					handleDeferredHandlerActions();
				}
			}
		}
	}

	/**
	 * Performs all deferred actions, if there are.
	 */
	private void handleDeferredHandlerActions() {
		// checks if thre is any deferred actions
		if (!deferredHandlerActions.isEmpty()) {
			// gets iterator
			Iterator<Runnable> iterator = deferredHandlerActions.iterator();
			// scans all deferred actions
			while (iterator.hasNext()) {
				// gets the deferred action
				Runnable action = iterator.next();
				// executes the pending action
				action.run();
				// removes the deferred action
				iterator.remove();
			}
		}
	}
}
