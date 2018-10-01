package org.pepstock.charba.client.jsinterop.defaults.chart;

import org.pepstock.charba.client.jsinterop.defaults.IsDefaultPadding;
import org.pepstock.charba.client.jsinterop.options.ScaleLabelPadding;

public final class DefaultChartScaleLabelPadding implements IsDefaultPadding {
	
	private final ScaleLabelPadding padding;

	/**
	 * @param padding
	 */
	DefaultChartScaleLabelPadding(ScaleLabelPadding padding) {
		this.padding = padding;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultPadding#getLeft()
	 */
	@Override
	public int getLeft() {
		return padding.getLeft();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultPadding#getRight()
	 */
	@Override
	public int getRight() {
		return padding.getRight();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultPadding#getTop()
	 */
	@Override
	public int getTop() {
		return padding.getTop();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultPadding#getBottom()
	 */
	@Override
	public int getBottom() {
		return padding.getBottom();
	}

}
