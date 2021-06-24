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
package org.pepstock.charba.client.geo;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;

/**
 * Defines the margin pixels for legend such that it doesn't stick to the edge of the chart .
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class Margin extends NativeObjectContainer {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		TOP("top"),
		RIGHT("right"),
		BOTTOM("bottom"),
		LEFT("left");

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
	 * Creates an empty margin object.
	 */
	public Margin() {
		this(null);
	}

	/**
	 * Creates an margin setting top-bottom and left-right initial values.
	 * 
	 * @param topBottom a values to set top and bottom dimensions
	 * @param leftRight a values to set left and right dimensions
	 */
	public Margin(int topBottom, int leftRight) {
		this(topBottom, leftRight, topBottom, leftRight);
	}

	/**
	 * Creates an margin setting top-bottom and left-right initial values.
	 * 
	 * @param top a values to set top dimension
	 * @param right a values to set right dimension
	 * @param bottom a values to set bottom dimension
	 * @param left a values to set left dimension
	 */
	public Margin(int top, int right, int bottom, int left) {
		this(null);
		// sets values
		setTop(top);
		setRight(right);
		setBottom(bottom);
		setLeft(left);
	}

	/**
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	Margin(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Sets the margin top of legend.
	 * 
	 * @param top the margin top of legend.
	 */
	public void setTop(int top) {
		setValue(Property.TOP, top);
	}

	/**
	 * Returns the margin top of legend.
	 * 
	 * @return the margin top of legend.
	 */
	public int getTop() {
		return getValue(Property.TOP, Legend.DEFAULT_MARGIN);
	}

	/**
	 * Sets the margin right of legend.
	 * 
	 * @param right the margin right of legend.
	 */
	public void setRight(int right) {
		setValue(Property.RIGHT, right);
	}

	/**
	 * Returns the margin right of legend.
	 * 
	 * @return the margin right of legend.
	 */
	public int getRight() {
		return getValue(Property.RIGHT, Legend.DEFAULT_MARGIN);
	}

	/**
	 * Sets the margin bottom of legend.
	 * 
	 * @param bottom the margin bottom of legend.
	 */
	public void setBottom(int bottom) {
		setValue(Property.BOTTOM, bottom);
	}

	/**
	 * Returns the margin bottom of legend.
	 * 
	 * @return the margin bottom of legend.
	 */
	public int getBottom() {
		return getValue(Property.BOTTOM, Legend.DEFAULT_MARGIN);
	}

	/**
	 * Sets the margin left of legend.
	 * 
	 * @param left the margin left of legend.
	 */
	public void setLeft(int left) {
		setValue(Property.LEFT, left);
	}

	/**
	 * Returns the margin left of legend.
	 * 
	 * @return the margin left of legend.
	 */
	public int getLeft() {
		return getValue(Property.LEFT, Legend.DEFAULT_MARGIN);
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