package org.pepstock.charba.client.jsinterop.defaults.chart;

import org.pepstock.charba.client.jsinterop.defaults.IsDefaultTitle;
import org.pepstock.charba.client.jsinterop.options.Title;

public final class DefaultChartTitle implements IsDefaultTitle{
	
	private final Title title;

	/**
	 * @param title
	 */
	DefaultChartTitle(Title title) {
		this.title = title;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultFontItem#getFontColor()
	 */
	@Override
	public String getFontColor() {
		return title.getFontColorAsString();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultFontItem#getFontSize()
	 */
	@Override
	public int getFontSize() {
		return title.getFontSize();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultFontItem#getFontStyle()
	 */
	@Override
	public String getFontStyle() {
		return title.getFontStyle().name();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultFontItem#getFontFamily()
	 */
	@Override
	public String getFontFamily() {
		return title.getFontFamily();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTitle#isDisplay()
	 */
	@Override
	public boolean isDisplay() {
		return title.isDisplay();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTitle#getPosition()
	 */
	@Override
	public String getPosition() {
		return title.getPosition().name();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTitle#getPadding()
	 */
	@Override
	public int getPadding() {
		return title.getPadding();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTitle#isFullWidth()
	 */
	@Override
	public boolean isFullWidth() {
		return title.isFullWidth();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTitle#getLineHeight()
	 */
	@Override
	public double getLineHeight() {
		return title.getLineHeight();
	}
	
}
