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

import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.dom.BaseHtmlElement;
import org.pepstock.charba.client.items.Undefined;

/**
 * Initialization object for a {@link Touch}.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class TouchInit extends NativeObjectContainer {

	// default client properties
	private static final double DEFAULT_CLIENT_X = 0;
	private static final double DEFAULT_CLIENT_Y = 0;
	// default screen properties
	private static final double DEFAULT_SCREEN_X = 0;
	private static final double DEFAULT_SCREEN_Y = 0;
	// default page properties
	private static final double DEFAULT_PAGE_X = 0;
	private static final double DEFAULT_PAGE_Y = 0;
	// default radius properties
	private static final double DEFAULT_RADIUS_X = 0;
	private static final double DEFAULT_RADIUS_Y = 0;
	// default rotation angle
	private static final double DEFAULT_ROTATION_ANGLE = 0;
	// default force
	private static final double DEFAULT_FORCE = 0;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		CLIENT_X("clientX"),
		CLIENT_Y("clientY"),
		FORCE("force"),
		IDENTIFIER("identifier"),
		PAGE_X("pageX"),
		PAGE_Y("pageY"),
		RADIUS_X("radiusX"),
		RADIUS_Y("radiusY"),
		ROTATION_ANGLE("rotationAngle"),
		SCREEN_X("screenX"),
		SCREEN_Y("screenY"),
		TARGET("target");

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
	 * Creates an object with identifier and target, both mandatory.
	 * 
	 * @param identifier a unique identifier for this touch object
	 * @param target the element on which the touch point started when it was first placed on the surface
	 */
	public TouchInit(double identifier, BaseHtmlElement target) {
		super();
		// checks if target is consistent
		setTarget(Checker.checkAndGetIfValid(target, "Target"));
		setIdentifier(Checker.validOrZero(identifier));
	}

	/**
	 * Returns a unique identifier for this touch object.<br>
	 * A given touch point (say, by a finger) will have the same identifier for the duration of its movement around the surface.<br>
	 * This lets you ensure that you're tracking the same touch all the time.
	 *
	 * @return a unique identifier for this touch object
	 */
	public double getIdentifier() {
		return getValue(Property.IDENTIFIER, Undefined.DOUBLE);
	}

	/**
	 * Sets a unique identifier for this touch object.<br>
	 * A given touch point (say, by a finger) will have the same identifier for the duration of its movement around the surface.<br>
	 * This lets you ensure that you're tracking the same touch all the time.
	 *
	 * @param identifier a unique identifier for this touch object
	 */
	public void setIdentifier(double identifier) {
		setValue(Property.IDENTIFIER, identifier);
	}

	/**
	 * Returns the element on which the touch point started when it was first placed on the surface, even if the touch point has since moved outside the interactive area of that
	 * element or even been removed from the document.
	 *
	 * @return the element on which the touch point started when it was first placed on the surface
	 */
	public BaseHtmlElement getTarget() {
		return getElement(Property.TARGET);
	}

	/**
	 * Sets the element on which the touch point started when it was first placed on the surface, even if the touch point has since moved outside the interactive area of that
	 * element or even been removed from the document.
	 *
	 * @param target the element on which the touch point started when it was first placed on the surface
	 */
	public void setTarget(BaseHtmlElement target) {
		setElement(Property.TARGET, target);
	}

	/**
	 * Returns the horizontal position of the touch on the client window of user's screen, excluding any scroll offset.
	 *
	 * @return the horizontal position of the touch on the client window of user's screen, excluding any scroll offset
	 */
	public double getClientX() {
		return getValue(Property.CLIENT_X, DEFAULT_CLIENT_X);
	}

	/**
	 * Sets the horizontal position of the touch on the client window of user's screen, excluding any scroll offset.
	 *
	 * @param clientX the horizontal position of the touch on the client window of user's screen, excluding any scroll offset
	 */
	public void setClientX(double clientX) {
		setValue(Property.CLIENT_X, clientX);
	}

	/**
	 * Returns the vertical position of the touch on the client window of the user's screen, excluding any scroll offset.
	 *
	 * @return the vertical position of the touch on the client window of the user's screen, excluding any scroll offset
	 */
	public double getClientY() {
		return getValue(Property.CLIENT_Y, DEFAULT_CLIENT_Y);
	}

	/**
	 * Returns the vertical position of the touch on the client window of the user's screen, excluding any scroll offset.
	 *
	 * @param clientY the vertical position of the touch on the client window of the user's screen, excluding any scroll offset
	 */
	public void setClientY(double clientY) {
		setValue(Property.CLIENT_Y, clientY);
	}

	/**
	 * Returns the horizontal position of the touch on the user's screen.
	 *
	 * @return the horizontal position of the touch on the user's screen.
	 */
	public double getScreenX() {
		return getValue(Property.SCREEN_X, DEFAULT_SCREEN_X);
	}

	/**
	 * Setss the horizontal position of the touch on the user's screen.
	 *
	 * @param screenX the horizontal position of the touch on the user's screen.
	 */
	public void setScreenX(double screenX) {
		setValue(Property.SCREEN_Y, screenX);
	}

	/**
	 * Returns the vertical position of the touch on the user's screen.
	 *
	 * @return the vertical position of the touch on the user's screen
	 */
	public double getScreenY() {
		return getValue(Property.SCREEN_Y, DEFAULT_SCREEN_Y);
	}

	/**
	 * Sets the vertical position of the touch on the user's screen.
	 *
	 * @param screenY the vertical position of the touch on the user's screen
	 */
	public void setScreenY(double screenY) {
		setValue(Property.SCREEN_Y, screenY);
	}

	/**
	 * Returns the horizontal position of the touch on the client window of user's screen, including any scroll offset.
	 *
	 * @return the horizontal position of the touch on the client window of user's screen, including any scroll offset
	 */
	public double getPageX() {
		return getValue(Property.PAGE_X, DEFAULT_PAGE_X);
	}

	/**
	 * Sets the horizontal position of the touch on the client window of user's screen, including any scroll offset.
	 *
	 * @param pageX the horizontal position of the touch on the client window of user's screen, including any scroll offset
	 */
	public void setPageX(double pageX) {
		setValue(Property.PAGE_X, pageX);
	}

	/**
	 * Returns the horizontal position of the touch on the client window of user's screen, including any scroll offset.
	 *
	 * @return the horizontal position of the touch on the client window of user's screen, including any scroll offset
	 */
	public double getPageY() {
		return getValue(Property.PAGE_Y, DEFAULT_PAGE_Y);
	}

	/**
	 * Sets the horizontal position of the touch on the client window of user's screen, including any scroll offset.
	 *
	 * @param pageY the horizontal position of the touch on the client window of user's screen, including any scroll offset
	 */
	public void setPageY(double pageY) {
		setValue(Property.PAGE_Y, pageY);
	}

	/**
	 * Returns the radius of the ellipse which most closely circumscribes the touching area (e.g. finger, stylus) along the axis indicated by rotationAngle.
	 *
	 * @return the radius of the ellipse which most closely circumscribes the touching area (e.g. finger, stylus) along the axis indicated by rotationAngle
	 */
	public double getRadiusX() {
		return getValue(Property.RADIUS_X, DEFAULT_RADIUS_X);
	}

	/**
	 * Sets the radius of the ellipse which most closely circumscribes the touching area (e.g. finger, stylus) along the axis indicated by rotationAngle.
	 *
	 * @param radiusX the radius of the ellipse which most closely circumscribes the touching area (e.g. finger, stylus) along the axis indicated by rotationAngle
	 */
	public void setRadiusX(double radiusX) {
		setValue(Property.RADIUS_X, Checker.positiveOrDefault(radiusX, DEFAULT_RADIUS_X));
	}

	/**
	 * Returns the radius of the ellipse which most closely circumscribes the touching area (e.g. finger, stylus) along the axis perpendicular to that indicated by rotationAngle.
	 *
	 * @return the radius of the ellipse which most closely circumscribes the touching area (e.g. finger, stylus) along the axis perpendicular to that indicated by rotationAngle
	 */
	public double getRadiusY() {
		return getValue(Property.RADIUS_Y, DEFAULT_RADIUS_Y);
	}

	/**
	 * Sets the radius of the ellipse which most closely circumscribes the touching area (e.g. finger, stylus) along the axis perpendicular to that indicated by rotationAngle.
	 *
	 * @param radiusY the radius of the ellipse which most closely circumscribes the touching area (e.g. finger, stylus) along the axis perpendicular to that indicated by
	 *            rotationAngle
	 */
	public void setRadiusY(double radiusY) {
		setValue(Property.RADIUS_Y, Checker.positiveOrDefault(radiusY, DEFAULT_RADIUS_Y));
	}

	/**
	 * Returns the angle (in degrees) that the ellipse described by radiusX and radiusY must be rotated, clockwise, to most accurately cover the area of contact between the user
	 * and the surface.
	 *
	 * @return the angle (in degrees) that the ellipse described by radiusX and radiusY must be rotated, clockwise, to most accurately cover the area of contact between the user
	 *         and the surface
	 */
	public double getRotationAngle() {
		return getValue(Property.ROTATION_ANGLE, DEFAULT_ROTATION_ANGLE);
	}

	/**
	 * Sets the angle (in degrees) that the ellipse described by radiusX and radiusY must be rotated, clockwise, to most accurately cover the area of contact between the user and
	 * the surface.<br>
	 * The value must be greater than or equal to 0 and less than 90.
	 *
	 * @param rotation the angle (in degrees) that the ellipse described by radiusX and radiusY must be rotated, clockwise, to most accurately cover the area of contact between the
	 *            user and the surface.<br>
	 *            The value must be greater than or equal to 0 and less than 90
	 */
	public void setRotationAngle(double rotation) {
		setValue(Property.ROTATION_ANGLE, Checker.betweenOrDefault(rotation, 0, 90, DEFAULT_ROTATION_ANGLE));
	}

	/**
	 * Returns the amount of pressure being applied to the surface by the user, as a double between 0.0 (no pressure) and 1.0 (maximum pressure).
	 *
	 * @return the amount of pressure being applied to the surface by the user
	 */
	public double getForce() {
		return getValue(Property.FORCE, DEFAULT_FORCE);
	}

	/**
	 * Sets the amount of pressure being applied to the surface by the user, as a double between 0.0 (no pressure) and 1.0 (maximum pressure).
	 *
	 * @param force the amount of pressure being applied to the surface by the user
	 */
	public void setForce(double force) {
		setValue(Property.FORCE, Checker.betweenOrDefault(force, 0, 1, DEFAULT_FORCE));
	}

	/**
	 * Returns the native object instance.
	 * 
	 * @return the native object instance.
	 */
	final NativeObject nativeObject() {
		return getNativeObject();
	}
}