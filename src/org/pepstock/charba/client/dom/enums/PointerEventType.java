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
package org.pepstock.charba.client.dom.enums;

import org.pepstock.charba.client.options.IsEvent;

/**
 * Enumerates the DOM event type of the pointer (mouse, touch and pen).
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum PointerEventType implements IsEvent
{
	/**
	 * This event is fired when a pointing device is moved into an element's hit test boundaries.
	 */
	POINTER_OVER("pointerover"),
	/**
	 * This event is fired when a pointing device is moved into the hit test boundaries of an element or one of its descendants, including as a result of a
	 * {@link PointerEventType#POINTER_DOWN} event from a device that does not support hover (see {@link PointerEventType#POINTER_DOWN}).<br>
	 * This event type is similar to {@link PointerEventType#POINTER_OVER}, but differs in that it does not bubble.
	 */
	POINTER_ENTER("pointerenter"),
	/**
	 * The event is fired when a pointer becomes active.<br>
	 * For {@link PointerType#MOUSE}, it is fired when the device transitions from no buttons pressed to at least one button pressed.<br>
	 * For {@link PointerType#TOUCH}, it is fired when physical contact is made with the digitizer.<br>
	 * For {@link PointerType#PEN}, it is fired when the stylus makes physical contact with the digitizer.<br>
	 * Note: For touchscreen browsers that allow direct manipulation, a {@link PointerEventType#POINTER_DOWN} event triggers implicit pointer capture, which causes the target to
	 * capture all subsequent pointer events as if they were occurring over the capturing target.<br>
	 * Accordingly, {@link PointerEventType#POINTER_OVER}, {@link PointerEventType#POINTER_ENTER}, {@link PointerEventType#POINTER_LEAVE}, and {@link PointerEventType#POINTER_OUT}
	 * will not fire as long as this capture is set.<br>
	 * The capture can be released manually by calling element.releasePointerCapture on the target element, or it will be implicitly released after a
	 * {@link PointerEventType#POINTER_UP} or {@link PointerEventType#POINTER_CANCEL} event.
	 */
	POINTER_DOWN("pointerdown"),
	/**
	 * This event is fired when a pointer changes coordinates.
	 */
	POINTER_MOVE("pointermove"),
	/**
	 * This event is fired when a pointer is no longer active.
	 */
	POINTER_UP("pointerup"),
	/**
	 * A browser fires this event if it concludes the pointer will no longer be able to generate events (for example the related device is deactivated).
	 */
	POINTER_CANCEL("pointercancel"),
	/**
	 * This event is fired for several reasons including: pointing device is moved out of the hit test boundaries of an element; firing the {@link PointerEventType#POINTER_UP}
	 * event for a device that does not support hover; after firing the {@link PointerEventType#POINTER_CANCEL} event when a pen stylus leaves the hover range detectable by the
	 * digitizer.
	 */
	POINTER_OUT("pointerout"),
	/**
	 * This event is fired when a pointing device is moved out of the hit test boundaries of an element.<br>
	 * For {@link PointerType#PEN} devices, this event is fired when the stylus leaves the hover range detectable by the digitizer.
	 */
	POINTER_LEAVE("pointerleave"),
	/**
	 * This event is fired when an element receives pointer capture.
	 */
	GOT_POINTER_CAPTURE("gotpointercapture"),
	/**
	 * This event is fired after pointer capture is released for a pointer.
	 */
	LOST_POINTER_CAPTURE("lostpointercapture");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private PointerEventType(String value) {
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