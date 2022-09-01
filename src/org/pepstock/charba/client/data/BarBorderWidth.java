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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.defaults.globals.DefaultsBuilder;
import org.pepstock.charba.client.enums.Position;

/**
 * Defines the border width for BAR data set (left, top, right, bottom).
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class BarBorderWidth extends AbstractBarBorderItem {

	/**
	 * Public factory to create a border width object from a native object.
	 */
	public static final BarBorderWidthFactory FACTORY = new BarBorderWidthFactory();

	// constants with a list of the managed keys
	private static final List<Key> KEYS = Collections.unmodifiableList(Arrays.asList(Position.values()));

	/**
	 * Creates the object with an empty native object instance.
	 */
	public BarBorderWidth() {
		this(DefaultsBuilder.get().getOptions().getElements().getBar().getBorderWidth());
	}

	/**
	 * Creates the object using the argument to set the border width size to all corners of the rectangle.
	 * 
	 * @param borderWidth border width size to apply to all dimensions.
	 */
	public BarBorderWidth(int borderWidth) {
		super(borderWidth);
	}

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 */
	BarBorderWidth(NativeObject nativeObject) {
		super(nativeObject);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.AbstractBarBorderItem#getKeys()
	 */
	@Override
	List<Key> getKeys() {
		return KEYS;
	}

	/**
	 * Sets the border width size to all dimensions.
	 * 
	 * @param borderWidth border width size to apply to all dimensions.
	 */
	@Override
	public void set(int borderWidth) {
		setTop(borderWidth);
		setBottom(borderWidth);
		setLeft(borderWidth);
		setRight(borderWidth);
	}

	/**
	 * Sets the border width left in pixel.
	 * 
	 * @param borderWidth the border width left in pixel.
	 */
	public void setLeft(int borderWidth) {
		setValue(Position.LEFT, Checker.positiveOrZero(borderWidth));
	}

	/**
	 * Returns the border width left in pixel.
	 * 
	 * @return the border width left in pixel.
	 */
	public int getLeft() {
		return getValue(Position.LEFT, Defaults.get().getGlobal().getElements().getBar().getBorderWidth());
	}

	/**
	 * Sets the border width right in pixel.
	 * 
	 * @param borderWidth the border width right in pixel.
	 */
	public void setRight(int borderWidth) {
		setValue(Position.RIGHT, Checker.positiveOrZero(borderWidth));
	}

	/**
	 * Returns the border width right in pixel.
	 * 
	 * @return the border width right in pixel.
	 */
	public int getRight() {
		return getValue(Position.RIGHT, Defaults.get().getGlobal().getElements().getBar().getBorderWidth());
	}

	/**
	 * Sets the border width top in pixel.
	 * 
	 * @param borderWidth the border width top in pixel.
	 */
	public void setTop(int borderWidth) {
		setValue(Position.TOP, Checker.positiveOrZero(borderWidth));
	}

	/**
	 * Returns the border width top in pixel.
	 * 
	 * @return the border width top in pixel.
	 */
	public int getTop() {
		return getValue(Position.TOP, Defaults.get().getGlobal().getElements().getBar().getBorderWidth());
	}

	/**
	 * Sets the border width bottom in pixel.
	 * 
	 * @param borderWidth the border width bottom in pixel.
	 */
	public void setBottom(int borderWidth) {
		setValue(Position.BOTTOM, Checker.positiveOrZero(borderWidth));
	}

	/**
	 * Returns the border width bottom in pixel.
	 * 
	 * @return the border width bottom in pixel.
	 */
	public int getBottom() {
		return getValue(Position.BOTTOM, Defaults.get().getGlobal().getElements().getBar().getBorderWidth());
	}

	/**
	 * Inner class to create bar border width object by a native object.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	public static class BarBorderWidthFactory implements NativeObjectContainerFactory<BarBorderWidth> {

		/**
		 * To avoid any instantiation
		 */
		private BarBorderWidthFactory() {
			// do nothing
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		public BarBorderWidth create(NativeObject nativeObject) {
			return new BarBorderWidth(nativeObject);
		}

	}

}