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
import org.pepstock.charba.client.defaults.IsDefaultAnimationMode;
import org.pepstock.charba.client.options.IsAnimationModeKey;

/**
 * CHART.JS default values for ANIMATION element.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultAnimation extends DefaultAnimationMode implements IsDefaultAnimation {

	private static final boolean DEFAULT_ANIMATE_ROTATE = true;

	private static final boolean DEFAULT_ANIMATE_SCALE = false;

	private static final DefaultAnimationMode DEFAULT_ANIMATION_MODE_ELEMENT = new DefaultAnimationMode();

	/**
	 * To avoid any instantiation
	 */

	DefaultAnimation() {
		// do nothing
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
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimation#getMode(org.pepstock.charba.client.options.IsAnimationMode)
	 */
	@Override
	public IsDefaultAnimationMode getMode(IsAnimationModeKey mode) {
		// FIXME checks if make sense to add real default https://github.com/chartjs/Chart.js/blob/2b40e04a4bc638abc4477e5bd813a7614b166bf9/src/core/core.animations.js#L9
		return DEFAULT_ANIMATION_MODE_ELEMENT;
	}

}
