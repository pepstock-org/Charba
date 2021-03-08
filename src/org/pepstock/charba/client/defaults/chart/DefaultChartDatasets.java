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
package org.pepstock.charba.client.defaults.chart;

import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.defaults.IsDefaultDatasets;
import org.pepstock.charba.client.defaults.IsDefaultTypedDataset;

/**
 * CHART.JS default values for DATASETS element.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultChartDatasets implements IsDefaultDatasets {

	private final IsDefaultDatasets datasets;

	/**
	 * Creates the object by datasets option element instance.
	 * 
	 * @param datasets datasets option element instance.
	 */
	public DefaultChartDatasets(IsDefaultDatasets datasets) {
		this.datasets = datasets;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultDatasets#get(org.pepstock.charba.client.Type)
	 */
	@Override
	public IsDefaultTypedDataset get(Type type) {
		return datasets.get(type);
	}

}
