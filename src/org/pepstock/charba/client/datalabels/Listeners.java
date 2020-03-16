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

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.callbacks.ScriptableContext;
import org.pepstock.charba.client.callbacks.ScriptableFunctions;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.datalabels.enums.Event;
import org.pepstock.charba.client.datalabels.events.ClickEventHandler;
import org.pepstock.charba.client.datalabels.events.EnterEventHandler;
import org.pepstock.charba.client.datalabels.events.LeaveEventHandler;

/**
 * This is the LISTENER options of {@link DataLabelsPlugin#ID} plugin allows to register callback(s) to be notified when an event is detected on a specific label. This option is an
 * object where the property is the type of the event to listen and the value is a callback with a unique context argument.<br>
 * Charba events need to be enabled in order to get the associated label event working.<br>
 * If no listener is registered, incoming events are immediately ignored, preventing extra computation such as intersecting label bounding box. That means there should be no
 * performance penalty for configurations that don't use events.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Listeners extends NativeObjectContainer {

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the ENTER event function
	private final CallbackProxy<ScriptableFunctions.ProxyBooleanCallback> enterEventCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the LEAVE event function
	private final CallbackProxy<ScriptableFunctions.ProxyBooleanCallback> leaveEventCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the CLICK event function
	private final CallbackProxy<ScriptableFunctions.ProxyBooleanCallback> clickEventCallbackProxy = JsHelper.get().newCallbackProxy();
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
		enterEventCallbackProxy.setCallback((contextFunction, context) -> onEnter(new ScriptableContext(context)));
		leaveEventCallbackProxy.setCallback((contextFunction, context) -> onLeave(new ScriptableContext(context)));
		clickEventCallbackProxy.setCallback((contextFunction, context) -> onClick(new ScriptableContext(context)));
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
			setValue(Event.CLICK, clickEventCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Event.CLICK);
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
			setValue(Event.ENTER, enterEventCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Event.ENTER);
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
			setValue(Event.LEAVE, leaveEventCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Event.LEAVE);
		}
	}

	/**
	 * Returns a boolean when the callback has been activated.
	 * 
	 * @param context native object as context.
	 * @return a object property value, as boolean
	 */
	private boolean onClick(ScriptableContext context) {
		// gets chart instance
		IsChart chart = ScriptableUtils.retrieveChart(context, clickEventHandler);
		// checks if the handler is set
		if (IsChart.isValid(chart)) {
			// calls handler
			return clickEventHandler.onClick(chart, context);
		}
		// defaults always true
		return true;
	}

	/**
	 * Returns a boolean when the callback has been activated.
	 * 
	 * @param context native object as context.
	 * @return a object property value, as boolean
	 */
	private boolean onEnter(ScriptableContext context) {
		// gets chart instance
		IsChart chart = ScriptableUtils.retrieveChart(context, enterEventHandler);
		// checks if the handler is set
		if (IsChart.isValid(chart)) {
			// calls handler
			return enterEventHandler.onEnter(chart, context);
		}
		// defaults always true
		return true;
	}

	/**
	 * Returns a boolean when the callback has been activated.
	 * 
	 * @param context native object as context.
	 * @return a object property value, as boolean
	 */
	private boolean onLeave(ScriptableContext context) {
		// gets chart instance
		IsChart chart = ScriptableUtils.retrieveChart(context, leaveEventHandler);
		// checks if the handler is set
		if (IsChart.isValid(chart)) {
			// calls handler
			return leaveEventHandler.onLeave(chart, context);
		}
		// defaults always true
		return true;
	}
}
