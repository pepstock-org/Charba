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

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.Chart;
import org.pepstock.charba.client.Charts;
import org.pepstock.charba.client.Controller;
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
	 * @author Andrea "Stock" Stocchero @
	 */
	@JsFunction
	interface ProxyInitializeCallback {
		/**
		 * Initializes the controller.
		 * 
		 * @param context java script <code>this</code> function context.
		 * @param chart CHART.JS chart instance
		 * @param datasetIndex dataset index
		 */
		void call(Context context, Chart chart, int datasetIndex);
	}

	/**
	 * Java script FUNCTION callback called to catch the chart add elements.
	 * 
	 * @author Andrea "Stock" Stocchero @
	 */
	@JsFunction
	interface ProxyAddElementsCallback {

		/**
		 * Create elements for each piece of data in the dataset.<br>
		 * Store elements in an array on the dataset as dataset.metaData.
		 * 
		 * @param context java script <code>this</code> function context.
		 */
		void call(Context context);
	}

	/**
	 * Java script FUNCTION callback called to catch the chart add element and reset.
	 * 
	 * @author Andrea "Stock" Stocchero @
	 */
	@JsFunction
	interface ProxyAddElementAndResetCallback {

		/**
		 * Create a single element for the data at the given index and reset its state.
		 * 
		 * @param context java script <code>this</code> function context.
		 * @param index dataset index
		 */
		void call(Context context, int index);
	}

	/**
	 * Java script FUNCTION callback called to catch the chart drawing.
	 * 
	 * @author Andrea "Stock" Stocchero @
	 */
	@JsFunction
	interface ProxyDrawCallback {

		/**
		 * Draw the representation of the dataset.
		 * 
		 * @param context java script <code>this</code> function context.
		 * @param ease if specified, this number represents how far to transition elements.
		 */
		void call(Context context, double ease);
	}

	/**
	 * Java script FUNCTION callback called to catch the chart remove hover.
	 * 
	 * @author Andrea "Stock" Stocchero @
	 */
	@JsFunction
	interface ProxyRemoveHoverStyleCallback {

		/**
		 * Remove hover styling from the given element.
		 * 
		 * @param context java script <code>this</code> function context.
		 * @param element element to be removed.
		 */
		void call(Context context, NativeObject element);
	}

	/**
	 * Java script FUNCTION callback called to catch the chart set hover.
	 * 
	 * @author Andrea "Stock" Stocchero @
	 */
	@JsFunction
	interface ProxySetHoverStyleCallback {

		/**
		 * Add hover styling to the given element.
		 * 
		 * @param context java script <code>this</code> function context.
		 * @param element element to be set.
		 */
		void call(Context context, NativeObject element);
	}

	/**
	 * Java script FUNCTION callback called to catch the chart updating.
	 * 
	 * @author Andrea "Stock" Stocchero @
	 */
	@JsFunction
	interface ProxyUpdateCallback {

		/**
		 * Update the elements in response to new data.
		 * 
		 * @param context java script <code>this</code> function context.
		 * @param reset if true, put the elements into a reset state so they can animate to their final values
		 */
		void call(Context context, boolean reset);
	}

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the initialize function
	private final CallbackProxy<ProxyInitializeCallback> initializeCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the add elements function
	private final CallbackProxy<ProxyAddElementsCallback> addElementsCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the add element and reset function
	private final CallbackProxy<ProxyAddElementAndResetCallback> addElementAndResetCallbackProxy = JsHelper.get().newCallbackProxy();
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
		type,
		initialize,
		addElements,
		addElementAndReset,
		draw,
		removeHoverStyle,
		setHoverStyle,
		update
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
		setValue(Property.type, delegation.getType());
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		initializeCallbackProxy.setCallback(new ProxyInitializeCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.controllers.WrapperController.ProxyInitializeCallback#call(org.pepstock.
			 * charba.client.controllers.Context, org.pepstock.charba.client.Chart, int)
			 */
			@Override
			public void call(Context context, Chart chart, int datasetIndex) {
				// adds the chart instance to the context
				// PAY ATTENTION: this is mandatory
				context.setNativeChart(chart);
				// invoke user method implementation
				onInitialize(context, chart.getCharbaId(), datasetIndex);
			}

		});
		addElementsCallbackProxy.setCallback(new ProxyAddElementsCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.controllers.WrapperController.ProxyAddElementsCallback#call(org.pepstock.
			 * charba.client.controllers.Context)
			 */
			@Override
			public void call(Context context) {
				// invoke user method implementation
				onAddElements(context, context.getCharbaId());
			}

		});
		addElementAndResetCallbackProxy.setCallback(new ProxyAddElementAndResetCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.controllers.WrapperController.ProxyAddElementAndResetCallback#call(org.
			 * pepstock.charba.client.controllers.Context, int)
			 */
			@Override
			public void call(Context context, int index) {
				// invoke user method implementation
				onAddElementAndReset(context, context.getCharbaId(), index);
			}

		});
		drawCallbackProxy.setCallback(new ProxyDrawCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.controllers.WrapperController.ProxyDrawCallback#call(org.pepstock.charba.
			 * client.controllers.Context, double)
			 */
			@Override
			public void call(Context context, double ease) {
				// invoke user method implementation
				onDraw(context, context.getCharbaId(), ease);
			}

		});
		removeHoverStyleCallbackProxy.setCallback(new ProxyRemoveHoverStyleCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.controllers.WrapperController.ProxyRemoveHoverStyleCallback#call(org.
			 * pepstock.charba.client.controllers.Context, org.pepstock.charba.client.commons.NativeObject)
			 */
			@Override
			public void call(Context context, NativeObject element) {
				// invoke user method implementation
				onRemoveHoverStyle(context, context.getCharbaId(), element);
			}

		});
		setHoverStyleCallbackProxy.setCallback(new ProxySetHoverStyleCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.controllers.WrapperController.ProxySetHoverStyleCallback#call(org.pepstock.
			 * charba.client.controllers.Context, org.pepstock.charba.client.commons.NativeObject)
			 */
			@Override
			public void call(Context context, NativeObject element) {
				// invoke user method implementation
				onSetHoverStyle(context, context.getCharbaId(), element);
			}

		});
		updateCallbackProxy.setCallback(new ProxyUpdateCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.controllers.WrapperController.ProxyUpdateCallback#call(org.pepstock.charba.
			 * client.controllers.Context, boolean)
			 */
			@Override
			public void call(Context context, boolean reset) {
				// invoke user method implementation
				onUpdate(context, context.getCharbaId(), reset);
			}

		});
		// adds all proxy functions to call the functions to the native object
		setValue(Property.initialize, initializeCallbackProxy.getProxy());
		setValue(Property.addElements, addElementsCallbackProxy.getProxy());
		setValue(Property.addElementAndReset, addElementAndResetCallbackProxy.getProxy());
		setValue(Property.draw, drawCallbackProxy.getProxy());
		setValue(Property.removeHoverStyle, removeHoverStyleCallbackProxy.getProxy());
		setValue(Property.setHoverStyle, setHoverStyleCallbackProxy.getProxy());
		setValue(Property.update, updateCallbackProxy.getProxy());
	}

	/**
	 * Returns the chart instances which must be consumed by controller.
	 * 
	 * @param chartId chart id.
	 * @return the chart instance or <code>null</code> if not found.
	 */
	AbstractChart<?, ?> getChart(String chartId) {
		return Charts.get(chartId);
	}

	/**
	 * Returns the controller type.
	 * 
	 * @return the controller type.
	 */
	final Type getType() {
		return delegation.getType();
	}

	/**
	 * Initializes the controller.
	 * 
	 * @param context context of controller
	 * @param chartId chartId chart id.
	 * @param datasetIndex dataset index
	 */
	protected void onInitialize(Context context, String chartId, int datasetIndex) {
		// gets chart instance
		AbstractChart<?, ?> chart = getChart(chartId);
		// if consistent, calls controller
		if (chart != null) {
			delegation.initialize(context, chart, datasetIndex);
		}
	}

	/**
	 * Create elements for each piece of data in the dataset. Store elements in an array on the dataset.
	 * 
	 * @param context context of controller
	 * @param chartId chartId chart id.
	 */
	protected void onAddElements(Context context, String chartId) {
		// gets chart instance
		AbstractChart<?, ?> chart = getChart(chartId);
		// if consistent, calls controller
		if (chart != null) {
			delegation.addElements(context, chart);
		}
	}

	/**
	 * Create a single element for the data at the given index and reset its state.
	 * 
	 * @param context context of controller
	 * @param chartId chartId chart id.
	 * @param index dataset index
	 */
	protected void onAddElementAndReset(Context context, String chartId, int index) {
		// gets chart instance
		AbstractChart<?, ?> chart = getChart(chartId);
		// if consistent, calls controller
		if (chart != null) {
			delegation.addElementAndReset(context, chart, index);
		}
	}

	/**
	 * Draw the representation of the dataset.
	 * 
	 * @param context context of controller
	 * @param chartId chartId chart id.
	 * @param ease if specified, this number represents how far to transition elements.
	 */
	protected void onDraw(Context context, String chartId, double ease) {
		// gets chart instance
		AbstractChart<?, ?> chart = getChart(chartId);
		// if consistent, calls controller
		if (chart != null) {
			delegation.draw(context, chart, ease);
		}
	}

	/**
	 * Remove hover styling from the given element.
	 * 
	 * @param context context of controller
	 * @param chartId chartId chart id.
	 * @param object element to be removed.
	 */
	protected void onRemoveHoverStyle(Context context, String chartId, NativeObject object) {
		// gets chart instance
		AbstractChart<?, ?> chart = getChart(chartId);
		// if consistent, calls controller
		if (chart != null) {
			delegation.removeHoverStyle(context, chart, new StyleElement(object));
		}
	}

	/**
	 * Add hover styling to the given element.
	 * 
	 * @param context context of controller
	 * @param chartId chartId chart id.
	 * @param object element to be set.
	 */
	protected void onSetHoverStyle(Context context, String chartId, NativeObject object) {
		// gets chart instance
		AbstractChart<?, ?> chart = getChart(chartId);
		// if consistent, calls controller
		if (chart != null) {
			delegation.setHoverStyle(context, chart, new StyleElement(object));
		}
	}

	/**
	 * Update the elements in response to new data.
	 * 
	 * @param context context of controller
	 * @param chartId chartId chart id.
	 * @param reset if true, put the elements into a reset state so they can animate to their final values
	 */
	protected void onUpdate(Context context, String chartId, boolean reset) {
		// gets chart instance
		AbstractChart<?, ?> chart = getChart(chartId);
		// if consistent, calls controller
		if (chart != null) {
			delegation.update(context, chart, reset);
		}
	}

	/**
	 * Wraps the protected method to get the java script object.
	 * 
	 * @return the java script object.
	 */
	NativeObject getObject() {
		return super.getNativeObject();
	}

}
