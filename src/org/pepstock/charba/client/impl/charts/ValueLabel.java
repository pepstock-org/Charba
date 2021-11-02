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

import org.pepstock.charba.client.callbacks.MeterFormatCallback;
import org.pepstock.charba.client.defaults.IsDefaultFont;

/**
 * Meter element class to define the value label to render in the meter chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ValueLabel extends AbstractMeterElement {

	/**
	 * Default precision <b>{@value DEFAULT_PRECISION}</b> to apply rendering the value or percentage.
	 */
	public static final int DEFAULT_PRECISION = 2;

	private static final boolean DEFAULT_ANIMATED = false;

	private boolean percentage = false;

	private int precision = DEFAULT_PRECISION;

	private boolean animated = DEFAULT_ANIMATED;

	private MeterFormatCallback formatCallback = null;

	/**
	 * Creates the element getting the font defaults as argument.
	 * 
	 * @param defaultValues font defaults to use to initialize the font option
	 */
	ValueLabel(IsDefaultFont defaultValues) {
		super(defaultValues);
	}

	/**
	 * Returns <code>true</code> if the value will be rendered as percentage.
	 * 
	 * @return <code>true</code> if the value will be rendered as percentage
	 */
	public boolean isPercentage() {
		return percentage;
	}

	/**
	 * Sets <code>true</code> if the value will be rendered as percentage.
	 * 
	 * @param percentage <code>true</code> if the value will be rendered as percentage
	 */
	public void setPercentage(boolean percentage) {
		this.percentage = percentage;
	}

	/**
	 * Returns the decimal places to apply to render the value.
	 * 
	 * @return the decimal places to apply to the value in chart
	 */
	public int getPrecision() {
		return precision;
	}

	/**
	 * Sets the decimal places to apply to render the value.
	 * 
	 * @param precision the decimal places to apply to the value in chart
	 */
	public void setPrecision(int precision) {
		this.precision = precision;
	}

	/**
	 * Returns if the render will be shown based on the animation of chart.
	 * 
	 * @return the <code>true</code> the rendering is animated, otherwise <code>false</code>
	 */
	public boolean isAnimated() {
		return animated;
	}

	/**
	 * Sets if the render will be shown based on the animation of chart.
	 * 
	 * @param animated <code>true</code> if the rendering is animated, otherwise <code>false</code>
	 */
	public void setAnimated(boolean animated) {
		this.animated = animated;
	}

	// --------------------------------------
	// CALLBACK
	// --------------------------------------

	/**
	 * Returns the callback to customize the value string in the chart.
	 * 
	 * @return the callback to customize the value string in the chart
	 */
	public MeterFormatCallback getFormatCallback() {
		return formatCallback;
	}

	/**
	 * Sets the callback to customize the value string in the chart.
	 * 
	 * @param formatCallback the callback to customize the value string in the chart
	 */
	public void setFormatCallback(MeterFormatCallback formatCallback) {
		this.formatCallback = formatCallback;
	}

}
