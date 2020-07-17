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

import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.commons.Array;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayMixedObject;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.Constants;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.configuration.RadialAxis;
import org.pepstock.charba.client.enums.AxisKind;
import org.pepstock.charba.client.enums.AxisType;
import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.items.ScaleTickItem.ScaleTickItemFactory;
import org.pepstock.charba.client.options.IsScaleId;

/**
 * Wraps the scale item of CHART JS chart.<br>
 * This is a wrapper of scale of Chart (of CHART.JS).
 * 
 * @author Andrea "Stock" Stocchero
 */
public class ScaleItem extends BaseBoxNodeItem {

	private static final ScaleTickItemFactory ITEM_FACTORY = new ScaleTickItemFactory();

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		ID("id"),
		AXIS("axis"),
		MIN("min"),
		MAX("max"),
		TICKS("ticks"),
		LABEL_ROTATION("labelRotation"),
		START("start"),
		END("end"),
		X_CENTER("xCenter"),
		Y_CENTER("yCenter"),
		DRAWING_AREA("drawingArea"),
		POINT_LABELS("pointLabels"),
		TYPE("type"),
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

	// reference to scale id
	private IsScaleId scaleId;

	/**
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param scaleId this is the scale id.
	 * @param nativeObject native java script object which contains all properties.
	 */
	ScaleItem(IsScaleId scaleId, NativeObject nativeObject) {
		super(nativeObject);
		// checks scale id
		IsScaleId.checkIfValid(scaleId);
		// stores scale id
		this.scaleId = scaleId;
	}

	/**
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	// FIXME envelop?
	public ScaleItem(NativeObject nativeObject) {
		super(nativeObject);
		// stores scale id
		this.scaleId = null;
	}

	/**
	 * Returns the id of scale
	 * 
	 * @return the id of scale.
	 */
	public final IsScaleId getId() {
		// checks if scale has been previously set
		if (scaleId == null) {
			// gets the value
			String storedId = getValue(Property.ID, getType().getDefaultScaleId().value());
			// stores the scale id
			scaleId = IsScaleId.create(storedId);
		}
		// returns the stored scale id
		return scaleId;
	}

	/**
	 * Returns the unique id of scale.
	 * 
	 * @return the unique id of scale. Default or if does not exist is Default is {@link UndefinedValues#INTEGER}.
	 */
	public final int getCharbaId() {
		// FIXME to be checked
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
	 * Which kind of axis this is.<br>
	 * Possible values are: ''x', 'y' or 'r'.
	 * 
	 * @return the kind of axis.
	 */
	public final AxisKind getAxis() {
		return getValue(Property.AXIS, AxisKind.values(), getType().getDefaultScaleId().getAxisKind());
	}

	/**
	 * Returns the type of scale
	 * 
	 * @return the type of scale. Default is {@link org.pepstock.charba.client.enums.AxisType#CATEGORY}.
	 */
	public final AxisType getType() {
		return getValue(Property.TYPE, AxisType.values(), AxisType.CATEGORY);
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
		if (AxisType.TIME.equals(getType()) || AxisType.TIMESERIES.equals(getType())) {
			// returns a date
			return getValue(Property.MAX, (Date) null);
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
		if (AxisType.TIME.equals(getType()) || AxisType.TIMESERIES.equals(getType())) {
			// returns a date
			return getValue(Property.MIN, (Date) null);
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
	public final List<ScaleTickItem> getTicks() {
		// gets array from native object
		ArrayObject array = getArrayValue(Property.TICKS);
		// returns list
		return ArrayListHelper.unmodifiableList(array, ITEM_FACTORY);
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
		ArrayMixedObject array = getArrayValue(Property.POINT_LABELS);
		// checks if array is consistent
		if (array != null && !array.isEmpty()) {
			// creates list to return
			List<String> result = new LinkedList<>();
			// scans all array
			for (int i = 0; i < array.length(); i++) {
				result.add(getPointLabelAsString(array.get(i)));
			}
			// returns the result
			return Collections.unmodifiableList(result);
		}
		// returns the list
		return Collections.emptyList();
	}

	/**
	 * Returns a label at a specific index.<br>
	 * If at index there is multi-line label, returns labels with {@link Constants#LINE_SEPARATOR} as separator.
	 * 
	 * @param element element of the array
	 * @return a label of an element
	 */
	private String getPointLabelAsString(Object element) {
		if (Array.isArray(element)) {
			ArrayString internalArray = (ArrayString) element;
			// creates an string builder
			StringBuilder result = new StringBuilder();
			// scans all values
			for (int i = 0; i < internalArray.length(); i++) {
				// adds separator after 1 element
				if (i > 0) {
					result.append(Constants.LINE_SEPARATOR);
				}
				// adds to builder
				result.append(internalArray.get(i));
			}
			// returns string
			return result.toString();
		}
		// returns string
		// string can not be null, because checked during loading
		return (String) element;
	}

	/**
	 * Returns the position of node as string. This is implements the possibility to have a specific position for scale item, not mapped into {@link Position} enumeration, like for
	 * {@link RadialAxis}.
	 * 
	 * @return the position of node. Default is {@link org.pepstock.charba.client.enums.Position#TOP}.
	 */
	public final String getPositionAsString() {
		// gets the value of native object
		String value = getValue(Property.POSITION, UndefinedValues.STRING);
		// if value is not consistent and not a enum item
		if (value != null && !Key.hasKeyByValue(Position.values(), value)) {
			// returns simply the string
			return value;
		}
		// invokes the parent implementation
		return super.getPosition().value();
	}
}