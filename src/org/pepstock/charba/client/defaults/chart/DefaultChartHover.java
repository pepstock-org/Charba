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

import org.pepstock.charba.client.defaults.IsDefaultHover;
import org.pepstock.charba.client.enums.InteractionAxis;
import org.pepstock.charba.client.enums.InteractionMode;
import org.pepstock.charba.client.options.Hover;

/**
 * Defaults for hover option element, based on chart type.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 */
public final class DefaultChartHover implements IsDefaultHover {

	private final Hover hover;

	/**
	 * Creates the object by hover option element instance.
	 * 
	 * @param hover hover option element instance.
	 */
	DefaultChartHover(Hover hover) {
		this.hover = hover;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultHover#getMode()
	 */
	@Override
	public InteractionMode getMode() {
		return hover.getMode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultHover#isIntersect()
	 */
	@Override
	public boolean isIntersect() {
		return hover.isIntersect();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultHover#getAnimationDuration()
	 */
	@Override
	public int getAnimationDuration() {
		return hover.getAnimationDuration();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultHover#getAxis()
	 */
	@Override
	public InteractionAxis getAxis() {
		return hover.getAxis();
	}

}
