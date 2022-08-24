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

import org.pepstock.charba.client.commons.ArrayUtil;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.dom.events.NativeBaseEvent;
import org.pepstock.charba.client.dom.events.NativeUIEvent;
import org.pepstock.charba.client.events.HasNativeEvent;

/**
 * Enumerates the modifiers which are special keys which are used to generate special characters or cause special actions when used in combination with other keys.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum KeyboardModifierKey implements Key
{
	/**
	 * The Alt (Alternative) key.
	 */
	ALT("Alt"),
	/**
	 * The AltGr or AltGraph (Alternate Graphics) key. Enables the ISO Level 3 shift modifier (where Shift is the level 2 modifier).
	 */
	ALT_GRAPH("AltGraph"),
	/**
	 * The Caps Lock key. Toggles the capital character lock on and off for subsequent input.
	 */
	CAPS_LOCK("CapsLock"),
	/**
	 * The Control, Ctrl, or Ctl key. Allows typing control characters.
	 */
	CONTROL("Control"),
	/**
	 * The Meta key. Allows issuing special command inputs.
	 */
	META("Meta"),
	/**
	 * The NumLock (Number Lock) key. Toggles the numeric keypad between number entry some other mode (often directional arrows).
	 */
	NUM_LOCK("NumLock"),
	/**
	 * The Windows key, sometimes is reported instead of as {@link KeyboardModifierKey#META}.
	 */
	OS("OS"),
	/**
	 * The Scroll Lock key. Toggles between scrolling and cursor movement modes.
	 */
	SCROLL_LOCK("ScrollLock"),
	/**
	 * The Shift key. Modifies keystrokes to allow typing upper (or other) case letters, and to support typing punctuation and other special characters.
	 */
	SHIFT("Shift");

	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private KeyboardModifierKey(String value) {
		this.value = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.Key#value()
	 */
	@Override
	public String value() {
		return value;
	}

	/**
	 * Returns <code>true</code> if the modifier key is pressed when the event was emitted.
	 * 
	 * @param event instance of event container to be checked
	 * @return <code>true</code> if the modifier key is pressed when the event was emitted
	 */
	public boolean isPressed(HasNativeEvent event) {
		// checks if argument is consistent
		if (event != null) {
			// checks if pressed
			return isPressed(checkAndGet(event.getNativeEvent()));
		}
		// if here, argument is not consistent
		// then returns false
		return false;
	}

	/**
	 * Returns <code>true</code> if the modifier key is pressed when the event was emitted.
	 * 
	 * @param event instance of native event to be checked
	 * @return <code>true</code> if the modifier key is pressed when the event was emitted
	 */
	public boolean isPressed(NativeBaseEvent event) {
		// gets native ui event
		NativeUIEvent uiEvent = checkAndGet(event);
		// checks if event is consistent
		if (uiEvent != null) {
			return uiEvent.getModifierState(this);
		}
		// if here, argument is not consistent
		// then returns false
		return false;
	}

	/**
	 * Returns <code>true</code> if all modifier keys are pressed when the event was emitted.
	 * 
	 * @param event instance of event container to be checked
	 * @param keys array of keys to be checked against the event
	 * @return <code>true</code> if the modifier key is pressed when the event was emitted
	 */
	public static boolean arePressed(HasNativeEvent event, KeyboardModifierKey... keys) {
		// checks if argument is consistent
		if (event != null) {
			// checks if pressed
			return arePressed(event.getNativeEvent(), keys);
		}
		// if here argument is not consistent
		// then returns false
		return false;
	}

	/**
	 * Returns <code>true</code> if all modifier keys are pressed when the event was emitted.
	 * 
	 * @param event instance of native event to be checked
	 * @param keys array of keys to be checked against the event
	 * @return <code>true</code> if the modifier key is pressed when the event was emitted
	 */
	public static boolean arePressed(NativeBaseEvent event, KeyboardModifierKey... keys) {
		// checks if event and keys are consistent
		if (event != null && ArrayUtil.isNotEmpty(keys)) {
			// creates result instance
			boolean result = true;
			// scans keys
			for (KeyboardModifierKey key : keys) {
				// checks if modifier key is consistent
				if (!Key.isValid(key)) {
					// if not consistent
					// returns false
					return false;
				}
				// stores result if pressed
				result = result && key.isPressed(event);
			}
			return result;
		}
		// if here arguments are not consistent
		// then returns false
		return false;
	}

	/**
	 * Checks and get the native event if is an instance of {@link NativeUIEvent}, otherwise <code>null</code>.
	 * 
	 * @param event DOM native event instance
	 * @return the native event if is an instance of {@link NativeUIEvent}, otherwise <code>null</code>
	 */
	private static NativeUIEvent checkAndGet(NativeBaseEvent event) {
		// checks if event has modifier keys
		if (event instanceof NativeUIEvent) {
			// casts to modifier key
			return (NativeUIEvent) event;
		}
		// if here, the event doesn't have modifier keys
		// then returns null
		return null;
	}

}
