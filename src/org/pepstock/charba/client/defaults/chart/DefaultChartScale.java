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

import org.pepstock.charba.client.defaults.IsDefaultAdapters;
import org.pepstock.charba.client.defaults.IsDefaultAngleLines;
import org.pepstock.charba.client.defaults.IsDefaultGrid;
import org.pepstock.charba.client.defaults.IsDefaultPointLabels;
import org.pepstock.charba.client.defaults.IsDefaultScale;
import org.pepstock.charba.client.defaults.IsDefaultScaleTitle;
import org.pepstock.charba.client.defaults.IsDefaultTicks;
import org.pepstock.charba.client.defaults.IsDefaultTime;
import org.pepstock.charba.client.enums.AxisPosition;
import org.pepstock.charba.client.enums.Bounds;
import org.pepstock.charba.client.enums.Display;
import org.pepstock.charba.client.options.Scale;

/**
 * Defaults for scale/axis option element, based on chart type.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultChartScale implements IsDefaultScale {

	private final Scale scale;

	private final DefaultChartScaleTitle title;

	private final DefaultChartTicks ticks;

	private final DefaultChartGrid grid;

	private final DefaultChartAngleLines angleLines;

	private final DefaultChartPointLabels pointLabels;

	private final DefaultChartTime time;

	private final DefaultChartAdapters adapters;

	/**
	 * Creates the object by scale option element instance.
	 * 
	 * @param scale scale option element instance.
	 */
	public DefaultChartScale(Scale scale) {
		this.scale = scale;
		// creates sub elements
		this.title = new DefaultChartScaleTitle(scale.getTitle());
		this.ticks = new DefaultChartTicks(scale.getTicks());
		this.grid = new DefaultChartGrid(scale.getGrid());
		this.angleLines = new DefaultChartAngleLines(scale.getAngleLines());
		this.pointLabels = new DefaultChartPointLabels(scale.getPointLabels());
		this.time = new DefaultChartTime(scale.getTime());
		this.adapters = new DefaultChartAdapters(scale.getAdapters());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScale#getTitle()
	 */
	@Override
	public IsDefaultScaleTitle getTitle() {
		return title;
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
	 * @see org.pepstock.charba.client.defaults.IsDefaultScale#getGrid()
	 */
	@Override
	public IsDefaultGrid getGrid() {
		return grid;
	}

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
	 * @see org.pepstock.charba.client.defaults.IsDefaultScale#getPointLabels()
	 */
	@Override
	public IsDefaultPointLabels getPointLabels() {
		return pointLabels;
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
		return scale.isStacked();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScale#isSingleStacked()
	 */
	@Override
	public boolean isSingleStacked() {
		return scale.isSingleStacked();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScale#getWeight()
	 */
	@Override
	public double getWeight() {
		return scale.getWeight();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScale#getDisplay()
	 */
	@Override
	public Display getDisplay() {
		return scale.getDisplay();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScale#isOffset()
	 */
	@Override
	public boolean isOffset() {
		return scale.isOffset();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScale#getPosition()
	 */
	@Override
	public AxisPosition getPosition() {
		return scale.getPosition();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScale#getBounds()
	 */
	@Override
	public Bounds getBounds() {
		return scale.getBounds();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScale#isBeginAtZero()
	 */
	@Override
	public boolean isBeginAtZero() {
		return scale.isBeginAtZero();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScale#getGrace()
	 */
	@Override
	public int getGrace() {
		return scale.getGrace();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScale#getGraceAsPercentage()
	 */
	@Override
	public String getGraceAsPercentage() {
		return scale.getGraceAsPercentage();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScale#getMin()
	 */
	@Override
	public double getMin() {
		return scale.getMin();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScale#getMax()
	 */
	@Override
	public double getMax() {
		return scale.getMax();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScale#getSuggestedMax()
	 */
	@Override
	public double getSuggestedMax() {
		return scale.getSuggestedMax();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScale#getSuggestedMin()
	 */
	@Override
	public double getSuggestedMin() {
		return scale.getSuggestedMin();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScale#isReverse()
	 */
	@Override
	public boolean isReverse() {
		return scale.isReverse();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScale#isAnimate()
	 */
	@Override
	public boolean isAnimate() {
		return scale.isAnimate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScale#getStartAngle()
	 */
	@Override
	public double getStartAngle() {
		return scale.getStartAngle();
	}

}
