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
package org.pepstock.charba.client.jsinterop.options.elements.rectangle;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.jsinterop.commons.AssignHelper;
import org.pepstock.charba.client.jsinterop.commons.IsDelegated;

/**
 * Rectangle elements are used to represent the bars in a bar chart.<br>
 * While chart types provide settings to configure the styling of each dataset, you sometimes want to style all datasets the
 * same way.<br>
 * Options can be configured for four different types of elements: arc, lines, points, and rectangles.<br>
 * When set, these options apply to all objects of that type unless specifically overridden by the configuration attached to a
 * dataset.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class Rectangle implements IsDelegated<NativeRectangle>{

	private final NativeRectangle delegated;
	
	private final IsDefaultRectangle defaultValues;
	
	public Rectangle(IsDefaultRectangle defaultValues) {
		this(new NativeRectangle(), defaultValues);
	}

	public Rectangle(NativeRectangle delegated, IsDefaultRectangle defaultValues) {
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
	 * Sets the edge to skip drawing the border for.
	 * 
	 * @param borderSkipped the edge to skip drawing the border for.
	 * @see  org.pepstock.charba.client.enums.Position
	 */
	public void setBorderSkipped(Position borderSkipped) {
		delegated.setBorderSkipped(borderSkipped.name());
	}

	/**
	 * Returns the edge to skip drawing the border for.
	 * 
	 * @return the edge to skip drawing the border for. Default is {@link  org.pepstock.charba.client.enums.Position#bottom}.
	 * @see  org.pepstock.charba.client.enums.Position
	 */
	public Position getBorderJoinStyle() {
		return AssignHelper.deserialize(AssignHelper.check(delegated.getBorderSkipped(), defaultValues.getBorderSkipped()), Position.class, Position.bottom);
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.commons.IsDelegated#getDelegated()
	 */
	@Override
	public NativeRectangle getDelegated() {
		return delegated;
	}
}