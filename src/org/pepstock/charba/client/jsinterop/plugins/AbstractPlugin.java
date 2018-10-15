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
package org.pepstock.charba.client.jsinterop.plugins;

import org.pepstock.charba.client.jsinterop.AbstractChart;
import org.pepstock.charba.client.jsinterop.Plugin;
import org.pepstock.charba.client.jsinterop.events.ChartNativeEvent;
import org.pepstock.charba.client.jsinterop.items.DatasetPluginItem;
import org.pepstock.charba.client.jsinterop.items.SizeItem;
import org.pepstock.charba.client.jsinterop.items.TooltipPluginItem;

/**
 * Implements a puglin interface to help who will create a plugin do not create all methods.<br>
 * The only method not implemented is <code>getId</code> which must implemented.<br>
 * All <code>onBefore*</code> cancellable methods return <code>true</code>.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class AbstractPlugin implements Plugin {

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.Plugin#onBeforeInit(org.pepstock.charba.client.jsinterop.AbstractChart, java.lang.Object)
	 */
	@Override
	public void onBeforeInit(AbstractChart<?, ?> chart, Object options) {
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.Plugin#onAfterInit(org.pepstock.charba.client.jsinterop.AbstractChart, java.lang.Object)
	 */
	@Override
	public void onAfterInit(AbstractChart<?, ?> chart, Object options) {
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.Plugin#onBeforeUpdate(org.pepstock.charba.client.jsinterop.AbstractChart, java.lang.Object)
	 */
	@Override
	public boolean onBeforeUpdate(AbstractChart<?, ?> chart, Object options) {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.Plugin#onAfterUpdate(org.pepstock.charba.client.jsinterop.AbstractChart, java.lang.Object)
	 */
	@Override
	public void onAfterUpdate(AbstractChart<?, ?> chart, Object options) {
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.Plugin#onBeforeLayout(org.pepstock.charba.client.jsinterop.AbstractChart, java.lang.Object)
	 */
	@Override
	public boolean onBeforeLayout(AbstractChart<?, ?> chart, Object options) {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.Plugin#onAfterLayout(org.pepstock.charba.client.jsinterop.AbstractChart, java.lang.Object)
	 */
	@Override
	public void onAfterLayout(AbstractChart<?, ?> chart, Object options) {
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.Plugin#onBeforeDatasetsUpdate(org.pepstock.charba.client.jsinterop.AbstractChart, java.lang.Object)
	 */
	@Override
	public boolean onBeforeDatasetsUpdate(AbstractChart<?, ?> chart, Object options) {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.Plugin#onAfterDatasetsUpdate(org.pepstock.charba.client.jsinterop.AbstractChart, java.lang.Object)
	 */
	@Override
	public void onAfterDatasetsUpdate(AbstractChart<?, ?> chart, Object options) {
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.Plugin#onBeforeDatasetUpdate(org.pepstock.charba.client.jsinterop.AbstractChart, org.pepstock.charba.client.jsinterop.items.DatasetPluginItem, java.lang.Object)
	 */
	@Override
	public boolean onBeforeDatasetUpdate(AbstractChart<?, ?> chart, DatasetPluginItem item, Object options) {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.Plugin#onAfterDatasetUpdate(org.pepstock.charba.client.jsinterop.AbstractChart, org.pepstock.charba.client.jsinterop.items.DatasetPluginItem, java.lang.Object)
	 */
	@Override
	public void onAfterDatasetUpdate(AbstractChart<?, ?> chart, DatasetPluginItem item, Object options) {
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.Plugin#onBeforeRender(org.pepstock.charba.client.jsinterop.AbstractChart, java.lang.Object)
	 */
	@Override
	public boolean onBeforeRender(AbstractChart<?, ?> chart, Object options) {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.Plugin#onAfterRender(org.pepstock.charba.client.jsinterop.AbstractChart, java.lang.Object)
	 */
	@Override
	public void onAfterRender(AbstractChart<?, ?> chart, Object options) {
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.Plugin#onBeforeDraw(org.pepstock.charba.client.jsinterop.AbstractChart, double, java.lang.Object)
	 */
	@Override
	public boolean onBeforeDraw(AbstractChart<?, ?> chart, double easing, Object options) {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.Plugin#onAfterDraw(org.pepstock.charba.client.jsinterop.AbstractChart, double, java.lang.Object)
	 */
	@Override
	public void onAfterDraw(AbstractChart<?, ?> chart, double easing, Object options) {
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.Plugin#onBeforeDatasetsDraw(org.pepstock.charba.client.jsinterop.AbstractChart, double, java.lang.Object)
	 */
	@Override
	public boolean onBeforeDatasetsDraw(AbstractChart<?, ?> chart, double easing, Object options) {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.Plugin#onAfterDatasetsDraw(org.pepstock.charba.client.jsinterop.AbstractChart, double, java.lang.Object)
	 */
	@Override
	public void onAfterDatasetsDraw(AbstractChart<?, ?> chart, double easing, Object options) {
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.Plugin#onBeforeDatasetDraw(org.pepstock.charba.client.jsinterop.AbstractChart, org.pepstock.charba.client.jsinterop.items.DatasetPluginItem, java.lang.Object)
	 */
	@Override
	public boolean onBeforeDatasetDraw(AbstractChart<?, ?> chart, DatasetPluginItem item, Object options) {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.Plugin#onAfterDatasetDraw(org.pepstock.charba.client.jsinterop.AbstractChart, org.pepstock.charba.client.jsinterop.items.DatasetPluginItem, java.lang.Object)
	 */
	@Override
	public void onAfterDatasetDraw(AbstractChart<?, ?> chart, DatasetPluginItem item, Object options) {
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.Plugin#onBeforeTooltipDraw(org.pepstock.charba.client.jsinterop.AbstractChart, org.pepstock.charba.client.jsinterop.items.TooltipPluginItem, java.lang.Object)
	 */
	@Override
	public boolean onBeforeTooltipDraw(AbstractChart<?, ?> chart, TooltipPluginItem item, Object options) {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.Plugin#onAfterTooltipDraw(org.pepstock.charba.client.jsinterop.AbstractChart, org.pepstock.charba.client.jsinterop.items.TooltipPluginItem, java.lang.Object)
	 */
	@Override
	public void onAfterTooltipDraw(AbstractChart<?, ?> chart, TooltipPluginItem item, Object options) {
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.Plugin#onBeforeEvent(org.pepstock.charba.client.jsinterop.AbstractChart, org.pepstock.charba.client.jsinterop.events.ChartNativeEvent, java.lang.Object)
	 */
	@Override
	public boolean onBeforeEvent(AbstractChart<?, ?> chart, ChartNativeEvent event, Object options) {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.Plugin#onAfterEvent(org.pepstock.charba.client.jsinterop.AbstractChart, org.pepstock.charba.client.jsinterop.events.ChartNativeEvent, java.lang.Object)
	 */
	@Override
	public void onAfterEvent(AbstractChart<?, ?> chart, ChartNativeEvent event, Object options) {
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.Plugin#onResize(org.pepstock.charba.client.jsinterop.AbstractChart, org.pepstock.charba.client.jsinterop.items.SizeItem, java.lang.Object)
	 */
	@Override
	public void onResize(AbstractChart<?, ?> chart, SizeItem size, Object options) {
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.Plugin#onDestroy(org.pepstock.charba.client.jsinterop.AbstractChart, java.lang.Object)
	 */
	@Override
	public void onDestroy(AbstractChart<?, ?> chart, Object options) {
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		return "Plugin ["+getId()+"]";
	}
	
}
