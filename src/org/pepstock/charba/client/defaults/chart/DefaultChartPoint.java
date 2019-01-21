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

import org.pepstock.charba.client.defaults.IsDefaultPoint;
import org.pepstock.charba.client.enums.PointStyle;
import org.pepstock.charba.client.options.Point;

/**
 * Defaults for point option element, based on chart type.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultChartPoint implements IsDefaultPoint {

	private final Point point;

	/**
	 * Creates the object by point option element instance.
	 * 
	 * @param point point option element instance.
	 */
	DefaultChartPoint(Point point) {
		this.point = point;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultArc#getBackgroundColorAsString()
	 */
	@Override
	public String getBackgroundColorAsString() {
		return point.getBackgroundColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultArc#getBorderWidth()
	 */
	@Override
	public int getBorderWidth() {
		return point.getBorderWidth();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultArc#getBorderColorAsString()
	 */
	@Override
	public String getBorderColorAsString() {
		return point.getBorderColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultPoint#getRadius()
	 */
	@Override
	public double getRadius() {
		return point.getRadius();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultPoint#getPointStyle()
	 */
	@Override
	public PointStyle getPointStyle() {
		return point.getPointStyle();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultPoint#getHitRadius()
	 */
	@Override
	public double getHitRadius() {
		return point.getHitRadius();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultPoint#getHoverRadius()
	 */
	@Override
	public double getHoverRadius() {
		return point.getHoverRadius();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultPoint#getHoverBorderWidth()
	 */
	@Override
	public int getHoverBorderWidth() {
		return point.getHoverBorderWidth();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultPoint#getRotation()
	 */
	@Override
	public double getRotation() {
		return point.getRotation();
	}
}
