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

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.pepstock.charba.client.Chart;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.Plugin;
import org.pepstock.charba.client.items.PluginDatasetArgument;
import org.pepstock.charba.client.items.PluginEventArgument;
import org.pepstock.charba.client.items.PluginResizeArgument;
import org.pepstock.charba.client.items.PluginScaleArgument;
import org.pepstock.charba.client.items.PluginTooltipArgument;
import org.pepstock.charba.client.items.PluginUpdateArgument;

/**
 * Wraps a plugin, delegating the execution of all hooks to it.<br>
 * The wrapper is mandatory to enable catching all hooks of chart even if the plugin implements just a part of the hooks.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class WrapperPlugin extends AbstractBasePlugin {

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
		super(delegation.getId());
		// stores the plugin
		this.delegation = delegation;
		// ------------------------------------
		// -- SET ALL FUNCTIONS in the object ---
		// ------------------------------------
		// sets proxy instance in the afterDataLimits property
		setValue(Property.AFTER_DATA_LIMITS, getAfterDataLimitsCallbackProxy().getProxy());
		// sets proxy instance in the afterBuildTicks property
		setValue(Property.AFTER_BUILD_TICKS, getAfterBuildTicksCallbackProxy().getProxy());
		// sets proxy instance in the afterDatasetDraw property
		setValue(Property.AFTER_DATASET_DRAW, getAfterDatasetDrawCallbackProxy().getProxy());
		// sets proxy instance in the afterDatasetUpdate property
		setValue(Property.AFTER_DATASET_UPDATE, getAfterDatasetUpdateCallbackProxy().getProxy());
		// sets proxy instance in the afterDatasetsDraw property
		setValue(Property.AFTER_DATASETS_DRAW, getAfterDatasetsDrawCallbackProxy().getProxy());
		// sets proxy instance in the afterDatasetsUpdate property
		setValue(Property.AFTER_DATASETS_UPDATE, getAfterDatasetsUpdateCallbackProxy().getProxy());
		// sets proxy instance in the afterDraw property
		setValue(Property.AFTER_DRAW, getAfterDrawCallbackProxy().getProxy());
		// sets proxy instance in the afterEvent property
		setValue(Property.AFTER_EVENT, getAfterEventCallbackProxy().getProxy());
		// sets proxy instance in the afterInit property
		setValue(Property.AFTER_INIT, getAfterInitCallbackProxy().getProxy());
		// sets proxy instance in the afterLayout property
		setValue(Property.AFTER_LAYOUT, getAfterLayoutCallbackProxy().getProxy());
		// sets proxy instance in the afterRender property
		setValue(Property.AFTER_RENDER, getAfterRenderCallbackProxy().getProxy());
		// sets proxy instance in the afterTooltipDraw property
		setValue(Property.AFTER_TOOLTIP_DRAW, getAfterTooltipDrawCallbackProxy().getProxy());
		// sets proxy instance in the afterUpdate property
		setValue(Property.AFTER_UPDATE, getAfterUpdateCallbackProxy().getProxy());
		// sets proxy instance in the beforeDataLimits property
		setValue(Property.BEFORE_DATA_LIMITS, getBeforeDataLimitsCallbackProxy().getProxy());
		// sets proxy instance in the beforeBuildTicks property
		setValue(Property.BEFORE_BUILD_TICKS, getBeforeBuildTicksCallbackProxy().getProxy());
		// sets proxy instance in the beforeDatasetDraw property
		setValue(Property.BEFORE_DATASET_DRAW, getBeforeDatasetDrawCallbackProxy().getProxy());
		// sets proxy instance in the beforeDatasetUpdate property
		setValue(Property.BEFORE_DATASET_UPDATE, getBeforeDatasetUpdateCallbackProxy().getProxy());
		// sets proxy instance in the beforeDatasetsDraw property
		setValue(Property.BEFORE_DATASETS_DRAW, getBeforeDatasetsDrawCallbackProxy().getProxy());
		// sets proxy instance in the beforeDatasetsUpdate property
		setValue(Property.BEFORE_DATASETS_UPDATE, getBeforeDatasetsUpdateCallbackProxy().getProxy());
		// sets proxy instance in the beforeDraw property
		setValue(Property.BEFORE_DRAW, getBeforeDrawCallbackProxy().getProxy());
		// sets proxy instance in the beforeEvent property
		setValue(Property.BEFORE_EVENT, getBeforeEventCallbackProxy().getProxy());
		// sets proxy instance in the beforeInit property
		setValue(Property.BEFORE_INIT, getBeforeInitCallbackProxy().getProxy());
		// sets proxy instance in the beforeLayout property
		setValue(Property.BEFORE_LAYOUT, getBeforeLayoutCallbackProxy().getProxy());
		// sets proxy instance in the beforeRender property
		setValue(Property.BEFORE_RENDER, getBeforeRenderCallbackProxy().getProxy());
		// sets proxy instance in the beforeTooltipDraw property
		setValue(Property.BEFORE_TOOLTIP_DRAW, getBeforeTooltipDrawCallbackProxy().getProxy());
		// sets proxy instance in the beforeUpdate property
		setValue(Property.BEFORE_UPDATE, getBeforeUpdateCallbackProxy().getProxy());
		// sets proxy instance in the before destroy property
		setValue(Property.BEFORE_DESTROY, getBeforeDestroyCallbackProxy().getProxy());
		// sets proxy instance in the after destroy property
		setValue(Property.AFTER_DESTROY, getAfterDestroyCallbackProxy().getProxy());
		// sets proxy instance in the resize property
		setValue(Property.RESIZE, getResizeCallbackProxy().getProxy());
		// sets proxy instance in the reset property
		setValue(Property.RESET, getResetCallbackProxy().getProxy());
		// sets proxy instance in the install property
		setValue(Property.INSTALL, getInstallCallbackProxy().getProxy());
		// sets proxy instance in the start property
		setValue(Property.START, getStartCallbackProxy().getProxy());
		// sets proxy instance in the stop property
		setValue(Property.STOP, getStopCallbackProxy().getProxy());
		// sets proxy instance in the uninstall property
		setValue(Property.UNINSTALL, getUninstallCallbackProxy().getProxy());
	}

	// ----------------------------
	// -- INITIALIZATION ---
	// ----------------------------

	/**
	 * Called before creation of 'chart' java script.
	 * 
	 * @param chart chart instance.
	 */
	@Override
	void invokeConfigure(IsChart chart) {
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
	@Override
	void invokeBeforeInit(IsChart chart) {
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
	@Override
	void invokeAfterInit(IsChart chart, Chart nativeChart) {
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
	@Override
	boolean invokeBeforeUpdate(IsChart chart, PluginUpdateArgument argument) {
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
	@Override
	void invokeAfterUpdate(IsChart chart, PluginUpdateArgument argument) {
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
	@Override
	void invokeBeforeElementsUpdate(IsChart chart) {
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
	@Override
	boolean invokeBeforeLayout(IsChart chart) {
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
	@Override
	void invokeAfterLayout(IsChart chart) {
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
	@Override
	boolean invokeBeforeDatasetsUpdate(IsChart chart, PluginUpdateArgument argument) {
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
	@Override
	void invokeAfterDatasetsUpdate(IsChart chart, PluginUpdateArgument argument) {
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
	@Override
	boolean invokeBeforeDatasetUpdate(IsChart chart, PluginDatasetArgument item) {
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
	@Override
	void invokeAfterDatasetUpdate(IsChart chart, PluginDatasetArgument item) {
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
	@Override
	boolean invokeBeforeRender(IsChart chart) {
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
	@Override
	void invokeAfterRender(IsChart chart) {
		// if consistent, calls plugin
		if (IsChart.isValid(chart)) {
			// invokes after render
			delegation.onAfterRender(chart);
			// checks if counter exists
			if (drawingStatus.containsKey(chart.getId())) {
				// gets the counter
				AtomicInteger counter = drawingStatus.get(chart.getId());
				// greater than 1
				if (counter.intValue() > 0) {
					// invokes the end drawing
					delegation.onEndDrawing(chart);
				}
				// reset to 0
				counter.set(0);
			}
		}
	}

	/**
	 * Called before drawing 'chart' at every animation frame.<br>
	 * If any plugin returns <code>false</code>, the frame drawing is cancelled until another 'render' is triggered.
	 * 
	 * @param chart chart instance
	 * @return <code>false</code> to cancel the chart drawing.
	 */
	@Override
	boolean invokeBeforeDraw(IsChart chart) {
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
	@Override
	void invokeAfterDraw(IsChart chart) {
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
	@Override
	boolean invokeBeforeDatasetsDraw(IsChart chart) {
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
	@Override
	void invokeAfterDatasetsDraw(IsChart chart) {
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
	@Override
	boolean invokeBeforeDatasetDraw(IsChart chart, PluginDatasetArgument item) {
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
	@Override
	void invokeAfterDatasetDraw(IsChart chart, PluginDatasetArgument item) {
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
	@Override
	boolean invokeBeforeTooltipDraw(IsChart chart, PluginTooltipArgument item) {
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
	@Override
	void invokeAfterTooltipDraw(IsChart chart, PluginTooltipArgument item) {
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
	@Override
	boolean invokeBeforeEvent(IsChart chart, PluginEventArgument argument) {
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
	@Override
	void invokeAfterEvent(IsChart chart, PluginEventArgument argument) {
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
	@Override
	void invokeResize(IsChart chart, PluginResizeArgument argument) {
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
	@Override
	void invokeReset(IsChart chart) {
		// if consistent, calls plugin
		if (IsChart.isValid(chart)) {
			// checks if counter is stored
			if (drawingStatus.containsKey(chart.getId())) {
				// gets the counter
				AtomicInteger counter = drawingStatus.get(chart.getId());
				// reset to 0
				counter.set(0);
			}
			// invokes the reset of plugin
			delegation.onReset(chart);
		}
	}

	/**
	 * Called before the chart is being destroyed.
	 * 
	 * @param chart chart instance
	 */
	@Override
	void invokeBeforeDestroy(IsChart chart) {
		// if consistent, calls plugin
		if (IsChart.isValid(chart)) {
			// removes the counter
			drawingStatus.remove(chart.getId());
			// invokes the destroy of plugin
			delegation.onBeforeDestroy(chart);
		}
	}

	/**
	 * Called after the chart has been destroyed.
	 * 
	 * @param chart chart instance
	 */
	@Override
	void invokeAfterDestroy(IsChart chart) {
		// if consistent, calls plugin
		if (IsChart.isValid(chart)) {
			// removes the counter
			drawingStatus.remove(chart.getId());
			// invokes the destroy of plugin
			delegation.onAfterDestroy(chart);
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
	@Override
	void invokeInstall(IsChart chart) {
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
	@Override
	void invokeStart(IsChart chart) {
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
	@Override
	void invokeStop(IsChart chart) {
		// if consistent, calls plugin
		if (IsChart.isValid(chart)) {
			// invokes the stop of plugin
			delegation.onStop(chart);
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
	@Override
	void invokeBeforeDataLimits(IsChart chart, PluginScaleArgument argument) {
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
	@Override
	void invokeAfterDataLimits(IsChart chart, PluginScaleArgument argument) {
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
	@Override
	void invokeBeforeBuildTicks(IsChart chart, PluginScaleArgument argument) {
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
	@Override
	void invokeAfterBuildTicks(IsChart chart, PluginScaleArgument argument) {
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
	@Override
	void invokeUninstall(IsChart chart) {
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