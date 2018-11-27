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
package org.pepstock.charba.client.jsinterop.options;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.enums.InteractionMode;
import org.pepstock.charba.client.enums.TextAlign;
import org.pepstock.charba.client.enums.TooltipPosition;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultTooltips;

/**
 * Configuration element to set all attributes and features of the default tooltip.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Tooltips extends AbstractModel<Options, IsDefaultTooltips> implements IsDefaultTooltips{
	
	private final TooltipsCallbacks callbacks;
	
	/**
	 * Name of fields of JavaScript object.
	 */
	enum Property implements Key
	{
		// sub elements
		callbacks,
		// properties
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
		titleAlign,
		bodyFontFamily,
		bodyFontSize,
		bodyFontStyle,
		bodyFontColor,
		bodySpacing,
		bodyAlign,
		footerFontFamily,
		footerFontSize,
		footerFontStyle,
		footerFontColor,
		footerSpacing,
		footerMarginTop,
		footerAlign,
		xPadding,
		yPadding,
		caretPadding,
		caretSize,
		cornerRadius,
		multiKeyBackground,
		displayColors,
		borderColor,
		borderWidth,
	}
	
	Tooltips(Options options, Key childKey, IsDefaultTooltips defaultValues, NativeObject nativeObject) {
		super(options, childKey, defaultValues, nativeObject);
		this.callbacks = new TooltipsCallbacks(this, Property.callbacks, defaultValues, getValue(Property.callbacks));
	}

	/**
	 * @return the callbacks
	 */
	public TooltipsCallbacks getCallbacks() {
		return callbacks;
	}

	/**
	 * Sets if tooltips are enabled.
	 * 
	 * @param enabled if tooltips are enabled.
	 */
	public void setEnabled(boolean enabled) {
		setValue(Property.enabled, enabled);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns if tooltips are enabled.
	 * 
	 * @return if tooltips are enabled.. Default is true.
	 */
	public boolean isEnabled() {
		return getValue(Property.enabled, getDefaultValues().isEnabled());
	}

	/**
	 * Sets which elements appear in the tooltip.
	 * 
	 * @param mode which elements appear in the tooltip.
	 * @see org.pepstock.charba.client.enums.InteractionMode
	 */
	public void setMode(InteractionMode mode) {
		setValue(Property.mode, mode);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns which elements appear in the tooltip.
	 * 
	 * @return which elements appear in the tooltip. Default is
	 *         {@link org.pepstock.charba.client.enums.InteractionMode#nearest}.
	 * @see org.pepstock.charba.client.enums.InteractionMode
	 */
	public InteractionMode getMode() {
		return getValue(Property.mode, InteractionMode.class, getDefaultValues().getMode());
	}

	/**
	 * if true, the tooltip mode applies only when the mouse position intersects with an element. If false, the mode will be
	 * applied at all times.
	 * 
	 * @param intersect if true, the tooltip mode applies only when the mouse position intersects with an element. If false, the
	 *            mode will be applied at all times.
	 */
	public void setIntersect(boolean intersect) {
		setValue(Property.intersect, intersect);;
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * if true, the tooltip mode applies only when the mouse position intersects with an element. If false, the mode will be
	 * applied at all times.
	 * 
	 * @return if true, the tooltip mode applies only when the mouse position intersects with an element. If false, the mode
	 *         will be applied at all times. Default is true.
	 */
	public boolean isIntersect() {
		return getValue(Property.intersect, getDefaultValues().isIntersect());
	}

	/**
	 * Sets the mode for positioning the tooltip.
	 * 
	 * @param position the mode for positioning the tooltip.
	 * @see org.pepstock.charba.client.enums.TooltipPosition
	 */
	public void setPosition(TooltipPosition position) {
		setValue(Property.position, position);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the mode for positioning the tooltip.
	 * 
	 * @return mode for positioning the tooltip. Default is {@link org.pepstock.charba.client.enums.TooltipPosition#average}.
	 * @see org.pepstock.charba.client.enums.TooltipPosition
	 */
	public TooltipPosition getPosition() {
		return getValue(Property.position, TooltipPosition.class, getDefaultValues().getPosition());
	}

	/**
	 * Sets the background color of the tooltip.
	 * 
	 * @param backgroundColor background color of the tooltip.
	 */
	public void setBackgroundColor(IsColor backgroundColor) {
		setBackgroundColor(backgroundColor.toRGBA());
	}

	/**
	 * Sets the background color of the tooltip.
	 * 
	 * @param backgroundColor background color of the tooltip.
	 */
	public void setBackgroundColor(String backgroundColor) {
		setValue(Property.backgroundColor, backgroundColor);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the background color of the tooltip.
	 * 
	 * @return Background color of the tooltip. Default is "rgba(0,0,0,0.8)".
	 */
	public String getBackgroundColorAsString() {
		return getValue(Property.backgroundColor, getDefaultValues().getBackgroundColorAsString());
	}

	/**
	 * Returns the background color of the tooltip.
	 * 
	 * @return Background color of the tooltip. Default is "rgba(0,0,0,0.8)".
	 */
	public IsColor getBackgroundColor() {
		return ColorBuilder.parse(getBackgroundColorAsString());
	}

	/**
	 * Sets the title font.
	 * 
	 * @param titleFontFamily title font.
	 */
	public void setTitleFontFamily(String titleFontFamily) {
		setValue(Property.titleFontFamily, titleFontFamily);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the title font.
	 * 
	 * @return the title font. Default is {@link org.pepstock.charba.client.defaults.global.Options#getDefaultFontFamily()}.
	 */
	public String getTitleFontFamily() {
		return getValue(Property.titleFontFamily, getDefaultValues().getTitleFontFamily());
	}

	/**
	 * Sets the title font size.
	 * 
	 * @param titleFontSize Title font size.
	 */
	public void setTitleFontSize(int titleFontSize) {
		setValue(Property.titleFontSize, titleFontSize);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the title font size.
	 * 
	 * @return Title font size. Default is {@link org.pepstock.charba.client.defaults.global.Options#getDefaultFontSize()}.
	 */
	public int getTitleFontSize() {
		return getValue(Property.titleFontSize, getDefaultValues().getTitleFontSize());
	}

	/**
	 * Sets the title font style.
	 * 
	 * @param titleFontStyle title font style.
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public void setTitleFontStyle(FontStyle titleFontStyle) {
		setValue(Property.titleFontStyle, titleFontStyle);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the title font style.
	 * 
	 * @return title font style. Default is {@link org.pepstock.charba.client.enums.FontStyle#bold}.
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public FontStyle getTitleFontStyle() {
		return getValue(Property.titleFontStyle, FontStyle.class, getDefaultValues().getTitleFontStyle());
	}

	/**
	 * Sets the title alignment.
	 * 
	 * @param align title alignment.
	 * @see org.pepstock.charba.client.enums.TextAlign
	 */
	public void setTitleAlign(TextAlign align) {
		setValue(Property.titleAlign, align);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the title alignment.
	 * 
	 * @return title alignment. Default is {@link org.pepstock.charba.client.enums.TextAlign#left}.
	 * @see org.pepstock.charba.client.enums.TextAlign
	 */
	public TextAlign getTitleAlign() {
		return getValue(Property.titleAlign, TextAlign.class, getDefaultValues().getTitleAlign());
	}

	/**
	 * Sets the title font color.
	 * 
	 * @param titleFontColor title font color.
	 */
	public void setTitleFontColor(IsColor titleFontColor) {
		setTitleFontColor(titleFontColor.toRGBA());
	}

	/**
	 * Sets the title font color.
	 * 
	 * @param titleFontColor title font color.
	 */
	public void setTitleFontColor(String titleFontColor) {
		setValue(Property.titleFontColor, titleFontColor);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the title font color.
	 * 
	 * @return title font color.Default is '#fff'.
	 */
	public String getTitleFontColorAsString() {
		return getValue(Property.titleFontColor, getDefaultValues().getTitleFontColorAsString());
	}

	/**
	 * Returns the title font color.
	 * 
	 * @return title font color.Default is '#fff'.
	 */
	public IsColor getTitleFontColor() {
		return ColorBuilder.parse(getTitleFontColorAsString());
	}

	/**
	 * Sets the spacing to add to top and bottom of each title line.
	 * 
	 * @param titleSpacing spacing to add to top and bottom of each title line.
	 */
	public void setTitleSpacing(int titleSpacing) {
		setValue(Property.titleSpacing, titleSpacing);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the spacing to add to top and bottom of each title line.
	 * 
	 * @return spacing to add to top and bottom of each title line. Default is 2.
	 */
	public int getTitleSpacing() {
		return getValue(Property.titleSpacing, getDefaultValues().getTitleSpacing());
	}

	/**
	 * Sets the margin to add on bottom of title section.
	 * 
	 * @param titleMarginBottom margin to add on bottom of title section.
	 */
	public void setTitleMarginBottom(int titleMarginBottom) {
		setValue(Property.titleMarginBottom, titleMarginBottom);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the margin to add on bottom of title section.
	 * 
	 * @return margin to add on bottom of title section. Default is 6.
	 */
	public int getTitleMarginBottom() {
		return getValue(Property.titleMarginBottom, getDefaultValues().getTitleMarginBottom());
	}

	/**
	 * Sets the body line font.
	 * 
	 * @param bodyFontFamily body line font.
	 */
	public void setBodyFontFamily(String bodyFontFamily) {
		setValue(Property.bodyFontFamily, bodyFontFamily);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the body line font.
	 * 
	 * @return body line font. Default is {@link org.pepstock.charba.client.defaults.global.Options#getDefaultFontFamily()}.
	 */
	public String getBodyFontFamily() {
		return getValue(Property.bodyFontFamily, getDefaultValues().getBodyFontFamily());
	}

	/**
	 * Sets the body font size.
	 * 
	 * @param bodyFontSize body font size.
	 */
	public void setBodyFontSize(int bodyFontSize) {
		setValue(Property.bodyFontSize, bodyFontSize);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the body font size.
	 * 
	 * @return body font size. Default is {@link org.pepstock.charba.client.defaults.global.Options#getDefaultFontSize()}.
	 */
	public int getBodyFontSize() {
		return getValue(Property.bodyFontSize, getDefaultValues().getBodyFontSize());
	}

	/**
	 * Sets the body font style.
	 * 
	 * @param bodyFontStyle body font style.
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public void setBodyFontStyle(FontStyle bodyFontStyle) {
		setValue(Property.bodyFontStyle, bodyFontStyle);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the body font style.
	 * 
	 * @return body font style. Default is {@link org.pepstock.charba.client.enums.FontStyle#normal}.
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public FontStyle getBodyFontStyle() {
		return getValue(Property.bodyFontStyle, FontStyle.class, getDefaultValues().getBodyFontStyle());
	}

	/**
	 * Sets the body alignment.
	 * 
	 * @param align body alignment.
	 * @see org.pepstock.charba.client.enums.TextAlign
	 */
	public void setBodyAlign(TextAlign align) {
		setValue(Property.bodyAlign, align);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the body alignment.
	 * 
	 * @return body alignment. Default is {@link org.pepstock.charba.client.enums.TextAlign#left}.
	 * @see org.pepstock.charba.client.enums.TextAlign
	 */
	public TextAlign getBodyAlign() {
		return getValue(Property.bodyAlign, TextAlign.class, getDefaultValues().getBodyAlign());
	}

	/**
	 * Sets the body font color.
	 * 
	 * @param bodyFontColor body font color.
	 */
	public void setBodyFontColor(IsColor bodyFontColor) {
		setBodyFontColor(bodyFontColor.toRGBA());
	}

	/**
	 * Sets the body font color.
	 * 
	 * @param bodyFontColor body font color.
	 */
	public void setBodyFontColor(String bodyFontColor) {
		setValue(Property.bodyFontColor, bodyFontColor);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the body font color.
	 * 
	 * @return body font color. Default is '#fff'.
	 */
	public String getBodyFontColorAsString() {
		return getValue(Property.bodyFontColor, getDefaultValues().getBodyFontColorAsString());
	}

	/**
	 * Returns the body font color.
	 * 
	 * @return body font color. Default is '#fff'.
	 */
	public IsColor getBodyFontColor() {
		return ColorBuilder.parse(getBodyFontColorAsString());
	}

	/**
	 * Sets the spacing to add to top and bottom of each tooltip item.
	 * 
	 * @param bodySpacing spacing to add to top and bottom of each tooltip item.
	 */
	public void setBodySpacing(int bodySpacing) {
		setValue(Property.bodySpacing, bodySpacing);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the spacing to add to top and bottom of each tooltip item.
	 * 
	 * @return spacing to add to top and bottom of each tooltip item. Default is 2.
	 */
	public int getBodySpacing() {
		return getValue(Property.bodySpacing, getDefaultValues().getBodySpacing());
	}

	/**
	 * Sets the footer font.
	 * 
	 * @param footerFontFamily footer font.
	 */
	public void setFooterFontFamily(String footerFontFamily) {
		setValue(Property.footerFontFamily, footerFontFamily);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the footer font.
	 * 
	 * @return footer font. Default is {@link org.pepstock.charba.client.defaults.global.Options#getDefaultFontFamily()}.
	 */
	public String getFooterFontFamily() {
		return getValue(Property.footerFontFamily, getDefaultValues().getFooterFontFamily());
	}

	/**
	 * Sets the footer font size.
	 * 
	 * @param footerFontSize footer font size.
	 */
	public void setFooterFontSize(int footerFontSize) {
		setValue(Property.footerFontSize, footerFontSize);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the footer font size.
	 * 
	 * @return footer font size. Default is {@link org.pepstock.charba.client.defaults.global.Options#getDefaultFontSize()}.
	 */
	public int getFooterFontSize() {
		return getValue(Property.footerFontSize, getDefaultValues().getFooterFontSize());
	}

	/**
	 * Sets the footer font style.
	 * 
	 * @param footerFontStyle the footer font style.
	 */
	public void setFooterFontStyle(FontStyle footerFontStyle) {
		setValue(Property.footerFontStyle, footerFontStyle);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the footer font style.
	 * 
	 * @return footer font style. Default is {@link org.pepstock.charba.client.enums.FontStyle#normal}.
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public FontStyle getFooterFontStyle() {
		return getValue(Property.footerFontStyle, FontStyle.class, getDefaultValues().getFooterFontStyle());
	}

	/**
	 * Sets the footer alignment.
	 * 
	 * @param align footer alignment.
	 * @see org.pepstock.charba.client.enums.TextAlign
	 */
	public void setFooterAlign(TextAlign align) {
		setValue(Property.footerAlign, align);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the body alignment.
	 * 
	 * @return footer alignment. Default is {@link org.pepstock.charba.client.enums.TextAlign#left}.
	 * @see org.pepstock.charba.client.enums.TextAlign
	 */
	public TextAlign getFooterAlign() {
		return getValue(Property.footerAlign, TextAlign.class, getDefaultValues().getFooterAlign());
	}

	/**
	 * Sets the footer font color.
	 * 
	 * @param footerFontColor footer font color.
	 */
	public void setFooterFontColor(IsColor footerFontColor) {
		setFooterFontColor(footerFontColor.toRGBA());
	}

	/**
	 * Sets the footer font color.
	 * 
	 * @param footerFontColor footer font color.
	 */
	public void setFooterFontColor(String footerFontColor) {
		setValue(Property.footerFontColor, footerFontColor);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the footer font color.
	 * 
	 * @return footer font color. Default is '#fff'.
	 */
	public String getFooterFontColorAsString() {
		return getValue(Property.footerFontColor, getDefaultValues().getFooterFontColorAsString());
	}

	/**
	 * Returns the footer font color.
	 * 
	 * @return footer font color. Default is '#fff'.
	 */
	public IsColor getFooterFontColor() {
		return ColorBuilder.parse(getFooterFontColorAsString());
	}
	
	/**
	 * Sets the spacing to add to top and bottom of each footer line.
	 * 
	 * @param footerSpacing spacing to add to top and bottom of each footer line.
	 */
	public void setFooterSpacing(int footerSpacing) {
		setValue(Property.footerSpacing, footerSpacing);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the spacing to add to top and bottom of each footer line.
	 * 
	 * @return spacing to add to top and bottom of each footer line. Default is 2.
	 */
	public int getFooterSpacing() {
		return getValue(Property.footerSpacing, getDefaultValues().getFooterSpacing());
	}

	/**
	 * Sets the margin to add before drawing the footer.
	 * 
	 * @param footerMarginTop margin to add before drawing the footer.
	 */
	public void setFooterMarginTop(int footerMarginTop) {
		setValue(Property.footerMarginTop, footerMarginTop);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the margin to add before drawing the footer.
	 * 
	 * @return margin to add before drawing the footer. Default is 6.
	 */
	public int getFooterMarginTop() {
		return getValue(Property.footerMarginTop, getDefaultValues().getFooterMarginTop());
	}

	/**
	 * Sets the padding to add on left and right of tooltip.
	 * 
	 * @param xPadding padding to add on left and right of tooltip.
	 */
	public void setXPadding(int xPadding) {
		setValue(Property.xPadding, xPadding);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the padding to add on left and right of tooltip.
	 * 
	 * @return padding to add on left and right of tooltip. Default is 6.
	 */
	public int getXPadding() {
		return getValue(Property.xPadding, getDefaultValues().getXPadding());
	}

	/**
	 * Sets the padding to add on top and bottom of tooltip.
	 * 
	 * @param yPadding padding to add on top and bottom of tooltip.
	 */
	public void setYPadding(int yPadding) {
		setValue(Property.yPadding, yPadding);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the padding to add on top and bottom of tooltip.
	 * 
	 * @return padding to add on top and bottom of tooltip. Default is 6.
	 */
	public int getYPadding() {
		return getValue(Property.yPadding, getDefaultValues().getYPadding());
	}

	/**
	 * Sets the extra distance to move the end of the tooltip arrow away from the tooltip point.
	 * 
	 * @param caretPadding extra distance to move the end of the tooltip arrow away from the tooltip point.
	 */
	public void setCaretPadding(int caretPadding) {
		setValue(Property.caretPadding, caretPadding);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the extra distance to move the end of the tooltip arrow away from the tooltip point.
	 * 
	 * @return extra distance to move the end of the tooltip arrow away from the tooltip point. Default is 2.
	 */
	public int getCaretPadding() {
		return getValue(Property.caretPadding, getDefaultValues().getCaretPadding());
	}

	/**
	 * Sets the size, in px, of the tooltip arrow.
	 * 
	 * @param caretSize size, in px, of the tooltip arrow.
	 */
	public void setCaretSize(int caretSize) {
		setValue(Property.caretSize, caretSize);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the size, in px, of the tooltip arrow.
	 * 
	 * @return size, in px, of the tooltip arrow. Default is 5.
	 */
	public int getCaretSize() {
		return getValue(Property.caretSize, getDefaultValues().getCaretSize());
	}

	/**
	 * Sets the radius of tooltip corner curves.
	 * 
	 * @param cornerRadius radius of tooltip corner curves.
	 */
	public void setCornerRadius(int cornerRadius) {
		setValue(Property.cornerRadius, cornerRadius);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the radius of tooltip corner curves.
	 * 
	 * @return radius of tooltip corner curves. Default is 6.
	 */
	public int getCornerRadius() {
		return getValue(Property.cornerRadius, getDefaultValues().getCornerRadius());
	}

	/**
	 * Sets the color to draw behind the colored boxes when multiple items are in the tooltip.
	 * 
	 * @param multiKeyBackground color to draw behind the colored boxes when multiple items are in the tooltip.
	 */
	public void setMultiKeyBackground(IsColor multiKeyBackground) {
		setMultiKeyBackground(multiKeyBackground.toRGBA());
	}

	/**
	 * Sets the color to draw behind the colored boxes when multiple items are in the tooltip.
	 * 
	 * @param multiKeyBackground color to draw behind the colored boxes when multiple items are in the tooltip.
	 */
	public void setMultiKeyBackground(String multiKeyBackground) {
		setValue(Property.multiKeyBackground, multiKeyBackground);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the color to draw behind the colored boxes when multiple items are in the tooltip.
	 * 
	 * @return color to draw behind the colored boxes when multiple items are in the tooltip. Default is '#fff'.
	 */
	public String getMultiKeyBackgroundAsString() {
		return getValue(Property.multiKeyBackground, getDefaultValues().getMultiKeyBackgroundAsString());
	}

	/**
	 * Returns the color to draw behind the colored boxes when multiple items are in the tooltip.
	 * 
	 * @return color to draw behind the colored boxes when multiple items are in the tooltip. Default is '#fff'.
	 */
	public IsColor getMultiKeyBackground() {
		return ColorBuilder.parse(getMultiKeyBackgroundAsString());
	}

	/**
	 * If true, color boxes are shown in the tooltip.
	 * 
	 * @param displayColors if true, color boxes are shown in the tooltip.
	 */
	public void setDisplayColors(boolean displayColors) {
		setValue(Property.displayColors, displayColors);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * If true, color boxes are shown in the tooltip.
	 * 
	 * @return if true, color boxes are shown in the tooltip. Default is true.
	 */
	public boolean isDisplayColors() {
		return getValue(Property.displayColors, getDefaultValues().isDisplayColors());
	}

	/**
	 * Sets the color of the border.
	 * 
	 * @param borderColor color of the border.
	 */
	public void setBorderColor(IsColor borderColor) {
		setBorderColor(borderColor.toRGBA());
	}

	/**
	 * Sets the color of the border.
	 * 
	 * @param borderColor color of the border.
	 */
	public void setBorderColor(String borderColor) {
		setValue(Property.borderColor, borderColor);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the color of the border.
	 * 
	 * @return color of the border. Default is 'rgba(0,0,0,0)'.
	 */
	public String getBorderColorAsString() {
		return getValue(Property.borderColor, getDefaultValues().getBorderColorAsString());
	}

	/**
	 * Returns the color of the border.
	 * 
	 * @return color of the border. Default is 'rgba(0,0,0,0)'.
	 */
	public IsColor getBorderColor() {
		return ColorBuilder.parse(getBorderColorAsString());
	}

	/**
	 * Sets the size of the border.
	 * 
	 * @param borderWidth size of the border.
	 */
	public void setBorderWidth(int borderWidth) {
		setValue(Property.borderWidth, borderWidth);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the size of the border.
	 * 
	 * @return size of the border. Default is 0.
	 */
	public int getBorderWidth() {
		return getValue(Property.borderWidth, getDefaultValues().getBorderWidth());
	}
}