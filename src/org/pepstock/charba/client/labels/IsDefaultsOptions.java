/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License";
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package org.pepstock.charba.client.labels;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.labels.callbacks.FontColorCallback;
import org.pepstock.charba.client.labels.callbacks.RenderCallback;
import org.pepstock.charba.client.labels.enums.Position;
import org.pepstock.charba.client.labels.enums.Render;

/**
 * Maps the default global options if there are and provides all default values for {@link LabelsPlugin#ID} plugin.
 * 
 * @author Andrea "Stock" Stocchero
 */
interface IsDefaultsOptions {

	/**
	 * Returns what data must be showed.
	 * 
	 * @return what data must be showed.
	 */
	default Render getRender() {
		return LabelsOptions.DEFAULT_RENDER;
	}

	/**
	 * Returns the precision for percentage.
	 * 
	 * @return the precision for percentage.
	 */
	default int getPrecision() {
		return LabelsOptions.DEFAULT_PRECISION;
	}

	/**
	 * Returns whether or not labels of value 0 are displayed.
	 * 
	 * @return whether or not labels of value 0 are displayed.
	 */
	default boolean isShowZero() {
		return LabelsOptions.DEFAULT_SHOW_ZERO;
	}

	/**
	 * Returns the the font size.
	 * 
	 * @return the font size..
	 */
	default int getFontSize() {
		return Defaults.get().getGlobal().getFont().getSize();
	}

	/**
	 * Returns the the font color as string.
	 * 
	 * @return the font color.
	 */
	default String getFontColorAsString() {
		return Defaults.get().getGlobal().getFont().getColorAsString();
	}

	/**
	 * Returns the font style.
	 * 
	 * @return the font style.
	 */
	default FontStyle getFontStyle() {
		return  Defaults.get().getGlobal().getFont().getStyle();
	}

	/**
	 * Returns the font family.
	 * 
	 * @return the font family.
	 */
	default String getFontFamily() {
		return  Defaults.get().getGlobal().getFont().getFamily();
	}

	/**
	 * Returns if draws text shadows under labels.
	 * 
	 * @return <code>true</code> if draws text shadows under labels.
	 */
	default boolean isTextShadow() {
		return  LabelsOptions.DEFAULT_TEXT_SHADOW;
	}

	/**
	 * Returns the text shadow intensity.
	 * 
	 * @return the text shadow intensity.
	 */
	default int getShadowBlur() {
		return  LabelsOptions.DEFAULT_SHADOW_BLUR;
	}

	/**
	 * Returns the text shadow X offset.
	 * 
	 * @return the text shadow X offset.
	 */
	default int getShadowOffsetX() {
		return  LabelsOptions.DEFAULT_SHADOW_OFFSET_X;
	}

	/**
	 * Returns the text shadow Y offset.
	 * 
	 * @return the text shadow Y offset.
	 */
	default int getShadowOffsetY() {
		return LabelsOptions.DEFAULT_SHADOW_OFFSET_Y;
	}

	/**
	 * Returns the text shadow color as string.
	 * 
	 * @return the text shadow color as string.
	 */
	default String getShadowColorAsString() {
		return LabelsOptions.DEFAULT_SHADOW_COLOR;
	}

	/**
	 * Returns if draws label in arc.
	 * 
	 * @return <code>true</code> if draws label in arc.
	 */
	default boolean isArc() {
		return LabelsOptions.DEFAULT_ARC;
	}

	/**
	 * Returns the position to draw label.
	 * 
	 * @return the position to draw label.
	 */
	default Position getPosition() {
		return LabelsOptions.DEFAULT_POSITION;
	}

	/**
	 * Returns if draws label even it's overlap.
	 * 
	 * @return <code>true</code>if draws label even it's overlap.
	 */
	default boolean isOverlap() {
		return LabelsOptions.DEFAULT_OVERLAP;
	}

	/**
	 * Returns if shows the real calculated percentages from the values and don't apply the additional logic to fit the percentages to 100 in total.
	 * 
	 * @return <code>true</code>if shows the real calculated percentages from the values and don't apply the additional logic to fit the percentages to 100 in total.
	 */
	default boolean isShowActualPercentages() {
		return LabelsOptions.DEFAULT_SHOW_ACTUAL_PERCENTAGES;
	}

	/**
	 * Returns the padding when position is {@link Position#OUTSIDE}.
	 * 
	 * @return the padding when position is {@link Position#OUTSIDE}.
	 */
	default int getOutsidePadding() {
		return LabelsOptions.DEFAULT_OUTSIDE_PADDING;
	}

	/**
	 * Returns the margin of text when position is {@link Position#OUTSIDE} or {@link Position#BORDER}.
	 * 
	 * @return the margin of text when position is {@link Position#OUTSIDE} or {@link Position#BORDER}.
	 */
	default int getTextMargin() {
		return LabelsOptions.DEFAULT_TEXT_MARGIN;
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
	 * Returns the font color callback.
	 * 
	 * @return the font color callback
	 */
	default FontColorCallback getFontColorCallback() {
		return null;
	}
}
