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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.pepstock.charba.client.commons.ArrayDouble;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.configuration.CartesianLogarithmicAxis;
import org.pepstock.charba.client.configuration.CartesianTimeAxis;
import org.pepstock.charba.client.configuration.RadialAxis;
import org.pepstock.charba.client.enums.AxisType;
import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.items.ScaleTickItem.ScaleTickItemFactory;
import org.pepstock.charba.client.items.TimeTickItem.TimeTickItemFactory;

/**
 * Wraps the scale item of CHART JS chart.<br>
 * This is a wrapper of scale of Chart (of CHART.JS).
 * 
 * @author Andrea "Stock" Stocchero
 */
public class ScaleItem extends BaseBoxNodeItem {

	private final ScaleLongestTextCacheItem longestTextCache;

	private final ScaleTickItemFactory itemFactory;

	private final TimeTickItemFactory timeItemFactory;

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
		MIN_NOT_ZERO("minNotZero"),
		MIN("min"),
		MAX("max"),
		TICKS("ticks"),
		LABEL_ROTATION("labelRotation"),
		LONGEST_LABEL_WIDTH("longestLabelWidth"),
		START("start"),
		END("end"),
		TICKS_AS_NUMBERS("ticksAsNumbers"),
		TICKS_VALUES("tickValues"),
		ZERO_LINE_INDEX("zeroLineIndex"),
		X_CENTER("xCenter"),
		Y_CENTER("yCenter"),
		DRAWING_AREA("drawingArea"),
		POINT_LABELS("pointLabels"),
		TYPE("type"),
		INTERNAL_TICKS("_ticks"),
		// override the key of parent
		POSITION("position"),
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
		// creates the factories
		itemFactory = new ScaleTickItemFactory();
		timeItemFactory = new TimeTickItemFactory();
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
			if (ObjectType.NUMBER.equals(JsHelper.get().typeOf(object, Property.CHARBA_ID.value()))) {
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
	 * @return the type of scale. Default is {@link org.pepstock.charba.client.enums.AxisType#CATEGORY}.
	 */
	public final AxisType getType() {
		return getValue(Property.TYPE, AxisType.class, AxisType.CATEGORY);
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
	 * @return the max value of scale. Default is {@link UndefinedValues#DOUBLE}.
	 */
	public final double getMax() {
		return getValueForMultipleKeyTypes(Property.MAX, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the minimum value of scale.
	 * 
	 * @return the minimum value of scale. Default is {@link UndefinedValues#DOUBLE}.
	 */
	public final double getMin() {
		return getValueForMultipleKeyTypes(Property.MIN, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the minimum value not zero of scale, only for {@link CartesianLogarithmicAxis}.
	 * 
	 * @return the minimum value not zero of scale. Default is {@link UndefinedValues#DOUBLE}.
	 */
	public final double getMinNotZero() {
		return getValue(Property.MIN_NOT_ZERO, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the max value of scale.
	 * 
	 * @return the max value of scale. Default is {@link UndefinedValues#STRING}.
	 */
	public final String getMaxAsString() {
		return getValueForMultipleKeyTypes(Property.MAX, UndefinedValues.STRING);
	}

	/**
	 * Returns the minimum value of scale.
	 * 
	 * @return the minimum value of scale. Default is {@link UndefinedValues#STRING}.
	 */
	public final String getMinAsString() {
		return getValueForMultipleKeyTypes(Property.MIN, UndefinedValues.STRING);
	}

	/**
	 * Returns the max value of scale.
	 * 
	 * @return the max value of scale. If missing returns is <code>null</code>.
	 */
	public final Date getMaxAsDate() {
		// checks if the axis is a time one
		if (AxisType.TIME.equals(getType())) {
			// returns a date
			return getValue(Property.MAX, (Date)null);
		}
		// if here is not a number
		// then returns undefined double
		return null;
	}

	/**
	 * Returns the minimum value of scale.
	 * 
	 * @return the minimum value of scale. If missing returns is <code>null</code>.
	 */
	public final Date getMinAsDate() {
		// checks if the axis is a time one
		if (AxisType.TIME.equals(getType())) {
			// returns a date
			return getValue(Property.MIN, (Date)null);
		}
		// if here is not a number
		// then returns undefined double
		return null;
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
	 * Returns the list of tick items.
	 * 
	 * @return the list of tick items.
	 */
	public final List<ScaleTickItem> getTickItems() {
		// gets array from native object
		ArrayObject array = getArrayValue(Property.INTERNAL_TICKS);
		// checks if is a time axis
		if (!AxisType.TIME.equals(getType())) {
			// returns list
			return ArrayListHelper.unmodifiableList(array, itemFactory);
		}
		// if here the axis is time
		// therefore returns an empty list
		return Collections.unmodifiableList(new ArrayList<>());
	}

	/**
	 * Returns the list of time tick items, only for {@link CartesianTimeAxis}.
	 * 
	 * @return the list of time tick items.
	 */
	public final List<TimeTickItem> getTimeTickItems() {
		// gets array from native object
		ArrayObject array = getArrayValue(Property.INTERNAL_TICKS);
		// checks if is a time axis
		if (AxisType.TIME.equals(getType())) {
			// returns list
			return ArrayListHelper.unmodifiableList(array, timeItemFactory);
		}
		// if here the axis is time
		// therefore returns an empty list
		return Collections.unmodifiableList(new ArrayList<>());
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
		// gets a key reference
		Key key = null;
		// checks if the axis type is log
		if (AxisType.LOGARITHMIC.equals(getType())) {
			// sets key value
			key = Property.TICKS_VALUES;
		} else {
			// sets for all other linear axes
			key = Property.TICKS_AS_NUMBERS;
		}
		// array reference to return
		ArrayDouble array = getArrayValue(key);
		// returns array as list
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

	/**
	 * Returns the position of node as string. This is implements the possibility to have a specific position for scale item,
	 * not mapped into {@link org.pepstock.charba.client.enums.Position} enumeration, like for {@link RadialAxis}.
	 * 
	 * @return the position of node. Default is {@link org.pepstock.charba.client.enums.Position#TOP}.
	 */
	public final String getPositionAsString() {
		// gets the value of native object
		String value = getValue(Property.POSITION, UndefinedValues.STRING);
		// if value is not consistent and not a enum item
		if (value != null && !Key.hasKeyByValue(Position.class, value)) {
			// returns simply the string
			return value;
		}
		// invokes the parent implementation
		return super.getPosition().value();
	}
}