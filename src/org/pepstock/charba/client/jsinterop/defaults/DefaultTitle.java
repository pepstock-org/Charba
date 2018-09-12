package org.pepstock.charba.client.jsinterop.defaults;

import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.jsinterop.options.title.IsDefaultTitle;

public class DefaultTitle extends AbstractDefaultFontItem implements IsDefaultTitle {

	private static final int DEFAULT_PADDING = 10;
	
	private static final boolean DEFAULT_FULL_WIDTH = true;

	private static final double DEFAULT_LINE_HEIGHT = 1.2D;
	
	private static final boolean DEFAULT_DISPLAY = false;

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.title.IsDefaultTitle#isDisplay()
	 */
	@Override
	public boolean isDisplay() {
		return DEFAULT_DISPLAY;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.title.IsDefaultTitle#getPosition()
	 */
	@Override
	public String getPosition() {
		return Position.top.name();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.title.IsDefaultTitle#getPadding()
	 */
	@Override
	public int getPadding() {
		return DEFAULT_PADDING;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.title.IsDefaultTitle#isFullWidth()
	 */
	@Override
	public boolean isFullWidth() {
		return DEFAULT_FULL_WIDTH;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.title.IsDefaultTitle#getLineHeight()
	 */
	@Override
	public double getLineHeight() {
		return DEFAULT_LINE_HEIGHT;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.AbstractDefaultFontItem#getFontStyle()
	 */
	@Override
	public String getFontStyle() {
		return FontStyle.bold.name();
	}
	
}
