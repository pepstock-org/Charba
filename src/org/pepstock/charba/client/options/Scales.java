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
 * 
 * display: true type:[boolean]
 * 
 * 
 * 
 */
public class Scales extends ChartContainer{
	
	private static final boolean DEFAULT_DISPLAY = true;
	
	private final AbstractList<Axis> yAxes = new JsObjectContainerArrayList<>();
	
	private final AbstractList<Axis> xAxes = new JsObjectContainerArrayList<>();
	
	enum Property implements Key {
		display,
		xAxes,
		yAxes
	}
	
	protected Scales(AbstractChart<?, ?> chart) {
		super(chart);
	}

	public void setDisplay(boolean display) {
		setValue(Property.display, display);
	}

	public boolean isDisplay() {
		return getValue(Property.display, DEFAULT_DISPLAY);
	}

	
	public void setXAxes(Axis... axes){
		setValue(Property.xAxes, ArrayListHelper.load(this.xAxes, axes));
		loadChartToAxes(axes);
	}

	/**
	 * @return the xAxes
	 */
	public List<Axis> getXAxes() {
		return xAxes;
	}

	public void setYAxes(Axis... axes){
		setValue(Property.yAxes, ArrayListHelper.load(this.yAxes, axes));
		loadChartToAxes(axes);
	}

	/**
	 * @return the yAxes
	 */
	public List<Axis> getYAxes() {
		return yAxes;
	}

	private void loadChartToAxes(Axis... axes){
		if (axes != null && axes.length > 0){
			for (Axis axis : axes){
				axis.setChart(getChart());
			}
		}
	}
	
}