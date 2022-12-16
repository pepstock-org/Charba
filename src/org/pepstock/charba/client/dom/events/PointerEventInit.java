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
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.dom.BaseHtmlElement;
import org.pepstock.charba.client.dom.enums.PointerType;

/**
 * Initialization object for a {@link NativePointerEvent}.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class PointerEventInit extends MouseEventInit {

	// default instance
	static final PointerEventInit DEFAULT_INSTANCE = new PointerEventInit();

	// default id property
	private static final int DEFAULT_ID = 0;
	// default width property
	private static final double DEFAULT_WIDTH = 1;
	// default width property
	private static final double DEFAULT_HEIGHT = 1;
	// default pressure property
	private static final double DEFAULT_PRESSURE = 0;
	// default tiltx property
	private static final double DEFAULT_TILT_X = 0;
	// default tilty property
	private static final double DEFAULT_TILT_Y = 0;
	// default pointer type property
	private static final PointerType DEFAULT_POINTER_TYPE = PointerType.UNKNOWN;
	// default primary property
	private static final boolean DEFAULT_IS_PRIMARY = false;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		POINTER_ID("pointerId"),
		WIDTH("width"),
		HEIGHT("height"),
		PRESSURE("pressure"),
		TILT_X("tiltX"),
		TILT_Y("tiltY"),
		POINTER_TYPE("pointerType"),
		IS_PRIMARY("isPrimary");

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
	 * Creates an empty object
	 */
	public PointerEventInit() {
		super();
	}

	/**
	 * Creates an object setting the pointer id by the passed argument.
	 * 
	 * @param id pointer id to set.
	 */
	public PointerEventInit(int id) {
		this();
		// stores id
		setId(id);
	}

	/**
	 * Creates an initialization object, setting the pointer id and the pointer type values.
	 * 
	 * @param id pointer id to set.
	 * @param type pointer type initialization property.
	 */
	public PointerEventInit(int id, PointerType type) {
		this(id);
		// stores type
		setPointerType(type);
	}

	/**
	 * Creates an initialization object, cloning all source event data
	 * 
	 * @param source source event to clone.
	 */
	public PointerEventInit(NativePointerEvent source) {
		super(source);
		setWidth(source.getWidth());
		setHeight(source.getHeight());
		setPointerType(source.getPointerType());
		setTiltX(source.getTiltX());
		setTiltY(source.getTiltY());
		setId(source.getId());
		setPressure(source.getPressure());
		setPrimary(source.isPrimary());
	}

	/**
	 * Creates an initialization object, cloning all source event data and new related target.
	 * 
	 * @param source source event to clone.
	 * @param relatedTarget new target of the event
	 */
	public PointerEventInit(NativePointerEvent source, BaseHtmlElement relatedTarget) {
		this(source);
		// checks if event is consistent
		Checker.checkIfValid(relatedTarget, "Target");
		// overrides target
		setRelatedTarget(relatedTarget);
	}

	/**
	 * Returns a unique identifier for the pointer causing the event.
	 * 
	 * @return a unique identifier for the pointer causing the event
	 */
	public final int getId() {
		return getValue(Property.POINTER_ID, DEFAULT_ID);
	}

	/**
	 * Sets a unique identifier for the pointer causing the event.
	 * 
	 * @param id a unique identifier for the pointer causing the event
	 */
	public final void setId(int id) {
		setValue(Property.POINTER_ID, id);
	}

	/**
	 * Returns the width (magnitude on the X axis), in CSS pixels, of the contact geometry of the pointer.
	 * 
	 * @return the width (magnitude on the X axis), in CSS pixels, of the contact geometry of the pointer
	 */
	public final double getWidth() {
		return getValue(Property.WIDTH, DEFAULT_WIDTH);
	}

	/**
	 * Sets the width (magnitude on the X axis), in CSS pixels, of the contact geometry of the pointer.
	 * 
	 * @param width the width (magnitude on the X axis), in CSS pixels, of the contact geometry of the pointer
	 */
	public final void setWidth(double width) {
		setValue(Property.WIDTH, width);
	}

	/**
	 * Returns the height (magnitude on the Y axis), in CSS pixels, of the contact geometry of the pointer.
	 * 
	 * @return the height (magnitude on the Y axis), in CSS pixels, of the contact geometry of the pointer.
	 */
	public final double getHeight() {
		return getValue(Property.HEIGHT, DEFAULT_HEIGHT);
	}

	/**
	 * Sets the height (magnitude on the Y axis), in CSS pixels, of the contact geometry of the pointer.
	 * 
	 * @param height the height (magnitude on the Y axis), in CSS pixels, of the contact geometry of the pointer.
	 */
	public final void setHeight(double height) {
		setValue(Property.HEIGHT, height);
	}

	/**
	 * Returns the normalized pressure of the pointer input in the range of 0 to 1, where 0 and 1 represent the minimum and maximum pressure the hardware is capable of detecting,
	 * respectively.
	 * 
	 * @return the normalized pressure of the pointer input in the range of 0 to 1, where 0 and 1 represent the minimum and maximum pressure the hardware is capable of detecting,
	 *         respectively.
	 */
	public final double getPressure() {
		return getValue(Property.PRESSURE, DEFAULT_PRESSURE);
	}

	/**
	 * Sets the normalized pressure of the pointer input in the range of 0 to 1, where 0 and 1 represent the minimum and maximum pressure the hardware is capable of detecting,
	 * respectively.
	 * 
	 * @param pressure the normalized pressure of the pointer input in the range of 0 to 1, where 0 and 1 represent the minimum and maximum pressure the hardware is capable of
	 *            detecting, respectively.
	 */
	public final void setPressure(double pressure) {
		setValue(Property.PRESSURE, Checker.betweenOrDefault(pressure, 0, 1, DEFAULT_PRESSURE));
	}

	/**
	 * Returns the plane angle (in degrees, in the range of -90 to 90) between the Y–Z plane and the plane containing both the pointer (e.g. pen stylus) axis and the Y axis.
	 * 
	 * @return the plane angle (in degrees, in the range of -90 to 90) between the Y–Z plane and the plane containing both the pointer (e.g. pen stylus) axis and the Y axis.
	 */
	public final double getTiltX() {
		return getValue(Property.TILT_X, DEFAULT_TILT_X);
	}

	/**
	 * Sets the plane angle (in degrees, in the range of -90 to 90) between the Y–Z plane and the plane containing both the pointer (e.g. pen stylus) axis and the Y axis.
	 * 
	 * @param tiltx the plane angle (in degrees, in the range of -90 to 90) between the Y–Z plane and the plane containing both the pointer (e.g. pen stylus) axis and the Y axis.
	 */
	public final void setTiltX(double tiltx) {
		setValue(Property.TILT_X, Checker.betweenOrDefault(tiltx, -90, 90, DEFAULT_TILT_X));
	}

	/**
	 * Returns the plane angle (in degrees, in the range of -90 to 90) between the X–Z plane and the plane containing both the pointer (e.g. pen stylus) axis and the X axis.
	 * 
	 * @return the plane angle (in degrees, in the range of -90 to 90) between the X–Z plane and the plane containing both the pointer (e.g. pen stylus) axis and the X axis.
	 */
	public final double getTiltY() {
		return getValue(Property.TILT_Y, DEFAULT_TILT_Y);
	}

	/**
	 * Sets the plane angle (in degrees, in the range of -90 to 90) between the X–Z plane and the plane containing both the pointer (e.g. pen stylus) axis and the X axis.
	 * 
	 * @param tilty the plane angle (in degrees, in the range of -90 to 90) between the X–Z plane and the plane containing both the pointer (e.g. pen stylus) axis and the X axis.
	 */
	public final void setTiltY(double tilty) {
		setValue(Property.TILT_Y, Checker.betweenOrDefault(tilty, -90, 90, DEFAULT_TILT_Y));
	}

	/**
	 * Returns the device type that caused the event (mouse, pen, touch, etc.).
	 * 
	 * @return the device type that caused the event (mouse, pen, touch, etc.)
	 */
	public final PointerType getPointerType() {
		return getValue(Property.POINTER_TYPE, PointerType.values(), DEFAULT_POINTER_TYPE);
	}

	/**
	 * Sets the device type that caused the event (mouse, pen, touch, etc.).
	 * 
	 * @param pointerType the device type that caused the event (mouse, pen, touch, etc.)
	 */
	public final void setPointerType(PointerType pointerType) {
		setValue(Property.POINTER_TYPE, pointerType);
	}

	/**
	 * Returns <code>true</code> if the pointer represents the primary pointer of this pointer type.
	 * 
	 * @return <code>true</code> if the pointer represents the primary pointer of this pointer type
	 */
	public final boolean isPrimary() {
		return getValue(Property.IS_PRIMARY, DEFAULT_IS_PRIMARY);
	}

	/**
	 * Sets <code>true</code> if the pointer represents the primary pointer of this pointer type.
	 * 
	 * @param isPrimary <code>true</code> if the pointer represents the primary pointer of this pointer type
	 */
	public final void setPrimary(boolean isPrimary) {
		setValue(Property.IS_PRIMARY, isPrimary);
	}

}