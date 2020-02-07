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

import org.pepstock.charba.client.callbacks.CallbackFunctionContext;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.NativeName;

import jsinterop.annotations.JsFunction;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * Base DOM entity extended by objects that can receive events and may have listeners for them.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
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
		 * @param context context value of <code>this</code> to the execution context of function.
		 * @param event a event object describing the event that has been fired and needs to be processed.
		 */
		void call(CallbackFunctionContext context, BaseNativeEvent event);
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
	 * @see BaseEventTypes
	 */
	@JsMethod
	public final native void addEventListener(String type, CallbackProxy.Proxy listener);

	/**
	 * Removes from the event target an event listener previously registered.<br>
	 * The event listener to be removed is identified using a combination of the event type and the event listener function
	 * itself.
	 * 
	 * @param type a string which specifies the type of event for which to remove an event listener
	 * @param listener function instance of the event handler to remove from the event target.
	 */
	@JsMethod
	public final native void removeEventListener(String type, CallbackProxy.Proxy listener);

}