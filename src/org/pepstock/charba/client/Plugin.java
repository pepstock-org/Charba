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
package org.pepstock.charba.client;

import org.pepstock.charba.client.items.PluginDatasetArgument;
import org.pepstock.charba.client.items.PluginEventArgument;
import org.pepstock.charba.client.items.PluginResizeArgument;
import org.pepstock.charba.client.items.PluginScaleArgument;
import org.pepstock.charba.client.items.PluginTooltipArgument;
import org.pepstock.charba.client.items.PluginUpdateArgument;

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
	default void onConfigure(IsChart chart) {
	}

	/**
	 * Called before every drawing cycle, coming from initialization, updating or rendering of chart.
	 * 
	 * @param chart the chart instance
	 * @param overridePreviousUpdate if <code>true</code> the drawing was already running.
	 */
	default void onBeginDrawing(IsChart chart, boolean overridePreviousUpdate) {
	}

	/**
	 * Called after every drawing cycle, coming from initialization, updating or rendering of chart.
	 * 
	 * @param chart the chart instance
	 */
	default void onEndDrawing(IsChart chart) {
	}

	/**
	 * Called before initializing 'chart'.
	 * 
	 * @param chart the chart instance.
	 */
	default void onBeforeInit(IsChart chart) {
	}

	/**
	 * Called after 'chart' has been initialized and before the first update.
	 * 
	 * @param chart the chart instance.
	 * @param nativeChart CHART.JS chart instance
	 */
	default void onAfterInit(IsChart chart, Chart nativeChart) {
	}

	/**
	 * Called before updating 'chart'.<br>
	 * If any plugin returns <code>false</code>, the update is cancelled (and thus subsequent render(s)) until another 'update' is triggered.
	 * 
	 * @param chart the chart instance.
	 * @param argument the argument passed for update
	 * @return <code>false</code> to cancel the chart update.
	 */
	default boolean onBeforeUpdate(IsChart chart, PluginUpdateArgument argument) {
		return true;
	}

	/**
	 * Called after 'chart' has been updated and before rendering.<br>
	 * Note that this hook will not be called if the chart update has been previously cancelled.
	 * 
	 * @param chart the chart instance.
	 * @param argument the argument passed for update
	 */
	default void onAfterUpdate(IsChart chart, PluginUpdateArgument argument) {
	}

	/**
	 * Called during the update process, before any chart elements have been created.
	 * 
	 * @param chart the chart instance.
	 */
	default void onBeforeElementsUpdate(IsChart chart) {
	}

	/**
	 * Called before laying out 'chart'.<br>
	 * If any plugin returns <code>false</code>, the layout update is cancelled until another 'update' is triggered.
	 * 
	 * @param chart the chart instance.
	 * @return <code>false</code> to cancel the chart layout.
	 */
	default boolean onBeforeLayout(IsChart chart) {
		return true;
	}

	/**
	 * Called after the 'chart' has been layed out.<br>
	 * Note that this hook will not be called if the layout update has been previously cancelled.
	 * 
	 * @param chart the chart instance.
	 */
	default void onAfterLayout(IsChart chart) {
	}

	/**
	 * Called before updating the 'chart' datasets.<br>
	 * If any plugin returns <code>false</code>, the datasets update is cancelled until another 'update' is triggered.
	 * 
	 * @param chart the chart instance.
	 * @param argument the argument passed for update
	 * @return <code>false</code> to cancel the datasets update.
	 */
	default boolean onBeforeDatasetsUpdate(IsChart chart, PluginUpdateArgument argument) {
		return true;
	}

	/**
	 * Called after the 'chart' datasets have been updated.<br>
	 * Note that this hook will not be called if the datasets update has been previously cancelled.
	 * 
	 * @param chart the chart instance.
	 * @param argument the argument passed for update
	 */
	default void onAfterDatasetsUpdate(IsChart chart, PluginUpdateArgument argument) {
	}

	/**
	 * Called before updating the 'chart' dataset at the given 'args.index'.<br>
	 * If any plugin returns <code>false</code>, the datasets update is cancelled until another 'update' is triggered.
	 * 
	 * @param chart the chart instance.
	 * @param item the dataset item.
	 * @return <code>false</code> to cancel the chart datasets drawing.
	 */
	default boolean onBeforeDatasetUpdate(IsChart chart, PluginDatasetArgument item) {
		return true;
	}

	/**
	 * Called after the 'chart' datasets at the given 'args.index' has been updated.<br>
	 * Note that this hook will not be called if the datasets update has been previously cancelled.
	 * 
	 * @param chart the chart instance.
	 * @param item the dataset item.
	 */
	default void onAfterDatasetUpdate(IsChart chart, PluginDatasetArgument item) {
	}

	/**
	 * Called before rendering 'chart'. <br>
	 * If any plugin returns <code>false</code>, the rendering is cancelled until another 'render' is triggered.
	 * 
	 * @param chart the chart instance.
	 * @return <code>false</code> to cancel the chart rendering.
	 */
	default boolean onBeforeRender(IsChart chart) {
		return true;
	}

	/**
	 * Called after the 'chart' has been fully rendered (and animation completed).<br>
	 * Note that this hook will not be called if the rendering has been previously cancelled.
	 * 
	 * @param chart the chart instance.
	 */
	default void onAfterRender(IsChart chart) {
	}

	/**
	 * Called before drawing 'chart' at every animation frame.<br>
	 * If any plugin returns <code>false</code>, the frame drawing is cancelled until another 'render' is triggered.
	 * 
	 * @param chart the chart instance.
	 * @return <code>false</code> to cancel the chart drawing.
	 */
	default boolean onBeforeDraw(IsChart chart) {
		return true;
	}

	/**
	 * Called after the 'chart' has been drawn.<br>
	 * Note that this hook will not be called if the drawing has been previously cancelled.
	 * 
	 * @param chart the chart instance.
	 */
	default void onAfterDraw(IsChart chart) {
	}

	/**
	 * Called before drawing the 'chart' datasets. <br>
	 * If any plugin returns <code>false</code>, the datasets drawing is cancelled until another 'render' is triggered.
	 * 
	 * @param chart the chart instance.
	 * @return <code>false</code> to cancel the chart datasets drawing.
	 */
	default boolean onBeforeDatasetsDraw(IsChart chart) {
		return true;
	}

	/**
	 * Called after the 'chart' datasets have been drawn.<br>
	 * Note that this hook will not be called if the datasets drawing has been previously cancelled.
	 * 
	 * @param chart the chart instance.
	 */
	default void onAfterDatasetsDraw(IsChart chart) {
	}

	/**
	 * Called before drawing the 'chart' dataset at the given 'args.index' (datasets are drawn in the reverse order).<br>
	 * If any plugin returns <code>false</code>, the datasets drawing is cancelled until another 'render' is triggered.
	 * 
	 * @param chart the chart instance.
	 * @param item the dataset item.
	 * @return <code>false</code> to cancel the chart datasets drawing.
	 */
	default boolean onBeforeDatasetDraw(IsChart chart, PluginDatasetArgument item) {
		return true;
	}

	/**
	 * Called after the 'chart' datasets at the given 'args.index' have been drawn (datasets are drawn in the reverse order).<br>
	 * Note that this hook will not be called if the datasets drawing has been previously cancelled.
	 * 
	 * @param chart the chart instance.
	 * @param item the dataset item.
	 */
	default void onAfterDatasetDraw(IsChart chart, PluginDatasetArgument item) {
	}

	/**
	 * Called before drawing the 'tooltip'.<br>
	 * If any plugin returns <code>false</code>, the tooltip drawing is cancelled until another 'render' is triggered.
	 * 
	 * @param chart the chart instance.
	 * @param item The tooltip instance.
	 * @return <code>false</code> to cancel the chart tooltip drawing.
	 */
	default boolean onBeforeTooltipDraw(IsChart chart, PluginTooltipArgument item) {
		return true;
	}

	/**
	 * Called after drawing the 'tooltip'.<br>
	 * Note that this hook will not be called if the tooltip drawing has been previously cancelled.
	 * 
	 * @param chart the chart instance.
	 * @param item The tooltip instance.
	 */
	default void onAfterTooltipDraw(IsChart chart, PluginTooltipArgument item) {
	}

	/**
	 * Called before processing the specified 'event'.<br>
	 * If any plugin returns <code>false</code>, the event will be discarded.
	 * 
	 * @param chart the chart instance.
	 * @param argument instance which contains event context
	 * @return <code>false</code> to discard the event.
	 */
	default boolean onBeforeEvent(IsChart chart, PluginEventArgument argument) {
		return true;
	}

	/**
	 * Called after the 'event' has been consumed.<br>
	 * Note that this hook will not be called if the 'event' has been previously discarded.
	 * 
	 * @param chart the chart instance.
	 * @param argument instance which contains event context
	 */
	default void onAfterEvent(IsChart chart, PluginEventArgument argument) {
	}

	/**
	 * Called after the chart as been resized.
	 * 
	 * @param chart the chart instance.
	 * @param argument argument of method which contains the new canvas display size (eq. canvas.style width and height).
	 */
	default void onResize(IsChart chart, PluginResizeArgument argument) {
	}

	/**
	 * Called during chart reset.
	 * 
	 * @param chart the chart instance.
	 */
	default void onReset(IsChart chart) {
	}

	/**
	 * Called before the chart is being destroyed.
	 * 
	 * @param chart the chart instance.
	 */
	default void onBeforeDestroy(IsChart chart) {
	}

	/**
	 * Called after the chart has been destroyed.
	 * 
	 * @param chart the chart instance.
	 */
	default void onAfterDestroy(IsChart chart) {
	}

	/**
	 * Called when plugin is installed for this chart instance.<br>
	 * This hook is also invoked for disabled plugins (options equals to false).
	 * 
	 * @param chart the chart instance.
	 */
	default void onInstall(IsChart chart) {
	}

	/**
	 * Called when a plugin is starting.<br>
	 * This happens when chart is created or plugin is enabled.
	 * 
	 * @param chart the chart instance.
	 */
	default void onStart(IsChart chart) {
	}

	/**
	 * Called when a plugin stopping.<br>
	 * This happens when chart is destroyed or plugin is disabled.
	 * 
	 * @param chart the chart instance.
	 */
	default void onStop(IsChart chart) {
	}

	/**
	 * Called after chart is destroyed on all plugins that were installed for that chart.<br>
	 * This hook is also invoked for disabled plugins (options equals to false).
	 * 
	 * @param chart the chart instance.
	 */
	default void onUninstall(IsChart chart) {
	}

	// -----------------------------
	// SCALES
	// -----------------------------

	/**
	 * Called before scale data limits are calculated.<br>
	 * This hook is called separately for each scale in the chart.
	 * 
	 * @param chart the chart instance.
	 * @param argument argument of method which contains the scale instance.
	 */
	default void onBeforeDataLimits(IsChart chart, PluginScaleArgument argument) {
	}

	/**
	 * Called after scale data limits are calculated.<br>
	 * This hook is called separately for each scale in the chart.
	 * 
	 * @param chart the chart instance.
	 * @param argument argument of method which contains the scale instance.
	 */
	default void onAfterDataLimits(IsChart chart, PluginScaleArgument argument) {
	}

	/**
	 * Called before scale builds its ticks.<br>
	 * This hook is called separately for each scale in the chart.
	 * 
	 * @param chart the chart instance.
	 * @param argument argument of method which contains the scale instance.
	 */
	default void onBeforeBuildTicks(IsChart chart, PluginScaleArgument argument) {
	}

	/**
	 * Called after scale has build its ticks.<br>
	 * This hook is called separately for each scale in the chart.
	 * 
	 * @param chart the chart instance.
	 * @param argument argument of method which contains the scale instance.
	 */
	default void onAfterBuildTicks(IsChart chart, PluginScaleArgument argument) {
	}
}