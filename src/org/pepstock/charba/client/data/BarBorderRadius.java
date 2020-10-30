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
package org.pepstock.charba.client.data;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.globals.DefaultsBuilder;

/**
 * Defines the border radius for BAR dataset element, applies the corner radius to all corners of the rectangle (topLeft, topRight, bottomLeft, bottomRight).
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class BarBorderRadius extends AbstractBarBorderItem {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		TOP_LEFT("topLeft"),
		TOP_RIGHT("topRight"),
		BOTTOM_LEFT("bottomLeft"),
		BOTTOM_RIGHT("bottomRight");

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
	 * Creates the object with an empty native object instance.
	 */
	public BarBorderRadius() {
		this(DefaultsBuilder.get().getOptions().getElements().getBar().getBorderRadius());
	}
	
	/**
	 * Creates the object using the argument to set the border radius size to all corners of the rectangle.
	 * 
	 * @param borderRadius border radius to apply to all corners of the rectangle.
	 */
	public BarBorderRadius(int borderRadius) {
		super(borderRadius);
	}

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 */
	BarBorderRadius(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Sets the border radius size to all corners of the rectangle.
	 * 
	 * @param borderRadius border radius to apply to all corners of the rectangle.
	 */
	@Override
	public void set(int borderRadius) {
		setTopLeft(borderRadius);
		setBottomLeft(borderRadius);
		setTopRight(borderRadius);
		setBottomRight(borderRadius);
	}

	/**
	 * Sets the border radius for top-left corner of the rectangle, in pixel.
	 * 
	 * @param borderRadius the border radius for top-left corner of the rectangle, in pixel
	 */
	public void setTopLeft(int borderRadius) {
		setValue(Property.TOP_LEFT, borderRadius);
	}

	/**
	 * Returns the border radius for top-left corner of the rectangle, in pixel.
	 * 
	 * @return the border radius for top-left corner of the rectangle, in pixel.
	 */
	public int getTopLeft() {
		return getValue(Property.TOP_LEFT, Defaults.get().getGlobal().getElements().getBar().getBorderRadius());
	}

	/**
	 * Sets the border radius for top-right corner of the rectangle, in pixel.
	 * 
	 * @param borderRadius the border radius for top-right corner of the rectangle, in pixel
	 */
	public void setTopRight(int borderRadius) {
		setValue(Property.TOP_RIGHT, borderRadius);
	}

	/**
	 * Returns the border radius for top-right corner of the rectangle, in pixel.
	 * 
	 * @return the border radius for top-right corner of the rectangle, in pixel.
	 */
	public int getTopRight() {
		return getValue(Property.TOP_RIGHT, Defaults.get().getGlobal().getElements().getBar().getBorderRadius());
	}
	
	/**
	 * Sets the border radius for bottom-left corner of the rectangle, in pixel.
	 * 
	 * @param borderRadius the border radius for bottom-left corner of the rectangle, in pixel
	 */
	public void setBottomLeft(int borderRadius) {
		setValue(Property.BOTTOM_LEFT, borderRadius);
	}

	/**
	 * Returns the border radius for bottom-left corner of the rectangle, in pixel.
	 * 
	 * @return the border radius for bottom-left corner of the rectangle, in pixel.
	 */
	public int getBottomLeft() {
		return getValue(Property.BOTTOM_LEFT, Defaults.get().getGlobal().getElements().getBar().getBorderRadius());
	}

	/**
	 * Sets the border radius for bottom-right corner of the rectangle, in pixel.
	 * 
	 * @param borderRadius the border radius for bottom-right corner of the rectangle, in pixel
	 */
	public void setBottomRight(int borderRadius) {
		setValue(Property.BOTTOM_RIGHT, borderRadius);
	}

	/**
	 * Returns the border radius for bottom-right corner of the rectangle, in pixel.
	 * 
	 * @return the border radius for bottom-right corner of the rectangle, in pixel.
	 */
	public int getBottomRight() {
		return getValue(Property.BOTTOM_RIGHT, Defaults.get().getGlobal().getElements().getBar().getBorderRadius());
	}

}