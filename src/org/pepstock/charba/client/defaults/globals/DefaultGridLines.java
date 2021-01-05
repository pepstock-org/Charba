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
package org.pepstock.charba.client.defaults.globals;

import org.pepstock.charba.client.defaults.IsDefaultGridLines;

/**
 * CHART.JS default values for GRIDLINES element.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultGridLines implements IsDefaultGridLines {

	private static final boolean DEFAULT_DISPLAY = true;

	// FIXME
	private static final String DEFAULT_COLOR = "rgba(0,0,0,0.1)";

	private static final double DEFAULT_BORDER_DASH_OFFSET = 0D;

	private static final int DEFAULT_LINE_WIDTH = 1;

	private static final boolean DEFAULT_DRAW_BORDER = true;

	private static final boolean DEFAULT_DRAW_ON_CHART_AREA = true;

	private static final boolean DEFAULT_DRAW_TICKS = true;

	private static final int DEFAULT_TICK_MARK_LENGTH = 10;

	private static final boolean DEFAULT_OFFSET_GRID_LINES = false;

	private static final boolean DEFAULT_CIRCULAR = false;

	private static final int DEFAULT_Z = 0;

	/**
	 * To avoid any instantiation
	 */
	DefaultGridLines() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultGridLines#isDisplay()
	 */
	@Override
	public boolean isDisplay() {
		return DEFAULT_DISPLAY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultGridLines#getColorAsString()
	 */
	@Override
	public String getColorAsString() {
		return DEFAULT_COLOR;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultGridLines#getBorderDashOffset()
	 */
	@Override
	public double getBorderDashOffset() {
		return DEFAULT_BORDER_DASH_OFFSET;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultGridLines#getLineWidth()
	 */
	@Override
	public int getLineWidth() {
		return DEFAULT_LINE_WIDTH;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultGridLines#isDrawBorder()
	 */
	@Override
	public boolean isDrawBorder() {
		return DEFAULT_DRAW_BORDER;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultGridLines#isDrawOnChartArea()
	 */
	@Override
	public boolean isDrawOnChartArea() {
		return DEFAULT_DRAW_ON_CHART_AREA;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultGridLines#isDrawTicks()
	 */
	@Override
	public boolean isDrawTicks() {
		return DEFAULT_DRAW_TICKS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultGridLines#getTickMarkLength()
	 */
	@Override
	public int getTickMarkLength() {
		return DEFAULT_TICK_MARK_LENGTH;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultGridLines#isOffsetGridLines()
	 */
	@Override
	public boolean isOffsetGridLines() {
		return DEFAULT_OFFSET_GRID_LINES;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultGridLines#isCircular()
	 */
	@Override
	public boolean isCircular() {
		return DEFAULT_CIRCULAR;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultGridLines#getZ()
	 */
	@Override
	public int getZ() {
		return DEFAULT_Z;
	}

}
