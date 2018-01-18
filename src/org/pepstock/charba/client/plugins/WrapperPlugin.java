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
import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.JavaScriptObjectContainer;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.events.ChartNativeEvent;
import org.pepstock.charba.client.items.SizeItem;
import org.pepstock.charba.client.items.TooltipModel;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Wraps a plugin, delegating the execution of all hooks to it.<br>
 * The wrapper is mandatory to able to catch all hooks of chart even if the plugin implements just a part of the hooks.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class WrapperPlugin extends JavaScriptObjectContainer {

	private final Plugin delegation;

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		id
	}
	
	/**
	 * Builds teh object with the chart and plugin instances
	 * 
	 * @param chart chart instance
	 * @param delegation plugin instance
	 */
	WrapperPlugin(Plugin delegation) {
		this.delegation = delegation;
		setValue(Property.id, delegation.getId());
		// registers itself with all methods of plugin definition
		registerNativePluginsHandler(getJavaScriptObject());
	}

	abstract AbstractChart<?, ?> getChart(String chartId);
	
	/**
	 * Returns the plugin id.
	 * 
	 * @return the plugin id.
	 */
	final String getId() {
		return delegation.getId();
	}

	private JavaScriptObject getOptions(String chartId){
		try {
			return getChart(chartId).getOptions().getPlugins().getOptions(getId());
		} catch (InvalidPluginIdException e) {
			return null;
		}
	}
	
	/**
	 * Called before initializing 'chart'.
	 */
	protected void onBeforeInit(String chartId) {
		delegation.onBeforeInit(getChart(chartId), getOptions(chartId));
	}

	/**
	 * Called after 'chart' has been initialized and before the first update.
	 */
	protected void onAfterInit(String chartId) {
		delegation.onAfterInit(getChart(chartId), getOptions(chartId));
	}

	/**
	 * Called before updating 'chart'. If any plugin returns <code>false</code>, the update is cancelled (and thus subsequent
	 * render(s)) until another 'update' is triggered.
	 * 
	 * @return <code>false</code> to cancel the chart update.
	 */
	protected boolean onBeforeUpdate(String chartId) {
		return delegation.onBeforeUpdate(getChart(chartId), getOptions(chartId));
	}

	/**
	 * Called after 'chart' has been updated and before rendering. Note that this hook will not be called if the chart update
	 * has been previously cancelled.
	 */
	protected void onAfterUpdate(String chartId) {
		delegation.onAfterUpdate(getChart(chartId), getOptions(chartId));
	}

	/**
	 * Called before laying out 'chart'. If any plugin returns <code>false</code>, the layout update is cancelled until another
	 * 'update' is triggered.
	 * 
	 * @return <code>false</code> to cancel the chart layout.
	 */
	protected boolean onBeforeLayout(String chartId) {
		return delegation.onBeforeLayout(getChart(chartId), getOptions(chartId));
	}

	/**
	 * Called after the 'chart' has been layed out. Note that this hook will not be called if the layout update has been
	 * previously cancelled.
	 */
	protected void onAfterLayout(String chartId) {
		delegation.onAfterLayout(getChart(chartId), getOptions(chartId));
	}

	/**
	 * Called before updating the 'chart' datasets. If any plugin returns <code>false</code>, the datasets update is cancelled
	 * until another 'update' is triggered.
	 * 
	 * @return <code>false</code> to cancel the datasets update.
	 */
	protected boolean onBeforeDatasetsUpdate(String chartId) {
		return delegation.onBeforeDatasetsUpdate(getChart(chartId), getOptions(chartId));
	}

	/**
	 * Called after the 'chart' datasets have been updated. Note that this hook will not be called if the datasets update has
	 * been previously cancelled.
	 */
	protected void onAfterDatasetsUpdate(String chartId) {
		delegation.onAfterDatasetsUpdate(getChart(chartId), getOptions(chartId));
	}

	/**
	 * Called before updating the 'chart' dataset at the given 'args.index'. If any plugin returns <code>false</code>, the
	 * datasets update is cancelled until another 'update' is triggered.
	 * 
	 * @param datasetIndex The dataset index.
	 * @return <code>false</code> to cancel the chart datasets drawing.
	 */
	protected boolean onBeforeDatasetUpdate(String chartId, int datasetIndex) {
		return delegation.onBeforeDatasetUpdate(getChart(chartId), datasetIndex, getOptions(chartId));
	}

	/**
	 * Called after the 'chart' datasets at the given 'args.index' has been updated. Note that this hook will not be called if
	 * the datasets update has been previously cancelled.
	 * 
	 * @param datasetIndex The dataset index.
	 */
	protected void onAfterDatasetUpdate(String chartId, int datasetIndex) {
		delegation.onAfterDatasetUpdate(getChart(chartId), datasetIndex, getOptions(chartId));
	}

	/**
	 * Called before rendering 'chart'. If any plugin returns <code>false</code>, the rendering is cancelled until another
	 * 'render' is triggered.
	 * 
	 * @return <code>false</code> to cancel the chart rendering.
	 */
	protected boolean onBeforeRender(String chartId) {
		return delegation.onBeforeRender(getChart(chartId), getOptions(chartId));
	}

	/**
	 * Called after the 'chart' has been fully rendered (and animation completed). Note that this hook will not be called if the
	 * rendering has been previously cancelled.
	 */
	protected void onAfterRender(String chartId) {
		delegation.onAfterRender(getChart(chartId), getOptions(chartId));
	}

	/**
	 * Called before drawing 'chart' at every animation frame specified by the given easing value. If any plugin returns
	 * <code>false</code>, the frame drawing is cancelled until another 'render' is triggered.
	 * 
	 * @param easing The current animation value, between 0.0 and 1.0.
	 * @return <code>false</code> to cancel the chart drawing.
	 */
	protected boolean onBeforeDraw(String chartId, String easing) {
		return delegation.onBeforeDraw(getChart(chartId), Double.valueOf(easing), getOptions(chartId));
	}

	/**
	 * Called after the 'chart' has been drawn for the specific easing value. Note that this hook will not be called if the
	 * drawing has been previously cancelled.
	 * 
	 * @param easing The current animation value, between 0.0 and 1.0.
	 */
	protected void onAfterDraw(String chartId, String easing) {
		delegation.onAfterDraw(getChart(chartId), Double.valueOf(easing), getOptions(chartId));
	}

	/**
	 * Called before drawing the 'chart' datasets. If any plugin returns <code>false</code>, the datasets drawing is cancelled
	 * until another 'render' is triggered.
	 * 
	 * @param easing The current animation value, between 0.0 and 1.0.
	 * @return <code>false</code> to cancel the chart datasets drawing.
	 */
	protected boolean onBeforeDatasetsDraw(String chartId, String easing) {
		return delegation.onBeforeDatasetsDraw(getChart(chartId), Double.valueOf(easing), getOptions(chartId));
	}

	/**
	 * Called after the 'chart' datasets have been drawn. Note that this hook will not be called if the datasets drawing has
	 * been previously cancelled.
	 * 
	 * @param easing The current animation value, between 0.0 and 1.0.
	 */
	protected void onAfterDatasetsDraw(String chartId, String easing) {
		delegation.onAfterDatasetsDraw(getChart(chartId), Double.valueOf(easing), getOptions(chartId));
	}

	/**
	 * Called before drawing the 'chart' dataset at the given 'args.index' (datasets are drawn in the reverse order). If any
	 * plugin returns <code>false</code>, the datasets drawing is cancelled until another 'render' is triggered.
	 * 
	 * @param datasetIndex The dataset index.
	 * @param easing The current animation value, between 0.0 and 1.0.
	 * @return <code>false</code> to cancel the chart datasets drawing.
	 */
	protected boolean onBeforeDatasetDraw(String chartId, int datasetIndex, String easing) {
		return delegation.onBeforeDatasetDraw(getChart(chartId), datasetIndex, Double.valueOf(easing), getOptions(chartId));
	}

	/**
	 * Called after the 'chart' datasets at the given 'args.index' have been drawn (datasets are drawn in the reverse order).
	 * Note that this hook will not be called if the datasets drawing has been previously cancelled.
	 * 
	 * @param datasetIndex The dataset index.
	 * @param easing The current animation value, between 0.0 and 1.0.
	 */
	protected void onAfterDatasetDraw(String chartId, int datasetIndex, String easing) {
		delegation.onAfterDatasetDraw(getChart(chartId), datasetIndex, Double.valueOf(easing), getOptions(chartId));
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
	protected boolean onBeforeTooltipDraw(String chartId, TooltipModel model, String easing) {
		return delegation.onBeforeTooltipDraw(getChart(chartId), model, Double.valueOf(easing), getOptions(chartId));
	}

	/**
	 * Called after drawing the 'tooltip'. Note that this hook will not be called if the tooltip drawing has been previously
	 * cancelled.
	 * 
	 * @param model The tooltip instance.
	 * @param easing The current animation value, between 0.0 and 1.0.
	 */
	protected void onAfterTooltipDraw(String chartId, TooltipModel model, String easing) {
		delegation.onAfterTooltipDraw(getChart(chartId), model, Double.valueOf(easing), getOptions(chartId));
	}

	/**
	 * Called before processing the specified 'event'. If any plugin returns <code>false</code>, the event will be discarded.
	 * 
	 * @param event The event object.
	 * @return <code>false</code> to discard the event.
	 */
	protected boolean onBeforeEvent(String chartId, ChartNativeEvent event) {
		return delegation.onBeforeEvent(getChart(chartId), event, getOptions(chartId));
	}

	/**
	 * Called after the 'event' has been consumed. Note that this hook will not be called if the 'event' has been previously
	 * discarded.
	 * 
	 * @param event The event object.
	 */
	protected void onAfterEvent(String chartId, ChartNativeEvent event) {
		delegation.onAfterEvent(getChart(chartId), event, getOptions(chartId));
	}

	/**
	 * Called after the chart as been resized.
	 * 
	 * @param item The new canvas display size (eq. canvas.style width & height).
	 */
	protected void onResize(String chartId, SizeItem item) {
		delegation.onResize(getChart(chartId), item, getOptions(chartId));
	}

	/**
	 * Called after the chart as been destroyed.
	 */
	protected void onDestroy(String chartId) {
		delegation.onDestroy(getChart(chartId), getOptions(chartId));
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
		self.@org.pepstock.charba.client.plugins.WrapperPlugin::onBeforeInit(Ljava/lang/String;)(chart.options.charbaId);
		return;
	}
	config.afterInit = function(chart, option) {
		self.@org.pepstock.charba.client.plugins.WrapperPlugin::onAfterInit(Ljava/lang/String;)(chart.options.charbaId);
		return;
	}

	// update
	config.beforeUpdate = function(chart, option) {
		return self.@org.pepstock.charba.client.plugins.WrapperPlugin::onBeforeUpdate(Ljava/lang/String;)(chart.options.charbaId);
	}
	config.afterUpdate = function(chart, option) {
		self.@org.pepstock.charba.client.plugins.WrapperPlugin::onAfterUpdate(Ljava/lang/String;)(chart.options.charbaId);
		return;
	}
	
	// layout
	config.beforeLayout = function(chart, option) {
		return self.@org.pepstock.charba.client.plugins.WrapperPlugin::onBeforeLayout(Ljava/lang/String;)(chart.options.charbaId);
	}
	config.afterLayout = function(chart, option) {
		self.@org.pepstock.charba.client.plugins.WrapperPlugin::onAfterLayout(Ljava/lang/String;)(chart.options.charbaId);
		return;
	}
	
	// datasets
	config.beforeDatasetsUpdate = function(chart, option) {
		return self.@org.pepstock.charba.client.plugins.WrapperPlugin::onBeforeDatasetsUpdate(Ljava/lang/String;)(chart.options.charbaId);
	}
	config.afterDatasetsUpdate = function(chart, option) {
		self.@org.pepstock.charba.client.plugins.WrapperPlugin::onAfterDatasetsUpdate(Ljava/lang/String;)(chart.options.charbaId);
		return;
	}
	
	// dataset
	config.beforeDatasetUpdate = function(chart, args, option) {
		return self.@org.pepstock.charba.client.plugins.WrapperPlugin::onBeforeDatasetUpdate(Ljava/lang/String;I)(chart.options.charbaId, args.index);
	}
	config.afterDatasetUpdate = function(chart, args, option) {
		self.@org.pepstock.charba.client.plugins.WrapperPlugin::onAfterDatasetUpdate(Ljava/lang/String;I)(chart.options.charbaId, args.index);
		return;
	}

	// render
	config.beforeRender = function(chart, option) {
		return self.@org.pepstock.charba.client.plugins.WrapperPlugin::onBeforeRender(Ljava/lang/String;)(chart.options.charbaId);
	}
	config.afterRender = function(chart, option) {
		self.@org.pepstock.charba.client.plugins.WrapperPlugin::onAfterRender(Ljava/lang/String;)(chart.options.charbaId);
		return;
	}
	
	// draw
	config.beforeDraw = function(chart, easing) {
		return self.@org.pepstock.charba.client.plugins.WrapperPlugin::onBeforeDraw(Ljava/lang/String;Ljava/lang/String;)(chart.options.charbaId, easing);
	}
	config.afterDraw = function(chart, easing) {
		self.@org.pepstock.charba.client.plugins.WrapperPlugin::onAfterDraw(Ljava/lang/String;Ljava/lang/String;)(chart.options.charbaId, easing);
		return;
	}

	// datasets draw
	config.beforeDatasetsDraw = function(chart, easing) {
		return self.@org.pepstock.charba.client.plugins.WrapperPlugin::onBeforeDatasetsDraw(Ljava/lang/String;Ljava/lang/String;)(chart.options.charbaId, easing);
	}
	config.afterDatasetsDraw = function(chart, easing) {
		self.@org.pepstock.charba.client.plugins.WrapperPlugin::onAfterDatasetsDraw(Ljava/lang/String;Ljava/lang/String;)(chart.options.charbaId, easing);
		return;
	}

	// dataset draw
	config.beforeDatasetDraw = function(chart, args, option) {
		return self.@org.pepstock.charba.client.plugins.WrapperPlugin::onBeforeDatasetDraw(Ljava/lang/String;ILjava/lang/String;)(chart.options.charbaId, args.index, args.easingValue);
	}
	config.afterDatasetDraw = function(chart, args, option) {
		self.@org.pepstock.charba.client.plugins.WrapperPlugin::onAfterDatasetDraw(Ljava/lang/String;ILjava/lang/String;)(chart.options.charbaId, args.index, args.easingValue);
		return;
	}

	// tooltip draw
	config.beforeTooltipDraw = function(chart, args, option) {
		return self.@org.pepstock.charba.client.plugins.WrapperPlugin::onBeforeTooltipDraw(Ljava/lang/String;Lorg/pepstock/charba/client/items/TooltipModel;Ljava/lang/String;)(chart.options.charbaId, args.tooltip._model, args.easingValue);
	}
	config.afterTooltipDraw = function(chart, args, option) {
		self.@org.pepstock.charba.client.plugins.WrapperPlugin::onAfterTooltipDraw(Ljava/lang/String;Lorg/pepstock/charba/client/items/TooltipModel;Ljava/lang/String;)(chart.options.charbaId, args.tooltip._model, args.easingValue);
		return;
	}

	// event
	config.beforeEvent = function(chart, event, option) {
		// uses the syntax ["native"] because . syntaz is not accepted 
		return self.@org.pepstock.charba.client.plugins.WrapperPlugin::onBeforeEvent(Ljava/lang/String;Lorg/pepstock/charba/client/events/ChartNativeEvent;)(chart.options.charbaId, event["native"]);
	}
	config.afterEvent = function(chart, event, option) {
		// uses the syntax ["native"] because . syntaz is not accepted
		self.@org.pepstock.charba.client.plugins.WrapperPlugin::onAfterEvent(Ljava/lang/String;Lorg/pepstock/charba/client/events/ChartNativeEvent;)(chart.options.charbaId, event["native"]);
		return;
	}

	// resize
	config.resize = function(chart, size, option) {
		self.@org.pepstock.charba.client.plugins.WrapperPlugin::onResize(Ljava/lang/String;Lorg/pepstock/charba/client/items/SizeItem;)(chart.options.charbaId, size);
		return;
	}


	// destroy
	config.destroy = function(chart, option) {
		self.@org.pepstock.charba.client.plugins.WrapperPlugin::onDestroy(Ljava/lang/String;)(chart.options.charbaId);
		return;
	}


	}-*/;

}
