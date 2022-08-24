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

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;

/**
 * Implements an modifier key handler for an event initialization object.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class ModifiersHandler extends NativeObjectContainer {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		CTRL_KEY("ctrlKey"),
		SHIFT_KEY("shiftKey"),
		ALT_KEY("altKey"),
		META_KEY("metaKey");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use in the native object.
		 * 
		 * @param value value of property name
		 */
		private Property(String value) {
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

	}

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 */
	ModifiersHandler(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns true if the alt key was down when the mouse event was fired.
	 *
	 * @return true if the alt key was down when the mouse event was fired
	 */
	boolean isAltKey() {
		return getValue(Property.ALT_KEY, false);
	}

	/**
	 * Sets true if the alt key was down when the mouse event was fired.
	 *
	 * @param alt true if the alt key was down when the mouse event was fired
	 */
	void setAltKey(boolean alt) {
		setValue(Property.ALT_KEY, alt);
	}

	/**
	 * Returns true if the control key was down when the mouse event was fired.
	 *
	 * @return true if the control key was down when the mouse event was fired
	 */
	boolean isCtrlKey() {
		return getValue(Property.CTRL_KEY, false);
	}

	/**
	 * Sets true if the control key was down when the mouse event was fired.
	 *
	 * @param ctrl true if the control key was down when the mouse event was fired
	 */
	void setCtrlKey(boolean ctrl) {
		setValue(Property.CTRL_KEY, ctrl);
	}

	/**
	 * Returns true if the meta key was down when the mouse event was fired.
	 *
	 * @return true if the meta key was down when the mouse event was fired
	 */
	boolean isMetaKey() {
		return getValue(Property.META_KEY, false);
	}

	/**
	 * Sets true if the meta key was down when the mouse event was fired.
	 *
	 * @param meta true if the meta key was down when the mouse event was fired
	 */
	void setMetaKey(boolean meta) {
		setValue(Property.META_KEY, meta);
	}

	/**
	 * Returns true if the shift key was down when the mouse event was fired.
	 *
	 * @return true if the shift key was down when the mouse event was fired
	 */
	boolean isShiftKey() {
		return getValue(Property.SHIFT_KEY, false);
	}

	/**
	 * Sets true if the shift key was down when the mouse event was fired.
	 *
	 * @param shift true if the shift key was down when the mouse event was fired
	 */
	void setShiftKey(boolean shift) {
		setValue(Property.SHIFT_KEY, shift);
	}

}