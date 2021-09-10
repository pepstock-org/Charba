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
import org.pepstock.charba.client.defaults.IsDefaultFont;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.enums.Weight;
import org.pepstock.charba.client.items.FontItem;

/**
 * CHART.JS default values for font items.
 * 
 * @author Andrea "Stock" Stocchero
 */
final class DefaultFont implements IsDefaultFont {

	private static final int DEFAULT_FONT_SIZE = 12;

	private static final String DEFAULT_FONT_FAMILY = "'Helvetica Neue', 'Helvetica', 'Arial', sans-serif";

	private static final double DEFAULT_LINE_HEIGHT = 1.2D;

	private static final String DEFAULT_LINE_HEIGHT_STRING = String.valueOf(DEFAULT_LINE_HEIGHT);

	/**
	 * To avoid any instantiation
	 */
	DefaultFont() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultFont#getSize()
	 */
	@Override
	public int getSize() {
		return DEFAULT_FONT_SIZE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultFont#getStyle()
	 */
	@Override
	public FontStyle getStyle() {
		return FontStyle.NORMAL;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultFont#getFamily()
	 */
	@Override
	public String getFamily() {
		return DEFAULT_FONT_FAMILY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultFont#getWeight()
	 */
	@Override
	public Weight getWeight() {
		return Weight.NORMAL;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultFont#getLineHeight()
	 */
	@Override
	public double getLineHeight() {
		return DEFAULT_LINE_HEIGHT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultFont#getLineHeightAsString()
	 */
	@Override
	public String getLineHeightAsString() {
		return DEFAULT_LINE_HEIGHT_STRING;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultFont#create()
	 */
	@Override
	public FontItem create() {
		return Defaults.get().getGlobal().getFont().create();
	}

}
