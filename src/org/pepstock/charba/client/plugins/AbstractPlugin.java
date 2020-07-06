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
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.Plugin;
import org.pepstock.charba.client.dom.BaseNativeEvent;
import org.pepstock.charba.client.items.DatasetPluginItem;
import org.pepstock.charba.client.items.SizeItem;
import org.pepstock.charba.client.items.TooltipPluginItem;

/**
 * Implements a plugin interface to help who will create a plugin do not create all methods.<br>
 * The only method not implemented is <code>getId</code> which must implemented.<br>
 * All <code>onBefore*</code> cancellable methods return <code>true</code>.
 * 
 * FIXME https://github.com/chartjs/Chart.js/issues/7549
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class AbstractPlugin implements Plugin {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onConfigure(org.pepstock.charba.client.IsChart)
	 */
	@Override
	public void onConfigure(IsChart chart) {
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.Plugin#onBeginDrawing(org.pepstock.charba.client.IsChart, boolean)
	 */
	@Override
	public void onBeginDrawing(IsChart chart, boolean overridePreviousUpdate) {
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.Plugin#onEndDrawing(org.pepstock.charba.client.IsChart)
	 */
	@Override
	public void onEndDrawing(IsChart chart) {
	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onBeforeInit(org.pepstock.charba.client.IsChart)
	 */
	@Override
	public void onBeforeInit(IsChart chart) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onAfterInit(org.pepstock.charba.client.IsChart, org.pepstock.charba.client.Chart)
	 */
	@Override
	public void onAfterInit(IsChart chart, Chart nativeChart) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onBeforeUpdate(org.pepstock.charba.client.IsChart)
	 */
	@Override
	public boolean onBeforeUpdate(IsChart chart) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onAfterUpdate(org.pepstock.charba.client.IsChart)
	 */
	@Override
	public void onAfterUpdate(IsChart chart) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onBeforeLayout(org.pepstock.charba.client.IsChart)
	 */
	@Override
	public boolean onBeforeLayout(IsChart chart) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onAfterLayout(org.pepstock.charba.client.IsChart)
	 */
	@Override
	public void onAfterLayout(IsChart chart) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onBeforeDatasetsUpdate(org.pepstock.charba.client.IsChart)
	 */
	@Override
	public boolean onBeforeDatasetsUpdate(IsChart chart) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onAfterDatasetsUpdate(org.pepstock.charba.client.IsChart)
	 */
	@Override
	public void onAfterDatasetsUpdate(IsChart chart) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onBeforeDatasetUpdate(org.pepstock.charba.client.IsChart, org.pepstock.charba.client.items.DatasetPluginItem)
	 */
	@Override
	public boolean onBeforeDatasetUpdate(IsChart chart, DatasetPluginItem item) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onAfterDatasetUpdate(org.pepstock.charba.client.IsChart, org.pepstock.charba.client.items.DatasetPluginItem)
	 */
	@Override
	public void onAfterDatasetUpdate(IsChart chart, DatasetPluginItem item) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onBeforeRender(org.pepstock.charba.client.IsChart)
	 */
	@Override
	public boolean onBeforeRender(IsChart chart) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onAfterRender(org.pepstock.charba.client.IsChart)
	 */
	@Override
	public void onAfterRender(IsChart chart) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onBeforeDraw(org.pepstock.charba.client.IsChart, double)
	 */
	@Override
	public boolean onBeforeDraw(IsChart chart, double easing) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onAfterDraw(org.pepstock.charba.client.IsChart, double)
	 */
	@Override
	public void onAfterDraw(IsChart chart, double easing) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onBeforeDatasetsDraw(org.pepstock.charba.client.IsChart, double)
	 */
	@Override
	public boolean onBeforeDatasetsDraw(IsChart chart, double easing) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onAfterDatasetsDraw(org.pepstock.charba.client.IsChart, double)
	 */
	@Override
	public void onAfterDatasetsDraw(IsChart chart, double easing) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onBeforeDatasetDraw(org.pepstock.charba.client.IsChart, org.pepstock.charba.client.items.DatasetPluginItem)
	 */
	@Override
	public boolean onBeforeDatasetDraw(IsChart chart, DatasetPluginItem item) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onAfterDatasetDraw(org.pepstock.charba.client.IsChart, org.pepstock.charba.client.items.DatasetPluginItem)
	 */
	@Override
	public void onAfterDatasetDraw(IsChart chart, DatasetPluginItem item) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onBeforeTooltipDraw(org.pepstock.charba.client.IsChart, org.pepstock.charba.client.items.TooltipPluginItem)
	 */
	@Override
	public boolean onBeforeTooltipDraw(IsChart chart, TooltipPluginItem item) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onAfterTooltipDraw(org.pepstock.charba.client.IsChart, org.pepstock.charba.client.items.TooltipPluginItem)
	 */
	@Override
	public void onAfterTooltipDraw(IsChart chart, TooltipPluginItem item) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onBeforeEvent(org.pepstock.charba.client.IsChart, org.pepstock.charba.client.dom.BaseNativeEvent)
	 */
	@Override
	public boolean onBeforeEvent(IsChart chart, BaseNativeEvent event) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onAfterEvent(org.pepstock.charba.client.IsChart, org.pepstock.charba.client.dom.BaseNativeEvent)
	 */
	@Override
	public void onAfterEvent(IsChart chart, BaseNativeEvent event) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onResize(org.pepstock.charba.client.IsChart, org.pepstock.charba.client.items.SizeItem)
	 */
	@Override
	public void onResize(IsChart chart, SizeItem size) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onReset(org.pepstock.charba.client.IsChart)
	 */
	@Override
	public void onReset(IsChart chart) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onDestroy(org.pepstock.charba.client.IsChart)
	 */
	@Override
	public void onDestroy(IsChart chart) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public final String toString() {
		return "Plugin [" + getId() + "]";
	}

}
