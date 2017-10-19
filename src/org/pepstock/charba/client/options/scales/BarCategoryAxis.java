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

import org.pepstock.charba.client.commons.Key;

public final class BarCategoryAxis extends CartesianCategoryAxis {
	
	private static final double DEFAULT_BAR_PERCENTAGE = 0.9F;
	
	private static final double DEFAULT_CATEGORY_PERCENTAGE = 0.8F;
	
	private static final int DEFAULT_BAR_THICKNESS = 0;
	
	private static final int DEFAULT_MAX_BAR_THICKNESS = 0;
	
	private final BarGridLines barGridLines = new BarGridLines();

	private enum Property implements Key {
		barPercentage,
		categoryPercentage,
		barThickness,
		maxBarThickness
	}
	
//	barPercentage  Number  0.9  Percent (0-1) of the available width each bar should be within the category width. 1.0 will take the whole category width and put the bars right next to each other. more...
//	categoryPercentage  Number  0.8  Percent (0-1) of the available width each category should be within the sample width. more...
//	barThickness  Number   Manually set width of each bar in pixels. If not set, the base sample widths are calculated automatically so that they take the full available widths without overlap. Then, the bars are sized using barPercentage and categoryPercentage.
//	maxBarThickness  Number   Set this to ensure that bars are not sized thicker than this.
//	gridLines.offsetGridLines  Boolean  true  If true, the bars for a particular data point fall between the grid lines. The grid line will move to the left by one half of the tick interval. If false, the grid line will go right down the middle of the bars. more...
	
	
	public void setBarPercentage(double barPercentage){
		  setValue(Property.barPercentage, barPercentage);
	}

	public double getBarPercentage(){
		  return getValue(Property.barPercentage, DEFAULT_BAR_PERCENTAGE);
	}

	public void setCategoryPercentage(double categoryPercentage){
		  setValue(Property.categoryPercentage, categoryPercentage);
	}

	public double getCategoryPercentage(){
		  return getValue(Property.categoryPercentage, DEFAULT_CATEGORY_PERCENTAGE);
	}

	public void setBarThickness(int barThickness){
		  setValue(Property.barThickness, barThickness);
	}

	public int getBarThickness(){
		  return getValue(Property.barThickness, DEFAULT_BAR_THICKNESS);
	}

	public void setMaxBarThickness(int maxBarThickness){
		  setValue(Property.maxBarThickness, maxBarThickness);
	}

	public int getMaxBarThickness(){
		  return getValue(Property.maxBarThickness, DEFAULT_MAX_BAR_THICKNESS);
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.options.scales.CartesianAxis#getGrideLines()
	 */
	@Override
	public GridLines getGrideLines() {
		return barGridLines;
	}
	
	/**
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class BarGridLines extends GridLines{

		/* (non-Javadoc)
		 * @see org.pepstock.charba.client.options.scales.GridLines#setOffsetGridLines(boolean)
		 */
		@Override
		public void setOffsetGridLines(boolean offsetGridLines) {
			super.setOffsetGridLines(true);
		}
	}
	
}