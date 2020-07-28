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

import org.pepstock.charba.client.defaults.IsDefaultFont;
import org.pepstock.charba.client.defaults.IsDefaultLegendTitle;

/**
 * Defaults for legend title option element, based on chart type.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultChartLegendTitle implements IsDefaultLegendTitle {

	private final IsDefaultLegendTitle title;

	/**
	 * Creates the object by legend title option element instance.
	 * 
	 * @param title legend title option element instance.
	 */
	DefaultChartLegendTitle(IsDefaultLegendTitle title) {
		this.title = title;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultLegendLabels#getFont()
	 */
	@Override
	public IsDefaultFont getFont() {
		return title.getFont();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultLegendTitle#isDisplay()
	 */
	@Override
	public boolean isDisplay() {
		return title.isDisplay();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultLegendTitle#getPadding()
	 */
	@Override
	public int getPadding() {
		return title.getPadding();
	}

}