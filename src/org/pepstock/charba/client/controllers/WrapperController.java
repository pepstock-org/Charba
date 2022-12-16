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
import org.pepstock.charba.client.options.TransitionKey;

import jsinterop.annotations.JsFunction;

/**
 * Wraps a controller, delegating the execution of all hooks to it.<br>
 * The wrapper is mandatory to enable catching all hooks of chart even if the controller implements just a part of the hooks.
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
	 * Java script FUNCTION callback called to catch the chart parse metadata.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyParseCallback {

		/**
		 * Create the meta data for data set.
		 * 
		 * @param context java script <code>this</code> function context.
		 * @param start start index of metadata
		 * @param count count of metadata
		 */
		void call(ControllerContext context, int start, int count);
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

	/**
	 * Java script FUNCTION callback called to catch the chart link scales.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyLinkScalesCallback {

		/**
		 * Ensures that the data set represented by this controller is linked to a scale.<br>
		 * Overridden to helpers.noop in the polar area and doughnut controllers as these chart types using a single scale.
		 * 
		 * @param context java script <code>this</code> function context.
		 */
		void call(ControllerContext context);
	}

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the initialize function
	private final CallbackProxy<ProxyInitializeCallback> initializeCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the parse function
	private final CallbackProxy<ProxyParseCallback> parseCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the draw function
	private final CallbackProxy<ProxyDrawCallback> drawCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the update function
	private final CallbackProxy<ProxyUpdateCallback> updateCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the linkScales function
	private final CallbackProxy<ProxyLinkScalesCallback> linkScalesCallbackProxy = JsHelper.get().newCallbackProxy();

	// user implementation of controller
	private final Controller delegation;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		TYPE("type"),
		INITIALIZE("initialize"),
		PARSE("parse"),
		DRAW("draw"),
		UPDATE("update"),
		LINK_SCALES("linkScales");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use in the native object.
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
		// invoke user method implementation
		this.initializeCallbackProxy.setIgnoreFunctionContext(false);
		this.initializeCallbackProxy.setCallback(context -> {
			// checks if context and native chart are consistent
			// checks if chart is consistent
			if (context != null && context.getNativeChart() != null && IsChart.isValid(context.getChart())) {
				// invoke user method implementation
				onInitialize(context, context.getChart());
			}
		});
		// invoke user method implementation
		this.parseCallbackProxy.setIgnoreFunctionContext(false);
		this.parseCallbackProxy.setCallback((context, start, count) -> onParse(context, context.getChart(), start, count));
		// invoke user method implementation
		this.drawCallbackProxy.setIgnoreFunctionContext(false);
		this.drawCallbackProxy.setCallback(context -> onDraw(context, context.getChart()));
		// invoke user method implementation
		this.updateCallbackProxy.setIgnoreFunctionContext(false);
		this.updateCallbackProxy.setCallback((context, mode) -> onUpdate(context, context.getChart(), mode));
		// invoke user method implementation
		this.linkScalesCallbackProxy.setIgnoreFunctionContext(false);
		this.linkScalesCallbackProxy.setCallback(context -> onLinkScales(context, context.getChart()));
		// adds all proxy functions to call the functions to the native object
		setValue(Property.INITIALIZE, initializeCallbackProxy.getProxy());
		setValue(Property.PARSE, parseCallbackProxy.getProxy());
		setValue(Property.DRAW, drawCallbackProxy.getProxy());
		setValue(Property.UPDATE, updateCallbackProxy.getProxy());
		setValue(Property.LINK_SCALES, linkScalesCallbackProxy.getProxy());
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
			// invokes on before
			delegation.onBeforeInitialize(context, chart);
			// invokes default
			JsControllerHelper.get().initialize(delegation.getType(), context);
			// invokes on after
			delegation.onAfterInitialize(context, chart);
		}
	}

	/**
	 * Create elements for each piece of data in the dataset. Store elements in an array on the dataset.
	 * 
	 * @param context context of controller
	 * @param chart chart chart instance
	 * @param start start index of metadata
	 * @param count count of metadata
	 */
	void onParse(ControllerContext context, IsChart chart, int start, int count) {
		// if consistent, calls controller
		if (Controller.isConsistent(delegation, context, chart)) {
			// invokes on before
			delegation.onBeforeParse(context, chart, start, count);
			// invokes default
			JsControllerHelper.get().parse(delegation.getType(), context, start, count);
			// invokes on after
			delegation.onAfterParse(context, chart, start, count);
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
			// invokes on before
			delegation.onBeforeDraw(context, chart);
			// invokes default
			JsControllerHelper.get().draw(delegation.getType(), context);
			// invokes on after
			delegation.onAfterDraw(context, chart);
		}
	}

	/**
	 * Update the elements in response to new data.
	 * 
	 * @param context context of controller
	 * @param chart chart chart instance
	 * @param mode update mode, core calls this method using any of 'active', 'hide', 'reset', 'resize', 'show' or undefined
	 */
	void onUpdate(ControllerContext context, IsChart chart, String mode) {
		// if consistent, calls controller
		if (Controller.isConsistent(delegation, context, chart)) {
			// checks if update mode is consistent
			if (mode == null || mode.length() == 0) {
				// invokes on before
				delegation.onBeforeUpdate(context, chart, null);
				// invokes default
				JsControllerHelper.get().update(delegation.getType(), context, null);
				// invokes on after
				delegation.onAfterUpdate(context, chart, null);
			} else {
				// otherwise creates a mode by the string
				// creates transition
				TransitionKey transition = TransitionKey.create(mode);
				// invokes on before
				delegation.onBeforeUpdate(context, chart, transition);
				// invokes default
				JsControllerHelper.get().update(delegation.getType(), context, transition.value());
				// invokes on after
				delegation.onAfterUpdate(context, chart, transition);
			}
		}
	}

	/**
	 * Ensures that the data set represented by this controller is linked to a scale.<br>
	 * Overridden to helpers.noop in the polar area and doughnut controllers as these chart types using a single scale.
	 * 
	 * @param context context of controller
	 * @param chart chart chart instance
	 */
	void onLinkScales(ControllerContext context, IsChart chart) {
		// if consistent, calls controller
		if (Controller.isConsistent(delegation, context, chart)) {
			// invokes on before
			delegation.onBeforeLinkScales(context, chart);
			// invokes default
			JsControllerHelper.get().linkScales(delegation.getType(), context);
			// invokes on after
			delegation.onAfterLinkScales(context, chart);
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