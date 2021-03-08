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

import org.pepstock.charba.client.Type;

/**
 * It contains the options to apply to all datasets of the chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class TypedDataset extends ConfigurationOptionsContainer implements HasAnimation {

	// animation container instance
	private final AnimationContainer animationContainer;
	// instance of chart type
	private final Type type;

	/**
	 * Builds the object storing the root options element.
	 * 
	 * @param options root options element.
	 */
	TypedDataset(ConfigurationOptions options) {
		this(options.getChart().getType(), options);
	}
	
	/**
	 * Builds the object storing the chart type and the root options element.
	 * 
	 * @param type chart type.
	 * @param options root options element.
	 */
	TypedDataset(Type type, ConfigurationOptions options) {
		super(options);
		// checks if type is consistent
		this.type = Type.checkAndGetIfValid(type);
		// creates animation configuration manager
		this.animationContainer = new AnimationContainer(getChart(), () -> getConfiguration().getDatasets().get(type));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.HasAnimation#getAnimationContainer()
	 */
	@Override
	public final AnimationContainer getAnimationContainer() {
		return animationContainer;
	}

	/**
	 * Sets the percent (0-1) of the available width each bar should be within the category width.<br>
	 * 1.0 will take the whole category width and put the bars right next to each other.
	 * 
	 * @param barPercentage percent (0-1) of the available width each bar should be within the category width.<br>
	 *            1.0 will take the whole category width and put the bars right next to each other.
	 */
	public void setBarPercentage(double barPercentage) {
		getConfiguration().getDatasets().get(type).setBarPercentage(barPercentage);
	}

	/**
	 * Returns the percent (0-1) of the available width each bar should be within the category width.<br>
	 * 1.0 will take the whole category width and put the bars right next to each other.
	 * 
	 * @return percent (0-1) of the available width each bar should be within the category width.<br>
	 *         1.0 will take the whole category width and put the bars right next to each other.
	 */
	public double getBarPercentage() {
		return getConfiguration().getDatasets().get(type).getBarPercentage();
	}

	/**
	 * Sets the percent (0-1) of the available width each category should be within the sample width.
	 * 
	 * @param categoryPercentage percent (0-1) of the available width each category should be within the sample width.
	 */
	public void setCategoryPercentage(double categoryPercentage) {
		getConfiguration().getDatasets().get(type).setCategoryPercentage(categoryPercentage);
	}

	/**
	 * Returns the percent (0-1) of the available width each category should be within the sample width.
	 * 
	 * @return the percent (0-1) of the available width each category should be within the sample width.
	 */
	public double getCategoryPercentage() {
		return getConfiguration().getDatasets().get(type).getCategoryPercentage();
	}

	/**
	 * Sets the width of each bar in pixels.<br>
	 * If set to 'flex', it computes "optimal" sample widths that globally arrange bars side by side.<br>
	 * If not set, the base sample widths are calculated automatically so that they take the full available widths without overlap.<br>
	 * Then, the bars are sized using barPercentage and categoryPercentage.
	 * 
	 * @param barThickness width of each bar in pixels.<br>
	 *            If not set, the base sample widths are calculated automatically so that they take the full available widths without overlap.<br>
	 *            Then, the bars are sized using barPercentage and categoryPercentage.
	 */
	public void setBarThickness(int barThickness) {
		getConfiguration().getDatasets().get(type).setBarThickness(barThickness);
	}

	/**
	 * Returns the width of each bar in pixels.<br>
	 * If set to 'flex', it computes "optimal" sample widths that globally arrange bars side by side.<br>
	 * If not set, the base sample widths are calculated automatically so that they take the full available widths without overlap.<br>
	 * Then, the bars are sized using barPercentage and categoryPercentage.
	 * 
	 * @return width of each bar in pixels.<br>
	 *         If not set, the base sample widths are calculated automatically so that they take the full available widths without overlap.<br>
	 *         Then, the bars are sized using barPercentage and categoryPercentage.
	 */
	public int getBarThickness() {
		// if here, is not flex
		return getConfiguration().getDatasets().get(type).getBarThickness();
	}

	/**
	 * Sets the maximum bar thickness, to ensure that bars are not sized thicker than this.
	 * 
	 * @param maxBarThickness the maximum bar thickness.
	 */
	public void setMaxBarThickness(int maxBarThickness) {
		getConfiguration().getDatasets().get(type).setMaxBarThickness(maxBarThickness);
	}

	/**
	 * Returns the maximum bar thickness.
	 * 
	 * @return the maximum bar thickness.
	 */
	public int getMaxBarThickness() {
		return getConfiguration().getDatasets().get(type).getMaxBarThickness();
	}

	/**
	 * Set this to ensure that bars have a minimum length in pixels.
	 * 
	 * @param minBarLength a minimum length in pixels.
	 */
	public void setMinBarLength(int minBarLength) {
		getConfiguration().getDatasets().get(type).setMinBarLength(minBarLength);
	}

	/**
	 * Returns a minimum length in pixels.
	 * 
	 * @return a minimum length in pixels.
	 */
	public int getMinBarLength() {
		return getConfiguration().getDatasets().get(type).getMinBarLength();
	}
}