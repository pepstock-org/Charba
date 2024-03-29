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
package org.pepstock.charba.client.data;

import org.pepstock.charba.client.ChartType;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;

/**
 * Utility to manage the stack option on bar/horizontal chart datasets.
 * 
 * @author Andrea "Stock" Stocchero
 */

final class BarStackHandler extends NativeObjectContainer {

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
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
	 * Creates the utility using data set native object.
	 * 
	 * @param nativeObject native object to update with options
	 */
	BarStackHandler(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Sets the name of stack group.
	 * 
	 * @param stackGroup name of stack group.
	 */
	void setStackGroup(String stackGroup) {
		setValue(Property.STACK, stackGroup);
	}

	/**
	 * Returns the name of stack group.
	 * 
	 * @return the name of stack group.
	 */
	String getStackGroup() {
		return getValue(Property.STACK, ChartType.BAR.value());
	}
}