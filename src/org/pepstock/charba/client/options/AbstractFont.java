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

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.defaults.IsDefaultFont;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.enums.Weight;

/**
 * Object can be provided with additional configuration by callbacks to define font options at runtime.
 * 
 * @author Andrea "Stock" Stocchero
 */
public abstract class AbstractFont extends NativeObjectContainer implements IsFont {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		FONT("font");

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

	// delegated animation mode
	private final Font font;

	/**
	 * Creates an empty font to use for chart configuration when the font is created by a callback.
	 * 
	 * @param defaultValues default provider
	 */
	protected AbstractFont(IsDefaultFont defaultValues) {
		this(defaultValues, null);
	}

	/**
	 * Creates a font to use for chart configuration when the font is created by a callback, using a clone of another font object.
	 * 
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	protected AbstractFont(IsDefaultFont defaultValues, NativeObject nativeObject) {
		super(nativeObject);
		// creates a animation to wrap
		this.font = new Font(null, Property.FONT, defaultValues, getNativeObject());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultFont#getSize()
	 */
	@Override
	public int getSize() {
		return font.getSize();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultFont#getStyle()
	 */
	@Override
	public FontStyle getStyle() {
		return font.getStyle();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultFont#getFamily()
	 */
	@Override
	public String getFamily() {
		return font.getFamily();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultFont#getWeight()
	 */
	@Override
	public Weight getWeight() {
		return font.getWeight();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultFont#getLineHeight()
	 */
	@Override
	public double getLineHeight() {
		return font.getLineHeight();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.IsFont#setSize(int)
	 */
	@Override
	public void setSize(int size) {
		font.setSize(size);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.IsFont#setStyle(org.pepstock.charba.client.enums.FontStyle)
	 */
	@Override
	public void setStyle(FontStyle style) {
		font.setStyle(style);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.IsFont#setFamily(java.lang.String)
	 */
	@Override
	public void setFamily(String family) {
		font.setFamily(family);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.IsFont#setWeight(org.pepstock.charba.client.enums.Weight)
	 */
	@Override
	public void setWeight(Weight weight) {
		font.setWeight(weight);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.IsFont#setLineHeight(double)
	 */
	@Override
	public void setLineHeight(double lineHeight) {
		font.setLineHeight(lineHeight);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.IsFont#setLineHeight(java.lang.String)
	 */
	@Override
	public void setLineHeight(String lineHeight) {
		font.setLineHeight(lineHeight);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.IsFont#getLineHeightAsString()
	 */
	@Override
	public String getLineHeightAsString() {
		return font.getLineHeightAsString();
	}

	/**
	 * Returns the native object instance.
	 * 
	 * @return the native object instance.
	 */
	protected final NativeObject getObject() {
		return font.nativeObject();
	}

}