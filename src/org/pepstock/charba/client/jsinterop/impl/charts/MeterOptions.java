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
package org.pepstock.charba.client.jsinterop.impl.charts;

import org.pepstock.charba.client.colors.Color;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.jsinterop.AbstractChart;
import org.pepstock.charba.client.jsinterop.ChartOptions;
import org.pepstock.charba.client.jsinterop.Defaults;
import org.pepstock.charba.client.jsinterop.callbacks.LegendCallback;
import org.pepstock.charba.client.jsinterop.configuration.AbstractPieOptions;
import org.pepstock.charba.client.jsinterop.configuration.Hover;
import org.pepstock.charba.client.jsinterop.configuration.Layout;
import org.pepstock.charba.client.jsinterop.configuration.Legend;
import org.pepstock.charba.client.jsinterop.configuration.Tooltips;

/**
 * Specific options for METER chart. This chart doesn't allow any legend, hover, layout and tooltips components.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 *
 */
public class MeterOptions extends AbstractPieOptions {
	// exception message
	private static final String INVALID__CALL = "The invoked component is not available for Meter or Gauge charts.";

	/**
	 * Default format to apply displaying the value or percentage
	 */
	public static final String DEFAULT_FORMAT = "##0";

	/**
	 * Default color of display.
	 */
	public static final IsColor DEFAULT_DISPLAY_COLOR = new Color(128, 128, 128);

	private static final double DEFAULT_CUTOUT_PERCENTAGE = 90D;

	private static final boolean DEFAULT_ANIMATED_DISPLAY = false;

	private MeterDisplay display = MeterDisplay.value;

	private String format = DEFAULT_FORMAT;

	private String fontFamily = Defaults.get().getGlobal().getDefaultFontFamily();

	private FontStyle fontStyle = FontStyle.normal;

	private IsColor displayFontColor = DEFAULT_DISPLAY_COLOR;

	private boolean animatedDisplay = DEFAULT_ANIMATED_DISPLAY;

	/**
	 * Builds the object storing the chart instance and defaults.
	 * 
	 * @param chart chart instance
	 * @param defaultValues defaults of chart
	 */
	public MeterOptions(AbstractChart<?, ?> chart, ChartOptions defaultValues) {
		super(chart, defaultValues);
		// disables legend, title and tooltips.
		super.getLegend().setDisplay(false);
		super.getTitle().setDisplay(false);
		super.getTooltips().setEnabled(false);
		// sets the 90% of cutout
		super.setCutoutPercentage(DEFAULT_CUTOUT_PERCENTAGE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.AbstractPieOptions#setCutoutPercentage(double)
	 */
	@Override
	public final void setCutoutPercentage(double cutoutPercentage) {
		// ignore the passed value. is ALWAYS 90
		super.setCutoutPercentage(DEFAULT_CUTOUT_PERCENTAGE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.BaseOptions#getHover()
	 */
	@Override
	public final Hover getHover() {
		throw new UnsupportedOperationException(INVALID__CALL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.BaseOptions#getLayout()
	 */
	@Override
	public final Layout getLayout() {
		throw new UnsupportedOperationException(INVALID__CALL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.BaseOptions#getLegend()
	 */
	@Override
	public final Legend getLegend() {
		throw new UnsupportedOperationException(INVALID__CALL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.BaseOptions#getTooltips()
	 */
	@Override
	public final Tooltips getTooltips() {
		throw new UnsupportedOperationException(INVALID__CALL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.pepstock.charba.client.jsinterop.configuration.ConfigurationOptions#setLegendCallback(org.pepstock.charba.client.
	 * jsinterop.callbacks.LegendCallback)
	 */
	@Override
	public final void setLegendCallback(LegendCallback legendCallback) {
		throw new UnsupportedOperationException(INVALID__CALL);
	}

	/**
	 * Returns the display type of data in chart.
	 * 
	 * @return the display type of data in chart.
	 */
	public final MeterDisplay getDisplay() {
		return display;
	}

	/**
	 * Sets the display type of data in chart.
	 * 
	 * @param display the display to set
	 */
	public final void setDisplay(MeterDisplay display) {
		this.display = display;
	}

	/**
	 * Sets the format to apply to display the value.
	 * 
	 * @return the format to apply to the value in chart.
	 */
	public final String getFormat() {
		return format;
	}

	/**
	 * Sets the format to apply to display the value.
	 * 
	 * @param format the format to set
	 */
	public final void setFormat(String format) {
		this.format = format;
	}

	/**
	 * Returns the font family to apply to the display of value.
	 * 
	 * @return the fontFamily to apply to the display of value.
	 */
	public final String getFontFamily() {
		return fontFamily;
	}

	/**
	 * Sets the font family to apply to the display of value.
	 * 
	 * @param fontFamily the fontFamily to set
	 */
	public final void setFontFamily(String fontFamily) {
		this.fontFamily = fontFamily;
	}

	/**
	 * Returns the font style to apply to the display of value.
	 * 
	 * @return the fontStyle to apply to the display of value.
	 */
	public final FontStyle getFontStyle() {
		return fontStyle;
	}

	/**
	 * Sets the font style to apply to the display of value.
	 * 
	 * @param fontStyle the fontStyle to set
	 */
	public final void setFontStyle(FontStyle fontStyle) {
		this.fontStyle = fontStyle;
	}

	/**
	 * Returns the font color to apply to the display of value.
	 * 
	 * @return the displayFontColor
	 */
	public final IsColor getDisplayFontColor() {
		return displayFontColor;
	}

	/**
	 * Sets the font color to apply to the display of value.
	 * 
	 * @param displayFontColor the displayFontColor to set
	 */
	public final void setDisplayFontColor(IsColor displayFontColor) {
		this.displayFontColor = displayFontColor;
	}

	/**
	 * Returns if the display will be shown based on the animation of chart.
	 * 
	 * @return the animatedDisplay, <code>true</code> if animated, otherwise <code>false</code>. Default is <code>false</code>.
	 */
	public final boolean isAnimatedDisplay() {
		return animatedDisplay;
	}

	/**
	 * Sets if the display will be shown based on the animation of chart.
	 * 
	 * @param animatedDisplay the animatedDisplay to set, <code>true</code> if animated, otherwise <code>false</code>
	 */
	public final void setAnimatedDisplay(boolean animatedDisplay) {
		this.animatedDisplay = animatedDisplay;
	}

}