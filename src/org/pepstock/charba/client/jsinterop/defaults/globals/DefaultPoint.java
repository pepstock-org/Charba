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

import org.pepstock.charba.client.enums.PointStyle;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultPoint;

public final class DefaultPoint extends DefaultArc implements IsDefaultPoint{
	
	/// default radius
	private static final double DEFAULT_RADIUS = 3;
	// default background color
	private static final String DEFAULT_BACKGROUND_COLOR = "rgba(0,0,0,0.1)";
	// default border with
	private static final int DEFAULT_BORDER_WIDTH = 1;
	// default border color
	private static final String DEFAULT_BORDER_COLOR = "rgba(0,0,0,0.1)";
	// default hit radius
	private static final double DEFAULT_HIT_RADIUS = 1;
	// default hover radius
	private static final double DEFAULT_HOVER_RADIUS = 4;
	// default hover border width
	private static final int DEFAULT_HOVER_BORDER_WIDTH = 1;

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.DefaultArc#getBackgroundColor()
	 */
	@Override
	public String getBackgroundColorAsString() {
		return DEFAULT_BACKGROUND_COLOR;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.DefaultArc#getBorderWidth()
	 */
	@Override
	public int getBorderWidth() {
		return DEFAULT_BORDER_WIDTH;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.DefaultArc#getBorderColor()
	 */
	@Override
	public String getBorderColorAsString() {
		return DEFAULT_BORDER_COLOR;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.elements.point.IsDefaultPoint#getRadius()
	 */
	@Override
	public double getRadius() {
		return DEFAULT_RADIUS;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.elements.point.IsDefaultPoint#getPointStyle()
	 */
	@Override
	public PointStyle getPointStyle() {
		return PointStyle.circle;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.elements.point.IsDefaultPoint#getHitRadius()
	 */
	@Override
	public double getHitRadius() {
		return DEFAULT_HIT_RADIUS;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.elements.point.IsDefaultPoint#getHoverRadius()
	 */
	@Override
	public double getHoverRadius() {
		return DEFAULT_HOVER_RADIUS;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.elements.point.IsDefaultPoint#getHoverBorderWidth()
	 */
	@Override
	public int getHoverBorderWidth() {
		return DEFAULT_HOVER_BORDER_WIDTH;
	}

}
