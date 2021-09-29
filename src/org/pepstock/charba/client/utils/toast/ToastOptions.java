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

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.dom.BaseNativeEvent;
import org.pepstock.charba.client.utils.toast.handlers.ClickEventHandler;
import org.pepstock.charba.client.utils.toast.handlers.CloseHandler;
import org.pepstock.charba.client.utils.toast.handlers.OpenHandler;

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

	/**
	 * Java script FUNCTION callback when an handler must engage for notification.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyHandlerCallback {

		/**
		 * Method of function to be called when an handler must engage for notification.
		 * 
		 * @param item toast item affected by event
		 */
		void call(NativeObject item);
	}

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the click function
	private final CallbackProxy<ProxyEventCallback> clickEventCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the open function
	private final CallbackProxy<ProxyHandlerCallback> openCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the close function
	private final CallbackProxy<ProxyHandlerCallback> closeCallbackProxy = JsHelper.get().newCallbackProxy();

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		ACTIONS("actions"),
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

	// list of actions
	private final List<ActionItem> storedActions = new LinkedList<>();
	// handler for click on toast item
	private ClickEventHandler clickEventHandler = null;
	// handler for opening a toast item
	private OpenHandler openHandler = null;
	// handler for closing a toast item
	private CloseHandler closeHandler = null;

	/**
	 * Creates the object with an empty configuration instance.
	 */
	public ToastOptions() {
		this(null, Toaster.get().getDefaults());
	}

	/**
	 * Creates the object cloning the passed argument.
	 * 
	 * @param source source object to be cloned
	 */
	ToastOptions(ToastOptions source) {
		this(NativeToasting.clone(source.nativeObject()), source.getDefaultValues());
		setClickEventHandler(source.getClickEventHandler());
		setOpenHandler(source.getOpenHandler());
		setCloseHandler(source.getCloseHandler());
		setActions(source.getActions());
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
				handler.onClick(new ToastItem(item, this), event);
			}
		});
		// sets function to proxy callback in order to invoke the java interface
		this.openCallbackProxy.setCallback(item -> {
			// gets handler
			OpenHandler handler = getOpenHandler();
			// checks if handler is consistent
			if (handler != null) {
				// invokes handler
				handler.onOpen(new ToastItem(item, this));
			}
		});
		// sets function to proxy callback in order to invoke the java interface
		this.closeCallbackProxy.setCallback(item -> {
			// gets handler
			CloseHandler handler = getCloseHandler();
			// checks if handler is consistent
			if (handler != null) {
				// invokes handler
				handler.onClose(new ToastItem(item, this));
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
	 * Returns the OPEN hander, if set, otherwise <code>null</code>.
	 * 
	 * @return the OPEN hander, if set, otherwise <code>null</code>.
	 */
	public OpenHandler getOpenHandler() {
		return openHandler;
	}

	/**
	 * Sets the OPEN hander.
	 * 
	 * @param openHandler the OPEN hander.
	 */
	public void setOpenHandler(OpenHandler openHandler) {
		// sets the handler
		this.openHandler = openHandler;
		// checks if handler is consistent
		if (openHandler != null) {
			// adds the callback proxy function to java script object
			setValue(Property.ON_OPEN, openCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.ON_OPEN);
		}
	}

	/**
	 * Returns the CLOSE hander, if set, otherwise <code>null</code>.
	 * 
	 * @return the CLOSE hander, if set, otherwise <code>null</code>.
	 */
	public CloseHandler getCloseHandler() {
		return closeHandler;
	}

	/**
	 * Sets the CLOSE hander.
	 * 
	 * @param closeHandler the CLOSE hander.
	 */
	public void setCloseHandler(CloseHandler closeHandler) {
		// sets the handler
		this.closeHandler = closeHandler;
		// checks if handler is consistent
		if (closeHandler != null) {
			// adds the callback proxy function to java script object
			setValue(Property.ON_CLOSE, closeCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.ON_CLOSE);
		}
	}

	/**
	 * FIXME
	 * 
	 * @param actions
	 */
	public void setActions(ActionItem... actions) {
		// clears stored actions
		storedActions.clear();
		// checks consistent of actions
		if (actions != null && actions.length > 0) {
			ArrayObject array = ArrayObject.fromOrEmpty(actions);
			// stores array
			setArrayValue(Property.ACTIONS, array);
			// stores in the list
			storedActions.addAll(Arrays.asList(actions));
		} else {
			// if here, the actions passed as argument
			// are not consistent
			// then removes from native object
			remove(Property.ACTIONS);
		}
	}

	/**
	 * FIXME
	 * 
	 * @param actions
	 */
	public void setActions(List<ActionItem> actions) {
		// sets array to store as empty
		ActionItem[] array = new ActionItem[0];
		// checks consistent of actions
		if (actions != null && !actions.isEmpty()) {
			// stores to array
			array = actions.toArray(new ActionItem[0]);
		}
		//
		// then invokes with empty array
		setActions(array);
	}

	/**
	 * FIXME
	 * 
	 * @return
	 */
	public List<ActionItem> getActions() {
		return storedActions;
	}
}