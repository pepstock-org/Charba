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
package org.pepstock.charba.client.defaults.global;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.defaults.AbstractDefaultsItem;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.enums.InteractionMode;
import org.pepstock.charba.client.enums.TooltipPosition;

/**
 * Configuration element to set all attributes and features of the tooltip.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Tooltips extends AbstractDefaultsItem {

	private static final boolean DEFAULT_ENABLED = true;

	private static final boolean DEFAULT_INTERSECT = true;

	private static final String DEFAULT_BACKGROUND_COLOR = "rgba(0,0,0,0.8)";

	private static final String DEFAULT_TITLE_FONT_COLOR = "#fff";

	private static final int DEFAULT_TITLE_SPACING = 2;

	private static final int DEFAULT_TITLE_MARGIN_BOTTOM = 6;

	private static final String DEFAULT_BODY_FONT_COLOR = "#fff";

	private static final int DEFAULT_BODY_SPACING = 2;

	private static final String DEFAULT_FOOTER_FONT_COLOR = "#fff";

	private static final int DEFAULT_FOOTER_SPACING = 2;

	private static final int DEFAULT_FOOTER_MARGIN_TOP = 6;

	private static final int DEFAULT_X_PADDING = 6;

	private static final int DEFAULT_Y_PADDING = 6;

	private static final int DEFAULT_CARET_PADDING = 2;

	private static final int DEFAULT_CARET_SIZE = 5;

	private static final int DEFAULT_CORNER_RADIUS = 6;

	private static final String DEFAULT_MULTI_KEY_BACKGROUND = "#fff";

	private static final boolean DEFAULT_DISPLAY_COLORS = true;

	private static final String DEFAULT_BORDER_COLOR = "rgba(0,0,0,0)";

	private static final int DEFAULT_BORDER_WIDTH = 0;

	// FIXME
	// "titleAlign": "left",
	// "bodyAlign": "left",
	// "footerAlign": "left",

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		enabled,
		mode,
		intersect,
		position,
		backgroundColor,
		titleFontFamily,
		titleFontSize,
		titleFontStyle,
		titleFontColor,
		titleSpacing,
		titleMarginBottom,
		bodyFontFamily,
		bodyFontSize,
		bodyFontStyle,
		bodyFontColor,
		bodySpacing,
		footerFontFamily,
		footerFontSize,
		footerFontStyle,
		footerFontColor,
		footerSpacing,
		footerMarginTop,
		xPadding,
		yPadding,
		caretPadding,
		caretSize,
		cornerRadius,
		multiKeyBackground,
		displayColors,
		borderColor,
		borderWidth
	}

	/**
	 * Builds the object storing the chart instance.<br>
	 * 
	 */
	public Tooltips(GenericJavaScriptObject javaScriptObject) {
		super(javaScriptObject);
	}

	/**
	 * Sets if tooltips are enabled.
	 * 
	 * @param enabled if tooltips are enabled.
	 */
	public void setEnabled(boolean enabled) {
		setValue(Property.enabled, enabled);
	}

	/**
	 * Returns if tooltips are enabled.
	 * 
	 * @return if tooltips are enabled.. Default is true.
	 */
	public boolean isEnabled() {
		return getValue(Property.enabled, DEFAULT_ENABLED);
	}

	/**
	 * Sets which elements appear in the tooltip.
	 * 
	 * @param mode which elements appear in the tooltip.
	 * @see org.pepstock.charba.client.enums.InteractionMode
	 */
	public void setMode(InteractionMode mode) {
		setValue(Property.mode, mode);
	}

	/**
	 * Returns which elements appear in the tooltip.
	 * 
	 * @return which elements appear in the tooltip. Default is
	 *         {@link org.pepstock.charba.client.enums.InteractionMode#nearest}.
	 * @see org.pepstock.charba.client.enums.InteractionMode
	 */
	public InteractionMode getMode() {
		return getValue(Property.mode, InteractionMode.class, InteractionMode.nearest);
	}

	/**
	 * if true, the tooltip mode applies only when the mouse position intersects with an element. If false, the mode will be
	 * applied at all times.
	 * 
	 * @param intersect if true, the tooltip mode applies only when the mouse position intersects with an element. If false, the
	 *            mode will be applied at all times.
	 */
	public void setIntersect(boolean intersect) {
		setValue(Property.intersect, intersect);
	}

	/**
	 * if true, the tooltip mode applies only when the mouse position intersects with an element. If false, the mode will be
	 * applied at all times.
	 * 
	 * @return if true, the tooltip mode applies only when the mouse position intersects with an element. If false, the mode
	 *         will be applied at all times. Default is true.
	 */
	public boolean isIntersect() {
		return getValue(Property.intersect, DEFAULT_INTERSECT);
	}

	/**
	 * Sets the mode for positioning the tooltip.
	 * 
	 * @param position the mode for positioning the tooltip.
	 * @see org.pepstock.charba.client.enums.TooltipPosition
	 */
	public void setPosition(TooltipPosition position) {
		setValue(Property.position, position);
	}

	/**
	 * Returns the mode for positioning the tooltip.
	 * 
	 * @return mode for positioning the tooltip. Default is {@link org.pepstock.charba.client.enums.TooltipPosition#average}.
	 * @see org.pepstock.charba.client.enums.TooltipPosition
	 */
	public TooltipPosition getPosition() {
		return getValue(Property.position, TooltipPosition.class, TooltipPosition.average);
	}

	/**
	 * Sets the background color of the tooltip.
	 * 
	 * @param backgroundColor background color of the tooltip.
	 */
	public void setBackgroundColor(String backgroundColor) {
		setValue(Property.backgroundColor, backgroundColor);
	}

	/**
	 * Returns the background color of the tooltip.
	 * 
	 * @return Background color of the tooltip. Default is "rgba(0,0,0,0.8)".
	 */
	public String getBackgroundColor() {
		return getValue(Property.backgroundColor, DEFAULT_BACKGROUND_COLOR);
	}

	/**
	 * Sets the title font.
	 * 
	 * @param titleFontFamily title font.
	 */
	public void setTitleFontFamily(String titleFontFamily) {
		setValue(Property.titleFontFamily, titleFontFamily);
	}

	/**
	 * Returns the title font.
	 * 
	 * @return the title font. Default is "'Helvetica Neue', 'Helvetica', 'Arial', sans-serif".
	 */
	public String getTitleFontFamily() {
		return getValue(Property.titleFontFamily, Defaults.getGlobal().getDefaultFontFamily());
	}

	/**
	 * Sets the title font size.
	 * 
	 * @param titleFontSize Title font size.
	 */
	public void setTitleFontSize(int titleFontSize) {
		setValue(Property.titleFontSize, titleFontSize);
	}

	/**
	 * Returns the title font size.
	 * 
	 * @return Title font size. Default is 12.
	 */
	public int getTitleFontSize() {
		return getValue(Property.titleFontSize, Defaults.getGlobal().getDefaultFontSize());
	}

	/**
	 * Sets the title font style.
	 * 
	 * @param titleFontStyle title font style.
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public void setTitleFontStyle(FontStyle titleFontStyle) {
		setValue(Property.titleFontStyle, titleFontStyle);
	}

	/**
	 * Returns the title font style.
	 * 
	 * @return title font style. Default is {@link org.pepstock.charba.client.enums.FontStyle#bold}.
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public FontStyle getTitleFontStyle() {
		return getValue(Property.titleFontStyle, FontStyle.class, FontStyle.bold);
	}

	/**
	 * Sets the title font color.
	 * 
	 * @param titleFontColor title font color.
	 */
	public void setTitleFontColor(String titleFontColor) {
		setValue(Property.titleFontColor, titleFontColor);
	}

	/**
	 * Returns the title font color.
	 * 
	 * @return title font color.Default is '#fff'.
	 */
	public String getTitleFontColor() {
		return getValue(Property.titleFontColor, DEFAULT_TITLE_FONT_COLOR);
	}

	/**
	 * Sets the spacing to add to top and bottom of each title line.
	 * 
	 * @param titleSpacing spacing to add to top and bottom of each title line.
	 */
	public void setTitleSpacing(int titleSpacing) {
		setValue(Property.titleSpacing, titleSpacing);
	}

	/**
	 * Returns the spacing to add to top and bottom of each title line.
	 * 
	 * @return spacing to add to top and bottom of each title line. Default is 2.
	 */
	public int getTitleSpacing() {
		return getValue(Property.titleSpacing, DEFAULT_TITLE_SPACING);
	}

	/**
	 * Sets the margin to add on bottom of title section.
	 * 
	 * @param titleMarginBottom margin to add on bottom of title section.
	 */
	public void setTitleMarginBottom(int titleMarginBottom) {
		setValue(Property.titleMarginBottom, titleMarginBottom);
	}

	/**
	 * Returns the margin to add on bottom of title section.
	 * 
	 * @return margin to add on bottom of title section. Default is 6.
	 */
	public int getTitleMarginBottom() {
		return getValue(Property.titleMarginBottom, DEFAULT_TITLE_MARGIN_BOTTOM);
	}

	/**
	 * Sets the body line font.
	 * 
	 * @param bodyFontFamily body line font.
	 */
	public void setBodyFontFamily(String bodyFontFamily) {
		setValue(Property.bodyFontFamily, bodyFontFamily);
	}

	/**
	 * Returns the body line font.
	 * 
	 * @return body line font. Default is "'Helvetica Neue', 'Helvetica', 'Arial', sans-serif".
	 */
	public String getBodyFontFamily() {
		return getValue(Property.bodyFontFamily, Defaults.getGlobal().getDefaultFontFamily());
	}

	/**
	 * Sets the body font size.
	 * 
	 * @param bodyFontSize body font size.
	 */
	public void setBodyFontSize(int bodyFontSize) {
		setValue(Property.bodyFontSize, bodyFontSize);
	}

	/**
	 * Returns the body font size.
	 * 
	 * @return body font size. Default is 12.
	 */
	public int getBodyFontSize() {
		return getValue(Property.bodyFontSize, Defaults.getGlobal().getDefaultFontSize());
	}

	/**
	 * Sets the body font style.
	 * 
	 * @param bodyFontStyle body font style.
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public void setBodyFontStyle(FontStyle bodyFontStyle) {
		setValue(Property.bodyFontStyle, bodyFontStyle);
	}

	/**
	 * Returns the body font style.
	 * 
	 * @return body font style. Default is {@link org.pepstock.charba.client.enums.FontStyle#normal}.
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public FontStyle getBodyFontStyle() {
		return getValue(Property.bodyFontStyle, FontStyle.class, FontStyle.normal);
	}

	/**
	 * Sets the body font color.
	 * 
	 * @param bodyFontColor body font color.
	 */
	public void setBodyFontColor(String bodyFontColor) {
		setValue(Property.bodyFontColor, bodyFontColor);
	}

	/**
	 * Returns the body font color.
	 * 
	 * @return body font color. Default is '#fff'.
	 */
	public String getBodyFontColor() {
		return getValue(Property.bodyFontColor, DEFAULT_BODY_FONT_COLOR);
	}

	/**
	 * Sets the spacing to add to top and bottom of each tooltip item.
	 * 
	 * @param bodySpacing spacing to add to top and bottom of each tooltip item.
	 */
	public void setBodySpacing(int bodySpacing) {
		setValue(Property.bodySpacing, bodySpacing);
	}

	/**
	 * Returns the spacing to add to top and bottom of each tooltip item.
	 * 
	 * @return spacing to add to top and bottom of each tooltip item. Default is 2.
	 */
	public int getBodySpacing() {
		return getValue(Property.bodySpacing, DEFAULT_BODY_SPACING);
	}

	/**
	 * Sets the footer font.
	 * 
	 * @param footerFontFamily footer font.
	 */
	public void setFooterFontFamily(String footerFontFamily) {
		setValue(Property.footerFontFamily, footerFontFamily);
	}

	/**
	 * Returns the footer font.
	 * 
	 * @return footer font. Default is "'Helvetica Neue', 'Helvetica', 'Arial', sans-serif".
	 */
	public String getFooterFontFamily() {
		return getValue(Property.footerFontFamily, Defaults.getGlobal().getDefaultFontFamily());
	}

	/**
	 * Sets the footer font size.
	 * 
	 * @param footerFontSize footer font size.
	 */
	public void setFooterFontSize(int footerFontSize) {
		setValue(Property.footerFontSize, footerFontSize);
	}

	/**
	 * Returns the footer font size.
	 * 
	 * @return footer font size. Default is 12.
	 */
	public int getFooterFontSize() {
		return getValue(Property.footerFontSize, Defaults.getGlobal().getDefaultFontSize());
	}

	/**
	 * Sets the footer font style.
	 * 
	 * @param footerFontStyle the footer font style.
	 */
	public void setFooterFontStyle(FontStyle footerFontStyle) {
		setValue(Property.footerFontStyle, footerFontStyle);
	}

	/**
	 * Returns the footer font style.
	 * 
	 * @return footer font style. Default is {@link org.pepstock.charba.client.enums.FontStyle#normal}.
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public FontStyle getFooterFontStyle() {
		return getValue(Property.footerFontStyle, FontStyle.class, FontStyle.bold);
	}

	/**
	 * Sets the footer font color.
	 * 
	 * @param footerFontColor footer font color.
	 */
	public void setFooterFontColor(String footerFontColor) {
		setValue(Property.footerFontColor, footerFontColor);
	}

	/**
	 * Returns the footer font color.
	 * 
	 * @return footer font color. Default is '#fff'.
	 */
	public String getFooterFontColor() {
		return getValue(Property.footerFontColor, DEFAULT_FOOTER_FONT_COLOR);
	}

	/**
	 * Sets the spacing to add to top and bottom of each footer line.
	 * 
	 * @param footerSpacing spacing to add to top and bottom of each footer line.
	 */
	public void setFooterSpacing(int footerSpacing) {
		setValue(Property.footerSpacing, footerSpacing);
	}

	/**
	 * Returns the spacing to add to top and bottom of each footer line.
	 * 
	 * @return spacing to add to top and bottom of each footer line. Default is 2.
	 */
	public int getFooterSpacing() {
		return getValue(Property.footerSpacing, DEFAULT_FOOTER_SPACING);
	}

	/**
	 * Sets the margin to add before drawing the footer.
	 * 
	 * @param footerMarginTop margin to add before drawing the footer.
	 */
	public void setFooterMarginTop(int footerMarginTop) {
		setValue(Property.footerMarginTop, footerMarginTop);
	}

	/**
	 * Returns the margin to add before drawing the footer.
	 * 
	 * @return margin to add before drawing the footer. Default is 6.
	 */
	public int getFooterMarginTop() {
		return getValue(Property.footerMarginTop, DEFAULT_FOOTER_MARGIN_TOP);
	}

	/**
	 * Sets the padding to add on left and right of tooltip.
	 * 
	 * @param xPadding padding to add on left and right of tooltip.
	 */
	public void setXPadding(int xPadding) {
		setValue(Property.xPadding, xPadding);
	}

	/**
	 * Returns the padding to add on left and right of tooltip.
	 * 
	 * @return padding to add on left and right of tooltip. Default is 6.
	 */
	public int getXPadding() {
		return getValue(Property.xPadding, DEFAULT_X_PADDING);
	}

	/**
	 * Sets the padding to add on top and bottom of tooltip.
	 * 
	 * @param yPadding padding to add on top and bottom of tooltip.
	 */
	public void setYPadding(int yPadding) {
		setValue(Property.yPadding, yPadding);
	}

	/**
	 * Returns the padding to add on top and bottom of tooltip.
	 * 
	 * @return padding to add on top and bottom of tooltip. Default is 6.
	 */
	public int getYPadding() {
		return getValue(Property.yPadding, DEFAULT_Y_PADDING);
	}

	/**
	 * Sets the extra distance to move the end of the tooltip arrow away from the tooltip point.
	 * 
	 * @param caretPadding extra distance to move the end of the tooltip arrow away from the tooltip point.
	 */
	public void setCaretPadding(int caretPadding) {
		setValue(Property.caretPadding, caretPadding);
	}

	/**
	 * Returns the extra distance to move the end of the tooltip arrow away from the tooltip point.
	 * 
	 * @return extra distance to move the end of the tooltip arrow away from the tooltip point. Default is 2.
	 */
	public int getCaretPadding() {
		return getValue(Property.caretPadding, DEFAULT_CARET_PADDING);
	}

	/**
	 * Sets the size, in px, of the tooltip arrow.
	 * 
	 * @param caretSize size, in px, of the tooltip arrow.
	 */
	public void setCaretSize(int caretSize) {
		setValue(Property.caretSize, caretSize);
	}

	/**
	 * Returns the size, in px, of the tooltip arrow.
	 * 
	 * @return size, in px, of the tooltip arrow. Default is 5.
	 */
	public int getCaretSize() {
		return getValue(Property.caretSize, DEFAULT_CARET_SIZE);
	}

	/**
	 * Sets the radius of tooltip corner curves.
	 * 
	 * @param cornerRadius radius of tooltip corner curves.
	 */
	public void setCornerRadius(int cornerRadius) {
		setValue(Property.cornerRadius, cornerRadius);
	}

	/**
	 * Returns the radius of tooltip corner curves.
	 * 
	 * @return radius of tooltip corner curves. Default is 6.
	 */
	public int getCornerRadius() {
		return getValue(Property.cornerRadius, DEFAULT_CORNER_RADIUS);
	}

	/**
	 * Sets the color to draw behind the colored boxes when multiple items are in the tooltip.
	 * 
	 * @param multiKeyBackground color to draw behind the colored boxes when multiple items are in the tooltip.
	 */
	public void setMultiKeyBackground(String multiKeyBackground) {
		setValue(Property.multiKeyBackground, multiKeyBackground);
	}

	/**
	 * Returns the color to draw behind the colored boxes when multiple items are in the tooltip.
	 * 
	 * @return color to draw behind the colored boxes when multiple items are in the tooltip. Default is '#fff'.
	 */
	public String getMultiKeyBackground() {
		return getValue(Property.multiKeyBackground, DEFAULT_MULTI_KEY_BACKGROUND);
	}

	/**
	 * If true, color boxes are shown in the tooltip.
	 * 
	 * @param displayColors if true, color boxes are shown in the tooltip.
	 */
	public void setDisplayColors(boolean displayColors) {
		setValue(Property.displayColors, displayColors);
	}

	/**
	 * If true, color boxes are shown in the tooltip.
	 * 
	 * @return if true, color boxes are shown in the tooltip. Default is true.
	 */
	public boolean isDisplayColors() {
		return getValue(Property.displayColors, DEFAULT_DISPLAY_COLORS);
	}

	/**
	 * Sets the color of the border.
	 * 
	 * @param borderColor color of the border.
	 */
	public void setBorderColor(String borderColor) {
		setValue(Property.borderColor, borderColor);
	}

	/**
	 * Returns the color of the border.
	 * 
	 * @return color of the border. Default is 'rgba(0,0,0,0)'.
	 */
	public String getBorderColor() {
		return getValue(Property.borderColor, DEFAULT_BORDER_COLOR);
	}

	/**
	 * Sets the size of the border.
	 * 
	 * @param borderWidth size of the border.
	 */
	public void setBorderWidth(int borderWidth) {
		setValue(Property.borderWidth, borderWidth);
	}

	/**
	 * Returns the size of the border.
	 * 
	 * @return size of the border. Default is 0.
	 */
	public int getBorderWidth() {
		return getValue(Property.borderWidth, DEFAULT_BORDER_WIDTH);
	}
}