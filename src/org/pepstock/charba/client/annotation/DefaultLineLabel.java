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
package org.pepstock.charba.client.annotation;

import org.pepstock.charba.client.defaults.IsDefaultFont;
import org.pepstock.charba.client.defaults.IsDefaultPadding;
import org.pepstock.charba.client.defaults.globals.DefaultPadding;
import org.pepstock.charba.client.defaults.globals.DefaultRoutedFont;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.enums.TextAlign;

/**
 * This is the {@link AnnotationPlugin#ID} plugin LABEL of LINE annotation DEFAULTS options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class DefaultLineLabel implements IsDefaultsLineLabel {

	// defaults options instance
	static final DefaultLineLabel INSTANCE = new DefaultLineLabel();
	// default font instance
	private final IsDefaultFont font = new InternalFont();
	// default padding instance
	private final IsDefaultPadding padding = new DefaultPadding(LineLabel.DEFAULT_PADDING);

	/**
	 * To avoid any instantiation
	 */
	private DefaultLineLabel() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.IsDefaultsLineLabel#getFont()
	 */
	@Override
	public IsDefaultFont getFont() {
		return font;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.IsDefaultsLabelHandler#getPadding()
	 */
	@Override
	public IsDefaultPadding getPadding() {
		return padding;
	}

	/**
	 * Returns the radius of label rectangle.
	 * 
	 * @return the radius of label rectangle
	 */
	@Override
	public int getBorderRadius() {
		return LineLabel.DEFAULT_BORDER_RADIUS;
	}

	/**
	 * Returns the font color of text as string.
	 * 
	 * @return the font color of text
	 */
	@Override
	public String getColorAsString() {
		return LineLabel.DEFAULT_FONT_COLOR_AS_STRING;
	}

	/**
	 * Returns the horizontal alignment of the label text.
	 * 
	 * @return the horizontal alignment of the label text
	 */
	@Override
	public TextAlign getTextAlign() {
		return LineLabel.DEFAULT_TEXT_ALIGN;
	}

	/**
	 * Internal class extending {@link DefaultRoutedFont} to override some defaults for body.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class InternalFont extends DefaultRoutedFont {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.globals.DefaultFont#getStyle()
		 */
		@Override
		public FontStyle getStyle() {
			return LineLabel.DEFAULT_FONT_STYLE;
		}

	}

}
