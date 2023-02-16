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
package org.pepstock.charba.client.items;

import org.pepstock.charba.client.commons.Envelop;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.options.OptionsEnvelop;

/**
 * The object enables the axis value to define the baseline for filling at axis level.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class FillBaseline extends NativeObjectContainer {

	/**
	 * Default baseline value, <b>{@value}</b>.
	 */
	public static final double DEFAULT_VALUE = 0;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		VALUE("value");

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
	 * Creates the item using the default value to set for filling baseline.
	 */
	public FillBaseline() {
		this(DEFAULT_VALUE);
	}

	/**
	 * Creates the item using the value to set for filling baseline.
	 * 
	 * @param value axis value to use for filling baseline.
	 */
	public FillBaseline(double value) {
		setValue(value);
	}

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param envelop envelop with native java script object which contains all properties.
	 */
	public FillBaseline(OptionsEnvelop<NativeObject> envelop) {
		super(Envelop.checkAndGetIfValid(envelop).getContent());
	}

	/**
	 * Sets the baseline value to use for filling.
	 * 
	 * @param value the baseline value to use for filling
	 */
	public void setValue(double value) {
		setValue(Property.VALUE, value);
	}

	/**
	 * Returns the baseline value to use for filling.
	 * 
	 * @return the baseline value to use for filling
	 */
	public double getValue() {
		return getValue(Property.VALUE, DEFAULT_VALUE);
	}

	/**
	 * Returns the native object instance.
	 * 
	 * @return the native object instance.
	 */
	public NativeObject nativeObject() {
		return getNativeObject();
	}

}