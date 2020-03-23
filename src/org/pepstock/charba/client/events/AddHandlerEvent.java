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
 * Event which is fired when new event handler has been added to the chart.<br>
 * This event should use only for use internal only to manage internally all handlers.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class AddHandlerEvent extends AbstractHandlerEvent {

	/**
	 * Event type
	 */
	public static final EventType TYPE = EventType.create(AddHandlerEvent.class);

	/**
	 * Creates the event with the type of new handler.
	 * 
	 * @param handlerType the type of new handler.
	 */
	public AddHandlerEvent(EventType handlerType) {
		super(handlerType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.events.Event#getAssociatedType()
	 */
	@Override
	public EventType getType() {
		return TYPE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.events.Event#dispatch(org.pepstock.charba.client.commons.events.EventHandler)
	 */
	@Override
	protected void dispatch(EventHandler handler) {
		// checks if handler is a correct instance
		if (handler instanceof AddHandlerEventHandler) {
			// casts handler
			AddHandlerEventHandler myHandler = (AddHandlerEventHandler) handler;
			// invokes
			myHandler.onAdd(this);
		}
	}

}