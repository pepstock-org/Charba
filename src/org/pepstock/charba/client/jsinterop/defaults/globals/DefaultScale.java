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

import org.pepstock.charba.client.enums.AxisType;
import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.enums.ScaleBounds;
import org.pepstock.charba.client.enums.ScaleDistribution;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultAngleLines;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultGridLines;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultPointLabels;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultScaleLabel;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultTicks;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultTime;

/**
 * CHART.JS default values for scale/axis element.
 * 
 * @author Andrea "Stock" Stocchero
 * @version 2.0
 */
public final class DefaultScale implements IsDefaultScale{
	
	private static final boolean DEFAULT_DISPLAY = true;

	private static final boolean DEFAULT_OFFSET = false;

	private static final int DEFAULT_WEIGHT = 0;
	
	private static final double DEFAULT_BAR_PERCENTAGE = 0.9F;

	private static final double DEFAULT_CATEGORY_PERCENTAGE = 0.8F;

	private static final int DEFAULT_BAR_THICKNESS = 0;

	private static final int DEFAULT_MAX_BAR_THICKNESS = 0;

	private static final boolean DEFAULT_STACKED = false;
	
	private final DefaultAngleLines angleLines = new DefaultAngleLines();
	
	private final DefaultGridLines gridLines = new DefaultGridLines();
	
	private final DefaultPointLabels pointLabels = new DefaultPointLabels();
	
	private final DefaultScaleLabel scaleLabel = new DefaultScaleLabel();
	
	private final DefaultTicks ticks = new DefaultTicks();
	
	private final DefaultTime time = new DefaultTime();

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale#getAngleLines()
	 */
	@Override
	public IsDefaultAngleLines getAngleLines() {
		return angleLines;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale#getGrideLines()
	 */
	@Override
	public IsDefaultGridLines getGrideLines() {
		return gridLines;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale#getPointLabels()
	 */
	@Override
	public IsDefaultPointLabels getPointLabels() {
		return pointLabels;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale#getScaleLabel()
	 */
	@Override
	public IsDefaultScaleLabel getScaleLabel() {
		return scaleLabel;
	}
	

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale#getTicks()
	 */
	@Override
	public IsDefaultTicks getTicks() {
		return ticks;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale#getTime()
	 */
	@Override
	public IsDefaultTime getTime() {
		return time;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale#isStacked()
	 */
	@Override
	public boolean isStacked() {
		return DEFAULT_STACKED;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale#getType()
	 */
	@Override
	public AxisType getType() {
		return AxisType.linear;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale#getWeight()
	 */
	@Override
	public int getWeight() {
		return DEFAULT_WEIGHT;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale#isDisplay()
	 */
	@Override
	public boolean isDisplay() {
		return DEFAULT_DISPLAY;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale#isOffset()
	 */
	@Override
	public boolean isOffset() {
		return DEFAULT_OFFSET;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale#getPosition()
	 */
	@Override
	public Position getPosition() {
		return Position.top;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale#getBarPercentage()
	 */
	@Override
	public double getBarPercentage() {
		return DEFAULT_BAR_PERCENTAGE;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale#getCategoryPercentage()
	 */
	@Override
	public double getCategoryPercentage() {
		return DEFAULT_CATEGORY_PERCENTAGE;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale#getBarThickness()
	 */
	@Override
	public int getBarThickness() {
		return DEFAULT_BAR_THICKNESS;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale#getMaxBarThickness()
	 */
	@Override
	public int getMaxBarThickness() {
		return DEFAULT_MAX_BAR_THICKNESS;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale#getDistribution()
	 */
	@Override
	public ScaleDistribution getDistribution() {
		return ScaleDistribution.linear;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale#getBounds()
	 */
	@Override
	public ScaleBounds getBounds() {
		return ScaleBounds.data;
	}
	
}
