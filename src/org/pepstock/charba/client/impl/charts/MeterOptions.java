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

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.callbacks.ColorCallback;
import org.pepstock.charba.client.callbacks.MeterFormatCallback;
import org.pepstock.charba.client.colors.Color;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.configuration.AbstractPieOptions;
import org.pepstock.charba.client.defaults.IsDefaultScaledOptions;
import org.pepstock.charba.client.enums.Render;
import org.pepstock.charba.client.items.FontItem;

/**
 * Specific options for METER chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class MeterOptions extends AbstractPieOptions {

	/**
	 * Default precision <b>{@value DEFAULT_PRECISION}</b> to apply rendering the value or percentage.
	 */
	public static final int DEFAULT_PRECISION = 2;

	/**
	 * Default color of render, <b>rgb(128, 128, 128)</b>
	 */
	public static final IsColor DEFAULT_FONT_COLOR = new Color(128, 128, 128);

	// default color of render as string
	static final String DEFAULT_FONT_COLOR_AS_STRING = DEFAULT_FONT_COLOR.toRGBA();

	private static final double DEFAULT_CIRCUMFERENCE = 360;

	private static final double DEFAULT_ROTATION = 0;

	private static final String DEFAULT_CUTOUT_PERCENTAGE = "90%";

	private static final boolean DEFAULT_ANIMATED = false;

	private static final boolean DEFAULT_AUTO_FONT_SIZE = true;

	// -------------------------
	// INSTANCES
	// -------------------------

	private Render render = Render.VALUE;

	private int precision = DEFAULT_PRECISION;

	private IsColor fontColor = DEFAULT_FONT_COLOR;

	private boolean animated = DEFAULT_ANIMATED;

	private boolean autoFontSize = DEFAULT_AUTO_FONT_SIZE;

	private FontItem fontItem = null;

	// -------------------------
	// CALLBACK
	// -------------------------

	private MeterContext context = null;

	private MeterFormatCallback formatCallback = null;

	private ColorCallback<MeterContext> fontColorCallback = null;

	/**
	 * Builds the object storing the chart instance and defaults.
	 * 
	 * @param chart chart instance
	 * @param defaultValues defaults of chart
	 */
	public MeterOptions(IsChart chart, IsDefaultScaledOptions defaultValues) {
		super(chart, defaultValues);
		// disables legend and tooltips.
		getLegend().setDisplay(false);
		getTooltips().setEnabled(false);
		// sets the 90% of cut out
		super.setCutoutPercentage(DEFAULT_CUTOUT_PERCENTAGE);
		// sets fixed circumference and rotation
		super.setCircumference(DEFAULT_CIRCUMFERENCE);
		super.setRotation(DEFAULT_ROTATION);
		// load
	}

	/**
	 * Returns the font item.<br>
	 * It's called from {@link BaseMeterController}.
	 * 
	 * @return the font item
	 */
	final FontItem getFontItem() {
		return fontItem;
	}

	/**
	 * Resets the font item for next computation.<br>
	 * It's called from {@link BaseMeterChart#applyConfiguration()}.
	 */
	final void resetFontItem() {
		this.fontItem = getFont().create();
	}

	/**
	 * Returns the scriptable context for callbacks, creating it if does not exist.<br>
	 * It's called from {@link BaseMeterController}.
	 * 
	 * @return the scriptable context for callbacks
	 */
	final MeterContext getContext() {
		// checks if context is instantiated
		if (context == null) {
			// creates the context
			context = new MeterContext(new BaseContext(getChart()));
		}
		// returns the context
		return context;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.AbstractPieOptions#setCutoutPercentage(java.lang.String)
	 */
	@Override
	public void setCutoutPercentage(String cutout) {
		// ignore the "set" because you can not change it
		super.setCutoutPercentage(DEFAULT_CUTOUT_PERCENTAGE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.AbstractPieOptions#setCutout(double)
	 */
	@Override
	public final void setCutout(double cutout) {
		// ignore the "set" because you can not change it
		setCutoutPercentage(DEFAULT_CUTOUT_PERCENTAGE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.AbstractPieOptions#getCutout()
	 */
	@Override
	public final double getCutout() {
		return Double.NaN;
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
	 * Returns the render type of data in chart.
	 * 
	 * @return the render type of data in chart.
	 */
	public final Render getRender() {
		return render;
	}

	/**
	 * Sets the render type of data in chart.
	 * 
	 * @param render the render to set
	 */
	public final void setRender(Render render) {
		// checks if consistent
		this.render = render == null ? Render.VALUE : render;
	}

	/**
	 * Returns the decimal places to apply to render the value.
	 * 
	 * @return the decimal places to apply to the value in chart
	 */
	public final int getPrecision() {
		return precision;
	}

	/**
	 * Sets the decimal places to apply to render the value.
	 * 
	 * @param precision the decimal places to apply to the value in chart
	 */
	public final void setPrecision(int precision) {
		this.precision = precision;
	}

	/**
	 * Returns the font color to apply to the render of value.
	 * 
	 * @return the font color to apply to the render of value
	 */
	public final IsColor getFontColor() {
		return fontColor;
	}

	/**
	 * Sets the font color to apply to the render of value.
	 * 
	 * @param fontColor the displayFontColor to set
	 */
	public final void setFontColor(IsColor fontColor) {
		// resets callback
		setFontColor((ColorCallback<MeterContext>)null);
		// stores value
		this.fontColor = IsColor.isValid(fontColor) ? fontColor : DEFAULT_FONT_COLOR;
	}

	/**
	 * Returns if the render will be shown based on the animation of chart.
	 * 
	 * @return the <code>true</code> the rendering is animated, otherwise <code>false</code>
	 */
	public final boolean isAnimated() {
		return animated;
	}

	/**
	 * Sets if the render will be shown based on the animation of chart.
	 * 
	 * @param animated <code>true</code> if the rendering is animated, otherwise <code>false</code>
	 */
	public final void setAnimated(boolean animated) {
		this.animated = animated;
	}

	/**
	 * Returns <code>true</code> if the font size of the rendering will be automatically calculated, based on dimension of the area for rendering.
	 * 
	 * @return <code>true</code> if the font size of the rendering will be automatically calculated, based on dimension of the area for rendering
	 */
	public boolean isAutoFontSize() {
		return autoFontSize;
	}

	/**
	 * Sets <code>true</code> if the font size of the rendering will be automatically calculated, based on dimension of the area for rendering.
	 * 
	 * @param autoFontSize <code>true</code> if the font size of the rendering will be automatically calculated, based on dimension of the area for rendering
	 */
	public void setAutoFontSize(boolean autoFontSize) {
		this.autoFontSize = autoFontSize;
	}

	/**
	 * Returns the callback to customize the value string in the chart.
	 * 
	 * @return the callback to customize the value string in the chart
	 */
	public final MeterFormatCallback getFormatCallback() {
		return formatCallback;
	}

	/**
	 * Sets the callback to customize the value string in the chart.
	 * 
	 * @param formatCallback the callback to customize the value string in the chart
	 */
	public final void setFormatCallback(MeterFormatCallback formatCallback) {
		this.formatCallback = formatCallback;
	}

	/**
	 * Returns the callback to customize the font color for rendered label in the chart.
	 * 
	 * @return the callback to customize the font color for rendered label in the chart
	 */
	public final ColorCallback<MeterContext> getFontColorCallback() {
		return fontColorCallback;
	}

	/**
	 * Sets the callback to customize the font color for rendered label in the chart.
	 * 
	 * @param fontColorCallback the callback to customize the font color for rendered label in the chart
	 */
	public final void setFontColor(ColorCallback<MeterContext> fontColorCallback) {
		// does not rest the font color because
		// the test in base controller is always done before for callback
		// and then to the color
		this.fontColorCallback = fontColorCallback;
	}

}