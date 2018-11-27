package org.pepstock.charba.client.jsinterop.defaults.chart;

import org.pepstock.charba.client.jsinterop.defaults.IsDefaultLayout;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultPadding;
import org.pepstock.charba.client.jsinterop.options.Layout;

public final class DefaultChartLayout implements IsDefaultLayout{

	private final DefaultChartPadding padding;
	
	/**
	 * @param chartOptions
	 */
	public DefaultChartLayout(Layout layout) {
		padding = new DefaultChartPadding(layout.getPadding());
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultLayout#getPadding()
	 */
	@Override
	public IsDefaultPadding getPadding() {
		return padding;
	}
}
