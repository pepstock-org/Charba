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

import org.pepstock.charba.client.defaults.IsDefaultLine;
import org.pepstock.charba.client.enums.CapStyle;
import org.pepstock.charba.client.enums.CubicInterpolationMode;
import org.pepstock.charba.client.enums.IsFill;
import org.pepstock.charba.client.enums.JoinStyle;
import org.pepstock.charba.client.options.Line;

/**
 * Defaults for line option element, based on chart type.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultChartLine implements IsDefaultLine {

	private final Line line;

	/**
	 * Creates the object by line option element instance.
	 * 
	 * @param line line option element instance.
	 */
	DefaultChartLine(Line line) {
		this.line = line;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultArc#getBackgroundColorAsString()
	 */
	@Override
	public String getBackgroundColorAsString() {
		return line.getBackgroundColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultArc#getBorderWidth()
	 */
	@Override
	public int getBorderWidth() {
		return line.getBorderWidth();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultArc#getBorderColorAsString()
	 */
	@Override
	public String getBorderColorAsString() {
		return line.getBorderColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultLine#getTension()
	 */
	@Override
	public double getTension() {
		return line.getTension();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultLine#getBorderCapStyle()
	 */
	@Override
	public CapStyle getBorderCapStyle() {
		return line.getBorderCapStyle();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultLine#getBorderDashOffset()
	 */
	@Override
	public int getBorderDashOffset() {
		return line.getBorderDashOffset();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultLine#getBorderJoinStyle()
	 */
	@Override
	public JoinStyle getBorderJoinStyle() {
		return line.getBorderJoinStyle();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultLine#isCapBezierPoints()
	 */
	@Override
	public boolean isCapBezierPoints() {
		return line.isCapBezierPoints();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultLine#getCubicInterpolationMode()
	 */
	@Override
	public CubicInterpolationMode getCubicInterpolationMode() {
		return line.getCubicInterpolationMode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultLine#getFill()
	 */
	@Override
	public IsFill getFill() {
		return line.getFill();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultLine#isStepped()
	 */
	@Override
	public boolean isStepped() {
		return line.isStepped();
	}

}
