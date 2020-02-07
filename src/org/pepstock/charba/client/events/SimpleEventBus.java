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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.pepstock.charba.client.commons.Key;

/**
 * Basic implementation of {@link EventBus}.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class SimpleEventBus extends EventBus {

	/**
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private interface Command {
		void execute();
	}

	private int firingDepth = 0;

	/** Add and remove operations received during dispatch. */
	private List<Command> deferredDeltas;

	/** Map of event type to map of event source to list of their handlers. */
	private final Map<EventType, List<EventHandler>> map = new HashMap<>();
	
	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.commons.events.EventBus#addHandler(org.pepstock.charba.client.commons.events.EventHandler, org.pepstock.charba.client.commons.events.EventType)
	 */
	@Override
	public void addHandler(EventHandler handler, EventType type) {
		Key.checkIfValid(type);
		if (handler == null) {
			throw new IllegalArgumentException("Cannot add a null handler");
		}
		if (firingDepth > 0) {
			defer(() -> doAddNow(handler, type));
		} else {
			doAddNow(handler, type);
		}
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.commons.events.EventBus#removeHandler(org.pepstock.charba.client.commons.events.EventHandler, org.pepstock.charba.client.commons.events.EventType)
	 */
	@Override
	public void removeHandler(EventHandler handler, EventType type) {
		doRemove(handler, type);
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.commons.events.EventBus#fireEvent(org.pepstock.charba.client.commons.events.Event)
	 */
	@Override
	public void fireEvent(Event event) {
		doFire(event);
	}

	int getHandlerCount(EventType type) {
		if (map.containsKey(type)) {
			return map.get(type).size();
		}
		return 0;
	}

	boolean isEventHandled(EventType type) {
		return map.containsKey(type);
	}

	private void doRemove(EventHandler handler, EventType type) {
		if (firingDepth > 0) {
			defer(() -> doRemoveNow(handler, type));
		} else {
			doRemoveNow(handler, type);
		}
	}

	private void doRemoveNow(EventHandler handler, EventType type) {
		if (map.containsKey(type)) {
			List<EventHandler> list = map.get(type);
			if (list.remove(handler) && list.isEmpty()) {
				map.remove(type);
			}
		}
	}

	private void doAddNow(EventHandler handler, EventType type) {
		if (!map.containsKey(type)) {
			List<EventHandler> newList = new ArrayList<>();
			map.put(type, newList);
		}
		List<EventHandler> list = map.get(type);
		list.add(handler);
	}

	private void defer(Command command) {
		if (deferredDeltas == null) {
			deferredDeltas = new ArrayList<>();
		}
		deferredDeltas.add(command);
	}

	private <H> void doFire(Event event) {
		if (event == null) {
			throw new IllegalArgumentException("Cannot fire null event");
		}
		try {
			firingDepth++;
			if (map.containsKey(event.getType())) {
				List<EventHandler> handlers = map.get(event.getType());
				for (EventHandler handler : handlers) {
					dispatchEvent(event, handler);
				}
			}
		} finally {
			firingDepth--;
			if (firingDepth == 0) {
				handleQueuedAddsAndRemoves();
			}
		}
	}

	private void handleQueuedAddsAndRemoves() {
		if (deferredDeltas != null) {
			try {
				for (Command c : deferredDeltas) {
					c.execute();
				}
			} finally {
				deferredDeltas = null;
			}
		}
	}
}
