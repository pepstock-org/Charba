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

import org.pepstock.charba.client.Charts;
import org.pepstock.charba.client.Controller;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;

import jsinterop.annotations.JsFunction;

/**
 * Wraps a controller, delegating the execution of all hooks to it.<br>
 * The wrapper is mandatory to able to catch all hooks of chart even if the controller implements just a part of the hooks.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class WrapperController extends NativeObjectContainer {

	// ---------------------------
	// -- JAVASCRIPT FUNCTIONS ---
	// ---------------------------
	/**
	 * Java script FUNCTION callback called to catch the chart initialization.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyInitializeCallback {
		/**
		 * Initializes the controller.
		 * 
		 * @param context java script <code>this</code> function context.
		 */
		void call(ControllerContext context);
	}

	/**
	 * Java script FUNCTION callback called to catch the chart add elements.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyAddElementsCallback {

		/**
		 * Create elements for each piece of data in the dataset.<br>
		 * Store elements in an array on the dataset as dataset.metaData.
		 * 
		 * @param context java script <code>this</code> function context.
		 */
		void call(ControllerContext context);
	}

	/**
	 * Java script FUNCTION callback called to catch the chart drawing.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyDrawCallback {

		/**
		 * Draw the representation of the dataset.
		 * 
		 * @param context java script <code>this</code> function context.
		 */
		void call(ControllerContext context);
	}

	/**
	 * Java script FUNCTION callback called to catch the chart remove hover.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyRemoveHoverStyleCallback {

		/**
		 * Remove hover styling from the given element.
		 * 
		 * @param context java script <code>this</code> function context.
		 * @param element element to be removed.
		 * @param datasetIndex dataset index
		 * @param index data index
		 */
		void call(ControllerContext context, NativeObject element, int datasetIndex, int index);
	}

	/**
	 * Java script FUNCTION callback called to catch the chart set hover.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxySetHoverStyleCallback {

		/**
		 * Add hover styling to the given element.
		 * 
		 * @param context java script <code>this</code> function context.
		 * @param element element to be set.
		 * @param datasetIndex dataset index
		 * @param index data index
		 */
		void call(ControllerContext context, NativeObject element, int datasetIndex, int index);
	}

	/**
	 * Java script FUNCTION callback called to catch the chart updating.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyUpdateCallback {

		/**
		 * Update the elements in response to new data.
		 * 
		 * @param context java script <code>this</code> function context.
		 * @param mode update mode, core calls this method using any of 'active', 'hide', 'reset', 'resize', 'show' or undefined
		 */
		void call(ControllerContext context, String mode);
	}

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the initialize function
	private final CallbackProxy<ProxyInitializeCallback> initializeCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the add elements function
	private final CallbackProxy<ProxyAddElementsCallback> addElementsCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the draw function
	private final CallbackProxy<ProxyDrawCallback> drawCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the remove hover function
	private final CallbackProxy<ProxyRemoveHoverStyleCallback> removeHoverStyleCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the set hover function
	private final CallbackProxy<ProxySetHoverStyleCallback> setHoverStyleCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the update function
	private final CallbackProxy<ProxyUpdateCallback> updateCallbackProxy = JsHelper.get().newCallbackProxy();

	// user implementation of controller
	private final Controller delegation;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		TYPE("type"),
		INITIALIZE("initialize"),
		ADD_ELEMENTS("addElements"),
		DRAW("draw"),
		REMOVE_HOVER_STYLE("removeHoverStyle"),
		SET_HOVER_STYLE("setHoverStyle"),
		UPDATE("update");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use into native object.
		 * 
		 * @param value value of property name
		 */
		private Property(String value) {
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

	/**
	 * Builds the object with controller instance
	 * 
	 * @param delegation controller instance
	 */
	WrapperController(Controller delegation) {
		// stores the controller
		this.delegation = delegation;
		// sets the controller type
		setValue(Property.TYPE, delegation.getType());
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		initializeCallbackProxy.setCallback((context) -> {
			// checks if context and native chart are consistent
			// checks if chart is consistent
			if (context != null && context.getNativeChart() != null && IsChart.isValid(context.getChart())) {
				// invoke user method implementation
				onInitialize(context, context.getChart());
			}
		});
		// invoke user method implementation
		addElementsCallbackProxy.setCallback(context -> onAddElements(context, context.getChart()));
		// invoke user method implementation
		drawCallbackProxy.setCallback((context) -> onDraw(context, context.getChart()));
		// invoke user method implementation
		removeHoverStyleCallbackProxy.setCallback((context, element, datasetIndex, index) -> onRemoveHoverStyle(context, context.getChart(), element, datasetIndex, index));
		// invoke user method implementation
		setHoverStyleCallbackProxy.setCallback((context, element, datasetIndex, index) -> onSetHoverStyle(context, context.getChart(), element, datasetIndex, index));
		// invoke user method implementation
		updateCallbackProxy.setCallback((context, mode) -> onUpdate(context, context.getChart(), mode));
		// adds all proxy functions to call the functions to the native object
		setValue(Property.INITIALIZE, initializeCallbackProxy.getProxy());
		setValue(Property.ADD_ELEMENTS, addElementsCallbackProxy.getProxy());
		setValue(Property.DRAW, drawCallbackProxy.getProxy());
		setValue(Property.REMOVE_HOVER_STYLE, removeHoverStyleCallbackProxy.getProxy());
		setValue(Property.SET_HOVER_STYLE, setHoverStyleCallbackProxy.getProxy());
		setValue(Property.UPDATE, updateCallbackProxy.getProxy());
	}

	/**
	 * Returns the chart instances which must be consumed by controller.
	 * 
	 * @param chartId chart id.
	 * @return the chart instance or <code>null</code> if not found.
	 */
	IsChart getChart(String chartId) {
		return Charts.get(chartId);
	}

	/**
	 * Returns the controller type.
	 * 
	 * @return the controller type.
	 */
	Type getType() {
		return delegation.getType();
	}

	/**
	 * Initializes the controller.
	 * 
	 * @param context context of controller
	 * @param chart chart chart instance
	 */
	void onInitialize(ControllerContext context, IsChart chart) {
		// if consistent, calls controller
		if (Controller.isConsistent(delegation, context, chart)) {
			delegation.initialize(context, chart);
		}
	}

	/**
	 * Create elements for each piece of data in the dataset. Store elements in an array on the dataset.
	 * 
	 * @param context context of controller
	 * @param chart chart chart instance
	 */
	void onAddElements(ControllerContext context, IsChart chart) {
		// if consistent, calls controller
		if (Controller.isConsistent(delegation, context, chart)) {
			delegation.addElements(context, chart);
		}
	}

	/**
	 * Draw the representation of the dataset.
	 * 
	 * @param context context of controller
	 * @param chart chart chart instance
	 */
	void onDraw(ControllerContext context, IsChart chart) {
		// if consistent, calls controller
		if (Controller.isConsistent(delegation, context, chart)) {
			delegation.draw(context, chart);
		}
	}

	/**
	 * Remove hover styling from the given element.
	 * 
	 * @param context context of controller
	 * @param chart chart chart instance
	 * @param object element to be removed.
	 * @param datasetIndex dataset index
	 * @param index data index
	 */
	void onRemoveHoverStyle(ControllerContext context, IsChart chart, NativeObject object, int datasetIndex, int index) {
		// if consistent, calls controller
		if (Controller.isConsistent(delegation, context, chart)) {
			delegation.removeHoverStyle(context, chart, new StyleElement(object), datasetIndex, index);
		}
	}

	/**
	 * Add hover styling to the given element.
	 * 
	 * @param context context of controller
	 * @param chart chart chart instance
	 * @param object element to be set.
	 * @param datasetIndex dataset index
	 * @param index data index
	 */
	void onSetHoverStyle(ControllerContext context, IsChart chart, NativeObject object, int datasetIndex, int index) {
		// if consistent, calls controller
		if (Controller.isConsistent(delegation, context, chart)) {
			delegation.setHoverStyle(context, chart, new StyleElement(object), datasetIndex, index);
		}
	}

	/**
	 * Update the elements in response to new data.
	 * 
	 * @param context context of controller
	 * @param chart chart chart instance
	 * @param mode update mode, core calls this method using any of 'active', 'hide', 'reset', 'resize', 'show' or undefined
	 */
	void onUpdate(ControllerContext context, IsChart chart, String reset) {
		// if consistent, calls controller
		if (Controller.isConsistent(delegation, context, chart)) {
			delegation.update(context, chart, reset);
		}
	}

	/**
	 * Wraps the protected method to get the java script object.
	 * 
	 * @return the java script object.
	 */
	NativeObject nativeObject() {
		return super.getNativeObject();
	}

}
