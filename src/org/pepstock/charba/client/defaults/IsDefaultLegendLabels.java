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

import org.pepstock.charba.client.enums.TextAlign;

/**
 * Interface to define legend labels object defaults.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface IsDefaultLegendLabels extends IsDefaultBoxHandler, IsDefaultPointStyleHandler, IsDefaultFontContainer {

	/**
	 * Returns if label style will match corresponding point style (size is based on fontSize, boxWidth is not used in this case).
	 * 
	 * @return if label style will match corresponding point style (size is based on fontSize, boxWidth is not used in this case).
	 */
	boolean isUsePointStyle();

	/**
	 * Returns if <code>usePointStyle</code> is true, the width of the point style used for the legend.
	 * 
	 * @return if <code>usePointStyle</code> is true, the width of the point style used for the legend.
	 */
	double getPointStyleWidth();

	/**
	 * Returns the padding to apply around labels. Only top and bottom are implemented.
	 * 
	 * @return Padding to apply around labels. Only top and bottom are implemented.
	 */
	int getPadding();

	/**
	 * Returns the horizontal alignment of the label text.
	 * 
	 * @return the horizontal alignment of the label text.
	 */
	TextAlign getTextAlign();

	/**
	 * Returns if label border radius will match corresponding border radius.
	 * 
	 * @return if label border radius will match corresponding border radius.
	 */
	boolean isUseBorderRadius();

	/**
	 * Returns the the border radius to use on the legend.
	 * 
	 * @return the the border radius to use on the legend
	 */
	int getBorderRadius();

}