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

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.Plugin;
import org.pepstock.charba.client.commons.ChartContainer;
import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.events.ChartNativeEvent;
import org.pepstock.charba.client.items.SizeItem;
import org.pepstock.charba.client.items.TooltipModel;

/**
 * Wraps a plugin, delegating the execution of all hoks to it.<br>
 * The wrapper is mandatory to able to catch all hooks of chart even if the plugin implements just a part of the hooks.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class WrapperPlugin extends ChartContainer {

	private final Plugin delegation;

	/**
	 * Builds teh object with the chart and plugin instances
	 * 
	 * @param chart chart instance
	 * @param delegation plugin instance
	 */
	WrapperPlugin(AbstractChart<?, ?> chart, Plugin delegation) {
		super(chart);
		this.delegation = delegation;
		// registers itself with all methods of plugin definition
		registerNativePluginsHandler(getJavaScriptObject());
	}

	/**
	 * Returns the plugin id.
	 * 
	 * @return the plugin id.
	 */
	final String getId() {
		return delegation.getId();
	}

	/**
	 * Called before initializing 'chart'.
	 */
	protected void onBeforeInit() {
		delegation.onBeforeInit(getChart());
	}

	/**
	 * Called after 'chart' has been initialized and before the first update.
	 */
	protected void onAfterInit() {
		delegation.onAfterInit(getChart());
	}

	/**
	 * Called before updating 'chart'. If any plugin returns <code>false</code>, the update is cancelled (and thus subsequent
	 * render(s)) until another 'update' is triggered.
	 * 
	 * @return <code>false</code> to cancel the chart update.
	 */
	protected boolean onBeforeUpdate() {
		return delegation.onBeforeUpdate(getChart());
	}

	/**
	 * Called after 'chart' has been updated and before rendering. Note that this hook will not be called if the chart update
	 * has been previously cancelled.
	 */
	protected void onAfterUpdate() {
		delegation.onAfterUpdate(getChart());
	}

	/**
	 * Called before laying out 'chart'. If any plugin returns <code>false</code>, the layout update is cancelled until another
	 * 'update' is triggered.
	 * 
	 * @return <code>false</code> to cancel the chart layout.
	 */
	protected boolean onBeforeLayout() {
		return delegation.onBeforeLayout(getChart());
	}

	/**
	 * Called after the 'chart' has been layed out. Note that this hook will not be called if the layout update has been
	 * previously cancelled.
	 */
	protected void onAfterLayout() {
		delegation.onAfterLayout(getChart());
	}

	/**
	 * Called before updating the 'chart' datasets. If any plugin returns <code>false</code>, the datasets update is cancelled
	 * until another 'update' is triggered.
	 * 
	 * @return <code>false</code> to cancel the datasets update.
	 */
	protected boolean onBeforeDatasetsUpdate() {
		return delegation.onBeforeDatasetsUpdate(getChart());
	}

	/**
	 * Called after the 'chart' datasets have been updated. Note that this hook will not be called if the datasets update has
	 * been previously cancelled.
	 */
	protected void onAfterDatasetsUpdate() {
		delegation.onAfterDatasetsUpdate(getChart());
	}

	/**
	 * Called before updating the 'chart' dataset at the given 'args.index'. If any plugin returns <code>false</code>, the
	 * datasets update is cancelled until another 'update' is triggered.
	 * 
	 * @param datasetIndex The dataset index.
	 * @return <code>false</code> to cancel the chart datasets drawing.
	 */
	protected boolean onBeforeDatasetUpdate(int datasetIndex) {
		return delegation.onBeforeDatasetUpdate(getChart(), datasetIndex);
	}

	/**
	 * Called after the 'chart' datasets at the given 'args.index' has been updated. Note that this hook will not be called if
	 * the datasets update has been previously cancelled.
	 * 
	 * @param datasetIndex The dataset index.
	 */
	protected void onAfterDatasetUpdate(int datasetIndex) {
		delegation.onAfterDatasetUpdate(getChart(), datasetIndex);
	}

	/**
	 * Called before rendering 'chart'. If any plugin returns <code>false</code>, the rendering is cancelled until another
	 * 'render' is triggered.
	 * 
	 * @return <code>false</code> to cancel the chart rendering.
	 */
	protected boolean onBeforeRender() {
		return delegation.onBeforeRender(getChart());
	}

	/**
	 * Called after the 'chart' has been fully rendered (and animation completed). Note that this hook will not be called if the
	 * rendering has been previously cancelled.
	 */
	protected void onAfterRender() {
		delegation.onAfterRender(getChart());
	}

	/**
	 * Called before drawing 'chart' at every animation frame specified by the given easing value. If any plugin returns
	 * <code>false</code>, the frame drawing is cancelled until another 'render' is triggered.
	 * 
	 * @param easing The current animation value, between 0.0 and 1.0.
	 * @return <code>false</code> to cancel the chart drawing.
	 */
	protected boolean onBeforeDraw(String easing) {
		return delegation.onBeforeDraw(getChart(), Double.valueOf(easing));
	}

	/**
	 * Called after the 'chart' has been drawn for the specific easing value. Note that this hook will not be called if the
	 * drawing has been previously cancelled.
	 * 
	 * @param easing The current animation value, between 0.0 and 1.0.
	 */
	protected void onAfterDraw(String easing) {
		delegation.onAfterDraw(getChart(), Double.valueOf(easing));
	}

	/**
	 * Called before drawing the 'chart' datasets. If any plugin returns <code>false</code>, the datasets drawing is cancelled
	 * until another 'render' is triggered.
	 * 
	 * @param easing The current animation value, between 0.0 and 1.0.
	 * @return <code>false</code> to cancel the chart datasets drawing.
	 */
	protected boolean onBeforeDatasetsDraw(String easing) {
		return delegation.onBeforeDatasetsDraw(getChart(), Double.valueOf(easing));
	}

	/**
	 * Called after the 'chart' datasets have been drawn. Note that this hook will not be called if the datasets drawing has
	 * been previously cancelled.
	 * 
	 * @param easing The current animation value, between 0.0 and 1.0.
	 */
	protected void onAfterDatasetsDraw(String easing) {
		delegation.onAfterDatasetsDraw(getChart(), Double.valueOf(easing));
	}

	/**
	 * Called before drawing the 'chart' dataset at the given 'args.index' (datasets are drawn in the reverse order). If any
	 * plugin returns <code>false</code>, the datasets drawing is cancelled until another 'render' is triggered.
	 * 
	 * @param datasetIndex The dataset index.
	 * @param easing The current animation value, between 0.0 and 1.0.
	 * @return <code>false</code> to cancel the chart datasets drawing.
	 */
	protected boolean onBeforeDatasetDraw(int datasetIndex, String easing) {
		return delegation.onBeforeDatasetDraw(getChart(), datasetIndex, Double.valueOf(easing));
	}

	/**
	 * Called after the 'chart' datasets at the given 'args.index' have been drawn (datasets are drawn in the reverse order).
	 * Note that this hook will not be called if the datasets drawing has been previously cancelled.
	 * 
	 * @param datasetIndex The dataset index.
	 * @param easing The current animation value, between 0.0 and 1.0.
	 */
	protected void onAfterDatasetDraw(int datasetIndex, String easing) {
		delegation.onAfterDatasetDraw(getChart(), datasetIndex, Double.valueOf(easing));
	}

	/**
	 * Called before drawing the 'tooltip'. If any plugin returns <code>false</code>, the tooltip drawing is cancelled until
	 * another 'render' is triggered.
	 * 
	 * @FIXME
	 * @param model The tooltip instance.
	 * @param easing The current animation value, between 0.0 and 1.0.
	 * @return <code>false</code> to cancel the chart tooltip drawing.
	 */
	protected boolean onBeforeTooltipDraw(TooltipModel model, String easing) {
		return delegation.onBeforeTooltipDraw(getChart(), model, Double.valueOf(easing));
	}

	/**
	 * Called after drawing the 'tooltip'. Note that this hook will not be called if the tooltip drawing has been previously
	 * cancelled.
	 * 
	 * @param model The tooltip instance.
	 * @param easing The current animation value, between 0.0 and 1.0.
	 */
	protected void onAfterTooltipDraw(TooltipModel model, String easing) {
		delegation.onAfterTooltipDraw(getChart(), model, Double.valueOf(easing));
	}

	/**
	 * Called before processing the specified 'event'. If any plugin returns <code>false</code>, the event will be discarded.
	 * 
	 * @param event The event object.
	 * @return <code>false</code> to discard the event.
	 */
	protected boolean onBeforeEvent(ChartNativeEvent event) {
		return delegation.onBeforeEvent(getChart(), event);
	}

	/**
	 * Called after the 'event' has been consumed. Note that this hook will not be called if the 'event' has been previously
	 * discarded.
	 * 
	 * @param event The event object.
	 */
	protected void onAfterEvent(ChartNativeEvent event) {
		delegation.onAfterEvent(getChart(), event);
	}

	/**
	 * Called after the chart as been resized.
	 * 
	 * @param item The new canvas display size (eq. canvas.style width & height).
	 */
	protected void onResize(SizeItem item) {
		delegation.onResize(getChart(), item);
	}

	/**
	 * Called after the chart as been destroyed.
	 */
	protected void onDestroy() {
		delegation.onDestroy(getChart());
	}

	/**
	 * Wraps the protected method to get the java script object.
	 * 
	 * @return the java script object.
	 */
	public GenericJavaScriptObject getObject() {
		return getJavaScriptObject();
	}
	
	/**
	 * Creates the java script functions to get the control when the hook is invoked.
	 * 
	 * @param config java script object of plugin.
	 */
	private native void registerNativePluginsHandler(GenericJavaScriptObject config)/*-{
	var self = this;

	// init
	config.beforeInit = function(chart, option) {
		self.@org.pepstock.charba.client.plugins.WrapperPlugin::onBeforeInit()();
		return;
	}
	config.afterInit = function(chart, option) {
		self.@org.pepstock.charba.client.plugins.WrapperPlugin::onAfterInit()();
		return;
	}

	// update
	config.beforeUpdate = function(chart, option) {
		return self.@org.pepstock.charba.client.plugins.WrapperPlugin::onBeforeUpdate()();
	}
	config.afterUpdate = function(chart, option) {
		self.@org.pepstock.charba.client.plugins.WrapperPlugin::onAfterUpdate()();
		return;
	}
	
	// layout
	config.beforeLayout = function(chart, option) {
		return self.@org.pepstock.charba.client.plugins.WrapperPlugin::onBeforeLayout()();
	}
	config.afterLayout = function(chart, option) {
		self.@org.pepstock.charba.client.plugins.WrapperPlugin::onAfterLayout()();
		return;
	}
	
	// datasets
	config.beforeDatasetsUpdate = function(chart, option) {
		return self.@org.pepstock.charba.client.plugins.WrapperPlugin::onBeforeDatasetsUpdate()();
	}
	config.afterDatasetsUpdate = function(chart, option) {
		self.@org.pepstock.charba.client.plugins.WrapperPlugin::onAfterDatasetsUpdate()();
		return;
	}
	
	// dataset
	config.beforeDatasetUpdate = function(chart, args, option) {
		return self.@org.pepstock.charba.client.plugins.WrapperPlugin::onBeforeDatasetUpdate(I)(args.index);
	}
	config.afterDatasetUpdate = function(chart, args, option) {
		self.@org.pepstock.charba.client.plugins.WrapperPlugin::onAfterDatasetUpdate(I)(args.index);
		return;
	}

	// render
	config.beforeRender = function(chart, option) {
		return self.@org.pepstock.charba.client.plugins.WrapperPlugin::onBeforeRender()();
	}
	config.afterRender = function(chart, option) {
		self.@org.pepstock.charba.client.plugins.WrapperPlugin::onAfterRender()();
		return;
	}
	
	// draw
	config.beforeDraw = function(chart, easing) {
		return self.@org.pepstock.charba.client.plugins.WrapperPlugin::onBeforeDraw(Ljava/lang/String;)(easing);
	}
	config.afterDraw = function(chart, easing) {
		self.@org.pepstock.charba.client.plugins.WrapperPlugin::onAfterDraw(Ljava/lang/String;)(easing);
		return;
	}

	// datasets draw
	config.beforeDatasetsDraw = function(chart, easing) {
		return self.@org.pepstock.charba.client.plugins.WrapperPlugin::onBeforeDatasetsDraw(Ljava/lang/String;)(easing);
	}
	config.afterDatasetsDraw = function(chart, easing) {
		self.@org.pepstock.charba.client.plugins.WrapperPlugin::onAfterDatasetsDraw(Ljava/lang/String;)(easing);
		return;
	}

	// dataset draw
	config.beforeDatasetDraw = function(chart, args, option) {
		return self.@org.pepstock.charba.client.plugins.WrapperPlugin::onBeforeDatasetDraw(ILjava/lang/String;)(args.index, args.easingValue);
	}
	config.afterDatasetDraw = function(chart, args, option) {
		self.@org.pepstock.charba.client.plugins.WrapperPlugin::onAfterDatasetDraw(ILjava/lang/String;)(args.index, args.easingValue);
		return;
	}

	// tooltip draw
	config.beforeTooltipDraw = function(chart, args, option) {
		return self.@org.pepstock.charba.client.plugins.WrapperPlugin::onBeforeTooltipDraw(Lorg/pepstock/charba/client/items/TooltipModel;Ljava/lang/String;)(args.tooltip._model, args.easingValue);
	}
	config.afterTooltipDraw = function(chart, args, option) {
		self.@org.pepstock.charba.client.plugins.WrapperPlugin::onAfterTooltipDraw(Lorg/pepstock/charba/client/items/TooltipModel;Ljava/lang/String;)(args.tooltip._model, args.easingValue);
		return;
	}

	// event
	config.beforeEvent = function(chart, event, option) {
		// uses the syntax ["native"] because . syntaz is not accepted 
		return self.@org.pepstock.charba.client.plugins.WrapperPlugin::onBeforeEvent(Lorg/pepstock/charba/client/events/ChartNativeEvent;)(event["native"]);
	}
	config.afterEvent = function(chart, event, option) {
		// uses the syntax ["native"] because . syntaz is not accepted
		self.@org.pepstock.charba.client.plugins.WrapperPlugin::onAfterEvent(Lorg/pepstock/charba/client/events/ChartNativeEvent;)(event["native"]);
		return;
	}

	// resize
	config.resize = function(chart, size, option) {
		self.@org.pepstock.charba.client.plugins.WrapperPlugin::onResize(Lorg/pepstock/charba/client/items/SizeItem;)(size);
		return;
	}


	// destroy
	config.destroy = function(chart, option) {
		self.@org.pepstock.charba.client.plugins.WrapperPlugin::onDestroy()();
		return;
	}


	}-*/;

}
