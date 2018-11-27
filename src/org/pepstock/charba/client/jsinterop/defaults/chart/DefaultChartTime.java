package org.pepstock.charba.client.jsinterop.defaults.chart;

import java.util.Date;

import org.pepstock.charba.client.enums.TimeUnit;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultTime;
import org.pepstock.charba.client.jsinterop.options.Time;

public final class DefaultChartTime implements IsDefaultTime{
	
	private final Time time;

	/**
	 * @param time
	 */
	DefaultChartTime(Time time) {
		this.time = time;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTime#isIsoWeekday()
	 */
	@Override
	public boolean isIsoWeekday() {
		return time.isIsoWeekday();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTime#getMax()
	 */
	@Override
	public Date getMax() {
		return time.getMax();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTime#getMin()
	 */
	@Override
	public Date getMin() {
		return time.getMin();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTime#getRound()
	 */
	@Override
	public TimeUnit getRound() {
		return time.getRound() != null ? time.getRound() : null;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTime#getTooltipFormat()
	 */
	@Override
	public String getTooltipFormat() {
		return time.getTooltipFormat();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTime#getUnit()
	 */
	@Override
	public TimeUnit getUnit() {
		return time.getUnit() != null ? time.getUnit() : null ;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTime#getStepSize()
	 */
	@Override
	public int getStepSize() {
		return time.getStepSize();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTime#getMinUnit()
	 */
	@Override
	public TimeUnit getMinUnit() {
		return time.getMinUnit();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTime#getParser()
	 */
	@Override
	public String getParser() {
		return time.getParser();
	}

}
