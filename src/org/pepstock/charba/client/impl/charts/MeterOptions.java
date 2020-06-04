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
package org.pepstock.charba.client.impl.charts;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.callbacks.LegendCallback;
import org.pepstock.charba.client.callbacks.ValueCallback;
import org.pepstock.charba.client.colors.Color;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.configuration.AbstractPieOptions;
import org.pepstock.charba.client.configuration.Hover;
import org.pepstock.charba.client.configuration.Layout;
import org.pepstock.charba.client.configuration.Legend;
import org.pepstock.charba.client.configuration.Tooltips;
import org.pepstock.charba.client.defaults.IsDefaultScaledOptions;
import org.pepstock.charba.client.enums.FontStyle;

/**
 * Specific options for METER chart. This chart doesn't allow any legend, hover, layout and tooltips components.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class MeterOptions extends AbstractPieOptions {

	/**
	 * Default precision <b>{@value DEFAULT_PRECISION}</b> to apply displaying the value or percentage.
	 */
	public static final int DEFAULT_PRECISION = 2;

	/**
	 * Default color of display, <b>rgb(128, 128, 128)</b>
	 */
	public static final IsColor DEFAULT_DISPLAY_COLOR = new Color(128, 128, 128);

	// exception message
	private static final String INVALID_CALL = "The invoked component is not available for meter or gauge charts.";

	private static final double DEFAULT_CUTOUT_PERCENTAGE = 90D;

	private static final double DEFAULT_CIRCUMFERENCE = 2 * Math.PI;

	private static final double DEFAULT_ROTATION = -0.5 * Math.PI;

	private static final boolean DEFAULT_ANIMATED_DISPLAY = false;

	private MeterDisplay display = MeterDisplay.VALUE;

	private int precision = DEFAULT_PRECISION;

	private String fontFamily = Defaults.get().getGlobal().getFont().getFamily();

	private FontStyle fontStyle = FontStyle.NORMAL;

	private IsColor displayFontColor = DEFAULT_DISPLAY_COLOR;

	private boolean animatedDisplay = DEFAULT_ANIMATED_DISPLAY;

	private ValueCallback valueCallback = null;

	private final LegendWrapper legend;

	/**
	 * Builds the object storing the chart instance and defaults.
	 * 
	 * @param chart chart instance
	 * @param defaultValues defaults of chart
	 */
	public MeterOptions(IsChart chart, IsDefaultScaledOptions defaultValues) {
		super(chart, defaultValues);
		// disables legend, title and tooltips.
		super.getTitle().setDisplay(false);
		super.getTooltips().setEnabled(false);
		super.getAnimation().getActive().setDuration(0);
		// creates a wrapper for legend
		legend = new LegendWrapper(super.getLegend());
		legend.setDisplay(false);
		// sets the 90% of cutout
		super.setCutoutPercentage(DEFAULT_CUTOUT_PERCENTAGE);
		super.setCircumference(DEFAULT_CIRCUMFERENCE);
		super.setRotation(DEFAULT_ROTATION);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.AbstractPieOptions#setCutoutPercentage(double)
	 */
	@Override
	public final void setCutoutPercentage(double cutoutPercentage) {
		// ignore the passed value. is ALWAYS 90
		super.setCutoutPercentage(DEFAULT_CUTOUT_PERCENTAGE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.AbstractPieOptions#setRotation(double)
	 */
	@Override
	public final void setRotation(double rotation) {
		// ignore the passed value.
		super.setRotation(DEFAULT_ROTATION);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.AbstractPieOptions#setCircumference(double)
	 */
	@Override
	public final void setCircumference(double circumference) {
		// ignore the passed value.
		super.setCircumference(DEFAULT_CIRCUMFERENCE);
	}

	/**
	 * Returns nothing but throws an exception because not available.
	 * 
	 * @return nothing because will throw an exception
	 */
	@Override
	public final Hover getHover() {
		throw new UnsupportedOperationException(INVALID_CALL);
	}

	/**
	 * Returns nothing but throws an exception because not available.
	 * 
	 * @return nothing because will throw an exception
	 */
	@Override
	public final Layout getLayout() {
		throw new UnsupportedOperationException(INVALID_CALL);
	}

	/**
	 * Returns a legend wrapper where is not possible to display it.
	 * 
	 * @return a legend wrapper where is not possible to display it
	 */
	@Override
	public final Legend getLegend() {
		return legend;
	}

	/**
	 * Returns nothing but throws an exception because not available.
	 * 
	 * @return nothing because will throw an exception
	 */
	@Override
	public final Tooltips getTooltips() {
		throw new UnsupportedOperationException(INVALID_CALL);
	}

	/**
	 * Throws an exception because not available.
	 * 
	 * @param legendCallback ignored because will throw an exception
	 */
	@Override
	public final void setLegendCallback(LegendCallback legendCallback) {
		throw new UnsupportedOperationException(INVALID_CALL);
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
		// checks if consistent
		this.display = display == null ? MeterDisplay.VALUE : display;
	}

	/**
	 * Returns the decimal places to apply to display the value.
	 * 
	 * @return the decimal places to apply to the value in chart
	 */
	public final int getPrecision() {
		return precision;
	}

	/**
	 * Sets the decimal places to apply to display the value.
	 * 
	 * @param precision the decimal places to apply to the value in chart
	 */
	public final void setPrecision(int precision) {
		this.precision = precision;
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
		this.fontFamily = fontFamily == null ? Defaults.get().getGlobal().getFont().getFamily() : fontFamily;
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
		this.fontStyle = fontStyle == null ? FontStyle.NORMAL : fontStyle;
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
		this.displayFontColor = IsColor.isValid(displayFontColor) ? displayFontColor : DEFAULT_DISPLAY_COLOR;
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

	/**
	 * Returns the callback to customize the value string into chart.
	 * 
	 * @return the callback to customize the value string into chart
	 */
	public final ValueCallback getValueCallback() {
		return valueCallback;
	}

	/**
	 * Sets the callback to customize the value string into chart.
	 * 
	 * @param valueCallback the callback to customize the value string into chart
	 */
	public final void setValueCallback(ValueCallback valueCallback) {
		this.valueCallback = valueCallback;
	}

	/**
	 * Internal implementation of legend which wraps the original one.<br>
	 * With this legend implementation you can not set true to display it.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class LegendWrapper extends Legend {

		/**
		 * Creates a legend object wrapping an existing one.
		 * 
		 * @param delegated legend instance to wrap
		 */
		LegendWrapper(Legend delegated) {
			super(delegated);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.configuration.Legend#setDisplay(boolean)
		 */
		@Override
		public void setDisplay(boolean display) {
			// ignore the passed value because
			// the legend must not display
			super.setDisplay(false);
		}

	}
}