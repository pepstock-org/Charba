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

import org.pepstock.charba.client.jsinterop.commons.ArrayString;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * All configuration for ticks of a chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
final class NativeTicks extends NativeFontItem {

	@JsProperty 
	native void setMinor(NativeTickItem minor);

	@JsProperty 
	native NativeTickItem getMinor();

	@JsProperty 
	native void setMajor(NativeTickItem major);

	@JsProperty 
	native NativeTickItem getMajor();
	
	@JsProperty
	native ArrayString getLabels();

	@JsProperty
	native void setLabels(ArrayString labels);

	/**
	 * If true, scale will include 0 if it is not already included.
	 * 
	 * @param beginAtZero if true, scale will include 0 if it is not already included.
	 */
	@JsProperty
	native void setBeginAtZero(boolean beginAtZero);

	/**
	 * If true, scale will include 0 if it is not already included.
	 * 
	 * @return if true, scale will include 0 if it is not already included.. Default is false
	 */
	@JsProperty
	native boolean isBeginAtZero();

	/**
	 * If true, show tick marks.
	 * 
	 * @param display if true, show tick marks
	 */
	@JsProperty
	native void setDisplay(boolean display);

	/**
	 * If true, show tick marks
	 * 
	 * @return if true, show tick marks. Default is true.
	 */
	@JsProperty
	native boolean isDisplay();

	/**
	 * Sets the reverses order of tick labels.
	 * 
	 * @param reverse reverses order of tick labels.
	 */
	@JsProperty
	native void setReverse(boolean reverse);

	/**
	 * Returns the reverses order of tick labels.
	 * 
	 * @return reverses order of tick labels. Default is false.
	 */
	@JsProperty
	native boolean isReverse();

	/**
	 * If true, automatically calculates how many labels that can be shown and hides labels accordingly. Turn it off to show all
	 * labels no matter what
	 * 
	 * @param autoSkip If true, automatically calculates how many labels that can be shown and hides labels accordingly. Turn it
	 *            off to show all labels no matter what
	 */
	@JsProperty
	native void setAutoSkip(boolean autoSkip);

	/**
	 * If true, automatically calculates how many labels that can be shown and hides labels accordingly. Turn it off to show all
	 * labels no matter what
	 * 
	 * @return If true, automatically calculates how many labels that can be shown and hides labels accordingly. Turn it off to
	 *         show all labels no matter what. Default is true
	 */
	@JsProperty
	native boolean isAutoSkip();

	/**
	 * Sets the padding between the ticks on the horizontal axis when autoSkip is enabled. Note: Only applicable to horizontal
	 * scales.
	 * 
	 * @param autoSkipPadding padding between the ticks on the horizontal axis when autoSkip is enabled. Note: Only applicable
	 *            to horizontal scales.
	 */
	@JsProperty
	native void setAutoSkipPadding(int autoSkipPadding);

	/**
	 * Returns the padding between the ticks on the horizontal axis when autoSkip is enabled. Note: Only applicable to
	 * horizontal scales.
	 * 
	 * @return padding between the ticks on the horizontal axis when autoSkip is enabled. Note: Only applicable to horizontal
	 *         scales. Defualt is 0.
	 */
	@JsProperty
	native int getAutoSkipPadding();

	/**
	 * Sets the distance in pixels to offset the label from the centre point of the tick (in the y direction for the x axis, and
	 * the x direction for the y axis).<br>
	 * Note: this can cause labels at the edges to be cropped by the edge of the canvas.
	 * 
	 * @param labelOffset the distance in pixels to offset the label from the centre point of the tick (in the y direction for
	 *            the x axis, and the x direction for the y axis)
	 */
	@JsProperty
	native void setLabelOffset(int labelOffset);

	/**
	 * Returns the distance in pixels to offset the label from the centre point of the tick (in the y direction for the x axis,
	 * and the x direction for the y axis).<br>
	 * Note: this can cause labels at the edges to be cropped by the edge of the canvas.
	 * 
	 * @return the distance in pixels to offset the label from the centre point of the tick (in the y direction for the x axis,
	 *         and the x direction for the y axis). Default is 0.
	 */
	@JsProperty
	native int getLabelOffset();

