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
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultPadding;
import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.items.Undefined;

/**
 * Maps the additional space to apply to the sides of elements (left, top, right, bottom), in pixels.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Padding extends AbstractNode implements IsPadding {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		X("x"),
		Y("y");

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

	// default font values
	private final IsDefaultPadding defaultValues;

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param parent parent of the font.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 * @param originalPadding if the padding is stored in the parent object as number (CHART.JS uses this configuration), the parent provides the values or
	 *            {@link Undefined#INTEGER}.
	 */
	Padding(AbstractNode parent, Key childKey, IsDefaultPadding defaultValues, NativeObject nativeObject, int originalPadding) {
		super(parent, childKey, nativeObject);
		// checks if default value is consistent
		// stores defaults values
		this.defaultValues = checkDefaultValuesArgument(defaultValues);
		// checks if the padding was stored as number
		// this could happen in the default of chart.js
		if (Undefined.isNot(originalPadding)) {
			// stores the values to all dimensions
			set(originalPadding);
		} else if (nativeObject != null && !keys().isEmpty()) {
			// checks if the padding was stored as object with X and Y
			// this could happen in the default of chart.js
			// normalizes for X
			if (has(Property.X)) {
				// stores in left and right
				setX(getValue(Property.X, Undefined.INTEGER));
				// removes X property
				remove(Property.X);
			}
			// normalizes for Y
			if (has(Property.Y)) {
				// stores in top and bottom
				setX(getValue(Property.Y, Undefined.INTEGER));
				// removes Y property
				remove(Property.Y);
			}
		} else {
			// if here the object is empty then
			// it applies the defaults
			// sets defaults
			setDefaultPadding(Position.TOP, defaultValues.getTop());
			setDefaultPadding(Position.BOTTOM, defaultValues.getBottom());
			setDefaultPadding(Position.LEFT, defaultValues.getLeft());
			setDefaultPadding(Position.RIGHT, defaultValues.getRight());
		}
	}

	/**
	 * Sets the padding left in pixel.
	 * 
	 * @param padding the padding left in pixel.
	 */
	@Override
	public void setLeft(int padding) {
		setValueAndAddToParent(Position.LEFT, Checker.positiveOrZero(padding));
	}

	/**
	 * Returns the padding left in pixel.
	 * 
	 * @return the padding left in pixel.
	 */
	@Override
	public int getLeft() {
		return getValue(Position.LEFT, defaultValues.getLeft());
	}

	/**
	 * Sets the padding right in pixel.
	 * 
	 * @param padding the padding right in pixel.
	 */
	@Override
	public void setRight(int padding) {
		setValueAndAddToParent(Position.RIGHT, Checker.positiveOrZero(padding));
	}

	/**
	 * Returns the padding right in pixel.
	 * 
	 * @return the padding right in pixel.
	 */
	@Override
	public int getRight() {
		return getValue(Position.RIGHT, defaultValues.getRight());
	}

	/**
	 * Sets the padding top in pixel.
	 * 
	 * @param padding the padding top in pixel.
	 */
	@Override
	public void setTop(int padding) {
		setValueAndAddToParent(Position.TOP, Checker.positiveOrZero(padding));
	}

	/**
	 * Returns the padding top in pixel.
	 * 
	 * @return the padding top in pixel.
	 */
	@Override
	public int getTop() {
		return getValue(Position.TOP, defaultValues.getTop());
	}

	/**
	 * Sets the padding bottom in pixel.
	 * 
	 * @param padding the padding bottom in pixel.
	 */
	@Override
	public void setBottom(int padding) {
		setValueAndAddToParent(Position.BOTTOM, Checker.positiveOrZero(padding));
	}

	/**
	 * Returns the padding bottom in pixel.
	 * 
	 * @return the padding bottom in pixel.
	 */
	@Override
	public int getBottom() {
		return getValue(Position.BOTTOM, defaultValues.getBottom());
	}

	/**
	 * Sets the default padding in the embedded JavaScript object at specific dimensions.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param padding padding to be set
	 */
	private void setDefaultPadding(Key key, int padding) {
		// checks if values is consistent
		if (Undefined.isNot(padding)) {
			setValue(key, Checker.positiveOrZero(padding));
		}
	}

	/**
	 * Returns the native object instance.
	 * 
	 * @return the native object instance.
	 */
	NativeObject nativeObject() {
		return getNativeObject();
	}

}