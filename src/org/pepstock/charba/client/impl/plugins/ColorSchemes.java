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

import org.pepstock.charba.client.ChartOptions;
import org.pepstock.charba.client.ChartType;
import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.callbacks.LegendLabelsCallback;
import org.pepstock.charba.client.colors.Color;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.controllers.ControllerType;
import org.pepstock.charba.client.data.Dataset;
import org.pepstock.charba.client.data.HasDataPoints;
import org.pepstock.charba.client.data.HovingDataset;
import org.pepstock.charba.client.data.HovingFlexDataset;
import org.pepstock.charba.client.data.LiningDataset;
import org.pepstock.charba.client.enums.DataType;
import org.pepstock.charba.client.impl.charts.GaugeChart;
import org.pepstock.charba.client.impl.charts.MeterChart;
import org.pepstock.charba.client.impl.plugins.ColorSchemesOptionsFactory.ColorSchemesDefaultsOptionsFactory;
import org.pepstock.charba.client.impl.plugins.enums.SchemeScope;
import org.pepstock.charba.client.plugins.AbstractPlugin;

/**
 * Default plugin implementation to use color schemes instead the single colors for border and background colors of chart.<br>
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
	// defaults global options factory
	static final ColorSchemesDefaultsOptionsFactory DEFAULTS_FACTORY = new ColorSchemesDefaultsOptionsFactory();
	// singleton instance
	private static final ColorSchemes INSTANCE = new ColorSchemes();
	// callback instance for legend to solve the issue when the scheme is changed when a chart is already
	// initialized and legend is not changed
	private final ColorSchemeLegendLabelsCallback pluginLegendLabelsCallback = new ColorSchemeLegendLabelsCallback();

	/**
	 * To avoid any instantiation
	 */
	private ColorSchemes() {
		// do nothing
	}

	/**
	 * Returns the singleton instance of plugin.
	 * 
	 * @return the singleton instance of plugin
	 */
	public static ColorSchemes get() {
		return INSTANCE;
	}

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
	 * @see org.pepstock.charba.client.plugins.AbstractPlugin#onConfigure(org.pepstock.charba.client.IsChart)
	 */
	@Override
	public void onConfigure(IsChart chart) {
		// checks if chart is consistent and if the plugin should be applicable to
		// this chart
		if (IsChart.isConsistent(chart) && mustBeActivated(chart)) {
			// disable the canvas object hanlder because with color scheme
			// you can use ONLY colors
			chart.getData().setCanvasObjectHandling(false);
			// gets the legend labels callback
			// this is done because changing colors by plugin
			// the legend does not change accordingly
			LegendLabelsCallback legendLabelsCallback = chart.getOptions().getLegend().getLabels().getLabelsCallback();
			// checks if the legend callbacks is not a color scheme ones and is consistent
			if (!(legendLabelsCallback instanceof ColorSchemeLegendLabelsCallback) && legendLabelsCallback != null) {
				// uses the color scheme callback to wrap the existing callback
				pluginLegendLabelsCallback.setDelegatedCallback(chart, legendLabelsCallback);
			}
			// applies the color scheme callback to chart
			chart.getOptions().getLegend().getLabels().setLabelsCallback(pluginLegendLabelsCallback);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.plugins.AbstractPlugin#onBeforeUpdate(org.pepstock.charba.client.IsChart)
	 */
	@Override
	public boolean onBeforeUpdate(IsChart chart) {
		// checks if chart is consistent and if the plugin should be applicable to
		// this chart
		if (IsChart.isConsistent(chart) && mustBeActivated(chart)) {
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
					scanDatasets(chart, options, colors);
				}
			}
		}
		// always true
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.plugins.AbstractPlugin#onDestroy(org.pepstock.charba.client.IsChart)
	 */
	@Override
	public void onDestroy(IsChart chart) {
		// checks if chart is consistent and if the plugin should be applicable to
		// this chart
		if (IsChart.isValid(chart) && mustBeActivated(chart)) {
			// clear the color scheme callback cache
			// for this chart
			pluginLegendLabelsCallback.removeDelegatedCallback(chart);
		}
	}

	/**
	 * Checks the type of the chart and if this plugin could be applicable.<br>
	 * The {@link GaugeChart} and {@link MeterChart} are not supported.
	 * 
	 * @param chart chart instance to check
	 * @return <code>true</code> if if this plugin could be applicable
	 */
	private boolean mustBeActivated(IsChart chart) {
		// gets the chart type
		Type type = chart.getType();
		// checks if the type is consistent and is a controller type
		if (type instanceof ControllerType && type.value() != null) {
			// casts to controller type
			ControllerType controllerType = (ControllerType) type;
			// compare to gauge and meter type
			// if equals the plugin is not activated
			return !controllerType.value().equals(GaugeChart.TYPE) && !controllerType.value().equals(MeterChart.TYPE);
		}
		return true;
	}

	/**
	 * Scans all datasets of chart to apply the colors by selected scheme.
	 * 
	 * @param chart chart instance
	 * @param options color scheme plugin options
	 * @param colors list of colors of scheme
	 */
	private void scanDatasets(IsChart chart, ColorSchemesOptions options, List<IsColor> colors) {
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
				// if hoving dataset, like PIE, POLAR, DOUGHNUT
				if (dataset instanceof HovingDataset) {
					// casts the dataset
					HovingDataset hovingDataset = (HovingDataset) dataset;
					// manages hoving dataset
					manageHovingDataset(chart, options, hovingDataset, color, colors);
				} else if (dataset instanceof HovingFlexDataset) {
					// if hoving FLEX dataset, like BAR
					HovingFlexDataset hovingDataset = (HovingFlexDataset) dataset;
					// manages hoving flex dataset
					manageHovingFlexDataset(options, hovingDataset, color, colors);
				} else if (dataset instanceof LiningDataset) {
					// if lining dataset, like LINE, RADAR, SCATTER
					LiningDataset liningDataset = (LiningDataset) dataset;
					manageLiningDataset(options, liningDataset, color);
				}
				// increments dataset index
				datasetIndex++;
			}
		}
	}

	/**
	 * Manages the colors for HOVING datasets.
	 * 
	 * @param chart chart instance
	 * @param options color scheme plugin options
	 * @param hovingDataset hoving dataset instance
	 * @param color color selected by dataset position
	 * @param colors list of colors of scheme
	 */
	private void manageHovingDataset(IsChart chart, ColorSchemesOptions options, HovingDataset hovingDataset, IsColor color, List<IsColor> colors) {
		// checks if bubble chart because the color will be selected by scheme, as for bar charts
		if (ChartType.BUBBLE.equals(chart.getBaseType()) && SchemeScope.DATASET.equals(options.getSchemeScope())) {
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
	}

	/**
	 * Manages the colors for HOVING FLEX datasets.
	 * 
	 * @param options color scheme plugin options
	 * @param hovingDataset hoving flex dataset instance
	 * @param color color selected by dataset position
	 * @param colors list of colors of scheme
	 */
	private void manageHovingFlexDataset(ColorSchemesOptions options, HovingFlexDataset hovingDataset, IsColor color, List<IsColor> colors) {
		// checks if the scope to apply the colors is at data or dataset level
		if (SchemeScope.DATA.equals(options.getSchemeScope())) {
			// if here is at data level
			// every data has got own color
			hovingDataset.setBackgroundColor(getColorsFromData(hovingDataset, colors, options.isReverse(), options.getBackgroundColorAlpha()));
			// checks if border has been requested
			if (ColorSchemesUtil.getMaxBorderWidth(hovingDataset) > 0) {
				// if yes, apply the colors to borders properties
				hovingDataset.setBorderColor(getColorsFromData(hovingDataset, colors, options.isReverse(), Color.DEFAULT_ALPHA));
			}
		} else {
			// if here is at dataset level
			// every dataset has got own color
			// sets background colors, applying the transparency
			hovingDataset.setBackgroundColor(color.alpha(options.getBackgroundColorAlpha()));
			// checks if border has been requested
			if (ColorSchemesUtil.getMaxBorderWidth(hovingDataset) > 0) {
				// if yes, apply the colors to borders properties
				hovingDataset.setBorderColor(color);
			}
		}
	}

	/**
	 * Manages the colors for LINING datasets.
	 * 
	 * @param options color scheme plugin options
	 * @param liningDataset lining dataset instance
	 * @param color color selected by dataset position
	 */
	private void manageLiningDataset(ColorSchemesOptions options, LiningDataset liningDataset, IsColor color) {
		// background color with transparency
		IsColor backgroundColor = color.alpha(options.getBackgroundColorAlpha());
		// sets border color
		liningDataset.setBorderColor(color);
		// sets background colors, applying the transparency
		liningDataset.setBackgroundColor(backgroundColor);
		// sets point hover border color
		liningDataset.setPointHoverBorderColor(color);
		// sets point hover background colors, applying the transparency
		liningDataset.setPointHoverBackgroundColor(backgroundColor);

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
		if (DataType.NUMBERS.equals(type)) {
			amountOfData = dataset.getData().size();
		} else if (DataType.POINTS.equals(type) && dataset instanceof HasDataPoints) {
			// ONLY datasets which implements the interface have got the data POINTS
			HasDataPoints dataPointsDataset = (HasDataPoints) dataset;
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
	 * Reads the plugin options at chart level and returns it.
	 * 
	 * @param chart chart instances where to extract options from.
	 * @return the options of plugin
	 */
	private ColorSchemesOptions getOptions(IsChart chart) {
		// options instance
		ColorSchemesOptions pOptions = null;
		// loads chart options for the chart
		ChartOptions options = Defaults.get().getOptions(chart);
		// creates the plugin options using the java script object
		// passing also the default color set at constructor.
		if (options.getPlugins().hasOptions(ID)) {
			pOptions = options.getPlugins().getOptions(ID, FACTORY);
		} else {
			// no options, creates new one with global/defaults values
			pOptions = new ColorSchemesOptions(options.getPlugins().getOptions(ID, DEFAULTS_FACTORY));
		}
		return pOptions;
	}
}
