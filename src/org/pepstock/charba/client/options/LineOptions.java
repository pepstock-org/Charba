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
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.commons.Key;

/**
 * Specific options for LINE chart. It contains all properties for this kind of chart.
 *
 * @author Andrea "Stock" Stocchero
 *
 */
public class LineOptions extends MultiScalesOptions {

	private static final boolean DEFAULT_SHOW_LINE = true;

	private static final boolean DEFAULT_SPAN_GAPS = false;

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		showLine,
		spanGaps
	}

	/**
	 * Builds the object storing the chart instance.
	 * 
	 * @param chart chart instance
	 */
	public LineOptions(AbstractChart<?, ?> chart) {
		super(chart);
	}

	/**
	 * If false, the lines between points are not drawn.
	 * 
	 * @param showLine If false, the lines between points are not drawn.
	 */
	public void setShowLine(boolean showLine) {
		setValue(Property.showLine, showLine);
	}

	/**
	 * If false, the lines between points are not drawn.
	 * 
	 * @return If false, the lines between points are not drawn.. Default is true.
	 */
	public boolean isShowLine() {
		return getValue(Property.showLine, DEFAULT_SHOW_LINE);
	}

	/**
	 * If false, NaN data causes a break in the line.
	 * 
	 * @param spanGaps If false, NaN data causes a break in the line.
	 */
	public void setSpanGaps(boolean spanGaps) {
		setValue(Property.spanGaps, spanGaps);
	}

	/**
	 * If false, NaN data causes a break in the line.
	 * 
	 * @return If false, NaN data causes a break in the line. Default is false.
	 */
	public boolean isSpanGaps() {
		return getValue(Property.spanGaps, DEFAULT_SPAN_GAPS);
	}

}