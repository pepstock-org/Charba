package org.pepstock.charba.client.jsinterop.defaults.chart;

import org.pepstock.charba.client.jsinterop.defaults.IsDefaultTooltips;
import org.pepstock.charba.client.jsinterop.options.Tooltips;

public final class DefaultChartTooltips implements IsDefaultTooltips{
	
	private final Tooltips tooltips;

	/**
	 * @param tooltips
	 */
	DefaultChartTooltips(Tooltips tooltips) {
		this.tooltips = tooltips;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTooltips#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		return tooltips.isEnabled();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTooltips#getMode()
	 */
	@Override
	public String getMode() {
		return tooltips.getMode().name();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTooltips#isIntersect()
	 */
	@Override
	public boolean isIntersect() {
		return tooltips.isIntersect();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTooltips#getPosition()
	 */
	@Override
	public String getPosition() {
		return tooltips.getPosition().name();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTooltips#getBackgroundColor()
	 */
	@Override
	public String getBackgroundColor() {
		return tooltips.getBackgroundColorAsString();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTooltips#getTitleFontFamily()
	 */
	@Override
	public String getTitleFontFamily() {
		return tooltips.getTitleFontFamily();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTooltips#getTitleFontSize()
	 */
	@Override
	public int getTitleFontSize() {
		return tooltips.getTitleFontSize();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTooltips#getTitleFontStyle()
	 */
	@Override
	public String getTitleFontStyle() {
		return tooltips.getTitleFontStyle().name();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTooltips#getTitleAlign()
	 */
	@Override
	public String getTitleAlign() {
		return tooltips.getTitleAlign().name();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTooltips#getTitleFontColor()
	 */
	@Override
	public String getTitleFontColor() {
		return tooltips.getTitleFontColorAsString();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTooltips#getTitleSpacing()
	 */
	@Override
	public int getTitleSpacing() {
		return tooltips.getTitleSpacing();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTooltips#getTitleMarginBottom()
	 */
	@Override
	public int getTitleMarginBottom() {
		return tooltips.getTitleMarginBottom();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTooltips#getBodyFontFamily()
	 */
	@Override
	public String getBodyFontFamily() {
		return tooltips.getBodyFontFamily();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTooltips#getBodyFontSize()
	 */
	@Override
	public int getBodyFontSize() {
		return tooltips.getBodyFontSize();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTooltips#getBodyFontStyle()
	 */
	@Override
	public String getBodyFontStyle() {
		return tooltips.getBodyFontStyle().name();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTooltips#getBodyAlign()
	 */
	@Override
	public String getBodyAlign() {
		return tooltips.getBodyAlign().name();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTooltips#getBodyFontColor()
	 */
	@Override
	public String getBodyFontColor() {
		return tooltips.getBodyFontColorAsString();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTooltips#getBodySpacing()
	 */
	@Override
	public int getBodySpacing() {
		return tooltips.getBodySpacing();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTooltips#getFooterFontFamily()
	 */
	@Override
	public String getFooterFontFamily() {
		return tooltips.getFooterFontFamily();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTooltips#getFooterFontSize()
	 */
	@Override
	public int getFooterFontSize() {
		return tooltips.getFooterFontSize();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTooltips#getFooterFontStyle()
	 */
	@Override
	public String getFooterFontStyle() {
		return tooltips.getFooterFontStyle().name();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTooltips#getFooterAlign()
	 */
	@Override
	public String getFooterAlign() {
		return tooltips.getFooterAlign().name();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTooltips#getFooterFontColor()
	 */
	@Override
	public String getFooterFontColor() {
		return tooltips.getFooterFontColorAsString();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTooltips#getFooterSpacing()
	 */
	@Override
	public int getFooterSpacing() {
		return tooltips.getFooterSpacing();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTooltips#getFooterMarginTop()
	 */
	@Override
	public int getFooterMarginTop() {
		return tooltips.getFooterMarginTop();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTooltips#getXPadding()
	 */
	@Override
	public int getXPadding() {
		return tooltips.getXPadding();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTooltips#getYPadding()
	 */
	@Override
	public int getYPadding() {
		return tooltips.getYPadding();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTooltips#getCaretPadding()
	 */
	@Override
	public int getCaretPadding() {
		return tooltips.getCaretPadding();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTooltips#getCaretSize()
	 */
	@Override
	public int getCaretSize() {
		return tooltips.getCaretSize();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTooltips#getCornerRadius()
	 */
	@Override
	public int getCornerRadius() {
		return tooltips.getCornerRadius();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTooltips#getMultiKeyBackground()
	 */
	@Override
	public String getMultiKeyBackground() {
		return tooltips.getMultiKeyBackgroundAsString();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTooltips#isDisplayColors()
	 */
	@Override
	public boolean isDisplayColors() {
		return tooltips.isDisplayColors();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTooltips#getBorderColor()
	 */
	@Override
	public String getBorderColor() {
		return tooltips.getBorderColorAsString();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTooltips#getBorderWidth()
	 */
	@Override
	public int getBorderWidth() {
		return tooltips.getBorderWidth();
	}
}
