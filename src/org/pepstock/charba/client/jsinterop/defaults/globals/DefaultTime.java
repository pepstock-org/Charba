package org.pepstock.charba.client.jsinterop.defaults.globals;

import java.util.Date;

import org.pepstock.charba.client.enums.TimeUnit;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultTime;

public class DefaultTime implements IsDefaultTime {
	
	private static final boolean DEFAULT_ISO_WEEKDAY = true;
	
	private static final int DEFAULT_STEP_SIZE = 1;

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTime#isIsoWeekday()
	 */
	@Override
	public boolean isIsoWeekday() {
		return DEFAULT_ISO_WEEKDAY;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTime#getMax()
	 */
	@Override
	public Date getMax() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTime#getMin()
	 */
	@Override
	public Date getMin() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTime#getRound()
	 */
	@Override
	public TimeUnit getRound() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTime#getTooltipFormat()
	 */
	@Override
	public String getTooltipFormat() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTime#getUnit()
	 */
	@Override
	public TimeUnit getUnit() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTime#getStepSize()
	 */
	@Override
	public int getStepSize() {
		return DEFAULT_STEP_SIZE;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTime#getMinUnit()
	 */
	@Override
	public TimeUnit getMinUnit() {
		return TimeUnit.millisecond;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTime#getParser()
	 */
	@Override
	public String getParser() {
		return null;
	}

}
