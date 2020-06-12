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
package org.pepstock.charba.client.callbacks;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.items.UndefinedValues;

/**
 * The option context is used to give contextual information when resolving options.<br>
 * The context object contains the following properties:<br>
 * <ul>
 * <li><b>index</b>(int): index of the associated data
 * <li><b>datasetIndex</b>(int): index of the associated dataset
 * <li><b>active</b>(boolean): if the data and dataset item is hovered/active.
 * </ul>
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class ScriptableContext extends AbstractScriptableContext {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		DATA_INDEX("dataIndex"),
		DATASET_INDEX("datasetIndex"),
		ACTIVE("active"),
		OPTIONS("options");

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
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 */
	public ScriptableContext(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the index of the data inside the dataset.
	 * 
	 * @return the index of the data inside the dataset. Default is {@link UndefinedValues#INTEGER}.
	 */
	public int getIndex() {
		return getValue(Property.DATA_INDEX, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the dataset index of the data inside the dataset.
	 * 
	 * @return the dataset index of the data inside the dataset. Default is {@link UndefinedValues#INTEGER}.
	 */
	public int getDatasetIndex() {
		return getValue(Property.DATASET_INDEX, UndefinedValues.INTEGER);
	}

	/**
	 * Returns whether the associated element is active.
	 * 
	 * @return whether the associated element is active. Default is false.
	 */
	public boolean isActive() {
		return getValue(Property.ACTIVE, false);
	}

	/**
	 * Sets the additional options.
	 * 
	 * @param options additional options instance.
	 * @param <T> type of native object container to store
	 */
	public <T extends NativeObjectContainer> void setOptions(T options) {
		// if null, removes the configuration
		if (options != null) {
			// stores configuration
			setValue(Property.OPTIONS, options);
		} else {
			// removes configuration if exists
			removeIfExists(Property.OPTIONS);
		}
	}

	/**
	 * Checks if there is any options.
	 * 
	 * @return <code>true</code> if there is an options, otherwise <code>false</code>.
	 */
	public boolean hasOptions() {
		return has(Property.OPTIONS);
	}

	/**
	 * Returns the options, if exist. It uses a factory instance to create a native object container.
	 * 
	 * @param factory factory instance to create a native object container.
	 * @param <T> type of native object container to return
	 * @return java script object used to map the options or an empty object if not exist.
	 */
	public <T extends NativeObjectContainer> T getOptions(NativeObjectContainerFactory<T> factory) {
		// checks if argument is consistent
		if (factory != null) {
			return factory.create(getValue(Property.OPTIONS));
		}
		// if here, argument is not consistent
		return null;
	}

}