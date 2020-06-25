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
package org.pepstock.charba.client.plugins;

import org.pepstock.charba.client.Chart;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.Plugin;
import org.pepstock.charba.client.callbacks.CallbackFunctionContext;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.items.DatasetPluginItem;
import org.pepstock.charba.client.items.EventPluginItem;
import org.pepstock.charba.client.items.SizeItem;
import org.pepstock.charba.client.items.TooltipPluginItem;

import jsinterop.annotations.JsFunction;

/**
 * Wraps a plugin, delegating the execution of all hooks to it.<br>
 * The wrapper is mandatory to able to catch all hooks of chart even if the plugin implements just a part of the hooks.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class WrapperPlugin extends NativeObjectContainer {

	// ---------------------------
	// -- JAVASCRIPT FUNCTIONS ---
	// ---------------------------
	/**
	 * Java script FUNCTION callback called before and after initialization.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyInitCallback {

		/**
		 * Called after 'chart' has been initialized and before and after the first update.
		 * 
		 * @param context context value of <code>this</code> to the execution context of function.
		 * @param chart The chart instance.
		 * @param options plugin options set by user into chart options.
		 */
		void call(CallbackFunctionContext context, Chart chart, NativeObject options);
	}

	/**
	 * Java script FUNCTION callback called before chart rendering.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyBeforeUpdateCallback {

		/**
		 * Called before updating 'chart'.<br>
		 * If any plugin returns <code>false</code>, the update is cancelled (and thus subsequent render(s)) until another 'update' is triggered.
		 * 
		 * @param context context value of <code>this</code> to the execution context of function.
		 * @param chart The chart instance.
		 * @param options plugin options set by user into chart options.
		 * @return <code>false</code> to cancel the chart update.
		 */
		boolean call(CallbackFunctionContext context, Chart chart, NativeObject options);
	}

	/**
	 * Java script FUNCTION callback called after chart rendering.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyAfterUpdateCallback {

		/**
		 * Called after 'chart' has been updated and before rendering. Note that this hook will not be called if the chart update has been previously cancelled.
		 * 
		 * @param context context value of <code>this</code> to the execution context of function.
		 * @param chart The chart instance.
		 * @param options plugin options set by user into chart options.
		 */
		void call(CallbackFunctionContext context, Chart chart, NativeObject options);
	}

	/**
	 * Java script FUNCTION callback called before laying out 'chart'.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyBeforeLayoutCallback {

		/**
		 * Called before laying out 'chart'.<br>
		 * If any plugin returns <code>false</code>, the layout update is cancelled until another 'update' is triggered.
		 * 
		 * @param context context value of <code>this</code> to the execution context of function.
		 * @param chart The chart instance.
		 * @param options plugin options set by user into chart options.
		 * @return <code>false</code> to cancel the chart layout.
		 */
		boolean call(CallbackFunctionContext context, Chart chart, NativeObject options);
	}

	/**
	 * Java script FUNCTION callback called after laying out 'chart'.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyAfterLayoutCallback {

		/**
		 * Called after the 'chart' has been layed out.<br>
		 * Note that this hook will not be called if the layout update has been previously cancelled.
		 * 
		 * @param context context value of <code>this</code> to the execution context of function.
		 * @param chart The chart instance.
		 * @param options plugin options set by user into chart options.
		 */
		void call(CallbackFunctionContext context, Chart chart, NativeObject options);
	}

	/**
	 * Java script FUNCTION callback called before updating the 'chart' datasets.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyBeforeDatasetsUpdateCallback {

		/**
		 * Called before updating the 'chart' datasets.<br>
		 * If any plugin returns <code>false</code>, the datasets update is cancelled until another 'update' is triggered.
		 * 
		 * @param context context value of <code>this</code> to the execution context of function.
		 * @param chart The chart instance.
		 * @param options plugin options set by user into chart options.
		 * @return <code>false</code> to cancel the datasets update.
		 */
		boolean call(CallbackFunctionContext context, Chart chart, NativeObject options);
	}

	/**
	 * Java script FUNCTION callback called after updating the 'chart' datasets.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyAfterDatasetsUpdateCallback {

		/**
		 * Called after the 'chart' datasets have been updated.<br>
		 * Note that this hook will not be called if the datasets update has been previously cancelled.
		 * 
		 * @param context context value of <code>this</code> to the execution context of function.
		 * @param chart The chart instance.
		 * @param options plugin options set by user into chart options.
		 */
		void call(CallbackFunctionContext context, Chart chart, NativeObject options);
	}

	/**
	 * Java script FUNCTION callback called before updating the 'chart' dataset at the given 'args.index'.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyBeforeDatasetUpdateCallback {

		/**
		 * Called before updating the 'chart' dataset at the given 'args.index'.<br>
		 * If any plugin returns <code>false</code>, the datasets update is cancelled until another 'update' is triggered.
		 * 
		 * @param context context value of <code>this</code> to the execution context of function.
		 * @param chart The chart instance.
		 * @param item native item instance.
		 * @param options plugin options set by user into chart options.
		 * @return <code>false</code> to cancel the chart datasets drawing.
		 */
		boolean call(CallbackFunctionContext context, Chart chart, NativeObject item, NativeObject options);
	}

	/**
	 * Java script FUNCTION callback called after updating the 'chart' dataset at the given 'args.index'.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyAfterDatasetUpdateCallback {

		/**
		 * Called after the 'chart' datasets at the given 'args.index' has been updated.<br>
		 * Note that this hook will not be called if the datasets update has been previously cancelled.
		 * 
		 * @param context context value of <code>this</code> to the execution context of function.
		 * @param chart The chart instance.
		 * @param item native item instance.
		 * @param options plugin options set by user into chart options.
		 */
		void call(CallbackFunctionContext context, Chart chart, NativeObject item, NativeObject options);
	}

	/**
	 * Java script FUNCTION callback called before rendering 'chart'.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyBeforeRenderCallback {

		/**
		 * Called before rendering 'chart'.<br>
		 * If any plugin returns <code>false</code>, the rendering is cancelled until another 'render' is triggered.
		 * 
		 * @param context context value of <code>this</code> to the execution context of function.
		 * @param chart The chart instance.
		 * @param options plugin options set by user into chart options.
		 * @return <code>false</code> to cancel the chart rendering.
		 */
		boolean call(CallbackFunctionContext context, Chart chart, NativeObject options);
	}

	/**
	 * Java script FUNCTION callback called after rendering 'chart'.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyAfterRenderCallback {

		/**
		 * Called after the 'chart' has been fully rendered (and animation completed).<br>
		 * Note that this hook will not be called if the rendering has been previously cancelled.
		 * 
		 * @param context context value of <code>this</code> to the execution context of function.
		 * @param chart The chart instance.
		 * @param options plugin options set by user into chart options.
		 */
		void call(CallbackFunctionContext context, Chart chart, NativeObject options);
	}

	/**
	 * Java script FUNCTION callback called before drawing 'chart'.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyBeforeDrawCallback {

		/**
		 * Called before drawing 'chart' at every animation frame specified by the given easing value.<br>
		 * If any plugin returns <code>false</code>, the frame drawing is cancelled until another 'render' is triggered.
		 * 
		 * @param context context value of <code>this</code> to the execution context of function.
		 * @param chart The chart instance.
		 * @param easing The current animation value, between 0.0 and 1.0.
		 * @param options plugin options set by user into chart options.
		 * @return <code>false</code> to cancel the chart drawing.
		 */
		boolean call(CallbackFunctionContext context, Chart chart, double easing, NativeObject options);
	}

	/**
	 * Java script FUNCTION callback called after drawing 'chart'.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyAfterDrawCallback {

		/**
		 * Called after the 'chart' has been drawn for the specific easing value.<br>
		 * Note that this hook will not be called if the drawing has been previously cancelled.
		 * 
		 * @param context context value of <code>this</code> to the execution context of function.
		 * @param chart The chart instance.
		 * @param easing The current animation value, between 0.0 and 1.0.
		 * @param options plugin options set by user into chart options.
		 */
		void call(CallbackFunctionContext context, Chart chart, double easing, NativeObject options);
	}

	/**
	 * Java script FUNCTION callback called before drawing 'chart' datasets.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyBeforeDatasetsDrawCallback {

		/**
		 * Called before drawing the 'chart' datasets.<br>
		 * If any plugin returns <code>false</code>, the datasets drawing is cancelled until another 'render' is triggered.
		 * 
		 * @param context context value of <code>this</code> to the execution context of function.
		 * @param chart The chart instance.
		 * @param easing The current animation value, between 0.0 and 1.0.
		 * @param options plugin options set by user into chart options.
		 * @return <code>false</code> to cancel the chart datasets drawing.
		 */
		boolean call(CallbackFunctionContext context, Chart chart, double easing, NativeObject options);
	}

	/**
	 * Java script FUNCTION callback called after drawing 'chart' datasets.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyAfterDatasetsDrawCallback {

		/**
		 * Called after the 'chart' datasets have been drawn. Note that this hook will not be called if the datasets drawing has been previously cancelled.
		 * 
		 * @param context context value of <code>this</code> to the execution context of function.
		 * @param chart The chart instance.
		 * @param easing The current animation value, between 0.0 and 1.0.
		 * @param options plugin options set by user into chart options.
		 */
		void call(CallbackFunctionContext context, Chart chart, double easing, NativeObject options);
	}

	/**
	 * Java script FUNCTION callback called before drawing 'chart' dataset at the given 'args.index'.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyBeforeDatasetDrawCallback {

		/**
		 * Called before drawing the 'chart' dataset at the given 'args.index' (datasets are drawn in the reverse order).<br>
		 * If any plugin returns <code>false</code>, the datasets drawing is cancelled until another 'render' is triggered.
		 * 
		 * @param context context value of <code>this</code> to the execution context of function.
		 * @param chart The chart instance.
		 * @param item native item instance.
		 * @param options plugin options set by user into chart options.
		 * @return <code>false</code> to cancel the chart datasets drawing.
		 */
		boolean call(CallbackFunctionContext context, Chart chart, NativeObject item, NativeObject options);
	}

	/**
	 * Java script FUNCTION callback called after drawing 'chart' dataset at the given 'args.index'.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyAfterDatasetDrawCallback {

		/**
		 * Called after the 'chart' datasets at the given 'args.index' have been drawn (datasets are drawn in the reverse order).<br>
		 * Note that this hook will not be called if the datasets drawing has been previously cancelled.
		 * 
		 * @param context context value of <code>this</code> to the execution context of function.
		 * @param chart The chart instance.
		 * @param item native item instance.
		 * @param options plugin options set by user into chart options.
		 */
		void call(CallbackFunctionContext context, Chart chart, NativeObject item, Object options);
	}

	/**
	 * Java script FUNCTION callback called before drawing the 'tooltip'.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyBeforeTooltipDrawCallback {

		/**
		 * Called before drawing the 'tooltip'.<br>
		 * If any plugin returns <code>false</code>, the tooltip drawing is cancelled until another 'render' is triggered.
		 * 
		 * @param context context value of <code>this</code> to the execution context of function.
		 * @param chart The chart instance.
		 * @param item native item instance.
		 * @param options plugin options set by user into chart options.
		 * @return <code>false</code> to cancel the chart tooltip drawing.
		 */
		boolean call(CallbackFunctionContext context, Chart chart, NativeObject item, NativeObject options);
	}

	/**
	 * Java script FUNCTION callback called after drawing the 'tooltip'.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyAfterTooltipDrawCallback {

		/**
		 * Called after drawing the 'tooltip'.<br>
		 * Note that this hook will not be called if the tooltip drawing has been previously cancelled.
		 * 
		 * @param context context value of <code>this</code> to the execution context of function.
		 * @param chart The chart instance.
		 * @param item native item instance.
		 * @param options plugin options set by user into chart options.
		 */
		void call(CallbackFunctionContext context, Chart chart, NativeObject item, NativeObject options);
	}

	/**
	 * Java script FUNCTION callback called before processing the specified 'event'.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyBeforeEventCallback {

		/**
		 * Called before processing the specified 'event'.<br>
		 * If any plugin returns <code>false</code>, the event will be discarded.
		 * 
		 * @param context context value of <code>this</code> to the execution context of function.
		 * @param chart The chart instance.
		 * @param item native item instance.
		 * @param options plugin options set by user into chart options.
		 * @return <code>false</code> to discard the event.
		 */
		boolean call(CallbackFunctionContext context, Chart chart, NativeObject item, NativeObject options);
	}

	/**
	 * Java script FUNCTION callback called after processing the specified 'event'.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyAfterEventCallback {

		/**
		 * Called after the 'event' has been consumed. Note that this hook will not be called if the 'event' has been previously discarded.
		 * 
		 * @param context context value of <code>this</code> to the execution context of function.
		 * @param chart The chart instance.
		 * @param item native item instance.
		 * @param options plugin options set by user into chart options.
		 */
		void call(CallbackFunctionContext context, Chart chart, NativeObject item, NativeObject options);
	}

	/**
	 * Java script FUNCTION callback called after the chart as been resized.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyResizeCallback {

		/**
		 * Called after the chart as been resized.
		 * 
		 * @param context context value of <code>this</code> to the execution context of function.
		 * @param chart The chart instance.
		 * @param size The new canvas display size (eq. canvas.style width and height).
		 * @param options plugin options set by user into chart options.
		 */
		void call(CallbackFunctionContext context, Chart chart, NativeObject size, NativeObject options);
	}
	
	/**
	 * Java script FUNCTION callback called during chart reset.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyResetCallback {

		/**
		 * Called during chart reset.
		 * 
		 * @param context context value of <code>this</code> to the execution context of function.
		 * @param chart The chart instance.
		 * @param options plugin options set by user into chart options.
		 */
		void call(CallbackFunctionContext context, Chart chart, NativeObject options);
	}

	/**
	 * Java script FUNCTION callback called after the chart as been resized.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyDestroyCallback {

		/**
		 * Called after the chart as been destroyed.
		 * 
		 * @param context context value of <code>this</code> to the execution context of function.
		 * @param chart The chart instance.
		 * @param options plugin options set by user into chart options.
		 */
		void call(CallbackFunctionContext context, Chart chart, NativeObject options);
	}

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		ID("id"),
		AFTER_DATASET_DRAW("afterDatasetDraw"),
		AFTER_DATASET_UPDATE("afterDatasetUpdate"),
		AFTER_DATASETS_DRAW("afterDatasetsDraw"),
		AFTER_DATASETS_UPDATE("afterDatasetsUpdate"),
		AFTER_DRAW("afterDraw"),
		AFTER_EVENT("afterEvent"),
		AFTER_INIT("afterInit"),
		AFTER_LAYOUT("afterLayout"),
		AFTER_RENDER("afterRender"),
		AFTER_TOOLTIP_DRAW("afterTooltipDraw"),
		AFTER_UPDATE("afterUpdate"),
		BEFORE_DATASET_DRAW("beforeDatasetDraw"),
		BEFORE_DATASET_UPDATE("beforeDatasetUpdate"),
		BEFORE_DATASETS_DRAW("beforeDatasetsDraw"),
		BEFORE_DATASETS_UPDATE("beforeDatasetsUpdate"),
		BEFORE_DRAW("beforeDraw"),
		BEFORE_EVENT("beforeEvent"),
		BEFORE_INIT("beforeInit"),
		BEFORE_LAYOUT("beforeLayout"),
		BEFORE_RENDER("beforeRender"),
		BEFORE_TOOLTIP_DRAW("beforeTooltipDraw"),
		BEFORE_UPDATE("beforeUpdate"),
		DESTROY("destroy"),
		RESET("reset"),
		RESIZE("resize");

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

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the afterDatasetDraw function
	private final CallbackProxy<ProxyAfterDatasetDrawCallback> afterDatasetDrawCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the afterDatasetUpdate function
	private final CallbackProxy<ProxyAfterDatasetUpdateCallback> afterDatasetUpdateCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the afterDatasetsDraw function
	private final CallbackProxy<ProxyAfterDatasetsDrawCallback> afterDatasetsDrawCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the afterDatasetsUpdate function
	private final CallbackProxy<ProxyAfterDatasetsUpdateCallback> afterDatasetsUpdateCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the afterDraw function
	private final CallbackProxy<ProxyAfterDrawCallback> afterDrawCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the afterEvent function
	private final CallbackProxy<ProxyAfterEventCallback> afterEventCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the afterInit function
	private final CallbackProxy<ProxyInitCallback> afterInitCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the afterLayout function
	private final CallbackProxy<ProxyAfterLayoutCallback> afterLayoutCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the afterRender function
	private final CallbackProxy<ProxyAfterRenderCallback> afterRenderCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the afterTooltipDraw function
	private final CallbackProxy<ProxyAfterTooltipDrawCallback> afterTooltipDrawCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the afterUpdate function
	private final CallbackProxy<ProxyAfterUpdateCallback> afterUpdateCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the beforeDatasetDraw function
	private final CallbackProxy<ProxyBeforeDatasetDrawCallback> beforeDatasetDrawCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the beforeDatasetUpdate function
	private final CallbackProxy<ProxyBeforeDatasetUpdateCallback> beforeDatasetUpdateCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the beforeDatasetsDraw function
	private final CallbackProxy<ProxyBeforeDatasetsDrawCallback> beforeDatasetsDrawCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the beforeDatasetsUpdate function
	private final CallbackProxy<ProxyBeforeDatasetsUpdateCallback> beforeDatasetsUpdateCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the beforeDraw function
	private final CallbackProxy<ProxyBeforeDrawCallback> beforeDrawCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the beforeEvent function
	private final CallbackProxy<ProxyBeforeEventCallback> beforeEventCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the beforeInit function
	private final CallbackProxy<ProxyInitCallback> beforeInitCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the beforeLayout function
	private final CallbackProxy<ProxyBeforeLayoutCallback> beforeLayoutCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the beforeRender function
	private final CallbackProxy<ProxyBeforeRenderCallback> beforeRenderCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the beforeTooltipDraw function
	private final CallbackProxy<ProxyBeforeTooltipDrawCallback> beforeTooltipDrawCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the beforeUpdate function
	private final CallbackProxy<ProxyBeforeUpdateCallback> beforeUpdateCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the destroy function
	private final CallbackProxy<ProxyDestroyCallback> destroyCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the resize function
	private final CallbackProxy<ProxyResizeCallback> resizeCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the reset function
	private final CallbackProxy<ProxyResetCallback> resetCallbackProxy = JsHelper.get().newCallbackProxy();

	// user plugin implementation
	private final Plugin delegation;

	/**
	 * Builds the object with plugin instance
	 * 
	 * @param delegation plugin instance
	 */
	WrapperPlugin(Plugin delegation) {
		// stores the plugin
		this.delegation = delegation;
		// sets the plugin ID
		setValue(Property.ID, delegation.getId());
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// invoke user method implementation
		afterDatasetDrawCallbackProxy.setCallback((context, chart, item, options) -> onAfterDatasetDraw(chart.getChart(), new DatasetPluginItem(item)));
		// invoke user method implementation
		afterDatasetUpdateCallbackProxy.setCallback((context, chart, item, options) -> onAfterDatasetUpdate(chart.getChart(), new DatasetPluginItem(item)));
		// invoke user method implementation
		afterDatasetsDrawCallbackProxy.setCallback((context, chart, easing, options) -> onAfterDatasetsDraw(chart.getChart(), easing));
		// invoke user method implementation
		afterDatasetsUpdateCallbackProxy.setCallback((context, chart, options) -> onAfterDatasetsUpdate(chart.getChart()));
		// invoke user method implementation
		afterDrawCallbackProxy.setCallback((context, chart, easing, options) -> onAfterDraw(chart.getChart(), easing));
		// invoke user method implementation
		afterEventCallbackProxy.setCallback((context, chart, item, options) -> onAfterEvent(chart.getChart(), new EventPluginItem(item)));
		// invoke user method implementation
		afterInitCallbackProxy.setCallback((context, chart, options) -> onAfterInit(chart.getChart(), chart));
		// invoke user method implementation
		afterLayoutCallbackProxy.setCallback((context, chart, options) -> onAfterLayout(chart.getChart()));
		// invoke user method implementation
		afterRenderCallbackProxy.setCallback((context, chart, options) -> onAfterRender(chart.getChart()));
		// invoke user method implementation
		afterTooltipDrawCallbackProxy.setCallback((context, chart, item, options) -> onAfterTooltipDraw(chart.getChart(), new TooltipPluginItem(item)));
		// invoke user method implementation
		afterUpdateCallbackProxy.setCallback((context, chart, options) -> onAfterUpdate(chart.getChart()));
		// invoke user method implementation
		beforeDatasetDrawCallbackProxy.setCallback((context, chart, item, options) -> onBeforeDatasetDraw(chart.getChart(), new DatasetPluginItem(item)));
		// invoke user method implementation
		beforeDatasetUpdateCallbackProxy.setCallback((context, chart, item, options) -> onBeforeDatasetUpdate(chart.getChart(), new DatasetPluginItem(item)));
		// invoke user method implementation
		beforeDatasetsDrawCallbackProxy.setCallback((context, chart, easing, options) -> onBeforeDatasetsDraw(chart.getChart(), easing));
		// invoke user method implementation
		beforeDatasetsUpdateCallbackProxy.setCallback((context, chart, options) -> onBeforeDatasetsUpdate(chart.getChart()));
		// invoke user method implementation
		beforeDrawCallbackProxy.setCallback((context, chart, easing, options) -> onBeforeDraw(chart.getChart(), easing));
		// invoke user method implementation
		beforeEventCallbackProxy.setCallback((context, chart, item, options) -> onBeforeEvent(chart.getChart(), new EventPluginItem(item)));
		// invoke user method implementation
		beforeInitCallbackProxy.setCallback((context, chart, options) -> onBeforeInit(chart.getChart()));
		// invoke user method implementation
		beforeLayoutCallbackProxy.setCallback((context, chart, options) -> onBeforeLayout(chart.getChart()));
		// invoke user method implementation
		beforeRenderCallbackProxy.setCallback((context, chart, options) -> onBeforeRender(chart.getChart()));
		// invoke user method implementation
		beforeTooltipDrawCallbackProxy.setCallback((context, chart, item, options) -> onBeforeTooltipDraw(chart.getChart(), new TooltipPluginItem(item)));
		// invoke user method implementation
		beforeUpdateCallbackProxy.setCallback((context, chart, options) -> onBeforeUpdate(chart.getChart()));
		// invoke user method implementation
		destroyCallbackProxy.setCallback((context, chart, options) -> onDestroy(chart.getChart()));
		// invoke user method implementation
		resizeCallbackProxy.setCallback((context, chart, size, options) -> onResize(chart.getChart(), new SizeItem(size)));
		// invoke user method implementation
		resetCallbackProxy.setCallback((context, chart, options) -> onReset(chart.getChart()));
		// ------------------------------------
		// -- SET ALL FUNCTIONS into object ---
		// ------------------------------------
		// sets proxy instance into afterDatasetDraw property
		setValue(Property.AFTER_DATASET_DRAW, afterDatasetDrawCallbackProxy.getProxy());
		// sets proxy instance into afterDatasetUpdate property
		setValue(Property.AFTER_DATASET_UPDATE, afterDatasetUpdateCallbackProxy.getProxy());
		// sets proxy instance into afterDatasetsDraw property
		setValue(Property.AFTER_DATASETS_DRAW, afterDatasetsDrawCallbackProxy.getProxy());
		// sets proxy instance into afterDatasetsUpdate property
		setValue(Property.AFTER_DATASETS_UPDATE, afterDatasetsUpdateCallbackProxy.getProxy());
		// sets proxy instance into afterDraw property
		setValue(Property.AFTER_DRAW, afterDrawCallbackProxy.getProxy());
		// sets proxy instance into afterEvent property
		setValue(Property.AFTER_EVENT, afterEventCallbackProxy.getProxy());
		// sets proxy instance into afterInit property
		setValue(Property.AFTER_INIT, afterInitCallbackProxy.getProxy());
		// sets proxy instance into afterLayout property
		setValue(Property.AFTER_LAYOUT, afterLayoutCallbackProxy.getProxy());
		// sets proxy instance into afterRender property
		setValue(Property.AFTER_RENDER, afterRenderCallbackProxy.getProxy());
		// sets proxy instance into afterTooltipDraw property
		setValue(Property.AFTER_TOOLTIP_DRAW, afterTooltipDrawCallbackProxy.getProxy());
		// sets proxy instance into afterUpdate property
		setValue(Property.AFTER_UPDATE, afterUpdateCallbackProxy.getProxy());
		// sets proxy instance into beforeDatasetDraw property
		setValue(Property.BEFORE_DATASET_DRAW, beforeDatasetDrawCallbackProxy.getProxy());
		// sets proxy instance into beforeDatasetUpdate property
		setValue(Property.BEFORE_DATASET_UPDATE, beforeDatasetUpdateCallbackProxy.getProxy());
		// sets proxy instance into beforeDatasetsDraw property
		setValue(Property.BEFORE_DATASETS_DRAW, beforeDatasetsDrawCallbackProxy.getProxy());
		// sets proxy instance into beforeDatasetsUpdate property
		setValue(Property.BEFORE_DATASETS_UPDATE, beforeDatasetsUpdateCallbackProxy.getProxy());
		// sets proxy instance into beforeDraw property
		setValue(Property.BEFORE_DRAW, beforeDrawCallbackProxy.getProxy());
		// sets proxy instance into beforeEvent property
		setValue(Property.BEFORE_EVENT, beforeEventCallbackProxy.getProxy());
		// sets proxy instance into beforeInit property
		setValue(Property.BEFORE_INIT, beforeInitCallbackProxy.getProxy());
		// sets proxy instance into beforeLayout property
		setValue(Property.BEFORE_LAYOUT, beforeLayoutCallbackProxy.getProxy());
		// sets proxy instance into beforeRender property
		setValue(Property.BEFORE_RENDER, beforeRenderCallbackProxy.getProxy());
		// sets proxy instance into beforeTooltipDraw property
		setValue(Property.BEFORE_TOOLTIP_DRAW, beforeTooltipDrawCallbackProxy.getProxy());
		// sets proxy instance into beforeUpdate property
		setValue(Property.BEFORE_UPDATE, beforeUpdateCallbackProxy.getProxy());
		// sets proxy instance into destroy property
		setValue(Property.DESTROY, destroyCallbackProxy.getProxy());
		// sets proxy instance into resize property
		setValue(Property.RESIZE, resizeCallbackProxy.getProxy());
		// sets proxy instance into reset property
		setValue(Property.RESET, resetCallbackProxy.getProxy());
	}

	/**
	 * Returns the plugin id.
	 * 
	 * @return the plugin id.
	 */
	String getId() {
		return delegation.getId();
	}

	/**
	 * Returns the native java script object.
	 * 
	 * @return the nativeObject
	 */
	NativeObject nativeObject() {
		return super.getNativeObject();
	}

	/**
	 * Called before creation of 'chart' java script.
	 * 
	 * @param chart chart instance.
	 */
	void onConfigure(IsChart chart) {
		// if consistent, calls plugin
		if (IsChart.isValid(chart)) {
			delegation.onConfigure(chart);
		}
	}

	/**
	 * Called before initializing 'chart'.
	 * 
	 * @param chart chart instance
	 */
	void onBeforeInit(IsChart chart) {
		// if consistent, calls plugin
		if (IsChart.isValid(chart)) {
			delegation.onBeforeInit(chart);
		}
	}

	/**
	 * Called after 'chart' has been initialized and before the first update.
	 * 
	 * @param chart chart instance
	 * @param nativeChart CHART.JS chart instance
	 */
	void onAfterInit(IsChart chart, Chart nativeChart) {
		// if consistent, calls plugin
		if (IsChart.isValid(chart)) {
			delegation.onAfterInit(chart, nativeChart);
		}
	}

	/**
	 * Called before updating 'chart'. If any plugin returns <code>false</code>, the update is cancelled (and thus subsequent render(s)) until another 'update' is triggered.
	 * 
	 * @param chart chart instance
	 * @return <code>false</code> to cancel the chart update.
	 */
	boolean onBeforeUpdate(IsChart chart) {
		// if consistent, calls plugin
		if (IsChart.isValid(chart)) {
			return delegation.onBeforeUpdate(chart);
		}
		return true;
	}

	/**
	 * Called after 'chart' has been updated and before rendering. Note that this hook will not be called if the chart update has been previously cancelled.
	 * 
	 * @param chart chart instance
	 */
	void onAfterUpdate(IsChart chart) {
		// if consistent, calls plugin
		if (IsChart.isValid(chart)) {
			delegation.onAfterUpdate(chart);
		}
	}

	/**
	 * Called before laying out 'chart'. If any plugin returns <code>false</code>, the layout update is cancelled until another 'update' is triggered.
	 * 
	 * @param chart chart instance
	 * @return <code>false</code> to cancel the chart layout.
	 */
	boolean onBeforeLayout(IsChart chart) {
		// if consistent
		if (IsChart.isValid(chart)) {
			// calls plugin
			return delegation.onBeforeLayout(chart);
		}
		return true;
	}

	/**
	 * Called after the 'chart' has been layed out. Note that this hook will not be called if the layout update has been previously cancelled.
	 * 
	 * @param chart chart instance
	 */
	void onAfterLayout(IsChart chart) {
		// if consistent, calls plugin
		if (IsChart.isValid(chart)) {
			delegation.onAfterLayout(chart);
		}
	}

	/**
	 * Called before updating the 'chart' datasets. If any plugin returns <code>false</code>, the datasets update is cancelled until another 'update' is triggered.
	 * 
	 * @param chart chart instance
	 * @return <code>false</code> to cancel the datasets update.
	 */
	boolean onBeforeDatasetsUpdate(IsChart chart) {
		// if consistent, calls plugin
		if (IsChart.isValid(chart)) {
			return delegation.onBeforeDatasetsUpdate(chart);
		}
		return true;
	}

	/**
	 * Called after the 'chart' datasets have been updated. Note that this hook will not be called if the datasets update has been previously cancelled.
	 * 
	 * @param chart chart instance
	 */
	void onAfterDatasetsUpdate(IsChart chart) {
		// if consistent, calls plugin
		if (IsChart.isValid(chart)) {
			delegation.onAfterDatasetsUpdate(chart);
		}
	}

	/**
	 * Called before updating the 'chart' dataset at the given 'args.index'. If any plugin returns <code>false</code>, the datasets update is cancelled until another 'update' is
	 * triggered.
	 * 
	 * @param chart chart instance
	 * @param item dataset item.
	 * @return <code>false</code> to cancel the chart datasets drawing.
	 */
	boolean onBeforeDatasetUpdate(IsChart chart, DatasetPluginItem item) {
		// if consistent, calls plugin
		if (IsChart.isValid(chart)) {
			return delegation.onBeforeDatasetUpdate(chart, item);
		}
		return true;
	}

	/**
	 * Called after the 'chart' datasets at the given 'args.index' has been updated. Note that this hook will not be called if the datasets update has been previously cancelled.
	 * 
	 * @param chart chart instance
	 * @param item dataset item.
	 */
	void onAfterDatasetUpdate(IsChart chart, DatasetPluginItem item) {
		// if consistent, calls plugin
		if (IsChart.isValid(chart)) {
			delegation.onAfterDatasetUpdate(chart, item);
		}
	}

	/**
	 * Called before rendering 'chart'. If any plugin returns <code>false</code>, the rendering is cancelled until another 'render' is triggered.
	 * 
	 * @param chart chart instance
	 * @return <code>false</code> to cancel the chart rendering.
	 */
	boolean onBeforeRender(IsChart chart) {
		// if consistent, calls plugin
		if (IsChart.isValid(chart)) {
			return delegation.onBeforeRender(chart);
		}
		return true;
	}

	/**
	 * Called after the 'chart' has been fully rendered (and animation completed). Note that this hook will not be called if the rendering has been previously cancelled.
	 * 
	 * @param chart chart instance
	 */
	void onAfterRender(IsChart chart) {
		// if consistent, calls plugin
		if (IsChart.isValid(chart)) {
			delegation.onAfterRender(chart);
		}
	}

	/**
	 * Called before drawing 'chart' at every animation frame specified by the given easing value. If any plugin returns <code>false</code>, the frame drawing is cancelled until
	 * another 'render' is triggered.
	 * 
	 * @param chart chart instance
	 * @param easing The current animation value, between 0.0 and 1.0.
	 * @return <code>false</code> to cancel the chart drawing.
	 */
	boolean onBeforeDraw(IsChart chart, double easing) {
		// if consistent, calls plugin
		if (IsChart.isValid(chart)) {
			return delegation.onBeforeDraw(chart, easing);
		}
		return true;
	}

	/**
	 * Called after the 'chart' has been drawn for the specific easing value. Note that this hook will not be called if the drawing has been previously cancelled.
	 * 
	 * @param chart chart instance
	 * @param easing The current animation value, between 0.0 and 1.0.
	 */
	void onAfterDraw(IsChart chart, double easing) {
		// if consistent, calls plugin
		if (IsChart.isValid(chart)) {
			delegation.onAfterDraw(chart, easing);
		}
	}

	/**
	 * Called before drawing the 'chart' datasets. If any plugin returns <code>false</code>, the datasets drawing is cancelled until another 'render' is triggered.
	 * 
	 * @param chart chart instance
	 * @param easing The current animation value, between 0.0 and 1.0.
	 * @return <code>false</code> to cancel the chart datasets drawing.
	 */
	boolean onBeforeDatasetsDraw(IsChart chart, double easing) {
		// if consistent, calls plugin
		if (IsChart.isValid(chart)) {
			return delegation.onBeforeDatasetsDraw(chart, easing);
		}
		return true;
	}

	/**
	 * Called after the 'chart' datasets have been drawn. Note that this hook will not be called if the datasets drawing has been previously cancelled.
	 * 
	 * @param chart chart instance
	 * @param easing The current animation value, between 0.0 and 1.0.
	 */
	void onAfterDatasetsDraw(IsChart chart, double easing) {
		// if consistent, calls plugin
		if (IsChart.isValid(chart)) {
			delegation.onAfterDatasetsDraw(chart, easing);
		}
	}

	/**
	 * Called before drawing the 'chart' dataset at the given 'args.index' (datasets are drawn in the reverse order). If any plugin returns <code>false</code>, the datasets drawing
	 * is cancelled until another 'render' is triggered.
	 * 
	 * @param chart chart instance
	 * @param item dataset item instance
	 * @return <code>false</code> to cancel the chart datasets drawing.
	 */
	boolean onBeforeDatasetDraw(IsChart chart, DatasetPluginItem item) {
		// if consistent, calls plugin
		if (IsChart.isValid(chart)) {
			return delegation.onBeforeDatasetDraw(chart, item);
		}
		return true;
	}

	/**
	 * Called after the 'chart' datasets at the given 'args.index' have been drawn (datasets are drawn in the reverse order). Note that this hook will not be called if the datasets
	 * drawing has been previously cancelled.
	 * 
	 * @param chart chart instance
	 * @param item dataset item instance
	 */
	void onAfterDatasetDraw(IsChart chart, DatasetPluginItem item) {
		// if consistent, calls plugin
		if (IsChart.isValid(chart)) {
			delegation.onAfterDatasetDraw(chart, item);
		}
	}

	/**
	 * Called before drawing the 'tooltip'. If any plugin returns <code>false</code>, the tooltip drawing is cancelled until another 'render' is triggered.
	 * 
	 * @param chart chart instance
	 * @param item tooltip item instance
	 * @return <code>false</code> to cancel the chart tooltip drawing.
	 */
	boolean onBeforeTooltipDraw(IsChart chart, TooltipPluginItem item) {
		// if consistent, calls plugin
		if (IsChart.isValid(chart)) {
			return delegation.onBeforeTooltipDraw(chart, item);
		}
		return true;
	}

	/**
	 * Called after drawing the 'tooltip'. Note that this hook will not be called if the tooltip drawing has been previously cancelled.
	 * 
	 * @param chart chart instance
	 * @param item tooltip item instance
	 */
	void onAfterTooltipDraw(IsChart chart, TooltipPluginItem item) {
		// if consistent, calls plugin
		if (IsChart.isValid(chart)) {
			delegation.onAfterTooltipDraw(chart, item);
		}
	}

	/**
	 * Called before processing the specified 'event'. If any plugin returns <code>false</code>, the event will be discarded.
	 * 
	 * @param chart chart instance
	 * @param item event item instance
	 * @return <code>false</code> to discard the event.
	 */
	boolean onBeforeEvent(IsChart chart, EventPluginItem item) {
		// if consistent, both chart and event, calls plugin
		if (IsChart.isValid(chart) && item.getEvent() != null) {
			return delegation.onBeforeEvent(chart, item.getEvent());
		}
		return true;
	}

	/**
	 * Called after the 'event' has been consumed. Note that this hook will not be called if the 'event' has been previously discarded.
	 * 
	 * @param chart chart instance
	 * @param item event item instance
	 */
	void onAfterEvent(IsChart chart, EventPluginItem item) {
		// if consistent, both chart and event, calls plugin
		if (IsChart.isValid(chart) && item.getEvent() != null) {
			delegation.onAfterEvent(chart, item.getEvent());
		}
	}

	/**
	 * Called after the chart as been resized.
	 * 
	 * @param chart chart instance
	 * @param item The new canvas display size (eq. canvas.style width and height).
	 */
	void onResize(IsChart chart, SizeItem item) {
		// if consistent, calls plugin
		if (IsChart.isValid(chart)) {
			delegation.onResize(chart, item);
		}
	}

	/**
	 * Called during chart reset.
	 * 
	 * @param chart chart instance
	 */
	void onReset(IsChart chart) {
		// if consistent, calls plugin
		if (IsChart.isValid(chart)) {
			delegation.onReset(chart);
		}
	}
	
	/**
	 * Called after the chart as been destroyed.
	 * 
	 * @param chart chart instance
	 */
	void onDestroy(IsChart chart) {
		// if consistent, calls plugin
		if (IsChart.isValid(chart)) {
			delegation.onDestroy(chart);
		}
	}

}
