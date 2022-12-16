/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.events;

import org.pepstock.charba.client.IsChart;

/**
 * Manager to add, remove event handlers and firing events to registered handlers.<br>
 * This is currently implemented by all charts.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class HandlerManager {

	// internal bus
	private final EventBus eventBus = new EventBus();

	/**
	 * Creates an empty handler manager
	 */
	protected HandlerManager() {
		// do nothing
	}

	/**
	 * Returns a chart instance as source for all events.
	 * 
	 * @return a chart instance as source for all events
	 */
	protected abstract IsChart getSource();

	/**
	 * Adds a event handler.
	 * 
	 * @param handler handler instance to add
	 * @param type the event type associated with handler
	 * @return the handler registration in order to remove the handler later
	 */
	public HandlerRegistration addHandler(EventHandler handler, EventType type) {
		// adds handler
		eventBus.addHandler(handler, type);
		// creates and returns the handler registration
		return new HandlerRegistration(this, handler, type);
	}

	/**
	 * Fires the event to the handlers.
	 *
	 * @param event the event to fire
	 */
	public void fireEvent(Event event) {
		// checks if source chart and event are consistent
		if (IsChart.isValid(getSource()) && event != null) {
			// sets the chart as source of event
			event.setSource(getSource());
			// fires event
			eventBus.fireEvent(event);
		}
	}

	/**
	 * Returns the amount of handlers for a specific event type.
	 * 
	 * @param type event type to use to get the amount of handlers
	 * @return the amount of handlers for a specific event type
	 */
	public final int getHandlerCount(EventType type) {
		return eventBus.getHandlerCount(type);
	}

	/**
	 * Returns <code>true</code> if there is any event handler for event type passed as argument.
	 * 
	 * @param type event type to check
	 * @return <code>true</code> if there is any event handler for event type passed as argument
	 */
	public final boolean isEventHandled(EventType type) {
		return eventBus.isEventHandled(type);
	}

	/**
	 * Removes a handler from the event type, passed as argument.
	 * 
	 * @param handler handler instance to remove
	 * @param type the event type associated with handler
	 */
	final void removeHandler(EventHandler handler, EventType type) {
		// removes handler
		eventBus.removeHandler(handler, type);
		// if the handler is a chart event handler one
		if (handler instanceof ChartEventHandler) {
			// sends the event to remove handler
			fireEvent(new RemoveHandlerEvent(type));
		}
	}

}