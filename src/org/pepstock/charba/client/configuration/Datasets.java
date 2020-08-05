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
package org.pepstock.charba.client.configuration;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.options.ExtendedOptions;

/**
 * It contains the options to apply to all datasets of the chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class Datasets extends ConfigurationContainer<ExtendedOptions> {

	private final Animation animation;

	/**
	 * Builds the object storing the chart instance and the root options element.
	 * 
	 * @param chart chart instance
	 * @param options root options element.
	 */
	Datasets(IsChart chart, ExtendedOptions options) {
		super(chart, options);
		// creates animations elements
		animation = new Animation(chart, getConfiguration());
	}

	/**
	 * Returns the animation element.
	 * 
	 * @return the animation
	 */
	public final Animation getAnimation() {
		return animation;
	}

	/**
	 * Enables or disables the animation.
	 * 
	 * @param enabled if <code>true</code> the animation is enabled otherwise <code>false</code> to disable it.
	 */
	public void setAnimationEnabled(boolean enabled) {
		getConfiguration().setAnimationEnabled(enabled);
	}

	/**
	 * Returns <code>true</code> if animation is enabled, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if animation is enabled, otherwise <code>false</code>
	 */
	public boolean isAnimationEnabled() {
		return getConfiguration().isAnimationEnabled();
	}

	/**
	 * Sets the percent (0-1) of the available width each bar should be within the category width. 1.0 will take the whole category width and put the bars right next to each other.
	 * 
	 * @param barPercentage percent (0-1) of the available width each bar should be within the category width. 1.0 will take the whole category width and put the bars right next to
	 *            each other.
	 */
	public void setBarPercentage(double barPercentage) {
		getConfiguration().getDatasets().setBarPercentage(barPercentage);
	}

	/**
	 * Returns the percent (0-1) of the available width each bar should be within the category width. 1.0 will take the whole category width and put the bars right next to each
	 * other.
	 * 
	 * @return percent (0-1) of the available width each bar should be within the category width. 1.0 will take the whole category width and put the bars right next to each other.
	 */
	public double getBarPercentage() {
		return getConfiguration().getDatasets().getBarPercentage();
	}

	/**
	 * Sets the percent (0-1) of the available width each category should be within the sample width.
	 * 
	 * @param categoryPercentage percent (0-1) of the available width each category should be within the sample width.
	 */
	public void setCategoryPercentage(double categoryPercentage) {
		getConfiguration().getDatasets().setCategoryPercentage(categoryPercentage);
	}

	/**
	 * Returns the percent (0-1) of the available width each category should be within the sample width.
	 * 
	 * @return the percent (0-1) of the available width each category should be within the sample width.
	 */
	public double getCategoryPercentage() {
		return getConfiguration().getDatasets().getCategoryPercentage();
	}

	/**
	 * Sets the width of each bar in pixels. If set to 'flex', it computes "optimal" sample widths that globally arrange bars side by side. If not set, the base sample widths are
	 * calculated automatically so that they take the full available widths without overlap. Then, the bars are sized using barPercentage and categoryPercentage.
	 * 
	 * @param barThickness width of each bar in pixels. If not set, the base sample widths are calculated automatically so that they take the full available widths without overlap.
	 *            Then, the bars are sized using barPercentage and categoryPercentage.
	 */
	public void setBarThickness(int barThickness) {
		getConfiguration().getDatasets().setBarThickness(barThickness);
	}

	/**
	 * Returns the width of each bar in pixels. If set to 'flex', it computes "optimal" sample widths that globally arrange bars side by side. If not set, the base sample widths
	 * are calculated automatically so that they take the full available widths without overlap. Then, the bars are sized using barPercentage and categoryPercentage.
	 * 
	 * @return width of each bar in pixels. If not set, the base sample widths are calculated automatically so that they take the full available widths without overlap. Then, the
	 *         bars are sized using barPercentage and categoryPercentage.
	 */
	public int getBarThickness() {
		// if here, is not flex
		return getConfiguration().getDatasets().getBarThickness();
	}

	/**
	 * Sets the maximum bar thickness, to ensure that bars are not sized thicker than this
	 * 
	 * @param maxBarThickness the maximum bar thickness.
	 */
	public void setMaxBarThickness(int maxBarThickness) {
		getConfiguration().getDatasets().setMaxBarThickness(maxBarThickness);
	}

	/**
	 * Returns the maximum bar thickness.
	 * 
	 * @return the maximum bar thickness.
	 */
	public int getMaxBarThickness() {
		return getConfiguration().getDatasets().getMaxBarThickness();
	}

	/**
	 * Set this to ensure that bars have a minimum length in pixels.
	 * 
	 * @param minBarLength a minimum length in pixels.
	 */
	public void setMinBarLength(int minBarLength) {
		getConfiguration().getDatasets().setMinBarLength(minBarLength);
	}

	/**
	 * Returns a minimum length in pixels.
	 * 
	 * @return a minimum length in pixels.
	 */
	public int getMinBarLength() {
		return getConfiguration().getDatasets().getMinBarLength();
	}
}