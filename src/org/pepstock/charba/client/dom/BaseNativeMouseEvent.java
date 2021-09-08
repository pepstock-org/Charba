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
import org.pepstock.charba.client.impl.plugins.SelectEventInit;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * Represents a mouse events that occur due to the user interacting with a pointing device.<br>
 * This is used only internally.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.DOM_MOUSE_EVENT, namespace = JsPackage.GLOBAL)
public final class BaseNativeMouseEvent extends BaseNativeEvent {

	/**
	 * Create a mouse event by its type and initialization configuration.
	 * 
	 * @param type type of the mouse event
	 * @param eventInitDict event initialization dictionary to configure the event
	 */
	BaseNativeMouseEvent(String type, SelectEventInit eventInitDict) {
		// do nothing
	}

}