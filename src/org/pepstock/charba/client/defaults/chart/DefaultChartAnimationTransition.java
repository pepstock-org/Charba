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
import org.pepstock.charba.client.defaults.IsDefaultAnimationTransition;
import org.pepstock.charba.client.defaults.IsDefaultAnimations;

/**
 * Defaults for property animation option element.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class DefaultChartAnimationTransition implements IsDefaultAnimationTransition {

	private final IsDefaultAnimationTransition transition;

	/**
	 * Creates the object wrapping a base animation instance.
	 * 
	 * @param transition a base animation transition instance to wrap
	 */
	DefaultChartAnimationTransition(IsDefaultAnimationTransition transition) {
		this.transition = transition;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationTransition#getAnimation()
	 */
	@Override
	public IsDefaultAnimation getAnimation() {
		return transition.getAnimation();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationTransition#getAnimations()
	 */
	@Override
	public IsDefaultAnimations getAnimations() {
		return transition.getAnimations();
	}

}
