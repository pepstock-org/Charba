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
package org.pepstock.charba.client.impl.plugins;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.callbacks.CrosshairFormatterCallback;
import org.pepstock.charba.client.defaults.IsDefaultFont;

/**
 * Maps {@link Crosshair#ID} plugin default options for labels element.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface IsCrosshairDefaultLabel {

	/**
	 * Returns <code>true</code> if clear of selection label will be applied in the chart, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if clear of selection label will be applied in the chart, otherwise <code>false</code>
	 */
	default boolean isDisplay() {
		return CrosshairLabel.DEFAULT_DISPLAY;
	}

	/**
	 * Returns the crosshair label font.
	 * 
	 * @return the crosshair label font
	 */
	default IsDefaultFont getFont() {
		return Defaults.get().getGlobal().getFont();
	}

	/**
	 * Returns the crosshair label font color.
	 * 
	 * @return the crosshair label font color.
	 */
	default String getColorAsString() {
		return CrosshairLabel.DEFAULT_COLOR_AS_STRING;
	}

	/**
	 * Returns the crosshair padding.
	 * 
	 * @return the crosshair padding
	 */
	default int getPadding() {
		return CrosshairLabel.DEFAULT_PADDING;
	}

	/**
	 * Returns the background color.
	 * 
	 * @return the background color.
	 */
	default String getBackgroundColorAsString() {
		return CrosshairLabel.DEFAULT_BACKGROUND_COLOR_AS_STRING;
	}

	/**
	 * Returns the label border radius (in pixels).
	 * 
	 * @return the label border radius (in pixels).
	 */
	default int getBorderRadius() {
		return CrosshairLabel.DEFAULT_BORDER_RADIUS;
	}

	/**
	 * Returns the callback which can be implemented to change the text of label.
	 * 
	 * @return the callback which can be implemented to change the text of label
	 */
	default CrosshairFormatterCallback getFormatter() {
		return null;
	}

	/**
	 * Returns the border color.
	 * 
	 * @return the border color.
	 */
	default String getBorderColorAsString() {
		return CrosshairLabel.DEFAULT_BORDER_COLOR_AS_STRING;
	}

	/**
	 * Returns the border width.
	 * 
	 * @return the border width.
	 */
	default int getBorderWidth() {
		return CrosshairLabel.DEFAULT_BORDER_WIDTH;
	}
}