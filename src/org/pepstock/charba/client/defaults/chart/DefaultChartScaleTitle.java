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
import org.pepstock.charba.client.defaults.IsDefaultPadding;
import org.pepstock.charba.client.defaults.IsDefaultScaleTitle;
import org.pepstock.charba.client.enums.ScaleTitleAlign;
import org.pepstock.charba.client.options.ScaleTitle;

/**
 * Defaults for scale title option element, based on chart type.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultChartScaleTitle implements IsDefaultScaleTitle {

	private final ScaleTitle title;

	private final DefaultChartPadding padding;

	/**
	 * Creates the object by scale title option element instance.
	 * 
	 * @param title scale title option element instance.
	 */
	DefaultChartScaleTitle(ScaleTitle title) {
		this.title = title;
		// creates sub element
		this.padding = new DefaultChartPadding(title.getPadding());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScaleTitle#getFont()
	 */
	@Override
	public IsDefaultFont getFont() {
		return title.getFont();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScaleTitle#getColorAsString()
	 */
	@Override
	public String getColorAsString() {
		return title.getColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScaleTitle#getPadding()
	 */
	@Override
	public IsDefaultPadding getPadding() {
		return padding;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScaleTitle#isDisplay()
	 */
	@Override
	public boolean isDisplay() {
		return title.isDisplay();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScaleTitle#getAlign()
	 */
	@Override
	public ScaleTitleAlign getAlign() {
		return title.getAlign();
	}

}
