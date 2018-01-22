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
import org.pepstock.charba.client.commons.Key;

/**
 * Common tick configuration.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class CartesianTick extends Tick {

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		autoSkip,
		autoSkipPadding,
		labelOffset,
		maxRotation,
		minRotation,
		mirror,
		padding
	}

	/**
	 * Builds the object storing the chart instance.
	 * 
	 * @param chart chart instance
	 */
	CartesianTick(AbstractChart<?, ?> chart) {
		super(chart);
	}

	/**
	 * If true, automatically calculates how many labels that can be shown and hides labels accordingly. Turn it off to show all
	 * labels no matter what
	 * 
	 * @param autoSkip If true, automatically calculates how many labels that can be shown and hides labels accordingly. Turn it
	 *            off to show all labels no matter what
	 */
	public void setAutoSkip(boolean autoSkip) {
		setValue(Property.autoSkip, autoSkip);
	}

	/**
	 * If true, automatically calculates how many labels that can be shown and hides labels accordingly. Turn it off to show all
	 * labels no matter what
	 * 
	 * @return If true, automatically calculates how many labels that can be shown and hides labels accordingly. Turn it off to
	 *         show all labels no matter what.
	 */
	public boolean isAutoSkip() {
		return getValue(Property.autoSkip, getAxis().getScale().getTicks().isAutoSkip());
	}

	/**
	 * Sets the padding between the ticks on the horizontal axis when autoSkip is enabled. Note: Only applicable to horizontal
	 * scales.
	 * 
	 * @param autoSkipPadding padding between the ticks on the horizontal axis when autoSkip is enabled. Note: Only applicable
	 *            to horizontal scales.
	 */
	public void setAutoSkipPadding(int autoSkipPadding) {
		setValue(Property.autoSkipPadding, autoSkipPadding);
	}

	/**
	 * Returns the padding between the ticks on the horizontal axis when autoSkip is enabled. Note: Only applicable to
	 * horizontal scales.
	 * 
	 * @return padding between the ticks on the horizontal axis when autoSkip is enabled. Note: Only applicable to horizontal
	 *         scales.
	 */
	public int getAutoSkipPadding() {
		return getValue(Property.autoSkipPadding, getAxis().getScale().getTicks().getAutoSkipPadding());
	}

	/**
	 * Sets the distance in pixels to offset the label from the centre point of the tick (in the y direction for the x axis, and
	 * the x direction for the y axis).<br>
	 * Note: this can cause labels at the edges to be cropped by the edge of the canvas.
	 * 
	 * @param labelOffset the distance in pixels to offset the label from the centre point of the tick (in the y direction for
	 *            the x axis, and the x direction for the y axis)
	 */
	public void setLabelOffset(int labelOffset) {
		setValue(Property.labelOffset, labelOffset);
	}

	/**
	 * Returns the distance in pixels to offset the label from the centre point of the tick (in the y direction for the x axis,
	 * and the x direction for the y axis).<br>
	 * Note: this can cause labels at the edges to be cropped by the edge of the canvas.
	 * 
	 * @return the distance in pixels to offset the label from the centre point of the tick (in the y direction for the x axis,
	 *         and the x direction for the y axis). 
	 */
	public int getLabelOffset() {
		return getValue(Property.labelOffset, getAxis().getScale().getTicks().getLabelOffset());
	}

	/**
	 * Sets the maximum rotation for tick labels when rotating to condense labels. Note: Rotation doesn't occur until necessary.
	 * Note: Only applicable to horizontal scales.
	 * 
	 * @param maxRotation maximum rotation for tick labels when rotating to condense labels. Note: Rotation doesn't occur until
	 *            necessary. Note: Only applicable to horizontal scales.
	 */
	public void setMaxRotation(int maxRotation) {
		setValue(Property.maxRotation, maxRotation);
	}

	/**
	 * Returns the maximum rotation for tick labels when rotating to condense labels. Note: Rotation doesn't occur until
	 * necessary. Note: Only applicable to horizontal scales.
	 * 
	 * @return maximum rotation for tick labels when rotating to condense labels. Note: Rotation doesn't occur until necessary.
	 *         Note: Only applicable to horizontal scales. 
	 */
	public int getMaxRotation() {
		return getValue(Property.maxRotation, getAxis().getScale().getTicks().getMaxRotation());
	}

	/**
	 * Sets the minimum rotation for tick labels. Note: Only applicable to horizontal scales.
	 * 
	 * @param minRotation minimum rotation for tick labels. Note: Only applicable to horizontal scales.
	 */
	public void setMinRotation(int minRotation) {
		setValue(Property.minRotation, minRotation);
	}

	/**
	 * Returns the minimum rotation for tick labels. Note: Only applicable to horizontal scales.
	 * 
	 * @return minimum rotation for tick labels. Note: Only applicable to horizontal scales. 
	 */
	public int getMinRotation() {
		return getValue(Property.minRotation, getAxis().getScale().getTicks().getMinRotation());
	}

	/**
	 * Sets the flips tick labels around axis, displaying the labels inside the chart instead of outside. Note: Only applicable
	 * to vertical scales.
	 * 
	 * @param mirror flips tick labels around axis, displaying the labels inside the chart instead of outside. Note: Only
	 *            applicable to vertical scales.
	 */
	public void setMirror(boolean mirror) {
		setValue(Property.mirror, mirror);
	}

	/**
	 * Returns the flips tick labels around axis, displaying the labels inside the chart instead of outside. Note: Only
	 * applicable to vertical scales.
	 * 
	 * @return flips tick labels around axis, displaying the labels inside the chart instead of outside. Note: Only applicable
	 *         to vertical scales.
	 */
	public boolean isMirror() {
		return getValue(Property.mirror, getAxis().getScale().getTicks().isMirror());
	}

	/**
	 * Sets the padding between the tick label and the axis. When set on a vertical axis, this applies in the horizontal (X)
	 * direction. When set on a horizontal axis, this applies in the vertical (Y) direction.
	 * 
	 * @param padding padding between the tick label and the axis. When set on a vertical axis, this applies in the horizontal
	 *            (X) direction. When set on a horizontal axis, this applies in the vertical (Y) direction.
	 */
	public void setPadding(int padding) {
		setValue(Property.padding, padding);
	}

	/**
	 * Returns the padding between the tick label and the axis. When set on a vertical axis, this applies in the horizontal (X)
	 * direction. When set on a horizontal axis, this applies in the vertical (Y) direction.
	 * 
	 * @return padding between the tick label and the axis. When set on a vertical axis, this applies in the horizontal (X)
	 *         direction. When set on a horizontal axis, this applies in the vertical (Y) direction.
	 */
	public int getPadding() {
		return getValue(Property.padding, getAxis().getScale().getTicks().getPadding());
	}

}