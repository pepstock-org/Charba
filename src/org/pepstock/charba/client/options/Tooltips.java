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
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.HasCallbackScope;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.data.BarBorderRadius;
import org.pepstock.charba.client.defaults.IsDefaultTooltips;
import org.pepstock.charba.client.enums.IsInteractionMode;
import org.pepstock.charba.client.enums.IsTooltipPosition;
import org.pepstock.charba.client.enums.TextAlign;
import org.pepstock.charba.client.enums.TooltipAlign;
import org.pepstock.charba.client.positioner.Positioner;

/**
 * Configuration element to set all attributes and features of the default tooltip.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Tooltips extends AbstractInteraction<Plugins, IsDefaultTooltips> implements IsDefaultTooltips, HasTextDirection, HasBox, HasAnimationOptions, HasEvents {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		CALLBACKS("callbacks"),
		// simple properties
		BACKGROUND_COLOR("backgroundColor"),
		BODY_ALIGN("bodyAlign"),
		BODY_COLOR("bodyColor"),
		BODY_FONT("bodyFont"),
		BODY_SPACING("bodySpacing"),
		BORDER_COLOR("borderColor"),
		BORDER_WIDTH("borderWidth"),
		BOX_PADDING("boxPadding"),
		CARET_PADDING("caretPadding"),
		CARET_SIZE("caretSize"),
		CORNER_RADIUS("cornerRadius"),
		DISPLAY_COLORS("displayColors"),
		ENABLED("enabled"),
		FOOTER_ALIGN("footerAlign"),
		FOOTER_COLOR("footerColor"),
		FOOTER_FONT("footerFont"),
		FOOTER_MARGIN_TOP("footerMarginTop"),
		FOOTER_SPACING("footerSpacing"),
		MULTI_KEY_BACKGROUND("multiKeyBackground"),
		PADDING("padding"),
		POSITION("position"),
		TITLE_ALIGN("titleAlign"),
		TITLE_COLOR("titleColor"),
		TITLE_FONT("titleFont"),
		TITLE_MARGIN_BOTTOM("titleMarginBottom"),
		TITLE_SPACING("titleSpacing"),
		USE_POINT_STYLE("usePointStyle"),
		X_ALIGN("xAlign"),
		Y_ALIGN("yAlign");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use in the native object.
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

	// Callbacks sub element
	private final TooltipsCallbacks callbacks;
	// Text direction sub element
	private final TextDirectionHandler textDirectionHandler;
	// instance of font for title
	private final Font titleFont;
	// instance of font for body
	private final Font bodyFont;
	// instance of font for footer
	private final Font footerFont;
	// padding instance
	private final Padding padding;
	// instance of box handler
	private final BoxHandler boxHandler;
	// animation container
	private final AnimationContainer animationContainer;
	// events handler
	private final EventsOptionHandler eventsHandler;

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param options plugins options of the chart.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	Tooltips(Plugins options, Key childKey, IsDefaultTooltips defaultValues, NativeObject nativeObject) {
		super(options, childKey, defaultValues, nativeObject);
		// gets sub elements
		this.padding = loadPadding(Property.PADDING, getDefaultValues().getPadding());
		this.callbacks = new TooltipsCallbacks(this, Property.CALLBACKS, getDefaultValues(), getValue(Property.CALLBACKS));
		// gets fonts definition
		this.titleFont = new Font(this, Property.TITLE_FONT, getDefaultValues().getTitleFont(), getValue(Property.TITLE_FONT));
		this.bodyFont = new Font(this, Property.BODY_FONT, getDefaultValues().getBodyFont(), getValue(Property.BODY_FONT));
		this.footerFont = new Font(this, Property.FOOTER_FONT, getDefaultValues().getFooterFont(), getValue(Property.FOOTER_FONT));
		// creates text direction handler
		this.textDirectionHandler = new TextDirectionHandler(this, getDefaultValues(), getNativeObject());
		// creates the box handler
		this.boxHandler = new BoxHandler(this, getDefaultValues(), getNativeObject());
		// sets animation container
		this.animationContainer = new AnimationContainer(options, childKey, getDefaultValues(), getNativeObject(), HasCallbackScope.extractScope(options));
		// creates events handler
		this.eventsHandler = new EventsOptionHandler(this, getDefaultValues(), getNativeObject());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.HasBox#getBoxHandler()
	 */
	@Override
	public BoxHandler getBoxHandler() {
		return boxHandler;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.HasEvents#getEventsOptionHandler()
	 */
	@Override
	public EventsOptionHandler getEventsOptionHandler() {
		return eventsHandler;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.HasTextDirection#getTextDirectionHandler()
	 */
	@Override
	public TextDirectionHandler getTextDirectionHandler() {
		return textDirectionHandler;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.HasAnimationOptions#getAnimationContainer()
	 */
	@Override
	public AnimationContainer getAnimationContainer() {
		return animationContainer;
	}

	/**
	 * Returns the callbacks element.
	 * 
	 * @return the callbacks
	 */
	public TooltipsCallbacks getCallbacks() {
		return callbacks;
	}

	/**
	 * Returns the padding element.
	 * 
	 * @return the padding
	 */
	@Override
	public Padding getPadding() {
		return padding;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.AbstractHover#getDefaultMode()
	 */
	@Override
	IsInteractionMode getDefaultMode() {
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
		setValueAndAddToParent(Property.ENABLED, enabled);
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
		setValueAndAddToParent(Property.POSITION, position);
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
		// it could be a custom tooltip position
		// checks if is a custom tooltip position
		if (Positioner.get().hasTooltipPosition(value)) {
			// returns the pout of the box
			return Positioner.get().getTooltipPosition(value);
		}
		// if here, it could be a default or custom one
		// then creates and returns it.
		return IsTooltipPosition.create(value);
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
		setValueAndAddToParent(Property.BACKGROUND_COLOR, backgroundColor);
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
	 * Sets the alignment of the tooltip caret in the X direction.
	 * 
	 * @param align the alignment of the tooltip caret in the X direction.
	 */
	public void setXAlign(TooltipAlign align) {
		// x align accepts only right, left and center
		setValueAndAddToParent(Property.X_ALIGN, Key.isValid(align) && align.isHorizontal() ? align : null);
	}

	/**
	 * Returns the alignment of the tooltip caret in the X direction.
	 * 
	 * @return the alignment of the tooltip caret in the X direction
	 */
	@Override
	public TooltipAlign getXAlign() {
		return getValue(Property.X_ALIGN, TooltipAlign.values(), getDefaultValues().getXAlign());
	}

	/**
	 * Sets the alignment of the tooltip caret in the Y direction.
	 * 
	 * @param align the alignment of the tooltip caret in the Y direction.
	 */
	public void setYAlign(TooltipAlign align) {
		// x align accepts only right, left and center
		setValueAndAddToParent(Property.Y_ALIGN, Key.isValid(align) && align.isVertical() ? align : null);
	}

	/**
	 * Returns the alignment of the tooltip caret in the Y direction.
	 * 
	 * @return the alignment of the tooltip caret in the Y direction
	 */
	@Override
	public TooltipAlign getYAlign() {
		return getValue(Property.Y_ALIGN, TooltipAlign.values(), getDefaultValues().getYAlign());
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
	 * Sets the title font color.
	 * 
	 * @param color title font color.
	 */
	public void setTitleColor(IsColor color) {
		setTitleColor(IsColor.checkAndGetValue(color));
	}

	/**
	 * Sets the title font color.
	 * 
	 * @param color title font color.
	 */
	public void setTitleColor(String color) {
		setValueAndAddToParent(Property.TITLE_COLOR, color);
	}

	/**
	 * Returns the title font color as string.
	 * 
	 * @return title font color as string
	 */
	@Override
	public String getTitleColorAsString() {
		return getValue(Property.TITLE_COLOR, getDefaultValues().getTitleColorAsString());
	}

	/**
	 * Returns the title font color.
	 * 
	 * @return title font color
	 */
	public IsColor getTitleColor() {
		return ColorBuilder.parse(getTitleColorAsString());
	}

	/**
	 * Sets the title alignment.
	 * 
	 * @param align title alignment.
	 */
	public void setTitleAlign(TextAlign align) {
		// tooltip accepts only right, left and center
		// then use the left-right value
		setValueAndAddToParent(Property.TITLE_ALIGN, Key.isValid(align) ? align.getLeftRightValue() : null);
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
		setValueAndAddToParent(Property.TITLE_SPACING, Checker.positiveOrZero(titleSpacing));
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
		setValueAndAddToParent(Property.TITLE_MARGIN_BOTTOM, Checker.positiveOrZero(titleMarginBottom));
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
	 * Sets the body font color.
	 * 
	 * @param color body font color.
	 */
	public void setBodyColor(IsColor color) {
		setBodyColor(IsColor.checkAndGetValue(color));
	}

	/**
	 * Sets the body font color.
	 * 
	 * @param color body font color.
	 */
	public void setBodyColor(String color) {
		setValueAndAddToParent(Property.BODY_COLOR, color);
	}

	/**
	 * Returns the body font color as string.
	 * 
	 * @return body font color as string
	 */
	@Override
	public String getBodyColorAsString() {
		return getValue(Property.BODY_COLOR, getDefaultValues().getBodyColorAsString());
	}

	/**
	 * Returns the body font color.
	 * 
	 * @return body font color
	 */
	public IsColor getBodyColor() {
		return ColorBuilder.parse(getBodyColorAsString());
	}

	/**
	 * Sets the body alignment.
	 * 
	 * @param align body alignment.
	 */
	public void setBodyAlign(TextAlign align) {
		// tooltip accepts only right, left and center
		// then use the left-right value
		setValueAndAddToParent(Property.BODY_ALIGN, Key.isValid(align) ? align.getLeftRightValue() : null);
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
		setValueAndAddToParent(Property.BODY_SPACING, Checker.positiveOrZero(bodySpacing));
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
	 * Sets the footer font color.
	 * 
	 * @param color footer font color.
	 */
	public void setFooterColor(IsColor color) {
		setFooterColor(IsColor.checkAndGetValue(color));
	}

	/**
	 * Sets the footer font color.
	 * 
	 * @param color footer font color.
	 */
	public void setFooterColor(String color) {
		setValueAndAddToParent(Property.FOOTER_COLOR, color);
	}

	/**
	 * Returns the footer font color as string.
	 * 
	 * @return footer font color as string
	 */
	@Override
	public String getFooterColorAsString() {
		return getValue(Property.FOOTER_COLOR, getDefaultValues().getFooterColorAsString());
	}

	/**
	 * Returns the footer font color.
	 * 
	 * @return footer font color
	 */
	public IsColor getFooterColor() {
		return ColorBuilder.parse(getFooterColorAsString());
	}

	/**
	 * Sets the footer alignment.
	 * 
	 * @param align footer alignment.
	 */
	public void setFooterAlign(TextAlign align) {
		// tooltip accepts only right, left and center
		// then use the left-right value
		setValueAndAddToParent(Property.FOOTER_ALIGN, Key.isValid(align) ? align.getLeftRightValue() : null);
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
		setValueAndAddToParent(Property.FOOTER_SPACING, Checker.positiveOrZero(footerSpacing));
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
		setValueAndAddToParent(Property.FOOTER_MARGIN_TOP, Checker.positiveOrZero(footerMarginTop));
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
	 * Sets the extra distance to move the end of the tooltip arrow away from the tooltip point.
	 * 
	 * @param caretPadding extra distance to move the end of the tooltip arrow away from the tooltip point.
	 */
	public void setCaretPadding(int caretPadding) {
		setValueAndAddToParent(Property.CARET_PADDING, Checker.positiveOrZero(caretPadding));
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
		setValueAndAddToParent(Property.CARET_SIZE, Checker.positiveOrZero(caretSize));
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
		setValueAndAddToParent(Property.CORNER_RADIUS, Checker.positiveOrZero(cornerRadius));
	}

	/**
	 * Sets the radius of tooltip corner curves.
	 * 
	 * @param cornerRadius radius of tooltip corner curves.
	 */
	public void setCornerRadius(BarBorderRadius cornerRadius) {
		setValueAndAddToParent(Property.CORNER_RADIUS, cornerRadius);
	}

	/**
	 * Returns the radius of tooltip corner curves.
	 * 
	 * @return radius of tooltip corner curves.
	 */
	@Override
	public int getCornerRadius() {
		// checks if the radius is stored as number
		if (isType(Property.CORNER_RADIUS, ObjectType.NUMBER)) {
			// gets and returns the value
			return getValue(Property.CORNER_RADIUS, getDefaultValues().getCornerRadius());
		}
		// if here, the value is not stored as number
		// then returns default
		return getDefaultValues().getCornerRadius();
	}

	/**
	 * Returns the radius of tooltip corner curves.
	 * 
	 * @return radius of tooltip corner curves.
	 */
	public BarBorderRadius getCornerRadiusAsObject() {
		// checks if the radius is stored as number
		if (isType(Property.CORNER_RADIUS, ObjectType.OBJECT)) {
			// gets and returns the value
			return BarBorderRadius.FACTORY.create(getValue(Property.CORNER_RADIUS));
		}
		// if here, the value is not stored as number
		// then returns default
		// by number value
		return new BarBorderRadius(getCornerRadius());
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
		setValueAndAddToParent(Property.MULTI_KEY_BACKGROUND, multiKeyBackground);
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
		setValueAndAddToParent(Property.DISPLAY_COLORS, displayColors);
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
		setValueAndAddToParent(Property.BORDER_COLOR, borderColor);
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
		setValueAndAddToParent(Property.BORDER_WIDTH, Checker.positiveOrZero(borderWidth));
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
	 * Sets if to use the corresponding point style (from dataset options) instead of color boxes (size is based on the minimum value between boxWidth and boxHeight).
	 * 
	 * @param usePointStyle <code>true</code> if to use the corresponding point style (from dataset options) instead of color boxes (size is based on the minimum value between
	 *            boxWidth and boxHeight)
	 */
	public void setUsePointStyle(boolean usePointStyle) {
		setValueAndAddToParent(Property.USE_POINT_STYLE, usePointStyle);
	}

	/**
	 * Returns if to use the corresponding point style (from dataset options) instead of color boxes (size is based on the minimum value between boxWidth and boxHeight).
	 * 
	 * @return <code>true</code> if to use the corresponding point style (from dataset options) instead of color boxes (size is based on the minimum value between boxWidth and
	 *         boxHeight)
	 */
	@Override
	public boolean isUsePointStyle() {
		return getValue(Property.USE_POINT_STYLE, getDefaultValues().isUsePointStyle());
	}

	/**
	 * Sets the padding between the color box and the text.
	 * 
	 * @param boxPadding padding between the color box and the text
	 */
	public void setBoxPadding(int boxPadding) {
		setValueAndAddToParent(Property.BOX_PADDING, Checker.positiveOrZero(boxPadding));
	}

	/**
	 * Returns the padding between the color box and the text.
	 * 
	 * @return padding between the color box and the text
	 */
	@Override
	public int getBoxPadding() {
		return getValue(Property.BOX_PADDING, getDefaultValues().getBoxPadding());
	}
}