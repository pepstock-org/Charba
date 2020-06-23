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

import org.pepstock.charba.client.defaults.IsDefaultAnimation;
import org.pepstock.charba.client.defaults.IsDefaultAnimationElement;
import org.pepstock.charba.client.enums.Easing;
import org.pepstock.charba.client.enums.InterpolatorType;

/**
 * Defaults for animation option element, based on chart type.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultChartAnimation implements IsDefaultAnimation {

	private final IsDefaultAnimation animation;

	private final DefaultChartAnimationActive animationActive;

	private final DefaultChartAnimationResize animationResize;

	/**
	 * Creates the object by animation option element instance.
	 * 
	 * @param animation animation option element instance.
	 */
	public DefaultChartAnimation(IsDefaultAnimation animation) {
		this.animation = animation;
		this.animationActive = new DefaultChartAnimationActive(animation.getActive());
		this.animationResize = new DefaultChartAnimationResize(animation.getResize());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimation#getActive()
	 */
	@Override
	public IsDefaultAnimationElement getActive() {
		return animationActive;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimation#getResize()
	 */
	@Override
	public IsDefaultAnimationElement getResize() {
		return animationResize;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimation#getEasing()
	 */
	@Override
	public Easing getEasing() {
		return animation.getEasing();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimation#getDuration()
	 */
	@Override
	public int getDuration() {
		return animation.getDuration();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimation#isAnimateRotate()
	 */
	@Override
	public boolean isAnimateRotate() {
		return animation.isAnimateRotate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimation#isAnimateScale()
	 */
	@Override
	public boolean isAnimateScale() {
		return animation.isAnimateScale();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimation#isDebug()
	 */
	@Override
	public boolean isDebug() {
		return animation.isDebug();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimation#getDelay()
	 */
	@Override
	public int getDelay() {
		return animation.getDelay();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimation#isLoop()
	 */
	@Override
	public boolean isLoop() {
		return animation.isLoop();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimation#getType()
	 */
	@Override
	public InterpolatorType getType() {
		return animation.getType();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimation#getFrom()
	 */
	@Override
	public double getFrom() {
		return animation.getFrom();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimation#getFromAsString()
	 */
	@Override
	public String getFromAsString() {
		return animation.getFromAsString();
	}

}
