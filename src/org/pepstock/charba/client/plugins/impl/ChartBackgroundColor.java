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
package org.pepstock.charba.client.plugins.impl;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.plugins.AbstractPlugin;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.core.client.JavaScriptObject;

/**
 * Default plugin implementation to set the background color of chart.<br>
 * If added to defaults, without any configuration, the chart will have a WHITE background color.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ChartBackgroundColor extends AbstractPlugin {
	
	// default background color
	static final String DEFAULT_BACKGROUND_COLOR = "white";
	
	/**
	 * Plugin ID 
	 */
	public static final String ID = "backgroundcolor";
	
	private final String color;
	
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
		this(color.toRGBA());
	}

	/**
	 * Builds the object with the default background color for all charts.
	 * 
	 * @param color background default color for all charts.
	 */
	public ChartBackgroundColor(String color) {
		super();
		this.color = (color != null) ? color : DEFAULT_BACKGROUND_COLOR;
	}

	/**
	 * @return the color
	 */
	public String getColorAsString() {
		return color;
	}

	/**
	 * @return the color
	 */
	public IsColor getColor() {
		return ColorBuilder.parse(getColorAsString());
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.Plugin#getId()
	 */
	@Override
	public String getId() {
		return ID;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.plugins.AbstractPlugin#onBeforeDraw(org.pepstock.charba.client.AbstractChart, double)
	 */
	@Override
	public boolean onBeforeDraw(AbstractChart<?, ?> chart, double easing, JavaScriptObject options) {
		// creates the plugin options using the java script object
		// passing also the default color set at constructor.
		ChartBackgroundColorOptions bgOptions  = new ChartBackgroundColorOptions(options, color);
		// gets the canvas
		Context2d ctx = chart.getCanvas().getContext2d();
		// set fill canvas color
		ctx.setFillStyle(bgOptions.getBackgroundColorAsString());
		// fills back ground
		ctx.fillRect(0, 0, chart.getCanvas().getWidth(), chart.getCanvas().getHeight());
		// always TRUE
		return true;
	}

}
