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
import org.pepstock.charba.client.jsinterop.commons.Array;
import org.pepstock.charba.client.jsinterop.commons.ArrayInteger;
import org.pepstock.charba.client.jsinterop.commons.ArrayIntegerList;
import org.pepstock.charba.client.jsinterop.commons.ArrayListHelper;
import org.pepstock.charba.client.jsinterop.commons.ArrayString;
import org.pepstock.charba.client.jsinterop.commons.ArrayStringList;
import org.pepstock.charba.client.jsinterop.commons.AssignHelper;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultGridLines;

/**
 * The grid line configuration defines options for the grid lines that run perpendicular to the axis.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class GridLines extends BaseModel<Scale, IsDefaultGridLines, NativeGridLines> {

	GridLines(Scale scale, IsDefaultGridLines defaultValues, NativeGridLines delegated) {
		super(scale, defaultValues, delegated == null ? new NativeGridLines(): delegated);
	}

	/**
	 * If false, do not display grid lines for this axis.
	 * 
	 * @param display If false, do not display grid lines for this axis.
	 */
	public void setDisplay(boolean display) {
		getDelegated().setDisplay(display);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If false, do not display grid lines for this axis.
	 * 
	 * @return If false, do not display grid lines for this axis. Default is true.
	 */
	public boolean isDisplay() {
		return AssignHelper.check(getDelegated().isDisplay(), getDefaultValues().isDisplay());
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
			getDelegated().setColor(color.get(0));
		} else {
			getDelegated().setColor(color);
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
	public List<String> getColorAsString() {
		ArrayStringList result = null;
		// loads stored data
		Object values = getDelegated().getColor();
		if (Array.isArray(values)) {
			ArrayString array = (ArrayString)values;
			if (array.length() == 0) {
				result = new ArrayStringList();
				result.add(getDefaultValues().getColor());
			} else {
				result = ArrayListHelper.build(array);
			}
		} else {
			result = new ArrayStringList();
			result.add(AssignHelper.check((String)values, getDefaultValues().getColor()));
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
		return ColorBuilder.parse(getColorAsString());
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
		getDelegated().setBorderDash(borderDash);
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
		return ArrayListHelper.build(getDelegated().getBorderDash());
	}

	/**
	 * Sets the line dash pattern offset or "phase".
	 * 
	 * @param borderDashOffset Offset for line dashes.
	 */
	public void setBorderDashOffset(int borderDashOffset) {
		getDelegated().setBorderDashOffset(borderDashOffset);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the line dash pattern offset or "phase".
	 * 
	 * @return Offset for line dashes. If not set, default is 0
	 */
	public int getBorderDashOffset() {
		return AssignHelper.check(getDelegated().getBorderDashOffset(), getDefaultValues().getBorderDashOffset());
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
			getDelegated().setLineWidth(lineWidth.get(0));
		} else {
			getDelegated().setLineWidth(lineWidth);
		}
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the stroke widths of grid lines.
	 * 
	 * @return lineWidth stroke widths of grid lines. If not set, default is 1.
	 */
	public List<Integer> getLineWidth() {
		ArrayIntegerList result = null;
		// loads stored data
		Object values = getDelegated().getLineWidth();
		
		if (Array.isArray(values)) {
			ArrayInteger array = (ArrayInteger)values;
			if (array.length() == 0) {
				result = new ArrayIntegerList();
				result.add(getDefaultValues().getLineWidth());
			} else {
				result = ArrayListHelper.build(array);
			}
		} else {
			result = new ArrayIntegerList();
			result.add(AssignHelper.check(values, getDefaultValues().getLineWidth()));
		}
		return result;
	}

	/***
	 * If true, draw border at the edge between the axis and the chart area.
	 * 
	 * @param drawBorder If true, draw border at the edge between the axis and the chart area.
	 */
	public void setDrawBorder(boolean drawBorder) {
		getDelegated().setDrawBorder(drawBorder);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If true, draw border at the edge between the axis and the chart area.
	 * 
	 * @return If true, draw border at the edge between the axis and the chart area. If not set, default is true
	 */
	public boolean isDrawBorder() {
		return AssignHelper.check(getDelegated().isDrawBorder(), getDefaultValues().isDrawBorder());
	}

	/**
	 * If true, draw lines on the chart area inside the axis lines. This is useful when there are multiple axes and you need to
	 * control which grid lines are drawn.
	 * 
	 * @param drawOnChartArea If true, draw lines on the chart area inside the axis lines. This is useful when there are
	 *            multiple axes and you need to control which grid lines are drawn.
	 */
	public void setDrawOnChartArea(boolean drawOnChartArea) {
		getDelegated().setDrawOnChartArea(drawOnChartArea);
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
		return AssignHelper.check(getDelegated().isDrawOnChartArea(), getDefaultValues().isDrawOnChartArea());
	}

	/**
	 * If true, draw lines beside the ticks in the axis area beside the chart.
	 * 
	 * @param drawTicks If true, draw lines beside the ticks in the axis area beside the chart.
	 */
	public void setDrawTicks(boolean drawTicks) {
		getDelegated().setDrawTicks(drawTicks);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If true, draw lines beside the ticks in the axis area beside the chart.
	 * 
	 * @return If true, draw lines beside the ticks in the axis area beside the chart. If not set, default is true
	 */
	public boolean isDrawTicks() {
		return AssignHelper.check(getDelegated().isDrawTicks(), getDefaultValues().isDrawTicks());
	}

	/**
	 * Sets the length in pixels that the grid lines will draw into the axis area.
	 * 
	 * @param tickMarkLength Length in pixels that the grid lines will draw into the axis area.
	 */
	public void setTickMarkLength(int tickMarkLength) {
		getDelegated().setTickMarkLength(tickMarkLength);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the length in pixels that the grid lines will draw into the axis area.
	 * 
	 * @return Length in pixels that the grid lines will draw into the axis area. If not set, default is 10
	 */
	public int getTickMarkLength() {
		return AssignHelper.check(getDelegated().getTickMarkLength(), getDefaultValues().getTickMarkLength());
	}

	/**
	 * Sets the stroke width of the grid line for the first index (index 0).
	 * 
	 * @param zeroLineWidth Stroke width of the grid line for the first index (index 0).
	 */
	public void setZeroLineWidth(int zeroLineWidth) {
		getDelegated().setZeroLineWidth(zeroLineWidth);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the stroke width of the grid line for the first index (index 0).
	 * 
	 * @return Stroke width of the grid line for the first index (index 0). If not set, default is 1
	 */
	public int getZeroLineWidth() {
		return AssignHelper.check(getDelegated().getZeroLineWidth(), getDefaultValues().getZeroLineWidth());
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
		getDelegated().setZeroLineColor(zeroLineColor);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the stroke color of the grid line for the first index (index 0).
	 * 
	 * @return Stroke color of the grid line for the first index (index 0). If not set, default is 'rgba(0, 0, 0, 0.25)'
	 */
	public String getZeroLineColorAsString() {
		return AssignHelper.check(getDelegated().getZeroLineColor(), getDefaultValues().getZeroLineColor());
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
		getDelegated().setZeroLineBorderDash(zeroLineBorderDash);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the length and spacing of dashes of the grid line for the first index (index 0).
	 * 
	 * @return the length and spacing of dashes of the grid line for the first index (index 0).
	 */
	public List<Integer> getZeroLineBorderDash() {
		return ArrayListHelper.build(getDelegated().getZeroLineBorderDash());
	}

	/**
	 * Sets the offset for line dashes of the grid line for the first index (index 0).
	 * 
	 * @param zeroLineBorderDashOffset the offset for line dashes of the grid line for the first index (index 0).
	 */
	public void setZeroLineBorderDashOffset(int zeroLineBorderDashOffset) {
		getDelegated().setZeroLineBorderDashOffset(zeroLineBorderDashOffset);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the offset for line dashes of the grid line for the first index (index 0).
	 * 
	 * @return the offset for line dashes of the grid line for the first index (index 0). if not set, default is 0
	 */
	public int getZeroLineBorderDashOffset() {
		return AssignHelper.check(getDelegated().getZeroLineBorderDashOffset(), getDefaultValues().getZeroLineBorderDashOffset());
	}

	/**
	 * If true, grid lines will be shifted to be between labels. This is set to true in the bar chart by default.
	 * 
	 * @param offsetGridLines If true, grid lines will be shifted to be between labels.
	 */
	public void setOffsetGridLines(boolean offsetGridLines) {
		getDelegated().setOffsetGridLines(offsetGridLines);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If true, grid lines will be shifted to be between labels. This is set to true in the bar chart by default.
	 * 
	 * @return If true, grid lines will be shifted to be between labels. If not set, default is false.
	 */
	public boolean isOffsetGridLines() {
		return AssignHelper.check(getDelegated().isOffsetGridLines(), getDefaultValues().isOffsetGridLines());
	}
	
	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.BaseModel#addToParent()
	 */
	@Override
	protected void addToParent() {
		if (getParent().getDelegated().getGridLines() == null) {
			getParent().getDelegated().setGridLines(getDelegated());
		}
	}

}