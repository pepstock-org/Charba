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
package org.pepstock.charba.client.enums;

import java.util.ArrayList;
import java.util.List;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.dom.enums.KeyboardModifierKey;
import org.pepstock.charba.client.dom.events.NativeBaseEvent;
import org.pepstock.charba.client.events.HasNativeEvent;

/**
 * A modifier key modifies the action of another key when the keys are pressed at the same time.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum ModifierKey implements Key
{
	/**
	 * Used in combination with the numeric keypad for entering <b>Alt</b> codes, which output special characters;
	 */
	ALT("alt", KeyboardModifierKey.ALT),
	/**
	 * Used for entering keyboard shortcuts.
	 */
	CTRL("ctrl", KeyboardModifierKey.CONTROL),
	/**
	 * Used for entering keyboard shortcuts.
	 */
	META("meta", KeyboardModifierKey.META),
	/**
	 * Used for capitalizing letters and entering different types of symbols.
	 */
	SHIFT("shift", KeyboardModifierKey.SHIFT);

	// name value of property
	private final String value;
	// instance of modifier key
	private final KeyboardModifierKey modifier;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 * @param modifier item of {@link KeyboardModifierKey}
	 */
	private ModifierKey(String value, KeyboardModifierKey modifier) {
		this.value = value;
		this.modifier = modifier;
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
	 * Returns the related {@link KeyboardModifierKey}.
	 * 
	 * @return the related {@link KeyboardModifierKey}
	 */
	public KeyboardModifierKey getKeyboardKey() {
		return modifier;
	}

	/**
	 * Returns <code>true</code> if the modifier key is pressed when the event was emitted.
	 * 
	 * @param event instance of event container to be checked
	 * @return <code>true</code> if the modifier key is pressed when the event was emitted
	 */
	public boolean isPressed(HasNativeEvent event) {
		return modifier.isPressed(event);
	}

	/**
	 * Returns <code>true</code> if the modifier key is pressed when the event was emitted.
	 * 
	 * @param event instance of native event to be checked
	 * @return <code>true</code> if the modifier key is pressed when the event was emitted
	 */
	public boolean isPressed(NativeBaseEvent event) {
		return modifier.isPressed(event);
	}

	/**
	 * Returns <code>true</code> if all modifier keys are pressed when the event was emitted.
	 * 
	 * @param event instance of event container to be checked
	 * @param keys array of keys to be checked against the event
	 * @return <code>true</code> if the modifier key is pressed when the event was emitted
	 */
	public static boolean arePressed(HasNativeEvent event, ModifierKey... keys) {
		return KeyboardModifierKey.arePressed(event, modifierKeysToKeyboardModifierKeys(keys).toArray(new KeyboardModifierKey[0]));
	}

	/**
	 * Returns <code>true</code> if all modifier keys are pressed when the event was emitted.
	 * 
	 * @param event instance of native event to be checked
	 * @param keys array of keys to be checked against the event
	 * @return <code>true</code> if the modifier key is pressed when the event was emitted
	 */
	public static boolean arePressed(NativeBaseEvent event, ModifierKey... keys) {
		return KeyboardModifierKey.arePressed(event, modifierKeysToKeyboardModifierKeys(keys).toArray(new KeyboardModifierKey[0]));
	}

	/**
	 * Transforms an array of {@link ModifierKey}s to a list of {@link KeyboardModifierKey}s.
	 * 
	 * @param keys an array of {@link ModifierKey}s
	 * @return a list of {@link KeyboardModifierKey}s
	 */
	private static List<KeyboardModifierKey> modifierKeysToKeyboardModifierKeys(ModifierKey... keys) {
		// creates result
		List<KeyboardModifierKey> result = new ArrayList<>();
		// checks if key argument is consistent
		if (keys != null) {
			// scans argument
			for (ModifierKey key : keys) {
				// checks if consistent
				if (Key.isValid(key)) {
					// adds to the result list
					result.add(key.modifier);
				}
			}
		}
		// if here, argument not consistent
		// then returns an empty list
		return result;

	}
}
