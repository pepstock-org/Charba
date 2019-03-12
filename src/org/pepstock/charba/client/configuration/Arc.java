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
package org.pepstock.charba.client.configuration;

import org.pepstock.charba.client.enums.BorderAlign;
import org.pepstock.charba.client.options.ExtendedOptions;

/**
 * Arcs are used in the polar area, doughnut and pie charts.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class Arc extends AbstractConfigurationElement {

	/**
	 * Builds the object setting the java script options object and defaults options for arc.
	 * 
	 * @param options root options of chart
	 */
	Arc(ExtendedOptions options) {
		super(options, options.getElements().getArc());
	}
	
	/**
	 * Sets the property to set the border alignment on chart datasets.
	 * 
	 * @param align the property to set the border alignment on chart datasets
	 */
	public void setBorderAlign(BorderAlign align) {
		getOptions().getElements().getArc().setBorderAlign(align);
	}
	
	/**
	 * Returns the property to set the border alignment on chart datasets.
	 * 
	 * @return the property to set the border alignment on chart datasets.
	 */
	public BorderAlign getBorderAlign() {
		return getOptions().getElements().getArc().getBorderAlign();
	}

	/**
	 * Sets the relative thickness of the dataset.<br>
	 * Providing a value for weight will cause the pie or doughnut dataset to be drawn with a thickness relative to the sum of
	 * all the dataset weight values.
	 * 
	 * @param weight the relative thickness of the dataset
	 */
	public void setWeight(double weight) {
		getOptions().getElements().getArc().setWeight(weight);
	}
	
	/**
	 * Returns the relative thickness of the dataset.<br>
	 * Providing a value for weight will cause the pie or doughnut dataset to be drawn with a thickness relative to the sum of
	 * all the dataset weight values.
	 * 
	 * @return the relative thickness of the dataset
	 */
	public double getWeight() {
		return getOptions().getElements().getArc().getWeight();
	}
}