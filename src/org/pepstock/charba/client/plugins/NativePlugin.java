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
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.items.DatasetPluginItem;
import org.pepstock.charba.client.items.EventPluginItem;
import org.pepstock.charba.client.items.SizeItem;
import org.pepstock.charba.client.items.TooltipPluginItem;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Exported java script object which wraps a plugin.<br>
 * This is the object which will be set to CHART.JS to add a plugin.
 * 
 * @author Andrea "Stock" Stocchero
 */
@JsType
final class NativePlugin {

	// this property is not mapped like the exported object
	// of JSINTEROP but it maintains the GWT standard
	private final WrapperPlugin wrapper;

	/**
	 * Creates the object by the plugin wrapper.
	 * 
	 * @param wrapper plugin wrapper.
	 */
	NativePlugin(WrapperPlugin wrapper) {
		this.wrapper = wrapper;
	}

	/**
	 * Plugins must define a unique id in order to be configurable.<br>
	 * Returns the plugin id. A plugin id <br>
	 * <ul>
	 * <li>can not start with a dot or an underscore
	 * <li>can not contain any non-URL-safe characters
	 * <li>cannot contain upper case letters
	 * <li>should be something short, but also reasonably descriptive
	 * </ul>
	 * 
	 * @return the plugin id.
	 */
	@JsProperty
	public native String getId();

	/**
	 * Plugins must define a unique id in order to be configurable.<br>
	 * Returns the plugin id. A plugin id <br>
	 * <ul>
	 * <li>can not start with a dot or an underscore
	 * <li>can not contain any non-URL-safe characters
	 * <li>cannot contain upper case letters
	 * <li>should be something short, but also reasonably descriptive
	 * </ul>
	 * 
	 * @param id the plugin id.
	 */
	@JsProperty
	public native void setId(String id);

	/**
	 * Called before initializing 'chart'.
	 * 
	 * @param chart The chart instance.
	 * @param options plugin options set by user into chart options.
	 */
	@JsMethod
	public void beforeInit(Chart chart, Object options) {
		wrapper.onBeforeInit(chart.getCharbaId());
	}

	/**
	 * Called after 'chart' has been initialized and before the first update.
	 * 
	 * @param chart The chart instance.
	 * @param options plugin options set by user into chart options.
	 */
	@JsMethod
	public void afterInit(Chart chart, Object options) {
		wrapper.onAfterInit(chart.getCharbaId());
	}

	/**
	 * Called before updating 'chart'. If any plugin returns <code>false</code>, the update is cancelled (and thus subsequent
	 * render(s)) until another 'update' is triggered.
	 * 
	 * @param chart The chart instance.
	 * @param options plugin options set by user into chart options.
	 * @return <code>false</code> to cancel the chart update.
	 */
	@JsMethod
	public boolean beforeUpdate(Chart chart, Object options) {
		return wrapper.onBeforeUpdate(chart.getCharbaId());
	}

	/**
	 * Called after 'chart' has been updated and before rendering. Note that this hook will not be called if the chart update
	 * has been previously cancelled.
	 * 
	 * @param chart The chart instance.
	 * @param options plugin options set by user into chart options.
	 */
	@JsMethod
	public void afterUpdate(Chart chart, Object options) {
		wrapper.onAfterUpdate(chart.getCharbaId());
	}

	/**
	 * Called before laying out 'chart'. If any plugin returns <code>false</code>, the layout update is cancelled until another
	 * 'update' is triggered.
	 * 
	 * @param chart The chart instance.
	 * @param options plugin options set by user into chart options.
	 * @return <code>false</code> to cancel the chart layout.
	 */
	@JsMethod
	public boolean beforeLayout(Chart chart, Object options) {
		return wrapper.onBeforeLayout(chart.getCharbaId());
	}

	/**
	 * Called after the 'chart' has been layed out. Note that this hook will not be called if the layout update has been
	 * previously cancelled.
	 * 
	 * @param chart The chart instance.
	 * @param options plugin options set by user into chart options.
	 */
	@JsMethod
	public void afterLayout(Chart chart, Object options) {
		wrapper.onAfterLayout(chart.getCharbaId());
	}

	/**
	 * Called before updating the 'chart' datasets. If any plugin returns <code>false</code>, the datasets update is cancelled
	 * until another 'update' is triggered.
	 * 
	 * @param chart The chart instance.
	 * @param options plugin options set by user into chart options.
	 * @return <code>false</code> to cancel the datasets update.
	 */
	@JsMethod
	public boolean beforeDatasetsUpdate(Chart chart, Object options) {
		return wrapper.onBeforeDatasetsUpdate(chart.getCharbaId());
	}

