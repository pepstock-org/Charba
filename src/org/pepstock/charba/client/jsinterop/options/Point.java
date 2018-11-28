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
package org.pepstock.charba.client.jsinterop.options;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.PointStyle;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultPoint;

/**
 * Point elements are used to represent the points in a line chart or a bubble chart.<br>
 * While chart types provide settings to configure the styling of each dataset, you sometimes want to style all datasets the
 * same way.<br>
 * Options can be configured for four different types of elements: arc, lines, points, and rectangles.<br>
 * When set, these options apply to all objects of that type unless specifically overridden by the configuration attached to a
 * dataset.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class Point extends AbstractElement<Elements, IsDefaultPoint> implements IsDefaultPoint{
	
	/**
	 * Name of fields of JavaScript object.
	 */
	enum Property implements Key
	{
		radius,
		pointStyle,
		hitRadius,
		hoverRadius,
		hoverBorderWidth
	}

	Point(Elements elements, Key childKey, IsDefaultPoint defaultValues, NativeObject nativeObject) {
		super(elements, childKey, defaultValues, nativeObject);
	}

	/**
	 * Sets the radius of the point shape. If set to 0, the point is not rendered.
	 * 
	 * @param radius array of the radius of the point shape.
	 */
	public void setRadius(double radius) {
		setValue(Property.radius, radius);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the radius of the point when hovered.
	 * 
	 * @return list of the radius of the point when hovered. Default is 3.
	 */
	public double getRadius() {
		return getValue(Property.radius, getDefaultValues().getRadius());
	}

	/**
	 * Sets the style of the point.
	 * 
	 * @param pointStyle array of the style of the point.
	 * @see org.pepstock.charba.client.enums.PointStyle
	 */
	public void setPointStyle(PointStyle pointStyle) {
		setValue(Property.pointStyle, pointStyle);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the style of the point.
	 * 
	 * @return the style of the point. Default is {@link org.pepstock.charba.client.enums.PointStyle#circle}.
	 * @see org.pepstock.charba.client.enums.PointStyle
	 */
	public PointStyle getPointStyle() {
		return getValue(Property.pointStyle, PointStyle.class, getDefaultValues().getPointStyle());
	}

	/**
	 * Sets the pixel size of the non-displayed point that reacts to mouse events.
	 * 
	 * @param hitRadius the pixel size of the non-displayed point.
	 */
	public void setHitRadius(double hitRadius) {
		setValue(Property.hitRadius, hitRadius);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the pixel size of the non-displayed point that reacts to mouse events.
	 * 
	 * @return the pixel size of the non-displayed point. Default is 1.
	 */
	public double getHitRadius() {
		return getValue(Property.hitRadius, getDefaultValues().getHitRadius());
	}

	/**
	 * Sets the radius of the point when hovered.
	 * 
	 * @param hoverRadius the radius of the point when hovered.
	 */
	public void setHoverRadius(double hoverRadius) {
		setValue(Property.hoverRadius, hoverRadius);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the radius of the point when hovered.
	 * 
	 * @return the radius of the point when hovered. Default is 4.
	 */
	public double getHoverRadius() {
		return getValue(Property.hoverRadius, getDefaultValues().getHoverRadius());
	}

	/**
	 * Sets the border width of point when hovered.
	 * 
	 * @param hoverBorderWidth the border width of point when hovered.
	 */
	public void setHoverBorderWidth(int hoverBorderWidth) {
		setValue(Property.hoverBorderWidth, hoverBorderWidth);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the border width of point when hovered.
	 * 
	 * @return the border width of point when hovered.Default is 1.
	 */
	public int getHoverBorderWidth() {
		return getValue(Property.hoverBorderWidth, getDefaultValues().getHoverBorderWidth());
	}

}