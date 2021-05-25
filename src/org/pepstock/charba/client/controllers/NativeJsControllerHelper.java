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
package org.pepstock.charba.client.controllers;

import org.pepstock.charba.client.commons.NativeName;
import org.pepstock.charba.client.commons.NativeObject;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * This is a Java native object which is wrapping a CHARBA java script object implementation with some utilities to manage CHART.JS controllers.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.JS_CONTROLLER_HELPER, namespace = JsPackage.GLOBAL)
final class NativeJsControllerHelper {

	/**
	 * To avoid any instantiation
	 */
	private NativeJsControllerHelper() {
		// do nothing
	}

	/**
	 * Extends an existing chart with a controller implementation.
	 * 
	 * @param controllerType controller type
	 * @param chartType type of extended chart
	 * @param object controller java script instance
	 */
	@JsMethod
	static native void register(String controllerType, String chartType, NativeObject object);

	/**
	 * Invokes the default <code>initialize</code> method.
	 * 
	 * @param chartType extended chart type
	 * @param context context of controller
	 */
	@JsMethod
	static native void initialize(String chartType, ControllerContext context);

	/**
	 * Invokes the default <code>parse</code> method.
	 * 
	 * @param chartType controller type
	 * @param context context of controller
	 * @param start start index of metadata
	 * @param count count of metadata
	 */
	@JsMethod
	static native void parse(String chartType, ControllerContext context, int start, int count);

	/**
	 * Invokes the default <code>draw</code> method.
	 * 
	 * @param chartType extended chart type
	 * @param context context of controller
	 */
	@JsMethod
	static native void draw(String chartType, ControllerContext context);

	/**
	 * Invokes the default <code>update</code> method.
	 * 
	 * @param chartType extended chart type
	 * @param context context of controller
	 * @param mode update mode, core calls this method using any of 'active', 'hide', 'reset', 'resize', 'show' or undefined
	 */
	@JsMethod
	static native void update(String chartType, ControllerContext context, String mode);

	/**
	 * Invokes the default <code>linkScales</code> method.
	 * 
	 * @param chartType extended chart type
	 * @param context context of controller
	 */
	@JsMethod
	static native void linkScales(String chartType, ControllerContext context);

}
