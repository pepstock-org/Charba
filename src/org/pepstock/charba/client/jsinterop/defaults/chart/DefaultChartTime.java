/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
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
