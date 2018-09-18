package org.pepstock.charba.client.jsinterop.defaults;

import org.pepstock.charba.client.jsinterop.commons.ArrayInteger;

public final class DefaultGridLines implements IsDefaultGridLines{
	
	private static final boolean DEFAULT_DISPLAY = true;

	private static final String DEFAULT_COLOR = "rgba(0,0,0,0.1)";

	private static final int DEFAULT_BORDER_DASH_OFFSET = 0;

	private static final int DEFAULT_LINE_WIDTH = 1;

	private static final boolean DEFAULT_DRAW_BORDER = true;

	private static final boolean DEFAULT_DRAW_ON_CHART_AREA = true;

	private static final boolean DEFAULT_DRAW_TICKS = true;

	private static final int DEFAULT_TICK_MARK_LENGTH = 10;

	private static final int DEFAULT_ZERO_LINE_WIDTH = 1;

	private static final String DEFAULT_ZERO_LINE_COLOR = "rgba(0,0,0,0.25)";

	private static final int DEFAULT_ZERO_LINE_BORDER_DASH_OFFSET = 0;

	private static final boolean DEFAULT_OFFSET_GRID_LINES = false;

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultGridLines#isDisplay()
	 */
	@Override
	public boolean isDisplay() {
		return DEFAULT_DISPLAY;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultGridLines#getColor()
	 */
	@Override
	public String getColor() {
		return DEFAULT_COLOR;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultGridLines#getBorderDash()
	 */
	@Override
	public ArrayInteger getBorderDash() {
		return new ArrayInteger();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultGridLines#getBorderDashOffset()
	 */
	@Override
	public int getBorderDashOffset() {
		return DEFAULT_BORDER_DASH_OFFSET;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultGridLines#getLineWidth()
	 */
	@Override
	public int getLineWidth() {
		return DEFAULT_LINE_WIDTH;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultGridLines#isDrawBorder()
	 */
	@Override
	public boolean isDrawBorder() {
		return DEFAULT_DRAW_BORDER;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultGridLines#isDrawOnChartArea()
	 */
	@Override
	public boolean isDrawOnChartArea() {
		return DEFAULT_DRAW_ON_CHART_AREA;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultGridLines#isDrawTicks()
	 */
	@Override
	public boolean isDrawTicks() {
		return DEFAULT_DRAW_TICKS;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultGridLines#getTickMarkLength()
	 */
	@Override
	public int getTickMarkLength() {
		return DEFAULT_TICK_MARK_LENGTH;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultGridLines#getZeroLineWidth()
	 */
	@Override
	public int getZeroLineWidth() {
		return DEFAULT_ZERO_LINE_WIDTH;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultGridLines#getZeroLineColor()
	 */
	@Override
	public String getZeroLineColor() {
		return DEFAULT_ZERO_LINE_COLOR;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultGridLines#getZeroLineBorderDash()
	 */
	@Override
	public ArrayInteger getZeroLineBorderDash() {
		return new ArrayInteger();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultGridLines#getZeroLineBorderDashOffset()
	 */
	@Override
	public int getZeroLineBorderDashOffset() {
		return DEFAULT_ZERO_LINE_BORDER_DASH_OFFSET;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultGridLines#isOffsetGridLines()
	 */
	@Override
	public boolean isOffsetGridLines() {
		return DEFAULT_OFFSET_GRID_LINES;
	}
	
}
