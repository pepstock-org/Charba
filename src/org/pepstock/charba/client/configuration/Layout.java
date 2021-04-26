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
package org.pepstock.charba.client.configuration;

import org.pepstock.charba.client.callbacks.ChartContext;
import org.pepstock.charba.client.callbacks.PaddingCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyNativeObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.items.PaddingItem;

/**
 * The layout configuration is needed to set the padding.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class Layout extends ConfigurationOptionsContainer {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		PADDING("padding");

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
	private final CallbackProxy<ProxyNativeObjectCallback> paddingCallbackProxy = JsHelper.get().newCallbackProxy();

	// instance of easing callback
	private PaddingCallback<ChartContext> paddingCallback = null;

	// instance of padding
	private final Padding padding;

	/**
	 * Builds the object storing the root options element.
	 * 
	 * @param options root options element.
	 */
	Layout(ConfigurationOptions options) {
		super(options);
		// sets the padding object
		this.padding = new Padding(() -> getConfiguration().getLayout().getPadding());
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.paddingCallbackProxy.setCallback((contextFunction, context) -> onPadding(new ChartContext(new ConfigurationEnvelop<>(context))));
	}

	/**
	 * Returns the padding element.
	 * 
	 * @return the padding
	 */
	public Padding getPadding() {
		return padding;
	}

	/**
	 * Returns the padding callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the padding callback, if set, otherwise <code>null</code>.
	 */
	public PaddingCallback<ChartContext> getPaddingCallback() {
		return paddingCallback;

	}

	/**
	 * Sets the padding callback.
	 * 
	 * @param paddingCallback the padding callback to set
	 */
	public void setPadding(PaddingCallback<ChartContext> paddingCallback) {
		// sets the callback
		this.paddingCallback = paddingCallback;
		// checks if callback is consistent
		if (paddingCallback != null) {
			// adds the callback proxy function to java script object
			getOptions().getConfiguration().setCallback(getOptions().getConfiguration().getLayout(), Property.PADDING, new ConfigurationEnvelop<>(paddingCallbackProxy.getProxy()));
		} else {
			// otherwise sets null which removes the properties from java script object
			getOptions().getConfiguration().setCallback(getOptions().getConfiguration().getLayout(), Property.PADDING, ConfigurationOptions.RESET_CALLBACK_ENVELOP);
		}
	}

	/**
	 * Returns a native object as padding when the callback has been activated.
	 * 
	 * @param context native object as context
	 * @return a native object as padding
	 */
	private NativeObject onPadding(ChartContext context) {
		// gets value
		PaddingItem result = ScriptableUtils.getOptionValue(context, getPaddingCallback());
		// checks if result is consistent
		if (result != null) {
			// returns result
			return result.nativeObject();
		}
		// default result
		return getPadding().create(getConfiguration().getLayout().getPadding()).nativeObject();
	}

}