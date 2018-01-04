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

import java.util.List;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.commons.AbstractList;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ChartContainer;
import org.pepstock.charba.client.commons.JsObjectContainerArrayList;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.options.scales.Axis;

/**
 * The configuration element which contains all axes definitions.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class Scales extends ChartContainer {

	private static final boolean DEFAULT_DISPLAY = true;
	// buffer to maintain axes
	private final AbstractList<Axis> yAxes = new JsObjectContainerArrayList<>();
	// buffer to maintain axes
	private final AbstractList<Axis> xAxes = new JsObjectContainerArrayList<>();

	/**
	 * Name of fields of JavaScript object.
	 */
	enum Property implements Key
	{
		display,
		xAxes,
		yAxes
	}

	/**
	 * Builds the object storing the chart instance.
	 * 
	 * @param chart chart instance
	 */
	protected Scales(AbstractChart<?, ?> chart) {
		super(chart);
	}

	/**
	 * Sets if the scales are shown.
	 * 
	 * @param display if the scales are shown.
	 */
	public void setDisplay(boolean display) {
		setValue(Property.display, display);
	}

	/**
	 * Returns if the scales are shown.
	 * 
	 * @return the scales are shown. Default is true.
	 */
	public boolean isDisplay() {
		return getValue(Property.display, DEFAULT_DISPLAY);
	}

	/**
	 * Sets an array of X axes.
	 * 
	 * @param axesan array of axes.
	 */
	public void setXAxes(Axis... axes) {
		// set java script array
		setValue(Property.xAxes, ArrayListHelper.load(this.xAxes, axes));
		loadChartToAxes(axes);
	}

	/**
	 * @return the xAxes
	 */
	public List<Axis> getXAxes() {
		return xAxes;
	}

	/**
	 * Sets an array of Y axes.
	 * 
	 * @param axesan array of axes.
	 */
	public void setYAxes(Axis... axes) {
		// set java script array
		setValue(Property.yAxes, ArrayListHelper.load(this.yAxes, axes));
		loadChartToAxes(axes);
	}

	/**
	 * @return the yAxes
	 */
	public List<Axis> getYAxes() {
		return yAxes;
	}

	/**
	 * Sets the chart instance to loaded axes.
	 * 
	 * @param axes array of axes
	 */
	private void loadChartToAxes(Axis... axes) {
		// checks the axes array
		if (axes != null && axes.length > 0) {
			// scans array
			for (Axis axis : axes) {
				// sets chart instance
				axis.setChart(getChart());
			}
		}
	}

}