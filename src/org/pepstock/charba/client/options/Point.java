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
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultPoint;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.enums.PointStyle;
import org.pepstock.charba.client.items.UndefinedValues;

/**
 * Point elements are used to represent the points in a line chart or a bubble chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class Point extends AbstractElement<IsDefaultPoint> implements IsDefaultPoint {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		RADIUS("radius"),
		POINT_STYLE("pointStyle"),
		HIT_RADIUS("hitRadius"),
		HOVER_RADIUS("hoverRadius"),
		HOVER_BORDER_WIDTH("hoverBorderWidth"),
		ROTATION("rotation"),
		// internal key to store if point style is an image or not
		CHARBA_POINT_STYLE("_charbaPointStyle");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use into native object.
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
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param elements parent node to use to add this element where changed
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	Point(Elements elements, Key childKey, IsDefaultPoint defaultValues, NativeObject nativeObject) {
		super(elements, childKey, defaultValues, nativeObject);
	}

	/**
	 * Sets the radius of the point shape. If set to 0, the point is not rendered.
	 * 
	 * @param radius array of the radius of the point shape.
	 */
	public void setRadius(double radius) {
		setValue(Property.RADIUS, radius);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the radius of the point when hovered.
	 * 
	 * @return list of the radius of the point when hovered.
	 */
	public double getRadius() {
		return getValue(Property.RADIUS, getDefaultValues().getRadius());
	}

	/**
	 * Sets the style of the point.
	 * 
	 * @param pointStyle array of the style of the point.
	 */
	public void setPointStyle(PointStyle pointStyle) {
		setValue(Property.POINT_STYLE, pointStyle);
		// checks if the node is already added to parent
		checkAndAddToParent();
		// remove if exist flag
		removeIfExists(Property.CHARBA_POINT_STYLE);
	}

	/**
	 * Returns the style of the point.
	 * 
	 * @return the style of the point or <code>null</code> if point style is set as image
	 */
	public PointStyle getPointStyle() {
		// checks if image as point style has been used
		if (!getValue(Property.CHARBA_POINT_STYLE, false)) {
			return getValue(Property.POINT_STYLE, PointStyle.class, getDefaultValues().getPointStyle());
		} else {
			// if here, means the point style as stored as images
			return null;
		}
	}

	/**
	 * Sets the style of the point as image.
	 * 
	 * @param pointStyle image element of the style of the point as image.
	 */
	public void setPointStyle(Img pointStyle) {
		setValue(Property.POINT_STYLE, pointStyle);
		// checks if the node is already added to parent
		checkAndAddToParent();
		// checks if there is the property
		if (has(Property.POINT_STYLE)) {
			// sets flags
			setValue(Property.CHARBA_POINT_STYLE, true);
		} else {
			// image not set
			// remove point style
			removeIfExists(Property.CHARBA_POINT_STYLE);
		}
	}

	/**
	 * Returns the style of the point as image. If property is missing or not an image, returns <code>null</code>.
	 * 
	 * @return image of the style of the point as image. If property is missing or not a image, returns <code>null</code>.
	 */
	public Img getPointStyleAsImage() {
		// checks if image as point style has been used
		if (getValue(Property.CHARBA_POINT_STYLE, false)) {
			// gets value
			return getValue(Property.POINT_STYLE, UndefinedValues.IMAGE_ELEMENT);
		} else {
			// if here, means the point style as stored as strings
			return null;
		}
	}

	/**
	 * Sets the pixel size of the non-displayed point that reacts to mouse events.
	 * 
	 * @param hitRadius the pixel size of the non-displayed point.
	 */
	public void setHitRadius(double hitRadius) {
		setValue(Property.HIT_RADIUS, hitRadius);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the pixel size of the non-displayed point that reacts to mouse events.
	 * 
	 * @return the pixel size of the non-displayed point.
	 */
	public double getHitRadius() {
		return getValue(Property.HIT_RADIUS, getDefaultValues().getHitRadius());
	}

	/**
	 * Sets the radius of the point when hovered.
	 * 
	 * @param hoverRadius the radius of the point when hovered.
	 */
	public void setHoverRadius(double hoverRadius) {
		setValue(Property.HOVER_RADIUS, hoverRadius);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the radius of the point when hovered.
	 * 
	 * @return the radius of the point when hovered.
	 */
	public double getHoverRadius() {
		return getValue(Property.HOVER_RADIUS, getDefaultValues().getHoverRadius());
	}

	/**
	 * Sets the border width of point when hovered.
	 * 
	 * @param hoverBorderWidth the border width of point when hovered.
	 */
	public void setHoverBorderWidth(int hoverBorderWidth) {
		setValue(Property.HOVER_BORDER_WIDTH, hoverBorderWidth);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the border width of point when hovered.
	 * 
	 * @return the border width of point when hovered.
	 */
	public int getHoverBorderWidth() {
		return getValue(Property.HOVER_BORDER_WIDTH, getDefaultValues().getHoverBorderWidth());
	}

	/**
	 * Sets the point rotation (in degrees).
	 * 
	 * @param rotation the point rotation (in degrees).
	 */
	public void setRotation(double rotation) {
		setValue(Property.ROTATION, rotation);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the point rotation (in degrees).
	 * 
	 * @return the point rotation (in degrees).
	 */
	public double getRotation() {
		return getValue(Property.ROTATION, getDefaultValues().getRotation());
	}

}