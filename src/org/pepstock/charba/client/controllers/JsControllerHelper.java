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
import org.pepstock.charba.client.Injector;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.resources.ResourcesType;

/**
 * This is a singleton wrapper for Java native object which is wrapping a CHARBA java script object implementation with some
 * utilities to manage CHART.JS controllers.<br>
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
		Injector.ensureInjected(ResourcesType.getClientBundle().chartJs());
		// to be sure that CHARBA java script object is injected
		Injector.ensureInjected(ResourcesType.getClientBundle().charbaHelper());
	}

	/**
	 * Singleton object to get the helper instance
	 * 
	 * @return jsControllerHelper instance.
	 */
	static JsControllerHelper get() {
		return INSTANCE;
	}

	/**
	 * Register the controller which does not extend any existing one.
	 * 
	 * @param controllerType controller type
	 * @param instance controller java script instance
	 */
	void register(ControllerType controllerType, NativeObject instance) {
		NativeJsControllerHelper.register(controllerType.name(), instance);
	}

	/**
	 * Extends an existing chart with a controller implementation.
	 * 
	 * @param controllerType controller type
	 * @param chartType type of extended chart
	 * @param instance controller java script instance
	 */
	void extend(ControllerType controllerType, ChartType chartType, NativeObject instance) {
		NativeJsControllerHelper.extend(controllerType.name(), chartType.name(), instance);
	}

	/**
	 * Invokes the default <code>initialize</code> method.
	 * 
	 * @param chartType extended chart type
	 * @param context context of controller
	 * @param datasetIndex dataset index
	 */
	void initialize(ChartType chartType, Context context, int datasetIndex) {
		NativeJsControllerHelper.initialize(chartType.name(), context, datasetIndex);
	}

	/**
	 * Invokes the default <code>AddElements</code> method.
	 * 
	 * @param chartType extended chart type
	 * @param context context of controller
	 */
	void addElements(ChartType chartType, Context context) {
		NativeJsControllerHelper.addElements(chartType.name(), context);
	}

	/**
	 * Invokes the default <code>addElementAndReset</code> method.
	 * 
	 * @param chartType extended chart type
	 * @param context context of controller
	 * @param index dataset index
	 */
	void addElementAndReset(ChartType chartType, Context context, int index) {
		NativeJsControllerHelper.addElementAndReset(chartType.name(), context, index);
	}

	/**
	 * Invokes the default <code>draw</code> method.
	 * 
	 * @param chartType extended chart type
	 * @param context context of controller
	 * @param ease if specified, this number represents how far to transition elements.
	 */
	void draw(ChartType chartType, Context context, double ease) {
		NativeJsControllerHelper.draw(chartType.name(), context, ease);
	}

	/**
	 * Invokes the default <code>removeHoverStyle</code> method.
	 * 
	 * @param chartType extended chart type
	 * @param context context of controller
	 * @param element element to be remove.
	 */
	void removeHoverStyle(ChartType chartType, Context context, NativeObject element) {
		NativeJsControllerHelper.removeHoverStyle(chartType.name(), context, element);
	}

	/**
	 * Invokes the default <code>setHoverStyle</code> method.
	 * 
	 * @param chartType extended chart type
	 * @param context context of controller
	 * @param element element to be set.
	 */
	void setHoverStyle(ChartType chartType, Context context, NativeObject element) {
		NativeJsControllerHelper.setHoverStyle(chartType.name(), context, element);
	}

	/**
	 * Invokes the default <code>update</code> method.
	 * 
	 * @param chartType extended chart type
	 * @param context context of controller
	 * @param reset if true, put the elements into a reset state so they can animate to their final values
	 */
	void update(ChartType chartType, Context context, boolean reset) {
		NativeJsControllerHelper.update(chartType.name(), context, reset);
	}

}
