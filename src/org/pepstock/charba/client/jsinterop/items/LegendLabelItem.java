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
package org.pepstock.charba.client.jsinterop.items;

import java.util.List;

import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.enums.CapStyle;
import org.pepstock.charba.client.enums.JoinStyle;
import org.pepstock.charba.client.enums.PointStyle;
import org.pepstock.charba.client.jsinterop.commons.ArrayInteger;
import org.pepstock.charba.client.jsinterop.commons.ArrayString;
import org.pepstock.charba.client.jsinterop.commons.NativeName;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * This object is created by CHART.JS and passed to all callbacks and events handlers related to legend entity.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name=NativeName.OBJECT)
public final class LegendLabelItem extends LegendItem {
	
	@JsProperty(name = "datasetIndex")
	native void setNativeDatasetIndex(int datasetIndex);

	@JsProperty(name = "index")
	native void setNativeIndex(int index);

	@JsProperty(name = "text")
	native void setNativeText(String text);

	@JsProperty(name = "fillStyle")
	native void setNativeFillStyle(String fillStyle);

	@JsProperty(name = "lineCap")
	native void setNativeLineCap(String lineCap);

	@JsProperty(name = "lineDash")
	native void setNativeLineDash(ArrayInteger lineDash);

	@JsProperty(name = "lineDashOffset")
	native void setNativeLineDashOffset(int lineDashOffset);

	@JsProperty(name = "lineJoin")
	native void setNativeLineJoin(String lineJoin);

	@JsProperty(name = "lineWidth")
	native void setNativeLineWidth(Object lineWidth);

	@JsProperty(name = "strokeStyle")
	native void setNativeStrokeStyle(Object strokeStyle);

	@JsProperty(name = "pointStyle")
	native void setNativePointStyle(Object pointStyle);

	/**
	 * Sets the dataset index of the chart
	 * 
	 * @param datasetIndex the dataset index of the chart
	 */
	@JsOverlay
	public void setDatasetIndex(int datasetIndex) {
		setNativeDatasetIndex(datasetIndex);
	}

	/**
	 * Sets the dataset index of the chart (for POLAR and PIE charts)
	 * 
	 * @param index the dataset index of the chart (for POLAR and PIE charts)
	 */
	@JsOverlay
	public void setIndex(int index) {
		setNativeIndex(index);
	}

	/**
	 * Sets the label that will be displayed
	 * 
	 * @param text the label that will be displayed
	 */
	@JsOverlay
	public void setText(String text) {
		setNativeText(text);
	}

	/**
	 * Returns the fill style of the legend box
	 * 
	 * @return the fill style of the legend box
	 */
	/**
	 * Sets the fill style of the legend box
	 * 
	 * @param color the fill style of the legend box
	 */
	@JsOverlay
	public void setFillStyle(IsColor color) {
		setNativeFillStyle(color.toRGBA());
	}

	/**
	 * Sets how the end points of every box border are drawn. There are three possible values for this property and those are:
	 * butt, round and square.
	 * 
	 * @param style how the end points of every box border are drawn.
	 * @see org.pepstock.charba.client.enums.CapStyle
	 */
	@JsOverlay
	public void setLineCap(CapStyle capstyle) {
		setNativeLineCap(capstyle.name());
	}

	/**
	 * Sets the box border dash pattern used when stroking lines, using an array of values which specify alternating lengths of
	 * lines and gaps which describe the pattern.
	 * 
	 * @param lineDash the box border dash pattern used when stroking lines, using an array of values which specify alternating
	 *            lengths of lines and gaps which describe the pattern.
	 */
	@JsOverlay
	public void setLineDash(List<Integer> lineDash) {
		setNativeLineDash(ArrayInteger.of(lineDash));
	}

	/**
	 * Sets the box border dash pattern offset or "phase".
	 * 
	 * @param lineDashOffset the box border dash pattern offset or "phase".
	 */
	@JsOverlay
	public void setLineDashOffset(int lineDashOffset) {
		setNativeLineDashOffset(lineDashOffset);
	}

	/**
	 * Sets how two connecting segments (of box border) with non-zero lengths in a shape are joined together (degenerate
	 * segments with zero lengths, whose specified endpoints and control points are exactly at the same position, are
	 * skipped).<br>
	 * There are three possible values for this property: round, bevel and miter.
	 * 
	 * @param style There are three possible values for this property: round, bevel and miter.
	 * @see org.pepstock.charba.client.enums.JoinStyle
	 */
	@JsOverlay
	public void setLineJoin(JoinStyle join) {
		setNativeLineJoin(join.name());
	}

	/**
	 * Sets the width of box border in pixels.
	 * 
	 * @param lineWidths the width of box border in pixels.
	 */
	@JsOverlay
	public void setLineWidth(int lineWidth) {
		setNativeLineWidth(lineWidth);
	}

	/**
	 * Sets the width of box border in pixels.
	 * 
	 * @param lineWidths the width of box border in pixels.
	 */
	@JsOverlay
	public void setLineWidth(int... lineWidths) {
		setNativeLineWidth(ArrayInteger.of(lineWidths));
	}

	/**
	 * Sets the stroke style of the legend box
	 * 
	 * @param color the stroke style of the legend box
	 */
	@JsOverlay
	public void setStrokeStyle(IsColor color) {
		setNativeStrokeStyle(color.toRGBA());
	}

	/**
	 * Sets the stroke style of the legend box
	 * 
	 * @param color the stroke style of the legend box
	 */
	@JsOverlay
	public void setStrokeStyle(IsColor... colors) {
		setNativeStrokeStyle(ArrayString.of(colors));
	}

	/**
	 * Sets the style of the legend box (only used if usePointStyle is true)
	 * 
	 * @param style the style of the legend box
	 * @see org.pepstock.charba.client.enums.PointStyle
	 */
	@JsOverlay
	public void setPointStyle(PointStyle style) {
		setNativePointStyle(style);
	}
	
	/**
	 * Sets the style of the legend box (only used if usePointStyle is true)
	 * 
	 * @param style the style of the legend box
	 * @see org.pepstock.charba.client.enums.PointStyle
	 */
	@JsOverlay
	public void setPointStyle(PointStyle... styles) {
		setNativePointStyle(ArrayString.of(styles));
	}

}