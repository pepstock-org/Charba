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

import org.pepstock.charba.client.jsinterop.commons.ArrayDouble;
import org.pepstock.charba.client.jsinterop.commons.ArrayListHelper;
import org.pepstock.charba.client.jsinterop.commons.ArrayString;
import org.pepstock.charba.client.jsinterop.commons.Checker;
import org.pepstock.charba.client.jsinterop.commons.NativeName;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Wraps the scale item of CHART JS chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = NativeName.OBJECT)
public class ScaleItem extends BaseBoxNodeItem {
	
	///private final  longestTextCache;
	@JsProperty
	public native ScaleLongestTextCacheItem getLongestTextCache();
	
	@JsProperty(name = "id")
	native String getNativeId();

	@JsProperty(name = "hidden")
	native boolean isNativeHidden();

	@JsProperty(name = "maxIndex")
	native int getNativeMaxIndex();

	@JsProperty(name = "minIndex")
	native int getNativeMinIndex();

	@JsProperty(name = "max")
	native Object getNativeMax();

	@JsProperty(name = "min")
	native Object getNativeMin();

	@JsProperty(name = "ticks")
	native ArrayString getNativeTicks();

	@JsProperty(name = "labelRotation")
	native double getNativeLabelRotation();
	
	@JsProperty(name = "longestLabelWidth")
	native int getNativeLongestLabelWidth();
	
	@JsProperty(name = "start")
	native double getNativeStart();
	
	@JsProperty(name = "end")
	native double getNativeEnd();

	@JsProperty(name = "ticksAsNumbers")
	native ArrayDouble getNativeTicksAsNumber();

	@JsProperty(name = "zeroLineIndex")
	native int getNativeZeroLineIndex();

	@JsProperty(name = "xCenter")
	native int getNativeXCenter();

	@JsProperty(name = "yCenter")
	native int getNativeYCenter();

	@JsProperty(name = "drawingArea")
	native int getNativeDrawingArea();

	@JsProperty(name = "pointLabels")
	native ArrayString getNativePointLabels();
	
	//-----------------------------------------------//

	/**
	 * Returns the id of scale
	 * 
	 * @return the id of scale. Default is {@link org.pepstock.charba.client.items.UndefinedValues#STRING}.
	 */
	@JsOverlay
	public final String getId() {
		return Checker.check(getNativeId(), UndefinedValues.STRING);
	}

	/**
	 * Returns true if this item represents a hidden scale.
	 * 
	 * @return <code>true</code> if this item represents a hidden scale. Default is {@link org.pepstock.charba.client.items.UndefinedValues#BOOLEAN}.
	 */
	@JsOverlay
	public final boolean isHidden() {
		return Checker.check(isNativeHidden(), UndefinedValues.BOOLEAN);
	}

	/**
	 * Returns the max index of scale.
	 * 
	 * @return the max index of scale. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	@JsOverlay
	public final int getMaxIndex() {
		return Checker.check(getNativeMaxIndex(), UndefinedValues.INTEGER);
	}

	/**
	 * Returns the min index of scale.
	 * 
	 * @return the min index of scale. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	@JsOverlay
	public final int getMinIndex() {
		return Checker.check(getNativeMaxIndex(), UndefinedValues.INTEGER);
	}

	/**
	 * Returns the max value of scale. Base on scale type, it could return a String or an Integer.
	 * 
	 * @return the max value of scale.
	 */
	@JsOverlay
	public final int getMax() {
		return Checker.check(getNativeMax(), UndefinedValues.INTEGER);
	}

	/**
	 * Returns the max value of scale. Base on scale type, it could return a String or an Integer.
	 * 
	 * @return the max value of scale.
	 */
	@JsOverlay
	public final String getMaxAsString() {
		return Checker.check(getNativeMax(), UndefinedValues.STRING);
	}

	/**
	 * Returns the min value of scale. Base on scale type, it could return a String or an Integer.
	 * 
	 * @return the min value of scale.
	 */
	@JsOverlay
	public final int getMin() {
		return Checker.check(getNativeMin(), UndefinedValues.INTEGER);
	}

	/**
	 * Returns the min value of scale. Base on scale type, it could return a String or an Integer.
	 * 
	 * @return the min value of scale.
	 */
	@JsOverlay
	public final String getMinAsString() {
		return Checker.check(getNativeMin(), UndefinedValues.STRING);
	}

	/**
	 * Returns the list of ticks.
	 * 
	 * @return the list of ticks.
	 */
	@JsOverlay
	public final List<String> getTicks() {
		return ArrayListHelper.unmodifiableList(getNativeTicks());
	}

	/**
	 * Returns the label rotation ratio.
	 * 
	 * @return the label rotation ratio. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	@JsOverlay
	public final double getLabelRotation() {
		return Checker.check(getNativeLabelRotation(), UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the longest width of label of ticks.
	 * 
	 * @return the longest width of label of ticks.Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	@JsOverlay
	public final int getLongestLabelWidth() {
		return Checker.check(getNativeLongestLabelWidth(), UndefinedValues.INTEGER);
	}

	/**
	 * Returns the start value of scale.
	 * 
	 * @return the start value of scale. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	@JsOverlay
	public final double getStart() {
		return Checker.check(getNativeStart(), UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the end value of scale.
	 * 
	 * @return the end value of scale. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	@JsOverlay
	public final double getEnd() {
		return Checker.check(getNativeEnd(), UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the list of ticks as number.
	 * 
	 * @return the list of ticks as number.
	 */
	@JsOverlay
	public final List<Double> getTicksAsNumber() {
		return ArrayListHelper.unmodifiableList(getNativeTicksAsNumber());
	}

	/**
	 * Returns the zero line index of scale.
	 * 
	 * @return the zero line index of scale. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	@JsOverlay
	public final int getZeroLineIndex() {
		return Checker.check(getNativeZeroLineIndex(), UndefinedValues.INTEGER);
	}

	/**
	 * Returns the X center of scale.
	 * 
	 * @return the X center of scale. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	@JsOverlay
	public final int getXCenter() {
		return Checker.check(getNativeXCenter(), UndefinedValues.INTEGER);
	}

	/**
	 * Returns the Y center of scale.
	 * 
	 * @return the Y center of scale. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	@JsOverlay
	public final int getYCenter() {
		return Checker.check(getNativeYCenter(), UndefinedValues.INTEGER);
	}

	/**
	 * Returns the drawing area dimension of scale.
	 * 
	 * @return the drawing area dimension of scale. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	@JsOverlay
	public final int getDrawingArea() {
		return Checker.check(getNativeDrawingArea(), UndefinedValues.INTEGER);
	}

	/**
	 * Returns the list of point labels of scale.
	 * 
	 * @return the list of point labels of scale.
	 */
	@JsOverlay
	public final List<String> getPointLabels() {
		return ArrayListHelper.unmodifiableList(getNativePointLabels());
	}

}