package org.pepstock.charba.client.jsinterop.defaults.globals;

import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultFontItem;

abstract class AbstractDefaultFontItem implements IsDefaultFontItem{

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.IsDefaultFontItem#getFontColor()
	 */
	@Override
	public String getFontColorAsString() {
		return DefaultOptions.get().getDefaultFontColorAsString();
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
	public FontStyle getFontStyle() {
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
