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
package org.pepstock.charba.client.jsinterop.items;

import java.util.List;

import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.jsinterop.commons.ArrayDouble;
import org.pepstock.charba.client.jsinterop.commons.ArrayString;
import org.pepstock.charba.client.jsinterop.commons.NativeName;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * There are a number of configuration callbacks that can be used to change parameters in the scale at different points in the
 * update process.<br>
 * This is the CHART.JS item with all needed info about an axis.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = NativeName.OBJECT)
public final class AxisItem extends ScaleItem {
	
	@JsProperty
	public native AxisMarginsItem getMargins();
	
	@JsProperty
	public native AxisMinSizeItem getMinSize();
	
	@JsProperty(name = "top")
	native void setNativeTop(int top);
	
	@JsProperty(name = "right")
	native void setNativeRight(int right);

	@JsProperty(name = "bottom")
	native void setNativeBottom(int bottom);

	@JsProperty(name = "left")
	native void setNativeLeft(int left);
	
	@JsProperty(name = "fullWidth")
	native void setNativeFullWidth(boolean fullWidth);

	@JsProperty(name = "position")
	native void setNativePosition(String position);

	@JsProperty(name = "weight")
	native void setNativeWeight(double weight);

	@JsProperty(name = "width")
	native void setNativeWidth(int width);

	@JsProperty(name = "height")
	native void setNativeHeight(int height);

	@JsProperty(name = "maxWidth")
	native void setNativeMaxWidth(double maxWidth);

	@JsProperty(name = "maxHeight")
	native void setNativeMaxHeight(double maxHeight);

	@JsProperty(name = "paddingTop")
	native void setNativePaddingTop(int paddingTop);

	@JsProperty(name = "paddingRight")
	native void setNativePaddingRight(int paddingRight);

	@JsProperty(name = "paddingBottom")
	native void setNativePaddingBottom(int paddingBottom);

	@JsProperty(name = "paddingLeft")
	native void setNativePaddingLeft(int paddingLeft);
	
	@JsProperty(name = "hidden")
	native void setNativeHidden(boolean hidden);

	@JsProperty(name = "max")
	native void setNativeMax(Object max);

	@JsProperty(name = "min")
	native void setNativeMin(Object min);

	@JsProperty(name = "ticks")
	native void setNativeTicks(ArrayString ticks);

	@JsProperty(name = "labelRotation")
	native void setNativeLabelRotation(double labelRotation);
	
	@JsProperty(name = "start")
	native void setNativeStart(double start);
	
	@JsProperty(name = "end")
	native void setNativeEnd(double end);

	@JsProperty(name = "ticksAsNumbers")
	native void setNativeTicksAsNumber(ArrayDouble ticks);

	@JsProperty(name = "zeroLineIndex")
	native void setNativeZeroLineIndex(int zeroLineIndex);
	
	//-----------------------------------------------//


	/**
	 * Sets if the axis must be hidden.
	 * 
	 * @param hidden <code>true</code> if the axis must be hidden, otherwise <code>false</code>.
	 */
	@JsOverlay
	public void setHidden(boolean hidden) {
		setNativeHidden(hidden);
	}

	/**
	 * Sets the full width of axis in pixel
	 * 
	 * @param fullWidth the full width of axis
	 */
	@JsOverlay
	public void setFullWidth(boolean fullWidth) {
		setNativeFullWidth(fullWidth);
	}

	/**
	 * Sets the weight of axis
	 * 
	 * @param weight the weight of axis
	 */
	@JsOverlay
	public void setWeight(int weight) {
		setNativeWeight(weight);
	}

	/**
	 * Sets the max width of axis in pixel.
	 * 
	 * @param maxWidth the max width of axis in pixel.
	 */
	@JsOverlay
	public void setMaxWidth(int maxWidth) {
		setNativeMaxWidth(maxWidth);
	}

	/**
	 * Sets the max height of the axis in pixel
	 * 
	 * @param maxHeight the max height of the axis in pixel
	 */
	@JsOverlay
	public void setMaxHeight(int maxHeight) {
		setNativeMaxHeight(maxHeight);
	}

	/**
	 * Sets the height of axis in pixel.
	 * 
	 * @param height the height of axis in pixel.
	 */
	@JsOverlay
	public void setHeight(int height) {
		setNativeHeight(height);
	}

	/**
	 * Sets the top location in pixel
	 * 
	 * @param top the top location in pixel
	 */
	@JsOverlay
	public void setTop(int top) {
		setNativeTop(top);
	}

