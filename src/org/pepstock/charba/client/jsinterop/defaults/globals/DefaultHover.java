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

import org.pepstock.charba.client.jsinterop.enums.InteractionAxis;
import org.pepstock.charba.client.jsinterop.enums.InteractionMode;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultHover;

/**
 * CHART.JS default values for HOVER element.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 */
public final class DefaultHover implements IsDefaultHover{

	private static final boolean DEFAULT_INTERSECT = true;

	private static final int DEFAULT_ANIMATION_DURATION = 400;

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.hover.IsDefaultHover#getMode()
	 */
	@Override
	public InteractionMode getMode() {
		return InteractionMode.nearest;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.hover.IsDefaultHover#isIntersect()
	 */
	@Override
	public boolean isIntersect() {
		return DEFAULT_INTERSECT;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.hover.IsDefaultHover#getAnimationDuration()
	 */
	@Override
	public int getAnimationDuration() {
		return DEFAULT_ANIMATION_DURATION;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.hover.IsDefaultHover#getAxis()
	 */
	@Override
	public InteractionAxis getAxis() {
		return InteractionAxis.x;
	}

}
