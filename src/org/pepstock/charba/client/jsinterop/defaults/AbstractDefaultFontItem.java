package org.pepstock.charba.client.jsinterop.defaults;

import org.pepstock.charba.client.jsinterop.Global;
import org.pepstock.charba.client.jsinterop.options.IsDefaultFontItem;

public abstract class AbstractDefaultFontItem implements IsDefaultFontItem{

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.IsDefaultFontItem#getFontColor()
	 */
	@Override
	public String getFontColor() {
		return Global.DEFAULT_GLOBAL_OPTIONS.getDefaultFontColor();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.IsDefaultFontItem#getFontSize()
	 */
	@Override
	public int getFontSize() {
		return Global.DEFAULT_GLOBAL_OPTIONS.getDefaultFontSize();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.IsDefaultFontItem#getFontStyle()
	 */
	@Override
	public String getFontStyle() {
		return Global.DEFAULT_GLOBAL_OPTIONS.getDefaultFontStyle();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.IsDefaultFontItem#getFontFamily()
	 */
	@Override
	public String getFontFamily() {
		return Global.DEFAULT_GLOBAL_OPTIONS.getDefaultFontFamily();
	}
}
