package org.pepstock.charba.client.jsinterop.defaults.chart;

import org.pepstock.charba.client.jsinterop.defaults.IsDefaultAngleLines;
import org.pepstock.charba.client.jsinterop.options.AngleLines;

public final class DefaultChartAngleLines implements IsDefaultAngleLines{
	
	private final AngleLines angleLines;

	/**
	 * @param ang
	 */
	DefaultChartAngleLines(AngleLines angleLines) {
		this.angleLines = angleLines;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultAngleLines#isDisplay()
	 */
	@Override
	public boolean isDisplay() {
		return angleLines.isDisplay();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultAngleLines#getColor()
	 */
	@Override
	public String getColor() {
		return angleLines.getColorAsString();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultAngleLines#getLineWidth()
	 */
	@Override
	public int getLineWidth() {
		return angleLines.getLineWidth();
	}

}
