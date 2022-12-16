/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.options;

import java.util.List;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.defaults.IsDefaultScaleBorder;

/**
 * Defines options for the border that run perpendicular to the axis.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ScaleBorder extends AbstractModel<AbstractScale, IsDefaultScaleBorder> implements IsDefaultScaleBorder {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		DISPLAY("display"),
		COLOR("color"),
		WIDTH("width"),
		DASH("dash"),
		DASH_OFFSET("dashOffset"),
		Z("z");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use in the native object.
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
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param scale scale/axis of this object.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	ScaleBorder(AbstractScale scale, Key childKey, IsDefaultScaleBorder defaultValues, NativeObject nativeObject) {
		super(scale, childKey, defaultValues, nativeObject);
	}

	/**
	 * If <code>true</code>, draw border at the edge between the axis and the chart area.
	 * 
	 * @param display if <code>true</code>, draw border at the edge between the axis and the chart area.
	 */
	public void setDisplay(boolean display) {
		setValueAndAddToParent(Property.DISPLAY, display);
	}

	/**
	 * If <code>true</code>, draw border at the edge between the axis and the chart area.
	 * 
	 * @return if <code>true</code>, draw border at the edge between the axis and the chart area.
	 */
	@Override
	public boolean isDisplay() {
		return getValue(Property.DISPLAY, getDefaultValues().isDisplay());
	}

	/**
	 * If set, used as the color of the border line.<br>
	 * If unset, the first color option is resolved and used.
	 * 
	 * @param color if set, used as the color of the border line.<br>
	 *            If unset, the first color option is resolved and used.
	 */
	public void setColor(IsColor color) {
		setColor(IsColor.checkAndGetValue(color));
	}

	/**
	 * If set, used as the color of the border line.<br>
	 * If unset, the first color option is resolved and used.
	 * 
	 * @param color if set, used as the color of the border line.<br>
	 *            If unset, the first color option is resolved and used.
	 */
	public void setColor(String color) {
		setValueAndAddToParent(Property.COLOR, color);
	}

	/**
	 * If set, used as the color of the border line.<br>
	 * If unset, the first color option is resolved and used.
	 * 
	 * @return if set, used as the color of the border line.<br>
	 *         If unset, the first color option is resolved and used.
	 */
	@Override
	public String getColorAsString() {
		return getValue(Property.COLOR, getDefaultValues().getColorAsString());
	}

	/**
	 * If set, used as the color of the border line.<br>
	 * If unset, the first color option is resolved and used.
	 * 
	 * @return if set, used as the color of the border line.<br>
	 *         If unset, the first color option is resolved and used.
	 */
	public IsColor getColor() {
		return ColorBuilder.parse(getColorAsString());
	}

	/**
	 * Sets the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @param borderDash the line dash pattern used when stroking lines
	 */
	public final void setDash(int... borderDash) {
		setArrayValueAndAddToParent(Property.DASH, ArrayInteger.fromOrNull(borderDash));
	}

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @return the line dash pattern used when stroking lines
	 */
	@Override
	public final List<Integer> getDash() {
		// checks if array
		if (isType(Property.DASH, ObjectType.ARRAY)) {
			ArrayInteger array = getArrayValue(Property.DASH);
			// exists then returns the value
			return ArrayListHelper.list(array);
		}
		// if here, the options is missing
		// the returns the defaults.
		return getDefaultValues().getDash();
	}

	/**
	 * Sets the line dash pattern offset.
	 * 
	 * @param borderDashOffset Offset for line dashes.
	 */
	public final void setDashOffset(double borderDashOffset) {
		setValueAndAddToParent(Property.DASH_OFFSET, borderDashOffset);
	}

	/**
	 * Returns the line dash pattern offset.
	 * 
	 * @return Offset for line dashes.
	 */
	@Override
	public final double getDashOffset() {
		return getValue(Property.DASH_OFFSET, getDefaultValues().getDashOffset());
	}

	/**
	 * If set, used as the width of the border line. If unset, the first lineWidth option is resolved and used.
	 * 
	 * @param borderWidth if set, used as the width of the border line. If unset, the first lineWidth option is resolved and used.
	 */
	public void setWidth(int borderWidth) {
		setValueAndAddToParent(Property.WIDTH, Checker.positiveOrZero(borderWidth));
	}

	/**
	 * If set, used as the width of the border line. If unset, the first lineWidth option is resolved and used.
	 * 
	 * @return if set, used as the width of the border line. If unset, the first lineWidth option is resolved and used.
	 */
	@Override
	public int getWidth() {
		return getValue(Property.WIDTH, getDefaultValues().getWidth());
	}

	/**
	 * Sets z-index of border layer.<br>
	 * Values less than or equals to 0 are drawn under datasets, greater than 0 on top.
	 * 
	 * @param z z-index of border layer.<br>
	 *            Values less than or equals to 0 are drawn under datasets, greater than 0 on top.
	 */
	public void setZ(int z) {
		setValueAndAddToParent(Property.Z, z);
	}

	/**
	 * Returns z-index of border layer.<br>
	 * Values less than or equals to 0 are drawn under datasets, greater than 0 on top.
	 * 
	 * @return z-index of border layer.<br>
	 *         Values less than or equals to 0 are drawn under datasets, greater than 0 on top.
	 */
	@Override
	public int getZ() {
		return getValue(Property.Z, getDefaultValues().getZ());
	}
}