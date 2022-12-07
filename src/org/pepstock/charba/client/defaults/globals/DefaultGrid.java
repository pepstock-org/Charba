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

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.defaults.IsDefaultGrid;

/**
 * CHART.JS default values for GRID element.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultGrid implements IsDefaultGrid {

	private static final boolean DEFAULT_DISPLAY = true;

	private static final int DEFAULT_LINE_WIDTH = 1;

	private static final boolean DEFAULT_DRAW_ON_CHART_AREA = true;

	private static final boolean DEFAULT_DRAW_TICKS = true;

	private static final int DEFAULT_TICK_MARK_LENGTH = 8;

	private static final boolean DEFAULT_OFFSET = false;

	private static final boolean DEFAULT_CIRCULAR = false;

	private static final int DEFAULT_Z = -1;

	private final DefaultScale parent;

	/**
	 * To avoid any instantiation
	 * 
	 * @param parent default scale instane
	 */
	DefaultGrid(DefaultScale parent) {
		this.parent = parent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultGrid#isDisplay()
	 */
	@Override
	public boolean isDisplay() {
		return DEFAULT_DISPLAY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultGrid#getColorAsString()
	 */
	@Override
	public String getColorAsString() {
		return Defaults.get().getGlobal().getBorderColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultGrid#getLineWidth()
	 */
	@Override
	public int getLineWidth() {
		return DEFAULT_LINE_WIDTH;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultGrid#isDrawOnChartArea()
	 */
	@Override
	public boolean isDrawOnChartArea() {
		return DEFAULT_DRAW_ON_CHART_AREA;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultGrid#isDrawTicks()
	 */
	@Override
	public boolean isDrawTicks() {
		return DEFAULT_DRAW_TICKS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultGrid#getTickLength()
	 */
	@Override
	public int getTickLength() {
		return DEFAULT_TICK_MARK_LENGTH;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultGrid#isOffset()
	 */
	@Override
	public boolean isOffset() {
		return DEFAULT_OFFSET;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultGrid#isCircular()
	 */
	@Override
	public boolean isCircular() {
		return DEFAULT_CIRCULAR;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultGrid#getZ()
	 */
	@Override
	public int getZ() {
		return DEFAULT_Z;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultGrid#getTickBorderDashOffset()
	 */
	@Override
	public double getTickBorderDashOffset() {
		return parent.getBorder().getDashOffset();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultGrid#getTickColorAsString()
	 */
	@Override
	public String getTickColorAsString() {
		return getColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultGrid#getTickWidth()
	 */
	@Override
	public int getTickWidth() {
		return getLineWidth();
	}

}
