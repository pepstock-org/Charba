/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License"){}
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package org.pepstock.charba.client.dom;

import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeName;
import org.pepstock.charba.client.dom.events.NativeBaseEvent;
import org.pepstock.charba.client.events.AbstractEvent;
import org.pepstock.charba.client.events.HasNativeEvent;
import org.pepstock.charba.client.options.IsEvent;

import jsinterop.annotations.JsFunction;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * Base DOM entity extended by objects that can receive events and may have listeners for them.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
// Ignores SonarCloud issue, java:S1610 - Abstract classes without fields should be converted to interfaces,
// because this is JSINTEROP implementation of native object which must be a class because there are native methods
@SuppressWarnings("java:S1610")
@JsType(isNative = true, name = NativeName.DOM_EVENT_TARGET, namespace = JsPackage.GLOBAL)
public abstract class BaseEventTarget {

	// ---------------------------
	// -- JAVASCRIPT FUNCTIONS ---
	// ---------------------------

	/**
	 * The object which receives a notification when an event of the specified type occurs.<br>
	 * Represents an object that can handle an event dispatched by a element.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	public interface EventListenerCallback {

		/**
		 * A function that is called whenever an event of the specified type occurs.
		 * 
		 * @param event a event object describing the event that has been fired and needs to be processed.
		 */
		void call(NativeBaseEvent event);
	}

	/**
	 * To avoid any instantiation
	 */
	BaseEventTarget() {
		// do nothing
	}

	/**
	 * Sets up a function that will be called whenever the specified event is delivered to the target.
	 * 
	 * @param type a case-sensitive string representing the event type to listen for
	 * @param listener the object which receives a notification when an event of the specified type occurs
	 */
	@JsMethod
	public final native void addEventListener(String type, CallbackProxy.Proxy listener);

	/**
	 * Removes from the event target an event listener previously registered.<br>
	 * The event listener to be removed is identified using a combination of the event type and the event listener function itself.
	 * 
	 * @param type a string which specifies the type of event for which to remove an event listener
	 * @param listener function instance of the event handler to remove from the event target.
	 */
	@JsMethod
	public final native void removeEventListener(String type, CallbackProxy.Proxy listener);

	/**
	 * Dispatches an {@link NativeBaseEvent} at the specified {@link BaseEventTarget}, (synchronously) invoking the affected event listeners in the appropriate order.
	 * 
	 * @param event the {@link NativeBaseEvent} instance to be dispatched.
	 * @return <code>false</code> if event is cancelable and at least one of the event handlers which received event called {@link NativeBaseEvent#preventDefault()}, otherwise it
	 *         returns <code>true</code>.
	 */
	@JsMethod
	public final native boolean dispatchEvent(NativeBaseEvent event);

	// --------------------
	// OVERLAY
	// --------------------

	/**
	 * Sets up a function that will be called whenever the specified event is delivered to the target.
	 * 
	 * @param type a case-sensitive string representing the event type to listen for
	 * @param listener the object which receives a notification when an event of the specified type occurs
	 */
	@JsOverlay
	public final void addEventListener(IsEvent type, CallbackProxy.Proxy listener) {
		// checks if type consistent
		if (Key.isValid(type)) {
			// adds listener
			addEventListener(type.value(), listener);
		}
	}

	/**
	 * Removes from the event target an event listener previously registered.<br>
	 * The event listener to be removed is identified using a combination of the event type and the event listener function itself.
	 * 
	 * @param type a string which specifies the type of event for which to remove an event listener
	 * @param listener function instance of the event handler to remove from the event target.
	 */
	@JsOverlay
	public final void removeEventListener(IsEvent type, CallbackProxy.Proxy listener) {
		// checks if type consistent
		if (Key.isValid(type)) {
			// adds listener
			removeEventListener(type.value(), listener);
		}
	}

	/**
	 * Dispatches an {@link AbstractEvent} at the specified {@link BaseEventTarget}, (synchronously) invoking the affected event listeners in the appropriate order.
	 * 
	 * @param event the {@link AbstractEvent} instance to be dispatched.
	 * @return <code>false</code> if event is cancelable and at least one of the event handlers which received event called {@link NativeBaseEvent#preventDefault()}, otherwise it
	 *         returns <code>true</code>.
	 */
	@JsOverlay
	public final boolean dispatchEvent(HasNativeEvent event) {
		// checks if argument is consistent
		if (event != null) {
			// dispatch event
			return dispatchEvent(event.getNativeEvent());
		}
		// event not consistent
		// then return false
		return false;

	}
}