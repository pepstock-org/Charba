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
import java.util.LinkedList;
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
public final class GridLines extends AbstractScaleLine<IsDefaultGridLines> implements IsDefaultGridLines {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		DISPLAY("display"),
		CIRCULAR("circular"),
		COLOR("color"),
		LINE_WIDTH("lineWidth"),
		DRAW_BORDER("drawBorder"),
		DRAW_ON_CHART_AREA("drawOnChartArea"),
		DRAW_TICKS("drawTicks"),
		TICK_MARK_LENGTH("tickMarkLength"),
		ZERO_LINE_WIDTH("zeroLineWidth"),
		ZERO_LINE_COLOR("zeroLineColor"),
		ZERO_LINE_BORDER_DASH("zeroLineBorderDash"),
		ZERO_LINE_BORDER_DASH_OFFSET("zeroLineBorderDashOffset"),
		OFFSET_GRID_LINES("offsetGridLines");

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
	 * Creates the object with the parent, the key of this element, default values and native object to map java script
	 * properties.
	 * 
	 * @param scale scale/axis of this object.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	GridLines(Scale scale, Key childKey, IsDefaultGridLines defaultValues, NativeObject nativeObject) {
		super(scale, childKey, defaultValues, nativeObject);
	}

	/**
	 * If <code>false</code>, do not display grid lines for this axis.
	 * 
	 * @param display If <code>false</code>, do not display grid lines for this axis.
	 */
	public void setDisplay(boolean display) {
		setValue(Property.DISPLAY, display);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If <code>false</code>, do not display grid lines for this axis.
	 * 
	 * @return If <code>false</code>, do not display grid lines for this axis.
	 */
	public boolean isDisplay() {
		return getValue(Property.DISPLAY, getDefaultValues().isDisplay());
	}

	/**
	 * If <code>true</code>, gridlines are circular (on radar chart only).
	 * 
	 * @param circular If <code>true</code>, gridlines are circular (on radar chart only).
	 */
	public void setCircular(boolean circular) {
		setValue(Property.CIRCULAR, circular);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If <code>true</code>, gridlines are circular (on radar chart only).
	 * 
	 * @return If <code>true</code>, gridlines are circular (on radar chart only).
	 */
	public boolean isCircular() {
		return getValue(Property.CIRCULAR, getDefaultValues().isCircular());
	}

	/**
	 * The color of the grid lines. If specified as an array, the first color applies to the first grid line, the second to the
	 * second grid line and so on.
	 * 
	 * @param color The color of the grid lines. If specified as an array, the first color applies to the first grid line, the
	 *            second to the second grid line and so on.
	 */
	public void setColor(IsColor... color) {
		setValueOrArray(Property.COLOR, color);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * The color of the grid lines. If specified as an array, the first color applies to the first grid line, the second to the
	 * second grid line and so on.
	 * 
	 * @param color The color of the grid lines. If specified as an array, the first color applies to the first grid line, the
	 *            second to the second grid line and so on.
	 */
	public void setColor(String... color) {
		setValueOrArray(Property.COLOR, color);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * The color of the grid lines. If specified as an array, the first color applies to the first grid line, the second to the
	 * second grid line and so on.
	 * 
	 * @return the list of colors of the grid lines.
	 */
	public String getColorAsString() {
		// checks if the stored value is a string
		if (ObjectType.STRING.equals(type(Property.COLOR)) || !has(Property.COLOR)) {
			// returns a string
			return getValue(Property.COLOR, getDefaultValues().getColorAsString());
		} else {
			// returns the default
			return getDefaultValues().getColorAsString();
		}
	}

	/**
	 * The color of the grid lines. If specified as an array, the first color applies to the first grid line, the second to the
	 * second grid line and so on.
	 * 
	 * @return the list of colors of the grid lines.
	 */
	public List<String> getColorsAsString() {
		// checks if the stored value is a string
		if (ObjectType.STRING.equals(type(Property.COLOR)) || !has(Property.COLOR)) {
			// adds the string value
			return Arrays.asList(getValue(Property.COLOR, getDefaultValues().getColorAsString()));
		} else if (ObjectType.ARRAY.equals(type(Property.COLOR))) {
			// if array
			// loads the array
			ArrayString array = getArrayValue(Property.COLOR);
			return ArrayListHelper.list(array);
		} else {
			return new LinkedList<>();
		}
	}

	/**
	 * The color of the grid lines. If specified as an array, the first color applies to the first grid line, the second to the
	 * second grid line and so on.
	 * 
	 * @return the list of colors of the grid lines.
	 */
	public List<IsColor> getColor() {
		return ColorBuilder.parse(getColorsAsString());
	}

	/**
	 * Sets the stroke widths of grid lines.
	 * 
	 * @param lineWidth stroke widths of grid lines.
	 */
	public void setLineWidth(int... lineWidth) {
		setValueOrArray(Property.LINE_WIDTH, lineWidth);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the stroke width of grid lines. The first element if set as array.
	 * 
	 * @return lineWidth stroke width of grid lines. The first element if set as array.
	 */
	public int getLineWidth() {
		ArrayInteger array = getValueOrArray(Property.LINE_WIDTH, getDefaultValues().getLineWidth());
		return array.isEmpty() ? getDefaultValues().getLineWidth() : array.get(0);
	}

	/**
	 * Returns the stroke widths of grid lines.
	 * 
	 * @return lineWidth stroke widths of grid lines.
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
		setValue(Property.DRAW_BORDER, drawBorder);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If <code>true</code>, draw border at the edge between the axis and the chart area.
	 * 
	 * @return If <code>true</code>, draw border at the edge between the axis and the chart area.
	 */
	public boolean isDrawBorder() {
		return getValue(Property.DRAW_BORDER, getDefaultValues().isDrawBorder());
	}

	/**
	 * If <code>true</code>, draw lines on the chart area inside the axis lines. This is useful when there are multiple axes and
	 * you need to control which grid lines are drawn.
	 * 
	 * @param drawOnChartArea If <code>true</code>, draw lines on the chart area inside the axis lines. This is useful when
	 *            there are multiple axes and you need to control which grid lines are drawn.
	 */
	public void setDrawOnChartArea(boolean drawOnChartArea) {
		setValue(Property.DRAW_ON_CHART_AREA, drawOnChartArea);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If <code>true</code>, draw lines on the chart area inside the axis lines. This is useful when there are multiple axes and
	 * you need to control which grid lines are drawn.
	 * 
	 * @return If <code>true</code>, draw lines on the chart area inside the axis lines. This is useful when there are multiple
	 *         axes and you need to control which grid lines are drawn.
	 */
	public boolean isDrawOnChartArea() {
		return getValue(Property.DRAW_ON_CHART_AREA, getDefaultValues().isDrawOnChartArea());
	}

	/**
	 * If <code>true</code>, draw lines beside the ticks in the axis area beside the chart.
	 * 
	 * @param drawTicks If <code>true</code>, draw lines beside the ticks in the axis area beside the chart.
	 */
	public void setDrawTicks(boolean drawTicks) {
		setValue(Property.DRAW_TICKS, drawTicks);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If <code>true</code>, draw lines beside the ticks in the axis area beside the chart.
	 * 
	 * @return If <code>true</code>, draw lines beside the ticks in the axis area beside the chart.
	 */
	public boolean isDrawTicks() {
		return getValue(Property.DRAW_TICKS, getDefaultValues().isDrawTicks());
	}

	/**
	 * Sets the length in pixels that the grid lines will draw into the axis area.
	 * 
	 * @param tickMarkLength Length in pixels that the grid lines will draw into the axis area.
	 */
	public void setTickMarkLength(int tickMarkLength) {
		setValue(Property.TICK_MARK_LENGTH, tickMarkLength);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the length in pixels that the grid lines will draw into the axis area.
	 * 
	 * @return Length in pixels that the grid lines will draw into the axis area.
	 */
	public int getTickMarkLength() {
		return getValue(Property.TICK_MARK_LENGTH, getDefaultValues().getTickMarkLength());
	}

	/**
	 * Sets the stroke width of the grid line for the first index (index 0).
	 * 
	 * @param zeroLineWidth Stroke width of the grid line for the first index (index 0).
	 */
	public void setZeroLineWidth(int zeroLineWidth) {
		setValue(Property.ZERO_LINE_WIDTH, zeroLineWidth);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the stroke width of the grid line for the first index (index 0).
	 * 
	 * @return Stroke width of the grid line for the first index (index 0).
	 */
	public int getZeroLineWidth() {
		return getValue(Property.ZERO_LINE_WIDTH, getDefaultValues().getZeroLineWidth());
	}

	/**
	 * Sets the stroke color of the grid line for the first index (index 0).
	 * 
	 * @param zeroLineColor Stroke color of the grid line for the first index (index 0).
	 */
	public void setZeroLineColor(IsColor zeroLineColor) {
		setZeroLineColor(zeroLineColor.toRGBA());
	}

	/**
	 * Sets the stroke color of the grid line for the first index (index 0).
	 * 
	 * @param zeroLineColor Stroke color of the grid line for the first index (index 0).
	 */
	public void setZeroLineColor(String zeroLineColor) {
		setValue(Property.ZERO_LINE_COLOR, zeroLineColor);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the stroke color of the grid line for the first index (index 0).
	 * 
	 * @return Stroke color of the grid line for the first index (index 0).
	 */
	public String getZeroLineColorAsString() {
		return getValue(Property.ZERO_LINE_COLOR, getDefaultValues().getZeroLineColorAsString());
	}

	/**
	 * Returns the stroke color of the grid line for the first index (index 0).
	 * 
	 * @return Stroke color of the grid line for the first index (index 0).
	 */
	public IsColor getZeroLineColor() {
		return ColorBuilder.parse(getZeroLineColorAsString());
	}

	/**
	 * Sets the length and spacing of dashes of the grid line for the first index (index 0).
	 * 
	 * @param zeroLineBorderDash length and spacing of dashes of the grid line for the first index (index 0).
	 */
	public void setZeroLineBorderDash(int... zeroLineBorderDash) {
		setArrayValue(Property.ZERO_LINE_BORDER_DASH, ArrayInteger.fromOrNull(zeroLineBorderDash));
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the length and spacing of dashes of the grid line for the first index (index 0).
	 * 
	 * @return the length and spacing of dashes of the grid line for the first index (index 0).
	 */
	public List<Integer> getZeroLineBorderDash() {
		ArrayInteger array = getArrayValue(Property.ZERO_LINE_BORDER_DASH);
		return ArrayListHelper.list(array);
	}

	/**
	 * Sets the offset for line dashes of the grid line for the first index (index 0).
	 * 
	 * @param zeroLineBorderDashOffset the offset for line dashes of the grid line for the first index (index 0).
	 */
	public void setZeroLineBorderDashOffset(int zeroLineBorderDashOffset) {
		setValue(Property.ZERO_LINE_BORDER_DASH_OFFSET, zeroLineBorderDashOffset);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the offset for line dashes of the grid line for the first index (index 0).
	 * 
	 * @return the offset for line dashes of the grid line for the first index (index 0).
	 */
	public int getZeroLineBorderDashOffset() {
		return getValue(Property.ZERO_LINE_BORDER_DASH_OFFSET, getDefaultValues().getZeroLineBorderDashOffset());
	}

	/**
	 * If <code>true</code>, grid lines will be shifted to be between labels. This is set to <code>true</code> in the bar chart
	 * by default.
	 * 
	 * @param offsetGridLines if <code>true</code>, grid lines will be shifted to be between labels.
	 */
	public void setOffsetGridLines(boolean offsetGridLines) {
		setValue(Property.OFFSET_GRID_LINES, offsetGridLines);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If <code>true</code>, grid lines will be shifted to be between labels. This is set to <code>true</code> in the bar chart
	 * by default.
	 * 
	 * @return if <code>true</code>, grid lines will be shifted to be between labels.
	 */
	public boolean isOffsetGridLines() {
		return getValue(Property.OFFSET_GRID_LINES, getDefaultValues().isOffsetGridLines());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.AbstractScaleLine#getDefaultBorderDashOffset()
	 */
	@Override
	int getDefaultBorderDashOffset() {
		return getDefaultValues().getBorderDashOffset();
	}

}