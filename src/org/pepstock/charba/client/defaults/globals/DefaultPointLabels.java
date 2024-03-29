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
package org.pepstock.charba.client.defaults.globals;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.defaults.IsDefaultFont;
import org.pepstock.charba.client.defaults.IsDefaultPadding;
import org.pepstock.charba.client.defaults.IsDefaultPointLabels;
import org.pepstock.charba.client.enums.Display;

/**
 * CHART.JS default values for POINTLABELS element.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultPointLabels implements IsDefaultPointLabels {

	private static final Display DEFAULT_DISPLAY = Display.FALSE;

	private static final int DEFAULT_PADDING = 5;

	private static final boolean DEFAULT_CENTER_POINT_LABELS = false;

	private static final int DEFAULT_BORDER_RADIUS = 0;

	private final DefaultPadding padding = new DefaultPadding(DEFAULT_PADDING);

	private final DefaultRoutedFont font = new DefaultRoutedFont();

	/**
	 * To avoid any instantiation
	 */
	DefaultPointLabels() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultPointLabels#getFont()
	 */
	@Override
	public IsDefaultFont getFont() {
		return font;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultPointLabels#getColorAsString()
	 */
	@Override
	public String getColorAsString() {
		return Defaults.get().getGlobal().getColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultPointLabels#getDisplay()
	 */
	@Override
	public Display getDisplay() {
		return DEFAULT_DISPLAY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultPointLabels#getBackdropPadding()
	 */
	@Override
	public IsDefaultPadding getBackdropPadding() {
		return padding;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultPointLabels#getPadding()
	 */
	@Override
	public int getPadding() {
		return DEFAULT_PADDING;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultPointLabels#getBackdropColorAsString()
	 */
	@Override
	public String getBackdropColorAsString() {
		return Defaults.get().getGlobal().getBackgroundColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultPointLabels#isCentered()
	 */
	@Override
	public boolean isCentered() {
		return DEFAULT_CENTER_POINT_LABELS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultPointLabels#getBorderRadius()
	 */
	@Override
	public int getBorderRadius() {
		return DEFAULT_BORDER_RADIUS;
	}

}