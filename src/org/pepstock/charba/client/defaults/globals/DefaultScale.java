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

import org.pepstock.charba.client.defaults.IsDefaultAdapters;
import org.pepstock.charba.client.defaults.IsDefaultAngleLines;
import org.pepstock.charba.client.defaults.IsDefaultGridLines;
import org.pepstock.charba.client.defaults.IsDefaultPointLabels;
import org.pepstock.charba.client.defaults.IsDefaultScale;
import org.pepstock.charba.client.defaults.IsDefaultScaleLabel;
import org.pepstock.charba.client.defaults.IsDefaultTicks;
import org.pepstock.charba.client.defaults.IsDefaultTime;
import org.pepstock.charba.client.enums.Display;
import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.enums.ScaleBounds;
import org.pepstock.charba.client.enums.ScaleDistribution;

/**
 * CHART.JS default values for scale/axis element.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultScale implements IsDefaultScale {

	private static final boolean DEFAULT_OFFSET = false;

	private static final double DEFAULT_WEIGHT = 0D;

	private static final boolean DEFAULT_STACKED = false;

	private static final boolean DEFAULT_BEGIN_AT_ZERO = true;

	private static final boolean DEFAULT_REVERSE = false;

	private static final double DEFAULT_MIN = Double.MIN_VALUE;

	private static final double DEFAULT_MAX = Double.MAX_VALUE;

	private static final double DEFAULT_SUGGESTED_MAX = Double.MAX_VALUE;

	private static final double DEFAULT_SUGGESTED_MIN = Double.MIN_VALUE;

	private final DefaultAngleLines angleLines = new DefaultAngleLines();

	private final DefaultGridLines gridLines = new DefaultGridLines();

	private final DefaultPointLabels pointLabels = new DefaultPointLabels();

	private final DefaultScaleLabel scaleLabel = new DefaultScaleLabel();

	private final DefaultTicks ticks = new DefaultTicks();

	private final DefaultTime time = new DefaultTime();

	private final DefaultAdapters adapters = new DefaultAdapters();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScale#getAngleLines()
	 */
	@Override
	public IsDefaultAngleLines getAngleLines() {
		return angleLines;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScale#getGrideLines()
	 */
	@Override
	public IsDefaultGridLines getGrideLines() {
		return gridLines;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScale#getPointLabels()
	 */
	@Override
	public IsDefaultPointLabels getPointLabels() {
		return pointLabels;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScale#getScaleLabel()
	 */
	@Override
	public IsDefaultScaleLabel getScaleLabel() {
		return scaleLabel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScale#getTicks()
	 */
	@Override
	public IsDefaultTicks getTicks() {
		return ticks;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScale#getTime()
	 */
	@Override
	public IsDefaultTime getTime() {
		return time;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScale#getAdapters()
	 */
	@Override
	public IsDefaultAdapters getAdapters() {
		return adapters;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScale#isStacked()
	 */
	@Override
	public boolean isStacked() {
		return DEFAULT_STACKED;
	}

//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see org.pepstock.charba.client.defaults.IsDefaultScale#getType()
//	 */
//	@Override
//	public AxisType getType() {
//		return AxisType.LINEAR;
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see org.pepstock.charba.client.defaults.IsDefaultScale#getAxis()
//	 */
//	@Override
//	public CartesianAxisType getAxis() {
//		return getType().getDefaultCartesianAxisType();
//	}
//
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScale#getWeight()
	 */
	@Override
	public double getWeight() {
		return DEFAULT_WEIGHT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScale#isDisplay()
	 */
	@Override
	public Display getDisplay() {
		return Display.TRUE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScale#isOffset()
	 */
	@Override
	public boolean isOffset() {
		return DEFAULT_OFFSET;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScale#getPosition()
	 */
	@Override
	public Position getPosition() {
		return Position.TOP;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScale#getDistribution()
	 */
	@Override
	public ScaleDistribution getDistribution() {
		return ScaleDistribution.LINEAR;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScale#getBounds()
	 */
	@Override
	public ScaleBounds getBounds() {
		return ScaleBounds.DATA;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScale#isBeginAtZero()
	 */
	@Override
	public boolean isBeginAtZero() {
		return DEFAULT_BEGIN_AT_ZERO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScale#getMin()
	 */
	@Override
	public double getMin() {
		return DEFAULT_MIN;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScale#getMax()
	 */
	@Override
	public double getMax() {
		return DEFAULT_MAX;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScale#getSuggestedMax()
	 */
	@Override
	public double getSuggestedMax() {
		return DEFAULT_SUGGESTED_MAX;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScale#getSuggestedMin()
	 */
	@Override
	public double getSuggestedMin() {
		return DEFAULT_SUGGESTED_MIN;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScale#isReverse()
	 */
	@Override
	public boolean isReverse() {
		return DEFAULT_REVERSE;
	}

}
