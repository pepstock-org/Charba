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

import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.CallbackProxy;

/**
 * Base object to map an axis tick for axes.<br>
 * Every axis type has got own data type therefore to activate a tick callback is needed a different signature.<br>
 * It is also common to want to change the tick marks to include information about the data type.<br>
 * To do this, you need to add a callback in the axis configuration. <br>
 * If the callback returns null or undefined the associated grid line will be hidden.
 * 
 * @author Andrea "Stock" Stocchero
 * @param <T> type of tick with configuration element
 * @param <C> type of callback
 */
abstract class AbstractTickHandler<T extends Tick, C> extends AxisContainer {

	// user callback instance
	private C callback = null;
	// the axis instance, owner of this tick
	private final T configuration;

	/**
	 * Builds the object storing the axis instance and options element, based on different kind of axis.
	 * 
	 * @param axis axis instance
	 * @param configuration options element, based on different kind of axis.
	 */
	AbstractTickHandler(Axis axis, T configuration) {
		super(axis);
		// stores the options element
		this.configuration = configuration;
	}

	/**
	 * Returns the options element for tick.
	 * 
	 * @return the configuration
	 */
	final T getConfiguration() {
		return configuration;
	}

	/**
	 * Returns the callback proxy to set tick callback.
	 * 
	 * @return the callback proxy to set tick callback
	 */
	abstract CallbackProxy.Proxy getProxy();

	/**
	 * Returns the user callback instance.
	 * 
	 * @return the callback
	 */
	final C getCallback() {
		return callback;
	}

	/**
	 * Sets the user callback instance.
	 * 
	 * @param callback the callback to set
	 */
	final void setCallback(C callback) {
		// sets the callback
		this.callback = callback;
		// stores and manages callback
		getAxis().setCallback(configuration.getConfiguration(), Tick.Property.CALLBACK, callback, getProxy());
	}

	/**
	 * Parses the result of a tick callback, checking if it's a string or a list of strings.
	 * 
	 * @param result the result of callback to check
	 * @param defaultValue default value to return if the result fo callback is not consistent.
	 * @return return a string or a list of strings (multiline) or <code>default</code> if result is not consistent
	 */
	final Object parseCallbackResult(Object result, String defaultValue) {
		// checks if a single string
		if (result instanceof String) {
			// returns the string
			return result;
		} else if (result instanceof List<?>) {
			// casts to list
			List<?> list = (List<?>) result;
			// checks if list is consistent
			if (!list.isEmpty()) {
				// creates the result array
				final List<String> normalizedList = new LinkedList<>();
				// scans list
				for (Object textItem : list) {
					// adds the string
					// to normalized list
					normalizedList.add(textItem.toString());
				}
				// checks if there is more than
				// returns the arrays of string for text
				return normalizedList.size() == 1 ? normalizedList.get(0) : ArrayString.fromOrNull(normalizedList);
			}
		}
		// if here, result is not consistent
		// then returns default
		return defaultValue;
	}
}