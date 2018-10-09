package org.pepstock.charba.client.jsinterop.defaults.chart;

import org.pepstock.charba.client.jsinterop.defaults.IsDefaultArc;
import org.pepstock.charba.client.jsinterop.options.Arc;

public final class DefaultChartArc implements IsDefaultArc {
	
	private final Arc arc;

	/**
	 * @param arc
	 */
	DefaultChartArc(Arc arc) {
		this.arc = arc;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultArc#getBackgroundColor()
	 */
	@Override
	public String getBackgroundColor() {
		return arc.getBackgroundColorAsString();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultArc#getBorderWidth()
	 */
	@Override
	public int getBorderWidth() {
		return arc.getBorderWidth();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultArc#getBorderColor()
	 */
	@Override
	public String getBorderColor() {
		return arc.getBorderColorAsString();
	}
	
	

}
