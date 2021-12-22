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
package org.pepstock.charba.client.annotation;

import org.pepstock.charba.client.annotation.enums.LabelPosition;
import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.items.Undefined;

/**
 * This object is to set the position of a label, setting the horizontal and vertical position.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class AlignPosition extends AbstractNode implements IsDefaultsAlignPosition {

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

	// default value instance
	private final IsDefaultsAlignPosition defaultValues;

	/**
	 * Creates an empty object. Default position is {@link LabelPosition#CENTER}.
	 */
	public AlignPosition() {
		this(LabelPosition.CENTER);
	}

	/**
	 * Creates an object with X position. The vertical position is the same of the passed for horizontal.
	 * 
	 * @param x the X position of the label.
	 */
	public AlignPosition(LabelPosition x) {
		this(x, x);
	}

	/**
	 * Creates an object with X and Y positions for the label.
	 * 
	 * @param x the X position of the label
	 * @param y the Y position of the label
	 */
	public AlignPosition(LabelPosition x, LabelPosition y) {
		this(null, null, null, DefaultAlignPosition.INSTANCE);
		// stores the values
		setX(x);
		setY(y);
	}

	/**
	 * Creates an object with X position. The vertical position is the same of the passed for horizontal.
	 * 
	 * @param x the X position of the label.
	 */
	public AlignPosition(double x) {
		this(x, x);
	}

	/**
	 * Creates an object with X and Y positions for the label.
	 * 
	 * @param x the X position of the label
	 * @param y the Y position of the label
	 */
	public AlignPosition(double x, double y) {
		this(null, null, null, DefaultAlignPosition.INSTANCE);
		// stores the values
		setXAsPercentage(x);
		setYAsPercentage(y);
	}

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param parent parent node to use to add this element where changed
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param nativeObject native object to map java script properties
	 * @param defaultValues default values instance
	 */
	AlignPosition(AbstractNode parent, Key childKey, NativeObject nativeObject, IsDefaultsAlignPosition defaultValues) {
		super(parent, childKey, nativeObject);
		// checks and stores default
		this.defaultValues = checkDefaultValuesArgument(defaultValues);
	}

	/**
	 * Sets the anchor position of label on horizontal dimension.
	 * 
	 * @param position the anchor position of label on horizontal dimension
	 */
	public void setX(LabelPosition position) {
		setValue(Property.X, position);
	}

	/**
	 * Sets the anchor position of label on vertical dimension.
	 * 
	 * @param position the anchor position of label on vertical dimension
	 */
	public void setY(LabelPosition position) {
		setValue(Property.Y, position);
	}

	/**
	 * Sets the position of label by the percentage (value between 0 and 1) of the horizontal dimension.
	 * 
	 * @param percentage the position of label by the percentage (value between 0 and 1) of the horizontal dimension
	 */
	public void setXAsPercentage(double percentage) {
		setValue(Property.X, LabelPosition.getAsPercentage(percentage));
	}

	/**
	 * Sets the position of label by the percentage (value between 0 and 1) of the vertical dimension.
	 * 
	 * @param percentage the position of label by the percentage (value between 0 and 1) of the vertical dimension
	 */
	public void setYAsPercentage(double percentage) {
		setValue(Property.Y, LabelPosition.getAsPercentage(percentage));
	}

	/**
	 * Returns the anchor position of label on horizontal dimension.
	 * 
	 * @return the anchor position of label on horizontal dimension.
	 */
	@Override
	public LabelPosition getX() {
		return getValue(Property.X, LabelPosition.values(), defaultValues.getX());
	}

	/**
	 * Returns the anchor position of label on vertical dimension.
	 * 
	 * @return the anchor position of label on vertical dimension.
	 */
	@Override
	public LabelPosition getY() {
		return getValue(Property.Y, LabelPosition.values(), defaultValues.getY());
	}

	/**
	 * Returns the position of label by the percentage (value between 0 and 1) of the horizontal dimension.
	 * 
	 * @return the position of label by the percentage (value between 0 and 1) of the horizontal dimension
	 */
	@Override
	public double getXAsPercentage() {
		return LabelPosition.getAsPercentage(getValue(Property.X, Undefined.STRING), defaultValues.getXAsPercentage());
	}

	/**
	 * Returns the position of label by the percentage (value between 0 and 1) of the vertical dimension.
	 * 
	 * @return the position of label by the percentage (value between 0 and 1) of the vertical dimension
	 */
	@Override
	public double getYAsPercentage() {
		return LabelPosition.getAsPercentage(getValue(Property.Y, Undefined.STRING), defaultValues.getYAsPercentage());
	}

	/**
	 * Returns the native object instance.
	 * 
	 * @return the native object instance.
	 */
	NativeObject nativeObject() {
		return super.getNativeObject();
	}
}