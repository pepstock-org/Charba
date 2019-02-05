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
package org.pepstock.charba.client.ext.datalabels;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.Charts;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.NativeObjectContainer;

import jsinterop.annotations.JsFunction;

/**
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Listeners extends NativeObjectContainer {

	// ---------------------------
	// -- JAVASCRIPT FUNCTIONS ---
	// ---------------------------

	/**
	 * Java script FUNCTION callback called to manage the ENTER event of plugin.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyEnterEventCallback {

		/**
		 * Method of function to be called to manage the ENTER event of plugin.
		 * 
		 * @param contextFunction context Value of <code>this</code> to the execution context of function.
		 * @param context native object as context.
		 * @return string with rendering value.
		 */
		boolean call(Object contextFunction, Context context);
	}

	/**
	 * Java script FUNCTION callback called to manage the LEAVE event of plugin.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyLeaveEventCallback {

		/**
		 * Method of function to be called to manage the LEAVE event of plugin.
		 * 
		 * @param contextFunction context Value of <code>this</code> to the execution context of function.
		 * @param context native object as context.
		 * @return string with rendering value.
		 */
		boolean call(Object contextFunction, Context context);
	}

	/**
	 * Java script FUNCTION callback called to manage the CLICK event of plugin.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyClickEventCallback {

		/**
		 * Method of function to be called to manage the CLICK event of plugin.
		 * 
		 * @param contextFunction context Value of <code>this</code> to the execution context of function.
		 * @param context native object as context.
		 * @return string with rendering value.
		 */
		boolean call(Object contextFunction, Context context);
	}

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the formatter function
	private final CallbackProxy<ProxyEnterEventCallback> enterEventCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the background color function
	private final CallbackProxy<ProxyLeaveEventCallback> leaveEventCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border color function
	private final CallbackProxy<ProxyClickEventCallback> clickEventCallbackProxy = JsHelper.get().newCallbackProxy();

	private EnterEventHandler enterEventHandler = null;

	private LeaveEventHandler leaveEventHandler = null;

	private ClickEventHandler clickEventHandler = null;

	Listeners() {
		super();
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		enterEventCallbackProxy.setCallback(new ProxyEnterEventCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.ext.datalabels.Listeners.ProxyEnterEventCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.ext.datalabels.Context)
			 */
			@Override
			public boolean call(Object contextFunction, Context context) {
				// gets chart instance
				String id = context.getNativeChart().getCharbaId();
				AbstractChart<?, ?> chart = Charts.get(id);
				// checks if the callback is set
				if (chart != null && enterEventHandler != null) {
					// calls callback
					return enterEventHandler.onEnter(chart, context);
				}
				return true;
			}
		});
		leaveEventCallbackProxy.setCallback(new ProxyLeaveEventCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.ext.datalabels.Listeners.ProxyLeaveEventCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.ext.datalabels.Context)
			 */
			@Override
			public boolean call(Object contextFunction, Context context) {
				// gets chart instance
				String id = context.getNativeChart().getCharbaId();
				AbstractChart<?, ?> chart = Charts.get(id);
				// checks if the callback is set
				if (chart != null && leaveEventHandler != null) {
					// calls callback
					return leaveEventHandler.onLeave(chart, context);
				}
				return true;
			}
		});
		clickEventCallbackProxy.setCallback(new ProxyClickEventCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.ext.datalabels.Listeners.ProxyClickEventCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.ext.datalabels.Context)
			 */
			@Override
			public boolean call(Object contextFunction, Context context) {
				// gets chart instance
				String id = context.getNativeChart().getCharbaId();
				AbstractChart<?, ?> chart = Charts.get(id);
				// checks if the callback is set
				if (chart != null && clickEventHandler != null) {
					// calls callback
					return clickEventHandler.onClick(chart, context);
				}
				return true;
			}
		});
	}

	/**
	 * @return the clickEventHandler
	 */
	public ClickEventHandler getClickEventHandler() {
		return clickEventHandler;
	}

	/**
	 * @param clickEventHandler the clickEventHandler to set
	 */
	public void setClickEventHandler(ClickEventHandler clickEventHandler) {
		// sets the callback
		this.clickEventHandler = clickEventHandler;
		// checks if callback is consistent
		if (clickEventHandler != null) {
			// adds the callback proxy function to java script object
			setValue(Event.click, clickEventCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Event.click);
		}
	}

	/**
	 * @return the enterEventHandler
	 */
	public EnterEventHandler getEnterEventHandler() {
		return enterEventHandler;
	}

	/**
	 * @param enterEventHandler the enterEventHandler to set
	 */
	public void setEnterEventHandler(EnterEventHandler enterEventHandler) {
		// sets the callback
		this.enterEventHandler = enterEventHandler;
		// checks if callback is consistent
		if (enterEventHandler != null) {
			// adds the callback proxy function to java script object
			setValue(Event.enter, enterEventCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Event.enter);
		}
	}

	/**
	 * @return the leaveEventHandler
	 */
	public LeaveEventHandler getLeaveEventHandler() {
		return leaveEventHandler;
	}

	/**
	 * @param leaveEventHandler the leaveEventHandler to set
	 */
	public void setLeaveEventHandler(LeaveEventHandler leaveEventHandler) {
		// sets the callback
		this.leaveEventHandler = leaveEventHandler;
		// checks if callback is consistent
		if (leaveEventHandler != null) {
			// adds the callback proxy function to java script object
			setValue(Event.leave, leaveEventCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Event.leave);
		}
	}

}
