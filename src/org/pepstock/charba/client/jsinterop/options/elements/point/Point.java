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
package org.pepstock.charba.client.jsinterop.options.elements.point;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.enums.PointStyle;
import org.pepstock.charba.client.jsinterop.commons.AssignHelper;
import org.pepstock.charba.client.jsinterop.commons.IsDelegated;

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
public class Point implements IsDelegated<NativePoint>{

	private final NativePoint delegated;
	
	private final IsDefaultPoint defaultValues;
	
	public Point(IsDefaultPoint defaultValues) {
		this(new NativePoint(), defaultValues);
	}

	public Point(NativePoint delegated, IsDefaultPoint defaultValues) {
		this.delegated = delegated;
		this.defaultValues = defaultValues;
	}

	/**
	 * Sets the background color.
	 * 
	 * @param backgroundColor the background color.
	 */
	public void setBackgroundColor(IsColor backgroundColor) {
		setBackgroundColor(backgroundColor.toRGBA());
	}
	
	/**
	 * Sets the background color.
	 * 
	 * @param backgroundColor the background color.
	 */
	public void setBackgroundColor(String backgroundColor) {
		delegated.setBackgroundColor(backgroundColor);
	}

	/**
	 * Returns the background color.
	 * 
	 * @return the background color. Default is rgba(0,0,0,0.1).
	 */
	public String getBackgroundColorAsString() {
		return AssignHelper.check(delegated.getBackgroundColor(), defaultValues.getBackgroundColor());
	}


	/**
	 * Returns the background color.
	 * 
	 * @return the background color. Default is rgba(0,0,0,0.1).
	 */
	public IsColor getBackgroundColor() {
		return ColorBuilder.parse(getBackgroundColorAsString());
	}

	/**
	 * Sets the border width.
	 * 
	 * @param borderWidth the border width.
	 */
	public void setBorderWidth(int borderWidth) {
		delegated.setBorderWidth(borderWidth);
	}

	/**
	 * Returns the border width.
	 * 
	 * @return the border width. Default is 2.
	 */
	public int getBorderWidth() {
		return AssignHelper.check(delegated.getBorderWidth(), defaultValues.getBorderWidth());
	}

	/**
	 * Sets the border color.
	 * 
	 * @param borderColor the border color.
	 */
	public void setBorderColor(IsColor borderColor) {
		setBorderColor(borderColor.toRGBA());
	}

	/**
	 * Sets the border color.
	 * 
	 * @param borderColor the border color.
	 */
	public void setBorderColor(String borderColor) {
		delegated.setBorderColor(borderColor);
	}

	/**
	 * Returns the border color.
	 * 
	 * @return the border color. Default is #fff.
	 */
	public String getBorderColorAsString() {
		return AssignHelper.check(delegated.getBorderColor(), defaultValues.getBorderColor());
	}

	/**
	 * Returns the border color.
	 * 
	 * @return the border color. Default is #fff.
	 */
	public IsColor getBorderColor() {
		return ColorBuilder.parse(getBorderColorAsString());
	}
	
	/**
	 * Sets the radius of the point shape. If set to 0, the point is not rendered.
	 * 
	 * @param radius array of the radius of the point shape.
	 */
	public void setRadius(int radius) {
		delegated.setRadius(radius);
	}

	/**
	 * Returns the radius of the point when hovered.
	 * 
	 * @return list of the radius of the point when hovered. Default is 3.
	 */
	public int getRadius() {
		return AssignHelper.check(delegated.getRadius(), defaultValues.getRadius());
	}

	/**
	 * Sets the style of the point.
	 * 
	 * @param pointStyle array of the style of the point.
	 * @see org.pepstock.charba.client.enums.PointStyle
	 */
	public void setPointStyle(PointStyle pointStyle) {
		delegated.setPointStyle(pointStyle.name());
	}

	/**
	 * Returns the style of the point.
	 * 
	 * @return the style of the point. Default is {@link org.pepstock.charba.client.enums.PointStyle#circle}.
	 * @see org.pepstock.charba.client.enums.PointStyle
	 */
	public PointStyle getPointStyle() {
		return AssignHelper.deserialize(AssignHelper.check(delegated.getPointStyle(), defaultValues.getPointStyle()), PointStyle.class, PointStyle.circle);
	}

	/**
	 * Sets the pixel size of the non-displayed point that reacts to mouse events.
	 * 
	 * @param hitRadius the pixel size of the non-displayed point.
	 */
	public void setHitRadius(int hitRadius) {
		delegated.setHitRadius(hitRadius);
	}

	/**
	 * Returns the pixel size of the non-displayed point that reacts to mouse events.
	 * 
	 * @return the pixel size of the non-displayed point. Default is 1.
	 */
	public int getHitRadius() {
		return AssignHelper.check(delegated.getHitRadius(), defaultValues.getHitRadius());
	}

	/**
	 * Sets the radius of the point when hovered.
	 * 
	 * @param hoverRadius the radius of the point when hovered.
	 */
	public void setHoverRadius(int hoverRadius) {
		delegated.setHoverRadius(hoverRadius);
	}

	/**
	 * Returns the radius of the point when hovered.
	 * 
	 * @return the radius of the point when hovered. Default is 4.
	 */
	public int getHoverRadius() {
		return AssignHelper.check(delegated.getHoverRadius(), defaultValues.getHoverRadius());
	}

	/**
	 * Sets the border width of point when hovered.
	 * 
	 * @param hoverBorderWidth the border width of point when hovered.
	 */
	public void setHoverBorderWidth(int hoverBorderWidth) {
		delegated.setHoverBorderWidth(hoverBorderWidth);
	}

	/**
	 * Returns the border width of point when hovered.
	 * 
	 * @return the border width of point when hovered.Default is 1.
	 */
	public int getHoverBorderWidth() {
		return AssignHelper.check(delegated.getHoverBorderWidth(), defaultValues.getHoverBorderWidth());
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.commons.IsDelegated#getDelegated()
	 */
	@Override
	public NativePoint getDelegated() {
		return delegated;
	}

}