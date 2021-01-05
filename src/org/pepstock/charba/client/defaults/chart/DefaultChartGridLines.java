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
package org.pepstock.charba.client.defaults.chart;

import org.pepstock.charba.client.defaults.IsDefaultGridLines;
import org.pepstock.charba.client.options.GridLines;

/**
 * Defaults for grid lines option element, based on chart type.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultChartGridLines implements IsDefaultGridLines {

	private final GridLines gridLines;

	/**
	 * Creates the object by grid lines option element instance.
	 * 
	 * @param gridLines grid lines option element instance.
	 */
	DefaultChartGridLines(GridLines gridLines) {
		this.gridLines = gridLines;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultGridLines#isDisplay()
	 */
	@Override
	public boolean isDisplay() {
		return gridLines.isDisplay();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultGridLines#getColorAsString()
	 */
	@Override
	public String getColorAsString() {
		return gridLines.getColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultGridLines#getBorderDashOffset()
	 */
	@Override
	public double getBorderDashOffset() {
		return gridLines.getBorderDashOffset();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultGridLines#getLineWidth()
	 */
	@Override
	public int getLineWidth() {
		return gridLines.getLineWidth();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultGridLines#isDrawBorder()
	 */
	@Override
	public boolean isDrawBorder() {
		return gridLines.isDrawBorder();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultGridLines#isDrawOnChartArea()
	 */
	@Override
	public boolean isDrawOnChartArea() {
		return gridLines.isDrawOnChartArea();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultGridLines#isDrawTicks()
	 */
	@Override
	public boolean isDrawTicks() {
		return gridLines.isDrawTicks();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultGridLines#getTickMarkLength()
	 */
	@Override
	public int getTickMarkLength() {
		return gridLines.getTickMarkLength();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultGridLines#isOffsetGridLines()
	 */
	@Override
	public boolean isOffsetGridLines() {
		return gridLines.isOffsetGridLines();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultGridLines#isCircular()
	 */
	@Override
	public boolean isCircular() {
		return gridLines.isCircular();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultGridLines#getZ()
	 */
	@Override
	public int getZ() {
		return gridLines.getZ();
	}

}
