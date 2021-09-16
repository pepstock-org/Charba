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
package org.pepstock.charba.client.utils.toast;

import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.dom.BaseNativeEvent;
import org.pepstock.charba.client.utils.toast.events.ClickEventHandler;
import org.pepstock.charba.client.utils.toast.events.CloseEventHandler;
import org.pepstock.charba.client.utils.toast.events.OpenEventHandler;

import jsinterop.annotations.JsFunction;

/**
 * Entity to configure the toast to show.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ToastOptions extends AbstractToastOptions {

	// ---------------------------
	// -- JAVASCRIPT FUNCTIONS ---
	// ---------------------------

	/**
	 * Java script FUNCTION callback when an event is emitted on the toast item.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyEventCallback {

		/**
		 * Method of function to be called when an event is emitted on the toast item.
		 * 
		 * @param item toast item affected by event
		 * @param event event fired on item
		 */
		void call(NativeObject item, BaseNativeEvent event);
	}

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the click function
	private final CallbackProxy<ProxyEventCallback> clickEventCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the open function
	private final CallbackProxy<ProxyEventCallback> openEventCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the close function
	private final CallbackProxy<ProxyEventCallback> closeEventCallbackProxy = JsHelper.get().newCallbackProxy();

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		ON_CLICK("onClick"),
		ON_OPEN("onOpen"),
		ON_CLOSE("onClose");

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

	// handler for click on toast item
	private ClickEventHandler clickEventHandler = null;
	// handler for opening a toast item
	private OpenEventHandler openEventHandler = null;
	// handler for closing a toast item
	private CloseEventHandler closeEventHandler = null;

	/**
	 * Creates the object with an empty configuration instance.
	 */
	public ToastOptions() {
		this(null, Toaster.get().getDefaults());
	}

	/**
	 * Creates the configuration with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 * @param defaultValues defaults instance
	 */
	ToastOptions(NativeObject nativeObject, IsDefaultToastOptions defaultValues) {
		super(nativeObject, defaultValues);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.clickEventCallbackProxy.setCallback((item, event) -> {
			// gets handler
			ClickEventHandler handler = getClickEventHandler();
			// checks if handler is consistent
			if (handler != null) {
				// invokes handler
				handler.onClick(new ToastItem(item), event);
			}
		});
		// sets function to proxy callback in order to invoke the java interface
		this.openEventCallbackProxy.setCallback((item, event) -> {
			// gets handler
			OpenEventHandler handler = getOpenEventHandler();
			// checks if handler is consistent
			if (handler != null) {
				// invokes handler
				handler.onOpen(new ToastItem(item), event);
			}
		});
		// sets function to proxy callback in order to invoke the java interface
		this.closeEventCallbackProxy.setCallback((item, event) -> {
			// gets handler
			CloseEventHandler handler = getCloseEventHandler();
			// checks if handler is consistent
			if (handler != null) {
				// invokes handler
				handler.onClose(new ToastItem(item), event);
			}
		});
	}

	/**
	 * Returns the CLICK event hander, if set, otherwise <code>null</code>.
	 * 
	 * @return the CLICK event hander, if set, otherwise <code>null</code>.
	 */
	public ClickEventHandler getClickEventHandler() {
		return clickEventHandler;
	}

	/**
	 * Sets the CLICK event hander.
	 * 
	 * @param clickEventHandler the CLICK event hander.
	 */
	public void setClickEventHandler(ClickEventHandler clickEventHandler) {
		// sets the handler
		this.clickEventHandler = clickEventHandler;
		// checks if handler is consistent
		if (clickEventHandler != null) {
			// adds the callback proxy function to java script object
			setValue(Property.ON_CLICK, clickEventCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.ON_CLICK);
		}
	}

	/**
	 * Returns the OPEN event hander, if set, otherwise <code>null</code>.
	 * 
	 * @return the OPEN event hander, if set, otherwise <code>null</code>.
	 */
	public OpenEventHandler getOpenEventHandler() {
		return openEventHandler;
	}

	/**
	 * Sets the OPEN event hander.
	 * 
	 * @param openEventHandler the OPEN event hander.
	 */
	public void setOpenEventHandler(OpenEventHandler openEventHandler) {
		// sets the handler
		this.openEventHandler = openEventHandler;
		// checks if handler is consistent
		if (openEventHandler != null) {
			// adds the callback proxy function to java script object
			setValue(Property.ON_OPEN, openEventCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.ON_OPEN);
		}
	}

	/**
	 * Returns the CLOSE event hander, if set, otherwise <code>null</code>.
	 * 
	 * @return the CLOSE event hander, if set, otherwise <code>null</code>.
	 */
	public CloseEventHandler getCloseEventHandler() {
		return closeEventHandler;
	}

	/**
	 * Sets the CLOSE event hander.
	 * 
	 * @param closeEventHandler the CLOSE event hander.
	 */
	public void setCloseEventHandler(CloseEventHandler closeEventHandler) {
		// sets the handler
		this.closeEventHandler = closeEventHandler;
		// checks if handler is consistent
		if (closeEventHandler != null) {
			// adds the callback proxy function to java script object
			setValue(Property.ON_CLOSE, closeEventCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.ON_CLOSE);
		}
	}
}