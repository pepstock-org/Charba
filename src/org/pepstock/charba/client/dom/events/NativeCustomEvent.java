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
package org.pepstock.charba.client.dom.events;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeName;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.options.IsEvent;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * Internal implementation of a custom native event.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.DOM_CUSTOM_EVENT, namespace = JsPackage.GLOBAL)
public class NativeCustomEvent extends NativeBaseEvent {

	/**
	 * Create a custom event by its type.
	 * 
	 * @param type type of the animation event
	 */
	// Ignores SonarCloud issue, java:S1172 - Unused method parameters should be removed, because this is the way of JSINTEROP to invoke a JavaScript constructor.
	@SuppressWarnings("java:S1172")
	protected NativeCustomEvent(String type) {
		// do nothing
	}

	/**
	 * Create a custom event by its type and initialization configuration.
	 * 
	 * @param type type of the custom event
	 * @param init event initialization dictionary to configure the event
	 */
	// Ignores SonarCloud issue, java:S1172 - Unused method parameters should be removed, because this is the way of JSINTEROP to invoke a JavaScript constructor.
	@SuppressWarnings("java:S1172")
	protected NativeCustomEvent(String type, NativeObject init) {
		// do nothing
	}

	/**
	 * Creates a new event object for a specific event type.
	 * 
	 * @param type type of event
	 * @return a new event object for a specific event type
	 */
	@JsOverlay
	public static final NativeCustomEvent createCustomEvent(String type) {
		return new NativeCustomEvent(type);
	}

	/**
	 * Creates a new event object for a specific event type.
	 * 
	 * @param type type of event
	 * @return a new event object for a specific event type
	 */
	@JsOverlay
	public static final NativeCustomEvent createCustomEvent(IsEvent type) {
		return createCustomEvent(Key.checkAndGetIfValid(type).value());
	}

	/**
	 * Creates a new event object for a specific event type with initial configuration.
	 * 
	 * @param type type of event
	 * @param init initial configuration of the event
	 * @return a new event object for a specific event type
	 */
	@JsOverlay
	public static final NativeCustomEvent createCustomEvent(IsEvent type, EventInit init) {
		return createCustomEvent(Key.checkAndGetIfValid(type).value(), init);
	}

	/**
	 * Creates a new event object for a specific event type with initial configuration.
	 * 
	 * @param type type of event
	 * @param init initial configuration of the event
	 * @return a new event object for a specific event type
	 */
	@JsOverlay
	public static final NativeCustomEvent createCustomEvent(String type, EventInit init) {
		// checks and gets init
		EventInit checked = init == null ? EventInit.DEFAULT_INSTANCE : init;
		// creates custom event
		return new NativeCustomEvent(type, checked.nativeObject());
	}

}