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

import org.pepstock.charba.client.defaults.IsDefaultScaledOptions;
import org.pepstock.charba.client.defaults.globals.DefaultsBuilder;
import org.pepstock.charba.client.items.ChartAreaNode;
import org.pepstock.charba.client.items.LegendNode;
import org.pepstock.charba.client.items.OptionsNode;
import org.pepstock.charba.client.items.ScalesNode;
import org.pepstock.charba.client.items.TitleNode;
import org.pepstock.charba.client.items.TooltipNode;
import org.pepstock.charba.client.items.UndefinedValues;
import org.pepstock.charba.client.utils.JSON;

/**
 * This is a wrapper of CHART.JS CHART instance in order to provide all properties of chart java script instance, set at runtime.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class ChartNode {

	// all sub elements
	private final Chart chart;

	private final OptionsNode options;

	private final LegendNode legend;

	private final ScalesNode scales;

	private final ChartAreaNode chartArea;

	private final TitleNode title;

	private final TooltipNode tooltip;

	private final boolean initialized;

	/**
	 * Creates the object wrapping a CHART instance.
	 *
	 * @param chart CHART.JS CHART instance
	 */
	public ChartNode(Chart chart) {
		// stores native chart instance
		this.chart = chart;
		// sets if is initialized checking the CHART instance
		initialized = chart != null;
		// gets the defaults for options
		IsDefaultScaledOptions defaultValues = initialized ? chart.getChart().getDefaultChartOptions() : DefaultsBuilder.get().getScaledOptions();
		// creates all sub elements
		options = new OptionsNode(defaultValues, initialized ? chart.getOptions() : null);
		legend = new LegendNode(initialized ? chart.getLegend() : null);
		scales = new ScalesNode(initialized ? chart.getScales() : null);
		chartArea = new ChartAreaNode(initialized ? chart.getChartArea() : null);
		title = new TitleNode(initialized ? chart.getTitleBlock() : null);
		tooltip = new TooltipNode(initialized ? chart.getTooltip() : null);
	}

	/**
	 * Returns if CHART.JS chart instance has been initialized.
	 * 
	 * @return <code>true</code> if initialized, otherwise <code>false</code>
	 */
	public boolean isInitialized() {
		return initialized;
	}

	/**
	 * Returns the options item.
	 * 
	 * @return the options item.
	 */
	public OptionsNode getOptions() {
		return options;
	}

	/**
	 * Returns the legend item.
	 * 
	 * @return the legend item.
	 */
	public LegendNode getLegend() {
		return legend;
	}

	/**
	 * Returns the scales item.
	 * 
	 * @return the scales item.
	 */
	public ScalesNode getScales() {
		return scales;
	}

	/**
	 * Returns the chart area item.
	 * 
	 * @return the chart area item.
	 */
	public ChartAreaNode getChartArea() {
		return chartArea;
	}

	/**
	 * Returns the title item.
	 * 
	 * @return the title item.
	 */
	public TitleNode getTitle() {
		return title;
	}

	/**
	 * Returns the tooltip item.
	 * 
	 * @return the tooltip item.
	 */
	public TooltipNode getTooltip() {
		return tooltip;
	}

	/**
	 * Returns the CHART JS chart ID.
	 * 
	 * @return the CHART JS chart ID. Default is {@link UndefinedValues#INTEGER}.
	 */
	public int getId() {
		return initialized ? check(chart.getId(), UndefinedValues.INTEGER) : UndefinedValues.INTEGER;
	}

	/**
	 * Returns the width in pixel.
	 * 
	 * @return the width in pixel. Default is {@link UndefinedValues#INTEGER}.
	 */
	public int getWidth() {
		return initialized ? check(chart.getWidth(), UndefinedValues.INTEGER) : UndefinedValues.INTEGER;
	}

	/**
	 * Returns the height in pixel.
	 * 
	 * @return the height in pixel. Default is {@link UndefinedValues#INTEGER}.
	 */
	public int getHeight() {
		return initialized ? check(chart.getHeight(), UndefinedValues.INTEGER) : UndefinedValues.INTEGER;
	}

	/**
	 * Returns the aspect ratio.
	 * 
	 * @return the aspect ratio. Default is {@link UndefinedValues#DOUBLE}.
	 */
	public double getAspectRatio() {
		return initialized ? check(chart.getAspectRatio(), UndefinedValues.DOUBLE) : UndefinedValues.DOUBLE;
	}

	/**
	 * Returns the current device pixel ratio.
	 * 
	 * @return the current device pixel ratio. Default is {@link UndefinedValues#DOUBLE}.
	 */
	public double getCurrentDevicePixelRatio() {
		return initialized ? check(chart.getCurrentDevicePixelRatio(), UndefinedValues.DOUBLE) : UndefinedValues.DOUBLE;
	}

	/**
	 * Returns the string JSON representation of the object.
	 * 
	 * @return the string JSON representation of the object.
	 */
	public String toJSON() {
		return JSON.stringifyWithReplacer(chart, 3);
	}
	//
	// /**
	// * Checks 2 booleans and returns the no-null one.
	// *
	// * @param value original value
	// * @param defaultValue default value
	// * @return returns the no-null one.
	// */
	// private boolean check(boolean value, boolean defaultValue) {
	// // transforms the value into string because a boolean can not be null
	// String stringValue = String.valueOf(value);
	// // by java script, if value is null, to string you have "undefined"
	// return ObjectType.UNDEFINED.name().equalsIgnoreCase(stringValue) ? defaultValue : value;
	// }

	/**
	 * Checks 2 integers and returns the no-null one.
	 * 
	 * @param value original value
	 * @param defaultValue default value
	 * @return returns the no-null one.
	 */
	private int check(int value, int defaultValue) {
		return Double.isNaN(value) ? defaultValue : value;
	}

	/**
	 * Checks 2 doubles and returns the no-null one.
	 * 
	 * @param value original value
	 * @param defaultValue default value
	 * @return returns the no-null one.
	 */
	private double check(double value, double defaultValue) {
		return Double.isNaN(value) ? defaultValue : value;
	}

}
