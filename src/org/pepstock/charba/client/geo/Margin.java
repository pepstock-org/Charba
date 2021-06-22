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
 * FIXME .
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
	 * FIXME
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	public Margin(int topBottom, int leftRight) {
		this(topBottom, leftRight, topBottom, leftRight);
	}

	/**
	 * FIXME
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	public Margin(int top, int right, int bottom, int left) {
		this(null);
		// sets values
		setTop(top);
		setRight(top);
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
	 * Sets the top of chart area.
	 * 
	 * @param top the top of chart area.
	 */
	public void setTop(int top) {
		setValue(Property.TOP, top);
	}

	/**
	 * Returns the top of chart area.
	 * 
	 * @return the top of chart area.
	 */
	public int getTop() {
		return getValue(Property.TOP, Legend.DEFAULT_MARGIN);
	}

	/**
	 * Sets the right of chart area.
	 * 
	 * @param right the right of chart area.
	 */
	public void setRight(int right) {
		setValue(Property.RIGHT, right);
	}

	/**
	 * Returns the right of chart area.
	 * 
	 * @return the right of chart area.
	 */
	public int getRight() {
		return getValue(Property.RIGHT, Legend.DEFAULT_MARGIN);
	}

	/**
	 * Sets the bottom of chart area.
	 * 
	 * @param bottom the bottom of chart area.
	 */
	public void setBottom(int bottom) {
		setValue(Property.BOTTOM, bottom);
	}

	/**
	 * Returns the bottom of chart area.
	 * 
	 * @return the bottom of chart area.
	 */
	public int getBottom() {
		return getValue(Property.BOTTOM, Legend.DEFAULT_MARGIN);
	}

	/**
	 * Sets the left of chart area.
	 * 
	 * @param left the left of chart area.
	 */
	public void setLeft(int left) {
		setValue(Property.LEFT, left);
	}

	/**
	 * Returns the left of chart area.
	 * 
	 * @return the left of chart area.
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