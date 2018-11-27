package org.pepstock.charba.client.jsinterop.defaults.chart;

import org.pepstock.charba.client.enums.Position;
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
	public String getBackgroundColorAsString() {
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
	public String getBorderColorAsString() {
		return rectangle.getBorderColorAsString();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultRectangle#getBorderSkipped()
	 */
	@Override
	public Position getBorderSkipped() {
		return rectangle.getBorderSkipped();
	}	

}