	/**
	 * Sets the maximum rotation for tick labels when rotating to condense labels. Note: Rotation doesn't occur until necessary.
	 * Note: Only applicable to horizontal scales.
	 * 
	 * @param maxRotation maximum rotation for tick labels when rotating to condense labels. Note: Rotation doesn't occur until
	 *            necessary. Note: Only applicable to horizontal scales.
	 */
	@JsProperty
	native void setMaxRotation(int maxRotation);

	/**
	 * Returns the maximum rotation for tick labels when rotating to condense labels. Note: Rotation doesn't occur until
	 * necessary. Note: Only applicable to horizontal scales.
	 * 
	 * @return maximum rotation for tick labels when rotating to condense labels. Note: Rotation doesn't occur until necessary.
	 *         Note: Only applicable to horizontal scales. Default is 90
	 */
	@JsProperty
	native int getMaxRotation();

	/**
	 * Sets the minimum rotation for tick labels. Note: Only applicable to horizontal scales.
	 * 
	 * @param minRotation minimum rotation for tick labels. Note: Only applicable to horizontal scales.
	 */
	@JsProperty
	native void setMinRotation(int minRotation);

	/**
	 * Returns the minimum rotation for tick labels. Note: Only applicable to horizontal scales.
	 * 
	 * @return minimum rotation for tick labels. Note: Only applicable to horizontal scales.. Default is 0.
	 */
	@JsProperty
	native int getMinRotation();

	/**
	 * Sets the flips tick labels around axis, displaying the labels inside the chart instead of outside. Note: Only applicable
	 * to vertical scales.
	 * 
	 * @param mirror flips tick labels around axis, displaying the labels inside the chart instead of outside. Note: Only
	 *            applicable to vertical scales.
	 */
	@JsProperty
	native void setMirror(boolean mirror);

	/**
	 * Returns the flips tick labels around axis, displaying the labels inside the chart instead of outside. Note: Only
	 * applicable to vertical scales.
	 * 
	 * @return flips tick labels around axis, displaying the labels inside the chart instead of outside. Note: Only applicable
	 *         to vertical scales. Default is false.
	 */
	@JsProperty
	native boolean isMirror();

	/**
	 * Sets the padding between the tick label and the axis. When set on a vertical axis, this applies in the horizontal (X)
	 * direction. When set on a horizontal axis, this applies in the vertical (Y) direction.
	 * 
	 * @param padding padding between the tick label and the axis. When set on a vertical axis, this applies in the horizontal
	 *            (X) direction. When set on a horizontal axis, this applies in the vertical (Y) direction.
	 */
	@JsProperty
	native void setPadding(int padding);

	/**
	 * Returns the padding between the tick label and the axis. When set on a vertical axis, this applies in the horizontal (X)
	 * direction. When set on a horizontal axis, this applies in the vertical (Y) direction.
	 * 
	 * @return padding between the tick label and the axis. When set on a vertical axis, this applies in the horizontal (X)
	 *         direction. When set on a horizontal axis, this applies in the vertical (Y) direction. Default is 10.
	 */
	@JsProperty
	native int getPadding();

	/**
	 * Sets the user defined minimum number for the scale, overrides minimum value from data.
	 * 
	 * @param min the user defined minimum number for the scale, overrides minimum value from data.
	 */
	@JsProperty
	native void setMin(Object min);

	/**
	 * Returns the user defined minimum number for the scale, overrides minimum value from data.
	 * 
	 * @return the user defined minimum number for the scale, overrides minimum value from data. Default is Double.MIN_VALUE.
	 */
	@JsProperty
	native Object getMin();

	/**
	 * Sets the user defined maximum number for the scale, overrides maximum value from data.
	 * 
	 * @param max user defined maximum number for the scale, overrides maximum value from data.
	 */
	@JsProperty
	native void setMax(Object max);

