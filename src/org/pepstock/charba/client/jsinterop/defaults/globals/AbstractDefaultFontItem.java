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
package org.pepstock.charba.client.jsinterop.defaults.globals;

import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultFontItem;

abstract class AbstractDefaultFontItem implements IsDefaultFontItem{

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.IsDefaultFontItem#getFontColor()
	 */
	@Override
	public String getFontColorAsString() {
		return DefaultOptions.get().getDefaultFontColorAsString();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.IsDefaultFontItem#getFontSize()
	 */
	@Override
	public int getFontSize() {
		return DefaultOptions.get().getDefaultFontSize();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.IsDefaultFontItem#getFontStyle()
	 */
	@Override
	public FontStyle getFontStyle() {
		return DefaultOptions.get().getDefaultFontStyle();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.IsDefaultFontItem#getFontFamily()
	 */
	@Override
	public String getFontFamily() {
		return DefaultOptions.get().getDefaultFontFamily();
	}
}
