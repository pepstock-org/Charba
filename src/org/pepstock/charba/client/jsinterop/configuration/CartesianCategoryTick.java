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

import java.util.List;

/**
 * The category scale provides the following options for configuring tick marks.<br>
 * The labels are drawn from one of the label arrays included in the chart data.
 * 
 * @author Andrea "Stock" Stocchero
 * @version 2.0
 *
 */
public class CartesianCategoryTick extends CartesianTick {

	/**
	 * Builds the object storing the axis instance.
	 * 
	 * @param axis axis instance
	 */
	CartesianCategoryTick(Axis axis) {
		super(axis);
	}

	/**
	 * Sets an array of labels to display.
	 * 
	 * @param labels An array of labels to display.
	 */
	public void setLabels(String... labels) {
		getConfiguration().setLabels(labels);
	}

	/**
	 * Sets an array of labels to display.
	 * 
	 * @param labels An array of labels to display.
	 */
	public void setLabels(List<String> labels) {
		getConfiguration().setLabels(labels);
	}

	/**
	 * Returns the array of labels to display.
	 * 
	 * @return the array of labels to display.
	 */
	public List<String> getLabels() {
		return getConfiguration().getLabels();
	}

	/**
	 * Sets the minimum item to display.
	 * 
	 * @param min The minimum item to display
	 */
	public void setMin(String min) {
		getConfiguration().setMin(min);
	}

	/**
	 * Returns the minimum item to display
	 * 
	 * @return The minimum item to display
	 */
	public String getMin() {
		return getConfiguration().getMinAsString();
	}

	/**
	 * Sets the maximum item to display.
	 * 
	 * @param max the maximum item to display.
	 */
	public void setMax(String max) {
		getConfiguration().setMax(max);
	}

	/**
	 * Returns the maximum item to display.
	 * 
	 * @return the maximum item to display.
	 */
	public String getMax() {
		return getConfiguration().getMaxAsString();
	}

}