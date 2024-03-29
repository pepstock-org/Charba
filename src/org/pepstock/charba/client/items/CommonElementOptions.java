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
package org.pepstock.charba.client.items;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.GradientBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.colors.Pattern;
import org.pepstock.charba.client.colors.PatternBuilder;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.dom.elements.CanvasGradientItem;
import org.pepstock.charba.client.dom.elements.CanvasPatternItem;

/**
 * Maps the common options of the {@link ChartElement}s.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class CommonElementOptions extends ChartElementOptions {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		BACKGROUND_COLOR("backgroundColor"),
		BORDER_WIDTH("borderWidth"),
		BORDER_COLOR("borderColor");

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

	/**
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	protected CommonElementOptions(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * This method must be overrided by all element where the border width is stored in different object.
	 * 
	 * @return an undefined integer
	 */
	protected int getDefaultBorderWidth() {
		return Undefined.INTEGER;
	}

	/**
	 * Returns <code>true</code> if the background color is defined as color.
	 * 
	 * @return <code>true</code> if the background color is defined as color
	 */
	public final boolean isBackgroundColorAsColor() {
		return isType(Property.BACKGROUND_COLOR, ObjectType.STRING);
	}

	/**
	 * Returns <code>true</code> if the background color is defined as gradient.
	 * 
	 * @return <code>true</code> if the background color is defined as gradient
	 */
	public final boolean isBackgroundColorAsGradient() {
		return JsItemsHelper.get().isCanvasGradient(this.getNativeObject(), Property.BACKGROUND_COLOR);
	}

	/**
	 * Returns <code>true</code> if the background color is defined as canvas pattern.
	 * 
	 * @return <code>true</code> if the background color is defined as canvas pattern
	 */
	public final boolean isBackgroundColorAsPattern() {
		return JsItemsHelper.get().isCanvasPattern(this.getNativeObject(), Property.BACKGROUND_COLOR);
	}

	/**
	 * Returns the background color of the dataset item.
	 *
	 * @return the background color of the dataset item.
	 */
	public final String getBackgroundColorAsString() {
		return getValue(Property.BACKGROUND_COLOR, Defaults.get().getGlobal().getBackgroundColorAsString());
	}

	/**
	 * Returns the background color of the dataset item.
	 *
	 * @return the background color of the dataset item.
	 */
	public final IsColor getBackgroundColor() {
		return ColorBuilder.parse(getBackgroundColorAsString());
	}

	/**
	 * Returns the background color as gradient.
	 * 
	 * @return the background color or <code>null</code> if is not a gradient
	 */
	public final Gradient getBackgroundColorAsGradient() {
		return GradientBuilder.retrieve(getBackgroundColorAsCanvasGradient());
	}

	/**
	 * Returns the background color as canvas gradient.
	 * 
	 * @return the background color or <code>null</code> if is not a canvas gradient
	 */
	public final CanvasGradientItem getBackgroundColorAsCanvasGradient() {
		// checks if the background color has been set as gradient
		if (isBackgroundColorAsGradient()) {
			return getValue(Property.BACKGROUND_COLOR, (CanvasGradientItem) null);
		}
		// if here, is not a color then returns null
		return null;
	}

	/**
	 * Returns the background color as pattern.
	 * 
	 * @return the background color or <code>null</code> if is not a pattern
	 */
	public final Pattern getBackgroundColorAsPattern() {
		return PatternBuilder.retrieve(getBackgroundColorAsCanvasPattern());
	}

	/**
	 * Returns the background color as canvas pattern.
	 * 
	 * @return the background color or <code>null</code> if is not a canvas pattern
	 */
	public final CanvasPatternItem getBackgroundColorAsCanvasPattern() {
		// checks if the background color has been set as pattern
		if (isBackgroundColorAsPattern()) {
			return getValue(Property.BACKGROUND_COLOR, (CanvasPatternItem) null);
		}
		// if here, is not a color then returns null
		return null;
	}

	/**
	 * Sets the background color.
	 * 
	 * @param backgroundColor the background color.
	 */
	public final void setBackgroundColor(IsColor backgroundColor) {
		setBackgroundColor(IsColor.checkAndGetValue(backgroundColor));
	}

	/**
	 * Sets the background color.
	 * 
	 * @param backgroundColor the background color.
	 */
	public final void setBackgroundColor(String backgroundColor) {
		setValue(Property.BACKGROUND_COLOR, backgroundColor);
	}

	/**
	 * Sets the background color as gradient.
	 * 
	 * @param backgroundColor the background color as gradient.
	 */
	public final void setBackgroundColor(CanvasGradientItem backgroundColor) {
		setValue(Property.BACKGROUND_COLOR, backgroundColor);
	}

	/**
	 * Sets the background color as pattern.
	 * 
	 * @param backgroundColor the background color as pattern.
	 */
	public final void setBackgroundColor(CanvasPatternItem backgroundColor) {
		setValue(Property.BACKGROUND_COLOR, backgroundColor);
	}

	/**
	 * Returns the border width of the dataset item in pixels.
	 *
	 * @return the border width of the dataset item in pixels.
	 */
	public int getBorderWidth() {
		return getValue(Property.BORDER_WIDTH, getDefaultBorderWidth());
	}

	/**
	 * Sets the border width.
	 * 
	 * @param borderWidth the border width.
	 */
	public final void setBorderWidth(int borderWidth) {
		setValue(Property.BORDER_WIDTH, Checker.positiveOrZero(borderWidth));
	}

	/**
	 * Returns <code>true</code> if the border color is defined as color.
	 * 
	 * @return <code>true</code> if the border color is defined as color
	 */
	public final boolean isBorderColorAsColor() {
		return isType(Property.BORDER_COLOR, ObjectType.STRING);
	}

	/**
	 * Returns <code>true</code> if the border color is defined as gradient.
	 * 
	 * @return <code>true</code> if the border color is defined as gradient
	 */
	public final boolean isBorderColorAsGradient() {
		return JsItemsHelper.get().isCanvasGradient(this.getNativeObject(), Property.BORDER_COLOR);
	}

	/**
	 * Returns the color of the dataset item border
	 *
	 * @return the color of the dataset item border.
	 */
	public final String getBorderColorAsString() {
		return getValue(Property.BORDER_COLOR, Defaults.get().getGlobal().getBorderColorAsString());
	}

	/**
	 * Returns the color of the dataset item border
	 *
	 * @return the color of the dataset item border
	 */
	public final IsColor getBorderColor() {
		return ColorBuilder.parse(getBorderColorAsString());
	}

	/**
	 * Returns the border color as gradient.
	 * 
	 * @return the border color or <code>null</code> if is not a gradient
	 */
	public final Gradient getBorderColorAsGradient() {
		return GradientBuilder.retrieve(getBackgroundColorAsCanvasGradient());
	}

	/**
	 * Returns the border color as canvas gradient.
	 * 
	 * @return the border color or <code>null</code> if is not a canvas gradient
	 */
	public final CanvasGradientItem getBorderColorAsCanvasGradient() {
		// checks if the border color has been set as gradient
		if (isBorderColorAsGradient()) {
			return getValue(Property.BORDER_COLOR, (CanvasGradientItem) null);
		}
		// if here, is not a color then returns null
		return null;
	}

	/**
	 * Sets the border color.
	 * 
	 * @param borderColor the border color.
	 */
	public final void setBorderColor(IsColor borderColor) {
		setBorderColor(IsColor.checkAndGetValue(borderColor));
	}

	/**
	 * Sets the border color.
	 * 
	 * @param borderColor the border color.
	 */
	public final void setBorderColor(String borderColor) {
		setValue(Property.BORDER_COLOR, borderColor);
	}

	/**
	 * Sets the border color as gradient.
	 * 
	 * @param borderColor the border color as gradient.
	 */
	public final void setBorderColor(CanvasGradientItem borderColor) {
		setValue(Property.BORDER_COLOR, borderColor);
	}

	/**
	 * Create new {@link TooltipLabelColor} filling it with background and border color of dataset element.
	 * 
	 * @return new {@link TooltipLabelColor} filling it with background and border color of dataset element
	 */
	public final TooltipLabelColor createTooltipLabelColor() {
		// creates an empty label color
		TooltipLabelColor result = new TooltipLabelColor();
		// loads background color
		loadBackgroundColor(result);
		// loads background color
		loadBorderColor(result);
		return result;
	}

	/**
	 * Returns if the point passed as the argument is in the element.
	 *
	 * @param x point X value
	 * @param y point Y value
	 * @param useFinalPosition if the position must be calculated with final dimensions or also during the animation.
	 * @return <code>true</code> if the point is in the element
	 */
	public final boolean inRange(double x, double y, boolean useFinalPosition) {
		return JsItemsHelper.get().inRange(getNativeObject(), x, y, useFinalPosition);
	}

	/**
	 * Returns if the point X passed as the argument is in the element.
	 *
	 * @param x point X value
	 * @param useFinalPosition if the position must be calculated with final dimensions or also during the animation.
	 * @return <code>true</code> if the point X is in the element
	 */
	public final boolean inXRange(double x, boolean useFinalPosition) {
		return JsItemsHelper.get().inXRange(getNativeObject(), x, useFinalPosition);
	}

	/**
	 * Returns if the point Y passed as the argument is in the element.
	 *
	 * @param y point Y value
	 * @param useFinalPosition if the position must be calculated with final dimensions or also during the animation.
	 * @return <code>true</code> if the point Y is in the element
	 */
	public final boolean inYRange(double y, boolean useFinalPosition) {
		return JsItemsHelper.get().inYRange(getNativeObject(), y, useFinalPosition);
	}

	/**
	 * Loads the background color in the {@link TooltipLabelColor} instance.
	 * 
	 * @param labelColor {@link TooltipLabelColor} instance to be filled
	 */
	private void loadBackgroundColor(TooltipLabelColor labelColor) {
		// checks the type of background color
		if (isBackgroundColorAsPattern()) {
			// --- PATTERN
			labelColor.setBackgroundColor(getBackgroundColorAsCanvasPattern());
		} else if (isBackgroundColorAsGradient()) {
			// --- GRADIENT
			labelColor.setBackgroundColor(getBackgroundColorAsCanvasGradient());
		} else {
			// --- COLOR
			labelColor.setBackgroundColor(getBackgroundColorAsString());
		}
	}

	/**
	 * Loads the border color in the {@link TooltipLabelColor} instance.
	 * 
	 * @param labelColor {@link TooltipLabelColor} instance to be filled
	 */
	private void loadBorderColor(TooltipLabelColor labelColor) {
		// checks the type of border color
		if (isBorderColorAsGradient()) {
			// --- GRADIENT
			labelColor.setBorderColor(getBorderColorAsCanvasGradient());
		} else {
			// --- COLOR
			labelColor.setBorderColor(getBorderColorAsString());
		}
	}

}