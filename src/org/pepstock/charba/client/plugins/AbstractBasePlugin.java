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
package org.pepstock.charba.client.plugins;

import org.pepstock.charba.client.Chart;
import org.pepstock.charba.client.IsChart;
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
import org.pepstock.charba.client.items.Undefined;

import jsinterop.annotations.JsFunction;

/**
 * Base plugin class implementation.<br>
 * It represents the instance to store in CHART.JS configuration.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class AbstractBasePlugin extends NativeObjectContainer {

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
		 * @param chart the chart instance.
		 * @param args argument object
		 * @param options plugin options set by user in the chart options.
		 */
		void call(Chart chart, NativeObject args, NativeObject options);
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
		 * @param chart the chart instance
		 * @param args the call arguments
		 * @param options plugin options set by user in the chart options
		 * @return <code>false</code> to cancel the chart operations
		 */
		boolean call(Chart chart, NativeObject args, NativeObject options);
	}

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
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
		BEFORE_DESTROY("beforeDestroy"),
		AFTER_DESTROY("afterDestroy"),
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
	// callback proxy to invoke the before destroy function
	private final CallbackProxy<ProxyWithoutReturnValueCallback> beforeDestroyCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the after destroy function
	private final CallbackProxy<ProxyWithoutReturnValueCallback> afterDestroyCallbackProxy = JsHelper.get().newCallbackProxy();
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

	/**
	 * Builds the object with plugin id
	 * 
	 * @param id plugin instance
	 */
	AbstractBasePlugin(String id) {
		// checks pugin id
		PluginIdChecker.check(id);
		// sets the plugin ID
		setValue(Property.ID, id);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// ---------------------------
		// -- INIT
		// ---------------------------
		// invoke user method implementation
		this.beforeInitCallbackProxy.setCallback((chart, args, options) -> invokeBeforeInit(chart.getChart()));
		// invoke user method implementation
		this.afterInitCallbackProxy.setCallback((chart, args, options) -> invokeAfterInit(chart.getChart(), chart));
		// ---------------------------
		// -- UPDATE
		// ---------------------------
		// invoke user method implementation
		this.afterUpdateCallbackProxy.setCallback((chart, args, options) -> invokeAfterUpdate(chart.getChart(), new PluginUpdateArgument(new PluginsEnvelop<>(args, true))));
		// invoke user method implementation
		this.beforeUpdateCallbackProxy.setCallback((chart, args, options) -> invokeBeforeUpdate(chart.getChart(), new PluginUpdateArgument(new PluginsEnvelop<>(args, true))));
		// ---------------------------
		// -- DATASETS UPDATE
		// ---------------------------
		// invoke user method implementation
		this.beforeDatasetsUpdateCallbackProxy.setCallback((chart, args, options) -> invokeBeforeDatasetsUpdate(chart.getChart(), new PluginUpdateArgument(new PluginsEnvelop<>(args, true))));
		// invoke user method implementation
		this.afterDatasetsUpdateCallbackProxy.setCallback((chart, args, options) -> invokeAfterDatasetsUpdate(chart.getChart(), new PluginUpdateArgument(new PluginsEnvelop<>(args, true))));
		// ---------------------------
		// -- DATASET UPDATE
		// ---------------------------
		// invoke user method implementation
		this.beforeDatasetUpdateCallbackProxy.setCallback((chart, args, options) -> invokeBeforeDatasetUpdate(chart.getChart(), new PluginDatasetArgument(new PluginsEnvelop<>(args, true))));
		// invoke user method implementation
		this.afterDatasetUpdateCallbackProxy.setCallback((chart, args, options) -> invokeAfterDatasetUpdate(chart.getChart(), new PluginDatasetArgument(new PluginsEnvelop<>(args, true))));
		// ---------------------------
		// -- ELEMENTS UPDATE
		// ---------------------------
		// invoke user method implementation
		this.beforeElementsUpdateCallbackProxy.setCallback((chart, args, options) -> invokeBeforeElementsUpdate(chart.getChart()));
		// ---------------------------
		// -- LAYOUT
		// ---------------------------
		// invoke user method implementation
		this.beforeLayoutCallbackProxy.setCallback((chart, args, options) -> invokeBeforeLayout(chart.getChart()));
		// invoke user method implementation
		this.afterLayoutCallbackProxy.setCallback((chart, args, options) -> invokeAfterLayout(chart.getChart()));
		// ---------------------------
		// -- RENDER
		// ---------------------------
		// invoke user method implementation
		this.beforeRenderCallbackProxy.setCallback((chart, args, options) -> invokeBeforeRender(chart.getChart()));
		// invoke user method implementation
		this.afterRenderCallbackProxy.setCallback((chart, args, options) -> invokeAfterRender(chart.getChart()));
		// ---------------------------
		// -- DRAW
		// ---------------------------
		// invoke user method implementation
		this.beforeDrawCallbackProxy.setCallback((chart, args, options) -> invokeBeforeDraw(chart.getChart()));
		// invoke user method implementation
		this.afterDrawCallbackProxy.setCallback((chart, args, options) -> invokeAfterDraw(chart.getChart()));
		// ---------------------------
		// -- DATASETS DRAW
		// ---------------------------
		// invoke user method implementation
		this.beforeDatasetsDrawCallbackProxy.setCallback((chart, args, options) -> invokeBeforeDatasetsDraw(chart.getChart()));
		// invoke user method implementation
		this.afterDatasetsDrawCallbackProxy.setCallback((chart, args, options) -> invokeAfterDatasetsDraw(chart.getChart()));
		// ---------------------------
		// -- DATASET DRAW
		// ---------------------------
		// invoke user method implementation
		this.beforeDatasetDrawCallbackProxy.setCallback((chart, args, options) -> invokeBeforeDatasetDraw(chart.getChart(), new PluginDatasetArgument(new PluginsEnvelop<>(args, true))));
		// invoke user method implementation
		this.afterDatasetDrawCallbackProxy.setCallback((chart, args, options) -> invokeAfterDatasetDraw(chart.getChart(), new PluginDatasetArgument(new PluginsEnvelop<>(args, true))));
		// ---------------------------
		// -- EVENT
		// ---------------------------
		// invoke user method implementation
		this.beforeEventCallbackProxy.setCallback((chart, args, options) -> invokeBeforeEvent(chart.getChart(), new PluginEventArgument(new PluginsEnvelop<>(args, true))));
		// invoke user method implementation
		this.afterEventCallbackProxy.setCallback((chart, args, options) -> invokeAfterEvent(chart.getChart(), new PluginEventArgument(new PluginsEnvelop<>(args, true))));
		// ---------------------------
		// -- TOOLTIP
		// ---------------------------
		// invoke user method implementation
		this.beforeTooltipDrawCallbackProxy.setCallback((chart, args, options) -> invokeBeforeTooltipDraw(chart.getChart(), new PluginTooltipArgument(new PluginsEnvelop<>(args, true))));
		// invoke user method implementation
		this.afterTooltipDrawCallbackProxy.setCallback((chart, args, options) -> invokeAfterTooltipDraw(chart.getChart(), new PluginTooltipArgument(new PluginsEnvelop<>(args, true))));
		// ---------------------------
		// -- RESET
		// ---------------------------
		// invoke user method implementation
		this.resetCallbackProxy.setCallback((chart, args, options) -> invokeReset(chart.getChart()));
		// ---------------------------
		// -- RESIZE
		// ---------------------------
		// invoke user method implementation
		this.resizeCallbackProxy.setCallback((chart, args, options) -> invokeResize(chart.getChart(), new PluginResizeArgument(new PluginsEnvelop<>(args, true))));
		// ---------------------------
		// -- DESTROY
		// ---------------------------
		// invoke user method implementation
		this.beforeDestroyCallbackProxy.setCallback((chart, args, options) -> invokeBeforeDestroy(chart.getChart()));
		// invoke user method implementation
		this.afterDestroyCallbackProxy.setCallback((chart, args, options) -> invokeAfterDestroy(chart.getChart()));
		// ---------------------------
		// -- PLUGIN LIFECYCLE
		// ---------------------------
		// invoke user method implementation
		this.installCallbackProxy.setCallback((chart, args, options) -> invokeInstall(chart.getChart()));
		// invoke user method implementation
		this.startCallbackProxy.setCallback((chart, args, options) -> invokeStart(chart.getChart()));
		// invoke user method implementation
		this.stopCallbackProxy.setCallback((chart, args, options) -> invokeStop(chart.getChart()));
		// invoke user method implementation
		this.uninstallCallbackProxy.setCallback((chart, args, options) -> invokeUninstall(chart.getChart()));
		// ---------------------------
		// -- SCALES DATA LIMITS
		// ---------------------------
		// invoke user method implementation
		this.beforeDataLimitsCallbackProxy.setCallback((chart, args, options) -> invokeBeforeDataLimits(chart.getChart(), new PluginScaleArgument(new PluginsEnvelop<>(args, true))));
		// invoke user method implementation
		this.afterDataLimitsCallbackProxy.setCallback((chart, args, options) -> invokeAfterDataLimits(chart.getChart(), new PluginScaleArgument(new PluginsEnvelop<>(args, true))));
		// ---------------------------
		// -- SCALES BUILD TICKS
		// ---------------------------
		// invoke user method implementation
		this.beforeBuildTicksCallbackProxy.setCallback((chart, args, options) -> invokeBeforeBuildTicks(chart.getChart(), new PluginScaleArgument(new PluginsEnvelop<>(args, true))));
		// invoke user method implementation
		this.afterBuildTicksCallbackProxy.setCallback((chart, args, options) -> invokeAfterBuildTicks(chart.getChart(), new PluginScaleArgument(new PluginsEnvelop<>(args, true))));
	}

	/**
	 * Returns the plugin id.
	 * 
	 * @return the plugin id.
	 */
	public final String getId() {
		return getValue(Property.ID, Undefined.STRING);
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
	abstract void invokeConfigure(IsChart chart);

	/**
	 * Called before initializing 'chart'.
	 * 
	 * @param chart chart instance
	 */
	abstract void invokeBeforeInit(IsChart chart);

	/**
	 * Called after 'chart' has been initialized and before the first update.
	 * 
	 * @param chart chart instance
	 * @param nativeChart CHART.JS chart instance
	 */
	abstract void invokeAfterInit(IsChart chart, Chart nativeChart);

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
	abstract boolean invokeBeforeUpdate(IsChart chart, PluginUpdateArgument argument);

	/**
	 * Called after 'chart' has been updated and before rendering.<br>
	 * Note that this hook will not be called if the chart update has been previously cancelled.
	 * 
	 * @param chart chart instance
	 * @param argument the argument passed for update
	 */
	abstract void invokeAfterUpdate(IsChart chart, PluginUpdateArgument argument);

	/**
	 * Called during the update process, before any chart elements have been created.
	 * 
	 * @param chart chart instance
	 */
	abstract void invokeBeforeElementsUpdate(IsChart chart);

	/**
	 * Called before laying out 'chart'.<br>
	 * If any plugin returns <code>false</code>, the layout update is cancelled until another 'update' is triggered.
	 * 
	 * @param chart chart instance
	 * @return <code>false</code> to cancel the chart layout.
	 */
	abstract boolean invokeBeforeLayout(IsChart chart);

	/**
	 * Called after the 'chart' has been layed out.<br>
	 * Note that this hook will not be called if the layout update has been previously cancelled.
	 * 
	 * @param chart chart instance
	 */
	abstract void invokeAfterLayout(IsChart chart);

	/**
	 * Called before updating the 'chart' datasets.<br>
	 * If any plugin returns <code>false</code>, the datasets update is cancelled until another 'update' is triggered.
	 * 
	 * @param chart chart instance
	 * @param argument the argument passed for update
	 * @return <code>false</code> to cancel the datasets update.
	 */
	abstract boolean invokeBeforeDatasetsUpdate(IsChart chart, PluginUpdateArgument argument);

	/**
	 * Called after the 'chart' datasets have been updated.<br>
	 * Note that this hook will not be called if the datasets update has been previously cancelled.
	 * 
	 * @param chart chart instance
	 * @param argument the argument passed for update
	 */
	abstract void invokeAfterDatasetsUpdate(IsChart chart, PluginUpdateArgument argument);

	/**
	 * Called before updating the 'chart' dataset at the given 'args.index'.<br>
	 * If any plugin returns <code>false</code>, the datasets update is cancelled until another 'update' is triggered.
	 * 
	 * @param chart chart instance
	 * @param item dataset item.
	 * @return <code>false</code> to cancel the chart datasets drawing.
	 */
	abstract boolean invokeBeforeDatasetUpdate(IsChart chart, PluginDatasetArgument item);

	/**
	 * Called after the 'chart' datasets at the given 'args.index' has been updated.<br>
	 * Note that this hook will not be called if the datasets update has been previously cancelled.
	 * 
	 * @param chart chart instance
	 * @param item dataset item.
	 */
	abstract void invokeAfterDatasetUpdate(IsChart chart, PluginDatasetArgument item);

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
	abstract boolean invokeBeforeRender(IsChart chart);

	/**
	 * Called after the 'chart' has been fully rendered (and animation completed).<br>
	 * Note that this hook will not be called if the rendering has been previously cancelled.
	 * 
	 * @param chart chart instance
	 */
	abstract void invokeAfterRender(IsChart chart);

	/**
	 * Called before drawing 'chart' at every animation frame.<br>
	 * If any plugin returns <code>false</code>, the frame drawing is cancelled until another 'render' is triggered.
	 * 
	 * @param chart chart instance
	 * @return <code>false</code> to cancel the chart drawing.
	 */
	abstract boolean invokeBeforeDraw(IsChart chart);

	/**
	 * Called after the 'chart' has been drawn.<br>
	 * Note that this hook will not be called if the drawing has been previously cancelled.
	 * 
	 * @param chart chart instance
	 */
	abstract void invokeAfterDraw(IsChart chart);

	/**
	 * Called before drawing the 'chart' datasets.<br>
	 * If any plugin returns <code>false</code>, the datasets drawing is cancelled until another 'render' is triggered.
	 * 
	 * @param chart chart instance
	 * @return <code>false</code> to cancel the chart datasets drawing.
	 */
	abstract boolean invokeBeforeDatasetsDraw(IsChart chart);

	/**
	 * Called after the 'chart' datasets have been drawn.<br>
	 * Note that this hook will not be called if the datasets drawing has been previously cancelled.
	 * 
	 * @param chart chart instance
	 */
	abstract void invokeAfterDatasetsDraw(IsChart chart);

	/**
	 * Called before drawing the 'chart' dataset at the given 'args.index' (datasets are drawn in the reverse order). If any plugin returns <code>false</code>, the datasets drawing
	 * is cancelled until another 'render' is triggered.
	 * 
	 * @param chart chart instance
	 * @param item dataset item instance
	 * @return <code>false</code> to cancel the chart datasets drawing.
	 */
	abstract boolean invokeBeforeDatasetDraw(IsChart chart, PluginDatasetArgument item);

	/**
	 * Called after the 'chart' datasets at the given 'args.index' have been drawn (datasets are drawn in the reverse order). Note that this hook will not be called if the datasets
	 * drawing has been previously cancelled.
	 * 
	 * @param chart chart instance
	 * @param item dataset item instance
	 */
	abstract void invokeAfterDatasetDraw(IsChart chart, PluginDatasetArgument item);

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
	abstract boolean invokeBeforeTooltipDraw(IsChart chart, PluginTooltipArgument item);

	/**
	 * Called after drawing the 'tooltip'. Note that this hook will not be called if the tooltip drawing has been previously cancelled.
	 * 
	 * @param chart chart instance
	 * @param item tooltip item instance
	 */
	abstract void invokeAfterTooltipDraw(IsChart chart, PluginTooltipArgument item);
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
	abstract boolean invokeBeforeEvent(IsChart chart, PluginEventArgument argument);

	/**
	 * Called after the 'event' has been consumed. Note that this hook will not be called if the 'event' has been previously discarded.
	 * 
	 * @param chart chart instance
	 * @param argument argument of event callback
	 */
	abstract void invokeAfterEvent(IsChart chart, PluginEventArgument argument);

	// ----------------------------
	// -- RESIZE, RESET, DESTORY --
	// ----------------------------

	/**
	 * Called after the chart as been resized.
	 * 
	 * @param chart chart instance
	 * @param argument argument of method which contains the new canvas display size (eq. canvas.style width and height).
	 */
	abstract void invokeResize(IsChart chart, PluginResizeArgument argument);

	/**
	 * Called during chart reset.
	 * 
	 * @param chart chart instance
	 */
	abstract void invokeReset(IsChart chart);

	/**
	 * Called before the chart is being destroyed.
	 * 
	 * @param chart chart instance
	 */
	abstract void invokeBeforeDestroy(IsChart chart);

	/**
	 * Called after the chart has been destroyed.
	 * 
	 * @param chart chart instance
	 */
	abstract void invokeAfterDestroy(IsChart chart);

	// ---------------------------
	// -- PLUGIN LIFECYCLE
	// ---------------------------

	/**
	 * Called when plugin is installed for this chart instance.<br>
	 * This hook is also invoked for disabled plugins (options equals to false).
	 * 
	 * @param chart chart instance
	 */
	abstract void invokeInstall(IsChart chart);

	/**
	 * Called when a plugin is starting.<br>
	 * This happens when chart is created or plugin is enabled.
	 * 
	 * @param chart chart instance
	 */
	abstract void invokeStart(IsChart chart);

	/**
	 * Called when a plugin stopping.<br>
	 * This happens when chart is destroyed or plugin is disabled.
	 * 
	 * @param chart chart instance
	 */
	abstract void invokeStop(IsChart chart);

	/**
	 * Called after chart is destroyed on all plugins that were installed for that chart.<br>
	 * This hook is also invoked for disabled plugins (options equals to false).
	 * 
	 * @param chart chart instance
	 */
	abstract void invokeUninstall(IsChart chart);

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
	abstract void invokeBeforeDataLimits(IsChart chart, PluginScaleArgument argument);

	/**
	 * Called after scale data limits are calculated.<br>
	 * This hook is called separately for each scale in the chart.
	 * 
	 * @param chart the chart instance.
	 * @param argument argument of method which contains the scale instance.
	 */
	abstract void invokeAfterDataLimits(IsChart chart, PluginScaleArgument argument);

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
	abstract void invokeBeforeBuildTicks(IsChart chart, PluginScaleArgument argument);

	/**
	 * Called after scale has build its ticks.<br>
	 * This hook is called separately for each scale in the chart.
	 * 
	 * @param chart the chart instance.
	 * @param argument argument of method which contains the scale instance.
	 */
	abstract void invokeAfterBuildTicks(IsChart chart, PluginScaleArgument argument);

	// ---------------------------
	// -- GET methods of PROXY
	// ---------------------------

	/**
	 * @return the beforeInitCallbackProxy
	 */
	final CallbackProxy<ProxyWithoutReturnValueCallback> getBeforeInitCallbackProxy() {
		return beforeInitCallbackProxy;
	}

	/**
	 * @return the afterInitCallbackProxy
	 */
	final CallbackProxy<ProxyWithoutReturnValueCallback> getAfterInitCallbackProxy() {
		return afterInitCallbackProxy;
	}

	/**
	 * @return the beforeUpdateCallbackProxy
	 */
	final CallbackProxy<ProxyWithReturnValueCallback> getBeforeUpdateCallbackProxy() {
		return beforeUpdateCallbackProxy;
	}

	/**
	 * @return the afterUpdateCallbackProxy
	 */
	final CallbackProxy<ProxyWithoutReturnValueCallback> getAfterUpdateCallbackProxy() {
		return afterUpdateCallbackProxy;
	}

	/**
	 * @return the beforeDatasetsUpdateCallbackProxy
	 */
	final CallbackProxy<ProxyWithReturnValueCallback> getBeforeDatasetsUpdateCallbackProxy() {
		return beforeDatasetsUpdateCallbackProxy;
	}

	/**
	 * @return the afterDatasetsUpdateCallbackProxy
	 */
	final CallbackProxy<ProxyWithoutReturnValueCallback> getAfterDatasetsUpdateCallbackProxy() {
		return afterDatasetsUpdateCallbackProxy;
	}

	/**
	 * @return the beforeDatasetUpdateCallbackProxy
	 */
	final CallbackProxy<ProxyWithReturnValueCallback> getBeforeDatasetUpdateCallbackProxy() {
		return beforeDatasetUpdateCallbackProxy;
	}

	/**
	 * @return the afterDatasetUpdateCallbackProxy
	 */
	final CallbackProxy<ProxyWithoutReturnValueCallback> getAfterDatasetUpdateCallbackProxy() {
		return afterDatasetUpdateCallbackProxy;
	}

	/**
	 * @return the beforeElementsUpdateCallbackProxy
	 */
	final CallbackProxy<ProxyWithoutReturnValueCallback> getBeforeElementsUpdateCallbackProxy() {
		return beforeElementsUpdateCallbackProxy;
	}

	/**
	 * @return the beforeLayoutCallbackProxy
	 */
	final CallbackProxy<ProxyWithReturnValueCallback> getBeforeLayoutCallbackProxy() {
		return beforeLayoutCallbackProxy;
	}

	/**
	 * @return the afterLayoutCallbackProxy
	 */
	final CallbackProxy<ProxyWithoutReturnValueCallback> getAfterLayoutCallbackProxy() {
		return afterLayoutCallbackProxy;
	}

	/**
	 * @return the beforeRenderCallbackProxy
	 */
	final CallbackProxy<ProxyWithReturnValueCallback> getBeforeRenderCallbackProxy() {
		return beforeRenderCallbackProxy;
	}

	/**
	 * @return the afterRenderCallbackProxy
	 */
	final CallbackProxy<ProxyWithoutReturnValueCallback> getAfterRenderCallbackProxy() {
		return afterRenderCallbackProxy;
	}

	/**
	 * @return the beforeDrawCallbackProxy
	 */
	final CallbackProxy<ProxyWithReturnValueCallback> getBeforeDrawCallbackProxy() {
		return beforeDrawCallbackProxy;
	}

	/**
	 * @return the afterDrawCallbackProxy
	 */
	final CallbackProxy<ProxyWithoutReturnValueCallback> getAfterDrawCallbackProxy() {
		return afterDrawCallbackProxy;
	}

	/**
	 * @return the beforeDatasetsDrawCallbackProxy
	 */
	final CallbackProxy<ProxyWithReturnValueCallback> getBeforeDatasetsDrawCallbackProxy() {
		return beforeDatasetsDrawCallbackProxy;
	}

	/**
	 * @return the afterDatasetsDrawCallbackProxy
	 */
	final CallbackProxy<ProxyWithoutReturnValueCallback> getAfterDatasetsDrawCallbackProxy() {
		return afterDatasetsDrawCallbackProxy;
	}

	/**
	 * @return the beforeDatasetDrawCallbackProxy
	 */
	final CallbackProxy<ProxyWithReturnValueCallback> getBeforeDatasetDrawCallbackProxy() {
		return beforeDatasetDrawCallbackProxy;
	}

	/**
	 * @return the afterDatasetDrawCallbackProxy
	 */
	final CallbackProxy<ProxyWithoutReturnValueCallback> getAfterDatasetDrawCallbackProxy() {
		return afterDatasetDrawCallbackProxy;
	}

	/**
	 * @return the beforeEventCallbackProxy
	 */
	final CallbackProxy<ProxyWithReturnValueCallback> getBeforeEventCallbackProxy() {
		return beforeEventCallbackProxy;
	}

	/**
	 * @return the afterEventCallbackProxy
	 */
	final CallbackProxy<ProxyWithoutReturnValueCallback> getAfterEventCallbackProxy() {
		return afterEventCallbackProxy;
	}

	/**
	 * @return the beforeTooltipDrawCallbackProxy
	 */
	final CallbackProxy<ProxyWithReturnValueCallback> getBeforeTooltipDrawCallbackProxy() {
		return beforeTooltipDrawCallbackProxy;
	}

	/**
	 * @return the afterTooltipDrawCallbackProxy
	 */
	final CallbackProxy<ProxyWithoutReturnValueCallback> getAfterTooltipDrawCallbackProxy() {
		return afterTooltipDrawCallbackProxy;
	}

	/**
	 * @return the resetCallbackProxy
	 */
	final CallbackProxy<ProxyWithoutReturnValueCallback> getResetCallbackProxy() {
		return resetCallbackProxy;
	}

	/**
	 * @return the resizeCallbackProxy
	 */
	final CallbackProxy<ProxyWithoutReturnValueCallback> getResizeCallbackProxy() {
		return resizeCallbackProxy;
	}

	/**
	 * @return the beforeDestroyCallbackProxy
	 */
	final CallbackProxy<ProxyWithoutReturnValueCallback> getBeforeDestroyCallbackProxy() {
		return beforeDestroyCallbackProxy;
	}

	/**
	 * @return the afterDestroyCallbackProxy
	 */
	final CallbackProxy<ProxyWithoutReturnValueCallback> getAfterDestroyCallbackProxy() {
		return afterDestroyCallbackProxy;
	}

	/**
	 * @return the beforeDataLimitsCallbackProxy
	 */
	final CallbackProxy<ProxyWithoutReturnValueCallback> getBeforeDataLimitsCallbackProxy() {
		return beforeDataLimitsCallbackProxy;
	}

	/**
	 * @return the afterDataLimitsCallbackProxy
	 */
	final CallbackProxy<ProxyWithoutReturnValueCallback> getAfterDataLimitsCallbackProxy() {
		return afterDataLimitsCallbackProxy;
	}

	/**
	 * @return the beforeBuildTicksCallbackProxy
	 */
	final CallbackProxy<ProxyWithoutReturnValueCallback> getBeforeBuildTicksCallbackProxy() {
		return beforeBuildTicksCallbackProxy;
	}

	/**
	 * @return the afterBuildTicksCallbackProxy
	 */
	final CallbackProxy<ProxyWithoutReturnValueCallback> getAfterBuildTicksCallbackProxy() {
		return afterBuildTicksCallbackProxy;
	}

	/**
	 * @return the installCallbackProxy
	 */
	final CallbackProxy<ProxyWithoutReturnValueCallback> getInstallCallbackProxy() {
		return installCallbackProxy;
	}

	/**
	 * @return the startCallbackProxy
	 */
	final CallbackProxy<ProxyWithoutReturnValueCallback> getStartCallbackProxy() {
		return startCallbackProxy;
	}

	/**
	 * @return the stopCallbackProxy
	 */
	final CallbackProxy<ProxyWithoutReturnValueCallback> getStopCallbackProxy() {
		return stopCallbackProxy;
	}

	/**
	 * @return the uninstallCallbackProxy
	 */
	final CallbackProxy<ProxyWithoutReturnValueCallback> getUninstallCallbackProxy() {
		return uninstallCallbackProxy;
	}

}