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

import org.pepstock.charba.client.events.ChartNativeEvent;
import org.pepstock.charba.client.items.SizeItem;

public interface Plugin  {
	
    /**
	 * Plugin extension hooks.
	 * @interface IPlugin
	 * @since 2.1.0
	 */
	String getId() ;

	/**
	 * @method IPlugin#beforeInit
	 * @desc Called before initializing 'chart'.
	 * @param {Chart.Controller} chart - The chart instance.
	 * @param {Object} options - The plugin options.
	 */
	void onBeforeInit(AbstractChart<?, ?> chart);
  
	/**
	 * @method IPlugin#afterInit
	 * @desc Called after 'chart' has been initialized and before the first update.
	 * @param {Chart.Controller} chart - The chart instance.
	 * @param {Object} options - The plugin options.
	 */
	void onAfterInit(AbstractChart<?, ?> chart);

	/**
	 * @method IPlugin#beforeUpdate
	 * @desc Called before updating 'chart'. If any plugin returns 'false', the update
	 * is cancelled (and thus subsequent render(s)) until another 'update' is triggered.
	 * @param {Chart.Controller} chart - The chart instance.
	 * @param {Object} options - The plugin options.
	 * @returns {Boolean} 'false' to cancel the chart update.
	 */
	boolean onBeforeUpdate(AbstractChart<?, ?> chart);
	
	/**
	 * @method IPlugin#afterUpdate
	 * @desc Called after 'chart' has been updated and before rendering. Note that this
	 * hook will not be called if the chart update has been previously cancelled.
	 * @param {Chart.Controller} chart - The chart instance.
	 * @param {Object} options - The plugin options.
	 */
	void onAfterUpdate(AbstractChart<?, ?> chart);

	/**
	 * @method IPlugin#beforeLayout
	 * @desc Called before laying out 'chart'. If any plugin returns 'false',
	 * the layout update is cancelled until another 'update' is triggered.
	 * @param {Chart.Controller} chart - The chart instance.
	 * @param {Object} options - The plugin options.
	 * @returns {Boolean} 'false' to cancel the chart layout.
	 */
	boolean onBeforeLayout(AbstractChart<?, ?> chart);
	
	/**
	 * @method IPlugin#afterLayout
	 * @desc Called after the 'chart' has been layed out. Note that this hook will not
	 * be called if the layout update has been previously cancelled.
	 * @param {Chart.Controller} chart - The chart instance.
	 * @param {Object} options - The plugin options.
	 */
	void onAfterLayout(AbstractChart<?, ?> chart);

	/**
	 * @method IPlugin#beforeDatasetsUpdate
 	 * @desc Called before updating the 'chart' datasets. If any plugin returns 'false',
	 * the datasets update is cancelled until another 'update' is triggered.
	 * @param {Chart.Controller} chart - The chart instance.
	 * @param {Object} options - The plugin options.
	 * @returns {Boolean} false to cancel the datasets update.
	 * @since version 2.1.5
	 */
	boolean onBeforeDatasetsUpdate(AbstractChart<?, ?> chart);
	
	/**
	 * @method IPlugin#afterDatasetsUpdate
	 * @desc Called after the 'chart' datasets have been updated. Note that this hook
	 * will not be called if the datasets update has been previously cancelled.
	 * @param {Chart.Controller} chart - The chart instance.
	 * @param {Object} options - The plugin options.
	 * @since version 2.1.5
	 */
	void onAfterDatasetsUpdate(AbstractChart<?, ?> chart);

	/**
	 * @method IPlugin#beforeDatasetUpdate
 	 * @desc Called before updating the 'chart' dataset at the given 'args.index'. If any plugin
	 * returns 'false', the datasets update is cancelled until another 'update' is triggered.
	 * @param {Chart} chart - The chart instance.
	 * @param {Object} args - The call arguments.
	 * @param {Number} args.index - The dataset index.
	 * @param {Object} args.meta - The dataset metadata.
	 * @param {Object} options - The plugin options.
	 * @returns {Boolean} 'false' to cancel the chart datasets drawing.
	 */
	boolean onBeforeDatasetUpdate(AbstractChart<?, ?> chart, int datasetIndex);
	
