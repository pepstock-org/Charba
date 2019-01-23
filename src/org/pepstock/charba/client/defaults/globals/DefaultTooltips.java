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

import org.pepstock.charba.client.defaults.IsDefaultTooltips;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.enums.InteractionMode;
import org.pepstock.charba.client.enums.TextAlign;
import org.pepstock.charba.client.enums.TooltipPosition;

/**
 * CHART.JS default values for TOOLTIPS element.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultTooltips implements IsDefaultTooltips {

	private static final boolean DEFAULT_ENABLED = true;

	private static final boolean DEFAULT_INTERSECT = true;

	private static final String DEFAULT_BACKGROUND_COLOR = "rgba(0,0,0,0.8)";

	private static final String DEFAULT_TITLE_FONT_COLOR = "#fff";

	private static final int DEFAULT_TITLE_SPACING = 2;

	private static final int DEFAULT_TITLE_MARGIN_BOTTOM = 6;

	private static final String DEFAULT_BODY_FONT_COLOR = "#fff";

	private static final int DEFAULT_BODY_SPACING = 2;

	private static final String DEFAULT_FOOTER_FONT_COLOR = "#fff";

	private static final int DEFAULT_FOOTER_SPACING = 2;

	private static final int DEFAULT_FOOTER_MARGIN_TOP = 6;

	private static final int DEFAULT_X_PADDING = 6;

	private static final int DEFAULT_Y_PADDING = 6;

	private static final int DEFAULT_CARET_PADDING = 2;

	private static final int DEFAULT_CARET_SIZE = 5;

	private static final int DEFAULT_CORNER_RADIUS = 6;

	private static final String DEFAULT_MULTI_KEY_BACKGROUND = "#fff";

	private static final boolean DEFAULT_DISPLAY_COLORS = true;

	private static final String DEFAULT_BORDER_COLOR = "rgba(0,0,0,0)";

	private static final int DEFAULT_BORDER_WIDTH = 0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.tooltips.IsDefaultTooltips#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		return DEFAULT_ENABLED;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.tooltips.IsDefaultTooltips#getMode()
	 */
	@Override
	public InteractionMode getMode() {
		return InteractionMode.nearest;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.tooltips.IsDefaultTooltips#isIntersect()
	 */
	@Override
	public boolean isIntersect() {
		return DEFAULT_INTERSECT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.tooltips.IsDefaultTooltips#getPosition()
	 */
	@Override
	public TooltipPosition getPosition() {
		return TooltipPosition.average;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.tooltips.IsDefaultTooltips#getBackgroundColorAsString()
	 */
	@Override
	public String getBackgroundColorAsString() {
		return DEFAULT_BACKGROUND_COLOR;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.tooltips.IsDefaultTooltips#getTitleFontFamily()
	 */
	@Override
	public String getTitleFontFamily() {
		return DefaultsBuilder.get().getOptions().getDefaultFontFamily();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.tooltips.IsDefaultTooltips#getTitleFontSize()
	 */
	@Override
	public int getTitleFontSize() {
		return DefaultsBuilder.get().getOptions().getDefaultFontSize();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.tooltips.IsDefaultTooltips#getTitleFontStyle()
	 */
	@Override
	public FontStyle getTitleFontStyle() {
		return FontStyle.bold;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.tooltips.IsDefaultTooltips#getTitleAlign()
	 */
	@Override
	public TextAlign getTitleAlign() {
		return TextAlign.left;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.tooltips.IsDefaultTooltips#getTitleFontColorAsString()
	 */
	@Override
	public String getTitleFontColorAsString() {
		return DEFAULT_TITLE_FONT_COLOR;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.tooltips.IsDefaultTooltips#getTitleSpacing()
	 */
	@Override
	public int getTitleSpacing() {
		return DEFAULT_TITLE_SPACING;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.tooltips.IsDefaultTooltips#getTitleMarginBottom()
	 */
	@Override
	public int getTitleMarginBottom() {
		return DEFAULT_TITLE_MARGIN_BOTTOM;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.tooltips.IsDefaultTooltips#getBodyFontFamily()
	 */
	@Override
	public String getBodyFontFamily() {
		return DefaultsBuilder.get().getOptions().getDefaultFontFamily();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.tooltips.IsDefaultTooltips#getBodyFontSize()
	 */
	@Override
	public int getBodyFontSize() {
		return DefaultsBuilder.get().getOptions().getDefaultFontSize();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.tooltips.IsDefaultTooltips#getBodyFontStyle()
	 */
	@Override
	public FontStyle getBodyFontStyle() {
		return FontStyle.normal;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.tooltips.IsDefaultTooltips#getBodyAlign()
	 */
	@Override
	public TextAlign getBodyAlign() {
		return TextAlign.left;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.tooltips.IsDefaultTooltips#getBodyFontColorAsString()
	 */
	@Override
	public String getBodyFontColorAsString() {
		return DEFAULT_BODY_FONT_COLOR;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.tooltips.IsDefaultTooltips#getBodySpacing()
	 */
	@Override
	public int getBodySpacing() {
		return DEFAULT_BODY_SPACING;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.tooltips.IsDefaultTooltips#getFooterFontFamily()
	 */
	@Override
	public String getFooterFontFamily() {
		return DefaultsBuilder.get().getOptions().getDefaultFontFamily();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.tooltips.IsDefaultTooltips#getFooterFontSize()
	 */
	@Override
	public int getFooterFontSize() {
		return DefaultsBuilder.get().getOptions().getDefaultFontSize();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.tooltips.IsDefaultTooltips#getFooterFontStyle()
	 */
	@Override
	public FontStyle getFooterFontStyle() {
		return FontStyle.bold;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.tooltips.IsDefaultTooltips#getFooterAlign()
	 */
	@Override
	public TextAlign getFooterAlign() {
		return TextAlign.left;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.tooltips.IsDefaultTooltips#getFooterFontColorAsString()
	 */
	@Override
	public String getFooterFontColorAsString() {
		return DEFAULT_FOOTER_FONT_COLOR;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.tooltips.IsDefaultTooltips#getFooterSpacing()
	 */
	@Override
	public int getFooterSpacing() {
		return DEFAULT_FOOTER_SPACING;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.tooltips.IsDefaultTooltips#getFooterMarginTop()
	 */
	@Override
	public int getFooterMarginTop() {
		return DEFAULT_FOOTER_MARGIN_TOP;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.tooltips.IsDefaultTooltips#getXPadding()
	 */
	@Override
	public int getXPadding() {
		return DEFAULT_X_PADDING;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.tooltips.IsDefaultTooltips#getYPadding()
	 */
	@Override
	public int getYPadding() {
		return DEFAULT_Y_PADDING;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.tooltips.IsDefaultTooltips#getCaretPadding()
	 */
	@Override
	public int getCaretPadding() {
		return DEFAULT_CARET_PADDING;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.tooltips.IsDefaultTooltips#getCaretSize()
	 */
	@Override
	public int getCaretSize() {
		return DEFAULT_CARET_SIZE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.tooltips.IsDefaultTooltips#getCornerRadius()
	 */
	@Override
	public int getCornerRadius() {
		return DEFAULT_CORNER_RADIUS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.tooltips.IsDefaultTooltips#getMultiKeyBackgroundAsString()
	 */
	@Override
	public String getMultiKeyBackgroundAsString() {
		return DEFAULT_MULTI_KEY_BACKGROUND;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.tooltips.IsDefaultTooltips#isDisplayColors()
	 */
	@Override
	public boolean isDisplayColors() {
		return DEFAULT_DISPLAY_COLORS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.tooltips.IsDefaultTooltips#getBorderColorAsString()
	 */
	@Override
	public String getBorderColorAsString() {
		return DEFAULT_BORDER_COLOR;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.tooltips.IsDefaultTooltips#getBorderWidth()
	 */
	@Override
	public int getBorderWidth() {
		return DEFAULT_BORDER_WIDTH;
	}

}
