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

/**
 * Interface to define BAR datasets object defaults.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsDefaultBarDatasets {

	/**
	 * Returns the percent (0-1) of the available width each bar should be within the category width.<br>
	 * 1.0 will take the whole category width and put the bars right next to each other.
	 * 
	 * @return percent (0-1) of the available width each bar should be within the category width.<br>
	 *         1.0 will take the whole category width and put the bars right next to each other.
	 */
	double getBarPercentage();

	/**
	 * Returns the percent (0-1) of the available width each category should be within the sample width.
	 * 
	 * @return the percent (0-1) of the available width each category should be within the sample width.
	 */
	double getCategoryPercentage();

	/**
	 * Returns the width of each bar in pixels.<br>
	 * If set to 'flex', it computes "optimal" sample widths that globally arrange bars side by side.<br>
	 * If not set, the base sample widths are calculated automatically so that they take the full available widths without overlap.<br>
	 * Then, the bars are sized using barPercentage and categoryPercentage.
	 * 
	 * @return width of each bar in pixels.<br>
	 *         If not set, the base sample widths are calculated automatically so that they take the full available widths without overlap.<br>
	 *         Then, the bars are sized using barPercentage and categoryPercentage.
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
	 * When <code>true</code>, all the data sets at same index value will be placed next to each other centering on that index value.<br>
	 * When <code>false</code>, each bar is placed on its actual index-axis value.
	 * 
	 * @return if <code>true</code>, all the data sets at same index value will be placed next to each other centering on that index value.
	 */
	boolean isGrouped();

}