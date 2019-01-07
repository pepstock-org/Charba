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
 * @version 2.0
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
	
	private MeterDisplay display = MeterDisplay.value;
	
	private String format = DEFAULT_FORMAT;
	
	private String fontFamily = Defaults.get().getGlobal().getDefaultFontFamily();
	
	private FontStyle fontStyle = FontStyle.normal;
	
	private IsColor displayFontColor = DEFAULT_DISPLAY_COLOR;
	
	/**
	 * Builds the object storing the chart instance.
	 * 
	 * @param chart chart instance
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
	
	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.options.AbstractPieOptions#setCutoutPercentage(double)
	 */
	@Override
	public void setCutoutPercentage(double cutoutPercentage) {
		// ignore the passed value. is ALWAYS 90
		super.setCutoutPercentage(DEFAULT_CUTOUT_PERCENTAGE);
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.options.BaseOptions#getHover()
	 */
	@Override
	public Hover getHover() {
		throw new UnsupportedOperationException(INVALID__CALL);
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.options.BaseOptions#getLayout()
	 */
	@Override
	public Layout getLayout() {
		throw new UnsupportedOperationException(INVALID__CALL);
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.options.BaseOptions#getLegend()
	 */
	@Override
	public Legend getLegend() {
		throw new UnsupportedOperationException(INVALID__CALL);
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.options.BaseOptions#getTooltips()
	 */
	@Override
	public Tooltips getTooltips() {
		throw new UnsupportedOperationException(INVALID__CALL);
	}
	
	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.configuration.ConfigurationOptions#setLegendCallback(org.pepstock.charba.client.jsinterop.callbacks.LegendCallback)
	 */
	@Override
	public void setLegendCallback(LegendCallback legendCallback) {
		throw new UnsupportedOperationException(INVALID__CALL);
	}

	/**
	 * @return the display
	 */
	public MeterDisplay getDisplay() {
		return display;
	}

	/**
	 * @param display the display to set
	 */
	public void setDisplay(MeterDisplay display) {
		this.display = display;
	}

	/**
	 * @return the format
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * @param format the format to set
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	/**
	 * @return the fontFamily
	 */
	public String getFontFamily() {
		return fontFamily;
	}

	/**
	 * @param fontFamily the fontFamily to set
	 */
	public void setFontFamily(String fontFamily) {
		this.fontFamily = fontFamily;
	}

	/**
	 * @return the fontStyle
	 */
	public FontStyle getFontStyle() {
		return fontStyle;
	}

	/**
	 * @param fontStyle the fontStyle to set
	 */
	public void setFontStyle(FontStyle fontStyle) {
		this.fontStyle = fontStyle;
	}

	/**
	 * @return the displayFontColor
	 */
	public IsColor getDisplayFontColor() {
		return displayFontColor;
	}

	/**
	 * @param displayFontColor the displayFontColor to set
	 */
	public void setDisplayFontColor(IsColor displayFontColor) {
		this.displayFontColor = displayFontColor;
	}

}