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

import java.util.Date;

import org.pepstock.charba.client.commons.ArrayDouble;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.configuration.CartesianLogarithmicAxis;
import org.pepstock.charba.client.enums.AxisType;
import org.pepstock.charba.client.enums.Position;

/**
 * There are a number of configuration callbacks that can be used to change parameters in the scale at different points in the update process.<br>
 * This is a wrapper of the CHART.JS item with all needed info about an axis.<br>
 * Implements all <code>set</code> methods to change java script object properties.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class AxisItem extends ScaleItem {

	private final AxisMinSizeItem minSize;

	/**
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	public AxisItem(NativeObject nativeObject) {
		super(nativeObject);
		// initializes the sub objects
		minSize = new AxisMinSizeItem(getValue(BaseBoxNodeItem.Property.MIN_SIZE));
	}

	/**
	 * Sets if the axis must be hidden.
	 * 
	 * @param hidden <code>true</code> if the axis must be hidden, otherwise <code>false</code>.
	 */
	public void setHidden(boolean hidden) {
		setValue(ScaleItem.Property.HIDDEN, hidden);
	}

	/**
	 * Sets the full width of axis in pixel
	 * 
	 * @param fullWidth the full width of axis
	 */
	public void setFullWidth(boolean fullWidth) {
		setValue(BaseBoxNodeItem.Property.FULL_WIDTH, fullWidth);
	}

	/**
	 * Sets the weight of axis
	 * 
	 * @param weight the weight of axis
	 */
	public void setWeight(int weight) {
		setValue(BaseBoxNodeItem.Property.WEIGHT, weight);
	}

	/**
	 * Sets the max width of axis in pixel.
	 * 
	 * @param maxWidth the max width of axis in pixel.
	 */
	public void setMaxWidth(int maxWidth) {
		setValue(BaseBoxNodeItem.Property.MAX_WIDTH, maxWidth);
	}

	/**
	 * Sets the max height of the axis in pixel
	 * 
	 * @param maxHeight the max height of the axis in pixel
	 */
	public void setMaxHeight(int maxHeight) {
		setValue(BaseBoxNodeItem.Property.MAX_HEIGHT, maxHeight);
	}

	/**
	 * Sets the height of axis in pixel.
	 * 
	 * @param height the height of axis in pixel.
	 */
	public void setHeight(int height) {
		setValue(BaseBoxItem.Property.HEIGHT, height);
	}

	/**
	 * Sets the top location in pixel
	 * 
	 * @param top the top location in pixel
	 */
	public void setTop(int top) {
		setValue(BaseBoxItem.Property.TOP, top);
	}

	/**
	 * Sets the bottom location in pixel
	 * 
	 * @param bottom the bottom location in pixel
	 */
	public void setBottom(int bottom) {
		setValue(BaseBoxItem.Property.BOTTOM, bottom);
	}

	/**
	 * Sets the padding left in pixel
	 * 
	 * @param paddingLeft the padding left in pixel
	 */
	public void setPaddingLeft(int paddingLeft) {
		setValue(BaseBoxNodeItem.Property.PADDING_LEFT, paddingLeft);
	}

	/**
	 * Sets the padding top in pixel
	 * 
	 * @param paddingTop the padding top in pixel
	 */
	public void setPaddingTop(int paddingTop) {
		setValue(BaseBoxNodeItem.Property.PADDING_TOP, paddingTop);
	}

	/**
	 * Sets the padding right in pixel
	 * 
	 * @param paddingRight the padding right in pixel
	 */
	public void setPaddingRight(int paddingRight) {
		setValue(BaseBoxNodeItem.Property.PADDING_RIGHT, paddingRight);
	}

	/**
	 * Sets the padding bottom in pixel
	 * 
	 * @param paddingBottom the padding bottom in pixel
	 */
	public void setPaddingBottom(int paddingBottom) {
		setValue(BaseBoxNodeItem.Property.PADDING_BOTTOM, paddingBottom);
	}

	/**
	 * Sets the minimum value of axis
	 * 
	 * @param min the minimum value of axis
	 */
	public void setMin(String min) {
		setValue(ScaleItem.Property.MIN, min);
	}

	/**
	 * Sets the maximum value of axis
	 * 
	 * @param max the maximum value of axis
	 */
	public void setMax(String max) {
		setValue(ScaleItem.Property.MAX, max);
	}

	/**
	 * Sets the minimum value of axis
	 * 
	 * @param min the minimum value of axis
	 */
	public void setMin(double min) {
		setValue(ScaleItem.Property.MIN, min);
	}

	/**
	 * Sets the maximum value of axis
	 * 
	 * @param max the maximum value of axis
	 */
	public void setMax(double max) {
		setValue(ScaleItem.Property.MAX, max);
	}

	/**
	 * Sets the minimum value of axis
	 * 
	 * @param min the minimum value of axis
	 */
	public void setMin(Date min) {
		setValue(ScaleItem.Property.MIN, min.getTime());
	}

	/**
	 * Sets the maximum value of axis
	 * 
	 * @param max the maximum value of axis
	 */
	public void setMax(Date max) {
		setValue(ScaleItem.Property.MAX, max.getTime());
	}

	/**
	 * Sets the minimum value not zero of scale, only for {@link CartesianLogarithmicAxis}.
	 * 
	 * @param min the minimum value not zero of scale.
	 */
	public void setMinNotZero(double min) {
		// sets values
		setValue(ScaleItem.Property.MIN_NOT_ZERO, min);
	}

	/**
	 * Sets an array of ticks
	 * 
	 * @param ticks the array of ticks
	 */
	public void setTicks(String... ticks) {
		setArrayValue(ScaleItem.Property.TICKS, ArrayString.fromOrEmpty(ticks));
	}

	/**
	 * Sets the start value of the axis
	 * 
	 * @param start the start value of the axis
	 */
	public void setStart(int start) {
		setValue(ScaleItem.Property.START, start);
	}

	/**
	 * Sets the end value of the axis
	 * 
	 * @param end the end value of the axis
	 */
	public void setEnd(int end) {
		setValue(ScaleItem.Property.END, end);
	}

	/**
	 * Sets an array of ticks
	 * 
	 * @param ticksAsNumbers the array of ticks
	 */
	public void setTicksAsNumbers(double... ticksAsNumbers) {
		// gets a key reference
		Key key = null;
		// checks if the axis type is log
		if (AxisType.LOGARITHMIC.equals(getType())) {
			// sets key value
			key = ScaleItem.Property.TICKS_VALUES;
		} else {
			// sets for all other linear axes
			key = ScaleItem.Property.TICKS_AS_NUMBERS;
		}
		// sets value
		setArrayValue(key, ArrayDouble.fromOrEmpty(ticksAsNumbers));
	}

	/**
	 * Sets the index of line 0 of axis
	 * 
	 * @param zeroLineIndex the index of line 0 of axis
	 */
	public void setZeroLineIndex(int zeroLineIndex) {
		setValue(ScaleItem.Property.ZERO_LINE_INDEX, zeroLineIndex);
	}

	/**
	 * Sets the label rotation value
	 * 
	 * @param labelRotation the label rotation value
	 */
	public void setLabelRotation(double labelRotation) {
		setValue(ScaleItem.Property.LABEL_ROTATION, labelRotation);
	}

	/**
	 * Sets the width of axis
	 * 
	 * @param width the width of axis
	 */
	public void setWidth(int width) {
		setValue(BaseBoxItem.Property.WIDTH, width);
	}

	/**
	 * Sets the left location in pixel
	 * 
	 * @param left the left location in pixel
	 */
	public void setLeft(int left) {
		setValue(BaseBoxItem.Property.LEFT, left);
	}

	/**
	 * Sets the right location in pixel
	 * 
	 * @param right the right location in pixel
	 */
	public void setRight(int right) {
		setValue(BaseBoxItem.Property.RIGHT, right);
	}

	/**
	 * Sets the position of axis
	 * 
	 * @param position the position of axis
	 */
	public void setPosition(Position position) {
		setValue(BaseBoxNodeItem.Property.POSITION, position);
	}

	/**
	 * Returns the minimum size of axis
	 * 
	 * @return the minimum size of axis
	 */
	public AxisMinSizeItem getAxisMinSize() {
		return minSize;
	}

}