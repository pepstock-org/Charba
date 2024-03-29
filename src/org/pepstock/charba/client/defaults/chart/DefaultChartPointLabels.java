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
package org.pepstock.charba.client.defaults.chart;

import org.pepstock.charba.client.defaults.IsDefaultFont;
import org.pepstock.charba.client.defaults.IsDefaultPadding;
import org.pepstock.charba.client.defaults.IsDefaultPointLabels;
import org.pepstock.charba.client.enums.Display;
import org.pepstock.charba.client.options.PointLabels;

/**
 * Defaults for point labels option element, based on chart type.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultChartPointLabels implements IsDefaultPointLabels {

	private final PointLabels pointLabels;

	/**
	 * Creates the object by point labels option element instance.
	 * 
	 * @param pointLabels point labels option element instance.
	 */
	DefaultChartPointLabels(PointLabels pointLabels) {
		this.pointLabels = pointLabels;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultPointLabels#getFont()
	 */
	@Override
	public IsDefaultFont getFont() {
		return pointLabels.getFont();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultPointLabels#getColorAsString()
	 */
	@Override
	public String getColorAsString() {
		return pointLabels.getColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultPointLabels#getDisplay()
	 */
	@Override
	public Display getDisplay() {
		return pointLabels.getDisplay();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultPointLabels#getBackdropPadding()
	 */
	@Override
	public IsDefaultPadding getBackdropPadding() {
		return pointLabels.getBackdropPadding();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultPointLabels#getPadding()
	 */
	@Override
	public int getPadding() {
		return pointLabels.getPadding();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultPointLabels#getBackdropColorAsString()
	 */
	@Override
	public String getBackdropColorAsString() {
		return pointLabels.getBackdropColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultPointLabels#isCentered()
	 */
	@Override
	public boolean isCentered() {
		return pointLabels.isCentered();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultPointLabels#getBorderRadius()
	 */
	@Override
	public int getBorderRadius() {
		return pointLabels.getBorderRadius();
	}

}