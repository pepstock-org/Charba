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

	private final SizeItem minSize;

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		// FIXME should add ACTIVE?
		FULL_WIDTH("fullWidth"),
		POSITION("position"),
		WEIGHT("weight"),
		MAX_WIDTH("maxWidth"),
		MAX_HEIGHT("maxHeight"),
		PADDING_TOP("paddingTop"),
		PADDING_RIGHT("paddingRight"),
		PADDING_LEFT("paddingLeft"),
		PADDING_BOTTOM("paddingBottom"),
		MIN_SIZE("minSize"); // FIXME to be removed?

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
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	BaseBoxNodeItem(NativeObject nativeObject) {
		super(nativeObject);
		// initializes the sub objects
		minSize = new SizeItem(getValue(Property.MIN_SIZE));
	}

	/**
	 * Returns the min size item.
	 * 
	 * @return the min size item.
	 */
	public final SizeItem getMinSize() {
		return minSize;
	}

	/**
	 * Returns the full width in pixel.
	 * 
	 * @return the full width in pixel. Default is {@link UndefinedValues#BOOLEAN}.
	 */
	public final boolean isFullWidth() {
		return getValue(Property.FULL_WIDTH, UndefinedValues.BOOLEAN);
	}

	/**
	 * Returns the position of node.
	 * 
	 * @return the position of node. Default is {@link org.pepstock.charba.client.enums.Position#TOP}.
	 */
	public final Position getPosition() {
		return getValue(Property.POSITION, Position.values(), Position.TOP);
	}

	/**
	 * Returns the weight.
	 * 
	 * @return the weight. Default is {@link UndefinedValues#DOUBLE}.
	 */
	public final double getWeight() {
		return getValue(Property.WEIGHT, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the max width in pixel.
	 * 
	 * @return the max width in pixel. Default is {@link UndefinedValues#DOUBLE}.
	 */
	public final double getMaxWidth() {
		return getValue(Property.MAX_WIDTH, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the max height in pixel.
	 * 
	 * @return the max height in pixel. Default is {@link UndefinedValues#DOUBLE}.
	 */
	public final double getMaxHeight() {
		return getValue(Property.MAX_HEIGHT, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the padding top in pixel.
	 * 
	 * @return the padding top in pixel. Default is {@link UndefinedValues#INTEGER}.
	 */
	public final int getPaddingTop() {
		return getValue(Property.PADDING_TOP, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the padding right in pixel.
	 * 
	 * @return the padding right in pixel. Default is {@link UndefinedValues#INTEGER}.
	 */
	public final int getPaddingRight() {
		return getValue(Property.PADDING_RIGHT, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the padding bottom in pixel.
	 * 
	 * @return the padding bottom in pixel. Default is {@link UndefinedValues#INTEGER}.
	 */
	public final int getPaddingBottom() {
		return getValue(Property.PADDING_BOTTOM, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the padding left in pixel.
	 * 
	 * @return the padding left in pixel. Default is {@link UndefinedValues#INTEGER}.
	 */
	public final int getPaddingLeft() {
		return getValue(Property.PADDING_LEFT, UndefinedValues.INTEGER);
	}

}