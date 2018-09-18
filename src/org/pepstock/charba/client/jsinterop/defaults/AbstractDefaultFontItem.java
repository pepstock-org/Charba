package org.pepstock.charba.client.jsinterop.defaults;

abstract class AbstractDefaultFontItem implements IsDefaultFontItem{

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.IsDefaultFontItem#getFontColor()
	 */
	@Override
	public String getFontColor() {
		return DefaultOptions.get().getDefaultFontColor();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.IsDefaultFontItem#getFontSize()
	 */
	@Override
	public int getFontSize() {
		return DefaultOptions.get().getDefaultFontSize();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.IsDefaultFontItem#getFontStyle()
	 */
	@Override
	public String getFontStyle() {
		return DefaultOptions.get().getDefaultFontStyle();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.IsDefaultFontItem#getFontFamily()
	 */
	@Override
	public String getFontFamily() {
		return DefaultOptions.get().getDefaultFontFamily();
	}
}
