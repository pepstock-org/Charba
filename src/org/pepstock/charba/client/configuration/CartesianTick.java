/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.configuration;

import org.pepstock.charba.client.enums.CrossAlign;
import org.pepstock.charba.client.enums.TickAlign;

/**
 * Common tick configuration, for cartesian axes.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class CartesianTick extends Tick {

	/**
	 * Builds the object storing the axis instance.
	 * 
	 * @param axis axis instance
	 */
	CartesianTick(Axis axis) {
		super(axis);
	}

	/**
	 * If true, automatically calculates how many labels that can be shown and hides labels accordingly. Turn it off to show all labels no matter what
	 * 
	 * @param autoSkip If true, automatically calculates how many labels that can be shown and hides labels accordingly. Turn it off to show all labels no matter what
	 */
	public void setAutoSkip(boolean autoSkip) {
		getConfiguration().setAutoSkip(autoSkip);
	}

	/**
	 * If true, automatically calculates how many labels that can be shown and hides labels accordingly. Turn it off to show all labels no matter what
	 * 
	 * @return If true, automatically calculates how many labels that can be shown and hides labels accordingly. Turn it off to show all labels no matter what.
	 */
	public boolean isAutoSkip() {
		return getConfiguration().isAutoSkip();
	}

	/**
	 * Sets the padding between the ticks on the horizontal axis when autoSkip is enabled. Note: Only applicable to horizontal scales.
	 * 
	 * @param autoSkipPadding padding between the ticks on the horizontal axis when autoSkip is enabled. Note: Only applicable to horizontal scales.
	 */
	public void setAutoSkipPadding(int autoSkipPadding) {
		getConfiguration().setAutoSkipPadding(autoSkipPadding);
	}

	/**
	 * Returns the padding between the ticks on the horizontal axis when autoSkip is enabled. Note: Only applicable to horizontal scales.
	 * 
	 * @return padding between the ticks on the horizontal axis when autoSkip is enabled. Note: Only applicable to horizontal scales.
	 */
	public int getAutoSkipPadding() {
		return getConfiguration().getAutoSkipPadding();
	}

	/**
	 * If <code>true</code>, the defined min and maximum values should be presented as ticks even if they are not "nice"
	 * 
	 * @param includeBounds if <code>true</code>, the defined min and maximum values should be presented as ticks even if they are not "nice"
	 */
	public void setIncludeBounds(boolean includeBounds) {
		getConfiguration().setIncludeBounds(includeBounds);
	}

	/**
	 * If <code>true</code>, the defined minimum and maximum values should be presented as ticks even if they are not "nice".
	 * 
	 * @return if <code>true</code>, the defined min and maximum values should be presented as ticks even if they are not "nice"
	 */
	public boolean isIncludeBounds() {
		return getConfiguration().isIncludeBounds();
	}

	/**
	 * Sets the distance in pixels to offset the label from the center point of the tick (in the y direction for the x axis, and the x direction for the y axis).<br>
	 * Note: this can cause labels at the edges to be cropped by the edge of the canvas.
	 * 
	 * @param labelOffset the distance in pixels to offset the label from the center point of the tick (in the y direction for the x axis, and the x direction for the y axis)
	 */
	public void setLabelOffset(int labelOffset) {
		getConfiguration().setLabelOffset(labelOffset);
	}

	/**
	 * Returns the distance in pixels to offset the label from the center point of the tick (in the y direction for the x axis, and the x direction for the y axis).<br>
	 * Note: this can cause labels at the edges to be cropped by the edge of the canvas.
	 * 
	 * @return the distance in pixels to offset the label from the center point of the tick (in the y direction for the x axis, and the x direction for the y axis).
	 */
	public int getLabelOffset() {
		return getConfiguration().getLabelOffset();
	}

	/**
	 * Sets the maximum rotation for tick labels when rotating to condense labels. Note: Rotation doesn't occur until necessary. Note: Only applicable to horizontal scales.
	 * 
	 * @param maxRotation maximum rotation for tick labels when rotating to condense labels. Note: Rotation doesn't occur until necessary. Note: Only applicable to horizontal
	 *            scales.
	 */
	public void setMaxRotation(int maxRotation) {
		getConfiguration().setMaxRotation(maxRotation);
	}

	/**
	 * Returns the maximum rotation for tick labels when rotating to condense labels. Note: Rotation doesn't occur until necessary. Note: Only applicable to horizontal scales.
	 * 
	 * @return maximum rotation for tick labels when rotating to condense labels. Note: Rotation doesn't occur until necessary. Note: Only applicable to horizontal scales.
	 */
	public int getMaxRotation() {
		return getConfiguration().getMaxRotation();
	}

	/**
	 * Sets the minimum rotation for tick labels. Note: Only applicable to horizontal scales.
	 * 
	 * @param minRotation minimum rotation for tick labels. Note: Only applicable to horizontal scales.
	 */
	public void setMinRotation(int minRotation) {
		getConfiguration().setMinRotation(minRotation);
	}

	/**
	 * Returns the minimum rotation for tick labels. Note: Only applicable to horizontal scales.
	 * 
	 * @return minimum rotation for tick labels. Note: Only applicable to horizontal scales.
	 */
	public int getMinRotation() {
		return getConfiguration().getMinRotation();
	}

	/**
	 * Sets the flips tick labels around axis, displaying the labels inside the chart instead of outside. Note: Only applicable to vertical scales.
	 * 
	 * @param mirror flips tick labels around axis, displaying the labels inside the chart instead of outside. Note: Only applicable to vertical scales.
	 */
	public void setMirror(boolean mirror) {
		getConfiguration().setMirror(mirror);
	}

	/**
	 * Returns the flips tick labels around axis, displaying the labels inside the chart instead of outside. Note: Only applicable to vertical scales.
	 * 
	 * @return flips tick labels around axis, displaying the labels inside the chart instead of outside. Note: Only applicable to vertical scales.
	 */
	public boolean isMirror() {
		return getConfiguration().isMirror();
	}

	/**
	 * Sets the number of ticks to examine when deciding how many labels will fit.<br>
	 * Setting a smaller value will be faster, but may be less accurate when there is large variability in label length.
	 * 
	 * @param sampleSize the number of ticks to examine when deciding how many labels will fit.
	 */
	public void setSampleSize(int sampleSize) {
		getConfiguration().setSampleSize(sampleSize);
	}

	/**
	 * Returns the number of ticks to examine when deciding how many labels will fit.<br>
	 * Setting a smaller value will be faster, but may be less accurate when there is large variability in label length.
	 * 
	 * @return the number of ticks to examine when deciding how many labels will fit.
	 */
	public int getSampleSize() {
		return getConfiguration().getSampleSize();
	}

	/**
	 * Sets the tick alignment along the axis.
	 * 
	 * @param align the tick alignment along the axis
	 */
	public void setAlign(TickAlign align) {
		getConfiguration().setAlign(align);
	}

	/**
	 * Returns the tick alignment along the axis.
	 * 
	 * @return the tick alignment along the axis
	 */
	public TickAlign getAlign() {
		return getConfiguration().getAlign();
	}

	/**
	 * Sets the tick alignment perpendicular to the axis.
	 * 
	 * @param crossAlign the tick alignment perpendicular to the axis
	 */
	public void setCrossAlign(CrossAlign crossAlign) {
		getConfiguration().setCrossAlign(crossAlign);
	}

	/**
	 * Returns the tick alignment perpendicular to the axis.
	 * 
	 * @return the tick alignment perpendicular to the axis
	 */
	public CrossAlign getCrossAlign() {
		return getConfiguration().getCrossAlign();
	}

}