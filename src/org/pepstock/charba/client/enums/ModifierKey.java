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

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.dom.BaseNativeEvent;
import org.pepstock.charba.client.dom.DOMBuilder;
import org.pepstock.charba.client.dom.elements.Div;
import org.pepstock.charba.client.events.AbstractEvent;
import org.pepstock.charba.client.zoom.ZoomPlugin;

/**
 * A modifier key modifies the action of another key when the keys are pressed at the same time.<br>
 * Below is a list of the common modifier keys to use to configure the {@link ZoomPlugin}.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum ModifierKey implements Key
{
	/**
	 * Used in combination with the numeric keypad for entering <b>Alt</b> codes, which output special characters;
	 */
	ALT("alt", event -> event.isAltKey()),
	/**
	 * Used for entering keyboard shortcuts.
	 */
	CTRL("ctrl", event -> event.isCtrlKey()),
	/**
	 * Used for entering keyboard shortcuts.
	 */
	META("meta", event -> event.isMetaKey()),
	/**
	 * Used for capitalizing letters and entering different types of symbols.
	 */
	SHIFT("shift", event -> event.isShiftKey());

	// CSS inline to show the modifier key by element
	private static final String CSS = "background: linear-gradient(180deg,#eee,#fff); background-color: rgba(0, 0, 0, 0); background-color: #eee; border: 1px solid #cdd5d7; border-radius: 6px; box-shadow: 0 1px 2px 1px #cdd5d7; "
			+ "font-family: consolas,courier,monospace; font-size: .9rem; font-weight: 700; line-height: 1; margin: 3px; padding: 4px 6px; white-space: nowrap; color: black;";
	// name value of property
	private final String value;
	// instance of modifier checker
	private final ModifierKeyPressChecker checker;
	// creates div element
	private Div element = null;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 * @param checker instance of {@link ModifierKeyPressChecker}
	 */
	private ModifierKey(String value, ModifierKeyPressChecker checker) {
		this.value = value;
		this.checker = checker;
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
	 * Returns the {@link Div} element which describes the modifier key.<br>
	 * It can be used in UI if needed.
	 * 
	 * @return the {@link Div} element which describes the modifier key
	 */
	public Div getElement() {
		// checks if element has been instantiated
		if (element == null) {
			// creates the div with kdb inside
			element = DOMBuilder.get().createDivElement();
			element.setInnerHTML("<kbd style=\"" + CSS + "\">" + value() + "</kbd>");
		}
		return element;
	}

	/**
	 * Returns <code>true</code> if the modifier key is pressed when the event was emitted.
	 * 
	 * @param event instance of CHARBA event to be checked
	 * @return <code>true</code> if the modifier key is pressed when the event was emitted
	 */
	public boolean isPressed(AbstractEvent event) {
		// checks if event is consistent
		if (event != null) {
			return isPressed(event.getNativeEvent());
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
	public boolean isPressed(BaseNativeEvent event) {
		// checks if event is consistent
		if (event != null) {
			return checker.isPressed(event);
		}
		// if here, argument is not consistent
		// then returns false
		return false;
	}

	/**
	 * Returns <code>true</code> if all modifier keys are pressed when the event was emitted.
	 * 
	 * @param event instance of CHARBA event to be checked
	 * @param keys array of keys to be checked against the event
	 * @return <code>true</code> if the modifier key is pressed when the event was emitted
	 */
	public static boolean arePressed(AbstractEvent event, ModifierKey... keys) {
		// checks if event is consistent
		if (event != null) {
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
	public static boolean arePressed(BaseNativeEvent event, ModifierKey... keys) {
		// checks if event and keys are consistent
		if (event != null && keys != null && keys.length > 0) {
			// creates result instance
			boolean result = true;
			// scans keys
			for (ModifierKey key : keys) {
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
	 * Interface to invoke by a {@link BaseNativeEvent} in order to know if the modifier has been pressed on the event.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private interface ModifierKeyPressChecker {

		/**
		 * Returns <code>true</code> if the modifier key is pressed when the event was emitted.
		 * 
		 * @param event instance of native event to be checked
		 * @return <code>true</code> if the modifier key is pressed when the event was emitted
		 */
		boolean isPressed(BaseNativeEvent event);

	}

}