	/**
	 * Called after the 'chart' datasets have been updated. Note that this hook will not be called if the datasets update has
	 * been previously cancelled.
	 * 
	 * @param chart The chart instance.
	 * @param options plugin options set by user into chart options.
	 */
	@JsMethod
	public void afterDatasetsUpdate(Chart chart, Object options) {
		wrapper.onAfterDatasetsUpdate(chart.getCharbaId());
	}

	/**
	 * Called before updating the 'chart' dataset at the given 'args.index'. If any plugin returns <code>false</code>, the
	 * datasets update is cancelled until another 'update' is triggered.
	 * 
	 * @param chart The chart instance.
	 * @param datasetIndex The dataset index.
	 * @param options plugin options set by user into chart options.
	 * @return <code>false</code> to cancel the chart datasets drawing.
	 */
	@JsMethod
	public boolean beforeDatasetUpdate(Chart chart, NativeObject item, Object options) {
		return wrapper.onBeforeDatasetUpdate(chart.getCharbaId(), new DatasetPluginItem(item));
	}

	/**
	 * Called after the 'chart' datasets at the given 'args.index' has been updated. Note that this hook will not be called if
	 * the datasets update has been previously cancelled.
	 * 
	 * @param chart The chart instance.
	 * @param datasetIndex The dataset index.
	 * @param options plugin options set by user into chart options.
	 */
	@JsMethod
	public void afterDatasetUpdate(Chart chart, NativeObject item, Object options) {
		wrapper.onAfterDatasetUpdate(chart.getCharbaId(), new DatasetPluginItem(item));
	}

	/**
	 * Called before rendering 'chart'. If any plugin returns <code>false</code>, the rendering is cancelled until another
	 * 'render' is triggered.
	 * 
	 * @param chart The chart instance.
	 * @param options plugin options set by user into chart options.
	 * @return <code>false</code> to cancel the chart rendering.
	 */
	@JsMethod
	public boolean beforeRender(Chart chart, Object options) {
		return wrapper.onBeforeRender(chart.getCharbaId());
	}

	/**
	 * Called after the 'chart' has been fully rendered (and animation completed). Note that this hook will not be called if the
	 * rendering has been previously cancelled.
	 * 
	 * @param chart The chart instance.
	 * @param options plugin options set by user into chart options.
	 */
	@JsMethod
	public void afterRender(Chart chart, Object options) {
		wrapper.onAfterRender(chart.getCharbaId());
	}

	/**
	 * Called before drawing 'chart' at every animation frame specified by the given easing value. If any plugin returns
	 * <code>false</code>, the frame drawing is cancelled until another 'render' is triggered.
	 * 
	 * @param chart The chart instance.
	 * @param easing The current animation value, between 0.0 and 1.0.
	 * @param options plugin options set by user into chart options.
	 * @return <code>false</code> to cancel the chart drawing.
	 */
	@JsMethod
	public boolean beforeDraw(Chart chart, double easing, Object options) {
		return wrapper.onBeforeDraw(chart.getCharbaId(), easing);
	}

	/**
	 * Called after the 'chart' has been drawn for the specific easing value. Note that this hook will not be called if the
	 * drawing has been previously cancelled.
	 * 
	 * @param chart The chart instance.
	 * @param easing The current animation value, between 0.0 and 1.0.
	 * @param options plugin options set by user into chart options.
	 */
	@JsMethod
	public void afterDraw(Chart chart, double easing, Object options) {
		wrapper.onAfterDraw(chart.getCharbaId(), easing);
	}

	/**
	 * Called before drawing the 'chart' datasets. If any plugin returns <code>false</code>, the datasets drawing is cancelled
	 * until another 'render' is triggered.
	 * 
	 * @param chart The chart instance.
	 * @param easing The current animation value, between 0.0 and 1.0.
	 * @param options plugin options set by user into chart options.
	 * @return <code>false</code> to cancel the chart datasets drawing.
	 */
	@JsMethod
	public boolean beforeDatasetsDraw(Chart chart, double easing, Object options) {
		return wrapper.onBeforeDatasetsDraw(chart.getCharbaId(), easing);
	}

