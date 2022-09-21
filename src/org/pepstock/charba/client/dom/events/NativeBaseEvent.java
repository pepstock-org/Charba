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
package org.pepstock.charba.client.dom.events;

import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Constants;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeName;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.dom.BaseEventTarget;
import org.pepstock.charba.client.dom.IsCastable;
import org.pepstock.charba.client.dom.enums.EventPhase;
import org.pepstock.charba.client.options.IsEvent;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Represents an base event which takes place in the DOM.<br>
 * There are many types of events, some of which use other interfaces based on the main event interface.<br>
 * Event itself contains the properties and methods which are common to all events.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
// Ignores SonarCloud issue, java:S1610 - Abstract classes without fields should be converted to interfaces,
// because this is JSINTEROP implementation of native object which must be a class because there are native methods
@SuppressWarnings("java:S1610")
@JsType(isNative = true, name = NativeName.DOM_EVENT, namespace = JsPackage.GLOBAL)
public abstract class NativeBaseEvent implements IsCastable {

	/**
	 * To avoid any instantiation
	 */
	NativeBaseEvent() {
		// do nothing
	}

	// ----------------------
	// PROPERTIES
	// ----------------------

	/**
	 * Returns <code>true</code> indicates whether the event bubbles up through the DOM tree or not.
	 * 
	 * @return <code>true</code> indicates whether the event bubbles up through the DOM tree or not
	 */
	@JsProperty
	public final native boolean isBubbles();

	/**
	 * Returns <code>true</code> indicates whether the event can be canceled, and therefore prevented as if the event never happened.<br>
	 * If the event is not cancelable, then its cancelable property will be false and the event listener cannot stop the event from occurring.
	 * 
	 * @return <code>true</code> indicates whether the event can be canceled, and therefore prevented as if the event never happened
	 */
	@JsProperty
	public final native boolean isCancelable();

	/**
	 * Returns which phase of the event flow is currently being evaluated.
	 * 
	 * @return which phase of the event flow is currently being evaluated
	 */
	@JsProperty(name = "eventPhase")
	private final native int getNativeEventPhase();

	/**
	 * Returns a reference to the currently registered target for the event.<br>
	 * This is the object to which the event is currently slated to be sent.
	 * 
	 * @return a reference to the currently registered target for the event
	 */
	@JsProperty
	public final native BaseEventTarget getCurrentTarget();

	/**
	 * Indicates whether or not the call to {@link NativeBaseEvent#preventDefault()} canceled the event.
	 * 
	 * @return Indicates whether or not the call to {@link NativeBaseEvent#preventDefault()} canceled the event
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

	// ----------------------
	// METHODS
	// ----------------------

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

	// ----------------------
	// OVERLAY
	// ----------------------

	/**
	 * Creates a new event object for a specific event type.
	 * 
	 * @param type type of event
	 * @return a new event object for a specific event type
	 */
	@JsOverlay
	public static final NativeBaseEvent createEvent(String type) {
		return createEvent(type, EventInit.DEFAULT_INSTANCE);
	}

	/**
	 * Creates a new event object for a specific event type.
	 * 
	 * @param type type of event
	 * @return a new event object for a specific event type
	 */
	@JsOverlay
	public static final NativeBaseEvent createEvent(IsEvent type) {
		return createEvent(type, EventInit.DEFAULT_INSTANCE);
	}

	/**
	 * Creates a new event object for a specific event type with initial configuration.
	 * 
	 * @param type type of event
	 * @param init initial configuration of the event
	 * @return a new event object for a specific event type
	 */
	@JsOverlay
	public static final NativeBaseEvent createEvent(IsEvent type, EventInit init) {
		return createEvent(Key.checkAndGetIfValid(type).value(), init);
	}

	/**
	 * Creates a new event object for a specific event type with initial configuration.
	 * 
	 * @param type type of event
	 * @param init initial configuration of the event
	 * @return a new event object for a specific event type
	 */
	@JsOverlay
	public static final NativeBaseEvent createEvent(String type, EventInit init) {
		return new BaseNativeEventImpl(type, checkAndGetInit(type, init, EventInit.DEFAULT_INSTANCE, null));
	}

	/**
	 * Returns which phase of the event flow is currently being evaluated.
	 * 
	 * @return which phase of the event flow is currently being evaluated
	 */
	@JsOverlay
	public final EventPhase getEventPhase() {
		return EventPhase.get(getNativeEventPhase());
	}

	/**
	 * Checks the initialization configuration of the event and if event type is consistent with event type.
	 * 
	 * @param type event type
	 * @param init initialization configuration instance
	 * @param defaultInit the default initialization configuration instance
	 * @param types possible event type of this specific event
	 * @return the initialization configuration of the event
	 */
	@JsOverlay
	static final NativeObject checkAndGetInit(String type, EventInit init, EventInit defaultInit, IsEvent[] types) {
		// checks if type is consistent
		Checker.assertCheck(type != null, "Type is null");
		// checks if types must be checks
		if (types != null) {
			// checks if is a consistent mouse type
			Checker.assertCheck(checkIfSubEventType(type, types) || Key.hasKeyByValue(types, type), "Type is not consistent for this kind of event: " + type);
		}
		// return init object reference
		return init != null ? init.nativeObject() : defaultInit.nativeObject();
	}

	/**
	 * Checks if the event type is a sub event of original ones.<br>
	 * For instance, 'mousedown_xxxx'.
	 * 
	 * @param type type of the event to check
	 * @param types possible event type of this specific event
	 * @return <code>true</code> if the event type is a sub event of the types
	 */
	@JsOverlay
	private static boolean checkIfSubEventType(String type, IsEvent[] types) {
		// scans types to check if is
		// a sub-type: type + underscore
		for (IsEvent originalType : types) {
			// normalized event
			String normEvent = originalType.value() + Constants.UNDERSCORE;
			// checks if is a sub event
			if (type.startsWith(normEvent)) {
				return true;
			}
		}
		// if event, is not a sub event
		// then returns true
		return false;
	}
}