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
package org.pepstock.charba.client.positioner;

import org.pepstock.charba.client.commons.CallbackProxy.Proxy;
import org.pepstock.charba.client.commons.NativeName;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * This is a Java native object which is wrapping a CHARBA java script object implementation with some utilities to manage
 * CHART.JS tooltips positioner.<br>
 * This script will be injected with CHART.JS.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.JSPOSITIONERHELPER, namespace = JsPackage.GLOBAL)
final class NativeJsPositionerHelper {

	/**
	 * To avoid any instantiation
	 */
	NativeJsPositionerHelper() {
		// do nothing
	}

	/**
	 * Register the tooltips positioner to CHART.JS.
	 * 
	 * @param name name of new position
	 * @param object callback to invoke the tolltip positioner
	 */
	static native void register(String name, Proxy object);
	
	/**
	 * Unregister the tooltips positioner from CHART.JS.
	 * 
	 * @param name name of position to be removed
	 */
	static native void unregister(String name);
	
	/**
	 * Invokes an existing positioner to get the point.
	 * 
	 * @param position position of tooltips to be invoked
	 * @param context context Value of <code>this</code> to the execution context of function.
	 * @param elements elements of tooltips.
	 * @param eventPosition point on the canvas where the event occurred.
	 * @return the point calculated by positioner or <code>null</code> if positioner does not exist
	 */
	static native Point invoke(String name, PositionerContext context, Object elements, Point eventPoint);

}
