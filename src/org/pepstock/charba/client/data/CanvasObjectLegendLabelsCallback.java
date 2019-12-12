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
package org.pepstock.charba.client.data;

import java.util.List;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.callbacks.LegendLabelsCallback;
import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.items.LegendLabelItem;

/**
 * Custom legend labels callback to change the gradients when the a {@link CanvasObjectHandler} plugin has been activated.<br>
 * This callback solved the issue that the legend does not change when gradient are applied to the chart which is already
 * instantiated.<br>
 * If the chart has been configured to have a own callback, this implementation will wrap it and invoke it after updating the
 * legend items.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class CanvasObjectLegendLabelsCallback implements LegendLabelsCallback {

	// user legend labels callback to wrap
	private final LegendLabelsCallback delegated;
	// flag to know if it must retrieves the gradients or not
	// that means the callback is called twice because the plugin
	// performs an update to the chart when gradients must be applied
	private boolean gradientsHandling = false;

	/**
	 * Creaets the legend labels callback, wrapping the passed callback.
	 * 
	 * @param delegated user legend labels callback to wrap
	 */
	CanvasObjectLegendLabelsCallback(LegendLabelsCallback delegated) {
		this.delegated = delegated;
	}

	/**
	 * Returns <code>true</code> if the callback must retrieve the gradients from datasets and apply to the legend.
	 * 
	 * @return <code>true</code> if the callback must retrieve the gradients from datasets and apply to the legend
	 */
	boolean isGradientsHandling() {
		return gradientsHandling;
	}

	/**
	 * Sets <code>true</code> if the callback must retrieve the gradients from datasets and apply to the legend.
	 * 
	 * @param gradientsHandling <code>true</code> if the callback must retrieve the gradients from datasets and apply to the
	 *            legend
	 */
	void setGradientsHandling(boolean gradientsHandling) {
		this.gradientsHandling = gradientsHandling;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.callbacks.LegendLabelsCallback#generateLegendLabels(org.pepstock.charba.client.IsChart,
	 * java.util.List)
	 */
	@Override
	public List<LegendLabelItem> generateLegendLabels(IsChart chart, List<LegendLabelItem> defaultLabels) {
		// checks if the chart is consistent
		// checks if must handle gradients
		if (IsChart.isConsistent(chart) && isGradientsHandling()) {
			// scans all legend items
			for (LegendLabelItem item : defaultLabels) {
				// retrieves the gradient from datasets
				Gradient fillStyle = chart.getData().retrieveFillStyleAsGradient(item);
				// checks if gradient is consistent
				if (fillStyle != null) {
					// then it sets to legend item
					item.setFillStyle(chart, fillStyle);
				}
				// retrieves the gradient from datasets
				Gradient strokeStyle = chart.getData().retrieveStrokeStyleAsGradient(item);
				// checks if gradient is consistent
				if (strokeStyle != null) {
					// then it sets to legend item
					item.setStrokeStyle(chart, strokeStyle);
				}
			}
			// resets the flag
			// the plugin will change it later if needed
			setGradientsHandling(false);
		}
		// checks if delegated callback is consistent
		if (delegated != null) {
			// invokes the custom legend labels callback
			// and returns the values
			return delegated.generateLegendLabels(chart, defaultLabels);
		}
		// returns the result
		return defaultLabels;
	}

}
