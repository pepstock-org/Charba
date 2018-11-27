package org.pepstock.charba.client.jsinterop.defaults.globals;

import org.pepstock.charba.client.jsinterop.defaults.IsDefaultLegendLabels;

public final class DefaultLegendLabels extends AbstractDefaultFontItem implements IsDefaultLegendLabels {

	private static final int DEFAULT_PADDING = 10;
	
	private static final int DEFAULT_BOX_WIDTH = 40;

	private static final boolean DEFAULT_USE_POINT_STYLE = false;

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.legend.labels.IsDefaultLegendLabels#isUsePointStyle()
	 */
	@Override
	public boolean isUsePointStyle() {
		return DEFAULT_USE_POINT_STYLE;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.legend.labels.IsDefaultLegendLabels#getBoxWidth()
	 */
	@Override
	public int getBoxWidth() {
		return DEFAULT_BOX_WIDTH;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.legend.labels.IsDefaultLegendLabels#getPadding()
	 */
	@Override
	public int getPadding() {
		return DEFAULT_PADDING;
	}

}
