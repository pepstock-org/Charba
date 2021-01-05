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
package org.pepstock.charba.client.options;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.defaults.IsDefaultGridLines;

/**
 * The grid line configuration defines options for the grid lines that run perpendicular to the axis.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class GridLines extends AbstractScaleLines<IsDefaultGridLines> implements IsDefaultGridLines {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		CIRCULAR("circular"),
		COLOR("color"),
		BORDER_COLOR("borderColor"),
		BORDER_WIDTH("borderWidth"),
		DISPLAY("display"),
		DRAW_BORDER("drawBorder"),
		DRAW_ON_CHART_AREA("drawOnChartArea"),
		DRAW_TICKS("drawTicks"),
		LINE_WIDTH("lineWidth"),
		OFFSET_GRID_LINES("offsetGridLines"),
		TICK_LENGTH("tickLength"),
		Z("z");

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
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param scale scale/axis of this object.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	GridLines(AbstractScale scale, Key childKey, IsDefaultGridLines defaultValues, NativeObject nativeObject) {
		super(scale, childKey, defaultValues, nativeObject);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.AbstractScaleLine#getDefaultBorderDashOffset()
	 */
	@Override
	double getDefaultBorderDashOffset() {
		return getDefaultValues().getBorderDashOffset();
	}

	/**
	 * If <code>false</code>, do not display grid lines for this axis.
	 * 
	 * @param display If <code>false</code>, do not display grid lines for this axis.
	 */
	public void setDisplay(boolean display) {
		setValueAndAddToParent(Property.DISPLAY, display);
	}

	/**
	 * If <code>false</code>, do not display grid lines for this axis.
	 * 
	 * @return If <code>false</code>, do not display grid lines for this axis.
	 */
	@Override
	public boolean isDisplay() {
		return getValue(Property.DISPLAY, getDefaultValues().isDisplay());
	}

	/**
	 * If <code>true</code>, gridlines are circular (on radar chart only).
	 * 
	 * @param circular If <code>true</code>, gridlines are circular (on radar chart only).
	 */
	public void setCircular(boolean circular) {
		setValueAndAddToParent(Property.CIRCULAR, circular);
	}

	/**
	 * If <code>true</code>, gridlines are circular (on radar chart only).
	 * 
	 * @return If <code>true</code>, gridlines are circular (on radar chart only).
	 */
	@Override
	public boolean isCircular() {
		return getValue(Property.CIRCULAR, getDefaultValues().isCircular());
	}

	/**
	 * If set, used as the color of the border line. If unset, the first color option is resolved and used.
	 * 
	 * @param color if set, used as the color of the border line. If unset, the first color option is resolved and used.
	 */
	public void setBorderColor(IsColor color) {
		setBorderColor(IsColor.checkAndGetValue(color));
	}

	/**
	 * If set, used as the color of the border line. If unset, the first color option is resolved and used.
	 * 
	 * @param color if set, used as the color of the border line. If unset, the first color option is resolved and used.
	 */
	public void setBorderColor(String color) {
		setValueAndAddToParent(Property.BORDER_COLOR, color);
	}

	/**
	 * If set, used as the color of the border line. If unset, the first color option is resolved and used.
	 * 
	 * @return if set, used as the color of the border line. If unset, the first color option is resolved and used.
	 */
	public String getBorderColorAsString() {
		// checks if exist
		if (!has(Property.BORDER_COLOR)) {
			// gets the color sets
			List<String> colors = getColorsAsString();
			// checks if list of colors is consistent
			if (!colors.isEmpty()) {
				return getValue(Property.BORDER_COLOR, colors.get(0));
			}
		}
		// if here, the colors are not consistent
		// then uses the default color as default for border color
		return getValue(Property.BORDER_COLOR, getDefaultValues().getColorAsString());
	}

	/**
	 * If set, used as the color of the border line. If unset, the first color option is resolved and used.
	 * 
	 * @return if set, used as the color of the border line. If unset, the first color option is resolved and used.
	 */
	public IsColor getBorderColor() {
		return ColorBuilder.parse(getBorderColorAsString());
	}

	/**
	 * The color of the grid lines. If specified as an array, the first color applies to the first grid line, the second to the second grid line and so on.
	 * 
	 * @param color The color of the grid lines. If specified as an array, the first color applies to the first grid line, the second to the second grid line and so on.
	 */
	public void setColor(IsColor... color) {
		setValueOrArrayAndAddToParent(Property.COLOR, color);
	}

	/**
	 * The color of the grid lines. If specified as an array, the first color applies to the first grid line, the second to the second grid line and so on.
	 * 
	 * @param color The color of the grid lines. If specified as an array, the first color applies to the first grid line, the second to the second grid line and so on.
	 */
	public void setColor(String... color) {
		setValueOrArrayAndAddToParent(Property.COLOR, color);
	}

	/**
	 * The color of the grid lines. If specified as an array, the first color applies to the first grid line, the second to the second grid line and so on.
	 * 
	 * @return the list of colors of the grid lines.
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
	 * The color of the grid lines. If specified as an array, the first color applies to the first grid line, the second to the second grid line and so on.
	 * 
	 * @return the list of colors of the grid lines.
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
	 * The color of the grid lines. If specified as an array, the first color applies to the first grid line, the second to the second grid line and so on.
	 * 
	 * @return the list of colors of the grid lines.
	 */
	public List<IsColor> getColor() {
		return ColorBuilder.parse(getColorsAsString());
	}

	/**
	 * If set, used as the width of the border line. If unset, the first lineWidth option is resolved and used.
	 * 
	 * @param borderWidth if set, used as the width of the border line. If unset, the first lineWidth option is resolved and used.
	 */
	public void setBorderWidth(int borderWidth) {
		setValueAndAddToParent(Property.BORDER_WIDTH, borderWidth);
	}

	/**
	 * If set, used as the width of the border line. If unset, the first lineWidth option is resolved and used.
	 * 
	 * @return if set, used as the width of the border line. If unset, the first lineWidth option is resolved and used.
	 */
	public int getBorderWidth() {
		return getValue(Property.BORDER_WIDTH, getLineWidth());
	}

	/**
	 * Sets the stroke widths of grid lines.
	 * 
	 * @param lineWidth stroke widths of grid lines.
	 */
	public void setLineWidth(int... lineWidth) {
		setValueOrArrayAndAddToParent(Property.LINE_WIDTH, lineWidth);
	}

	/**
	 * Returns the stroke width of grid lines. The first element if set as array.
	 * 
	 * @return stroke width of grid lines. The first element if set as array.
	 */
	@Override
	public int getLineWidth() {
		ArrayInteger array = getValueOrArray(Property.LINE_WIDTH, getDefaultValues().getLineWidth());
		return array.isEmpty() ? getDefaultValues().getLineWidth() : array.get(0);
	}

	/**
	 * Returns the stroke widths of grid lines.
	 * 
	 * @return stroke widths of grid lines.
	 */
	public List<Integer> getLinesWidth() {
		ArrayInteger array = getValueOrArray(Property.LINE_WIDTH, getDefaultValues().getLineWidth());
		return ArrayListHelper.list(array);
	}

	/***
	 * If <code>true</code>, draw border at the edge between the axis and the chart area.
	 * 
	 * @param drawBorder If <code>true</code>, draw border at the edge between the axis and the chart area.
	 */
	public void setDrawBorder(boolean drawBorder) {
		setValueAndAddToParent(Property.DRAW_BORDER, drawBorder);
	}

	/**
	 * If <code>true</code>, draw border at the edge between the axis and the chart area.
	 * 
	 * @return If <code>true</code>, draw border at the edge between the axis and the chart area.
	 */
	@Override
	public boolean isDrawBorder() {
		return getValue(Property.DRAW_BORDER, getDefaultValues().isDrawBorder());
	}

	/**
	 * If <code>true</code>, draw lines on the chart area inside the axis lines. This is useful when there are multiple axes and you need to control which grid lines are drawn.
	 * 
	 * @param drawOnChartArea If <code>true</code>, draw lines on the chart area inside the axis lines. This is useful when there are multiple axes and you need to control which
	 *            grid lines are drawn.
	 */
	public void setDrawOnChartArea(boolean drawOnChartArea) {
		setValueAndAddToParent(Property.DRAW_ON_CHART_AREA, drawOnChartArea);
	}

	/**
	 * If <code>true</code>, draw lines on the chart area inside the axis lines. This is useful when there are multiple axes and you need to control which grid lines are drawn.
	 * 
	 * @return If <code>true</code>, draw lines on the chart area inside the axis lines. This is useful when there are multiple axes and you need to control which grid lines are
	 *         drawn.
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
	 * Sets the length in pixels that the grid lines will draw into the axis area.
	 * 
	 * @param tickLength Length in pixels that the grid lines will draw into the axis area.
	 */
	public void setTickLength(int tickLength) {
		setValueAndAddToParent(Property.TICK_LENGTH, tickLength);
	}

	/**
	 * Returns the length in pixels that the grid lines will draw into the axis area.
	 * 
	 * @return Length in pixels that the grid lines will draw into the axis area.
	 */
	@Override
	public int getTickLength() {
		return getValue(Property.TICK_LENGTH, getDefaultValues().getTickLength());
	}

	/**
	 * If <code>true</code>, grid lines will be shifted to be between labels. This is set to <code>true</code> in the bar chart by default.
	 * 
	 * @param offsetGridLines if <code>true</code>, grid lines will be shifted to be between labels.
	 */
	public void setOffsetGridLines(boolean offsetGridLines) {
		setValueAndAddToParent(Property.OFFSET_GRID_LINES, offsetGridLines);
	}

	/**
	 * If <code>true</code>, grid lines will be shifted to be between labels. This is set to <code>true</code> in the bar chart by default.
	 * 
	 * @return if <code>true</code>, grid lines will be shifted to be between labels.
	 */
	@Override
	public boolean isOffsetGridLines() {
		return getValue(Property.OFFSET_GRID_LINES, getDefaultValues().isOffsetGridLines());
	}

	/**
	 * Sets z-index of gridline layer. Values less than or equals to 0 are drawn under datasets, greater than 0 on top.
	 * 
	 * @param z z-index of gridline layer. Values less than or equals to 0 are drawn under datasets, greater than 0 on top.
	 */
	public void setZ(int z) {
		setValueAndAddToParent(Property.Z, z);
	}

	/**
	 * Returns z-index of gridline layer. Values less than or equals to 0 are drawn under datasets, greater than 0 on top.
	 * 
	 * @return z-index of gridline layer. Values less than or equals to 0 are drawn under datasets, greater than 0 on top.
	 */
	@Override
	public int getZ() {
		return getValue(Property.Z, getDefaultValues().getZ());
	}

}