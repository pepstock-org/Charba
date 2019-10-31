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

import org.pepstock.charba.client.callbacks.BorderAlignCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.defaults.IsDefaultOptions;
import org.pepstock.charba.client.enums.BorderAlign;

/**
 * Utility to manage the border alignment on chart datasets.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class BorderAligner extends NativeObjectContainer {

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the border align function
	private final CallbackProxy<ScriptableFunctions.ProxyStringCallback> borderAlignCallbackProxy = JsHelper.get().newCallbackProxy();

	// border align callback instance
	private BorderAlignCallback borderAlignCallback = null;
	// default values
	private final IsDefaultOptions defaultValues;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		BORDER_ALIGN("borderAlign");

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
	 * Creates the utility using dataset native object.
	 * 
	 * @param nativeObject native object to update with options
	 * @param defaultValues default options
	 */
	BorderAligner(NativeObject nativeObject, IsDefaultOptions defaultValues) {
		super(nativeObject);
		this.defaultValues = defaultValues;
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// gets value and calls the callback
		borderAlignCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValueAsString(context, borderAlignCallback, defaultValues.getElements().getArc().getBorderAlign()).value());
	}

	/**
	 * Sets the property to set the border alignment on chart datasets.
	 * 
	 * @param align the property to set the border alignment on chart datasets
	 */
	public void setBorderAlign(BorderAlign align) {
		setValue(Property.BORDER_ALIGN, align);
	}

	/**
	 * Returns the property to set the border alignment on chart datasets.
	 * 
	 * @return the property to set the border alignment on chart datasets.
	 */
	public BorderAlign getBorderAlign() {
		if (ObjectType.FUNCTION.equals(type(Property.BORDER_ALIGN))) {
			// checks if a callback has been set
			// returns defaults
			return defaultValues.getElements().getArc().getBorderAlign();
		}
		// otherwise returns the enum value as string
		return getValue(Property.BORDER_ALIGN, BorderAlign.class, defaultValues.getElements().getArc().getBorderAlign());
	}

	/**
	 * Returns the border align callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border align callback, if set, otherwise <code>null</code>.
	 */
	public BorderAlignCallback getBorderAlignCallback() {
		return borderAlignCallback;
	}

	/**
	 * Sets the border align callback.
	 * 
	 * @param borderAlignCallback the border align callback to set
	 */
	public void setBorderAlign(BorderAlignCallback borderAlignCallback) {
		// sets the callback
		this.borderAlignCallback = borderAlignCallback;
		// checks if callback is consistent
		if (borderAlignCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.BORDER_ALIGN, borderAlignCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.BORDER_ALIGN);
		}
	}
}