	/**
	 * Returns the user defined maximum number for the scale, overrides maximum value from data.
	 * 
	 * @return user defined maximum number for the scale, overrides maximum value from data. Default is Double.MAX_VALUE.
	 */
	@JsProperty
	native Object getMax();

	/**
	 * Sets the maximum number of ticks and gridlines to show.
	 * 
	 * @param maxTicksLimit maximum number of ticks and gridlines to show.
	 */
	@JsProperty
	native void setMaxTicksLimit(int maxTicksLimit);

	/**
	 * Returns the maximum number of ticks and gridlines to show.
	 * 
	 * @return maximum number of ticks and gridlines to show. Default is 11.
	 */
	@JsProperty
	native int getMaxTicksLimit();

	/**
	 * Sets the user defined fixed step size for the scale.
	 * 
	 * @param stepSize user defined fixed step size for the scale.
	 */
	@JsProperty
	native void setStepSize(double stepSize);

	/**
	 * Returns the user defined fixed step size for the scale.
	 * 
	 * @return user defined fixed step size for the scale. Default is Double.MIN_VALUE.
	 */
	@JsProperty
	native double getStepSize();

	/**
	 * Sets the adjustment used when calculating the maximum data value.
	 * 
	 * @param suggestedMax adjustment used when calculating the maximum data value.
	 */
	@JsProperty
	native void setSuggestedMax(double suggestedMax);

	/**
	 * Returns the adjustment used when calculating the maximum data value.
	 * 
	 * @return adjustment used when calculating the maximum data value.
	 */
	@JsProperty
	native double getSuggestedMax();

	/**
	 * Sets the adjustment used when calculating the minimum data value.
	 * 
	 * @param suggestedMin adjustment used when calculating the minimum data value.
	 */
	@JsProperty
	native void setSuggestedMin(double suggestedMin);

	/**
	 * Returns the adjustment used when calculating the minimum data value.
	 * 
	 * @return adjustment used when calculating the minimum data value.
	 */
	@JsProperty
	native double getSuggestedMin();

	/**
	 * Sets the color of label backdrops.
	 * 
	 * @param backdropColor color of label backdrops.
	 */
	@JsProperty
	native void setBackdropColor(String backdropColor);

	/**
	 * Returns the color of label backdrops.
	 * 
	 * @return color of label backdrops. Default is 'rgba(255, 255, 255, 0.75)'
	 */
	@JsProperty
	native String getBackdropColor();

	/**
	 * Sets the horizontal padding of label backdrop.
	 * 
	 * @param backdropPaddingX horizontal padding of label backdrop.
	 */
	@JsProperty
	native void setBackdropPaddingX(int backdropPaddingX);

	/**
	 * Returns the horizontal padding of label backdrop.
	 * 
	 * @return horizontal padding of label backdrop. Default is 2.
	 */
	@JsProperty
	native int getBackdropPaddingX();

	/**
	 * Sets the vertical padding of label backdrop.
	 * 
	 * @param backdropPaddingY vertical padding of label backdrop.
	 */
	@JsProperty
	native void setBackdropPaddingY(int backdropPaddingY);

	/**
	 * Returns the vertical padding of label backdrop.
	 * 
	 * @return vertical padding of label backdrop. Default is 2.
	 */
	@JsProperty
	native int getBackdropPaddingY();

	/**
	 * If true, draw a background behind the tick labels.
	 * 
	 * @param showLabelBackdrop if true, draw a background behind the tick labels.
	 */
	@JsProperty
	native void setShowLabelBackdrop(boolean showLabelBackdrop);

	/**
	 * If true, draw a background behind the tick labels.
	 * 
	 * @return if true, draw a background behind the tick labels. Default is true.
	 */
	@JsProperty
	native boolean isShowLabelBackdrop();
	
	/**
	 * Sets the property controls the ticks generation.
	 * 
	 * @param source property controls the ticks generation.
	 */
	@JsProperty
	native void setSource(String source);

	/**
	 * Returns the property controls the ticks generation.
	 * 
	 * @return property controls the ticks generation.
	 */
	@JsProperty
	native String getSource();
}