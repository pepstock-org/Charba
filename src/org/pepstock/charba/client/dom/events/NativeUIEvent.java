/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.dom.events;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeName;
import org.pepstock.charba.client.dom.enums.KeyboardModifierKey;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Represents simple user interface events, in DOM.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.DOM_UI_EVENT, namespace = JsPackage.GLOBAL)
public abstract class NativeUIEvent extends NativeBaseEvent {

	/**
	 * To avoid any instantiation
	 */
	NativeUIEvent() {
		// do nothing
	}

	// ----------------------
	// PROPERTIES
	// ----------------------

	/**
	 * When non-zero, provides the current (or next, depending on the event) click count.<br>
	 * For click events, is the current click count.<br>
	 * For mousedown or mouseup events, is 1 plus the current click count.<br>
	 * For all other, is always zero.
	 * 
	 * @return When non-zero, provides the current (or next, depending on the event) click count.<br>
	 *         For click events, is the current click count.<br>
	 *         For mousedown or mouseup events, is 1 plus the current click count.<br>
	 *         For all other, is always zero
	 */
	@JsProperty
	public final native int getDetail();

	/**
	 * Returns the current state of the specified modifier key: <code>true</code> if the modifier is active (that is the modifier key is pressed or locked), otherwise,
	 * <code>false</code>.
	 *
	 * @param key A modifier key value. The value must be one of the {@link KeyboardModifierKey} values which represent modifier keys.
	 * @return the current state of the specified modifier key: <code>true</code> if the modifier is active (that is the modifier key is pressed or locked), otherwise,
	 *         <code>false</code>
	 */
	@JsMethod(name = "getModifierState")
	final native boolean getNativeModifierState(String key);

	/**
	 * Returns the current state of the specified modifier key: <code>true</code> if the modifier is active (that is the modifier key is pressed or locked), otherwise,
	 * <code>false</code>.
	 *
	 * @param modifier a modifier key
	 * @return the current state of the specified modifier key: <code>true</code> if the modifier is active (that is the modifier key is pressed or locked), otherwise,
	 *         <code>false</code>
	 */
	@JsOverlay
	public final boolean getModifierState(KeyboardModifierKey modifier) {
		// checks if the argument is consistent
		// and if the event has modifier key method.
		if (Key.isValid(modifier) && (this instanceof NativeKeyboardEvent || this instanceof NativeAbstractMouseEvent || this instanceof NativeTouchEvent)) {
			// invokes modifier key method
			return getNativeModifierState(modifier.value());
		}
		// if here, argument not consistent or invalid event type
		// then returns false
		return false;
	}

}