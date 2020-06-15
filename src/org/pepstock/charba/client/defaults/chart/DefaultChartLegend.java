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

import org.pepstock.charba.client.defaults.IsDefaultLegend;
import org.pepstock.charba.client.defaults.IsDefaultLegendLabels;
import org.pepstock.charba.client.defaults.IsDefaultLegendTitle;
import org.pepstock.charba.client.enums.ElementAlign;
import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.enums.TextDirection;
import org.pepstock.charba.client.options.Legend;

/**
 * Defaults for legend option element, based on chart type.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultChartLegend implements IsDefaultLegend {

	private final Legend legend;

	private final DefaultChartLegendLabels labels;

	private final DefaultChartLegendTitle title;

	/**
	 * Creates the object by legend option element instance.
	 * 
	 * @param legend legend option element instance.
	 */
	DefaultChartLegend(Legend legend) {
		this.legend = legend;
		// creates sub element
		labels = new DefaultChartLegendLabels(legend.getLabels());
		title = new DefaultChartLegendTitle(legend.getTitle());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultLegend#getLabels()
	 */
	@Override
	public IsDefaultLegendLabels getLabels() {
		return labels;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultLegend#getTitle()
	 */
	@Override
	public IsDefaultLegendTitle getTitle() {
		return title;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultLegend#isDisplay()
	 */
	@Override
	public boolean isDisplay() {
		return legend.isDisplay();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultLegend#isFullWidth()
	 */
	@Override
	public boolean isFullWidth() {
		return legend.isFullWidth();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultLegend#isReverse()
	 */
	@Override
	public boolean isReverse() {
		return legend.isReverse();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultLegend#getPosition()
	 */
	@Override
	public Position getPosition() {
		return legend.getPosition();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultLegend#getAlign()
	 */
	@Override
	public ElementAlign getAlign() {
		return legend.getAlign();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultLegend#isRtl()
	 */
	@Override
	public boolean isRtl() {
		return legend.isRtl();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultLegend#getTextDirection()
	 */
	@Override
	public TextDirection getTextDirection() {
		return legend.getTextDirection();
	}

}
