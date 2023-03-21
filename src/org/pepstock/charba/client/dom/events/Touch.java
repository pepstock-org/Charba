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

import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.NativeName;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.dom.BaseEventTarget;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Represents a single contact point on a touch-sensitive device<br>
 * The contact point is commonly a finger or stylus and the device may be a touchscreen or trackpad.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.DOM_TOUCH, namespace = JsPackage.GLOBAL)
public final class Touch {

	/**
	 * Create a touch by its initialization configuration.
	 * 
	 * @param init event initialization dictionary to configure the event
	 */
	// Ignores SonarCloud issue, java:S1172 - Unused method parameters should be removed, because this is the way of JSINTEROP to invoke a JavaScript constructor.
	@SuppressWarnings("java:S1172")
	private Touch(NativeObject init) {
		// do nothing
	}

	/**
	 * Returns a unique identifier for this touch object.<br>
	 * A given touch point (say, by a finger) will have the same identifier for the duration of its movement around the surface.<br>
	 * This lets you ensure that you're tracking the same touch all the time.
	 *
	 * @return a unique identifier for this touch object
	 */
	@JsProperty
	public native double getIdentifier();

	/**
	 * Returns the element on which the touch point started when it was first placed on the surface, even if the touch point has since moved outside the interactive area of that
	 * element or even been removed from the document.
	 *
	 * @return the element on which the touch point started when it was first placed on the surface
	 */
	@JsProperty
	public native BaseEventTarget getTarget();

	/**
	 * Returns the horizontal position of the touch on the client window of user's screen, excluding any scroll offset.
	 *
	 * @return the horizontal position of the touch on the client window of user's screen, excluding any scroll offset
	 */
	@JsProperty
	public native double getClientX();

	/**
	 * Returns the vertical position of the touch on the client window of the user's screen, excluding any scroll offset.
	 *
	 * @return the vertical position of the touch on the client window of the user's screen, excluding any scroll offset
	 */
	@JsProperty
	public native double getClientY();

	/**
	 * Returns the horizontal position of the touch on the user's screen.
	 *
	 * @return the horizontal position of the touch on the user's screen.
	 */
	@JsProperty
	public native double getScreenX();

	/**
	 * Returns the vertical position of the touch on the user's screen.
	 *
	 * @return the vertical position of the touch on the user's screen
	 */
	@JsProperty
	public native double getScreenY();

	/**
	 * Returns the horizontal position of the touch on the client window of user's screen, including any scroll offset.
	 *
	 * @return the horizontal position of the touch on the client window of user's screen, including any scroll offset
	 */
	@JsProperty
	public native double getPageX();

	/**
	 * Returns the horizontal position of the touch on the client window of user's screen, including any scroll offset.
	 *
	 * @return the horizontal position of the touch on the client window of user's screen, including any scroll offset
	 */
	@JsProperty
	public native double getPageY();

	/**
	 * Returns the X radius of the ellipse that most closely circumscribes the area of contact with the screen.<br>
	 * The value is in pixels of the same scale as screenX.
	 *
	 * @return the X radius of the ellipse that most closely circumscribes the area of contact with the screen
	 */
	@JsProperty
	public native double getRadiusX();

	/**
	 * Returns the Y radius of the ellipse that most closely circumscribes the area of contact with the screen.<br>
	 * The value is in pixels of the same scale as screenY.
	 *
	 * @return the Y radius of the ellipse that most closely circumscribes the area of contact with the screen
	 */
	@JsProperty
	public native double getRadiusY();

	/**
	 * Returns the angle (in degrees) that the ellipse described by radiusX and radiusY must be rotated, clockwise, to most accurately cover the area of contact between the user
	 * and the surface.
	 *
	 * @return the angle (in degrees) that the ellipse described by radiusX and radiusY must be rotated, clockwise, to most accurately cover the area of contact between the user
	 *         and the surface
	 */
	@JsProperty
	public native double getRotationAngle();

	/**
	 * Returns the amount of pressure being applied to the surface by the user, as a double between 0.0 (no pressure) and 1.0 (maximum pressure).
	 *
	 * @return the amount of pressure being applied to the surface by the user
	 */
	@JsProperty
	public native double getForce();

	// ----------------------
	// OVERLAY
	// ----------------------

	/**
	 * Creates new touch object with initial configuration.
	 * 
	 * @param init initial configuration of the touch
	 * @return a new touch
	 */
	@JsOverlay
	public static Touch create(TouchInit init) {
		// checks and set init
		TouchInit checked = Checker.checkAndGetIfValid(init, "Touch initialization object");
		// creates and returns object
		return new Touch(checked.nativeObject());
	}

}