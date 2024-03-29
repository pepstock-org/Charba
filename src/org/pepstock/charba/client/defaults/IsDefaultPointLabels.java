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

import org.pepstock.charba.client.enums.Display;

/**
 * Interface to define point labels object defaults.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsDefaultPointLabels extends IsDefaultFontContainer {

	/**
	 * Returns the padding of label backdrop.
	 * 
	 * @return padding of label backdrop.
	 */
	IsDefaultPadding getBackdropPadding();

	/**
	 * Returns if the labels is shown.
	 * 
	 * @return if the labels is shown.
	 */
	Display getDisplay();

	/**
	 * Returns the padding between chart and point labels, in pixels.
	 * 
	 * @return padding the padding between chart and point labels, in pixels.
	 */
	int getPadding();

	/**
	 * Returns the background color of the point label.
	 * 
	 * @return the background color of the point label
	 */
	String getBackdropColorAsString();

	/**
	 * Returns if point labels are centered.
	 * 
	 * @return if point labels are centered
	 */
	boolean isCentered();

	/**
	 * Returns the border radius (in pixels).
	 * 
	 * @return the border radius (in pixels).
	 */
	int getBorderRadius();

}