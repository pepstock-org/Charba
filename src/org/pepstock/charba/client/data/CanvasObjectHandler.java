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
package org.pepstock.charba.client.data;

import java.util.List;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.items.PluginResizeArgument;
import org.pepstock.charba.client.plugins.SmartPlugin;
import org.pepstock.charba.client.plugins.hooks.BeforeDestroyHook;
import org.pepstock.charba.client.plugins.hooks.ConfigureHook;
import org.pepstock.charba.client.plugins.hooks.ResizeHook;

/**
 * Internal plugin, set by data object before a chart is initializing.<br>
 * This plugin is added to the chart ONLY if the data set is configured to have patterns and gradients.<br>
 * It cleans up where the chart is destroyed or resized.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class CanvasObjectHandler extends SmartPlugin implements ConfigureHook, ResizeHook, BeforeDestroyHook {

	// singleton instance
	private static final CanvasObjectHandler INSTANCE = new CanvasObjectHandler();
	// plugin ID
	static final String ID = "charbacanvasobjecthandler";

	/**
	 * To avoid any instantiation
	 */
	private CanvasObjectHandler() {
		super(ID);
		// stores itself as hook handler
		setConfigureHook(this);
		setResizeHook(this);
		setBeforeDestroyHook(this);
	}

	/**
	 * Returns the singleton instance of plugin.
	 * 
	 * @return the singleton instance of plugin
	 */
	static CanvasObjectHandler get() {
		return INSTANCE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.plugins.hooks.ConfigureHook#onConfigure(org.pepstock.charba.client.IsChart)
	 */
	@Override
	public void onConfigure(IsChart chart) {
		// checks if chart is not consistent
		if (IsChart.isConsistent(chart)) {
			// gets data
			Data data = chart.getData();
			// gets list of data sets
			List<Dataset> datasets = data.getDatasets();
			// clears all patterns and gradients created by callbacks
			for (Dataset dataset : datasets) {
				dataset.clearCallbackPatternsAndGradients();
				// reset flags of change
				dataset.getGradientsContainer().setChanged(false);
				// reset flags of change
				dataset.getPatternsContainer().setChanged(false);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.plugins.hooks.ResizeHook#onResize(org.pepstock.charba.client.IsChart, org.pepstock.charba.client.items.PluginResizeArgument)
	 */
	@Override
	public void onResize(IsChart chart, PluginResizeArgument argument) {
		// checks if chart is consistent
		if (IsChart.isConsistent(chart)) {
			// Due to gradients are created based on dimension of
			// canvas or chart area, every time a resize is occurring
			// gradients must be recreated
			// sets true to changed status in order that
			// during the drawing after resize
			// all gradients will be applied and recreated
			for (Dataset dataset : chart.getData().getDatasets()) {
				// resets all gradients created by callbacks
				dataset.resetCallbackGradients();
			}
			// because gradients must be recreated
			// the cache of gradients must be clear
			DatasetCanvasObjectFactory.get().resetGradients(chart);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.plugins.hooks.BeforeDestroyHook#onBeforeDestroy(org.pepstock.charba.client.IsChart)
	 */
	@Override
	public void onBeforeDestroy(IsChart chart) {
		// checks if arguments are consistent
		if (IsChart.isConsistent(chart)) {
			// because chart is destroy
			// clears the cache of patterns and gradients of the chart
			DatasetCanvasObjectFactory.get().clear(chart);
			// gets data
			Data data = chart.getData();
			// gets list of datasets
			List<Dataset> datasets = data.getDatasets();
			// clears all patterns and gradients created by callbacks
			for (Dataset dataset : datasets) {
				dataset.clearCallbackPatternsAndGradients();
			}
		}
	}

}