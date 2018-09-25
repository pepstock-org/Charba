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
package org.pepstock.charba.client.jsinterop.configuration;

import org.pepstock.charba.client.jsinterop.AbstractChart;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions;

/**
 * Abstract options for PIE chart. It contains all properties for this kind of chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class AbstractPieOptions extends ConfigurationOptions {

	/**
	 * Builds the object storing the chart instance.
	 * 
	 * @param chart chart instance
	 */
	protected AbstractPieOptions(AbstractChart<?, ?> chart, IsDefaultOptions defaultvalues) {
		super(chart, defaultvalues);
	}

	/**
	 * Sets the percentage of the chart that is cut out of the middle.
	 * 
	 * @param cutoutPercentage the percentage of the chart that is cut out of the middle.
	 */
	public void setCutoutPercentage(double cutoutPercentage) {
		getConfiguration().setCutoutPercentage(cutoutPercentage);
	}

	/**
	 * Returns the the percentage of the chart that is cut out of the middle.
	 * 
	 * @return the percentage of the chart that is cut out of the middle. Default is {@link org.pepstock.charba.client.GlobalOptions#getCutoutPercentage()}.
	 */
	public double getCutoutPercentage() {
		return getConfiguration().getCutoutPercentage();
	}

	/**
	 * Sets the starting angle to draw arcs from.
	 * 
	 * @param rotation starting angle to draw arcs from.
	 */
	public void setRotation(double rotation) {
		getConfiguration().setRotation(rotation);
	}

	/**
	 * returns the starting angle to draw arcs from.
	 * 
	 * @return starting angle to draw arcs from. Default is {@link org.pepstock.charba.client.GlobalOptions#getRotation()}.
	 */
	public double getRotation() {
		return getConfiguration().getRotation();
	}

	/**
	 * Sets the sweep to allow arcs to cover.
	 * 
	 * @param circumference the sweep to allow arcs to cover.
	 */
	public void setCircumference(double circumference) {
		getConfiguration().setCircumference(circumference);
	}

	/**
	 * Returns the the sweep to allow arcs to cover.
	 * 
	 * @return the sweep to allow arcs to cover. Default is {@link org.pepstock.charba.client.GlobalOptions#getCircumference()}.
	 */
	public double getCircumference() {
		return getConfiguration().getCircumference();
	}

}