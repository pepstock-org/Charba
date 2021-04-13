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
package org.pepstock.charba.client.labels;

import org.pepstock.charba.client.callbacks.AbstractDatasetContext;
import org.pepstock.charba.client.commons.ArrayDouble;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.enums.ContextType;
import org.pepstock.charba.client.items.DataItem;
import org.pepstock.charba.client.items.UndefinedValues;

/**
 * This object is wrapping the native java script object provided by {@link LabelsPlugin#ID} plugin when the callback function is called.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class LabelsContext extends AbstractDatasetContext {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		LABEL("label"),
		VALUE("value"),
		PERCENTAGE("percentage");

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

	// instance of data item to wrap the value
	private final DataItem dataItem;
	// instance of label
	private final Label labelOptions;

	/**
	 * Creates the object with native object instance to be wrapped and the label instance.
	 * 
	 * @param labelOptions label configuration object
	 * @param nativeObject native object instance to be wrapped.
	 */
	LabelsContext(Label labelOptions, NativeObject nativeObject) {
		super(nativeObject);
		// checks if label is consistent
		if (labelOptions == null) {
			// exception!
			throw new IllegalArgumentException("Label options argument is null");
		}
		// stores label
		this.labelOptions = labelOptions;
		// gets the type of value
		ObjectType type = type(Property.VALUE);
		// checks if is floating data
		if (ObjectType.NUMBER.equals(type)) {
			// get the value as double
			this.dataItem = new DataItem(getValue(Property.VALUE, UndefinedValues.DOUBLE));
		} else if (ObjectType.ARRAY.equals(type)) {
			// get the value as array
			ArrayDouble array = getArrayValue(Property.VALUE);
			// sets object
			this.dataItem = new DataItem(array);
		} else if (ObjectType.STRING.equals(type)) {
			// get the value as string
			this.dataItem = new DataItem(getValue(Property.VALUE, UndefinedValues.STRING));
		} else {
			// a data item with unknown values
			this.dataItem = new DataItem(UndefinedValues.STRING);
		}
	}

	/**
	 * Returns the label options of plugin.
	 * 
	 * @return the label options of the plugin
	 */
	public Label getLabelOptions() {
		return labelOptions;
	}	
	
	/**
	 * Returns the label for the data set.
	 * 
	 * @return the label for the data set.
	 */
	public String getLabel() {
		return getValue(Property.LABEL, UndefinedValues.STRING);
	}

	/**
	 * Returns the percentage for the data set.
	 * 
	 * @return the percentage for the data set.
	 */
	public double getPercentage() {
		return getValue(Property.PERCENTAGE, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the value for the data set by a {@link DataItem}.
	 * 
	 * @return the value for the data set by a {@link DataItem}.
	 */
	public DataItem getDataItem() {
		return dataItem;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.callbacks.ChartContext#isConsistent()
	 */
	@Override
	protected boolean isConsistent() {
		// checks if the data index and data set index are consistent
		boolean indexed = getDatasetIndex() != UndefinedValues.INTEGER && getDataIndex() != UndefinedValues.INTEGER;
		// checks that all items are there
		return indexed && ContextType.LABELS.equals(getType()) && has(Property.LABEL) && has(Property.PERCENTAGE);
	}

}