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

import org.pepstock.charba.client.callbacks.DatasetContext;
import org.pepstock.charba.client.callbacks.FillCallback;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtil;
import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.enums.IsFill;
import org.pepstock.charba.client.options.FillHandler;

/**
 * Manages the FILL property of options for lining datasets, implementing the FILL callback for scriptable options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class LiningDatasetFillHandler extends FillHandler {

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the fill function
	private final CallbackProxy<ProxyObjectCallback> fillCallbackProxy = JsHelper.get().newCallbackProxy();

	// fill callback instance
	private FillCallback fillCallback = null;

	/**
	 * Creates a fill handler with the native object where FILL property must be managed and the default value to use when the property does not exist.
	 * 
	 * @param parent model which contains the fill handler.
	 * @param defaultValues default value of FILL to use when the property does not exist
	 * @param nativeObject native object where FILL property must be managed
	 */
	LiningDatasetFillHandler(AbstractNode parent, IsFill defaultValues, NativeObject nativeObject) {
		super(parent, defaultValues, new DataEnvelop<>(nativeObject));
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.fillCallbackProxy.setCallback(context -> onFill(new DatasetContext(context)));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.FillHandler#setFill(boolean)
	 */
	@Override
	public void setFill(boolean fill) {
		// reset callback
		setFill((FillCallback) null);
		// call set value
		super.setFill(fill);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.FillHandler#setFill(org.pepstock.charba.client.enums.IsFill)
	 */
	@Override
	public void setFill(IsFill fill) {
		// reset callback
		setFill((FillCallback) null);
		// call set value
		super.setFill(fill);
	}

	/**
	 * Returns the fill callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the fill callback, if set, otherwise <code>null</code>.
	 */
	FillCallback getFillCallback() {
		return fillCallback;
	}

	/**
	 * Sets the fill callback.
	 * 
	 * @param fillCallback the fill callback.
	 */
	void setFill(FillCallback fillCallback) {
		// sets the callback
		this.fillCallback = fillCallback;
		// checks if callback is consistent
		if (fillCallback != null) {
			// adds the callback proxy function to java script object
			setValue(FillHandler.Property.FILL, fillCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(FillHandler.Property.FILL);
		}
		// remove if exist flag
		remove(FillHandler.Property.CHARBA_FILLING_MODE);
	}

	/**
	 * Sets the fill callback.
	 * 
	 * @param fillCallback the fill callback.
	 */
	void setFill(NativeCallback fillCallback) {
		// resets callback
		setFill((FillCallback) null);
		// stores value
		setValue(FillHandler.Property.FILL, fillCallback);
	}

	/**
	 * Returns a object which can be a boolean, integer, string or {@link IsFill} when the callback has been activated.
	 * 
	 * @param context native object as context.
	 * @return a object property value
	 */
	private Object onFill(DatasetContext context) {
		// gets value
		Object result = ScriptableUtil.getOptionValue(context, getFillCallback());
		// checks and transforms result
		Object transformed = IsFill.transform(result);
		// checks if consistent
		if (transformed != null) {
			// returns the result
			return transformed;
		}
		// if here, result is null
		// then returns default
		return IsFill.transform(getDefaultValues());
	}

}