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

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultArc;
import org.pepstock.charba.client.enums.BorderAlign;

/**
 * Arcs are used in the polar area, doughnut and pie charts.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class Arc extends AbstractElement<IsDefaultArc> implements IsDefaultArc {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		BORDER_ALIGN("borderAlign"),
		WEIGHT("weight"),
		ANGLE("angle");

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

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script
	 * properties.
	 * 
	 * @param elements parent node to use to add this element where changed
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	Arc(Elements elements, Key childKey, IsDefaultArc defaultValues, NativeObject nativeObject) {
		super(elements, childKey, defaultValues, nativeObject);
	}

	/**
	 * Sets the property to set the border alignment on chart datasets.
	 * 
	 * @param align the property to set the border alignment on chart datasets
	 */
	public void setBorderAlign(BorderAlign align) {
		setValue(Property.BORDER_ALIGN, align);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the property to set the border alignment on chart datasets.
	 * 
	 * @return the property to set the border alignment on chart datasets.
	 */
	public BorderAlign getBorderAlign() {
		return getValue(Property.BORDER_ALIGN, BorderAlign.class, getDefaultValues().getBorderAlign());
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
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the relative thickness of the dataset.<br>
	 * Providing a value for weight will cause the pie or doughnut dataset to be drawn with a thickness relative to the sum of
	 * all the dataset weight values.
	 * 
	 * @return the relative thickness of the dataset
	 */
	public double getWeight() {
		return getValue(Property.WEIGHT, getDefaultValues().getWeight());
	}

	/**
	 * Sets the arc angle to cover.
	 * 
	 * @param angle the arc angle to cover
	 */
	public void setAngle(double angle) {
		setValue(Property.ANGLE, angle);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the arc angle to cover.
	 * 
	 * @return the arc angle to cover
	 */
	public double getAngle() {
		return getValue(Property.ANGLE, getDefaultValues().getAngle());
	}
}