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

/**
 * Entity of {@link ZoomPlugin#ID} configuration in order to set minimum and maximum values of the scales for pan or zoom.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ScaleLimit extends ScaleRange implements IsDefaultScaleLimit {

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

}
