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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.defaults.IsDefaultGrid;

/**
 * The grid line configuration defines options for the grid that run perpendicular to the axis.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Grid extends AbstractModel<AbstractScale, IsDefaultGrid> implements IsDefaultGrid {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		CIRCULAR("circular"),
		COLOR("color"),
		DISPLAY("display"),
		DRAW_ON_CHART_AREA("drawOnChartArea"),
		DRAW_TICKS("drawTicks"),
		LINE_WIDTH("lineWidth"),
		OFFSET("offset"),
		TICK_LENGTH("tickLength"),
		TICK_BORDER_DASH("tickBorderDash"),
		TICK_BORDER_DASH_OFFSET("tickBorderDashOffset"),
		TICK_COLOR("tickColor"),
		TICK_WIDTH("tickWidth"),
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
	Grid(AbstractScale scale, Key childKey, IsDefaultGrid defaultValues, NativeObject nativeObject) {
		super(scale, childKey, defaultValues, nativeObject);
	}

	/**
	 * If <code>false</code>, do not display grid for this axis.
	 * 
	 * @param display If <code>false</code>, do not display grid for this axis.
	 */
	public void setDisplay(boolean display) {
		setValueAndAddToParent(Property.DISPLAY, display);
	}

	/**
	 * If <code>false</code>, do not display grid for this axis.
	 * 
	 * @return If <code>false</code>, do not display grid for this axis.
	 */
	@Override
	public boolean isDisplay() {
		return getValue(Property.DISPLAY, getDefaultValues().isDisplay());
	}

	/**
	 * If <code>true</code>, grid are circular (on radar chart only).
	 * 
	 * @param circular If <code>true</code>, grid are circular (on radar chart only).
	 */
	public void setCircular(boolean circular) {
		setValueAndAddToParent(Property.CIRCULAR, circular);
	}

	/**
	 * If <code>true</code>, grid are circular (on radar chart only).
	 * 
	 * @return If <code>true</code>, grid are circular (on radar chart only).
	 */
	@Override
	public boolean isCircular() {
		return getValue(Property.CIRCULAR, getDefaultValues().isCircular());
	}

	/**
	 * Sets the color of the grid.<br>
	 * If specified as an array, the first color applies to the first grid line, the second to the second grid line and so on.
	 * 
	 * @param color The color of the grid.<br>
	 *            If specified as an array, the first color applies to the first grid line, the second to the second grid line and so on.
	 */
	public void setColor(IsColor... color) {
		setValueOrArrayAndAddToParent(Property.COLOR, color);
	}

	/**
	 * Sets the color of the grid.<br>
	 * If specified as an array, the first color applies to the first grid line, the second to the second grid line and so on.
	 * 
	 * @param color The color of the grid.<br>
	 *            If specified as an array, the first color applies to the first grid line, the second to the second grid line and so on.
	 */
	public void setColor(String... color) {
		setValueOrArrayAndAddToParent(Property.COLOR, color);
	}

	/**
	 * Returns the color of the grid, if not specified as an array.
	 * 
	 * @return the list of colors of the grid.
	 */
	@Override
	public String getColorAsString() {
		// checks if the stored value is a string
		if (isType(Property.COLOR, ObjectType.STRING) || !has(Property.COLOR)) {
			// returns a string
			return getValue(Property.COLOR, getDefaultValues().getColorAsString());
		} else {
			// returns the default
			return getDefaultValues().getColorAsString();
		}
	}

	/**
	 * Returns the colors of the grid.<br>
	 * If specified as an array, the first color applies to the first grid line, the second to the second grid line and so on.
	 * 
	 * @return the list of colors of the grid.
	 */
	public List<String> getColorsAsString() {
		// gets object type
		ObjectType type = type(Property.COLOR);
		// checks if the stored value is a string
		if (ObjectType.STRING.equals(type) || !has(Property.COLOR)) {
			// adds the string value
			return Arrays.asList(getValue(Property.COLOR, getDefaultValues().getColorAsString()));
		} else if (ObjectType.ARRAY.equals(type)) {
			// if array
			// loads the array
			ArrayString array = getArrayValue(Property.COLOR);
			return ArrayListHelper.list(array);
		}
		// if here, the properties is not consistent
		// then returns an empty list
		return Collections.emptyList();
	}

	/**
	 * Returns the colors of the grid.<br>
	 * If specified as an array, the first color applies to the first grid line, the second to the second grid line and so on.
	 * 
	 * @return the list of colors of the grid.
	 */
	public List<IsColor> getColor() {
		return ColorBuilder.parse(getColorsAsString());
	}

	/**
	 * Sets the stroke widths of grid.
	 * 
	 * @param lineWidth stroke widths of grid.
	 */
	public void setLineWidth(int... lineWidth) {
		setValueOrArrayAndAddToParent(Property.LINE_WIDTH, lineWidth);
	}

	/**
	 * Returns the stroke width of grid.<br>
	 * The first element if set as array.
	 * 
	 * @return stroke width of grid.<br>
	 *         The first element if set as array.
	 */
	@Override
	public int getLineWidth() {
		ArrayInteger array = getValueOrArray(Property.LINE_WIDTH, getDefaultValues().getLineWidth());
		return array.isEmpty() ? getDefaultValues().getLineWidth() : array.get(0);
	}

	/**
	 * Returns the stroke widths of grid.
	 * 
	 * @return stroke widths of grid.
	 */
	public List<Integer> getLinesWidth() {
		ArrayInteger array = getValueOrArray(Property.LINE_WIDTH, getDefaultValues().getLineWidth());
		return ArrayListHelper.list(array);
	}

	/**
	 * If <code>true</code>, draw lines on the chart area inside the axis lines. This is useful when there are multiple axes and you need to control which grid are drawn.
	 * 
	 * @param drawOnChartArea If <code>true</code>, draw lines on the chart area inside the axis lines. This is useful when there are multiple axes and you need to control which
	 *            grid are drawn.
	 */
	public void setDrawOnChartArea(boolean drawOnChartArea) {
		setValueAndAddToParent(Property.DRAW_ON_CHART_AREA, drawOnChartArea);
	}

	/**
	 * If <code>true</code>, draw lines on the chart area inside the axis lines. This is useful when there are multiple axes and you need to control which grid are drawn.
	 * 
	 * @return If <code>true</code>, draw lines on the chart area inside the axis lines. This is useful when there are multiple axes and you need to control which grid are drawn.
	 */
	@Override
	public boolean isDrawOnChartArea() {
		return getValue(Property.DRAW_ON_CHART_AREA, getDefaultValues().isDrawOnChartArea());
	}

	/**
	 * If <code>true</code>, draw lines beside the ticks in the axis area beside the chart.
	 * 
	 * @param drawTicks If <code>true</code>, draw lines beside the ticks in the axis area beside the chart.
	 */
	public void setDrawTicks(boolean drawTicks) {
		setValueAndAddToParent(Property.DRAW_TICKS, drawTicks);
	}

	/**
	 * If <code>true</code>, draw lines beside the ticks in the axis area beside the chart.
	 * 
	 * @return If <code>true</code>, draw lines beside the ticks in the axis area beside the chart.
	 */
	@Override
	public boolean isDrawTicks() {
		return getValue(Property.DRAW_TICKS, getDefaultValues().isDrawTicks());
	}

	/**
	 * Sets the length in pixels that the grid will draw in the axis area.
	 * 
	 * @param tickLength Length in pixels that the grid will draw in the axis area.
	 */
	public void setTickLength(int tickLength) {
		setValueAndAddToParent(Property.TICK_LENGTH, Checker.positiveOrZero(tickLength));
	}

	/**
	 * Returns the length in pixels that the grid will draw in the axis area.
	 * 
	 * @return Length in pixels that the grid will draw in the axis area.
	 */
	@Override
	public int getTickLength() {
		return getValue(Property.TICK_LENGTH, getDefaultValues().getTickLength());
	}

	/**
	 * If <code>true</code>, grid will be shifted to be between labels.<br>
	 * This is set to <code>true</code> in the bar chart by default.
	 * 
	 * @param offset if <code>true</code>, grid will be shifted to be between labels.
	 */
	public void setOffset(boolean offset) {
		setValueAndAddToParent(Property.OFFSET, offset);
	}

	/**
	 * If <code>true</code>, grid will be shifted to be between labels.<br>
	 * This is set to <code>true</code> in the bar chart by default.
	 * 
	 * @return if <code>true</code>, grid will be shifted to be between labels.
	 */
	@Override
	public boolean isOffset() {
		return getValue(Property.OFFSET, getDefaultValues().isOffset());
	}

	/**
	 * Sets z-index of grid layer.<br>
	 * Values less than or equals to 0 are drawn under datasets, greater than 0 on top.
	 * 
	 * @param z z-index of grid layer.<br>
	 *            Values less than or equals to 0 are drawn under datasets, greater than 0 on top.
	 */
	public void setZ(int z) {
		setValueAndAddToParent(Property.Z, z);
	}

	/**
	 * Returns z-index of grid layer.<br>
	 * Values less than or equals to 0 are drawn under datasets, greater than 0 on top.
	 * 
	 * @return z-index of grid layer.<br>
	 *         Values less than or equals to 0 are drawn under datasets, greater than 0 on top.
	 */
	@Override
	public int getZ() {
		return getValue(Property.Z, getDefaultValues().getZ());
	}

	/**
	 * Sets the length and spacing of the tick mark line.
	 * 
	 * @param tickBorderDash the length and spacing of the tick mark line.
	 */
	public void setTickBorderDash(int tickBorderDash) {
		setArrayValueAndAddToParent(Property.TICK_BORDER_DASH, ArrayInteger.fromOrNull(tickBorderDash));
	}

	/**
	 * Returns the length and spacing of the tick mark line.
	 * 
	 * @return the length and spacing of the tick mark line.
	 */
	public List<Integer> getTickBorderDash() {
		// checks if the property is stored
		if (has(Property.TICK_BORDER_DASH)) {
			// then reads the array
			ArrayInteger array = getArrayValue(Property.TICK_BORDER_DASH);
			return ArrayListHelper.list(array);
		}
		// if here, the property is missing
		// then returns the default
		return getParent().getBorder().getDash();
	}

	/**
	 * Sets the offset for the line dash of the tick mark.
	 * 
	 * @param tickBorderDashOffset the offset for the line dash of the tick mark
	 */
	public final void setTickBorderDashOffset(double tickBorderDashOffset) {
		setValueAndAddToParent(Property.TICK_BORDER_DASH_OFFSET, tickBorderDashOffset);
	}

	/**
	 * Returns the offset for the line dash of the tick mark.
	 * 
	 * @return the offset for the line dash of the tick mark
	 */
	@Override
	public final double getTickBorderDashOffset() {
		return getValue(Property.TICK_BORDER_DASH_OFFSET, getParent().getBorder().getDashOffset());
	}

	/**
	 * Sets the color of the tick line.
	 * 
	 * @param color the color of the tick line.
	 */
	public void setTickColor(IsColor... color) {
		setValueOrArrayAndAddToParent(Property.TICK_COLOR, color);
	}

	/**
	 * Sets the color of the tick line.
	 * 
	 * @param color the color of the tick line.
	 */
	public void setTickColor(String... color) {
		setValueOrArrayAndAddToParent(Property.TICK_COLOR, color);
	}

	/**
	 * Returns the color of the tick line.
	 * 
	 * @return the color of the tick line.
	 */
	@Override
	public String getTickColorAsString() {
		// checks if the stored value is a string
		if (isType(Property.TICK_COLOR, ObjectType.STRING) || !has(Property.TICK_COLOR)) {
			// returns a string
			return getValue(Property.TICK_COLOR, getDefaultValues().getTickColorAsString());
		} else {
			// returns the default
			return getDefaultValues().getTickColorAsString();
		}
	}

	/**
	 * The color of the grid.<br>
	 * If specified as an array, the first color applies to the first grid line, the second to the second grid line and so on.
	 * 
	 * @return the list of colors of the grid.
	 */
	public List<String> getTickColorsAsString() {
		// gets object type
		ObjectType type = type(Property.TICK_COLOR);
		// checks if the stored value is a string
		if (ObjectType.STRING.equals(type) || !has(Property.TICK_COLOR)) {
			// adds the string value
			return Arrays.asList(getValue(Property.TICK_COLOR, getDefaultValues().getTickColorAsString()));
		} else if (ObjectType.ARRAY.equals(type)) {
			// if array
			// loads the array
			ArrayString array = getArrayValue(Property.TICK_COLOR);
			return ArrayListHelper.list(array);
		}
		// if here, the properties is not consistent
		// then returns an empty list
		return Collections.emptyList();
	}

	/**
	 * The color of the grid.<br>
	 * If specified as an array, the first color applies to the first grid line, the second to the second grid line and so on.
	 * 
	 * @return the list of colors of the grid.
	 */
	public List<IsColor> getTickColor() {
		return ColorBuilder.parse(getTickColorsAsString());
	}

	/**
	 * Sets the width of the tick marks in pixels.
	 * 
	 * @param tickWidth the width of the tick mark in pixels
	 */
	public void setTickWidth(int... tickWidth) {
		setValueOrArrayAndAddToParent(Property.TICK_WIDTH, tickWidth);
	}

	/**
	 * Returns the width of the tick mark in pixels.<br>
	 * The first element if set as array.
	 * 
	 * @return stroke width of grid.<br>
	 *         The first element if set as array.
	 */
	@Override
	public int getTickWidth() {
		ArrayInteger array = getValueOrArray(Property.TICK_WIDTH, getDefaultValues().getTickWidth());
		return array.isEmpty() ? getDefaultValues().getTickWidth() : array.get(0);
	}

	/**
	 * Returns the width of the tick marks in pixels.
	 * 
	 * @return stroke widths of grid.
	 */
	public List<Integer> getTicksWidth() {
		ArrayInteger array = getValueOrArray(Property.TICK_WIDTH, getDefaultValues().getTickWidth());
		return ArrayListHelper.list(array);
	}

}