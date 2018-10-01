package org.pepstock.charba.client.jsinterop.defaults.chart;

import org.pepstock.charba.client.jsinterop.defaults.IsDefaultFontItem;
import org.pepstock.charba.client.jsinterop.options.FontItem;

public final class DefaultChartFontItem implements IsDefaultFontItem{

	private final FontItem<?, ?, ?> item;

	/**
	 * @param item
	 */
	DefaultChartFontItem(FontItem<?, ?, ?> item) {
		this.item = item;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultFontItem#getFontColor()
	 */
	@Override
	public String getFontColor() {
		return item.getFontColorAsString();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultFontItem#getFontSize()
	 */
	@Override
	public int getFontSize() {
		return item.getFontSize();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultFontItem#getFontStyle()
	 */
	@Override
	public String getFontStyle() {
		return item.getFontStyle().name();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultFontItem#getFontFamily()
	 */
	@Override
	public String getFontFamily() {
		return item.getFontFamily();
	}
	
}
