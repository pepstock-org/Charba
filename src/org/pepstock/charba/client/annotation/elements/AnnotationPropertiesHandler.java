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

import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.items.Undefined;

/**
 * Manages all common properties of the annotation element and properties.
 * 
 * @author Andrea "Stock" Stocchero
 */
final class AnnotationPropertiesHandler extends AbstractNode {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		X("x"),
		Y("y"),
		X2("x2"),
		Y2("y2"),
		CENTER_X("centerX"),
		CENTER_Y("centerY"),
		HEIGHT("height"),
		WIDTH("width"),
		POINT_X("pointX"),
		POINT_Y("pointY"),
		RADIUS("radius");

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
	 * Creates the object with native object to map java script properties.
	 * 
	 * @param nativeObject native object to map java script properties
	 */
	AnnotationPropertiesHandler(NativeObject nativeObject) {
		super(null, null, nativeObject);
	}

	/**
	 * Sets the X location of element in pixel.
	 * 
	 * @param x the X location of element in pixel.
	 */
	void setX(double x) {
		setValue(Property.X, x);
	}

	/**
	 * Sets the Y location of element in pixel.
	 * 
	 * @param y the Y location of element in pixel.
	 */
	void setY(double y) {
		setValue(Property.Y, y);
	}

	/**
	 * Returns the X2 location of element in pixel.
	 * 
	 * @return the X2 location of element in pixel.
	 */
	double getX2() {
		return getValue(Property.X2, Undefined.DOUBLE);
	}

	/**
	 * Sets the X2 location of element in pixel.
	 * 
	 * @param x2 the X2 location of element in pixel.
	 */
	void setX2(double x2) {
		setValue(Property.X2, x2);
	}

	/**
	 * Returns the Y location of element in pixel.
	 * 
	 * @return the Y location of element in pixel.
	 */
	double getY2() {
		return getValue(Property.Y2, Undefined.DOUBLE);
	}

	/**
	 * Sets the Y2 location of element in pixel.
	 * 
	 * @param y2 the Y2 location of element in pixel.
	 */
	void setY2(double y2) {
		setValue(Property.Y2, y2);
	}

	/**
	 * Returns the width of element in pixel.
	 * 
	 * @return the width of element in pixel.
	 */
	double getWidth() {
		return getValue(Property.WIDTH, Undefined.DOUBLE);
	}

	/**
	 * Sets the width of element in pixel.
	 * 
	 * @param width the width of element in pixel.
	 */
	void setWidth(double width) {
		setValue(Property.WIDTH, width);
	}

	/**
	 * Returns the height of element in pixel.
	 * 
	 * @return the height of element in pixel.
	 */
	double getHeight() {
		return getValue(Property.HEIGHT, Undefined.DOUBLE);
	}

	/**
	 * Sets the height of element in pixel.
	 * 
	 * @param height the height of element in pixel.
	 */
	void setHeight(double height) {
		setValue(Property.HEIGHT, height);
	}

	/**
	 * Returns the radius of element in pixel.
	 * 
	 * @return the radius of element in pixel.
	 */
	double getRadius() {
		return getValue(Property.RADIUS, Undefined.DOUBLE);
	}

	/**
	 * Sets the radius of element in pixel.
	 * 
	 * @param radius the radius of element in pixel.
	 */
	void setRadius(double radius) {
		setValue(Property.RADIUS, radius);
	}

	/**
	 * Returns the X location of element point in pixel.
	 * 
	 * @return the X location of element point in pixel.
	 */
	double getPointX() {
		return getValue(Property.POINT_X, Undefined.DOUBLE);
	}

	/**
	 * Sets the X location of element point in pixel.
	 * 
	 * @param x the X location of element point in pixel.
	 */
	void setPointX(double x) {
		setValue(Property.POINT_X, x);
	}

	/**
	 * Returns the Y location of element point in pixel.
	 * 
	 * @return the Y location of element point in pixel.
	 */
	double getPointY() {
		return getValue(Property.POINT_Y, Undefined.DOUBLE);
	}

	/**
	 * Sets the Y location of element point in pixel.
	 * 
	 * @param y the Y location of element point in pixel.
	 */
	void setPointY(double y) {
		setValue(Property.POINT_Y, y);
	}

	/**
	 * Sets the center point of the element.
	 * 
	 * @param x the X value of center point of the element.
	 * @param y the Y value of center point of the element.
	 */
	void setCenterPoint(double x, double y) {
		setValue(Property.CENTER_X, x);
		setValue(Property.CENTER_Y, y);
	}
}