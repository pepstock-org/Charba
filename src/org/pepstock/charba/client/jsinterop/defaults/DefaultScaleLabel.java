package org.pepstock.charba.client.jsinterop.defaults;

public final class DefaultScaleLabel extends AbstractDefaultFontItem implements IsDefaultScaleLabel {
	
	private static final boolean DEFAULT_DISPLAY = false;

	private static final String DEFAULT_LABEL_STRING = "";

	private static final double DEFAULT_LINE_HEIGHT = 1.2D;
	
	private final DefaultScaleLabelPadding padding = new DefaultScaleLabelPadding();

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
		return DEFAULT_DISPLAY;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScaleLabel#getLabelString()
	 */
	@Override
	public String getLabelString() {
		return DEFAULT_LABEL_STRING;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScaleLabel#getLineHeight()
	 */
	@Override
	public double getLineHeight() {
		return DEFAULT_LINE_HEIGHT;
	}
	
}
