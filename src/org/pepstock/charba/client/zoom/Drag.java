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
import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.enums.ModifierKey;

/**
 * Configuration item to define the style to apply to drag area, during zooming.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Drag extends AbstractNode implements IsDefaultDrag {

	/**
	 * Default enabled, <b>{@value DEFAULT_ENABLED}</b>.
	 */
	public static final boolean DEFAULT_ENABLED = false;

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
	 * Default threshold, <b>{@value DEFAULT_THRESHOLD}</b>.
	 */
	public static final double DEFAULT_THRESHOLD = 0D;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		BACKGROUND_COLOR("backgroundColor"),
		BORDER_COLOR("borderColor"),
		BORDER_WIDTH("borderWidth"),
		ENABLED("enabled"),
		MODIFIER_KEY("modifierKey"),
		THRESHOLD("threshold");

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
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param parent parent node to use to add this element where changed
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultOptions default drag options to returns the default when required.
	 * @param nativeObject native object to map java script properties
	 */
	Drag(AbstractNode parent, Key childKey, IsDefaultDrag defaultOptions, NativeObject nativeObject) {
		super(parent, childKey, nativeObject);
		// checks if defaults options is consistent
		// stores defaults options
		this.defaultOptions = checkDefaultValuesArgument(defaultOptions);
	}

	/**
	 * Sets <code>true</code> to enable element for drag zooming.
	 * 
	 * @param enabled <code>true</code> to enable element for drag zooming
	 */
	public void setEnabled(boolean enabled) {
		setValueAndAddToParent(Property.ENABLED, enabled);
	}

	/**
	 * Returns <code>true</code> to enable element for drag zooming.
	 * 
	 * @return <code>true</code> to enable element for drag zooming
	 */
	@Override
	public boolean isEnabled() {
		return getValue(Property.ENABLED, defaultOptions.isEnabled());
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
		setValueAndAddToParent(Property.BACKGROUND_COLOR, backgroundColor);
	}

	/**
	 * Returns the fill color during dragging.
	 * 
	 * @return the fill color during dragging
	 */
	@Override
	public String getBackgroundColorAsString() {
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
		setValueAndAddToParent(Property.BORDER_COLOR, borderColor);
	}

	/**
	 * Returns the color of the border during dragging.
	 * 
	 * @return the color of the border during dragging
	 */
	@Override
	public String getBorderColorAsString() {
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
		setValueAndAddToParent(Property.BORDER_WIDTH, Checker.positiveOrZero(borderWidth));
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
	 * Sets the minimal zoom distance required before actually applying zoom.
	 * 
	 * @param threshold the minimal zoom distance required before actually applying zoom
	 */
	public void setThreshold(double threshold) {
		setValueAndAddToParent(Property.THRESHOLD, Checker.positiveOrZero(threshold));
	}

	/**
	 * Returns the minimal zoom distance required before actually applying zoom.
	 * 
	 * @return the minimal zoom distance required before actually applying zoom
	 */
	@Override
	public double getThreshold() {
		return getValue(Property.THRESHOLD, defaultOptions.getThreshold());
	}

	/**
	 * Sets the modifier key to activate drag-to-zoom.
	 * 
	 * @param modifierKey the modifier key to activate drag-to-zoom
	 */
	public void setModifierKey(ModifierKey modifierKey) {
		setValueAndAddToParent(Property.MODIFIER_KEY, modifierKey);
	}

	/**
	 * Returns the modifier key to activate drag-to-zoom.
	 * 
	 * @return the modifier key to activate drag-to-zoom
	 */
	@Override
	public ModifierKey getModifierKey() {
		return getValue(Property.MODIFIER_KEY, ModifierKey.values(), defaultOptions.getModifierKey());
	}

}
