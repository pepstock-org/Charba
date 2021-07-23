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
import org.pepstock.charba.client.defaults.IsDefaultPadding;
import org.pepstock.charba.client.defaults.IsDefaultSubtitle;
import org.pepstock.charba.client.enums.Weight;

/**
 * CHART.JS default values for SUBTITLE element.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultSubtitle extends AbstractDefaultTitle implements IsDefaultSubtitle {

	private static final int DEFAULT_PADDING = 0;

	private final DefaultRoutedFont font = new InternalSubtitleFont();

	private final DefaultPadding padding = new DefaultPadding(DEFAULT_PADDING);

	/**
	 * To avoid any instantiation
	 */
	DefaultSubtitle() {
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
	 * @see org.pepstock.charba.client.defaults.IsDefaultTitle#getPadding()
	 */
	@Override
	public IsDefaultPadding getPadding() {
		return padding;
	}

	/**
	 * Internal class extending {@link DefaultRoutedFont} to override some defaults for subtitle.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class InternalSubtitleFont extends DefaultRoutedFont {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.globals.DefaultRoutedFont#getWeight()
		 */
		@Override
		public Weight getWeight() {
			return Weight.NORMAL;
		}

	}

}
