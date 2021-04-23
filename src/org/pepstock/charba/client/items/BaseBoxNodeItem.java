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
package org.pepstock.charba.client.items;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.enums.Position;

/**
 * Base object which maps the CHART.JS chart items and represents main nodes of chart java script object.<br>
 * This is a wrapper of the CHART.JS item with all needed info.<br>
 * Implements all <code>get</code> methods to change java script object properties.
 * 
 * @author Andrea "Stock" Stocchero
 */
public abstract class BaseBoxNodeItem extends BaseBoxItem {

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		ACTIVE("active"),
		FULL_SIZE("fullSize"),
		POSITION("position"),
		WEIGHT("weight"),
		MAX_WIDTH("maxWidth"),
		MAX_HEIGHT("maxHeight"),
		PADDING_TOP("paddingTop"),
		PADDING_RIGHT("paddingRight"),
		PADDING_LEFT("paddingLeft"),
		PADDING_BOTTOM("paddingBottom");

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
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	BaseBoxNodeItem(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns <code>true</code> if the element is active.
	 * 
	 * @return <code>true</code> if the element is active.
	 */
	public final boolean isActive() {
		return getValue(Property.ACTIVE, Undefined.BOOLEAN);
	}

	/**
	 * Returns <code>true</code> if marks that this box should take the full width/height of the canvas (moving other boxes).
	 * 
	 * @return <code>true</code> if marks that this box should take the full width/height of the canvas (moving other boxes)
	 */
	public final boolean isFullSize() {
		return getValue(Property.FULL_SIZE, Undefined.BOOLEAN);
	}

	/**
	 * Returns the position of node.
	 * 
	 * @return the position of node.
	 */
	public final Position getPosition() {
		return getValue(Property.POSITION, Position.values(), Position.TOP);
	}

	/**
	 * Returns the weight.
	 * 
	 * @return the weight.
	 */
	public final double getWeight() {
		return getValue(Property.WEIGHT, Undefined.DOUBLE);
	}

	/**
	 * Returns the max width in pixel.
	 * 
	 * @return the max width in pixel.
	 */
	public final double getMaxWidth() {
		return getValue(Property.MAX_WIDTH, Undefined.DOUBLE);
	}

	/**
	 * Returns the max height in pixel.
	 * 
	 * @return the max height in pixel.
	 */
	public final double getMaxHeight() {
		return getValue(Property.MAX_HEIGHT, Undefined.DOUBLE);
	}

	/**
	 * Returns the padding top in pixel.
	 * 
	 * @return the padding top in pixel.
	 */
	public final int getPaddingTop() {
		return getValue(Property.PADDING_TOP, Undefined.INTEGER);
	}

	/**
	 * Returns the padding right in pixel.
	 * 
	 * @return the padding right in pixel.
	 */
	public final int getPaddingRight() {
		return getValue(Property.PADDING_RIGHT, Undefined.INTEGER);
	}

	/**
	 * Returns the padding bottom in pixel.
	 * 
	 * @return the padding bottom in pixel.
	 */
	public final int getPaddingBottom() {
		return getValue(Property.PADDING_BOTTOM, Undefined.INTEGER);
	}

	/**
	 * Returns the padding left in pixel.
	 * 
	 * @return the padding left in pixel.
	 */
	public final int getPaddingLeft() {
		return getValue(Property.PADDING_LEFT, Undefined.INTEGER);
	}

}