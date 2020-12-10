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

import org.pepstock.charba.client.defaults.IsDefaultBar;
import org.pepstock.charba.client.enums.BorderSkipped;

/**
 * CHART.JS default values for BAR element.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultBar extends AbstractDefaultOptionsElement implements IsDefaultBar {

	private static final String DEFAULT_BACKGROUND_COLOR = DefaultOptions.DEFAULT_BACKGROUND_COLOR;

	private static final int DEFAULT_BORDER_WIDTH = 0;

	private static final int DEFAULT_BORDER_RADIUS = 0;

	private static final String DEFAULT_BORDER_COLOR = DefaultOptions.DEFAULT_BORDER_COLOR;

	/**
	 * To avoid any instantiation
	 */
	DefaultBar() {
		super(DEFAULT_BACKGROUND_COLOR, DEFAULT_BORDER_COLOR, DEFAULT_BORDER_WIDTH);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultBar#getBorderSkipped()
	 */
	@Override
	public BorderSkipped getBorderSkipped() {
		return BorderSkipped.START;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultBar#getBorderRadius()
	 */
	@Override
	public int getBorderRadius() {
		return DEFAULT_BORDER_RADIUS;
	}

}
