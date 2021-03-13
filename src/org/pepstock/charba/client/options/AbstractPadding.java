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
import org.pepstock.charba.client.defaults.IsDefaultPadding;

/**
 * This is abstract padidng object element of the chart options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class AbstractPadding  extends NativeObjectContainer implements IsPadding {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		PADDING("padding");

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
	
	// delegated padding
	private final Padding padding;
	
	/**
	 * Creates an empty padding to use for chart configuration when the font is created by a callback.
	 * 
	 * @param defaultValues default provider
	 */
	protected AbstractPadding(IsDefaultPadding defaultValues) {
		this(defaultValues, null);
	}

	/**
	 * Creates a padding to use for chart configuration when the font is created by a callback, using a clone of another font object.
	 * 
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	protected AbstractPadding(IsDefaultPadding defaultValues, NativeObject nativeObject) {
		super(nativeObject);
		// creates a padding to wrap
		this.padding = new Padding(null, Property.PADDING, defaultValues, getNativeObject());
	}
	
	/**
	 * Sets the padding size to all dimensions.
	 * 
	 * @param value padding size to apply to all dimensions.
	 */
	@Override
	public final void set(int value) {
		padding.set(value);
	}

	/**
	 * Sets the padding left in pixel.
	 * 
	 * @param value the padding left in pixel.
	 */
	@Override
	public final void setLeft(int value) {
		padding.setLeft(value);
	}

	/**
	 * Returns the padding left in pixel.
	 * 
	 * @return the padding left in pixel.
	 */
	@Override
	public final int getLeft() {
		return padding.getLeft();
	}

	/**
	 * Sets the padding right in pixel.
	 * 
	 * @param value the padding right in pixel.
	 */
	@Override
	public final void setRight(int value) {
		padding.setRight(value);
	}

	/**
	 * Returns the padding right in pixel.
	 * 
	 * @return the padding right in pixel.
	 */
	@Override
	public final int getRight() {
		return padding.getRight();
	}

	/**
	 * Sets the padding top in pixel.
	 * 
	 * @param value the padding top in pixel.
	 */
	@Override
	public final void setTop(int value) {
		padding.setTop(value);
	}

	/**
	 * Returns the padding top in pixel.
	 * 
	 * @return the padding top in pixel.
	 */
	@Override
	public final int getTop() {
		return padding.getTop();
	}

	/**
	 * Sets the padding bottom in pixel.
	 * 
	 * @param value the padding bottom in pixel.
	 */
	@Override
	public final void setBottom(int value) {
		padding.setBottom(value);
	}

	/**
	 * Returns the padding bottom in pixel.
	 * 
	 * @return the padding bottom in pixel.
	 */
	@Override
	public final int getBottom() {
		return padding.getBottom();
	}
}