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

import java.util.List;

/**
 * Interface to define options for the border that run perpendicular to the axis.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsDefaultScaleBorder {

	/**
	 * If <code>true</code>, draw border at the edge between the axis and the chart area.
	 * 
	 * @return if <code>true</code>, draw border at the edge between the axis and the chart area.
	 */
	boolean isDisplay();

	/**
	 * If set, used as the width of the border line.<br>
	 * If unset, the first lineWidth option is resolved and used.
	 * 
	 * @return if set, used as the width of the border line.<br>
	 *         If unset, the first lineWidth option is resolved and used.
	 */
	int getWidth();

	/**
	 * If set, used as the color of the border line.<br>
	 * If unset, the first color option is resolved and used.
	 * 
	 * @return if set, used as the color of the border line.<br>
	 *         If unset, the first color option is resolved and used.
	 */
	String getColorAsString();

	/**
	 * Returns the line dash pattern offset.
	 * 
	 * @return offset for line dashes.
	 */
	double getDashOffset();

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @return the line dash pattern used when stroking lines
	 */
	List<Integer> getDash();

	/**
	 * Returns z-index of border layer.<br>
	 * Values less than or equals to 0 are drawn under data sets, greater than 0 on top.
	 * 
	 * @return z-index of border layer.<br>
	 *         Values less than or equals to 0 are drawn under data sets, greater than 0 on top.
	 */
	int getZ();
}