	/**
	 * Called after the 'chart' datasets have been drawn. Note that this hook will not be called if the datasets drawing has
	 * been previously cancelled.
	 * 
	 * @param chart The chart instance.
	 * @param easing The current animation value, between 0.0 and 1.0.
	 * @param options plugin options set by user into chart options.
	 */
	@JsMethod
	public void afterDatasetsDraw(Chart chart, double easing, Object options) {
		wrapper.onAfterDatasetsDraw(chart.getCharbaId(), easing);
	}

	/**
	 * Called before drawing the 'chart' dataset at the given 'args.index' (datasets are drawn in the reverse order). If any
	 * plugin returns <code>false</code>, the datasets drawing is cancelled until another 'render' is triggered.
	 * 
	 * @param chart The chart instance.
	 * @param datasetIndex The dataset index.
	 * @param easing The current animation value, between 0.0 and 1.0.
	 * @param options plugin options set by user into chart options.
	 * @return <code>false</code> to cancel the chart datasets drawing.
	 */
	@JsMethod
	public boolean beforeDatasetDraw(Chart chart, NativeObject item, Object options) {
		return wrapper.onBeforeDatasetDraw(chart.getCharbaId(), new DatasetPluginItem(item));
	}

	/**
	 * Called after the 'chart' datasets at the given 'args.index' have been drawn (datasets are drawn in the reverse order).
	 * Note that this hook will not be called if the datasets drawing has been previously cancelled.
	 * 
	 * @param chart The chart instance.
	 * @param datasetIndex The dataset index.
	 * @param easing The current animation value, between 0.0 and 1.0.
	 * @param options plugin options set by user into chart options.
	 */
	@JsMethod
	public void afterDatasetDraw(Chart chart, NativeObject item, Object options) {
		wrapper.onAfterDatasetDraw(chart.getCharbaId(), new DatasetPluginItem(item));
	}

	/**
	 * Called before drawing the 'tooltip'. If any plugin returns <code>false</code>, the tooltip drawing is cancelled until
	 * another 'render' is triggered.
	 * 
	 * @param chart The chart instance.
	 * @param tooltip The tooltip instance.
	 * @param easing The current animation value, between 0.0 and 1.0.
	 * @param options plugin options set by user into chart options.
	 * @return <code>false</code> to cancel the chart tooltip drawing.
	 */
	@JsMethod
	public boolean beforeTooltipDraw(Chart chart, NativeObject item, Object options) {
		return wrapper.onBeforeTooltipDraw(chart.getCharbaId(), new TooltipPluginItem(item));
	}

	/**
	 * Called after drawing the 'tooltip'. Note that this hook will not be called if the tooltip drawing has been previously
	 * cancelled.
	 * 
	 * @param chart The chart instance.
	 * @param tooltip The tooltip instance.
	 * @param easing The current animation value, between 0.0 and 1.0.
	 * @param options plugin options set by user into chart options.
	 */
	@JsMethod
	public void afterTooltipDraw(Chart chart, NativeObject item, Object options) {
		wrapper.onAfterTooltipDraw(chart.getCharbaId(), new TooltipPluginItem(item));
	}

	/**
	 * Called before processing the specified 'event'. If any plugin returns <code>false</code>, the event will be discarded.
	 * 
	 * @param chart The chart instance.
	 * @param event The event object.
	 * @param options plugin options set by user into chart options.
	 * @return <code>false</code> to discard the event.
	 */
	@JsMethod
	public boolean beforeEvent(Chart chart, NativeObject item, Object options) {
		return wrapper.onBeforeEvent(chart.getCharbaId(), new EventPluginItem(item));
	}

	/**
	 * Called after the 'event' has been consumed. Note that this hook will not be called if the 'event' has been previously
	 * discarded.
	 * 
	 * @param chart The chart instance.
	 * @param event The event object.
	 * @param options plugin options set by user into chart options.
	 */
	@JsMethod
	public void afterEvent(Chart chart, NativeObject item, Object options) {
		wrapper.onAfterEvent(chart.getCharbaId(), new EventPluginItem(item));
	}

	/**
	 * Called after the chart as been resized.
	 * 
	 * @param chart The chart instance.
	 * @param size The new canvas display size (eq. canvas.style width and height).
	 * @param options plugin options set by user into chart options.
	 */
	@JsMethod
	public void resize(Chart chart, NativeObject size, Object options) {
		wrapper.onResize(chart.getCharbaId(), new SizeItem(size));
	}

	/**
	 * Called after the chart as been destroyed.
	 * 
	 * @param chart The chart instance.
	 * @param options plugin options set by user into chart options.
	 */
	@JsMethod
	public void destroy(Chart chart, Object options) {
		wrapper.onDestroy(chart.getCharbaId());
	}

}
