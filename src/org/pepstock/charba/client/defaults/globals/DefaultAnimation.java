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

import org.pepstock.charba.client.defaults.IsDefaultAnimation;
import org.pepstock.charba.client.defaults.IsDefaultAnimationElement;
import org.pepstock.charba.client.enums.Easing;
import org.pepstock.charba.client.enums.InterpolatorType;
import org.pepstock.charba.client.items.UndefinedValues;

/**
 * CHART.JS default values for ANIMATION element.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultAnimation implements IsDefaultAnimation {

	private static final int DEFAULT_DURATION = 1000;

	private static final boolean DEFAULT_ANIMATE_ROTATE = true;

	private static final boolean DEFAULT_ANIMATE_SCALE = false;

	private static final boolean DEFAULT_DEBUG = false;

	private static final int DEFAULT_DELAY = UndefinedValues.INTEGER;

	private static final boolean DEFAULT_LOOP = false;

	private static final double DEFAULT_FROM = UndefinedValues.DOUBLE;

	private static final String DEFAULT_FROM_AS_STRING = UndefinedValues.STRING;

	private final DefaultAnimationActive active = new DefaultAnimationActive();

	private final DefaultAnimationResize resize = new DefaultAnimationResize();

	/**
	 * To avoid any instantiation
	 */

	DefaultAnimation() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimation#getActive()
	 */
	@Override
	public IsDefaultAnimationElement getActive() {
		return active;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimation#getResize()
	 */
	@Override
	public IsDefaultAnimationElement getResize() {
		return resize;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.animation.IsDefaultAnimation#getEasing()
	 */
	@Override
	public Easing getEasing() {
		return Easing.EASE_OUT_QUART;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.animation.IsDefaultAnimation#getDuration()
	 */
	@Override
	public int getDuration() {
		return DEFAULT_DURATION;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.animation.IsDefaultAnimation#isAnimateRotate()
	 */
	@Override
	public boolean isAnimateRotate() {
		return DEFAULT_ANIMATE_ROTATE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.animation.IsDefaultAnimation#isAnimateScale()
	 */
	@Override
	public boolean isAnimateScale() {
		return DEFAULT_ANIMATE_SCALE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimation#isDebug()
	 */
	@Override
	public boolean isDebug() {
		return DEFAULT_DEBUG;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimation#getDelay()
	 */
	@Override
	public int getDelay() {
		return DEFAULT_DELAY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimation#isLoop()
	 */
	@Override
	public boolean isLoop() {
		return DEFAULT_LOOP;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimation#getType()
	 */
	@Override
	public InterpolatorType getType() {
		return InterpolatorType.UNKNOWN;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimation#getFrom()
	 */
	@Override
	public double getFrom() {
		return DEFAULT_FROM;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimation#getFromAsString()
	 */
	@Override
	public String getFromAsString() {
		return DEFAULT_FROM_AS_STRING;
	}

}
