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

import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyBooleanCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.CallbackPropertyHandler;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
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
public final class Listeners extends AbstractNode implements IsDefaultListeners {

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the ENTER event function
	private final CallbackProxy<ProxyBooleanCallback> enterEventCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the LEAVE event function
	private final CallbackProxy<ProxyBooleanCallback> leaveEventCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the CLICK event function
	private final CallbackProxy<ProxyBooleanCallback> clickEventCallbackProxy = JsHelper.get().newCallbackProxy();

	// CLICK event handler instance
	private static final CallbackPropertyHandler<ClickEventHandler> CLICK_EVENT_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Event.CLICK);
	// ENTER event handler instance
	private static final CallbackPropertyHandler<EnterEventHandler> ENTER_EVENT_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Event.ENTER);
	// LEAVE event handler instance
	private static final CallbackPropertyHandler<LeaveEventHandler> LEAVE_EVENT_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Event.LEAVE);
	// parent instance
	private final LabelItem parent;
	// parent instance
	private final IsDefaultListeners defaultOptions;

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param parent data labels options instance, parent of this node
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultOptions default options instance
	 * @param nativeObject native object instance to be wrapped.
	 */
	Listeners(LabelItem parent, Key childKey, IsDefaultListeners defaultOptions, NativeObject nativeObject) {
		super(parent, childKey, nativeObject);
		// checks if label is consistent
		// stores parent
		this.parent = Checker.checkAndGetIfValid(parent, "Parent label argument");
		// checks if default value is consistent
		// stores default
		this.defaultOptions = checkDefaultValuesArgument(defaultOptions);
		// stores incremental ID
		setNewIncrementalId();
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		this.enterEventCallbackProxy.setCallback(context -> onEnter(new DataLabelsContext(this.parent, context)));
		this.leaveEventCallbackProxy.setCallback(context -> onLeave(new DataLabelsContext(this.parent, context)));
		this.clickEventCallbackProxy.setCallback(context -> onClick(new DataLabelsContext(this.parent, context)));
	}

	/**
	 * Returns the CLICK event (the mouse's primary button is pressed and released on a label) handler.
	 * 
	 * @return the click event handler instance or <code>null</code> if not set.
	 */
	@Override
	public ClickEventHandler getClickEventHandler() {
		return CLICK_EVENT_PROPERTY_HANDLER.getCallback(this, defaultOptions.getClickEventHandler());
	}

	/**
	 * Sets the CLICK event (the mouse's primary button is pressed and released on a label) handler.
	 * 
	 * @param clickEventHandler the click event handler to set
	 */
	public void setClickEventHandler(ClickEventHandler clickEventHandler) {
		CLICK_EVENT_PROPERTY_HANDLER.setCallback(this, DataLabelsPlugin.ID, clickEventHandler, clickEventCallbackProxy.getProxy());
	}

	/**
	 * Returns the ENTER event (the mouse is moved over a label) handler.
	 * 
	 * @return the ENTER event handler instance or <code>null</code> if not set.
	 */
	@Override
	public EnterEventHandler getEnterEventHandler() {
		return ENTER_EVENT_PROPERTY_HANDLER.getCallback(this, defaultOptions.getEnterEventHandler());

	}

	/**
	 * Sets the ENTER event (the mouse is moved over a label) handler.
	 * 
	 * @param enterEventHandler the enter event handler to set
	 */
	public void setEnterEventHandler(EnterEventHandler enterEventHandler) {
		ENTER_EVENT_PROPERTY_HANDLER.setCallback(this, DataLabelsPlugin.ID, enterEventHandler, enterEventCallbackProxy.getProxy());
	}

	/**
	 * Returns the LEAVE event (the mouse is moved out of a label) handler.
	 * 
	 * @return the LEAVE event handler instance or <code>null</code> if not set.
	 */
	@Override
	public LeaveEventHandler getLeaveEventHandler() {
		return LEAVE_EVENT_PROPERTY_HANDLER.getCallback(this, defaultOptions.getLeaveEventHandler());
	}

	/**
	 * Sets the LEAVE event (the mouse is moved out of a label) handler.
	 * 
	 * @param leaveEventHandler the leave event handler to set
	 */
	public void setLeaveEventHandler(LeaveEventHandler leaveEventHandler) {
		LEAVE_EVENT_PROPERTY_HANDLER.setCallback(this, DataLabelsPlugin.ID, leaveEventHandler, leaveEventCallbackProxy.getProxy());
	}

	/**
	 * Returns a boolean when the callback has been activated.
	 * 
	 * @param context native object as context.
	 * @return a object property value, as boolean
	 */
	private boolean onClick(DataLabelsContext context) {
		// gets callback
		ClickEventHandler clickEventHandler = getClickEventHandler();
		// checks if the context and handler are consistent
		if (ScriptableUtils.isContextConsistent(context) && clickEventHandler != null) {
			// calls handler
			return clickEventHandler.onClick(context);
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
	private boolean onEnter(DataLabelsContext context) {
		// gets callback
		EnterEventHandler enterEventHandler = getEnterEventHandler();
		// checks if the context and handler are consistent
		if (ScriptableUtils.isContextConsistent(context) && enterEventHandler != null) {
			// calls handler
			return enterEventHandler.onEnter(context);
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
	private boolean onLeave(DataLabelsContext context) {
		// gets callback
		LeaveEventHandler leaveEventHandler = getLeaveEventHandler();
		// checks if the context and handler are consistent
		if (ScriptableUtils.isContextConsistent(context) && leaveEventHandler != null) {
			// calls handler
			return leaveEventHandler.onLeave(context);
		}
		// defaults always true
		return true;
	}
}
