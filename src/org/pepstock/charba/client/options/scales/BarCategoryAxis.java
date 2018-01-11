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
package org.pepstock.charba.client.options.scales;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.commons.Key;

/**
 * This a cartesian axis for BAR chart.<br>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class BarCategoryAxis extends CartesianCategoryAxis {



	// specific gridlines for BAR charts
	private final BarGridLines barGridLines = new BarGridLines();

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		barPercentage,
		categoryPercentage,
		barThickness,
		maxBarThickness
	}
	
	// FIXME defaults comment

	/**
	 * Sets the percent (0-1) of the available width each bar should be within the category width. 1.0 will take the whole
	 * category width and put the bars right next to each other.
	 * 
	 * @param barPercentage percent (0-1) of the available width each bar should be within the category width. 1.0 will take the
	 *            whole category width and put the bars right next to each other.
	 */
	public void setBarPercentage(double barPercentage) {
		setValue(Property.barPercentage, barPercentage);
	}

	/**
	 * Returns the percent (0-1) of the available width each bar should be within the category width. 1.0 will take the whole
	 * category width and put the bars right next to each other.
	 * 
	 * @return percent (0-1) of the available width each bar should be within the category width. 1.0 will take the whole
	 *         category width and put the bars right next to each other. Default is 0.9.
	 */
	public double getBarPercentage() {
		return getValue(Property.barPercentage, Defaults.getScale().getBarPercentage());
	}

	/**
	 * Sets the percent (0-1) of the available width each category should be within the sample width.
	 * 
	 * @param categoryPercentage percent (0-1) of the available width each category should be within the sample width.
	 */
	public void setCategoryPercentage(double categoryPercentage) {
		setValue(Property.categoryPercentage, categoryPercentage);
	}

	/**
	 * Returns the percent (0-1) of the available width each category should be within the sample width.
	 * 
	 * @return the percent (0-1) of the available width each category should be within the sample width. Default is 0.8.
	 */
	public double getCategoryPercentage() {
		return getValue(Property.categoryPercentage, Defaults.getScale().getCategoryPercentage());
	}

	/**
	 * Sets the width of each bar in pixels. If not set, the base sample widths are calculated automatically so that they take
	 * the full available widths without overlap. Then, the bars are sized using barPercentage and categoryPercentage.
	 * 
	 * @param barThickness width of each bar in pixels. If not set, the base sample widths are calculated automatically so that
	 *            they take the full available widths without overlap. Then, the bars are sized using barPercentage and
	 *            categoryPercentage.
	 */
	public void setBarThickness(int barThickness) {
		setValue(Property.barThickness, barThickness);
	}

	/**
	 * Returns the width of each bar in pixels. If not set, the base sample widths are calculated automatically so that they
	 * take the full available widths without overlap. Then, the bars are sized using barPercentage and categoryPercentage.
	 * 
	 * @return width of each bar in pixels. If not set, the base sample widths are calculated automatically so that they take
	 *         the full available widths without overlap. Then, the bars are sized using barPercentage and categoryPercentage.
	 *         Default is 0.
	 */
	public int getBarThickness() {
		return getValue(Property.barThickness, Defaults.getScale().getBarThickness());
	}

	/**
	 * Sets the maximum bar thickness, to ensure that bars are not sized thicker than this
	 * 
	 * @param maxBarThickness the maximum bar thickness.
	 */
	public void setMaxBarThickness(int maxBarThickness) {
		setValue(Property.maxBarThickness, maxBarThickness);
	}

	/**
	 * Returns the maximum bar thickness.
	 * 
	 * @return the maximum bar thickness. Default is 0.
	 */
	public int getMaxBarThickness() {
		return getValue(Property.maxBarThickness, Defaults.getScale().getMaxBarThickness());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.scales.CartesianAxis#getGrideLines()
	 */
	@Override
	public GridLines getGrideLines() {
		return barGridLines;
	}

	/**
	 * Gridlines is set to true in the bar chart by default.<br>
	 * It overrides the method to set offset, setting ALWAYS true.<br>
	 * If true, the bars for a particular data point fall between the grid lines.<br>
	 * The grid line will move to the left by one half of the tick interval.<br>
	 * If false, the grid line will go right down the middle of the bars.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class BarGridLines extends GridLines {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.options.scales.GridLines# setOffsetGridLines(boolean)
		 */
		@Override
		public void setOffsetGridLines(boolean offsetGridLines) {
			// sets always true
			super.setOffsetGridLines(true);
		}
	}

}