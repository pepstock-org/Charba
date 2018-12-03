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
package org.pepstock.charba.client.jsinterop.defaults.globals;

import org.pepstock.charba.client.enums.Easing;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultAnimation;

public final class DefaultAnimation implements IsDefaultAnimation{

	private static final int DEFAULT_DURATION = 1000;

	private static final boolean DEFAULT_ANIMATE_ROTATE = true;

	private static final boolean DEFAULT_ANIMATE_SCALE = false;

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.animation.IsDefaultAnimation#getEasing()
	 */
	@Override
	public Easing getEasing() {
		return Easing.easeOutQuart;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.animation.IsDefaultAnimation#getDuration()
	 */
	@Override
	public int getDuration() {
		return DEFAULT_DURATION;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.animation.IsDefaultAnimation#isAnimateRotate()
	 */
	@Override
	public boolean isAnimateRotate() {
		return DEFAULT_ANIMATE_ROTATE;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.animation.IsDefaultAnimation#isAnimateScale()
	 */
	@Override
	public boolean isAnimateScale() {
		return DEFAULT_ANIMATE_SCALE;
	}

}
