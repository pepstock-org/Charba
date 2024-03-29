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
package org.pepstock.charba.client.defaults;

import org.pepstock.charba.client.enums.AxisPosition;
import org.pepstock.charba.client.enums.Bounds;
import org.pepstock.charba.client.enums.Display;
import org.pepstock.charba.client.items.AxisPositionItem;

/**
 * Interface to define scale/axis object defaults.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsDefaultScale {

	/**
	 * Returns the scale title defaults
	 * 
	 * @return the scale title defaults.
	 */
	IsDefaultScaleTitle getTitle();

	/**
	 * Returns the border defaults.
	 * 
	 * @return the border defaults.
	 */
	IsDefaultScaleBorder getBorder();

	/**
	 * Returns the ticks defaults.
	 * 
	 * @return the ticks defaults.
	 */
	IsDefaultTicks getTicks();

	/**
	 * Returns the grid defaults.
	 * 
	 * @return the grid defaults.
	 */
	IsDefaultGrid getGrid();

	/**
	 * Returns the angle lines default.
	 * 
	 * @return the angleLines defaults.
	 */
	IsDefaultAngleLines getAngleLines();

	/**
	 * Returns the point labels defaults.
	 * 
	 * @return the pointLabels defaults.
	 */
	IsDefaultPointLabels getPointLabels();

	/**
	 * Returns the time defaults.
	 * 
	 * @return the time defaults.
	 */
	IsDefaultTime getTime();

	/**
	 * Returns the adapter defaults.
	 * 
	 * @return the adapter defaults.
	 */
	IsDefaultAdapters getAdapters();

	/**
	 * If true, scale will include 0 if it is not already included.
	 * 
	 * @return if true, scale will include 0 if it is not already included.
	 */
	boolean isBeginAtZero();

	/**
	 * Returns the value in pixels is added to the maximum data value and subtracted from the minimum data.<br>
	 * This extends the scale range as if the data values were that much greater.
	 * 
	 * @return the value in pixels is added to the maximum data value and subtracted from the minimum data
	 */
	int getGrace();

	/**
	 * Returns the value in percentage, value between 0 and 1, is added to the maximum data value and subtracted from the minimum data.<br>
	 * This extends the scale range as if the data values were that much greater.
	 * 
	 * @return the value in percentage is added to the maximum data value and subtracted from the minimum data
	 */
	double getGraceAsPercentage();

	/**
	 * Returns the user defined minimum number for the scale, overrides minimum value from data.
	 * 
	 * @return the user defined minimum number for the scale, overrides minimum value from data.
	 */
	double getMin();

	/**
	 * Returns the user defined maximum number for the scale, overrides maximum value from data.
	 * 
	 * @return user defined maximum number for the scale, overrides maximum value from data.
	 */
	double getMax();

	/**
	 * Returns the adjustment used when calculating the maximum data value.
	 * 
	 * @return adjustment used when calculating the maximum data value.
	 */
	double getSuggestedMax();

	/**
	 * Returns the adjustment used when calculating the minimum data value.
	 * 
	 * @return adjustment used when calculating the minimum data value.
	 */
	double getSuggestedMin();

	/**
	 * Returns <code>true</code> to align pixel values to device pixels.
	 * 
	 * @return <code>true</code> to align pixel values to device pixels
	 */
	boolean isAlignToPixels();

	/**
	 * Returns the reversed order of tick labels.
	 * 
	 * @return reversed order of tick labels.
	 */
	boolean isReverse();

	/**
	 * Returns if the axis are stacked or not.
	 * 
	 * @return if the axis are stacked or not.
	 */
	boolean isStacked();

	/**
	 * Returns if you might want to stack positive and negative values together.
	 * 
	 * @return if you might want to stack positive and negative values together
	 */
	boolean isSingleStacked();

	/**
	 * The weight used to sort the axis. Higher weights are further away from the chart area.
	 * 
	 * @return weight of axis.
	 */
	double getWeight();

	/**
	 * Returns how showing the axis.
	 * 
	 * @return how showing the axis
	 */
	Display getDisplay();

	/**
	 * If true, extra space is added to the both edges and the axis is scaled to fit in the chart area.
	 * 
	 * @return extra space of axis.
	 */
	boolean isOffset();

	/**
	 * An axis can either be positioned at the edge of the chart, at the center of the chart area, or dynamically with respect to a data value.<br>
	 * To position the axis at the edge of the chart, set the position option to one of: 'top', 'left', 'bottom', 'right'.<br>
	 * To position the axis at the center of the chart area, set the position option to 'center'.
	 * 
	 * @return position of axis.
	 */
	AxisPosition getPosition();

	/**
	 * Returns the position of the axis at a specific value of another axis.
	 * 
	 * @return position of axis.
	 */
	AxisPositionItem getPositionAsItem();

	/**
	 * Returns the property controls the scale boundary strategy (bypassed by min/max time options).
	 * 
	 * @return property controls the scale boundary strategy (bypassed by min/max time options).
	 */
	Bounds getBounds();

	/**
	 * Returns the stack group.<br>
	 * Axes at the same position with same stack are stacked.
	 * 
	 * @return the stack group.<br>
	 *         Axes at the same position with same stack are stacked
	 */
	String getStack();

	/**
	 * Returns the weight of the scale in stack group.<br>
	 * Used to determine the amount of allocated space for the scale within the group.
	 * 
	 * @return the weight of the scale in stack group.<br>
	 *         Used to determine the amount of allocated space for the scale within the group
	 */
	double getStackWeight();

	/**
	 * Returns whether to animate scaling the chart from the center.
	 * 
	 * @return whether to animate scaling the chart from the center.
	 */
	boolean isAnimate();

	/**
	 * Returns the starting angle to draw arcs for the first item in a dataset.
	 * 
	 * @return starting angle to draw arcs for the first item in a dataset.
	 */
	double getStartAngle();

	/**
	 * Returns the background color of the scale area.
	 * 
	 * @return the background color of the scale area.
	 */
	String getBackgroundColorAsString();

	/**
	 * If <code>true</code>, bar chart offsets are computed with auto skipped ticks..
	 * 
	 * @return if <code>true</code>, bar chart offsets are computed with auto skipped ticks..
	 */
	boolean isOffsetAfterAutoskip();

}