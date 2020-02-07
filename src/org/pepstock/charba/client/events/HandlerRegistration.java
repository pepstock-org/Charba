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

/**
 * Registration objects returned when an event handler is bound, used to deregister.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class HandlerRegistration {

	private final HandlerManager manager;

	private final EventHandler handler;

	private final EventType type;

	/**
	 * FIXME
	 * @param manager
	 * @param handler
	 * @param type
	 */
	HandlerRegistration(HandlerManager manager, EventHandler handler, EventType type) {
		this.manager = manager;
		this.handler = handler;
		this.type = type;
	}

	/**
	 * Deregisters the handler associated with this registration object.
	 */
	public void removeHandler() {
		manager.removeHandler(handler, type);
	}
}
