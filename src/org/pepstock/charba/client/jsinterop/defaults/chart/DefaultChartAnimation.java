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
package org.pepstock.charba.client.jsinterop.defaults.chart;

import org.pepstock.charba.client.enums.Easing;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultAnimation;
import org.pepstock.charba.client.jsinterop.options.Animation;

public final class DefaultChartAnimation implements IsDefaultAnimation{

	private final Animation animation;

	/**
	 * @param animation
	 */
	DefaultChartAnimation(Animation animation) {
		this.animation = animation;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultAnimation#getEasing()
	 */
	@Override
	public Easing getEasing() {
		return animation.getEasing();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultAnimation#getDuration()
	 */
	@Override
	public int getDuration() {
		return animation.getDuration();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultAnimation#isAnimateRotate()
	 */
	@Override
	public boolean isAnimateRotate() {
		return animation.isAnimateRotate();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultAnimation#isAnimateScale()
	 */
	@Override
	public boolean isAnimateScale() {
		return animation.isAnimateScale();
	}

}
