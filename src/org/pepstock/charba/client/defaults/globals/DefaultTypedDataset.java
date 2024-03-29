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

import org.pepstock.charba.client.defaults.IsDefaultAnimation;
import org.pepstock.charba.client.defaults.IsDefaultAnimations;
import org.pepstock.charba.client.defaults.IsDefaultTransitions;
import org.pepstock.charba.client.defaults.IsDefaultTypedDataset;

/**
 * CHART.JS default values for DATASETS element.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultTypedDataset implements IsDefaultTypedDataset {

	private final IsDefaultAnimation animation = new DefaultAnimation();

	private final IsDefaultAnimations animations = new DefaultAnimations();

	private final IsDefaultTransitions transitions = new DefaultTransitions();

	/**
	 * If set to 'flex', the base sample widths are calculated automatically based on the previous and following samples so that they take the full available widths without
	 * overlap.<br>
	 * Then, bars are sized using barPercentage and categoryPercentage.<br>
	 * There is no gap when the percentage options are 1.<br>
	 * This mode generates bars with different widths when data are not evenly spaced, {@link Integer#MIN_VALUE}.
	 */
	public static final int FLEX_BAR_THICKNESS = Integer.MIN_VALUE;

	/**
	 * The value to set to have the base sample widths are calculated automatically based on the previous and following samples so that they take the full available widths without
	 * overlap.<br>
	 * Then, bars are sized using barPercentage and categoryPercentage.<br>
	 * There is no gap when the percentage options are 1.<br>
	 * This mode generates bars with different widths when data are not evenly spaced, <b>{@value FLEX_BAR_THICKNESS_VALUE}</b>.
	 */
	public static final String FLEX_BAR_THICKNESS_VALUE = "flex";

	private static final double DEFAULT_BAR_PERCENTAGE = 0.9D;

	private static final double DEFAULT_CATEGORY_PERCENTAGE = 0.8D;

	private static final int DEFAULT_BAR_THICKNESS = 0;

	private static final int DEFAULT_MAX_BAR_THICKNESS = 0;

	private static final int DEFAULT_MIN_BAR_LENGTH = 0;

	private static final boolean DEFAULT_GROUPED = true;

	private static final boolean DEFAULT_SHOW_LINE = true;

	/**
	 * To avoid any instantiation
	 */
	DefaultTypedDataset() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationContainer#getAnimation()
	 */
	@Override
	public IsDefaultAnimation getAnimation() {
		return animation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationContainer#getTransitions()
	 */
	@Override
	public IsDefaultTransitions getTransitions() {
		return transitions;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationTransition#getAnimations()
	 */
	@Override
	public IsDefaultAnimations getAnimations() {
		return animations;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultDatasets#getBarPercentage()
	 */
	@Override
	public double getBarPercentage() {
		return DEFAULT_BAR_PERCENTAGE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultDatasets#getCategoryPercentage()
	 */
	@Override
	public double getCategoryPercentage() {
		return DEFAULT_CATEGORY_PERCENTAGE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultDatasets#getBarThickness()
	 */
	@Override
	public int getBarThickness() {
		return DEFAULT_BAR_THICKNESS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultDatasets#getMaxBarThickness()
	 */
	@Override
	public int getMaxBarThickness() {
		return DEFAULT_MAX_BAR_THICKNESS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultDatasets#getMinBarLength()
	 */
	@Override
	public int getMinBarLength() {
		return DEFAULT_MIN_BAR_LENGTH;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultBarDatasets#isGrouped()
	 */
	@Override
	public boolean isGrouped() {
		return DEFAULT_GROUPED;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultDatasets#isShowLine()
	 */
	@Override
	public boolean isShowLine() {
		return DEFAULT_SHOW_LINE;
	}

}