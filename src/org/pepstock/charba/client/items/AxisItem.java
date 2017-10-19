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

public class AxisItem extends BaseItem {

	private enum Property implements Key{
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
		
	}

	public final String getId() {
		return getString(Property.id.name());
	}

	public final boolean isHidden() {
		return getBoolean(Property.hidden.name());
	}

	public final void setHidden(boolean hidden) {
		setBoolean(Property.hidden.name(), hidden);
	}

	public final boolean isFullWidth() {
		return getBoolean(Property.fullWidth.name());
	}

	public final void setFullWidth(boolean fullWidth) {
		setBoolean(Property.fullWidth.name(), fullWidth);
	}

	public final int getWeight() {
		return getInt(Property.weight.name());
	}

	public final void setWeight(int weight) {
		setInt(Property.weight.name(), weight);
	}

	public final int getMaxWidth() {
		return getInt(Property.maxWidth.name());
	}

	public final void setMaxWidth(int maxWidth) {
		setInt(Property.maxWidth.name(), maxWidth);
	}

	public final int getMaxHeight() {
		return getInt(Property.maxHeight.name());
	}

	public final void setMaxHeight(int maxHeight) {
		setInt(Property.maxHeight.name(), maxHeight);
	}

	public final int getHeight() {
		return getInt(Property.height.name());
	}

	public final void setHeight(int height) {
		setInt(Property.height.name(), height);
	}

	public final int getTop() {
		return getInt(Property.top.name());
	}

	public final void setTop(int top) {
		setInt(Property.top.name(), top);
	}

	public final int getBottom() {
		return getInt(Property.bottom.name());
	}

	public final void setBottom(int bottom) {
		setInt(Property.bottom.name(), bottom);
	}

	public final int getPaddingLeft() {
		return getInt(Property.paddingLeft.name());
	}

	public final void setPaddingLeft(int paddingLeft) {
		setInt(Property.paddingLeft.name(), paddingLeft);
	}

	public final int getPaddingTop() {
		return getInt(Property.paddingTop.name());
	}

	public final void setPaddingTop(int paddingTop) {
		setInt(Property.paddingTop.name(), paddingTop);
	}

	public final int getPaddingRight() {
		return getInt(Property.paddingRight.name());
	}

	public final void setPaddingRight(int paddingRight) {
		setInt(Property.paddingRight.name(), paddingRight);
	}

	public final int getPaddingBottom() {
		return getInt(Property.paddingBottom.name());
	}

	public final void setPaddingBottom(int paddingBottom) {
		setInt(Property.paddingBottom.name(), paddingBottom);
	}

	public final String getMin() {
		return getString(Property.min.name());
	}

	public final void setMin(String min) {
		setString(Property.min.name(), min);
	}

	public final String getMax() {
		return getString(Property.max.name());
	}

	public final void setMax(String max) {
		setString(Property.max.name(), max);
	}
	
	public final int getMinAsNumber() {
		return getInt(Property.min.name());
	}

	public final void setMinAsNumber(int min) {
		setInt(Property.min.name(), min);
	}

	public final int getMaxAsNumber() {
		return getInt(Property.max.name());
	}

	public final void setMaxAsNumber(int max) {
		setInt(Property.max.name(), max);
	}

	public final List<String> getTicks() {
		return getStringArray(Property.ticks.name());
	}

	public final void setTicks(String... ticks) {
		setTicks(ArrayListHelper.build(ticks));
	}
	
	private final void setTicks(JsStringArrayList ticks){
		setStringArray(Property.ticks.name(), ticks);
	}

	public final int getStart() {
		return getInt(Property.start.name());
	}

	public final void setStart(int start) {
		setInt(Property.start.name(), start);
	}

	public final int getEnd() {
		return getInt(Property.end.name());
	}

	public final void setEnd(int end) {
		setInt(Property.end.name(), end);
	}

	public final List<Double> getTicksAsNumbers() {
		return getDoubleArray(Property.ticksAsNumbers.name());
	}

	public final void setTicksAsNumbers(double... ticksAsNumbers) {
		setInternalTicksAsNumbers(ArrayListHelper.build(ticksAsNumbers));
	}

	public final void setTicksAsNumbers(List<Double> ticksAsNumbers) {
		if (ticksAsNumbers instanceof JsDoubleArrayList){
			setInternalTicksAsNumbers((JsDoubleArrayList)ticksAsNumbers);
		} else {
			JsDoubleArrayList list = new JsDoubleArrayList();
			list.addAll(ticksAsNumbers);
			setInternalTicksAsNumbers(list);
		}
	}

	private final void setInternalTicksAsNumbers(JsDoubleArrayList ticksAsNumbers) {
		setDoubleArray(Property.ticksAsNumbers.name(), ticksAsNumbers);
	}

	public final int getZeroLineIndex() {
		return getInt(Property.zeroLineIndex.name());
	}

	public final void setZeroLineIndex(int zeroLineIndex) {
		setInt(Property.zeroLineIndex.name(), zeroLineIndex);
	}

	public final double getLabelRotation() {
		return getDouble(Property.labelRotation.name());
	}

	public final void setLabelRotation(double labelRotation) {
		setDouble(Property.labelRotation.name(), labelRotation);
	}

	public final int getWidth() {
		return getInt(Property.width.name());
	}

	public final void setWidth(int width) {
		setInt(Property.width.name(), width);
	}

	public final int getLeft() {
		return getInt(Property.left.name());
	}

	public final void setLeft(int left) {
		setInt(Property.left.name(), left);
	}

	public final int getRight() {
		return getInt(Property.right.name());
	}

	public final void setRight(int right) {
		setInt(Property.right.name(), right);
	}

	public final void setPosition(Position position) {
		setString(Property.position.name(), position.name());
	}

	public final Position getPosition() {
		return getValue(Property.position, Position.class, Position.top);
	}

	public final void setMargins(AxisMarginsItem item) {
		setJavaScriptObject(Property.margins.name(), item);
	}

	public final AxisMarginsItem getMargins() {
		return (AxisMarginsItem)getJavaScriptObject(Property.margins.name());
	}

	public final void setMinSize(AxisMinSizeItem minSize) {
		setJavaScriptObject(Property.minSize.name(), minSize);
	}

	public final AxisMinSizeItem getMinSize() {
		return (AxisMinSizeItem)getJavaScriptObject(Property.minSize.name());
	}

	public final String toContentString() {
		return "AxisItem [getId()=" + getId() + ", isHidden()=" + isHidden() + ", isFullWidth()=" + isFullWidth() + ", getWeight()=" + getWeight() + ", getMaxWidth()=" + getMaxWidth() + ", getMaxHeight()=" + getMaxHeight() + ", getHeight()=" + getHeight()
				+ ", getTop()=" + getTop() + ", getBottom()=" + getBottom() + ", getPaddingLeft()=" + getPaddingLeft() + ", getPaddingTop()=" + getPaddingTop() + ", getPaddingRight()=" + getPaddingRight() + ", getPaddingBottom()=" + getPaddingBottom()
				+ ", getMin()=" + getMin() + ", getMax()=" + getMax() + ", getTicks()=" + getTicks() + ", getStart()=" + getStart() + ", getEnd()=" + getEnd() + ", getTicksAsNumbers()=" + getTicksAsNumbers()
				+ ", getZeroLineIndex()=" + getZeroLineIndex() + ", getLabelRotation()=" + getLabelRotation() + ", getWidth()=" + getWidth() + ", getLeft()=" + getLeft() + ", getRight()=" + getRight() + ", getPosition()=" + getPosition() + "]";
	} 

}