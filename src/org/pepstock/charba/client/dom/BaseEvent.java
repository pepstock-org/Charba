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
package org.pepstock.charba.client.dom;

import org.pepstock.charba.client.commons.NativeName;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Represents an event which takes place in the DOM.<br>
 * There are many types of events, some of which use other interfaces based on the main event interface.<br>
 * Event itself contains the properties and methods which are common to all events.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
// Ignores SonarCloud issue, java:S1610 - Abstract classes without fields should be converted to interfaces,
// because this is JSINTEROP implementation of native object which must be a class because there are native methods
@SuppressWarnings("java:S1133")
@JsType(isNative = true, name = NativeName.DOM_EVENT, namespace = JsPackage.GLOBAL)
abstract class BaseEvent {

	/**
	 * To avoid any instantiation
	 */
	BaseEvent() {
		// do nothing
	}

	/**
	 * Returns a reference to the currently registered target for the event.<br>
	 * This is the object to which the event is currently slated to be sent.
	 * 
	 * @return a reference to the currently registered target for the event
	 */
	@JsProperty
	public final native BaseEventTarget getCurrentTarget();

	/**
	 * Indicates whether or not the call to {@link BaseEvent#preventDefault()} canceled the event.
	 * 
	 * @return Indicates whether or not the call to {@link BaseEvent#preventDefault()} canceled the event
	 */
	@JsProperty
	public final native boolean isDefaultPrevented();

	/**
	 * Returns a reference to the target to which the event was originally dispatched.
	 * 
	 * @return a reference to the target to which the event was originally dispatched
	 */
	@JsProperty
	public final native BaseEventTarget getTarget();

	/**
	 * Returns the type of the event.
	 * 
	 * @return the type of the event
	 */
	@JsProperty
	public final native String getType();

	/**
	 * Initialize the value of a created event.
	 * 
	 * @param eventTypeArg a string which is defining the type of event
	 * @param bubbles deciding whether the event should bubble up through the event chain or not
	 * @param cancelable defining whether the event can be canceled
	 */
	@JsMethod
	public final native void initEvent(String eventTypeArg, boolean bubbles, boolean cancelable);

	/**
	 * Cancels the event (if it is cancelable).
	 */
	@JsMethod
	public final native void preventDefault();

	/**
	 * Prevents all other listeners from being called.
	 */
	@JsMethod
	public final native void stopImmediatePropagation();

	/**
	 * Stops the propagation of events further along in the DOM.
	 */
	@JsMethod
	public final native void stopPropagation();

}