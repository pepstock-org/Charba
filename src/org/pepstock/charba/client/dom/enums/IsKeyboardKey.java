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
package org.pepstock.charba.client.dom.enums;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.dom.events.NativeBaseEvent;
import org.pepstock.charba.client.dom.events.NativeKeyboardEvent;
import org.pepstock.charba.client.events.HasNativeEvent;

/**
 * Maps a keyboard key methods.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface IsKeyboardKey extends Key {

	/**
	 * Checks and get the native event if is an instance of {@link NativeKeyboardEvent}, otherwise <code>null</code>.
	 * 
	 * @param event DOM native event instance
	 * @return the native event if is an instance of {@link NativeKeyboardEvent}, otherwise <code>null</code>
	 */
	static NativeKeyboardEvent checkAndGet(NativeBaseEvent event) {
		// checks if event is a keyboard one
		if (event instanceof NativeKeyboardEvent) {
			// casts to keyboard event
			return (NativeKeyboardEvent) event;
		}
		// if here, argument is not a keyboard event
		// then returns null
		return null;
	}

	/**
	 * Returns <code>true</code> if the key is the same of the event.
	 * 
	 * @param event instance of event container to be checked
	 * @return <code>true</code> if the key is the same of the event
	 */
	default boolean is(HasNativeEvent event) {
		// checks if argument is consistent
		if (event != null) {
			// checks if pressed
			return is(event.getNativeEvent());
		}
		// if here, argument is not consistent
		// then returns false
		return false;
	}

	/**
	 * Returns <code>true</code> if the key is the same of the event.
	 * 
	 * @param event instance of native event to be checked
	 * @return <code>true</code> if the key is the same of the event
	 */
	default boolean is(NativeBaseEvent event) {
		// gets native ui event
		NativeKeyboardEvent keyboardEvent = checkAndGet(event);
		// checks if event is consistent
		if (keyboardEvent != null) {
			return is(keyboardEvent.getKey());
		}
		// if here, argument is not consistent
		// then returns false
		return false;
	}

	/**
	 * Returns <code>true</code> if the key is the same of the event.
	 * 
	 * @param key key as string to compare
	 * @return <code>true</code> if the key is the same of the event
	 */
	default boolean is(String key) {
		return value().equalsIgnoreCase(key);
	}

}
