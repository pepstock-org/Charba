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
package org.pepstock.charba.client.configuration;

import org.pepstock.charba.client.IsChart;

/**
 * This a cartesian axis for BAR chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class BarCategoryAxis extends CartesianCategoryAxis {

	// specific gridlines for BAR charts
	private final BarGridLines barGridLines;

	/**
	 * Builds the object storing the chart instance.
	 * 
	 * @param chart chart instance
	 */
	public BarCategoryAxis(IsChart chart) {
		super(chart);
		// creates grid lines
		barGridLines = new BarGridLines(this);
	}

	/**
	 * Sets the percent (0-1) of the available width each bar should be within the category width. 1.0 will take the whole
	 * category width and put the bars right next to each other.
	 * 
	 * @param barPercentage percent (0-1) of the available width each bar should be within the category width. 1.0 will take the
	 *            whole category width and put the bars right next to each other.
	 */
	public void setBarPercentage(double barPercentage) {
		getScale().setBarPercentage(barPercentage);
	}

	/**
	 * Returns the percent (0-1) of the available width each bar should be within the category width. 1.0 will take the whole
	 * category width and put the bars right next to each other.
	 * 
	 * @return percent (0-1) of the available width each bar should be within the category width. 1.0 will take the whole
	 *         category width and put the bars right next to each other.
	 */
	public double getBarPercentage() {
		return getScale().getBarPercentage();
	}

	/**
	 * Sets the percent (0-1) of the available width each category should be within the sample width.
	 * 
	 * @param categoryPercentage percent (0-1) of the available width each category should be within the sample width.
	 */
	public void setCategoryPercentage(double categoryPercentage) {
		getScale().setCategoryPercentage(categoryPercentage);
	}

	/**
	 * Returns the percent (0-1) of the available width each category should be within the sample width.
	 * 
	 * @return the percent (0-1) of the available width each category should be within the sample width.
	 */
	public double getCategoryPercentage() {
		return getScale().getCategoryPercentage();
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
		getScale().setBarThickness(barThickness);
	}

	/**
	 * Returns the width of each bar in pixels. If not set, the base sample widths are calculated automatically so that they
	 * take the full available widths without overlap. Then, the bars are sized using barPercentage and categoryPercentage.
	 * 
	 * @return width of each bar in pixels. If not set, the base sample widths are calculated automatically so that they take
	 *         the full available widths without overlap. Then, the bars are sized using barPercentage and categoryPercentage.
	 */
	public int getBarThickness() {
		return getScale().getBarThickness();
	}

	/**
	 * Sets the maximum bar thickness, to ensure that bars are not sized thicker than this
	 * 
	 * @param maxBarThickness the maximum bar thickness.
	 */
	public void setMaxBarThickness(int maxBarThickness) {
		getScale().setMaxBarThickness(maxBarThickness);
	}

	/**
	 * Returns the maximum bar thickness.
	 * 
	 * @return the maximum bar thickness.
	 */
	public int getMaxBarThickness() {
		return getScale().getMaxBarThickness();
	}

	/**
	 * Set this to ensure that bars have a minimum length in pixels.
	 * 
	 * @param minBarLength a minimum length in pixels.
	 */
	public final void setMinBarLength(int minBarLength) {
		getScale().setMinBarLength(minBarLength);
	}

	/**
	 * Returns a minimum length in pixels.
	 * 
	 * @return a minimum length in pixels.
	 */
	public final int getMinBarLength() {
		return getScale().getMinBarLength();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.CartesianAxis#getGrideLines()
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
	 * @author Andrea "Stock" Stocchero *
	 */
	private static class BarGridLines extends GridLines {

		/**
		 * Builds the object by an axis.
		 * 
		 * @param axis axis instance
		 */
		BarGridLines(Axis axis) {
			super(axis);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.configuration.GridLines#setOffsetGridLines(boolean)
		 */
		@Override
		public void setOffsetGridLines(boolean offsetGridLines) {
			// sets always true
			super.setOffsetGridLines(true);
		}
	}

}