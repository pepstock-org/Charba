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
package org.pepstock.charba.client;

import org.pepstock.charba.client.dom.BaseNativeEvent;
import org.pepstock.charba.client.items.DatasetPluginItem;
import org.pepstock.charba.client.items.SizeItem;
import org.pepstock.charba.client.items.TooltipPluginItem;

/**
 * This interface is defining the extension hook for Chart.JS plugin implementation (both for <i>inline</i> and <i>global</i> plugins).<br>
 * Plugins are the most efficient way to customize or change the default behavior of a chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface Plugin {

	/**
	 * Plugins must define a unique id in order to be configurable.<br>
	 * Returns the plugin id. A plugin id <br>
	 * <ul>
	 * <li>can not start with a dot or an underscore
	 * <li>can not contain any non-URL-safe characters
	 * <li>cannot contain upper-case letters
	 * <li>should be something short, but also reasonably descriptive
	 * </ul>
	 * 
	 * @return the plugin id.
	 */
	String getId();

	/**
	 * Called before initializing configuration of 'chart'.
	 * 
	 * @param chart the chart instance.
	 */
	void onConfigure(IsChart chart);

	/**
	 * Called before initializing 'chart'.
	 * 
	 * @param chart the chart instance.
	 */
	void onBeforeInit(IsChart chart);

	/**
	 * Called after 'chart' has been initialized and before the first update.
	 * 
	 * @param chart the chart instance.
	 * @param nativeChart CHART.JS chart instance
	 */
	void onAfterInit(IsChart chart, Chart nativeChart);

	/**
	 * Called before updating 'chart'. If any plugin returns <code>false</code>, the update is cancelled (and thus subsequent render(s)) until another 'update' is triggered.
	 * 
	 * @param chart the chart instance.
	 * @return <code>false</code> to cancel the chart update.
	 */
	boolean onBeforeUpdate(IsChart chart);

	/**
	 * Called after 'chart' has been updated and before rendering. Note that this hook will not be called if the chart update has been previously cancelled.
	 * 
	 * @param chart the chart instance.
	 */
	void onAfterUpdate(IsChart chart);

	/**
	 * Called before laying out 'chart'. If any plugin returns <code>false</code>, the layout update is cancelled until another 'update' is triggered.
	 * 
	 * @param chart the chart instance.
	 * @return <code>false</code> to cancel the chart layout.
	 */
	boolean onBeforeLayout(IsChart chart);

	/**
	 * Called after the 'chart' has been layed out. Note that this hook will not be called if the layout update has been previously cancelled.
	 * 
	 * @param chart the chart instance.
	 */
	void onAfterLayout(IsChart chart);

	/**
	 * Called before updating the 'chart' datasets. If any plugin returns <code>false</code>, the datasets update is cancelled until another 'update' is triggered.
	 * 
	 * @param chart the chart instance.
	 * @return <code>false</code> to cancel the datasets update.
	 */
	boolean onBeforeDatasetsUpdate(IsChart chart);

	/**
	 * Called after the 'chart' datasets have been updated. Note that this hook will not be called if the datasets update has been previously cancelled.
	 * 
	 * @param chart the chart instance.
	 */
	void onAfterDatasetsUpdate(IsChart chart);

	/**
	 * Called before updating the 'chart' dataset at the given 'args.index'. If any plugin returns <code>false</code>, the datasets update is cancelled until another 'update' is
	 * triggered.
	 * 
	 * @param chart the chart instance.
	 * @param item the dataset item.
	 * @return <code>false</code> to cancel the chart datasets drawing.
	 */
	boolean onBeforeDatasetUpdate(IsChart chart, DatasetPluginItem item);

	/**
	 * Called after the 'chart' datasets at the given 'args.index' has been updated. Note that this hook will not be called if the datasets update has been previously cancelled.
	 * 
	 * @param chart the chart instance.
	 * @param item the dataset item.
	 */
	void onAfterDatasetUpdate(IsChart chart, DatasetPluginItem item);

	/**
	 * Called before rendering 'chart'. If any plugin returns <code>false</code>, the rendering is cancelled until another 'render' is triggered.
	 * 
	 * @param chart the chart instance.
	 * @return <code>false</code> to cancel the chart rendering.
	 */
	boolean onBeforeRender(IsChart chart);

	/**
	 * Called after the 'chart' has been fully rendered (and animation completed). Note that this hook will not be called if the rendering has been previously cancelled.
	 * 
	 * @param chart the chart instance.
	 */
	void onAfterRender(IsChart chart);

	/**
	 * Called before drawing 'chart' at every animation frame specified by the given easing value. If any plugin returns <code>false</code>, the frame drawing is cancelled until
	 * another 'render' is triggered.
	 * 
	 * @param chart the chart instance.
	 * @param easing The current animation value, between 0.0 and 1.0.
	 * @return <code>false</code> to cancel the chart drawing.
	 */
	boolean onBeforeDraw(IsChart chart, double easing);

	/**
	 * Called after the 'chart' has been drawn for the specific easing value. Note that this hook will not be called if the drawing has been previously cancelled.
	 * 
	 * @param chart the chart instance.
	 * @param easing The current animation value, between 0.0 and 1.0.
	 */
	void onAfterDraw(IsChart chart, double easing);

	/**
	 * Called before drawing the 'chart' datasets. If any plugin returns <code>false</code>, the datasets drawing is cancelled until another 'render' is triggered.
	 * 
	 * @param chart the chart instance.
	 * @param easing The current animation value, between 0.0 and 1.0.
	 * @return <code>false</code> to cancel the chart datasets drawing.
	 */
	boolean onBeforeDatasetsDraw(IsChart chart, double easing);

	/**
	 * Called after the 'chart' datasets have been drawn. Note that this hook will not be called if the datasets drawing has been previously cancelled.
	 * 
	 * @param chart the chart instance.
	 * @param easing The current animation value, between 0.0 and 1.0.
	 */
	void onAfterDatasetsDraw(IsChart chart, double easing);

	/**
	 * Called before drawing the 'chart' dataset at the given 'args.index' (datasets are drawn in the reverse order). If any plugin returns <code>false</code>, the datasets drawing
	 * is cancelled until another 'render' is triggered.
	 * 
	 * @param chart the chart instance.
	 * @param item the dataset item.
	 * @return <code>false</code> to cancel the chart datasets drawing.
	 */
	boolean onBeforeDatasetDraw(IsChart chart, DatasetPluginItem item);

	/**
	 * Called after the 'chart' datasets at the given 'args.index' have been drawn (datasets are drawn in the reverse order). Note that this hook will not be called if the datasets
	 * drawing has been previously cancelled.
	 * 
	 * @param chart the chart instance.
	 * @param item the dataset item.
	 */
	void onAfterDatasetDraw(IsChart chart, DatasetPluginItem item);

	/**
	 * Called before drawing the 'tooltip'. If any plugin returns <code>false</code>, the tooltip drawing is cancelled until another 'render' is triggered.
	 * 
	 * @param chart the chart instance.
	 * @param item The tooltip instance.
	 * @return <code>false</code> to cancel the chart tooltip drawing.
	 */
	boolean onBeforeTooltipDraw(IsChart chart, TooltipPluginItem item);

	/**
	 * Called after drawing the 'tooltip'. Note that this hook will not be called if the tooltip drawing has been previously cancelled.
	 * 
	 * @param chart the chart instance.
	 * @param item The tooltip instance.
	 */
	void onAfterTooltipDraw(IsChart chart, TooltipPluginItem item);

	/**
	 * Called before processing the specified 'event'. If any plugin returns <code>false</code>, the event will be discarded.
	 * 
	 * @param chart the chart instance.
	 * @param event The event object.
	 * @return <code>false</code> to discard the event.
	 */
	boolean onBeforeEvent(IsChart chart, BaseNativeEvent event);

	/**
	 * Called after the 'event' has been consumed. Note that this hook will not be called if the 'event' has been previously discarded.
	 * 
	 * @param chart the chart instance.
	 * @param event The event object.
	 */
	void onAfterEvent(IsChart chart, BaseNativeEvent event);

	/**
	 * Called after the chart as been resized.
	 * 
	 * @param chart the chart instance.
	 * @param size The new canvas display size (eq. canvas.style width and height).
	 */
	void onResize(IsChart chart, SizeItem size);

	/**
	 * Called after the chart as been destroyed.
	 * 
	 * @param chart the chart instance.
	 */
	void onDestroy(IsChart chart);
}
