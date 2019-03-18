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
import org.pepstock.charba.client.items.DatasetPluginItem;
import org.pepstock.charba.client.items.EventPluginItem;
import org.pepstock.charba.client.items.SizeItem;
import org.pepstock.charba.client.items.TooltipPluginItem;

/**
 * Wraps a plugin, delegating the execution of all hooks to it.<br>
 * The wrapper is mandatory to able to catch all hooks of chart even if the plugin implements just a part of the hooks.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class WrapperPlugin {
	// user plugin implementation
	private final Plugin delegation;
	// native object which will be added to chart.js
	private final NativePlugin nativeObject;

	/**
	 * Builds the object with plugin instance
	 * 
	 * @param delegation plugin instance
	 */
	WrapperPlugin(Plugin delegation) {
		// stores the plugin
		this.delegation = delegation;
		// sets the plugin ID
		nativeObject = new NativePlugin(this);
		nativeObject.setId(delegation.getId());
	}

//	/**
//	 * Returns the chart instances which must be consumed by plugin.
//	 * 
//	 * @param chartId chart id.
//	 * @return the chart instance or <code>null</code> if not found.
//	 */
//	abstract AbstractChart<?, ?> getChart(String chartId);

	/**
	 * Returns the plugin id.
	 * 
	 * @return the plugin id.
	 */
	final String getId() {
		return delegation.getId();
	}

	/**
	 * Returns the native java script object.
	 * 
	 * @return the nativeObject
	 */
	NativePlugin getNativeObject() {
		return nativeObject;
	}

	/**
	 * Called before creation of 'chart' java script.
	 * 
	 * @param chart chart instance.
	 */
	protected void onConfigure(AbstractChart<?, ?> chart) {
		// if consistent, calls plugin
		if (chart != null) {
			delegation.onConfigure(chart);
		}
	}

	/**
	 * Called before initializing 'chart'.
	 * 
	 * @param chartId chart id.
	 */
	protected void onBeforeInit(AbstractChart<?, ?> chart) {
		// if consistent, calls plugin
		if (chart != null) {
			delegation.onBeforeInit(chart);
		}
	}

	/**
	 * Called after 'chart' has been initialized and before the first update.
	 * 
	 * @param chartId chart id.
	 */
	protected void onAfterInit(AbstractChart<?, ?> chart) {
		// if consistent, calls plugin
		if (chart != null) {
			delegation.onAfterInit(chart);
		}
	}

	/**
	 * Called before updating 'chart'. If any plugin returns <code>false</code>, the update is cancelled (and thus subsequent
	 * render(s)) until another 'update' is triggered.
	 * 
	 * @param chartId chart id.
	 * @return <code>false</code> to cancel the chart update.
	 */
	protected boolean onBeforeUpdate(AbstractChart<?, ?> chart) {
		// if consistent, calls plugin
		if (chart != null) {
			return delegation.onBeforeUpdate(chart);
		}
		return true;
	}

	/**
	 * Called after 'chart' has been updated and before rendering. Note that this hook will not be called if the chart update
	 * has been previously cancelled.
	 * 
	 * @param chartId chart id.
	 */
	protected void onAfterUpdate(AbstractChart<?, ?> chart) {
		// if consistent, calls plugin
		if (chart != null) {
			delegation.onAfterUpdate(chart);
		}
	}

	/**
	 * Called before laying out 'chart'. If any plugin returns <code>false</code>, the layout update is cancelled until another
	 * 'update' is triggered.
	 * 
	 * @param chartId chart id.
	 * @return <code>false</code> to cancel the chart layout.
	 */
	protected boolean onBeforeLayout(AbstractChart<?, ?> chart) {
		// if consistent
		if (chart != null) {
			// calls plugin
			return delegation.onBeforeLayout(chart);
		}
		return true;
	}

	/**
	 * Called after the 'chart' has been layed out. Note that this hook will not be called if the layout update has been
	 * previously cancelled.
	 * 
	 * @param chartId chart id.
	 */
	protected void onAfterLayout(AbstractChart<?, ?> chart) {
		// if consistent, calls plugin
		if (chart != null) {
			delegation.onAfterLayout(chart);
		}
	}

	/**
	 * Called before updating the 'chart' datasets. If any plugin returns <code>false</code>, the datasets update is cancelled
	 * until another 'update' is triggered.
	 * 
	 * @param chartId chart id.
	 * @return <code>false</code> to cancel the datasets update.
	 */
	protected boolean onBeforeDatasetsUpdate(AbstractChart<?, ?> chart) {
		// if consistent, calls plugin
		if (chart != null) {
			return delegation.onBeforeDatasetsUpdate(chart);
		}
		return true;
	}

	/**
	 * Called after the 'chart' datasets have been updated. Note that this hook will not be called if the datasets update has
	 * been previously cancelled.
	 * 
	 * @param chartId chart id.
	 */
	protected void onAfterDatasetsUpdate(AbstractChart<?, ?> chart) {
		// if consistent, calls plugin
		if (chart != null) {
			delegation.onAfterDatasetsUpdate(chart);
		}
	}

	/**
	 * Called before updating the 'chart' dataset at the given 'args.index'. If any plugin returns <code>false</code>, the
	 * datasets update is cancelled until another 'update' is triggered.
	 * 
	 * @param chartId chart id.
	 * @param datasetIndex The dataset index.
	 * @return <code>false</code> to cancel the chart datasets drawing.
	 */
	protected boolean onBeforeDatasetUpdate(AbstractChart<?, ?> chart, DatasetPluginItem item) {
		// if consistent, calls plugin
		if (chart != null) {
			return delegation.onBeforeDatasetUpdate(chart, item);
		}
		return true;
	}

	/**
	 * Called after the 'chart' datasets at the given 'args.index' has been updated. Note that this hook will not be called if
	 * the datasets update has been previously cancelled.
	 * 
	 * @param chartId chart id.
	 * @param datasetIndex The dataset index.
	 */
	protected void onAfterDatasetUpdate(AbstractChart<?, ?> chart, DatasetPluginItem item) {
		// if consistent, calls plugin
		if (chart != null) {
			delegation.onAfterDatasetUpdate(chart, item);
		}
	}

	/**
	 * Called before rendering 'chart'. If any plugin returns <code>false</code>, the rendering is cancelled until another
	 * 'render' is triggered.
	 * 
	 * @param chartId chart id.
	 * @return <code>false</code> to cancel the chart rendering.
	 */
	protected boolean onBeforeRender(AbstractChart<?, ?> chart) {
		// if consistent, calls plugin
		if (chart != null) {
			return delegation.onBeforeRender(chart);
		}
		return true;
	}

	/**
	 * Called after the 'chart' has been fully rendered (and animation completed). Note that this hook will not be called if the
	 * rendering has been previously cancelled.
	 * 
	 * @param chartId chart id.
	 */
	protected void onAfterRender(AbstractChart<?, ?> chart) {
		// if consistent, calls plugin
		if (chart != null) {
			delegation.onAfterRender(chart);
		}
	}

	/**
	 * Called before drawing 'chart' at every animation frame specified by the given easing value. If any plugin returns
	 * <code>false</code>, the frame drawing is cancelled until another 'render' is triggered.
	 * 
	 * @param chartId chart id.
	 * @param easing The current animation value, between 0.0 and 1.0.
	 * @return <code>false</code> to cancel the chart drawing.
	 */
	protected boolean onBeforeDraw(AbstractChart<?, ?> chart, double easing) {
		// if consistent, calls plugin
		if (chart != null) {
			return delegation.onBeforeDraw(chart, easing);
		}
		return true;
	}

	/**
	 * Called after the 'chart' has been drawn for the specific easing value. Note that this hook will not be called if the
	 * drawing has been previously cancelled.
	 * 
	 * @param chartId chart id.
	 * @param easing The current animation value, between 0.0 and 1.0.
	 */
	protected void onAfterDraw(AbstractChart<?, ?> chart, double easing) {
		// if consistent, calls plugin
		if (chart != null) {
			delegation.onAfterDraw(chart, easing);
		}
	}

	/**
	 * Called before drawing the 'chart' datasets. If any plugin returns <code>false</code>, the datasets drawing is cancelled
	 * until another 'render' is triggered.
	 * 
	 * @param chartId chart id.
	 * @param easing The current animation value, between 0.0 and 1.0.
	 * @return <code>false</code> to cancel the chart datasets drawing.
	 */
	protected boolean onBeforeDatasetsDraw(AbstractChart<?, ?> chart, double easing) {
		// if consistent, calls plugin
		if (chart != null) {
			return delegation.onBeforeDatasetsDraw(chart, easing);
		}
		return true;
	}

	/**
	 * Called after the 'chart' datasets have been drawn. Note that this hook will not be called if the datasets drawing has
	 * been previously cancelled.
	 * 
	 * @param chartId chart id.
	 * @param easing The current animation value, between 0.0 and 1.0.
	 */
	protected void onAfterDatasetsDraw(AbstractChart<?, ?> chart, double easing) {
		// if consistent, calls plugin
		if (chart != null) {
			delegation.onAfterDatasetsDraw(chart, easing);
		}
	}

	/**
	 * Called before drawing the 'chart' dataset at the given 'args.index' (datasets are drawn in the reverse order). If any
	 * plugin returns <code>false</code>, the datasets drawing is cancelled until another 'render' is triggered.
	 * 
	 * @param chartId chart id.
	 * @param datasetIndex The dataset index.
	 * @param easing The current animation value, between 0.0 and 1.0.
	 * @return <code>false</code> to cancel the chart datasets drawing.
	 */
	protected boolean onBeforeDatasetDraw(AbstractChart<?, ?> chart, DatasetPluginItem item) {
		// if consistent, calls plugin
		if (chart != null) {
			return delegation.onBeforeDatasetDraw(chart, item);
		}
		return true;
	}

	/**
	 * Called after the 'chart' datasets at the given 'args.index' have been drawn (datasets are drawn in the reverse order).
	 * Note that this hook will not be called if the datasets drawing has been previously cancelled.
	 * 
	 * @param chartId chart id.
	 * @param datasetIndex The dataset index.
	 * @param easing The current animation value, between 0.0 and 1.0.
	 */
	protected void onAfterDatasetDraw(AbstractChart<?, ?> chart, DatasetPluginItem item) {
		// if consistent, calls plugin
		if (chart != null) {
			delegation.onAfterDatasetDraw(chart, item);
		}
	}

	/**
	 * Called before drawing the 'tooltip'. If any plugin returns <code>false</code>, the tooltip drawing is cancelled until
	 * another 'render' is triggered.
	 * 
	 * @param chartId chart id.
	 * @param object The tooltip model instance as java script object.
	 * @param easing The current animation value, between 0.0 and 1.0.
	 * @return <code>false</code> to cancel the chart tooltip drawing.
	 */
	protected boolean onBeforeTooltipDraw(AbstractChart<?, ?> chart, TooltipPluginItem item) {
		// if consistent, calls plugin
		if (chart != null) {
			return delegation.onBeforeTooltipDraw(chart, item);
		}
		return true;
	}

	/**
	 * Called after drawing the 'tooltip'. Note that this hook will not be called if the tooltip drawing has been previously
	 * cancelled.
	 * 
	 * @param chartId chart id.
	 * @param object The tooltip model instance as java script object.
	 * @param easing The current animation value, between 0.0 and 1.0.
	 */
	protected void onAfterTooltipDraw(AbstractChart<?, ?> chart, TooltipPluginItem item) {
		// if consistent, calls plugin
		if (chart != null) {
			delegation.onAfterTooltipDraw(chart, item);
		}
	}

	/**
	 * Called before processing the specified 'event'. If any plugin returns <code>false</code>, the event will be discarded.
	 * 
	 * @param chartId chart id.
	 * @param event The event object wrapper.
	 * @return <code>false</code> to discard the event.
	 */
	protected boolean onBeforeEvent(AbstractChart<?, ?> chart, EventPluginItem event) {
		// if consistent, both chart and event, calls plugin
		if (chart != null && event.getEvent() != null) {
			return delegation.onBeforeEvent(chart, event.getEvent());
		}
		return true;
	}

	/**
	 * Called after the 'event' has been consumed. Note that this hook will not be called if the 'event' has been previously
	 * discarded.
	 * 
	 * @param chartId chart id.
	 * @param event The event object wrapper.
	 */
	protected void onAfterEvent(AbstractChart<?, ?> chart, EventPluginItem event) {
		// if consistent, both chart and event, calls plugin
		if (chart != null && event.getEvent() != null) {
			delegation.onAfterEvent(chart, event.getEvent());
		}
	}

	/**
	 * Called after the chart as been resized.
	 * 
	 * @param chartId chart id.
	 * @param item The new canvas display size (eq. canvas.style width & height).
	 */
	protected void onResize(AbstractChart<?, ?> chart, SizeItem item) {
		// if consistent, calls plugin
		if (chart != null) {
			delegation.onResize(chart, item);
		}
	}

	/**
	 * Called after the chart as been destroyed.
	 * 
	 * @param chartId chart id.
	 */
	protected void onDestroy(AbstractChart<?, ?> chart) {
		// if consistent, calls plugin
		if (chart != null) {
			delegation.onDestroy(chart);
		}
	}

}
