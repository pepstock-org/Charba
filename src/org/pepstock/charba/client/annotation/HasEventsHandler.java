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
package org.pepstock.charba.client.annotation;

import org.pepstock.charba.client.annotation.listeners.ClickCallback;
import org.pepstock.charba.client.annotation.listeners.EnterCallback;
import org.pepstock.charba.client.annotation.listeners.LeaveCallback;

/**
 * Interface to map the event callbacks options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface HasEventsHandler extends IsDefaultsEventsHandler {

	/**
	 * Returns a event callbacks handler instance to use in the default methods of this interface.
	 * 
	 * @return a event callbacks handler instance
	 */
	EventsHandler getEventsHandler();

	/**
	 * Returns the callback called when a "enter" event is occurring.
	 * 
	 * @return the callback called when a "enter" event is occurring
	 */
	@Override
	default EnterCallback getEnterCallback() {
		// checks if handler is consistent
		if (getEventsHandler() != null) {
			return getEventsHandler().getEnterCallback();
		}
		// if here, the property is missing
		// then returns null
		return null;
	}

	/**
	 * Sets the callback called when a "enter" event is occurring.
	 * 
	 * @param enterCallback the callback called when a "enter" event is occurring
	 */
	default void setEnterCallback(EnterCallback enterCallback) {
		// checks if handler is consistent
		if (getEventsHandler() != null) {
			getEventsHandler().setEnterCallback(enterCallback);
		}
	}

	/**
	 * Returns the callback called when a "leave" event is occurring.
	 * 
	 * @return the callback called when a "leave" event is occurring
	 */
	@Override
	default LeaveCallback getLeaveCallback() {
		// checks if handler is consistent
		if (getEventsHandler() != null) {
			return getEventsHandler().getLeaveCallback();
		}
		// if here, the property is missing
		// then returns null
		return null;
	}

	/**
	 * Sets the callback called when a "leave" event is occurring.
	 * 
	 * @param leaveCallback the callback called when a "leave" event is occurring
	 */
	default void setLeaveCallback(LeaveCallback leaveCallback) {
		// checks if handler is consistent
		if (getEventsHandler() != null) {
			getEventsHandler().setLeaveCallback(leaveCallback);
		}
	}

	/**
	 * Returns the callback called when a "click" event is occurring.
	 * 
	 * @return the callback called when a "click" event is occurring
	 */
	@Override
	default ClickCallback getClickCallback() {
		// checks if handler is consistent
		if (getEventsHandler() != null) {
			return getEventsHandler().getClickCallback();
		}
		// if here, the property is missing
		// then returns null
		return null;
	}

	/**
	 * Sets the callback called when a "click" event is occurring.
	 * 
	 * @param clickCallback the callback called when a "click" event is occurring
	 */
	default void setClickCallback(ClickCallback clickCallback) {
		// checks if handler is consistent
		if (getEventsHandler() != null) {
			getEventsHandler().setClickCallback(clickCallback);
		}
	}
}