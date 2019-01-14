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

import org.pepstock.charba.client.jsinterop.enums.FontStyle;
import org.pepstock.charba.client.jsinterop.enums.Position;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultTitle;

/**
 * CHART.JS default values for TITLE element.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 */
public final class DefaultTitle extends AbstractDefaultFontItem implements IsDefaultTitle {

	private static final int DEFAULT_PADDING = 10;
	
	private static final boolean DEFAULT_FULL_WIDTH = true;

	private static final double DEFAULT_LINE_HEIGHT = 1.2D;
	
	private static final boolean DEFAULT_DISPLAY = false;

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.title.IsDefaultTitle#isDisplay()
	 */
	@Override
	public boolean isDisplay() {
		return DEFAULT_DISPLAY;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.title.IsDefaultTitle#getPosition()
	 */
	@Override
	public Position getPosition() {
		return Position.top;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.title.IsDefaultTitle#getPadding()
	 */
	@Override
	public int getPadding() {
		return DEFAULT_PADDING;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.title.IsDefaultTitle#isFullWidth()
	 */
	@Override
	public boolean isFullWidth() {
		return DEFAULT_FULL_WIDTH;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.title.IsDefaultTitle#getLineHeight()
	 */
	@Override
	public double getLineHeight() {
		return DEFAULT_LINE_HEIGHT;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.AbstractDefaultFontItem#getFontStyle()
	 */
	@Override
	public FontStyle getFontStyle() {
		return FontStyle.bold;
	}
	
}
