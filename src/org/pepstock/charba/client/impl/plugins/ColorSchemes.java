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

import java.util.Arrays;
import java.util.List;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.ChartType;
import org.pepstock.charba.client.colors.Color;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.data.BarBorderWidth;
import org.pepstock.charba.client.data.BarDataset;
import org.pepstock.charba.client.data.Dataset;
import org.pepstock.charba.client.data.HasDataPoints;
import org.pepstock.charba.client.data.HovingDataset;
import org.pepstock.charba.client.data.HovingFlexDataset;
import org.pepstock.charba.client.data.LiningDataset;
import org.pepstock.charba.client.enums.DataType;
import org.pepstock.charba.client.impl.plugins.enums.SchemeScope;
import org.pepstock.charba.client.plugins.AbstractPlugin;

/**
 * Default plugin implementation to us color schemes instead the single colors for border and background colors of chart.<br>
 * It enables to pick the color combination for charts from the predefined or custom color schemes.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ColorSchemes extends AbstractPlugin {

	/**
	 * Plugin ID <b>{@value ID}</b>.
	 */
	public static final String ID = "charbacolorschemes";

	/**
	 * Data labels options factory
	 */
	public static final ColorSchemesOptionsFactory FACTORY = new ColorSchemesOptionsFactory(ID);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#getId()
	 */
	@Override
	public String getId() {
		return ID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.plugins.AbstractPlugin#onBeforeUpdate(org.pepstock.charba.client.AbstractChart)
	 */
	@Override
	public boolean onBeforeUpdate(AbstractChart<?, ?> chart) {
		// gets options from chart options
		ColorSchemesOptions options = getOptions(chart);
		// gets scheme
		ColorScheme scheme = options.getScheme();
		// if null, skips all logic
		if (scheme != null) {
			// gets the list of colors
			List<IsColor> colors = scheme.getColors();
			// checks if the list colors is consistent, if not skips the logic
			if (colors != null && !colors.isEmpty()) {
				// gets the amount of colors
				int amountOfColors = colors.size();
				// gets the list of datasets of chart
				List<Dataset> datasets = chart.getData().getDatasets();
				// if dataset list is empty, skips the logic
				if (!datasets.isEmpty()) {
					// initial dataset index
					int datasetIndex = 0;
					// scans all datasets
					for (Dataset dataset : datasets) {
						// get the module for color index
						int colorIndex = datasetIndex % amountOfColors;
						// checks if reverse is requested to get the color
						IsColor color = colors.get(options.isReverse() ? amountOfColors - colorIndex - 1 : colorIndex);
						// if hoving dataset, like PIE, POLAR, DOIUGHNUT
						if (dataset instanceof HovingDataset) {
							// casts the dataset
							HovingDataset hovingDataset = (HovingDataset) dataset;
							// checks if bubble chart because the color will be selected by scheme, as for bar charts
							if (ChartType.bubble.equals(chart.getType()) && SchemeScope.dataset.equals(options.getSchemeScope())){
								// if here is at dataset level
								// every dataset has got own color
								// sets background color (passed as list but it's only 1), applying the transparency
								hovingDataset.setBackgroundColor(getColorsFromData(hovingDataset, Arrays.asList(color), options.isReverse(), options.getBackgroundColorAlpha()));
								// checks if border has been requested
								if (!hovingDataset.getBorderWidth().isEmpty()) {
									// if yes, apply the color (passed as list but it's only 1) to borders properties
									hovingDataset.setBorderColor(getColorsFromData(hovingDataset, Arrays.asList(color), options.isReverse(), Color.DEFAULT_ALPHA));
								}
							} else {
								// sets background colors, applying the transparency
								hovingDataset.setBackgroundColor(getColorsFromData(hovingDataset, colors, options.isReverse(), options.getBackgroundColorAlpha()));
								// checks if border has been requested
								if (!hovingDataset.getBorderWidth().isEmpty()) {
									// if yes, apply the colors to borders properties
									hovingDataset.setBorderColor(getColorsFromData(hovingDataset, colors, options.isReverse(), Color.DEFAULT_ALPHA));
								}
							}
						} else if (dataset instanceof HovingFlexDataset) {
							// if hoving FLEX dataset, like BAR
							HovingFlexDataset hovingDataset = (HovingFlexDataset) dataset;
							// checks if the scope to apply the colors is at data or dataset level
							if (SchemeScope.data.equals(options.getSchemeScope())) {
								// if here is at data level
								// every data has got own color
								hovingDataset.setBackgroundColor(getColorsFromData(hovingDataset, colors, options.isReverse(), options.getBackgroundColorAlpha()));
								// checks if border has been requested
								if (getMaxBorderWidth(hovingDataset) > 0) {
									// if yes, apply the colors to borders properties
									hovingDataset.setBorderColor(getColorsFromData(hovingDataset, colors, options.isReverse(), Color.DEFAULT_ALPHA));
								}
							} else {
								// if here is at dataset level
								// every dataset has got own color
								// sets background colors, applying the transparency
								hovingDataset.setBackgroundColor(color.alpha(options.getBackgroundColorAlpha()));
								// checks if border has been requested
								if (getMaxBorderWidth(hovingDataset) > 0) {
									// if yes, apply the colors to borders properties
									hovingDataset.setBorderColor(color);
								}
							}
						} else if (dataset instanceof LiningDataset) {
							// background color with transparency
							IsColor backgroundColor = color.alpha(options.getBackgroundColorAlpha());
							// if lining dataset, like LINE, RADAR, SCATTER
							LiningDataset liningDataset = (LiningDataset) dataset;
							// sets border color
							liningDataset.setBorderColor(color);
							// sets background colors, applying the transparency
							liningDataset.setBackgroundColor(backgroundColor);
							// sets point hover border color
							liningDataset.setPointHoverBorderColor(color);
							// sets point hover background colors, applying the transparency
							liningDataset.setPointHoverBackgroundColor(backgroundColor);
						}
						// increments dataset index
						datasetIndex++;
					}
				}
			}
		}
		// always true
		return true;
	}

	/**
	 * Returns an array of colors for each data of dataset.
	 * 
	 * @param dataset dataset to update
	 * @param colors list of colors of scheme
	 * @param isReverse if reverse selection is requested
	 * @param alpha transparency value to apply
	 * @return an array of colors for each data of dataset
	 */
	private IsColor[] getColorsFromData(Dataset dataset, List<IsColor> colors, boolean isReverse, double alpha) {
		// gets the amount of colors
		int amountOfColors = colors.size();
		// gets the amount of data
		int amountOfData = 0;
		// gets data checking type
		DataType type = dataset.getDataType();
		// depending on data type, gets the amount of data
		if (DataType.numbers.equals(type)) {
			amountOfData = dataset.getData().size();
		} else if (DataType.points.equals(type) && dataset instanceof HasDataPoints) {
			// ONLY datasets which implements the interface have got the data POINTS 
			HasDataPoints dataPointsDataset = (HasDataPoints)dataset;
			amountOfData = dataPointsDataset.getDataPoints().size();
		}
		// creates an array with the data dimension
		IsColor[] colorsToSet = new IsColor[amountOfData];
		// scans all data
		for (int dataIndex = 0; dataIndex < amountOfData; dataIndex++) {
			// gets the data color index by module with colors size
			int dataColorIndex = dataIndex % amountOfColors;
			// sets the color got from index
			colorsToSet[dataIndex] = colors.get(isReverse ? amountOfColors - dataColorIndex - 1 : dataColorIndex).alpha(alpha);
		}
		// returns array
		return colorsToSet;
	}

	/**
	 * Calculates the maximum border width for hoving flex dataset (BAR).
	 * 
	 * @param hovingDataset dataset to use to calculate
	 * @return the maximum border width defined into dataset
	 */
	private int getMaxBorderWidth(HovingFlexDataset hovingDataset) {
		// gets the list border widths
		List<Integer> borderWidths = hovingDataset.getBorderWidth();
		// sets max
		int maxBorderWidth = 0;
		// if list is not empty
		if (!borderWidths.isEmpty()) {
			// means border widths are defined
			// then scans all to calculate the max
			for (Integer borderWidth : borderWidths) {
				// max
				maxBorderWidth = Math.max(maxBorderWidth, borderWidth);
			}
		} else if (hovingDataset instanceof BarDataset) {
			// if here, the list of border widths is empty
			// but BAR dataset can have BarBorderWidth object to set the border width
			BarDataset barDataset = (BarDataset) hovingDataset;
			// gets border width object
			BarBorderWidth borderWidth = barDataset.getBorderWidthAsItem();
			// calculates the max comparing all dimensions
			maxBorderWidth = Math.max(maxBorderWidth, borderWidth.getTop());
			maxBorderWidth = Math.max(maxBorderWidth, borderWidth.getBottom());
			maxBorderWidth = Math.max(maxBorderWidth, borderWidth.getLeft());
			maxBorderWidth = Math.max(maxBorderWidth, borderWidth.getRight());
		}
		return maxBorderWidth;
	}

	/**
	 * Reads the plugin options at chart level and returns it.
	 * 
	 * @param chart chart instances where to extract options from.
	 * @return the options of plugin
	 */
	private ColorSchemesOptions getOptions(AbstractChart<?, ?> chart) {
		// options instance
		ColorSchemesOptions options = null;
		// creates the plugin options using the java script object
		// passing also the default color set at constructor.
		if (chart.getOptions().getPlugins().hasOptions(ID)) {
			options = chart.getOptions().getPlugins().getOptions(ID, FACTORY);
		} else {
			// no options, creates new one with global/defaults values
			options = new ColorSchemesOptions();
		}
		return options;
	}
}
