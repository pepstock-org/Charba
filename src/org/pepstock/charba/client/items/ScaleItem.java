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
 * This item contains the new size of the chart after it has been resized.<br>
 * This object has been created ONLY when a resize event occurs.
 * 
 * @author Andrea "Stock" Stocchero
 * @see org.pepstock.charba.client.events.ChartResizeEvent
 * @see org.pepstock.charba.client.events.ChartResizeEventHandler
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

	protected ScaleItem(GenericJavaScriptObject javaScriptObject) {
		super(javaScriptObject);
		longestTextCache = new LongestTextCacheItem((GenericJavaScriptObject)getValue(Property.longestTextCache));
	}

	/**
	 * Returns the id of scale
	 * 
	 * @return the id of scale
	 */
	public final String getId() {
		return getValue(Property.id, UndefinedValues.STRING);
	}
	
	public final boolean isHidden() {
		return getValue(Property.hidden, UndefinedValues.BOOLEAN);
	}

	public final LongestTextCacheItem getLongestTextCache() {
		return longestTextCache;
	}

	public final int getMaxIndex() {
		return getValue(Property.maxIndex, UndefinedValues.INTEGER);
	}

	public final int getMinIndex() {
		return getValue(Property.minIndex, UndefinedValues.INTEGER);
	}
	
	public final Object getMax() {
		return JavaScriptFieldType.String.equals(type(Property.max)) ? getValue(Property.max, UndefinedValues.STRING) : getValue(Property.max, UndefinedValues.INTEGER);
	}

	public final Object getMin() {
		return JavaScriptFieldType.String.equals(type(Property.min)) ? getValue(Property.min, UndefinedValues.STRING) : getValue(Property.min, UndefinedValues.INTEGER);
	}
	
	public final List<String> getTicks(){
		return getStringArray(Property.ticks);
	}

	public final double getLabelRotation() {
		return getValue(Property.labelRotation, UndefinedValues.DOUBLE);
	}
	
	public final int getLongestLabelWidth() {
		return getValue(Property.longestLabelWidth, UndefinedValues.INTEGER);
	}

	public final double getStart() {
		return getValue(Property.start, UndefinedValues.DOUBLE);
	}

	public final double getEnd() {
		return getValue(Property.end, UndefinedValues.DOUBLE);
	}

	public final List<Double> getTicksAsNumber(){
		return getDoubleArray(Property.ticksAsNumbers);
	}

	public final int getZeroLineIndex() {
		return getValue(Property.zeroLineIndex, UndefinedValues.INTEGER);
	}

	public final int getXCenter() {
		return getValue(Property.xCenter, UndefinedValues.INTEGER);
	}

	public final int getYCenter() {
		return getValue(Property.yCenter, UndefinedValues.INTEGER);
	}

	public final int getDrawingArea() {
		return getValue(Property.drawingArea, UndefinedValues.INTEGER);
	}
	
	public final List<String> getPointLabels(){
		return getStringArray(Property.pointLabels);
	}


}