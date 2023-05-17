/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.defaults.globals;

import org.pepstock.charba.client.colors.HtmlColor;
import org.pepstock.charba.client.defaults.IsDefaultAdapters;
import org.pepstock.charba.client.defaults.IsDefaultAngleLines;
import org.pepstock.charba.client.defaults.IsDefaultGrid;
import org.pepstock.charba.client.defaults.IsDefaultPointLabels;
import org.pepstock.charba.client.defaults.IsDefaultScale;
import org.pepstock.charba.client.defaults.IsDefaultScaleBorder;
import org.pepstock.charba.client.defaults.IsDefaultScaleTitle;
import org.pepstock.charba.client.defaults.IsDefaultTicks;
import org.pepstock.charba.client.defaults.IsDefaultTime;
import org.pepstock.charba.client.enums.AxisPosition;
import org.pepstock.charba.client.enums.Bounds;
import org.pepstock.charba.client.enums.Display;
import org.pepstock.charba.client.items.AxisPositionItem;
import org.pepstock.charba.client.items.Undefined;

/**
 * CHART.JS default values for scale/axis element.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultScale implements IsDefaultScale {

	private static final boolean DEFAULT_ALIGN_TO_PIXEL = false;

	private static final boolean DEFAULT_ANIMATE = true;

	private static final boolean DEFAULT_OFFSET = false;

	private static final double DEFAULT_WEIGHT = 0D;

	private static final double DEFAULT_START_ANGLE = 0D;

	private static final boolean DEFAULT_STACKED = false;

	private static final boolean DEFAULT_SINGLE_STACKED = false;

	private static final boolean DEFAULT_BEGIN_AT_ZERO = true;

	private static final boolean DEFAULT_REVERSE = false;

	private static final int DEFAULT_GRACE = 0;

	private static final double DEFAULT_GRACE_AS_PERCENTAGE = 0;

	private static final double DEFAULT_MIN = Double.MIN_VALUE;

	private static final double DEFAULT_MAX = Double.MAX_VALUE;

	private static final double DEFAULT_SUGGESTED_MAX = Double.MAX_VALUE;

	private static final double DEFAULT_SUGGESTED_MIN = Double.MIN_VALUE;

	private static final String DEFAULT_BACKGROUND_COLOR = HtmlColor.TRANSPARENT.toRGBA();

	private static final String DEFAULT_STACK = Undefined.STRING;

	private static final double DEFAULT_STACK_WEIGHT = 1D;

	private static final boolean DEFAULT_OFFSET_AFTER_AUTOSKIP = false;

	private final DefaultGrid grid;

	private final DefaultAngleLines angleLines = new DefaultAngleLines();

	private final DefaultPointLabels pointLabels = new DefaultPointLabels();

	private final DefaultScaleBorder border = new DefaultScaleBorder();

	private final DefaultScaleTitle title = new DefaultScaleTitle();

	private final DefaultTicks ticks = new DefaultTicks();

	private final DefaultTime time = new DefaultTime();

	private final DefaultAdapters adapters = new DefaultAdapters();

	/**
	 * To avoid any instantiation
	 */
	DefaultScale() {
		this.grid = new DefaultGrid(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScale#getBorder()
	 */
	@Override
	public IsDefaultScaleBorder getBorder() {
		return border;
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
	 * @see org.pepstock.charba.client.defaults.IsDefaultScale#getGrid()
	 */
	@Override
	public IsDefaultGrid getGrid() {
		return grid;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScale#isSingleStacked()
	 */
	@Override
	public boolean isSingleStacked() {
		return DEFAULT_SINGLE_STACKED;
	}

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
	public AxisPosition getPosition() {
		return AxisPosition.TOP;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScale#getPositionAsItem()
	 */
	@Override
	public AxisPositionItem getPositionAsItem() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScale#getBounds()
	 */
	@Override
	public Bounds getBounds() {
		return Bounds.TICKS;
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
	 * @see org.pepstock.charba.client.defaults.IsDefaultScale#getGrace()
	 */
	@Override
	public int getGrace() {
		return DEFAULT_GRACE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScale#getGraceAsPercentage()
	 */
	@Override
	public double getGraceAsPercentage() {
		return DEFAULT_GRACE_AS_PERCENTAGE;
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
	 * @see org.pepstock.charba.client.defaults.IsDefaultScale#isAlignToPixels()
	 */
	@Override
	public boolean isAlignToPixels() {
		return DEFAULT_ALIGN_TO_PIXEL;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScale#isAnimate()
	 */
	@Override
	public boolean isAnimate() {
		return DEFAULT_ANIMATE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScale#getStartAngle()
	 */
	@Override
	public double getStartAngle() {
		return DEFAULT_START_ANGLE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScale#getBackgroundColorAsString()
	 */
	@Override
	public String getBackgroundColorAsString() {
		return DEFAULT_BACKGROUND_COLOR;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScale#getStack()
	 */
	@Override
	public String getStack() {
		return DEFAULT_STACK;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScale#getStackWeight()
	 */
	@Override
	public double getStackWeight() {
		return DEFAULT_STACK_WEIGHT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScale#isOffsetAfterAutoskip()
	 */
	@Override
	public boolean isOffsetAfterAutoskip() {
		return DEFAULT_OFFSET_AFTER_AUTOSKIP;
	}

}