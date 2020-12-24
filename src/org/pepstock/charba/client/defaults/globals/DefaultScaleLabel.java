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

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.commons.Constants;
import org.pepstock.charba.client.defaults.IsDefaultFont;
import org.pepstock.charba.client.defaults.IsDefaultPadding;
import org.pepstock.charba.client.defaults.IsDefaultScaleLabel;
import org.pepstock.charba.client.enums.ScaleLabelAlign;

/**
 * CHART.JS default values for SCALELABEL element.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultScaleLabel implements IsDefaultScaleLabel {

	private static final boolean DEFAULT_DISPLAY = false;

	private static final String DEFAULT_LABEL_STRING = Constants.EMPTY_STRING;

	private final DefaultScaleLabelPadding padding = new DefaultScaleLabelPadding();

	private final DefaultRoutedFont font = new DefaultRoutedFont();

	/**
	 * To avoid any instantiation
	 */
	DefaultScaleLabel() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScaleLabel#getFont()
	 */
	@Override
	public IsDefaultFont getFont() {
		return font;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScaleLabel#getPadding()
	 */
	@Override
	public IsDefaultPadding getPadding() {
		return padding;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScaleLabel#getColorAsString()
	 */
	@Override
	public String getColorAsString() {
		return Defaults.get().getGlobal().getColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScaleLabel#isDisplay()
	 */
	@Override
	public boolean isDisplay() {
		return DEFAULT_DISPLAY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScaleLabel#getLabelString()
	 */
	@Override
	public String getLabelString() {
		return DEFAULT_LABEL_STRING;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScaleLabel#getAlign()
	 */
	@Override
	public ScaleLabelAlign getAlign() {
		return ScaleLabelAlign.CENTER;
	}

}
