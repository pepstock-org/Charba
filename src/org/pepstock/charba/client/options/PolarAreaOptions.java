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
import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.commons.Key;

/**
 * pecific options for POLAR AREA chart. It contains all properties for this kind of chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class PolarAreaOptions extends SingleScaleOptions {

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		startAngle
	}

	/**
	 * Builds the object storing the chart instance.<br>
	 * Sets also the internal parts of options.
	 * 
	 * @param chart chart instance
	 */
	public PolarAreaOptions(AbstractChart<?, ?> chart) {
		super(chart);
		// sets rotate and animation scale
		getAnimation().setAnimateRotate(true);
		getAnimation().setAnimateScale(true);
	}

	/**
	 * Sets the starting angle to draw arcs for the first item in a dataset.
	 * 
	 * @param startAngle starting angle to draw arcs for the first item in a dataset.
	 */
	public void setStartAngle(double startAngle) {
		setValue(Property.startAngle, startAngle);
	}

	/**
	 * Returns the starting angle to draw arcs for the first item in a dataset.
	 * 
	 * @return starting angle to draw arcs for the first item in a dataset. Default is <code>-0.5 * Math.PI</code>.
	 */
	public double getStartAngle() {
		return getValue(Property.startAngle, Defaults.getGlobal().getStartAngle());
	}
}