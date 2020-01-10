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

import java.util.List;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * Abstract event which is fired when event handler has been added or removed to/from the chart.<br>
 * This event should use only for use internal only to manage internally all handlers.
 * 
 * @author Andrea "Stock" Stocchero
 */
abstract class AbstractHandlerEvent<H extends EventHandler> extends GwtEvent<H> {

	// type of new handler added to the chart
	private final Type<? extends EventHandler> handlerType;

	/**
	 * Creates the event with the type of new handler.
	 * 
	 * @param handlerType the type of new handler.
	 */
	public AbstractHandlerEvent(Type<? extends EventHandler> handlerType) {
		// checks if argument is consistent
		if (handlerType == null) {
			throw new IllegalArgumentException("Handler type argument is null");
		}
		this.handlerType = handlerType;
	}

	/**
	 * Returns <code>true</code> if the type of event handler is inside the list provided as argument, otherwise
	 * <code>false</code>.
	 * 
	 * @param types list of type to use checking if the type of event handler of event is in the list
	 * @return <code>true</code> if the type of event handler is inside the list provided as argument, otherwise
	 *         <code>false</code>
	 */
	public final boolean isRecognize(List<Type<?>> types) {
		// checks if argument is consistent
		if (types != null && !types.isEmpty()) {
			// scans all types
			for (Type<?> type : types) {
				// if types is equals to the type of event
				if (type.equals(handlerType)) {
					return true;
				}
			}
		}
		// if here, the event handler type
		// is not recognized
		return false;
	}

	/**
	 * Returns <code>true</code> if the type of event handler is equals to the event handler type provided as argument,
	 * otherwise <code>false</code>.
	 * 
	 * @param type the event handler type to use checking if equals to the type of event handler of event
	 * @return <code>true</code> if the type of event handler is equals to the event handler type provided as argument,
	 *         otherwise <code>false</code>
	 */
	public final boolean isRecognize(Type<?> type) {
		// if types is equals to the type of event
		return handlerType.equals(type);
	}
}