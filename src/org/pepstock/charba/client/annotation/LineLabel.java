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
package org.pepstock.charba.client.annotation;

import java.util.List;

import org.pepstock.charba.client.annotation.enums.LineLabelPosition;
import org.pepstock.charba.client.colors.Color;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.HtmlColor;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.items.UndefinedValues;

/**
 * Implements a LABEL to apply on a LINE annotation.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class LineLabel extends NativeObjectContainer implements IsDefaultsLineLabel {

	/**
	 * Default line label enabled, <b>{@value DEFAULT_ENABLED}</b>.
	 */
	public static final boolean DEFAULT_ENABLED = false;

	/**
	 * Default line label background color, <b>rgba(0, 0, 0, 0.8)</b>.
	 */
	public static final IsColor DEFAULT_BACKGROUND_COLOR = new Color(0, 0, 0).alpha(0.8D);

	/**
	 * Default line label background color as string, <b>rgba(0, 0, 0, 0.8)</b>.
	 */
	public static final String DEFAULT_BACKGROUND_COLOR_AS_STRING = DEFAULT_BACKGROUND_COLOR.toRGBA();

	/**
	 * Default line label font style, <b>{@link FontStyle#BOLD}</b>.
	 */
	public static final FontStyle DEFAULT_FONT_STYLE = FontStyle.BOLD;

	/**
	 * Default line label font color, <b>white</b>.
	 */
	public static final IsColor DEFAULT_FONT_COLOR = HtmlColor.WHITE;

	/**
	 * Default line label font color as string, <b>white</b>.
	 */
	public static final String DEFAULT_FONT_COLOR_AS_STRING = DEFAULT_FONT_COLOR.toRGB();

	/**
	 * Default line label X padding, <b>{@value DEFAULT_X_PADDING}</b>.
	 */
	public static final int DEFAULT_X_PADDING = 6;

	/**
	 * Default line label Y padding, <b>{@value DEFAULT_Y_PADDING}</b>.
	 */
	public static final int DEFAULT_Y_PADDING = 6;

	/**
	 * Default line label corner radius, <b>{@value DEFAULT_CORNER_RADIUS}</b>.
	 */
	public static final double DEFAULT_CORNER_RADIUS = 6D;

	/**
	 * Default line label position, <b>{@link LineLabelPosition#CENTER}</b>.
	 */
	public static final LineLabelPosition DEFAULT_POSITION = LineLabelPosition.CENTER;

	/**
	 * Default line label X adjust, <b>{@value DEFAULT_X_ADJUST}</b>.
	 */
	public static final int DEFAULT_X_ADJUST = 0;

	/**
	 * Default line label Y adjust, <b>{@value DEFAULT_Y_ADJUST}</b>.
	 */
	public static final int DEFAULT_Y_ADJUST = 0;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		ENABLED("enabled"),
		BACKGROUND_COLOR("backgroundColor"),
		FONT_SIZE("fontSize"),
		FONT_STYLE("fontStyle"),
		FONT_COLOR("fontColor"),
		FONT_FAMILY("fontFamily"),
		X_PADDING("xPadding"),
		Y_PADDING("yPadding"),
		CORNER_RADIUS("cornerRadius"),
		POSITION("position"),
		X_ADJUST("xAdjust"),
		Y_ADJUST("yAdjust"),
		CONTENT("content");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use into native object.
		 * 
		 * @param value value of property name
		 */
		private Property(String value) {
			this.value = value;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.Key#value()
		 */
		@Override
		public String value() {
			return value;
		}

	}

	/**
	 * To avoid any instantiation because is added into all {@link LineAnnotation}.
	 */
	LineLabel() {
		super();
	}

	/**
	 * Sets <code>true</code> whether the label is enabled and should be displayed.
	 * 
	 * @param enabled <code>true</code> whether the label is enabled and should be displayed
	 */
	public void setEnabled(boolean enabled) {
		setValue(Property.ENABLED, enabled);
	}

	/**
	 * Returns <code>true</code> whether the label is enabled and should be displayed.
	 * 
	 * @return <code>true</code> whether the label is enabled and should be displayed
	 */
	@Override
	public boolean isEnabled() {
		return getValue(Property.ENABLED, IsDefaultsLineLabel.super.isEnabled());
	}

	/**
	 * Sets the background color of label.
	 * 
	 * @param backgroundColor the background color of label
	 */
	public void setBackgroundColor(IsColor backgroundColor) {
		setBackgroundColor(checkValue(backgroundColor));
	}

	/**
	 * Sets the background color of label as string.
	 * 
	 * @param backgroundColor the background color of label
	 */
	public void setBackgroundColor(String backgroundColor) {
		setValue(Property.BACKGROUND_COLOR, backgroundColor);
	}

	/**
	 * Returns the background color of label.
	 * 
	 * @return the background color of label
	 */
	@Override
	public String getBackgroundColorAsString() {
		return getValue(Property.BACKGROUND_COLOR, IsDefaultsLineLabel.super.getBackgroundColorAsString());
	}

	/**
	 * Returns the background color of label.
	 * 
	 * @return backgroundColor the background color of label
	 */
	public IsColor getBackgroundColor() {
		return ColorBuilder.parse(getBackgroundColorAsString());
	}

	/**
	 * Sets the font size of text.
	 * 
	 * @param fontSize the font size of text
	 */
	public void setFontSize(int fontSize) {
		setValue(Property.FONT_SIZE, fontSize);
	}

	/**
	 * Returns the font size of text.
	 * 
	 * @return the font size of text
	 */
	@Override
	public int getFontSize() {
		return getValue(Property.FONT_SIZE, IsDefaultsLineLabel.super.getFontSize());
	}

	/**
	 * Sets the font style of text.
	 * 
	 * @param fontStyle the font style of text
	 */
	public void setFontStyle(FontStyle fontStyle) {
		setValue(Property.FONT_STYLE, fontStyle);
	}

	/**
	 * Returns the font style of text.
	 * 
	 * @return the font style of text
	 */
	@Override
	public FontStyle getFontStyle() {
		return getValue(Property.FONT_STYLE, FontStyle.class, IsDefaultsLineLabel.super.getFontStyle());
	}

	/**
	 * Sets the font color of text.
	 * 
	 * @param fontColor the font color of text
	 */
	public void setFontColor(IsColor fontColor) {
		setFontColor(checkValue(fontColor));
	}

	/**
	 * Sets the font color of text as string.
	 * 
	 * @param fontColor the font color of text
	 */
	public void setFontColor(String fontColor) {
		setValue(Property.FONT_COLOR, fontColor);
	}

	/**
	 * Returns the font color of text as string.
	 * 
	 * @return the font color of text
	 */
	@Override
	public String getFontColorAsString() {
		return getValue(Property.FONT_COLOR, IsDefaultsLineLabel.super.getFontColorAsString());
	}

	/**
	 * Returns the font color of text.
	 * 
	 * @return the font color of text
	 */
	public IsColor getFontColor() {
		return ColorBuilder.parse(getFontColorAsString());
	}

	/**
	 * Sets the font family of text.
	 * 
	 * @param fontFamily the font family of text
	 */
	public void setFontFamily(String fontFamily) {
		setValue(Property.FONT_FAMILY, fontFamily);
	}

	/**
	 * Returns the font family of text.
	 * 
	 * @return the font family of text
	 */
	@Override
	public String getFontFamily() {
		return getValue(Property.FONT_FAMILY, IsDefaultsLineLabel.super.getFontFamily());
	}

	/**
	 * Sets the padding of label to add left and right.
	 * 
	 * @param xPadding the padding of label to add left and right
	 */
	public void setXPadding(int xPadding) {
		setValue(Property.X_PADDING, xPadding);
	}

	/**
	 * Returns the padding of label to add left and right.
	 * 
	 * @return the padding of label to add left and right
	 */
	@Override
	public int getXPadding() {
		return getValue(Property.X_PADDING, IsDefaultsLineLabel.super.getXPadding());
	}

	/**
	 * Sets the padding of label to add top and bottom.
	 * 
	 * @param yPadding the padding of label to add top and bottom
	 */
	public void setYPadding(int yPadding) {
		setValue(Property.Y_PADDING, yPadding);
	}

	/**
	 * Returns the padding of label to add top and bottom.
	 * 
	 * @return the padding of label to add top and bottom
	 */
	@Override
	public int getYPadding() {
		return getValue(Property.Y_PADDING, IsDefaultsLineLabel.super.getYPadding());
	}

	/**
	 * Sets the radius of label rectangle.
	 * 
	 * @param cornerRadius the radius of label rectangle
	 */
	public void setCornerRadius(double cornerRadius) {
		setValue(Property.CORNER_RADIUS, cornerRadius);
	}

	/**
	 * Returns the radius of label rectangle.
	 * 
	 * @return the radius of label rectangle
	 */
	@Override
	public double getCornerRadius() {
		return getValue(Property.CORNER_RADIUS, IsDefaultsLineLabel.super.getCornerRadius());
	}

	/**
	 * Sets the anchor position of label on line.
	 * 
	 * @param position the anchor position of label on line
	 */
	public void setPosition(LineLabelPosition position) {
		setValue(Property.POSITION, position);
	}

	/**
	 * Returns the anchor position of label on line.
	 * 
	 * @return the anchor position of label on line
	 */
	@Override
	public LineLabelPosition getPosition() {
		return getValue(Property.POSITION, LineLabelPosition.class, IsDefaultsLineLabel.super.getPosition());
	}

	/**
	 * Sets the adjustment along x-axis (left-right) of label relative to above number (can be negative).<br>
	 * For horizontal lines positioned left or right, negative values move the label toward the edge, and positive values toward
	 * the center.
	 * 
	 * @param xAdjust the adjustment along x-axis (left-right) of label
	 */
	public void setXAdjust(int xAdjust) {
		setValue(Property.X_ADJUST, xAdjust);
	}

	/**
	 * Returns the adjustment along x-axis (left-right) of label relative to above number (can be negative).<br>
	 * For horizontal lines positioned left or right, negative values move the label toward the edge, and positive values toward
	 * the center.
	 * 
	 * @return the adjustment along x-axis (left-right) of label
	 */
	@Override
	public int getXAdjust() {
		return getValue(Property.X_ADJUST, IsDefaultsLineLabel.super.getXAdjust());
	}

	/**
	 * Sets the adjustment along y-axis (top-bottom) of label relative to above number (can be negative).<br>
	 * For vertical lines positioned top or bottom, negative values move the label toward the edge, and positive values toward
	 * the center.
	 * 
	 * @param yAdjust the adjustment along y-axis (top-bottom) of label
	 */
	public void setYAdjust(int yAdjust) {
		setValue(Property.Y_ADJUST, yAdjust);
	}

	/**
	 * Returns the adjustment along y-axis (top-bottom) of label relative to above number (can be negative).<br>
	 * For vertical lines positioned top or bottom, negative values move the label toward the edge, and positive values toward
	 * the center.
	 * 
	 * @return the adjustment along y-axis (top-bottom) of label
	 */
	@Override
	public int getYAdjust() {
		return getValue(Property.Y_ADJUST, IsDefaultsLineLabel.super.getYAdjust());
	}

	/**
	 * Sets the text to display in label.<br>
	 * Provide a list to display values on a new line.
	 * 
	 * @param content the text to display in label as multi-line values
	 */
	public void setContent(List<String> content) {
		setContent(content.toArray(new String[0]));
	}

	/**
	 * Sets the text to display in label.<br>
	 * Provide an array to display values on a new line.
	 * 
	 * @param content the text to display in label
	 */
	public void setContent(String... content) {
		setValueOrArray(Property.CONTENT, content);
	}

	/**
	 * Returns the text to display in label as list.
	 * 
	 * @return the text to display in label as list
	 */
	public List<String> getContent() {
		// reads as array
		// and returns it
		ArrayString array = getValueOrArray(Property.CONTENT, UndefinedValues.STRING);
		return ArrayListHelper.list(array);
	}

}
