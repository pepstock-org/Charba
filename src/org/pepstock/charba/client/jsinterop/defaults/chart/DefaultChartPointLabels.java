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

import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultPointLabels;
import org.pepstock.charba.client.jsinterop.options.PointLabels;

/**
 * Defaults for point labels option element, based on chart type.
 * 
 * @author Andrea "Stock" Stocchero
 * @version 2.0
 */
public final class DefaultChartPointLabels implements IsDefaultPointLabels {

	private final PointLabels pointLabels;

	/**
	 * Creates the object by point labels option element instance.
	 * 
	 * @param pointLabels point labels option element instance.
	 */
	DefaultChartPointLabels(PointLabels pointLabels) {
		this.pointLabels = pointLabels;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultFontItem#getFontColorAsString()
	 */
	@Override
	public String getFontColorAsString() {
		return pointLabels.getFontColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultFontItem#getFontSize()
	 */
	@Override
	public int getFontSize() {
		return pointLabels.getFontSize();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultFontItem#getFontStyle()
	 */
	@Override
	public FontStyle getFontStyle() {
		return pointLabels.getFontStyle();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultFontItem#getFontFamily()
	 */
	@Override
	public String getFontFamily() {
		return pointLabels.getFontFamily();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultPointLabels#isDisplay()
	 */
	@Override
	public boolean isDisplay() {
		return pointLabels.isDisplay();
	}

}
