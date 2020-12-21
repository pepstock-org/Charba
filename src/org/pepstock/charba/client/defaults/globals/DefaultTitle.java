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

import org.pepstock.charba.client.defaults.IsDefaultFont;
import org.pepstock.charba.client.defaults.IsDefaultTitle;
import org.pepstock.charba.client.enums.ElementAlign;
import org.pepstock.charba.client.enums.Position;

/**
 * CHART.JS default values for TITLE element.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultTitle implements IsDefaultTitle {

	private static final int DEFAULT_PADDING = 10;

	private static final boolean DEFAULT_FULL_WIDTH = true;

	private static final boolean DEFAULT_DISPLAY = false;

	private final DefaultFont font = new DefaultFont();

	/**
	 * To avoid any instantiation
	 */
	DefaultTitle() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTitle#getFont()
	 */
	@Override
	public IsDefaultFont getFont() {
		return font;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTitle#getColorAsString()
	 */
	@Override
	public String getColorAsString() {
		return DefaultOptions.DEFAULT_COLOR;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.title.IsDefaultTitle#isDisplay()
	 */
	@Override
	public boolean isDisplay() {
		return DEFAULT_DISPLAY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.title.IsDefaultTitle#getPosition()
	 */
	@Override
	public Position getPosition() {
		return Position.TOP;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.title.IsDefaultTitle#getPadding()
	 */
	@Override
	public int getPadding() {
		return DEFAULT_PADDING;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.title.IsDefaultTitle#isFullWidth()
	 */
	@Override
	public boolean isFullWidth() {
		return DEFAULT_FULL_WIDTH;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTitle#getAlign()
	 */
	@Override
	public ElementAlign getAlign() {
		return ElementAlign.CENTER;
	}

}
