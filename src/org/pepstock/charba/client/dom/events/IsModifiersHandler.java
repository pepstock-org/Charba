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
package org.pepstock.charba.client.dom.events;

/**
 * Mapsthe modifier keys for an event initialization object.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface IsModifiersHandler {

	/**
	 * Returns the modifier handler.
	 * 
	 * @return the modifier handler
	 */
	ModifiersHandler getModifiersHandler();

	/**
	 * Returns true if the alt key was down when the mouse event was fired.
	 *
	 * @return true if the alt key was down when the mouse event was fired
	 */
	default boolean isAltKey() {
		// checks if handler is consistent
		if (getModifiersHandler() != null) {
			return getModifiersHandler().isAltKey();
		}
		// if here, no handler
		return false;
	}

	/**
	 * Sets true if the alt key was down when the mouse event was fired.
	 *
	 * @param alt true if the alt key was down when the mouse event was fired
	 */
	default void setAltKey(boolean alt) {
		// checks if handler is consistent
		if (getModifiersHandler() != null) {
			getModifiersHandler().setAltKey(alt);
		}
	}

	/**
	 * Returns true if the control key was down when the mouse event was fired.
	 *
	 * @return true if the control key was down when the mouse event was fired
	 */
	default boolean isCtrlKey() {
		// checks if handler is consistent
		if (getModifiersHandler() != null) {
			return getModifiersHandler().isCtrlKey();
		}
		// if here, no handler
		return false;
	}

	/**
	 * Sets true if the control key was down when the mouse event was fired.
	 *
	 * @param ctrl true if the control key was down when the mouse event was fired
	 */
	default void setCtrlKey(boolean ctrl) {
		// checks if handler is consistent
		if (getModifiersHandler() != null) {
			getModifiersHandler().setCtrlKey(ctrl);
		}
	}

	/**
	 * Returns true if the meta key was down when the mouse event was fired.
	 *
	 * @return true if the meta key was down when the mouse event was fired
	 */
	default boolean isMetaKey() {
		// checks if handler is consistent
		if (getModifiersHandler() != null) {
			return getModifiersHandler().isMetaKey();
		}
		// if here, no handler
		return false;
	}

	/**
	 * Sets true if the meta key was down when the mouse event was fired.
	 *
	 * @param meta true if the meta key was down when the mouse event was fired
	 */
	default void setMetaKey(boolean meta) {
		// checks if handler is consistent
		if (getModifiersHandler() != null) {
			getModifiersHandler().setMetaKey(meta);
		}
	}

	/**
	 * Returns true if the shift key was down when the mouse event was fired.
	 *
	 * @return true if the shift key was down when the mouse event was fired
	 */
	default boolean isShiftKey() {
		// checks if handler is consistent
		if (getModifiersHandler() != null) {
			return getModifiersHandler().isShiftKey();
		}
		// if here, no handler
		return false;
	}

	/**
	 * Sets true if the shift key was down when the mouse event was fired.
	 *
	 * @param shift true if the shift key was down when the mouse event was fired
	 */
	default void setShiftKey(boolean shift) {
		// checks if handler is consistent
		if (getModifiersHandler() != null) {
			getModifiersHandler().setShiftKey(shift);
		}
	}
}