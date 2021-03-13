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
package org.pepstock.charba.client.data;

import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.defaults.IsDefaultOptions;
import org.pepstock.charba.client.items.UndefinedValues;

/**
 * The stacked area chart allows a number of properties to be specified for each dataset. These are used to set display properties for a specific dataset.<br>
 * Extends the line dataset setting <code>stack</code> property.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class StackedAreaDataset extends LineDataset {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		STACK("stack");

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

	/**
	 * Creates a dataset.<br>
	 * It uses the global options has default.
	 */
	public StackedAreaDataset() {
		this(Dataset.DEFAULT_HIDDEN);
	}

	/**
	 * Creates a dataset.<br>
	 * It uses the global options has default.
	 * 
	 * @param hidden if <code>true</code>, it will be initially hidden.
	 */
	public StackedAreaDataset(boolean hidden) {
		this((IsDefaultOptions) null, hidden);
	}

	/**
	 * Creates the dataset using a default.
	 * 
	 * @param defaultValues default options
	 */
	public StackedAreaDataset(IsDefaultOptions defaultValues) {
		this(defaultValues, Dataset.DEFAULT_HIDDEN);
	}

	/**
	 * Creates the dataset using a default.
	 * 
	 * @param defaultValues default options
	 * @param hidden if <code>true</code>, it will be initially hidden.
	 */
	public StackedAreaDataset(IsDefaultOptions defaultValues, boolean hidden) {
		super(defaultValues, hidden);
	}

	/**
	 * Creates the dataset using chart type related to the dataset.
	 * 
	 * @param type chart type related to the dataset
	 * @param hidden if <code>true</code>, it will be initially hidden.
	 */
	protected StackedAreaDataset(Type type, boolean hidden) {
		this(type, null, hidden);
	}

	/**
	 * Creates the dataset using a default and chart type related to the dataset.
	 * 
	 * @param type chart type related to the dataset
	 * @param defaultValues default options
	 * @param hidden if <code>true</code>, it will be initially hidden.
	 */
	protected StackedAreaDataset(Type type, IsDefaultOptions defaultValues, boolean hidden) {
		super(type, defaultValues, hidden);
	}

	/**
	 * Sets the name of stack group.
	 * 
	 * @param stackGroup name of stack group.
	 */
	public void setStackGroup(String stackGroup) {
		setValue(Property.STACK, stackGroup);
	}

	/**
	 * Returns the name of stack group.
	 * 
	 * @return the name of stack group.
	 */
	public String getStackGroup() {
		return getValue(Property.STACK, UndefinedValues.STRING);
	}

}