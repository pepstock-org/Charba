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
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.Position;

/**
 * Rectangle elements are used to represent the bars in a bar chart.
 * 
 * @author Andrea "Stock" Stocchero
 * @see org.pepstock.charba.client.BarChart
 */
public final class Rectangle extends AbstractElement {

	/**
	 * Name of fields of JavaScript object.
	 */
	enum Property implements Key
	{
		borderSkipped
	}

	/**
	 * Builds the object storing the chart instance.
	 * 
	 * @param chart chart instance
	 */
	public Rectangle(AbstractChart<?, ?> chart) {
		super(chart);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.elements.Arc#getDefaultBackgroundColor()
	 */
	@Override
	protected String getDefaultBackgroundColor() {
		return getChart().getGlobal().getElements().getRectangle().getBackgroundColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.elements.Arc#getDefaultBorderWidth()
	 */
	@Override
	protected int getDefaultBorderWidth() {
		return getChart().getGlobal().getElements().getRectangle().getBorderWidth();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.elements.Arc#getDefaultBorderColor()
	 */
	@Override
	protected String getDefaultBorderColor() {
		return getChart().getGlobal().getElements().getRectangle().getBorderColorAsString();
	}

	/**
	 * Sets the edge to skip drawing the border for.
	 * 
	 * @param borderSkipped the edge to skip drawing the border for.
	 */
	public void setBorderSkipped(Position borderSkipped) {
		setValue(Property.borderSkipped, borderSkipped);
	}

	/**
	 * Returns the edge to skip drawing the border for.
	 * 
	 * @return the edge to skip drawing the border for.
	 */
	public Position getBorderSkipped() {
		return getValue(Property.borderSkipped, Position.class, getChart().getGlobal().getElements().getRectangle().getBorderSkipped());
	}

}