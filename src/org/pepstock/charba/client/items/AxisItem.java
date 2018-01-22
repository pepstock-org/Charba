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
import org.pepstock.charba.client.commons.JsDoubleArrayList;
import org.pepstock.charba.client.commons.JsStringArrayList;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.Position;

/**
 * There are a number of configuration callbacks that can be used to change parameters in the scale at different points in the
 * update process.<br>
 * This is the CHART.JS item with all needed info about an axis.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class AxisItem extends BaseItem {

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		id,
		hidden,
		fullWidth,
		position,
		weight,
		maxWidth,
		maxHeight,
		margins,
		height,
		top,
		bottom,
		paddingLeft,
		paddingTop,
		paddingRight,
		paddingBottom,
		min,
		max,
		ticks,
		start,
		end,
		ticksAsNumbers,
		zeroLineIndex,
		labelRotation,
		minSize,
		width,
		left,
		right
	}

	/**
	 * Needed for GWt injection
	 */
	protected AxisItem() {
		// do nothing
	}

	/**
	 * Returns the ID, used to link datasets and scale axes together.
	 * 
	 * @return the axis ID
	 */
	public final String getId() {
		return getString(Property.id.name());
	}

	/**
	 * Returns if the axis is hidden.
	 * 
	 * @return <code>true</code> if the axis is hidden, otherwise <code>false</code>.
	 */
	public final boolean isHidden() {
		return getBoolean(Property.hidden.name());
	}

	/**
	 * Sets if the axis must be hidden.
	 * 
	 * @param hidden <code>true</code> if the axis must be hidden, otherwise <code>false</code>.
	 */
	public final void setHidden(boolean hidden) {
		setBoolean(Property.hidden.name(), hidden);
	}

	/**
	 * Returns the full width of axis
	 * 
	 * @return the full width of axis
	 */
	public final boolean isFullWidth() {
		return getBoolean(Property.fullWidth.name());
	}

	/**
	 * Sets the full width of axis in pixel
	 * 
	 * @param fullWidth the full width of axis
	 */
	public final void setFullWidth(boolean fullWidth) {
		setBoolean(Property.fullWidth.name(), fullWidth);
	}

	/**
	 * Returns the weight of axis
	 * 
	 * @return the weight of axis
	 */
	public final int getWeight() {
		return getInt(Property.weight.name());
	}

	/**
	 * Sets the weight of axis
	 * 
	 * @param weight the weight of axis
	 */
	public final void setWeight(int weight) {
		setInt(Property.weight.name(), weight);
	}

	/**
	 * Returns the max width of axis in pixel.
	 * 
	 * @return the max width of axis in pixel.
	 */
	public final int getMaxWidth() {
		return getInt(Property.maxWidth.name());
	}

	/**
	 * Sets the max width of axis in pixel.
	 * 
	 * @param maxWidth the max width of axis in pixel.
	 */
	public final void setMaxWidth(int maxWidth) {
		setInt(Property.maxWidth.name(), maxWidth);
	}

	/**
	 * Returns the max height of the axis in pixel
	 * 
	 * @return the max height of the axis in pixel
	 */
	public final int getMaxHeight() {
		return getInt(Property.maxHeight.name());
	}

	/**
	 * Sets the max height of the axis in pixel
	 * 
	 * @param maxHeight the max height of the axis in pixel
	 */
	public final void setMaxHeight(int maxHeight) {
		setInt(Property.maxHeight.name(), maxHeight);
	}

	/**
	 * Returns the height of axis in pixel.
	 * 
	 * @return the height of axis in pixel.
	 */
	public final int getHeight() {
		return getInt(Property.height.name());
	}

	/**
	 * Sets the height of axis in pixel.
	 * 
	 * @param height the height of axis in pixel.
	 */
	public final void setHeight(int height) {
		setInt(Property.height.name(), height);
	}

	/**
	 * Returns the top location in pixel
	 * 
	 * @return the top location in pixel
	 */
	public final int getTop() {
		return getInt(Property.top.name());
	}

	/**
	 * Sets the top location in pixel
	 * 
	 * @param top the top location in pixel
	 */
	public final void setTop(int top) {
		setInt(Property.top.name(), top);
	}

	/**
	 * Returns the bottom location in pixel
	 * 
	 * @return the bottom location in pixel
	 */
	public final int getBottom() {
		return getInt(Property.bottom.name());
	}

	/**
	 * Sets the bottom location in pixel
	 * 
	 * @param bottom the bottom location in pixel
	 */
	public final void setBottom(int bottom) {
		setInt(Property.bottom.name(), bottom);
	}

	/**
	 * Returns the padding left in pixel
	 * 
	 * @return the padding left in pixel
	 */
	public final int getPaddingLeft() {
		return getInt(Property.paddingLeft.name());
	}

	/**
	 * Sets the padding left in pixel
	 * 
	 * @param paddingLeft the padding left in pixel
	 */
	public final void setPaddingLeft(int paddingLeft) {
		setInt(Property.paddingLeft.name(), paddingLeft);
	}

	/**
	 * Returns the padding top in pixel
	 * 
	 * @return the padding top in pixel
	 */
	public final int getPaddingTop() {
		return getInt(Property.paddingTop.name());
	}

	/**
	 * Sets the padding top in pixel
	 * 
	 * @param paddingTop the padding top in pixel
	 */
	public final void setPaddingTop(int paddingTop) {
		setInt(Property.paddingTop.name(), paddingTop);
	}

	/**
	 * Returns the padding right in pixel
	 * 
	 * @return the padding right in pixel
	 */
	public final int getPaddingRight() {
		return getInt(Property.paddingRight.name());
	}

	/**
	 * Sets the padding right in pixel
	 * 
	 * @param paddingRight the padding right in pixel
	 */
	public final void setPaddingRight(int paddingRight) {
		setInt(Property.paddingRight.name(), paddingRight);
	}

	/**
	 * Returns the padding bottom in pixel
	 * 
	 * @return the padding bottom in pixel
	 */
	public final int getPaddingBottom() {
		return getInt(Property.paddingBottom.name());
	}

	/**
	 * Sets the padding bottom in pixel
	 * 
	 * @param paddingBottom the padding bottom in pixel
	 */
	public final void setPaddingBottom(int paddingBottom) {
		setInt(Property.paddingBottom.name(), paddingBottom);
	}

	/**
	 * Returns the minimum value of axis
	 * 
	 * @return the minimum value of axis
	 */
	public final String getMin() {
		return getString(Property.min.name());
	}

	/**
	 * Sets the minimum value of axis
	 * 
	 * @param min the minimum value of axis
	 */
	public final void setMin(String min) {
		setString(Property.min.name(), min);
	}

	/**
	 * Returns the maximum value of axis
	 * 
	 * @return the maximum value of axis
	 */
	public final String getMax() {
		return getString(Property.max.name());
	}

	/**
	 * Sets the maximum value of axis
	 * 
	 * @param max the maximum value of axis
	 */
	public final void setMax(String max) {
		setString(Property.max.name(), max);
	}

	/**
	 * Returns the minimum value of axis
	 * 
	 * @return the minimum value of axis
	 */
	public final int getMinAsNumber() {
		return getInt(Property.min.name());
	}

	/**
	 * Sets the minimum value of axis
	 * 
	 * @param min the minimum value of axis
	 */
	public final void setMinAsNumber(int min) {
		setInt(Property.min.name(), min);
	}

	/**
	 * Returns the maximum value of axis
	 * 
	 * @return the maximum value of axis
	 */
	public final int getMaxAsNumber() {
		return getInt(Property.max.name());
	}

	/**
	 * Sets the maximum value of axis
	 * 
	 * @param max the maximum value of axis
	 */
	public final void setMaxAsNumber(int max) {
		setInt(Property.max.name(), max);
	}

	/**
	 * Returns the list of ticks
	 * 
	 * @return the list of ticks
	 */
	public final List<String> getTicks() {
		return getStringArray(Property.ticks.name());
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
		setStringArray(Property.ticks.name(), ticks);
	}

	/**
	 * Returns the start value of the axis
	 * 
	 * @return the start value of the axis
	 */
	public final int getStart() {
		return getInt(Property.start.name());
	}

	/**
	 * Sets the start value of the axis
	 * 
	 * @param start the start value of the axis
	 */
	public final void setStart(int start) {
		setInt(Property.start.name(), start);
	}

	/**
	 * Returns the end value of the axis
	 * 
	 * @return the end value of the axis
	 */
	public final int getEnd() {
		return getInt(Property.end.name());
	}

	/**
	 * Sets the end value of the axis
	 * 
	 * @param end the end value of the axis
	 */
	public final void setEnd(int end) {
		setInt(Property.end.name(), end);
	}

	/**
	 * Returns the list of ticks
	 * 
	 * @return the list of ticks
	 */
	public final List<Double> getTicksAsNumbers() {
		return getDoubleArray(Property.ticksAsNumbers.name());
	}

	/**
	 * Sets an array of ticks
	 * 
	 * @param ticksAsNumbers the array of ticks
	 */
	public final void setTicksAsNumbers(double... ticksAsNumbers) {
		setInternalTicksAsNumbers(ArrayListHelper.build(ticksAsNumbers));
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
			setInternalTicksAsNumbers((JsDoubleArrayList) ticksAsNumbers);
		} else {
			// creates a JavaScript list
			JsDoubleArrayList list = new JsDoubleArrayList();
			// adds all values
			list.addAll(ticksAsNumbers);
			// sets list
			setInternalTicksAsNumbers(list);
		}
	}

	/**
	 * Sets the list of ticks
	 * 
	 * @param ticksAsNumbers the list of ticks
	 */
	private final void setInternalTicksAsNumbers(JsDoubleArrayList ticksAsNumbers) {
		setDoubleArray(Property.ticksAsNumbers.name(), ticksAsNumbers);
	}

	/**
	 * Returns the index of line 0 of axis
	 * 
	 * @return the index of line 0 of axis
	 */
	public final int getZeroLineIndex() {
		return getInt(Property.zeroLineIndex.name());
	}

	/**
	 * Sets the index of line 0 of axis
	 * 
	 * @param zeroLineIndex the index of line 0 of axis
	 */
	public final void setZeroLineIndex(int zeroLineIndex) {
		setInt(Property.zeroLineIndex.name(), zeroLineIndex);
	}

	/**
	 * Returns the label rotation value
	 * 
	 * @return the label rotation value
	 */
	public final double getLabelRotation() {
		return getDouble(Property.labelRotation.name());
	}

	/**
	 * Sets the label rotation value
	 * 
	 * @param labelRotation the label rotation value
	 */
	public final void setLabelRotation(double labelRotation) {
		setDouble(Property.labelRotation.name(), labelRotation);
	}

	/**
	 * Returns the width of axis
	 * 
	 * @return the width of axis
	 */
	public final int getWidth() {
		return getInt(Property.width.name());
	}

	/**
	 * Sets the width of axis
	 * 
	 * @param width the width of axis
	 */
	public final void setWidth(int width) {
		setInt(Property.width.name(), width);
	}

	/**
	 * Returns the left location in pixel
	 * 
	 * @return the left location in pixel
	 */
	public final int getLeft() {
		return getInt(Property.left.name());
	}

	/**
	 * Sets the left location in pixel
	 * 
	 * @param left the left location in pixel
	 */
	public final void setLeft(int left) {
		setInt(Property.left.name(), left);
	}

	/**
	 * Returns the right location in pixel
	 * 
	 * @return the right location in pixel
	 */
	public final int getRight() {
		return getInt(Property.right.name());
	}

	/**
	 * Sets the right location in pixel
	 * 
	 * @param right the right location in pixel
	 */
	public final void setRight(int right) {
		setInt(Property.right.name(), right);
	}

	/**
	 * Sets the position of axis
	 * 
	 * @param position the position of axis
	 * @see org.pepstock.charba.client.enums.Position
	 */
	public final void setPosition(Position position) {
		setString(Property.position.name(), position.name());
	}

	/**
	 * Returns the position of axis
	 * 
	 * @return the position of axis
	 * @see org.pepstock.charba.client.enums.Position
	 */
	public final Position getPosition() {
		return getValue(Property.position, Position.class, Position.top);
	}

	/**
	 * Sets the margins of axis
	 * 
	 * @param item the margins of axis
	 * @see org.pepstock.charba.client.items.AxisMarginsItem
	 */
	public final void setMargins(AxisMarginsItem item) {
		setJavaScriptObject(Property.margins.name(), item);
	}

	/**
	 * Returns the margins of axis
	 * 
	 * @return the margins of axis
	 * @see org.pepstock.charba.client.items.AxisMarginsItem
	 */
	public final AxisMarginsItem getMargins() {
		return (AxisMarginsItem) getJavaScriptObject(Property.margins.name());
	}

	/**
	 * Sets the minimum size of axis
	 * 
	 * @param minSize the minimum size of axis
	 * @see org.pepstock.charba.client.items.AxisMinSizeItem
	 */
	public final void setMinSize(AxisMinSizeItem minSize) {
		setJavaScriptObject(Property.minSize.name(), minSize);
	}

	/**
	 * Returns the minimum size of axis
	 * 
	 * @return the minimum size of axis
	 * @see org.pepstock.charba.client.items.AxisMinSizeItem
	 */
	public final AxisMinSizeItem getMinSize() {
		return (AxisMinSizeItem) getJavaScriptObject(Property.minSize.name());
	}

}