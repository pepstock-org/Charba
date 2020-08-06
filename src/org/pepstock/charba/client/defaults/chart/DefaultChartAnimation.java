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
import org.pepstock.charba.client.defaults.IsDefaultAnimationMode;
import org.pepstock.charba.client.defaults.globals.DefaultAnimation;
import org.pepstock.charba.client.options.IsAnimationModeKey;

/**
 * Defaults for animation option element, based on chart type.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultChartAnimation extends DefaultChartAnimationMode<IsDefaultAnimation> implements IsDefaultAnimation {

	/**
	 * Creates the object by animation option element instance.
	 * 
	 * @param animation animation option element instance.
	 */
	public DefaultChartAnimation(IsDefaultAnimation animation) {
		super(animation);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimation#isAnimateRotate()
	 */
	@Override
	public boolean isAnimateRotate() {
		return getDefaults().isAnimateRotate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimation#isAnimateScale()
	 */
	@Override
	public boolean isAnimateScale() {
		return getDefaults().isAnimateScale();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimation#hasMode(org.pepstock.charba.client.options.IsAnimationModeKey)
	 */
	@Override
	public boolean hasMode(IsAnimationModeKey mode) {
		return getDefaults().hasMode(mode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimation#getMode(org.pepstock.charba.client.options.IsAnimationModeKey)
	 */
	@Override
	public IsDefaultAnimationMode getMode(IsAnimationModeKey mode) {
		// gets from defaults
		IsDefaultAnimationMode storedMode = getDefaults().getMode(mode);
		// checks if mode is consistent
		if (storedMode != null) {
			// returns a default chart animation mode wrapping the mode
			// from chart
			return new DefaultChartAnimationMode<IsDefaultAnimationMode>(storedMode);
		}
		// if here, the stored collection is not consistent
		return DefaultAnimation.DEFAULT_ANIMATION_MODE;
	}

}
