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
package org.pepstock.charba.client.defaults.chart;

import java.util.List;

import org.pepstock.charba.client.defaults.IsDefaultScaleBorder;
import org.pepstock.charba.client.options.ScaleBorder;

/**
 * Defaults for scale border option element, based on chart type.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultChartScaleBorder implements IsDefaultScaleBorder {

	private final ScaleBorder border;

	/**
	 * Creates the object by scale border option element instance.
	 * 
	 * @param border scale border option element instance.
	 */
	DefaultChartScaleBorder(ScaleBorder border) {
		this.border = border;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScaleBorder#isDisplay()
	 */
	@Override
	public boolean isDisplay() {
		return border.isDisplay();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScaleBorder#getWidth()
	 */
	@Override
	public int getWidth() {
		return border.getWidth();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScaleBorder#getColorAsString()
	 */
	@Override
	public String getColorAsString() {
		return border.getColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScaleBorder#getDashOffset()
	 */
	@Override
	public double getDashOffset() {
		return border.getDashOffset();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScaleBorder#getDash()
	 */
	@Override
	public List<Integer> getDash() {
		return border.getDash();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScaleBorder#getZ()
	 */
	@Override
	public int getZ() {
		return border.getZ();
	}

}
