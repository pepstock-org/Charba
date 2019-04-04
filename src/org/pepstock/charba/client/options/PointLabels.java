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

import java.util.List;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.defaults.IsDefaultPointLabels;

/**
 * It is used to configure the point labels that are shown on the perimeter of the scale.<br>
 * Note that these options only apply if display is <code>true</code>.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class PointLabels extends FontItem<Scale, IsDefaultPointLabels> implements IsDefaultPointLabels {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		DISPLAY("display"),
		LINE_HEIGHT("lineHeight");

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
	 * @param scale scale/axis of this object.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	PointLabels(Scale scale, Key childKey, IsDefaultPointLabels defaultValues, NativeObject nativeObject) {
		super(scale, childKey, defaultValues, nativeObject);
	}

	/**
	 * If <code>true</code>, labels are shown.
	 * 
	 * @param display if <code>true</code>, labels are shown.
	 */
	public void setDisplay(boolean display) {
		setValue(Property.DISPLAY, display);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If <code>true</code>, labels are shown.
	 * 
	 * @return if <code>true</code>, labels are shown.
	 */
	public boolean isDisplay() {
		return getValue(Property.DISPLAY, getDefaultValues().isDisplay());
	}

	/**
	 * Sets the height of an individual line of text.
	 * 
	 * @param lineHeight height of an individual line of text.
	 */
	public void setLineHeight(double lineHeight) {
		setValue(Property.LINE_HEIGHT, lineHeight);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Sets the height of an individual line of text.
	 * 
	 * @param lineHeight height of an individual line of text.
	 */
	public void setLineHeight(String lineHeight) {
		setValue(Property.LINE_HEIGHT, lineHeight);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the height of an individual line of text.
	 * 
	 * @return the height of an individual line of text.
	 */
	public double getLineHeight() {
		// creates default
		double defaultValue = getDefaultValues().getLineHeight();
		// checks type if number
		if (ObjectType.NUMBER.equals(type(Property.LINE_HEIGHT))) {
			// reads and returns as double
			return getValue(Property.LINE_HEIGHT, defaultValue);
		}
		// if here, is not a number
		// then returns the default
		return defaultValue;
	}

	/**
	 * Returns the height of an individual line of text.
	 * 
	 * @return the height of an individual line of text.
	 */
	public String getLineHeightAsString() {
		// creates default
		String defaultValue = String.valueOf(getDefaultValues().getLineHeight());
		// checks type if string
		if (ObjectType.STRING.equals(type(Property.LINE_HEIGHT))) {
			// reads and returns as string
			return getValue(Property.LINE_HEIGHT, defaultValue);
		}
		// if here, is not a number
		// then returns the default
		return defaultValue;
	}

	/**
	 * Sets an array of font colors.
	 * 
	 * @param fontColors an array of font colors
	 */
	public void setFontColor(IsColor... fontColors) {
		// stores value
		setValueOrArray(FontItem.Property.FONT_COLOR, fontColors);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Sets an array of font colors.
	 * 
	 * @param fontColors an array of font colors
	 */
	public void setFontColor(String... fontColors) {
		// stores value
		setValueOrArray(FontItem.Property.FONT_COLOR, fontColors);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.FontItem#getFontColorAsString()
	 */
	@Override
	public String getFontColorAsString() {
		// gets list of colors
		List<String> colors = getFontColorsAsString();
		// if list is empty, retruns default otherwise the first color
		return colors.isEmpty() ? getDefaultValues().getFontColorAsString() : colors.get(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.FontItem#getFontColor()
	 */
	@Override
	public IsColor getFontColor() {
		return ColorBuilder.parse(getFontColorAsString());
	}

	/**
	 * Returns the font colors as list.
	 * 
	 * @return Font color as list
	 */
	public List<String> getFontColorsAsString() {
		// returns list of colors
		ArrayString array = getValueOrArray(FontItem.Property.FONT_COLOR, getDefaultValues().getFontColorAsString());
		return ArrayListHelper.list(array);
	}

	/**
	 * Returns the font colors as list.
	 * 
	 * @return Font color as list
	 */
	public List<IsColor> getFontColors() {
		return ColorBuilder.parse(getFontColorsAsString());
	}

}