	/**
	 * @method IPlugin#afterDatasetUpdate
 	 * @desc Called after the 'chart' datasets at the given 'args.index' has been updated. Note
	 * that this hook will not be called if the datasets update has been previously cancelled.
	 * @param {Chart} chart - The chart instance.
	 * @param {Object} args - The call arguments.
	 * @param {Number} args.index - The dataset index.
	 * @param {Object} args.meta - The dataset metadata.
	 * @param {Object} options - The plugin options.
	 */
	void onAfterDatasetUpdate(AbstractChart<?, ?> chart, int datasetIndex);

	/**
	 * @method IPlugin#beforeRender
	 * @desc Called before rendering 'chart'. If any plugin returns 'false',
	 * the rendering is cancelled until another 'render' is triggered.
	 * @param {Chart.Controller} chart - The chart instance.
	 * @param {Object} options - The plugin options.
	 * @returns {Boolean} 'false' to cancel the chart rendering.
	 */
	boolean onBeforeRender(AbstractChart<?, ?> chart);
	
	/**
	 * @method IPlugin#afterRender
	 * @desc Called after the 'chart' has been fully rendered (and animation completed). Note
	 * that this hook will not be called if the rendering has been previously cancelled.
	 * @param {Chart.Controller} chart - The chart instance.
	 * @param {Object} options - The plugin options.
	 */
	void onAfterRender(AbstractChart<?, ?> chart);

	/**
	 * @method IPlugin#beforeDraw
	 * @desc Called before drawing 'chart' at every animation frame specified by the given
	 * easing value. If any plugin returns 'false', the frame drawing is cancelled until
	 * another 'render' is triggered.
	 * @param {Chart.Controller} chart - The chart instance.
	 * @param {Number} easingValue - The current animation value, between 0.0 and 1.0.
	 * @param {Object} options - The plugin options.
	 * @returns {Boolean} 'false' to cancel the chart drawing.
	 */
	// FIXME
	boolean onBeforeDraw(AbstractChart<?, ?> chart, double easing);
	
	/**
	 * @method IPlugin#afterDraw
	 * @desc Called after the 'chart' has been drawn for the specific easing value. Note
	 * that this hook will not be called if the drawing has been previously cancelled.
	 * @param {Chart.Controller} chart - The chart instance.
	 * @param {Number} easingValue - The current animation value, between 0.0 and 1.0.
	 * @param {Object} options - The plugin options.
	 */
	//FIXME
	void onAfterDraw(AbstractChart<?, ?> chart, double easing);
	
	/**
	 * @method IPlugin#beforeDatasetsDraw
 	 * @desc Called before drawing the 'chart' datasets. If any plugin returns 'false',
	 * the datasets drawing is cancelled until another 'render' is triggered.
	 * @param {Chart.Controller} chart - The chart instance.
	 * @param {Number} easingValue - The current animation value, between 0.0 and 1.0.
	 * @param {Object} options - The plugin options.
	 * @returns {Boolean} 'false' to cancel the chart datasets drawing.
	 */
	boolean onBeforeDatasetsDraw(AbstractChart<?, ?> chart, double easing);
	
	/**
	 * @method IPlugin#afterDatasetsDraw
	 * @desc Called after the 'chart' datasets have been drawn. Note that this hook
	 * will not be called if the datasets drawing has been previously cancelled.
	 * @param {Chart.Controller} chart - The chart instance.
	 * @param {Number} easingValue - The current animation value, between 0.0 and 1.0.
	 * @param {Object} options - The plugin options.
	 */
	void onAfterDatasetsDraw(AbstractChart<?, ?> chart, double easing);

