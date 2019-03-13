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

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.Charts;
import org.pepstock.charba.client.callbacks.BorderAlignCallback;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.defaults.IsDefaultOptions;
import org.pepstock.charba.client.enums.BorderAlign;

import jsinterop.annotations.JsFunction;

/**
 * The polar area chart allows a number of properties to be specified for each dataset. These are used to set display properties
 * for a specific dataset.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class PolarAreaDataset extends HovingDataset {

	// ---------------------------
	// -- JAVASCRIPT FUNCTIONS ---
	// ---------------------------

	/**
	 * Java script FUNCTION callback called to provide the border align.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyBorderAlignCallback {

		/**
		 * Method of function to be called to provide the border align.
		 * 
		 * @param contextFunction context Value of <code>this</code> to the execution context of function.
		 * @param context native object as context.
		 * @return border align property value.
		 */
		Object call(Object contextFunction, Context context);
	}

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the border align function
	private final CallbackProxy<ProxyBorderAlignCallback> borderAlignCallbackProxy = JsHelper.get().newCallbackProxy();

	// border align callback instance
	private BorderAlignCallback borderAlignCallback = null;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		borderAlign
	}

	/**
	 * Creates a dataset.<br>
	 * It uses the global options has default.
	 */
	public PolarAreaDataset() {
		this(null);
	}

	/**
	 * Creates the dataset using a default.
	 * 
	 * @param defaultValues default options
	 */
	public PolarAreaDataset(IsDefaultOptions defaultValues) {
		super(defaultValues);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		borderAlignCallbackProxy.setCallback(new ProxyBorderAlignCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.data.BarDataset.ProxyBorderAlignCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.data.Context)
			 */
			@Override
			public Object call(Object contextFunction, Context context) {
				// gets chart instance
				String id = context.getNativeChart().getCharbaId();
				AbstractChart<?, ?> chart = Charts.get(id);
				// checks if the callback is set
				if (chart != null && borderAlignCallback != null) {
					// calls callback
					BorderAlign result = borderAlignCallback.align(chart, context);
					// checks result
					if (result != null) {
						// returns the string value
						return result.name();
					}
				}
				// returns the string value
				return getDefaultValues().getElements().getArc().getBorderAlign().name();
			}
		});
	}

	/**
	 * Sets the property to set the border alignment on chart datasets.
	 * 
	 * @param align the property to set the border alignment on chart datasets
	 */
	public void setBorderAlign(BorderAlign align) {
		setValue(Property.borderAlign, align);
	}

	/**
	 * Returns the property to set the border alignment on chart datasets.
	 * 
	 * @return the property to set the border alignment on chart datasets.
	 */
	public BorderAlign getBorderAlign() {
		if (ObjectType.Function.equals(type(Property.borderAlign))) {
			// checks if a callback has been set
			// returns defaults
			return getDefaultValues().getElements().getArc().getBorderAlign();
		}
		// otherwise returns the enum value as string
		return getValue(Property.borderAlign, BorderAlign.class, getDefaultValues().getElements().getArc().getBorderAlign());
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
			setValue(Property.borderAlign, borderAlignCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.borderAlign);
		}
	}
}