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

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Is a mouse event initialization dictionary, having the fields to create an event.<br>
 * This is used only internally.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.OBJECT, namespace = JsPackage.GLOBAL)
public final class SelectEventInit {

	/**
	 * To avoid any instantiation
	 */
	SelectEventInit() {
	}

	/**
	 * Sets the horizontal position of the mouse event on the client window of user's screen; setting this value doesn't move the mouse pointer.
	 *
	 * @param x the horizontal position of the mouse event on the client window of user's screen
	 */
	@JsProperty
	native void setClientX(double x);

	/**
	 * Sets the vertical position of the mouse event on the client window of the user's screen; setting this value doesn't move the mouse pointer.
	 *
	 * @param y the vertical position of the mouse event on the client window of the user's screen
	 */
	@JsProperty
	native void setClientY(double y);

	/**
	 * Sets the type of the event.
	 * 
	 * @param type the type of the event
	 */
	@JsProperty
	native void setType(String type);

	/**
	 * Returns the type of the event.
	 * 
	 * @return the type of the event
	 */
	@JsProperty
	public native String getType();

}