	/**
	 * Sets the bottom location in pixel
	 * 
	 * @param bottom the bottom location in pixel
	 */
	@JsOverlay
	public void setBottom(int bottom) {
		setNativeBottom(bottom);
	}

	/**
	 * Sets the padding left in pixel
	 * 
	 * @param paddingLeft the padding left in pixel
	 */
	@JsOverlay
	public void setPaddingLeft(int paddingLeft) {
		setNativePaddingLeft(paddingLeft);
	}

	/**
	 * Sets the padding top in pixel
	 * 
	 * @param paddingTop the padding top in pixel
	 */
	@JsOverlay
	public void setPaddingTop(int paddingTop) {
		setNativePaddingTop(paddingTop);
	}

	/**
	 * Sets the padding right in pixel
	 * 
	 * @param paddingRight the padding right in pixel
	 */
	@JsOverlay
	public void setPaddingRight(int paddingRight) {
		setNativePaddingRight(paddingRight);
	}

	/**
	 * Sets the padding bottom in pixel
	 * 
	 * @param paddingBottom the padding bottom in pixel
	 */
	@JsOverlay
	public void setPaddingBottom(int paddingBottom) {
		setNativePaddingBottom(paddingBottom);
	}

	/**
	 * Sets the minimum value of axis
	 * 
	 * @param min the minimum value of axis
	 */
	@JsOverlay
	public void setMin(String min) {
		setNativeMin(min);
	}

	/**
	 * Sets the maximum value of axis
	 * 
	 * @param max the maximum value of axis
	 */
	@JsOverlay
	public void setMax(String max) {
		setNativeMax(max);
	}

	/**
	 * Sets the minimum value of axis
	 * 
	 * @param min the minimum value of axis
	 */
	@JsOverlay
	public void setMinAsNumber(int min) {
		setNativeMin(min);
	}

	/**
	 * Sets the maximum value of axis
	 * 
	 * @param max the maximum value of axis
	 */
	@JsOverlay
	public void setMaxAsNumber(int max) {
		setNativeMax(max);
	}

	/**
	 * Sets an array of ticks
	 * 
	 * @param ticks the array of ticks
	 */
	@JsOverlay
	public void setTicks(String... ticks) {
		setNativeTicks(ArrayString.of(ticks));
	}

	/**
	 * Sets the start value of the axis
	 * 
	 * @param start the start value of the axis
	 */
	@JsOverlay
	public void setStart(int start) {
		setNativeStart(start);
	}

	/**
	 * Sets the end value of the axis
	 * 
	 * @param end the end value of the axis
	 */
	@JsOverlay
	public void setEnd(int end) {
		setNativeEnd(end);
	}

	/**
	 * Sets an array of ticks
	 * 
	 * @param ticksAsNumbers the array of ticks
	 */
	@JsOverlay
	public void setTicksAsNumbers(double... ticksAsNumbers) {
		setNativeTicksAsNumber(ArrayDouble.of(ticksAsNumbers));
	}

	/**
	 * Sets the list of ticks
	 * 
	 * @param ticksAsNumbers the list of ticks
	 */
	@JsOverlay
	public void setTicksAsNumbers(List<Double> ticksAsNumbers) {
		setNativeTicksAsNumber(ArrayDouble.of(ticksAsNumbers));
	}

	/**
	 * Sets the index of line 0 of axis
	 * 
	 * @param zeroLineIndex the index of line 0 of axis
	 */
	@JsOverlay
	public void setZeroLineIndex(int zeroLineIndex) {
		setNativeZeroLineIndex(zeroLineIndex);
	}

	/**
	 * Sets the label rotation value
	 * 
	 * @param labelRotation the label rotation value
	 */
	@JsOverlay
	public void setLabelRotation(double labelRotation) {
		setNativeLabelRotation(labelRotation);
	}

	/**
	 * Sets the width of axis
	 * 
	 * @param width the width of axis
	 */
	@JsOverlay
	public void setWidth(int width) {
		setNativeWidth(width);
	}

	/**
	 * Sets the left location in pixel
	 * 
	 * @param left the left location in pixel
	 */
	@JsOverlay
	public void setLeft(int left) {
		setNativeLeft(left);
	}

	/**
	 * Sets the right location in pixel
	 * 
	 * @param right the right location in pixel
	 */
	@JsOverlay
	public void setRight(int right) {
		setNativeRight(right);
	}

	/**
	 * Sets the position of axis
	 * 
	 * @param position the position of axis
	 * @see org.pepstock.charba.client.enums.Position
	 */
	@JsOverlay
	public void setPosition(Position position) {
		setNativePosition(position.name());
	}

}