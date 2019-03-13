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
package org.pepstock.charba.client.datalabels;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.Charts;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.datalabels.enums.Event;
import org.pepstock.charba.client.datalabels.events.ClickEventHandler;
import org.pepstock.charba.client.datalabels.events.EnterEventHandler;
import org.pepstock.charba.client.datalabels.events.LeaveEventHandler;

import jsinterop.annotations.JsFunction;

/**
 * This is the LISTENER options of DATALABELS plugin allows to register callback(s) to be notified when an event is detected on
 * a specific label. This option is an object where the property is the type of the event to listen and the value is a callback
 * with a unique context argument.<br>
 * Charba events need to be enabled in order to get the associated label event working.<br>
 * If no listener is registered, incoming events are immediately ignored, preventing extra computation such as intersecting
 * label bounding box. That means there should be no performance penalty for configurations that don't use events.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Listeners extends NativeObjectContainer {

	// ---------------------------
	// -- JAVASCRIPT FUNCTIONS ---
	// ---------------------------

	/**
	 * Java script FUNCTION callback called to manage the ENTER event of plugin.<br>
	 * If the callback explicitly returns <code>true</code>, the label is updated with the new context and the chart
	 * re-rendered.<br>
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
		 * @return if the callback explicitly returns <code>true</code>, the label is updated with the new context and the chart
		 *         re-rendered.
		 */
		boolean call(Object contextFunction, DataLabelsContext context);
	}

	/**
	 * Java script FUNCTION callback called to manage the LEAVE event of plugin.<br>
	 * If the callback explicitly returns <code>true</code>, the label is updated with the new context and the chart
	 * re-rendered.<br>
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
		 * @return if the callback explicitly returns <code>true</code>, the label is updated with the new context and the chart
		 *         re-rendered.
		 */
		boolean call(Object contextFunction, DataLabelsContext context);
	}

	/**
	 * Java script FUNCTION callback called to manage the CLICK event of plugin.<br>
	 * If the callback explicitly returns <code>true</code>, the label is updated with the new context and the chart
	 * re-rendered.<br>
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
		 * @return if the callback explicitly returns <code>true</code>, the label is updated with the new context and the chart
		 *         re-rendered.
		 */
		boolean call(Object contextFunction, DataLabelsContext context);
	}

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the ENTER event function
	private final CallbackProxy<ProxyEnterEventCallback> enterEventCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the LEAVE event function
	private final CallbackProxy<ProxyLeaveEventCallback> leaveEventCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the CLICK event function
	private final CallbackProxy<ProxyClickEventCallback> clickEventCallbackProxy = JsHelper.get().newCallbackProxy();
	// ENTER event handler instance
	private EnterEventHandler enterEventHandler = null;
	// LEAVE event handler instance
	private LeaveEventHandler leaveEventHandler = null;
	// CLICK event handler instance
	private ClickEventHandler clickEventHandler = null;

	/**
	 * Creates the options element, adding all function to callbacks proxies.
	 */
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
			public boolean call(Object contextFunction, DataLabelsContext context) {
				// gets chart instance by id
				String id = context.getNativeChart().getCharbaId();
				AbstractChart<?, ?> chart = Charts.get(id);
				// checks if the handler is set
				if (chart != null && enterEventHandler != null) {
					// calls handler
					return enterEventHandler.onEnter(chart, context);
				}
				// defaults always true
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
			public boolean call(Object contextFunction, DataLabelsContext context) {
				// gets chart instance
				String id = context.getNativeChart().getCharbaId();
				AbstractChart<?, ?> chart = Charts.get(id);
				// checks if the handler is set
				if (chart != null && leaveEventHandler != null) {
					// calls handler
					return leaveEventHandler.onLeave(chart, context);
				}
				// defaults always true
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
			public boolean call(Object contextFunction, DataLabelsContext context) {
				// gets chart instance
				String id = context.getNativeChart().getCharbaId();
				AbstractChart<?, ?> chart = Charts.get(id);
				// checks if the handler is set
				if (chart != null && clickEventHandler != null) {
					// calls handler
					return clickEventHandler.onClick(chart, context);
				}
				// defaults always true
				return true;
			}
		});
	}

	/**
	 * Returns the CLICK event (the mouse's primary button is pressed and released on a label) handler.
	 * 
	 * @return the click event handler instance or <code>null</code> if not set.
	 */
	public ClickEventHandler getClickEventHandler() {
		return clickEventHandler;
	}

	/**
	 * Sets the CLICK event (the mouse's primary button is pressed and released on a label) handler.
	 * 
	 * @param clickEventHandler the click event handler to set
	 */
	public void setClickEventHandler(ClickEventHandler clickEventHandler) {
		// sets the handler
		this.clickEventHandler = clickEventHandler;
		// checks if handler is consistent
		if (clickEventHandler != null) {
			// adds the callback proxy function to java script object
			setValue(Event.click, clickEventCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Event.click);
		}
	}

	/**
	 * Returns the ENTER event (the mouse is moved over a label) handler.
	 * 
	 * @return the ENTER event handler instance or <code>null</code> if not set.
	 */
	public EnterEventHandler getEnterEventHandler() {
		return enterEventHandler;
	}

	/**
	 * Sets the ENTER event (the mouse is moved over a label) handler.
	 * 
	 * @param enterEventHandler the enter event handler to set
	 */
	public void setEnterEventHandler(EnterEventHandler enterEventHandler) {
		// sets the handler
		this.enterEventHandler = enterEventHandler;
		// checks if handler is consistent
		if (enterEventHandler != null) {
			// adds the callback proxy function to java script object
			setValue(Event.enter, enterEventCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Event.enter);
		}
	}

	/**
	 * Returns the LEAVE event (the mouse is moved out of a label) handler.
	 * 
	 * @return the LEAVE event handler instance or <code>null</code> if not set.
	 */
	public LeaveEventHandler getLeaveEventHandler() {
		return leaveEventHandler;
	}

	/**
	 * Sets the LEAVE event (the mouse is moved out of a label) handler.
	 * 
	 * @param leaveEventHandler the leave event handler to set
	 */
	public void setLeaveEventHandler(LeaveEventHandler leaveEventHandler) {
		// sets the handler
		this.leaveEventHandler = leaveEventHandler;
		// checks if handler is consistent
		if (leaveEventHandler != null) {
			// adds the callback proxy function to java script object
			setValue(Event.leave, leaveEventCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Event.leave);
		}
	}

}
