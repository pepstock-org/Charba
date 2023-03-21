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
package org.pepstock.charba.client.labels;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.callbacks.ColorCallback;
import org.pepstock.charba.client.callbacks.FontCallback;
import org.pepstock.charba.client.defaults.IsDefaultFont;
import org.pepstock.charba.client.labels.callbacks.RenderCallback;
import org.pepstock.charba.client.labels.enums.Position;
import org.pepstock.charba.client.labels.enums.Render;

/**
 * Maps the default global options if there are and provides all default values for {@link LabelsPlugin#ID} plugin.
 * 
 * @author Andrea "Stock" Stocchero
 */
interface IsDefaultLabel {

	/**
	 * Returns what data must be showed.
	 * 
	 * @return what data must be showed.
	 */
	default Render getRender() {
		return Label.DEFAULT_RENDER;
	}

	/**
	 * Returns the precision for percentage.
	 * 
	 * @return the precision for percentage.
	 */
	default int getPrecision() {
		return Label.DEFAULT_PRECISION;
	}

	/**
	 * Returns whether or not labels of value 0 are displayed.
	 * 
	 * @return whether or not labels of value 0 are displayed.
	 */
	default boolean isShowZero() {
		return Label.DEFAULT_SHOW_ZERO;
	}

	/**
	 * Returns the font color.
	 * 
	 * @return the font color.
	 */
	default String getColorAsString() {
		return Defaults.get().getGlobal().getColorAsString();
	}

	/**
	 * Returns the font object.
	 * 
	 * @return the font object.
	 */
	default IsDefaultFont getFont() {
		return Defaults.get().getGlobal().getFont();
	}

	/**
	 * Returns if draws text shadows under labels.
	 * 
	 * @return <code>true</code> if draws text shadows under labels.
	 */
	default boolean isTextShadow() {
		return Label.DEFAULT_TEXT_SHADOW;
	}

	/**
	 * Returns the text shadow intensity.
	 * 
	 * @return the text shadow intensity.
	 */
	default int getShadowBlur() {
		return Label.DEFAULT_SHADOW_BLUR;
	}

	/**
	 * Returns the text shadow X offset.
	 * 
	 * @return the text shadow X offset.
	 */
	default int getShadowOffsetX() {
		return Label.DEFAULT_SHADOW_OFFSET_X;
	}

	/**
	 * Returns the text shadow Y offset.
	 * 
	 * @return the text shadow Y offset.
	 */
	default int getShadowOffsetY() {
		return Label.DEFAULT_SHADOW_OFFSET_Y;
	}

	/**
	 * Returns the text shadow color as string.
	 * 
	 * @return the text shadow color as string.
	 */
	default String getShadowColorAsString() {
		return Label.DEFAULT_SHADOW_COLOR;
	}

	/**
	 * Returns if draws label in arc.
	 * 
	 * @return <code>true</code> if draws label in arc.
	 */
	default boolean isArc() {
		return Label.DEFAULT_ARC;
	}

	/**
	 * Returns the position to draw label.
	 * 
	 * @return the position to draw label.
	 */
	default Position getPosition() {
		return Label.DEFAULT_POSITION;
	}

	/**
	 * Returns if draws label even it's overlap.
	 * 
	 * @return <code>true</code>if draws label even it's overlap.
	 */
	default boolean isOverlap() {
		return Label.DEFAULT_OVERLAP;
	}

	/**
	 * Returns if shows the real calculated percentages from the values and don't apply the additional logic to fit the percentages to 100 in total.
	 * 
	 * @return <code>true</code>if shows the real calculated percentages from the values and don't apply the additional logic to fit the percentages to 100 in total.
	 */
	default boolean isShowActualPercentages() {
		return Label.DEFAULT_SHOW_ACTUAL_PERCENTAGES;
	}

	/**
	 * Returns the padding when position is {@link Position#OUTSIDE}.
	 * 
	 * @return the padding when position is {@link Position#OUTSIDE}.
	 */
	default int getOutsidePadding() {
		return Label.DEFAULT_OUTSIDE_PADDING;
	}

	/**
	 * Returns the margin of text when position is {@link Position#OUTSIDE} or {@link Position#BORDER}.
	 * 
	 * @return the margin of text when position is {@link Position#OUTSIDE} or {@link Position#BORDER}.
	 */
	default int getTextMargin() {
		return Label.DEFAULT_TEXT_MARGIN;
	}

	/**
	 * Returns the render callback.
	 * 
	 * @return the render callback
	 */
	default RenderCallback getRenderCallback() {
		return null;
	}

	/**
	 * Returns the font callback.
	 * 
	 * @return the font callback
	 */
	default FontCallback<LabelsContext> getFontCallback() {
		return null;
	}

	/**
	 * Returns the font color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the font color callback, if set, otherwise <code>null</code>
	 */
	default ColorCallback<LabelsContext> getColorCallback() {
		return null;
	}
}