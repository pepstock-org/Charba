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
package org.pepstock.charba.client.data;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.defaults.IsDefaultOptions;
import org.pepstock.charba.client.enums.BorderAlign;

/**
 * The pie chart allows a number of properties to be specified for each dataset. These are used to set display properties for a
 * specific dataset.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class PieDataset extends HovingDataset {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		borderAlign,
		weight
	}
	
	/**
	 * Creates a dataset.<br>
	 * It uses the global options has default.
	 */
	public PieDataset() {
		this(null);
	}

	/**
	 * Creates the dataset using a default.
	 * 
	 * @param defaultValues default options
	 */
	public PieDataset(IsDefaultOptions defaultValues) {
		super(defaultValues);
	}
	
	/**
	 * Sets the property to set the border alignment on chart datasets.
	 * 
	 * @param align the property to set the border alignment on chart datasets
	 */
	public void setBorderAlign(BorderAlign align) {
		setValue(Property.borderAlign, align);
	}
	
	/**
	 * Returns the property to set the border alignment on chart datasets.
	 * 
	 * @return the property to set the border alignment on chart datasets.
	 */
	public BorderAlign getBorderAlign() {
		return getValue(Property.borderAlign, BorderAlign.class, getDefaultValues().getElements().getArc().getBorderAlign());
	}

	/**
	 * Sets the relative thickness of the dataset.<br>
	 * Providing a value for weight will cause the pie or doughnut dataset to be drawn with a thickness relative to the sum of
	 * all the dataset weight values.
	 * 
	 * @param weight the relative thickness of the dataset
	 */
	public void setWeight(double weight) {
		setValue(Property.weight, weight);
	}
	
	/**
	 * Returns the relative thickness of the dataset.<br>
	 * Providing a value for weight will cause the pie or doughnut dataset to be drawn with a thickness relative to the sum of
	 * all the dataset weight values.
	 * 
	 * @return the relative thickness of the dataset
	 */
	public double getWeight() {
		return getValue(Property.weight, getDefaultValues().getElements().getArc().getWeight());
	}
}