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

import org.pepstock.charba.client.jsinterop.defaults.IsDefaultArc;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultElements;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultLine;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultPoint;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultRectangle;
import org.pepstock.charba.client.jsinterop.options.Elements;

/**
 * Defaults for elements option element, based on chart type.
 * 
 * @author Andrea "Stock" Stocchero
 * @version 2.0
 */
public final class DefaultChartElements implements IsDefaultElements {

	private final DefaultChartArc arc;

	private final DefaultChartLine line;

	private final DefaultChartPoint point;

	private final DefaultChartRectangle rectangle;

	/**
	 * Creates the object by elements option element instance.
	 * 
	 * @param elements elements option element instance.
	 */
	DefaultChartElements(Elements elements) {
		arc = new DefaultChartArc(elements.getArc());
		line = new DefaultChartLine(elements.getLine());
		point = new DefaultChartPoint(elements.getPoint());
		rectangle = new DefaultChartRectangle(elements.getRectangle());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultElements#getArc()
	 */
	@Override
	public IsDefaultArc getArc() {
		return arc;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultElements#getLine()
	 */
	@Override
	public IsDefaultLine getLine() {
		return line;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultElements#getPoint()
	 */
	@Override
	public IsDefaultPoint getPoint() {
		return point;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultElements#getRectangle()
	 */
	@Override
	public IsDefaultRectangle getRectangle() {
		return rectangle;
	}

}
