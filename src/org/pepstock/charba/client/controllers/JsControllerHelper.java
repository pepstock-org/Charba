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

import org.pepstock.charba.client.ChartType;
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
	 * FIXME to be removed Register the controller which does not extend any existing one.
	 * 
	 * @param controllerType controller type
	 * @param instance controller java script instance
	 */
	void register(ControllerType controllerType, NativeObject instance) {
		NativeJsControllerHelper.register(controllerType.value(), instance);
	}

	/**
	 * Extends an existing chart with a controller implementation.
	 * 
	 * @param controllerType controller type
	 * @param chartType type of extended chart
	 * @param instance controller java script instance
	 */
	void extend(ControllerType controllerType, ChartType chartType, NativeObject instance) {
		// uses the controller template applying hte chart and controller types
		String javaScript = Utilities.applyTemplate(ControllerTemplate.CONTENT, controllerType.value(), chartType.value());
		// creates a function with teh template
		NativeFunction function = new NativeFunction(javaScript);
		// executes the function
		function.call();
		// registers new controller
		NativeJsControllerHelper.extend(controllerType.value(), chartType.value(), instance);
	}

	/**
	 * Invokes the default <code>initialize</code> method.
	 * 
	 * @param chartType extended chart type
	 * @param context context of controller
	 */
	void initialize(ChartType chartType, ControllerContext context) {
		NativeJsControllerHelper.initialize(chartType.value(), context);
	}

	/**
	 * Invokes the default <code>AddElements</code> method.
	 * 
	 * @param chartType extended chart type
	 * @param context context of controller
	 */
	void addElements(ChartType chartType, ControllerContext context) {
		NativeJsControllerHelper.addElements(chartType.value(), context);
	}

	/**
	 * Invokes the default <code>draw</code> method.
	 * 
	 * @param chartType extended chart type
	 * @param context context of controller
	 */
	void draw(ChartType chartType, ControllerContext context) {
		NativeJsControllerHelper.draw(chartType.value(), context);
	}

	/**
	 * Invokes the default <code>removeHoverStyle</code> method.
	 * 
	 * @param chartType extended chart type
	 * @param context context of controller
	 * @param element element to be remove
	 * @param datasetIndex dataset index
	 * @param index data index
	 */
	void removeHoverStyle(ChartType chartType, ControllerContext context, NativeObject element, int datasetIndex, int index) {
		NativeJsControllerHelper.removeHoverStyle(chartType.value(), context, element, datasetIndex, index);
	}

	/**
	 * Invokes the default <code>setHoverStyle</code> method.
	 * 
	 * @param chartType extended chart type
	 * @param context context of controller
	 * @param element element to be set.
	 * @param datasetIndex dataset index
	 * @param index data index
	 */
	void setHoverStyle(ChartType chartType, ControllerContext context, NativeObject element, int datasetIndex, int index) {
		NativeJsControllerHelper.setHoverStyle(chartType.value(), context, element, datasetIndex, index);
	}

	/**
	 * Invokes the default <code>update</code> method.
	 * 
	 * @param chartType extended chart type
	 * @param context context of controller
	 * @param mode update mode, core calls this method using any of 'active', 'hide', 'reset', 'resize', 'show' or undefined
	 */
	void update(ChartType chartType, ControllerContext context, String mode) {
		NativeJsControllerHelper.update(chartType.value(), context, mode);
	}

	/**
	 * Invokes the default <code>linkScales</code> method.
	 * 
	 * @param chartType extended chart type
	 * @param context context of controller
	 */
	void linkScales(ChartType chartType, ControllerContext context) {
		NativeJsControllerHelper.linkScales(chartType.value(), context);
	}

	/**
	 * Invokes the default <code>buildOrUpdateElements</code> method.
	 * 
	 * @param chartType extended chart type
	 * @param context context of controller
	 */
	void buildOrUpdateElements(ChartType chartType, ControllerContext context) {
		NativeJsControllerHelper.buildOrUpdateElements(chartType.value(), context);
	}

}
