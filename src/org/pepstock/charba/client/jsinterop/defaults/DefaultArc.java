package org.pepstock.charba.client.jsinterop.defaults;

import org.pepstock.charba.client.jsinterop.options.elements.arc.IsDefaultArc;

public class DefaultArc implements IsDefaultArc{

	// default background color
	private static final String DEFAULT_BACKGROUND_COLOR = "rgba(0,0,0,0.1)";
	// default border with
	private static final int DEFAULT_BORDER_WIDTH = 2;
	// default border color
	private static final String DEFAULT_BORDER_COLOR = "#fff";
	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.elements.arc.IsReadableArc#getBackgroundColor()
	 */
	@Override
	public String getBackgroundColor() {
		return DEFAULT_BACKGROUND_COLOR;
	}
	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.elements.arc.IsReadableArc#getBorderWidth()
	 */
	@Override
	public int getBorderWidth() {
		return DEFAULT_BORDER_WIDTH;
	}
	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.elements.arc.IsReadableArc#getBorderColor()
	 */
	@Override
	public String getBorderColor() {
		return DEFAULT_BORDER_COLOR;
	}

}
