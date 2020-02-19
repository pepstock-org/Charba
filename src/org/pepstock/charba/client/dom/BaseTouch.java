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
package org.pepstock.charba.client.dom;

import org.pepstock.charba.client.commons.NativeName;

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
public final class BaseTouch {

	/**
	 * To avoid any instantiation
	 */
	private BaseTouch() {
		// do nothing
	}

	/**
	 * Returns the X coordinate of the touch point relative to the left edge of the browser viewport, not including any scroll offset.
	 *
	 * @return the X coordinate of the touch point relative to the left edge of the browser viewport, not including any scroll offset
	 */
	@JsProperty
	public native double getClientX();

	/**
	 * Returns the Y coordinate of the touch point relative to the top edge of the browser viewport, not including any scroll offset.
	 *
	 * @return the Y coordinate of the touch point relative to the top edge of the browser viewport, not including any scroll offset
	 */
	@JsProperty
	public native double getClientY();

	/**
	 * Returns the amount of pressure being applied to the surface by the user, as a double between 0.0 (no pressure) and 1.0 (maximum pressure).
	 *
	 * @return the amount of pressure being applied to the surface by the user
	 */
	@JsProperty
	public native double getForce();

	/**
	 * Returns a unique identifier for this touch object.<br>
	 * A given touch point (say, by a finger) will have the same identifier for the duration of its movement around the surface.<br>
	 * This lets you ensure that you're tracking the same touch all the time.
	 *
	 * @return a unique identifier for this touch object
	 */
	@JsProperty
	public native int getIdentifier();

	/**
	 * Returns the X coordinate of the touch point relative to the left edge of the document.<br>
	 * Unlike clientX, this value includes the horizontal scroll offset, if any.
	 *
	 * @return the X coordinate of the touch point relative to the left edge of the document
	 */
	@JsProperty
	public native double getPageX();

	/**
	 * Returns the Y coordinate of the touch point relative to the top of the document.<br>
	 * Unlike clientY, this value includes the vertical scroll offset, if any.
	 *
	 * @return the Y coordinate of the touch point relative to the top of the document
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
	 * Returns the X coordinate of the touch point relative to the left edge of the screen.
	 *
	 * @return the X coordinate of the touch point relative to the left edge of the screen
	 */
	@JsProperty
	public native double getScreenX();

	/**
	 * Returns the Y coordinate of the touch point relative to the top edge of the screen.
	 *
	 * @return the Y coordinate of the touch point relative to the top edge of the screen
	 */
	@JsProperty
	public native double getScreenY();

	/**
	 * Returns the element on which the touch point started when it was first placed on the surface, even if the touch point has since moved outside the interactive area of that
	 * element or even been removed from the document.
	 *
	 * @return the element on which the touch point started when it was first placed on the surface
	 */
	@JsProperty
	public native BaseEventTarget getTarget();

}
