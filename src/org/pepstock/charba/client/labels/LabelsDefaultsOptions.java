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
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.labels.enums.Position;
import org.pepstock.charba.client.labels.enums.Render;

/**
 * It wraps default global options if there are and provides all default values for LABELS plugin.
 * 
 * @author Andrea "Stock" Stocchero
 */
final class LabelsDefaultsOptions extends NativeObjectContainer {

	private static final Render DEFAULT_RENDER = Render.value;

	private static final int DEFAULT_PRECISION = 0;

	private static final boolean DEFAULT_SHOWZERO = false;

	private static final boolean DEFAULT_TEXTSHADOW = false;

	private static final int DEFAULT_SHADOWBLUR = 6;

	private static final int DEFAULT_SHADOWOFFSETX = 3;

	private static final int DEFAULT_SHADOWOFFSETY = 3;

	private static final String DEFAULT_SHADOWCOLOR = "rgba(0,0,0,0.3)";

	private static final boolean DEFAULT_ARC = false;

	private static final String DEFAULT_POSITION = Position.defaults.getValue();

	private static final boolean DEFAULT_OVERLAP = true;

	private static final boolean DEFAULT_SHOWACTUALPERCENTAGES = false;

	private static final int DEFAULT_OUTSIDEPADDING = 2;

	private static final int DEFAULT_TEXTMARGIN = 2;

	/**
	 * Creates an empty options without any default global options. it will use the constants as default of plugin properties.
	 */
	LabelsDefaultsOptions() {
		super();
	}

	/**
	 * Creates the object wrapping the default global options if there are.
	 * 
	 * @param nativeObject native object which maps default global options.
	 */
	LabelsDefaultsOptions(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns what data must be showed.
	 * 
	 * @return what data must be showed. Default is {@link Render#value}.
	 */
	Render getRender() {
		return getValue(LabelsOptions.Property.render, Render.class, DEFAULT_RENDER);
	}

	/**
	 * Returns the precision for percentage.
	 * 
	 * @return the precision for percentage. Default is 0.
	 */
	int getPrecision() {
		return getValue(LabelsOptions.Property.precision, DEFAULT_PRECISION);
	}

	/**
	 * Returns whether or not labels of value 0 are displayed.
	 * 
	 * @return whether or not labels of value 0 are displayed. Default is false.
	 */
	boolean isShowZero() {
		return getValue(LabelsOptions.Property.showZero, DEFAULT_SHOWZERO);
	}

	/**
	 * Returns the the font size.
	 * 
	 * @return the font size. Default is <code>Defaults.get().getGlobal().getDefaultFontSize()</code>.
	 */
	int getFontSize() {
		return getValue(LabelsOptions.Property.fontSize, Defaults.get().getGlobal().getDefaultFontSize());
	}

	/**
	 * Returns the the font color as string.
	 * 
	 * @return the font color. Default is <code>Defaults.get().getGlobal().getDefaultFontColorAsString()</code>.
	 */
	String getFontColorAsString() {
		return getValue(LabelsOptions.Property.fontColor, Defaults.get().getGlobal().getDefaultFontColorAsString());
	}

	/**
	 * Returns the font style.
	 * 
	 * @return the font style. Default is <code>Defaults.get().getGlobal().getDefaultFontStyle()</code>.
	 */
	FontStyle getFontStyle() {
		return getValue(LabelsOptions.Property.fontStyle, FontStyle.class, Defaults.get().getGlobal().getDefaultFontStyle());
	}

	/**
	 * Returns the font family.
	 * 
	 * @return the font family. Default is <code>Defaults.get().getGlobal().getDefaultFontFamily()</code>.
	 */
	String getFontFamily() {
		return getValue(LabelsOptions.Property.fontFamily, Defaults.get().getGlobal().getDefaultFontFamily());
	}

	/**
	 * Returns if draws text shadows under labels.
	 * 
	 * @return <code>true</code> if draws text shadows under labels. Default is <code>false</code>.
	 */
	boolean isTextShadow() {
		return getValue(LabelsOptions.Property.textShadow, DEFAULT_TEXTSHADOW);
	}

	/**
	 * Returns the text shadow intensity.
	 * 
	 * @return the text shadow intensity. Default is 6.
	 */
	int getShadowBlur() {
		return getValue(LabelsOptions.Property.shadowBlur, DEFAULT_SHADOWBLUR);
	}

	/**
	 * Returns the text shadow X offset.
	 * 
	 * @return the text shadow X offset. Default is 3.
	 */
	int getShadowOffsetX() {
		return getValue(LabelsOptions.Property.shadowOffsetX, DEFAULT_SHADOWOFFSETX);
	}

	/**
	 * Returns the text shadow Y offset.
	 * 
	 * @return the text shadow Y offset. Default is 3.
	 */
	int getShadowOffsetY() {
		return getValue(LabelsOptions.Property.shadowOffsetY, DEFAULT_SHADOWOFFSETY);
	}

	/**
	 * Returns the text shadow color as string.
	 * 
	 * @return the text shadow color as string. Default is <code>rgba(0,0,0,0.3)</code>.
	 */
	String getShadowColorAsString() {
		return getValue(LabelsOptions.Property.shadowColor, DEFAULT_SHADOWCOLOR);
	}

	/**
	 * Returns if draws label in arc.
	 * 
	 * @return <code>true</code> if draws label in arc. Default is <code>false</code>.
	 */
	boolean isArc() {
		return getValue(LabelsOptions.Property.arc, DEFAULT_ARC);
	}

	/**
	 * Returns the position to draw label.
	 * 
	 * @return the position to draw label. Default is {@link Position#defaults}.
	 */
	String getPositionAsString() {
		return getValue(LabelsOptions.Property.position, DEFAULT_POSITION);
	}

	/**
	 * Returns if draws label even it's overlap.
	 * 
	 * @return <code>true</code>if draws label even it's overlap. Default is <code>true</code>.
	 */
	boolean isOverlap() {
		return getValue(LabelsOptions.Property.overlap, DEFAULT_OVERLAP);
	}

	/**
	 * Returns if shows the real calculated percentages from the values and don't apply the additional logic to fit the
	 * percentages to 100 in total.
	 * 
	 * @return <code>true</code>if shows the real calculated percentages from the values and don't apply the additional logic to
	 *         fit the percentages to 100 in total. Default is <code>false</code>.
	 */
	boolean isShowActualPercentages() {
		return getValue(LabelsOptions.Property.showActualPercentages, DEFAULT_SHOWACTUALPERCENTAGES);
	}

	/**
	 * Returns the padding when position is {@link Position#outside}.
	 * 
	 * @return the padding when position is {@link Position#outside}. Default is 2.
	 */
	int getOutsidePadding() {
		return getValue(LabelsOptions.Property.outsidePadding, DEFAULT_OUTSIDEPADDING);
	}

	/**
	 * Returns the margin of text when position is {@link Position#outside} or {@link Position#border}.
	 * 
	 * @return the margin of text when position is {@link Position#outside} or {@link Position#border}. Default is 2.
	 */
	int getTextMargin() {
		return getValue(LabelsOptions.Property.textMargin, DEFAULT_TEXTMARGIN);
	}
}
