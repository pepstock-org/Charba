package org.pepstock.charba.client.jsinterop.defaults.chart;

import org.pepstock.charba.client.jsinterop.defaults.IsDefaultLegendLabels;
import org.pepstock.charba.client.jsinterop.options.LegendLabels;

public final class DefaultChartLegendLabels implements IsDefaultLegendLabels {
	
	private final LegendLabels labels;

	/**
	 * @param labels
	 */
	DefaultChartLegendLabels(LegendLabels labels) {
		this.labels = labels;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultFontItem#getFontColor()
	 */
	@Override
	public String getFontColor() {
		return labels.getFontColorAsString();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultFontItem#getFontSize()
	 */
	@Override
	public int getFontSize() {
		return labels.getFontSize();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultFontItem#getFontStyle()
	 */
	@Override
	public String getFontStyle() {
		return labels.getFontStyle().name();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultFontItem#getFontFamily()
	 */
	@Override
	public String getFontFamily() {
		return labels.getFontFamily();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultLegendLabels#isUsePointStyle()
	 */
	@Override
	public boolean isUsePointStyle() {
		return labels.isUsePointStyle();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultLegendLabels#getBoxWidth()
	 */
	@Override
	public int getBoxWidth() {
		return labels.getBoxWidth();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultLegendLabels#getPadding()
	 */
	@Override
	public int getPadding() {
		return labels.getPadding();
	}
}
