package org.pepstock.charba.client.jsinterop.defaults.chart;

import org.pepstock.charba.client.enums.PointStyle;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultPoint;
import org.pepstock.charba.client.jsinterop.options.Point;

public final class DefaultChartPoint implements IsDefaultPoint{
	
	private final Point point;

	/**
	 * @param point
	 */
	DefaultChartPoint(Point point) {
		this.point = point;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultArc#getBackgroundColor()
	 */
	@Override
	public String getBackgroundColorAsString() {
		return point.getBackgroundColorAsString();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultArc#getBorderWidth()
	 */
	@Override
	public int getBorderWidth() {
		return point.getBorderWidth();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultArc#getBorderColor()
	 */
	@Override
	public String getBorderColorAsString() {
		return point.getBorderColorAsString();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultPoint#getRadius()
	 */
	@Override
	public int getRadius() {
		return point.getRadius();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultPoint#getPointStyle()
	 */
	@Override
	public PointStyle getPointStyle() {
		return point.getPointStyle();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultPoint#getHitRadius()
	 */
	@Override
	public int getHitRadius() {
		return point.getHitRadius();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultPoint#getHoverRadius()
	 */
	@Override
	public int getHoverRadius() {
		return point.getHoverRadius();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultPoint#getHoverBorderWidth()
	 */
	@Override
	public int getHoverBorderWidth() {
		return point.getHoverBorderWidth();
	}

}
