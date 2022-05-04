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

import org.pepstock.charba.client.callbacks.ScriptableUtil;
import org.pepstock.charba.client.callbacks.TickCallback;
import org.pepstock.charba.client.commons.ArrayDouble;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.CallbackProxy.Proxy;
import org.pepstock.charba.client.commons.JsHelper;

import jsinterop.annotations.JsFunction;

/**
 * Base object to map an axis tick for LINEAR axes, {@link CartesianLinearAxis}, {@link CartesianLogarithmicAxis} or {@link RadialAxis}.<br>
 * It is also common to want to change the tick marks to include information about the data type.<br>
 * To do this, you need to add a callback in the axis configuration. <br>
 * If the callback returns null or undefined the associated grid line will be hidden.
 * 
 * @author Andrea "Stock" Stocchero
 */
final class LinearTickHandler<T extends Tick> extends AbstractTickHandler<T, TickCallback> {

	// ---------------------------
	// -- JAVASCRIPT FUNCTIONS ---
	// ---------------------------

	/**
	 * Java script FUNCTION callback when tick is created.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyTickCallback {

		/**
		 * Method of function to be called when tick is created.
		 * 
		 * @param value value of the tick
		 * @param index index of tick
		 * @param values array with all values of ticks
		 * @return string or array of strings representation of tick
		 */
		Object call(double value, int index, ArrayDouble values);
	}

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the tick function
	private final CallbackProxy<ProxyTickCallback> tickCallbackProxy = JsHelper.get().newCallbackProxy();

	/**
	 * Builds the object storing the axis instance and options element, based on different kind of axis.
	 * 
	 * @param axis axis instance
	 * @param configuration options element, based on different kind of axis.
	 */
	LinearTickHandler(Axis axis, T configuration) {
		super(axis, configuration);

		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		tickCallbackProxy.setCallback((value, index, values) -> {
			// get default
			String current = String.valueOf(value);
			// checks if user callback is consistent
			if (getCallback() != null) {
				// then calls user callback
				Object result = getCallback().onCallback(getAxis(), value, index, ArrayListHelper.unmodifiableList(values));
				// parses and returns the result
				return ScriptableUtil.parseCallbackResult(result, current);
			}
			// default tick is the string representation of the tick value
			return current;
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.AbstractTickHandler#getProxy()
	 */
	@Override
	Proxy getProxy() {
		return tickCallbackProxy.getProxy();
	}

}