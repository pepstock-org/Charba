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
package org.pepstock.charba.client.impl.plugins;

import org.pepstock.charba.client.commons.NativeName;
import org.pepstock.charba.client.dom.BaseEventTarget;
import org.pepstock.charba.client.dom.BaseNativeEvent;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * Represents a DOM element that enables drawing graphics and animations with own the canvas scripting API.<br>
 * Enables the event dispatching.<br>
 * Internal class used by {@link DatasetsItemsSelector} plugin.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.DOM_HTML_CANVAS_ELEMENT, namespace = JsPackage.GLOBAL)
final class SelectCanvas {

	/**
	 * To avoid any instantiation
	 */
	private SelectCanvas() {
		// do nothing
	}

	/**
	 * Dispatches an {@link BaseNativeEvent} at the specified {@link BaseEventTarget}, (synchronously) invoking the affected event listeners in the appropriate order.
	 * 
	 * @param event the {@link BaseNativeEvent} instance to be dispatched.
	 * @return <code>false</code> if event is cancelable and at least one of the event handlers which received event called {@link BaseNativeEvent#preventDefault()}, otherwise it
	 *         returns <code>true</code>.
	 */
	@JsMethod
	native boolean dispatchEvent(BaseNativeEvent event);

}
