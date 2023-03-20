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
package org.pepstock.charba.client.annotation.elements;

import org.pepstock.charba.client.annotation.ControlPoint;
import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.items.Undefined;
import org.pepstock.charba.client.utils.Utilities;

/**
 * This object is to set the control point of a line annotation, when set as curve.<br>
 * If curve is enabled, it configures the control point to drawn the curve, calculated in pixels.<br>
 * It can be set by a string in percentage format "number%" which are representing the percentage of the distance between the start and end point from the center.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class ControlPointElement extends AbstractNode {

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
	 * Creates an object with values in pixels.
	 * 
	 * @param parent parent node to use to add this element where changed
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param value the values of the control point in pixels
	 */
	ControlPointElement(AbstractNode parent, Key childKey, double value) {
		super(parent, childKey, null);
		// stores values
		setX(value);
		setY(value);
	}

	/**
	 * Creates an object with values as percentage.
	 * 
	 * @param parent parent node to use to add this element where changed
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param value the values of the control point as percentage
	 */
	ControlPointElement(AbstractNode parent, Key childKey, String value) {
		super(parent, childKey, null);
		// stores values
		setValue(Property.X, value);
		setValue(Property.Y, value);
	}

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param parent parent node to use to add this element where changed
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param nativeObject native object to map java script properties
	 */
	ControlPointElement(AbstractNode parent, Key childKey, NativeObject nativeObject) {
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
	 * @param percentage the X value of the control point as percentage
	 */
	public void setXAsPercentage(double percentage) {
		setValueAndAddToParent(Property.X, Utilities.getAsPercentage(percentage, ControlPoint.MINIMUN_PERCENTAGE, ControlPoint.MAXIMUN_PERCENTAGE, ControlPoint.DEFAULT_X_PERCENTAGE));
	}

	/**
	 * Sets the Y value of the control point as percentage.
	 * 
	 * @param percentage the Y value of the control point as percentage
	 */
	public void setYAsPercentage(double percentage) {
		setValueAndAddToParent(Property.Y, Utilities.getAsPercentage(percentage, ControlPoint.MINIMUN_PERCENTAGE, ControlPoint.MAXIMUN_PERCENTAGE, ControlPoint.DEFAULT_Y_PERCENTAGE));
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
	public double getXAsPercentage() {
		return Utilities.getAsPercentage(getValue(Property.X, Undefined.STRING), ControlPoint.DEFAULT_X_PERCENTAGE);
	}

	/**
	 * Returns the Y value of the control point as percentage.
	 * 
	 * @return the Y value of the control point as percentage.
	 */
	public double getYAsPercentage() {
		return Utilities.getAsPercentage(getValue(Property.Y, Undefined.STRING), ControlPoint.DEFAULT_Y_PERCENTAGE);
	}
}