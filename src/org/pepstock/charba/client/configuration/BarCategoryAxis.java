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
public final class BarCategoryAxis extends CartesianCategoryAxis {

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