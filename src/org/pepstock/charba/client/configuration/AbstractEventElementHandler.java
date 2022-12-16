/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.configuration;

import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.dom.BaseEventTarget.EventListenerCallback;
import org.pepstock.charba.client.dom.enums.MouseEventType;
import org.pepstock.charba.client.dom.events.NativeBaseEvent;

/**
 * Base class to manage the events on canvas for chart elements, like title and axes.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class AbstractEventElementHandler implements IsEventProvider {

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------

	// callback proxy to invoke the click function for element
	private final CallbackProxy<EventListenerCallback> clickCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the hover function for element
	private final CallbackProxy<EventListenerCallback> hoverCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the leave function for element
	private final CallbackProxy<EventListenerCallback> leaveCallbackProxy = JsHelper.get().newCallbackProxy();

	// configuration options instance
	private final ConfigurationOptions configuration;

	/**
	 * Builds the object storing the configuration options.
	 * 
	 * @param configuration chart configuration instance
	 */
	AbstractEventElementHandler(ConfigurationOptions configuration) {
		// checks and stores the configuration
		this.configuration = Checker.checkAndGetIfValid(configuration, "Configuration argument");
		// registers as event handler
		IsEventProvider.register(this.configuration.getChart(), this);
		// --------------------------------------------------
		// -- SET CALLBACK for title and axis click event ---
		// --------------------------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.clickCallbackProxy.setCallback(this::handleClickEventOnElements);
		// sets function to proxy callback in order to invoke the java interface
		this.hoverCallbackProxy.setCallback(this::handleHoverEventOnElements);
		// sets function to proxy callback in order to invoke the java interface
		this.leaveCallbackProxy.setCallback(this::handleLeaveEventOnElements);
	}

	/**
	 * Returns the configuration instance.
	 * 
	 * @return the configuration instance
	 */
	final ConfigurationOptions getConfiguration() {
		return configuration;
	}

	/**
	 * Checks and manage the canvas event listeners for click events.<br>
	 * If the argument is <code>true</code>, it will add the event listener if is the first adding, otherwise it will remove the listener if is the last handler.
	 * 
	 * @param isAdding if <code>true</code>, the handlers are added.
	 * @param manageListener if <code>true</code>, is the first or last listener and then it will add or remove event listener to/from canvas.
	 */
	final void checkAndManageCanvasClickListeners(boolean isAdding, boolean manageListener) {
		// checks if is the first or last in order to apply the action on canvas
		if (manageListener) {
			// checks if must be add
			if (isAdding) {
				// adds listener
				configuration.getChart().getCanvas().addEventListener(MouseEventType.CLICK, clickCallbackProxy.getProxy());
			} else {
				// removes listener
				configuration.getChart().getCanvas().removeEventListener(MouseEventType.CLICK, clickCallbackProxy.getProxy());
			}
		}
	}

	/**
	 * Checks and manage the canvas event listeners for hover events.<br>
	 * If the argument is <code>true</code>, it will add the event listener if is the first adding, otherwise it will remove the listener if is the last handler.
	 * 
	 * @param isAdding if <code>true</code>, the handlers are added.
	 * @param manageListener if <code>true</code>, is the first or last listener and then it will add or remove event listener to/from canvas.
	 */
	final void checkAndManageCanvasHoverListeners(boolean isAdding, boolean manageListener) {
		// checks if is the first or last in order to apply the action on canvas
		if (manageListener) {
			// checks if must be add
			if (isAdding) {
				// adds listeners
				configuration.getChart().getCanvas().addEventListener(MouseEventType.MOUSE_MOVE, hoverCallbackProxy.getProxy());
				configuration.getChart().getCanvas().addEventListener(MouseEventType.MOUSE_LEAVE, leaveCallbackProxy.getProxy());
			} else {
				// removes listeners
				configuration.getChart().getCanvas().removeEventListener(MouseEventType.MOUSE_MOVE, hoverCallbackProxy.getProxy());
				configuration.getChart().getCanvas().removeEventListener(MouseEventType.MOUSE_LEAVE, leaveCallbackProxy.getProxy());
			}
		}
	}

	/**
	 * Check if the click event on element of chart and manage it firing the event.
	 * 
	 * @param event event generated on chart
	 */
	abstract void handleClickEventOnElements(NativeBaseEvent event);

	/**
	 * Check if the hover event on element of chart and manage it firing the event.
	 * 
	 * @param event event generated on chart
	 */
	abstract void handleHoverEventOnElements(NativeBaseEvent event);

	/**
	 * Check if the leave event on element of chart and manage it firing the event.
	 * 
	 * @param event event generated on chart
	 */
	abstract void handleLeaveEventOnElements(NativeBaseEvent event);

}