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

import org.pepstock.charba.client.jsinterop.defaults.IsDefaultLayout;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultPadding;
import org.pepstock.charba.client.jsinterop.options.Layout;

/**
 * Defaults for layout option element, based on chart type.
 * 
 * @author Andrea "Stock" Stocchero
 * @version 2.0
 */
public final class DefaultChartLayout implements IsDefaultLayout {

	private final DefaultChartPadding padding;

	/**
	 * Creates the object by layout option element instance.
	 * 
	 * @param layout layout option element instance.
	 */
	DefaultChartLayout(Layout layout) {
		padding = new DefaultChartPadding(layout.getPadding());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultLayout#getPadding()
	 */
	@Override
	public IsDefaultPadding getPadding() {
		return padding;
	}
}
