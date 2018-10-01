package org.pepstock.charba.client.jsinterop.defaults.chart;

import org.pepstock.charba.client.jsinterop.defaults.IsDefaultPadding;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultScaleLabel;
import org.pepstock.charba.client.jsinterop.options.ScaleLabel;

public final class DefaultChartScaleLabel implements IsDefaultScaleLabel {
	
	private final ScaleLabel scaleLabel;
	
	private final DefaultChartScaleLabelPadding padding;

	/**
	 * @param scaleLabel
	 */
	DefaultChartScaleLabel(ScaleLabel scaleLabel) {
		this.scaleLabel = scaleLabel;
		this.padding = new DefaultChartScaleLabelPadding(scaleLabel.getPadding());
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultFontItem#getFontColor()
	 */
	@Override
	public String getFontColor() {
		return scaleLabel.getFontColorAsString();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultFontItem#getFontSize()
	 */
	@Override
	public int getFontSize() {
		return scaleLabel.getFontSize();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultFontItem#getFontStyle()
	 */
	@Override
	public String getFontStyle() {
		return scaleLabel.getFontStyle().name();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultFontItem#getFontFamily()
	 */
	@Override
	public String getFontFamily() {
		return scaleLabel.getFontFamily();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScaleLabel#getPadding()
	 */
	@Override
	public IsDefaultPadding getPadding() {
		return padding;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScaleLabel#isDisplay()
	 */
	@Override
	public boolean isDisplay() {
		return scaleLabel.isDisplay();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScaleLabel#getLabelString()
	 */
	@Override
	public String getLabelString() {
		return scaleLabel.getLabelString();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScaleLabel#getLineHeight()
	 */
	@Override
	public double getLineHeight() {
		return scaleLabel.getLineHeight();
	}

}
