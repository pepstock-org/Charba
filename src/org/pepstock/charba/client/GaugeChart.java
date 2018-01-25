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

import java.util.List;

import org.pepstock.charba.client.data.Dataset;
import org.pepstock.charba.client.data.GaugeDataset;
import org.pepstock.charba.client.items.ChartItem;
import org.pepstock.charba.client.options.GaugeOptions;
import org.pepstock.charba.client.plugins.AbstractPlugin;
import org.pepstock.charba.client.plugins.InvalidPluginIdException;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * GAUGE chart implementation.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class GaugeChart extends AbstractChart<GaugeOptions, GaugeDataset> {
	
	private static final double DEFAULT_MAX = 100D;
	
	private final GaugeOptions options;
	
	private final GaugeChartLabel plugin = new GaugeChartLabel();
	
	/**
	 * Builds the object.
	 */
	public GaugeChart() {
		options = new GaugeOptions(this);
		try {
			getPlugins().add(plugin);
		} catch (InvalidPluginIdException e) {
			// nop
			e.printStackTrace();
		}
	}

	private final JavaScriptObject getChart(){
		return super.chart;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Chart#getType()
	 */
	@Override
	public Type getType() {
		return Type.doughnut;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Chart#getOptions()
	 */
	@Override
	public GaugeOptions getOptions() {
		return options;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Chart#newDataset()
	 */
	@Override
	public GaugeDataset newDataset() {
		return newDataset(DEFAULT_MAX);
	}

	public GaugeDataset newDataset(double max) {
		return new GaugeDataset(max);
	}
	
	
	private static class GaugeChartLabel extends AbstractPlugin {
	
		/**
		 * Plugin ID 
		 */
		private static final String ID = "meter";
		
		private final CenteredLabelDesigner<GaugeDataset, GaugeOptions> designer = new CenteredLabelDesigner<GaugeDataset, GaugeOptions>();

		GaugeChartLabel() {
			super();
		}

		/* (non-Javadoc)
		 * @see org.pepstock.charba.client.Plugin#getId()
		 */
		@Override
		public String getId() {
			return ID;
		}

		/* (non-Javadoc)
		 * @see org.pepstock.charba.client.plugins.AbstractPlugin#onAfterDatasetsDraw(org.pepstock.charba.client.AbstractChart, double)
		 */
		@Override
		//public void onAfterDatasetsDraw(AbstractChart<?, ?> chart, double easing, JavaScriptObject options) {
		public void onAfterDraw(AbstractChart<?, ?> chart, double easing, JavaScriptObject options) {
			List<Dataset> datasets = chart.getData().getDatasets();
			if (!datasets.isEmpty()){
				GaugeChart mChart = (GaugeChart) chart;
				ChartItem m = (ChartItem)mChart.getChart();
				GaugeDataset dataset = (GaugeDataset) datasets.get(0);
				designer.execute(mChart, m, dataset, mChart.getOptions());
			}
		}
	}
}