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
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.dom.enums.MouseEventType;
import org.pepstock.charba.client.options.IsEvent;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * Represents a mouse events that occur due to the user interacting with a pointing device.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.DOM_MOUSE_EVENT, namespace = JsPackage.GLOBAL)
public final class NativeMouseEvent extends NativeAbstractMouseEvent {

	/**
	 * Create a mouse event by its type and initialization configuration.
	 * 
	 * @param type type of the mouse event
	 * @param init event initialization dictionary to configure the event
	 */
	// Ignores SonarCloud issue, java:S1172 - Unused method parameters should be removed, because this is the way of JSINTEROP to invoke a JavaScript constructor.
	@SuppressWarnings("java:S1172")
	NativeMouseEvent(String type, NativeObject init) {
		// do nothing
	}

	// ----------------------
	// OVERLAY
	// ----------------------

	/**
	 * Creates a new event object for a specific event type.
	 * 
	 * @param type type of event
	 * @return a new event object for a specific event type
	 */
	@JsOverlay
	public static NativeMouseEvent createMouseEvent(String type) {
		return createMouseEvent(type, MouseEventInit.DEFAULT_INSTANCE);
	}

	/**
	 * Creates a new event object for a specific event type.
	 * 
	 * @param type type of event
	 * @return a new event object for a specific event type
	 */
	@JsOverlay
	public static NativeMouseEvent createMouseEvent(IsEvent type) {
		return createMouseEvent(type, MouseEventInit.DEFAULT_INSTANCE);
	}

	/**
	 * Creates a new event object for a specific event type with initial configuration.
	 * 
	 * @param type type of event
	 * @param init initial configuration of the event
	 * @return a new event object for a specific event type
	 */
	@JsOverlay
	public static NativeMouseEvent createMouseEvent(IsEvent type, MouseEventInit init) {
		return createMouseEvent(Key.checkAndGetIfValid(type).value(), init);
	}

	/**
	 * Creates a new event object for a specific event type with initial configuration.
	 * 
	 * @param type type of event
	 * @param init initial configuration of the event
	 * @return a new event object for a specific event type
	 */
	@JsOverlay
	public static NativeMouseEvent createMouseEvent(String type, MouseEventInit init) {
		return new NativeMouseEvent(type, NativeBaseEvent.checkAndGetInit(type, init, MouseEventInit.DEFAULT_INSTANCE, MouseEventType.values()));
	}

}