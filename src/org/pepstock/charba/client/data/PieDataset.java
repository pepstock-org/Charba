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

/**
 * The pie chart allows a number of properties to be specified for each dataset. These are used to set display properties for a
 * specific dataset.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class PieDataset extends HovingDataset implements HasBorderAlign {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		WEIGHT("weight");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use into native object.
		 * 
		 * @param value value of property name
		 */
		private Property(String value) {
			this.value = value;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.Key#value()
		 */
		@Override
		public String value() {
			return value;
		}

	}

	// instance of border aligner
	private final BorderAligner borderAligner;

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
		// creates border aligner instance
		this.borderAligner = new BorderAligner(getNativeObject(), getDefaultValues());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.HasBorderAlign#getBorderAligner()
	 */
	@Override
	public final BorderAligner getBorderAligner() {
		return borderAligner;
	}

	/**
	 * Sets the relative thickness of the dataset.<br>
	 * Providing a value for weight will cause the pie or doughnut dataset to be drawn with a thickness relative to the sum of
	 * all the dataset weight values.
	 * 
	 * @param weight the relative thickness of the dataset
	 */
	public void setWeight(double weight) {
		setValue(Property.WEIGHT, weight);
	}

	/**
	 * Returns the relative thickness of the dataset.<br>
	 * Providing a value for weight will cause the pie or doughnut dataset to be drawn with a thickness relative to the sum of
	 * all the dataset weight values.
	 * 
	 * @return the relative thickness of the dataset
	 */
	public double getWeight() {
		return getValue(Property.WEIGHT, getDefaultValues().getElements().getArc().getWeight());
	}
}