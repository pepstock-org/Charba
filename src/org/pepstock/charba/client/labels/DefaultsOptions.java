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
package org.pepstock.charba.client.labels;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.labels.enums.Position;
import org.pepstock.charba.client.labels.enums.Render;
import org.pepstock.charba.client.plugins.AbstractPluginOptions;

/**
 * It wraps default global options if there are and provides all default values for {@link LabelsPlugin#ID} plugin.
 * 
 * @author Andrea "Stock" Stocchero
 */
final class DefaultsOptions extends AbstractPluginOptions {

	// defaults options instance
	static final DefaultsOptions DEFAULTS_INSTANCE = new DefaultsOptions();

	/**
	 * Creates an empty options without any default global options. it will use the constants as default of plugin properties.
	 */
	private DefaultsOptions() {
		this(null);
	}

	/**
	 * Creates the object wrapping the default global options if there are.
	 * 
	 * @param nativeObject native object which maps default global options.
	 */
	DefaultsOptions(NativeObject nativeObject) {
		super(LabelsPlugin.ID, nativeObject);
	}

	/**
	 * Returns what data must be showed.
	 * 
	 * @return what data must be showed.
	 */
	Render getRender() {
		return getValue(LabelsOptions.Property.RENDER, Render.class, LabelsOptions.DEFAULT_RENDER);
	}

	/**
	 * Returns the precision for percentage.
	 * 
	 * @return the precision for percentage.
	 */
	int getPrecision() {
		return getValue(LabelsOptions.Property.PRECISION, LabelsOptions.DEFAULT_PRECISION);
	}

	/**
	 * Returns whether or not labels of value 0 are displayed.
	 * 
	 * @return whether or not labels of value 0 are displayed.
	 */
	boolean isShowZero() {
		return getValue(LabelsOptions.Property.SHOW_ZERO, LabelsOptions.DEFAULT_SHOW_ZERO);
	}

	/**
	 * Returns the the font size.
	 * 
	 * @return the font size..
	 */
	int getFontSize() {
		return getValue(LabelsOptions.Property.FONT_SIZE, Defaults.get().getGlobal().getDefaultFontSize());
	}

	/**
	 * Returns the the font color as string.
	 * 
	 * @return the font color.
	 */
	String getFontColorAsString() {
		return getValue(LabelsOptions.Property.FONT_COLOR, Defaults.get().getGlobal().getDefaultFontColorAsString());
	}

	/**
	 * Returns the font style.
	 * 
	 * @return the font style.
	 */
	FontStyle getFontStyle() {
		return getValue(LabelsOptions.Property.FONT_STYLE, FontStyle.class, Defaults.get().getGlobal().getDefaultFontStyle());
	}

	/**
	 * Returns the font family.
	 * 
	 * @return the font family.
	 */
	String getFontFamily() {
		return getValue(LabelsOptions.Property.FONT_FAMILY, Defaults.get().getGlobal().getDefaultFontFamily());
	}

	/**
	 * Returns if draws text shadows under labels.
	 * 
	 * @return <code>true</code> if draws text shadows under labels.
	 */
	boolean isTextShadow() {
		return getValue(LabelsOptions.Property.TEXT_SHADOW, LabelsOptions.DEFAULT_TEXT_SHADOW);
	}

	/**
	 * Returns the text shadow intensity.
	 * 
	 * @return the text shadow intensity.
	 */
	int getShadowBlur() {
		return getValue(LabelsOptions.Property.SHADOW_BLUR, LabelsOptions.DEFAULT_SHADOW_BLUR);
	}

	/**
	 * Returns the text shadow X offset.
	 * 
	 * @return the text shadow X offset.
	 */
	int getShadowOffsetX() {
		return getValue(LabelsOptions.Property.SHADOW_OFFSET_X, LabelsOptions.DEFAULT_SHADOW_OFFSET_X);
	}

	/**
	 * Returns the text shadow Y offset.
	 * 
	 * @return the text shadow Y offset.
	 */
	int getShadowOffsetY() {
		return getValue(LabelsOptions.Property.SHADOW_OFFSET_Y, LabelsOptions.DEFAULT_SHADOW_OFFSET_Y);
	}

	/**
	 * Returns the text shadow color as string.
	 * 
	 * @return the text shadow color as string.
	 */
	String getShadowColorAsString() {
		return getValue(LabelsOptions.Property.SHADOW_COLOR, LabelsOptions.DEFAULT_SHADOW_COLOR);
	}

	/**
	 * Returns if draws label in arc.
	 * 
	 * @return <code>true</code> if draws label in arc.
	 */
	boolean isArc() {
		return getValue(LabelsOptions.Property.ARC, LabelsOptions.DEFAULT_ARC);
	}

	/**
	 * Returns the position to draw label.
	 * 
	 * @return the position to draw label.
	 */
	Position getPosition() {
		return getValue(LabelsOptions.Property.POSITION, Position.class, LabelsOptions.DEFAULT_POSITION);
	}

	/**
	 * Returns if draws label even it's overlap.
	 * 
	 * @return <code>true</code>if draws label even it's overlap.
	 */
	boolean isOverlap() {
		return getValue(LabelsOptions.Property.OVERLAP, LabelsOptions.DEFAULT_OVERLAP);
	}

	/**
	 * Returns if shows the real calculated percentages from the values and don't apply the additional logic to fit the
	 * percentages to 100 in total.
	 * 
	 * @return <code>true</code>if shows the real calculated percentages from the values and don't apply the additional logic to
	 *         fit the percentages to 100 in total.
	 */
	boolean isShowActualPercentages() {
		return getValue(LabelsOptions.Property.SHOW_ACTUAL_PERCENTAGES, LabelsOptions.DEFAULT_SHOW_ACTUAL_PERCENTAGES);
	}

	/**
	 * Returns the padding when position is {@link Position#OUTSIDE}.
	 * 
	 * @return the padding when position is {@link Position#OUTSIDE}.
	 */
	int getOutsidePadding() {
		return getValue(LabelsOptions.Property.OUTSIDE_PADDING, LabelsOptions.DEFAULT_OUTSIDE_PADDING);
	}

	/**
	 * Returns the margin of text when position is {@link Position#OUTSIDE} or {@link Position#BORDER}.
	 * 
	 * @return the margin of text when position is {@link Position#OUTSIDE} or {@link Position#BORDER}.
	 */
	int getTextMargin() {
		return getValue(LabelsOptions.Property.TEXT_MARGIN, LabelsOptions.DEFAULT_TEXT_MARGIN);
	}
}
