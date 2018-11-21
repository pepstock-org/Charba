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

import org.pepstock.charba.client.jsinterop.commons.ArrayInteger;
import org.pepstock.charba.client.jsinterop.commons.NativeName;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * The grid line configuration defines options for the grid lines that run perpendicular to the axis.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = NativeName.OBJECT)
final class NativeGridLines extends NativeObject {

	/**
	 * If false, do not display grid lines for this axis.
	 * 
	 * @param display If false, do not display grid lines for this axis.
	 */
	@JsProperty
	native void setDisplay(boolean display);

	/**
	 * If false, do not display grid lines for this axis.
	 * 
	 * @return If false, do not display grid lines for this axis. Default is true.
	 */
	@JsProperty
	native boolean isDisplay();

	/**
	 * The color of the grid lines. If specified as an array, the first color applies to the first grid line, the second to the
	 * second grid line and so on.
	 * 
	 * @param color The color of the grid lines. If specified as an array, the first color applies to the first grid line, the
	 *            second to the second grid line and so on.
	 */
	@JsProperty
	native void setColor(Object colors);

	/**
	 * The color of the grid lines. If specified as an array, the first color applies to the first grid line, the second to the
	 * second grid line and so on.
	 * 
	 * @return the list of colors of the grid lines. If not set, default is only 1 value 'rgba(0, 0, 0, 0.1)'
	 */
	@JsProperty
	native Object getColor();

	/**
	 * Sets the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines
	 * and gaps which describe the pattern.
	 * 
	 * @param borderDash the line dash pattern used when stroking lines
	 */
	@JsProperty
	native void setBorderDash(ArrayInteger borderDash);

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of
	 * lines and gaps which describe the pattern.
	 * 
	 * @return the line dash pattern used when stroking lines
	 */
	@JsProperty
	native ArrayInteger getBorderDash();

	/**
	 * Sets the line dash pattern offset or "phase".
	 * 
	 * @param borderDashOffset Offset for line dashes.
	 */
	@JsProperty
	native void setBorderDashOffset(int borderDashOffset);

	/**
	 * Returns the line dash pattern offset or "phase".
	 * 
	 * @return Offset for line dashes. If not set, default is 0
	 */
	@JsProperty
	native int getBorderDashOffset();

	/**
	 * Sets the stroke widths of grid lines.
	 * 
	 * @param lineWidth stroke widths of grid lines.
	 */
	@JsProperty
	native void setLineWidth(Object lineWidth);

	/**
	 * Returns the stroke widths of grid lines.
	 * 
	 * @return lineWidth stroke widths of grid lines. If not set, default is 1.
	 */
	@JsProperty
	native Object getLineWidth();

	/***
	 * If true, draw border at the edge between the axis and the chart area.
	 * 
	 * @param drawBorder If true, draw border at the edge between the axis and the chart area.
	 */
	@JsProperty
	native void setDrawBorder(boolean drawBorder);

	/**
	 * If true, draw border at the edge between the axis and the chart area.
	 * 
	 * @return If true, draw border at the edge between the axis and the chart area. If not set, default is true
	 */
	@JsProperty
	native boolean isDrawBorder();

	/**
	 * If true, draw lines on the chart area inside the axis lines. This is useful when there are multiple axes and you need to
	 * control which grid lines are drawn.
	 * 
	 * @param drawOnChartArea If true, draw lines on the chart area inside the axis lines. This is useful when there are
	 *            multiple axes and you need to control which grid lines are drawn.
	 */
	@JsProperty
	native void setDrawOnChartArea(boolean drawOnChartArea);

	/**
	 * If true, draw lines on the chart area inside the axis lines. This is useful when there are multiple axes and you need to
	 * control which grid lines are drawn.
	 * 
	 * @return If true, draw lines on the chart area inside the axis lines. This is useful when there are multiple axes and you
	 *         need to control which grid lines are drawn. If not set, default is true
	 */
	@JsProperty
	native boolean isDrawOnChartArea();

	/**
	 * If true, draw lines beside the ticks in the axis area beside the chart.
	 * 
	 * @param drawTicks If true, draw lines beside the ticks in the axis area beside the chart.
	 */
	@JsProperty
	native void setDrawTicks(boolean drawTicks);

	/**
	 * If true, draw lines beside the ticks in the axis area beside the chart.
	 * 
	 * @return If true, draw lines beside the ticks in the axis area beside the chart. If not set, default is true
	 */
	@JsProperty
	native boolean isDrawTicks();

	/**
	 * Sets the length in pixels that the grid lines will draw into the axis area.
	 * 
	 * @param tickMarkLength Length in pixels that the grid lines will draw into the axis area.
	 */
	@JsProperty
	native void setTickMarkLength(int tickMarkLength);

	/**
	 * Returns the length in pixels that the grid lines will draw into the axis area.
	 * 
	 * @return Length in pixels that the grid lines will draw into the axis area. If not set, default is 10
	 */
	@JsProperty
	native int getTickMarkLength();

	/**
	 * Sets the stroke width of the grid line for the first index (index 0).
	 * 
	 * @param zeroLineWidth Stroke width of the grid line for the first index (index 0).
	 */
	@JsProperty
	native void setZeroLineWidth(int zeroLineWidth);

	/**
	 * Returns the stroke width of the grid line for the first index (index 0).
	 * 
	 * @return Stroke width of the grid line for the first index (index 0). If not set, default is 1
	 */
	@JsProperty
	native int getZeroLineWidth();

	/**
	 * Sets the stroke color of the grid line for the first index (index 0).
	 * 
	 * @param zeroLineColor Stroke color of the grid line for the first index (index 0).
	 */
	@JsProperty
	native void setZeroLineColor(String zeroLineColor);

	/**
	 * Returns the stroke color of the grid line for the first index (index 0).
	 * 
	 * @return Stroke color of the grid line for the first index (index 0). If not set, default is 'rgba(0, 0, 0, 0.25)'
	 */
	@JsProperty
	native String getZeroLineColor();

	/**
	 * Sets the length and spacing of dashes of the grid line for the first index (index 0).
	 * 
	 * @param zeroLineBorderDash length and spacing of dashes of the grid line for the first index (index 0).
	 */
	@JsProperty
	native void setZeroLineBorderDash(ArrayInteger zeroLineBorderDash);

	/**
	 * Returns the length and spacing of dashes of the grid line for the first index (index 0).
	 * 
	 * @return the length and spacing of dashes of the grid line for the first index (index 0).
	 */
	@JsProperty
	native ArrayInteger getZeroLineBorderDash();

	/**
	 * Sets the offset for line dashes of the grid line for the first index (index 0).
	 * 
	 * @param zeroLineBorderDashOffset the offset for line dashes of the grid line for the first index (index 0).
	 */
	@JsProperty
	native void setZeroLineBorderDashOffset(int zeroLineBorderDashOffset);

	/**
	 * Returns the offset for line dashes of the grid line for the first index (index 0).
	 * 
	 * @return the offset for line dashes of the grid line for the first index (index 0). if not set, default is 0
	 */
	@JsProperty
	native int getZeroLineBorderDashOffset();

	/**
	 * If true, grid lines will be shifted to be between labels. This is set to true in the bar chart by default.
	 * 
	 * @param offsetGridLines If true, grid lines will be shifted to be between labels.
	 */
	@JsProperty
	native void setOffsetGridLines(boolean offsetGridLines);

	/**
	 * If true, grid lines will be shifted to be between labels. This is set to true in the bar chart by default.
	 * 
	 * @return If true, grid lines will be shifted to be between labels. If not set, default is false.
	 */
	@JsProperty
	native boolean isOffsetGridLines();

}