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
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.items.ChartAreaNode;
import org.pepstock.charba.client.items.Undefined;

/**
 * Defines how to clip relative to the chart area.<br>
 * Positive value allows overflow, negative value clips that many pixels inside the chart area.<br>
 * If sets <code>false</code>, that means that CHART.JS will use chart area dimension.<br>
 * With <code>false</code> value, the properties will be:
 * <ul>
 * <li>Left is 0
 * <li>Top is 0
 * <li>Right is area right
 * <li>Bottom is area bottom
 * </ul>
 * 
 * @author Andrea "Stock" Stocchero
 * @see ChartAreaNode
 */
public final class Clip extends NativeObjectContainer {

	/**
	 * Creates the object with an empty native object instance.
	 */
	public Clip() {
		super();
	}

	/**
	 * Creates the object with the same clip value for all dimensions.
	 * 
	 * @param clip clip value for all dimensions
	 */
	public Clip(double clip) {
		this();
		set(clip);
	}

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 */
	Clip(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Sets the clip size to all dimensions.
	 * 
	 * @param clip clip to apply to all dimensions.
	 */
	public void set(double clip) {
		setTop(clip);
		setBottom(clip);
		setLeft(clip);
		setRight(clip);
	}

	/**
	 * Sets the clip left.
	 * 
	 * @param clip the clip left.
	 */
	public void setLeft(double clip) {
		setValue(Position.LEFT, clip);
	}

	/**
	 * Sets the clip left.
	 * 
	 * @param clip the clip left.
	 */
	public void setLeft(boolean clip) {
		setInternalValue(Position.LEFT, clip);
	}

	/**
	 * Returns the clip left.
	 * 
	 * @return the clip left.
	 */
	public double getLeft() {
		return getInternalValue(Position.LEFT);
	}

	/**
	 * Returns <code>false</code> if the clip left has been set.
	 * 
	 * @return <code>false</code> if the clip left has been set
	 */
	public boolean isLeft() {
		return isInternalValue(Position.LEFT);
	}

	/**
	 * Sets the clip right.
	 * 
	 * @param clip the clip right.
	 */
	public void setRight(double clip) {
		setValue(Position.RIGHT, clip);
	}

	/**
	 * Sets the clip right.
	 * 
	 * @param clip the clip right.
	 */
	public void setRight(boolean clip) {
		setInternalValue(Position.RIGHT, clip);
	}

	/**
	 * Returns the clip right.
	 * 
	 * @return the clip right.
	 */
	public double getRight() {
		return getInternalValue(Position.RIGHT);
	}

	/**
	 * Returns <code>false</code> if the clip right has been set.
	 * 
	 * @return <code>false</code> if the clip right has been set
	 */
	public boolean isRight() {
		return isInternalValue(Position.RIGHT);
	}

	/**
	 * Sets the clip top.
	 * 
	 * @param clip the clip top.
	 */
	public void setTop(double clip) {
		setValue(Position.TOP, clip);
	}

	/**
	 * Sets the clip top.
	 * 
	 * @param clip the clip top.
	 */
	public void setTop(boolean clip) {
		setInternalValue(Position.TOP, clip);
	}

	/**
	 * Returns the clip top.
	 * 
	 * @return the clip top.
	 */
	public double getTop() {
		return getInternalValue(Position.TOP);
	}

	/**
	 * Returns <code>false</code> if the clip top has been set.
	 * 
	 * @return <code>false</code> if the clip top has been set
	 */
	public boolean isTop() {
		return isInternalValue(Position.TOP);
	}

	/**
	 * Sets the clip bottom.
	 * 
	 * @param clip the clip bottom.
	 */
	public void setBottom(double clip) {
		setValue(Position.BOTTOM, clip);
	}

	/**
	 * Sets the clip bottom.
	 * 
	 * @param clip the clip bottom.
	 */
	public void setBottom(boolean clip) {
		setInternalValue(Position.BOTTOM, clip);
	}

	/**
	 * Returns the clip bottom.
	 * 
	 * @return the clip bottom.
	 */
	public double getBottom() {
		return getInternalValue(Position.BOTTOM);
	}

	/**
	 * Returns <code>false</code> if the clip bottom has been set.
	 * 
	 * @return <code>false</code> if the clip bottom has been set
	 */
	public boolean isBottom() {
		return isInternalValue(Position.BOTTOM);
	}

	/**
	 * Returns <code>true</code> if clip is enable for passed position.
	 * 
	 * @param position position to check
	 * @return <code>true</code> if clip is enable for passed position
	 */
	private boolean isInternalValue(Position position) {
		// checks if previously was set to a number
		// therefore true
		if (isType(position, ObjectType.NUMBER)) {
			// if number returns true
			return true;
		}
		// if here, a number has been stored
		// then returns the number
		return getValue(position, Undefined.BOOLEAN);
	}

	/**
	 * Returns the value of clip for passed position or {@link Undefined#DOUBLE} if is disable.
	 * 
	 * @param position position to check
	 * @return the value of clip for passed position or {@link Undefined#DOUBLE} if is disable
	 */
	private double getInternalValue(Position position) {
		// checks if previously was set to a boolean
		// therefore false
		if (isType(position, ObjectType.BOOLEAN)) {
			// if boolean returns NaN
			return Undefined.DOUBLE;
		}
		// if here, a number has been stored
		// then returns the number
		return getValue(position, Defaults.get().getGlobal().getElements().getLine().getBorderWidth() / 2D);
	}

	/**
	 * Sets <code>false</code> disabling clip for passed position but if <code>true</code> is passed, removes the previous value if boolean, using the default number.
	 * 
	 * @param position position to check
	 * @param value <code>false</code> to disable clip or <code>true</code> to remove previous disable.
	 */
	private void setInternalValue(Position position, boolean value) {
		// checks if want to set true
		if (value) {
			// gets the type stored
			ObjectType type = type(position);
			// checks if previously was set to a boolean
			// therefore false
			if (ObjectType.BOOLEAN.equals(type)) {
				// removes the key and use the default as number
				remove(position);
			}
		} else {
			// sets false, always
			setValue(position, false);
		}
	}

}