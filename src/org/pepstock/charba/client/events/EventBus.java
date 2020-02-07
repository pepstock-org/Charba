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
 * Dispatches events to the registered handlers.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class EventBus implements HasHandlers {

	/**
	 * Dispatches the event to the handler.
	 * 
	 * @param event event instance to dispatch
	 * @param handler handler which must consume the event
	 */
	protected static void dispatchEvent(Event event, EventHandler handler) {
		event.dispatch(handler);
	}

	/**
	 * Adds an handler to receive events of the type passed as argument.
	 * 
	 * @param handler handler instance
	 * @param type type of event which the handler will consume
	 */
	public abstract void addHandler(EventHandler handler, EventType type);

	/**
	 * Removes an handler from the specified event type.
	 * 
	 * @param handler handler instance
	 * @param type type of event which the handler will consume
	 */
	public abstract void removeHandler(EventHandler handler, EventType type);

	/**
	 * Fires the event.
	 *
	 * @param event the event to fire
	 */
	@Override
	public abstract void fireEvent(Event event);

}
