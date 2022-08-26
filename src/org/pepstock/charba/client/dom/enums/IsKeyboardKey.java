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

import org.pepstock.charba.client.commons.Constants;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.dom.DOMBuilder;
import org.pepstock.charba.client.dom.elements.Div;
import org.pepstock.charba.client.dom.events.NativeBaseEvent;
import org.pepstock.charba.client.dom.events.NativeKeyboardEvent;
import org.pepstock.charba.client.events.HasNativeEvent;

/**
 * Maps a keyboard key methods.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface IsKeyboardKey extends Key {

	/**
	 * Returns the {@link Div} element which describes the modifier key.<br>
	 * It can be used in UI if needed.
	 * 
	 * @param key the keyboard key to use in <code>kbd</code> tag.
	 * @return the {@link Div} element which describes the modifier key
	 */
	static Div getElement(IsKeyboardKey key) {
		return getElement(Key.isValid(key) ? key.value() : null);
	}

	/**
	 * Returns the {@link Div} element which describes the modifier key.<br>
	 * It can be used in UI if needed.
	 * 
	 * @param key the keyboard key to use in <code>kbd</code> tag.
	 * @return the {@link Div} element which describes the modifier key
	 */
	static Div getElement(String key) {
		// creates the div with kbd inside
		Div element = DOMBuilder.get().createDivElement();
		// checks key value if consistent
		String inner = key == null ? Constants.NULL_STRING : key;
		// sets inner html for key code
		element.setInnerHTML("<kbd style=\"" + KeyboardCommonKey.CSS + "\">" + inner + "</kbd>");
		// returns it
		return element;
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
		NativeKeyboardEvent keyboardEvent = KeyboardCommonKey.checkAndGet(event);
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
