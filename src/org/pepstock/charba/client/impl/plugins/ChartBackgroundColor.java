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

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.colors.CanvasObjectFactory;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.HtmlColor;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.colors.Pattern;
import org.pepstock.charba.client.plugins.AbstractPlugin;

import com.google.gwt.canvas.dom.client.CanvasGradient;
import com.google.gwt.canvas.dom.client.CanvasPattern;
import com.google.gwt.canvas.dom.client.Context2d;

/**
 * Default plugin implementation to set the background color of chart.<br>
 * If added to defaults, without any configuration, the chart will have a WHITE background color.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ChartBackgroundColor extends AbstractPlugin {

	// default background color
	static final String DEFAULT_BACKGROUND_COLOR = HtmlColor.White.toRGBA();

	/**
	 * Plugin ID {@value ID}
	 */
	public static final String ID = "backgroundcolor";
	// factory to create options (native object container)
	private final ChartBackgroundColorOptionsFactory factory = new ChartBackgroundColorOptionsFactory();
	// color instance
	private final String color;
	// gradient instance
	private final Gradient gradient;
	// pattern instance
	private final Pattern pattern;

	/**
	 * Default constructor with WIHITE background color.
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
		this((color != null) ? color.toRGBA() : DEFAULT_BACKGROUND_COLOR);
	}

	/**
	 * Builds the object with the default background color for all charts.
	 * 
	 * @param color background default color for all charts.
	 */
	public ChartBackgroundColor(String color) {
		super();
		this.color = (color != null) ? color : DEFAULT_BACKGROUND_COLOR;
		this.pattern = null;
		this.gradient = null;
	}

	/**
	 * Builds the object with the default gradient for all charts.
	 * 
	 * @param gradient background default gradient for all charts.
	 */
	public ChartBackgroundColor(Gradient gradient) {
		super();
		if (gradient == null) {
			throw new IllegalArgumentException("Gradient is null");
		}
		this.color = null;
		this.pattern = null;
		this.gradient = gradient;
	}

	/**
	 * Builds the object with the default gradient for all charts.
	 * 
	 * @param pattern background default gradient for all charts.
	 */
	public ChartBackgroundColor(Pattern pattern) {
		super();
		if (pattern == null) {
			throw new IllegalArgumentException("Pattern is null");
		}
		this.color = null;
		this.pattern = pattern;
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
	 * @see org.pepstock.charba.client.plugins.AbstractPlugin#onBeforeDraw(org.pepstock.charba.client.AbstractChart, double)
	 */
	@Override
	public boolean onBeforeDraw(AbstractChart<?, ?> chart, double easing) {
		ChartBackgroundColorOptions bgOptions = null;
		// creates the plugin options using the java script object
		// passing also the default color set at constructor.
		if (chart.getOptions().getPlugins().hasOptions(ID)) {
			bgOptions = chart.getOptions().getPlugins().getOptions(ID, factory);
		} else {
			bgOptions = new ChartBackgroundColorOptions();
			if (color != null) {
				bgOptions.setBackgroundColor(color);
			} else if (pattern != null) {
				bgOptions.setBackgroundColor(pattern);
			} else if (gradient != null) {
				bgOptions.setBackgroundColor(gradient);
			}
		}
		// gets the canvas
		Context2d ctx = chart.getCanvas().getContext2d();
		if (ChartBackgroundColorOptions.ColorType.color.equals(bgOptions.getColorType())) {
			// set fill canvas color
			ctx.setFillStyle(bgOptions.getBackgroundColorAsString());
		} else if (ChartBackgroundColorOptions.ColorType.pattern.equals(bgOptions.getColorType())){
			// creates the pattern
			CanvasPattern canvasPattern = CanvasObjectFactory.createPattern(chart, bgOptions.getBackgroundColorAsPattern());
			// set fill canvas pattern
			ctx.setFillStyle(canvasPattern);
		} else if (ChartBackgroundColorOptions.ColorType.gradient.equals(bgOptions.getColorType())){
			// creates the gradient
			CanvasGradient canvasGradient = CanvasObjectFactory.createGradient(chart, bgOptions.getBackgroundColorAsGradient(), Integer.MIN_VALUE, Integer.MIN_VALUE);
			// set fill canvas color
			ctx.setFillStyle(canvasGradient);
		}
		// fills back ground
		ctx.fillRect(0, 0, chart.getCanvas().getOffsetWidth(), chart.getCanvas().getOffsetHeight());
		// always TRUE
		return true;
	}
}
