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

import org.pepstock.charba.client.callbacks.BeginAtZeroCallback;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyBooleanCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtil;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;

/**
 * Base object to map the beginAtZero options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class BegiAtZeroCallbackHandler {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		BEGIN_AT_ZERO("beginAtZero");

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
	// callback proxy to invoke the beginAtZero function
	private final CallbackProxy<ProxyBooleanCallback> beginAtZeroCallbackProxy = JsHelper.get().newCallbackProxy();

	// ---------------------------
	// -- USER CALLBACKS ---
	// ---------------------------
	// callback for the beginAtZero option
	private BeginAtZeroCallback beginAtZeroCallback = null;
	// parent instance
	private final Axis parent;

	/**
	 * Creates the handler with the axis which the handler belongs to.
	 * 
	 * @param parent the axis instance which the handler belongs to.
	 */
	BegiAtZeroCallbackHandler(Axis parent) {
		this.parent = Checker.checkAndGetIfValid(parent, "Parent instance");
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.beginAtZeroCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValue(this.parent.createContext(context), getBeginAtZeroCallback(), this.parent.getDefaultValues().isBeginAtZero()).booleanValue());
	}

	/**
	 * Returns the callback to set if true, scale will include 0 if it is not already included.
	 * 
	 * @return the callback to set if true, scale will include 0 if it is not already included.
	 */
	BeginAtZeroCallback getBeginAtZeroCallback() {
		return beginAtZeroCallback;
	}

	/**
	 * Sets the callback to set if true, scale will include 0 if it is not already included.
	 * 
	 * @param beginAtZeroCallback the callback to set if true, scale will include 0 if it is not already included.
	 */
	void setBeginAtZero(BeginAtZeroCallback beginAtZeroCallback) {
		// sets the callback
		this.beginAtZeroCallback = beginAtZeroCallback;
		// stores and manages callback
		this.parent.setCallback(this.parent.getConfiguration(), Property.BEGIN_AT_ZERO, beginAtZeroCallback, beginAtZeroCallbackProxy);
	}

	/**
	 * Sets the callback to set if true, scale will include 0 if it is not already included.
	 * 
	 * @param beginAtZeroCallback the callback to set if true, scale will include 0 if it is not already included.
	 */
	void setBeginAtZero(NativeCallback beginAtZeroCallback) {
		// resets callback
		setBeginAtZero((BeginAtZeroCallback) null);
		// stores and manages callback
		this.parent.setCallback(this.parent.getConfiguration(), Property.BEGIN_AT_ZERO, beginAtZeroCallback);
	}

}