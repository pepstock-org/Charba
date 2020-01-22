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

import org.pepstock.charba.client.callbacks.CallbackFunctionContext;
import org.pepstock.charba.client.callbacks.TimeAxisBuildTicksCallback;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.CallbackProxy.Proxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.items.AxisItem;

import jsinterop.annotations.JsFunction;

/**
 * Base object to enable the {@link TimeAxisBuildTicksCallback} on an axis, {@link CartesianTimeAxis}, when the ticks are
 * building by CHART.JS.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class TimeAxisBuildTicksCallbackHandler extends AbstractAxisBuildTicksCallbackHandler<TimeAxisBuildTicksCallback> {

	// ---------------------------
	// -- JAVASCRIPT FUNCTIONS ---
	// ---------------------------
	/**
	 * Java script FUNCTION callback called to invoke a custom callback for axis.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyAxisCallback {

		/**
		 * Method of function to be called to invoke a custom callback for axis.
		 * 
		 * @param context value of <code>this</code> to the execution context of function.
		 * @param item native object of axis
		 * @param tickItems array of created ticks
		 */
		void call(CallbackFunctionContext context, NativeObject item, ArrayObject tickItems);
	}

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the after build tricks function
	private final CallbackProxy<ProxyAxisCallback> afterBuildTicksCallbackProxy = JsHelper.get().newCallbackProxy();
	// stored time ticks defintion
	private final CartesianTimeTick ticks;

	/**
	 * Builds the object storing the axis instance.
	 * 
	 * @param axis axis instance
	 */
	TimeAxisBuildTicksCallbackHandler(CartesianTimeAxis axis) {
		super(axis);
		// stores time ticks
		ticks = axis.getTicks();
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		afterBuildTicksCallbackProxy.setCallback((context, item, tickItems) -> onAfterBuildTicksCallback(item, tickItems));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.AbstractAxisBuildTicksCallbackHandler#getAfterBuildTicksCallbackProxy()
	 */
	@Override
	Proxy getAfterBuildTicksCallbackProxy() {
		return afterBuildTicksCallbackProxy.getProxy();
	}

	/**
	 * Invokes BUILD TICKS axis callback.
	 * 
	 * @param item axis item instance
	 * @param tickItems array of created ticks
	 */
	private void onAfterBuildTicksCallback(NativeObject item, ArrayObject tickItems) {
		// if user callback is consistent
		if (getCallback() != null) {
			AxisItem mItem = new AxisItem(item);
			// then it is called
			getCallback().onAfterBuildTicks(getAxis(), mItem, ticks.getTickHandler().getTimeTickItems(tickItems));
		}
	}

}
