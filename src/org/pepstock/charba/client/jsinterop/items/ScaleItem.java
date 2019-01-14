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

import org.pepstock.charba.client.jsinterop.commons.Key;
import org.pepstock.charba.client.jsinterop.enums.AxisType;
import org.pepstock.charba.client.jsinterop.commons.ArrayDouble;
import org.pepstock.charba.client.jsinterop.commons.ArrayListHelper;
import org.pepstock.charba.client.jsinterop.commons.ArrayString;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;

/**
 * Wraps the scale item of CHART JS chart.<br>
 * This is a wrapper of scale of Chart (of CHART.JS).
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 */
public class ScaleItem extends BaseBoxNodeItem {
	
	private final ScaleLongestTextCacheItem longestTextCache;
	
	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		id,
		hidden,
		longestTextCache,
		minIndex,
		maxIndex,
		min,
		max,
		ticks,
		labelRotation,
		longestLabelWidth,
		start,
		end,
		ticksAsNumbers,
		zeroLineIndex,
		xCenter,
		yCenter,
		drawingArea,
		pointLabels,
		type
	}

	/**
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	public ScaleItem(NativeObject nativeObject) {
		super(nativeObject);
		// initializes sub objects
		longestTextCache = new ScaleLongestTextCacheItem(getValue(Property.longestTextCache));
	}

	/**
	 * Returns the longest text cache item.
	 * 
	 * @return the longest text cache item.
	 */
	public final ScaleLongestTextCacheItem getLongestTextCache() {
		return longestTextCache;
	}

	/**
	 * Returns the id of scale
	 * 
	 * @return the id of scale. Default is {@link org.pepstock.charba.client.jsinterop.items.UndefinedValues#STRING}.
	 */
	public final String getId() {
		return getValue(Property.id, UndefinedValues.STRING);
	}
	
	/**
	 * Returns the type of scale
	 * 
	 * @return the type of scale. Default is {@link org.pepstock.charba.client.jsinterop.enums.AxisType#category}.
	 */
	public final AxisType getType() {
		return getValue(Property.type, AxisType.class, AxisType.category);
	}

	/**
	 * Returns true if this item represents a hidden scale.
	 * 
	 * @return <code>true</code> if this item represents a hidden scale. Default is {@link org.pepstock.charba.client.jsinterop.items.UndefinedValues#BOOLEAN}.
	 */
	public final boolean isHidden() {
		return getValue(Property.hidden, UndefinedValues.BOOLEAN);
	}

	/**
	 * Returns the max index of scale.
	 * 
	 * @return the max index of scale. Default is {@link org.pepstock.charba.client.jsinterop.items.UndefinedValues#INTEGER}.
	 */
	public final int getMaxIndex() {
		return getValue(Property.maxIndex, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the min index of scale.
	 * 
	 * @return the min index of scale. Default is {@link org.pepstock.charba.client.jsinterop.items.UndefinedValues#INTEGER}.
	 */
	public final int getMinIndex() {
		return getValue(Property.minIndex, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the max value of scale. 
	 * 
	 * @return the max value of scale. Default is {@link org.pepstock.charba.client.jsinterop.items.UndefinedValues#INTEGER}.
	 */
	public final int getMax() {
		return getValue(Property.max, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the min value of scale.
	 * 
	 * @return the min value of scale. Default is {@link org.pepstock.charba.client.jsinterop.items.UndefinedValues#INTEGER}.
	 */
	public final int getMin() {
		return getValue(Property.min, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the max value of scale. 
	 * 
	 * @return the max value of scale. Default is {@link org.pepstock.charba.client.jsinterop.items.UndefinedValues#STRING}.
	 */
	public final String getMaxAsString() {
		return getValue(Property.max, UndefinedValues.STRING);
	}

	/**
	 * Returns the min value of scale. 
	 * 
	 * @return the min value of scale. Default is {@link org.pepstock.charba.client.jsinterop.items.UndefinedValues#STRING}.
	 */
	public final String getMinAsString() {
		return getValue(Property.min, UndefinedValues.STRING);
	}

	/**
	 * Returns the list of ticks.
	 * 
	 * @return the list of ticks.
	 */
	public final List<String> getTicks() {
		// gets array from native object
		ArrayString array = getArrayValue(Property.ticks);
		// returns list
		return ArrayListHelper.unmodifiableList(array);
	}

	/**
	 * Returns the label rotation ratio.
	 * 
	 * @return the label rotation ratio. Default is {@link org.pepstock.charba.client.jsinterop.items.UndefinedValues#DOUBLE}.
	 */
	public final double getLabelRotation() {
		return getValue(Property.labelRotation, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the longest width of label of ticks.
	 * 
	 * @return the longest width of label of ticks.Default is {@link org.pepstock.charba.client.jsinterop.items.UndefinedValues#INTEGER}.
	 */
	public final int getLongestLabelWidth() {
		return getValue(Property.longestLabelWidth, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the start value of scale.
	 * 
	 * @return the start value of scale. Default is {@link org.pepstock.charba.client.jsinterop.items.UndefinedValues#DOUBLE}.
	 */
	public final double getStart() {
		return getValue(Property.start, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the end value of scale.
	 * 
	 * @return the end value of scale. Default is {@link org.pepstock.charba.client.jsinterop.items.UndefinedValues#DOUBLE}.
	 */
	public final double getEnd() {
		return getValue(Property.end, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the list of ticks as number.
	 * 
	 * @return the list of ticks as number.
	 */
	public final List<Double> getTicksAsNumber() {
		ArrayDouble array = getArrayValue(Property.ticksAsNumbers);
		return ArrayListHelper.unmodifiableList(array);
	}

	/**
	 * Returns the zero line index of scale.
	 * 
	 * @return the zero line index of scale. Default is {@link org.pepstock.charba.client.jsinterop.items.UndefinedValues#INTEGER}.
	 */
	public final int getZeroLineIndex() {
		return getValue(Property.zeroLineIndex, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the X center of scale.
	 * 
	 * @return the X center of scale. Default is {@link org.pepstock.charba.client.jsinterop.items.UndefinedValues#INTEGER}.
	 */
	public final int getXCenter() {
		return getValue(Property.xCenter, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the Y center of scale.
	 * 
	 * @return the Y center of scale. Default is {@link org.pepstock.charba.client.jsinterop.items.UndefinedValues#INTEGER}.
	 */
	public final int getYCenter() {
		return getValue(Property.yCenter, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the drawing area dimension of scale.
	 * 
	 * @return the drawing area dimension of scale. Default is {@link org.pepstock.charba.client.jsinterop.items.UndefinedValues#INTEGER}.
	 */
	public final int getDrawingArea() {
		return getValue(Property.drawingArea, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the list of point labels of scale.
	 * 
	 * @return the list of point labels of scale.
	 */
	public final List<String> getPointLabels() {
		// gets array from native object
		ArrayString array = getArrayValue(Property.pointLabels);
		// returns the list
		return ArrayListHelper.unmodifiableList(array);
	}
}