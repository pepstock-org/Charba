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
package org.pepstock.charba.client.jsinterop.defaults.globals;

import org.pepstock.charba.client.jsinterop.enums.PointStyle;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultPoint;

/**
 * CHART.JS default values for POINT element.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 */
public final class DefaultPoint extends DefaultArc implements IsDefaultPoint {

	private static final double DEFAULT_RADIUS = 3;

	private static final String DEFAULT_BACKGROUND_COLOR = "rgba(0,0,0,0.1)";

	private static final int DEFAULT_BORDER_WIDTH = 1;

	private static final String DEFAULT_BORDER_COLOR = "rgba(0,0,0,0.1)";

	private static final double DEFAULT_HIT_RADIUS = 1;

	private static final double DEFAULT_HOVER_RADIUS = 4;

	private static final int DEFAULT_HOVER_BORDER_WIDTH = 1;

	private static final double DEFAULT_ROTATION = 0D;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.DefaultArc#getBackgroundColorAsString()
	 */
	@Override
	public String getBackgroundColorAsString() {
		return DEFAULT_BACKGROUND_COLOR;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.DefaultArc#getBorderWidth()
	 */
	@Override
	public int getBorderWidth() {
		return DEFAULT_BORDER_WIDTH;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.DefaultArc#getBorderColorAsString()
	 */
	@Override
	public String getBorderColorAsString() {
		return DEFAULT_BORDER_COLOR;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.options.elements.point.IsDefaultPoint#getRadius()
	 */
	@Override
	public double getRadius() {
		return DEFAULT_RADIUS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.options.elements.point.IsDefaultPoint#getPointStyle()
	 */
	@Override
	public PointStyle getPointStyle() {
		return PointStyle.circle;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.options.elements.point.IsDefaultPoint#getHitRadius()
	 */
	@Override
	public double getHitRadius() {
		return DEFAULT_HIT_RADIUS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.options.elements.point.IsDefaultPoint#getHoverRadius()
	 */
	@Override
	public double getHoverRadius() {
		return DEFAULT_HOVER_RADIUS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.options.elements.point.IsDefaultPoint#getHoverBorderWidth()
	 */
	@Override
	public int getHoverBorderWidth() {
		return DEFAULT_HOVER_BORDER_WIDTH;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultPoint#getRotation()
	 */
	@Override
	public double getRotation() {
		return DEFAULT_ROTATION;
	}

}
