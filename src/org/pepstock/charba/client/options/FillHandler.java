/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Envelop;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.PropertyHandler;
import org.pepstock.charba.client.data.DataEnvelop;
import org.pepstock.charba.client.enums.AbsoluteDatasetIndexFill;
import org.pepstock.charba.client.enums.Fill;
import org.pepstock.charba.client.enums.FillingMode;
import org.pepstock.charba.client.enums.IsFill;
import org.pepstock.charba.client.enums.RelativeDatasetIndexFill;
import org.pepstock.charba.client.items.FillBaseline;
import org.pepstock.charba.client.items.FillColors;
import org.pepstock.charba.client.items.ItemsEnvelop;

/**
 * Manages the FILL property of options in order to use the same logic between line datasets and options/configuration.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class FillHandler extends PropertyHandler<IsFill> {

	/**
	 * Name of properties of native object.
	 */
	protected enum Property implements Key
	{
		FILL("fill"),
		// used in fill colors
		TARGET("target"),
		// internal property key to map the type of FILL property
		CHARBA_FILLING_MODE("charbaFillingMode");

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

	// property to use the fill data.
	// This is needed for fill colors when the key can be target
	private final Key property;

	/**
	 * Creates a fill handler with the native object where FILL property must be managed and the default value to use when the property does not exist.<br>
	 * This is called from <code>data</code> package.
	 * 
	 * @param parent model which contains the fill handler.
	 * @param defaultValues default value of FILL to use when the property does not exist
	 * @param envelop envelop of native object where FILL property must be managed
	 */
	protected FillHandler(AbstractNode parent, IsFill defaultValues, DataEnvelop<NativeObject> envelop) {
		this(parent, defaultValues, Envelop.checkAndGetIfValid(envelop).getContent(), Property.FILL);
	}

	/**
	 * Creates a fill handler with the native object where FILL property must be managed and the default value to use when the property does not exist.<br>
	 * This is called from <code>items</code> package.
	 * 
	 * @param parent model which contains the fill handler.
	 * @param defaultValues default value of FILL to use when the property does not exist
	 * @param envelop envelop of native object where FILL property must be managed
	 */
	protected FillHandler(AbstractNode parent, IsFill defaultValues, ItemsEnvelop<NativeObject> envelop) {
		this(parent, defaultValues, Envelop.checkAndGetIfValid(envelop).getContent(), Property.TARGET);
	}

	/**
	 * Creates a fill handler with the native object where FILL property must be managed and the default value to use when the property does not exist.
	 * 
	 * @param parent model which contains the fill handler.
	 * @param defaultValues default value of FILL to use when the property does not exist
	 * @param nativeObject native object where FILL property must be managed
	 * @param property the key to use to store the fill options
	 */
	FillHandler(AbstractNode parent, IsFill defaultValues, NativeObject nativeObject, Key property) {
		super(parent, defaultValues, nativeObject);
		// checks default value instance
		Checker.assertCheck(IsFill.isValid(getDefaultValues()), "Default fill argument is null or not consistent");
		// stores key
		this.property = property;
	}

	/**
	 * Sets how to fill the area under the line.
	 * 
	 * @param fill <code>true</code> to fill, otherwise <code>false</code>.
	 */
	protected void setFill(boolean fill) {
		setValueAndAddToParent(this.property, fill);
		// stores the filling mode
		setValue(Property.CHARBA_FILLING_MODE, FillingMode.PREDEFINED_BOOLEAN);
	}

	/**
	 * Sets how to fill the area under the line, by absolute dataset index.
	 * 
	 * @param index absolute dataset index of the chart.
	 */
	void setFill(int index) {
		setFill(Fill.getFill(index));
	}

	/**
	 * Sets how to fill the area under the line, by relative dataset index.
	 * 
	 * @param index relative dataset index of the chart.
	 */
	void setFill(String index) {
		setFill(Fill.getFill(index));
	}

	/**
	 * Sets how to fill the area under the line.
	 * 
	 * @param fill how to fill the area under the line.
	 */
	protected void setFill(IsFill fill) {
		// checks if argument is consistent
		if (IsFill.isValid(fill)) {
			// checks if is no fill
			if (Fill.FALSE.equals(fill)) {
				// sets the boolean value instead of string one
				setValueAndAddToParent(this.property, false);
				// stores the filling mode
				setValue(Property.CHARBA_FILLING_MODE, FillingMode.PREDEFINED_BOOLEAN);
			} else if (Fill.isPredefined(fill)) {
				// sets value
				setValueAndAddToParent(this.property, fill);
				// stores the filling mode
				setValue(Property.CHARBA_FILLING_MODE, fill.getMode());
			} else if (FillingMode.ABSOLUTE_DATASET_INDEX.equals(fill.getMode())) {
				// sets value
				setValueAndAddToParent(this.property, fill.getValueAsInt());
				// stores the filling mode
				setValue(Property.CHARBA_FILLING_MODE, FillingMode.ABSOLUTE_DATASET_INDEX);
			} else if (FillingMode.RELATIVE_DATASET_INDEX.equals(fill.getMode())) {
				// sets value
				setValueAndAddToParent(this.property, fill.getValue());
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
	protected IsFill getFill() {
		// checks if there is the property
		if (has(Property.CHARBA_FILLING_MODE)) {
			// gets the filling mode
			FillingMode mode = getValue(Property.CHARBA_FILLING_MODE, FillingMode.values(), FillingMode.PREDEFINED);
			// checks all possible types of filling mode
			// to return the right value
			if (FillingMode.PREDEFINED_BOOLEAN.equals(mode)) {
				// returns no fill
				return getValue(this.property, false) ? Fill.ORIGIN : Fill.FALSE;
			} else if (FillingMode.PREDEFINED.equals(mode)) {
				// gets the fill value, using null as default
				IsFill fill = getValue(this.property, Fill.values(), null);
				// if null, returns the default one
				return fill == null ? getDefaultValues() : fill;
			} else if (FillingMode.ABSOLUTE_DATASET_INDEX.equals(mode)) {
				// the default here is 0 because the property must be set
				// setting 0, an exception will be thrown
				return Fill.getFill(getValue(this.property, AbsoluteDatasetIndexFill.DEFAULT_VALUE_AS_INT));
			} else if (FillingMode.RELATIVE_DATASET_INDEX.equals(mode)) {
				// the default here is 0 because the property must be set
				// setting 0, an exception will be thrown
				return Fill.getFill(getValue(this.property, RelativeDatasetIndexFill.DEFAULT_VALUE));
			}
		}
		// returns the default
		return getDefaultValues();
	}

	/**
	 * Sets the baseline value to use for filling.
	 * 
	 * @param baseline the baseline value to use for filling
	 */
	protected void setFillBaseline(double baseline) {
		// checks if there is already a baseline
		FillBaseline storedBaseline = getFillBaseline();
		// checks if consistent
		if (storedBaseline != null) {
			storedBaseline.setValue(baseline);
		} else {
			// if here the baseline wasm't set
			// then creates and sets new one
			setFillBaseline(new FillBaseline(baseline));
		}
	}

	/**
	 * Sets the baseline value to use for filling.
	 * 
	 * @param baseline the baseline value to use for filling
	 */
	protected void setFillBaseline(FillBaseline baseline) {
		// sets value
		setValueAndAddToParent(this.property, baseline);
		// stores the filling mode
		setValue(Property.CHARBA_FILLING_MODE, FillingMode.BASELINE);
	}

	/**
	 * Returns the baseline value to use for filling.
	 * 
	 * @return the baseline value to use for filling
	 */
	protected FillBaseline getFillBaseline() {
		// checks if there is the property
		if (has(Property.CHARBA_FILLING_MODE)) {
			// gets the filling mode
			FillingMode mode = getValue(Property.CHARBA_FILLING_MODE, FillingMode.values(), FillingMode.PREDEFINED);
			// checks type of filling mode
			if (FillingMode.BASELINE.equals(mode)) {
				// returns baseline
				return new FillBaseline(new OptionsEnvelop<>(getValue(this.property)));
			}
		}
		// returns null
		return null;
	}

	/**
	 * Sets the above and below color of baseline to use for filling.
	 * 
	 * @param colors the above and below color of baseline to use for filling.
	 */
	protected void setFillColors(FillColors colors) {
		// sets value
		setValueAndAddToParent(Property.FILL, colors);
		// stores the filling mode
		setValue(Property.CHARBA_FILLING_MODE, FillingMode.COLORS);
	}

	/**
	 * Returns the above and below color of baseline to use for filling.
	 * 
	 * @return the above and below color of baseline to use for filling.
	 */
	protected FillColors getFillColors() {
		// checks if there is the property
		if (has(Property.CHARBA_FILLING_MODE)) {
			// gets the filling mode
			FillingMode mode = getValue(Property.CHARBA_FILLING_MODE, FillingMode.values(), FillingMode.PREDEFINED);
			// checks type of filling mode
			if (FillingMode.COLORS.equals(mode)) {
				// returns baseline
				return new FillColors(new OptionsEnvelop<>(getValue(Property.FILL)));
			}
		}
		// returns null because no set
		return null;
	}

}