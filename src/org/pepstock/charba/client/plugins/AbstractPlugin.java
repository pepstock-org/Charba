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
import org.pepstock.charba.client.events.ChartNativeEvent;
import org.pepstock.charba.client.items.DatasetPluginItem;
import org.pepstock.charba.client.items.SizeItem;
import org.pepstock.charba.client.items.TooltipPluginItem;

/**
 * Implements a plugin interface to help who will create a plugin do not create all methods.<br>
 * The only method not implemented is <code>getId</code> which must implemented.<br>
 * All <code>onBefore*</code> cancellable methods return <code>true</code>.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class AbstractPlugin implements Plugin {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onConfigure(org.pepstock.charba.client.AbstractChart)
	 */
	@Override
	public void onConfigure(AbstractChart<?, ?> chart) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onBeforeInit(org.pepstock.charba.client.AbstractChart)
	 */
	@Override
	public void onBeforeInit(AbstractChart<?, ?> chart) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onAfterInit(org.pepstock.charba.client.AbstractChart)
	 */
	@Override
	public void onAfterInit(AbstractChart<?, ?> chart) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onBeforeUpdate(org.pepstock.charba.client.AbstractChart)
	 */
	@Override
	public boolean onBeforeUpdate(AbstractChart<?, ?> chart) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onAfterUpdate(org.pepstock.charba.client.AbstractChart)
	 */
	@Override
	public void onAfterUpdate(AbstractChart<?, ?> chart) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onBeforeLayout(org.pepstock.charba.client.AbstractChart)
	 */
	@Override
	public boolean onBeforeLayout(AbstractChart<?, ?> chart) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onAfterLayout(org.pepstock.charba.client.AbstractChart)
	 */
	@Override
	public void onAfterLayout(AbstractChart<?, ?> chart) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onBeforeDatasetsUpdate(org.pepstock.charba.client.AbstractChart)
	 */
	@Override
	public boolean onBeforeDatasetsUpdate(AbstractChart<?, ?> chart) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onAfterDatasetsUpdate(org.pepstock.charba.client.AbstractChart)
	 */
	@Override
	public void onAfterDatasetsUpdate(AbstractChart<?, ?> chart) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onBeforeDatasetUpdate(org.pepstock.charba.client.AbstractChart,
	 * org.pepstock.charba.client.items.DatasetPluginItem)
	 */
	@Override
	public boolean onBeforeDatasetUpdate(AbstractChart<?, ?> chart, DatasetPluginItem item) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onAfterDatasetUpdate(org.pepstock.charba.client.AbstractChart,
	 * org.pepstock.charba.client.items.DatasetPluginItem)
	 */
	@Override
	public void onAfterDatasetUpdate(AbstractChart<?, ?> chart, DatasetPluginItem item) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onBeforeRender(org.pepstock.charba.client.AbstractChart)
	 */
	@Override
	public boolean onBeforeRender(AbstractChart<?, ?> chart) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onAfterRender(org.pepstock.charba.client.AbstractChart)
	 */
	@Override
	public void onAfterRender(AbstractChart<?, ?> chart) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onBeforeDraw(org.pepstock.charba.client.AbstractChart, double)
	 */
	@Override
	public boolean onBeforeDraw(AbstractChart<?, ?> chart, double easing) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onAfterDraw(org.pepstock.charba.client.AbstractChart, double)
	 */
	@Override
	public void onAfterDraw(AbstractChart<?, ?> chart, double easing) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onBeforeDatasetsDraw(org.pepstock.charba.client.AbstractChart, double)
	 */
	@Override
	public boolean onBeforeDatasetsDraw(AbstractChart<?, ?> chart, double easing) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onAfterDatasetsDraw(org.pepstock.charba.client.AbstractChart, double)
	 */
	@Override
	public void onAfterDatasetsDraw(AbstractChart<?, ?> chart, double easing) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onBeforeDatasetDraw(org.pepstock.charba.client.AbstractChart,
	 * org.pepstock.charba.client.items.DatasetPluginItem)
	 */
	@Override
	public boolean onBeforeDatasetDraw(AbstractChart<?, ?> chart, DatasetPluginItem item) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onAfterDatasetDraw(org.pepstock.charba.client.AbstractChart,
	 * org.pepstock.charba.client.items.DatasetPluginItem)
	 */
	@Override
	public void onAfterDatasetDraw(AbstractChart<?, ?> chart, DatasetPluginItem item) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onBeforeTooltipDraw(org.pepstock.charba.client.AbstractChart,
	 * org.pepstock.charba.client.items.TooltipPluginItem)
	 */
	@Override
	public boolean onBeforeTooltipDraw(AbstractChart<?, ?> chart, TooltipPluginItem item) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onAfterTooltipDraw(org.pepstock.charba.client.AbstractChart,
	 * org.pepstock.charba.client.items.TooltipPluginItem)
	 */
	@Override
	public void onAfterTooltipDraw(AbstractChart<?, ?> chart, TooltipPluginItem item) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onBeforeEvent(org.pepstock.charba.client.AbstractChart,
	 * org.pepstock.charba.client.events.ChartNativeEvent)
	 */
	@Override
	public boolean onBeforeEvent(AbstractChart<?, ?> chart, ChartNativeEvent event) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onAfterEvent(org.pepstock.charba.client.AbstractChart,
	 * org.pepstock.charba.client.events.ChartNativeEvent)
	 */
	@Override
	public void onAfterEvent(AbstractChart<?, ?> chart, ChartNativeEvent event) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onResize(org.pepstock.charba.client.AbstractChart,
	 * org.pepstock.charba.client.items.SizeItem)
	 */
	@Override
	public void onResize(AbstractChart<?, ?> chart, SizeItem size) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#onDestroy(org.pepstock.charba.client.AbstractChart)
	 */
	@Override
	public void onDestroy(AbstractChart<?, ?> chart) {
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
