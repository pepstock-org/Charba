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

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.dom.elements.Canvas;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.enums.PointStyle;
import org.pepstock.charba.client.enums.PointStyleType;

/**
 * Maps the out-of-the-box CHART.JS element options used to represents points on the charts.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class PointElementOptions extends CommonElementOptions {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		RADIUS("radius"),
		HIT_RADIUS("hitRadius"),
		HOVER_RADIUS("hoverRadius"),
		HOVER_BORDER_WIDTH("hoverBorderWidth"),
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
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	PointElementOptions(NativeObject nativeObject) {
		super(nativeObject);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.items.ChartElementOptions#getDefaultBorderWidth()
	 */
	@Override
	protected int getDefaultBorderWidth() {
		return Defaults.get().getGlobal().getElements().getPoint().getBorderWidth();
	}

	/**
	 * Returns the radius of the point.
	 * 
	 * @return the radius of the point
	 */
	public double getRadius() {
		return getValue(Property.RADIUS, Defaults.get().getGlobal().getElements().getPoint().getRadius());
	}

	/**
	 * Returns the pixel size of the non-displayed point that reacts to mouse events.
	 * 
	 * @return the pixel size of the non-displayed point.
	 */
	public double getHitRadius() {
		return getValue(Property.HIT_RADIUS, Defaults.get().getGlobal().getElements().getPoint().getHitRadius());
	}

	/**
	 * Returns the radius of the point when hovered.
	 * 
	 * @return the radius of the point when hovered.
	 */
	public double getHoverRadius() {
		return getValue(Property.HOVER_RADIUS, Defaults.get().getGlobal().getElements().getPoint().getHoverRadius());
	}

	/**
	 * Returns the border width of point when hovered.
	 * 
	 * @return the border width of point when hovered.
	 */
	public int getHoverBorderWidth() {
		return getValue(Property.HOVER_BORDER_WIDTH, Defaults.get().getGlobal().getElements().getPoint().getHoverBorderWidth());
	}

	/**
	 * Returns the point rotation (in degrees).
	 * 
	 * @return the point rotation (in degrees).
	 */
	public double getRotation() {
		return getValue(Property.ROTATION, Defaults.get().getGlobal().getElements().getPoint().getRotation());
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
	 * Returns the style of the point as image.<br>
	 * If property is missing or not an image, returns <code>null</code>.
	 * 
	 * @return image of the style of the point as image.<br>
	 *         If property is missing or not a image, returns <code>null</code>.
	 */
	public Img getPointStyleAsImage() {
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
	 * @return canvas of the style of the point as canvas.<br>
	 *         If property is missing or not a canvas, returns <code>null</code>.
	 */
	public Canvas getPointStyleAsCanvas() {
		// checks if image as point style has been used
		if (PointStyleType.CANVAS.equals(getPointStyleType())) {
			return getValue(Property.POINT_STYLE, Undefined.CANVAS_ELEMENT);
		}
		// if here, means the point style as stored as string or image
		// returns undefined
		return Undefined.CANVAS_ELEMENT;
	}

	/**
	 * Returns the style of the point.
	 * 
	 * @return the style of the point
	 */
	public PointStyle getPointStyle() {
		// checks if string as point style has been used
		if (PointStyleType.STRING.equals(getPointStyleType())) {
			// checks if is boolean
			if (isType(Property.POINT_STYLE, ObjectType.BOOLEAN)) {
				// is false
				return PointStyle.FALSE;
			}
			return getValue(Property.POINT_STYLE, PointStyle.values(), Defaults.get().getGlobal().getElements().getPoint().getPointStyle());
		}
		// if here, means the point style as stored as image or canvas
		// then returns the default
		return Defaults.get().getGlobal().getElements().getPoint().getPointStyle();
	}

	/**
	 * Sets the radius of the point shape.<br>
	 * If set to 0, the point is not rendered.
	 * 
	 * @param radius array of the radius of the point shape.
	 */
	public void setRadius(double radius) {
		setValue(Property.RADIUS, Checker.positiveOrZero(radius));
	}

	/**
	 * Sets the pixel size of the non-displayed point that reacts to mouse events.
	 * 
	 * @param hitRadius the pixel size of the non-displayed point.
	 */
	public void setHitRadius(double hitRadius) {
		setValue(Property.HIT_RADIUS, Checker.positiveOrZero(hitRadius));
	}

	/**
	 * Sets the radius of the point when hovered.
	 * 
	 * @param hoverRadius the radius of the point when hovered.
	 */
	public void setHoverRadius(double hoverRadius) {
		setValue(Property.HOVER_RADIUS, Checker.positiveOrZero(hoverRadius));
	}

	/**
	 * Sets the point rotation (in degrees).
	 * 
	 * @param rotation the point rotation (in degrees).
	 */
	public void setRotation(double rotation) {
		setValue(Property.ROTATION, rotation);
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
	 * Sets the border width of point when hovered.
	 * 
	 * @param borderWidth the border width of point when hovered.
	 */
	public void setHoverBorderWidth(int borderWidth) {
		setValue(Property.HOVER_BORDER_WIDTH, Checker.positiveOrZero(borderWidth));
	}

	/**
	 * Create new {@link TooltipLabelPointStyle} filling it with point style and rotation of dataset element.
	 * 
	 * @return new {@link TooltipLabelPointStyle} filling it with point style and rotation of dataset element
	 */
	public TooltipLabelPointStyle createTooltipLabelPointStyle() {
		// creates an empty label point style
		TooltipLabelPointStyle result = new TooltipLabelPointStyle();
		// checks if point style is an image or canvas
		if (PointStyleType.IMAGE.equals(getPointStyleType())) {
			// stores as image
			result.setPointStyle(getPointStyleAsImage());
		} else if (PointStyleType.CANVAS.equals(getPointStyleType())) {
			// stores as canvas
			result.setPointStyle(getPointStyleAsCanvas());
		} else {
			// stores as point sytle
			result.setPointStyle(getPointStyle());
		}
		// sets rotation
		result.setRotation(getRotation());
		return result;
	}

}