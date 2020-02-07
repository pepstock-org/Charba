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

/**
 * Manager responsible for adding handlers to event sources and firing those handlers on passed in events. Primitive ancestor of
 * {@link EventBus}, and used at the core of {org.gwtproject.user.client.ui.Widget}.
 * <p>
 * While widget authors should continue to use
 * {@link org.gwtproject.user.client.ui.Widget#addDomHandler(EventHandler, org.gwtproject.event.dom.client.DomEvent.Type)} and
 * {@link org.gwtproject.user.client.ui.Widget#addHandler(Object, org.pepstock.charba.client.events.gwtproject.event.shared.Event.Type)} , application
 * developers are strongly discouraged from using a HandlerManager instance as a global event dispatch mechanism.
 */
public abstract class HandlerManager implements HasHandlers {

	private final SimpleEventBus eventBus = new SimpleEventBus();

	/**
	 * Creates a handler manager with a source to be set on all events fired via {@link #fireEvent(Event)}. Handlers will be
	 * fired in the order that they are added.
	 * 
	 * @param source the default event source
	 */
	protected HandlerManager() {
	}

	protected abstract IsChart getSource();

	/**
	 * Adds a handler.
	 * 
	 * @param <H> The type of handler
	 * @param type the event type associated with this handler
	 * @param handler the handler
	 * @return the handler registration, can be stored in order to remove the handler later
	 */
	public HandlerRegistration addHandler(EventHandler handler, EventType type) {
		eventBus.addHandler(handler, type);
		return new HandlerRegistration(this, handler, type);
	}

	/**
	 * Fires the given event to the handlers listening to the event's type.
	 * <p>
	 * Any exceptions thrown by handlers will be bundled into a {@link UmbrellaException} and then re-thrown after all handlers
	 * have completed. An exception thrown by a handler will not prevent other handlers from executing.
	 * <p>
	 * Note, any subclass should be very careful about overriding this method, as adds/removes of handlers will not be safe
	 * except within this implementation.
	 * 
	 * @param event the event
	 */
	@Override
	public final void fireEvent(Event event) {
		// checks if source is consistent
		if (getSource() != null) {
			event.setSource(getSource());
			eventBus.fireEvent(event);
		}
	}

	/**
	 * Gets the number of handlers listening to the event type.
	 * 
	 * @param type the event type
	 * @return the number of registered handlers
	 */
	public final int getHandlerCount(EventType type) {
		return eventBus.getHandlerCount(type);
	}

	/**
	 * Does this handler manager handle the given event type?
	 * 
	 * @param e the event type
	 * @return whether the given event type is handled
	 */
	public final boolean isEventHandled(EventType e) {
		return eventBus.isEventHandled(e);
	}

	/**
     * Removes the given handler from the specified event type.
     * 
     * @param <H> handler type
     * 
     * @param type the event type
     * @param handler the handler
     */
    public final void removeHandler(EventHandler handler, EventType type) {
    	// removes handler
    	eventBus.removeHandler(handler, type);
		// if the handler is a chart event handler one
		if (handler instanceof ChartEventHandler) {
			// sends the event
			fireEvent(new RemoveHandlerEvent(type));
		}
    }

}
