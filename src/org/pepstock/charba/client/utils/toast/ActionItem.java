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

import java.util.concurrent.atomic.AtomicInteger;

import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.dom.BaseNativeEvent;
import org.pepstock.charba.client.utils.toast.ToastItem.CommonProperty;
import org.pepstock.charba.client.utils.toast.handlers.ActionClickEventHandler;

import jsinterop.annotations.JsFunction;

/**
 * Defines user action to show on a toast in order to enable the user to act with the toast.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ActionItem extends Action {

	// ---------------------------
	// -- JAVASCRIPT FUNCTIONS ---
	// ---------------------------

	/**
	 * Java script FUNCTION callback when an event is emitted on the toast action.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyActionEventCallback {

		/**
		 * Method of function to be called when an event is emitted on the toast action.
		 * 
		 * @param itemId toast item Id affected by event
		 * @param event event fired on item
		 * @return <code>true</code> if the toaster must be close after click on action
		 */
		boolean call(int itemId, BaseNativeEvent event);
	}

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the click function
	private final CallbackProxy<ProxyActionEventCallback> clickEventCallbackProxy = JsHelper.get().newCallbackProxy();

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		// mandatory items
		CONTENT("content"),
		ON_CLICK("onClick");

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

	// counter for action id
	private static final AtomicInteger COUNTER = new AtomicInteger();
	// prefix for action id
	private static final String PREFIX_ID = "action-";

	// unique id
	private final Key id;
	// content of the action
	private final String content;
	// handler for click on toast action
	private final ActionClickEventHandler clickEventHandler;

	/**
	 * Creates the action with the content to show on the toast and the handler to invoke when the user will click on the action.
	 * 
	 * @param content content to show on the toast
	 * @param handler the handler to use when a click was performed on the toast
	 */
	public ActionItem(String content, ActionClickEventHandler handler) {
		super(null, null, Toaster.get().getDefaults().getAction(), null);
		// checks arguments
		this.content = Checker.checkAndGetIfValid(content, "Action content ");
		this.clickEventHandler = Checker.checkAndGetIfValid(handler, "Action click event handler ");
		// creates unique id
		this.id = Key.create(PREFIX_ID + COUNTER.getAndIncrement());
		// stores values
		setValue(CommonProperty.ID, this.id);
		setValue(Property.CONTENT, this.content);
		setValue(Property.ON_CLICK, clickEventCallbackProxy.getProxy());
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.clickEventCallbackProxy.setCallback((itemId, event) -> {
			// gets the toast item
			ToastItem item = Toaster.get().getCurrentOpenItem(itemId);
			// checks if item is consistent
			if (item != null) {
				// invokes handler
				return handler.onClick(item, event);
			}
			return false;
		});
	}

	/**
	 * Creates the action cloning the action reference passed as argument.
	 * 
	 * @param source source to clone
	 */
	ActionItem(ActionItem source) {
		// clones the native object
		super(null, null, Toaster.get().getDefaults().getAction(), NativeToasting.clone(source.getNativeObject()));
		// stores the properties from the source
		this.id = source.getId();
		this.content = source.getContent();
		this.clickEventHandler = source.getClickEventHandler();
	}

	/**
	 * Returns the action unique id.
	 * 
	 * @return the action unique id
	 */
	public Key getId() {
		return id;
	}

	/**
	 * Returns the content to show in the toast.
	 * 
	 * @return the content to show in the toast
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Returns the action click event handler instance.
	 * 
	 * @return the action click event handler instance
	 */
	public ActionClickEventHandler getClickEventHandler() {
		return clickEventHandler;
	}

}
