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

import org.pepstock.charba.client.defaults.IsDefaultRectangle;
import org.pepstock.charba.client.enums.BorderSkipped;

/**
 * CHART.JS default values for RECTANGLE element.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultRectangle extends AbstractDefaultOptionsElement implements IsDefaultRectangle {

	private static final String DEFAULT_BACKGROUND_COLOR = DefaultOptions.DEFAULT_COLOR;

	private static final int DEFAULT_BORDER_WIDTH = 0;

	private static final String DEFAULT_BORDER_COLOR = DefaultOptions.DEFAULT_COLOR;

	/**
	 * To avoid any instantiation
	 */
	DefaultRectangle() {
		super(DEFAULT_BACKGROUND_COLOR, DEFAULT_BORDER_COLOR, DEFAULT_BORDER_WIDTH);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.elements.rectangle.IsDefaultRectangle#getBorderSkipped()
	 */
	@Override
	public BorderSkipped getBorderSkipped() {
		return BorderSkipped.START;
	}

}
