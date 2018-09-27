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

import java.util.List;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.enums.TickSource;
import org.pepstock.charba.client.jsinterop.commons.ArrayListHelper;
import org.pepstock.charba.client.jsinterop.commons.ArrayString;
import org.pepstock.charba.client.jsinterop.commons.Checker;
import org.pepstock.charba.client.jsinterop.commons.Enumer;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultTicks;

/**
 * All configuration for ticks of a chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Ticks extends FontItem<Scale, IsDefaultTicks, NativeTicks> {

	private final TickMinor minor;

	private final TickMajor major;

	Ticks(Scale scale, IsDefaultTicks defaultValues, NativeTicks delegated) {
		super(scale, defaultValues, delegated == null ? new NativeTicks() : delegated);
		minor = new TickMinor(this, getDefaultValues().getMinor(), getDelegated().getMinor());
		major = new TickMajor(this, getDefaultValues().getMajor(), getDelegated().getMajor());
	}
	
	/**
	 * @return the minor
	 */
	public final TickMinor getMinor() {
		return minor;
	}

	/**
	 * @return the major
	 */
	public final TickMajor getMajor() {
		return major;
	}

	/**
	 * If true, scale will include 0 if it is not already included.
	 * 
	 * @param beginAtZero if true, scale will include 0 if it is not already included.
	 */
	public void setBeginAtZero(boolean beginAtZero) {
		getDelegated().setBeginAtZero(beginAtZero);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If true, scale will include 0 if it is not already included.
	 * 
	 * @return if true, scale will include 0 if it is not already included.. Default is false
	 */
	public boolean isBeginAtZero() {
		return Checker.check(getDelegated().isBeginAtZero(), getDefaultValues().isBeginAtZero());
	}

	/**
	 * If true, show tick marks.
	 * 
	 * @param display if true, show tick marks
	 */
	public void setDisplay(boolean display) {
		getDelegated().setDisplay(display);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If true, show tick marks
	 * 
	 * @return if true, show tick marks. Default is true.
	 */
	public boolean isDisplay() {
		return Checker.check(getDelegated().isDisplay(), getDefaultValues().isDisplay());
	}

	/**
	 * Sets the reverses order of tick labels.
	 * 
	 * @param reverse reverses order of tick labels.
	 */
	public void setReverse(boolean reverse) {
		getDelegated().setReverse(reverse);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the reverses order of tick labels.
	 * 
	 * @return reverses order of tick labels. Default is false.
	 */
	public boolean isReverse() {
		return Checker.check(getDelegated().isReverse(), getDefaultValues().isReverse());
	}
	
	/**
	 * If true, automatically calculates how many labels that can be shown and hides labels accordingly. Turn it off to show all
	 * labels no matter what
	 * 
	 * @param autoSkip If true, automatically calculates how many labels that can be shown and hides labels accordingly. Turn it
	 *            off to show all labels no matter what
	 */
	public void setAutoSkip(boolean autoSkip) {
		getDelegated().setAutoSkip(autoSkip);
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
		return Checker.check(getDelegated().isAutoSkip(), getDefaultValues().isAutoSkip());
	}

	/**
	 * Sets the padding between the ticks on the horizontal axis when autoSkip is enabled. Note: Only applicable to horizontal
	 * scales.
	 * 
	 * @param autoSkipPadding padding between the ticks on the horizontal axis when autoSkip is enabled. Note: Only applicable
	 *            to horizontal scales.
	 */
	public void setAutoSkipPadding(int autoSkipPadding) {
		getDelegated().setAutoSkipPadding(autoSkipPadding);
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
		return Checker.check(getDelegated().getAutoSkipPadding(), getDefaultValues().getAutoSkipPadding());
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
		getDelegated().setLabelOffset(labelOffset);
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
		return Checker.check(getDelegated().getLabelOffset(), getDefaultValues().getLabelOffset());
	}

	/**
	 * Sets the maximum rotation for tick labels when rotating to condense labels. Note: Rotation doesn't occur until necessary.
	 * Note: Only applicable to horizontal scales.
	 * 
	 * @param maxRotation maximum rotation for tick labels when rotating to condense labels. Note: Rotation doesn't occur until
	 *            necessary. Note: Only applicable to horizontal scales.
	 */
	public void setMaxRotation(int maxRotation) {
		getDelegated().setMaxRotation(maxRotation);
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
		return Checker.check(getDelegated().getMaxRotation(), getDefaultValues().getMaxRotation());
	}

	/**
	 * Sets the minimum rotation for tick labels. Note: Only applicable to horizontal scales.
	 * 
	 * @param minRotation minimum rotation for tick labels. Note: Only applicable to horizontal scales.
	 */
	public void setMinRotation(int minRotation) {
		getDelegated().setMinRotation(minRotation);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the minimum rotation for tick labels. Note: Only applicable to horizontal scales.
	 * 
	 * @return minimum rotation for tick labels. Note: Only applicable to horizontal scales.. Default is 0.
	 */
	public int getMinRotation() {
		return Checker.check(getDelegated().getMinRotation(), getDefaultValues().getMinRotation());
	}

	/**
	 * Sets the flips tick labels around axis, displaying the labels inside the chart instead of outside. Note: Only applicable
	 * to vertical scales.
	 * 
	 * @param mirror flips tick labels around axis, displaying the labels inside the chart instead of outside. Note: Only
	 *            applicable to vertical scales.
	 */
	public void setMirror(boolean mirror) {
		getDelegated().setMirror(mirror);
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
		return Checker.check(getDelegated().isMirror(), getDefaultValues().isMirror());
	}

	/**
	 * Sets the padding between the tick label and the axis. When set on a vertical axis, this applies in the horizontal (X)
	 * direction. When set on a horizontal axis, this applies in the vertical (Y) direction.
	 * 
	 * @param padding padding between the tick label and the axis. When set on a vertical axis, this applies in the horizontal
	 *            (X) direction. When set on a horizontal axis, this applies in the vertical (Y) direction.
	 */
	public void setPadding(int padding) {
		getDelegated().setPadding(padding);
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
		return Checker.check(getDelegated().getPadding(), getDefaultValues().getPadding());
	}
	
	/**
	 * Sets the user defined minimum number for the scale, overrides minimum value from data.
	 * 
	 * @param min the user defined minimum number for the scale, overrides minimum value from data.
	 */
	public void setMin(double min) {
		getDelegated().setMin(min);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the user defined minimum number for the scale, overrides minimum value from data.
	 * 
	 * @return the user defined minimum number for the scale, overrides minimum value from data. Default is Double.MIN_VALUE.
	 */
	public double getMin() {
		return Checker.check(getDelegated().getMin(), getDefaultValues().getMin());
	}

	/**
	 * Sets the user defined maximum number for the scale, overrides maximum value from data.
	 * 
	 * @param max user defined maximum number for the scale, overrides maximum value from data.
	 */
	public void setMax(double max) {
		getDelegated().setMax(max);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the user defined maximum number for the scale, overrides maximum value from data.
	 * 
	 * @return user defined maximum number for the scale, overrides maximum value from data. Default is Double.MAX_VALUE.
	 */
	public double getMax() {
		return Checker.check(getDelegated().getMax(), getDefaultValues().getMax());
	}

	/**
	 * Sets the user defined minimum number for the scale, overrides minimum value from data.
	 * 
	 * @param min the user defined minimum number for the scale, overrides minimum value from data.
	 */
	public void setMin(String min) {
		getDelegated().setMin(min);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the user defined minimum number for the scale, overrides minimum value from data.
	 * 
	 * @return the user defined minimum number for the scale, overrides minimum value from data. Default is Double.MIN_VALUE.
	 */
	public String getMinAsString() {
		return Checker.check(getDelegated().getMin(), String.valueOf(getDefaultValues().getMin()));
	}

	/**
	 * Sets the user defined maximum number for the scale, overrides maximum value from data.
	 * 
	 * @param max user defined maximum number for the scale, overrides maximum value from data.
	 */
	public void setMax(String max) {
		getDelegated().setMax(max);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the user defined maximum number for the scale, overrides maximum value from data.
	 * 
	 * @return user defined maximum number for the scale, overrides maximum value from data. Default is Double.MAX_VALUE.
	 */
	public String getMaxAsString() {
		return Checker.check(getDelegated().getMax(), String.valueOf(getDefaultValues().getMax()));
	}
	
	/**
	 * Sets the maximum number of ticks and gridlines to show.
	 * 
	 * @param maxTicksLimit maximum number of ticks and gridlines to show.
	 */
	public void setMaxTicksLimit(int maxTicksLimit) {
		getDelegated().setMaxTicksLimit(maxTicksLimit);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the maximum number of ticks and gridlines to show.
	 * 
	 * @return maximum number of ticks and gridlines to show. Default is 11.
	 */
	public int getMaxTicksLimit() {
		return Checker.check(getDelegated().getMaxTicksLimit(), getDefaultValues().getMaxTicksLimit());
	}

	/**
	 * Sets the user defined fixed step size for the scale.
	 * 
	 * @param stepSize user defined fixed step size for the scale.
	 */
	public void setStepSize(double stepSize) {
		getDelegated().setStepSize(stepSize);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the user defined fixed step size for the scale.
	 * 
	 * @return user defined fixed step size for the scale. Default is Double.MIN_VALUE.
	 */
	public double getStepSize() {
		return Checker.check(getDelegated().getStepSize(), getDefaultValues().getStepSize());
	}

	/**
	 * Sets the adjustment used when calculating the maximum data value.
	 * 
	 * @param suggestedMax adjustment used when calculating the maximum data value.
	 */
	public void setSuggestedMax(double suggestedMax) {
		getDelegated().setSuggestedMax(suggestedMax);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the adjustment used when calculating the maximum data value.
	 * 
	 * @return adjustment used when calculating the maximum data value.
	 */
	public double getSuggestedMax() {
		return Checker.check(getDelegated().getSuggestedMax(), getDefaultValues().getSuggestedMax());
	}

	/**
	 * Sets the adjustment used when calculating the minimum data value.
	 * 
	 * @param suggestedMin adjustment used when calculating the minimum data value.
	 */
	public void setSuggestedMin(double suggestedMin) {
		getDelegated().setSuggestedMin(suggestedMin);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the adjustment used when calculating the minimum data value.
	 * 
	 * @return adjustment used when calculating the minimum data value.
	 */
	public double getSuggestedMin() {
		return Checker.check(getDelegated().getSuggestedMin(), getDefaultValues().getSuggestedMin());
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
		getDelegated().setBackdropColor(backdropColor);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the color of label backdrops.
	 * 
	 * @return color of label backdrops. Default is 'rgba(255, 255, 255, 0.75)'
	 */
	public String getBackdropColorAsString() {
		return Checker.check(getDelegated().getBackdropColor(), getDefaultValues().getBackdropColor());
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
		getDelegated().setBackdropPaddingX(backdropPaddingX);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the horizontal padding of label backdrop.
	 * 
	 * @return horizontal padding of label backdrop. Default is 2.
	 */
	public int getBackdropPaddingX() {
		return Checker.check(getDelegated().getBackdropPaddingX(), getDefaultValues().getBackdropPaddingX());
	}

	/**
	 * Sets the vertical padding of label backdrop.
	 * 
	 * @param backdropPaddingY vertical padding of label backdrop.
	 */
	public void setBackdropPaddingY(int backdropPaddingY) {
		getDelegated().setBackdropPaddingY(backdropPaddingY);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the vertical padding of label backdrop.
	 * 
	 * @return vertical padding of label backdrop. Default is 2.
	 */
	public int getBackdropPaddingY() {
		return Checker.check(getDelegated().getBackdropPaddingY(), getDefaultValues().getBackdropPaddingY());
	}

	/**
	 * If true, draw a background behind the tick labels.
	 * 
	 * @param showLabelBackdrop if true, draw a background behind the tick labels.
	 */
	public void setShowLabelBackdrop(boolean showLabelBackdrop) {
		getDelegated().setShowLabelBackdrop(showLabelBackdrop);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If true, draw a background behind the tick labels.
	 * 
	 * @return if true, draw a background behind the tick labels. Default is true.
	 */
	public boolean isShowLabelBackdrop() {
		return Checker.check(getDelegated().isShowLabelBackdrop(), getDefaultValues().isShowLabelBackdrop());
	}
	
	/**
	 * Sets an array of labels to display.
	 * 
	 * @param labels An array of labels to display.
	 */
	public void setLabels(String... labels) {
		getDelegated().setLabels(ArrayString.of(labels));
		// checks if all parents are attached
		checkAndAddToParent();
	}
	
	/**
	 * Sets an array of labels to display.
	 * 
	 * @param labels A list of labels to display.
	 */
	public void setLabels(List<String> labels) {
		setLabels(labels.toArray(new String[0]));
	}

	/**
	 * Returns the array of labels to display.
	 * 
	 * @return the array of labels to display.
	 */
	public List<String> getLabels() {
		return ArrayListHelper.list(getDelegated().getLabels());
	}
	
	/**
	 * Sets the property controls the ticks generation.
	 * 
	 * @param source property controls the ticks generation.
	 * @see org.pepstock.charba.client.enums.TickSource
	 */
	public void setSource(TickSource source) {
		getDelegated().setSource(source.name());
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the property controls the ticks generation.
	 * 
	 * @return property controls the ticks generation.
	 * @see org.pepstock.charba.client.enums.TickSource
	 */
	public TickSource getSource() {
		return Enumer.deserialize(getDelegated().getSource(), getDefaultValues().getSource(), TickSource.class, TickSource.auto);
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.BaseModel#addToParent()
	 */
	@Override
	protected void addToParent() {
		if (getParent().getDelegated().getTicks() == null) {
			getParent().getDelegated().setTicks(getDelegated());
		}
	}
}