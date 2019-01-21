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

import org.pepstock.charba.client.colors.IsColor;

/**
 * This object is used to map defined radial axis as linear.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class RadialLinearTick extends Tick {

	/**
	 * Builds the object storing the axis which this tick belongs to.
	 * 
	 * @param axis axis which this tick belongs to.
	 */
	RadialLinearTick(Axis axis) {
		super(axis);
	}

	/**
	 * If true, scale will include 0 if it is not already included.
	 * 
	 * @param beginAtZero if true, scale will include 0 if it is not already included.
	 */
	public void setBeginAtZero(boolean beginAtZero) {
		getConfiguration().setBeginAtZero(beginAtZero);
	}

	/**
	 * If true, scale will include 0 if it is not already included.
	 * 
	 * @return if true, scale will include 0 if it is not already included.
	 */
	public boolean isBeginAtZero() {
		return getConfiguration().isBeginAtZero();
	}

	/**
	 * Sets the user defined minimum number for the scale, overrides minimum value from data.
	 * 
	 * @param min user defined minimum number for the scale, overrides minimum value from data.
	 */
	public void setMin(double min) {
		getConfiguration().setMin(min);
	}

	/**
	 * Returns the user defined minimum number for the scale, overrides minimum value from data.
	 * 
	 * @return user defined minimum number for the scale, overrides minimum value from data.
	 */
	public double getMin() {
		return getConfiguration().getMin();
	}

	/**
	 * Sets the user defined maximum number for the scale, overrides maximum value from data.
	 * 
	 * @param max user defined maximum number for the scale, overrides maximum value from data.
	 */
	public void setMax(double max) {
		getConfiguration().setMax(max);
	}

	/**
	 * Returns the user defined maximum number for the scale, overrides maximum value from data.
	 * 
	 * @return user defined maximum number for the scale, overrides maximum value from data.
	 */
	public double getMax() {
		return getConfiguration().getMax();
	}

	/**
	 * Sets the maximum number of ticks and gridlines to show.
	 * 
	 * @param maxTicksLimit maximum number of ticks and gridlines to show.
	 */
	public void setMaxTicksLimit(int maxTicksLimit) {
		getConfiguration().setMaxTicksLimit(maxTicksLimit);
	}

	/**
	 * Returns the maximum number of ticks and gridlines to show.
	 * 
	 * @return maximum number of ticks and gridlines to show.
	 */
	public int getMaxTicksLimit() {
		return getConfiguration().getMaxTicksLimit();
	}

	/**
	 * Sets the user defined fixed step size for the scale.
	 * 
	 * @param stepSize user defined fixed step size for the scale.
	 */
	public void setStepSize(double stepSize) {
		getConfiguration().setStepSize(stepSize);
	}

	/**
	 * Returns the user defined fixed step size for the scale.
	 * 
	 * @return user defined fixed step size for the scale.
	 */
	public double getStepSize() {
		return getConfiguration().getStepSize();
	}

	/**
	 * Sets the adjustment used when calculating the maximum data value.
	 * 
	 * @param suggestedMax adjustment used when calculating the maximum data value.
	 */
	public void setSuggestedMax(double suggestedMax) {
		getConfiguration().setSuggestedMax(suggestedMax);
	}

	/**
	 * Returns the adjustment used when calculating the maximum data value.
	 * 
	 * @return adjustment used when calculating the maximum data value.
	 */
	public double getSuggestedMax() {
		return getConfiguration().getSuggestedMax();
	}

	/**
	 * Sets the adjustment used when calculating the minimum data value.
	 * 
	 * @param suggestedMin adjustment used when calculating the minimum data value.
	 */
	public void setSuggestedMin(double suggestedMin) {
		getConfiguration().setSuggestedMin(suggestedMin);
	}

	/**
	 * Returns the adjustment used when calculating the minimum data value.
	 * 
	 * @return adjustment used when calculating the minimum data value.
	 */
	public double getSuggestedMin() {
		return getConfiguration().getSuggestedMin();
	}

	/**
	 * Sets the color of label backdrops.
	 * 
	 * @param backdropColor color of label backdrops.
	 */
	public void setBackdropColor(IsColor backdropColor) {
		getConfiguration().setBackdropColor(backdropColor);
	}

	/**
	 * Sets the color of label backdrops.
	 * 
	 * @param backdropColor color of label backdrops.
	 */
	public void setBackdropColor(String backdropColor) {
		getConfiguration().setBackdropColor(backdropColor);
	}

	/**
	 * Returns the color of label backdrops.
	 * 
	 * @return color of label backdrops.
	 */
	public String getBackdropColorAsString() {
		return getConfiguration().getBackdropColorAsString();
	}

	/**
	 * Returns the color of label backdrops.
	 * 
	 * @return color of label backdrops.
	 */
	public IsColor getBackdropColor() {
		return getConfiguration().getBackdropColor();
	}

	/**
	 * Sets the horizontal padding of label backdrop.
	 * 
	 * @param backdropPaddingX horizontal padding of label backdrop.
	 */
	public void setBackdropPaddingX(int backdropPaddingX) {
		getConfiguration().setBackdropPaddingX(backdropPaddingX);
	}

	/**
	 * Returns the horizontal padding of label backdrop.
	 * 
	 * @return horizontal padding of label backdrop.
	 */
	public int getBackdropPaddingX() {
		return getConfiguration().getBackdropPaddingX();
	}

	/**
	 * Sets the vertical padding of label backdrop.
	 * 
	 * @param backdropPaddingY vertical padding of label backdrop.
	 */
	public void setBackdropPaddingY(int backdropPaddingY) {
		getConfiguration().setBackdropPaddingY(backdropPaddingY);
	}

	/**
	 * Returns the vertical padding of label backdrop.
	 * 
	 * @return vertical padding of label backdrop.
	 */
	public int getBackdropPaddingY() {
		return getConfiguration().getBackdropPaddingY();
	}

	/**
	 * If true, draw a background behind the tick labels.
	 * 
	 * @param showLabelBackdrop if true, draw a background behind the tick labels.
	 */
	public void setShowLabelBackdrop(boolean showLabelBackdrop) {
		getConfiguration().setShowLabelBackdrop(showLabelBackdrop);
	}

	/**
	 * If true, draw a background behind the tick labels.
	 * 
	 * @return if true, draw a background behind the tick labels.
	 */
	public boolean isShowLabelBackdrop() {
		return getConfiguration().isShowLabelBackdrop();
	}

	/**
	 * If defined and stepSize is not specified, the step size will be rounded to this many decimal places.
	 * 
	 * @param precision if defined and stepSize is not specified, the step size will be rounded to this many decimal places.
	 */
	public void setPrecision(int precision) {
		getConfiguration().setPrecision(precision);
	}

	/**
	 * If defined and stepSize is not specified, the step size will be rounded to this many decimal places.
	 * 
	 * @return if defined and stepSize is not specified, the step size will be rounded to this many decimal places.
	 */
	public int getPrecision() {
		return getConfiguration().getPrecision();
	}

}