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
package org.pepstock.charba.client.items;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.dom.elements.Canvas;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.enums.PointStyle;
import org.pepstock.charba.client.enums.PointStyleType;

/**
 * This object contains the point style info when a label in the tooltip.<br>
 * It must be used in the label tooltip callback.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class TooltipLabelPointStyle extends NativeObjectContainer {

	/**
	 * Tooltip point style factory to create label point style objects.
	 */
	public static final TooltipLabelPointStyleFactory FACTORY = new TooltipLabelPointStyleFactory();

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		POINT_STYLE("pointStyle"),
		ROTATION("rotation");

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
	 * Creates the object with an empty native object
	 */
	public TooltipLabelPointStyle() {
		super();
	}

	/**
	 * Creates the object setting the point style argument
	 * 
	 * @param poitnStyle initial point style value
	 */
	public TooltipLabelPointStyle(PointStyle poitnStyle) {
		this();
		setPointStyle(poitnStyle);
	}

	/**
	 * Creates the object setting the point style argument
	 * 
	 * @param poitnStyle initial point style value
	 */
	public TooltipLabelPointStyle(Img poitnStyle) {
		this();
		setPointStyle(poitnStyle);
	}

	/**
	 * Creates the object setting the point style argument
	 * 
	 * @param poitnStyle initial point style value
	 */
	public TooltipLabelPointStyle(Canvas poitnStyle) {
		this();
		setPointStyle(poitnStyle);
	}

	/**
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	TooltipLabelPointStyle(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the style of the point.
	 * 
	 * @return the style of the point
	 */
	public PointStyle getPointStyle() {
		// checks if image as point style has been used
		if (PointStyleType.STRING.equals(getPointStyleType())) {
			// checks if is boolean
			if (isType(Property.POINT_STYLE, ObjectType.BOOLEAN)) {
				// is false
				return PointStyle.FALSE;
			}
			return getValue(Property.POINT_STYLE, PointStyle.values(), PointStyle.CIRCLE);
		}
		// if here, means the point style as stored as image or canvas
		// then returns the default
		return PointStyle.CIRCLE;
	}

	/**
	 * Returns the style of the point as image.<br>
	 * If property is missing or not an image, returns <code>null</code>.
	 * 
	 * @return image of the style of the point as image.<br>
	 *         If property is missing or not a image, returns <code>null</code>.
	 */
	public Img getPointStyleAsImage() {
		// checks if image as point style has been used
		// checks if image as point style has been used
		if (PointStyleType.IMAGE.equals(getPointStyleType())) {
			return getValue(Property.POINT_STYLE, Undefined.IMAGE_ELEMENT);
		}
		// if here, means the point style as stored as string or canvas
		// returns undefined
		return Undefined.IMAGE_ELEMENT;
	}

	/**
	 * Returns the style of the point as canvas.<br>
	 * If property is missing or not an canvas, returns <code>null</code>.
	 * 
	 * @return image of the style of the point as canvas.<br>
	 *         If property is missing or not a canvas, returns <code>null</code>.
	 */
	public Canvas getPointStyleAsCanvas() {
		// checks if image as point style has been used
		// checks if image as point style has been used
		if (PointStyleType.CANVAS.equals(getPointStyleType())) {
			return getValue(Property.POINT_STYLE, Undefined.CANVAS_ELEMENT);
		}
		// if here, means the point style as stored as string or image
		// returns undefined
		return Undefined.CANVAS_ELEMENT;
	}

	/**
	 * Returns the type of point style.
	 * 
	 * @return the type of point style
	 */
	public PointStyleType getPointStyleType() {
		return PointStyleType.getType(this, Property.POINT_STYLE);
	}

	/**
	 * Sets the style of the point.
	 * 
	 * @param pointStyle style of the point.
	 */
	public void setPointStyle(PointStyle pointStyle) {
		// checks if false
		if (PointStyle.FALSE.equals(pointStyle)) {
			setPointStyle(false);
		} else {
			// stores value
			setValue(Property.POINT_STYLE, pointStyle);
		}
	}

	/**
	 * Sets the style of the point as image.
	 * 
	 * @param pointStyle image element of the style of the point as image.
	 */
	public void setPointStyle(Img pointStyle) {
		setValue(Property.POINT_STYLE, pointStyle);
	}

	/**
	 * Sets the style of the point as canvas.
	 * 
	 * @param pointStyle canvas element of the style of the point as canvas.
	 */
	public void setPointStyle(Canvas pointStyle) {
		setValue(Property.POINT_STYLE, pointStyle);
	}

	/**
	 * Sets the style of the point.
	 * 
	 * @param pointStyle array of the style of the point.
	 */
	public void setPointStyle(boolean pointStyle) {
		// checks if false
		if (!pointStyle) {
			setValue(Property.POINT_STYLE, pointStyle);
		} else {
			// if true, remove the value and use default
			remove(Property.POINT_STYLE);
		}
	}

	/**
	 * Sets the rotation of label in degrees.
	 * 
	 * @param rotation the rotation of label in degrees
	 */
	public void setRotation(double rotation) {
		setValue(Property.ROTATION, rotation);
	}

	/**
	 * Returns the rotation in degrees.
	 * 
	 * @return the rotation in degrees
	 */
	public double getRotation() {
		return getValue(Property.ROTATION, Undefined.INTEGER);
	}

	/**
	 * Wraps the protected method to get the java script object in order to consume it.
	 * 
	 * @return the java script object in order to consume it.
	 */
	public NativeObject getObject() {
		return getNativeObject();
	}

	/**
	 * Inner class to create tooltip label point style by a native object.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	public static class TooltipLabelPointStyleFactory implements NativeObjectContainerFactory<TooltipLabelPointStyle> {

		/**
		 * To avoid any instantiation
		 */
		private TooltipLabelPointStyleFactory() {
			// do nothing
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		public TooltipLabelPointStyle create(NativeObject nativeObject) {
			return new TooltipLabelPointStyle(nativeObject);
		}
	}

}