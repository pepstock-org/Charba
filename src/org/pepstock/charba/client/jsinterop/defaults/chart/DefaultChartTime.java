package org.pepstock.charba.client.jsinterop.defaults.chart;

import org.pepstock.charba.client.jsinterop.commons.Checker;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultTime;
import org.pepstock.charba.client.jsinterop.options.Time;

import com.google.gwt.core.client.JsDate;

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
	public JsDate getMax() {
		return Checker.fromDate(time.getMax());
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTime#getMin()
	 */
	@Override
	public JsDate getMin() {
		return Checker.fromDate(time.getMin());
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTime#getRound()
	 */
	@Override
	public String getRound() {
		return time.getRound() != null ? time.getRound().name() : null;
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
	public String getUnit() {
		return time.getUnit() != null ? time.getUnit().name() : null ;
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
	public String getMinUnit() {
		return time.getMinUnit().name();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTime#getParser()
	 */
	@Override
	public String getParser() {
		return time.getParser();
	}

}
