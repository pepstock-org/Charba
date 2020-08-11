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

import org.pepstock.charba.client.commons.IsEnvelop;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.data.DataEnvelop;
import org.pepstock.charba.client.defaults.IsDefaultOptions;
import org.pepstock.charba.client.items.UndefinedValues;

/**
 * Manages the SPANGAPS property of options in order to use the same logic between line datasets and options/configuration.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class SpanGapper extends NativeObjectContainer {

	// default value
	private final IsDefaultOptions defaultValues;

	/**
	 * Name of properties of native object.
	 */
	protected enum Property implements Key
	{
		SPAN_GAPS("spanGaps");

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
	 * Creates a span gapper with the envelop of the native object where SPANGAPS property must be managed and the default value to use when the property does not exist.
	 * 
	 * @param envelop envelop of the native object where SPANGAPS property must be managed
	 * @param defaultValues default value of SPANGAPS to use when the property does not exist
	 */
	public SpanGapper(DataEnvelop<NativeObject> envelop, IsDefaultOptions defaultValues) {
		this(IsEnvelop.checkAndGetIfValid(envelop).getContent(), defaultValues);
	}

	/**
	 * Creates a span gapper with the native object where SPANGAPS property must be managed and the default value to use when the property does not exist.
	 * 
	 * @param nativeObject native object where SPANGAPS property must be managed
	 * @param defaultValues default value of SPANGAPS to use when the property does not exist
	 */
	SpanGapper(NativeObject nativeObject, IsDefaultOptions defaultValues) {
		super(nativeObject);
		// checks default value instance
		if (defaultValues == null) {
			throw new IllegalArgumentException("Default value argument is null");
		}
		this.defaultValues = defaultValues;
	}

	/**
	 * Returns the default value of options to use when the property does not exist.
	 * 
	 * @return the default value of options to use when the property does not exist
	 */
	protected IsDefaultOptions getDefaultValues() {
		return defaultValues;
	}

	/**
	 * Sets if lines will be drawn between points with no or null data.<br>
	 * If <code>false</code>, points with {@link Double#NaN} data will create a break in the line.
	 * 
	 * @param spanGaps <code>true</code> if lines will be drawn between points with no or null data.<br>
	 *            If <code>false</code>, points with {@link Double#NaN} data will create a break in the line
	 */
	void setSpanGaps(boolean spanGaps) {
		setValue(Property.SPAN_GAPS, spanGaps);
	}

	/**
	 * Sets the value of the data if lines will be drawn between points with no or null data.
	 * 
	 * @param spanGaps the value of the data if lines will be drawn between points with no or null data
	 */
	void setSpanGaps(double spanGaps) {
		setValue(Property.SPAN_GAPS, spanGaps);
	}

	/**
	 * Returns if lines will be drawn between points with no or null data.<br>
	 * If <code>false</code>, points with {@link Double#NaN} data will create a break in the line.
	 * 
	 * @return <code>true</code> if lines will be drawn between points with no or null data.<br>
	 *         If <code>false</code>, points with {@link Double#NaN} data will create a break in the line
	 */
	boolean isSpanGaps() {
		// checks the type of stored value
		if (ObjectType.NUMBER.equals(type(Property.SPAN_GAPS))) {
			// if the there is a number, span gaps is activated
			return true;
		}
		return getValue(Property.SPAN_GAPS, getDefaultValues().isSpanGaps());
	}

	/**
	 * Returns the value of the data if lines will be drawn between points with no or null data.
	 * 
	 * @return the value of the data if lines will be drawn between points with no or null data
	 */
	double getSpanGaps() {
		return getValue(Property.SPAN_GAPS, UndefinedValues.DOUBLE);
	}
}
