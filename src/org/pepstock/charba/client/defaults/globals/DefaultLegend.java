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

import org.pepstock.charba.client.defaults.IsDefaultLegend;
import org.pepstock.charba.client.defaults.IsDefaultLegendLabels;
import org.pepstock.charba.client.defaults.IsDefaultLegendTitle;
import org.pepstock.charba.client.enums.LegendAlign;
import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.enums.TextDirection;

/**
 * CHART.JS default values for LEGEND element.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultLegend implements IsDefaultLegend {

	private static final boolean DEFAULT_DISPLAY = true;

	private static final boolean DEFAULT_FULL_WIDTH = true;

	private static final boolean DEFAULT_REVERSE = false;

	private static final boolean DEFAULT_RTL = false;

	private final DefaultLegendLabels legendLabels = new DefaultLegendLabels();

	private final DefaultLegendTitle legendTitle = new DefaultLegendTitle();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.legend.IsDefaultLegend#getLabels()
	 */
	@Override
	public IsDefaultLegendLabels getLabels() {
		return legendLabels;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultLegend#getTitle()
	 */
	@Override
	public IsDefaultLegendTitle getTitle() {
		return legendTitle;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.legend.IsDefaultLegend#isDisplay()
	 */
	@Override
	public boolean isDisplay() {
		return DEFAULT_DISPLAY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.legend.IsDefaultLegend#isFullWidth()
	 */
	@Override
	public boolean isFullWidth() {
		return DEFAULT_FULL_WIDTH;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.legend.IsDefaultLegend#isReverse()
	 */
	@Override
	public boolean isReverse() {
		return DEFAULT_REVERSE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.legend.IsDefaultLegend#getPosition()
	 */
	@Override
	public Position getPosition() {
		return Position.TOP;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultLegend#getAlign()
	 */
	@Override
	public LegendAlign getAlign() {
		return LegendAlign.CENTER;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultLegend#isRtl()
	 */
	@Override
	public boolean isRtl() {
		return DEFAULT_RTL;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultLegend#getTextDirection()
	 */
	@Override
	public TextDirection getTextDirection() {
		return TextDirection.LEFT_TO_RIGHT;
	}

}
