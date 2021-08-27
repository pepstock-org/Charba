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

import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.defaults.IsDefaultScaledOptions;
import org.pepstock.charba.client.defaults.globals.DefaultsBuilder;
import org.pepstock.charba.client.items.ChartAreaNode;
import org.pepstock.charba.client.items.LegendNode;
import org.pepstock.charba.client.items.OptionsNode;
import org.pepstock.charba.client.items.ScalesNode;
import org.pepstock.charba.client.items.SubtitleNode;
import org.pepstock.charba.client.items.TitleNode;
import org.pepstock.charba.client.items.TooltipNode;
import org.pepstock.charba.client.items.Undefined;
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

	private final SubtitleNode subtitle;

	private final TooltipNode tooltip;

	private final boolean initialized;

	/**
	 * Creates the object wrapping a CHART instance.
	 * 
	 * @param chartId scope of the options, in this case the chart id.
	 * @param chart CHART.JS CHART instance
	 */
	public ChartNode(String chartId, Chart chart) {
		// stores native chart instance
		this.chart = chart;
		// sets if is initialized checking the CHART instance
		this.initialized = chart != null;
		// gets the defaults for options
		IsDefaultScaledOptions defaultValues = this.initialized ? chart.getChart().getDefaultChartOptions() : DefaultsBuilder.get().getScaledOptions();
		// creates all sub elements
		this.options = new OptionsNode(chartId, defaultValues, new ChartEnvelop<>(this.initialized ? chart.getOptions() : null, true));
		this.legend = new LegendNode(new ChartEnvelop<>(this.initialized ? chart.getLegend() : null, true));
		this.scales = new ScalesNode(new ChartEnvelop<>(this.initialized ? chart.getScales() : null, true));
		this.chartArea = new ChartAreaNode(new ChartEnvelop<>(this.initialized ? chart.getChartArea() : null, true));
		this.title = new TitleNode(new ChartEnvelop<>(this.initialized ? chart.getTitleBlock() : null, true));
		this.subtitle = new SubtitleNode(new ChartEnvelop<>(this.initialized ? chart.getSubtitle() : null, true));
		this.tooltip = new TooltipNode(new ChartEnvelop<>(this.initialized ? chart.getTooltip() : null, true));
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
	 * Returns the subtitle item.
	 * 
	 * @return the subtitle item.
	 */
	public SubtitleNode getSubtitle() {
		return subtitle;
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
	 * @return the CHART JS chart ID.
	 */
	public int getId() {
		return initialized ? Checker.positiveOrDefault(chart.getId(), Undefined.INTEGER) : Undefined.INTEGER;
	}

	/**
	 * Returns the width in pixel.
	 * 
	 * @return the width in pixel.
	 */
	public int getWidth() {
		return initialized ? Checker.positiveOrDefault(chart.getWidth(), Undefined.INTEGER) : Undefined.INTEGER;
	}

	/**
	 * Returns the height in pixel.
	 * 
	 * @return the height in pixel.
	 */
	public int getHeight() {
		return initialized ? Checker.positiveOrDefault(chart.getHeight(), Undefined.INTEGER) : Undefined.INTEGER;
	}

	/**
	 * Returns the aspect ratio.
	 * 
	 * @return the aspect ratio.
	 */
	public double getAspectRatio() {
		return initialized ? Checker.positiveOrDefault(chart.getAspectRatio(), Undefined.DOUBLE) : Undefined.DOUBLE;
	}

	/**
	 * Returns the current device pixel ratio.
	 * 
	 * @return the current device pixel ratio.
	 */
	public double getCurrentDevicePixelRatio() {
		return initialized ? Checker.positiveOrDefault(chart.getCurrentDevicePixelRatio(), Undefined.DOUBLE) : Undefined.DOUBLE;
	}

	/**
	 * Returns the string JSON representation of the object.
	 * 
	 * @return the string JSON representation of the object.
	 */
	public String toJSON() {
		return JSON.stringifyWithReplacer(chart, 3);
	}

}
