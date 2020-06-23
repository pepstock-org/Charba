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

import org.pepstock.charba.client.defaults.IsDefaultHover;
import org.pepstock.charba.client.enums.InteractionAxis;
import org.pepstock.charba.client.enums.InteractionMode;

/**
 * CHART.JS default values for HOVER element.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultHover implements IsDefaultHover {

	private static final boolean DEFAULT_INTERSECT = true;

	/**
	 * To avoid any instantiation
	 */
	DefaultHover() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.hover.IsDefaultHover#getMode()
	 */
	@Override
	public InteractionMode getMode() {
		return InteractionMode.NEAREST;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.hover.IsDefaultHover#isIntersect()
	 */
	@Override
	public boolean isIntersect() {
		return DEFAULT_INTERSECT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.hover.IsDefaultHover#getAxis()
	 */
	@Override
	public InteractionAxis getAxis() {
		return InteractionAxis.X;
	}

}
