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
package org.pepstock.charba.client.zoom;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;

/**
 * Entity of {@link ZoomPlugin#ID} configuration in order to set minimum and maximum values of the scales for pan or zoom.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ScaleLimit extends ScaleRange implements IsDefaultScaleLimit {

	// string value to set is the roginal limits are set
	private static final String ORIGINAL_LIMIT = "original";

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		MIN_RANGE("minRange");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use in the native object.
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

	// defaults global options instance
	private IsDefaultScaleLimit defaultOptions;

	/**
	 * Creates new scale limit element, using stored native object instance and the default values options.
	 * 
	 * @param defaultOptions default scale limit options to returns the default when required.
	 * @param nativeObject stored scale limit values in the native object to read.
	 */
	ScaleLimit(IsDefaultScaleLimit defaultOptions, NativeObject nativeObject) {
		super(defaultOptions, nativeObject);
		// checks if defaults options is consistent
		// stores defaults options
		this.defaultOptions = checkDefaultValuesArgument(defaultOptions);
	}

	/**
	 * Sets <code>true</code> to use whatever minimum limit the scale had when the chart was first displayed.
	 * 
	 * @param enable <code>true</code> to use whatever minimum limit the scale had when the chart was first displayed
	 */
	public final void setOriginalMin(boolean enable) {
		setOringinal(ScaleRange.Property.MIN, enable);
	}

	/**
	 * Returns <code>true</code> to use whatever minimum limit the scale had when the chart was first displayed.
	 * 
	 * @return <code>true</code> to use whatever minimum limit the scale had when the chart was first displayed
	 */
	@Override
	public final boolean isOriginalMin() {
		return isType(ScaleRange.Property.MIN, ObjectType.STRING) || defaultOptions.isOriginalMin();
	}

	/**
	 * Sets <code>true</code> to use whatever maximum limit the scale had when the chart was first displayed.
	 * 
	 * @param enable <code>true</code> to use whatever maximum limit the scale had when the chart was first displayed
	 */
	public final void setOriginalMax(boolean enable) {
		setOringinal(ScaleRange.Property.MAX, enable);
	}

	/**
	 * Returns <code>true</code> to use whatever maximum limit the scale had when the chart was first displayed.
	 * 
	 * @return <code>true</code> to use whatever maximum limit the scale had when the chart was first displayed
	 */
	@Override
	public final boolean isOriginalMax() {
		return isType(ScaleRange.Property.MAX, ObjectType.STRING) || defaultOptions.isOriginalMax();
	}

	/**
	 * Sets the minimum allowed range.<br>
	 * This defines the max zoom level.
	 * 
	 * @param min the minimum allowed range.<br>
	 *            This defines the max zoom level.
	 */
	public void setMinRange(double min) {
		setValue(Property.MIN_RANGE, min);
	}

	/**
	 * Returns the minimum allowed range.<br>
	 * This defines the max zoom level.
	 * 
	 * @return the minimum allowed range.<br>
	 *         This defines the max zoom level.
	 */
	@Override
	public double getMinRange() {
		return getValue(Property.MIN_RANGE, defaultOptions.getMinRange());
	}

	/**
	 * Manages the original value for the property passed.
	 * 
	 * @param property property of limit to set
	 * @param enable <code>true</code> to use whatever whatever limits the scale had when the chart was first displayed
	 */
	private void setOringinal(Key property, boolean enable) {
		// checks if is enabling
		if (enable) {
			setValue(property, ORIGINAL_LIMIT);
		} else {
			// if here, original just be disabled
			// removing property and using default
			remove(property);
		}
	}
}
