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
package org.pepstock.charba.client.defaults;

import org.pepstock.charba.client.enums.CrossAlign;
import org.pepstock.charba.client.enums.ElementAlign;
import org.pepstock.charba.client.enums.TickSource;

/**
 * Interface to define ticks object defaults.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsDefaultTicks extends IsDefaultFontContainer {

	/**
	 * Returns the major tick defaults.
	 * 
	 * @return the major tick defaults.
	 */
	IsDefaultMajor getMajor();

	/**
	 * Returns the number formatting options.
	 * 
	 * @return the number formatting options
	 */
	IsDefaultNumberFormatOptions getNumberFormat();

	/**
	 * If true, show tick marks
	 * 
	 * @return if true, show tick marks.
	 */
	boolean isDisplay();

	/**
	 * If true, automatically calculates how many labels that can be shown and hides labels accordingly. Turn it off to show all labels no matter what
	 * 
	 * @return If true, automatically calculates how many labels that can be shown and hides labels accordingly. Turn it off to show all labels no matter what.
	 */
	boolean isAutoSkip();

	/**
	 * Returns the padding between the ticks on the horizontal axis when autoSkip is enabled. Note: Only applicable to horizontal scales.
	 * 
	 * @return padding between the ticks on the horizontal axis when autoSkip is enabled. Note: Only applicable to horizontal scales.
	 */
	int getAutoSkipPadding();
	
	/**
	 * If <code>true</code>, the defined minimum and maximum values should be presented as ticks even if they are not "nice".
	 * 
	 * @return if <code>true</code>, the defined min and maximum values should be presented as ticks even if they are not "nice"
	 */
	boolean isIncludeBounds();

	/**
	 * Returns the distance in pixels to offset the label from the center point of the tick (in the y direction for the x axis, and the x direction for the y axis).<br>
	 * Note: this can cause labels at the edges to be cropped by the edge of the canvas.
	 * 
	 * @return the distance in pixels to offset the label from the center point of the tick (in the y direction for the x axis, and the x direction for the y axis).
	 */
	int getLabelOffset();

	/**
	 * Returns the number of ticks to generate.<br>
	 * If specified, this overrides the automatic generation.
	 * 
	 * @return the number of ticks to generate.<br>
	 *         If specified, this overrides the automatic generation
	 */
	int getCount();

	/**
	 * Returns the maximum rotation for tick labels when rotating to condense labels. Note: Rotation doesn't occur until necessary. Note: Only applicable to horizontal scales.
	 * 
	 * @return maximum rotation for tick labels when rotating to condense labels. Note: Rotation doesn't occur until necessary. Note: Only applicable to horizontal scales.
	 */
	int getMaxRotation();

	/**
	 * Returns the minimum rotation for tick labels. Note: Only applicable to horizontal scales.
	 * 
	 * @return minimum rotation for tick labels. Note: Only applicable to horizontal scales.
	 */
	int getMinRotation();

	/**
	 * Returns the flips tick labels around axis, displaying the labels inside the chart instead of outside. Note: Only applicable to vertical scales.
	 * 
	 * @return flips tick labels around axis, displaying the labels inside the chart instead of outside. Note: Only applicable to vertical scales.
	 */
	boolean isMirror();

	/**
	 * Returns the padding between the tick label and the axis. When set on a vertical axis, this applies in the horizontal (X) direction. When set on a horizontal axis, this
	 * applies in the vertical (Y) direction.
	 * 
	 * @return padding between the tick label and the axis. When set on a vertical axis, this applies in the horizontal (X) direction. When set on a horizontal axis, this applies
	 *         in the vertical (Y) direction.
	 */
	int getPadding();

	/**
	 * Returns the maximum number of ticks and grid to show.
	 * 
	 * @return maximum number of ticks and grid to show.
	 */
	int getMaxTicksLimit();

	/**
	 * Returns the user defined fixed step size for the scale.
	 * 
	 * @return user defined fixed step size for the scale.
	 */
	double getStepSize();

	/**
	 * Returns the color of label backdrops.
	 * 
	 * @return color of label backdrops.
	 */
	String getBackdropColorAsString();

	/**
	 * Returns the padding of label backdrop.
	 * 
	 * @return padding of label backdrop.
	 */
	IsDefaultPadding getBackdropPadding();

	/**
	 * If true, draw a background behind the tick labels.
	 * 
	 * @return if true, draw a background behind the tick labels.
	 */
	boolean isShowLabelBackdrop();

	/**
	 * Returns the property controls the ticks generation.
	 * 
	 * @return property controls the ticks generation.
	 */
	TickSource getSource();

	/**
	 * If defined and stepSize is not specified, the step size will be rounded to this many decimal places.
	 * 
	 * @return if defined and stepSize is not specified, the step size will be rounded to this many decimal places.
	 */
	int getPrecision();

	/**
	 * Returns z-index of tick layer. Useful when ticks are drawn on chart area.<br>
	 * Values less than or equals to 0 are drawn under datasets, greater than 0 on top.
	 * 
	 * @return z-index of tick layer. Useful when ticks are drawn on chart area.<br>
	 *         Values less than or equals to 0 are drawn under datasets, greater than 0 on top.
	 */
	int getZ();

	/**
	 * Returns the number of ticks to examine when deciding how many labels will fit.<br>
	 * Setting a smaller value will be faster, but may be less accurate when there is large variability in label length.
	 * 
	 * @return the number of ticks to examine when deciding how many labels will fit.
	 */
	int getSampleSize();

	/**
	 * Returns the tick alignment along the axis.
	 * 
	 * @return the tick alignment along the axis
	 */
	ElementAlign getAlign();

	/**
	 * Returns the tick alignment perpendicular to the axis.
	 * 
	 * @return the tick alignment perpendicular to the axis
	 */
	CrossAlign getCrossAlign();

	/**
	 * Returns the text stroke color as string.
	 * 
	 * @return the text stroke color as string.
	 */
	String getTextStrokeColorAsString();

	/**
	 * Returns the text stroke width.
	 * 
	 * @return the text stroke width.
	 */
	int getTextStrokeWidth();

}