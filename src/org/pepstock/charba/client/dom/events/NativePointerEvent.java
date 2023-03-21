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
import org.pepstock.charba.client.dom.enums.PointerEventType;
import org.pepstock.charba.client.dom.enums.PointerType;
import org.pepstock.charba.client.options.IsEvent;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Much of today's web content assumes the user's pointing device will be a mouse.<br>
 * However, since many devices support other types of pointing input devices, such as pen/stylus and touch surfaces, extensions to the existing pointing device event models are
 * needed.<br>
 * Pointer events address that need.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.DOM_POINTER_EVENT, namespace = JsPackage.GLOBAL)
public final class NativePointerEvent extends NativeAbstractMouseEvent {

	/**
	 * Create a pointer event by its type and initialization configuration.
	 * 
	 * @param type type of the pointer event
	 * @param init event initialization dictionary to configure the event
	 */
	// Ignores SonarCloud issue, java:S1172 - Unused method parameters should be removed, because this is the way of JSINTEROP to invoke a JavaScript constructor.
	@SuppressWarnings("java:S1172")
	NativePointerEvent(String type, NativeObject init) {
		// do nothing
	}

	/**
	 * Returns a unique identifier for the pointer causing the event.
	 * 
	 * @return a unique identifier for the pointer causing the event
	 */
	@JsProperty(name = "pointerId")
	public native int getId();

	/**
	 * Returns the width (magnitude on the X axis), in CSS pixels, of the contact geometry of the pointer.
	 * 
	 * @return the width (magnitude on the X axis), in CSS pixels, of the contact geometry of the pointer
	 */
	@JsProperty
	public native double getWidth();

	/**
	 * Returns the height (magnitude on the Y axis), in CSS pixels, of the contact geometry of the pointer.
	 * 
	 * @return the height (magnitude on the Y axis), in CSS pixels, of the contact geometry of the pointer.
	 */
	@JsProperty
	public native double getHeight();

	/**
	 * Returns the normalized pressure of the pointer input in the range of 0 to 1, where 0 and 1 represent the minimum and maximum pressure the hardware is capable of detecting,
	 * respectively.
	 * 
	 * @return the normalized pressure of the pointer input in the range of 0 to 1, where 0 and 1 represent the minimum and maximum pressure the hardware is capable of detecting,
	 *         respectively.
	 */
	@JsProperty
	public native double getPressure();

	/**
	 * Returns the plane angle (in degrees, in the range of -90 to 90) between the Y–Z plane and the plane containing both the pointer (e.g. pen stylus) axis and the Y axis.
	 * 
	 * @return the plane angle (in degrees, in the range of -90 to 90) between the Y–Z plane and the plane containing both the pointer (e.g. pen stylus) axis and the Y axis.
	 */
	@JsProperty
	public native double getTiltX();

	/**
	 * Returns the plane angle (in degrees, in the range of -90 to 90) between the X–Z plane and the plane containing both the pointer (e.g. pen stylus) axis and the X axis.
	 * 
	 * @return the plane angle (in degrees, in the range of -90 to 90) between the X–Z plane and the plane containing both the pointer (e.g. pen stylus) axis and the X axis.
	 */
	@JsProperty
	public native double getTiltY();

	/**
	 * Returns the device type that caused the event (mouse, pen, touch, etc.).
	 * 
	 * @return the device type that caused the event (mouse, pen, touch, etc.)
	 */
	@JsProperty(name = "pointerType")
	private native String getNativePointType();

	/**
	 * Returns <code>true</code> if the pointer represents the primary pointer of this pointer type.
	 * 
	 * @return <code>true</code> if the pointer represents the primary pointer of this pointer type
	 */
	@JsProperty(name = "isPrimary")
	public native boolean isPrimary();

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
	public static NativePointerEvent createPointerEvent(String type) {
		return createPointerEvent(type, PointerEventInit.DEFAULT_INSTANCE);
	}

	/**
	 * Creates a new event object for a specific event type.
	 * 
	 * @param type type of event
	 * @return a new event object for a specific event type
	 */
	@JsOverlay
	public static NativePointerEvent createPointerEvent(IsEvent type) {
		return createPointerEvent(type, PointerEventInit.DEFAULT_INSTANCE);
	}

	/**
	 * Creates a new event object for a specific event type with initial configuration.
	 * 
	 * @param type type of event
	 * @param init initial configuration of the event
	 * @return a new event object for a specific event type
	 */
	@JsOverlay
	public static NativePointerEvent createPointerEvent(IsEvent type, PointerEventInit init) {
		return createPointerEvent(Key.checkAndGetIfValid(type).value(), init);
	}

	/**
	 * Creates a new event object for a specific event type with initial configuration.
	 * 
	 * @param type type of event
	 * @param init initial configuration of the event
	 * @return a new event object for a specific event type
	 */
	@JsOverlay
	public static NativePointerEvent createPointerEvent(String type, PointerEventInit init) {
		return new NativePointerEvent(type, NativeBaseEvent.checkAndGetInit(type, init, PointerEventInit.DEFAULT_INSTANCE, PointerEventType.values()));
	}

	/**
	 * Returns the device type that caused the event (mouse, pen, touch, etc.).
	 * 
	 * @return the device type that caused the event (mouse, pen, touch, etc.)
	 */
	@JsOverlay
	public PointerType getPointerType() {
		return Key.getKeyByValue(PointerType.values(), getNativePointType(), PointerType.UNKNOWN);
	}
}