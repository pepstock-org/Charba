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

import org.pepstock.charba.client.jsinterop.commons.NativeObject;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Axes are an integral part of a chart. They are used to determine how data maps to a pixel value on the chart. <br>
 * In a cartesian chart, there is 1 or more X axis and 1 or more Y axis to map points onto the 2 dimensional canvas. These axes
 * are know as 'cartesian axes'.<br>
 * Axes that follow a cartesian grid are known as 'Cartesian Axes'. Cartesian axes are used for line, bar, and bubble charts.
 * Four cartesian axes are included by default.<br>
 * <ul>
 * <li>linear
 * <li>logarithmic
 * <li>category
 * <li>time (not implemented yet)
 * </ul>
 * <br>
 * It maps the CHART.JS object of default, <code>chart.defaults.scale</code>.<br>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
final class NativeScale extends NativeObject {

	@JsProperty
	native NativeTicks getTicks();

	@JsProperty
	native void setTicks(NativeTicks ticks);

	@JsProperty
	native NativeScaleLabel getScaleLabel();

	@JsProperty
	native void setScaleLabel(NativeScaleLabel scaleLabel);

	@JsProperty
	native NativePointLabels getPointLabels();

	@JsProperty
	native void setPointLabels(NativePointLabels pointLabels);

	@JsProperty
	native NativeGridLines getGridLines();

	@JsProperty
	native void setGridLines(NativeGridLines gridLines);

	/**
	 * @return the angleLines
	 * @see AngleLines
	 */
	@JsProperty
	native NativeAngleLines getAngleLines();

	@JsProperty
	native void setAngleLines(NativeAngleLines angleLines);

//	/**
//	 * @return the pointLabels
//	 * @see PointLabels
//	 */
//	@JsProperty
//	native PointLabels getPointLabels();

	/**
	 * The ID is used to link datasets and scale axes together.<br>
	 * This is especially needed if multi-axes charts are used.
	 * 
	 * @param id The ID is used to link datasets and scale axes together
	 */
	@JsProperty
	native void setId(String id);

	/**
	 * The ID is used to link datasets and scale axes together.<br>
	 * This is especially needed if multi-axes charts are used.
	 * 
	 * @return The ID is used to link datasets and scale axes together or <code>null</code> if not set
	 */
	@JsProperty
	native String getId();

	/**
	 * Sets if the axis are stacked or not.
	 * 
	 * @param stacked if the axis are stacked or not.
	 */
	@JsProperty
	native void setStacked(boolean stacked);

	/**
	 * Returns if the axis are stacked or not.
	 * 
	 * @return if the axis are stacked or not. Default is <code>false</code>.
	 */
	@JsProperty
	native boolean isStacked();

	/**
	 * Type of scale being employed.
	 * 
	 * @param type type of axis
	 * @see org.pepstock.charba.client.enums.AxisType
	 */
	@JsProperty
	native void setType(String type);

	/**
	 * Returns the type of axis.
	 * 
	 * @return the type of axis. If not set, the default is {@link org.pepstock.charba.client.enums.AxisType#linear}.
	 * @see org.pepstock.charba.client.enums.AxisType
	 */
	@JsProperty
	native String getType();

	/**
	 * The weight used to sort the axis. Higher weights are further away from the chart area.
	 * 
	 * @param weight weight of axis
	 */
	@JsProperty
	native void setWeight(int weight);

	/**
	 * The weight used to sort the axis. Higher weights are further away from the chart area.
	 * 
	 * @return weight of axis. Default is 0.
	 */
	@JsProperty
	native int getWeight();

	/**
	 * If true, shows the axis.
	 * 
	 * @param display if true, shows the axes.
	 */
	@JsProperty
	native void setDisplay(boolean display);

	/**
	 * If true, shows the axis.
	 * 
	 * @return if true, shows the axis. Default is true.
	 */
	@JsProperty
	native boolean isDisplay();

	/**
	 * If true, extra space is added to the both edges and the axis is scaled to fit into the chart area.
	 * 
	 * @param offset extra space of axis
	 */
	@JsProperty
	native void setOffset(boolean offset);

	/**
	 * If true, extra space is added to the both edges and the axis is scaled to fit into the chart area.
	 * 
	 * @return extra space of axis. Default is <code>false</code>.
	 */
	@JsProperty
	native boolean isOffset();

	/**
	 * Position of the axis in the chart. Possible values are: 'top', 'left', 'bottom', 'right'
	 * 
	 * @param position position of axis
	 * @see org.pepstock.charba.client.enums.Position
	 */
	@JsProperty
	native void setPosition(String position);

	/**
	 * Position of the axis in the chart. Possible values are: 'top', 'left', 'bottom', 'right'
	 * 
	 * @return position of axis. Default is {@link org.pepstock.charba.client.enums.Position#top}.
	 * @see org.pepstock.charba.client.enums.Position
	 */
	@JsProperty
	native String getPosition();

	/**
	 * Sets the percent (0-1) of the available width each bar should be within the category width. 1.0 will take the whole
	 * category width and put the bars right next to each other.
	 * 
	 * @param barPercentage percent (0-1) of the available width each bar should be within the category width. 1.0 will take the
	 *            whole category width and put the bars right next to each other.
	 */
	@JsProperty
	native void setBarPercentage(double barPercentage);

	/**
	 * Returns the percent (0-1) of the available width each bar should be within the category width. 1.0 will take the whole
	 * category width and put the bars right next to each other.
	 * 
	 * @return percent (0-1) of the available width each bar should be within the category width. 1.0 will take the whole
	 *         category width and put the bars right next to each other. Default is 0.9.
	 */
	@JsProperty
	native double getBarPercentage();

	/**
	 * Sets the percent (0-1) of the available width each category should be within the sample width.
	 * 
	 * @param categoryPercentage percent (0-1) of the available width each category should be within the sample width.
	 */
	@JsProperty
	native void setCategoryPercentage(double categoryPercentage);

	/**
	 * Returns the percent (0-1) of the available width each category should be within the sample width.
	 * 
	 * @return the percent (0-1) of the available width each category should be within the sample width. Default is 0.8.
	 */
	@JsProperty
	native double getCategoryPercentage();

	/**
	 * Sets the width of each bar in pixels. If not set, the base sample widths are calculated automatically so that they take
	 * the full available widths without overlap. Then, the bars are sized using barPercentage and categoryPercentage.
	 * 
	 * @param barThickness width of each bar in pixels. If not set, the base sample widths are calculated automatically so that
	 *            they take the full available widths without overlap. Then, the bars are sized using barPercentage and
	 *            categoryPercentage.
	 */
	@JsProperty
	native void setBarThickness(int barThickness);

	/**
	 * Returns the width of each bar in pixels. If not set, the base sample widths are calculated automatically so that they
	 * take the full available widths without overlap. Then, the bars are sized using barPercentage and categoryPercentage.
	 * 
	 * @return width of each bar in pixels. If not set, the base sample widths are calculated automatically so that they take
	 *         the full available widths without overlap. Then, the bars are sized using barPercentage and categoryPercentage.
	 *         Default is 0.
	 */
	@JsProperty
	native int getBarThickness();

	/**
	 * Sets the maximum bar thickness, to ensure that bars are not sized thicker than this
	 * 
	 * @param maxBarThickness the maximum bar thickness.
	 */
	@JsProperty
	native void setMaxBarThickness(int maxBarThickness);

	/**
	 * Returns the maximum bar thickness.
	 * 
	 * @return the maximum bar thickness. Default is 0.
	 */
	@JsProperty
	native int getMaxBarThickness();

}