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
package org.pepstock.charba.client.jsinterop.options;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.jsinterop.callbacks.TickCallback;
import org.pepstock.charba.client.jsinterop.callbacks.handlers.TickHandler;
import org.pepstock.charba.client.jsinterop.commons.ArrayDouble;
import org.pepstock.charba.client.jsinterop.commons.CallbackProxy;
import org.pepstock.charba.client.jsinterop.commons.JsHelper;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultFontItem;

import jsinterop.annotations.JsFunction;

/**
 * Configures the default chart title which defines text to draw at the top of the chart.<br>
 * "weight"property is not present.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class BaseTick<P extends BaseModel<?,?,?>, D extends IsDefaultFontItem, O extends NativeTickItem> extends FontItem<P, D, O> {

	@JsFunction
	interface ProxyTickCallback {
		String call(Object context, double value, int index, ArrayDouble values);
	}
	
	private final CallbackProxy<ProxyTickCallback> tickCallbackProxy = JsHelper.newCallbackProxy();
	
	private TickCallback callback = null;
	
	private TickHandler callbackHandler = null;;
	
	/**
	 * Name of fields of JavaScript object.
	 */
	enum Property implements Key
	{
		callback
	}

	
	BaseTick(P parent, D defaultValues, O delegated) {
		super(parent, defaultValues, delegated);
		tickCallbackProxy.setCallback(new ProxyTickCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.options.BaseTick.ProxyTickCallback#call(java.lang.Object, double, int, org.pepstock.charba.client.jsinterop.commons.ArrayDouble)
			 */
			@Override
			public String call(Object context, double value, int index, ArrayDouble values) {
				if (callbackHandler != null) {
					return callbackHandler.onCallback(value, index, values);
				}
				return String.valueOf(value);
			}
			
		});
	}

	/**
	 * @return the callback
	 */
	public TickCallback getCallback() {
		return callback;
	}


	/**
	 * @param callback the callback to set
	 */
	public void setCallback(TickCallback callback) {
		this.callback = callback;
	}


	/**
	 * @return the callbackHandler
	 */
	TickHandler getCallbackHandler() {
		return callbackHandler;
	}


	/**
	 * @param callbackHandler the callbackHandler to set
	 */
	void setCallbackHandler(TickHandler callbackHandler) {
		if (callbackHandler != null) {
			getDelegated().setCallback(tickCallbackProxy.getProxy());
			// checks if the node is already added to parent
			checkAndAddToParent();
		} else {
			remove(Property.callback);
		}
		this.callbackHandler = callbackHandler;
	}
	
}