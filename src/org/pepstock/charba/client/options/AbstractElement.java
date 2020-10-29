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
import org.pepstock.charba.client.defaults.IsDefaultOptionsElement;

/**
 * Options can be configured for four different types of elements: arc, lines, points, and bars.<br>
 * When set, these options apply to all objects of that type unless specifically overridden by the configuration attached to a dataset.
 * 
 * @author Andrea "Stock" Stocchero
 *
 * @param <D> defaults provider class
 */
public abstract class AbstractElement<D extends IsDefaultOptionsElement> extends AbstractModel<Elements, D> {

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
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param elements elements node of the chart options.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	AbstractElement(Elements elements, Key childKey, D defaultValues, NativeObject nativeObject) {
		super(elements, childKey, defaultValues, nativeObject);
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
		setValueAndAddToParent(Property.BACKGROUND_COLOR, backgroundColor);
	}

	/**
	 * Returns the background color.
	 * 
	 * @return the background color.
	 */
	public final String getBackgroundColorAsString() {
		return getValue(Property.BACKGROUND_COLOR, getDefaultValues().getBackgroundColorAsString());
	}

	/**
	 * Returns the background color.
	 * 
	 * @return the background color.
	 */
	public final IsColor getBackgroundColor() {
		return ColorBuilder.parse(getBackgroundColorAsString());
	}

	/**
	 * Sets the border width.
	 * 
	 * @param borderWidth the border width.
	 */
	public final void setBorderWidth(int borderWidth) {
		setValueAndAddToParent(Property.BORDER_WIDTH, borderWidth);
	}

	/**
	 * Returns the border width.
	 * 
	 * @return the border width.
	 */
	public final int getBorderWidth() {
		return getValue(Property.BORDER_WIDTH, getDefaultValues().getBorderWidth());
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
		setValueAndAddToParent(Property.BORDER_COLOR, borderColor);
	}

	/**
	 * Returns the border color.
	 * 
	 * @return the border color.
	 */
	public final String getBorderColorAsString() {
		return getValue(Property.BORDER_COLOR, getDefaultValues().getBorderColorAsString());
	}

	/**
	 * Returns the border color.
	 * 
	 * @return the border color.
	 */
	public final IsColor getBorderColor() {
		return ColorBuilder.parse(getBorderColorAsString());
	}

}