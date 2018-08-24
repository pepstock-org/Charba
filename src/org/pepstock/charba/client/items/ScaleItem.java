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

import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.JavaScriptFieldType;
import org.pepstock.charba.client.commons.Key;

/**
 * Wraps the scale item of CHART JS chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class ScaleItem extends BaseBoxNodeItem {
	
	private final LongestTextCacheItem longestTextCache;

	/**
	 * Name of fields of JavaScript object.
	 */
	protected enum Property implements Key
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
		pointLabels
	}

	/**
	 * Wraps the CHART.JS java script object.
	 * 
	 * @param javaScriptObject CHART.JS java script object
	 */
	protected ScaleItem(GenericJavaScriptObject javaScriptObject) {
		super(javaScriptObject);
		// initializes sub objects
		longestTextCache = new LongestTextCacheItem((GenericJavaScriptObject) getValue(Property.longestTextCache));
	}

	/**
	 * Returns the longest text cache item.
	 * 
	 * @return the longest text cache item.
	 */
	public final LongestTextCacheItem getLongestTextCache() {
		return longestTextCache;
	}

	/**
	 * Returns the id of scale
	 * 
	 * @return the id of scale. Default is {@link org.pepstock.charba.client.items.UndefinedValues#STRING}.
	 */
	public final String getId() {
		return getValue(Property.id, UndefinedValues.STRING);
	}

	/**
	 * Returns true if this item represents a hidden scale.
	 * 
	 * @return <code>true</code> if this item represents a hidden scale. Default is {@link org.pepstock.charba.client.items.UndefinedValues#BOOLEAN}.
	 */
	public final boolean isHidden() {
		return getValue(Property.hidden, UndefinedValues.BOOLEAN);
	}

	/**
	 * Returns the max index of scale.
	 * 
	 * @return the max index of scale. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	public final int getMaxIndex() {
		return getValue(Property.maxIndex, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the min index of scale.
	 * 
	 * @return the min index of scale. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	public final int getMinIndex() {
		return getValue(Property.minIndex, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the max value of scale. Base on scale type, it could return a String or an Integer.
	 * 
	 * @return the max value of scale.
	 */
	public final Object getMax() {
		return JavaScriptFieldType.String.equals(type(Property.max)) ? getValue(Property.max, UndefinedValues.STRING) : getValue(Property.max, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the min value of scale. Base on scale type, it could return a String or an Integer.
	 * 
	 * @return the min value of scale.
	 */
	public final Object getMin() {
		return JavaScriptFieldType.String.equals(type(Property.min)) ? getValue(Property.min, UndefinedValues.STRING) : getValue(Property.min, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the list of ticks.
	 * 
	 * @return the list of ticks.
	 */
	public final List<String> getTicks() {
		return getStringArray(Property.ticks);
	}

	/**
	 * Returns the label rotation ratio.
	 * 
	 * @return the label rotation ratio. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	public final double getLabelRotation() {
		return getValue(Property.labelRotation, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the longest width of label of ticks.
	 * 
	 * @return the longest width of label of ticks.Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	public final int getLongestLabelWidth() {
		return getValue(Property.longestLabelWidth, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the start value of scale.
	 * 
	 * @return the start value of scale. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	public final double getStart() {
		return getValue(Property.start, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the end value of scale.
	 * 
	 * @return the end value of scale. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
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
		return getDoubleArray(Property.ticksAsNumbers);
	}

	/**
	 * Returns the zero line index of scale.
	 * 
	 * @return the zero line index of scale. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	public final int getZeroLineIndex() {
		return getValue(Property.zeroLineIndex, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the X center of scale.
	 * 
	 * @return the X center of scale. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	public final int getXCenter() {
		return getValue(Property.xCenter, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the Y center of scale.
	 * 
	 * @return the Y center of scale. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	public final int getYCenter() {
		return getValue(Property.yCenter, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the drawing area dimension of scale.
	 * 
	 * @return the drawing area dimension of scale. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
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
		return getStringArray(Property.pointLabels);
	}

}