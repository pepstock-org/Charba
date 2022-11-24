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

import org.pepstock.charba.client.annotation.enums.DrawTime;
import org.pepstock.charba.client.defaults.IsDefaultFont;
import org.pepstock.charba.client.defaults.IsDefaultPadding;
import org.pepstock.charba.client.defaults.globals.DefaultPadding;
import org.pepstock.charba.client.defaults.globals.DefaultRoutedFont;
import org.pepstock.charba.client.enums.CapStyle;
import org.pepstock.charba.client.enums.JoinStyle;
import org.pepstock.charba.client.enums.TextAlign;
import org.pepstock.charba.client.enums.Weight;

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
	 * @see org.pepstock.charba.client.annotation.IsDefaultsLineLabel#getCallout()
	 */
	@Override
	public IsDefaultsCallout getCallout() {
		return DefaultCallout.INSTANCE;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.IsDefaultsBorderRadiusHandler#getBorderRadius()
	 */
	@Override
	public int getBorderRadius() {
		return LineLabel.DEFAULT_BORDER_RADIUS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.IsDefaultsInnerLabel#isDisplay()
	 */
	@Override
	public boolean isDisplay() {
		return LineLabel.DEFAULT_DISPLAY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.IsDefaultsInnerLabel#getDrawTime()
	 */
	@Override
	public DrawTime getDrawTime() {
		return AnnotationOptions.DEFAULT_DRAW_TIME;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.IsDefaultsInnerLabel#getXAdjust()
	 */
	@Override
	public double getXAdjust() {
		return LineLabel.DEFAULT_X_ADJUST;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.IsDefaultsInnerLabel#getYAdjust()
	 */
	@Override
	public double getYAdjust() {
		return LineLabel.DEFAULT_Y_ADJUST;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.IsDefaultsLabelHandler#getColorAsString()
	 */
	@Override
	public String getColorAsString() {
		return LineLabel.DEFAULT_COLOR_AS_STRING;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.IsDefaultsLabelHandler#getTextAlign()
	 */
	@Override
	public TextAlign getTextAlign() {
		return LineLabel.DEFAULT_TEXT_ALIGN;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.IsDefaultsBorderOptionsHandler#getBorderWidth()
	 */
	@Override
	public int getBorderWidth() {
		return LineLabel.DEFAULT_BORDER_WIDTH;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.IsDefaultsBorderOptionsHandler#getBorderColorAsString()
	 */
	@Override
	public String getBorderColorAsString() {
		return LineLabel.DEFAULT_BORDER_COLOR_AS_STRING;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.IsDefaultsBorderOptionsHandler#getBorderDashOffset()
	 */
	@Override
	public double getBorderDashOffset() {
		return LineLabel.DEFAULT_BORDER_DASH_OFFSET;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.IsDefaultsExtendedBorderOptionsHandler#getBorderCapStyle()
	 */
	@Override
	public CapStyle getBorderCapStyle() {
		return LineLabel.DEFAULT_BORDER_CAP_STYLE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.IsDefaultsExtendedBorderOptionsHandler#getBorderJoinStyle()
	 */
	@Override
	public JoinStyle getBorderJoinStyle() {
		return LineLabel.DEFAULT_BORDER_JOIN_STYLE;
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
		 * @see org.pepstock.charba.client.defaults.globals.DefaultRoutedFont#getWeight()
		 */
		@Override
		public Weight getWeight() {
			return LineLabel.DEFAULT_FONT_WEIGHT;
		}
	}

}
