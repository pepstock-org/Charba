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
package org.pepstock.charba.client.ext.labels;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.ext.labels.LabelsOptionsFactory.LabelsDefaultsOptionsFactory;

public final class LabelsOptions extends NativeObjectContainer {

	public static final String ID = "labels";

	// defaults global options instance
	private LabelsDefaultsOptions defaultsOptions;
	// defaults global options factory
	private final LabelsDefaultsOptionsFactory defaultsFactory = new LabelsDefaultsOptionsFactory();

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		render,
		precision,
		showZero,
		fontSize,
		fontColor,
		fontStyle,
		fontFamily,
		textShadow,
		shadowBlur,
		shadowOffsetX,
		shadowOffsetY,
		shadowColor,
		arc,
		position,
		overlap,
		showActualPercentages,
		images,
		outsidePadding,
		textMargin
	}

	/**
	 * 
	 */
	public LabelsOptions() {
		// creates an empty object
		super(null);
		// checks if the default global options has been added for the plugin
		if (Defaults.get().getGlobal().getPlugins().hasOptions(LabelsOptions.ID)) {
			// reads the default default global options
			defaultsOptions = Defaults.get().getGlobal().getPlugins().getOptions(LabelsOptions.ID, defaultsFactory);
		} else {
			// if here, no default global option
			// then the plugin will use the static defaults
			defaultsOptions = new LabelsDefaultsOptions(null);
		}
	}

	/**
	 * @param nativeObject
	 */
	LabelsOptions(NativeObject nativeObject, LabelsDefaultsOptions defaultsOptions) {
		super(nativeObject);
		this.defaultsOptions = defaultsOptions;
	}

	/**
	 * Sets what data must be showed.
	 * 
	 * @param render what data must be showed.
	 */
	public void setRender(Render render) {
		setValue(Property.render, render);
	}

	/**
	 * Returns what data must be showed.
	 * 
	 * @return what data must be showed. Default is {@link Render#value}.
	 */
	public Render getRender() {
		return getValue(Property.render, Render.class, Render.value);
	}

	/**
	 * Sets the precision for percentage.
	 * 
	 * @param precision the precision for percentage
	 */
	public void setPrecision(int precision) {
		setValue(Property.precision, precision);
	}

	/**
	 * Returns the precision for percentage.
	 * 
	 * @return the precision for percentage. Default is 0.
	 */
	public int getPrecision() {
		return getValue(Property.precision, defaultsOptions.getPrecision());
	}

	/**
	 * Sets whether or not labels of value 0 are displayed.
	 * 
	 * @param showZero whether or not labels of value 0 are displayed.
	 */
	public void setShowZero(boolean showZero) {
		setValue(Property.showZero, showZero);
	}

	/**
	 * Returns whether or not labels of value 0 are displayed.
	 * 
	 * @return whether or not labels of value 0 are displayed. Default is false.
	 */
	public boolean isShowZero() {
		return getValue(Property.showZero, defaultsOptions.isShowZero());
	}

	/**
	 * Sets the font size.
	 * 
	 * @param size the font size.
	 */
	public void setFontSize(int size) {
		setValue(Property.fontSize, size);
	}

	/**
	 * Returns the the font size.
	 * 
	 * @return the font size. Default is <code>Defaults.get().getGlobal().getDefaultFontSize()</code>.
	 */
	public int getFontSize() {
		return getValue(Property.fontSize, defaultsOptions.getFontSize());
	}

	/**
	 * Sets the font color as color.
	 * 
	 * @param color the font color as color.
	 */
	public void setFontColor(IsColor color) {
		setFontColor(color.toRGBA());
	}

	/**
	 * Sets the font color as string.
	 * 
	 * @param color the font color as string.
	 */
	public void setFontColor(String color) {
		setValue(Property.fontColor, color);
	}

	/**
	 * Returns the the font color as string.
	 * 
	 * @return the font color. Default is <code>Defaults.get().getGlobal().getDefaultFontColorAsString()</code>.
	 */
	public String getFontColorAsString() {
		return getValue(Property.fontColor, defaultsOptions.getFontColorAsString());
	}

	/**
	 * Returns the the font color as color.
	 * 
	 * @return the font color.
	 */
	public IsColor getFontColor() {
		return ColorBuilder.parse(getFontColorAsString());
	}

	/**
	 * Sets the font style.
	 * 
	 * @param fontStyle the font style.
	 */
	public void setFontStyle(FontStyle fontStyle) {
		setValue(Property.fontStyle, fontStyle);
	}

	/**
	 * Returns the font style.
	 * 
	 * @return the font style. Default is <code>Defaults.get().getGlobal().getDefaultFontStyle()</code>.
	 */
	public FontStyle getFontStyle() {
		return getValue(Property.fontStyle, FontStyle.class, defaultsOptions.getFontStyle());
	}

	/**
	 * Sets the font family
	 * 
	 * @param fontFamily the font family
	 */
	public void setFontFamily(String fontFamily) {
		setValue(Property.fontFamily, fontFamily);
	}

	/**
	 * Returns the font family.
	 * 
	 * @return the font family. Default is <code>Defaults.get().getGlobal().getDefaultFontFamily()</code>.
	 */
	public String getFontFamily() {
		return getValue(Property.fontFamily, defaultsOptions.getFontFamily());
	}

	/**
	 * Sets if draws text shadows under labels.
	 * 
	 * @param textShadow <code>true</code> if draws text shadows under labels.
	 */
	public void setTextShadow(boolean textShadow) {
		setValue(Property.textShadow, textShadow);
	}

	/**
	 * Returns if draws text shadows under labels.
	 * 
	 * @return <code>true</code> if draws text shadows under labels. Default is <code>false</code>.
	 */
	public boolean isTextShadow() {
		return getValue(Property.textShadow, defaultsOptions.isTextShadow());
	}

	/**
	 * Sets the text shadow intensity.
	 * 
	 * @param shadowBlur the text shadow intensity.
	 */
	public void setShadowBlur(int shadowBlur) {
		setValue(Property.shadowBlur, shadowBlur);
	}

	/**
	 * Returns the text shadow intensity.
	 * 
	 * @return the text shadow intensity. Default is 6.
	 */
	public int getShadowBlur() {
		return getValue(Property.shadowBlur, defaultsOptions.getShadowBlur());
	}

	/**
	 * Sets the text shadow X offset.
	 * 
	 * @param shadowOffsetX the text shadow X offset.
	 */
	public void setShadowOffsetX(int shadowOffsetX) {
		setValue(Property.shadowOffsetX, shadowOffsetX);
	}

	/**
	 * Returns the text shadow X offset.
	 * 
	 * @return the text shadow X offset. Default is 3.
	 */
	public int getShadowOffsetX() {
		return getValue(Property.shadowOffsetX, defaultsOptions.getShadowOffsetX());
	}

	/**
	 * Sets the text shadow Y offset.
	 * 
	 * @param shadowOffsetY the text shadow Y offset.
	 */
	public void setShadowOffsetY(int shadowOffsetY) {
		setValue(Property.shadowOffsetY, shadowOffsetY);
	}

	/**
	 * Returns the text shadow Y offset.
	 * 
	 * @return the text shadow Y offset. Default is 3.
	 */
	public int getShadowOffsetY() {
		return getValue(Property.shadowOffsetY, defaultsOptions.getShadowOffsetY());
	}

	/**
	 * Sets the text shadow color as color.
	 * 
	 * @param shadowColor the text shadow color as color.
	 */
	public void setShadowColor(IsColor shadowColor) {
		setShadowColor(shadowColor.toRGBA());
	}

	/**
	 * Sets the text shadow color as string.
	 * 
	 * @param shadowColor the text shadow color as string.
	 */
	public void setShadowColor(String shadowColor) {
		setValue(Property.shadowColor, shadowColor);
	}

	/**
	 * Returns the text shadow color as string.
	 * 
	 * @return the text shadow color as string. Default is <code>rgba(0,0,0,0.3)</code>.
	 */
	public String getShadowColorAsString() {
		return getValue(Property.shadowColor, defaultsOptions.getShadowColorAsString());
	}

	/**
	 * Returns the text shadow color as color.
	 * 
	 * @return the text shadow color as color.
	 */
	public IsColor getShadowColor() {
		return ColorBuilder.parse(getShadowColorAsString());
	}

	/**
	 * Sets if draws label in arc. For bar chart this is ignored.
	 * 
	 * @param arc if draws label in arc.
	 */
	public void setArc(boolean arc) {
		setValue(Property.arc, arc);
	}

	/**
	 * Returns if draws label in arc.
	 * 
	 * @return <code>true</code> if draws label in arc. Default is <code>false</code>.
	 */
	public boolean isArc() {
		return getValue(Property.arc, defaultsOptions.isArc());
	}

	/**
	 * + Sets the position to draw label. For bar chart this is ignored.
	 * 
	 * @param position the position to draw label.
	 */
	public void setPosition(Position position) {
		setValue(Property.position, position.getValue());
	}

	/**
	 * Returns the position to draw label.
	 * 
	 * @return the position to draw label. Default is {@link Position#defaults}.
	 */
	public Position getPosition() {
		String value = getValue(Property.position, defaultsOptions.getPositionAsString());
		return Position.getPositionByValue(value);
	}

	/**
	 * Sets if draws label even it's overlap. For bar chart this is ignored.
	 * 
	 * @param overlap if draws label even it's overlap.
	 */
	public void setOverlap(boolean overlap) {
		setValue(Property.overlap, overlap);
	}

	/**
	 * Returns if draws label even it's overlap.
	 * 
	 * @return <code>true</code>if draws label even it's overlap. Default is <code>true</code>.
	 */
	public boolean isOverlap() {
		return getValue(Property.overlap, defaultsOptions.isOverlap());
	}

	/**
	 * Sets if shows the real calculated percentages from the values and don't apply the additional logic to fit the percentages
	 * to 100 in total.
	 * 
	 * @param showActualPercentages if shows the real calculated percentages from the values and don't apply the additional
	 *            logic to fit the percentages to 100 in total.
	 */
	public void setShowActualPercentages(boolean showActualPercentages) {
		setValue(Property.showActualPercentages, showActualPercentages);
	}

	/**
	 * Returns if shows the real calculated percentages from the values and don't apply the additional logic to fit the
	 * percentages to 100 in total.
	 * 
	 * @return <code>true</code>if shows the real calculated percentages from the values and don't apply the additional logic to
	 *         fit the percentages to 100 in total. Default is <code>false</code>.
	 */
	public boolean isShowActualPercentages() {
		return getValue(Property.showActualPercentages, defaultsOptions.isShowActualPercentages());
	}

	/**
	 * Sets the padding when position is {@link Position#outside}.
	 * 
	 * @param outsidePadding the padding when position is {@link Position#outside}.
	 */
	public void setOutsidePadding(int outsidePadding) {
		setValue(Property.outsidePadding, outsidePadding);
	}

	/**
	 * Returns the padding when position is {@link Position#outside}.
	 * 
	 * @return the padding when position is {@link Position#outside}. Default is 2.
	 */
	public int getOutsidePadding() {
		return getValue(Property.outsidePadding, defaultsOptions.getOutsidePadding());
	}

	/**
	 * Sets the margin of text when position is {@link Position#outside} or {@link Position#border}.
	 * 
	 * @param textMargin the margin of text when position is {@link Position#outside} or {@link Position#border}.
	 */
	public void setTextMargin(int textMargin) {
		setValue(Property.textMargin, textMargin);
	}

	/**
	 * Returns the margin of text when position is {@link Position#outside} or {@link Position#border}.
	 * 
	 * @return the margin of text when position is {@link Position#outside} or {@link Position#border}. Default is 2.
	 */
	public int getTextMargin() {
		return getValue(Property.textMargin, defaultsOptions.getTextMargin());
	}

}
