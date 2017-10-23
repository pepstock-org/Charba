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
package org.pepstock.charba.client.options.elements;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.PointStyle;

/**
 * Point elements are used to represent the points in a line chart or a bubble chart.<br>
 * While chart types provide settings to configure the styling of each dataset, you sometimes want to style all datasets the same way.<br>
 * Options can be configured for four different types of elements: arc, lines, points, and rectangles.<br>
 * When set, these options apply to all objects of that type unless specifically overridden by the configuration attached to a dataset.
 * @author Andrea "Stock" Stocchero
 * @see org.pepstock.charba.client.LineChart
 * @see org.pepstock.charba.client.BubbleChart
 */
public class Point extends Arc{

	/// default radius 
	private static final int DEFAULT_RADIUS = 3;
	// default background color
	private static final String DEFAULT_BACKGROUND_COLOR = "rgba(0,0,0,0.1)";
	// default border with
	private static final int DEFAULT_BORDER_WIDTH = 1;
	// default border color
	private static final String DEFAULT_BORDER_COLOR = "rgba(0,0,0,0.1)";
	// default hit radius
	private static final int DEFAULT_HIT_RADIUS = 1;
	// default hover radius
	private static final int DEFAULT_HOVER_RADIUS = 4;
	// default hover border width
	private static final int DEFAULT_HOVER_BORDER_WIDTH = 1;
	
	/**
	 * Name of fields of JavaScript object. 
	 */
	enum Property implements Key {
		radius,
		pointStyle,
		hitRadius,
		hoverRadius,
		hoverBorderWidth
	}
	
	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.options.elements.Arc#getDefaultBackgroundColor()
	 */
	@Override
	protected String getDefaultBackgroundColor() {
		return DEFAULT_BACKGROUND_COLOR;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.options.elements.Arc#getDefaultBorderWidth()
	 */
	@Override
	protected int getDefaultBorderWidth() {
		return DEFAULT_BORDER_WIDTH;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.options.elements.Arc#getDefaultBorderColor()
	 */
	@Override
	protected String getDefaultBorderColor() {
		return DEFAULT_BORDER_COLOR;
	}
	
	/**
	 * Sets the radius of the point shape. If set to 0, the point is not rendered.
	 * @param pointRadius array of the radius of the point shape.
	 */
	public void setRadius(int radius) {
		setValue(Property.radius, radius);
	}

	/**
	 * Returns the radius of the point when hovered.
	 * @return list of the radius of the point when hovered.
	 */
	public int getRadius() {
		return getValue(Property.radius, DEFAULT_RADIUS);
	}

	/**
	 * Sets the style of the point.
	 * @param pointStyle array of the style of the point.
	 * @see org.pepstock.charba.client.enums.PointStyle
	 */
	public void setPointStyle(PointStyle pointStyle) {
		setValue(Property.pointStyle, pointStyle);
	}

	/**
	 * Returns the style of the point.
	 * @return the style of the point. Default is <code>PointStyle.circle</code>
	 * @see org.pepstock.charba.client.enums.PointStyle
	 */
	public PointStyle getPointStyle() {
		return getValue(Property.pointStyle, PointStyle.class, PointStyle.circle);
	}

	/**
	 * Sets the pixel size of the non-displayed point that reacts to mouse events.
	 * @param hitRadius the pixel size of the non-displayed point.
	 */
	public void setHitRadius(int hitRadius) {
		setValue(Property.hitRadius, hitRadius);
	}

	/**
	 * Returns the pixel size of the non-displayed point that reacts to mouse events.
	 * @return the pixel size of the non-displayed point.
	 */
	public int getHitRadius() {
		return getValue(Property.hitRadius, DEFAULT_HIT_RADIUS);
	}

	/**
	 * Sets the radius of the point when hovered.
	 * @param hoverRadius the radius of the point when hovered.
	 */
	public void setHoverRadius(int hoverRadius) {
		setValue(Property.hoverRadius, hoverRadius);
	}

	/**
	 * Returns the radius of the point when hovered.
	 * @return the radius of the point when hovered.
	 */
	public int getHoverRadius() {
		return getValue(Property.hoverRadius, DEFAULT_HOVER_RADIUS);
	}

	/**
	 * Sets the border width of point when hovered.
	 * @param hoverBorderWidth  the border width of point when hovered.
	 */
	public void setHoverBorderWidth(int hoverBorderWidth) {
		setValue(Property.hoverBorderWidth, hoverBorderWidth);
	}

	/**
	 * Returns the border width of point when hovered.
	 * @return the border width of point when hovered.
	 */
	public int getHoverBorderWidth() {
		return getValue(Property.hoverBorderWidth, DEFAULT_HOVER_BORDER_WIDTH);
	}
    
}