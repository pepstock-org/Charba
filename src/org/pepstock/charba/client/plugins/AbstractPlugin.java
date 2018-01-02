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
import org.pepstock.charba.client.items.SizeItem;

public abstract class AbstractPlugin implements Plugin {

	@Override
	public void onBeforeInit(AbstractChart<?, ?> chart) {
	}

	@Override
	public void onAfterInit(AbstractChart<?, ?> chart) {
	}

	@Override
	public boolean onBeforeUpdate(AbstractChart<?, ?> chart) {
		return true;
	}

	@Override
	public void onAfterUpdate(AbstractChart<?, ?> chart) {
	}

	@Override
	public boolean onBeforeLayout(AbstractChart<?, ?> chart) {
		return true;
	}

	@Override
	public void onAfterLayout(AbstractChart<?, ?> chart) {
	}

	@Override
	public boolean onBeforeDatasetsUpdate(AbstractChart<?, ?> chart) {
		return true;
	}

	@Override
	public void onAfterDatasetsUpdate(AbstractChart<?, ?> chart) {
	}

	@Override
	public boolean onBeforeDatasetUpdate(AbstractChart<?, ?> chart, int datasetIndex) {
		return true;
	}

	@Override
	public void onAfterDatasetUpdate(AbstractChart<?, ?> chart, int datasetIndex) {
	}

	@Override
	public boolean onBeforeRender(AbstractChart<?, ?> chart) {
		return true;
	}

	@Override
	public void onAfterRender(AbstractChart<?, ?> chart) {
	}

	@Override
	public boolean onBeforeDraw(AbstractChart<?, ?> chart, double easing) {
		return true;
	}

	@Override
	public void onAfterDraw(AbstractChart<?, ?> chart, double easing) {
	}

	@Override
	public boolean onBeforeDatasetsDraw(AbstractChart<?, ?> chart, double easing) {
		return true;
	}

	@Override
	public void onAfterDatasetsDraw(AbstractChart<?, ?> chart, double easing) {
	}

	@Override
	public boolean onBeforeDatasetDraw(AbstractChart<?, ?> chart, int datasetIndex, double easing) {
		return true;
	}

	@Override
	public void onAfterDatasetDraw(AbstractChart<?, ?> chart, int datasetIndex, double easing) {
	}

	@Override
	public void onBeforeEvent(AbstractChart<?, ?> chart, ChartNativeEvent event) {
	}

	@Override
	public void onAfterEvent(AbstractChart<?, ?> chart, ChartNativeEvent event) {
	}

	@Override
	public void onResize(AbstractChart<?, ?> chart, SizeItem size) {
	}

	@Override
	public void onDestroy(AbstractChart<?, ?> chart) {
	}

	@Override
	public boolean onBeforeTooltipDraw(AbstractChart<?, ?> chart, double easing) {
		return true;
	}

	@Override
	public void onAfterTooltipDraw(AbstractChart<?, ?> chart, double easing) {
	}

}
