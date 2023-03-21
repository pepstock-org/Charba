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
import org.pepstock.charba.client.dom.enums.TouchEventType;
import org.pepstock.charba.client.options.IsEvent;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Represents an {@link NativeUIEvent} which is sent when the state of contacts with a touch-sensitive surface changes.<br>
 * This surface can be a touch screen or trackpad, for example.<br>
 * The event can describe one or more points of contact with the screen and includes support for detecting movement, addition and removal of contact points, and so forth.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.DOM_TOUCH_EVENT, namespace = JsPackage.GLOBAL)
public final class NativeTouchEvent extends NativeUIEvent {

	/**
	 * Create a touch event by its type and initialization configuration.
	 * 
	 * @param type type of the touch event
	 * @param init event initialization dictionary to configure the event
	 */
	// Ignores SonarCloud issue, java:S1172 - Unused method parameters should be removed, because this is the way of JSINTEROP to invoke a JavaScript constructor.
	@SuppressWarnings("java:S1172")
	NativeTouchEvent(String type, NativeObject init) {
		// do nothing
	}

	/**
	 * Returns true if the alt key was down when the touch event was fired.
	 *
	 * @return true if the alt key was down when the touch event was fired
	 */
	@JsProperty
	public native boolean isAltKey();

	/**
	 * Returns true if the control key was down when the touch event was fired.
	 *
	 * @return true if the control key was down when the touch event was fired
	 */
	@JsProperty
	public native boolean isCtrlKey();

	/**
	 * Returns true if the meta key was down when the touch event was fired.
	 *
	 * @return true if the meta key was down when the touch event was fired
	 */
	@JsProperty
	public native boolean isMetaKey();

	/**
	 * Returns true if the shift key was down when the touch event was fired.
	 *
	 * @return true if the shift key was down when the touch event was fired
	 */
	@JsProperty
	public native boolean isShiftKey();

	/**
	 * Returns a list of all the touch objects representing individual points of contact whose states changed between the previous touch event and this one.
	 * 
	 * @return a list of all the touch objects representing individual points of contact whose states changed between the previous touch event and this one
	 */
	@JsProperty
	public native TouchList getChangedTouches();

	/**
	 * Returns a list of all the touch objects that are both currently in contact with the touch surface and were also started on the same element that is the target of the event.
	 * 
	 * @return a list of all the touch objects that are both currently in contact with the touch surface and were also started on the same element that is the target of the event
	 */
	@JsProperty
	public native TouchList getTargetTouches();

	/**
	 * Returns a list of all the touch objects representing all current points of contact with the surface, regardless of target or changed status.
	 * 
	 * @return a list of all the touch objects representing all current points of contact with the surface, regardless of target or changed status
	 */
	@JsProperty
	public native TouchList getTouches();

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
	public static NativeTouchEvent createTouchEvent(String type) {
		return createTouchEvent(type, TouchEventInit.DEFAULT_INSTANCE);
	}

	/**
	 * Creates a new event object for a specific event type.
	 * 
	 * @param type type of event
	 * @return a new event object for a specific event type
	 */
	@JsOverlay
	public static NativeTouchEvent createTouchEvent(IsEvent type) {
		return createTouchEvent(type, TouchEventInit.DEFAULT_INSTANCE);
	}

	/**
	 * Creates a new event object for a specific event type with initial configuration.
	 * 
	 * @param type type of event
	 * @param init initial configuration of the event
	 * @return a new event object for a specific event type
	 */
	@JsOverlay
	public static NativeTouchEvent createTouchEvent(IsEvent type, TouchEventInit init) {
		return createTouchEvent(Key.checkAndGetIfValid(type).value(), init);
	}

	/**
	 * Creates a new event object for a specific event type with initial configuration.
	 * 
	 * @param type type of event
	 * @param init initial configuration of the event
	 * @return a new event object for a specific event type
	 */
	@JsOverlay
	public static NativeTouchEvent createTouchEvent(String type, TouchEventInit init) {
		return new NativeTouchEvent(type, NativeBaseEvent.checkAndGetInit(type, init, TouchEventInit.DEFAULT_INSTANCE, TouchEventType.values()));
	}

}