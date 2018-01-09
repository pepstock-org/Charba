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
package org.pepstock.charba.client.options.scales;

import java.util.List;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.JavaScriptObjectContainer;
import org.pepstock.charba.client.commons.JsIntegerArrayList;
import org.pepstock.charba.client.commons.JsStringArrayList;
import org.pepstock.charba.client.commons.Key;

/**
 * The grid line configuration defines options for the grid lines that run perpendicular to the axis.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class GridLines extends JavaScriptObjectContainer {

	private boolean isColorArray = false;

	private boolean isLineWidthArray = false;

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

	/**
	 * Empty constructor to reduce visibility
	 */
	GridLines() {
	}

	/**
	 * If false, do not display grid lines for this axis.
	 * 
	 * @param display If false, do not display grid lines for this axis.
	 */
	public void setDisplay(boolean display) {
		setValue(Property.display, display);
	}

	/**
	 * If false, do not display grid lines for this axis.
	 * 
	 * @return If false, do not display grid lines for this axis. Default is {@link org.pepstock.charba.client.defaults.scale.GridLines#isDisplay()}.
	 */
	public boolean isDisplay() {
		return getValue(Property.display, Defaults.getScale().getGrideLines().isDisplay());
	}

	/**
	 * The color of the grid lines. If specified as an array, the first color applies to the first grid line, the second to the
	 * second grid line and so on.
	 * 
	 * @param color The color of the grid lines. If specified as an array, the first color applies to the first grid line, the
	 *            second to the second grid line and so on.
	 */
	public void setColor(String... color) {
		setColor(ArrayListHelper.build(color));
	}

	/**
	 * The color of the grid lines. If specified as an array, the first color applies to the first grid line, the second to the
	 * second grid line and so on.
	 * 
	 * @param color The color of the grid lines. If specified as an array, the first color applies to the first grid line, the
	 *            second to the second grid line and so on.
	 */
	private void setColor(JsStringArrayList color) {
		isColorArray = checkAndSetStringValues(Property.color, color);
	}

	/**
	 * The color of the grid lines. If specified as an array, the first color applies to the first grid line, the second to the
	 * second grid line and so on.
	 * 
	 * @return the list of colors of the grid lines. If not set, default is {@link org.pepstock.charba.client.defaults.scale.GridLines#getColor()}.
	 */
	public List<String> getColor() {
		// loads stored data
		JsStringArrayList values = checkAndGetStringValues(Property.color, isColorArray);
		// if empty
		if (values.isEmpty()) {
			// return default color
			return Defaults.getScale().getGrideLines().getColor();
		}
		return values;
	}

	/**
	 * Sets the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines
	 * and gaps which describe the pattern.
	 * 
	 * @param borderDash the line dash pattern used when stroking lines
	 */
	public void setBorderDash(int... borderDash) {
		setBorderDash(ArrayListHelper.build(borderDash));
	}

	/**
	 * Sets the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines
	 * and gaps which describe the pattern.
	 * 
	 * @param borderDash the line dash pattern used when stroking lines
	 */
	private void setBorderDash(JsIntegerArrayList borderDash) {
		setIntegerArray(Property.borderDash, borderDash);
	}

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of
	 * lines and gaps which describe the pattern.
	 * 
	 * @return the line dash pattern used when stroking lines. Default is {@link org.pepstock.charba.client.defaults.scale.GridLines#getBorderDash()}.
	 */
	public List<Integer> getBorderDash() {
		List<Integer> values = getIntegerArray(Property.borderDash);
		// if empty
		if (values.isEmpty()) {
			// return default color
			return Defaults.getScale().getGrideLines().getBorderDash();
		}
		return values;
	}

	/**
	 * Sets the line dash pattern offset or "phase".
	 * 
	 * @param borderDashOffset Offset for line dashes.
	 */
	public void setBorderDashOffset(int borderDashOffset) {
		setValue(Property.borderDashOffset, borderDashOffset);
	}

	/**
	 * Returns the line dash pattern offset or "phase".
	 * 
	 * @return Offset for line dashes. If not set, default is {@link org.pepstock.charba.client.defaults.scale.GridLines#getBorderDashOffset()}.
	 */
	public int getBorderDashOffset() {
		return getValue(Property.borderDashOffset, Defaults.getScale().getGrideLines().getBorderDashOffset());
	}

	/**
	 * Sets the stroke widths of grid lines.
	 * 
	 * @param lineWidth stroke widths of grid lines.
	 */
	public void setLineWidth(int... lineWidth) {
		setLineWidth(ArrayListHelper.build(lineWidth));
	}

	/**
	 * Sets the stroke widths of grid lines.
	 * 
	 * @param lineWidth stroke widths of grid lines.
	 */
	private void setLineWidth(JsIntegerArrayList lineWidth) {
		isLineWidthArray = checkAndSetIntegerValues(Property.lineWidth, lineWidth);
	}

	/**
	 * Returns the stroke widths of grid lines.
	 * 
	 * @return lineWidth stroke widths of grid lines. If not set, default is {@link org.pepstock.charba.client.defaults.scale.GridLines#getLineWidth()}.
	 */
	public List<Integer> getLineWidth() {
		// loads stored data
		JsIntegerArrayList values = checkAndGetIntegerValues(Property.lineWidth, isLineWidthArray);
		// if empty
		if (values.isEmpty()) {
			// returns default
			return Defaults.getScale().getGrideLines().getLineWidth();
		}
		return values;
	}

	/***
	 * If true, draw border at the edge between the axis and the chart area.
	 * 
	 * @param drawBorder If true, draw border at the edge between the axis and the chart area.
	 */
	public void setDrawBorder(boolean drawBorder) {
		setValue(Property.drawBorder, drawBorder);
	}

	/**
	 * If true, draw border at the edge between the axis and the chart area.
	 * 
	 * @return If true, draw border at the edge between the axis and the chart area. If not set, default is {@link org.pepstock.charba.client.defaults.scale.GridLines#isDrawBorder()}.
	 */
	public boolean isDrawBorder() {
		return getValue(Property.drawBorder, Defaults.getScale().getGrideLines().isDrawBorder());
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
	}

	/**
	 * If true, draw lines on the chart area inside the axis lines. This is useful when there are multiple axes and you need to
	 * control which grid lines are drawn.
	 * 
	 * @return If true, draw lines on the chart area inside the axis lines. This is useful when there are multiple axes and you
	 *         need to control which grid lines are drawn. If not set, default is {@link org.pepstock.charba.client.defaults.scale.GridLines#isDrawOnChartArea()}.
	 */
	public boolean isDrawOnChartArea() {
		return getValue(Property.drawOnChartArea, Defaults.getScale().getGrideLines().isDrawOnChartArea());
	}

	/**
	 * If true, draw lines beside the ticks in the axis area beside the chart.
	 * 
	 * @param drawTicks If true, draw lines beside the ticks in the axis area beside the chart.
	 */
	public void setDrawTicks(boolean drawTicks) {
		setValue(Property.drawTicks, drawTicks);
	}

	/**
	 * If true, draw lines beside the ticks in the axis area beside the chart.
	 * 
	 * @return If true, draw lines beside the ticks in the axis area beside the chart. If not set, default is {@link org.pepstock.charba.client.defaults.scale.GridLines#isDrawTicks()}.
	 */
	public boolean isDrawTicks() {
		return getValue(Property.drawTicks, Defaults.getScale().getGrideLines().isDrawTicks());
	}

	/**
	 * Sets the length in pixels that the grid lines will draw into the axis area.
	 * 
	 * @param tickMarkLength Length in pixels that the grid lines will draw into the axis area.
	 */
	public void setTickMarkLength(int tickMarkLength) {
		setValue(Property.tickMarkLength, tickMarkLength);
	}

	/**
	 * Returns the length in pixels that the grid lines will draw into the axis area.
	 * 
	 * @return Length in pixels that the grid lines will draw into the axis area. If not set, default is {@link org.pepstock.charba.client.defaults.scale.GridLines#getTickMarkLength()}.
	 */
	public int getTickMarkLength() {
		return getValue(Property.tickMarkLength, Defaults.getScale().getGrideLines().getTickMarkLength());
	}

	/**
	 * Sets the stroke width of the grid line for the first index (index 0).
	 * 
	 * @param zeroLineWidth Stroke width of the grid line for the first index (index 0).
	 */
	public void setZeroLineWidth(int zeroLineWidth) {
		setValue(Property.zeroLineWidth, zeroLineWidth);
	}

	/**
	 * Returns the stroke width of the grid line for the first index (index 0).
	 * 
	 * @return Stroke width of the grid line for the first index (index 0). If not set, default is {@link org.pepstock.charba.client.defaults.scale.GridLines#getZeroLineWidth()}.
	 */
	public int getZeroLineWidth() {
		return getValue(Property.zeroLineWidth, Defaults.getScale().getGrideLines().getZeroLineWidth());
	}

	/**
	 * Sets the stroke color of the grid line for the first index (index 0).
	 * 
	 * @param zeroLineColor Stroke color of the grid line for the first index (index 0).
	 */
	public void setZeroLineColor(String zeroLineColor) {
		setValue(Property.zeroLineColor, zeroLineColor);
	}

	/**
	 * Returns the stroke color of the grid line for the first index (index 0).
	 * 
	 * @return Stroke color of the grid line for the first index (index 0). If not set, default is {@link org.pepstock.charba.client.defaults.scale.GridLines#getZeroLineColor()}.
	 */
	public String getZeroLineColor() {
		return getValue(Property.zeroLineColor, Defaults.getScale().getGrideLines().getZeroLineColor());
	}

	/**
	 * Sets the length and spacing of dashes of the grid line for the first index (index 0).
	 * 
	 * @param zeroLineBorderDash length and spacing of dashes of the grid line for the first index (index 0).
	 */
	public void setZeroLineBorderDash(int... zeroLineBorderDash) {
		setZeroLineBorderDash(ArrayListHelper.build(zeroLineBorderDash));
	}

	/**
	 * Sets the length and spacing of dashes of the grid line for the first index (index 0).
	 * 
	 * @param zeroLineBorderDash length and spacing of dashes of the grid line for the first index (index 0).
	 */
	private void setZeroLineBorderDash(JsIntegerArrayList zeroLineBorderDash) {
		setIntegerArray(Property.zeroLineBorderDash, zeroLineBorderDash);
	}

	/**
	 * Returns the length and spacing of dashes of the grid line for the first index (index 0).
	 * 
	 * @return the length and spacing of dashes of the grid line for the first index (index 0). Default is {@link org.pepstock.charba.client.defaults.scale.GridLines#getZeroLineBorderDash()}.
	 */
	public List<Integer> getZeroLineBorderDash() {
		List<Integer> values = getIntegerArray(Property.zeroLineBorderDash);
		// if empty
		if (values.isEmpty()) {
			// return default color
			return Defaults.getScale().getGrideLines().getZeroLineBorderDash();
		}
		return values;
	}

	/**
	 * Sets the offset for line dashes of the grid line for the first index (index 0).
	 * 
	 * @param zeroLineBorderDashOffset the offset for line dashes of the grid line for the first index (index 0).
	 */
	public void setZeroLineBorderDashOffset(int zeroLineBorderDashOffset) {
		setValue(Property.zeroLineBorderDashOffset, zeroLineBorderDashOffset);
	}

	/**
	 * Returns the offset for line dashes of the grid line for the first index (index 0).
	 * 
	 * @return the offset for line dashes of the grid line for the first index (index 0). if not set, default is {@link org.pepstock.charba.client.defaults.scale.GridLines#getZeroLineBorderDashOffset()}.
	 */
	public int getZeroLineBorderDashOffset() {
		return getValue(Property.zeroLineBorderDashOffset, Defaults.getScale().getGrideLines().getZeroLineBorderDashOffset());
	}

	/**
	 * If true, grid lines will be shifted to be between labels. This is set to true in the bar chart by default.
	 * 
	 * @param offsetGridLines If true, grid lines will be shifted to be between labels.
	 */
	public void setOffsetGridLines(boolean offsetGridLines) {
		setValue(Property.offsetGridLines, offsetGridLines);
	}

	/**
	 * If true, grid lines will be shifted to be between labels. This is set to true in the bar chart by default.
	 * 
	 * @return If true, grid lines will be shifted to be between labels. If not set, default is {@link org.pepstock.charba.client.defaults.scale.GridLines#isOffsetGridLines()}.
	 */
	public boolean isOffsetGridLines() {
		return getValue(Property.offsetGridLines, Defaults.getScale().getGrideLines().isOffsetGridLines());
	}

}