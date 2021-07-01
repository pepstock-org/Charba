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
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.items.Undefined;

/**
 * Entity of {@link ZoomPlugin#ID} configuration in order to set minimum and maximum values of the scales for pan or zoom.<br>
 * The values to set to the properties depends on the type of scales are used.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class ScaleRange extends NativeObjectContainer implements IsDefaultScaleRange {

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		MIN("min"),
		MAX("max");

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
	private IsDefaultScaleRange defaultOptions;

	/**
	 * Creates new scale range element.
	 */
	public ScaleRange() {
		this(Undefined.DOUBLE);
	}

	/**
	 * Creates scale range element with the minimum value.
	 * 
	 * @param min the minimum value of range
	 */
	public ScaleRange(double min) {
		this(min, Undefined.DOUBLE);
	}

	/**
	 * Creates scale range element with the minimum and maximum values.
	 * 
	 * @param min the minimum value of range
	 * @param max the maximum value of range
	 */
	public ScaleRange(double min, double max) {
		this(DefaultScaleRange.INSTANCE, null);
		// checks if arguments are consistent
		if (Undefined.isNot(min)) {
			// stores the value
			setMin(min);
		}
		if (Undefined.isNot(max)) {
			// stores the value
			setMax(max);
		}
	}

	/**
	 * Creates new scale range element, using stored native object instance and the default values options.
	 * 
	 * @param defaultOptions default scale range options to returns the default when required.
	 * @param nativeObject stored scale range values in the native object to read.
	 */
	ScaleRange(IsDefaultScaleRange defaultOptions, NativeObject nativeObject) {
		super(nativeObject);
		// checks if defaults options is consistent
		// stores defaults options
		this.defaultOptions = checkDefaultValuesArgument(defaultOptions);
	}

	/**
	 * Sets the minimum value of range.
	 * 
	 * @param min the minimum value of range
	 */
	public final void setMin(double min) {
		setValue(Property.MIN, min);
	}

	/**
	 * Returns the minimum value of range.
	 * 
	 * @return the minimum value of range
	 */
	@Override
	public final double getMin() {
		return getValue(Property.MIN, defaultOptions.getMin());
	}

	/**
	 * Sets the maximum value of range.
	 * 
	 * @param max the maximum value of range
	 */
	public final void setMax(double max) {
		setValue(Property.MAX, max);
	}

	/**
	 * Returns the maximum value of range.
	 * 
	 * @return the maximum value of range
	 */
	@Override
	public final double getMax() {
		return getValue(Property.MAX, defaultOptions.getMax());
	}

	/**
	 * Returns the native object instance.
	 * 
	 * @return the native object instance.
	 */
	final NativeObject nativeObject() {
		return getNativeObject();
	}

}
