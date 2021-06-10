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

import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.PropertyHandler;
import org.pepstock.charba.client.defaults.IsDefaultFontContainer;

/**
 * This is the labels configuration of the legend.
 * 
 * @author Andrea "Stock" Stocchero
 */
final class FontContainer extends PropertyHandler<IsDefaultFontContainer> {

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		COLOR("color"),
		FONT("font");

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

	// instance of font
	private final Font font;

	/**
	 * Creates a font container with the native object where font properties must be managed and the default value to use when the property does not exist.
	 * 
	 * @param parent model which contains the font.
	 * @param defaultValues default value of font and color to use when the properties do not exist
	 * @param nativeObject native object where font properties must be managed
	 */
	protected FontContainer(AbstractNode parent, IsDefaultFontContainer defaultValues, NativeObject nativeObject) {
		super(parent, defaultValues, nativeObject);
		this.font = new Font(parent, Property.FONT, getDefaultValues().getFont(), getValue(Property.FONT));
	}

	/**
	 * Returns the font element.
	 * 
	 * @return the font
	 */
	Font getFont() {
		return font;
	}

	/**
	 * Sets the font color.
	 * 
	 * @param color font color.
	 */
	void setColor(String color) {
		setValueAndAddToParent(Property.COLOR, color);
	}

	/**
	 * Returns the font color as string.
	 * 
	 * @return font color as string
	 */
	String getColorAsString() {
		return getValue(Property.COLOR, getDefaultValues().getColorAsString());
	}

}