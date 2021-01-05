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
package org.pepstock.charba.client.defaults.globals;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.defaults.IsDefaultAngleLines;

/**
 * CHART.JS default values for ANGLELINES element.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultAngleLines implements IsDefaultAngleLines {

	private static final boolean DEFAULT_DISPLAY = true;

	private static final int DEFAULT_LINE_WIDTH = 1;

	private static final double DEFAULT_BORDER_DASH_OFFSET = 0D;

	/**
	 * To avoid any instantiation
	 */
	DefaultAngleLines() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAngleLines#isDisplay()
	 */
	@Override
	public boolean isDisplay() {
		return DEFAULT_DISPLAY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAngleLines#getColorAsString()
	 */
	@Override
	public String getColorAsString() {
		return Defaults.get().getGlobal().getBorderColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAngleLines#getLineWidth()
	 */
	@Override
	public int getLineWidth() {
		return DEFAULT_LINE_WIDTH;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAngleLines#getBorderDashOffset()
	 */
	@Override
	public double getBorderDashOffset() {
		return DEFAULT_BORDER_DASH_OFFSET;
	}

}
