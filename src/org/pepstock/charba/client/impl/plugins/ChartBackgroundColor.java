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
import java.util.Map;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.HtmlColor;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.colors.Pattern;
import org.pepstock.charba.client.defaults.IsDefaultScaledOptions;
import org.pepstock.charba.client.dom.elements.CanvasGradientItem;
import org.pepstock.charba.client.dom.elements.CanvasPatternItem;
import org.pepstock.charba.client.dom.elements.Context2dItem;
import org.pepstock.charba.client.enums.ColorType;
import org.pepstock.charba.client.impl.plugins.ChartBackgroundColorOptionsFactory.ChartBackgroundColorDefaultsOptionsFactory;
import org.pepstock.charba.client.items.SizeItem;
import org.pepstock.charba.client.items.UndefinedValues;
import org.pepstock.charba.client.plugins.AbstractPlugin;
import org.pepstock.charba.client.utils.Utilities;

/**
 * A plugin implementation to set the background color, gradient or pattern of chart.<br>
 * If added to defaults, without any configuration, the chart will have a {@link ChartBackgroundColor#DEFAULT_BACKGROUND_COLOR} background color.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ChartBackgroundColor extends AbstractPlugin {

	/**
	 * Plugin ID <b>{@value ID}</b>.
	 */
	public static final String ID = "charbabackgroundcolor";
	/**
	 * The factory to create options
	 */
	public static final ChartBackgroundColorOptionsFactory FACTORY = new ChartBackgroundColorOptionsFactory();
	/**
	 * Default background color, {@link HtmlColor#WHITE}.
	 */
	public static final String DEFAULT_BACKGROUND_COLOR = HtmlColor.WHITE.toRGBA();
	// defaults options factory instance
	static final ChartBackgroundColorDefaultsOptionsFactory DEFAULTS_FACTORY = new ChartBackgroundColorDefaultsOptionsFactory();
	// cache to store options in order do not load every time the options
	private static final Map<String, ChartBackgroundColorOptions> OPTIONS = new HashMap<>();
	// color instance
	private final String color;
	// gradient instance
	private final Gradient gradient;
	// pattern instance
	private final Pattern pattern;

	/**
	 * Default constructor with {@link ChartBackgroundColor#DEFAULT_BACKGROUND_COLOR} background color.
	 */
	public ChartBackgroundColor() {
		this(DEFAULT_BACKGROUND_COLOR);
	}

	/**
	 * Builds the object with the default background color for all charts.
	 * 
	 * @param color background default color for all charts.
	 */
	public ChartBackgroundColor(IsColor color) {
		this(IsColor.isConsistent(color) ? color.toRGBA() : DEFAULT_BACKGROUND_COLOR);
	}

	/**
	 * Builds the object with the default background color for all charts.
	 * 
	 * @param color background default color for all charts.
	 */
	public ChartBackgroundColor(String color) {
		// sets default color if null
		this.color = (color != null) ? color : DEFAULT_BACKGROUND_COLOR;
		// sets null other kind of background
		this.pattern = null;
		this.gradient = null;
	}

	/**
	 * Builds the object with the default gradient for all charts.
	 * 
	 * @param gradient background default gradient for all charts.
	 */
	public ChartBackgroundColor(Gradient gradient) {
		// checks if gradient is consistent
		if (gradient == null) {
			// if null, exception!
			throw new IllegalArgumentException("Gradient argument is null");
		}
		// sets gradient
		this.gradient = gradient;
		// sets null other kind of background
		this.color = null;
		this.pattern = null;
	}

	/**
	 * Builds the object with the default pattern for all charts.
	 * 
	 * @param pattern background default pattern for all charts.
	 */
	public ChartBackgroundColor(Pattern pattern) {
		// checks if pattern is consistent
		if (pattern == null) {
			// if null, exception!
			throw new IllegalArgumentException("Pattern argument is null");
		}
		// sets gradient
		this.pattern = pattern;
		// sets null other kind of background
		this.color = null;
		this.gradient = null;
	}

	/**
	 * Returns the color as string if it has been set, otherwise <code>null</code>.
	 * 
	 * @return the color as string if it has been set, otherwise <code>null</code>.
	 */
	public String getColorAsString() {
		return color;
	}

	/**
	 * Returns the color if it has been set, otherwise <code>null</code>.
	 * 
	 * @return the color if it has been set, otherwise <code>null</code>
	 */
	public IsColor getColor() {
		// checks if color has been set
		if (getColorAsString() != null) {
			// returns color
			return ColorBuilder.parse(getColorAsString());
		}
		// otherwise null
		return null;
	}

	/**
	 * Returns the gradient if it has been set, otherwise <code>null</code>.
	 * 
	 * @return the gradient if it has been set, otherwise <code>null</code>
	 */
	public Gradient getGradient() {
		return gradient;
	}

	/**
	 * Returns the pattern if it has been set, otherwise <code>null</code>.
	 * 
	 * @return the pattern if it has been set, otherwise <code>null</code>
	 */
	public Pattern getPattern() {
		return pattern;
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
	 * @see org.pepstock.charba.client.plugins.AbstractPlugin#onBeforeDraw(org.pepstock.charba.client.IsChart)
	 */
	@Override
	public boolean onBeforeDraw(IsChart chart) {
		// checks if chart is consistent
		if (IsChart.isConsistent(chart)) {
			// gets options
			ChartBackgroundColorOptions bgOptions = getOptions(chart);
			// gets the canvas
			Context2dItem ctx = chart.getCanvas().getContext2d();
			// save context
			ctx.save();
			if (ColorType.COLOR.equals(bgOptions.getColorType())) {
				// set fill canvas color
				ctx.setFillColor(bgOptions.getBackgroundColorAsString());
				// sets back ground to chart HTML element
				applyBackgroundColorToChartElement(chart, bgOptions.getBackgroundColorAsString());
			} else if (ColorType.PATTERN.equals(bgOptions.getColorType())) {
				// creates the pattern
				CanvasPatternItem canvasPattern = ChartBackgroundGradientFactory.get().createPattern(chart, bgOptions.getBackgroundColorAsPattern());
				// set fill canvas pattern
				ctx.setFillPattern(canvasPattern);
				// sets back ground to chart HTML element
				applyBackgroundToChartElement(chart, Utilities.toCSSBackgroundProperty(bgOptions.getBackgroundColorAsPattern()));
			} else if (ColorType.GRADIENT.equals(bgOptions.getColorType())) {
				// creates the gradient
				CanvasGradientItem canvasGradient = ChartBackgroundGradientFactory.get().createGradient(chart, bgOptions.getBackgroundColorAsGradient(), UndefinedValues.INTEGER, UndefinedValues.INTEGER);
				// set fill canvas color
				ctx.setFillGradient(canvasGradient);
				// sets back ground to chart HTML element
				applyBackgroundImageToChartElement(chart, Utilities.toCSSBackgroundProperty(bgOptions.getBackgroundColorAsGradient()));
			}
			// fills back ground
			ctx.fillRect(0, 0, chart.getCanvas().getOffsetWidth(), chart.getCanvas().getOffsetHeight());
			// restore context
			ctx.restore();
		}
		// always TRUE
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.plugins.AbstractPlugin#onEndDrawing(org.pepstock.charba.client.IsChart)
	 */
	@Override
	public void onEndDrawing(IsChart chart) {
		// checks if chart is consistent
		if (IsChart.isValid(chart)) {
			// when the draw is completed
			// remove the options from cache in order to reload it
			// when chart is re drawing for whatever reason.
			// in this way if options are updating during chart's life cycle
			// the updates can be applied.
			OPTIONS.remove(chart.getId());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.plugins.AbstractPlugin#onResize(org.pepstock.charba.client.IsChart, org.pepstock.charba.client.items.SizeItem)
	 */
	@Override
	public void onResize(IsChart chart, SizeItem size) {
		// checks if chart is consistent
		if (IsChart.isConsistent(chart)) {
			// gets options
			ChartBackgroundColorOptions bgOptions = getOptions(chart);
			// if gradient has been set
			if (ColorType.GRADIENT.equals(bgOptions.getColorType())) {
				// Due to gradients are created based on dimension of
				// canvas or chart area, every time a resize is occurring
				// gradients must be recreated
				// because gradients must be recreated
				// the cache of gradients must be clear
				ChartBackgroundGradientFactory.get().resetGradients(chart);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.plugins.AbstractPlugin#onDestroy(org.pepstock.charba.client.IsChart)
	 */
	@Override
	public void onDestroy(IsChart chart) {
		// checks if chart is consistent
		if (IsChart.isValid(chart)) {
			// removes the options from the cache
			// even if it could not be needed
			// because the options should be remove after draw
			OPTIONS.remove(chart.getId());
			// because chart is destroy
			// clears the cache of patterns and gradients of the chart
			ChartBackgroundGradientFactory.get().clear(chart);
		}
	}

	/**
	 * Reads the plugin options at chart level, if exists, puts in cache if needed and returns it.
	 * 
	 * @param chart chart instances where to extract options from.
	 * @return the options of plugin
	 */
	private ChartBackgroundColorOptions getOptions(IsChart chart) {
		// checks if options are in cache
		if (OPTIONS.containsKey(chart.getId())) {
			// returns from cache
			return OPTIONS.get(chart.getId());
		}
		// options instance
		ChartBackgroundColorOptions bgOptions = null;
		// loads chart options for the chart
		IsDefaultScaledOptions options = chart.getWholeOptions();
		// creates the plugin options using the java script object
		// passing also the default color set at constructor.
		if (options.getPlugins().hasOptions(ID)) {
			bgOptions = options.getPlugins().getOptions(ID, FACTORY);
		} else {
			// no options, creates new one with global/defaults values
			bgOptions = new ChartBackgroundColorOptions(ChartBackgroundColorDefaultsOptions.DEFAULTS_INSTANCE);
			// if configured with a color
			if (color != null) {
				bgOptions.setBackgroundColor(color);
			} else if (pattern != null) {
				// if configured with a pattern
				bgOptions.setBackgroundColor(pattern);
			} else if (gradient != null) {
				// if configured with a gradient
				bgOptions.setBackgroundColor(gradient);
			}
		}
		// stores into cache
		OPTIONS.put(chart.getId(), bgOptions);
		// returns it
		return bgOptions;
	}

	/**
	 * Applies the background CSS property into chart HTML element.
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
	 * Applies the background color CSS property into chart HTML element.
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
	 * Applies the background image CSS property into chart HTML element.
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
