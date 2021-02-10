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

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.pepstock.charba.client.Chart;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.Plugin;
import org.pepstock.charba.client.callbacks.CallbackFunctionContext;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.items.PluginDatasetArgument;
import org.pepstock.charba.client.items.PluginEventArgument;
import org.pepstock.charba.client.items.PluginResizeArgument;
import org.pepstock.charba.client.items.PluginScaleArgument;
import org.pepstock.charba.client.items.PluginTooltipArgument;
import org.pepstock.charba.client.items.PluginUpdateArgument;

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
	 * Java script FUNCTION callback called without providing any value.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyWithoutReturnValueCallback {

		/**
		 * Called without providing any value.
		 * 
		 * @param context context value of <code>this</code> to the execution context of function.
		 * @param chart the chart instance.
		 * @param args argument object
		 * @param options plugin options set by user into chart options.
		 */
		void call(CallbackFunctionContext context, Chart chart, NativeObject args, NativeObject options);
	}

	/**
	 * Java script FUNCTION callback called with return value.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyWithReturnValueCallback {

		/**
		 * Called with return value.
		 * 
		 * @param context context value of <code>this</code> to the execution context of function.
		 * @param chart the chart instance
		 * @param args the call arguments
		 * @param options plugin options set by user into chart options
		 * @return <code>false</code> to cancel the chart operations
		 */
		boolean call(CallbackFunctionContext context, Chart chart, NativeObject args, NativeObject options);
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
		// ---------------------------
		// -- INIT
		// ---------------------------
		BEFORE_INIT("beforeInit"),
		AFTER_INIT("afterInit"),
		// ---------------------------
		// -- UPDATE
		// ---------------------------
		BEFORE_UPDATE("beforeUpdate"),
		AFTER_UPDATE("afterUpdate"),
		// ---------------------------
		// -- DATASETS UPDATE
		// ---------------------------
		BEFORE_DATASETS_UPDATE("beforeDatasetsUpdate"),
		AFTER_DATASETS_UPDATE("afterDatasetsUpdate"),
		// ---------------------------
		// -- DATASET UPDATE
		// ---------------------------
		BEFORE_DATASET_UPDATE("beforeDatasetUpdate"),
		AFTER_DATASET_UPDATE("afterDatasetUpdate"),
		// ---------------------------
		// -- ELEMENTS UPDATE
		// ---------------------------
		BEFORE_ELEMENTS_UPDATE("beforeElementsUpdate"),
		// ---------------------------
		// -- LAYOUT
		// ---------------------------
		BEFORE_LAYOUT("beforeLayout"),
		AFTER_LAYOUT("afterLayout"),
		// ---------------------------
		// -- RENDER
		// ---------------------------
		BEFORE_RENDER("beforeRender"),
		AFTER_RENDER("afterRender"),
		// ---------------------------
		// -- DRAW
		// ---------------------------
		BEFORE_DRAW("beforeDraw"),
		AFTER_DRAW("afterDraw"),
		// ---------------------------
		// -- DATASETS DRAW
		// ---------------------------
		BEFORE_DATASETS_DRAW("beforeDatasetsDraw"),
		AFTER_DATASETS_DRAW("afterDatasetsDraw"),
		// ---------------------------
		// -- DATASET DRAW
		// ---------------------------
		BEFORE_DATASET_DRAW("beforeDatasetDraw"),
		AFTER_DATASET_DRAW("afterDatasetDraw"),
		// ---------------------------
		// -- EVENT
		// ---------------------------
		BEFORE_EVENT("beforeEvent"),
		AFTER_EVENT("afterEvent"),
		// ---------------------------
		// -- TOOLTIP
		// ---------------------------
		BEFORE_TOOLTIP_DRAW("beforeTooltipDraw"),
		AFTER_TOOLTIP_DRAW("afterTooltipDraw"),
		// ---------------------------
		// -- RESET
		// ---------------------------
		RESET("reset"),
		// ---------------------------
		// -- RESIZE
		// ---------------------------
		RESIZE("resize"),
		// ---------------------------
		// -- DESTROY
		// ---------------------------
		DESTROY("destroy"),
		// ---------------------------
		// -- SCALES DATA LIMITS
		// ---------------------------
		BEFORE_DATA_LIMITS("beforeDataLimits"),
		AFTER_DATA_LIMITS("afterDataLimits"),
		// ---------------------------
		// -- SCALES BUILD TICKS
		// ---------------------------
		BEFORE_BUILD_TICKS("beforeBuildTicks"),
		AFTER_BUILD_TICKS("afterBuildTicks"),
		// ---------------------------
		// -- PLUGIN LIFECYCLE
		// ---------------------------
		INSTALL("install"),
		START("start"),
		STOP("stop"),
		UNINSTALL("uninstall");

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
	// -- CALLBACKS PROXIES
	// ---------------------------
	// ---------------------------
	// -- INIT
	// ---------------------------
	// callback proxy to invoke the beforeInit function
	private final CallbackProxy<ProxyWithoutReturnValueCallback> beforeInitCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the afterInit function
	private final CallbackProxy<ProxyWithoutReturnValueCallback> afterInitCallbackProxy = JsHelper.get().newCallbackProxy();
	// ---------------------------
	// -- UPDATE
	// ---------------------------
	// callback proxy to invoke the beforeUpdate function
	private final CallbackProxy<ProxyWithReturnValueCallback> beforeUpdateCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the afterUpdate function
	private final CallbackProxy<ProxyWithoutReturnValueCallback> afterUpdateCallbackProxy = JsHelper.get().newCallbackProxy();
	// ---------------------------
	// -- DATASETS UPDATE
	// ---------------------------
	// callback proxy to invoke the beforeDatasetsUpdate function
	private final CallbackProxy<ProxyWithReturnValueCallback> beforeDatasetsUpdateCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the afterDatasetsUpdate function
	private final CallbackProxy<ProxyWithoutReturnValueCallback> afterDatasetsUpdateCallbackProxy = JsHelper.get().newCallbackProxy();
	// ---------------------------
	// -- DATASET UPDATE
	// ---------------------------
	// callback proxy to invoke the beforeDatasetUpdate function
	private final CallbackProxy<ProxyWithReturnValueCallback> beforeDatasetUpdateCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the afterDatasetUpdate function
	private final CallbackProxy<ProxyWithoutReturnValueCallback> afterDatasetUpdateCallbackProxy = JsHelper.get().newCallbackProxy();
	// ---------------------------
	// -- ELEMENTS UPDATE
	// ---------------------------
	// callback proxy to invoke the beforeElementsUpdate function
	private final CallbackProxy<ProxyWithoutReturnValueCallback> beforeElementsUpdateCallbackProxy = JsHelper.get().newCallbackProxy();
	// ---------------------------
	// -- LAYOUT
	// ---------------------------
	// callback proxy to invoke the beforeLayout function
	private final CallbackProxy<ProxyWithReturnValueCallback> beforeLayoutCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the afterLayout function
	private final CallbackProxy<ProxyWithoutReturnValueCallback> afterLayoutCallbackProxy = JsHelper.get().newCallbackProxy();
	// ---------------------------
	// -- RENDER
	// ---------------------------
	// callback proxy to invoke the beforeRender function
	private final CallbackProxy<ProxyWithReturnValueCallback> beforeRenderCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the afterRender function
	private final CallbackProxy<ProxyWithoutReturnValueCallback> afterRenderCallbackProxy = JsHelper.get().newCallbackProxy();
	// ---------------------------
	// -- DRAW
	// ---------------------------
	// callback proxy to invoke the beforeDraw function
	private final CallbackProxy<ProxyWithReturnValueCallback> beforeDrawCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the afterDraw function
	private final CallbackProxy<ProxyWithoutReturnValueCallback> afterDrawCallbackProxy = JsHelper.get().newCallbackProxy();
	// ---------------------------
	// -- DATASETS DRAW
	// ---------------------------
	// callback proxy to invoke the beforeDatasetsDraw function
	private final CallbackProxy<ProxyWithReturnValueCallback> beforeDatasetsDrawCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the afterDatasetsDraw function
	private final CallbackProxy<ProxyWithoutReturnValueCallback> afterDatasetsDrawCallbackProxy = JsHelper.get().newCallbackProxy();
	// ---------------------------
	// -- DATASET DRAW
	// ---------------------------
	// callback proxy to invoke the beforeDatasetDraw function
	private final CallbackProxy<ProxyWithReturnValueCallback> beforeDatasetDrawCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the afterDatasetDraw function
	private final CallbackProxy<ProxyWithoutReturnValueCallback> afterDatasetDrawCallbackProxy = JsHelper.get().newCallbackProxy();
	// ---------------------------
	// -- EVENT
	// ---------------------------
	// callback proxy to invoke the beforeEvent function
	private final CallbackProxy<ProxyWithReturnValueCallback> beforeEventCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the afterEvent function
	private final CallbackProxy<ProxyWithoutReturnValueCallback> afterEventCallbackProxy = JsHelper.get().newCallbackProxy();
	// ---------------------------
	// -- TOOLTIP
	// ---------------------------
	// callback proxy to invoke the beforeTooltipDraw function
	private final CallbackProxy<ProxyWithReturnValueCallback> beforeTooltipDrawCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the afterTooltipDraw function
	private final CallbackProxy<ProxyWithoutReturnValueCallback> afterTooltipDrawCallbackProxy = JsHelper.get().newCallbackProxy();
	// ---------------------------
	// -- RESET
	// ---------------------------
	// callback proxy to invoke the reset function
	private final CallbackProxy<ProxyWithoutReturnValueCallback> resetCallbackProxy = JsHelper.get().newCallbackProxy();
	// ---------------------------
	// -- RESIZE
	// ---------------------------
	// callback proxy to invoke the resize function
	private final CallbackProxy<ProxyWithoutReturnValueCallback> resizeCallbackProxy = JsHelper.get().newCallbackProxy();
	// ---------------------------
	// -- DESTROY
	// ---------------------------
	// callback proxy to invoke the destroy function
	private final CallbackProxy<ProxyWithoutReturnValueCallback> destroyCallbackProxy = JsHelper.get().newCallbackProxy();
	// ---------------------------
	// -- SCALES DATA LIMITS
	// ---------------------------
	// callback proxy to invoke the beforeDataLimits function
	private final CallbackProxy<ProxyWithoutReturnValueCallback> beforeDataLimitsCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the afterDataLimits function
	private final CallbackProxy<ProxyWithoutReturnValueCallback> afterDataLimitsCallbackProxy = JsHelper.get().newCallbackProxy();
	// ---------------------------
	// -- SCALES BUILD TICKS
	// ---------------------------
	// callback proxy to invoke the beforeBuildTicks function
	private final CallbackProxy<ProxyWithoutReturnValueCallback> beforeBuildTicksCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the afterBuildTicks function
	private final CallbackProxy<ProxyWithoutReturnValueCallback> afterBuildTicksCallbackProxy = JsHelper.get().newCallbackProxy();
	// ---------------------------
	// -- PLUGIN LIFECYCLE
	// ---------------------------
	// callback proxy to invoke the install function
	private final CallbackProxy<ProxyWithoutReturnValueCallback> installCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the start function
	private final CallbackProxy<ProxyWithoutReturnValueCallback> startCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the stop function
	private final CallbackProxy<ProxyWithoutReturnValueCallback> stopCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the uninstall function
	private final CallbackProxy<ProxyWithoutReturnValueCallback> uninstallCallbackProxy = JsHelper.get().newCallbackProxy();

	// user plugin implementation
	private final Plugin delegation;
	// cache to store true during drawing for each chart
	// K = chart id, V = counter of update cycles
	private final Map<String, AtomicInteger> drawingStatus = new HashMap<>();

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
		// ---------------------------
		// -- INIT
		// ---------------------------
		// invoke user method implementation
		beforeInitCallbackProxy.setCallback((context, chart, args, options) -> onBeforeInit(chart.getChart()));
		// invoke user method implementation
		afterInitCallbackProxy.setCallback((context, chart, args, options) -> onAfterInit(chart.getChart(), chart));
		// ---------------------------
		// -- UPDATE
		// ---------------------------
		// invoke user method implementation
		afterUpdateCallbackProxy.setCallback((context, chart, args, options) -> onAfterUpdate(chart.getChart(), new PluginUpdateArgument(new PluginsEnvelop<>(args, true))));
		// invoke user method implementation
		beforeUpdateCallbackProxy.setCallback((context, chart, args, options) -> onBeforeUpdate(chart.getChart(), new PluginUpdateArgument(new PluginsEnvelop<>(args, true))));
		// ---------------------------
		// -- DATASETS UPDATE
		// ---------------------------
		// invoke user method implementation
		beforeDatasetsUpdateCallbackProxy.setCallback((context, chart, args, options) -> onBeforeDatasetsUpdate(chart.getChart(), new PluginUpdateArgument(new PluginsEnvelop<>(args, true))));
		// invoke user method implementation
		afterDatasetsUpdateCallbackProxy.setCallback((context, chart, args, options) -> onAfterDatasetsUpdate(chart.getChart(), new PluginUpdateArgument(new PluginsEnvelop<>(args, true))));
		// ---------------------------
		// -- DATASET UPDATE
		// ---------------------------
		// invoke user method implementation
		beforeDatasetUpdateCallbackProxy.setCallback((context, chart, args, options) -> onBeforeDatasetUpdate(chart.getChart(), new PluginDatasetArgument(new PluginsEnvelop<>(args, true))));
		// invoke user method implementation
		afterDatasetUpdateCallbackProxy.setCallback((context, chart, args, options) -> onAfterDatasetUpdate(chart.getChart(), new PluginDatasetArgument(new PluginsEnvelop<>(args, true))));
		// ---------------------------
		// -- ELEMENTS UPDATE
		// ---------------------------
		// invoke user method implementation
		beforeElementsUpdateCallbackProxy.setCallback((context, chart, args, options) -> onBeforeElementsUpdate(chart.getChart()));
		// ---------------------------
		// -- LAYOUT
		// ---------------------------
		// invoke user method implementation
		beforeLayoutCallbackProxy.setCallback((context, chart, args, options) -> onBeforeLayout(chart.getChart()));
		// invoke user method implementation
		afterLayoutCallbackProxy.setCallback((context, chart, args, options) -> onAfterLayout(chart.getChart()));
		// ---------------------------
		// -- RENDER
		// ---------------------------
		// invoke user method implementation
		beforeRenderCallbackProxy.setCallback((context, chart, args, options) -> onBeforeRender(chart.getChart()));
		// invoke user method implementation
		afterRenderCallbackProxy.setCallback((context, chart, args, options) -> onAfterRender(chart.getChart()));
		// ---------------------------
		// -- DRAW
		// ---------------------------
		// invoke user method implementation
		beforeDrawCallbackProxy.setCallback((context, chart, args, options) -> onBeforeDraw(chart.getChart()));
		// invoke user method implementation
		afterDrawCallbackProxy.setCallback((context, chart, args, options) -> onAfterDraw(chart.getChart()));
		// ---------------------------
		// -- DATASETS DRAW
		// ---------------------------
		// invoke user method implementation
		beforeDatasetsDrawCallbackProxy.setCallback((context, chart, args, options) -> onBeforeDatasetsDraw(chart.getChart()));
		// invoke user method implementation
		afterDatasetsDrawCallbackProxy.setCallback((context, chart, args, options) -> onAfterDatasetsDraw(chart.getChart()));
		// ---------------------------
		// -- DATASET DRAW
		// ---------------------------
		// invoke user method implementation
		beforeDatasetDrawCallbackProxy.setCallback((context, chart, args, options) -> onBeforeDatasetDraw(chart.getChart(), new PluginDatasetArgument(new PluginsEnvelop<>(args, true))));
		// invoke user method implementation
		afterDatasetDrawCallbackProxy.setCallback((context, chart, args, options) -> onAfterDatasetDraw(chart.getChart(), new PluginDatasetArgument(new PluginsEnvelop<>(args, true))));
		// ---------------------------
		// -- EVENT
		// ---------------------------
		// invoke user method implementation
		beforeEventCallbackProxy.setCallback((context, chart, args, options) -> onBeforeEvent(chart.getChart(), new PluginEventArgument(new PluginsEnvelop<>(args, true))));
		// invoke user method implementation
		afterEventCallbackProxy.setCallback((context, chart, args, options) -> onAfterEvent(chart.getChart(), new PluginEventArgument(new PluginsEnvelop<>(args, true))));
		// ---------------------------
		// -- TOOLTIP
		// ---------------------------
		// invoke user method implementation
		beforeTooltipDrawCallbackProxy.setCallback((context, chart, args, options) -> onBeforeTooltipDraw(chart.getChart(), new PluginTooltipArgument(new PluginsEnvelop<>(args, true))));
		// invoke user method implementation
		afterTooltipDrawCallbackProxy.setCallback((context, chart, args, options) -> onAfterTooltipDraw(chart.getChart(), new PluginTooltipArgument(new PluginsEnvelop<>(args, true))));
		// ---------------------------
		// -- RESET
		// ---------------------------
		// invoke user method implementation
		resetCallbackProxy.setCallback((context, chart, args, options) -> onReset(chart.getChart()));
		// ---------------------------
		// -- RESIZE
		// ---------------------------
		// invoke user method implementation
		resizeCallbackProxy.setCallback((context, chart, args, options) -> onResize(chart.getChart(), new PluginResizeArgument(new PluginsEnvelop<>(args, true))));
		// ---------------------------
		// -- DESTROY
		// ---------------------------
		// invoke user method implementation
		destroyCallbackProxy.setCallback((context, chart, args, options) -> onDestroy(chart.getChart()));
		// ---------------------------
		// -- PLUGIN LIFECYCLE
		// ---------------------------
		// invoke user method implementation
		installCallbackProxy.setCallback((context, chart, args, options) -> onInstall(chart.getChart()));
		// invoke user method implementation
		startCallbackProxy.setCallback((context, chart, args, options) -> onStart(chart.getChart()));
		// invoke user method implementation
		stopCallbackProxy.setCallback((context, chart, args, options) -> onStop(chart.getChart()));
		// invoke user method implementation
		uninstallCallbackProxy.setCallback((context, chart, args, options) -> onUninstall(chart.getChart()));
		// ---------------------------
		// -- SCALES DATA LIMITS
		// ---------------------------
		// invoke user method implementation
		beforeDataLimitsCallbackProxy.setCallback((context, chart, args, options) -> onBeforeDataLimits(chart.getChart(), new PluginScaleArgument(new PluginsEnvelop<>(args, true))));
		// invoke user method implementation
		afterDataLimitsCallbackProxy.setCallback((context, chart, args, options) -> onAfterDataLimits(chart.getChart(), new PluginScaleArgument(new PluginsEnvelop<>(args, true))));
		// ---------------------------
		// -- SCALES BUILD TICKS
		// ---------------------------
		// invoke user method implementation
		beforeBuildTicksCallbackProxy.setCallback((context, chart, args, options) -> onBeforeBuildTicks(chart.getChart(), new PluginScaleArgument(new PluginsEnvelop<>(args, true))));
		// invoke user method implementation
		afterBuildTicksCallbackProxy.setCallback((context, chart, args, options) -> onAfterBuildTicks(chart.getChart(), new PluginScaleArgument(new PluginsEnvelop<>(args, true))));
		// ------------------------------------
		// -- SET ALL FUNCTIONS into object ---
		// ------------------------------------
		// sets proxy instance into afterDataLimits property
		setValue(Property.AFTER_DATA_LIMITS, afterDataLimitsCallbackProxy.getProxy());
		// sets proxy instance into afterBuildTicks property
		setValue(Property.AFTER_BUILD_TICKS, afterBuildTicksCallbackProxy.getProxy());
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
		// sets proxy instance into beforeDataLimits property
		setValue(Property.BEFORE_DATA_LIMITS, beforeDataLimitsCallbackProxy.getProxy());
		// sets proxy instance into beforeBuildTicks property
		setValue(Property.BEFORE_BUILD_TICKS, beforeBuildTicksCallbackProxy.getProxy());
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
		// sets proxy instance into install property
		setValue(Property.INSTALL, installCallbackProxy.getProxy());
		// sets proxy instance into start property
		setValue(Property.START, startCallbackProxy.getProxy());
		// sets proxy instance into stop property
		setValue(Property.STOP, stopCallbackProxy.getProxy());
		// sets proxy instance into uninstall property
		setValue(Property.UNINSTALL, uninstallCallbackProxy.getProxy());
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

	// ----------------------------
	// -- INITIALIZATION ---
	// ----------------------------

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
			// stores the counter
			drawingStatus.put(chart.getId(), new AtomicInteger(0));
			// invokes plugin method
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

	// ----------------------------
	// -- UPDATE ---
	// ----------------------------

	/**
	 * Called before updating 'chart'.<br>
	 * If any plugin returns <code>false</code>, the update is cancelled (and thus subsequent render(s)) until another 'update' is triggered.
	 * 
	 * @param chart chart instance
	 * @param argument the argument passed for update
	 * @return <code>false</code> to cancel the chart update.
	 */
	boolean onBeforeUpdate(IsChart chart, PluginUpdateArgument argument) {
		// if consistent, calls plugin
		if (IsChart.isValid(chart)) {
			// gets the counter
			AtomicInteger counter = drawingStatus.get(chart.getId());
			// invokes begin drawing
			// if after increment the value is greater than1 means that this is the only current update
			delegation.onBeginDrawing(chart, counter.incrementAndGet() > 1);
			// invokes the before update, checking result for plugin status
			return checkAndGetBeforeContinue(chart, delegation.onBeforeUpdate(chart, argument));
		}
		return true;
	}

	/**
	 * Called after 'chart' has been updated and before rendering.<br>
	 * Note that this hook will not be called if the chart update has been previously cancelled.
	 * 
	 * @param chart chart instance
	 * @param argument the argument passed for update
	 */
	void onAfterUpdate(IsChart chart, PluginUpdateArgument argument) {
		// if consistent, calls plugin
		if (IsChart.isValid(chart)) {
			delegation.onAfterUpdate(chart, argument);
		}
	}

	/**
	 * Called during the update process, before any chart elements have been created.
	 * 
	 * @param chart chart instance
	 */
	void onBeforeElementsUpdate(IsChart chart) {
		// if consistent, calls plugin
		if (IsChart.isValid(chart)) {
			// invokes the reset of plugin
			delegation.onBeforeElementsUpdate(chart);
		}
	}

	/**
	 * Called before laying out 'chart'.<br>
	 * If any plugin returns <code>false</code>, the layout update is cancelled until another 'update' is triggered.
	 * 
	 * @param chart chart instance
	 * @return <code>false</code> to cancel the chart layout.
	 */
	boolean onBeforeLayout(IsChart chart) {
		// if consistent
		if (IsChart.isValid(chart)) {
			// invokes the call plugin, checking result for plugin status
			return checkAndGetBeforeContinue(chart, delegation.onBeforeLayout(chart));
		}
		return true;
	}

	/**
	 * Called after the 'chart' has been layed out.<br>
	 * Note that this hook will not be called if the layout update has been previously cancelled.
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
	 * Called before updating the 'chart' datasets.<br>
	 * If any plugin returns <code>false</code>, the datasets update is cancelled until another 'update' is triggered.
	 * 
	 * @param chart chart instance
	 * @param argument the argument passed for update
	 * @return <code>false</code> to cancel the datasets update.
	 */
	boolean onBeforeDatasetsUpdate(IsChart chart, PluginUpdateArgument argument) {
		// if consistent, calls plugin
		if (IsChart.isValid(chart)) {
			// invokes the call plugin, checking result for plugin status
			return checkAndGetBeforeContinue(chart, delegation.onBeforeDatasetsUpdate(chart, argument));
		}
		return true;
	}

	/**
	 * Called after the 'chart' datasets have been updated.<br>
	 * Note that this hook will not be called if the datasets update has been previously cancelled.
	 * 
	 * @param chart chart instance
	 * @param argument the argument passed for update
	 */
	void onAfterDatasetsUpdate(IsChart chart, PluginUpdateArgument argument) {
		// if consistent, calls plugin
		if (IsChart.isValid(chart)) {
			delegation.onAfterDatasetsUpdate(chart, argument);
		}
	}

	/**
	 * Called before updating the 'chart' dataset at the given 'args.index'.<br>
	 * If any plugin returns <code>false</code>, the datasets update is cancelled until another 'update' is triggered.
	 * 
	 * @param chart chart instance
	 * @param item dataset item.
	 * @return <code>false</code> to cancel the chart datasets drawing.
	 */
	boolean onBeforeDatasetUpdate(IsChart chart, PluginDatasetArgument item) {
		// if consistent, calls plugin
		if (IsChart.isValid(chart)) {
			// invokes the call plugin, checking result for plugin status
			return checkAndGetBeforeContinue(chart, delegation.onBeforeDatasetUpdate(chart, item));
		}
		return true;
	}

	/**
	 * Called after the 'chart' datasets at the given 'args.index' has been updated.<br>
	 * Note that this hook will not be called if the datasets update has been previously cancelled.
	 * 
	 * @param chart chart instance
	 * @param item dataset item.
	 */
	void onAfterDatasetUpdate(IsChart chart, PluginDatasetArgument item) {
		// if consistent, calls plugin
		if (IsChart.isValid(chart)) {
			delegation.onAfterDatasetUpdate(chart, item);
		}
	}

	// ----------------------------
	// -- RENDER ---
	// ----------------------------

	/**
	 * Called before rendering 'chart'.<br>
	 * If any plugin returns <code>false</code>, the rendering is cancelled until another 'render' is triggered.
	 * 
	 * @param chart chart instance
	 * @return <code>false</code> to cancel the chart rendering.
	 */
	boolean onBeforeRender(IsChart chart) {
		// if consistent, calls plugin
		if (IsChart.isValid(chart)) {
			// invokes the before render, checking result for plugin status
			return checkAndGetBeforeContinue(chart, delegation.onBeforeRender(chart));
		}
		return true;
	}

	/**
	 * Called after the 'chart' has been fully rendered (and animation completed).<br>
	 * Note that this hook will not be called if the rendering has been previously cancelled.
	 * 
	 * @param chart chart instance
	 */
	void onAfterRender(IsChart chart) {
		// if consistent, calls plugin
		if (IsChart.isValid(chart)) {
			// invokes after render
			delegation.onAfterRender(chart);
			// gets the counter
			AtomicInteger counter = drawingStatus.get(chart.getId());
			// checks if counter is consistent, greater than 1
			if (counter.intValue() > 0) {
				// invokes the end drawing
				delegation.onEndDrawing(chart);
			}
			// reset to 0
			counter.set(0);
		}
	}

	/**
	 * Called before drawing 'chart' at every animation frame.<br>
	 * If any plugin returns <code>false</code>, the frame drawing is cancelled until another 'render' is triggered.
	 * 
	 * @param chart chart instance
	 * @return <code>false</code> to cancel the chart drawing.
	 */
	boolean onBeforeDraw(IsChart chart) {
		// if consistent, calls plugin
		if (IsChart.isValid(chart)) {
			// invokes the call plugin, checking result for plugin status
			return checkAndGetBeforeContinue(chart, delegation.onBeforeDraw(chart));
		}
		return true;
	}

	/**
	 * Called after the 'chart' has been drawn.<br>
	 * Note that this hook will not be called if the drawing has been previously cancelled.
	 * 
	 * @param chart chart instance
	 */
	void onAfterDraw(IsChart chart) {
		// if consistent, calls plugin
		if (IsChart.isValid(chart)) {
			delegation.onAfterDraw(chart);
		}
	}

	/**
	 * Called before drawing the 'chart' datasets.<br>
	 * If any plugin returns <code>false</code>, the datasets drawing is cancelled until another 'render' is triggered.
	 * 
	 * @param chart chart instance
	 * @return <code>false</code> to cancel the chart datasets drawing.
	 */
	boolean onBeforeDatasetsDraw(IsChart chart) {
		// if consistent, calls plugin
		if (IsChart.isValid(chart)) {
			// invokes the call plugin, checking result for plugin status
			return checkAndGetBeforeContinue(chart, delegation.onBeforeDatasetsDraw(chart));
		}
		return true;
	}

	/**
	 * Called after the 'chart' datasets have been drawn.<br>
	 * Note that this hook will not be called if the datasets drawing has been previously cancelled.
	 * 
	 * @param chart chart instance
	 */
	void onAfterDatasetsDraw(IsChart chart) {
		// if consistent, calls plugin
		if (IsChart.isValid(chart)) {
			delegation.onAfterDatasetsDraw(chart);
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
	boolean onBeforeDatasetDraw(IsChart chart, PluginDatasetArgument item) {
		// if consistent, calls plugin
		if (IsChart.isValid(chart)) {
			// invokes the call plugin, checking result for plugin status
			return checkAndGetBeforeContinue(chart, delegation.onBeforeDatasetDraw(chart, item));
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
	void onAfterDatasetDraw(IsChart chart, PluginDatasetArgument item) {
		// if consistent, calls plugin
		if (IsChart.isValid(chart)) {
			delegation.onAfterDatasetDraw(chart, item);
		}
	}

	// ----------------------------
	// -- TOOLTIP ---
	// ----------------------------

	/**
	 * Called before drawing the 'tooltip'. If any plugin returns <code>false</code>, the tooltip drawing is cancelled until another 'render' is triggered.
	 * 
	 * @param chart chart instance
	 * @param item tooltip item instance
	 * @return <code>false</code> to cancel the chart tooltip drawing.
	 */
	boolean onBeforeTooltipDraw(IsChart chart, PluginTooltipArgument item) {
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
	void onAfterTooltipDraw(IsChart chart, PluginTooltipArgument item) {
		// if consistent, calls plugin
		if (IsChart.isValid(chart)) {
			delegation.onAfterTooltipDraw(chart, item);
		}
	}

	// ----------------------------
	// -- EVENT ---
	// ----------------------------

	/**
	 * Called before processing the specified 'event'. If any plugin returns <code>false</code>, the event will be discarded.
	 * 
	 * @param chart chart instance
	 * @param argument argument of event callback
	 * @return <code>false</code> to discard the event.
	 */
	boolean onBeforeEvent(IsChart chart, PluginEventArgument argument) {
		// if consistent, calls plugin
		if (IsChart.isValid(chart)) {
			return delegation.onBeforeEvent(chart, argument);
		}
		return true;
	}

	/**
	 * Called after the 'event' has been consumed. Note that this hook will not be called if the 'event' has been previously discarded.
	 * 
	 * @param chart chart instance
	 * @param argument argument of event callback
	 */
	void onAfterEvent(IsChart chart, PluginEventArgument argument) {
		// if consistent, calls plugin
		if (IsChart.isValid(chart)) {
			delegation.onAfterEvent(chart, argument);
		}
	}

	// ----------------------------
	// -- RESIZE, RESET, DESTORY --
	// ----------------------------

	/**
	 * Called after the chart as been resized.
	 * 
	 * @param chart chart instance
	 * @param argument argument of method which contains the new canvas display size (eq. canvas.style width and height).
	 */
	void onResize(IsChart chart, PluginResizeArgument argument) {
		// if consistent, calls plugin
		if (IsChart.isValid(chart)) {
			delegation.onResize(chart, argument);
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
			// gets the counter
			AtomicInteger counter = drawingStatus.get(chart.getId());
			// reset to 0
			counter.set(0);
			// invokes the reset of plugin
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
			// removes the counter
			drawingStatus.remove(chart.getId());
			// invokes the destroy of plugin
			delegation.onDestroy(chart);
		}
	}

	// ---------------------------
	// -- PLUGIN LIFECYCLE
	// ---------------------------

	/**
	 * Called when plugin is installed for this chart instance.<br>
	 * This hook is also invoked for disabled plugins (options equals to false).
	 * 
	 * @param chart chart instance
	 */
	void onInstall(IsChart chart) {
		// if consistent, calls plugin
		if (IsChart.isValid(chart)) {
			// invokes the install of plugin
			delegation.onInstall(chart);
		}
	}

	/**
	 * Called when a plugin is starting.<br>
	 * This happens when chart is created or plugin is enabled.
	 * 
	 * @param chart chart instance
	 */
	void onStart(IsChart chart) {
		// if consistent, calls plugin
		if (IsChart.isValid(chart)) {
			// invokes the start of plugin
			delegation.onStart(chart);
		}
	}

	/**
	 * Called when a plugin stopping.<br>
	 * This happens when chart is destroyed or plugin is disabled.
	 * 
	 * @param chart chart instance
	 */
	void onStop(IsChart chart) {
		// if consistent, calls plugin
		if (IsChart.isValid(chart)) {
			// invokes the stop of plugin
			delegation.onDestroy(chart);
		}
	}

	// ---------------------------
	// -- SCALES DATA LIMITS
	// ---------------------------

	/**
	 * Called before scale data limits are calculated.<br>
	 * This hook is called separately for each scale in the chart.
	 * 
	 * @param chart the chart instance.
	 * @param argument argument of method which contains the scale instance.
	 */
	void onBeforeDataLimits(IsChart chart, PluginScaleArgument argument) {
		// if consistent, calls plugin
		if (IsChart.isValid(chart)) {
			delegation.onBeforeDataLimits(chart, argument);
		}
	}

	/**
	 * Called after scale data limits are calculated.<br>
	 * This hook is called separately for each scale in the chart.
	 * 
	 * @param chart the chart instance.
	 * @param argument argument of method which contains the scale instance.
	 */
	void onAfterDataLimits(IsChart chart, PluginScaleArgument argument) {
		// if consistent, calls plugin
		if (IsChart.isValid(chart)) {
			delegation.onAfterDataLimits(chart, argument);
		}
	}

	// ---------------------------
	// -- SCALES BUILD TICKS
	// ---------------------------

	/**
	 * Called before scale builds its ticks.<br>
	 * This hook is called separately for each scale in the chart.
	 * 
	 * @param chart the chart instance.
	 * @param argument argument of method which contains the scale instance.
	 */
	void onBeforeBuildTicks(IsChart chart, PluginScaleArgument argument) {
		// if consistent, calls plugin
		if (IsChart.isValid(chart)) {
			delegation.onBeforeBuildTicks(chart, argument);
		}
	}

	/**
	 * Called after scale has build its ticks.<br>
	 * This hook is called separately for each scale in the chart.
	 * 
	 * @param chart the chart instance.
	 * @param argument argument of method which contains the scale instance.
	 */
	void onAfterBuildTicks(IsChart chart, PluginScaleArgument argument) {
		// if consistent, calls plugin
		if (IsChart.isValid(chart)) {
			delegation.onAfterBuildTicks(chart, argument);
		}
	}

	// ---------------------------
	// -- COMMON
	// ---------------------------

	/**
	 * Called after chart is destroyed on all plugins that were installed for that chart.<br>
	 * This hook is also invoked for disabled plugins (options equals to false).
	 * 
	 * @param chart chart instance
	 */
	void onUninstall(IsChart chart) {
		// if consistent, calls plugin
		if (IsChart.isValid(chart)) {
			// invokes the unistall of plugin
			delegation.onUninstall(chart);
		}
	}

	/**
	 * Returns the returned boolean value of "before" methods of plugin.<br>
	 * It checks if returning <code>false</code> and then stopping the chart drawing, the counter must be decremented in order to be always aligned with overriding parameter of
	 * {@link Plugin#onBeginDrawing(IsChart, boolean)}.
	 * 
	 * @param chart chart instance where the plugin has been applied
	 * @param returnedValue the returned boolean value of "before" method of plugin
	 * @return the returned boolean value of "before" method of plugin
	 */
	private boolean checkAndGetBeforeContinue(IsChart chart, boolean returnedValue) {
		// checks if result of plugin call is to stop the rendering
		if (!returnedValue && drawingStatus.containsKey(chart.getId())) {
			// gets the counter
			AtomicInteger counter = drawingStatus.get(chart.getId());
			// checks if counter is consistent and then greater than 0
			if (counter.intValue() > 0) {
				// decrements of 1
				counter.decrementAndGet();
			}
		}
		// returns the value of plugin call
		return returnedValue;
	}

}
