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

/**
 * Defaults for hover option element, based on chart type.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultChartHover implements IsDefaultHover {

	private final IsDefaultHover hover;

	/**
	 * Creates the object by hover option element instance.
	 * 
	 * @param hover hover option element instance.
	 */
	public DefaultChartHover(IsDefaultHover hover) {
		this.hover = hover;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultHover#getMode()
	 */
	@Override
	public InteractionMode getMode() {
		return hover.getMode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultHover#isIntersect()
	 */
	@Override
	public boolean isIntersect() {
		return hover.isIntersect();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultHover#getAxis()
	 */
	@Override
	public InteractionAxis getAxis() {
		return hover.getAxis();
	}

}
