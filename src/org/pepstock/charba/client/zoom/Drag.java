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
package org.pepstock.charba.client.zoom;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;

/**
 * Configuration item to define the style to apply to drag area, during zooming.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Drag extends NativeObjectContainer implements IsDefaultDrag {

	/**
	 * Default background color, <b>{@value DEFAULT_BACKGROUND_COLOR}</b>.
	 */
	public static final String DEFAULT_BACKGROUND_COLOR = "rgba(225,225,225,0.3)";

	/**
	 * Default border color, <b>{@value DEFAULT_BORDER_COLOR}</b>.
	 */
	public static final String DEFAULT_BORDER_COLOR = "rgb(225,225,225)";

	/**
	 * Default border width, <b>{@value DEFAULT_BORDER_WIDTH}</b>.
	 */
	public static final int DEFAULT_BORDER_WIDTH = 0;

	/**
	 * Default animation duration, <b>{@value DEFAULT_ANIMATION_DURATION}</b>.
	 */
	public static final int DEFAULT_ANIMATION_DURATION = 0;

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		BACKGROUND_COLOR("backgroundColor"),
		BORDER_COLOR("borderColor"),
		BORDER_WIDTH("borderWidth"),
		ANIMATION_DURATION("animationDuration");

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

	// defaults global drag options instance
	private IsDefaultDrag defaultOptions;

	/**
	 * Creates new range element, using the default values options.
	 * 
	 * @param defaultOptions default DRAG options to returns the default when required.
	 */
	Drag(IsDefaultDrag defaultOptions) {
		this(null, defaultOptions);
	}

	/**
	 * Creates new range element, using stored native object instance and the default values options.
	 * 
	 * @param nativeObject stored range values in the native object to read.
	 * @param defaultOptions default DRAG options to returns the default when required.
	 */
	Drag(NativeObject nativeObject, IsDefaultDrag defaultOptions) {
		super(nativeObject);
		this.defaultOptions = defaultOptions;
	}

	/**
	 * Sets the fill color during dragging.
	 * 
	 * @param backgroundColor the fill color during dragging
	 */
	public void setBackgroundColor(IsColor backgroundColor) {
		setBackgroundColor(IsColor.checkAndGetValue(backgroundColor));
	}

	/**
	 * Sets the fill color during dragging.
	 * 
	 * @param backgroundColor the fill color during dragging
	 */
	public void setBackgroundColor(String backgroundColor) {
		// stores value
		setValue(Property.BACKGROUND_COLOR, backgroundColor);
	}

	/**
	 * Returns the fill color during dragging.
	 * 
	 * @return the fill color during dragging
	 */
	@Override
	public String getBackgroundColorAsString() {
		// returns color as string
		return getValue(Property.BACKGROUND_COLOR, defaultOptions.getBackgroundColorAsString());
	}

	/**
	 * Returns the fill color during dragging.
	 * 
	 * @return the fill color during dragging
	 */
	public IsColor getBackgroundColor() {
		return ColorBuilder.parse(getBackgroundColorAsString());
	}

	/**
	 * Sets the color of the border during dragging.
	 * 
	 * @param borderColor the color of the border during dragging
	 */
	public void setBorderColor(IsColor borderColor) {
		setBorderColor(IsColor.checkAndGetValue(borderColor));
	}

	/**
	 * Sets the color of the border during dragging.
	 * 
	 * @param borderColor the color of the border during dragging
	 */
	public void setBorderColor(String borderColor) {
		// stores value
		setValue(Property.BORDER_COLOR, borderColor);
	}

	/**
	 * Returns the color of the border during dragging.
	 * 
	 * @return the color of the border during dragging
	 */
	@Override
	public String getBorderColorAsString() {
		// returns color as string
		return getValue(Property.BORDER_COLOR, defaultOptions.getBorderColorAsString());
	}

	/**
	 * Returns the color of the border during dragging.
	 * 
	 * @return the color of the border during dragging
	 */
	public IsColor getBorderColor() {
		return ColorBuilder.parse(getBorderColorAsString());
	}

	/**
	 * Sets the width of the border in pixels.
	 * 
	 * @param borderWidth the width of the border in pixels.
	 */
	public void setBorderWidth(int borderWidth) {
		// stores value
		setValue(Property.BORDER_WIDTH, borderWidth);
	}

	/**
	 * Returns the width of the border in pixels.
	 * 
	 * @return the width of the border in pixels.
	 */
	@Override
	public int getBorderWidth() {
		return getValue(Property.BORDER_WIDTH, defaultOptions.getBorderWidth());
	}

	/**
	 * Sets the number of milliseconds an animation takes.
	 * 
	 * @param milliseconds the number of milliseconds an animation takes.
	 */
	public void setAnimationDuration(int milliseconds) {
		setValue(Property.ANIMATION_DURATION, milliseconds);
	}

	/**
	 * Returns the number of milliseconds an animation takes.
	 * 
	 * @return the number of milliseconds an animation takes.
	 */
	@Override
	public int getAnimationDuration() {
		return getValue(Property.ANIMATION_DURATION, defaultOptions.getAnimationDuration());
	}

}
