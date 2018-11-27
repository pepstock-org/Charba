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

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultArc;

/**
 * Arcs are used in the polar area, doughnut and pie charts.<br>
 * While chart types provide settings to configure the styling of each dataset, you sometimes want to style all datasets the
 * same way.<br>
 * Options can be configured for four different types of elements: arc, lines, points, and rectangles.<br>
 * When set, these options apply to all objects of that type unless specifically overridden by the configuration attached to a
 * dataset.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 */
public abstract class AbstractElement<P extends Elements, D extends IsDefaultArc> extends AbstractModel<P, D>{
	
	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		backgroundColor,
		borderWidth,
		borderColor
	}

	AbstractElement(P parent, Key childKey, D defaultValues, NativeObject nativeObject) {
		super(parent, childKey, defaultValues, nativeObject);
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
		setValue(Property.backgroundColor, backgroundColor);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the background color.
	 * 
	 * @return the background color. Default is rgba(0,0,0,0.1).
	 */
	public String getBackgroundColorAsString() {
		return getValue(Property.backgroundColor, getDefaultValues().getBackgroundColorAsString());
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
		setValue(Property.borderWidth, borderWidth);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the border width.
	 * 
	 * @return the border width. Default is 2.
	 */
	public int getBorderWidth() {
		return getValue(Property.borderWidth, getDefaultValues().getBorderWidth());
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
		setValue(Property.borderColor, borderColor);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the border color.
	 * 
	 * @return the border color. Default is #fff.
	 */
	public String getBorderColorAsString() {
		return getValue(Property.borderColor, getDefaultValues().getBorderColorAsString());
	}

	/**
	 * Returns the border color.
	 * 
	 * @return the border color. Default is #fff.
	 */
	public IsColor getBorderColor() {
		return ColorBuilder.parse(getBorderColorAsString());
	}

}