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

import org.pepstock.charba.client.enums.Display;
import org.pepstock.charba.client.enums.AxisType;
import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.enums.ScaleBounds;
import org.pepstock.charba.client.enums.ScaleDistribution;

/**
 * Interface to define scale/axis object defaults.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsDefaultScale {

	/**
	 * Returns the scale label defaults
	 * 
	 * @return the scaleLabel defaults.
	 */
	IsDefaultScaleLabel getScaleLabel();

	/**
	 * Returns the ticks defaults.
	 * 
	 * @return the ticks defaults.
	 */
	IsDefaultTicks getTicks();

	/**
	 * Returns the grid lines defaults.
	 * 
	 * @return the grideLines defaults.
	 */
	IsDefaultGridLines getGrideLines();

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
	 * Returns if the axis are stacked or not.
	 * 
	 * @return if the axis are stacked or not.
	 */
	boolean isStacked();

	/**
	 * Returns the type of axis.
	 * 
	 * @return the type of axis.
	 */
	AxisType getType();

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
	 * If true, extra space is added to the both edges and the axis is scaled to fit into the chart area.
	 * 
	 * @return extra space of axis.
	 */
	boolean isOffset();

	/**
	 * Position of the axis in the chart. Possible values are: 'top', 'left', 'bottom', 'right'
	 * 
	 * @return position of axis.
	 */
	Position getPosition();

	/**
	 * Returns the percent (0-1) of the available width each bar should be within the category width. 1.0 will take the whole
	 * category width and put the bars right next to each other.
	 * 
	 * @return percent (0-1) of the available width each bar should be within the category width. 1.0 will take the whole
	 *         category width and put the bars right next to each other.
	 */
	double getBarPercentage();

	/**
	 * Returns the percent (0-1) of the available width each category should be within the sample width.
	 * 
	 * @return the percent (0-1) of the available width each category should be within the sample width.
	 */
	double getCategoryPercentage();

	/**
	 * Returns the width of each bar in pixels. If not set, the base sample widths are calculated automatically so that they
	 * take the full available widths without overlap. Then, the bars are sized using barPercentage and categoryPercentage.
	 * 
	 * @return width of each bar in pixels. If not set, the base sample widths are calculated automatically so that they take
	 *         the full available widths without overlap. Then, the bars are sized using barPercentage and categoryPercentage.
	 */
	int getBarThickness();

	/**
	 * Returns the maximum bar thickness.
	 * 
	 * @return the maximum bar thickness.
	 */
	int getMaxBarThickness();

	/**
	 * Returns a minimum length in pixels.
	 * 
	 * @return a minimum length in pixels.
	 */
	int getMinBarLength();

	/**
	 * Returns the property controls the data distribution along the scale.
	 * 
	 * @return property controls the data distribution along the scale.
	 */
	ScaleDistribution getDistribution();

	/**
	 * Returns the property controls the scale boundary strategy (bypassed by min/max time options).
	 * 
	 * @return property controls the scale boundary strategy (bypassed by min/max time options).
	 */
	ScaleBounds getBounds();

}