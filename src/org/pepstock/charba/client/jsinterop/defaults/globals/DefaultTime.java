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
