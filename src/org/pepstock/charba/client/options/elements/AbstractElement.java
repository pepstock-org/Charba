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

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.commons.ChartContainer;
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
abstract class AbstractElement extends ChartContainer {

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
	 * Builds the object setting the java script padding object.
	 * 
	 * @param chart chart instance
	 */
	AbstractElement(AbstractChart<?, ?> chart) {
		super(chart);
	}

	/**
	 * Sets the background color.
	 * 
	 * @param backgroundColor the background color.
	 */
	public void setBackgroundColor(String backgroundColor) {
		setValue(Property.backgroundColor, backgroundColor);
	}

	/**
	 * Returns the background color.
	 * 
	 * @return the background color.
	 */
	public String getBackgroundColor() {
		return getValue(Property.backgroundColor, getDefaultBackgroundColor());
	}

	/**
	 * Returns the default background color.
	 * 
	 * @return the default background color. Default is {@link org.pepstock.charba.client.defaults.global.Arc#getBackgroundColor()}.
	 */
	protected String getDefaultBackgroundColor() {
		return Defaults.getGlobal().getElements().getArc().getBackgroundColor();
	}

	/**
	 * Sets the border width.
	 * 
	 * @param borderWidth the border width.
	 */
	public void setBorderWidth(int borderWidth) {
		setValue(Property.borderWidth, borderWidth);
	}

	/**
	 * Returns the border width.
	 * 
	 * @return the border width.
	 */
	public int getBorderWidth() {
		return getValue(Property.borderWidth, getDefaultBorderWidth());
	}

	/**
	 * Returns the default border width.
	 * 
	 * @return the default border width. Default is {@link org.pepstock.charba.client.defaults.global.Arc#getBorderWidth()}.
	 */
	protected int getDefaultBorderWidth() {
		return Defaults.getGlobal().getElements().getArc().getBorderWidth();
	}

	/**
	 * Sets the border color.
	 * 
	 * @param borderColor the border color.
	 */
	public void setBorderColor(String borderColor) {
		setValue(Property.borderColor, borderColor);
	}

	/**
	 * Returns the border color.
	 * 
	 * @return the border color.
	 */
	public String getBorderColor() {
		return getValue(Property.borderColor, getDefaultBorderColor());
	}

	/**
	 * Returns the default border color.
	 * 
	 * @return the default border color. Default is {@link org.pepstock.charba.client.defaults.global.Arc#getBorderColor()}.
	 */
	protected String getDefaultBorderColor() {
		return Defaults.getGlobal().getElements().getArc().getBorderColor();
	}

}