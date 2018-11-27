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
package org.pepstock.charba.client.jsinterop.options;

import java.util.List;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.jsinterop.commons.ArrayInteger;
import org.pepstock.charba.client.jsinterop.commons.ArrayIntegerList;
import org.pepstock.charba.client.jsinterop.commons.ArrayListHelper;
import org.pepstock.charba.client.jsinterop.commons.ArrayString;
import org.pepstock.charba.client.jsinterop.commons.ArrayStringList;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.commons.ObjectType;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultGridLines;

/**
 * The grid line configuration defines options for the grid lines that run perpendicular to the axis.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class GridLines extends AbstractModel<Scale, IsDefaultGridLines> implements IsDefaultGridLines {

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		display,
		color,
		borderDash,
		borderDashOffset,
		lineWidth,
		drawBorder,
		drawOnChartArea,
		drawTicks,
		tickMarkLength,
		zeroLineWidth,
		zeroLineColor,
		zeroLineBorderDash,
		zeroLineBorderDashOffset,
		offsetGridLines
	}
	
	GridLines(Scale scale, Key childKey, IsDefaultGridLines defaultValues, NativeObject nativeObject) {
		super(scale, childKey, defaultValues, nativeObject);
	}

	/**
	 * If false, do not display grid lines for this axis.
	 * 
	 * @param display If false, do not display grid lines for this axis.
	 */
	public void setDisplay(boolean display) {
		setValue(Property.display, display);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If false, do not display grid lines for this axis.
	 * 
	 * @return If false, do not display grid lines for this axis. Default is true.
	 */
	public boolean isDisplay() {
		return getValue(Property.display, getDefaultValues().isDisplay());
	}

	/**
	 * The color of the grid lines. If specified as an array, the first color applies to the first grid line, the second to the
	 * second grid line and so on.
	 * 
	 * @param color The color of the grid lines. If specified as an array, the first color applies to the first grid line, the
	 *            second to the second grid line and so on.
	 */
	public void setColor(IsColor... color) {
		setColor(ArrayString.of(color));
	}

	/**
	 * The color of the grid lines. If specified as an array, the first color applies to the first grid line, the second to the
	 * second grid line and so on.
	 * 
	 * @param color The color of the grid lines. If specified as an array, the first color applies to the first grid line, the
	 *            second to the second grid line and so on.
	 */
	public void setColor(String... color) {
		setColor(ArrayString.of(color));
	}

	/**
	 * The color of the grid lines. If specified as an array, the first color applies to the first grid line, the second to the
	 * second grid line and so on.
	 * 
	 * @param color The color of the grid lines. If specified as an array, the first color applies to the first grid line, the
	 *            second to the second grid line and so on.
	 */
	private void setColor(ArrayString color) {
		if (color.length() == 1) {
			setValue(Property.color, color.get(0));
		} else {
			setArrayValue(Property.color, color);
		}
		// checks if all parents are attached
		checkAndAddToParent();
	}
	
	/**
	 * The color of the grid lines. If specified as an array, the first color applies to the first grid line, the second to the
	 * second grid line and so on.
	 * 
	 * @return the list of colors of the grid lines. If not set, default is only 1 value 'rgba(0, 0, 0, 0.1)'
	 */
	public String getColorAsString() {
		if (ObjectType.String.equals(type(Property.color)) || !has(Property.color)) {
			return getValue(Property.color, getDefaultValues().getColorAsString());
		} else{
			return getDefaultValues().getColorAsString();
		}
	}

	/**
	 * The color of the grid lines. If specified as an array, the first color applies to the first grid line, the second to the
	 * second grid line and so on.
	 * 
	 * @return the list of colors of the grid lines. If not set, default is only 1 value 'rgba(0, 0, 0, 0.1)'
	 */
	public List<String> getColorsAsString() {
		ArrayStringList result = null;
		// loads stored data
		if (ObjectType.String.equals(type(Property.color)) || !has(Property.color)) {
			result = new ArrayStringList();
			result.add(getValue(Property.color, getDefaultValues().getColorAsString()));
		} else if (ObjectType.Array.equals(type(Property.color))) {
			ArrayString array = getArrayValue(Property.color);
			result = ArrayListHelper.list(array);
		}
		return result;
	}

	/**
	 * The color of the grid lines. If specified as an array, the first color applies to the first grid line, the second to the
	 * second grid line and so on.
	 * 
	 * @return the list of colors of the grid lines. If not set, default is only 1 value 'rgba(0, 0, 0, 0.1)'
	 */
	public List<IsColor> getColor() {
		return ColorBuilder.parse(getColorsAsString());
	}

	/**
	 * Sets the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines
	 * and gaps which describe the pattern.
	 * 
	 * @param borderDash the line dash pattern used when stroking lines
	 */
	public void setBorderDash(int... borderDash) {
		setBorderDash(ArrayInteger.of(borderDash));
	}

	/**
	 * Sets the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines
	 * and gaps which describe the pattern.
	 * 
	 * @param borderDash the line dash pattern used when stroking lines
	 */
	private void setBorderDash(ArrayInteger borderDash) {
		setArrayValue(Property.borderDash, borderDash);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of
	 * lines and gaps which describe the pattern.
	 * 
	 * @return the line dash pattern used when stroking lines
	 */
	public List<Integer> getBorderDash() {
		ArrayInteger array = getArrayValue(Property.borderDash);
		return ArrayListHelper.list(array);
	}

	/**
	 * Sets the line dash pattern offset or "phase".
	 * 
	 * @param borderDashOffset Offset for line dashes.
	 */
	public void setBorderDashOffset(int borderDashOffset) {
		setValue(Property.borderDashOffset, borderDashOffset);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the line dash pattern offset or "phase".
	 * 
	 * @return Offset for line dashes. If not set, default is 0
	 */
	public int getBorderDashOffset() {
		return getValue(Property.borderDashOffset, getDefaultValues().getBorderDashOffset());
	}

	/**
	 * Sets the stroke widths of grid lines.
	 * 
	 * @param lineWidth stroke widths of grid lines.
	 */
	public void setLineWidth(int... lineWidth) {
		setLineWidth(ArrayInteger.of(lineWidth));
	}

	/**
	 * Sets the stroke widths of grid lines.
	 * 
	 * @param lineWidth stroke widths of grid lines.
	 */
	private void setLineWidth(ArrayInteger lineWidth) {
		if (lineWidth.length() == 1) {
			setValue(Property.lineWidth, lineWidth.get(0));
		} else {
			setArrayValue(Property.lineWidth, lineWidth);
		}
		// checks if all parents are attached
		checkAndAddToParent();
	}
	/**
	 * Returns the stroke widths of grid lines.
	 * 
	 * @return lineWidth stroke widths of grid lines. If not set, default is 1.
	 */
	public int getLineWidth() {
		if (ObjectType.Number.equals(type(Property.lineWidth)) || !has(Property.lineWidth)) {
			return getValue(Property.lineWidth, getDefaultValues().getLineWidth());
		} else {
			return getDefaultValues().getLineWidth();
		}
	}


	/**
	 * Returns the stroke widths of grid lines.
	 * 
	 * @return lineWidth stroke widths of grid lines. If not set, default is 1.
	 */
	public List<Integer> getLinesWidth() {
		ArrayIntegerList result = null;
		// loads stored data
		if (ObjectType.Number.equals(type(Property.lineWidth)) || !has(Property.lineWidth)) {
			result = new ArrayIntegerList();
			result.add(getValue(Property.lineWidth, getDefaultValues().getLineWidth()));
		} else if (ObjectType.Array.equals(type(Property.lineWidth))) {
			ArrayInteger array = getArrayValue(Property.lineWidth);
			result = ArrayListHelper.list(array);
		}
		return result;
	}

	/***
	 * If true, draw border at the edge between the axis and the chart area.
	 * 
	 * @param drawBorder If true, draw border at the edge between the axis and the chart area.
	 */
	public void setDrawBorder(boolean drawBorder) {
		setValue(Property.drawBorder, drawBorder);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If true, draw border at the edge between the axis and the chart area.
	 * 
	 * @return If true, draw border at the edge between the axis and the chart area. If not set, default is true
	 */
	public boolean isDrawBorder() {
		return getValue(Property.drawBorder, getDefaultValues().isDrawBorder());
	}

	/**
	 * If true, draw lines on the chart area inside the axis lines. This is useful when there are multiple axes and you need to
	 * control which grid lines are drawn.
	 * 
	 * @param drawOnChartArea If true, draw lines on the chart area inside the axis lines. This is useful when there are
	 *            multiple axes and you need to control which grid lines are drawn.
	 */
	public void setDrawOnChartArea(boolean drawOnChartArea) {
		setValue(Property.drawOnChartArea, drawOnChartArea);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If true, draw lines on the chart area inside the axis lines. This is useful when there are multiple axes and you need to
	 * control which grid lines are drawn.
	 * 
	 * @return If true, draw lines on the chart area inside the axis lines. This is useful when there are multiple axes and you
	 *         need to control which grid lines are drawn. If not set, default is true
	 */
	public boolean isDrawOnChartArea() {
		return getValue(Property.drawOnChartArea, getDefaultValues().isDrawOnChartArea());
	}

	/**
	 * If true, draw lines beside the ticks in the axis area beside the chart.
	 * 
	 * @param drawTicks If true, draw lines beside the ticks in the axis area beside the chart.
	 */
	public void setDrawTicks(boolean drawTicks) {
		setValue(Property.drawTicks, drawTicks);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If true, draw lines beside the ticks in the axis area beside the chart.
	 * 
	 * @return If true, draw lines beside the ticks in the axis area beside the chart. If not set, default is true
	 */
	public boolean isDrawTicks() {
		return getValue(Property.drawTicks, getDefaultValues().isDrawTicks());
	}

	/**
	 * Sets the length in pixels that the grid lines will draw into the axis area.
	 * 
	 * @param tickMarkLength Length in pixels that the grid lines will draw into the axis area.
	 */
	public void setTickMarkLength(int tickMarkLength) {
		setValue(Property.tickMarkLength, tickMarkLength);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the length in pixels that the grid lines will draw into the axis area.
	 * 
	 * @return Length in pixels that the grid lines will draw into the axis area. If not set, default is 10
	 */
	public int getTickMarkLength() {
		return getValue(Property.tickMarkLength, getDefaultValues().getTickMarkLength());
	}

	/**
	 * Sets the stroke width of the grid line for the first index (index 0).
	 * 
	 * @param zeroLineWidth Stroke width of the grid line for the first index (index 0).
	 */
	public void setZeroLineWidth(int zeroLineWidth) {
		setValue(Property.zeroLineWidth, zeroLineWidth);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the stroke width of the grid line for the first index (index 0).
	 * 
	 * @return Stroke width of the grid line for the first index (index 0). If not set, default is 1
	 */
	public int getZeroLineWidth() {
		return getValue(Property.zeroLineWidth, getDefaultValues().getZeroLineWidth());
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
		setValue(Property.zeroLineColor, zeroLineColor);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the stroke color of the grid line for the first index (index 0).
	 * 
	 * @return Stroke color of the grid line for the first index (index 0). If not set, default is 'rgba(0, 0, 0, 0.25)'
	 */
	public String getZeroLineColorAsString() {
		return getValue(Property.zeroLineColor, getDefaultValues().getZeroLineColorAsString());
	}

	/**
	 * Returns the stroke color of the grid line for the first index (index 0).
	 * 
	 * @return Stroke color of the grid line for the first index (index 0). If not set, default is 'rgba(0, 0, 0, 0.25)'
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
		setZeroLineBorderDash(ArrayInteger.of(zeroLineBorderDash));
	}

	/**
	 * Sets the length and spacing of dashes of the grid line for the first index (index 0).
	 * 
	 * @param zeroLineBorderDash length and spacing of dashes of the grid line for the first index (index 0).
	 */
	private void setZeroLineBorderDash(ArrayInteger zeroLineBorderDash) {
		setArrayValue(Property.zeroLineBorderDash, zeroLineBorderDash);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the length and spacing of dashes of the grid line for the first index (index 0).
	 * 
	 * @return the length and spacing of dashes of the grid line for the first index (index 0).
	 */
	public List<Integer> getZeroLineBorderDash() {
		ArrayInteger array = getArrayValue(Property.zeroLineBorderDash);
		return ArrayListHelper.list(array);
	}

	/**
	 * Sets the offset for line dashes of the grid line for the first index (index 0).
	 * 
	 * @param zeroLineBorderDashOffset the offset for line dashes of the grid line for the first index (index 0).
	 */
	public void setZeroLineBorderDashOffset(int zeroLineBorderDashOffset) {
		setValue(Property.zeroLineBorderDashOffset, zeroLineBorderDashOffset);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the offset for line dashes of the grid line for the first index (index 0).
	 * 
	 * @return the offset for line dashes of the grid line for the first index (index 0). if not set, default is 0
	 */
	public int getZeroLineBorderDashOffset() {
		return getValue(Property.zeroLineBorderDashOffset, getDefaultValues().getZeroLineBorderDashOffset());
	}

	/**
	 * If true, grid lines will be shifted to be between labels. This is set to true in the bar chart by default.
	 * 
	 * @param offsetGridLines If true, grid lines will be shifted to be between labels.
	 */
	public void setOffsetGridLines(boolean offsetGridLines) {
		setValue(Property.offsetGridLines, offsetGridLines);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If true, grid lines will be shifted to be between labels. This is set to true in the bar chart by default.
	 * 
	 * @return If true, grid lines will be shifted to be between labels. If not set, default is false.
	 */
	public boolean isOffsetGridLines() {
		return getValue(Property.offsetGridLines, getDefaultValues().isOffsetGridLines());
	}
	
}