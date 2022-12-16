/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.impl.plugins;

import java.util.HashMap;
import java.util.Map;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.colors.CanvasObjectFactory;
import org.pepstock.charba.client.defaults.IsDefaultScaledOptions;
import org.pepstock.charba.client.dom.elements.CanvasGradientItem;
import org.pepstock.charba.client.dom.elements.CanvasPatternItem;
import org.pepstock.charba.client.dom.elements.Context2dItem;
import org.pepstock.charba.client.enums.ColorType;
import org.pepstock.charba.client.items.ChartAreaNode;
import org.pepstock.charba.client.items.IsArea;
import org.pepstock.charba.client.items.PluginResizeArgument;
import org.pepstock.charba.client.items.Undefined;
import org.pepstock.charba.client.plugins.SmartPlugin;
import org.pepstock.charba.client.plugins.SmartPluginContainer;
import org.pepstock.charba.client.plugins.hooks.BeforeDestroyHook;
import org.pepstock.charba.client.plugins.hooks.BeforeDrawHook;
import org.pepstock.charba.client.plugins.hooks.ResizeHook;
import org.pepstock.charba.client.utils.Utilities;

/**
 * A plugin implementation to set the background color, gradient or pattern of chart.<br>
 * If added to defaults, without any configuration, the chart will have a {@link ChartBackgroundColor#DEFAULT_BACKGROUND_COLOR} background color.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class ChartBackgroundColorPlugin extends SmartPlugin implements BeforeDrawHook, ResizeHook, BeforeDestroyHook {

	// cache to store options in order do not load every time the options
	private static final Map<String, ChartBackgroundColorOptions> OPTIONS = new HashMap<>();
	// cache to store the draw count for chart
	private static final Map<String, Integer> DRAW_COUNTS = new HashMap<>();
	// container instance
	private final ChartBackgroundColor container;

	/**
	 * Creates the plugin.
	 * 
	 * @param container the {@link SmartPluginContainer} of this plugin
	 */
	ChartBackgroundColorPlugin(ChartBackgroundColor container) {
		super(ChartBackgroundColor.ID);
		// stores container
		this.container = container;
		// stores itself as hook handler
		setBeforeDrawHook(this);
		setResizeHook(this);
		setBeforeDestroyHook(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.plugins.hooks.BeforeDrawHook#onBeforeDraw(org.pepstock.charba.client.IsChart)
	 */
	@Override
	public boolean onBeforeDraw(IsChart chart) {
		// checks if chart is consistent
		if (IsChart.isConsistent(chart)) {
			// gets options
			ChartBackgroundColorOptions bgOptions = getOptions(chart);
			// fills chart background
			fillChart(chart, bgOptions);
			// fills chart area background
			fillChartArea(chart, bgOptions);
		}
		// always TRUE
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.plugins.hooks.ResizeHook#onResize(org.pepstock.charba.client.IsChart, org.pepstock.charba.client.items.PluginResizeArgument)
	 */
	@Override
	public void onResize(IsChart chart, PluginResizeArgument argument) {
		// checks if chart is consistent
		if (IsChart.isConsistent(chart)) {
			// gets options
			ChartBackgroundColorOptions bgOptions = getOptions(chart);
			// resets gradients for chart background
			resetGradients(chart, bgOptions.getColorType(), ChartBackgroundGradientFactory.get());
			// resets gradients for chart area background
			resetGradients(chart, bgOptions.getAreaColorType(), ChartAreaBackgroundGradientFactory.get());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.plugins.hooks.BeforeDestroyHook#onBeforeDestroy(org.pepstock.charba.client.IsChart)
	 */
	@Override
	public void onBeforeDestroy(IsChart chart) {
		// checks if chart is consistent
		if (IsChart.isValid(chart)) {
			// removes the options from the cache
			OPTIONS.remove(chart.getId());
			// removes the draw count from the cache
			DRAW_COUNTS.remove(chart.getId());
			// because chart is destroy
			// clears the cache of patterns and gradients of the chart
			ChartBackgroundGradientFactory.get().clear(chart);
		}
	}

	/**
	 * Resets the gradients, stored in cache for the chart instance.
	 * 
	 * @param chart chart instance
	 * @param type type of color set in the options
	 * @param factory the gradient factory to invoke for resetting
	 */
	private void resetGradients(IsChart chart, ColorType type, CanvasObjectFactory factory) {
		// if gradient has been set
		if (ColorType.GRADIENT.equals(type)) {
			// Due to gradients are created based on dimension of
			// canvas or chart area, every time a resize is occurring
			// gradients must be recreated
			// because gradients must be recreated
			// the cache of gradients must be clear
			factory.resetGradients(chart);
		}
	}

	/**
	 * Fills the chart background.
	 * 
	 * @param chart chart instance
	 * @param options plugin options
	 */
	private void fillChart(IsChart chart, ChartBackgroundColorOptions options) {
		// gets the canvas
		Context2dItem ctx = chart.getCanvas().getContext2d();
		// save context
		ctx.save();
		// sets global composite operation
		ctx.setGlobalCompositeOperation(options.getGlobalCompositeOperation());
		// checks color types
		if (ColorType.COLOR.equals(options.getColorType())) {
			// set fill canvas color
			ctx.setFillColor(options.getBackgroundColorAsString());
			// sets background to chart HTML element
			applyBackgroundColorToChartElement(chart, options.getBackgroundColorAsString());
		} else if (ColorType.PATTERN.equals(options.getColorType())) {
			// creates the pattern
			CanvasPatternItem canvasPattern = ChartBackgroundGradientFactory.get().createPattern(chart, options.getBackgroundColorAsPattern());
			// set fill canvas pattern
			ctx.setFillPattern(canvasPattern);
			// sets background to chart HTML element
			applyBackgroundToChartElement(chart, Utilities.toCSSBackgroundProperty(options.getBackgroundColorAsPattern()));
		} else if (ColorType.GRADIENT.equals(options.getColorType())) {
			// creates the gradient
			CanvasGradientItem canvasGradient = ChartBackgroundGradientFactory.get().createGradient(chart, options.getBackgroundColorAsGradient(), Undefined.INTEGER);
			// set fill canvas color
			ctx.setFillGradient(canvasGradient);
			// sets background to chart HTML element
			applyBackgroundImageToChartElement(chart, Utilities.toCSSBackgroundProperty(options.getBackgroundColorAsGradient()));
		}
		// fills background
		ctx.fillRect(0, 0, chart.getCanvas().getOffsetWidth(), chart.getCanvas().getOffsetHeight());
		// restore context
		ctx.restore();
	}

	/**
	 * Fills the chart background.
	 * 
	 * @param chart chart instance
	 * @param options plugin options
	 */
	private void fillChartArea(IsChart chart, ChartBackgroundColorOptions options) {
		// gets chart area
		ChartAreaNode chartArea = chart.getNode().getChartArea();
		// checks if area is consistent
		if (IsArea.isConsistent(chartArea)) {
			// gets the canvas
			Context2dItem ctx = chart.getCanvas().getContext2d();
			// save context
			ctx.save();
			// checks color types
			if (ColorType.COLOR.equals(options.getAreaColorType())) {
				// set fill canvas color
				ctx.setFillColor(options.getAreaBackgroundColorAsString());
			} else if (ColorType.PATTERN.equals(options.getAreaColorType())) {
				// creates the pattern
				CanvasPatternItem canvasPattern = ChartAreaBackgroundGradientFactory.get().createPattern(chart, options.getAreaBackgroundColorAsPattern());
				// set fill canvas pattern
				ctx.setFillPattern(canvasPattern);
			} else if (ColorType.GRADIENT.equals(options.getAreaColorType())) {
				// creates the gradient
				CanvasGradientItem canvasGradient = ChartAreaBackgroundGradientFactory.get().createGradient(chart, options.getAreaBackgroundColorAsGradient(), Undefined.INTEGER);
				// set fill canvas color
				ctx.setFillGradient(canvasGradient);
			}
			// fills background area
			ctx.fillRect(chartArea.getLeft(), chartArea.getTop(), chartArea.getWidth(), chartArea.getHeight());
			// restore context
			ctx.restore();
		}
	}

	/**
	 * Reads the plugin options at chart level, if exists, puts in cache if needed and returns it.
	 * 
	 * @param chart chart instances where to extract options from.
	 * @return the options of plugin
	 */
	private ChartBackgroundColorOptions getOptions(IsChart chart) {
		// gets chart id
		String id = chart.getId();
		// checks if options are in cache
		if (DRAW_COUNTS.containsKey(id) && chart.getDrawCount() == DRAW_COUNTS.get(id) && OPTIONS.containsKey(id)) {
			// returns from cache
			return OPTIONS.get(id);
		}
		// stores draw count
		DRAW_COUNTS.put(id, chart.getDrawCount());
		// options instance
		ChartBackgroundColorOptions bgOptions = null;
		// loads chart options for the chart
		IsDefaultScaledOptions options = chart.getWholeOptions();
		// creates the plugin options using the java script object
		// passing also the default color set at constructor.
		if (options.getPlugins().hasOptions(ChartBackgroundColor.ID)) {
			bgOptions = options.getPlugins().getOptions(ChartBackgroundColor.ID, ChartBackgroundColor.FACTORY);
		} else {
			// no options, creates new one with global/defaults values
			bgOptions = new ChartBackgroundColorOptions(ChartBackgroundColorDefaultOptions.INSTANCE);
			// if configured with a color
			if (container.getColorAsString() != null) {
				bgOptions.setBackgroundColor(container.getColorAsString());
			} else if (container.getPattern() != null) {
				// if configured with a pattern
				bgOptions.setBackgroundColor(container.getPattern());
			} else if (container.getGradient() != null) {
				// if configured with a gradient
				bgOptions.setBackgroundColor(container.getGradient());
			}
		}
		// stores in the cache
		OPTIONS.put(id, bgOptions);
		// returns it
		return bgOptions;
	}

	/**
	 * Applies the background CSS property in the chart HTML element.
	 * 
	 * @param chart chart instance to use to apply CSS property
	 * @param value value of CSS property to set
	 */
	private void applyBackgroundToChartElement(IsChart chart, String value) {
		// checks if chart, name and value of CSS property are consistent
		if (IsChart.isValid(chart) && value != null) {
			// sets style property
			chart.getChartElement().getStyle().setBackground(value);
		}
	}

	/**
	 * Applies the background color CSS property in the chart HTML element.
	 * 
	 * @param chart chart instance to use to apply CSS property
	 * @param value value of CSS property to set
	 */
	private void applyBackgroundColorToChartElement(IsChart chart, String value) {
		// checks if chart, name and value of CSS property are consistent
		if (IsChart.isValid(chart) && value != null) {
			// sets style property
			chart.getChartElement().getStyle().setBackgroundColor(value);
		}
	}

	/**
	 * Applies the background image CSS property in the chart HTML element.
	 * 
	 * @param chart chart instance to use to apply CSS property
	 * @param value value of CSS property to set
	 */
	private void applyBackgroundImageToChartElement(IsChart chart, String value) {
		// checks if chart, name and value of CSS property are consistent
		if (IsChart.isValid(chart) && value != null) {
			// sets style property
			chart.getChartElement().getStyle().setBackgroundImage(value);
		}
	}

}