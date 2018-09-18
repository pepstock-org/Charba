package org.pepstock.charba.client.jsinterop.defaults;

import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.enums.InteractionMode;
import org.pepstock.charba.client.enums.TextAlign;
import org.pepstock.charba.client.enums.TooltipPosition;

public final class DefaultTooltips implements IsDefaultTooltips{

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
	
	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.tooltips.IsDefaultTooltips#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		return DEFAULT_ENABLED;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.tooltips.IsDefaultTooltips#getMode()
	 */
	@Override
	public String getMode() {
		return InteractionMode.nearest.name();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.tooltips.IsDefaultTooltips#isIntersect()
	 */
	@Override
	public boolean isIntersect() {
		return DEFAULT_INTERSECT;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.tooltips.IsDefaultTooltips#getPosition()
	 */
	@Override
	public String getPosition() {
		return TooltipPosition.average.name();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.tooltips.IsDefaultTooltips#getBackgroundColor()
	 */
	@Override
	public String getBackgroundColor() {
		return DEFAULT_BACKGROUND_COLOR;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.tooltips.IsDefaultTooltips#getTitleFontFamily()
	 */
	@Override
	public String getTitleFontFamily() {
		return DefaultOptions.get().getDefaultFontFamily();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.tooltips.IsDefaultTooltips#getTitleFontSize()
	 */
	@Override
	public int getTitleFontSize() {
		return DefaultOptions.get().getDefaultFontSize();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.tooltips.IsDefaultTooltips#getTitleFontStyle()
	 */
	@Override
	public String getTitleFontStyle() {
		return FontStyle.bold.name();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.tooltips.IsDefaultTooltips#getTitleAlign()
	 */
	@Override
	public String getTitleAlign() {
		return TextAlign.left.name();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.tooltips.IsDefaultTooltips#getTitleFontColor()
	 */
	@Override
	public String getTitleFontColor() {
		return DEFAULT_TITLE_FONT_COLOR;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.tooltips.IsDefaultTooltips#getTitleSpacing()
	 */
	@Override
	public int getTitleSpacing() {
		return DEFAULT_TITLE_SPACING;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.tooltips.IsDefaultTooltips#getTitleMarginBottom()
	 */
	@Override
	public int getTitleMarginBottom() {
		return DEFAULT_TITLE_MARGIN_BOTTOM;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.tooltips.IsDefaultTooltips#getBodyFontFamily()
	 */
	@Override
	public String getBodyFontFamily() {
		return DefaultOptions.get().getDefaultFontFamily();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.tooltips.IsDefaultTooltips#getBodyFontSize()
	 */
	@Override
	public int getBodyFontSize() {
		return DefaultOptions.get().getDefaultFontSize();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.tooltips.IsDefaultTooltips#getBodyFontStyle()
	 */
	@Override
	public String getBodyFontStyle() {
		return FontStyle.normal.name();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.tooltips.IsDefaultTooltips#getBodyAlign()
	 */
	@Override
	public String getBodyAlign() {
		return TextAlign.left.name();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.tooltips.IsDefaultTooltips#getBodyFontColor()
	 */
	@Override
	public String getBodyFontColor() {
		return DEFAULT_BODY_FONT_COLOR;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.tooltips.IsDefaultTooltips#getBodySpacing()
	 */
	@Override
	public int getBodySpacing() {
		return DEFAULT_BODY_SPACING;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.tooltips.IsDefaultTooltips#getFooterFontFamily()
	 */
	@Override
	public String getFooterFontFamily() {
		return DefaultOptions.get().getDefaultFontFamily();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.tooltips.IsDefaultTooltips#getFooterFontSize()
	 */
	@Override
	public int getFooterFontSize() {
		return DefaultOptions.get().getDefaultFontSize();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.tooltips.IsDefaultTooltips#getFooterFontStyle()
	 */
	@Override
	public String getFooterFontStyle() {
		return FontStyle.bold.name();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.tooltips.IsDefaultTooltips#getFooterAlign()
	 */
	@Override
	public String getFooterAlign() {
		return TextAlign.left.name();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.tooltips.IsDefaultTooltips#getFooterFontColor()
	 */
	@Override
	public String getFooterFontColor() {
		return DEFAULT_FOOTER_FONT_COLOR;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.tooltips.IsDefaultTooltips#getFooterSpacing()
	 */
	@Override
	public int getFooterSpacing() {
		return DEFAULT_FOOTER_SPACING;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.tooltips.IsDefaultTooltips#getFooterMarginTop()
	 */
	@Override
	public int getFooterMarginTop() {
		return DEFAULT_FOOTER_MARGIN_TOP;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.tooltips.IsDefaultTooltips#getXPadding()
	 */
	@Override
	public int getXPadding() {
		return DEFAULT_X_PADDING;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.tooltips.IsDefaultTooltips#getYPadding()
	 */
	@Override
	public int getYPadding() {
		return DEFAULT_Y_PADDING;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.tooltips.IsDefaultTooltips#getCaretPadding()
	 */
	@Override
	public int getCaretPadding() {
		return DEFAULT_CARET_PADDING;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.tooltips.IsDefaultTooltips#getCaretSize()
	 */
	@Override
	public int getCaretSize() {
		return DEFAULT_CARET_SIZE;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.tooltips.IsDefaultTooltips#getCornerRadius()
	 */
	@Override
	public int getCornerRadius() {
		return DEFAULT_CORNER_RADIUS;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.tooltips.IsDefaultTooltips#getMultiKeyBackground()
	 */
	@Override
	public String getMultiKeyBackground() {
		return DEFAULT_MULTI_KEY_BACKGROUND;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.tooltips.IsDefaultTooltips#isDisplayColors()
	 */
	@Override
	public boolean isDisplayColors() {
		return DEFAULT_DISPLAY_COLORS;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.tooltips.IsDefaultTooltips#getBorderColor()
	 */
	@Override
	public String getBorderColor() {
		return DEFAULT_BORDER_COLOR;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.tooltips.IsDefaultTooltips#getBorderWidth()
	 */
	@Override
	public int getBorderWidth() {
		return DEFAULT_BORDER_WIDTH;
	}

}
