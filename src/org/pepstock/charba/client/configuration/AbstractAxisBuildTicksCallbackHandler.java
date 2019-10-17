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

import org.pepstock.charba.client.callbacks.AbstractBuildTicksCallback;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.configuration.Axis.ProxyAxisCallback;
import org.pepstock.charba.client.items.AxisItem;

/**
 * Base object to map an axis build tick callback for axes.<br>
 * Every axis type has got own data type therefore to activate a tick callback is needed a different signature.<br>
 * 
 * @author Andrea "Stock" Stocchero
 * @param <C> type of callback 
 */
abstract class AbstractAxisBuildTicksCallbackHandler<C extends AbstractBuildTicksCallback> extends AxisContainer {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		BEFORE_BUILD_TICKS("beforeBuildTicks"),
		AFTER_BUILD_TICKS("afterBuildTicks");

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

	
	// callback proxy to invoke the before build tricks function
	private final CallbackProxy<ProxyAxisCallback> beforeBuildTicksCallbackProxy = JsHelper.get().newCallbackProxy();

	// user callback instance
	private C callback = null;

	/**
	 * Builds the object storing the axis instance.
	 * 
	 * @param axis axis instance
	 */
	AbstractAxisBuildTicksCallbackHandler(Axis axis) {
		super(axis);
		beforeBuildTicksCallbackProxy.setCallback((context, item) -> onBeforeBuildTicksCallback(item));
	}

	/**
	 * Returns the callback proxy to set tick callback.
	 * 
	 * @return the callback proxy to set tick callback
	 */
	abstract CallbackProxy.Proxy getAfterBuildTicksCallbackProxy();

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
			getAxis().getConfiguration().setCallback(Property.BEFORE_BUILD_TICKS, beforeBuildTicksCallbackProxy.getProxy());
			getAxis().getConfiguration().setCallback(Property.AFTER_BUILD_TICKS, getAfterBuildTicksCallbackProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			getAxis().getConfiguration().setCallback(Property.BEFORE_BUILD_TICKS, null);
			getAxis().getConfiguration().setCallback(Property.AFTER_BUILD_TICKS, null);
		}
	}
	

	/**
	 * Invokes BUILD TICKS axis callback.
	 * 
	 * @param item axis item instance
	 */
	private void onBeforeBuildTicksCallback(NativeObject item) {
		// if user callback is consistent
		if (callback != null) {
			// then it is called
			callback.onBeforeBuildTicks(getAxis(), new AxisItem(item));
		}
	}
}