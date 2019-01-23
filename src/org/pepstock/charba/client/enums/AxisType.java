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
package org.pepstock.charba.client.enums;

import org.pepstock.charba.client.commons.Key;

/**
 * Axes are an integral part of a chart. They are used to determine how data maps to a pixel value on the chart.<br>
 * These are the possible types of an axis.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum AxisType implements Key
{

	/**
	 * The linear scale is use to chart numerical data. It can be placed on either the x or y axis.<br>
	 * The linear interpolation is used to determine where a value lies on the axis.
	 */
	linear,
	/**
	 * The logarithmic scale is use to chart numerical data. It can be placed on either the x or y axis. <br>
	 * The logarithmic interpolation is used to determine where a value lies on the axis.
	 */
	logarithmic,
	/**
	 * The labels are drawn from one of the label arrays included in the chart data.<br>
	 * Where not specified, this is the default.
	 */
	category,
	/**
	 * The time scale is used to display times and dates. When building its ticks, it will automatically calculate the most
	 * comfortable unit base on the size of the scale.<br>
	 * Not implemented
	 */
	time,
	/**
	 * Radial axes are used specifically for the radar and polar area chart types.<br>
	 * These axes overlay the chart area, rather than being positioned on one of the edges.<br>
	 * The linear scale is use to chart numerical data.<br>
	 * The linear interpolation is used to determine where a value lies in relation the center of the axis.
	 */
	radialLinear

}