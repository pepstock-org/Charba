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
import org.pepstock.charba.client.options.scales.Axis;

/**
 * Configuration of chart with only 1 scale.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class SingleScaleOptions extends BaseOptions{

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key {
		scale
	}
	
	/**
	 * Builds the object storing the chart instance.
	 * @param chart chart instance
	 */
	public SingleScaleOptions(AbstractChart<?, ?> chart) {
		super(chart);
	}

	/**
	 * Sets an array of X axes.
	 * 
	 * @param axesan array of axes.
	 */
	public void setXAxis(Axis axis){
		// sets chart instance
		axis.setChart(getChart());
		// set java script array
		setValue(Property.scale, axis);
	}

}