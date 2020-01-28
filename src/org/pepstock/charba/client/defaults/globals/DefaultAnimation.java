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

/**
 * CHART.JS default values for ANIMATION element.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultAnimation implements IsDefaultAnimation {

	private static final int DEFAULT_DURATION = 1000;

	private static final boolean DEFAULT_ANIMATE_ROTATE = true;

	private static final boolean DEFAULT_ANIMATE_SCALE = false;

	private final DefaultAnimationActive active = new DefaultAnimationActive();

	private final DefaultAnimationResize resize = new DefaultAnimationResize();

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

}
