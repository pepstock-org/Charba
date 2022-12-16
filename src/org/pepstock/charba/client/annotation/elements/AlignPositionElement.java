/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.annotation.elements;

import org.pepstock.charba.client.annotation.enums.LabelPosition;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.items.Undefined;
import org.pepstock.charba.client.utils.Utilities;

/**
 * This object is to set the position of a label, setting the horizontal and vertical position.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class AlignPositionElement extends NativeObjectContainer {

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

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 */
	AlignPositionElement(NativeObject nativeObject) {
		super(nativeObject);
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
		setValue(Property.X, Utilities.getAsPercentage(percentage, 0));
	}

	/**
	 * Sets the position of label by the percentage (value between 0 and 1) of the vertical dimension.
	 * 
	 * @param percentage the position of label by the percentage (value between 0 and 1) of the vertical dimension
	 */
	public void setYAsPercentage(double percentage) {
		setValue(Property.Y, Utilities.getAsPercentage(percentage, 0));
	}

	/**
	 * Returns the anchor position of label on horizontal dimension.
	 * 
	 * @return the anchor position of label on horizontal dimension.
	 */
	public LabelPosition getX() {
		return getValue(Property.X, LabelPosition.values(), LabelPosition.CENTER);
	}

	/**
	 * Returns the anchor position of label on vertical dimension.
	 * 
	 * @return the anchor position of label on vertical dimension.
	 */
	public LabelPosition getY() {
		return getValue(Property.Y, LabelPosition.values(), LabelPosition.CENTER);
	}

	/**
	 * Returns the position of label by the percentage (value between 0 and 1) of the horizontal dimension.
	 * 
	 * @return the position of label by the percentage (value between 0 and 1) of the horizontal dimension
	 */
	public double getXAsPercentage() {
		return Utilities.getAsPercentage(getValue(Property.X, Undefined.STRING), Undefined.DOUBLE);
	}

	/**
	 * Returns the position of label by the percentage (value between 0 and 1) of the vertical dimension.
	 * 
	 * @return the position of label by the percentage (value between 0 and 1) of the vertical dimension
	 */
	public double getYAsPercentage() {
		return Utilities.getAsPercentage(getValue(Property.Y, Undefined.STRING), Undefined.DOUBLE);
	}
}