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

import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.resources.ResourcesType;
import org.pepstock.charba.client.utils.Utilities;

/**
 * This is a singleton wrapper for Java native object which is wrapping a CHARBA java script object implementation with some utilities to manage CHART.JS controllers.<br>
 * This wrapper is necessary to ensure that script is injected with CHART.JS.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class JsControllerHelper {
	// static instance for singleton
	private static final JsControllerHelper INSTANCE = new JsControllerHelper();

	/**
	 * To avoid any instantiation
	 */
	private JsControllerHelper() {
		// to be sure that CHART.JS java script object is injected
		// some methods are calling CHART.JS for this reason is mandatory
		// to include also chart.js
		// inject Chart.js and date library if not already loaded
		ResourcesType.getClientBundle().inject();
		// to be sure that CHARBA java script object is injected
		// invoking the JsHelper
		JsHelper.get();
	}

	/**
	 * Singleton object to get the helper instance
	 * 
	 * @return helper instance.
	 */
	static JsControllerHelper get() {
		return INSTANCE;
	}

	/**
	 * Extends an existing chart with a controller implementation.
	 * 
	 * @param controllerType controller type
	 * @param instance controller java script instance
	 */
	void register(ControllerType controllerType, NativeObject instance) {
		// uses the controller template applying hte chart and controller types
		String javaScript = Utilities.applyTemplate(ControllerTemplate.get().getTemplate(), controllerType.value(), controllerType.getChartType().value());
		// creates a function with teh template
		NativeFunction function = new NativeFunction(javaScript);
		// executes the function
		function.call();
		// registers new controller
		NativeJsControllerHelper.register(controllerType.value(), controllerType.getChartType().value(), instance);
	}

	/**
	 * Invokes the default <code>initialize</code> method.
	 * 
	 * @param controllerType controller type
	 * @param context context of controller
	 */
	void initialize(ControllerType controllerType, ControllerContext context) {
		NativeJsControllerHelper.initialize(controllerType.getChartType().value(), context);
	}

	/**
	 * Invokes the default <code>AddElements</code> method.
	 * 
	 * @param controllerType controller type
	 * @param context context of controller
	 */
	void addElements(ControllerType controllerType, ControllerContext context) {
		NativeJsControllerHelper.addElements(controllerType.getChartType().value(), context);
	}

	/**
	 * Invokes the default <code>draw</code> method.
	 * 
	 * @param controllerType controller type
	 * @param context context of controller
	 */
	void draw(ControllerType controllerType, ControllerContext context) {
		NativeJsControllerHelper.draw(controllerType.getChartType().value(), context);
	}

	/**
	 * Invokes the default <code>removeHoverStyle</code> method.
	 * 
	 * @param controllerType controller type
	 * @param context context of controller
	 * @param element element to be remove
	 * @param datasetIndex dataset index
	 * @param index data index
	 */
	void removeHoverStyle(ControllerType controllerType, ControllerContext context, NativeObject element, int datasetIndex, int index) {
		NativeJsControllerHelper.removeHoverStyle(controllerType.getChartType().value(), context, element, datasetIndex, index);
	}

	/**
	 * Invokes the default <code>setHoverStyle</code> method.
	 * 
	 * @param controllerType controller type
	 * @param context context of controller
	 * @param element element to be set.
	 * @param datasetIndex dataset index
	 * @param index data index
	 */
	void setHoverStyle(ControllerType controllerType, ControllerContext context, NativeObject element, int datasetIndex, int index) {
		NativeJsControllerHelper.setHoverStyle(controllerType.getChartType().value(), context, element, datasetIndex, index);
	}

	/**
	 * Invokes the default <code>update</code> method.
	 * 
	 * @param controllerType controller type
	 * @param context context of controller
	 * @param mode update mode, core calls this method using any of 'active', 'hide', 'reset', 'resize', 'show' or undefined
	 */
	void update(ControllerType controllerType, ControllerContext context, String mode) {
		NativeJsControllerHelper.update(controllerType.getChartType().value(), context, mode);
	}

	/**
	 * Invokes the default <code>linkScales</code> method.
	 * 
	 * @param controllerType controller type
	 * @param context context of controller
	 */
	void linkScales(ControllerType controllerType, ControllerContext context) {
		NativeJsControllerHelper.linkScales(controllerType.getChartType().value(), context);
	}

	/**
	 * Invokes the default <code>buildOrUpdateElements</code> method.
	 * 
	 * @param controllerType controller type
	 * @param context context of controller
	 */
	void buildOrUpdateElements(ControllerType controllerType, ControllerContext context) {
		NativeJsControllerHelper.buildOrUpdateElements(controllerType.getChartType().value(), context);
	}

}
