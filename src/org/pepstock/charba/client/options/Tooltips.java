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
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultTooltips;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.enums.InteractionMode;
import org.pepstock.charba.client.enums.IsTooltipPosition;
import org.pepstock.charba.client.enums.TextAlign;
import org.pepstock.charba.client.enums.TooltipPosition;
import org.pepstock.charba.client.positioner.Positioner;

/**
 * Configuration element to set all attributes and features of the default tooltip.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Tooltips extends AbstractHover<IsDefaultTooltips> implements IsDefaultTooltips {

	private final TooltipsCallbacks callbacks;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		CALLBACKS("callbacks"),
		ENABLED("enabled"),
		POSITION("position"),
		BACKGROUND_COLOR("backgroundColor"),
		TITLE_FONT_FAMILY("titleFontFamily"),
		TITLE_FONT_SIZE("titleFontSize"),
		TITLE_FONT_STYLE("titleFontStyle"),
		TITLE_FONT_COLOR("titleFontColor"),
		TITLE_SPACING("titleSpacing"),
		TITLE_MARGIN_BOTTOM("titleMarginBottom"),
		TITLE_ALIGN("titleAlign"),
		BODY_FONT_FAMILY("bodyFontFamily"),
		BODY_FONT_SIZE("bodyFontSize"),
		BODY_FONT_STYLE("bodyFontStyle"),
		BODY_FONT_COLOR("bodyFontColor"),
		BODY_SPACING("bodySpacing"),
		BODY_ALIGN("bodyAlign"),
		FOOTER_FONT_FAMILY("footerFontFamily"),
		FOOTER_FONT_SIZE("footerFontSize"),
		FOOTER_FONT_STYLE("footerFontStyle"),
		FOOTER_FONT_COLOR("footerFontColor"),
		FOOTER_SPACING("footerSpacing"),
		FOOTER_MARGIN_TOP("footerMarginTop"),
		FOOTER_ALIGN("footerAlign"),
		X_PADDING("xPadding"),
		Y_PADDING("yPadding"),
		CARET_PADDING("caretPadding"),
		CARET_SIZE("caretSize"),
		CORNER_RADIUS("cornerRadius"),
		MULTI_KEY_BACKGROUND("multiKeyBackground"),
		DISPLAY_COLORS("displayColors"),
		BORDER_COLOR("borderColor"),
		BORDER_WIDTH("borderWidth");

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
	 * Creates the object with the parent, the key of this element, default values and native object to map java script
	 * properties.
	 * 
	 * @param options options of the chart.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	Tooltips(Options options, Key childKey, IsDefaultTooltips defaultValues, NativeObject nativeObject) {
		super(options, childKey, defaultValues, nativeObject);
		// gets sub element
		this.callbacks = new TooltipsCallbacks(this, Property.CALLBACKS, defaultValues, getValue(Property.CALLBACKS));
	}

	/**
	 * Returns the callbacks element.
	 * 
	 * @return the callbacks
	 */
	public TooltipsCallbacks getCallbacks() {
		return callbacks;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.AbstractHover#getDefaultMode()
	 */
	@Override
	InteractionMode getDefaultMode() {
		return getDefaultValues().getMode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.AbstractHover#isDefaultIntersect()
	 */
	@Override
	boolean isDefaultIntersect() {
		return getDefaultValues().isIntersect();
	}

	/**
	 * Sets <code>true</code> if tooltips are enabled.
	 * 
	 * @param enabled <code>true</code> if tooltips are enabled.
	 */
	public void setEnabled(boolean enabled) {
		setValue(Property.ENABLED, enabled);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns <code>true</code> if tooltips are enabled.
	 * 
	 * @return <code>true</code> if tooltips are enabled.
	 */
	public boolean isEnabled() {
		return getValue(Property.ENABLED, getDefaultValues().isEnabled());
	}

	/**
	 * Sets the mode for positioning the tooltip.
	 * 
	 * @param position the mode for positioning the tooltip.
	 */
	public void setPosition(IsTooltipPosition position) {
		// checks if the tooltip position is consistent
		// & that means that is defined both otu of the box or custom one by positioner
		if (position != null && !Key.hasKeyByValue(TooltipPosition.class, position.value()) && !Positioner.get().hasTooltipPosition(position.value())) {
			throw new IllegalArgumentException("Name of tooltip position is not consistent not defined: " + position);
		}
		// stores values
		setValue(Property.POSITION, position);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the mode for positioning the tooltip.
	 * 
	 * @return mode for positioning the tooltip.
	 */
	public IsTooltipPosition getPosition() {
		// gets string value
		String value = getValue(Property.POSITION, getDefaultValues().getPosition().value());
		// checks if is the out of the box one
		if (Key.hasKeyByValue(TooltipPosition.class, value)) {
			// returns the pout of the box
			return Key.getKeyByValue(TooltipPosition.class, value);
		}
		// if here, it could be a custom tooltip position
		// checks if is a custom tooltip position
		if (Positioner.get().hasTooltipPosition(value)) {
			// returns the pout of the box
			return Positioner.get().getTooltipPosition(value);
		}
		// if here, it's wrong but returns the default ones
		return getDefaultValues().getPosition();
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
		setValue(Property.BACKGROUND_COLOR, backgroundColor);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the background color of the tooltip.
	 * 
	 * @return background color of the tooltip.
	 */
	public String getBackgroundColorAsString() {
		return getValue(Property.BACKGROUND_COLOR, getDefaultValues().getBackgroundColorAsString());
	}

	/**
	 * Returns the background color of the tooltip.
	 * 
	 * @return background color of the tooltip.
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
		setValue(Property.TITLE_FONT_FAMILY, titleFontFamily);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the title font.
	 * 
	 * @return the title font.
	 */
	public String getTitleFontFamily() {
		return getValue(Property.TITLE_FONT_FAMILY, getDefaultValues().getTitleFontFamily());
	}

	/**
	 * Sets the title font size.
	 * 
	 * @param titleFontSize title font size.
	 */
	public void setTitleFontSize(int titleFontSize) {
		setValue(Property.TITLE_FONT_SIZE, titleFontSize);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the title font size.
	 * 
	 * @return title font size.
	 */
	public int getTitleFontSize() {
		return getValue(Property.TITLE_FONT_SIZE, getDefaultValues().getTitleFontSize());
	}

	/**
	 * Sets the title font style.
	 * 
	 * @param titleFontStyle title font style.
	 */
	public void setTitleFontStyle(FontStyle titleFontStyle) {
		setValue(Property.TITLE_FONT_STYLE, titleFontStyle);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the title font style.
	 * 
	 * @return title font style.
	 */
	public FontStyle getTitleFontStyle() {
		return getValue(Property.TITLE_FONT_STYLE, FontStyle.class, getDefaultValues().getTitleFontStyle());
	}

	/**
	 * Sets the title alignment.
	 * 
	 * @param align title alignment.
	 */
	public void setTitleAlign(TextAlign align) {
		setValue(Property.TITLE_ALIGN, align);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the title alignment.
	 * 
	 * @return title alignment.
	 */
	public TextAlign getTitleAlign() {
		return getValue(Property.TITLE_ALIGN, TextAlign.class, getDefaultValues().getTitleAlign());
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
		setValue(Property.TITLE_FONT_COLOR, titleFontColor);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the title font color.
	 * 
	 * @return title font color.
	 */
	public String getTitleFontColorAsString() {
		return getValue(Property.TITLE_FONT_COLOR, getDefaultValues().getTitleFontColorAsString());
	}

	/**
	 * Returns the title font color.
	 * 
	 * @return title font color.
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
		setValue(Property.TITLE_SPACING, titleSpacing);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the spacing to add to top and bottom of each title line.
	 * 
	 * @return spacing to add to top and bottom of each title line.
	 */
	public int getTitleSpacing() {
		return getValue(Property.TITLE_SPACING, getDefaultValues().getTitleSpacing());
	}

	/**
	 * Sets the margin to add on bottom of title section.
	 * 
	 * @param titleMarginBottom margin to add on bottom of title section.
	 */
	public void setTitleMarginBottom(int titleMarginBottom) {
		setValue(Property.TITLE_MARGIN_BOTTOM, titleMarginBottom);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the margin to add on bottom of title section.
	 * 
	 * @return margin to add on bottom of title section.
	 */
	public int getTitleMarginBottom() {
		return getValue(Property.TITLE_MARGIN_BOTTOM, getDefaultValues().getTitleMarginBottom());
	}

	/**
	 * Sets the body line font.
	 * 
	 * @param bodyFontFamily body line font.
	 */
	public void setBodyFontFamily(String bodyFontFamily) {
		setValue(Property.BODY_FONT_FAMILY, bodyFontFamily);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the body line font.
	 * 
	 * @return body line font.
	 */
	public String getBodyFontFamily() {
		return getValue(Property.BODY_FONT_FAMILY, getDefaultValues().getBodyFontFamily());
	}

	/**
	 * Sets the body font size.
	 * 
	 * @param bodyFontSize body font size.
	 */
	public void setBodyFontSize(int bodyFontSize) {
		setValue(Property.BODY_FONT_SIZE, bodyFontSize);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the body font size.
	 * 
	 * @return body font size.
	 */
	public int getBodyFontSize() {
		return getValue(Property.BODY_FONT_SIZE, getDefaultValues().getBodyFontSize());
	}

	/**
	 * Sets the body font style.
	 * 
	 * @param bodyFontStyle body font style.
	 */
	public void setBodyFontStyle(FontStyle bodyFontStyle) {
		setValue(Property.BODY_FONT_STYLE, bodyFontStyle);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the body font style.
	 * 
	 * @return body font style.
	 */
	public FontStyle getBodyFontStyle() {
		return getValue(Property.BODY_FONT_STYLE, FontStyle.class, getDefaultValues().getBodyFontStyle());
	}

	/**
	 * Sets the body alignment.
	 * 
	 * @param align body alignment.
	 */
	public void setBodyAlign(TextAlign align) {
		setValue(Property.BODY_ALIGN, align);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the body alignment.
	 * 
	 * @return body alignment.
	 */
	public TextAlign getBodyAlign() {
		return getValue(Property.BODY_ALIGN, TextAlign.class, getDefaultValues().getBodyAlign());
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
		setValue(Property.BODY_FONT_COLOR, bodyFontColor);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the body font color.
	 * 
	 * @return body font color.
	 */
	public String getBodyFontColorAsString() {
		return getValue(Property.BODY_FONT_COLOR, getDefaultValues().getBodyFontColorAsString());
	}

	/**
	 * Returns the body font color.
	 * 
	 * @return body font color.
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
		setValue(Property.BODY_SPACING, bodySpacing);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the spacing to add to top and bottom of each tooltip item.
	 * 
	 * @return spacing to add to top and bottom of each tooltip item.
	 */
	public int getBodySpacing() {
		return getValue(Property.BODY_SPACING, getDefaultValues().getBodySpacing());
	}

	/**
	 * Sets the footer font.
	 * 
	 * @param footerFontFamily footer font.
	 */
	public void setFooterFontFamily(String footerFontFamily) {
		setValue(Property.FOOTER_FONT_FAMILY, footerFontFamily);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the footer font.
	 * 
	 * @return footer font.
	 */
	public String getFooterFontFamily() {
		return getValue(Property.FOOTER_FONT_FAMILY, getDefaultValues().getFooterFontFamily());
	}

	/**
	 * Sets the footer font size.
	 * 
	 * @param footerFontSize footer font size.
	 */
	public void setFooterFontSize(int footerFontSize) {
		setValue(Property.FOOTER_FONT_SIZE, footerFontSize);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the footer font size.
	 * 
	 * @return footer font size.
	 */
	public int getFooterFontSize() {
		return getValue(Property.FOOTER_FONT_SIZE, getDefaultValues().getFooterFontSize());
	}

	/**
	 * Sets the footer font style.
	 * 
	 * @param footerFontStyle the footer font style.
	 */
	public void setFooterFontStyle(FontStyle footerFontStyle) {
		setValue(Property.FOOTER_FONT_STYLE, footerFontStyle);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the footer font style.
	 * 
	 * @return footer font style.
	 */
	public FontStyle getFooterFontStyle() {
		return getValue(Property.FOOTER_FONT_STYLE, FontStyle.class, getDefaultValues().getFooterFontStyle());
	}

	/**
	 * Sets the footer alignment.
	 * 
	 * @param align footer alignment.
	 */
	public void setFooterAlign(TextAlign align) {
		setValue(Property.FOOTER_ALIGN, align);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the body alignment.
	 * 
	 * @return footer alignment.
	 */
	public TextAlign getFooterAlign() {
		return getValue(Property.FOOTER_ALIGN, TextAlign.class, getDefaultValues().getFooterAlign());
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
		setValue(Property.FOOTER_FONT_COLOR, footerFontColor);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the footer font color.
	 * 
	 * @return footer font color.
	 */
	public String getFooterFontColorAsString() {
		return getValue(Property.FOOTER_FONT_COLOR, getDefaultValues().getFooterFontColorAsString());
	}

	/**
	 * Returns the footer font color.
	 * 
	 * @return footer font color.
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
		setValue(Property.FOOTER_SPACING, footerSpacing);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the spacing to add to top and bottom of each footer line.
	 * 
	 * @return spacing to add to top and bottom of each footer line.
	 */
	public int getFooterSpacing() {
		return getValue(Property.FOOTER_SPACING, getDefaultValues().getFooterSpacing());
	}

	/**
	 * Sets the margin to add before drawing the footer.
	 * 
	 * @param footerMarginTop margin to add before drawing the footer.
	 */
	public void setFooterMarginTop(int footerMarginTop) {
		setValue(Property.FOOTER_MARGIN_TOP, footerMarginTop);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the margin to add before drawing the footer.
	 * 
	 * @return margin to add before drawing the footer.
	 */
	public int getFooterMarginTop() {
		return getValue(Property.FOOTER_MARGIN_TOP, getDefaultValues().getFooterMarginTop());
	}

	/**
	 * Sets the padding to add on left and right of tooltip.
	 * 
	 * @param xPadding padding to add on left and right of tooltip.
	 */
	public void setXPadding(int xPadding) {
		setValue(Property.X_PADDING, xPadding);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the padding to add on left and right of tooltip.
	 * 
	 * @return padding to add on left and right of tooltip.
	 */
	public int getXPadding() {
		return getValue(Property.X_PADDING, getDefaultValues().getXPadding());
	}

	/**
	 * Sets the padding to add on top and bottom of tooltip.
	 * 
	 * @param yPadding padding to add on top and bottom of tooltip.
	 */
	public void setYPadding(int yPadding) {
		setValue(Property.Y_PADDING, yPadding);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the padding to add on top and bottom of tooltip.
	 * 
	 * @return padding to add on top and bottom of tooltip.
	 */
	public int getYPadding() {
		return getValue(Property.Y_PADDING, getDefaultValues().getYPadding());
	}

	/**
	 * Sets the extra distance to move the end of the tooltip arrow away from the tooltip point.
	 * 
	 * @param caretPadding extra distance to move the end of the tooltip arrow away from the tooltip point.
	 */
	public void setCaretPadding(int caretPadding) {
		setValue(Property.CARET_PADDING, caretPadding);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the extra distance to move the end of the tooltip arrow away from the tooltip point.
	 * 
	 * @return extra distance to move the end of the tooltip arrow away from the tooltip point.
	 */
	public int getCaretPadding() {
		return getValue(Property.CARET_PADDING, getDefaultValues().getCaretPadding());
	}

	/**
	 * Sets the size, in pixels, of the tooltip arrow.
	 * 
	 * @param caretSize size, in pixels, of the tooltip arrow.
	 */
	public void setCaretSize(int caretSize) {
		setValue(Property.CARET_SIZE, caretSize);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the size, in pixels, of the tooltip arrow.
	 * 
	 * @return size, in pixels, of the tooltip arrow.
	 */
	public int getCaretSize() {
		return getValue(Property.CARET_SIZE, getDefaultValues().getCaretSize());
	}

	/**
	 * Sets the radius of tooltip corner curves.
	 * 
	 * @param cornerRadius radius of tooltip corner curves.
	 */
	public void setCornerRadius(int cornerRadius) {
		setValue(Property.CORNER_RADIUS, cornerRadius);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the radius of tooltip corner curves.
	 * 
	 * @return radius of tooltip corner curves.
	 */
	public int getCornerRadius() {
		return getValue(Property.CORNER_RADIUS, getDefaultValues().getCornerRadius());
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
		setValue(Property.MULTI_KEY_BACKGROUND, multiKeyBackground);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the color to draw behind the colored boxes when multiple items are in the tooltip.
	 * 
	 * @return color to draw behind the colored boxes when multiple items are in the tooltip.
	 */
	public String getMultiKeyBackgroundAsString() {
		return getValue(Property.MULTI_KEY_BACKGROUND, getDefaultValues().getMultiKeyBackgroundAsString());
	}

	/**
	 * Returns the color to draw behind the colored boxes when multiple items are in the tooltip.
	 * 
	 * @return color to draw behind the colored boxes when multiple items are in the tooltip.
	 */
	public IsColor getMultiKeyBackground() {
		return ColorBuilder.parse(getMultiKeyBackgroundAsString());
	}

	/**
	 * If <code>true</code>, color boxes are shown in the tooltip.
	 * 
	 * @param displayColors if <code>true</code>, color boxes are shown in the tooltip.
	 */
	public void setDisplayColors(boolean displayColors) {
		setValue(Property.DISPLAY_COLORS, displayColors);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * If <code>true</code>, color boxes are shown in the tooltip.
	 * 
	 * @return if <code>true</code>, color boxes are shown in the tooltip.
	 */
	public boolean isDisplayColors() {
		return getValue(Property.DISPLAY_COLORS, getDefaultValues().isDisplayColors());
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
		setValue(Property.BORDER_COLOR, borderColor);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the color of the border.
	 * 
	 * @return color of the border.
	 */
	public String getBorderColorAsString() {
		return getValue(Property.BORDER_COLOR, getDefaultValues().getBorderColorAsString());
	}

	/**
	 * Returns the color of the border.
	 * 
	 * @return color of the border.
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
		setValue(Property.BORDER_WIDTH, borderWidth);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the size of the border.
	 * 
	 * @return size of the border.
	 */
	public int getBorderWidth() {
		return getValue(Property.BORDER_WIDTH, getDefaultValues().getBorderWidth());
	}
}