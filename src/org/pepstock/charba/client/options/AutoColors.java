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

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultOptions;

/**
 * The chart auto colors plugin to apply a default colors palette when the user doesn't define them.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class AutoColors extends AbstractModel<Plugins, IsDefaultOptions> {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		ENABLED("enabled");

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
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param options plugins options of the chart.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	AutoColors(Plugins options, Key childKey, IsDefaultOptions defaultValues, NativeObject nativeObject) {
		super(options, childKey, defaultValues, nativeObject);
	}

	/**
	 * Sets <code>true</code> if auto color plugin is enabled.
	 * 
	 * @param enabled <code>true</code> if auto color plugin is enabled.
	 */
	void setEnabled(boolean enabled) {
		setValueAndAddToParent(Property.ENABLED, enabled);
	}

	/**
	 * Returns <code>true</code> if auto color plugin is enabled.
	 * 
	 * @return <code>true</code> if auto color plugin is enabled.
	 */
	boolean isEnabled() {
		return getValue(Property.ENABLED, getDefaultValues().isAutoColors());
	}
}