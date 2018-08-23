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

import java.util.List;

import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.JsDoubleArrayList;
import org.pepstock.charba.client.commons.JsStringArrayList;
import org.pepstock.charba.client.enums.Position;

/**
 * There are a number of configuration callbacks that can be used to change parameters in the scale at different points in the
 * update process.<br>
 * This is the CHART.JS item with all needed info about an axis.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class AxisItem extends ScaleItem {
	
	private final AxisMarginsItem margins;
	
	private final AxisMinSizeItem minSize;

	public AxisItem(GenericJavaScriptObject javaScriptObject) {
		super(javaScriptObject);
		margins = new AxisMarginsItem((GenericJavaScriptObject)getValue(BaseBoxNodeItem.Property.margins));
		minSize= new AxisMinSizeItem((GenericJavaScriptObject)getValue(BaseBoxNodeItem.Property.minSize));
	}

	/**
	 * Sets if the axis must be hidden.
	 * 
	 * @param hidden <code>true</code> if the axis must be hidden, otherwise <code>false</code>.
	 */
	public final void setHidden(boolean hidden) {
		setValue(Property.hidden, hidden);
	}

	/**
	 * Sets the full width of axis in pixel
	 * 
	 * @param fullWidth the full width of axis
	 */
	public final void setFullWidth(boolean fullWidth) {
		setValue(BaseBoxNodeItem.Property.fullWidth, fullWidth);
	}

	/**
	 * Sets the weight of axis
	 * 
	 * @param weight the weight of axis
	 */
	public final void setWeight(int weight) {
		setValue(BaseBoxNodeItem.Property.weight, weight);
	}

	/**
	 * Sets the max width of axis in pixel.
	 * 
	 * @param maxWidth the max width of axis in pixel.
	 */
	public final void setMaxWidth(int maxWidth) {
		setValue(BaseBoxNodeItem.Property.maxWidth, maxWidth);
	}

	/**
	 * Sets the max height of the axis in pixel
	 * 
	 * @param maxHeight the max height of the axis in pixel
	 */
	public final void setMaxHeight(int maxHeight) {
		setValue(BaseBoxNodeItem.Property.maxHeight, maxHeight);
	}

	/**
	 * Sets the height of axis in pixel.
	 * 
	 * @param height the height of axis in pixel.
	 */
	public final void setHeight(int height) {
		setValue(BaseBoxNodeItem.Property.height, height);
	}

	/**
	 * Sets the top location in pixel
	 * 
	 * @param top the top location in pixel
	 */
	public final void setTop(int top) {
		setValue(BaseBoxItem.Property.top, top);
	}

	/**
	 * Sets the bottom location in pixel
	 * 
	 * @param bottom the bottom location in pixel
	 */
	public final void setBottom(int bottom) {
		setValue(BaseBoxItem.Property.bottom, bottom);
	}

	/**
	 * Sets the padding left in pixel
	 * 
	 * @param paddingLeft the padding left in pixel
	 */
	public final void setPaddingLeft(int paddingLeft) {
		setValue(BaseBoxNodeItem.Property.paddingLeft, paddingLeft);
	}

	/**
	 * Sets the padding top in pixel
	 * 
	 * @param paddingTop the padding top in pixel
	 */
	public final void setPaddingTop(int paddingTop) {
		setValue(BaseBoxNodeItem.Property.paddingTop, paddingTop);
	}

	/**
	 * Sets the padding right in pixel
	 * 
	 * @param paddingRight the padding right in pixel
	 */
	public final void setPaddingRight(int paddingRight) {
		setValue(BaseBoxNodeItem.Property.paddingRight, paddingRight);
	}

	/**
	 * Sets the padding bottom in pixel
	 * 
	 * @param paddingBottom the padding bottom in pixel
	 */
	public final void setPaddingBottom(int paddingBottom) {
		setValue(BaseBoxNodeItem.Property.paddingBottom, paddingBottom);
	}

	/**
	 * Sets the minimum value of axis
	 * 
	 * @param min the minimum value of axis
	 */
	public final void setMin(String min) {
		setValue(Property.min, min);
	}

	/**
	 * Sets the maximum value of axis
	 * 
	 * @param max the maximum value of axis
	 */
	public final void setMax(String max) {
		setValue(Property.max, max);
	}

	/**
	 * Sets the minimum value of axis
	 * 
	 * @param min the minimum value of axis
	 */
	public final void setMinAsNumber(int min) {
		setValue(Property.min, min);
	}

	/**
	 * Sets the maximum value of axis
	 * 
	 * @param max the maximum value of axis
	 */
	public final void setMaxAsNumber(int max) {
		setValue(Property.max, max);
	}

	/**
	 * Sets an array of ticks
	 * 
	 * @param ticks the array of ticks
	 */
	public final void setTicks(String... ticks) {
		setTicks(ArrayListHelper.build(ticks));
	}

	/**
	 * Sets the list of ticks
	 * 
	 * @param ticks the list of ticks
	 */
	private final void setTicks(JsStringArrayList ticks) {
		setStringArray(Property.ticks, ticks);
	}

	/**
	 * Sets the start value of the axis
	 * 
	 * @param start the start value of the axis
	 */
	public final void setStart(int start) {
		setValue(Property.start, start);
	}

	/**
	 * Sets the end value of the axis
	 * 
	 * @param end the end value of the axis
	 */
	public final void setEnd(int end) {
		setValue(Property.end, end);
	}

	/**
	 * Sets an array of ticks
	 * 
	 * @param ticksAsNumbers the array of ticks
	 */
	public final void setTicksAsNumbers(double... ticksAsNumbers) {
		setValueernalTicksAsNumbers(ArrayListHelper.build(ticksAsNumbers));
	}

	/**
	 * Sets the list of ticks
	 * 
	 * @param ticksAsNumbers the list of ticks
	 */
	public final void setTicksAsNumbers(List<Double> ticksAsNumbers) {
		// checks if is already a JavaScript object wrapper
		if (ticksAsNumbers instanceof JsDoubleArrayList) {
			// sets directly
			setValueernalTicksAsNumbers((JsDoubleArrayList) ticksAsNumbers);
		} else {
			// creates a JavaScript list
			JsDoubleArrayList list = new JsDoubleArrayList();
			// adds all values
			list.addAll(ticksAsNumbers);
			// sets list
			setValueernalTicksAsNumbers(list);
		}
	}

	/**
	 * Sets the list of ticks
	 * 
	 * @param ticksAsNumbers the list of ticks
	 */
	private final void setValueernalTicksAsNumbers(JsDoubleArrayList ticksAsNumbers) {
		setDoubleArray(Property.ticksAsNumbers, ticksAsNumbers);
	}

	/**
	 * Sets the index of line 0 of axis
	 * 
	 * @param zeroLineIndex the index of line 0 of axis
	 */
	public final void setZeroLineIndex(int zeroLineIndex) {
		setValue(Property.zeroLineIndex, zeroLineIndex);
	}

	/**
	 * Sets the label rotation value
	 * 
	 * @param labelRotation the label rotation value
	 */
	public final void setLabelRotation(double labelRotation) {
		setValue(Property.labelRotation, labelRotation);
	}

	/**
	 * Sets the width of axis
	 * 
	 * @param width the width of axis
	 */
	public final void setWidth(int width) {
		setValue(BaseBoxNodeItem.Property.width, width);
	}

	/**
	 * Sets the left location in pixel
	 * 
	 * @param left the left location in pixel
	 */
	public final void setLeft(int left) {
		setValue(BaseBoxItem.Property.left, left);
	}

	/**
	 * Sets the right location in pixel
	 * 
	 * @param right the right location in pixel
	 */
	public final void setRight(int right) {
		setValue(BaseBoxItem.Property.right, right);
	}

	/**
	 * Sets the position of axis
	 * 
	 * @param position the position of axis
	 * @see org.pepstock.charba.client.enums.Position
	 */
	public final void setPosition(Position position) {
		setValue(BaseBoxNodeItem.Property.position, position);
	}

	/**
	 * Returns the margins of axis
	 * 
	 * @return the margins of axis
	 * @see org.pepstock.charba.client.items.AxisMarginsItem
	 */
	public final AxisMarginsItem getAxisMargins() {
		return margins;
	}

	/**
	 * Returns the minimum size of axis
	 * 
	 * @return the minimum size of axis
	 * @see org.pepstock.charba.client.items.AxisMinSizeItem
	 */
	public final AxisMinSizeItem getAxisMinSize() {
		return minSize;
	}

}