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
package org.pepstock.charba.client.jsinterop.controllers;

import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.jsinterop.AbstractChart;
import org.pepstock.charba.client.jsinterop.Chart;
import org.pepstock.charba.client.jsinterop.Charts;
import org.pepstock.charba.client.jsinterop.Controller;
import org.pepstock.charba.client.jsinterop.commons.CallbackProxy;
import org.pepstock.charba.client.jsinterop.commons.JsHelper;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.commons.NativeObjectContainer;

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
	 * 
	 * @author Andrea "Stock" Stocchero
	 * @version 2.0
	 */
	@JsFunction
	interface ProxyInitializeCallback {
		
		void call(Context context, Chart chart, int datasetIndex);
	}

	
	/**
	 * 
	 * @author Andrea "Stock" Stocchero
	 * @version 2.0
	 */
	@JsFunction
	interface ProxyAddElementsCallback {
		
		void call(Context context);
	}
	
	/**
	 * 
	 * @author Andrea "Stock" Stocchero
	 * @version 2.0
	 */
	@JsFunction
	interface ProxyAddElementAndResetCallback {
		
		void call(Context context, int index);
	}

	/**
	 * 
	 * @author Andrea "Stock" Stocchero
	 * @version 2.0
	 */
	@JsFunction
	interface ProxyDrawCallback {
		
		void call(Context context, double ease);
	}
	
	/**
	 * 
	 * @author Andrea "Stock" Stocchero
	 * @version 2.0
	 */
	@JsFunction
	interface ProxyRemoveHoverStyleCallback {
		
		void call(Context context, NativeObject element);
	}

	/**
	 * 
	 * @author Andrea "Stock" Stocchero
	 * @version 2.0
	 */
	@JsFunction
	interface ProxySetHoverStyleCallback {
		
		void call(Context context, NativeObject element);
	}

	/**
	 * 
	 * @author Andrea "Stock" Stocchero
	 * @version 2.0
	 */
	@JsFunction
	interface ProxyUpdateCallback {
		
		void call(Context context, boolean reset);
	}

	// ---------------------------
	// -- CALLBACKS PROXIES    ---
	// ---------------------------

	private final CallbackProxy<ProxyInitializeCallback> initializeCallbackProxy = JsHelper.get().newCallbackProxy();
	private final CallbackProxy<ProxyAddElementsCallback> addElementsCallbackProxy = JsHelper.get().newCallbackProxy();
	private final CallbackProxy<ProxyAddElementAndResetCallback> addElementAndResetCallbackProxy = JsHelper.get().newCallbackProxy();
	private final CallbackProxy<ProxyDrawCallback> drawCallbackProxy = JsHelper.get().newCallbackProxy();
	private final CallbackProxy<ProxyRemoveHoverStyleCallback> removeHoverStyleCallbackProxy = JsHelper.get().newCallbackProxy();
	private final CallbackProxy<ProxySetHoverStyleCallback> setHoverStyleCallbackProxy = JsHelper.get().newCallbackProxy();
	private final CallbackProxy<ProxyUpdateCallback> updateCallbackProxy = JsHelper.get().newCallbackProxy();

	private final Controller delegation;

	/**
	 * Name of fields of JavaScript object.
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

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.controllers.WrapperController.ProxyInitializeCallback#call(org.pepstock.charba.client.jsinterop.controllers.Context, org.pepstock.charba.client.jsinterop.Chart, int)
			 */
			@Override
			public void call(Context context, Chart chart, int datasetIndex) {
				onInitialize(context, chart.getCharbaId(), datasetIndex);
			}
			
		});
		addElementsCallbackProxy.setCallback(new ProxyAddElementsCallback() {
			
			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.controllers.WrapperController.ProxyAddElementsCallback#call(org.pepstock.charba.client.jsinterop.controllers.Context)
			 */
			@Override
			public void call(Context context) {
				onAddElements(context, context.getCharbaId());
			}
			
		});
		addElementAndResetCallbackProxy.setCallback(new ProxyAddElementAndResetCallback() {
			
			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.controllers.WrapperController.ProxyAddElementAndResetCallback#call(org.pepstock.charba.client.jsinterop.controllers.Context, int)
			 */
			@Override
			public void call(Context context, int index) {
				onAddElementAndReset(context, context.getCharbaId(), index);
			}

		});
		drawCallbackProxy.setCallback(new ProxyDrawCallback() {
			
			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.controllers.WrapperController.ProxyDrawCallback#call(org.pepstock.charba.client.jsinterop.controllers.Context, double)
			 */
			@Override
			public void call(Context context, double ease) {
				onDraw(context, context.getCharbaId(), ease);
			}
			
		});
		removeHoverStyleCallbackProxy.setCallback(new ProxyRemoveHoverStyleCallback() {
			
			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.controllers.WrapperController.ProxyRemoveHoverStyleCallback#call(org.pepstock.charba.client.jsinterop.controllers.Context, org.pepstock.charba.client.jsinterop.commons.NativeObject)
			 */
			@Override
			public void call(Context context, NativeObject element) {
				onRemoveHoverStyle(context, context.getCharbaId(), element);
			}

		});
		setHoverStyleCallbackProxy.setCallback(new ProxySetHoverStyleCallback() {
			
			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.controllers.WrapperController.ProxySetHoverStyleCallback#call(org.pepstock.charba.client.jsinterop.controllers.Context, org.pepstock.charba.client.jsinterop.commons.NativeObject)
			 */
			@Override
			public void call(Context context, NativeObject element) {
				onSetHoverStyle(context, context.getCharbaId(), element);
			}

		});
		updateCallbackProxy.setCallback(new ProxyUpdateCallback() {
			
			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.controllers.WrapperController.ProxyUpdateCallback#call(org.pepstock.charba.client.jsinterop.controllers.Context, boolean)
			 */
			@Override
			public void call(Context context, boolean reset) {
				onUpdate(context, context.getCharbaId(), reset);
			}

		});
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
