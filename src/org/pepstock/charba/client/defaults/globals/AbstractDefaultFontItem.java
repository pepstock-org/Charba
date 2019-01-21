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

import org.pepstock.charba.client.defaults.IsDefaultFontItem;
import org.pepstock.charba.client.enums.FontStyle;

/**
 * CHART.JS default values for font items.
 * 
 * @author Andrea "Stock" Stocchero
 */
abstract class AbstractDefaultFontItem implements IsDefaultFontItem {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.options.IsDefaultFontItem#getFontColor()
	 */
	@Override
	public String getFontColorAsString() {
		return DefaultsBuilder.get().getOptions().getDefaultFontColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.options.IsDefaultFontItem#getFontSize()
	 */
	@Override
	public int getFontSize() {
		return DefaultsBuilder.get().getOptions().getDefaultFontSize();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.options.IsDefaultFontItem#getFontStyle()
	 */
	@Override
	public FontStyle getFontStyle() {
		return DefaultsBuilder.get().getOptions().getDefaultFontStyle();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.options.IsDefaultFontItem#getFontFamily()
	 */
	@Override
	public String getFontFamily() {
		return DefaultsBuilder.get().getOptions().getDefaultFontFamily();
	}
}