	/**
	 * @method IPlugin#beforeDatasetDraw
 	 * @desc Called before drawing the 'chart' dataset at the given 'args.index' (datasets
	 * are drawn in the reverse order). If any plugin returns 'false', the datasets drawing
	 * is cancelled until another 'render' is triggered.
	 * @param {Chart} chart - The chart instance.
	 * @param {Object} args - The call arguments.
	 * @param {Number} args.index - The dataset index.
	 * @param {Object} args.meta - The dataset metadata.
	 * @param {Number} args.easingValue - The current animation value, between 0.0 and 1.0.
	 * @param {Object} options - The plugin options.
	 * @returns {Boolean} 'false' to cancel the chart datasets drawing.
	 */
	//FIXME
	boolean onBeforeDatasetDraw(AbstractChart<?, ?> chart, int datasetIndex, double easing);
	
	/**
	 * @method IPlugin#afterDatasetDraw
 	 * @desc Called after the 'chart' datasets at the given 'args.index' have been drawn
	 * (datasets are drawn in the reverse order). Note that this hook will not be called
	 * if the datasets drawing has been previously cancelled.
	 * @param {Chart} chart - The chart instance.
	 * @param {Object} args - The call arguments.
	 * @param {Number} args.index - The dataset index.
	 * @param {Object} args.meta - The dataset metadata.
	 * @param {Number} args.easingValue - The current animation value, between 0.0 and 1.0.
	 * @param {Object} options - The plugin options.
	 */
	// FIXME
	void onAfterDatasetDraw(AbstractChart<?, ?> chart, int datasetIndex, double easing);

	/**
  	 * @method IPlugin#beforeTooltipDraw
	 * @desc Called before drawing the 'tooltip'. If any plugin returns 'false',
	 * the tooltip drawing is cancelled until another 'render' is triggered.
	 * @param {Chart} chart - The chart instance.
	 * @param {Object} args - The call arguments.
	 * @param {Object} args.tooltip - The tooltip.
	 * @param {Number} args.easingValue - The current animation value, between 0.0 and 1.0.
	 * @param {Object} options - The plugin options.
	 * @returns {Boolean} 'false' to cancel the chart tooltip drawing.
  	 */
	boolean onBeforeTooltipDraw(AbstractChart<?, ?> chart, double easing);

	/**
 	 * @method IPlugin#afterTooltipDraw
  	 * @desc Called after drawing the 'tooltip'. Note that this hook will not
 	 * be called if the tooltip drawing has been previously cancelled.
 	 * @param {Chart} chart - The chart instance.
 	 * @param {Object} args - The call arguments.
 	 * @param {Object} args.tooltip - The tooltip.
	 * @param {Number} args.easingValue - The current animation value, between 0.0 and 1.0.
 	 * @param {Object} options - The plugin options.
 	 */
	void onAfterTooltipDraw(AbstractChart<?, ?> chart, double easing);

	/**
	 * @method IPlugin#beforeEvent
 	 * @desc Called before processing the specified 'event'. If any plugin returns 'false',
	 * the event will be discarded.
	 * @param {Chart.Controller} chart - The chart instance.
	 * @param {IEvent} event - The event object.
	 * @param {Object} options - The plugin options.
	 */
	void onBeforeEvent(AbstractChart<?, ?> chart, ChartNativeEvent event);
	
	/**
	 * @method IPlugin#afterEvent
	 * @desc Called after the 'event' has been consumed. Note that this hook
	 * will not be called if the 'event' has been previously discarded.
	 * @param {Chart.Controller} chart - The chart instance.
	 * @param {IEvent} event - The event object.
	 * @param {Object} options - The plugin options.
	 */
	void onAfterEvent(AbstractChart<?, ?> chart, ChartNativeEvent event);
	
	/**
	 * @method IPlugin#resize
	 * @desc Called after the chart as been resized.
	 * @param {Chart.Controller} chart - The chart instance.
	 * @param {Number} size - The new canvas display size (eq. canvas.style width & height).
	 * @param {Object} options - The plugin options.
	 */
    void onResize(AbstractChart<?, ?> chart, SizeItem size);

	/**
	 * @method IPlugin#destroy
	 * @desc Called after the chart as been destroyed.
	 * @param {Chart.Controller} chart - The chart instance.
	 * @param {Object} options - The plugin options.
	 */
    void onDestroy(AbstractChart<?, ?> chart);
}
