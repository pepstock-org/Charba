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
package org.pepstock.charba.client.annotation;

import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.items.Undefined;
import org.pepstock.charba.client.utils.Utilities;

/**
 * This object is to set the control point of a line annotation, when set as curve.<br>
 * If curve is enabled, it configures the control point to drawn the curve, calculated in pixels.<br>
 * It can be set by a string in percentage format "number%" which are representing the percentage of the distance between the start and end point from the center.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class ControlPoint extends AbstractNode {

	/**
	 * Maximum value as percentage, <b>{@value}</b>
	 */
	public static final double MINIMUN_PERCENTAGE = -1000D;

	/**
	 * Maximum value as percentage, <b>{@value}</b>
	 */
	public static final double MAXIMUN_PERCENTAGE = 1000D;

	/**
	 * Default of X value as percentage, <b>{@value}</b>
	 */
	public static final double DEFAULT_X_PERCENTAGE = 0D;

	/**
	 * Default of Y value as percentage, <b>{@value}</b>
	 */
	public static final double DEFAULT_Y_PERCENTAGE = -0.5D;

	// default of X value as percentage string
	private static final String DEFAULT_X_PERCENTAGE_STRING = "0%";

	// default of Y value as percentage string
	private static final String DEFAULT_Y_PERCENTAGE_STRING = "-50%";

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		X("x"),
		Y("y");

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
	 * Creates an object with default.
	 */
	public ControlPoint() {
		this(Undefined.DOUBLE);
	}

	/**
	 * Creates an object with X value in pixels.
	 * 
	 * @param x the X value of the control point in pixels
	 */
	public ControlPoint(double x) {
		this(x, Undefined.DOUBLE);
	}

	/**
	 * Creates an object with X and Y values in pixels.
	 * 
	 * @param x the X value of the control point in pixels
	 * @param y the Y value of the control point in pixels
	 */
	public ControlPoint(double x, double y) {
		this(null, null, null);
		// checks if value is consistent
		if (Undefined.isNot(x)) {
			// stores value
			setX(x);
		}
		// checks if value is consistent
		if (Undefined.isNot(y)) {
			// stores value
			setY(y);
		} else {
			// stores defaults
			setYAsPercentage(DEFAULT_Y_PERCENTAGE);
		}
	}

	/**
	 * Creates an object with X value as percentage.
	 * 
	 * @param x the X value of the control point as percentage
	 */
	public ControlPoint(String x) {
		this(x, DEFAULT_Y_PERCENTAGE_STRING);
	}

	/**
	 * Creates an object with X and Y values as percentage.
	 * 
	 * @param x the X value of the control point as percentage
	 * @param y the Y value of the control point as percentage
	 */
	public ControlPoint(String x, String y) {
		this(null, null, null);
		// stores values
		setX(x);
		setY(y);
	}

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param parent parent node to use to add this element where changed
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param nativeObject native object to map java script properties
	 */
	ControlPoint(AbstractNode parent, Key childKey, NativeObject nativeObject) {
		super(parent, childKey, nativeObject);
	}

	/**
	 * Sets the X value of the control point in pixels.
	 * 
	 * @param x the X value of the control point in pixels
	 */
	public void setX(double x) {
		setValueAndAddToParent(Property.X, x);
	}

	/**
	 * Sets the Y value of the control point in pixels.
	 * 
	 * @param y the Y value of the control point in pixels
	 */
	public void setY(double y) {
		setValueAndAddToParent(Property.Y, y);
	}

	/**
	 * Sets the X value of the control point as percentage.
	 * 
	 * @param x the X value of the control point as percentage
	 */
	public void setX(String x) {
		setXAsPercentage(Utilities.getAsPercentage(x, DEFAULT_X_PERCENTAGE));
	}

	/**
	 * Sets the Y value of the control point as percentage.
	 * 
	 * @param y the Y value of the control point as percentage
	 */
	public void setY(String y) {
		setYAsPercentage(Utilities.getAsPercentage(y, DEFAULT_Y_PERCENTAGE));
	}

	/**
	 * Sets the X value of the control point as percentage.
	 * 
	 * @param percentage the X value of the control point as percentage
	 */
	public void setXAsPercentage(double percentage) {
		setValueAndAddToParent(Property.X, Utilities.getAsPercentage(percentage, MINIMUN_PERCENTAGE, MAXIMUN_PERCENTAGE, DEFAULT_X_PERCENTAGE));
	}

	/**
	 * Sets the Y value of the control point as percentage.
	 * 
	 * @param percentage the Y value of the control point as percentage
	 */
	public void setYAsPercentage(double percentage) {
		setValueAndAddToParent(Property.Y, Utilities.getAsPercentage(percentage, MINIMUN_PERCENTAGE, MAXIMUN_PERCENTAGE, DEFAULT_Y_PERCENTAGE));
	}

	/**
	 * Returns the X value of the control point in pixels.
	 * 
	 * @return the X value of the control point in pixels.
	 */
	public double getX() {
		return getValue(Property.X, Undefined.DOUBLE);
	}

	/**
	 * Returns the Y value of the control point in pixels.
	 * 
	 * @return the Y value of the control point in pixels.
	 */
	public double getY() {
		return getValue(Property.Y, Undefined.DOUBLE);
	}

	/**
	 * Returns the X value of the control point as percentage.
	 * 
	 * @return the X value of the control point as percentage.
	 */
	public String getXAsPercentageString() {
		return getValue(Property.X, DEFAULT_X_PERCENTAGE_STRING);
	}

	/**
	 * Returns the Y value of the control point as percentage.
	 * 
	 * @return the Y value of the control point as percentage.
	 */
	public String getYAsPercentageString() {
		return getValue(Property.Y, DEFAULT_Y_PERCENTAGE_STRING);
	}

	/**
	 * Returns the X value of the control point as percentage.
	 * 
	 * @return the X value of the control point as percentage.
	 */
	public double getXAsPercentage() {
		// checks if stored as percentage
		if (isType(Property.X, ObjectType.STRING)) {
			return Utilities.getAsPercentage(getValue(Property.X, DEFAULT_X_PERCENTAGE_STRING), DEFAULT_X_PERCENTAGE);
		}
		// if here, it's not a percentage
		// then returns default
		return DEFAULT_X_PERCENTAGE;
	}

	/**
	 * Returns the Y value of the control point as percentage.
	 * 
	 * @return the Y value of the control point as percentage.
	 */
	public double getYAsPercentage() {
		// checks if stored as percentage
		if (isType(Property.Y, ObjectType.STRING)) {
			return Utilities.getAsPercentage(getValue(Property.Y, DEFAULT_Y_PERCENTAGE_STRING), DEFAULT_Y_PERCENTAGE);
		}
		// if here, it's not a percentage
		// then returns default
		return DEFAULT_Y_PERCENTAGE;
	}

	/**
	 * Returns the native object instance.
	 * 
	 * @return the native object instance.
	 */
	NativeObject nativeObject() {
		return super.getNativeObject();
	}
}