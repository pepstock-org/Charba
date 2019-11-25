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
package org.pepstock.charba.client.impl.plugins;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.callbacks.LegendLabelsCallback;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.data.Dataset;
import org.pepstock.charba.client.data.HovingFlexDataset;
import org.pepstock.charba.client.data.LiningDataset;
import org.pepstock.charba.client.items.LegendLabelItem;
import org.pepstock.charba.client.items.UndefinedValues;

/**
 * Custom legend labels callback to change the colors when the a {@link ColorSchemes} plugin has been activated.<br>
 * This callback solved the issue that the legend does not change when colors are applied to the chart which is already
 * instantiated.<br>
 * If the chart has been configured to have a own callback, this implementation will wrap it and invoke it after updating the
 * legend items.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class ColorSchemeLegendCallback implements LegendLabelsCallback {

	// static map to get all legend labels callbacks defined for the chart
	// which will be wrapped by this callback
	private static final Map<String, LegendLabelsCallback> CALLBACKS = new HashMap<>();

	/**
	 * Returns the delegated callback previously set by user.
	 * 
	 * @param chart chart instance
	 * @return the delegated callback previously set by user
	 */
	LegendLabelsCallback getDelegatedCallback(IsChart chart) {
		return CALLBACKS.get(chart.getId());
	}

	/**
	 * Sets the delegated callback previously set by user.
	 * 
	 * @param chart chart instance
	 * @param delegatedCallback the delegated callback previously set by user
	 */
	void setDelegatedCallback(IsChart chart, LegendLabelsCallback delegatedCallback) {
		CALLBACKS.put(chart.getId(), delegatedCallback);
	}

	/**
	 * Removes the delegated callback previously set by user.
	 * 
	 * @param chart chart instance
	 */
	void removeDelegatedCallback(IsChart chart) {
		CALLBACKS.remove(chart.getId());
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
		// and the labels are consistent
		if (IsChart.isValid(chart) && defaultLabels != null && !defaultLabels.isEmpty()) {
			//s cans all legend labels items
			for (LegendLabelItem item : defaultLabels) {
				// gets the dataset related to legend items
				Dataset dataset = chart.getData().retrieveDataset(item);
				// if hoving flex dataset, like PIE, POLAR, DOUGHNUT, BAR or BUBBLE
				if (dataset instanceof HovingFlexDataset) {
					// gets hoving flex dataset
					HovingFlexDataset hovingDataset = (HovingFlexDataset) dataset;
					// manages hoving flex dataset
					manageHovingFlexDataset(chart, hovingDataset, item);
				} else if (dataset instanceof LiningDataset) {
					// if lining dataset, like LINE, RADAR, SCATTER
					LiningDataset liningDataset = (LiningDataset) dataset;
					manageLiningDataset(liningDataset, item);
				}
			}
			// checks if there is a delegated legend labels callback
			LegendLabelsCallback delegatedCallback = CALLBACKS.get(chart.getId());
			// if the legend callback set by user is consistent
			if (delegatedCallback != null) {
				// invokes the legend callback
				return delegatedCallback.generateLegendLabels(chart, defaultLabels);
			}
		}
		// returns the legend labels items
		return defaultLabels;
	}

	/**
	 * Manages the colors for HOVING FLEX datasets.
	 * 
	 * @param chart chart instance
	 * @param hovingFlexDataset hoving flex dataset instance
	 * @param iteme legend item to change applying new colors
	 */
	private void manageHovingFlexDataset(IsChart chart, HovingFlexDataset hovingFlexDataset, LegendLabelItem item) {
		// gets background colors, border colors
		List<IsColor> backgroundColors = hovingFlexDataset.getBackgroundColor();
		List<IsColor> borderColors = hovingFlexDataset.getBackgroundColor();
		// calculates the max border width
		int borderWidth = ColorSchemesUtil.getMaxBorderWidth(hovingFlexDataset);
		// checks if bubble chart
		if (item.getDatasetIndex() != UndefinedValues.INTEGER) {
			// sets background colors
			item.setFillStyle(backgroundColors.get(0));
			// sets border color
			item.setStrokeStyle(borderColors.get(0));
		} else if (item.getIndex() != UndefinedValues.INTEGER) {
			// sets background colors
			item.setFillStyle(backgroundColors.get(item.getIndex()));
			// sets border color
			item.setStrokeStyle(borderColors.get(item.getIndex()));
		}
		// sets border width
		item.setLineWidth(borderWidth);
	}

	/**
	 * Manages the colors for LINING datasets.
	 * 
	 * @param liningDataset lining dataset instance
	 * @param iteme legend item to change applying new colors
	 */
	private void manageLiningDataset(LiningDataset liningDataset, LegendLabelItem item) {
		// sets background colors
		item.setFillStyle(liningDataset.getBackgroundColorAsString());
		// sets border color
		item.setStrokeStyle(liningDataset.getBorderColorAsString());
		// sets border color
		item.setLineWidth(liningDataset.getBorderWidth());
	}

}
