package org.pepstock.charba.client.jsinterop.defaults.chart;

import org.pepstock.charba.client.jsinterop.defaults.IsDefaultGridLines;
import org.pepstock.charba.client.jsinterop.options.GridLines;

public final class DefaultChartGridLines implements IsDefaultGridLines{

	private final GridLines gridLines;

	/**
	 * @param gridLines
	 */
	DefaultChartGridLines(GridLines gridLines) {
		this.gridLines = gridLines;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultGridLines#isDisplay()
	 */
	@Override
	public boolean isDisplay() {
		return gridLines.isDisplay();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultGridLines#getColor()
	 */
	@Override
	public String getColorAsString() {
		return gridLines.getColorAsString();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultGridLines#getBorderDashOffset()
	 */
	@Override
	public int getBorderDashOffset() {
		return gridLines.getBorderDashOffset();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultGridLines#getLineWidth()
	 */
	@Override
	public int getLineWidth() {
		return gridLines.getLineWidth();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultGridLines#isDrawBorder()
	 */
	@Override
	public boolean isDrawBorder() {
		return gridLines.isDrawBorder();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultGridLines#isDrawOnChartArea()
	 */
	@Override
	public boolean isDrawOnChartArea() {
		return gridLines.isDrawOnChartArea();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultGridLines#isDrawTicks()
	 */
	@Override
	public boolean isDrawTicks() {
		return gridLines.isDrawTicks();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultGridLines#getTickMarkLength()
	 */
	@Override
	public int getTickMarkLength() {
		return gridLines.getTickMarkLength();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultGridLines#getZeroLineWidth()
	 */
	@Override
	public int getZeroLineWidth() {
		return gridLines.getZeroLineWidth();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultGridLines#getZeroLineColor()
	 */
	@Override
	public String getZeroLineColorAsString() {
		return gridLines.getZeroLineColorAsString();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultGridLines#getZeroLineBorderDashOffset()
	 */
	@Override
	public int getZeroLineBorderDashOffset() {
		return gridLines.getZeroLineBorderDash().get(0);
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultGridLines#isOffsetGridLines()
	 */
	@Override
	public boolean isOffsetGridLines() {
		return gridLines.isOffsetGridLines();
	}
	
}
