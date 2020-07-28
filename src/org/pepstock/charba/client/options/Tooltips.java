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
public final class Tooltips extends AbstractHover<IsDefaultTooltips> implements IsDefaultTooltips, HasTextDirection, HasAnimation {

	// Callbacks sub element
	private final TooltipsCallbacks callbacks;
	// Text direction sub element
	private final TextDirectioner textDirectioner;
	// instance of font for title
	private final Font titleFont;
	// instance of font for body
	private final Font bodyFont;
	// instance of font for footer
	private final Font footerFont;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		CALLBACKS("callbacks"),
		// simple properties
		ENABLED("enabled"),
		POSITION("position"),
		BACKGROUND_COLOR("backgroundColor"),
		TITLE_FONT("titleFont"),
		TITLE_ALIGN("titleAlign"),
		TITLE_SPACING("titleSpacing"),
		TITLE_MARGIN_BOTTOM("titleMarginBottom"),
		BODY_FONT("bodyFont"),
		BODY_SPACING("bodySpacing"),
		BODY_ALIGN("bodyAlign"),
		FOOTER_FONT("footerFont"),
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
		BOX_WIDTH("boxWidth"),
		BOX_HEIGHT("boxHeight"),
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
	
	// animation container
	private final AnimationContainer animationContainer;

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param options options of the chart.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	Tooltips(Options options, Key childKey, IsDefaultTooltips defaultValues, NativeObject nativeObject) {
		super(options, childKey, defaultValues, nativeObject);
		// gets sub elements
		this.callbacks = new TooltipsCallbacks(this, Property.CALLBACKS, defaultValues, getValue(Property.CALLBACKS));
		// gets fonts definition
		this.titleFont = new Font(this, Property.TITLE_FONT, getDefaultValues().getTitleFont(), getValue(Property.TITLE_FONT));
		this.bodyFont = new Font(this, Property.BODY_FONT, getDefaultValues().getBodyFont(), getValue(Property.BODY_FONT));
		this.footerFont = new Font(this, Property.FOOTER_FONT, getDefaultValues().getFooterFont(), getValue(Property.FOOTER_FONT));
		// creates text directioner
		this.textDirectioner = new TextDirectioner(getNativeObject(), this, defaultValues);
		// sets animation container
		this.animationContainer = new AnimationContainer(getDefaultValues().getAnimation(), getNativeObject());
	}

	/**
	 * Returns the animation element.
	 * 
	 * @return the animation
	 */
	@Override
	public Animation getAnimation() {
		return animationContainer.getAnimation();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.HasAnimation#getAnimationContainer()
	 */
	@Override
	public final AnimationContainer getAnimationContainer() {
		return animationContainer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.HasTextDirection#getTextDirectioner()
	 */
	@Override
	public TextDirectioner getTextDirectioner() {
		return textDirectioner;
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
	@Override
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
		Key.checkIfValid(position);
		// that means that is defined both out of the box or custom one by positioner
		if (!Key.hasKeyByValue(TooltipPosition.values(), position.value()) && !Positioner.get().hasTooltipPosition(position.value())) {
			throw new IllegalArgumentException("The tooltip position '" + position + "' is not consistent");
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
	@Override
	public IsTooltipPosition getPosition() {
		// gets string value
		String value = getValue(Property.POSITION, getDefaultValues().getPosition().value());
		// checks if is the out of the box one
		if (Key.hasKeyByValue(TooltipPosition.values(), value)) {
			// returns the pout of the box
			return Key.getKeyByValue(TooltipPosition.values(), value);
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
		setBackgroundColor(IsColor.checkAndGetValue(backgroundColor));
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
	@Override
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
	 * Returns the title font element.
	 * 
	 * @return the font
	 */
	@Override
	public Font getTitleFont() {
		return titleFont;
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
	@Override
	public TextAlign getTitleAlign() {
		return getValue(Property.TITLE_ALIGN, TextAlign.values(), getDefaultValues().getTitleAlign());
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
	@Override
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
	@Override
	public int getTitleMarginBottom() {
		return getValue(Property.TITLE_MARGIN_BOTTOM, getDefaultValues().getTitleMarginBottom());
	}

	/**
	 * Returns the body font element.
	 * 
	 * @return the font
	 */
	@Override
	public Font getBodyFont() {
		return bodyFont;
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
	@Override
	public TextAlign getBodyAlign() {
		return getValue(Property.BODY_ALIGN, TextAlign.values(), getDefaultValues().getBodyAlign());
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
	@Override
	public int getBodySpacing() {
		return getValue(Property.BODY_SPACING, getDefaultValues().getBodySpacing());
	}

	/**
	 * Returns the footer font element.
	 * 
	 * @return the font
	 */
	@Override
	public Font getFooterFont() {
		return footerFont;
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
	@Override
	public TextAlign getFooterAlign() {
		return getValue(Property.FOOTER_ALIGN, TextAlign.values(), getDefaultValues().getFooterAlign());
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
	@Override
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
	@Override
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
	@Override
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
	@Override
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
	@Override
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
	@Override
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
	@Override
	public int getCornerRadius() {
		return getValue(Property.CORNER_RADIUS, getDefaultValues().getCornerRadius());
	}

	/**
	 * Sets the color to draw behind the colored boxes when multiple items are in the tooltip.
	 * 
	 * @param multiKeyBackground color to draw behind the colored boxes when multiple items are in the tooltip.
	 */
	public void setMultiKeyBackground(IsColor multiKeyBackground) {
		setMultiKeyBackground(IsColor.checkAndGetValue(multiKeyBackground));
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
	@Override
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
	@Override
	public boolean isDisplayColors() {
		return getValue(Property.DISPLAY_COLORS, getDefaultValues().isDisplayColors());
	}

	/**
	 * Sets the color of the border.
	 * 
	 * @param borderColor color of the border.
	 */
	public void setBorderColor(IsColor borderColor) {
		setBorderColor(IsColor.checkAndGetValue(borderColor));
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
	@Override
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
	@Override
	public int getBorderWidth() {
		return getValue(Property.BORDER_WIDTH, getDefaultValues().getBorderWidth());
	}

	/**
	 * Sets the width of of the colored box if displayColors is <code>true</code>..
	 * 
	 * @param boxWidth width of colored box.
	 */
	public void setBoxWidth(int boxWidth) {
		setValue(Property.BOX_WIDTH, boxWidth);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the width of the colored box if displayColors is <code>true</code>..
	 * 
	 * @return width of colored box.
	 */
	@Override
	public int getBoxWidth() {
		return getValue(Property.BOX_WIDTH, getDefaultValues().getBoxWidth());
	}

	/**
	 * Sets the height of the colored box if displayColors is <code>true</code>..
	 * 
	 * @param boxHeight width of colored box.
	 */
	public void setBoxHeight(int boxHeight) {
		setValue(Property.BOX_HEIGHT, boxHeight);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the height of the colored box if displayColors is <code>true</code>..
	 * 
	 * @return height of colored box.
	 */
	@Override
	public int getBoxHeight() {
		return getValue(Property.BOX_HEIGHT, getDefaultValues().getBoxHeight());
	}

}