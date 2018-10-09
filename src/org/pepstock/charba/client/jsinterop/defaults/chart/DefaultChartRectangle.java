package org.pepstock.charba.client.jsinterop.defaults.chart;

import org.pepstock.charba.client.jsinterop.defaults.IsDefaultRectangle;
import org.pepstock.charba.client.jsinterop.options.Rectangle;

public final class DefaultChartRectangle implements IsDefaultRectangle {
	
	private final Rectangle rectangle;

	/**
	 * @param rectangle
	 */
	DefaultChartRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultArc#getBackgroundColor()
	 */
	@Override
	public String getBackgroundColor() {
		return rectangle.getBackgroundColorAsString();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultArc#getBorderWidth()
	 */
	@Override
	public int getBorderWidth() {
		return rectangle.getBorderWidth();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultArc#getBorderColor()
	 */
	@Override
	public String getBorderColor() {
		return rectangle.getBorderColorAsString();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultRectangle#getBorderSkipped()
	 */
	@Override
	public String getBorderSkipped() {
		return rectangle.getBorderSkipped().name();
	}
	
	

}
