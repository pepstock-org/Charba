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
		// checks if callback is consistent
		if (callback != null) {
			// adds the callback proxy function to java script object
			getAxis().getConfiguration().setCallback(configuration.getConfiguration(), BaseTick.Property.CALLBACK, getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			getAxis().getConfiguration().setCallback(configuration.getConfiguration(), BaseTick.Property.CALLBACK, null);
		}
	}
}