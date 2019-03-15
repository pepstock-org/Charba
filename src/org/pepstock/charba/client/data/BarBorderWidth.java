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
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.enums.Position;

/**
 * Defines the border width for BAR dataset (left, top, right, bottom).
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class BarBorderWidth extends NativeObjectContainer {

	/**
	 * Creates the object with an empty native object instance.
	 */
	public BarBorderWidth() {
		super();
	}

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 */
	BarBorderWidth(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Sets the border width size to all dimensions.
	 * 
	 * @param borderWidth border width size to apply to all dimensions.
	 */
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
		setValue(Position.left, borderWidth);
	}

	/**
	 * Returns the border width left in pixel.
	 * 
	 * @return the border width left in pixel.
	 */
	public int getLeft() {
		return getValue(Position.left, Defaults.get().getGlobal().getElements().getRectangle().getBorderWidth());
	}

	/**
	 * Sets the border width right in pixel.
	 * 
	 * @param borderWidth the border width right in pixel.
	 */
	public void setRight(int borderWidth) {
		setValue(Position.right, borderWidth);
	}

	/**
	 * Returns the border width right in pixel.
	 * 
	 * @return the border width right in pixel.
	 */
	public int getRight() {
		return getValue(Position.right, Defaults.get().getGlobal().getElements().getRectangle().getBorderWidth());
	}

	/**
	 * Sets the border width top in pixel.
	 * 
	 * @param borderWidth the border width top in pixel.
	 */
	public void setTop(int borderWidth) {
		setValue(Position.top, borderWidth);
	}

	/**
	 * Returns the border width top in pixel.
	 * 
	 * @return the border width top in pixel.
	 */
	public int getTop() {
		return getValue(Position.top, Defaults.get().getGlobal().getElements().getRectangle().getBorderWidth());
	}

	/**
	 * Sets the border width bottom in pixel.
	 * 
	 * @param borderWidth the border width bottom in pixel.
	 */
	public void setBottom(int borderWidth) {
		setValue(Position.bottom, borderWidth);
	}

	/**
	 * Returns the border width bottom in pixel.
	 * 
	 * @return the border width bottom in pixel.
	 */
	public int getBottom() {
		return getValue(Position.bottom, Defaults.get().getGlobal().getElements().getRectangle().getBorderWidth());
	}
}