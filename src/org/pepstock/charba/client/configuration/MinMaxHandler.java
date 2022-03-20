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

import java.util.Date;

import org.pepstock.charba.client.callbacks.MinMaxCallback;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.ScaleContext;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.ScaleDataType;
import org.pepstock.charba.client.utils.Window;

/**
 * Base object to map the min, max, suggestedMin and suggestedMax options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class MinMaxHandler<T> {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		MIN("min"),
		MAX("max"),
		SUGGESTED_MAX("suggestedMax"),
		SUGGESTED_MIN("suggestedMin");

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
	// callback proxy to invoke the min function
	private final CallbackProxy<ProxyObjectCallback> minCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the min function
	private final CallbackProxy<ProxyObjectCallback> maxCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the min function
	private final CallbackProxy<ProxyObjectCallback> suggestedMinCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the min function
	private final CallbackProxy<ProxyObjectCallback> suggestedMaxCallbackProxy = JsHelper.get().newCallbackProxy();

	// ---------------------------
	// -- USER CALLBACKS ---
	// ---------------------------
	// callback for the min option
	private MinMaxCallback<T> minCallback = null;
	// callback for the max option
	private MinMaxCallback<T> maxCallback = null;
	// callback for the suggestedMin option
	private MinMaxCallback<T> suggestedMinCallback = null;
	// callback for the suggestedMax option
	private MinMaxCallback<T> suggestedMaxCallback = null;
	// parent instance
	private final Axis parent;
	// data type
	private final ScaleDataType dataType;

	/**
	 * Creates the handler with the axis which the handler belongs to.
	 * 
	 * @param parent the axis instance which the handler belongs to.
	 */
	MinMaxHandler(Axis parent) {
		this.parent = Checker.checkAndGetIfValid(parent, "Parent instance");
		// checks if consistent
		// stores data type
		this.dataType = this.parent.getType().getDataType();
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.minCallbackProxy.setCallback(context -> onValue(this.parent.createContext(context), getMinCallback()));
	}

	/**
	 * Returns the callback to set the user defined minimum number for the scale, overrides minimum value from data.
	 * 
	 * @return the callback to set the user defined minimum number for the scale, overrides minimum value from data.
	 */
	MinMaxCallback<T> getMinCallback() {
		return minCallback;
	}

	/**
	 * Sets the callback to set the user defined minimum number for the scale, overrides minimum value from data.
	 * 
	 * @param minCallback the callback to set the user defined minimum number for the scale, overrides minimum value from data.
	 */
	void setMin(MinMaxCallback<T> minCallback) {
		// sets the callback
		this.minCallback = minCallback;
		// stores and manages callback
		this.parent.setCallback(this.parent.getConfiguration(), Property.MIN, minCallback, minCallbackProxy);
	}

	/**
	 * Sets the callback to set the user defined minimum number for the scale, overrides minimum value from data.
	 * 
	 * @param minCallback the callback to set the user defined minimum number for the scale, overrides minimum value from data.
	 */
	void setMin(NativeCallback minCallback) {
		// resets callback
		setMin((MinMaxCallback<T>) null);
		// stores and manages callback
		this.parent.setCallback(this.parent.getConfiguration(), Property.MIN, minCallback);
	}

	/**
	 * Sets the callback to set the user defined maximum number for the scale, overrides maximum value from data.
	 *
	 * @param maxCallback the callback to set the user defined maximum number for the scale, overrides maximum value from data.
	 */
	void setMax(MinMaxCallback<T> maxCallback) {
		// sets the callback
		this.maxCallback = maxCallback;
		// stores and manages callback
		this.parent.setCallback(this.parent.getConfiguration(), Property.MIN, maxCallback, maxCallbackProxy);
	}

	/**
	 * Sets the callback to set the user defined maximum number for the scale, overrides maximum value from data.
	 *
	 * @param maxCallback the callback to set the user defined maximum number for the scale, overrides maximum value from data.
	 */
	void setMax(NativeCallback maxCallback) {
		// resets callback
		setMax((MinMaxCallback<T>) null);
		// stores and manages callback
		this.parent.setCallback(this.parent.getConfiguration(), Property.MAX, maxCallback);
	}

	/**
	 * Returns the callback to set the user defined maximum number for the scale, overrides maximum value from data.
	 *
	 * @return the callback to set the user defined maximum number for the scale, overrides maximum value from data.
	 */
	MinMaxCallback<T> getMaxCallback() {
		return maxCallback;
	}

	/**
	 * Returns the callback to set the adjustment used when calculating the minimum data value.
	 * 
	 * @return the callback to set the adjustment used when calculating the minimum data value.
	 */
	MinMaxCallback<T> getSuggestedMinCallback() {
		return suggestedMinCallback;
	}

	/**
	 * Sets the callback to set the adjustment used when calculating the minimum data value.
	 * 
	 * @param suggestedMinCallback the callback to set the adjustment used when calculating the minimum data value.
	 */
	void setSuggestedMin(MinMaxCallback<T> suggestedMinCallback) {
		// sets the callback
		this.suggestedMinCallback = suggestedMinCallback;
		// stores and manages callback
		this.parent.setCallback(this.parent.getConfiguration(), Property.SUGGESTED_MIN, suggestedMinCallback, suggestedMinCallbackProxy);
	}

	/**
	 * Sets the callback to set the adjustment used when calculating the minimum data value.
	 * 
	 * @param suggestedMinCallback the callback to set the adjustment used when calculating the minimum data value.
	 */
	void setSuggestedMin(NativeCallback suggestedMinCallback) {
		// resets callback
		setSuggestedMin((MinMaxCallback<T>) null);
		// stores and manages callback
		this.parent.setCallback(this.parent.getConfiguration(), Property.SUGGESTED_MIN, suggestedMinCallback);
	}

	/**
	 * Returns the callback to set the adjustment used when calculating the maximum data value.
	 * 
	 * @return the callback to set the adjustment used when calculating the maximum data value.
	 */
	MinMaxCallback<T> getSuggestedMaxCallback() {
		return suggestedMaxCallback;
	}

	/**
	 * Sets the callback to set the adjustment used when calculating the maximum data value.
	 * 
	 * @param suggestedMaxCallback the callback to set the adjustment used when calculating the maximum data value.
	 */
	void setSuggestedMax(MinMaxCallback<T> suggestedMaxCallback) {
		// sets the callback
		this.suggestedMaxCallback = suggestedMaxCallback;
		// stores and manages callback
		this.parent.setCallback(this.parent.getConfiguration(), Property.SUGGESTED_MAX, suggestedMaxCallback, suggestedMaxCallbackProxy);
	}

	/**
	 * Sets the callback to set the adjustment used when calculating the maximum data value.
	 * 
	 * @param suggestedMaxCallback the callback to set the adjustment used when calculating the maximum data value.
	 */
	void setSuggestedMax(NativeCallback suggestedMaxCallback) {
		// resets callback
		setSuggestedMax((MinMaxCallback<T>) null);
		// stores and manages callback
		this.parent.setCallback(this.parent.getConfiguration(), Property.SUGGESTED_MAX, suggestedMaxCallback);
	}

	/**
	 * Returns an object as double, string or date (as time) when the callback has been activated.
	 * 
	 * @param context scale context instance.
	 * @param callback min max callback instance
	 * @return an object as double, string or date
	 */
	private Object onValue(ScaleContext context, MinMaxCallback<T> callback) {
		// gets value
		Object result = ScriptableUtils.getOptionValue(context, callback);
		// checks if consistent
		if (result instanceof Number && ScaleDataType.NUMBER.equals(dataType)) {
			// casts to number
			Number number = (Number) result;
			// returns the number
			return number.doubleValue();
		} else if (result instanceof String && ScaleDataType.STRING.equals(dataType)) {
			// returns the string
			return result;
		} else if (result instanceof Date && ScaleDataType.DATE.equals(dataType)) {
			// casts to date
			Date date = (Date) result;
			// returns the number
			return (double) date.getTime();
		}
		// default result is undefined
		return Window.undefined();
	}

}