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
package org.pepstock.charba.client.datalabels;

import org.pepstock.charba.client.callbacks.AbstractDatasetContext;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.enums.ContextType;
import org.pepstock.charba.client.items.UndefinedValues;

/**
 * The option context is used to give contextual information when resolving options for {@link DataLabelsPlugin}.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DataLabelsContext extends AbstractDatasetContext {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		TYPE("type"),
		OPTIONS("options");

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

	// instance of label
	private final LabelItem label;

	/**
	 * Creates the object with native object instance to be wrapped and the label instance.
	 * 
	 * @param label label configuration object
	 * @param nativeObject native object instance to be wrapped.
	 */
	DataLabelsContext(LabelItem label, NativeObject nativeObject) {
		super(nativeObject);
		// checks if label is consistent
		if (label == null) {
			// exception!
			throw new IllegalArgumentException("Label argument is null");
		}
		// stores label
		this.label = label;
		// as you can see from following rejected PR
		// https://github.com/chartjs/chartjs-plugin-datalabels/pull/229
		// data labels does not provide the context type
		// to normalize the structure of the context
		// the type is added here
		if (!has(Property.TYPE)) {
			// overrides and sets the data labels type
			setValue(Property.TYPE, ContextType.DATALABELS);
		}
	}

	/**
	 * Returns the label options of plugin.
	 * 
	 * @return the label options of the plugin
	 */
	public LabelItem getLabel() {
		return label;
	}
	

	/**
	 * Sets the additional options.
	 * 
	 * @param options additional options instance.
	 * @param <T> type of public object container to store
	 */
	public final <T extends NativeObjectContainer> void setOptions(T options) {
		setValue(Property.OPTIONS, options);
	}

	/**
	 * Checks if there is any options.
	 * 
	 * @return <code>true</code> if there is an options, otherwise <code>false</code>.
	 */
	public final boolean hasOptions() {
		return has(Property.OPTIONS);
	}

	/**
	 * Returns the options, if exist. It uses a factory instance to create a public object container.
	 * 
	 * @param factory factory instance to create a public object container.
	 * @param <T> type of public object container to return
	 * @return java script object used to map the options or an empty object if not exist.
	 */
	public final <T extends NativeObjectContainer> T getOptions(NativeObjectContainerFactory<T> factory) {
		// checks if factory is consistent
		if (factory != null) {
			// checks if there is a options
			if (hasOptions()) {
				// creates and returns the object
				return factory.create(getValue(Property.OPTIONS));
			}
			// if here, returns an empty object
			return factory.create();
		}
		// if here, argument is not consistent
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.callbacks.ChartContext#isConsistent()
	 */
	@Override
	protected boolean isConsistent() {
		// checks if the data index and data set index are consistent
		return ContextType.DATALABELS.equals(getType()) && getDatasetIndex() != UndefinedValues.INTEGER && getDataIndex() != UndefinedValues.INTEGER;
	}

}
