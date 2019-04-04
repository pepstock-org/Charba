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

import org.pepstock.charba.client.commons.ArrayDouble;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.enums.AxisType;

/**
 * Wraps the scale item of CHART JS chart.<br>
 * This is a wrapper of scale of Chart (of CHART.JS).
 * 
 * @author Andrea "Stock" Stocchero
 */
public class ScaleItem extends BaseBoxNodeItem {

	private final ScaleLongestTextCacheItem longestTextCache;

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		ID("id"),
		HIDDEN("hidden"),
		LONGEST_TEXT_CACHE("longestTextCache"),
		MIN_INDEX("minIndex"),
		MAX_INDEX("maxIndex"),
		MIN("min"),
		MAX("max"),
		TICKS("ticks"),
		LABEL_ROTATION("labelRotation"),
		LONGEST_LABEL_WIDTH("longestLabelWidth"),
		START("start"),
		END("end"),
		TICKS_AS_NUMBERS("ticksAsNumbers"),
		ZERO_LINE_INDEX("zeroLineIndex"),
		X_CENTER("xCenter"),
		Y_CENTER("yCenter"),
		DRAWING_AREA("drawingArea"),
		POINT_LABELS("pointLabels"),
		TYPE("type"),
		OPTIONS("options"),
		// internal key to store a unique id
		CHARBA_ID("_charbaId");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use into native object.
		 * 
		 * @param value value of property name
		 */
		private Property(String value) {
			this.value = value;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.Key#value()
		 */
		@Override
		public String value() {
			return value;
		}

	}

	/**
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	public ScaleItem(NativeObject nativeObject) {
		super(nativeObject);
		// initializes sub objects
		longestTextCache = new ScaleLongestTextCacheItem(getValue(Property.LONGEST_TEXT_CACHE));
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
	 * @return the id of scale. Default is {@link UndefinedValues#STRING}.
	 */
	public final String getId() {
		return getValue(Property.ID, UndefinedValues.STRING);
	}

	/**
	 * Returns the unique id of scale.
	 * 
	 * @return the unique id of scale. Default or if does not exist is Default is {@link UndefinedValues#INTEGER}.
	 */
	public final int getCharbaId() {
		// the unique id is under options object of scale item
		// checks if there is
		if (has(Property.OPTIONS)) {
			// gets the options object
			NativeObject object = getValue(Property.OPTIONS);
			// checks if the charba id exists and is a number
			if (ObjectType.Number.equals(JsHelper.get().typeOf(object, Property.CHARBA_ID.value()))) {
				// returns the number
				return JsHelper.get().propertyAsInt(object, Property.CHARBA_ID.value());
			}
		}
		// otherwise if here is undefined
		return UndefinedValues.INTEGER;
	}

	/**
	 * Returns the type of scale
	 * 
	 * @return the type of scale. Default is {@link org.pepstock.charba.client.enums.AxisType#category}.
	 */
	public final AxisType getType() {
		return getValue(Property.TYPE, AxisType.class, AxisType.category);
	}

	/**
	 * Returns true if this item represents a hidden scale.
	 * 
	 * @return <code>true</code> if this item represents a hidden scale. Default is {@link UndefinedValues#BOOLEAN}.
	 */
	public final boolean isHidden() {
		return getValue(Property.HIDDEN, UndefinedValues.BOOLEAN);
	}

	/**
	 * Returns the max index of scale.
	 * 
	 * @return the max index of scale. Default is {@link UndefinedValues#INTEGER}.
	 */
	public final int getMaxIndex() {
		return getValue(Property.MAX_INDEX, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the minimum index of scale.
	 * 
	 * @return the minimum index of scale. Default is {@link UndefinedValues#INTEGER}.
	 */
	public final int getMinIndex() {
		return getValue(Property.MIN_INDEX, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the max value of scale.
	 * 
	 * @return the max value of scale. Default is {@link UndefinedValues#INTEGER}.
	 */
	public final int getMax() {
		return getValue(Property.MAX, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the minimum value of scale.
	 * 
	 * @return the minimum value of scale. Default is {@link UndefinedValues#INTEGER}.
	 */
	public final int getMin() {
		return getValue(Property.MIN, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the max value of scale.
	 * 
	 * @return the max value of scale. Default is {@link UndefinedValues#STRING}.
	 */
	public final String getMaxAsString() {
		return getValue(Property.MAX, UndefinedValues.STRING);
	}

	/**
	 * Returns the minimum value of scale.
	 * 
	 * @return the minimum value of scale. Default is {@link UndefinedValues#STRING}.
	 */
	public final String getMinAsString() {
		return getValue(Property.MIN, UndefinedValues.STRING);
	}

	/**
	 * Returns the list of ticks.
	 * 
	 * @return the list of ticks.
	 */
	public final List<String> getTicks() {
		// gets array from native object
		ArrayString array = getArrayValue(Property.TICKS);
		// returns list
		return ArrayListHelper.unmodifiableList(array);
	}

	/**
	 * Returns the label rotation ratio.
	 * 
	 * @return the label rotation ratio. Default is {@link UndefinedValues#DOUBLE}.
	 */
	public final double getLabelRotation() {
		return getValue(Property.LABEL_ROTATION, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the longest width of label of ticks.
	 * 
	 * @return the longest width of label of ticks.Default is {@link UndefinedValues#INTEGER}.
	 */
	public final int getLongestLabelWidth() {
		return getValue(Property.LONGEST_LABEL_WIDTH, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the start value of scale.
	 * 
	 * @return the start value of scale. Default is {@link UndefinedValues#DOUBLE}.
	 */
	public final double getStart() {
		return getValue(Property.START, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the end value of scale.
	 * 
	 * @return the end value of scale. Default is {@link UndefinedValues#DOUBLE}.
	 */
	public final double getEnd() {
		return getValue(Property.END, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the list of ticks as number.
	 * 
	 * @return the list of ticks as number.
	 */
	public final List<Double> getTicksAsNumber() {
		ArrayDouble array = getArrayValue(Property.TICKS_AS_NUMBERS);
		return ArrayListHelper.unmodifiableList(array);
	}

	/**
	 * Returns the zero line index of scale.
	 * 
	 * @return the zero line index of scale. Default is {@link UndefinedValues#INTEGER}.
	 */
	public final int getZeroLineIndex() {
		return getValue(Property.ZERO_LINE_INDEX, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the X center of scale.
	 * 
	 * @return the X center of scale. Default is {@link UndefinedValues#INTEGER}.
	 */
	public final int getXCenter() {
		return getValue(Property.X_CENTER, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the Y center of scale.
	 * 
	 * @return the Y center of scale. Default is {@link UndefinedValues#INTEGER}.
	 */
	public final int getYCenter() {
		return getValue(Property.Y_CENTER, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the drawing area dimension of scale.
	 * 
	 * @return the drawing area dimension of scale. Default is {@link UndefinedValues#INTEGER}.
	 */
	public final int getDrawingArea() {
		return getValue(Property.DRAWING_AREA, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the list of point labels of scale.
	 * 
	 * @return the list of point labels of scale.
	 */
	public final List<String> getPointLabels() {
		// gets array from native object
		ArrayString array = getArrayValue(Property.POINT_LABELS);
		// returns the list
		return ArrayListHelper.unmodifiableList(array);
	}
}