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
import org.pepstock.charba.client.data.MeterDataset;
import org.pepstock.charba.client.items.ChartItem;
import org.pepstock.charba.client.options.MeterOptions;
import org.pepstock.charba.client.plugins.AbstractPlugin;
import org.pepstock.charba.client.plugins.InvalidPluginIdException;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * METER chart implementation.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class MeterChart extends AbstractChart<MeterOptions, MeterDataset> {

	private static final double DEFAULT_MAX = 100D;
	
	private final MeterOptions options;
	
	private final MeterChartLabel plugin = new MeterChartLabel();
	
	/**
	 * Builds the object.
	 */
	public MeterChart() {
		options = new MeterOptions(this);
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
	public MeterOptions getOptions() {
		return options;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Chart#newDataset()
	 */
	@Override
	public MeterDataset newDataset() {
		return newDataset(DEFAULT_MAX);
	}

	public MeterDataset newDataset(double max) {
		return new MeterDataset(max);
	}
	
	private static class MeterChartLabel extends AbstractPlugin {
		
		/**
		 * Plugin ID 
		 */
		private static final String ID = "meter";
		
		private final CenteredLabelDesigner<MeterDataset, MeterOptions> designer = new CenteredLabelDesigner<MeterDataset, MeterOptions>();

		MeterChartLabel() {
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
				MeterChart mChart = (MeterChart) chart;
				ChartItem m = (ChartItem)mChart.getChart();
				MeterDataset dataset = (MeterDataset) datasets.get(0);
				designer.execute(mChart, m, dataset, mChart.getOptions());
			}
		}
	}
}