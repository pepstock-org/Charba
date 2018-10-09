package org.pepstock.charba.client.jsinterop.defaults.chart;

import org.pepstock.charba.client.jsinterop.defaults.IsDefaultLine;
import org.pepstock.charba.client.jsinterop.options.Line;

public final class DefaultChartLine implements IsDefaultLine{
	
	private final Line line;

	/**
	 * @param line
	 */
	DefaultChartLine(Line line) {
		this.line = line;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultArc#getBackgroundColor()
	 */
	@Override
	public String getBackgroundColor() {
		return line.getBackgroundColorAsString();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultArc#getBorderWidth()
	 */
	@Override
	public int getBorderWidth() {
		return line.getBorderWidth();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultArc#getBorderColor()
	 */
	@Override
	public String getBorderColor() {
		return line.getBorderColorAsString();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultLine#getTension()
	 */
	@Override
	public double getTension() {
		return line.getTension();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultLine#getBorderCapStyle()
	 */
	@Override
	public String getBorderCapStyle() {
		return line.getBorderCapStyle().name();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultLine#getBorderDashOffset()
	 */
	@Override
	public int getBorderDashOffset() {
		return line.getBorderDashOffset();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultLine#getBorderJoinStyle()
	 */
	@Override
	public String getBorderJoinStyle() {
		return line.getBorderJoinStyle().name();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultLine#isCapBezierPoints()
	 */
	@Override
	public boolean isCapBezierPoints() {
		return line.isCapBezierPoints();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultLine#getFill()
	 */
	@Override
	public String getFill() {
		return line.getFill().name();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultLine#isStepped()
	 */
	@Override
	public boolean isStepped() {
		return line.isStepped();
	}

}
