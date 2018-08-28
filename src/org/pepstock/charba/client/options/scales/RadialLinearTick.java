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
package org.pepstock.charba.client.options.scales;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.Key;

/**
 * This object is used to map defined radial axis as linear.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class RadialLinearTick extends Tick {

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		backdropColor,
		backdropPaddingX,
		backdropPaddingY,
		showLabelBackdrop,
		beginAtZero,
		min,
		max,
		maxTicksLimit,
		stepSize,
		suggestedMax,
		suggestedMin
	}

	/**
	 * Builds the object storing the chart instance.
	 * 
	 * @param chart chart instance
	 */
	RadialLinearTick(AbstractChart<?, ?> chart) {
		super(chart);
	}

	/**
	 * If true, scale will include 0 if it is not already included.
	 * 
	 * @param beginAtZero if true, scale will include 0 if it is not already included.
	 */
	public void setBeginAtZero(boolean beginAtZero) {
		setValue(Property.beginAtZero, beginAtZero);
	}

	/**
	 * If true, scale will include 0 if it is not already included.
	 * 
	 * @return if true, scale will include 0 if it is not already included.
	 */
	public boolean isBeginAtZero() {
		return getValue(Property.beginAtZero, getAxis().getScale().getTicks().isBeginAtZero());
	}

	/**
	 * Sets the user defined minimum number for the scale, overrides minimum value from data.
	 * 
	 * @param min user defined minimum number for the scale, overrides minimum value from data.
	 */
	public void setMin(double min) {
		setValue(Property.min, min);
	}

	/**
	 * Returns the user defined minimum number for the scale, overrides minimum value from data.
	 * 
	 * @return user defined minimum number for the scale, overrides minimum value from data.
	 */
	public double getMin() {
		return getValue(Property.min, getAxis().getScale().getTicks().getMin());
	}

	/**
	 * Sets the user defined maximum number for the scale, overrides maximum value from data.
	 * 
	 * @param max user defined maximum number for the scale, overrides maximum value from data.
	 */
	public void setMax(double max) {
		setValue(Property.max, max);
	}

	/**
	 * Returns the user defined maximum number for the scale, overrides maximum value from data.
	 * 
	 * @return user defined maximum number for the scale, overrides maximum value from data.
	 */
	public double getMax() {
		return getValue(Property.max, getAxis().getScale().getTicks().getMax());
	}

	/**
	 * Sets the maximum number of ticks and gridlines to show.
	 * 
	 * @param maxTicksLimit maximum number of ticks and gridlines to show.
	 */
	public void setMaxTicksLimit(int maxTicksLimit) {
		setValue(Property.maxTicksLimit, maxTicksLimit);
	}

	/**
	 * Returns the maximum number of ticks and gridlines to show.
	 * 
	 * @return maximum number of ticks and gridlines to show.
	 */
	public int getMaxTicksLimit() {
		return getValue(Property.maxTicksLimit, getAxis().getScale().getTicks().getMaxTicksLimit());
	}

	/**
	 * Sets the user defined fixed step size for the scale.
	 * 
	 * @param stepSize user defined fixed step size for the scale.
	 */
	public void setStepSize(double stepSize) {
		setValue(Property.stepSize, stepSize);
	}

	/**
	 * Returns the user defined fixed step size for the scale.
	 * 
	 * @return user defined fixed step size for the scale.
	 */
	public double getStepSize() {
		return getValue(Property.stepSize, getAxis().getScale().getTicks().getStepSize());
	}

	/**
	 * Sets the adjustment used when calculating the maximum data value.
	 * 
	 * @param suggestedMax adjustment used when calculating the maximum data value.
	 */
	public void setSuggestedMax(double suggestedMax) {
		setValue(Property.suggestedMax, suggestedMax);
	}

	/**
	 * Returns the adjustment used when calculating the maximum data value.
	 * 
	 * @return adjustment used when calculating the maximum data value.
	 */
	public double getSuggestedMax() {
		return getValue(Property.suggestedMax, getAxis().getScale().getTicks().getSuggestedMax());
	}

	/**
	 * Sets the adjustment used when calculating the minimum data value.
	 * 
	 * @param suggestedMin adjustment used when calculating the minimum data value.
	 */
	public void setSuggestedMin(double suggestedMin) {
		setValue(Property.suggestedMin, suggestedMin);
	}

	/**
	 * Returns the adjustment used when calculating the minimum data value.
	 * 
	 * @return adjustment used when calculating the minimum data value.
	 */
	public double getSuggestedMin() {
		return getValue(Property.suggestedMin, getAxis().getScale().getTicks().getSuggestedMin());
	}

	/**
	 * Sets the color of label backdrops.
	 * 
	 * @param backdropColor color of label backdrops.
	 */
	public void setBackdropColor(IsColor backdropColor) {
		setBackdropColor(backdropColor.toRGBA());
	}

	/**
	 * Sets the color of label backdrops.
	 * 
	 * @param backdropColor color of label backdrops.
	 */
	public void setBackdropColor(String backdropColor) {
		setValue(Property.backdropColor, backdropColor);
	}

	/**
	 * Returns the color of label backdrops.
	 * 
	 * @return color of label backdrops.
	 */
	public String getBackdropColorAsString() {
		return getValue(Property.backdropColor, getAxis().getScale().getTicks().getBackdropColorAsString());
	}

	/**
	 * Returns the color of label backdrops.
	 * 
	 * @return color of label backdrops.
	 */
	public IsColor getBackdropColor() {
		return ColorBuilder.parse(getBackdropColorAsString());
	}

	/**
	 * Sets the horizontal padding of label backdrop.
	 * 
	 * @param backdropPaddingX horizontal padding of label backdrop.
	 */
	public void setBackdropPaddingX(int backdropPaddingX) {
		setValue(Property.backdropPaddingX, backdropPaddingX);
	}

	/**
	 * Returns the horizontal padding of label backdrop.
	 * 
	 * @return horizontal padding of label backdrop.
	 */
	public int getBackdropPaddingX() {
		return getValue(Property.backdropPaddingX, getAxis().getScale().getTicks().getBackdropPaddingX());
	}

	/**
	 * Sets the vertical padding of label backdrop.
	 * 
	 * @param backdropPaddingY vertical padding of label backdrop.
	 */
	public void setBackdropPaddingY(int backdropPaddingY) {
		setValue(Property.backdropPaddingY, backdropPaddingY);
	}

	/**
	 * Returns the vertical padding of label backdrop.
	 * 
	 * @return vertical padding of label backdrop.
	 */
	public int getBackdropPaddingY() {
		return getValue(Property.backdropPaddingY, getAxis().getScale().getTicks().getBackdropPaddingY());
	}

	/**
	 * If true, draw a background behind the tick labels.
	 * 
	 * @param showLabelBackdrop if true, draw a background behind the tick labels.
	 */
	public void setShowLabelBackdrop(boolean showLabelBackdrop) {
		setValue(Property.showLabelBackdrop, showLabelBackdrop);
	}

	/**
	 * If true, draw a background behind the tick labels.
	 * 
	 * @return if true, draw a background behind the tick labels.
	 */
	public boolean isShowLabelBackdrop() {
		return getValue(Property.showLabelBackdrop, getAxis().getScale().getTicks().isShowLabelBackdrop());
	}

}