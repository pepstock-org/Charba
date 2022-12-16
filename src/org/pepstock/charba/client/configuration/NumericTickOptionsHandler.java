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
package org.pepstock.charba.client.configuration;

import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.NumberFormatCallback;
import org.pepstock.charba.client.callbacks.ScaleContext;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyNativeObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtil;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultNumberFormatOptions;
import org.pepstock.charba.client.items.NumberFormatItem;

/**
 * This object is used to provide the scriptable options of {@link IsLinearTick} ticks.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
class NumericTickOptionsHandler extends AxisContainer {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		FORMAT("format");

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

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the padding function
	private final CallbackProxy<ProxyNativeObjectCallback> numberFormatCallbackProxy = JsHelper.get().newCallbackProxy();

	// instance of padding callback
	private NumberFormatCallback numberFormatCallback = null;

	/**
	 * Builds the object storing the axis which this tick belongs to.
	 * 
	 * @param axis axis which this tick belongs to.
	 */
	NumericTickOptionsHandler(Axis axis) {
		super(axis);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.numberFormatCallbackProxy.setCallback(context -> onNumberFormat(getAxis().createContext(context), getNumberFormatCallback(), getAxis().getDefaultValues().getTicks().getNumberFormat()));
	}

	/**
	 * Returns the callback to set the number formatting options.
	 * 
	 * @return the callback instance to use
	 */
	final NumberFormatCallback getNumberFormatCallback() {
		return numberFormatCallback;
	}

	/**
	 * Sets the number formatting options.
	 * 
	 * @param numberFormatCallback the callback instance to use
	 */
	final void setNumberFormat(NumberFormatCallback numberFormatCallback) {
		// sets the callback
		this.numberFormatCallback = numberFormatCallback;
		// stores and manages callback
		getAxis().setCallback(getAxis().getConfiguration().getTicks(), Property.FORMAT, numberFormatCallback, numberFormatCallbackProxy);
	}

	/**
	 * Sets the number formatting options.
	 * 
	 * @param numberFormatCallback the callback instance to use
	 */
	final void setNumberFormat(NativeCallback numberFormatCallback) {
		// resets the callback
		setNumberFormat((NumberFormatCallback) null);
		// stores and manages callback
		getAxis().setCallback(getAxis().getConfiguration().getTicks(), Property.FORMAT, numberFormatCallback);
	}

	/**
	 * Returns a number format options when the callback has been activated.
	 * 
	 * @param context native object as context
	 * @param callback callback to invoke
	 * @param defaultValue default value of the number format options
	 * @return a number format options
	 */
	private NativeObject onNumberFormat(ScaleContext context, NumberFormatCallback callback, IsDefaultNumberFormatOptions defaultValue) {
		// checks if default number format options is consistent
		Checker.checkIfValid(defaultValue, "Default number format item");
		// invokes callback
		NumberFormatItem result = ScriptableUtil.getOptionValue(context, callback, defaultValue.create());
		// returns native object
		return result.nativeObject();
	}

}