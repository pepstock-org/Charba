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
package org.pepstock.charba.client.enums;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;

/**
 * Manages the FILL property of options in order to use the same logic between line datasets and options/configuration.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Filler extends NativeObjectContainer {

	// default value
	private final IsFill defaultValue;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		FILL("fill"),
		// internal property key to map the type of FILL property
		CHARBA_FILLING_MODE("_charbaFillingMode");

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
	 * Creates a filler with the native object where FILL property must be managed and the default value to use when the
	 * property does not exist.
	 * 
	 * @param nativeObject native object where FILL property must be managed
	 * @param defaultValue default value of FILL to use when the property does not exist
	 */
	public Filler(NativeObject nativeObject, IsFill defaultValue) {
		super(nativeObject);
		// checks default value instance
		if (IsFill.isValid(defaultValue)) {
			throw new IllegalArgumentException("Default value is null or not consistent");
		}
		this.defaultValue = defaultValue;
	}

	/**
	 * Sets how to fill the area under the line.
	 * 
	 * @param fill <code>true</code> to fill, otherwise <code>false</code>.
	 */
	public void setFill(boolean fill) {
		setValue(Property.FILL, fill);
		// stores the filling mode
		setValue(Property.CHARBA_FILLING_MODE, FillingMode.PREDEFINED_BOOLEAN);
	}

	/**
	 * Sets how to fill the area under the line, by absolute dataset index.
	 * 
	 * @param index absolute dataset index of the chart.
	 */
	public final void setFill(int index) {
		setFill(Fill.getFill(index));
	}

	/**
	 * Sets how to fill the area under the line, by relative dataset index.
	 * 
	 * @param index relative dataset index of the chart.
	 */
	public final void setFill(String index) {
		setFill(Fill.getFill(index));
	}

	/**
	 * Sets how to fill the area under the line.
	 * 
	 * @param fill how to fill the area under the line.
	 */
	public void setFill(IsFill fill) {
		// checks if argument is consistent
		if (IsFill.isValid(fill)) {
			// checks if is no fill
			if (Fill.FALSE.equals(fill)) {
				// sets the boolean value instead of string one
				setValue(Property.FILL, false);
				// stores the filling mode
				setValue(Property.CHARBA_FILLING_MODE, FillingMode.PREDEFINED_BOOLEAN);
			} else if (Fill.isPredefined(fill)) {
				// sets value
				setValue(Property.FILL, fill);
				// stores the filling mode
				setValue(Property.CHARBA_FILLING_MODE, fill.getMode());
			} else if (FillingMode.ABSOLUTE_DATASET_INDEX.equals(fill.getMode())) {
				// sets value
				setValue(Property.FILL, fill.getValueAsInt());
				// stores the filling mode
				setValue(Property.CHARBA_FILLING_MODE, FillingMode.ABSOLUTE_DATASET_INDEX);
			} else if (FillingMode.RELATIVE_DATASET_INDEX.equals(fill.getMode())) {
				// sets value
				setValue(Property.FILL, fill.getValue());
				// stores the filling mode
				setValue(Property.CHARBA_FILLING_MODE, FillingMode.RELATIVE_DATASET_INDEX);
			}
		}
	}

	/**
	 * Returns how to fill the area under the line.
	 * 
	 * @return how to fill the area under the line.
	 */
	public final IsFill getFill() {
		// checks if there is the property
		if (has(Property.CHARBA_FILLING_MODE)) {
			// gets the filling mode
			FillingMode mode = getValue(Property.CHARBA_FILLING_MODE, FillingMode.class, FillingMode.PREDEFINED);
			// checks all possible types of filling mode
			// to return the right value
			if (FillingMode.PREDEFINED_BOOLEAN.equals(mode)) {
				// returns no fill
				return getValue(Property.FILL, false) ? Fill.ORIGIN : Fill.FALSE;
			} else if (FillingMode.PREDEFINED.equals(mode)) {
				// gets the fill value, using null as default
				IsFill fill = getValue(Property.FILL, Fill.class, null);
				// if null, returns the default one
				return fill == null ? defaultValue : fill;
			} else if (FillingMode.ABSOLUTE_DATASET_INDEX.equals(mode)) {
				// the default here is 0 because the property must be set
				// setting 0, an exception will be thrown
				return Fill.getFill(getValue(Property.FILL, AbsoluteDatasetIndexFill.DEFAULT_VALUE_AS_INT));
			} else if (FillingMode.RELATIVE_DATASET_INDEX.equals(mode)) {
				// the default here is 0 because the property must be set
				// setting 0, an exception will be thrown
				return Fill.getFill(getValue(Property.FILL, RelativeDatasetIndexFill.DEFAULT_VALUE));
			}
		}
		// returns the default
		return defaultValue;
	}
}
