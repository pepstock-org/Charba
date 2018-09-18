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
package org.pepstock.charba.client.jsinterop.options;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.defaults.AbstractItem;
import org.pepstock.charba.client.defaults.FontItem;

/**
 * All configuration for ticks of a chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Ticks extends FontItem {
	
	private static final boolean DEFAULT_DISPLAY = true;
	
	private static final boolean DEFAULT_BEGIN_AT_ZERO = false;

	private static final boolean DEFAULT_REVERSE = false;
	
	private static final boolean DEFAULT_AUTO_SKIP = true;

	private static final int DEFAULT_AUTO_SKIP_PADDING = 0;

	private static final int DEFAULT_LABEL_OFFSET = 0;

	private static final int DEFAULT_MAX_ROTATION = 50;

	private static final int DEFAULT_MIN_ROTATION = 0;

	private static final boolean DEFAULT_MIRROR = false;

	private static final int DEFAULT_PADDING = 0;
	
	private static final double DEFAULT_MIN = Double.MIN_VALUE;

	private static final double DEFAULT_MAX = Double.MAX_VALUE;

	private static final int DEFAULT_MAX_TICKS_LIMIT = 11;

	private static final double DEFAULT_STEP_SIZE = Double.MIN_VALUE;

	private static final double DEFAULT_SUGGESTED_MAX = Double.MAX_VALUE;

	private static final double DEFAULT_SUGGESTED_MIN = Double.MIN_VALUE;
	
	private static final String DEFAULT_BACKDROP_COLOR = "rgba(255,255,255,0.75)";

	private static final int DEFAULT_BACKDROP_PADDING_X = 2;

	private static final int DEFAULT_BACKDROP_PADDING_Y = 2;

	private static final boolean DEFAULT_SHOW_LABEL_BACKDROP = true;

	private final FontItem minor;

	private final FontItem major;

	/**
	 * Name of fields of JavaScript object.
	 */
	enum Property implements Key
	{
		display,
		reverse,
		autoSkip,
		autoSkipPadding,
		labelOffset,
		maxRotation,
		minRotation,
		mirror,
		padding,
		beginAtZero,
		minor,
		major,
		min,
		max,
		maxTicksLimit,
		stepSize,
		suggestedMax,
		suggestedMin,
		backdropColor,
		backdropPaddingX,
		backdropPaddingY,
		showLabelBackdrop
	}

	/**
	 * Builds the object with parent item and child.
	 * 
	 * @param parent parent item.
	 * @param childKey key of child.
	 */
	Ticks(AbstractItem parent, Key childKey) {
		super(parent, childKey);
		// creates children objects
		minor = new FontItemImpl(this, Property.minor);
		major = new FontItemImpl(this, Property.major);
	}
	
	/**
	 * @return the minor
	 */
	public FontItem getMinor() {
		return minor;
	}

	/**
	 * @return the major
	 */
	public FontItem getMajor() {
		return major;
	}
	
	/**
	 * If true, scale will include 0 if it is not already included.
	 * 
	 * @param beginAtZero if true, scale will include 0 if it is not already included.
	 */
	public void setBeginAtZero(boolean beginAtZero) {
		setValue(Property.beginAtZero, beginAtZero);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If true, scale will include 0 if it is not already included.
	 * 
	 * @return if true, scale will include 0 if it is not already included.. Default is false
	 */
	public boolean isBeginAtZero() {
		return getValue(Property.beginAtZero, DEFAULT_BEGIN_AT_ZERO);
	}

	/**
	 * If true, show tick marks.
	 * 
	 * @param display if true, show tick marks
	 */
	public void setDisplay(boolean display) {
		setValue(Property.display, display);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If true, show tick marks
	 * 
	 * @return if true, show tick marks. Default is true.
	 */
	public boolean isDisplay() {
		return getValue(Property.display, DEFAULT_DISPLAY);
	}

	/**
	 * Sets the reverses order of tick labels.
	 * 
	 * @param reverse reverses order of tick labels.
	 */
	public void setReverse(boolean reverse) {
		setValue(Property.reverse, reverse);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the reverses order of tick labels.
	 * 
	 * @return reverses order of tick labels. Default is false.
	 */
	public boolean isReverse() {
		return getValue(Property.reverse, DEFAULT_REVERSE);
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
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If true, automatically calculates how many labels that can be shown and hides labels accordingly. Turn it off to show all
	 * labels no matter what
	 * 
	 * @return If true, automatically calculates how many labels that can be shown and hides labels accordingly. Turn it off to
	 *         show all labels no matter what. Default is true
	 */
	public boolean isAutoSkip() {
		return getValue(Property.autoSkip, DEFAULT_AUTO_SKIP);
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
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the padding between the ticks on the horizontal axis when autoSkip is enabled. Note: Only applicable to
	 * horizontal scales.
	 * 
	 * @return padding between the ticks on the horizontal axis when autoSkip is enabled. Note: Only applicable to horizontal
	 *         scales. Defualt is 0.
	 */
	public int getAutoSkipPadding() {
		return getValue(Property.autoSkipPadding, DEFAULT_AUTO_SKIP_PADDING);
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
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the distance in pixels to offset the label from the centre point of the tick (in the y direction for the x axis,
	 * and the x direction for the y axis).<br>
	 * Note: this can cause labels at the edges to be cropped by the edge of the canvas.
	 * 
	 * @return the distance in pixels to offset the label from the centre point of the tick (in the y direction for the x axis,
	 *         and the x direction for the y axis). Default is 0.
	 */
	public int getLabelOffset() {
		return getValue(Property.labelOffset, DEFAULT_LABEL_OFFSET);
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
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the maximum rotation for tick labels when rotating to condense labels. Note: Rotation doesn't occur until
	 * necessary. Note: Only applicable to horizontal scales.
	 * 
	 * @return maximum rotation for tick labels when rotating to condense labels. Note: Rotation doesn't occur until necessary.
	 *         Note: Only applicable to horizontal scales. Default is 90
	 */
	public int getMaxRotation() {
		return getValue(Property.maxRotation, DEFAULT_MAX_ROTATION);
	}

	/**
	 * Sets the minimum rotation for tick labels. Note: Only applicable to horizontal scales.
	 * 
	 * @param minRotation minimum rotation for tick labels. Note: Only applicable to horizontal scales.
	 */
	public void setMinRotation(int minRotation) {
		setValue(Property.minRotation, minRotation);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the minimum rotation for tick labels. Note: Only applicable to horizontal scales.
	 * 
	 * @return minimum rotation for tick labels. Note: Only applicable to horizontal scales.. Default is 0.
	 */
	public int getMinRotation() {
		return getValue(Property.minRotation, DEFAULT_MIN_ROTATION);
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
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the flips tick labels around axis, displaying the labels inside the chart instead of outside. Note: Only
	 * applicable to vertical scales.
	 * 
	 * @return flips tick labels around axis, displaying the labels inside the chart instead of outside. Note: Only applicable
	 *         to vertical scales. Default is false.
	 */
	public boolean isMirror() {
		return getValue(Property.mirror, DEFAULT_MIRROR);
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
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the padding between the tick label and the axis. When set on a vertical axis, this applies in the horizontal (X)
	 * direction. When set on a horizontal axis, this applies in the vertical (Y) direction.
	 * 
	 * @return padding between the tick label and the axis. When set on a vertical axis, this applies in the horizontal (X)
	 *         direction. When set on a horizontal axis, this applies in the vertical (Y) direction. Default is 10.
	 */
	public int getPadding() {
		return getValue(Property.padding, DEFAULT_PADDING);
	}
	
	/**
	 * Sets the user defined minimum number for the scale, overrides minimum value from data.
	 * 
	 * @param min the user defined minimum number for the scale, overrides minimum value from data.
	 */
	public void setMin(double min) {
		setValue(Property.min, min);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the user defined minimum number for the scale, overrides minimum value from data.
	 * 
	 * @return the user defined minimum number for the scale, overrides minimum value from data. Default is Double.MIN_VALUE.
	 */
	public double getMin() {
		return getValue(Property.min, DEFAULT_MIN);
	}

	/**
	 * Sets the user defined maximum number for the scale, overrides maximum value from data.
	 * 
	 * @param max user defined maximum number for the scale, overrides maximum value from data.
	 */
	public void setMax(double max) {
		setValue(Property.max, max);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the user defined maximum number for the scale, overrides maximum value from data.
	 * 
	 * @return user defined maximum number for the scale, overrides maximum value from data. Default is Double.MAX_VALUE.
	 */
	public double getMax() {
		return getValue(Property.max, DEFAULT_MAX);
	}

	/**
	 * Sets the maximum number of ticks and gridlines to show.
	 * 
	 * @param maxTicksLimit maximum number of ticks and gridlines to show.
	 */
	public void setMaxTicksLimit(int maxTicksLimit) {
		setValue(Property.maxTicksLimit, maxTicksLimit);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the maximum number of ticks and gridlines to show.
	 * 
	 * @return maximum number of ticks and gridlines to show. Default is 11.
	 */
	public int getMaxTicksLimit() {
		return getValue(Property.maxTicksLimit, DEFAULT_MAX_TICKS_LIMIT);
	}

	/**
	 * Sets the user defined fixed step size for the scale.
	 * 
	 * @param stepSize user defined fixed step size for the scale.
	 */
	public void setStepSize(double stepSize) {
		setValue(Property.stepSize, stepSize);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the user defined fixed step size for the scale.
	 * 
	 * @return user defined fixed step size for the scale. Default is Double.MIN_VALUE.
	 */
	public double getStepSize() {
		return getValue(Property.stepSize, DEFAULT_STEP_SIZE);
	}

	/**
	 * Sets the adjustment used when calculating the maximum data value.
	 * 
	 * @param suggestedMax adjustment used when calculating the maximum data value.
	 */
	public void setSuggestedMax(double suggestedMax) {
		setValue(Property.suggestedMax, suggestedMax);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the adjustment used when calculating the maximum data value.
	 * 
	 * @return adjustment used when calculating the maximum data value.
	 */
	public double getSuggestedMax() {
		return getValue(Property.suggestedMax, DEFAULT_SUGGESTED_MAX);
	}

	/**
	 * Sets the adjustment used when calculating the minimum data value.
	 * 
	 * @param suggestedMin adjustment used when calculating the minimum data value.
	 */
	public void setSuggestedMin(double suggestedMin) {
		setValue(Property.suggestedMin, suggestedMin);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the adjustment used when calculating the minimum data value.
	 * 
	 * @return adjustment used when calculating the minimum data value.
	 */
	public double getSuggestedMin() {
		return getValue(Property.suggestedMin, DEFAULT_SUGGESTED_MIN);
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
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the color of label backdrops.
	 * 
	 * @return color of label backdrops. Default is 'rgba(255, 255, 255, 0.75)'
	 */
	public String getBackdropColorAsString() {
		return getValue(Property.backdropColor, DEFAULT_BACKDROP_COLOR);
	}

	/**
	 * Returns the color of label backdrops.
	 * 
	 * @return color of label backdrops. Default is 'rgba(255, 255, 255, 0.75)'
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
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the horizontal padding of label backdrop.
	 * 
	 * @return horizontal padding of label backdrop. Default is 2.
	 */
	public int getBackdropPaddingX() {
		return getValue(Property.backdropPaddingX, DEFAULT_BACKDROP_PADDING_X);
	}

	/**
	 * Sets the vertical padding of label backdrop.
	 * 
	 * @param backdropPaddingY vertical padding of label backdrop.
	 */
	public void setBackdropPaddingY(int backdropPaddingY) {
		setValue(Property.backdropPaddingY, backdropPaddingY);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the vertical padding of label backdrop.
	 * 
	 * @return vertical padding of label backdrop. Default is 2.
	 */
	public int getBackdropPaddingY() {
		return getValue(Property.backdropPaddingY, DEFAULT_BACKDROP_PADDING_Y);
	}

	/**
	 * If true, draw a background behind the tick labels.
	 * 
	 * @param showLabelBackdrop if true, draw a background behind the tick labels.
	 */
	public void setShowLabelBackdrop(boolean showLabelBackdrop) {
		setValue(Property.showLabelBackdrop, showLabelBackdrop);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If true, draw a background behind the tick labels.
	 * 
	 * @return if true, draw a background behind the tick labels. Default is true.
	 */
	public boolean isShowLabelBackdrop() {
		return getValue(Property.showLabelBackdrop, DEFAULT_SHOW_LABEL_BACKDROP);
	}

	/**
	 * Internal class to extend the configuration item enabling the protected methods.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class FontItemImpl extends FontItem{

		/**
		 * Builds the object with parent item and child.
		 * 
		 * @param parent parent item.
		 * @param childKey key of child.
		 */
		protected FontItemImpl(AbstractItem parent, Key childKey) {
			super(parent, childKey);
		}
		
	}

}