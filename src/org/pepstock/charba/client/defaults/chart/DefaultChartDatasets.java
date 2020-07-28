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

import org.pepstock.charba.client.defaults.IsDefaultAnimation;
import org.pepstock.charba.client.defaults.IsDefaultDatasets;

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
	 * @see org.pepstock.charba.client.defaults.IsDefaultDatasets#getAnimation()
	 */
	@Override
	public IsDefaultAnimation getAnimation() {
		return datasets.getAnimation();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultDatasets#getBarPercentage()
	 */
	@Override
	public double getBarPercentage() {
		return datasets.getBarPercentage();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultDatasets#getCategoryPercentage()
	 */
	@Override
	public double getCategoryPercentage() {
		return datasets.getCategoryPercentage();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultDatasets#getBarThickness()
	 */
	@Override
	public int getBarThickness() {
		return datasets.getBarThickness();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultDatasets#getMaxBarThickness()
	 */
	@Override
	public int getMaxBarThickness() {
		return datasets.getMaxBarThickness();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultDatasets#getMinBarLength()
	 */
	@Override
	public int getMinBarLength() {
		return datasets.getMinBarLength();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultDatasets#isShowLine()
	 */
	@Override
	public boolean isShowLine() {
		return datasets.isShowLine();
	}

}
