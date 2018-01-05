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

import org.pepstock.charba.client.commons.JavaScriptObjectContainer;
import org.pepstock.charba.client.commons.Key;

/**
 * Arcs are used in the polar area, doughnut and pie charts.<br>
 * While chart types provide settings to configure the styling of each dataset, you sometimes want to style all datasets the same way.<br>
 * Options can be configured for four different types of elements: arc, lines, points, and rectangles.<br>
 * When set, these options apply to all objects of that type unless specifically overridden by the configuration attached to a dataset.
 * 
 * @author Andrea "Stock" Stocchero
 * @see org.pepstock.charba.client.PolarAreaChart
 * @see org.pepstock.charba.client.DoughnutChart
 * @see org.pepstock.charba.client.PieChart
 */
public class Arc extends JavaScriptObjectContainer {

	// default background color
	private static final String DEFAULT_BACKGROUND_COLOR = "rgba(0,0,0,0.1)";
	// default border with
	private static final int DEFAULT_BORDER_WIDTH = 2;
	// default border color
	private static final String DEFAULT_BORDER_COLOR = "#fff";

	/**
	 * Name of fields of JavaScript object.
	 */
	enum Property implements Key
	{
		backgroundColor,
		borderWidth,
		borderColor
	}

	/**
	 * Sets the background color
	 * 
	 * @param backgroundColor the background color
	 */
	public void setBackgroundColor(String backgroundColor) {
		setValue(Property.backgroundColor, backgroundColor);
	}

	/**
	 * Returns the background color
	 * 
	 * @return the background color
	 */
	public String getBackgroundColor() {
		return getValue(Property.backgroundColor, getDefaultBackgroundColor());
	}

	/**
	 * Returns the default background color
	 * 
	 * @return the default background color
	 */
	protected String getDefaultBackgroundColor() {
		return DEFAULT_BACKGROUND_COLOR;
	}

	/**
	 * Sets the border width
	 * 
	 * @param borderWidth the border width
	 */
	public void setBorderWidth(int borderWidth) {
		setValue(Property.borderWidth, borderWidth);
	}

	/**
	 * Returns the border width
	 * 
	 * @return the border width
	 */
	public int getBorderWidth() {
		return getValue(Property.borderWidth, getDefaultBorderWidth());
	}

	/**
	 * Returns the default border width
	 * 
	 * @return the default border width
	 */
	protected int getDefaultBorderWidth() {
		return DEFAULT_BORDER_WIDTH;
	}

	/**
	 * Sets the border color
	 * 
	 * @param borderColor the border color
	 */
	public void setBorderColor(String borderColor) {
		setValue(Property.borderColor, borderColor);
	}

	/**
	 * Returns the border color
	 * 
	 * @return the border color
	 */
	public String getBorderColor() {
		return getValue(Property.borderColor, getDefaultBorderColor());
	}

	/**
	 * Returns the default border color
	 * 
	 * @return the default border color
	 */
	protected String getDefaultBorderColor() {
		return DEFAULT_BORDER_COLOR;
	}

}